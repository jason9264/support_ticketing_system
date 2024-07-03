package edu.ncsu.csc216.wolf_tickets.model.tickets;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
class AbstractCategoryTest {

	/**
	 * Category
	 */
    private AbstractCategory category;
    /**
     * Ticket1
     */
    private Ticket ticket1;
    /**
     * Ticket2
     */
    private Ticket ticket2;
    /**
     * Ticekt3
     */
    private Ticket ticket3;

    /**
     * Test the constructor
     */
    @Test
    public void testAbstractCategory() {
    	category = new Category("Category Name", 2);
        ticket1 = new Ticket("Ticket 1", "Test", true);
        ticket2 = new Ticket("Ticket 2", "Test", true);
        ticket3 = new Ticket("Ticket 3", "Test", true);
        assertEquals("Category Name", category.getCategoryName());
        assertEquals(2, category.getCompletedCount());
    }
    
    /**
     * Test the invalid count
     */
    @Test
    public void testAbstractCategoryInvalidCount() {
    	category = new Category("Category Name", 2);
        ticket1 = new Ticket("Ticket 1", "Test", true);
        ticket2 = new Ticket("Ticket 2", "Test", true);
        ticket3 = new Ticket("Ticket 3", "Test", true);
    	Exception e = assertThrows(IllegalArgumentException.class, 
				() -> new Category("Category Name", -1));
		assertEquals("Invalid completed count.", e.getMessage());
    }
    
    /**
     * Test setting name to null
     */
    @Test
    public void testSetCategoryNameNull() {
        category = new Category("Category Name", 2);
    	Exception e = assertThrows(IllegalArgumentException.class, 
				() -> category.setCategoryName(null));
		assertEquals("Invalid name.", e.getMessage());
    	
        
    }
    
    /**
     * Test setting an empty name
     */
    @Test
    public void testSetCategoryNameEmpty() {
    	category = new Category("Category Name", 2);
        ticket1 = new Ticket("Ticket 1", "Test", true);
        ticket2 = new Ticket("Ticket 2", "Test", true);
        ticket3 = new Ticket("Ticket 3", "Test", true);
    	Exception e = assertThrows(IllegalArgumentException.class, 
				() -> category.setCategoryName(""));
		assertEquals("Invalid name.", e.getMessage());
    }
    
    /**
     * Tests setting a category name
     */
    @Test
    public void testSetCategoryName() {
    	category = new Category("Category Name", 2);
        ticket1 = new Ticket("Ticket 1", "Test", true);
        ticket2 = new Ticket("Ticket 2", "Test", true);
        ticket3 = new Ticket("Ticket 3", "Test", true);
        category.setCategoryName("New Name");
        assertEquals("New Name", category.getCategoryName());
    }

    /**
     * Tests adding a ticket
     */
    @Test
    public void testAddTicket() {
    	category = new Category("Category Name", 2);
        ticket1 = new Ticket("Ticket 1", "Test", true);
        ticket2 = new Ticket("Ticket 2", "Test", true);
        ticket3 = new Ticket("Ticket 3", "Test", true);
        category.addTicket(ticket1);
        category.addTicket(ticket3);
        assertEquals(2, category.getTickets().size());
    }

    /**
     * Tests adding a ticket that is not active
     */
    @Test
    public void testAddTicketNotActive() {
    	category = new Category("Category Name", 2);
        ticket1 = new Ticket("Ticket 1", "Test", true);
        ticket2 = new Ticket("Ticket 2", "Test", false);
        ticket3 = new Ticket("Ticket 3", "Test", true);
    	category.addTicket(ticket2);
    	assertEquals(category.getTicket(0), ticket2);
    }

    /**
     * Tests removing a ticket
     */
    @Test
    public void testRemoveTicket() {
    	category = new Category("Category Name", 2);
        ticket1 = new Ticket("Ticket 1", "Test", true);
        ticket2 = new Ticket("Ticket 2", "Test", true);
        ticket3 = new Ticket("Ticket 3", "Test", true);
        category.addTicket(ticket1);
        category.addTicket(ticket3);
        category.removeTicket(1);
        assertEquals(1, category.getTickets().size());
    }

    /**
     * Tests getting a ticket as an array
     */
    @Test
    public void testGetTicketsAsArray() {
    	category = new Category("Category Name", 2);
        ticket1 = new Ticket("Ticket 1", "Test", true);
        ticket2 = new Ticket("Ticket 2", "Test", true);
        ticket3 = new Ticket("Ticket 3", "Test", true);
        category.addTicket(ticket1);
        category.addTicket(ticket3);
        String[][] expected = {{"0", "Ticket 1"}, {"1", "Ticket 3"}};
        assertArrayEquals(expected, category.getTicketsAsArray());
    }

    /**
     * TEsts getting the completed count
     */
    @Test
    public void testGetCompletedCount() {
    	category = new Category("Category Name", 2);
        ticket1 = new Ticket("Ticket 1", "Test", true);
        ticket2 = new Ticket("Ticket 2", "Test", true);
        ticket3 = new Ticket("Ticket 3", "Test", true);
        assertEquals(2, category.getCompletedCount());
    }
    
    /**
     * Ticket field to hold one
     */
    private Ticket ticket;
    /**
     * an activeTicketList
     */
	private ActiveTicketList activeTicketList;
	
	/**
	 * Tests completing a ticket
	 */
    @Test
    public void testCompleteTicket() {
    	activeTicketList = new ActiveTicketList();
		ticket = new Ticket("Ticket Name", "Ticket Description", true);
		activeTicketList.addTicket(ticket);
		activeTicketList.completeTicket(ticket);
		assertEquals(0, activeTicketList.getTickets().size());
		assertEquals(1, activeTicketList.getCompletedCount());
	}
    
}

