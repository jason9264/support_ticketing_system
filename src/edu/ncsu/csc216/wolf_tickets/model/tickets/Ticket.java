package edu.ncsu.csc216.wolf_tickets.model.tickets;

import edu.ncsu.csc216.wolf_tickets.model.util.ISwapList;
import edu.ncsu.csc216.wolf_tickets.model.util.SwapList;

/**
 * Implementation for the ticket class
 * @author jason
 *
 */
public class Ticket {

	/**
	 * The variable for tickets name
	 */
	private String ticketName;
	
	/**
	 * The variable for description of the ticket
	 */
	private String ticketDescription;
	
	/**
	 * The variable for tracking ticket active status
	 */
	private boolean active;
	
	/**
	 * ISwapList of categories for tickets to know what kind they are
	 */
	private ISwapList<AbstractCategory> categories;
	
	/**
	 * The constructor for ticket
	 * @param ticketName the name of the ticket to be made as
	 * @param ticketDescription the description of the ticket to be set
	 * @param active the status of the ticket ot be set
	 */
	public Ticket(String ticketName, String ticketDescription, boolean active) {
		setTicketName(ticketName);
		setTicketDescription(ticketDescription);
		setActive(active);
		this.categories = new SwapList<AbstractCategory>();
	}
	
	/**
	 * Method to get the ticket name
	 * @return the ticket name as a string
	 */
	public String getTicketName() {
		return ticketName;
	}
	
	/**
	 * Method that Sets the ticket name
	 * @param ticketName the name of the ticket to set
	 * @throws IllegalArgumentException if the ticket information is incomplete
	 */
	public void setTicketName(String ticketName) {
		if (ticketName == null || "".equals(ticketName)) {
			throw new IllegalArgumentException("Incomplete ticket information.");
		}
		this.ticketName = ticketName;
	}
	
	/**
	 * Method to get the ticket decription
	 * @return a string of the ticket's description
	 */
	public String getTicketDescription() {
		return ticketDescription;
		
	}
	
	/**
	 * Method to set the ticket description
	 * @param ticketDescription the description to set
	 * @throws IllegalArgumentException if the ticketDescription is not complete
	 */
	public void setTicketDescription(String ticketDescription) {
		if (ticketDescription == null) {
			throw new IllegalArgumentException("Incomplete ticket information.");
		}
		this.ticketDescription = ticketDescription;
	}
	
	/**
	 * Method to return the status of the ticket
	 * @return the status of the ticket boolean
	 */
	public boolean isActive() {
		return active;
		
	}
	
	/**
	 * Sets the status of the ticket
	 * @param active status of the ticket to set to
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	/**
	 * Method to get the categories name of the ticket
	 * @return the category name as a string
	 */
	public String getCategoryName() {
		try {
			if(categories.get(0) == null || "".equals(categories.get(0).getCategoryName())) {
				return "";
			}
		} catch (IndexOutOfBoundsException e) {
			return "";
		}
		
		String store = categories.get(0).getCategoryName();
		return store;

	}
	
	/**
	 * Method that adds a category to the ticket
	 * @param category the category to be added
	 * @throws IllegalArgumentException if the ticket information is incomplete
	 */
	public void addCategory(AbstractCategory category) {
		if(category == null) {
			throw new IllegalArgumentException("Incomplete ticket information.");
		}
		categories.add(category);
	}
	
	/**
	 * Completes the ticket
	 */
	public void completeTicket() {
		setActive(false);
		int size = categories.size();
		for (int i = 0; i < size; i++){
		categories.get(i).completeTicket(this);
		}
	}
	
	/**
	 * Returns the ticket in a string format
	 * @return a string version of the ticket
	 */
	public String toString() {
		String activeStatus = "";
		if(active) {
			activeStatus = ",active";
		}
	    return "* " + getTicketName() + activeStatus + "\n" + getTicketDescription();

		
	}
	
}
