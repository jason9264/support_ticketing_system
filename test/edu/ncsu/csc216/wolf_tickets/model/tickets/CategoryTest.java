package edu.ncsu.csc216.wolf_tickets.model.tickets;


import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for Category
 * @author jason
 *
 */
public class CategoryTest {

	/**
	 * Category Field
	 */
    private Category category;
    


    /**
     * Test Category construction method
     */
    @Test
    public void testCategory() {
        category = new Category("Test Category", 0);

        assertEquals("Test Category", category.getCategoryName());
        assertEquals(0, category.getCompletedCount());
    }

    /**
     * Test getting ticket as a array
     */
    @Test
    public void testGetTicketsAsArray() {
        category = new Category("Test Category", 0);

        // Test empty ticket list
        String[][] arr = category.getTicketsAsArray();
        assertEquals(0, arr.length);        
        // Test non-empty ticket list
        category.addTicket(new Ticket("Test Ticket", "Test", true));
        arr = category.getTicketsAsArray();
        assertEquals(1, arr.length);
        assertEquals(2, arr[0].length);
        assertEquals("0", arr[0][0]);
        assertEquals("Test Ticket", arr[0][1]);
    }

    /**
     * Test compare and comparable functionality
     */
    @Test
    public void testCompareTo() {
        category = new Category("Test Category", 0);

        Category category1 = new Category("A Category", 0);
        Category category2 = new Category("B Category", 0);
        Category category3 = new Category("a category", 0);
        
        assertTrue(category1.compareTo(category2) < 0);
        assertTrue(category2.compareTo(category1) > 0);
        assertTrue(category1.compareTo(category3) >= 0);
    }

}
