package edu.ncsu.csc216.wolf_tickets.model.util;

/**
 * Public class SortedList that extends ComparableE and implements ISortedList
 * @author jason
 *
 * @param <E> Comparable item E
 */
public class SortedList<E extends Comparable<E>> implements ISortedList<E> {

	/**
	 * The size variable to keep track of the size
	 */
	private int size;
	
	/**
	 * listNode for the front of the list
	 */
	private ListNode front;
	
	/**
	 * SortedLists constructor
	 */
	public SortedList() {
		size = 0;
		front = null;
	}
	
	/**
	 * Adds the element to the list in sorted order.
	 * @param element element to add
	 * @throws NullPointerException if element is null
	 * @throws IllegalArgumentException if element cannot be added 
	 */
	@Override
	public void add(E element) {
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		
		if(contains(element)) {
			throw new IllegalArgumentException("Cannot add duplicate element.");
		}
		
		if (front == null) {
			front = new ListNode(element, null);
			size++;
		} else if (front.data.compareTo(element) > 0) {
			front = new ListNode(element, front);
			size++;
		} else {
			ListNode current = front;
			while (current.next != null && current.next.data.compareTo(element) < 0) {
				current = current.next;
			}
			current.next = new ListNode(element, current.next);
			size++;
		}
	}

	/**
	 * Returns the element from the given index.  The element is
	 * removed from the list.
	 * @param idx index to remove element from
	 * @return element at given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public E remove(int idx) {
		checkindex(idx);
		E element;
		if (idx == 0) {
			element = front.data;
			front = front.next;
			size--;
			return element;
		} else {
			ListNode current = front;
			for (int i = 0; i < idx - 1; i++) {
				current = current.next;
			}
			element = current.next.data;
			current.next = current.next.next;
			size--;
			return element;
		}
	}

	/**
	 * Method that Checks the index 
	 * @param idx the index to check
	 * @throws IndexOutOfBoundsException if the index is invalid
	 */
	private void checkindex(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
	}
	
	/**
	 * Returns true if the element is in the list.
	 * @param element element to search for
	 * @return true if element is found
	 */
	@Override
	public boolean contains(E element) {
		ListNode current = front;
		while (current != null) {
			if (current.data.equals(element)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	/**
	 * Returns the element at the given index.
	 * @param idx index of the element to retrieve
	 * @return element at the given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 	for the list
	 */
	@Override
	public E get(int idx) {
		 if (idx < 0 || idx >= size) {
		        throw new IndexOutOfBoundsException("Invalid index.");
		    }

		    ListNode curr = front;
		    for (int i = 0; i < idx; i++) {
		        curr = curr.next;
		    }

		    return curr.data;
	}

	/**
	 * Returns the number of elements in the list.
	 * @return number of elements in the list
	 */
	@Override
	public int size() {
		return size;
	}
	
	private class ListNode {

		/**
		 * The data for listNode variable
		 */
		private E data;
		
		/**
		 * Next item stored for the listNode
		 */
		private ListNode next;

		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}

}
