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

	public int height() {
		if (myRoot != null) {
			return myRoot.height();
		}
		return 0;
	}
	
	public boolean isCompletelyBalanced() {
		if (myRoot!=null) {
			return myRoot.isCompletelyBalanced();
		}
		return true;
	}

	public void fillSampleTree1() {
		myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
	}

	public void fillSampleTree2() {
		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d",
				new TreeNode("e"), new TreeNode("f")), null), new TreeNode("c"));
	}

	public void fillSampleTree3() {
		myRoot = new TreeNode("A", new TreeNode("B"), new TreeNode("C", new TreeNode("D", new TreeNode("E"), new TreeNode("F")), null));
	}
/*	public void fillSampleTree4() {
		myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c", new TreeNode("D", new TreeNode("E", new TreeNode("F"), null), new TreeNode("G")), null));
	} */
	
	public void fillBalancedTree() {
		myRoot = new TreeNode("1", new TreeNode("2.0", new TreeNode("3.0"), new TreeNode("3.1")), new TreeNode("2.1", new TreeNode("3.2"), new TreeNode("3.3")));
	}

	public static void main(String[] args) {
		BinaryTree t;
		t = new BinaryTree();
		print(t, "the empty tree");
		System.out.println(t.height());
		System.out.println(t.isCompletelyBalanced());
		t.fillSampleTree1();
		print(t, "sample tree 1");
		System.out.println(t.height());
		System.out.println(t.isCompletelyBalanced());
		t.fillSampleTree2();
		print(t, "sample tree 2");
		System.out.println(t.height());
		System.out.println(t.isCompletelyBalanced());
		t.fillSampleTree3();
		print(t, "sample tree 3");
		System.out.println(t.height());
		System.out.println(t.isCompletelyBalanced());
		t.fillBalancedTree();
		print(t, "sample tree balanced");
		System.out.println(t.height());
		System.out.println(t.isCompletelyBalanced());
	}

	private static void print(BinaryTree t, String description) {
		System.out.println(description + " in preorder");
		t.printPreorder();
		System.out.println(description + " in inorder");
		t.printInorder();
		System.out.println();
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

		private int height() {
			int longestSoFar=0;
			if (myItem == null) {
				return 0;
			}
			if (myLeft == null && myRight != null) {
				longestSoFar = myRight.height();
				longestSoFar++;
				return longestSoFar;
			}
			if (myLeft != null && myRight == null) {
				longestSoFar = myLeft.height();
				longestSoFar++;
				return longestSoFar;
			}
			if (myLeft == null && myRight == null){
				return 1;
			} else {
				longestSoFar = Math.max(myLeft.height(), myRight.height());
				longestSoFar++;
				return longestSoFar;
			}
		} 
		
		private boolean isCompletelyBalanced() {
			boolean isBalanced = true;
			if (myLeft == null && myRight == null && isBalanced == true) {
				isBalanced = true;
				return isBalanced;
			} else if (myLeft != null && myRight != null && isBalanced == true) {
				return (myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced());
			} else {
				isBalanced = false;
				return isBalanced;
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
