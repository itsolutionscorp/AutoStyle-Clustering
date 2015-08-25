
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
		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d", new TreeNode("e"), new TreeNode("f")), null),
				new TreeNode("c"));
	}

	public int height() {
		if (myRoot != null)
			return myRoot.height(myRoot);
		return 0;
	}

	public void noTree() {
		myRoot = new TreeNode("full",
				new TreeNode("a", new TreeNode("b", new TreeNode("c"), new TreeNode("d")),
						new TreeNode("e", new TreeNode("f"), new TreeNode("g"))),
				new TreeNode("h", new TreeNode("j", new TreeNode("k"), new TreeNode("l")),
						new TreeNode("m", new TreeNode("n"), new TreeNode("o"))));
	}

	public boolean isCompletelyBalanced() {
		if (myRoot != null) {
			if (myRoot.height(myRoot) == 0 || myRoot.height(myRoot) == 1)
				return true;
			if (myRoot.height(myRoot.myLeft) != myRoot.height(myRoot.myRight))
				return false;
			if (!myRoot.isCompletelyBalanced(myRoot))
				return false;
		}
		return true;
	}

	public void one() {
		myRoot = new TreeNode("a");
	}

	public void sameHeight() {
		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("c"), new TreeNode("d")),
				new TreeNode("e", new TreeNode("f"), null));
	}

	public void trick() {
		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("c"), null),
				new TreeNode("d", new TreeNode("e"), null));
	}

	public void fillSampleTree7() {
		myRoot = new TreeNode("a", new TreeNode("b", null, null),
				new TreeNode("c", new TreeNode("d"), new TreeNode("d")));
	}

	public void fillSampleTree8() {
		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d"), new TreeNode("d")),
				new TreeNode("c", new TreeNode("d"), new TreeNode("d")));
	}

	public static void main(String[] args) {
		BinaryTree t;
		t = new BinaryTree();
		print(t, "the empty tree");
		t.fillSampleTree1();
		print(t, "sample tree 1");
		t.fillSampleTree2();
		print(t, "sample tree 2");
		t.noTree();
		print(t, "noTree");
		t.one();
		print(t, "one");
		t.sameHeight();
		print(t, "sameHeight");
		t.trick();
		print(t, "trick");
		t.fillSampleTree7();
		System.out.println("7: " + t.isCompletelyBalanced());
		t.fillSampleTree8();
		System.out.println("8: " + t.isCompletelyBalanced());
		System.out.println("one" + t.height());
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

		private int height(TreeNode node) {
			if (node == null) {
				return 0;
			} else {
				return Math.max(this.height(node.myLeft), this.height(node.myRight)) + 1;
			}
		}

		private boolean isCompletelyBalanced(TreeNode node) {
			if (node.myLeft == null && node.myRight == null)
				return true;
			if (node.myLeft != null && node.myRight != null) {
				if (isCompletelyBalanced(node.myLeft) && isCompletelyBalanced(node.myRight))
					return true;
			}
			return false;
		}
	}
}
