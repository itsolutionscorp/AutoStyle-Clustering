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

//	private boolean contains(TreeNode t, T key) {
//		if (t == null) {
//			return false;
//		}
//		if (key == t.myItem) {
//			return true;
//		} else if (t.myLeft != null && contains(t.myLeft, key)) {
//			return true;
//		} else if (t.myRight != null && contains(t.myRight, key)) {
//			return true;
//		}
//
//		return false;
//
//	}
	public BinaryTree(ArrayList<T> preOrder, ArrayList<T> inOrder){
		myRoot = BinaryTree(preOrder, inOrder);
	}
	
	public TreeNode BinaryTree(ArrayList<T> preOrder, ArrayList<T> inOrder){
		if(preOrder.size() == 0 || inOrder.size() == 0){
			return null;
		}
		
		ArrayList inOrderLeft = new ArrayList();
		ArrayList preOrderLeft = new ArrayList();
		ArrayList preOrderRight = new ArrayList();
		ArrayList inOrderRight = new ArrayList();
		//int count = 1;
		
		
		
		
		TreeNode t = new TreeNode(preOrder.get(0));
		int root = inOrder.indexOf(preOrder.get(0));
		for(int c = 0; c < inOrder.size(); c ++){
		
			if(c < root){
				inOrderLeft.add(inOrder.get(c));
			}
			if(c == root){
//				inOrder.remove(c);
//				c--;
				continue;
				
			}if(c > root){
				inOrderRight.add(inOrder.get(c));
			}
			
		}
//		System.out.println(inOrderLeft);
//		System.out.println(inOrderRight);
		
		for(int c2 = 1; c2 < preOrder.size(); c2++){
			if(inOrderLeft.contains(preOrder.get(c2))){
				preOrderLeft.add(preOrder.get(c2));
//				System.out.println(preOrderLeft);
			}else{
				preOrderRight.add(preOrder.get(c2));
//				System.out.println(preOrderRight);
//				System.out.println(inOrderRight);
			}
			}
		
		
		t.myLeft = BinaryTree(preOrderLeft, inOrderLeft);
		t.myRight = BinaryTree(preOrderRight, inOrderRight);
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
		t.myRoot = t.new TreeNode("a", t.new TreeNode("b", t.new TreeNode("d",
				t.new TreeNode("e"), t.new TreeNode("f")), null),
				t.new TreeNode("c"));
		return t;
	}
	
	public static BinaryTree<String> fillSampleTree3(){
		BinaryTree<String> t = new BinaryTree<String>();
		t.myRoot = t.new TreeNode("a", 
				t.new TreeNode("b", t.new TreeNode("d", null, t.new TreeNode("f", null, t.new TreeNode("h"))), null ), 
				t.new TreeNode("c", null, t.new TreeNode("e", t.new TreeNode("g", t.new TreeNode("i"), null) ,null)));
		return t;
	}
	
	

	public static void main(String[] args) {
		BinaryTree<String> t = new BinaryTree<String>();
		print(t, "the empty tree");
		BinaryTree<String> s = fillSampleTree1();
//		System.out.println(s.contains(s.myRoot, "c"));
//		System.out.println(s.contains(s.myRoot, "l"));
		print(s, "sample tree 1");
		BinaryTree<String> r = fillSampleTree2();
//		System.out.println(r.contains(r.myRoot, "f"));
//		System.out.println(r.contains(r.myRoot, "e"));
		print(r, "sample tree 2");
		
		BinaryTree<String> l = fillSampleTree3();
		print(l, "dimond tree");
		
		
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
		
		
		
		ArrayList<String> preOrder1 = new ArrayList<String>();
		preOrder1.add("A");
		preOrder1.add("B");
		preOrder1.add("E");
		preOrder1.add("F");
		preOrder1.add("G");
		preOrder1.add("P");
		preOrder1.add("D");
		preOrder1.add("H");
		preOrder1.add("R");
		preOrder1.add("I");
		preOrder1.add("C");
		preOrder1.add("J");
		preOrder1.add("N");
		preOrder1.add("O");
		preOrder1.add("S");
		preOrder1.add("K");
		preOrder1.add("Q");
		preOrder1.add("L");
		preOrder1.add("M");
		ArrayList<String> inOrder1 = new ArrayList<String>();
		inOrder1.add("G");
		inOrder1.add("F");
		inOrder1.add("E");
		inOrder1.add("P");
		inOrder1.add("B");
		inOrder1.add("D");
		inOrder1.add("R");
		inOrder1.add("H");
		inOrder1.add("I");
		inOrder1.add("A");
		inOrder1.add("O");
		inOrder1.add("N");
		inOrder1.add("S");
		inOrder1.add("J");
		inOrder1.add("C");
		inOrder1.add("Q");
		inOrder1.add("K");
		inOrder1.add("L");
		inOrder1.add("M");
		BinaryTree<String> test1 = new BinaryTree<String>(preOrder1, inOrder1);
		print(test1, "test intense tree test");
		
		ArrayList<String> preOrderD = new ArrayList<String>();
		preOrderD.add("A");
		preOrderD.add("B");
		preOrderD.add("D");
		preOrderD.add("F");
		preOrderD.add("H");
		preOrderD.add("C");
		preOrderD.add("E");
		preOrderD.add("G");
		preOrderD.add("I");
		ArrayList<String> inOrderD = new ArrayList<String>();
		inOrderD.add("D");
		inOrderD.add("F");
		inOrderD.add("H");
		inOrderD.add("B");
		inOrderD.add("A");
		inOrderD.add("C");
		inOrderD.add("I");
		inOrderD.add("G");
		inOrderD.add("E");
		BinaryTree<String> testD = new BinaryTree<String>(preOrderD, inOrderD);
		print(testD, "test DIMOND tree test");
		
		
		
		
		
		
		
		BinarySearchTree dove = new BinarySearchTree();
		dove.add("c");
		dove.add("a");
		dove.add("b");
		dove.add("e");
		dove.add("d");

		print(dove, "loreal");
		System.out.println( dove.contains("t") );
		System.out.println( dove.contains("a"));
		System.out.println( dove.contains("d") );
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
