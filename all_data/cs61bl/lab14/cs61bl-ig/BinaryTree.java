import java.util.ArrayList;

import java.util.*;
public class BinaryTree {

	private TreeNode myRoot;
	// Contains nodes already seen in the traversal. 
	private ArrayList alreadySeen; 

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
		if (myRoot == null) {
			return 0;
		}
		return myRoot.height();
	}
	
	public boolean isCompletelyBalanced() {
		if(myRoot == null) {
			return true;
		}
		return myRoot.isCompletelyBalanced();
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
		myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c",
														new TreeNode("d"), new TreeNode("e") ));
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
		if (alreadySeen.contains(t.myItem)) {
			throw new IllegalStateException();
		} else {
			alreadySeen.add(t);
			if (t.myRight!=null){
				isOK(t.myRight);
			}
			if (t.myLeft!=null){
				isOK(t.myLeft);
			}
			
		}
	}
	
	

	public static void main(String[] args) {
		/*BinaryTree t;
		t = new BinaryTree();
		print(t, "the empty tree");
		t.fillSampleTree1();
		print(t, "sample tree 1");
		t.fillSampleTree2();
		print(t, "sample tree 2");
		t.fillSampleTree3();
		print(t, "sample tree 3");
		t.fillSampleTree4();
		t.print();
		System.out.println(t.check());*/
		String expression =  "((a+(5*(9+1)))+(6*5))";
		exprTree(expression).optimize().print();
		
		System.out.println("--------");
		exprTree("((a+50)+30)").print();
	}
	
	public void print() {
	    if (myRoot != null) {
	        myRoot.print(0);
	    }
	}
	
	private static void print(BinaryTree t, String description) {
		System.out.println(description + " in preorder");
		t.printPreorder();
		System.out.println(description + " in inorder");
		t.printInorder();
		System.out.println();
	}
	
	public static BinaryTree fibTree(int n) {
	    BinaryTree result = new BinaryTree();
	    result.myRoot = result.fibTreeHelper(n);
	    return result;
	}
	
	private TreeNode fibTreeHelper (int n) {
		if(n <= 1) return new TreeNode(n);
		TreeNode left = fibTreeHelper(n - 1);
		TreeNode right = fibTreeHelper(n - 2);
		return new TreeNode((Integer)right.myItem + (Integer)left.myItem, left, right);
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
	        return new TreeNode(expr);//____; // you fill this in
	    } else {
	        // expr is a parenthesized expression.
	        // Strip off the beginning and ending parentheses,
	        // find the main operator (an occurrence of + or * not nested
	        // in parentheses, and construct the two subtrees.
	        int nesting = 0;
	        int opPos = 0;
	        for(int k = 1; k < expr.length() - 1; k++) {
	        	if(expr.charAt(k) == '(') nesting++;
	        	if(expr.charAt(k) == ')') nesting--;
        		if((expr.charAt(k) == '+' || expr.charAt(k) == '*') && nesting == 0) {
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
	       return new TreeNode(op, exprTreeHelper(opnd1), exprTreeHelper(opnd2)); // you fill this in
	    }
	}
	
	public BinaryTree optimize() {
		myRoot = optimize(myRoot);
		return this;
	}
	
	public static TreeNode optimize(TreeNode node) {
		if(node.myRight == null || node.myLeft == null) return node;
		if(node.myRight.myRight == null && !isNumber((String)node.myRight.myItem)) {
			if(node.myLeft.myLeft != null) {
				node.myLeft = optimize(node.myLeft);
			}
			return node;
		}
		if(node.myLeft.myLeft == null && !isNumber((String)node.myLeft.myItem)) {
			if(node.myRight.myRight != null) {
				node.myRight = optimize(node.myRight);
			}
			return node;
		}
		TreeNode left = optimize(node.myLeft);
		TreeNode right = optimize(node.myRight);
		if(isNumber((String)left.myItem) && isNumber((String)right.myItem)) {
			node.myItem = calculate((String)node.myItem, (String)node.myRight.myItem, (String)node.myLeft.myItem);
			node.myLeft = null;
			node.myRight = null;
			
			return node;
		} else {
			node.myLeft = left;
			node.myRight = right;
			return node;
		}
	}
	
	public static String calculate(String operator, String operand1, String operand2) {
		if(operator.equals("*")) {
			return String.valueOf(Integer.valueOf(operand1) * Integer.valueOf(operand2));
		}
		return String.valueOf(Integer.valueOf(operand1) + Integer.valueOf(operand2));
	}
	
	public static boolean isNumber(String s) {
		for(int i = 0;  i < s.length(); i++) {
			if(!Character.isDigit(s.charAt(i))) {
				return false;
			}
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
		

		
		

		//In TreeNode
		private static final String indent1 = "    ";

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
		
		private boolean isCompletelyBalanced() {
			if(myRight == null && myLeft == null) return true;
			if(myRight == null || myLeft == null) return false;
			
			if(myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced() &&
			   myLeft.height() == myRight.height()) {
				return true;
			}
			return false;
		}

		private int height() {
			int height = 1;
			if (myLeft == null && myRight == null) {
				return height;
			}
			if (myLeft == null) {
				return myRight.height() + 1;
			}
			if (myRight == null) {
				return myLeft.height() + 1;
			}
			return Math.max(myRight.height()+1,myLeft.height()+1);
			
		}
	}

}
