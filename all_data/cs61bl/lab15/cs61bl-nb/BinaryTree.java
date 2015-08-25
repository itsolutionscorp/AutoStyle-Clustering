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
    
    public int height(){
    		if(myRoot == null){
    			return 0;
    		} else {
    			return myRoot.height();
    		}
    }
    
    public boolean isCompletelyBalanced(){
    		if(myRoot == null){
    			return true;
    		} else {
    			return myRoot.isCompletelyBalanced();
    		}
    }

    public void fillSampleTree1() {
        myRoot = new TreeNode("a", new TreeNode("a"), new TreeNode("a"));
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
    		TreeNode D = new TreeNode("D");
    		myRoot = new TreeNode("A", new TreeNode("B", null , D), new TreeNode("C", D, null));
    }
    
    

    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        //print(t, "the empty tree");
        //t.fillSampleTree1();
        //System.out.println(t.check());
        //print(t, "sample tree 1");
        //t.fillSampleTree2();
        //t.check();
        //print(t, "sample tree 2");
        t.fillSampleTree4();
        //System.out.println(t.check());
        t.check();
        //BinaryTree a = BinaryTree.fibTree(5);
        //a.print();
        //BinaryTree b = BinaryTree.exprTree("((a+(5*(a+b)))+(6*5))");
        //BinaryTree c = BinaryTree.exprTree("((10+(5*(9+1)))+(6*5))");
       // b.print();
       // c.optimize();
        //c.print();
        //print(t, "sample tree 3");
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
    
    public boolean check() { 
        alreadySeen = new ArrayList<TreeNode>(); 
        try { 
            isOK(myRoot); 
            return true; 
        } catch (IllegalStateException e) { 
        	System.out.println(e.getMessage());
            return false; 
        } 
    }
    //need to ask, does this different node mean treenode.myItem different? Do we need to write
    //hashcode() and equal() or just check t.myItem?
    private void isOK(TreeNode t) throws IllegalStateException{
	    	if(alreadySeen.contains(t)){
				throw new IllegalStateException("duplicate traversal.");   			
			} else {
				alreadySeen.add(t);
			}
	    	if(t.myLeft != null){
	    		isOK(t.myLeft);   		
	    	}
	    	if(t.myRight != null){
	    		isOK(t.myRight);
	    	}
    }
    
    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
    }

    private TreeNode fibTreeHelper (int n) {
    		TreeNode t = null ;
    		if(n == 0 ){
    			t = new TreeNode(0);

    		} else if(n == 1){
    			t = new TreeNode(1);

    		} else {
    			t = new TreeNode((Integer)fibTreeHelper(n-1).myItem + (Integer)fibTreeHelper(n-2).myItem);
    			t.myLeft = fibTreeHelper(n-1);
    			t.myRight = fibTreeHelper(n-2);  

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
        	for(int i = 0; i < expr.length(); i++){       	
        		if(expr.charAt(i) == '+' || expr.charAt(i) == '*'){
        			return new TreeNode(expr.charAt(i), new TreeNode(expr.substring(0,i)), exprTreeHelper(expr.substring(i+1, expr.length()-1))); 
        		}         		// you fill this in
        	}
        	return new TreeNode(expr);
        } else {
            // expr is a parenthesized expression.
            // Strip off the beginning and ending parentheses,
            // find the main operator (an occurrence of + or * not nested
            // in parentheses, and construct the two subtrees.
            int nesting = 0;
            int opPos = 0;
            for (int k = 1; k < expr.length() - 1; k++) {
                // you supply the missing code
            		if(expr.charAt(k) == '('){
            			nesting++;
            		} else if(expr.charAt(k) == ')'){
            			nesting--;
            		}
            		if(nesting == 0 && (expr.charAt(k+1) == '*' || expr.charAt(k+1) == '+')){
            			opPos = k + 1;
            			System.out.println(opPos);
            			break;
            		}
            }
            String opnd1 = expr.substring(1, opPos);
            //System.out.println();
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
         optimizeHelper(this.myRoot);       
    }
    
    private TreeNode optimizeHelper(TreeNode t){
    	if (t.myLeft == null & t.myRight == null) {
    		return t;
    	} 
    	t.myLeft = optimizeHelper(t.myLeft);
    	t.myRight = optimizeHelper(t.myRight);
    	try{
    		int left = Integer.parseInt((String)t.myLeft.myItem);
    		int right = Integer.parseInt((String)t.myRight.myItem);
    		if(((String)t.myItem).equals("*")){
    			t.myItem = Integer.toString(left * right);    			
    		} else if (((String)t.myItem).equals("+")){
    			t.myItem = Integer.toString(left + right); 
    		}
    		t.myLeft = null;
    		t.myRight = null;
    		return t;
    	} catch (Exception e){
    		return t;
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
        
        private int height(){
        		if(myLeft == null && myRight == null){
        			return 1;
        		} else if(myLeft == null && myRight != null){
        			return 1 + myRight.height();
        		} else if(myLeft != null && myRight == null){
        			return 1 + myLeft.height();
        		} else {
        			return 1 + Math.max(myLeft.height(), myRight.height());
        		}
        }
        
        private boolean isCompletelyBalanced(){
        		if(myLeft == null && myRight == null){
        			return true;
        		} else if(myLeft == null && myRight != null){
        			return false;
        		} else if(myLeft != null && myRight == null){
        			return false;
        		} else {
        			return (myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced());
        		}
        }
        
        private static final String indent1 = "    ";

        private void print(int indent) {
            // TODO your code here
        	if(myRight != null){
        		myRight.print(indent + 1);
        	}
        		println (myItem, indent);
    		if(myLeft != null){
        		myLeft.print(indent + 1);
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
