import java.util.*;
public class BinaryTree {

    private TreeNode myRoot;
    private ArrayList alreadySeen;
    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
    }	

    public static BinaryTree exprTree(String s) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.exprTreeHelper(s);
        return result;
    }
    public void optimize(){
        if(myRoot!=null){
        	myRoot.optimize();
        }
    }
    // Return the tree corresponding to the given arithmetic expression.
    // The expression is legal, fully parenthesized, contains no blanks, 
    // and involves only the operations + and *.
    private TreeNode exprTreeHelper(String expr) {
        if (expr.charAt(0) != '(') {
            
        	return new TreeNode(expr); // you fill this in
        } else{
            // expr is a parenthesized expression.
            // Strip off the beginning and ending parentheses,
            // find the main operator (an occurrence of + or * not nested
            // in parentheses, and construct the two subtrees.
            int nesting = 0;
            int opPos = 0;
            char myChar='\0';
             
            for (int k = 1; k < expr.length() - 1; k++) {
                myChar = expr.charAt(k);
            	if(myChar == '('){
                	nesting++;
                }
                if(myChar==')'){
                	nesting--;
                }
                if(nesting == 0){
                	if(myChar == '+' || myChar == '*'){
                		opPos = k;
                	    break;	
                	}
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
            return new TreeNode(op,exprTreeHelper(opnd1),exprTreeHelper(opnd2));
         
        }
    }
    
    
    
    public boolean isCompletelyBalanced(){
    	if(myRoot==null){
    		return true;
    	} else{
    		return myRoot.isCompletelyBalanced();
    	}
    }

	public static BinaryTree fibTree(int n) {
	    BinaryTree result = new BinaryTree();
	    result.myRoot = result.fibTreeHelper(n);
	    return result;
	}

	private TreeNode fibTreeHelper(int n){
		
		if(n==0){
		
		TreeNode right = new TreeNode(0);
		return right;
		}
		if(n==1){
		TreeNode left = new TreeNode(1);
		return left;
		}
		else{
			TreeNode resultTree = new TreeNode(fib(n), fibTreeHelper(n-1) , fibTreeHelper(n-2));
			
			return resultTree;
		}
	} 
	
	public static int fib(int n) {
		if (n==0 || n==1) {
			return n;
		} else {
			return fib(n-1)+fib(n-2);
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
     
    private void isOK(TreeNode T){
    	
    	if(alreadySeen.contains(T)){
    		throw new IllegalStateException("already seen");
    	} 
    	if(T!=null){
    	   alreadySeen.add(T);
    	   isOK(T.myLeft);
    	   isOK(T.myRight);
    	
    	}
    }
    public void print() {
        if (myRoot != null) {
            myRoot.print(0);
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
    
    public int height(){
    	return myRoot.height();
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
        myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c", new TreeNode("d", new
        		 TreeNode("e"), new TreeNode("f")), null));	
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
        print(t, "Sample tree 3");
        
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
        
        public void optimize(){
        	
        	if(myRight != null){
        		myRight.optimize();
        	}
        	if(myLeft != null){
        		myLeft.optimize();
        	}
       	    optimizehelper(this);	
        }
        public void optimizehelper(TreeNode this_treenode){
        	
        	    
        	     
        		if((this_treenode.myItem).equals("*")){
        			try{
        			
        				this_treenode.myItem = ((Integer) (Integer.parseInt((String) this_treenode.myLeft.myItem) * Integer.parseInt((String) this_treenode.myRight.myItem)));
        				this_treenode.myLeft = null;
        	            this_treenode.myRight = null;
        	            this_treenode.myItem = this_treenode.myItem.toString();
        			}
        			catch(Exception e){
        			    
        			}
        		}
        		else if((this_treenode.myItem).equals("+")){    				
        			try{
        				
        				this_treenode.myItem = ((Integer) (Integer.parseInt((String) this_treenode.myLeft.myItem) + Integer.parseInt((String) this_treenode.myRight.myItem)));
        				this_treenode.myLeft = null;
        	            this_treenode.myRight = null;
        	            this_treenode.myItem = this_treenode.myItem.toString();
        			}
        			catch(Exception e){
        				
        			}
        		}
        	}
        
        public boolean isCompletelyBalanced(){
        	
        	if(myLeft== null && myRight ==null){
        		return true;
        	}
        	else if(myLeft== null && myRight != null){
        		return false;	
        	}else if(myLeft == null && myRight != null){
        		return false;
        	}else{
        		return (myLeft.height() == myRight.height() && myLeft.isCompletelyBalanced() && 
            			myRight.isCompletelyBalanced());
        	}
        }
        private static final String indent1 = "    ";

        private void print(int indent) {
            if(myRight != null){
            	myRight.print(indent+1);
            }
            println (myItem, indent);
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
        public int height(){
        	
        	if(myItem == null){
        		return 0;
        	}
        	
        	if(myLeft== null && myRight ==null){
        		return 1;
        	}
        	else if(myLeft!= null && myRight != null){
        			return Math.max(myLeft.height(), myRight.height()) +1;	
        	}else if(myLeft == null && myRight != null){
        		return myRight.height() +1;
        	}else{
        		return myLeft.height() +1;
        	}
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
