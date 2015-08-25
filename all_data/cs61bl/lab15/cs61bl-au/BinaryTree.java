import java.util.ArrayList;

public class BinaryTree {

    private TreeNode myRoot;
    private ArrayList<TreeNode> alreadySeen; 


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
    
    public void printPostorder() {
        if (myRoot == null) {
            System.out.println("(empty tree)");
        } else {
            myRoot.printPostorder();
            System.out.println();
        }
    }
    
    public int height(){
    	if (myRoot == null) return 0;
    	return myRoot.height();
    }
    
    public boolean isCompletelyBalanced(){
    	if (myRoot == null) return true;
    	return leftHeight() == rightHeight();
    }
    public int leftHeight(){
		return myRoot.leftHeight();
	}
    
    public int rightHeight(){
    	return myRoot.rightHeight();
    }

    public void fillSampleTree1() {
        myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
    }

    public void fillSampleTree2() {
        myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d",
                new TreeNode("e"), new TreeNode("f")), null), new TreeNode("c"));
    }

    public void fillSampleTree3(){
    	myRoot = new TreeNode("A", new TreeNode("B"), new TreeNode("C",
    			new TreeNode("D", new TreeNode("E"), new TreeNode("F")), null));
    }
    
    public void fillSampleTree4(){
    	TreeNode myRepeat =  new TreeNode("F");
    	myRoot = new TreeNode("A", myRepeat, new TreeNode("C",
    			new TreeNode("D", new TreeNode("E"), myRepeat), null));
    }
    
    public void print() {
        if (myRoot != null) {
            myRoot.print(0);
        }
    }
    
    public boolean check() { 
        alreadySeen = new ArrayList<TreeNode>(); 
        if(myRoot != null){
            try { 
                isOK(myRoot); 
                return true; 
            } catch (IllegalStateException e) { 
                 return false; 
            } 
        }
        else return true;
    }
    
    private void isOK(TreeNode t) throws IllegalStateException{
    	if(alreadySeen.contains(t)) throw new IllegalStateException();
        alreadySeen.add(t);
        if (t.myLeft != null){
        	isOK(t.myLeft);
        }
        if (t.myRight != null){
        	isOK(t.myRight);
        }
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
        System.out.println(t.isCompletelyBalanced());
        t.print();
        
        BinaryTree fib = fibTree(8);
        fib.print();
        
        BinaryTree exp = exprTree("((a+(5*(9+1)))+(6*5))");
        exp.print();
        
        System.out.println();
        System.out.println();
        
        exp.optimize();
        exp.print();
        
        t.fillSampleTree4();
        System.out.println(t.check()+"-------------");
        print(t, "sample tree 4");
    }

    private static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println(description + " in postorder");
        t.printPostorder();
        System.out.println();
    }
    
    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
    }

    private TreeNode fibTreeHelper (int n){
    	if(n == 0) return new TreeNode(0);
    	else if(n == 1) return new TreeNode(1);
    	else return new TreeNode((int)fibTreeHelper(n-1).myItem + 
    			(int)fibTreeHelper(n-2).myItem, fibTreeHelper(n-1), fibTreeHelper(n-2));
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
        	// you fill this in
        	return new TreeNode(expr.charAt(0) + "");
        } else {
            // expr is a parenthesized expression.
            // Strip off the beginning and ending parentheses,
            // find the main operator (an occurrence of + or * not nested
            // in parentheses, and construct the two subtrees.
            int nesting = 0;
            int opPos = 0;
            for (int k = 1; k < expr.length() - 1; k++) {
                // you supply the missing code
            	if (expr.charAt(1) == '('){
            	    if (expr.charAt(k) == '(') nesting++;
            	    if (nesting == 0){ opPos = k; break;}
            	    else if (expr.charAt(k) == ')') nesting--;
            	}
            	else {opPos = 2; break;}
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
    	if(myRoot != null) myRoot.optimize();
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
        
        private void printPostorder() {
            if (myLeft != null) {
                myLeft.printInorder();
            }
            if (myRight != null) {
                myRight.printInorder();
            }
            System.out.print(myItem + " ");
        }
        
        private int height() {
            if (myLeft == null && myRight == null) {
            	return 1;
            } else {
                int bestSoFar = 1;
                if (myLeft == null && myRight != null) bestSoFar = Math.max(myRight.height() + 1, bestSoFar);
                else if (myLeft != null && myRight == null) bestSoFar = Math.max(myLeft.height() + 1, bestSoFar);
                else {
                	bestSoFar = Math.max(myRight.height() + 1, bestSoFar);
                	bestSoFar = Math.max(myLeft.height() + 1, bestSoFar);
                }
                return bestSoFar;
            }
        }
        
        private int leftHeight(){
        	if (myLeft == null) return 1;
        	else {
        		int bestSofar = 1;
        		bestSofar = Math.max(myLeft.leftHeight() + 1, bestSofar);
            	return bestSofar;
        	}
        }
        
        private int rightHeight(){
        	if (myLeft == null) return 1;
        	else {
        		int bestSofar = 1;
        		bestSofar = Math.max(myRight.leftHeight() + 1, bestSofar);
            	return bestSofar;
        	}
        }  
        
        public static boolean isNumeric(Object str) {  
            try {  
                int d = Integer.parseInt((String)str);  
            }catch(NumberFormatException nfe) {  
                return false;
            }
            return true;  
        }
        
        public void optimize(){ 
    		
    		if(myLeft.myLeft != null && myLeft.myRight != null){
        		myLeft.optimize();	
        	}
    		
    		if(myRight.myLeft != null && myRight.myRight != null){
        		myRight.optimize();	
        	}
    		if(isNumeric(myLeft.myItem) && isNumeric(myRight.myItem)) {
    			int result;
    			if (myItem.equals("+")) {
    				result = Integer.parseInt((String)myLeft.myItem) + Integer.parseInt((String)myRight.myItem);
    			}
    			else result = Integer.parseInt((String)myLeft.myItem) * Integer.parseInt((String)myRight.myItem);
    			this.myItem = result +"";
    			myLeft = myRight = null;
    		}

        }

        private static final String indent1 = "    ";

        private void print(int indent) {
            // TODO your code here
        	int ind = indent;
        	if (myRight != null) {
                myRight.print(ind + 1);
            }
            println (myItem, indent);
            // TODO your code here
            if (myLeft != null) {
                myLeft.print(ind + 1);

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
