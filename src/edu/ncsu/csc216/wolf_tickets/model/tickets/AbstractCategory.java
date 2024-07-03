package edu.ncsu.csc216.wolf_tickets.model.tickets;

import edu.ncsu.csc216.wolf_tickets.model.util.ISwapList;
import edu.ncsu.csc216.wolf_tickets.model.util.SwapList;

/**
 * Abstract class in the hierarchy implementation
 * @author jason
 *
 */
public abstract class AbstractCategory {

	/**
	 * The categories name
	 */
	private String categoryName;
	
	/**
	 * Keeps a count of completed tickets
	 */
	private int completedCount;
	
	/**
	 * Private ISwapList to hold the tickets
	 */
	private ISwapList<Ticket> tickets;
	
	/**
	 * Constructor for the abstract AbstractCategory class
	 * @param categoryName the name of the category to construct with
	 * @param completedCount the count of completed tickets
	 * @throws IllegalArgumentException if the completed count is an invalid value
	 */
	public AbstractCategory(String categoryName, int completedCount) {
		if(completedCount < 0) {
			throw new IllegalArgumentException("Invalid completed count.");
		}
		setCategoryName(categoryName);
		this.completedCount = completedCount;
		tickets = new SwapList<Ticket>();
		
	}
	
	/**
	 * Gets the categoryName
	 * @return the stored category name as a string
	 */
	public String getCategoryName() {
		return categoryName;
		
	}
	
	/**
	 * Sets the category name
	 * @param categoryName the string Category name to set the category name to
	 * @throws IllegalArgumentException if the name is an invalid parameter
	 */
	public void setCategoryName(String categoryName) {
		if(categoryName == null || "".equals(categoryName)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		this.categoryName = categoryName;
	}
	
	/**
	 * Gets the ticket from the list
	 * @return the ISwapList of tickets as an ISwapList
	 */
	public ISwapList<Ticket> getTickets(){
		return tickets;
		
	}
	
	/**
	 * Gets the completed count counter
	 * @return the completed count of tickets as an integer
	 */
	public int getCompletedCount() {
		return completedCount;
		
	}
	
	/**
	 * Method to add a ticket 
	 * @param t the ticket to be added
	 * @throws IllegalArgumentException if the ticket is invalid
	 */
	public void addTicket(Ticket t) {
		if ( t == null) throw new IllegalArgumentException("Invalid ticket");
		tickets.add(t);
		t.addCategory(this);
	}
	
	/**
	 * Removes a ticket from the category
	 * @param idx the index of the ticket to be removed
	 * @return the ticket that was removed
	 */
	public Ticket removeTicket(int idx) {
		return tickets.remove(idx);
		
	}
	
	/**
	 * Gets the ticket and returns it
	 * @param idx the index of the ticket to be returned
	 * @return a ticket from the chosen index
	 */
	public Ticket getTicket(int idx) {
		return tickets.get(idx);
		
	}
	
	/**
	 * Completed the ticket chosen
	 * @param t the ticket to be completed
	 */
	public void completeTicket(Ticket t) {
		for(int i = 0; i < tickets.size(); i++) {
			if(tickets.get(i).getTicketName() == t.getTicketName() 
					&& tickets.get(i).getTicketDescription() == t.getTicketDescription()) {
				tickets.remove(i);
				completedCount = completedCount + 1;
				break;
			}
		}
	}
	
	/**
	 * Abstract method
	 * @return the String 2D array either category or ticket
	 */
	public abstract String[][] getTicketsAsArray();

}
