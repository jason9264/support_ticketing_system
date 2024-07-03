package edu.ncsu.csc216.wolf_tickets.model.io;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import edu.ncsu.csc216.wolf_tickets.model.group.Group;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Category;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Ticket;
import edu.ncsu.csc216.wolf_tickets.model.util.ISortedList;
import edu.ncsu.csc216.wolf_tickets.model.util.SortedList;

/**
 * Test class for the GroupWriter class
 * @author jason
 *
 */
public class GroupWriterTest {

	/**
	 * Test writing to a group file
	 * @throws IOException when it cannot be created or written to
	 */
    @Test
    public void testWriteGroupFile() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("test_group", ".txt");

        // Create some categories with tickets
        Category cat1 = new Category("c1", 0);
        cat1.addTicket(new Ticket("t1", "test", true));
        cat1.addTicket(new Ticket("t2", "test", true));

        Category cat2 = new Category("c2", 1);
        cat2.addTicket(new Ticket("t3", "test", false));
        cat2.addTicket(new Ticket("t4", "test", true));
        cat2.addTicket(new Ticket("t5", "test", true));

        ISortedList<Category> categories = new SortedList<Category>();
        categories.add(cat1);
        categories.add(cat2);

        // Write the group to the file
        GroupWriter.writeGroupFile(tempFile, "Test Group", categories);

        // Read the file and check that it has the expected content
        Group returnedGroup = GroupReader.readGroupFile(tempFile);
        assertEquals(returnedGroup.getGroupName(), "Test Group");
        assertEquals(returnedGroup.getCurrentCategory().getCategoryName(), "Active Tickets");
        assertEquals(returnedGroup.getCategoriesNames().length, 3);
    }

}
