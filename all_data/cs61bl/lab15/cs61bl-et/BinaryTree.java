import java.util.ArrayList;
import java.util.Stack;

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
		if (myRoot == null){
			return 0;
		} else {
			return myRoot.height();
		}
	}

	public boolean isCompletelyBalanced() {
		if (myRoot == null){
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
		myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode
				("c",new TreeNode("d", new TreeNode("e"), new TreeNode("f") ),null));
	}
	public void fillSampleTree4() {
		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode
				("c"),new TreeNode("d")), new TreeNode("e", new TreeNode("f"),new TreeNode("g")));
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
		System.out.println(t.height());
		t.fillSampleTree4();
		System.out.println(t.isCompletelyBalanced());
		t.fillSampleTree3();
		t=fibTree(5);
		t.print();
		t=exprTree("((a+(5*(a+b)))+(6*5))");
		t.print();

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
		if (expr.charAt(0) != '(') {// you fill this in
			if (expr.length()==1){
				return new TreeNode(expr);
			} else {
				return new TreeNode(expr.substring(1, 2),
						new TreeNode(expr.charAt(0)),
						exprTreeHelper(expr.substring(2, expr.length())));
			}
		} else {
			// expr is a parenthesized expression.
			// Strip off the beginning and ending parentheses,
			// find the main operator (an occurrence of + or * not nested
			// in parentheses, and construct the two subtrees.
			int nesting = 0;
			int opPos = 0;
			for (int k = 1; k < expr.length() - 1; k++) {
				if (expr.charAt(1)!= '('){
					return new TreeNode(expr.substring(2, 3),
							new TreeNode(expr.charAt(1)),
							exprTreeHelper(expr.substring(3, expr.length()-1)));
				}
				if (expr.charAt(k)=='('){
					nesting++;
				}
				if (expr.charAt(k)==')'){
					nesting--;
				}
				if (nesting == 0){
					opPos = k + 1;
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
			return new TreeNode(op,exprTreeHelper(opnd1),exprTreeHelper(opnd2)); // you fill this in
		}
	}

	public void print() {
		if (myRoot != null) {
			myRoot.print(0);
		}
	}

	public boolean check() { 
		ArrayList alreadySeen = new ArrayList(); 
		try { 
			isOK(myRoot); 
			return true; 
		} catch (IllegalStateException e) { 
			return false; 
		} 
	}

	private void isOK(TreeNode t) throws IllegalStateException{
		ArrayList everyelement = new ArrayList();
		if (t.myLeft==null&&t.myRight==null){
			if (everyelement.contains(t.myItem)){
				throw new IllegalStateException("opps");
			}
			everyelement.add(t.myItem);
		} else if(t.myLeft==null){
			if (everyelement.contains(t.myItem)){
				throw new IllegalStateException("opps");
			}
			everyelement.add(t.myItem);
			isOK(t.myRight);
		} else if(t.myRight==null){
			if (everyelement.contains(t.myItem)){
				throw new IllegalStateException("opps");
			}
			everyelement.add(t.myItem);
			isOK(t.myLeft);
		}else{
			if (everyelement.contains(t.myItem)){
				throw new IllegalStateException("opps");
			}
			everyelement.add(t.myItem);
		}
	}

	public static BinaryTree fibTree(int n) {
		TreeNode cur1 = new TreeNode(1,new TreeNode(1),new TreeNode("0"));
		BinaryTree result = new BinaryTree();
		result.myRoot = result.fibTreeHelper(n);
		return result;
	}
	TreeNode cur1 = new TreeNode(1,new TreeNode(1),new TreeNode("0"));
	private TreeNode fibTreeHelper (int n){

		if (!((Integer)cur1.myItem).equals(n)){
			cur1 = new TreeNode((Integer)cur1.myItem+(Integer)cur1.myLeft.myItem,cur1,cur1.myLeft);
			fibTreeHelper (n);
		}
		return cur1;
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

		private int height(){
			int bestSoFar = 1;
			if (myLeft!=null&&myRight!=null){
				bestSoFar = bestSoFar+Math.max(myLeft.height(), myRight.height());
			} else if(myLeft!=null){
				bestSoFar = bestSoFar + myLeft.height();
			} else if(myRight!=null){
				bestSoFar = bestSoFar + myRight.height();
			}
			return bestSoFar;
		}

		private boolean isCompletelyBalanced(){
			if (myLeft==null&& myRight==null){
				return true;
			}else if (myLeft!=null&& myRight==null){
				return false;
			}else if (myLeft==null&& myRight!=null){
				return false;
			} else {
				return myLeft.isCompletelyBalanced()&&myRight.isCompletelyBalanced();
			}
		}

		private static final String indent1 = "    ";

		private void print(int indent) {
			// TODO your code here
			if (myRight!=null){
				myRight.print(indent+1);

			} 
			BinaryTree.TreeNode.println(myItem,indent);
			if (myLeft!=null){
				myLeft.print(indent+1);
			}

			// TODO your code here
		}

		private static void println(Object obj, int indent) {
			for (int k=0; k<indent; k++) {
				System.out.print(indent1);
			}
			System.out.println(obj);
		}



	}
}
