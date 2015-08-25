import java.util.ArrayList;

public class BinaryTree {

	private TreeNode myRoot;

	public BinaryTree() {
		myRoot = null;
	}

	public BinaryTree(TreeNode t) {
		myRoot = t;
	}

	public void print() {
		if (myRoot != null) {
			myRoot.print(0);
		}
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
				new TreeNode("d"), new TreeNode("e")));
	}

	public void fillSampleTree4() {
		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d"),
				new TreeNode("e")), new TreeNode("c", new TreeNode("f"),
				new TreeNode("g")));
	}

	public int height() {
		if (myRoot != null) {
			return myRoot.height();
		}
		return 0;
	}

	public boolean isCompletelyBalanced() {
		if (myRoot != null) {
			return myRoot.isCompletelyBalanced();
		}
		return true;
	}

	public static void main(String[] args) {
		BinaryTree t;
		t = new BinaryTree();
		// print(t, "the empty tree");
		// t.fillSampleTree1();
		// print(t, "sample tree 1");
		// System.out.println("tree 1 height " + t.height());
		// System.out.println("tree 1 balanced " + t.isCompletelyBalanced());
		// t.fillSampleTree2();
		// print(t, "sample tree 2");
		// System.out.println("tree 2 height " + t.height());
		// System.out.println("tree 2 balanced " + t.isCompletelyBalanced());
		// t.fillSampleTree3();
		t = exprTree("((a+(5*(9+1)))+(6*5))");
		// print(t, "sample tree 3");
		// System.out.println("tree 3 height " + t.height());
		// System.out.println("tree 3 balanced " + t.isCompletelyBalanced());
		// t.fillSampleTree4();
		// print(t, "sample tree 4");
		// System.out.println("tree 4 height " + t.height());
		// System.out.println("tree 4 balanced " + t.isCompletelyBalanced());
		t.print();
		t.optimize();
		System.out.println();
		System.out.println();
		System.out.println();
		t.print();
		// System.out.println(t.check());
		// t = new BinaryTree();
		// TreeNode x = new TreeNode("a");
		// t.myRoot = new TreeNode("b",x,x);
		// System.out.println(t.check());
	}

	private static void print(BinaryTree t, String description) {
		System.out.println(description + " in preorder");
		t.printPreorder();
		System.out.println(description + " in inorder");
		t.printInorder();
		System.out.println();
	}

	public boolean check() {
		ArrayList<TreeNode> alreadySeen = new ArrayList<TreeNode>();
		try {
			isOK(myRoot, alreadySeen);
			return true;
		} catch (IllegalStateException e) {
			return false;
		}
	}

	private void isOK(TreeNode t, ArrayList<TreeNode> seen)
			throws IllegalStateException {
		if (seen.contains(t)) {
			throw new IllegalStateException();
		} else {
			seen.add(t);
			if (t.myLeft != null)
				isOK(t.myLeft, seen);
			if (t.myRight != null)
				isOK(t.myRight, seen);
		}
	}

	public static BinaryTree fibTree(int n) {
		BinaryTree result = new BinaryTree();
		result.myRoot = result.fibTreeHelper(n);
		return result;
	}

	private TreeNode fibTreeHelper(int n) {
		if (n == 0)
			return new TreeNode(0);
		if (n == 1)
			return new TreeNode(1);
		else {
			TreeNode left = fibTreeHelper(n - 1);
			TreeNode right = fibTreeHelper(n - 2);
			return new TreeNode((Integer) left.myItem + (Integer) right.myItem,
					left, right);
		}
	}

	public static BinaryTree exprTree(String s) {
		BinaryTree result = new BinaryTree();
		result.myRoot = result.exprTreeHelper(s);
		return result;
	}

	// Return the tree corresponding to the given arithmetic expression.
	// The expression is legal, fully parenthesized, contains no blanks,
	// and involves only the operations + and *.
	private TreeNode exprTreeHelper(String expr) {
		if (expr.charAt(0) != '(') {
			int opPos = 0;
			for (int k = 0; k < expr.length(); k++) {
				if (expr.charAt(k) == '*' || expr.charAt(k) == '+') {
					opPos = k;
					break;
				}
			}
			String opnd1 = expr.substring(0, opPos);
			String opnd2 = expr.substring(opPos + 1, expr.length());
			String op = expr.substring(opPos, opPos +1);
			if (opPos == 0) {
				return new TreeNode(op);
			}
			return new TreeNode(op, new TreeNode(opnd1), new TreeNode(opnd2));
		} else {
			// expr is a parenthesized expression.
			// Strip off the beginning and ending parentheses,
			// find the main operator (an occurrence of + or * not nested
			// in parentheses, and construct the two subtrees.
			int nesting = 0;
			int opPos = 0;
			for (int k = 1; k < expr.length() - 1; k++) {
				if (expr.charAt(k) == '(') {
					nesting++;
				} else if (expr.charAt(k) == ')') {
					nesting--;
				} else if ((expr.charAt(k) == '*' || expr.charAt(k) == '+')
						&& nesting == 0) {
					opPos = k;
					break;
				}
			}
			String opnd1 = expr.substring(1, opPos);
			String opnd2 = expr.substring(opPos + 1, expr.length() - 1);
			String op = expr.substring(opPos, opPos + 1);
			System.out.println("expression = " + expr);
			System.out.println("operand 1  = " + opnd1);
			System.out.println("operator   = " + op);
			System.out.println("operand 2  = " + opnd2);
			System.out.println();
			return new TreeNode(op, exprTreeHelper(opnd1),
					exprTreeHelper(opnd2));
		}
	}

	public void optimize() {
		optimizeHelper(myRoot);
	}

	public static void optimizeHelper(TreeNode t) {
		if (t.myLeft != null)
			optimizeHelper(t.myLeft);
		if (t.myRight != null)
			optimizeHelper(t.myRight);
		try {
			if (t.myLeft != null && t.myRight != null) {
				int left = Integer.valueOf("" + t.myLeft.myItem);
				int right = Integer.valueOf("" + t.myRight.myItem);
				if (((String)t.myItem).equals("*")) {
					t.myItem = left * right;
				} else if (((String)t.myItem).equals("+")) {
					t.myItem = left + right;
				}
				t.myLeft = null;
				t.myRight = null;
			}
		} catch (NumberFormatException e) {

		}
	}

	private static class TreeNode {

		public Object myItem;
		public TreeNode myLeft;
		public TreeNode myRight;

		public TreeNode(Object obj) {
			myItem = obj;
			myLeft = myRight = null;
		}

		public boolean isCompletelyBalanced() {
			if (myLeft == null && myRight == null) {
				return true;
			} else {
				if (myLeft == null) {
					return false;
				} else if (myRight == null) {
					return false;
				} else {
					return myLeft.height() == myRight.height()
							&& myLeft.isCompletelyBalanced()
							&& myRight.isCompletelyBalanced();
				}
			}
		}

		public TreeNode(Object obj, TreeNode left, TreeNode right) {
			myItem = obj;
			myLeft = left;
			myRight = right;
		}

		public int height() {
			if (myLeft == null && myRight == null) {
				return 1;
			} else {
				if (myLeft == null) {
					return 1 + myRight.height();
				} else if (myRight == null) {
					return 1 + myLeft.height();
				} else {
					return 1 + Math.max(myLeft.height(), myRight.height());
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

		private static final String indent1 = "    ";

		private void print(int indent) {
			if (myRight != null) {
				myRight.print(indent + 1);
			}
			println(myItem, indent);
			if (myLeft != null) {
				myLeft.print(indent + 1);
			}
		}

		private static void println(Object obj, int indent) {
			for (int k = 0; k < indent; k++) {
				System.out.print(indent1);
			}
			System.out.println(obj);
		}
	}
}
