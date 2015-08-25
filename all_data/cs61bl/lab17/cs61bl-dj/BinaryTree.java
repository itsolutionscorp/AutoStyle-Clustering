import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;
import java.util.NoSuchElementException;

public class BinaryTree<K> implements Iterable<K> {

	protected TreeNode myRoot;
	int mySize;

	public BinaryTree() {
		myRoot = null;
		mySize = 0;

	}

	public BinaryTree(TreeNode t) {
		myRoot = t;
		mySize = 0;
	}

	public BinaryTree(ArrayList<K> preO, ArrayList<K> inO) {
		myRoot = new TreeNode(preO.get(0));
		myRoot.myHelper(preO, inO, new ArrayList<K>());

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

	public static BinaryTree<String> fillSampleKree1() {
		BinaryTree<String> t = new BinaryTree<String>();
		t.myRoot = t.new TreeNode("a", t.new TreeNode("b"), t.new TreeNode("c"));
		return t;
	}

	public static BinaryTree<String> fillSampleKree2() {
		BinaryTree<String> t = new BinaryTree<String>();
		t.myRoot = t.new TreeNode("a", t.new TreeNode("b", t.new TreeNode("d",
				t.new TreeNode("e"), t.new TreeNode("f")), null),
				t.new TreeNode("c"));
		return t;
	}

	public static void main(String[] args) {
		/*
		 * testing print BinaryTree<String> t = new BinaryTree<String>();
		 * print(t, "the empty tree"); BinaryTree<String> s = fillSampleKree1();
		 * print(s, "sample tree 1"); BinaryTree<String> r = fillSampleKree2();
		 * print(r, "sample tree 2"); }
		 */

		// testing Binary Constructor
		ArrayList<String> one = new ArrayList<String>(Arrays.asList("A", "B",
				"C", "D", "E", "F"));
		ArrayList<String> two = new ArrayList<String>(Arrays.asList("B", "A",
				"E", "D", "F", "C"));
		BinaryTree<String> f = new BinaryTree(one, two);

		print(f, "testingBinaryTreeConstructor");
	}

	protected static void print(BinaryTree<?> t, String description) {
		System.out.println(description + " in preorder");
		t.printPreorder();
		System.out.println(description + " in inorder");
		t.printInorder();
		System.out.println();
	}

	// Method for the BinaryTree class
	public Iterator<K> iterator() {
		return new InorderIterator();
	}

	// Inner class inside of the BinaryTree class.
	// Also, it uses java.util.Iterator and java.util.Stack.

	private class InorderIterator implements Iterator<K> {
		private Stack<TreeNode> nodeStack;
		private TreeNode currentNode;

		public InorderIterator() {
			nodeStack = new Stack<TreeNode>();
			currentNode = myRoot;
		}

		public boolean hasNext() {
			return !nodeStack.isEmpty() || (currentNode != null);
		}

		public K next() {
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
		public K myItem;
		public TreeNode myLeft;
		public TreeNode myRight;
		public int mySize;

		public TreeNode(K item) {
			myItem = item;
			myLeft = myRight = null;

		}

		public TreeNode(K item, TreeNode left, TreeNode right) {
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

		private TreeNode myHelper(ArrayList<K> preO, ArrayList<K> inO,
				ArrayList<K> alreadySeen) {
			TreeNode copyNode;
			alreadySeen = new ArrayList<K>();
			if (preO.size() == 1) {
				return new TreeNode(preO.get(0));
			}
			if (preO.get(0) == inO.get(0)) {
				K newItem = preO.get(0);
				return new TreeNode(newItem);
			} else {

				ArrayList<K> dest = new ArrayList<K>();
				dest.addAll(preO);
				K nodeItem = dest.remove(0);
				alreadySeen.add(nodeItem);
				copyNode = new TreeNode(nodeItem);

				if (alreadySeen.contains(inO.get(0))) {
					inO.remove(0);
				} else {
					copyNode.myLeft = myHelper(dest, inO, alreadySeen);
					dest.remove(0);
					inO.remove(0);
					copyNode.myRight = myHelper(dest, inO, alreadySeen);
				}

			}
			return copyNode;

		}

		/*
		public int compareTo(TreeNode o) {
			if (o == null) {
				throw new NullPointerException();
			} else if (myItem.toString().charAt(0) < (o).myItem.toString()
					.charAt(0)) {
				return -1;
			} else if (myItem.toString().charAt(0) > (o).myItem.toString()
					.charAt(0)) {
				return 1;
			} else {
				return 0;
			}
		}
		*/
	}

}