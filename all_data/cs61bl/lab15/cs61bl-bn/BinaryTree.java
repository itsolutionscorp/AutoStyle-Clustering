import java.util.ArrayList;
import java.util.Stack;

public class BinaryTree {

	private TreeNode myRoot;
	protected ArrayList<TreeNode> alreadySeen;

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
		if (myRoot != null) {
			if (myRoot.myLeft == null && myRoot.myRight == null) {
				return true;
			}
			return myRoot.isCompletelyBalanced();
		}
		return true;
	}

	public boolean check() {
		alreadySeen = new ArrayList<TreeNode>();
		try {
			isOK(myRoot);
			return true;
		} catch (IllegalStateException e) {
			return false;
		}
	}

	private void isOK(TreeNode root) throws IllegalStateException {
		if (root != null) {
			root.isOK(alreadySeen);
		}

	}

	/**
	 * BINARY TREE
	 * 
	 * @param n
	 * @return
	 */
	public static BinaryTree fibTree(int n) {
		BinaryTree result = new BinaryTree();
		result.myRoot = result.fibTreeHelper(n);
		return result;
	}

	/**
	 * FIBTREEHELPER
	 * 
	 * @param n
	 * @return
	 */
	private TreeNode fibTreeHelper(int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		if (n == 0) {
			return new TreeNode(0);
		} else if (n == 1) {
			return new TreeNode(1);

		} else {
			return new TreeNode(
					((Integer) fibTreeHelper(n - 1).myItem + (Integer) fibTreeHelper(n - 2).myItem),
					fibTreeHelper(n - 1), fibTreeHelper(n - 2));
		}
	}

	/**
	 * EXPRTREE
	 * 
	 * @param s
	 * @return
	 */
	public static BinaryTree exprTree(String s) {
		BinaryTree result = new BinaryTree();
		result.myRoot = result.exprTreeHelper(s);
		return result;
	}

	// Return the tree corresponding to the given arithmetic expression.
	// The expression is legal, fully parenthesized, contains no blanks,
	// and involves only the operations + and *.
	private TreeNode exprTreeHelper(String expr) {
		Stack<Character> paren = new Stack<Character>();

		if (expr.charAt(0) != '(') {
			return new TreeNode(expr);

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
					paren.push('(');
				} else if (expr.charAt(k) == ')') {
					paren.pop();
				} else if (paren.isEmpty()
						&& (expr.charAt(k) == '+' || expr.charAt(k) == '*')) {
					opPos = k;
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
		simplify(myRoot);
	}

	// helper method for optimize
	public static TreeNode simplify(TreeNode current) {
		if (current.myLeft == null || current.myRight == null) {
			return current;
		}

		if (current.myItem.equals("+")) {

			TreeNode leftNode = simplify(current.myLeft);
			TreeNode rightNode = simplify(current.myRight);
			if (Character.isDigit(((String) leftNode.myItem).charAt(0))
					&& Character.isDigit(((String) rightNode.myItem).charAt(0)))

			{
				current.myItem = ""
						+ (Integer
								.parseInt((String) simplify(current.myLeft).myItem) + Integer
								.parseInt((String) simplify(current.myRight).myItem));
				current.myLeft = null;
				current.myRight = null;

			}

		}

		if (current.myItem.equals("*")) {

			TreeNode leftNode = simplify(current.myLeft);
			TreeNode rightNode = simplify(current.myRight);
			if (Character.isDigit(((String) leftNode.myItem).charAt(0))
					&& Character.isDigit(((String) rightNode.myItem).charAt(0)))

			{

				current.myItem = ""
						+ (Integer
								.parseInt((String) simplify(current.myLeft).myItem) * Integer
								.parseInt((String) simplify(current.myRight).myItem));
				current.myLeft = null;
				current.myRight = null;
			}

		}
		return current;

	}

	public static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
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
		myRoot = new TreeNode("A", new TreeNode("B"), new TreeNode("C",
				new TreeNode("D", new TreeNode("E"), new TreeNode("F")), null));
	}

	public void s3() {
		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d",
				new TreeNode("q"), new TreeNode("w")), new TreeNode("e")),
				new TreeNode("c", new TreeNode("F ", new TreeNode("q"),
						new TreeNode("w")), new TreeNode("g")));

	}

	public void s4() {
		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d",
				new TreeNode("q"), new TreeNode("w")), new TreeNode("e",
				new TreeNode("q"), new TreeNode("w"))), new TreeNode("c",
				new TreeNode("F ", new TreeNode("q"), new TreeNode("w")),
				new TreeNode("g", new TreeNode("q"), new TreeNode("w"))));
	}

	public void s5() {
		myRoot = new TreeNode("A", new TreeNode("B"), new TreeNode("C",
				new TreeNode("D"), new TreeNode("E")));
	}

	public void s6() {
		TreeNode wrong = new TreeNode("Wrong");
		myRoot = new TreeNode("A", wrong, new TreeNode("C", wrong,
				new TreeNode("D")));
	}

	public void s7() {
		TreeNode wrong = new TreeNode("Wrong");
		myRoot = new TreeNode("A", wrong, new TreeNode("B", wrong, wrong));
	}

	public void s8() {
		myRoot = new TreeNode("+", new TreeNode("1"), new TreeNode("9"));
	}

	public void s9() {
		myRoot = new TreeNode("+", new TreeNode("+", new TreeNode("1"),
				new TreeNode("1")), new TreeNode("9"));
	}

	public static void main(String[] args) {
		BinaryTree s = new BinaryTree();

		s = exprTree("(s+(3+(0*1)))");
		// s.print();
		// s.optimize();
		//
		// s.s9();
		// s.optimize();
		s.optimize();
		s.print();

		// // print(t, "the empty tree");
		// t.fillSampleTree3();
		// t.printPreorder();
		// t.print(0);
		// t.fillSampleTree1();
		// print(t, "sample tree 1");
		// System.out.println(t.isCompletelyBalanced());
		//
		// t.fillSampleTree2();
		// print(t, "sample tree 2");
		// System.out.println(t.isCompletelyBalanced());

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

	private static class TreeNode {

		public Object myItem;
		public TreeNode myLeft;
		public TreeNode myRight;
		private static final String indent1 = "    ";
		Stack<TreeNode> binaryStack = new Stack<TreeNode>();

		public TreeNode(Object obj) {
			myItem = obj;
			myLeft = myRight = null;

		}

		public void isOK(ArrayList<TreeNode> alreadySeen) {
			for (TreeNode t : alreadySeen) {
				if (t.equals(this)) {
					throw new IllegalStateException();
				}
			}
			alreadySeen.add(this);

			if (myLeft != null) {

				myLeft.isOK(alreadySeen);
			}
			if (myRight != null) {

				myRight.isOK(alreadySeen);
			}

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

		private void print(int indent) {
			if (myRight != null) {
				myRight.print(indent + 1);
			}
			println(myItem, indent);
			if (myLeft != null) {
				myLeft.print(indent + 1);
			}
		}

		public String toString() {
			return myItem.toString();
		}

		// public static void main(String[] args) {
		// BinaryTree t;
		// t = new BinaryTree();
		// // print(t, "the empty tree");
		// t.fillSampleTree3();
		// t.myRoot.print(1);
		// t.fillSampleTree1();
		// print(t, "sample tree 1");
		// System.out.println(t.isCompletelyBalanced());
		//
		// t.fillSampleTree2();
		// print(t, "sample tree 2");
		// System.out.println(t.isCompletelyBalanced());
		// }

		private static void println(Object obj, int indent) {
			for (int k = 0; k < indent; k++) {
				System.out.print(indent1);
			}
			System.out.println(obj);
		}

		private int height() {
			if (myLeft == null && myRight == null) {
				return 1;
			} else {
				int HighestSoFar;
				int l = 0;
				int r = 0;
				if (myLeft == null) {
				} else {
					l = 1;
					l = l + myLeft.height();
				}
				if (myRight == null) {
				} else {
					r = 1;
					r = r + myRight.height();
				}
				HighestSoFar = Math.max(l, r);

				return HighestSoFar;
			}
		}

		private boolean isCompletelyBalanced() {
			if ((myLeft != null && myRight == null)
					|| (myLeft == null && myRight != null)) {
				return false;
			} else if (myLeft == null && myRight == null) {
				return true;
			} else {
				if (myLeft.height() == myRight.height()) {
					return myLeft.isCompletelyBalanced()
							|| myRight.isCompletelyBalanced();

				}
				return false;
			}
		}

	}
}
