package edu.ncsu.csc216.wolf_tickets.model.tickets;

/**
 * Category class that extends AbstractCategory and implements the comparable
 * @author jason
 *
 */
public class Category extends AbstractCategory implements Comparable<Category> { 

	/**
	 * Constructor for the Category class
	 * @param categoryName the categoryname to be created
	 * @param completedCount the completed count counter to be created with
	 */
	public Category(String categoryName, int completedCount) {
		super(categoryName, completedCount);

	}
	
	/**
	 * Method to get the category as a 2d String ticket array
	 * @return a 2d array for the category
	 */
	@Override
	public String[][] getTicketsAsArray(){
		if (this.getTickets().size() == 0) {
			return new String[0][0];
		}
		String[][] arr = new String[this.getTickets().size()][2];
		for (int i = 0; i < this.getTickets().size(); i++) {
			Ticket ticket = this.getTickets().get(i);
			arr[i][0] = Integer.toString(i);
			arr[i][1] = ticket.getTicketName();
		}
		return arr;
		
	}
	
	/**
	 * Method to compare cateogories
	 * @param otherCategory the category to compare the current one to
	 * @return integer based on comparable result
	 */
	@Override
	public int compareTo(Category otherCategory) {
		return this.getCategoryName().compareToIgnoreCase(otherCategory.getCategoryName());
		
	}
}
