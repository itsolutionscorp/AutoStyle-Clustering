
import java.util.ArrayList;

public class BinaryTree {

    private TreeNode myRoot;
 // Contains nodes already seen in the traversal. 
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
    public int height(){
    	if(myRoot != null){
    		return myRoot.height();
    	}
    	return 0;
    }
    public boolean isCompletelyBalanced(){
    	if(myRoot ==null){
    		return true;
    	}else{return myRoot.isCompletelyBalanced();}
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
    	if(alreadySeen.contains(t)){
    		throw new IllegalStateException();
    	}
    	else{
    		alreadySeen.add(t);
        	if(t.myLeft!= null){
        		isOK(t.myLeft);
        	}else if(t.myRight != null){
        		isOK(t.myRight);
        	}
    	}	
	}
    
    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
    }

    private TreeNode fibTreeHelper (int n){
    	TreeNode fibNode;
    	if(n == 0){
    		fibNode = new TreeNode(0);
    		return fibNode;
    	}
    	else if(n == 1){
    		fibNode = new TreeNode(1);
    		return fibNode;
    	}else{
        	TreeNode myLeft = fibTreeHelper(n-1);
        	TreeNode myRight = fibTreeHelper(n-2);
        	int myItem = (Integer)myLeft.myItem + (Integer)myRight.myItem;
        	fibNode = new TreeNode(myItem, myLeft, myRight); 	
    	}
    	return fibNode;
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
            return new TreeNode(expr.substring(0)) ; // you fill this in
        }else {
            // expr is a parenthesized expression.
            // Strip off the beginning and ending parentheses,
            // find the main operator (an occurrence of + or * not nested
            // in parentheses, and construct the two subtrees.
            int nesting = 0;
            int opPos = 0;
            TreeNode exprTree = new TreeNode(0);
            for (int k = 1; k < expr.length() - 1; k++) {
            	if(expr.charAt(k) == '('){
            		nesting++;
            	}else if(expr.charAt(k)==')'){
            		nesting--;
            	}
            	if((nesting == 0 && expr.charAt(k) == '+')||(nesting == 0 && expr.charAt(k) == '*')){
            		opPos = k;
            		String Item = expr.substring(opPos, opPos+1);
            		TreeNode Left = exprTreeHelper(expr.substring(1,opPos));
            		TreeNode Right = exprTreeHelper(expr.substring(opPos + 1, expr.length() - 1));
            		exprTree = new TreeNode(Item, Left, Right);
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
            return exprTree;
        }
    }
    public static boolean helper(TreeNode curr){
    	if(curr.myLeft == null&&curr.myRight == null){
    		return true;
    	}else{
    		String a = (String)curr.myLeft.myItem;
    		String b = (String)curr.myRight.myItem;
    		if((a.charAt(0)>'0'&&a.charAt(0)<='9')&&(b.charAt(0)>'0'&&b.charAt(0)<='9')){
    			return false;
    		}
    		return helper(curr.myLeft)&&helper(curr.myRight);

    	}
    }
    
    public void optimize(){
    	while(!helper(myRoot)){
            this.optimizeHelper();}
        this.optimizeHelper();
    }
    
    public void optimizeHelper(){
    	String a = (String)myRoot.myLeft.myItem;
    	String b = (String)myRoot.myRight.myItem;
    	if((a.charAt(0)>'0'&&a.charAt(0)<='9')&&(b.charAt(0)>'0'&&b.charAt(0)<='9')){
    		if(myRoot.myItem.equals("+")){
    			myRoot.myItem = Integer.toString(Integer.parseInt(a)+Integer.parseInt(b));
    			myRoot.myLeft = null;
    			myRoot.myRight = null;
    		}
    		else if(myRoot.myItem.equals("*")){
    			myRoot.myItem = Integer.toString(Integer.parseInt(a)*Integer.parseInt(b));
    			myRoot.myLeft = null;
    			myRoot.myRight = null;
    		}
    	}else{
    		BinaryTree result = new BinaryTree();
        	if(myRoot.myLeft.myItem.equals("+")||myRoot.myLeft.myItem.equals("*")){
        		result.myRoot = myRoot.myLeft;
        		result.optimizeHelper();
        	}
        	if(myRoot.myRight.myItem.equals("+")||myRoot.myRight.myItem.equals("*")){
        		result.myRoot = myRoot.myRight;
        		result.optimizeHelper();
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

    public void fillSampleTree3() {
        myRoot = new TreeNode("a", new TreeNode("b"),new TreeNode("c",new TreeNode("d",new TreeNode("e"),new TreeNode("f")), null));
    }
    
    public void fillSampleTree4(){
    	myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c", new TreeNode("d"), new TreeNode("e")));
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
        System.out.println(t.check());
        print(t, "sample tree 3");
        t.fillSampleTree4();
        t.print();
//        print(fibTree(3), "fibTree");
//        BinaryTree f = exprTree("((a+(5*(9+1)))+(60*5))");
//        f.print();
//        f.optimize();
//        f.print();
        
        
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
        private int height(){
        	if(myLeft == null && myRight == null){
        		return 1;
        	}else{
        		if(myLeft == null){
        			return 1+myRight.height();
        		}else if(myRight == null){
        			return 1+myLeft.height();
        		}
        		return 1+Math.max(myLeft.height(), myRight.height()); 
        	}
        }
        private boolean isCompletelyBalanced(){
        	if(myLeft == null && myRight == null){
        		return true;
        	}else if(myLeft == null&&myRight != null||myLeft !=null && myRight == null)
        	{return false;}
        	return myLeft.isCompletelyBalanced()&&myRight.isCompletelyBalanced();
        	
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
        
       
        //In TreeNode
        private static final String indent1 = "    ";

        private void print(int indent) {
            // TODO your code here
        	if(myRight != null){
        		myRight.print(indent+1);
        	}
            println (myItem, indent);
            // TODO your code here
            if(myLeft != null){
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
