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

	// Contains nodes already seen in the traversal.
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
		if (!alreadySeen.contains(t.myItem)) {
			alreadySeen.add(t.myItem);
			if (t.myLeft != null) {
				isOK(t.myLeft);
			}
			if (t.myRight != null) {
				isOK(t.myRight);
			}
		} else {
			throw new IllegalStateException();
		}
	}

	public static BinaryTree fibTree(int n) {
		BinaryTree result = new BinaryTree();
		result.myRoot = result.fibTreeHelper(n);
		return result;
	}

	private TreeNode fibTreeHelper(int n) {
		if (n <= 0) {
			return new TreeNode(0);
		} else if (n == 1) {
			return new TreeNode(1);
		}
		int count = 0;
		int curr = 0;
		int next = 1;
		while (count < n) {
			int temp = curr;
			curr = next;
			next = temp + next;
			count++;
		}
		return new TreeNode(curr, fibTreeHelper(n - 1), fibTreeHelper(n - 2));
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
			return new TreeNode(expr.charAt(0)); // you fill this in
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
				} else if ((expr.charAt(k) == '+' || expr.charAt(k) == '*')
						&& nesting == 0) {
					opPos = k;
				} else if (expr.charAt(k) == ')') {
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
			return new TreeNode(expr.charAt(opPos), exprTreeHelper(opnd1),
					exprTreeHelper(opnd2)); // you fill this in
		}
	}

	// public void optimize() {
	// if (myRoot.myLeft == null && myRoot.myRight == null) {
	// return;
	// } else {
	// myRoot = optimizeHelper(myRoot);
	// }
	// }
	//
	// private TreeNode optimizeHelper(TreeNode t) {
	// if (t == null) {
	// return null;
	// }
	// if (t.myItem.equals('*')) {
	// // System.out.println(t.myLeft.myItem.getClass() + "got here");
	// if (Character.getNumericValue((char) t.myLeft.myItem) instanceof Integer
	// && Character.getNumericValue(t.myRight.myItem) instanceof Integer) {
	// t.myItem = Integer.parseInt((String) t.myLeft.myItem)
	// * Integer.parseInt((String) t.myRight.myItem);
	// t.myLeft = null;
	// t.myRight = null;
	// System.out.println("times entered");
	// return t;
	// }
	// } else if (t.myItem.equals('+')) {
	// if (t.myLeft.myItem instanceof Integer
	// && t.myRight.myItem instanceof Integer) {
	// t.myItem = Integer.parseInt((String) t.myLeft.myItem)
	// + Integer.parseInt((String) t.myRight.myItem);
	// t.myLeft = null;
	// t.myRight = null;
	// System.out.println("plus entered");
	// return t;
	// }
	// }
	// return new TreeNode(t.myItem, optimizeHelper(t.myLeft),
	// optimizeHelper(t.myRight));
	//
	// }

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
		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d"),
				new TreeNode("e")), new TreeNode("c", new TreeNode("f"),
				new TreeNode("g")));
	}

	public static void main(String[] args) {
		BinaryTree t;
		// t = new BinaryTree();
		// System.out.println(t.isCompletelyBalanced()); // true
		// System.out.println(t.height());
		// print(t, "the empty tree");
		// t.print();
		//
		// t.fillSampleTree1();
		// System.out.println(t.isCompletelyBalanced()); // true
		// System.out.println(t.height());
		// print(t, "sample tree 1");
		// t.print();
		//
		// t.fillSampleTree2();
		// System.out.println(t.isCompletelyBalanced()); // false
		// System.out.println(t.height());
		// print(t, "sample tree 2");
		// t.print();
		//
		// t.fillSampleTree3();
		// System.out.println(t.isCompletelyBalanced()); // false
		// System.out.println(t.height());
		// print(t, "sample tree 3");
		// t.print();
		// System.out.println(t.check());
		//
		// t.fillSampleTree4();
		// System.out.println(t.isCompletelyBalanced()); // true
		// System.out.println(t.height());
		// print(t, "sample tree 4");
		// t.print();
		// System.out.println(t.check());

		// t = fibTree(0);
		// t.print();
		// System.out.println();
		// t = fibTree(1);
		// t.print();
		// System.out.println();
		// t = fibTree(2);
		// t.print();
		// System.out.println();
		// t = fibTree(3);
		// t.print();
		// System.out.println();
		// t = fibTree(4);
		// t.print();

		t = exprTree("((a+(5*(a+b)))+(6*5))");
		t.print();
		System.out.println();
		// t.optimize();
		// t.print();
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

		private int height() {
			int bestSoFar = 1;
			if (myLeft != null) {
				bestSoFar = Math.max(myLeft.height() + 1, bestSoFar);
			}
			if (myRight != null) {
				bestSoFar = Math.max(myRight.height() + 1, bestSoFar);
			}
			return bestSoFar;
		}

		private boolean isCompletelyBalanced() {
			if (myLeft == null && myRight == null) {
				return true;
			}
			if ((myLeft.height() == myRight.height())
					&& myLeft.isCompletelyBalanced()
					&& myRight.isCompletelyBalanced()) {
				return true;
			}
			return false;
		}

		private static final String indent1 = "    ";

		private void print(int indent) {
			// TODO your code here
			if (myRight != null) {
				myRight.print(indent + 1);
			}
			println(myItem, indent);
			// TODO your code here
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
