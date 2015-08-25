

import java.util.ArrayList;

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
     
    public int height(){
        if(myRoot != null){
            return myRoot.height();
        }return 0;
    }
     
    public boolean isCompletelyBalanced(){
        if(myRoot != null){
            return myRoot.isCompletelyBalanced();
        }return true;
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
         
        TreeNode duplicate = new TreeNode("d");
        myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d",
                new TreeNode("e"), duplicate), null), duplicate);
    }
     
    public void fillSampleTree3() {
        myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c", new TreeNode("d", new TreeNode("e"), new TreeNode("f")), null ));
    }
 
    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        //print(t, "the empty tree");
        //t.fillSampleTree1();
        //t.print();
        t.fillSampleTree2();
        t.check();
         
        BinaryTree myTree = BinaryTree.fibTree(8);
        myTree.print();
         
        BinaryTree myTree2 = BinaryTree.exprTree("((a+(5*(9+8)))+(7*5))");
        myTree2.print();
        
        System.out.println();
        System.out.println();
        
        myTree2.optimize();
        myTree2.print();
         
       // t.fillSampleTree3();
        //t.print();
         
        //System.out.println(t.height());
         
       // System.out.println();
        //System.out.println(t.isCompletelyBalanced());
         
        //t.fillSampleTree1();
        //System.out.println(t.isCompletelyBalanced());
        //System.out.println();
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
 
        
        public void optimizeHelper(){
        	if(myLeft != null)
        		myLeft.optimizeHelper();
        	if(myRight != null)
        		myRight.optimizeHelper();
        	if((myItem+"").equals("*") || (myItem+"").equals("+")){
        		if( (int)(myLeft.myItem +"").charAt(0) >= 48 && (int)(myLeft.myItem + "").charAt(0) <= 57 ){
        			if((int)(myRight.myItem+"").charAt(0) >= 48 && (int)(myRight.myItem+"").charAt(0) <= 57){
        				if( (myItem+"").equals("*") )
        					myItem = Integer.parseInt(myRight.myItem+"") * Integer.parseInt(myLeft.myItem+"");
        				else if ( (myItem+"").equals("+") )
        					myItem = Integer.parseInt(myRight.myItem+"") + Integer.parseInt(myLeft.myItem+"");
        				myLeft = null;
        				myRight = null;
        			}
        		}
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
         
        private int height(){
            if(myLeft != null && myRight != null){
                return 1 + Math.max(myLeft.height(), myRight.height());
            }else if (myLeft != null){
                return 1 + myLeft.height();
            }else if (myRight != null){
                return 1 + myRight.height();
            } return 1;
        }
         
        private boolean isCompletelyBalanced(){
            if(myLeft != null && myRight != null){
                return (myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced());
            }else if (myLeft == null && myRight == null){
                return true;
            }else{
                return false;
            }
        }
         
        public boolean equals(TreeNode other){
            if(myItem.equals(other.myItem))
                return true;
            return false;
        }
         
        //In TreeNode
        private static final String indent1 = "    ";
 
        private void print(int indent) {
            // TODO your code here
            if(myRight != null){
                myRight.print(indent + 1);
            }println (myItem, indent);
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
    public void print() {
        if (myRoot != null) {
            myRoot.print(0);
        }
    }
     
    private ArrayList<TreeNode> alreadySeen; 
    public boolean check() { 
        // Contains nodes already seen in the traversal.
        alreadySeen = new ArrayList<TreeNode>(); 
        try { 
            isOK(myRoot); 
            return true; 
        } catch (IllegalStateException e) { 
            return false; 
        } 
    }
     
    private void isOK(TreeNode t) throws IllegalStateException{
        alreadySeen.add(t);
        for( TreeNode t1 : alreadySeen ){
            if(t1.equals(t))
                throw new IllegalStateException("Already Seen");
            isOK(t1);
        }
         
    }
     
 
    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
    }
 
    private TreeNode fibTreeHelper (int n)  {
        if(n == 0 || n == 1){
            return new TreeNode(n);
        }return new TreeNode((int)fibTreeHelper(n-1).myItem + (int)fibTreeHelper(n-2).myItem, new TreeNode(fibTreeHelper(n-1)), new TreeNode(fibTreeHelper(n-2)));
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
            return new TreeNode(expr.charAt(0)); // you fill this in
        } else {
            // expr is a parenthesized expression.
            // Strip off the beginning and ending parentheses,
            // find the main operator (an occurrence of + or * not nested
            // in parentheses, and construct the two subtrees.
            int nesting = 0;
            int opPos = 0;
           // if(expr.length()>0){
            for (int k = 1; k < expr.length() - 1; k++) {
                // you supply the missing code
                if(expr.charAt(k) == '(')
                    nesting++;
                if(expr.charAt(k) == ')')
                    nesting--;
                 
                    if((expr.charAt(k) == '*' || expr.charAt(k) == '+')&&nesting==0){
                        opPos = k;
                        //return new TreeNode(expr.charAt(k), exprTreeHelper(expr.substring(0, k)), exprTreeHelper(expr.substring(k)));
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
            return new TreeNode(op, exprTreeHelper(opnd1), exprTreeHelper(opnd2));
             
        }
    }
    
    public void optimize(){
    	if(myRoot != null)
    		myRoot.optimizeHelper();
    }
    
   
     
    
}