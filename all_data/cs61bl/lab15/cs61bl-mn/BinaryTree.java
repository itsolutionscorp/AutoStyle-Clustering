import java.util.ArrayList;
import java.util.List;

public class BinaryTree {

	private TreeNode myRoot;
	private List<TreeNode> alreadySeen;

	public BinaryTree() {
		myRoot = null;
	}

	public BinaryTree(TreeNode t) {
		myRoot = t;
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
		if (myRoot!=null) {
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
		myRoot = new TreeNode("A", new TreeNode("B"), new TreeNode("C", new TreeNode("D", new TreeNode("E"), new TreeNode("F")), null));
	}


	public void fillBalancedTree() {
		myRoot = new TreeNode("1", new TreeNode("2.0", new TreeNode("3.0"), new TreeNode("3.1")), new TreeNode("2.1", new TreeNode("3.2"), new TreeNode("3.3")));
	}

	public static BinaryTree fibTree(int n) {
		BinaryTree result = new BinaryTree();
		result.myRoot = result.fibTreeHelper(n);
		return result;
	}

	private TreeNode fibTreeHelper (int n) {
		if (n == 0 || n == 1) {
			return new TreeNode(n);
		}
		TreeNode node = new TreeNode((int) fibTreeHelper(n-1).myItem + (int) fibTreeHelper(n-2).myItem, fibTreeHelper(n-1), fibTreeHelper(n-2));
		return node;
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
			return new TreeNode(expr);// you fill this in
		} else {
			// expr is a parenthesized expression.
			// Strip off the beginning and ending parentheses,
			// find the main operator (an occurrence of + or * not nested
			// in parentheses, and construct the two subtrees.
			int nesting = 0;
			int opPos = 0;
			for (int k = 1; k < expr.length() - 1; k++) {
				char curChar = expr.charAt(k);
				if (curChar == '(') {
					nesting++;
				} else if (curChar == ')') {
					nesting--;
				} else if (nesting == 0 && (curChar == '+' || curChar == '*')) {
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
			return new TreeNode(op, exprTreeHelper(opnd1), exprTreeHelper(opnd2));
		}
	}

	public void optimize() {
		if (myRoot != null) {
			optimizeHelper(myRoot);
		}
	}

	public void optimizeHelper(TreeNode t) {
		if (t == null) {
			return;
		} 
		optimizeHelper(t.myLeft);
		optimizeHelper(t.myRight);
		if (((String) t.myItem).equals("*") && (Character.isDigit(((String) t.myLeft.myItem).charAt(0)) &&  Character.isDigit(((String) t.myRight.myItem).charAt(0)))) {
			t.myItem = Integer.parseInt((String) t.myLeft.myItem) *  Integer.parseInt((String) t.myRight.myItem) +"";
			t.myLeft = null;
			t.myRight = null;
		} else if (((String) t.myItem).equals("+") && (Character.isDigit(((String) t.myLeft.myItem).charAt(0)) &&  Character.isDigit(((String) t.myRight.myItem).charAt(0)))) {
			t.myItem = Integer.parseInt((String) t.myLeft.myItem) +  Integer.parseInt((String) t.myRight.myItem) +"";
			t.myLeft = null;
			t.myRight = null;
		} 
	}

	public static void main(String[] args) {
		BinaryTree t;
		t = new BinaryTree();
		print(t, "the empty tree");
		System.out.println(t.height());
		System.out.println(t.isCompletelyBalanced());
		t.fillSampleTree1();
		print(t, "sample tree 1");
		System.out.println(t.height());
		System.out.println(t.isCompletelyBalanced());
		t.fillSampleTree2();
		print(t, "sample tree 2");
		System.out.println(t.height());
		System.out.println(t.isCompletelyBalanced());
		t.fillSampleTree3();
		print(t, "sample tree 3");
		System.out.println(t.height());
		System.out.println(t.isCompletelyBalanced());
		t.fillBalancedTree();
		print(t, "sample tree balanced");
		System.out.println(t.height());
		System.out.println(t.isCompletelyBalanced());
		t.print();
		System.out.println(t.check());

		BinaryTree fib = fibTree(5);
		fib.print();

		BinaryTree expr = exprTree("((a+(5*(a+b)))+(6*5))");
		expr.print();
		expr.optimize();
		expr.print();

		BinaryTree anotherExpr = exprTree("((a+(5*(9+1)))+(6*5))");
		anotherExpr.optimize();
		anotherExpr.print();
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

	private void isOK(TreeNode t) throws IllegalStateException {		
		if (t != null) {
			if (alreadySeen.contains(t)) {
				throw new IllegalStateException ("TreeNode already seen");
			} else {
				alreadySeen.add(t);
				isOK(t.myLeft);
				isOK(t.myRight);
			}
		}
	}



	private static class TreeNode {

		public Object myItem;
		public TreeNode myLeft;
		public TreeNode myRight;
		private static final String indent1 = "    ";

		public TreeNode(Object obj) {
			myItem = obj;
			myLeft = myRight = null;
		}

		public TreeNode(Object obj, TreeNode left, TreeNode right) {
			myItem = obj;
			myLeft = left;
			myRight = right;
		}

		private void print(int indent) {
			if (myRight != null) {
				myRight.print(indent+1);
			}
			println (myItem, indent);
			if (myLeft != null) {
				myLeft.print(indent+1);
			}
		}

		private static void println(Object obj, int indent) {
			for (int k=0; k<indent; k++) {
				System.out.print(indent1);
			}
			System.out.println(obj);
		}

		private int height() {
			int longestSoFar=0;
			if (myItem == null) {
				return 0;
			}
			if (myLeft == null && myRight != null) {
				longestSoFar = myRight.height();
				longestSoFar++;
				return longestSoFar;
			}
			if (myLeft != null && myRight == null) {
				longestSoFar = myLeft.height();
				longestSoFar++;
				return longestSoFar;
			}
			if (myLeft == null && myRight == null){
				return 1;
			} else {
				longestSoFar = Math.max(myLeft.height(), myRight.height());
				longestSoFar++;
				return longestSoFar;
			}
		} 

		private boolean isCompletelyBalanced() {
			boolean isBalanced = true;
			if (myLeft == null && myRight == null && isBalanced == true) {
				isBalanced = true;
				return isBalanced;
			} else if (myLeft != null && myRight != null && isBalanced == true) {
				return (myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced());
			} else {
				isBalanced = false;
				return isBalanced;
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
	}
}