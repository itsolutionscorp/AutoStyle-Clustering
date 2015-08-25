import java.util.HashSet;


public class BinaryTree {

    private TreeNode myRoot;
    
    HashSet alreadySeen;
    
    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
    }
    
    public void print() {
        if (myRoot != null) {
            myRoot.print(0);
        }
    }
    
    public boolean check() { 
        alreadySeen = new HashSet(); 
        try { 
            isOK(myRoot); 
            return true; 
        } catch (IllegalStateException e) { 
            return false; 
        } 
    }

    private void isOK(TreeNode t) throws IllegalStateException{
    	if (alreadySeen.contains(t)){
    		throw new IllegalStateException("node appeared more than once");
    	}
    	alreadySeen.add(t);
    	if (t.myLeft!=null){
    		isOK(t.myLeft);
    	}
    	if (t.myRight!=null){
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
        	if (Character.isLetter(expr.charAt(0))){
        		return new TreeNode(expr.charAt(0));
        	} else {
        		return new TreeNode(new Integer(Character.getNumericValue(expr.charAt(0))));
        	}
        } else {
            // expr is a parenthesized expression.
            // Strip off the beginning and ending parentheses,
            // find the main operator (an occurrence of + or * not nested
            // in parentheses, and construct the two subtrees.
            int nesting = 0;
            int opPos = 0;
            for (int k = 1; k < expr.length() - 1; k++) {
            	if (expr.charAt(k) == '('){
            		nesting++;
            	} else if (expr.charAt(k) == ')'){
            		nesting--;
            	} else if (nesting == 0 && (expr.charAt(k) == '+' || expr.charAt(k) == '*')) {
            		opPos = k;
            	}
                // you supply the missing code
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
    
    public void optimize(){
    	BinaryTree.optimizeHelp(myRoot);
    }
    
    private static void optimizeHelp(TreeNode current){
    	int left;
    	int right;
    	if (current.myLeft == null){
    		return;
    	}
    	optimizeHelp(current.myLeft);
    	optimizeHelp(current.myRight);
    	try {
    		left = Integer.parseInt((String) current.myLeft.myItem);
    		right = Integer.parseInt((String) current.myRight.myItem);
    		if (current.myItem.equals("*")) {
    			current.myItem = left*right;
    		} else {
    			current.myItem = left + right;
    		}
    		current.myItem = current.myItem.toString();
    		current.myLeft = null;
    		current.myRight = null;
    	} catch (NumberFormatException e){
    		System.out.println("some number formatting f'd up!");
    	}
    }
    
    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
    }
    
    private TreeNode fibTreeHelper(int n){
    	if (n==0){
    		return new TreeNode(n);
    	} else if (n==1){
    		return new TreeNode(n);
    	} else {
    		int fib = (int) fibTreeHelper(n-1).myItem + (int) fibTreeHelper(n-2).myItem;
    		return new TreeNode(fib, fibTreeHelper(n-1), fibTreeHelper(n-2));
    	}
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
    	if  (myRoot!=null){
    		return myRoot.height();
    	}
    	return 0;
    }
    
    public boolean isCompletelyBalanced(){
    	if (myRoot!=null){
    		return myRoot.isCompletelyBalanced();
    	}
    	return true;
    }
    
    public void fillSampleTree1() {
        myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
    }

    public void fillSampleTree2() {
        myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d",
                new TreeNode("e"), new TreeNode("f")), null), new TreeNode("c"));
    }
    
    public void fillSampleTree3() {
    	myRoot = new TreeNode("A", new TreeNode("B"), new TreeNode("C", new TreeNode("D", new TreeNode("E"), new TreeNode("F")), null));
    }

    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        print(t, "the empty tree");
        t.print();
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree1();
        print(t, "sample tree 1");
        t.print();
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree2();
        print(t, "sample tree 2");
        t.print();
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree3();
        print(t, "sample tree 3");
        t.print();
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
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
        
        private int height(){
        	if (myLeft == null && myRight == null){
        		return 1;
        	} else {
        		int bestSoFar = 1;
        		if (myLeft == null){
        			bestSoFar = 1+Math.max(myRight.height(),bestSoFar);
        			return bestSoFar;
        		} else if (myRight == null){
        			bestSoFar = 1+Math.max(myLeft.height(),bestSoFar);
        			return bestSoFar;
        		} else {        			
        			bestSoFar = 1+Math.max(Math.max(myLeft.height(),bestSoFar),Math.max(myRight.height(),bestSoFar));
        			return bestSoFar;
        		}
        	}
        }
        
        private boolean isCompletelyBalanced(){
        	if (myLeft==null&& myRight==null){
        		return true;
        	} else if (myLeft !=null && myRight!=null){
        		return myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced();
        	} else {
        		return false;
        	}
        }

        private static final String indent1 = "    ";

        private void print(int indent) {
        	if (myRight!=null){
        		myRight.print(indent+1);
        	}
            println (myItem, indent);
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
    }
}
