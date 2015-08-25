import java.util.ArrayList;
import java.util.Collections;
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

	public BinaryTree(ArrayList<T> preorder, ArrayList<T> inorder){
		int pos = inorder.indexOf(preorder.get(0));
		int size = inorder.size();
		ArrayList<T> inorder1 =new ArrayList<T>(inorder.subList(0, pos));
		ArrayList<T> inorder2 =new ArrayList<T>(inorder.subList(pos+1,inorder.size()));
		ArrayList<T> preorder1 =new ArrayList<T>( preorder.subList(1, inorder1.size()+1));
		ArrayList<T> preorder2 =new ArrayList<T>( preorder.subList( inorder1.size()+1,inorder.size()));
		myRoot= new TreeNode(preorder.get(0),new TreeNode(preorder1,inorder1),new TreeNode(preorder2,inorder2));

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
		t.myRoot = t.new TreeNode("a", t.new TreeNode("b", t.new TreeNode("d", t.new TreeNode("e"), t.new TreeNode("f")), null), t.new TreeNode("c"));
		return t;
	}

	public static BinaryTree<String> fillSampleTree3() {
		ArrayList<String> s = new ArrayList<String>();
		Collections.addAll(s, "a", "b", "c","d","e","f");
		ArrayList<String> d = new ArrayList<String>();
		Collections.addAll(d, "b", "a", "e","d","f","c");
		BinaryTree<String> t = new BinaryTree<String>(s,d);
		return t;
	}

	public static void main(String[] args) {
		BinaryTree<String> t = new BinaryTree<String>();
		print(t, "the empty tree");
		BinaryTree<String> s = fillSampleTree1();
		print(s, "sample tree 1");
		BinaryTree<String> r = fillSampleTree2();
		print(r, "sample tree 2");
		BinaryTree<String> u = fillSampleTree3();
		print(u, "sample tree 3");
		ArrayList<String> d = new ArrayList<String>();
		Collections.addAll(d, "b", "a", "e","d","f","c");
		System.out.println(d.subList(5,6));
	}

	protected static void print(BinaryTree<?> t, String description) {
		System.out.println(description + " in preorder");
		t.printPreorder();
		System.out.println(description + " in inorder");
		t.printInorder();
		System.out.println();
	}

	// Method for the BinaryTree class
	public Iterator<T> iterator(){
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
				assert nextNode != null;    // since nodeStack was not empty before the pop
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

		public TreeNode(ArrayList<T> preorder, ArrayList<T> inorder){
			if (preorder.isEmpty()){
			}
			else if (preorder.size()==1){
				myItem = preorder.get(0);
			}else {
					myItem = preorder.get(0);
				int pos = inorder.indexOf(preorder.get(0));
				ArrayList<T> inorder1 =new ArrayList<T>(inorder.subList(0, pos));
				ArrayList<T> inorder2 =new ArrayList<T>(inorder.subList(pos+1,inorder.size()));
				ArrayList<T> preorder1 =new ArrayList<T>( preorder.subList(1, inorder1.size()+1));
				ArrayList<T> preorder2 =new ArrayList<T>( preorder.subList( inorder1.size()+1,inorder.size()));
				if (preorder1.isEmpty()){myLeft=null;}else{
				myLeft = new TreeNode(preorder1,inorder1);
				}
				if (preorder2.isEmpty()){
					myRight=null;
				}else{
				myRight = new TreeNode(preorder2,inorder2);
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

	}
}