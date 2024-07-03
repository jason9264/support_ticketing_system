package edu.ncsu.csc216.wolf_tickets.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_tickets.model.group.Group;
import edu.ncsu.csc216.wolf_tickets.model.tickets.AbstractCategory;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Category;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Ticket;

/**
 * The class implementation for GroupReader for the wolf ticket program
 * @author jason wang
 */
public class GroupReader {

	/**
	 * Reads the entire group file
	 * @param groupFile the file to be read in
	 * @throws IllegalArgumentException if the file cnanot be loaded or read
	 * @return a Group that has been read in
	 */
	public static Group readGroupFile(File groupFile) {
		Scanner scnr;
		try {
			scnr = new Scanner(groupFile);
			
		} catch(FileNotFoundException e1) {
			Group blank = null;
			return blank;
		}
		
		Group group = null;
		String line;
		if (scnr.hasNextLine()) {
			try {
				line = scnr.nextLine();
				if (line.startsWith("!")) {
					String groupName = line.substring(1).trim();
					group = new Group(groupName);
				}
				else {
					scnr.close();
					throw new IllegalArgumentException();
				}
			} catch (Exception e) {
				throw new IllegalArgumentException("Unable to load file.");
			}
		}
		Ticket ticket = null;
		Category currentCat = null;
		String ticketText = "";
		try {
			while (scnr.hasNextLine()) {
				line = "";
				line = scnr.nextLine();
				if (line.startsWith("#")) {
					
					if (!ticketText.isEmpty()) {
						ticket = processTicket(currentCat, ticketText.trim());
						currentCat.addTicket(ticket);
					}
					currentCat = processCategory(line);
					if(currentCat != null) {
						group.addCategory(currentCat);
					}
					ticketText = "";
				}
				else if (line.startsWith("*") && currentCat != null) {
					if (!ticketText.isEmpty()) {
						try {
							ticket = processTicket(currentCat, ticketText.trim());
							currentCat.addTicket(ticket);
						} catch (Exception e1) {
							//nun
						}
						ticketText = "";
					}
					ticketText = ticketText.concat(line.substring(1).trim()).concat("\n");
				}
				else {
					ticketText = ticketText.concat(line.trim()).concat("\n");
				}
			}
			scnr.close();
			
			if(currentCat != null) {
				if(!ticketText.isEmpty()) {
					ticket = processTicket(currentCat, ticketText.trim());
					currentCat.addTicket(ticket);
				}
				group.addCategory(currentCat);
			}
		}
		catch (Exception e2) {
			// nun
		}
		if (group != null) 
			group.setCurrentCategory(group.getCategoriesNames()[0]);
			return group;
		
	}
	
	/**
	 * Processes a single category
	 * @param categoryText the text block to be read in about the category
	 * @return the category that is read in
	 */
	private static Category processCategory(String categoryText) {	
		String[] categoryHeader = categoryText.split(",");
		String categoryName = categoryHeader[0].substring(1).trim();
		int completedCount = Integer.parseInt(categoryHeader[1].trim());
	    Category category = new Category(categoryName, completedCount);
	    return category;
	}
	
	/**
	 * Processes a single ticket
	 * @param category the category the ticket is in
	 * @param ticketText the text block about the ticket
	 * @return a ticket that is read in
	 */
	private static Ticket processTicket(AbstractCategory category, String ticketText) {	
		try {
		int newLineIndex = ticketText.indexOf('\n');
		String header = ticketText.substring(0, newLineIndex);
		String[] parts = header.split(",");
	    String name = parts[0]; // remove leading * character
	    boolean status = false;
	    if(parts.length == 2) {
	    	status = true;
	    }
	    String description = ticketText.substring(newLineIndex + 1);
	    return new Ticket(name, description, status);
		}
		catch (Exception e) {
			String header = ticketText;
			String[] parts = header.split(",");
			String name = parts[0];
			boolean status = false;
			if (parts.length == 2) {
				status = true;
			}
			String description = "";
			return new Ticket(name, description, status);
		}
	}
}
