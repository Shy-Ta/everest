package com.everest.samples.ds;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.everest.samples.ds.Iterators.NonRemovableIterator;
import com.everest.samples.ds.Nodes.Node;

public class Queues {
	
	static class ArrayQueue<E> extends AbstractCollectionExample<E> implements IQueue<E> {
		private E[] elements;
		private int first; // index of the first element
		private int last; // index of next available slot

		@SuppressWarnings("unchecked")
		public ArrayQueue() {
			this.elements = (E[]) new Object[2];
			this.first = this.last = 0;
		}

		public void enqueue(E item) {
			if (size == elements.length) {
				resize(2 * elements.length);
			}
			elements[last++] = item;
			if (last == elements.length) {
				last = 0;
			}
			size++;
		}

		public E dequeue() {
			failIfEmpty();
			E item = elements[first];
			elements[first++] = null;
			size--;
			if (first == elements.length) {
				first = 0;
			}
			if (size > 0 && size == elements.length / 4)
				resize(elements.length / 2);
			return item;
		}

		public E peek() {
			failIfEmpty();
			return elements[first];
		}
		
		@Override
		public Iterator<E> iterator() {
			return new ArrayIterator();
		}

		private class ArrayIterator extends NonRemovableIterator<E> {
			private int index = 0;

			public boolean hasNext() {
				return index < size;
			}

			public E next() {
				if (!hasNext())
					throw new NoSuchElementException();
				E item = elements[(index + first) % elements.length];
				index++;
				return item;
			}
		}

		private void resize(int newSize) {
			@SuppressWarnings("unchecked")
			E[] newElements = (E[]) new Object[newSize];
			for (int i = 0; i < size; i++) {
				newElements[i] = elements[(first + i) % elements.length];
			}
			elements = newElements;
			first = 0;
			last = size;
		}
		
		protected String getPrefix() {
			return getClass().getSimpleName();
		}
	}

	static class LinkedQueue<E> extends AbstractCollectionExample<E> implements IQueue<E> {

		private Node<E> first;//beginning
		private Node<E> last;//end
		
		public LinkedQueue() {
			this.first = this.last = null;
			this.size = 0;
		}
		public void enqueue(E item) {
			Node<E> oldLast = last;
			last = Nodes.create(item, null);
			if(isEmpty()) {
				first = last;
			} else {
				oldLast.next = last;
			}
			size++;
		}

		public E dequeue() {
			failIfEmpty();
			E element = first.element;
			first = first.next;
			size--;
			if(isEmpty()) {
				last = null;
			}
			return element;
		}

		public E peek() {
			failIfEmpty();
			return first.element;
		}

		@Override
		public Iterator<E> iterator() {
			return Iterators. reverseLinked(first);
		}
		
		protected String getPrefix() {
			return getClass().getSimpleName();
		}
	}

	public static <E> IQueue<E> createArrayQueue(E... elements) {
		return populateElements(new ArrayQueue<E>(), elements);
	}
	
	public static <E> IQueue<E> createLinkedQueue(E... elements) {
		return populateElements(new LinkedQueue<E>(), elements);
	}
	
	static <E> IQueue<E> populateElements(IQueue<E> queue, E... elements) {
		for (E e : elements) {
			queue.enqueue(e);
		}
		return queue;
	}
	
	public static void main(String[] args) {
		Integer[] elements = {1, 3, 5, 7, 9};
		System.out.println(createArrayQueue(elements));
		System.out.println(createLinkedQueue(elements));
	}
	
	public interface IQueue<E> {
		void enqueue(E item);
		E dequeue();
		E peek();
	}
}
