package com.everest.samples.ds;

public class Nodes{

	public static <E> Node<E> create(E element, Node<E> next) {
		return new Node<E>(element, next);
	}

	public static <E> BNode<E> createB(E element) {
		return new BNode<E>(element);
	}

	static class Node<E> {
		E element;
		Node<E> next;

		Node(E element, Node<E> next) {
			this.element = element;
			this.next = next;
		}
	}

	static class BNode<E> {
		E element;
		BNode<E> left;
		BNode<E> right;

		BNode(E element) {
			this.element = element;
		}
	}
}
