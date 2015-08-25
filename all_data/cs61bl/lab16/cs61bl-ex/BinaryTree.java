import java.util.*;

public class BinaryTree<T> implements Iterable<T> {

	protected TreeNode myRoot;

	public BinaryTree() {
		myRoot = null;
	}

	public BinaryTree(TreeNode t) {
		myRoot = t;
	}

	public BinaryTree(ArrayList<T> preOrder, ArrayList<T> inOrder) {
		myRoot = piTree(preOrder, inOrder);
	}

	public TreeNode piTree(ArrayList<T> preOrder, ArrayList<T> inOrder) {
		if (preOrder.size() == 0 || inOrder.size() == 0) {
			return null;
		}
		if (preOrder.size() != inOrder.size()) {
			return null;
		}
		ArrayList<T> iOL = new ArrayList<T>();
		ArrayList<T> pOL = new ArrayList<T>();
		ArrayList<T> pOR = new ArrayList<T>();
		ArrayList<T> iOR = new ArrayList<T>();
		TreeNode t = new TreeNode(preOrder.get(0));
		int root = inOrder.indexOf(preOrder.get(0));
		for (int c = 0; c < inOrder.size(); c++) {

			if (c < root) {
				iOL.add(inOrder.get(c));
			}
			if (c == root) {
				continue;

			}
			if (c > root) {
				iOR.add(inOrder.get(c));
			}

		}
		for (int c2 = 1; c2 < preOrder.size(); c2++) {
			if (iOL.contains(preOrder.get(c2))) {
				pOL.add(preOrder.get(c2));
			} else {
				pOR.add(preOrder.get(c2));
			}
		}

		t.myLeft = piTree(pOL, iOL);
		t.myRight = piTree(pOR, iOR);
		return t;
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
		t.myRoot = t.new TreeNode("a",
				t.new TreeNode("b", t.new TreeNode("d", t.new TreeNode("e"), t.new TreeNode("f")), null),
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
		BinarySearchTree<String> bst = new BinarySearchTree<String>();
		bst.add("C");
		bst.add("A");
		bst.add("B");
		bst.add("E");
		bst.add("D");
		bst.add("B");
		System.out.println("Contains A?" + bst.contains("A"));
		System.out.println("Contains B?" + bst.contains("B"));
		System.out.println("Contains F?" + bst.contains("F"));
		print(bst, "test tree bst");
		ArrayList<String> preOrder = new ArrayList<String>();
		preOrder.add("A");
		preOrder.add("B");
		preOrder.add("Q");
		preOrder.add("C");
		preOrder.add("D");
		preOrder.add("E");
		preOrder.add("F");
		ArrayList<String> inOrder = new ArrayList<String>();
		inOrder.add("Q");
		inOrder.add("B");
		inOrder.add("A");
		inOrder.add("E");
		inOrder.add("D");
		inOrder.add("F");
		inOrder.add("C");
		BinaryTree<String> test = new BinaryTree<String>(preOrder, inOrder);
		print(test, "test tree test");
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