package edu.ncsu.csc216.wolf_tickets.model.tickets;


/**
 * Class active ticket list extends abstract category
 * @author jason
 *
 */
public class ActiveTicketList extends AbstractCategory {

	/**
	 * Default active ticket list string name variable
	 */
	public final static String ACTIVE_TASKS_NAME = "Active Tickets";
	
	
	/**
	 * Constructor for the ActiveTicketList
	 */
	public ActiveTicketList() {
		super(ACTIVE_TASKS_NAME, 0);
	}

	/**
	 * Adds a ticket to the ticket list
	 * @param t the ticket to be added
	 * @throws IllegalArgumentException if the active ticket list cannot be added
	 */
	@Override
	public void addTicket(Ticket t) {
		if(t.isActive()) {
			getTickets().add(t);
			t.addCategory(this);
		}
		else {
			throw new IllegalArgumentException("Cannot add to Active Tickets.");
		} 
	}
	
	/**
	 * The categoryName to be set
	 * @param categoryName the string to set the categoryName to 
	 * @throws IllegalArgumentException if the ticket list cannot be edited
	 */
	@Override
	public void setCategoryName(String categoryName) {
		if(!(ACTIVE_TASKS_NAME.equals(categoryName))) {
			throw new IllegalArgumentException("The Active Tickets list may not be edited.");
		}
		super.setCategoryName(categoryName);
		
	}
	
	/**
	 * Returns a String 2D array of the tickets
	 * @return the 2D string array of tickets
	 */
	@Override
	public String[][] getTicketsAsArray() {
		if (this.getTickets().size() == 0) {
			return new String[0][0];
		}
		String[][] arr = new String[this.getTickets().size()][2];
		for (int i = 0; i < this.getTickets().size(); i++) {
			Ticket ticket = this.getTickets().get(i);
			arr[i][0] = ticket.getCategoryName();
			arr[i][1] = ticket.getTicketName();
		}
		return arr;
		
	}
	
	/**
	 * Method to clear the ticket list
	 */
	public void clearTickets() {
		int size = super.getTickets().size();
		for(int i = 0; i < size; i++) {
			super.removeTicket(0);
		}
	}


}
