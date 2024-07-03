package edu.ncsu.csc216.wolf_tickets.model.group;

import java.io.File;

import edu.ncsu.csc216.wolf_tickets.model.io.GroupWriter;
import edu.ncsu.csc216.wolf_tickets.model.tickets.AbstractCategory;
import edu.ncsu.csc216.wolf_tickets.model.tickets.ActiveTicketList;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Category;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Ticket;
import edu.ncsu.csc216.wolf_tickets.model.util.ISortedList;
import edu.ncsu.csc216.wolf_tickets.model.util.SortedList;

/**
 * Group class for wolf ticket
 * @author jason wang
 *
 */
public class Group {

	/**
	 * String variabe for this groupName
	 */
	private String groupName;
	
	
	/**
	 * Variable that stores the isChanged value for this group
	 */
	private boolean isChanged;
	
	/**
	 * Field that Holds the activeTicketList
	 */
	private ActiveTicketList activeTicketList;
	
	/**
	 * Holds the current category
	 */
	private AbstractCategory currentCategory;
	
	/**
	 * A ISortedList of categories that hold the categories
	 */
	private ISortedList<Category> categories;
	
	/**
	 * Constructor for group
	 * @param groupName the group's name to use for this group
	 */
	public Group(String groupName) {
		categories = new SortedList<Category>();
		setChanged(true);
		setGroupName(groupName);
		activeTicketList = new ActiveTicketList();
		currentCategory = activeTicketList;
	}
	
	/**
	 * Method to save the groups information to a file
	 * @param groupFile the name of the file to save the group to
	 */
	public void saveGroup(File groupFile) {
		GroupWriter.writeGroupFile(groupFile, groupName, categories);
		setChanged(false);
	}
	
	/**
	 * Gets the groups name
	 * @return the groups name
	 */
	public String getGroupName() {
		return groupName;
		
	}
	
	/**
	 * Sets the groups name for use
	 * @param groupName the group name to set to
	 * @throws IllegalArgumentException on entering an invalid group name
	 */
	private void setGroupName(String groupName) {
		if (groupName == null || "".equals(groupName) || "Active Tickets".equals(groupName)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		this.groupName = groupName;
	}
	
	/**
	 * Gets the isChanged value and checks
	 * @return the is changed boolean value
	 */
	public boolean isChanged() {
		return isChanged;
		
	}
	
	/**
	 * Sets the isChanged value
	 * @param changed the value to change
	 */
	public void setChanged(boolean changed) {
		isChanged = changed;
		
	}
	
	/**
	 * adds a category to the group
	 * @param category the category to be added
	 * @throws IllegalArgumentException on invalid name entry
	 */
	public void addCategory(Category category) {
		String hold = category.getCategoryName();
		if ("Active Tickets".equals(hold)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		for(int i = 0; i < categories.size(); i++) {
				if (categories.get(i).getCategoryName() == category.getCategoryName()) {
					throw new IllegalArgumentException("Invalid name.");
				}
		}
		categories.add(category);
		setCurrentCategory(category.getCategoryName()); 
	}
	
	/**
	 * Gets the categories name as a string array
	 * @return a string array of the names of all the categories
	 */
	public String[] getCategoriesNames() {
		
		if (categories.size() == 0) {
			String [] str = new String[1];
			str[0] = "Active Tickets";
			return str;
		}
		
		else {
		String [] names = new String[categories.size() + 1];
		names[0] = activeTicketList.getCategoryName();
		for(int i = 0; i < categories.size(); i++) {
			names[i + 1] = categories.get(i).getCategoryName();
			}
		return names;
		}
	}
	
	/**
	 * Gets the active ticket list
	 */
	private void getActiveTicketList() {
		activeTicketList.clearTickets();
		for (int i = 0; i < categories.size(); i++) {
		    Category category = categories.get(i);
		    for (int j = 0; j < category.getTickets().size(); j++) {
		        Ticket ticket = category.getTickets().get(j);
		        if (ticket.isActive()) {
		            activeTicketList.addTicket(ticket);
		        }
		    }
		}
	}
	
	/**
	 * Sets the current category
	 * @param categoryName the name of the category to set
	 */
	public void setCurrentCategory(String categoryName) {
		boolean found = false;
		for(int i = 0; i < categories.size(); i++) {
			if(categories.get(i).getCategoryName().equals(categoryName)) {
				currentCategory = categories.get(i);
				found = true;
			}			
		}
		if(!found) {
			getActiveTicketList();
			currentCategory = activeTicketList;
		}
	}
	
	/**
	 * Gets the current Category
	 * @return and abstract category, category
	 */
	public AbstractCategory getCurrentCategory() {
		return currentCategory;
		
	}
	
	/**
	 * Edit the current category
	 * @param categoryName the name of the category to edit
	 * @throws IllegalArgumentException on invalid class and names
	 */
	public void editCategory(String categoryName) {
		if (currentCategory instanceof ActiveTicketList) {
	        throw new IllegalArgumentException("The Active Tickets list may not be edited.");
	    }
		if ("Active Tickets".equals(categoryName)){
	        throw new IllegalArgumentException("Invalid name.");
	    }
	    for (int i = 0; i < categories.size(); i++) {
	        if (categoryName == categories.get(i).getCategoryName()){
	            throw new IllegalArgumentException("Invalid name.");
	        }
	    }
	    
	    int idx = 0;
	    
	    for (int i = 0; i < categories.size(); i++) {
	        if (currentCategory.getCategoryName() == categories.get(i).getCategoryName()){
	        	idx = i;
	        	break;
	        }
	    }
	    
	    categories.remove(idx);
	    currentCategory.setCategoryName(categoryName);
	    categories.add((Category) currentCategory);
	    isChanged = true;

	 
	}
	
	/**
	 * Removes a category
	 * @throws IllegalArgumentException if the class is an instance of ActiveTicketList
	 */
	public void removeCategory() {
		if(currentCategory instanceof ActiveTicketList) {
			throw new IllegalArgumentException("The Active Tickets list may not be deleted.");
		}
		
	    int idx = 0;

	    for (int i = 0; i < categories.size(); i++) {
	        if (currentCategory.getCategoryName() == categories.get(i).getCategoryName()){
	        	idx = i;
	        	break;
	        }
	    }
	    
		categories.remove(idx);
		getActiveTicketList();
		currentCategory = activeTicketList;
		isChanged = true;
	}
	
	/**
	 * Adds a ticket to the group
	 * @param t the ticket to be added
	 */
	public void addTicket(Ticket t) {
		if (currentCategory instanceof Category) {
			currentCategory.addTicket(t);
			if (t.isActive()) {
				getActiveTicketList();
			}
			isChanged = true;

		}
	}
	
	/**
	 * Edits the specific ticket
	 * @param idx the index of the ticket
	 * @param ticketName name of the ticket
	 * @param ticketDescription description of the ticket
	 * @param active status of the ticket whether active or not
	 */
	public void editTicket(int idx, String ticketName, String ticketDescription, boolean active) {
		if(currentCategory instanceof Category) {
			currentCategory.getTicket(idx).setTicketName(ticketName);
			currentCategory.getTicket(idx).setTicketDescription(ticketDescription);
			currentCategory.getTicket(idx).setActive(active);
			getActiveTicketList();
			isChanged = true;
		}
	}
	
}
