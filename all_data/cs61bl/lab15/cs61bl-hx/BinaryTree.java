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

    public void fillSampleTree1() {
        myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
    }

    public void fillSampleTree2() {
        myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d",
                new TreeNode("e"), new TreeNode("f")), null), new TreeNode("c"));
    }
    
    public void fillSampleTree3() {
        myRoot = new TreeNode("a", new TreeNode("b", null, null),new TreeNode("c", new TreeNode("d",new TreeNode("e", null, null),new TreeNode("f", null, null)), null));
        
    }
    
    private void fillSampleTree4() {
        myRoot = new TreeNode("a", new TreeNode("b",  new TreeNode("b",null,null), new TreeNode("b",null,null)),new TreeNode("b", new TreeNode("b",null,null), new TreeNode("b",null,null)));
        
    }
    
    public void fillSampleTree5() {
        myRoot = new TreeNode("A", new TreeNode("B", null, null),new TreeNode("C", new TreeNode("D",null,null), new TreeNode("E",null,null)));
        
    }
    
    private void fillSampleTree6() {
    	TreeNode repeat = new TreeNode ("C", null , null);
        myRoot = new TreeNode("A", new TreeNode("B", repeat, null), new TreeNode("B", null, repeat));
        
    }

    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        //print(t, "the empty tree");
        //t.fillSampleTree1();
        //print(t, "sample tree 1");
       // System.out.println(t.isCompletelyBalanced());
        //t.fillSampleTree2();
        //print(t, "sample tree 2");
       // System.out.println(t.isCompletelyBalanced());
        //t.fillSampleTree3();
        //print(t, "sample tree 3");
        //System.out.println(t.height());
        //System.out.println(t.isCompletelyBalanced());
        //t.fillSampleTree4();
        //print(t, "sample tree 4");
        //System.out.println(t.height());
        //System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree6();
        t.print();
//        System.out.println(t.check());
//        fibTree(5).print();
        t = exprTree("((a+(5*(9+1)))+(6*5))");
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
    
    public int height(){
    	if (myRoot != null) {
			return myRoot.height();
		}		
		return 0;
    }
    
    public boolean isCompletelyBalanced(){
    	if (myRoot != null) {
			return myRoot.isCompletelyBalanced();
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
        	int bestSoFar = 0;
        	if (myLeft != null) {
				bestSoFar = Math.max(myLeft.height(), bestSoFar);
			}
        	if (myRight != null) {
				bestSoFar = Math.max(myRight.height(), bestSoFar);
			}
			return bestSoFar+1;
        }
        
        private boolean isCompletelyBalanced(){
        	if(myLeft==null&&myRight==null){
        		return true;
        	} else if (myLeft==null||myRight==null){
        		return false;
        	}
        	if (myLeft.height()!=myRight.height()){
        		return false;
        	}
        	return true;
        }
        
        private static final String indent1 = "    ";

        private void print(int indent) {
        	indent++;
        	if (myRight != null)    			
        		myRight.print(indent);
        	
            println (myItem, indent);
            
            if (myLeft != null)            	
            	myLeft.print(indent); 
        }

        private static void println(Object obj, int indent) {
            for (int k=0; k<indent; k++) {
                System.out.print(indent1);
            }
            System.out.println(obj);
        }
        
    }
    
    public void print() {
        if (myRoot != null) {
            myRoot.print(0);
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
    
    private void isOK(TreeNode t) throws IllegalStateException{
//    	if (t==null){
//    		throw new IllegalStateException();
//    	}
    	if (alreadySeen.contains(t)==true){
    		throw new IllegalStateException();
    	}
    	alreadySeen.add(t);
    	if (t.myRight != null)    			
    		isOK(t.myRight);

        if (t.myLeft != null)            	
        	isOK(t.myLeft);
    }
    
    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
    }

    private TreeNode fibTreeHelper (int n){
    	if(n==0){
    		return new TreeNode(0, null, null);
    	}
    	if(n==1){
    		return new TreeNode(1, null, null);
    	}
    	TreeNode rtnLeft = fibTreeHelper(n-1);
    	TreeNode rtnRight = fibTreeHelper(n-2);
    	return new TreeNode(((Integer)rtnLeft.myItem)+((Integer)rtnRight.myItem), rtnLeft, rtnRight);
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
            return new TreeNode(expr, null, null); // you fill this in
        } else {
            // expr is a parenthesized expression.
            // Strip off the beginning and ending parentheses,
            // find the main operator (an occurrence of + or * not nested
            // in parentheses, and construct the two subtrees.
            int nesting = 0;
            int opPos = 0;
            for (int k = 1; k < expr.length() - 1; k++) {
               
                if(expr.charAt(k)=='('){
                	nesting++;
                }
                if(expr.charAt(k)==')'){
                	nesting--;
                }
                if((expr.charAt(k)=='+'||expr.charAt(k)=='*')&&(nesting==0)){
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
    public void optimize() {
    	optimizeHelper(myRoot);
    }
    
    private void optimizeHelper(TreeNode t) {
    	if (t.myLeft!=null && t.myRight != null){
    		optimizeHelper(t.myLeft);
    		optimizeHelper(t.myRight);
		int rightInt=0;
		int leftInt=0;
    	try {
    		rightInt = Integer.parseInt((String)t.myRight.myItem);
    		leftInt = Integer.parseInt((String)t.myLeft.myItem);
    		if(((String)t.myItem).equals("*")){
			t.myItem = Integer.toString(rightInt*leftInt);
			t.myRight = null;
			t.myLeft = null;
			return;
    		}
			if(((String)t.myItem).equals("+")){
				t.myItem = Integer.toString(rightInt+leftInt);
				t.myRight = null;
				t.myLeft = null;
				return;
			}
    	} catch (NumberFormatException e){
    		return;
    	}
		
	
    	}
    	}
}
