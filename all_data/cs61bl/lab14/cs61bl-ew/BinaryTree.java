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

	public void jenn() {
		myRoot = new TreeNode("full", new TreeNode("a", new TreeNode("b",
				new TreeNode("c"), new TreeNode("d")), new TreeNode("e",
				new TreeNode("f"), new TreeNode("g"))), new TreeNode("h",
				new TreeNode("j", new TreeNode("k"), new TreeNode("l")),
				new TreeNode("m", new TreeNode("n"), new TreeNode("o"))));
	}

	public void one() {
		myRoot = new TreeNode("a");
	}

	public void sameHeight() {
		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("c"),
				new TreeNode("d")), new TreeNode("e", new TreeNode("f"), null));
	}

	public void trick() {
		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("c"), null),
				new TreeNode("d", new TreeNode("e"), null));
	}

	public static void main(String[] args) {
		BinaryTree t;
		t = new BinaryTree();
		print(t, "the empty tree");
		System.out.println("height empty " + t.height());
		System.out.println(t.isCompletelyBalanced());
		t.fillSampleTree1();
		print(t, "sample tree 1");
		System.out.println("height 1 " + t.height());
		System.out.println(t.isCompletelyBalanced());
		t.fillSampleTree2();
		print(t, "sample tree 2");
		System.out.println("height 2 " + t.height());
		System.out.println(t.isCompletelyBalanced());
		t.fillSampleTree3();
		print(t, "sample tree 3");
		System.out.println("height 3 " + t.height());
		System.out.println(t.isCompletelyBalanced());
		t.jenn();
		print(t, "jenn");
		System.out.println("height jenn " + t.height());
		System.out.println(t.isCompletelyBalanced());
		t.one();
		print(t, "one");
		System.out.println("one " + t.height());
		System.out.println(t.isCompletelyBalanced());
		t.sameHeight();
		print(t, "sameHeight");
		System.out.println("sameHeight " + t.height());
		System.out.println(t.isCompletelyBalanced());
		t.trick();
		print(t, "trick");
		System.out.println("trick " + t.height());
		System.out.println(t.isCompletelyBalanced());

	}

	private static void print(BinaryTree t, String description) {
		System.out.println(description + " in preorder");
		t.printPreorder();
		System.out.println(description + " in inorder");
		t.printInorder();
		System.out.println();
	}

	//
	// private int height() {
	// if (myRoot != null) {
	// return myRoot.height();
	// // return Math.max(myRoot.myLeft.height(), myRoot.myRight.height())
	// // + 1;
	// } else {
	// return 0;
	// }
	// }

	private int height() {
		if (myRoot != null) {
			return myRoot.treeHeight(myRoot);
			// return Math.max(myRoot.myLeft.height(),
			// myRoot.myRight.height()) + 1;
		} else {
			return 0;
		}
	}

	public boolean isCompletelyBalanced() {
		if (myRoot != null) {
			if (myRoot.treeHeight(myRoot) == 0
					|| myRoot.treeHeight(myRoot) == 1) {
				return true;
			}
			if (myRoot.treeHeight(myRoot.myLeft) != myRoot
					.treeHeight(myRoot.myRight)) {
				return false;
			}
			if (!myRoot.helperIsCompletelyBalanced(myRoot))
				return false;
		}
		return true;
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

		// private int height() {
		// if (myRoot != null) {
		// return treeHeight(myRoot);
		// // return Math.max(myRoot.myLeft.height(),
		// // myRoot.myRight.height()) + 1;
		// } else {
		// return 0;
		// }
		// }

		private int treeHeight(TreeNode n) {
			if (n != null) {
				return Math.max(treeHeight(n.myLeft), treeHeight(n.myRight)) + 1;
			} else {
				return 0;
			}
		}

		public boolean helperIsCompletelyBalanced(TreeNode n) {
			if (n.myLeft == null && n.myRight == null) {
				return true;
			}

			if (n.myLeft != null && n.myRight != null) {
				if (helperIsCompletelyBalanced(n.myLeft)
						&& helperIsCompletelyBalanced(n.myRight)) {
					return true;
				}
				return false;

			}
			return false;
		}
	}
}
