import java.util.*;
import java.util.Stack;
import java.util.NoSuchElementException;

public class BinaryTree<T> implements Iterable<T> {

	protected TreeNode myRoot;

	public BinaryTree() {
		myRoot = null;
	}

	public BinaryTree(TreeNode t) {
		myRoot = t;
	}

	public BinaryTree(ArrayList<T> preorder, ArrayList<T> inorder) {
		myRoot = BinaryTreeHelper(preorder, inorder);
	}

	public TreeNode BinaryTreeHelper(ArrayList<T> preorder, ArrayList<T> inorder) {

		if (preorder.isEmpty()) {
			return null;
		} else if (preorder.size() == 1) {
			return new TreeNode(preorder.get(0));
		} else {
			T currentItem = preorder.get(0);
			int rootIndexInorder = inorder.indexOf(currentItem);

			// get rightInorder, leftInorder
			ArrayList<T> rightInorder = new ArrayList<T>(inorder.subList(
					rootIndexInorder + 1, inorder.size()));
			ArrayList<T> leftInorder = new ArrayList<T>(inorder.subList(0,
					rootIndexInorder));

			// iterate the preorder array to get rightPreorder, leftPreorder
			ArrayList<T> rightPreorder = new ArrayList<T>();
			ArrayList<T> leftPreorder = new ArrayList<T>();
			Iterator<T> iter = preorder.iterator();
			while (iter.hasNext()) {
				T current = iter.next();
				if (rightInorder.contains(current)) {
					rightPreorder.add(current);
				} else if (leftInorder.contains(current)) {
					leftPreorder.add(current);
				}
			}

			// make recursive calls
			if (rightPreorder.size() == 0) {
				if (leftPreorder.size() == 0) {
					return new TreeNode(currentItem);
				} else {
					return new TreeNode(currentItem, BinaryTreeHelper(
							leftPreorder, leftInorder), null);
				}
			} else {
				if (leftPreorder.size() == 0) {
					return new TreeNode(currentItem, null, BinaryTreeHelper(
							rightPreorder, rightInorder));
				} else {
					return new TreeNode(currentItem, BinaryTreeHelper(
							leftPreorder, leftInorder), BinaryTreeHelper(
							rightPreorder, rightInorder));
				}
			}
		}
	}

	// Print the values in the tree in preorder: root value first,
	// then values in the left subtree (in preorder), then values
	// in the right subtree (in preorder).
	public void printPreorder() {
		if (myRoot == null) {
			System.out.println("(empty tree)");
		} else {
			myRoot.printPreorder();
			System.out.println();
		}
	}

	// Print the values in the tree in inorder: values in the left
	// subtree first (in inorder), then the root value, then values
	// in the right subtree (in inorder).
	public void printInorder() {
		if (myRoot == null) {
			System.out.println("(empty tree)");
		} else {
			myRoot.printInorder();
			System.out.println();
		}
	}

	public static BinaryTree<String> fillSampleTree1() {
		BinaryTree<String> t = new BinaryTree<String>();
		t.myRoot = t.new TreeNode("a", t.new TreeNode("b"), t.new TreeNode("c"));
		return t;
	}

	public static BinaryTree<String> fillSampleTree2() {
		BinaryTree<String> t = new BinaryTree<String>();
		t.myRoot = t.new TreeNode("a", t.new TreeNode("b", t.new TreeNode("d",
				t.new TreeNode("e"), t.new TreeNode("f")), null),
				t.new TreeNode("c"));
		return t;
	}

	public static void main(String[] args) {
		BinaryTree<String> t = new BinaryTree<String>();
		print(t, "the empty tree");
		BinaryTree<String> s = fillSampleTree1();
		print(s, "sample tree 1");
		BinaryTree<String> r = fillSampleTree2();
		print(r, "sample tree 2");
	}

	protected static void print(BinaryTree<?> t, String description) {
		System.out.println(description + " in preorder");
		t.printPreorder();
		System.out.println(description + " in inorder");
		t.printInorder();
		System.out.println();
	}

	// Method for the BinaryTree class
	public Iterator<T> iterator() {
		return new InorderIterator();
	}

	// Inner class inside of the BinaryTree class.
	// Also, it uses java.util.Iterator and java.util.Stack.
	private class InorderIterator implements Iterator<T> {
		private Stack<TreeNode> nodeStack;
		private TreeNode currentNode;

		public InorderIterator() {
			nodeStack = new Stack<TreeNode>();
			currentNode = myRoot;
		}

		public boolean hasNext() {
			return !nodeStack.isEmpty() || (currentNode != null);
		}

		public T next() {
			TreeNode nextNode = null;

			// find leftmost node with no left child
			while (currentNode != null) {
				nodeStack.push(currentNode);
				currentNode = currentNode.myLeft;
			}

			// get leftmost node, then move to its right subtree
			if (!nodeStack.isEmpty()) {
				nextNode = nodeStack.pop();
				assert nextNode != null; // since nodeStack was not empty before
											// the pop
				currentNode = nextNode.myRight;
			} else {
				throw new NoSuchElementException();
			}

			return nextNode.myItem;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	protected class TreeNode {

		public T myItem;
		public TreeNode myLeft;
		public TreeNode myRight;

		public TreeNode(T item) {
			myItem = item;
			myLeft = myRight = null;
		}

		public TreeNode(T item, TreeNode left, TreeNode right) {
			myItem = item;
			myLeft = left;
			myRight = right;
		}

		private void printPreorder() {
			System.out.print(myItem + " ");
			if (myLeft != null) {
				myLeft.printPreorder();
			}
			if (myRight != null) {
				myRight.printPreorder();
			}
		}

		private void printInorder() {
			if (myLeft != null) {
				myLeft.printInorder();
			}
			System.out.print(myItem + " ");
			if (myRight != null) {
				myRight.printInorder();
			}
		}
	}
}