import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.NoSuchElementException;
import java.util.*;

public class BinaryTree<T> implements Iterable<T> {

	protected TreeNode myRoot;

	public BinaryTree() {
		myRoot = null;
	}

	public BinaryTree(TreeNode t) {
		myRoot = t;
	}

	public BinaryTree(ArrayList<T> preorder, ArrayList<T> inorder) {
		if (preorder == null || preorder.isEmpty() || inorder == null
				|| inorder.isEmpty()) {
			myRoot = null;
		} else if (preorder.size() != inorder.size()) {
			myRoot = null;
		} else if (preorder.size() == 1) {
			myRoot = new TreeNode(preorder.get(1));
		} else {
			myRoot = initHelper(preorder, inorder);
		}
	}

	private TreeNode initHelper(ArrayList<T> preorder, ArrayList<T> inorder) {
		if (preorder == null) {
			return null;
		} else if (preorder.size() == 0) {
			return null;
		} else if (preorder.size() == 1) {
			return new TreeNode(preorder.get(0));
		}
		TreeNode rtn = new TreeNode(preorder.get(0));

		int inCurPos = inorder.indexOf(preorder.get(0));

		ArrayList<T> INleft = new ArrayList<T>();
		for (int i = 0; i < inCurPos; i++) {
			INleft.add(inorder.get(i));
		}

		ArrayList<T> INright = new ArrayList<T>();
		for (int i = inCurPos + 1; i < inorder.size(); i++) {
			INright.add(inorder.get(i));
		}

		preorder.remove(0);
		inorder.remove(inCurPos);

		ArrayList<T> PREright = new ArrayList<T>();
		ArrayList<T> PREleft = new ArrayList<T>();

		for (T item : preorder) {
			if (INleft.contains(item)) {
				PREleft.add(item);
			} else if (INright.contains(item)) {
				PREright.add(item);
			}
		}

		rtn.myRight = initHelper(PREright, INright);
		rtn.myLeft = initHelper(PREleft, INleft);

		return rtn;

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
		ArrayList<Integer> preorder = new ArrayList<Integer>();
		ArrayList<Integer> inorder = new ArrayList<Integer>();

		// preorder.add(7);
		// preorder.add(10);
		// preorder.add(4);
		// preorder.add(3);
		// preorder.add(1);
		// preorder.add(2);
		// preorder.add(8);
		// preorder.add(11);
		//
		// inorder.add(4);
		// inorder.add(10);
		// inorder.add(3);
		// inorder.add(1);
		//
		// inorder.add(7);
		//
		// inorder.add(11);
		// inorder.add(8);
		// inorder.add(2);

		BinaryTree<Integer> a = new BinaryTree<Integer>(preorder, inorder);
		print(a, "binary");

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
		public int mySize;

		public TreeNode(T item) {
			myItem = item;
			myLeft = myRight = null;
			mySize = 1;
		}

		public TreeNode(T item, TreeNode left, TreeNode right) {
			myItem = item;
			myLeft = left;
			myRight = right;
			mySize = 1 + left.mySize + right.mySize;
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