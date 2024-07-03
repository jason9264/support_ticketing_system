package edu.ncsu.csc216.wolf_tickets.model.tickets;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class for the Ticket class
 * @author jason
 *
 */
class TicketTest {     

	/**
	 * Test getter for ticket Name
	 */
    @Test
    void testGetTicketName() {
    
    	Ticket t1 = new Ticket("t1", "description1", true);
        // Test that ticket name is returned correctly
        assertEquals("t1", t1.getTicketName());
    }

    /**
     * Test setter for ticket name
     */
    @Test
    public void testSetTicketName() {
    	Ticket t1 = new Ticket("t1", "description1", true);
        // Test that ticket name is set correctly
        t1.setTicketName("newName");
        assertEquals("newName", t1.getTicketName());

        // Test that IllegalArgumentException is thrown if name is null
        Exception e = assertThrows(IllegalArgumentException.class, 
        		() -> t1.setTicketName(null));
		assertEquals("Incomplete ticket information.", e.getMessage());	
		
        // Test that IllegalArgumentException is thrown if name is empty
		Exception e2 = assertThrows(IllegalArgumentException.class, 
				() -> t1.setTicketName(""));
		assertEquals("Incomplete ticket information.", e2.getMessage());	
    }

    /**
     * Test getter for ticket description
     */
    @Test
    public void testGetTicketDescription() {
    	Ticket t1 = new Ticket("t1", "description1", true);

        // Test that ticket description is returned correctly
        assertEquals("description1", t1.getTicketDescription());
    }

    /**
     * Test setter for ticket description
     */
    @Test
    public void testSetTicketDescription() {
    	Ticket t1 = new Ticket("t1", "description1", true);
        // Test that ticket description is set correctly
        t1.setTicketDescription("newDescription");
        assertEquals("newDescription", t1.getTicketDescription());

        // Test that IllegalArgumentException is thrown if description is null
        Exception e = assertThrows(IllegalArgumentException.class, 
        		() -> t1.setTicketDescription(null));
		assertEquals("Incomplete ticket information.", e.getMessage());

        // Test that IllegalArgumentException is thrown if description is empty
		t1.setTicketDescription("");
		assertEquals("", t1.getTicketDescription());	
    }

    /**
     * Test if the ticket is active
     */
    @Test
    public void testIsActive() {
    	Ticket t1 = new Ticket("t1", "description1", true);
        Ticket t3 = new Ticket("t3", "description3", false);

        // Test that ticket active status is returned correctly
        assertTrue(t1.isActive());
        assertFalse(t3.isActive());
    }

    /**
     * Test setter for ticket
     */
    @Test
    public void testSetActive() {
    	Ticket t1 = new Ticket("t1", "description1", true);
        Ticket t3 = new Ticket("t3", "description3", false);
        // Test that ticket active status is set correctly
        t1.setActive(false);
        assertFalse(t1.isActive());
        t3.setActive(true);
        assertTrue(t3.isActive());
    }

    /**
     * Test getter for category name
     */
    @Test
    public void testGetCategoryName() {
    	Ticket t1 = new Ticket("t1", "description1", true);
        AbstractCategory c1 = new Category("c1", 0);
        
        
        assertEquals("", t1.getCategoryName());

        // Test that category name is set correctly
        t1.addCategory(c1);
        assertEquals("c1", t1.getCategoryName());
    }

    /**
     * Tests adding a category to the ticket
     */
    @Test
    public void testAddCategory() {
    	Ticket t1 = new Ticket("t1", "description1", true);
        AbstractCategory c1 = new Category("c1", 0);
        // Test that category is added correctly
        t1.addCategory(c1);
        assertEquals("c1", t1.getCategoryName());
        // Test that IllegalArgumentException is thrown if category is null
        Exception e1 = assertThrows(IllegalArgumentException.class, 
        		() -> t1.addCategory(null));
		assertEquals("Incomplete ticket information.", e1.getMessage());	
    }

    /**
     * Test converting ticket to a string
     */
    @Test
    public void testToString() {
        Ticket t1 = new Ticket("Ticket 1", "This is ticket 1", true);
        AbstractCategory c1 = new Category("Category 1", 0);
        t1.addCategory(c1);
        assertEquals("* Ticket 1,active\nThis is ticket 1", t1.toString());
    }

}
