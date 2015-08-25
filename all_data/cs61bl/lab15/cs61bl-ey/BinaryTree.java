//import AmoebaFamily.Amoeba;
import java.util.*;

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

	public int height() {
		if (myRoot != null)
			return myRoot.height(myRoot);
		return 0;
	}

	public void noTree() {
		myRoot = new TreeNode("full", new TreeNode("a", new TreeNode("b",
				new TreeNode("c"), new TreeNode("d")), new TreeNode("e",
				new TreeNode("f"), new TreeNode("g"))), new TreeNode("h",
				new TreeNode("j", new TreeNode("k"), new TreeNode("l")),
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
		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("c"),
				new TreeNode("d")), new TreeNode("e", new TreeNode("f"), null));
	}

	public void trick() {
		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("c"), null),
				new TreeNode("d", new TreeNode("e"), null));
	}

	public void fillSampleTree7() {
		myRoot = new TreeNode("a", new TreeNode("b", null, null), new TreeNode(
				"c", new TreeNode("d"), new TreeNode("d")));
	}

	public void fillSampleTree8() {
		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d"),
				new TreeNode("d")), new TreeNode("c", new TreeNode("d"),
				new TreeNode("d")));
	}

	public void fillSampleTree9() {
		myRoot = new TreeNode("a", new TreeNode("b", null, null), new TreeNode(
				"c", new TreeNode("d"), new TreeNode("e")));
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
			throw new IllegalStateException("Try again guy");
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
			return new TreeNode(0);
		}
		if (n == 1) {
			return new TreeNode(1);
		}
		int fib = 0;
		int fib2 = 1;
		for (int i = 0; i < n; i++) {
			int temp = fib;
			fib = fib + fib2;
			fib2 = temp;
		}
		return new TreeNode(fib, fibTreeHelper(n - 1), fibTreeHelper(n - 2));
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
				if (expr.charAt(k) == ')')
					nesting--;
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
			return new TreeNode(((Character) expr.charAt(opPos)).toString(),
					exprTreeHelper(opnd1), exprTreeHelper(opnd2)); // you fill
																	// this in
		}
	}

	public void optimize() {

		if (myRoot != null) {
			for (int i = 0; i <= myRoot.height(myRoot); i++) {
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
		System.out.println("isOK? " + t.check());
		System.out.println(t.isCompletelyBalanced());
		System.out.println("__________");
		t.fillSampleTree9();
		t.print();
		System.out.println("isOK? " + t.check());
		t = t.fibTree(0);
		t.print();
		t = t.exprTree("((0+(5*a))+((6*55)+(1+(2+3))))");
		t = t.exprTree("(((20+30)+(70*60))+((a+b)*(1*2)))");
		t.print();
		System.out.println("__________");
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
				return Math.max(this.height(node.myLeft),
						this.height(node.myRight)) + 1;
			}
		}

		private boolean isCompletelyBalanced(TreeNode node) {
			if (node.myLeft == null && node.myRight == null)
				return true;
			if (node.myLeft != null && node.myRight != null) {
				if (isCompletelyBalanced(node.myLeft)
						&& isCompletelyBalanced(node.myRight))
					return true;
			}
			return false;
		}

		// In TreeNode
		private static final String indent1 = "    ";

		private void print(int indent) {
			if (this.myRight != null) {
				this.myRight.print(indent + 1);
			}
			println(myItem, indent);
			// TODO your code here
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
	}
}
