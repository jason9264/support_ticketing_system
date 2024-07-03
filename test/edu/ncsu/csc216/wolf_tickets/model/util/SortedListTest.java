package edu.ncsu.csc216.wolf_tickets.model.util;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

/**
 * SortedList test class
 * @author jason
 *
 */
public class SortedListTest {

	/**
	 * a Sorted List field to hold list
	 */
    private SortedList<String> list;

    /**
     * Tests adding to the list
     */
    @Test
    public void testAdd() {
        list = new SortedList<String>();
        list.add("apple");
        list.add("banana");
        list.add("cherry");
        assertEquals(3, list.size());
        assertEquals("apple", list.get(0));
        assertEquals("banana", list.get(1));
        assertEquals("cherry", list.get(2));
    }

    /**
     * Tests removing from the list
     */
    @Test
    public void testRemove() {
        list = new SortedList<String>();
        list.add("apple");
        list.add("banana");
        list.add("cherry");
        String removed = list.remove(1);
        assertEquals(2, list.size());
        assertEquals("banana", removed);
        assertEquals("apple", list.get(0));
        assertEquals("cherry", list.get(1));
    }

    /**
     * Tests the contain method for the list
     */
    @Test
    public void testContains() {
        list = new SortedList<String>();
        list.add("apple");
        list.add("banana");
        list.add("cherry");
        assertTrue(list.contains("banana"));
        assertFalse(list.contains("orange"));
    }

    /**
     * Tests getting the size
     */
    @Test
    public void testSize() {
        list = new SortedList<String>();
        assertEquals(0, list.size());
        list.add("apple");
        assertEquals(1, list.size());
        list.add("banana");
        assertEquals(2, list.size());
        list.add("cherry");
        assertEquals(3, list.size());
        list.remove(1);
        assertEquals(2, list.size());
    }

    /**
     * Tests adding a null item
     */
    @Test
    public void testAddNull() {
        list = new SortedList<String>();
        Exception e = assertThrows(NullPointerException.class,
				() -> list.add(null));
		assertEquals("Cannot add null element.", e.getMessage());	
        
    }

    /**
     * Tests getting an invalid index
     */
    @Test
    public void testGetInvalidIndex() {
        list = new SortedList<String>();
        Exception e = assertThrows(IndexOutOfBoundsException.class,
				() -> list.get(0));
		assertEquals("Invalid index.", e.getMessage());	
        
        
    }

    /**
     * Tests removing at an invalid index
     */
    @Test
    public void testRemoveInvalidIndex() {
        list = new SortedList<String>();
        Exception e = assertThrows(IndexOutOfBoundsException.class,
				() -> list.remove(0));
		assertEquals("Invalid index.", e.getMessage());	
        
        
        
    }

}

