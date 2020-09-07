package com.everest.samples.ds;

import java.util.Iterator;

import com.everest.samples.ds.Nodes.Node;

public class LinkedLists {

	public static <T> ILinkedList<T> create(T[] elements) {
		ILinkedList<T> list = new MyLinkedList<T>();
		for (T element : elements) {
			list.add(element);
		}
		return list;
	}

	static class MyLinkedList<T> extends AbstractCollectionExample<T> implements ILinkedList<T> {
		private Node<T> head;

		MyLinkedList() {
			this(null);
		}

		public MyLinkedList(Node<T> head) {
			this.head = head;
		}

		public void add(T element) {
			Node<T> node = Nodes.create(element, null);
			if (this.head == null) {
				this.head = node;
			} else {
				Node<T> currentLast = getLastNode();
				currentLast.next = node;
			}
			increment();
		}

		public void addFirst(T element) {
			this.head = Nodes.create(element, head);
			increment();

		}

		public T getFirst() {
			if (head != null) {
				return head.element;
			}
			return null;
		}

		Node<T> getLastNode() {
			Node<T> current = head;
			while (current.next != null) {
				current = current.next;
			}
			return current;
		}

		public T getLast() {
			Node<T> currentLast = getLastNode();
			return currentLast != null ? currentLast.element : null;
		}

		public void insertAfter(T element, T toInsert) {
			Node<T> current = head;
			while (current.next != null && !current.element.equals(element)) {
				current = current.next;
			}
			if (current != null) {
				current.next = Nodes.create(toInsert, current.next);
				increment();
			}
		}

		public void insertBefore(T element, T toInsert) {
			if (head == null)
				return;

			if (head.element.equals(element)) {
				addFirst(toInsert);
				return;
			}
			Node<T> current = head;
			Node<T> before = null;
			while (current.next != null && !current.element.equals(element)) {
				before = current;
				current = current.next;
			}
			if (current != null) {
				before.next = Nodes.create(toInsert, before.next);
				increment();
			}

		}

		public void remove(T element) {
			if (head == null)
				return;
			Node<T> current = head;
			Node<T> before = null;
			if (head.element.equals(element)) {
				head = head.next;
				return;
			}
			while (current.next != null && !current.element.equals(element)) {
				before = current;
				current = current.next;
			}
			if (current != null) {
				before.next = current.next;
				decrement();
			}
		}
		public String toString() {
			StringBuilder result = new StringBuilder();
			for (Object x : this)
				result.append(x + " ");

			return result.toString();
		}

		@Override
		public Iterator<T> iterator() {
			return Iterators.reverseLinked(head);
		}
		
		protected String getPrefix() {
			return "LinkedList";
		}
		
		@Override
		public void reverse() {
			this.head = reverseRecursively(head);//changes list in place
			//reverseRecursively(head); use the returned to create a new list
		}
		
		/**
		 * Call stack for a sample list of 2, 3, 4 ,5 2, 3, 4, 5 (returns and is
		 * initialized as reversedHeadNode), then 4 unwraps, which then becomes 5's next
		 * and then the previous association between 4 and 5 is removed.
		 * 
		 */
		Node<T> reverseRecursively(Node<T> node) {
			if (node == null || node.next == null) {
//				System.out.println("Returning " + node.element);
				return node;
			}
			Node<T> reversedHeadNode = reverseRecursively(node.next);
//			System.out.println("After recursion call " + node.element + ", head:" + reversedHeadNode.element);
			node.next.next = node;// the node prior to last element (which is now the head), becomes the element
									// next to head
			node.next = null; // break the previous association between second last and last element
			return reversedHeadNode;
		}
		
		public ILinkedList<T> reverseInPlace() {
			Node<T> previous = null;
			while (head != null) {
				Node<T> nextElem = head.next;
				head.next = previous;
				previous = head;
				head = nextElem;
			}
			return new MyLinkedList<>(previous);
		}	
	}

	public static void main(String[] args) {
		Integer[] elements = {2, 3, 4};
		ILinkedList<Integer> list = create(elements);
		list.add(5);//2,3,4,5
		list.insertBefore(3, 9);//2,9,7,3,4,5
		list.insertAfter(9, 7);//2,9,7,3,4,5
		System.out.println(list);
		list.reverse();
		System.out.println(list);
		System.out.println(list.reverseInPlace());
		
	}
	public interface ILinkedList<T> {
		void add(T element);// addLast by default

		void addFirst(T element);

		T getFirst();

		T getLast();

		void insertAfter(T element, T toInsert);

		void insertBefore(T element, T toInsert);

		void remove(T element);
		
		public void reverse();
		
		public ILinkedList<T> reverseInPlace();
	}
}
