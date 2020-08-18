package com.everest.samples.ds;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.everest.samples.ds.Nodes.Node;

public class Stacks {

	//LIFO
	static class ArrayStack<E> extends AbstractCollectionExample<E> implements IStack<E> {
		private E[] elements; // array of items

		@SuppressWarnings("unchecked")
		public ArrayStack() {
			elements = (E[]) new Object[2];
			size = 0;
		}

		@SuppressWarnings("unchecked")
		private void resize(int capacity) {
			E[] temp = (E[]) new Object[capacity];
			for (int i = 0; i < size; i++) {
				temp[i] = elements[i];
			}
			elements = temp;
		}

		public void push(E item) {
			if (size == elements.length)
				resize(2 * elements.length);
			elements[size++] = item;
		}

		public E pop() {
			failIfEmpty();
			E item = elements[size--];
			// resize if necessary
			if (size > 0 && size == elements.length / 4)
				resize(elements.length / 2);
			return item;
		}

		public E peek() {
			if (isEmpty())
				throw new NoSuchElementException("Stack underflow");
			return elements[size - 1];
		}

		@Override
		public Iterator<E> iterator() {
			return Iterators.reverseArray(elements);
		}
		
		protected String getPrefix() {
			return getClass().getSimpleName();
		}
	}

	static class LinkedStack<E> extends AbstractCollectionExample<E> implements IStack<E> {
		private Node<E> first; // top of stack

		public LinkedStack() {
			first = null;
			size = 0;
		}

		public void push(E item) {
			Node<E> oldfirst = first;
			first = Nodes.create(item, oldfirst);
			size++;
		}

		public E pop() {
			failIfEmpty();
			E item = first.element;
			first = first.next;
			size--;
			return item;
		}

		public E peek() {
			failIfEmpty();
			return first.element;
		}

		public Iterator<E> iterator() {
			return Iterators.reverseLinked(first);
		}
		
		protected String getPrefix() {
			return getClass().getSimpleName();
		}
	}

	public static void main(String[] args) {
		String item = "to be or not to be that is";
		String[] words = item.split(" ");
		IStack<String> stack = createLinkedStack(words);
		IStack<String> arrayStack = createArrayStack(words);
		System.out.println(stack);
		System.out.println(arrayStack);
	}

	public static <E> IStack<E> createArrayStack(E... elements) {
		return populateStack(new ArrayStack<E>(), elements);
	}

	public static <E> IStack<E> createLinkedStack(E... elements) {
		return populateStack(new LinkedStack<E>(), elements);
	}

	static <E> IStack<E> populateStack(IStack<E> stack, E... elements) {
		for (E element : elements) {
			stack.push(element);
		}
		return stack;
	}

	public interface IStack<E> {
		void push(E item);
		E pop();
		E peek();
	}
}
