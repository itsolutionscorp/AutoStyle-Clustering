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
		myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c",
				new TreeNode("d", new TreeNode("e"), new TreeNode("f")), null));
	}

	public void fillSampleTree4() {
		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d"),
				new TreeNode("e")), new TreeNode("c", new TreeNode("f"),
				new TreeNode("g")));
	}

	public void fillSampleTree5() {
		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d"),
				new TreeNode("e")), new TreeNode("c", new TreeNode("f"),
				null));
	}
	
	public void fillSampleTree6() {
		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d"),
				null), new TreeNode("c", new TreeNode("f"),
				new TreeNode("g")));
	}

	public static void main(String[] args) {
		BinaryTree t;
		t = new BinaryTree();
		print(t, "the empty tree");
		System.out.println("height: " + t.height());
		System.out.println(t.isBalanced());
		t.fillSampleTree1();
		print(t, "sample tree 1");
		System.out.println("height: " + t.height());
		System.out.println(t.isBalanced());
		t.fillSampleTree2();
		print(t, "sample tree 2");
		System.out.println("height: " + t.height());
		System.out.println(t.isBalanced());
		t.fillSampleTree3();
		print(t, "somple tree 3");
		System.out.println("height: " + t.height());
		System.out.println(t.isBalanced());

		//Completely balanced tree of height 3
		t.fillSampleTree4();
		print(t, "somple tree 4");
		System.out.println("height: " + t.height());
		System.out.println(t.isBalanced());
		
		//Unbalanced tree of height 3
		t.fillSampleTree5();
		print(t, "somple tree 5");
		System.out.println("height: " + t.height());
		System.out.println(t.isBalanced());
		
		//Unbalanced tree of height 3
		t.fillSampleTree6();
		print(t, "somple tree 6");
		System.out.println("height: " + t.height());
		System.out.println(t.isBalanced());

	}

	private static void print(BinaryTree t, String description) {
		System.out.println(description + " in preorder");
		t.printPreorder();
		System.out.println(description + " in inorder");
		t.printInorder();
		System.out.println();
	}

	private int heightHelper(TreeNode T) {
		if (T == null) {
			return 0;
		} else if (T.myLeft == null && T.myRight == null) {
			return 1;
		} else {
			return 1 + Math
					.max(heightHelper(T.myLeft), heightHelper(T.myRight));
		}
	}

	public int height() {
		return heightHelper(myRoot);
	}

	private boolean balancedHelper(TreeNode T) {
		if (T == null) {
			return true;
		} else if (T.myLeft == null && T.myRight == null) {
			return true;
		} else {
			return ((heightHelper(T.myLeft) == heightHelper(T.myRight)) && 
					balancedHelper(T.myLeft) == balancedHelper(T.myRight) == true);
		}
	}

	public boolean isBalanced() {
		return balancedHelper(myRoot);
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
	}
}