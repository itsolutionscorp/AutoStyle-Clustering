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

	/**
	 * New in lab 16
	 */

	public BinaryTree(ArrayList<T> pre, ArrayList<T> ino) {
		T[] preorder = null;
		T[] inorder = null;
		if (!pre.isEmpty() && !ino.isEmpty()) {
			preorder = (T[]) pre.toArray();
			inorder = (T[]) ino.toArray();
		}
		myRoot = helper(preorder, 0, pre.size() - 1, inorder, 0, ino.size() - 1);
	}

	private TreeNode helper(T[] preorder, int preL, int preR, T[] inorder,
			int inL, int inR) {
		if (preL > preR || inL > inR) {
			return null;
		}
		T temp = preorder[preL];
		TreeNode root = new TreeNode(temp);
		int index = -1;
		for (int i = inL; i <= inR; i++) {
			if (inorder[i].equals(temp)) {
				index = i;
			}
		}
		if (index != -1) {
			root.myLeft = helper(preorder, preL + 1, index - inL + preL,
					inorder, inL, index - 1);
			root.myRight = helper(preorder, preL + index - inL + 1, preR,
					inorder, index + 1, inR);
		}
		return root;
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
		ArrayList<String> a = new ArrayList<String>();
		a.add("A");
		a.add("B");
		a.add("C");
		a.add("D");
		a.add("E");
		a.add("F");
		ArrayList<String> b = new ArrayList<String>();
		b.add("B");
		b.add("A");
		b.add("E");
		b.add("D");
		b.add("F");
		b.add("C");
		t = new BinaryTree<String>(a, b);
		print(t, "using preorder and inorder to construct a tree");

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

		public int sizeofTree;
		public T myItem;
		public TreeNode myLeft;
		public TreeNode myRight;

		public TreeNode(T item) {
			sizeofTree = 1;
			myItem = item;
			myLeft = myRight = null;
		}

		public TreeNode(T item, TreeNode left, TreeNode right) {
			sizeofTree = 1 + left.sizeofTree + right.sizeofTree;
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

		/**
		 * new in lab17
		 */

		public Comparable kthLargest(int k) {
			if (myRight == null) {
				if (k == 1) {
					return (Comparable) myItem;
				} else {
					if (myLeft != null) {
						return myLeft.kthLargest(k - 1);
					} else {
						return null;
					}
				}
			} else {
				if (myRight.sizeofTree >= k){
					return myRight.kthLargest(k);
				} else if (myRight.sizeofTree == k-1){
					return (Comparable) myItem;
				} else {
					if (myLeft != null) {
						if (myRight == null){
							return myLeft.kthLargest(k - 1);
						} else{
							return myLeft.kthLargest(k - myRight.sizeofTree - 1);
						}
					} else {
						return null;
					}
				}
			}

		}
		
		public int height() {
        	int bestSoFar = 0;
        	if (myLeft == null && myRight != null) {
		    	return myRight.height()+1;
		    } else if (myLeft != null && myRight == null){
		    	return myLeft.height()+1;
		    } else if (myLeft != null && myRight != null){
		        bestSoFar = Math.max(myLeft.height(), myRight.height());
		    }
        	return bestSoFar + 1;
		}
	}
}