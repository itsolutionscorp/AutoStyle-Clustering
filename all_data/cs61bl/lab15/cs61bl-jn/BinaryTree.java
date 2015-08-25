import java.util.*;

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

	public boolean isCompletelyBalanced() {
		if (myRoot != null) {
			return myRoot.isCompletelyBalanced();
		}
		return true;
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

	public void fillSampleTreeBalaced3() {
		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d",
				new TreeNode("e"), new TreeNode("f")), new TreeNode("d",
				new TreeNode("e"), new TreeNode("f"))), new TreeNode("c",
				new TreeNode("d", new TreeNode("e"), new TreeNode("f")),
				new TreeNode("d", new TreeNode("e"), new TreeNode("f"))));
	}

	public void fillUltimateTREE1234() {
		TreeNode a = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
		myRoot = new TreeNode("a", a, new TreeNode("d", a, new TreeNode("s")));
	}

	public boolean check() {
		try {
			isOK(myRoot);
			return true;
		} catch (IllegalStateException e) {
			return false;
		}
	}

	public void isOK(TreeNode t) throws IllegalStateException {
		if (t == null) {
			return;
		}
		Stack<TreeNode> tress = t.helper();
		HashSet<TreeNode> unique = new HashSet<TreeNode>();
		while (tress.empty() == false) {
			TreeNode curr = tress.pop();
			if (unique.contains(curr)) {
				throw new IllegalStateException();
			}
			unique.add(curr);
		}

	}

	public static BinaryTree fibTree(int n) {
		BinaryTree result = new BinaryTree();
		result.myRoot = result.fibTreeHelper(n);
		return result;
	}

	private static TreeNode fibTreeHelper(int n) {
		if (n == 0) {
			return new TreeNode(0);
		}
		if (n == 1) {
			return new TreeNode(1);
		}
		TreeNode returned = new TreeNode((int) fibTreeHelper(n - 1).myItem
				+ (int) fibTreeHelper(n - 2).myItem, fibTreeHelper(n - 1),
				fibTreeHelper(n - 2));
		return returned;
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
			// if(expr.length()!=3){
			// return new TreeNode(expr.substring(1,2), new
			// TreeNode(expr.substring(0,1)),
			// exprTreeHelper(expr.substring(2,expr.length()-1)));
			// }
			return new TreeNode(expr);
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
				}
				if (expr.charAt(k) == ')') {
					nesting--;
				}
				if (nesting == 0
						&& (expr.charAt(k) == '*' || expr.charAt(k) == '+')) {
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
			// you fill this in
		}
	}

	public void optimize() {
		if (myRoot.myItem.equals("+") || myRoot.myItem.equals("*")) {
			int left = 0;
			int right = 0;
			boolean lefty = false;
			boolean righty = false;
			try {
				left = Integer.parseInt((String) myRoot.myLeft.myItem);
				lefty = true;
			} catch (NumberFormatException e) {
				BinaryTree temp = new BinaryTree(myRoot.myLeft);
				temp.optimize();
			}
			try {
				right = Integer.parseInt((String) myRoot.myRight.myItem);
				righty = true;

			} catch (NumberFormatException e) {
				BinaryTree temp = new BinaryTree(myRoot.myRight);
				temp.optimize();
			}

			try {
				left = Integer.parseInt((String) myRoot.myLeft.myItem);
				lefty = true;
			} catch (NumberFormatException e) {

			}
			try {
				right = Integer.parseInt((String) myRoot.myRight.myItem);
				righty = true;

			} catch (NumberFormatException e) {

			}
			if (myRoot.myItem.equals("+")) {
				if (righty && lefty) {
					int sum = left + right;
					myRoot.myItem = "" + sum;
					myRoot.myLeft = null;
					myRoot.myRight = null;
				}
			} else {
				if (righty && lefty) {
					int product = left * right;
					myRoot.myItem = "" + product;
					myRoot.myLeft = null;
					myRoot.myRight = null;
				}
			}

		}
	}

	public static void main(String[] args) {
		BinaryTree t;
		t = exprTree("((a+(5*(9+1)))+(6*5))");
		// t.print();
		// //
		// System.out.println("ahwrghaeghvv     "+t.myRoot.myRight.myLeft.myItem);
		// t.optimize();
		//
		// t.print();
		// BinaryTree l=exprTree("(1*(5+6))");
		// System.out.println("wegew     "+l.myRoot.myRight.myItem);
		// l.print();
		print(t, "the empty tree");
		// System.out.println(t.isCompletelyBalanced());
		t.fillSampleTree1();
		print(t, "sample tree 1");
		// System.out.println(t.isCompletelyBalanced());
		t.fillSampleTree2();
		print(t, "sample tree 2");
		// System.out.println(t.isCompletelyBalanced());
		t.fillSampleTree3();
		print(t, "sample tree 3");
		t.print();
		// t.fillUltimateTREE1234();
		// System.out.println(t.check());
		// // System.out.println(t.isCompletelyBalanced());
		// t.fillSampleTreeBalaced3();
		// System.out.println(t.check());
		// System.out.println("HI");
		// t.print();
		// // System.out.println(t.isCompletelyBalanced());
		// t=fibTree(5);
		// System.out.println(t.check());
		// t.print();
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

	private static class TreeNode {

		public Object myItem;
		public TreeNode myLeft;
		public TreeNode myRight;
		private static final String indent1 = "    ";

		private void print(int indent) {
			Stack<TreeNode> alpha = helper();
			Stack<Integer> beta = helperint(0);

			while (!alpha.empty()) {
				TreeNode thing = alpha.pop();
				int thingy = beta.pop();
				println(thing.myItem, thingy);
			}
		}

		private Stack<TreeNode> helper() {
			Stack<TreeNode> bucket = new Stack<TreeNode>();
			if (myLeft != null) {
				Stack<TreeNode> left = myLeft.helper();
				Stack<TreeNode> temp = new Stack<TreeNode>();
				while (!left.empty()) {
					temp.push(left.pop());
				}
				while (!temp.empty()) {
					bucket.push(temp.pop());
				}
			}
			bucket.push(this);
			if (myRight != null) {
				Stack<TreeNode> right = myRight.helper();
				Stack<TreeNode> temp = new Stack<TreeNode>();
				while (!right.empty()) {
					temp.push(right.pop());
				}
				while (!temp.empty()) {
					bucket.push(temp.pop());
				}

			}
			return bucket;
		}

		private Stack<Integer> helperint(int x) {
			Stack<Integer> bucket = new Stack<Integer>();
			if (myLeft != null) {
				Stack<Integer> left = myLeft.helperint(x + 1);
				Stack<Integer> temp = new Stack<Integer>();
				while (!left.empty()) {
					temp.push(left.pop());
				}
				while (!temp.empty()) {
					bucket.push(temp.pop());
				}
			}
			bucket.push(x);
			if (myRight != null) {
				Stack<Integer> right = myRight.helperint(x + 1);
				Stack<Integer> temp = new Stack<Integer>();
				while (!right.empty()) {
					temp.push(right.pop());
				}
				while (!temp.empty()) {
					bucket.push(temp.pop());
				}
			}
			return bucket;
		}

		private static void println(Object obj, int indent) {
			for (int k = 0; k < indent; k++) {
				System.out.print(indent1);
			}
			System.out.println(obj);
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

		public int height() {
			if (myLeft == null && myRight == null) {
				return 1;
			} else {
				if (myLeft == null) {
					return 1 + myRight.height();
				}
				if (myRight == null) {
					return 1 + myLeft.height();
				}
				return 1 + Math.max(myLeft.height(), myRight.height());
			}
		}

		public boolean isCompletelyBalanced() {
			if (myLeft == null && myRight == null) {
				return true;
			}
			if (myLeft == null) {
				return false;
			}
			if (myRight == null) {
				return false;
			}
			if (myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced()
					&& myLeft.height() == myRight.height()) {
				return true;
			}
			return false;
		}

	}
}
