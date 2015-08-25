import java.io.PrintStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BinaryTree {

    private TreeNode myRoot;
    private ArrayList alreadySeen; 

    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
    }
    
    public int height() {
    	if (myRoot == null){
    		return 0;
    	}
		return myRoot.height();
	}
    
    public boolean isCompletelyBalanced(){
    	if (myRoot == null){
    		return true;
    	}
    	return myRoot.isCompletelyBalanced();
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

    public void fillSampleTree3(){
    	myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c", 
        		new TreeNode("d", new TreeNode("e"), new TreeNode("f")), null));
    }
    
    public void fillSampleTree4() {
    	myRoot = new TreeNode("a", new TreeNode("a", new TreeNode("b"), new TreeNode("a")), 
    			new TreeNode("a", new TreeNode("b"), new TreeNode("a")));
    }
    
    public void fillSampleTree5(){
    	myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c", 
        		new TreeNode("d"), new TreeNode("e")));
    }
    
    public TreeNode fillSampleTree6(){
    	myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c", 
        		new TreeNode("d"), new TreeNode("e")));
    	return myRoot;
    }
    
    public void fillSampleTree7(){
    	fibTree(1);
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
    
    private void isOK(TreeNode t) throws IllegalStateException{
    	if (t.myLeft == null && t.myRight == null){
    		return;
    	} else if(t.myLeft != null){
    		if (!alreadySeen.contains(t.myItem)){
    			alreadySeen.add(t.myItem);
    			isOK(t.myLeft);
    		} else{
    			throw new IllegalStateException("NOT OK");
    		}
    	}else if(t.myRight != null){
    		if (!alreadySeen.contains(t.myItem)){
    			alreadySeen.add(t.myItem);
    			isOK(t.myRight);
    		} else{
    			throw new IllegalStateException("NOT OK");
    		}
    	}
    }
    
    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
    }

    private TreeNode fibTreeHelper (int n){
    	if (n == 0){
    		return new TreeNode(0);
    	} else if (n == 1){
    		return new TreeNode(1);
    	} else{
    		return new TreeNode((int) fibTreeHelper(n-1).myItem + (int)fibTreeHelper(n-2).myItem, fibTreeHelper(n-1), fibTreeHelper(n-2));
    	}
    }
    
    public void optimize(){
    	myRoot = optimizeHelper(myRoot);
    }
    
    public TreeNode optimizeHelper(TreeNode t){
    	if (t.myLeft != null){
    		t.myLeft = optimizeHelper(t.myLeft);
    	}
    	if (t.myRight != null){
    		t.myRight = optimizeHelper(t.myRight);
    	}
    	if (t.myLeft != null && t.myRight != null){
    		try{
    			int leftint = Integer.parseInt((String) t.myLeft.myItem);
    			int rightint = Integer.parseInt((String) t.myRight.myItem);
    			if (t.myItem.equals("+")){
    				t.myItem = leftint + rightint;
    				t.myLeft = null;
    				t.myRight = null;
    			}
    			if (t.myItem.equals("*")){
    				t.myItem = leftint * rightint;
    				t.myLeft = null;
    				t.myRight = null;
    			}
    		} catch(NumberFormatException e){
    		}
    	}
    	return t;
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
                // you supply the missing code
            	char curr = expr.charAt(k);
            	if (curr == '('){
            		nesting ++;
            	} else if ((nesting == 0) && (curr == '*' || curr == '+')){
            		opPos = k;
            	} else if (curr == ')'){
            		nesting --;
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
            if (nesting != 0){
            	System.err.println("Malformed expression #noob");
            	return null ;
            }
            return new TreeNode(op, exprTreeHelper(opnd1), exprTreeHelper(opnd2));
            
            // you fill this in
        }
    }
    
    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
//        print(t, "the empty tree");
//        System.out.println(t.height());
//        System.out.println("should be true = " + t.isCompletelyBalanced() + "\n");        
//        t.fillSampleTree1();
//        print(t, "sample tree 1");
////        System.out.println(t.height());
////        System.out.println("should be true = " + t.isCompletelyBalanced() + "\n");        
//        t.fillSampleTree2();
//
//        System.out.println(t.check());
////        print(t, "sample tree 2");
////        System.out.println(t.height());
////        System.out.println("should be false = " + t.isCompletelyBalanced() + "\n");
//
////        
////        t.fillSampleTree3();    
//////        System.out.println("tree 3's height should be 4: " + t.height());
//////        System.out.println("should be false = " + t.isCompletelyBalanced());
////        t.print();
////        
//        t.fillSampleTree4();    
////        System.out.println("tree 4's height is " + t.height());
////        System.out.println("should be true = " + t.isCompletelyBalanced());
//        System.out.println(t.check());
//        
//        t.fillSampleTree5();    
////        t.print();
//        System.out.println(t.check());
//        
//        t.fillSampleTree7();    
//        t.print();
//      System.out.println(t.check());
//        fibTree(5).print();
        t = exprTree("((a+(5*(a+b)))+(6*5))");
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
		    if (myLeft == null && myRight == null) {
		    	return 1;
		    } else if (myLeft == null){
		    	return Math.max(bestSoFar, 1 + myRight.height());
		    } else if (myRight == null){
		    	return Math.max(bestSoFar, 1 + myLeft.height());
		    }
		    else {
		        bestSoFar = 1 + Math.max(myLeft.height(), myRight.height()); 
		        return bestSoFar;
		    }
		}
        
        private boolean isCompletelyBalanced(){
        	if (myLeft == null && myRight == null) {
		    	return true;
        	} else if (myLeft.height() != myRight.height()){
        		return false;
        	} else{
        		return myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced();
        	}
        }

        private static final String indent1 = "    ";

        private void print(int indent) {
        	if (myLeft == null &&  myRight == null){
        		println(myItem, indent);
        	} else{
        		if (myRight != null){
        			myRight.print(indent + 1);
        		}
        		println (myItem, indent);
        		if (myLeft != null){
        			myLeft.print(indent + 1);
        		}
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
