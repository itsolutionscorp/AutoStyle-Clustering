import java.util.ArrayList;
import java.util.Iterator;
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

	public BinaryTree(ArrayList<T> preO, ArrayList<T> inO) {
		myRoot = new TreeNode(preO.get(0));
		myRoot.arrayHelper(preO, inO);
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

		private TreeNode arrayHelper(ArrayList<T> pre0, ArrayList<T> in0) {
			TreeNode stringNode = null;

			/*
			 * if (pre0.get(0) == in0.get(0)) { T newItem = pre0.get(0);
			 * copyNode = new TreeNode(newItem); return copyNode; } else {
			 * ArrayList<T> copy = pre0; ArrayList<T> dest = new ArrayList<T>();
			 * dest.addAll(in0); copyNode = new TreeNode (dest.remove(0));
			 * myLeft = arrayHelper(dest, in0); myRight = arrayHelper(dest,
			 * in0); }
			 */

			if (in0.size() == 0|| pre0.size() == 0) {
				return new TreeNode(null);
			} else if (in0.get(0) == pre0.get(0)) { // establishes branch
				stringNode = new TreeNode(pre0.get(0));
				ArrayList<T> newIn = new ArrayList<T>();
				newIn.addAll(pre0);
				newIn.remove(in0);
				newIn.trimToSize();
				return stringNode.arrayHelper(pre0, newIn);
			} else {
				
				ArrayList<T> newPre = new ArrayList<T>();
				newPre.addAll(pre0);
				newPre.remove(0);
				newPre.trimToSize();
				myLeft = arrayHelper(newPre, in0);
				myRight = arrayHelper(pre0, in0);

			}

			return stringNode;
		}

	}

}