package edu.ncsu.csc216.wolf_tickets.model.util;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

/**
 * Test class for SwapList
 * @author jason
 *
 */
public class SwapListTest {

	/**
	 * Creates a list that work with intergers
	 */
    private SwapList<Integer> integerList;
    /**
     * Creates a list for string tests
     */
    private SwapList<String> stringList;
    
    /**
     * Test add method
     */
    @Test
    public void testAdd() {
        integerList = new SwapList<Integer>();
        stringList = new SwapList<String>();
        integerList.add(1);
        integerList.add(2);
        assertEquals(2, integerList.size());
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");
        assertEquals(3, stringList.size());
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");
        stringList.add("4");
        stringList.add("5");
        stringList.add("6");
        stringList.add("7");
        stringList.add("8");
        stringList.add("9");
    }
    
    /**
     * Test remove 
     */
    @Test
    public void testRemove() {
        integerList = new SwapList<Integer>();
        stringList = new SwapList<String>();
        integerList.add(1);
        integerList.add(2);
        assertEquals(1, (int)integerList.remove(0));
        assertEquals(1, integerList.size());
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");
        assertEquals("b", stringList.remove(1));
        assertEquals(2, stringList.size());
    }
    
    /**
     * Test moveUp 
     */
    @Test
    public void testMoveUp() {
        integerList = new SwapList<Integer>();
        stringList = new SwapList<String>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.moveUp(1);
        assertEquals(2, (int)integerList.get(0));
        assertEquals(1, (int)integerList.get(1));
        assertEquals(3, (int)integerList.get(2));
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");
        stringList.moveUp(1);
        assertEquals("b", stringList.get(0));
        assertEquals("a", stringList.get(1));
        assertEquals("c", stringList.get(2));
    }
    
    /**
     * Test moveDown 
     */
    @Test
    public void testMoveDown() {
        integerList = new SwapList<Integer>();
        stringList = new SwapList<String>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.moveDown(1);
        assertEquals(1, (int)integerList.get(0));
        assertEquals(3, (int)integerList.get(1));
        assertEquals(2, (int)integerList.get(2));
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");
        stringList.moveDown(1);
        assertEquals("a", stringList.get(0));
        assertEquals("c", stringList.get(1));
        assertEquals("b", stringList.get(2));
    }
    
    /**
     * Test moveToFront
     */
    @Test
    public void testMoveToFront() {
        integerList = new SwapList<Integer>();
        stringList = new SwapList<String>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.moveToFront(1);
        assertEquals(2, (int)integerList.get(0));
        assertEquals(1, (int)integerList.get(1));
        assertEquals(3, (int)integerList.get(2));
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");
        stringList.moveToFront(2);
        assertEquals("c", stringList.get(0));
        assertEquals("a", stringList.get(1));
        assertEquals("b", stringList.get(2));
    }
    
    /**
     * Test moveToBack
     */
    @Test
    public void testMoveToBack() {
        integerList = new SwapList<Integer>();

        integerList.add(10);
        integerList.add(20);
        integerList.add(30);
        // Test moving an element from the middle to the back
        integerList.moveToBack(1);
        assertEquals(Integer.valueOf(10), integerList.get(0));
        assertEquals(Integer.valueOf(20), integerList.get(2));
        
        // Test moving the first element to the back
        integerList.moveToBack(0);
        assertEquals(Integer.valueOf(10), integerList.get(2));
        assertEquals(Integer.valueOf(30), integerList.get(0));
        
        // Test moving the last element (should not change anything)
        integerList.moveToBack(2);
        assertEquals(Integer.valueOf(10), integerList.get(2));
        assertEquals(Integer.valueOf(30), integerList.get(0));
    }
    
    /**
     * Test moveToBack IOOBE
     */
    @Test
    public void testMoveToBackIndexOutOfBounds() {
        integerList = new SwapList<Integer>();

        // Test index out of bounds exception
    	Exception e = assertThrows(IndexOutOfBoundsException.class,
				() -> integerList.moveToBack(3));
		assertEquals("Invalid index.", e.getMessage());	
    	
    }

    /**
     * Test get
     */
    @Test
    public void testGet() {
        integerList = new SwapList<Integer>();
        integerList.add(10);
        integerList.add(20);
        integerList.add(30);
        assertEquals(Integer.valueOf(10), integerList.get(0));
        assertEquals(Integer.valueOf(20), integerList.get(1));
        assertEquals(Integer.valueOf(30), integerList.get(2));
    }
    
    /**
     * Test get IOOBE
     */
    @Test
    public void testGetIndexOutOfBounds() {
        integerList = new SwapList<Integer>();
        // Test index out of bounds exception
        Exception e = assertThrows(IndexOutOfBoundsException.class,
				() -> integerList.get(3));
		assertEquals("Invalid index.", e.getMessage());	
    	
    }
    
    /**
     * Test size
     */
    @Test
    public void testSize() {
        // Test getting size of list
        integerList = new SwapList<Integer>();

        assertEquals(0, integerList.size());
        
        // Test adding and removing elements
        integerList.add(1);
        assertEquals(1, integerList.size());
        integerList.remove(0);
        assertEquals(0, integerList.size());
    }
}