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

	public void fillSampleTree5() {
		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d"),
				new TreeNode("e")), new TreeNode("c", new TreeNode("f"),
				null));
	}
	//Tree that has a node points to its parent
	public void fillSampleTree7() {
		TreeNode myError = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
		myRoot = new TreeNode("a", myError , new TreeNode("b", myError, null));
	}
	//
	public void fillSampleTree6() {
		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d"),
				null), new TreeNode("c", new TreeNode("f"),
				new TreeNode("g")));
	}

	public static void main(String[] args) {
		BinaryTree t;
		t = new BinaryTree();
		print(t, "the empty tree");
		System.out.println("height: " + t.height());
		System.out.println(t.isCompletelyBalanced());
		t.print();
		t.fillSampleTree1();
		print(t, "sample tree 1");
		System.out.println("height: " + t.height());
		System.out.println(t.isCompletelyBalanced());
		t.print();
		t.fillSampleTree2();
		print(t, "sample tree 2");
		System.out.println("height: " + t.height());
		System.out.println(t.isCompletelyBalanced());
		t.print();
		t.fillSampleTree3();
		print(t, "somple tree 3");
		System.out.println("height: " + t.height());
		System.out.println(t.isCompletelyBalanced());
		t.print();
		//Completely balanced tree of height 3
		t.fillSampleTree4();
		print(t, "somple tree 4");
		System.out.println("height: " + t.height());
		System.out.println(t.isCompletelyBalanced());
		t.print();

	
		//Unbalanced tree of height 3
		t.fillSampleTree5();
		print(t, "somple tree 5");
		System.out.println("height: " + t.height());
		System.out.println(t.isCompletelyBalanced());
		t.print();
		
		//Unbalanced tree of height 3
		t.fillSampleTree6();
		print(t, "somple tree 6");
		System.out.println("height: " + t.height());
		System.out.println(t.isCompletelyBalanced());
		t.print();
		
		t.fillSampleTree7();
		System.out.println("checking the traversal of sample7:"+t.check()+" should be false");
		
		//test the Fib tree
		BinaryTree fib=BinaryTree.fibTree(4);
		fib.print();
		
		//test expr Tree
		BinaryTree expr=BinaryTree.exprTree("((a+(5*(a+b)))+(6*5))");
		expr.print();
		
		//optimze test1
		expr.optimize();
		expr.print();
		
		//test optimzie tree2
		BinaryTree expr2=BinaryTree.exprTree("((a+(5*(9+1)))+(6*5))");
		expr2.print();
		expr2.optimize();
		expr2.print();
		

	}

	private static void print(BinaryTree t, String description) {
		System.out.println(description + " in preorder");
		t.printPreorder();
		System.out.println(description + " in inorder");
		t.printInorder();
		System.out.println();
	}

	private int heightHelper(TreeNode T) {
		if (T == null) {
			return 0;
		} else if (T.myLeft == null && T.myRight == null) {
			return 1;
		} else {
			return 1 + Math
					.max(heightHelper(T.myLeft), heightHelper(T.myRight));
		}
	}

	public int height() {
		return heightHelper(myRoot);
	}
	
	public static BinaryTree fibTree(int n) {
	    BinaryTree result = new BinaryTree();
	    result.myRoot = result.fibTreeHelper(n);
	    return result;
	}

	private TreeNode fibTreeHelper (int n){
		if(n==0){
			return new TreeNode(0);
		}else if(n==1){
			return new TreeNode(1);
		}else{
			TreeNode left=fibTreeHelper(n-1);
			TreeNode right=fibTreeHelper(n-2);
			return new TreeNode((int)left.myItem+(int)right.myItem,left,right);
		}
		
	}

	private boolean balancedHelper(TreeNode T) {
		if (T == null) {
			return true;
		} else if (T.myLeft == null && T.myRight == null) {
			return true;
		} else {
			return ((heightHelper(T.myLeft) == heightHelper(T.myRight)) && 
					balancedHelper(T.myLeft) == balancedHelper(T.myRight) == true);
		}
	}

	public boolean isCompletelyBalanced() {
		return balancedHelper(myRoot);
	}
	
	// Check if tree contains nodes already seen in the traversal. 
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
	
	private void isOK(TreeNode t) throws IllegalStateException{
		if(t==null)
			return;
		if(alreadySeen.contains(t))
			throw new IllegalStateException("Traverse the same item twice!");
		alreadySeen.add(t);
    	isOK(t.myLeft);
    	isOK(t.myRight);		  			
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
	        return new TreeNode(expr.charAt(0));
	    }else {
	        // expr is a parenthesized expression.
	        // Strip off the beginning and ending parentheses,
	        // find the main operator (an occurrence of + or * not nested)
	        // in parentheses, and construct the two subtrees.
	        int nesting = 0;
	        int opPos = 0;
	        for (int k = 1; k < expr.length() - 1; k++) {
	            // you supply the missing code
	        	if(nesting==0&&(expr.charAt(k)=='+'||expr.charAt(k)=='*')){
        			opPos=k;
        			break;
        		}
	        	if(expr.charAt(k)=='(')
	        		nesting++;
	        	if(expr.charAt(k)==')'){
	        		nesting--;
	        		
	        	}
	        	
	        }
	        String opnd1 = expr.substring(1, opPos);
	        String opnd2 = expr.substring(opPos + 1, expr.length()-1 );
	        String op = expr.substring(opPos, opPos + 1);
	        System.out.println("expression = " + expr);
	        System.out.println("operand 1  = " + opnd1);
	        System.out.println("operator   = " + op);
	        System.out.println("operand 2  = " + opnd2);
	        System.out.println();
	        TreeNode left=exprTreeHelper(opnd1);
	        TreeNode right=exprTreeHelper(opnd2);
	        return new TreeNode(op,left,right); // you fill this in
	    }
	}
	
	public void optimize() {
		if(myRoot!=null){
		optimizeHelper(myRoot);
		}
	}
	
	private void optimizeHelper(TreeNode t){
		if(t.myLeft==null){
			return;
		}else{
			optimizeHelper(t.myLeft);
			optimizeHelper(t.myRight);
			String left=t.myLeft.myItem.toString();
			String right=t.myRight.myItem.toString();
			System.out.println("left:"+left+"right:"+right);
			if(isNumeric(left) && isNumeric(right)){
				t.myItem=oper(left,right,t.myItem.toString().charAt(0));
				System.out.println("t.myItem is"+t.myItem);
				t.myLeft=null;
				t.myRight=null;
			}
			
		}
							
		
	}
	private static boolean isNumeric(String s){
		try{
			int a=Integer.parseInt(s);
			
		}catch(NumberFormatException e){
			return false;
		}return true;
	}
	private int oper(String left, String right, char ope){
		int l=Integer.parseInt(left);
		int r=Integer.parseInt(right);
		int result=0;
		if(ope=='+'){
			result=l+r;
		}
		else if(ope=='*'){
			result =l*r;
		}
		return result;
	}

	private static class TreeNode  {

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
		
		//In TreeNode
		private static final String indent1 = "    ";

		private void print(int indent) {
		    if (myRight != null){
		    	myRight.print(indent + 1);
		    }
		    println (myItem, indent);
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
