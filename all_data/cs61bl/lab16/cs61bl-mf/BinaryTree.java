import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinaryTree<T> implements Iterable<T> {

	protected TreeNode myRoot;

	public BinaryTree() {
		myRoot = null;
	}

	public BinaryTree(TreeNode t) {
		myRoot = t;
	}

	public BinaryTree(ArrayList<T> preorder, ArrayList<T> inorder) { // Assumes
																		// lengths
																		// are
																		// the
																		// same
		if (!preorder.isEmpty()) {
			myRoot = new TreeNode(preorder, inorder);
		} else {
			myRoot = null;
		}
	}

	public void print() {
		if (myRoot != null) {
			myRoot.print(0);
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

		String[] temp = { "A", "B", "C", "D", "E", "F" };
		ArrayList a = new ArrayList();
		for (String item : temp) {
			a.add(item);
		}

		String[] temp2 = { "B", "A", "E", "D", "F", "C" };
		ArrayList b = new ArrayList();
		for (String item : temp2) {
			b.add(item);
		}

		BinaryTree<String> v = new BinaryTree<String>(a, b);
		v.print();

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

		public TreeNode(ArrayList<T> preorder, ArrayList<T> inorder) {
			if (preorder.size() == 1) {
				myItem = preorder.get(0);
			} else {
				T target = preorder.get(0);
				int index = inorder.indexOf(target);

				ArrayList<T> leftPreorder = new ArrayList<T>(preorder.subList(
						1, index + 1));
				ArrayList<T> leftInorder = new ArrayList<T>(inorder.subList(0,
						index));
				ArrayList<T> rightPreorder = new ArrayList<T>(preorder.subList(
						index + 1, preorder.size()));
				ArrayList<T> rightInorder = new ArrayList<T>(inorder.subList(
						index + 1, inorder.size()));

				myItem = target;
				if (leftPreorder.size() == 0) {
					myLeft = null;
					if (rightPreorder.size() == 0) {
						myRight = null;
					} else {
						myRight = new TreeNode(rightPreorder, rightInorder);
					}
				} else {
					myLeft = new TreeNode(leftPreorder, leftInorder);
					if (rightPreorder.size() == 0) {
						myRight = null;
					} else {
						myRight = new TreeNode(rightPreorder, rightInorder);
					}
				}
			}
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

		// In TreeNode
		private static final String indent1 = "    ";

		private void print(int indent) {
			if (myLeft == null) {
				if (myRight == null) {
					println(myItem, indent);
				} else {
					myRight.print(indent + 1);
					println(myItem, indent);
				}
			} else {
				if (myRight == null) {
					println(myItem, indent);
					myLeft.print(indent + 1);
				} else {
					myRight.print(indent + 1);
					println(myItem, indent);
					myLeft.print(indent + 1);
				}
			}
			//
			// if (myLeft == null && myRight == null)
			// println(myItem, indent);
			// else if (myRight != null) {
			// myRight.print(indent + 1);
			// println(myItem, indent);
			// } else {
			// myLeft.print(indent + 1);
			// println(myItem, indent);
			// }
		}

		private void println(Object obj, int indent) {
			for (int k = 0; k < indent; k++) {
				System.out.print(indent1);
			}
			System.out.println(obj);
		}
	}
}