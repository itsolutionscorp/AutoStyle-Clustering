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

	// Q2
	public void print() {
		if (myRoot != null) {
			myRoot.print(0);
		}
	}

	// Q3 check the tree isOK ?
	ArrayList<TreeNode> alreadySeen;

	public boolean check() {
		alreadySeen = new ArrayList<TreeNode>();
		try {
			isOK(myRoot);
			System.out.println(" OK  ");
			return true;
		} catch (IllegalStateException e) {
			return false;
		}

	}

	public void isOK(TreeNode t) throws IllegalStateException {
		if (alreadySeen.contains(t)) {
			throw new IllegalStateException("already seen this Node");
		} else {
			alreadySeen.add(t);
		}

		if (t.myLeft != null) {
			isOK(t.myLeft);
		}

		if (t.myRight != null) {
			isOK(t.myRight);
		}

	}

	// Q4 implement fib helper
	public static BinaryTree fibTree(int n) {
		BinaryTree result = new BinaryTree();
		result.myRoot = result.fibTreeHelper(n);
		return result;
	}

	private TreeNode fibTreeHelper(int n) {

		if (n == 0) {
			TreeNode right = new TreeNode((Integer) 0);
			return right;

		} else if (n == 1) {
			TreeNode left = new TreeNode((Integer) 1);
			return left;
		} else {
			TreeNode a = fibTreeHelper(n - 1);
			TreeNode b = fibTreeHelper(n - 2);
			return new TreeNode((int) a.myItem + (int) b.myItem, a, b);
		}

	}

	// Q5
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
			return new TreeNode(expr); // base case
		} else {
			// expr is a parenthesized expression.
			// Strip off the beginning and ending parentheses,
			// find the main operator (an occurrence of + or * not nested
			// in parentheses, and construct the two subtrees.
			int nesting = 0;
			int opPos = 0;

			for (int k = 1; k < expr.length() - 1; k++) { // fin the ops in the
															// express
				if (expr.charAt(k) == '(') {
					nesting++;
				} else if (expr.charAt(k) == ')') {
					nesting--;
				}
				if (nesting == 0
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
			TreeNode a = exprTreeHelper(opnd1);
			TreeNode b = exprTreeHelper(opnd2);
			return new TreeNode(op, a, b);
		}
	}

	// ((a+(5*(9+1)))+(6*5)) -> ((a+50)+30)
	public void optimize() {
		//myRoot.debug("");// print out everything before doing something
							// when am i wrong
		optimizeHelper(myRoot);
	}

	public void optimizeHelper(TreeNode e) {

		// complete binary search tree/ must have two child
		if (e.myLeft != null) {
			// super genius debugging method by jh
			//System.out.print(e.myItem.getClass());
			//System.out.print("            ");
			//System.out.println(e.myItem.toString());
			optimizeHelper(e.myLeft);
			optimizeHelper(e.myRight);
			if (('0' <= ((String) e.myLeft.myItem).charAt(0) && ((String) e.myLeft.myItem)
					.charAt(0) <= '9')
					&& ('0' <= ((String) e.myRight.myItem).charAt(0) && ((String) e.myRight.myItem)
							.charAt(0) <= '9')) {
				if (e.myLeft.myItem.equals("+")) { // when char small less some
													// length, same string is
													// same char.
					e.myItem = ""
							+ (Integer.parseInt((String) e.myLeft.myItem) + Integer
									.parseInt((String) e.myRight.myItem));
					e.myLeft = null;
					e.myRight = null;
				} else {
					e.myItem = ""
							+ (Integer.parseInt((String) e.myLeft.myItem) * Integer
									.parseInt((String) e.myRight.myItem));
					e.myLeft = null;
					e.myRight = null;
				}
			}

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

	public void printPostorder() {
		if (myRoot == null) {
			System.out.println("(empty tree)");
		} else {
			myRoot.printPostorder();
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

	public int findHeight(TreeNode myRoot) {
		if (myRoot == null)
			return 0;

		int lefth = findHeight(myRoot.myLeft);
		int righth = findHeight(myRoot.myRight);

		if (lefth > righth)
			return lefth + 1;
		else
			return righth + 1;
	}

	public boolean isCompletelyBalanced() {
		if (myRoot == null)
			return true;

		int lefth = findHeight(myRoot.myLeft);
		int righth = findHeight(myRoot.myRight);

		if (lefth == righth)
			return true;
		else
			return false;
	}

	public static void main(String[] args) {
		BinaryTree t, k, e;
		t = new BinaryTree();
		System.out.println("============TEST 1 ==========");
		System.out.println(t.findHeight(t.myRoot));
		System.out.println(t.isCompletelyBalanced());
		print(t, "the empty tree");
		t.fillSampleTree1();
		System.out.println("============TEST 2.1 check ==========");
		t.check();
		System.out.println(t.findHeight(t.myRoot));
		System.out.println(t.isCompletelyBalanced());
		print(t, "sample tree 1");
		t.fillSampleTree2();
		System.out.println("============TEST 2.2 check ==========");
		t.check();
		System.out.println(t.findHeight(t.myRoot));
		System.out.println(t.isCompletelyBalanced());
		print(t, "sample tree 2");
		t.fillSampleTree3();
		System.out.println("============TEST 2.3 check ==========");
		t.check();
		System.out.println(t.findHeight(t.myRoot));
		System.out.println(t.isCompletelyBalanced());
		print(t, "sample tree 3");
		t.fillSampleTree2();
		System.out.println("============TEST 3 fib ==========");
		// test case for fibTreeHelper
		k = fibTree(5);
		// k.printInorder();
		k.print();
		System.out.println("============TEST 4 expression ==========");
		// test case for expression Tree
		e = exprTree("((a+(5*(a+b)))+(6*5))");
		System.out.println("============TEST 5 opt ==========");
		e.optimize();
		e.print();
	}

	private static void print(BinaryTree t, String description) {
		System.out.println(description + " in preorder");
		t.printPreorder();
		System.out.println(description + " in inorder");
		t.printInorder();
		System.out.println(description + " in postorder");
		t.printPostorder();
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

		// In TreeNode
		private static final String indent1 = "    ";

		private void print(int indent) {
			indent++;
			if (myRight != null) {
				myRight.print(indent);
			}
			println(myItem, indent);
			// TODO your code here
			if (myLeft != null) {
				myLeft.print(indent);
			}

		}

		// method to debug the expression tree.
		private void debug(String indent) {
			if (myRight != null) {
				myRight.debug(indent + "    ");
			}
			System.err.println(indent + myItem.getClass() + "            "
					+ myItem);
			if (myLeft != null) {
				myLeft.debug(indent + "    ");
			}
		}

		private static void println(Object obj, int indent) {
			for (int k = 0; k < indent; k++) {
				System.out.print(indent1);
			}
			System.out.println(obj);
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

		private void printPostorder() {
			System.out.print(myItem + " ");
			if (myLeft != null) {
				myLeft.printPostorder();
			}
			if (myRight != null) {
				myRight.printPostorder();
			}
		}
	}
}