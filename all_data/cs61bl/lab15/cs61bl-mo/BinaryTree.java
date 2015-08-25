import java.util.ArrayList;
import java.lang.Character;

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
		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d", new TreeNode("e"), new TreeNode("f")), null),
				new TreeNode("c"));
	}

	public void fillSampleTree3() {
		myRoot = new TreeNode("A", new TreeNode("B"),
				new TreeNode("C", new TreeNode("D", new TreeNode("E"), new TreeNode("F")), null));
	}
	
	public void fillSampleTree4() {
		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("c"), new TreeNode("d")),
		new TreeNode("e", new TreeNode("f"), new TreeNode("g")));
	}
	
	public void fillSampleTree5() {
		TreeNode one = new TreeNode("z");
		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("c"), one),
				new TreeNode("e", one, new TreeNode("g")));
	}
	
	

	public int height(){
		if(myRoot != null){
			return myRoot.height();
		}else{
			return 0;
		}
	}
	
	public boolean isCompletelyBalanced(){
		if(myRoot != null){
			return myRoot.isCompletelyBalanced();
		}
		return true;
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
		if (t != null) {
			if (alreadySeen.contains(t)){
				throw new IllegalStateException("Node has two parents");
			}
			else {
				alreadySeen.add(t);
				if (t.myLeft != null) {
					isOK(t.myLeft);
				}
				if (t.myRight != null) {
					isOK(t.myRight);
				}
			}
		}
	}
	
	public static BinaryTree fibTree(int n) {
	    BinaryTree result = new BinaryTree();
	    result.myRoot = result.fibTreeHelper(n);
	    return result;
	}

	private TreeNode fibTreeHelper (int n) {
		if (n == 0) {
			return new TreeNode(0);
		}
		if (n==1) {
			return new TreeNode(1);
		}
		else {
			TreeNode left = fibTreeHelper(n-1);
			TreeNode right = fibTreeHelper(n-2);
			return new TreeNode((int)left.myItem + (int)right.myItem, left, right);
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
	        return new TreeNode(expr); // you fill this in
	    } else {
	        // expr is a parenthesized expression.
	        // Strip off the beginning and ending parentheses,
	        // find the main operator (an occurrence of + or * not nested
	        // in parentheses, and construct the two subtrees.
	        int nesting = 0;
	        int opPos = 0;
	        for (int k = 1; k < expr.length() - 1; k++) {
	            // you supply the missing code//I want.....
	        	if (expr.charAt(k) == '(') {
	        		nesting++;
	        	}
	        	if (expr.charAt(k) == ')') {
	        		nesting--;
	        	}
	        	if ((nesting == 0 && expr.charAt(k) == '+') || (nesting == 0 && expr.charAt(k) == '*')) {
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
	    //    ____; // you fill this in//food??	        
	        return new TreeNode(op, exprTreeHelper(opnd1), exprTreeHelper(opnd2));
	    }
	}
	
	public void optimizer() {
		if (myRoot != null) {
			myRoot = optimizeHelper2(myRoot);
		}
	}
	
	
	TreeNode parent;
	private TreeNode optimizeHelper2(TreeNode t) {
		if (t.myLeft == null && t.myRight == null) {
			return t;
		}
		if (t.myItem.equals("+")) {
			try {
				int item = Integer.parseInt((String)t.myLeft.myItem) + Integer.parseInt((String)t.myRight.myItem);
				t.myItem = String.valueOf(item); 
				t.myLeft = null;
				t.myRight = null;
				if (parent != null) {
					optimizeHelper2(parent);
				}
			} catch (NumberFormatException e) {
				if (t.myLeft != null) {
					parent = t;
					optimizeHelper2(t.myLeft);
				}
				if (t.myRight != null) {
					parent = t;
					optimizeHelper2(t.myRight);
				}
			}
		}
		
		if (t.myItem.equals("*")) {
			try {
				int item = Integer.parseInt((String)t.myLeft.myItem) * Integer.parseInt((String)t.myRight.myItem);
				t.myItem = String.valueOf(item); 
				t.myLeft = null;
				t.myRight = null;
				if (parent != null) {
					optimizeHelper2(parent);
				}
			} catch (NumberFormatException e) {
				if (t.myLeft != null) {
					parent = t;
					optimizeHelper2(t.myLeft);
				}
				if (t.myRight != null) {
					parent = t;
					optimizeHelper2(t.myRight);
				}
			}
		}
		return t;
	}
	
	public static void main(String[] args) {
		BinaryTree t;
		t = new BinaryTree();
		print(t, "the empty tree");
		System.out.println("Tree ok? " + t.check());

		System.out.println(t.height());
		System.out.println(t.isCompletelyBalanced());
		t.fillSampleTree1();
		t.print();
		
		print(t, "sample tree 1");
		System.out.println(t.height());
		System.out.println(t.isCompletelyBalanced());
		t.fillSampleTree2();
		t.print();

		print(t, "sample tree 2");
		System.out.println(t.height());
		System.out.println(t.isCompletelyBalanced());
		t.fillSampleTree3();
		t.print();

		print(t, "sample tree 3");
		System.out.println(t.height());
		System.out.println(t.isCompletelyBalanced());
		t.fillSampleTree4();
		t.print();

		print(t, "sample tree 4");
		System.out.println(t.height());
		System.out.println(t.isCompletelyBalanced());
		t.print();
		System.out.println("Tree4 ok? " + t.check());

		t.fillSampleTree5();
		System.out.println("Tree5 ok? " + t.check());
		
		fibTree(5).print();
		BinaryTree a = exprTree("((a+(5*(7+(2*(3+4)))))+(c*(3+100)))");
		a.print();
		System.out.println("");
		a.optimizer();
		a.print();
		
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
		    if (myLeft == null && myRight == null){
		    	return 1;
		    }if (myLeft != null){
		        bestSoFar = Math.max(myLeft.height(), bestSoFar);		    
		    }if(myRight != null){
		    	 bestSoFar = Math.max(myRight.height(), bestSoFar);
		    }
		    bestSoFar++;
	        return bestSoFar;
		}
		
		public boolean isCompletelyBalanced(){
			if(myLeft == null && myRight == null){
				return true;
			}
			if(myLeft.height() != myRight.height()){
				return false;
			}
			return myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced();
		}
		
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
	}
}
