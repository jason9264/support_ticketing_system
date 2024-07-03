package edu.ncsu.csc216.wolf_tickets.model.io;

import java.io.File;
import java.io.FileWriter;

import edu.ncsu.csc216.wolf_tickets.model.tickets.Category;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Ticket;
import edu.ncsu.csc216.wolf_tickets.model.util.ISortedList;

/**
 * Group writer class implementation for the wolf ticket application
 * @author jason
 *
 */
public class GroupWriter {

	/**
	 * Method to write the group to a file
	 * @param groupFile the name of the file to be written to or as
	 * @param groupName the name of the groups to write down
	 * @param categories the categories in the group to write as
	 * @throws IllegalArgumentException if the file is unable to be saved
	 */
	public static void writeGroupFile(File groupFile, String groupName, ISortedList<Category> categories) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(groupFile);
			writer.write("! " + groupName + "\n");
			for (int i = 0; i < categories.size(); i++) {
				Category category = categories.get(i);
				writer.write("# " + category.getCategoryName() + "," + category.getCompletedCount() + "\n");
				for (int j = 0; j < category.getTickets().size(); j++) {
					Ticket ticket = category.getTickets().get(j);
					writer.write(ticket.toString() + "\n");
				}
				
			}
			writer.close();
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to save file.");
		}
	}
}
