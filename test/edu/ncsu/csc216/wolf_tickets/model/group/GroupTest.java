package edu.ncsu.csc216.wolf_tickets.model.group;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import org.junit.Test;

import edu.ncsu.csc216.wolf_tickets.model.tickets.Category;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Ticket;

/**
 * Test class for group
 * @author jason
 *
 */
public class GroupTest {
	
	/**
	 * Holding a group variable
	 */
	Group a;
	
	/**
	 * Test Contructor
	 */
	@Test
	public void testGroupConstructor() {
		Group g = new Group("Group 1");
		assertEquals("Group 1", g.getGroupName());
	}
	
	/**
	 * Test Save Group
	 */
	@Test
	public void testSaveGroup() {
		Group g = new Group("Group 2");
		Category c1 = new Category("c1", 0);
		Category c2 = new Category("c2", 0);
		g.addCategory(c1);
		g.addCategory(c2);
		File f = new File("group_test.txt");
		g.saveGroup(f);
		assertTrue(f.exists());
		f.delete();
	}
	
	/**
	 * Test get group name
	 */
	@Test
	public void testGetGroupName() {
		Group g = new Group("Group 3");
		assertEquals("Group 3", g.getGroupName());
	}
	
	/**
	 * Test get categories name
	 */
	@Test
	public void testGetCategoriesNames() {
		Group g = new Group("Group 4");
		Category c1 = new Category("c1", 0);
		Category c2 = new Category("c2", 0);
		g.addCategory(c1);
		g.addCategory(c2);
		String[] names = g.getCategoriesNames();
		assertEquals(3, names.length);
		assertEquals("Active Tickets", names[0]);
		assertEquals("c1", names[1]);
	}
	
	/**
	 * Test set Current Category
	 */
	@Test
	public void testSetCurrentCategory() {
		Group g = new Group("Group 5");
		Category c1 = new Category("c1", 0);
		Category c2 = new Category("c2", 0);
		g.addCategory(c1);
		g.addCategory(c2);
		g.setCurrentCategory("c1");
		assertEquals("c1", g.getCurrentCategory().getCategoryName());
	}
	
	/**
	 * Test edit category
	 */
	@Test
	public void testEditCategory() {
		Group g = new Group("Group 6");
		Category c1 = new Category("c1", 0);
		Category c2 = new Category("c2", 0);
		g.addCategory(c1);
		g.addCategory(c2);
		g.setCurrentCategory("c1");
		g.editCategory("c3");
		String[] names = g.getCategoriesNames();
		assertEquals(3, names.length);
		assertEquals("c2", names[1]);
	}
	
	/**
	 * Test exception set group name
	 */
	@Test
	public void testSetGroupNameInvalid() {		
		Exception e = assertThrows(IllegalArgumentException.class, 
				() -> a = new Group(""));
		assertEquals("Invalid name.", e.getMessage());		
	}
	
	
	/**
	 * Test edit active ticket list
	 */
	@Test
	public void testEditCategoryActiveTicketList() {
		Group g = new Group("Group 9");
		Category c1 = new Category("c1", 0);
		Category c2 = new Category("c2", 0);
		g.addCategory(c1);
		g.addCategory(c2);
		g.setCurrentCategory("Active Tickets");
		Exception e = assertThrows(IllegalArgumentException.class, 
				() -> g.editCategory("Category 2"));
		assertEquals("The Active Tickets list may not be edited.", e.getMessage());		
	
	}
	
	/**
	 * Field for group
	 */
	private Group group;
	/**
	 * Field used for category1
	 */
    private Category category1;
    /**
     * Field used for category2
     */
    private Category category2;
    /**
     * Ticket 1 field
     */
    private Ticket ticket1;
    /**
     * Ticket 2 field
     */
    private Ticket ticket2;

	
    /**
     * Test remove a category
     */
	@Test
    public void testRemoveActiveTicketList() {
    	group = new Group("Test Group");
        category1 = new Category("Category 1", 0);
        category2 = new Category("Category 2", 0);
        group.addCategory(category1);
        group.addCategory(category2);
        group.setCurrentCategory("Category 2");
        group.removeCategory();
        assertEquals(2, group.getCategoriesNames().length);
    }
    
