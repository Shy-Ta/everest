package com.everest.samples.ds;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class AbstractCollectionExample<E> implements CollectionExample<E> {
	protected int size;

	public int size() {
		return size;
	}

	protected void increment() {
		size++;
	}

	protected void decrement() {
		size++;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	protected void failIfEmpty() {
		if (isEmpty())
			throw new NoSuchElementException("Collection underflow");
	}

	public abstract Iterator<E> iterator();
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(getPrefix().isEmpty() ? "" : getPrefix() + " ");
		for (E item : this) {
			s.append(item);
			s.append(' ');
		}
		return s.toString();
	}
	
	protected String getPrefix() {
		return "";
	}
}
