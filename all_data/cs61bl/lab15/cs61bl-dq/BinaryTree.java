import java.util.ArrayList;

public class BinaryTree {

	private static TreeNode myRoot;

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
		myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c",
				new TreeNode("d"), new TreeNode("e")));
	}

	public void fillSampleTree5() {
		myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c",
				new TreeNode("d"), new TreeNode("a")));
	}

	public static void main(String[] args) {
		BinaryTree t;
		t = new BinaryTree();
		print(t, "the empty tree");
		t.fillSampleTree1();
		print(t, "sample tree 1");
		t.fillSampleTree2();
		print(t, "sample tree 2");
		t.fillSampleTree3();
		print(t, "sample tree 3");
		t.fillSampleTree4();
		print(t, "sample tree 4");
		t.print();
		t.fillSampleTree5();
		print(t, "sample tree 5");
		System.out.println(t.check());
	}

	private static void print(BinaryTree t, String description) {
		System.out.println(description + " in preorder");
		t.printPreorder();
		System.out.println(description + " in inorder");
		t.printInorder();
		System.out.println();
	}

	public void print() {
		if (myRoot != null) {
			myRoot.print(0);
		}
	}

	public boolean check() {
		alreadySeen = new ArrayList();
		try {
			isOK(myRoot);
			return true;
		} catch (IllegalStateException e) {
			return false;
		}
	}

	// Contains nodes already seen in the traversal.
	private ArrayList alreadySeen;

	private void isOK(TreeNode t) throws IllegalStateException {
		if (alreadySeen.contains(t.myItem)) {
			throw new IllegalStateException("not a legal binary tree");
		}
		alreadySeen.add(t.myItem);
		if (t.myRight != null) {
			isOK(t.myRight);
		}
		if (t.myLeft != null) {
			isOK(t.myLeft);
		}
	}

	public static BinaryTree fibTree(int n) {
		BinaryTree result = new BinaryTree();
		result.myRoot = result.fibTreeHelper(n);
		return result;
	}

	private TreeNode fibTreeHelper(int n) {
		if (n == 0) {
			return new TreeNode(new Integer(0));
		} else if (n == 1) {
			return new TreeNode(new Integer(1));
		} else {
			TreeNode left = fibTreeHelper(n - 1);
			TreeNode right = fibTreeHelper(n - 2);
			Integer newobj = ((Integer) left.myItem) + ((Integer) right.myItem);
			return new TreeNode(newobj, left, right);
		}

	}

	public void optimize() {
		optimizehelp(myRoot);
	}

	private static void optimizehelp(TreeNode current) {
		int leftnum;
		int rightnum;
		Integer total;
		if (current.myLeft == null || current.myRight == null) {
			return;
		}
		optimizehelp(current.myLeft);
		optimizehelp(current.myRight);
		try {
			leftnum = Integer.parseInt((String) current.myLeft.myItem);
			rightnum = Integer.parseInt((String) current.myRight.myItem);
			if (current.myItem.equals("+")) {
				total = leftnum + rightnum;
				current.myItem = total.toString();
			}
			if (current.myItem.equals("*")) {
				total = leftnum * rightnum;
				current.myItem = total.toString();
			}
			current.myLeft = null;
			current.myRight = null;
		} catch (NumberFormatException e) {
			// void method so returns nothing
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
			return new TreeNode(expr); // you fill this in
		} else {
			// expr is a parenthesized expression.
			// Strip off the beginning and ending parentheses,
			// find the main operator (an occurrence of + or * not nested
			// in parentheses, and construct the two subtrees.
			int nesting = 0;
			int opPos = 0;
			for (int k = 1; k < expr.length() - 1; k++) {
				// you supply the missing code
				if (expr.charAt(k) == '(') {
					nesting++;
				}
				if (expr.charAt(k) == ')') {
					nesting--;
				}
				if (nesting == 0
						&& (expr.charAt(k) == '*' || expr.charAt(k) == '+')) {
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
			TreeNode leftnode = exprTreeHelper(opnd1);
			TreeNode rightnode = exprTreeHelper(opnd2);
			return new TreeNode(op, leftnode, rightnode); // you fill this in
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

		public int heightOfBinaryTree(TreeNode node) {
			if (node == null) {
				return 0;
			} else {
				return 1 + Math.max(heightOfBinaryTree(node.myLeft),
						heightOfBinaryTree(node.myRight));
			}
		}

		public boolean isBalanced(TreeNode node) {
			if (node == null) {
				return true; // tree is empty
			} else {
				int lh = heightOfBinaryTree(node.myLeft);
				int rh = heightOfBinaryTree(node.myRight);
				if (lh - rh > 1 || rh - lh > 1) {
					return false;
				}
			}
			return true;
		}

		private static final String indent1 = "    ";

		private void print(int indent) {
			// TODO your code here
			int newindent = indent + 1;
			if (myRight != null) {
				myRight.print(newindent);
			}
			println(myItem, indent);
			// TODO your code here
			if (myLeft != null) {
				myLeft.print(newindent);
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