    /**
     * tests adding a ticket to group category
     */
    @Test
    public void testAddTicket() {
    	group = new Group("Test Group");
        category1 = new Category("Category 1", 0);
        group.addCategory(category1);
        Ticket ticket4 = new Ticket("Ticket 4", "Description 4", true);
        ticket1 = new Ticket("Ticket 1", "Description 1", false);
        ticket2 = new Ticket("Ticket 2", "Description 1", false);
        Ticket ticket3 = new Ticket("Ticket 3", "Description 1", true);
        group.setCurrentCategory("Category 1");
        group.addTicket(ticket4);
        assertEquals(1, category1.getTickets().size());
        assertEquals(ticket4, category1.getTickets().get(0));
        group.addTicket(ticket1);
        group.addTicket(ticket2);
        group.addTicket(ticket3); 
    }
    
    /**
     * Test editing a ticket in group category
     */
    @Test
    public void testEditTicket() {
    	group = new Group("Test Group");
        category1 = new Category("Category 1", 0);
        category2 = new Category("Category 2", 0);
        group.addCategory(category1);
        group.addCategory(category2);
        ticket1 = new Ticket("Ticket 1", "Description 1", true);
        ticket2 = new Ticket("Ticket 2", "Description 2", false);
        category1.addTicket(ticket1);
        category2.addTicket(ticket2);
        group.setCurrentCategory("Category 2");
        group.editTicket(0, "New Name", "New Description", true);
        Ticket editedTicket = category2.getTickets().get(0);
        assertEquals("New Name", editedTicket.getTicketName());
        assertEquals("New Description", editedTicket.getTicketDescription());
        assertTrue(editedTicket.isActive());
    }
    
    /**
     * Replicate the TS test for this one
     */
    @Test
    public void teachAddTestDupe() {
    	Group newGroup = new Group("TS Test");
    	newGroup.addCategory(new Category("Category1", 0));
    	newGroup.addTicket(new Ticket("Ticket1", "Ticket1Description", false));
    	
    	newGroup.addTicket(new Ticket("Ticket2", "Ticket2Description", false));

    	newGroup.addTicket(new Ticket("Ticket3", "Ticket3Description", true));
    	newGroup.setCurrentCategory("Active Tickets");
    	int size = newGroup.getCurrentCategory().getTickets().size();
    	assertEquals(size, 1);
    	newGroup.setCurrentCategory("Category1");
    	newGroup.addTicket(new Ticket("Ticket4", "Ticket4Description", true));
    	newGroup.setCurrentCategory("Active Tickets");
    	size = newGroup.getCurrentCategory().getTickets().size();
    	assertEquals(size, 2);

    }
    
    /**
     * Replicate TS scenario test
     */
    @Test
    public void teachTestScenario() {
    	Group newGroup = new Group("TS Test");
    	newGroup.addCategory(new Category("Category1", 0));
    	newGroup.addTicket(new Ticket("Ticket1", "Ticket1Description", true));
    	newGroup.addTicket(new Ticket("Ticket2", "Ticket2Description", false));
    	newGroup.addTicket(new Ticket("Ticket3", "Ticket3Description", true));
    	newGroup.addCategory(new Category("ACategory", 0));
    	newGroup.addTicket(new Ticket("Ticket4", "Ticket4Description", true));
    	newGroup.addTicket(new Ticket("Ticket5", "Ticket2Description", false));
    	newGroup.setCurrentCategory("ACategory");
    	newGroup.setCurrentCategory("Category1");
    	newGroup.setCurrentCategory("Active Tickets");
    	newGroup.getCurrentCategory().completeTicket(new Ticket("Ticket1", "Ticket1Description", true));
    	int size = newGroup.getCurrentCategory().getTickets().size();
    	assertEquals(2, size);
    	newGroup.setCurrentCategory("ACategory");
    	newGroup.setCurrentCategory("Category1");
    	newGroup.getCurrentCategory().completeTicket(new Ticket("Ticket3", "Ticket3Description", true));
    	size = newGroup.getCurrentCategory().getTickets().size();
    	assertEquals(2, size);
    	newGroup.setCurrentCategory("Active Tickets");
    	size = newGroup.getCurrentCategory().getTickets().size();
    	assertEquals(2, size);
    	newGroup.setCurrentCategory("ACategory");
    	size = newGroup.getCurrentCategory().getTickets().size();
    	assertEquals(2, size);


    }

}
