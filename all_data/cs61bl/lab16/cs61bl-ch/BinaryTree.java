import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinaryTree<T> implements Iterable<T> {

	protected TreeNode myRoot;

	/*
	 * Constructor with ArrayListArg1(PreOrder) and ArrayListArg2(InOrder)
	 */
	public BinaryTree(ArrayList<T> arg1, ArrayList<T> arg2) {

		myRoot = helper(arg1, arg2);

	}

	public BinaryTree() {
		myRoot = null;
	}

	public BinaryTree(TreeNode t) {
		myRoot = t;
	}

	private TreeNode helper(List<T> preOrder, List<T> inOrder) {
		if (preOrder.size() == 0 || inOrder.size() == 0) {
			return null;
		}
		T myitem = preOrder.get(0);
		int index = inOrder.indexOf(myitem);

		List<T> leftInOrder = inOrder.subList(0, index);
		List<T> rightInOrder = inOrder.subList(index+1, inOrder.size());
		List<T> leftPreOrder = preOrder.subList(1, leftInOrder.size() + 1 );
		List<T> rightPreOrder = preOrder.subList(leftInOrder.size() + 1,
				preOrder.size());

		return new TreeNode(myitem, helper(leftPreOrder, leftInOrder), helper(
				rightPreOrder, rightInOrder));

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
		
		/*
		 * Arraylist Tree Test
		 */
		ArrayList<String> name = new ArrayList<String>(); 
		name.add("A"); 
		name.add("B"); 
		name.add("C"); 
		name.add("D"); 
		name.add("E"); 
		name.add("F");
		
		ArrayList<String> name1= new ArrayList<String>();
		name1.add("B"); 
		name1.add("A"); 
		name1.add("E");
		name1.add("D");
		name1.add("F");
		name1.add("C");
		
		BinaryTree<String> b = new BinaryTree<String>(name, name1); 
		System.out.println("______________________________________________________________________");
		b.printPreorder();
		b.printInorder();

		/*
		 * calls from the BinaryTreeSearch Method
		 */

		BinarySearchTree<String> searchTree = new BinarySearchTree<String>();
		searchTree.add("C");
		searchTree.add("A");
		searchTree.add("B");
		searchTree.add("E");
		searchTree.add("D");

		System.out
				.println("****************************PreOrder*********************");
		searchTree.printPreorder();
		System.out
				.println("****************************InOrder*********************");
		searchTree.printInorder();

		System.out.println(searchTree.contains("C"));
		System.out.println(searchTree.contains("G"));
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