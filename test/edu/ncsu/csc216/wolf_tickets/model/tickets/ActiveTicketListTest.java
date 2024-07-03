package edu.ncsu.csc216.wolf_tickets.model.tickets;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * JUnit test cases for ActiveTicketList class
 * @author Jason Wang
 *
 */
public class ActiveTicketListTest {
	/**ActiveTicketList object used for testing */
	private ActiveTicketList activeTicketList;

	/**
	 * Test method for active ticket List construction
	 */
	@Test
	public void testActiveTicketList() {
		activeTicketList = new ActiveTicketList();

		assertEquals(0, activeTicketList.getCompletedCount());
	}

	/**
	 * Test method for add ticket
	 */
	@Test
	public void testAddTicket() {
		activeTicketList = new ActiveTicketList();
		Ticket ticket = new Ticket("ticket", "Test", true);
		activeTicketList.addTicket(ticket);
		assertEquals(ticket, activeTicketList.getTicket(0));
		Ticket inactiveTicket = new Ticket("inactive", "test", false);
		Exception e = assertThrows(IllegalArgumentException.class, 
				() -> activeTicketList.addTicket(inactiveTicket));
		assertEquals("Cannot add to Active Tickets.", e.getMessage());		
	}

	/**
	 * Test method for getting the category name
	 */
	@Test
	public void testSetCategoryName() {
		activeTicketList = new ActiveTicketList();
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> activeTicketList.setCategoryName("New"));
		assertEquals("The Active Tickets list may not be edited.", e.getMessage());	
	}

	/**
	 * Test method for get tickets as array
	 */
	@Test
	public void testGetTicketsAsArray() {
		activeTicketList = new ActiveTicketList();
		assertEquals(0, activeTicketList.getTicketsAsArray().length);
		
		Ticket ticket = new Ticket("ticket", "test", true);
		activeTicketList.addTicket(ticket);
		assertEquals(1, activeTicketList.getTicketsAsArray().length);
		assertEquals(2, activeTicketList.getTicketsAsArray()[0].length);
	}

	/**
	 * Test method for clear Ticket
	 */
	@Test
	public void testClearTickets() {
		activeTicketList = new ActiveTicketList();
		Ticket ticket = new Ticket("ticket", "test", true);
		activeTicketList.addTicket(ticket);
		assertEquals(1, activeTicketList.getTickets().size());
		activeTicketList.clearTickets();
		assertEquals(0, activeTicketList.getTickets().size());
	}
	
	/**
	 * Test method for clear Ticket ts test
	 */
	@Test
	public void tsClearTicketDupe() {
		activeTicketList = new ActiveTicketList();
		activeTicketList.addTicket(new Ticket("ticket1", "test", true));
		activeTicketList.addTicket(new Ticket("ticket2", "test", true));
		activeTicketList.addTicket(new Ticket("ticket3", "test", true));
		activeTicketList.addTicket(new Ticket("ticket4", "test", true));
		activeTicketList.addTicket(new Ticket("ticket5", "test", true));
		activeTicketList.clearTickets();
		assertEquals(activeTicketList.getTickets().size(), 0);



	}
	
	

}

