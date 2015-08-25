import java.util.ArrayList;


public class BinaryTree {

    private TreeNode myRoot;
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

    public void fillSampleTree1() {
        myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
    }

    public void fillSampleTree2() {
        myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d",
                new TreeNode("e"), new TreeNode("f")), null), new TreeNode("c"));
    }
    
    public void fillSampleTree3(){
    	myRoot = new TreeNode("a", 
    				new TreeNode("b"),
    				new TreeNode("c",
    					new TreeNode("d", 
    							new TreeNode("e"), 
    							new TreeNode("f")
    					),
    					null 
    				) 
    			 );
    }

    
    public int height() {
    	if(myRoot!=null){
    		return myRoot.height();
    	}
    	return 0;
    }
    
    public boolean isCompletelyBalanced(){
    	if(myRoot!=null){
    		return myRoot.isCompletelyBalanced();
    	} else {
    		return true;
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
    
    private void isOK(TreeNode t) throws IllegalStateException{
    	if(!alreadySeen.contains(t.myItem)){
    		alreadySeen.add(t.myItem);
    		if(t.myRight!=null){
    			isOK(t.myRight);
    		}
    		if(t.myLeft!=null){
    			isOK(t.myLeft);
    		}
    	} else {
    		throw new IllegalStateException("dfd");
    	}
    }
    
    public void optimize() {
    	optimizeHelper(this.myRoot);
    }
    
    private void optimizeHelper(TreeNode n){
    	if (n.myLeft == null && n.myRight == null){
    		return;
    	} else if (n.myLeft != null && n.myRight != null){
    		optimizeHelper(n.myLeft);
    		optimizeHelper(n.myRight);
    	} else if (n.myLeft != null){
    		optimizeHelper(n.myLeft);
    	} else if (n.myRight != null){
    		optimizeHelper(n.myRight);
    	}
    	String s1 = n.myLeft.myItem.toString();
    	String s2 = n.myRight.myItem.toString();
    	if (Character.isDigit(s1.charAt(0)) && Character.isDigit(s2.charAt(0))){
    		//then we will change this.myItem to s1(this.myItem)s2
    		int i = Integer.parseInt(s1);
    		int j = Integer.parseInt(s2);
    		if(n.myItem.equals("+")){
    			n.myItem=i+j;
    			n.myRight=null;
    			n.myLeft=null;
    		}
    		if(n.myItem.equals("*")){
    			n.myItem=i*j;
    			n.myRight=null;
    			n.myLeft=null;
    		}		
    	}
    } 


    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        print(t, "the empty tree");
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        
        t.fillSampleTree1();
        print(t, "sample tree 1");
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.print();

        t.fillSampleTree2();
        print(t, "sample tree 2");
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.print();


        t.fillSampleTree3();
        print(t, "sample tree 3");
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.print();
        System.out.println(t.check());
        
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
            	if(expr.charAt(k)=='('){
            		nesting++;
            	}
            	if(expr.charAt(k)==')'){
            		nesting--;
            	}
            	if(nesting==0){
            		opPos=k+1;
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
        	if (myLeft==null && myRight == null){
        		return 1;
        	} else if (myLeft!=null && myRight == null){
        		return 1+myLeft.height();
        	} else if (myLeft==null && myRight != null){
        		return 1+myRight.height();
        	} else {
        		return 1+Math.max(myLeft.height(),myRight.height());
        	}
        }
        
        private boolean isCompletelyBalanced(){
        	if(height()==1){
        		return true;
        	} else {
        		if (myLeft==null || myRight==null){
        			return false;
        		}
        		if (myLeft.height()!=myRight.height()){
        			return false;
        		}
        		return myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced();
        	}
        }
        	        	
//        	if(height()==1){
//        		return true;
//        	}
//        	if(myLeft!=null && myRight!=null){	
//	        	if (myLeft.height()== myRight.height()){
//	        		return true;
//	        	}
//        	}
//        		return false;
//        	
//        }
        
        
        private static final String indent1 = "    ";

        private void print(int indent) {
            // TODO your code here
        	if(myRight!=null){
        		myRight.print(indent+1);
        	}
            println (myItem, indent);
            // TODO your code here
            if(myLeft!=null){
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
