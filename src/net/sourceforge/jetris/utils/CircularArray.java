package net.sourceforge.jetris.utils;

public class CircularArray<E> {
	
	// Attributes
	private final E[] array;
	private int currentPosition;
	private final int maxPosition;
	
	// Constructors
	public CircularArray(E[] array) {
		this.array = array;
		this.maxPosition = array.length;
		this.currentPosition = 0;
	}
	
	// Copy Constructor
	public CircularArray(CircularArray<E> circularArray) {
		this(circularArray.array);
		this.currentPosition = circularArray.currentPosition;
	}
	
	// Methods
	public int getCurrentInternalPosition() {
		return currentPosition;
	}
	
	public E current() {
		return array[currentPosition];
	}
	
	public E next() {
		circularIncrement();
		return current();
	}
	
	public E previous() {
		circularDecrement();
		return current();
	}
	
	private void circularIncrement() {
		currentPosition = ( currentPosition + 1 ) % maxPosition;
	}
	
	private void circularDecrement() {
		currentPosition = ( currentPosition - 1 ) % maxPosition; 
	}
	
	@Override
	public String toString() {
		return "[ CurrentPosition: " + currentPosition + " " + array + "]";
	}

}
