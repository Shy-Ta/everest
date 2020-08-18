package com.everest.samples.ds;

import com.everest.samples.ds.Nodes.BNode;

public class Trees {

	static class BinaryTree<E extends Comparable<E>> {
		BNode<E> root;

		public BinaryTree() {
		}

		public void insert(E element) {
			root = insert(root, element);
		}

		BNode<E> insert(BNode<E> node, E element) {
			if (node == null) {
				node = Nodes.createB(element);
			} else if (element.compareTo(node.element) < 0) {
				node.left = insert(node.left, element);
			} else if (element.compareTo(node.element) > 0) {
				node.right = insert(node.right, element);
			}
			return node;
		}

		public boolean lookup(E element) {
			return lookup(root, element);
		}

		private boolean lookup(BNode<E> node, E element) {
			if (node == null) {
				return false;
			}
			if (element.equals(node.element)) {
				return true;
			} else if (element.compareTo(node.element) < 0) {
				return lookup(node.left, element);
			} else {
				return lookup(node.right, element);
			}
		}

		public void preOrderTraversal() {
			preOrderTraversal(root);
		}

		public void inOrderTraversal() {
			inOrderTraversal(root);
		}

		public void postOrderTraversal() {
			postOrderTraversal(root);
		}

		private void preOrderTraversal(BNode<E> node) {
			if (node != null) {
				System.out.println(node.element);
				preOrderTraversal(node.left);
				preOrderTraversal(node.right);
			}
		}

		private void inOrderTraversal(BNode<E> node) {
			if (node != null) {
				inOrderTraversal(node.left);
				System.out.println(node.element);
				inOrderTraversal(node.right);
			}
		}

		private void postOrderTraversal(BNode<E> node) {
			if (node != null) {
				postOrderTraversal(node.left);
				postOrderTraversal(node.right);
				System.out.println(node.element);
			}
		}
	}

	public static <E extends Comparable<E>> BinaryTree<E> create(E... elements) {
		BinaryTree<E> tree = new BinaryTree<E>();
		for (E element : elements) {
			tree.insert(element);
		}
		return tree;
	}

	
	/**       1
	 *   2         3       
	 * 4   5     6   7
	 */
	public static void main(String[] args) {
		Integer[] elements = {1, 2, 3, 4, 5, 6, 7};
		BinaryTree<Integer> tree = create(elements);
		System.out.println("PreOrder :");
		tree.preOrderTraversal();
		System.out.println("InOrder  :");
		tree.inOrderTraversal();
		System.out.println("PostOrder:");
		tree.postOrderTraversal();
	}
}
