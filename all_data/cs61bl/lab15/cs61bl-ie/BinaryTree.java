import java.util.ArrayList;

public class BinaryTree {

	private TreeNode myRoot;
	private ArrayList <Object> alreadySeen; 

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

	public int height(){
		if(myRoot == null){
			return 0;
		} else {
			return myRoot.height(1);
		}

	}

	public boolean isCompleteleyBalanced(){
		if(myRoot == null){
			return true;
		}
		else{
			return myRoot.isCompleteleyBalanced();
		}
	}

	public boolean check() { 
		alreadySeen = new ArrayList<Object>(); 
		try { 
			isOK(myRoot); 
			return true; 
		} catch (IllegalStateException e) { 
			return false; 
		} 
	}

	private void isOK(TreeNode t) throws IllegalStateException{
		if(t == null){
			return;
		} else if(alreadySeen.contains(t.myItem)){
			//System.out.println(t.myItem);
			throw new IllegalStateException();
		}else{
			alreadySeen.add(t.myItem);
			System.out.println(t.myItem);
			isOK(t.myLeft);
			isOK(t.myRight);
		}
	}

	public static BinaryTree fibTree(int n) {
		BinaryTree result = new BinaryTree();
		result.myRoot = result.fibTreeHelper(n);
		return result;
	}

	private TreeNode fibTreeHelper (int n){
		if(n == 0){
			return new TreeNode(0);
		} else if(n == 1){
			return new TreeNode(1);
		} else{
			TreeNode left = fibTreeHelper(n-1);
			TreeNode right = fibTreeHelper(n-2);
			int head = (int) left.myItem + (int) right.myItem;
			return new TreeNode(head, left, right);
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
				if(expr.charAt(k) == '('){
					nesting++;
				} else if(expr.charAt(k) ==')'){
					nesting--;
				} else if(nesting == 0 && (expr.charAt(k) == '*' || expr.charAt(k)=='+')){
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
			TreeNode left = exprTreeHelper(opnd1);
			TreeNode right = exprTreeHelper(opnd2);
			return new TreeNode(op, left, right);
		}
	}

	public void optimize(){
		optimizeHelper(myRoot);
	}

	public void optimizeHelper(TreeNode t){
		if(t.myLeft != null){
			optimizeHelper(t.myLeft);
		}
		if(t.myRight != null){
			optimizeHelper(t.myRight);
		}
		if(t.myItem.equals("+")){
			try{
				t.myItem = Integer.toString((Integer.parseInt((String)t.myLeft.myItem) + Integer.parseInt((String)t.myRight.myItem)));
				t.myLeft = null;
				t.myRight = null;
			} catch(Exception e){
			}
		} else if(t.myItem.equals("*")){
			try{
				t.myItem = Integer.toString(((Integer.parseInt((String)t.myLeft.myItem) * Integer.parseInt((String)t.myRight.myItem))));
				t.myLeft = null;
				t.myRight = null;
			} catch(Exception e){
			}	
		}
	}

	public void fillSampleTree1() {
		myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
	}

	public void fillSampleTree2() {
		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d",
				new TreeNode("e"), new TreeNode("f")), null), new TreeNode("c"));
	}

	public void fillSampleTree3(){
		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("c"), new TreeNode("x")), 
				new TreeNode("d", new TreeNode("z"), new TreeNode("z")));
	}

	public static void main(String[] args) {
		BinaryTree t;
		t = new BinaryTree();
		//print(t, "the empty tree");
		t.fillSampleTree1();
		//t.print();
		//print(t, "sample tree 1");
		t.fillSampleTree2();
		//t.print();
		//System.out.println(t.isCompleteleyBalanced());
		//print(t, "sample tree 2");
		t.fillSampleTree3();
		//System.out.println(t.check());
		//t.print();
		//System.out.println("");
		//print(t, "sample tree 3");
		// System.out.println(t.height());
		//System.out.println(t.isCompleteleyBalanced());
		BinaryTree exp = exprTree("((a+(5*(a+b)))+(6*5))");
		//exp.print();
		BinaryTree exp2 = exprTree("((a+(5*(9+1)))+(6*5))");
		exp2.print();
		System.out.println("----------------------------------");
		exp2.optimize();
		exp2.print();
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
			indent++;
			if (myRight != null){
				myRight.print(indent);
			}
			println (myItem, indent);
			if(myLeft != null){
				myLeft.print(indent);
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

		private int height(int soFar){
			if(myLeft != null && myRight != null){
				return Math.max(myLeft.height(soFar)+1, myRight.height(soFar)+1);
			} else if(myRight != null){
				return Math.max(myRight.height(soFar)+1, soFar);
			} else if (myLeft != null){
				return Math.max(myLeft.height(soFar)+1, soFar);
			} else{
				return 1;
			}
		}

		private boolean isCompleteleyBalanced(){
			if(myLeft == null && myRight == null){
				return true;
			} else if(myLeft != null && myRight != null){        		
				return myRight.isCompleteleyBalanced() && myLeft.isCompleteleyBalanced();
			} else{      		
				return false;
			}
		}
	}
}
