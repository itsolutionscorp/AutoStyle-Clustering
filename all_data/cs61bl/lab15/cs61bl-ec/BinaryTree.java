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

	public void print() {
		if (myRoot != null) {
			myRoot.print(0);
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
		} else if (n == 1) {
			return new TreeNode(1);
		} else {
			TreeNode left = fibTreeHelper(n - 1);
			TreeNode right = fibTreeHelper(n - 2);
			return new TreeNode((Integer)left.myItem + (Integer)right.myItem, left, right);
		}
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

	// Contains nodes already seen in the traversal. 
	private ArrayList alreadySeen; 

	private void isOK(TreeNode t) throws IllegalStateException {
		if (alreadySeen.contains(t)) {
			throw new IllegalStateException();
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
					nesting--; 
				} else if (expr.charAt(k) == ')') {
					nesting++; 
				} 
				if (nesting == 0) {
					opPos = k+1;
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

	private TreeNode optimizeHelper(TreeNode t) {
		if (t.myLeft == null && t.myRight == null) {
			return t; 
		} else if (t.myLeft != null && t.myRight != null) {
			Integer left = null, right = null; 
			try {
				left = Integer.parseInt((String) t.myLeft.myItem); 
			} catch (NumberFormatException e) {
				t.myLeft = optimizeHelper(t.myLeft); 
			} 
			try {
				right = Integer.parseInt((String) t.myRight.myItem); 
			} catch (NumberFormatException e) {
				t.myRight = optimizeHelper(t.myRight); 
			}

			// after t.myLeft = optimizeHelper(t.myLeft); 
			// t.myLeft may be converted to a "good guy"
			// so just give t.myLeft another chance to be left
			try {
				// 2 possibilities: 
				// (1) t.myLeft.myItem is still a "bad guy," e.g. "b"
				// (2) t.myLeft.myItem is a "good guy," e.g. "5"
				// in order to handle both cases, we just blindly cast the exception 
				// (if t.myLeft.myItem is still a "bad guy"  
				left = (Integer) t.myLeft.myItem; 
			} catch (ClassCastException e) {
				
			} 
			// after t.myRight = optimizeHelper(t.myLeft); 
			// t.myRight may be converted to a "good guy"
			// so just give t.myRight another chance to be left
			try {
				right = (Integer) t.myRight.myItem; 
			} catch (ClassCastException e) {
			}

			if (t.myItem.equals("+")) {
				if (left != null && right != null) {
					t.myItem = right + left; 
					t.myLeft = null; 
					t.myRight = null; 
				}
			} else if (t.myItem.equals("*")) {
				if (left != null && right != null) {
					t.myItem = right * left; 
					t.myLeft = null; 
					t.myRight = null; 
				}
			}
		}
		return t;
	}

	public void fillSampleTree1() {
		myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
	}

	public void fillSampleTree2() {
		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d",
				new TreeNode("e"), new TreeNode("f")), null), new TreeNode("c"));
	}

	public void fillSampleTree3() {
		myRoot = new TreeNode("Hilfinger", new TreeNode("Hug", new TreeNode("Yeseon"), new TreeNode("Daniel")), new TreeNode("Joseph", new TreeNode("Sam"), new TreeNode("Armani")));
	}

	public void fillSampleTree4() {
		myRoot = new TreeNode('A', new TreeNode('B'), new TreeNode('C', new TreeNode('D'), new TreeNode('E')));
	}

	public void fillSampleTree5() {
		TreeNode weird = new TreeNode("d"); 
		myRoot = new TreeNode( "a", new TreeNode("b", null, weird), new TreeNode("c", weird, null)); 
	}

	public void fillSampleTree6() {
		TreeNode weird = new TreeNode("d"); 
		myRoot = new TreeNode( "a", new TreeNode("b", null, weird), new TreeNode("c", weird, null)); 
	}

	public static void main(String[] args) {
		BinaryTree t;
		t = new BinaryTree();
		print(t, "the empty tree");

		t.fillSampleTree1();
		print(t, "sample tree 1");
		System.out.println("t.height() " + t.height());
		t.print();
		System.out.println("t.isCompletelyBalanced() " + t.isCompletelyBalanced() + "\n\n");

		t.fillSampleTree2();
		print(t, "sample tree 2");
		System.out.println("t.height() " + t.height());
		t.print();
		System.out.println("t.isCompletelyBalanced() " + t.isCompletelyBalanced() + "\n\n");

		t.fillSampleTree3();
		print(t, "sample tree 3");
		System.out.println("t.height() " + t.height());
		t.print();
		System.out.println("t.isCompletelyBalanced() " + t.isCompletelyBalanced() + "\n\n");

		t.fillSampleTree4();
		System.out.println("t.check() " + t.check()); 
		print(t, "sample tree 4");
		System.out.println("t.height() " + t.height());
		t.print();
		System.out.println("t.isCompletelyBalanced() " + t.isCompletelyBalanced() + "\n\n");

		System.out.println("************** Test Check ****************"); 
		t.fillSampleTree5();
		System.out.println("t.check() " + t.check()); 

		t.fillSampleTree6();
		System.out.println("t.check() " + t.check()); 

		System.out.println("************** Expression Tree Check ****************");
		BinaryTree result = exprTree("((a+(5*(9+1)))+(6*5))"); 
		result.print(); 
		System.out.println("************** Optimize ***********" ); 
		result.optimize();
		result.print(); 
		
		System.out.println("************** Expression Tree Check ****************");
		BinaryTree result2 = exprTree("((a+5)*(2+10))"); 
		result2.print(); 
		System.out.println("************** Optimize ***********" ); 
		result2.optimize();
		result2.print(); 
		
		System.out.println("************** Expression Tree Check ****************");
		BinaryTree result3 = exprTree("(((5+6)*(7+b))*(((3+2)+1)*(7+2)))"); 
		result3.print(); 
		System.out.println("************** Optimize ***********" ); 
		result3.optimize();
		result3.print();
	}

	private static void print(BinaryTree t, String description) {
		System.out.println(description + " in preorder");
		t.printPreorder();
		System.out.println(description + " in inorder");
		t.printInorder();
		System.out.println();
	}


	public int height() {
		// always find the height of the entire tree
		if (myRoot != null) {
			return myRoot.height();
		}
		return 0; // the height of a null tree as 0
	}


	public boolean isCompletelyBalanced() {
		if (myRoot != null) {
			return myRoot.isCompletelyBalanced();
		}
		return true; // an empty tree is completely balanced
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
			if (myLeft == null && myRight == null) {
				// if you have no child, your bestSoFar is 1 (just you)
				return 1;
			} else {
				int bestSoFar = 1; // you're guaranteed to have at least one child, so your max depth must be at least 1
				// if you have child, each of your children will return his/her bestSoFar
				// and you just update bestSoFar and return it when you are done asking all your children
				if (myLeft != null) bestSoFar =  Math.max(myLeft.height(), bestSoFar);
				if (myRight != null) bestSoFar =  Math.max(myRight.height(), bestSoFar);
				return 1 + bestSoFar;
			}
		}


		private boolean isCompletelyBalanced() {		
			int numOfLR = 0;
			if (myLeft != null) ++numOfLR;
			if (myRight != null) ++numOfLR;

			if (numOfLR == 0) {
				// if you have no child, so you are balanced
				return true;
			} else if (numOfLR == 1) {
				// you only got one leg, so you are unbalanced
				return false;
			} else {
				// you have two legs, but need to check if they have the same length
				return myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced();
			}

		}


		private static final String indent1 = "    ";

		private void print(int indent) {
			if (myRight != null) {
				myRight.print(indent + 1);
			}
			println (myItem, indent + 1);
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




	} // end of TreeNode


}

