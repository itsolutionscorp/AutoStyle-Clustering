import java.util.ArrayList;

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

		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("c"),
				new TreeNode("d")), new TreeNode("e", new TreeNode("f"),
				new TreeNode("g")));

		myRoot.myRight.myLeft = myRoot.myLeft.myRight;
	}

	public static void main(String[] args) {
		BinaryTree t;
		t = new BinaryTree();

		System.out.println(t.height());
		System.out.println(t.isCompletelyBalanced());
		print(t, "the empty tree");

		t.fillSampleTree1();
		System.out.println(t.height());
		System.out.println(t.isCompletelyBalanced());
		print(t, "sample tree 1");
		t.print();
		System.out.println(t.check());

		t.fillSampleTree2();
		System.out.println(t.height());
		System.out.println(t.isCompletelyBalanced());
		print(t, "sample tree 2");
		t.print();
		System.out.println(t.check());

		t.fillSampleTree3();
		System.out.println(t.height());
		System.out.println(t.isCompletelyBalanced());
		print(t, "sample tree 3");
		t.print();
		System.out.println(t.check());

		t.fillSampleTree4();
		System.out.println(t.height());
		System.out.println(t.isCompletelyBalanced());
		print(t, "sample tree 4");
		t.print();
		System.out.println(t.check());

		t = fibTree(5);
		t.print();

		t = exprTree("((a+(5*(a+b)))+(6*5))");
		t.print();

		System.out.println("Optimize it!");

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

	public void print() {
		if (myRoot != null) {
			myRoot.print(0);
		}
	}

	private ArrayList<TreeNode> alreadySeen;

	public boolean check() {
		alreadySeen = new ArrayList<TreeNode>();
		try {
			isOK(myRoot);
			return true;
		} catch (IllegalStateException e) {
			return false;
		}
	}

	private void isOK(TreeNode t) throws IllegalStateException {
		if (alreadySeen.contains(t)) {
			throw new IllegalStateException("you've got two of the same node!");
		} else {
			alreadySeen.add(t);
			if (t.myLeft != null) {
				isOK(t.myLeft);
			}
			if (t.myRight != null) {
				isOK(t.myRight);
			}
		}
	}

	public static BinaryTree fibTree(int n) {
		BinaryTree result = new BinaryTree();
		result.myRoot = result.fibTreeHelper(n);
		return result;
	}

	public int fib(int n) {
		if (n == 0)
			return 0;
		else if (n == 1)
			return 1;
		else
			return fib(n - 1) + fib(n - 2);
	}

	private TreeNode fibTreeHelper(int n) {
		TreeNode returnNode;
		if (n == 0 || n == 1) {
			return new TreeNode(n);
		}
		return new TreeNode(fib(n), fibTreeHelper(n - 1), fibTreeHelper(n - 2));
	}

	public static BinaryTree exprTree(String s) {
		BinaryTree result = new BinaryTree();
		result.myRoot = result.exprTreeHelper(s);
		return result;
	}

	public void optimize() {
		if (myRoot != null) {
			myRoot.optimize();
		}
	}

	// Return the tree corresponding to the given arithmetic expression.
	// The expression is legal, fully parenthesized, contains no blanks,
	// and involves only the operations + and *.

	private TreeNode exprTreeHelper(String expr) {
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
				if (expr.charAt(k) == '+' || expr.charAt(k) == '*') {
					if (nesting == 0) {
						opPos = k;
					}
				}
				if (expr.charAt(k) == '(') {
					nesting++;
				}
				if (expr.charAt(k) == ')') {
					nesting--;
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

	private static class TreeNode {

		public Object myItem;
		public TreeNode myLeft;
		public TreeNode myRight;
		private static final String indent1 = "    ";

		private void optimize() {
			if (myLeft != null && myRight != null) {
				myLeft.optimize();
				myRight.optimize();
				int number;
				if (myItem.equals("+")) {
					try {
						number = Integer.parseInt((String) myLeft.myItem)
								+ Integer.parseInt((String) myRight.myItem);
						myItem = Integer.toString(number);
						myLeft = null;
						myRight = null;
					} catch (NumberFormatException n) {

					}
				}
				if (myItem.equals("*")) {
					try {
						number = Integer.parseInt((String) myLeft.myItem)
								* Integer.parseInt((String) myRight.myItem);
						myItem = Integer.toString(number);
						myLeft = null;
						myRight = null;
					} catch (NumberFormatException n) {

					}
				}
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

		private static void println(Object obj, int indent) {
			for (int k = 0; k < indent; k++) {
				System.out.print(indent1);
			}
			System.out.println(obj);
		}

		public int height() {
			int h = 1;
			if (myLeft == null && myRight == null) {
				return 1;
			} else if (myLeft == null) {
				h = Math.max(h, 1 + myRight.height());
			} else if (myRight == null) {
				h = Math.max(h, 1 + myLeft.height());
			} else {
				h = Math.max(Math.max(h, 1 + myRight.height()),
						Math.max(h, 1 + myLeft.height()));
			}
			return h;
		}

		public boolean isCompletelyBalanced() {
			boolean isBalanced = true;
			if (myLeft == null && myRight == null) {
				return true;
			} else if (myLeft == null) {
				return false;
			} else if (myRight == null) {
				return false;
			} else {
				return (isBalanced && myLeft.isCompletelyBalanced() && myRight
						.isCompletelyBalanced());
			}
		}

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
