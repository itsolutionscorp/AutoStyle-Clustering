import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class BinaryTree {

	private TreeNode myRoot;

	public BinaryTree() {
		myRoot = null;
	}

	public BinaryTree(TreeNode t) {
		myRoot = t;
	}

	private HashSet alreadySeen;

	public boolean check() {
		alreadySeen = new HashSet();
		try {
			isOK(myRoot);
			return true;
		} catch (IllegalStateException e) {
			return false;
		}
	}

	private void isOK(TreeNode t) throws IllegalStateException {
		if (alreadySeen.contains(t.myItem)) {
			throw new IllegalStateException("No.");
		}
		alreadySeen.add(t.myItem);
		if (t.myLeft != null) {
			isOK(t.myLeft);
		}
		if (t.myRight != null) {
			isOK(t.myRight);
		}
	}

	public static BinaryTree fibTree(int n) {
		BinaryTree result = new BinaryTree();
		result.myRoot = result.fibTreeHelper(n);
		return result;
	}

	private TreeNode fibTreeHelper(int n) {
		if (n == 0) {
			return new TreeNode("0");
		}
		if (n == 1) {
			return new TreeNode("1");
		} else {
			int top = fibHelper(n);
			TreeNode shell = new TreeNode(top);
			// BinaryTree snail = new BinaryTree(shell);
			// snail.myRoot = new TreeNode(n);
			shell.myLeft = fibTreeHelper(n - 1);
			shell.myRight = fibTreeHelper(n - 2);
			return shell;
		}
	}

	public int fibHelper(int n) {
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		} else {
			return fibHelper(n - 1) + fibHelper(n - 2);
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
			for (int k = 1; k < expr.length() - 1; k++) {
				if (expr.charAt(k) == '(')
					nesting++;
				if (expr.charAt(k) == ')')
					nesting--;
				if (nesting == 0
						&& (expr.charAt(k) == '+' || expr.charAt(k) == '*')) {
					opPos = k;
					break;
				}
				// you supply the missing code
			}
			String opnd1 = expr.substring(1, opPos);
			String opnd2 = expr.substring(opPos + 1, expr.length() - 1);
			String op = expr.substring(opPos, opPos + 1);
			System.out.println("expression = " + expr);
			System.out.println("operand 1  = " + opnd1);
			System.out.println("operator   = " + op);
			System.out.println("operand 2  = " + opnd2);
			System.out.println();
			return new TreeNode(((Character) expr.charAt(opPos)).toString(),
					exprTreeHelper(opnd1), exprTreeHelper(opnd2));
			// you fill this in
		}
	}

	public void optimize() {

		if (myRoot != null) {
			for (int c = 0; c <= myRoot.treeHeight(myRoot); c++) {
				myRoot.optimize(myRoot);
			}
			if (((String) myRoot.myItem).equals("+")
					&& Character.isDigit(((String) myRoot.myLeft.myItem)
							.charAt(0))
					&& Character.isDigit(((String) myRoot.myRight.myItem)
							.charAt(0))) {
				myRoot.myItem = Integer.toString(Integer
						.parseInt((String) myRoot.myLeft.myItem)
						+ Integer.parseInt((String) myRoot.myRight.myItem));
				myRoot.myLeft = null;
				myRoot.myRight = null;
			} else if (((String) myRoot.myItem).equals("*")
					&& Character.isDigit(((String) myRoot.myLeft.myItem)
							.charAt(0))
					&& Character.isDigit(((String) myRoot.myRight.myItem)
							.charAt(0))) {
				myRoot.myItem = Integer.toString(Integer
						.parseInt((String) myRoot.myLeft.myItem)
						* Integer.parseInt((String) myRoot.myRight.myItem));
				myRoot.myLeft = null;
				myRoot.myRight = null;
			}
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

	public void print() {
		if (myRoot != null) {
			myRoot.print(0);
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
		t.print();
		t.fillSampleTree1();
		print(t, "sample tree 1");
		System.out.println("height 1 " + t.height());
		System.out.println(t.isCompletelyBalanced());
		t.print();
		t.fillSampleTree2();
		print(t, "sample tree 2");
		System.out.println("height 2 " + t.height());
		System.out.println(t.isCompletelyBalanced());
		t.print();
		t.fillSampleTree3();
		print(t, "sample tree 3");
		System.out.println("height 3 " + t.height());
		System.out.println(t.isCompletelyBalanced());
		t.print();
		t.jenn();
		print(t, "jenn");
		System.out.println("height jenn " + t.height());
		System.out.println(t.isCompletelyBalanced());
		t.print();
		t.one();
		print(t, "one");
		System.out.println("one " + t.height());
		System.out.println(t.isCompletelyBalanced());
		t.print();
		t.sameHeight();
		print(t, "sameHeight");
		System.out.println("sameHeight " + t.height());
		System.out.println(t.isCompletelyBalanced());
		t.print();
		t.trick();
		print(t, "trick");
		System.out.println("trick " + t.height());
		System.out.println(t.isCompletelyBalanced());
		t.print();

		// BinaryTree f = new BinaryTree();
		// f = fibTree(2);
		
		System.out.println("_____________");
		t = fibTree(0);
		t.print();
		System.out.println("_____________");
		t = fibTree(1);
		t.print();
		System.out.println("_____________");
		t = fibTree(2);
		t.print();
		System.out.println("_____________");
		t = fibTree(3);
		t.print();
		System.out.println("_____________");
		t = fibTree(4);
		t.print();
		System.out.println("_____________");
		t = fibTree(5);
		t.print();
		System.out.println("_____________");
		t = fibTree(7);
		t.print();
		System.out.println("_____________");
		t = exprTree("((a+(5*(a+b)))+(6*5))");
		t.print();
		System.out.println("_____________");
		t = exprTree("((a+(5*(9+1)))+(6*5))");
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

		private static final String indent1 = "    ";

		private void print(int indent) {

			if (this.myRight != null) {
				this.myRight.print(indent + 1);
			}

			println(myItem, indent);

			if (this.myLeft != null) {
				this.myLeft.print(indent + 1);
			}

		}

		private static void println(Object obj, int indent) {
			for (int k = 0; k < indent; k++) {
				System.out.print(indent1);
			}
			System.out.println(obj);
		}

		private void optimize(TreeNode node) {
			if (node == null)
				return;
			if (((String) node.myItem).equals("+")
					&& Character.isDigit(((String) node.myLeft.myItem)
							.charAt(0))
					&& Character.isDigit(((String) node.myRight.myItem)
							.charAt(0))) {
				node.myItem = Integer.toString(Integer
						.parseInt((String) node.myLeft.myItem)
						+ Integer.parseInt((String) node.myRight.myItem));
				node.myLeft = null;
				node.myRight = null;
			} else if (((String) node.myItem).equals("*")
					&& Character.isDigit(((String) node.myLeft.myItem)
							.charAt(0))
					&& Character.isDigit(((String) node.myRight.myItem)
							.charAt(0))) {
				node.myItem = Integer.toString(Integer
						.parseInt((String) node.myLeft.myItem)
						* Integer.parseInt((String) node.myRight.myItem));
				node.myLeft = null;
				node.myRight = null;
			} else {
				optimize(node.myLeft);
				optimize(node.myRight);
			}

		}

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
