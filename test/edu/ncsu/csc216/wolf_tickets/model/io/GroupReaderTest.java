package edu.ncsu.csc216.wolf_tickets.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tickets.model.group.Group;
import edu.ncsu.csc216.wolf_tickets.model.tickets.AbstractCategory;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Ticket;

class GroupReaderTest {

	/**
	 * Test reading in a group file
	 */
	@Test
    public void testReadGroupFile() {
        // Test that GroupReader can read in a valid group file
        Group g = GroupReader.readGroupFile(new File("test-files/group1.txt"));
        assertEquals("CSC IT", g.getGroupName());
        assertEquals(4, g.getCategoriesNames().length);

        AbstractCategory d = g.getCurrentCategory();
        assertEquals("Active Tickets", d.getCategoryName());
        assertEquals(5, d.getTickets().size());
        Ticket desktopTicket = d.getTicket(0);
        assertEquals("EBII 1025 Laptop display won't work", desktopTicket.getTicketName());
        assertTrue(desktopTicket.isActive());
        assertEquals("The projector will not show my laptop's display in EBII 1025. Using the podium computer works fine. My laptop shows the extra display, but I only see a black screen on the classroom screen.", desktopTicket.getTicketDescription());
    }
	
	/**
	 * Replicate TS test for Valid7 reader
	 */
	@Test
	public void tsTestRepValid7() {
        Group g = GroupReader.readGroupFile(new File("test-files/group7.txt"));
        assertEquals(1, g.getCurrentCategory().getTickets().size());
        g.setCurrentCategory("License Renewals");
        assertEquals(3, g.getCurrentCategory().getCompletedCount());

		
	}
}
