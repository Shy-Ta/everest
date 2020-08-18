package com.everest.samples.ds;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.everest.samples.ds.Nodes.Node;

public class Iterators {
	public static <E> Iterator<E> reverseArray(E[] elements) {
		return new ReverseArrayIterator<E>(elements);
	}
	
	public static <E> Iterator<E> reverseLinked(Node<E> first) {
		return new ReverseLinkedIterator<E>(first);
	}

	static class ReverseArrayIterator<E> extends NonRemovableIterator<E> {
		private int size;
		private final E[] elements;

		public ReverseArrayIterator(E[] elements) {
			this.elements = elements;
			this.size = elements.length - 1;
		}

		public boolean hasNext() {
			return size >= 0;
		}

		public E next() {
			if (!hasNext())
				throw new NoSuchElementException();
			return elements[size--];
		}
	}
	
	static class ReverseLinkedIterator<E> extends NonRemovableIterator<E> {
		private Node<E> current;

		public ReverseLinkedIterator(Node<E> first) {
			current = first;
		}

		public boolean hasNext() {
			return current != null;
		}

		public E next() {
			if (!hasNext())
				throw new NoSuchElementException();
			E item = current.element;
			current = current.next;
			return item;
		}
	}

	static abstract class NonRemovableIterator<E> implements Iterator<E> {
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
