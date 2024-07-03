package edu.ncsu.csc216.wolf_tickets.model.util;

/**
 * SwapList class that implements ISwapList interface
 * @author jason
 *
 * @param <E> the object E to use
 */
public class SwapList<E> implements ISwapList<E> {

	/**
	 * The variable initial capacity to be used for creating sortedList
	 */
	private final static int INITIAL_CAPACITY = 10;
	
	/**
	 * Variable for List of E object array
	 */
	private E[] list;
	
	/**
	 * The size variable to keep track of the size
	 */
	private int size;
	
	/**
	 * Constructor for SwapList
	 */
	@SuppressWarnings("unchecked")
	public SwapList() {
		this.list = (E[]) new Object[INITIAL_CAPACITY];
		size = 0;
	}
	
	/**
	 * Adds the element to the end of the list.
	 * @param element element to add
	 * @throws NullPointerException if element is null
	 */
	@Override
	public void add(E element) {
		if( element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		
		checkCapacity(size + 1);
		list[size] = element;
		size++;
	}

	/**
	 * Method to check the capacity for this list
	 * @param currCap the i int to check
	 */
	private void checkCapacity(int currCap) {
		if (currCap > list.length) {
			int newCapacity = list.length * 2;
			if (newCapacity < currCap) {
				newCapacity = currCap;
			}
			@SuppressWarnings("unchecked")
			E[] newList = (E[]) new Object[newCapacity];
			for (int i = 0; i < size; i++) {
				newList[i] = list[i];
			}
			list = newList;
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
		try {
		checkindex(idx);
		}
		catch (Exception e) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		E remove = list[idx];
		for (int i = idx; i < size - 1; ++i) {
			list[i] = list[i + 1];
		}
		list[size - 1] = null;
		size--;
		return remove;
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
	 * Moves the element at the given index to index-1.  If the element is
	 * already at the front of the list, the list is not changed.
	 * @param idx index of element to move up
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public void moveUp(int idx) {
		checkindex(idx);
		if (idx > 0) {
			E temp = list[idx - 1];
			list[idx - 1] = list[idx];
			list[idx] = temp;
		}
	}

	
	/**
	 * Moves the element at the given index to index+1.  If the element is
	 * already at the end of the list, the list is not changed.
	 * @param idx index of element to move down
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public void moveDown(int idx) {
		 if (idx < 0 || idx >= size) {
		        throw new IndexOutOfBoundsException("Invalid index.");
		    }
		    if (idx == size - 1) {
		        return;
		    }
		    E temp = list[idx];
		    list[idx] = list[idx + 1];
		    list[idx + 1] = temp;
	}

	
	/**
	 * Moves the element at the given index to index 0.  If the element is
	 * already at the front of the list, the list is not changed.
	 * @param idx index of element to move to the front
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public void moveToFront(int idx) {
		if (idx < 0 || idx >= size) {
	        throw new IndexOutOfBoundsException("Invalid index.");
	    }
	    if (idx == 0) {
	        // already at the front
	        return;
	    }
	    E temp = list[idx];
	    for (int i = idx; i > 0; i--) {
	        list[i] = list[i - 1];
	    }
	    list[0] = temp;
	}

	
	/**
	 * Moves the element at the given index to size-1.  If the element is
	 * already at the end of the list, the list is not changed.
	 * @param idx index of element to move to the back
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public void moveToBack(int idx) {
		if (idx < 0 || idx >= size) {
	        throw new IndexOutOfBoundsException("Invalid index.");
	    }
	    if (idx == size - 1) {
	        // already at the back
	        return;
	    }
	    E temp = list[idx];
	    for (int i = idx; i < size - 1; i++) {
	        list[i] = list[i + 1];
	    }
	    list[size - 1] = temp;		
	}

	
	/**
	 * Returns the element at the given index.
	 * @param idx index of the element to retrieve
	 * @return element at the given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public E get(int idx) {
		if (idx < 0 || idx >= size) {
	        throw new IndexOutOfBoundsException("Invalid index.");
	    }
	    return list[idx];
	}

	/**
	 * Returns the number of elements in the list.
	 * @return number of elements in the list
	 */
	@Override
	public int size() {
		return size;
	}

}
