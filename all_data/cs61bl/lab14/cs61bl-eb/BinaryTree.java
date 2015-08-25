public class BinaryTree {

	private TreeNode myRoot;

	public BinaryTree() {
		myRoot = null;
	}

	public BinaryTree(TreeNode t) {
		myRoot = t;
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

	public void fillSampleTree1() {
		myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
	}

	public void fillSampleTree2() {
		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d",
				new TreeNode("e"), new TreeNode("f")), null), new TreeNode("c"));
	}
	
	public void fillSampleTree3() {
		myRoot = new TreeNode("Hilfinger", new TreeNode("Hug", new TreeNode("Yeseon"), new TreeNode("Daniel")), new TreeNode("Joseph", new TreeNode("Sam"), new TreeNode("Armani")));
	}

	public static void main(String[] args) {
		BinaryTree t;
		t = new BinaryTree();
		print(t, "the empty tree");
		
		t.fillSampleTree1();
		print(t, "sample tree 1");
		System.out.println("t.height() " + t.height());
		System.out.println("t.isCompletelyBalanced() " + t.isCompletelyBalanced() + "\n\n");
		
		t.fillSampleTree2();
		print(t, "sample tree 2");
		System.out.println("t.height() " + t.height());
		System.out.println("t.isCompletelyBalanced() " + t.isCompletelyBalanced() + "\n\n");
		
		t.fillSampleTree3();
		print(t, "sample tree 3");
		System.out.println("t.height() " + t.height());
		System.out.println("t.isCompletelyBalanced() " + t.isCompletelyBalanced() + "\n\n");
	}

	private static void print(BinaryTree t, String description) {
		System.out.println(description + " in preorder");
		t.printPreorder();
		System.out.println(description + " in inorder");
		t.printInorder();
		System.out.println();
	}


	public int height() {
		// always find the height of the entire tree
		if (myRoot != null) {
			return myRoot.height();
		}
		return 0; // the height of a null tree as 0
	}


	public boolean isCompletelyBalanced() {
		if (myRoot != null) {
			return myRoot.isCompletelyBalanced();
		}
		return true; // an empty tree is completely balanced
	}


	private static class TreeNode {

		public Object myItem;
		public TreeNode myLeft;
		public TreeNode myRight;

		public TreeNode(Object obj) {
			myItem = obj;
			myLeft = myRight = null;
		}

		public TreeNode(Object obj, TreeNode left, TreeNode right) {
			myItem = obj;
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

		private int height() {
			if (myLeft == null && myRight == null) {
				// if you have no child, your bestSoFar is 1 (just you)
				return 1;
			} else {
				int bestSoFar = 1; // you're guaranteed to have at least one child, so your max depth must be at least 1
				// if you have child, each of your children will return his/her bestSoFar
				// and you just update bestSoFar and return it when you are done asking all your children
				if (myLeft != null) bestSoFar =  Math.max(myLeft.height(), bestSoFar);
				if (myRight != null) bestSoFar =  Math.max(myRight.height(), bestSoFar);
				return 1 + bestSoFar;
			}
		}


		private boolean isCompletelyBalanced() {		
			int numOfLR = 0;
			if (myLeft != null) ++numOfLR;
			if (myRight != null) ++numOfLR;

			if (numOfLR == 0) {
				// if you have no child, so you are balanced
				return true;
			} else if (numOfLR == 1) {
				// you only got one leg, so you are unbalanced
				return false;
			} else {
				// you have two legs, but need to check if they have the same length
				return myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced();
			}
			
		}
		
		
		
	} // end of TreeNode


}

