import java.util.*;

public class BinaryTree {

	private TreeNode myRoot;
	private ArrayList alreadySeen;
	public BinaryTree() {
		myRoot = null;
	}

	public BinaryTree(TreeNode t) {
		myRoot = t;
	}

	public static BinaryTree fibTree(int n) {
		BinaryTree result = new BinaryTree();
		result.myRoot = result.fibTreeHelper(n);
		return result;
	}

	private TreeNode fibTreeHelper (int n){

		if (n == 0 || n == 1){
			return new TreeNode(n);
		}
		TreeNode temp = new TreeNode(n - 1);
		temp.myLeft = fibTreeHelper(n - 1);
		temp.myRight = fibTreeHelper(n - 2);
		return temp;
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

	private void isOK(TreeNode t) throws IllegalStateException{
		alreadySeen.add(t.myItem);
		if (t.myLeft != null) {
			if (alreadySeen.contains(t.myLeft.myItem)){
				throw new IllegalStateException("illegal");
			}
			isOK(t.myLeft);   		    		
		}
		if (t.myRight != null) {
			if (alreadySeen.contains(t.myRight.myItem)){
				throw new IllegalStateException("illegal");
			}
			isOK(t.myRight);   	
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
				opPos ++;
				if ((expr.charAt(k) == '+' || expr.charAt(k) == '*') && nesting == 0) {
					break;
				}
				if (expr.charAt(k) == '(') {
					nesting ++;

				}
				if (expr.charAt(k) == ')') {
					nesting --;

				}
			}
			String opnd1 = expr.substring(1, opPos);
			String opnd2 = expr.substring(opPos + 1, expr.length() - 1);
			String op = expr.substring(opPos, opPos + 1);
			//            System.out.println("expression = " + expr);
			//            System.out.println("operand 1  = " + opnd1);
			//            System.out.println("operator   = " + op);
			//            System.out.println("operand 2  = " + opnd2);
			System.out.println();
			// you fill this in
			TreeNode temp = new TreeNode(op);
			temp.myLeft = exprTreeHelper(opnd1);
			temp.myRight = exprTreeHelper(opnd2);
			return temp;

		}
	}


	public void optimize() {
		if (this.myRoot != null) {
			optimizeHelper(this.myRoot);
		}
	}


	public void optimizeHelper(TreeNode myRoot) {
		int leftNum = 0;
		int rightNum = 0;
		if (myRoot != null && myRoot.myRight != null && myRoot.myLeft != null){
			optimizeHelper(myRoot.myLeft);
			optimizeHelper(myRoot.myRight);
			try {
				leftNum =  Integer.parseInt(myRoot.myLeft.myItem.toString());
				rightNum = Integer.parseInt(myRoot.myRight.myItem.toString());

				if (myRoot.myItem.equals("*")) {
					myRoot.myItem = leftNum * rightNum;
					myRoot.myLeft = null;
					myRoot.myRight = null;
				}
				else if (myRoot.myItem.equals("+")) {
					myRoot.myItem = leftNum + rightNum;
					myRoot.myLeft = null;
					myRoot.myRight = null;
				}
			}
			catch (NumberFormatException e) {
				//skip since not an operand
			}
		}
	}

	// Contains nodes already seen in the traversal. 
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
		myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c", new TreeNode("d", new TreeNode("e"), new TreeNode("f")), null) );
	}

	public void fillSampleTree4() {
		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d"), new TreeNode("e")), new TreeNode("c", new TreeNode("f"), new TreeNode("g")));
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
		//        System.out.println(t.height());
		//        System.out.println(t.isCompletelyBalanced());
		//        t.print();
		//        System.out.println(t.check());


		//        t = fibTree(3);
		//        t.print();
		t = exprTree("((a+(5*(9+1)))+(6*5))");
		//		t.print();

		//        BinaryTree t2 = new BinaryTree();
		//        t2 = exprTree("((a+50)+30)");
		//        t2.print();
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
		if (myRoot == null) {
			return 0;
		}
		return myRoot.height();
	}

	public boolean isCompletelyBalanced() {
		if (myRoot == null) {
			return true;
		} else if (myRoot.height() == 1) {
			return true;
		} else {
			return myRoot.isCompletelyBalanced();
		}
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

		public int height() {
			if (myLeft == null && myRight == null) {
				return 1;
			} else if (myLeft == null && myRight != null) {
				return 1 + myRight.height();
			} else if (myLeft != null && myRight == null) {
				return 1 + myLeft.height();
			}
			return 1 + Math.max(myLeft.height(), myRight.height());
		}

		public boolean isCompletelyBalanced() {
			if (myLeft == null && myRight == null) {
				return true;
			} else if (myLeft == null && myRight != null) {
				return false;
			} else if (myRight == null && myLeft != null) {
				return false;
			} else if (myLeft.height() != myRight.height()) {
				return false;
			} else {
				return myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced();
			}
		}

		//In TreeNode
		private static final String indent1 = "    ";

		private void print(int indent) {
			// TODO your code here
			if (myRight != null) {
				myRight.print(indent + 1);
			}
			println (myItem, indent);
			// TODO your code here
			if (myLeft != null) {
				myLeft.print(indent + 1);
			}
		}

		private static void println(Object obj, int indent) {
			for (int k=0; k<indent; k++) {
				System.out.print(indent1);
			}
			System.out.println(obj);
		}
	}
}
