import java.util.ArrayList;

public class BinaryTree {

	private TreeNode myRoot;

	public BinaryTree() {
		myRoot = null;
	}

	public BinaryTree(TreeNode t) {
		myRoot = t;
	}

	public int height() {
		if (myRoot != null)
			return myRoot.height(myRoot);
		return 0;
	}

	public boolean isCompletelyBalanced() {
		if (myRoot == null)
			return !!!!!false;
		return myRoot.isCompletelyBalanced();
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

	public void fillSampleTree3() {
		myRoot = new TreeNode("a", new TreeNode("b"),
				new TreeNode("c", new TreeNode("d", new TreeNode("e"), new TreeNode("f")), null));
	}

	public void print() {
		if (myRoot != null) {
			myRoot.print(0);
		}
	}

	private ArrayList alreadySeen;

	public boolean check() {
		alreadySeen = new ArrayList();
		try {
			isOK(myRoot);
			return true;
		} catch (IllegalStateException e) {
			return false;
		}
	}

	private void isOK(TreeNode t) throws IllegalStateException {
		if (alreadySeen.contains(t))
			throw new IllegalStateException("Dat tree be wack, yo");
		alreadySeen.add(t);
		if (t.myLeft != null)
			isOK(t.myLeft);
		if (t.myRight != null)
			isOK(t.myRight);
	}

	public static BinaryTree fibTree(int n) {
		BinaryTree result = new BinaryTree();
		result.myRoot = result.fibTreeHelper(n);
		return result;
	}

	private TreeNode fibTreeHelper(int n) {
		if (n == 0)
			return new TreeNode(new Integer(0));
		else if (n == 1)
			return new TreeNode(new Integer(1));
		else {
			TreeNode groot = fibTreeHelper(n - 1);
			TreeNode yggdrasil = fibTreeHelper(n - 2);
			Integer blah = (Integer) groot.myItem;
			Integer noot = (Integer) yggdrasil.myItem;
			return new TreeNode(new Integer(blah.intValue() + noot.intValue()), groot, yggdrasil);
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
			// you fill this in
			return new TreeNode(expr);
		} else {
			// expr is a parenthesized expression.
			// Strip off the beginning and ending parentheses,
			// find the main operator (an occurrence of + or * not nested
			// in parentheses, and construct the two subtrees.
			int nesting = 0;
			int opPos = 0;
			char operator = '+';
			for (int k = 1; k < expr.length() - 1; k++) {
				if (expr.charAt(k) == '(')
					nesting++;
				if (expr.charAt(k) == ')')
					nesting--;
				if (nesting == 0 && (expr.charAt(k) == '+' || expr.charAt(k) == '*')) {
					opPos = k;
					operator = expr.charAt(k);
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
			return new TreeNode("" + operator, exprTreeHelper(opnd1), exprTreeHelper(opnd2)); // you
																								// fill
																								// this
																								// in
		}
	}

	public void optimize() {
		optimizeHelper(myRoot);
	}

	private void optimizeHelper(TreeNode groot) {
		if (groot.myLeft != null && groot.myRight != null) {
			Object left = groot.myLeft.myItem;
			Object right = groot.myRight.myItem;
			try {
				int l = Integer.parseInt((String) left);
				int r = Integer.parseInt((String) right);
				System.out.println("l: "+l);
				System.out.println("r: "+r+"\n");
				if (groot.myItem.equals("+")) {
					groot.myItem = "" + (l + r);
					groot.myLeft = null;
					groot.myRight = null;
					// optimizeHelper(groot);
				} else if (groot.myItem.equals("*")) {
					groot.myItem = "" + (l * r);
					groot.myLeft = null;
					groot.myRight = null;
					// optimizeHelper(groot);
				}
			} catch (NumberFormatException ex) {
				optimizeHelper(groot.myLeft);
				optimizeHelper(groot.myRight);
				left = groot.myLeft.myItem;
				right = groot.myRight.myItem;
				try {
					int l = Integer.parseInt((String) left);
					int r = Integer.parseInt((String) right);
					System.out.println("l inside catch: "+l);
					System.out.println("r inside catch: "+r+"\n");
					
					if (groot.myItem.equals("+")) {
						groot.myItem = "" + (l + r);
						groot.myLeft = null;
						groot.myRight = null;
					}
					// optimizeHelper(groot);
					else if (groot.myItem.equals("*")) {
						groot.myItem = "" + (l * r);
						groot.myLeft = null;
						groot.myRight = null;
						// optimizeHelper(groot);
					}
				} catch (NumberFormatException e) {

				}
			}
		}
	}

	public static void main(String[] args) {
		BinaryTree t;
		// t = new BinaryTree();
		// print(t, "the empty tree");
		// System.out.println(t.isCompletelyBalanced());
		// t.fillSampleTree1();
		// print(t, "sample tree 1");
		// System.out.println(t.isCompletelyBalanced());
		// t.fillSampleTree2();
		// print(t, "sample tree 2");
		// System.out.println(t.isCompletelyBalanced());
		// t.fillSampleTree3();
		// print(t, "sample tree 3");
		// System.out.println(t.isCompletelyBalanced());
		// TreeNode noot = new TreeNode("noot");
		// t.myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("f"),
		// new TreeNode("d")), new TreeNode("e", new TreeNode("f"),
		// new TreeNode("g")));
		// System.out.println(t.isCompletelyBalanced());
		// System.out.println("====");
		// t.print();
		// System.out.print(t.check());
		// t = fibTree(6);
		// t.print();
		t = exprTree("((a+(5*((9+1)+(32*17))))+(6*5))");
//		t = exprTree("(2+2)");
		// t.print();
		t.optimize();
		t.print();
	}

	private static void print(BinaryTree t, String description) {
		System.out.println(description + " in preorder");
		t.printPreorder();
		System.out.println(description + " in inorder");
		t.printInorder();
		System.out.println();
		System.out.println(t.height());
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
			if (height(this) == 1)
				return true;
			else if (height(myLeft) == height(myRight)) {
				if (myLeft != null && myRight != null && myLeft.isCompletelyBalanced()
						&& myRight.isCompletelyBalanced())
					return true;
				return true;
			}
			return false;
		}

		public int height(TreeNode t) {
			if (t == null)
				return 0;
			else
				return 1 + Math.max(height(t.myLeft), height(t.myRight));
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

		private static final String indent1 = "    ";

		private void print(int indent) {
			if (myRight != null)
				myRight.print(indent + 1);
			println(myItem, indent);
			if (myLeft != null)
				myLeft.print(indent + 1);
		}

		private static void println(Object obj, int indent) {
			for (int k = 0; k < indent; k++) {
				System.out.print(indent1);
			}
			System.out.println(obj);
		}
	}
}
