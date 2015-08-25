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

	// Returns the height of the tree
	public int height() {
		if (myRoot == null) {
			return 0;
		} else {
			return myRoot.height();
		}
	}

	public boolean isCompletelyBalanced() {
		if (myRoot == null) {
			return true;
		} else {
			return myRoot.isCompletelyBalanced();
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
		TreeNode def = new TreeNode("d", new TreeNode("e"), new TreeNode("f"));
		TreeNode cdef = new TreeNode("c", def, null);
		TreeNode abcdef = new TreeNode("a", new TreeNode("b"), cdef);
		myRoot = abcdef;

	}

	public void fillSampleTree4() {
		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("c"),
				new TreeNode("d")), new TreeNode("e", new TreeNode("f"),
				new TreeNode("g")));
	}

	public void fillSampleTree5() {
		TreeNode d = new TreeNode("doubled");
		TreeNode cde = new TreeNode("c", d, new TreeNode("e"));
		TreeNode bd = new TreeNode("b", null, d);
		TreeNode abcde = new TreeNode("a", bd, cde);
		myRoot = abcde;
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
	
	// Contains nodes already seen in the traversal. 
	private ArrayList alreadySeen; 
	// (IllegalStateException is provided in Java.)
	
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
		if (t == null) return;
		if (alreadySeen.contains(t)) {
			throw new IllegalStateException("Binary tree is invalid because there is more than one " + t.myItem);
		}
		alreadySeen.add(t);
		isOK(t.myLeft);
		isOK(t.myRight);
	}
	
	public static BinaryTree fibTree(int n) {
	    BinaryTree result = new BinaryTree();
	    result.myRoot = result.fibTreeHelper(n);
	    return result;
	}

	private TreeNode fibTreeHelper (int n) {
		if (n == 0 || n == 1) {
			return new TreeNode(n);
		} else {
			TreeNode left = fibTreeHelper(n - 1);
			TreeNode right = fibTreeHelper(n - 2);
			return new TreeNode((Integer) left.myItem + (Integer) right.myItem, left, right);
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
//	    	int i = Math.max(expr.indexOf('+'), expr.indexOf('*'));
//	    	if (i == -1) {
	    		return new TreeNode(expr);
//	    	}
//	    	return new TreeNode(expr.charAt(i), exprTreeHelper(expr.substring(i+1)), exprTreeHelper(expr.substring(i+1, expr.length())));
	    } else {
	        // expr is a parenthesized expression.
	        // Strip off the beginning and ending parentheses,
	        // find the main operator (an occurrence of + or * not nested
	        // in parentheses, and construct the two subtrees.
	        int nesting = 0;
	        int opPos = 0;
	        for (int k = 1; k < expr.length() - 1; k++) {
	            // you supply the missing code
	        	char c = expr.charAt(k);
	        	if (c == '(') {
	        		nesting++;
	        	} else if (c == ')') {
	        		nesting--;
	        	} else if (nesting == 0 && (c == '+' || c == '*')) {
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
			myRoot.optimize();
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
			if (myLeft == null && myRight == null) {
				return 1;
			} else {
				int bestSoFar = 0;
				if (myLeft != null) {
					bestSoFar = Math.max(1 + myLeft.height(), bestSoFar);
				}
				if (myRight != null) {
					bestSoFar = Math.max(1 + myRight.height(), bestSoFar);
				}
				return bestSoFar;
			}
		}

		private boolean isCompletelyBalanced() {
			if (myLeft == null && myRight == null) {
				return true;
			} else if (myLeft == null || myRight == null) {
				return false;
			} else {
				boolean balanced = true;
				if (!myLeft.isCompletelyBalanced()
						|| !myRight.isCompletelyBalanced()) {
					balanced = false;
				}
				return balanced;
			}
		}
		
		public boolean canEvaluateExpr() {
			if (myItem.equals("+") || myItem.equals("*")) {
				return (myLeft.canEvaluateExpr() && myRight.canEvaluateExpr());
			}
			try {
				Integer.parseInt((String) myItem);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}
		
		public void evaluateExpr() {
			if (myLeft == null && myRight == null) {
				myItem = Integer.parseInt((String) myItem);
			} else {
				myLeft.evaluateExpr();
				myRight.evaluateExpr();
				if (myItem.equals("+")) {
					myItem = (Integer) myLeft.myItem + (Integer) myRight.myItem;
					myLeft = null;
					myRight = null;
				} else if (myItem.equals("*")) {
					myItem = (Integer) myLeft.myItem * (Integer) myRight.myItem;
					myLeft = null;
					myRight = null;
				}
			}
		}
		
		public void optimize() {
			if (canEvaluateExpr()) {
				evaluateExpr();
			} else {
				if (myRight != null) {
					myRight.optimize();
				}
				if (myLeft != null) {
					myLeft.optimize();
				}
			}
		}

		// In TreeNode
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
