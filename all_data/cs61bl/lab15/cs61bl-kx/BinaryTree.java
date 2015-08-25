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

    public void fillSampleTree1() {
        myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
    }

    public void fillSampleTree2() {
        myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d",
                new TreeNode("e"), new TreeNode("f")), null), new TreeNode("c"));
    }
    
    public void fillSampleTree3(){
    	myRoot = new TreeNode("A", new TreeNode("B"), new TreeNode("C", new TreeNode("D", new TreeNode("E"), new TreeNode("F")), null));
    }
    
    public int height(){
    	if(myRoot!=null){
    		return myRoot.height();
    	}
    	return 0;
    }
    
    public boolean isCompletelyBalanced(){
    	if(myRoot!=null){
    		return myRoot.isCompletelyBalanced();
    	}
    	return true;
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
            return false; 
        } 
    }
    
    private void isOK(TreeNode t) throws IllegalStateException{
    	if(t==null){
    		return;
    	}
    	if(!alreadySeen.contains(t)){
    		alreadySeen.add(t);
    	}
    	else{
    		throw new IllegalStateException("Already seen this node");
    	}
    	if(t.myLeft!=null){
    		isOK(t.myLeft);
    	}
    	if(t.myRight!=null){
    		isOK(t.myRight);
    	}
    }
    
    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
    }

    private TreeNode fibTreeHelper (int n){
    	if(n==0){
    		return new TreeNode(new Integer(0));
    	}
    	else if(n==1){
    		return new TreeNode(new Integer(1));
    	}
    	else{
    		TreeNode prev = fibTreeHelper(n-1);
    		TreeNode prevprev = fibTreeHelper(n-2);
    		return new TreeNode(new Integer((int)prev.myItem +(int) prevprev.myItem), prev, prevprev);
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
            	if(nesting==0 && (expr.charAt(k)=='*' || expr.charAt(k)=='+')){
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
    
    public TreeNode optimize(){
    	int height = myRoot.height();
    	for(int i=0;i<height;i++){
    		myRoot = optimizeHelper(myRoot);
    	}
    	return myRoot;
    }
    
    private TreeNode optimizeHelper(TreeNode t){
    	if(t.myLeft==null && t.myRight==null){
			return t;
		}
    	else if(t.myItem.equals("+") && BinaryTree.isInt((String)t.myLeft.myItem) && BinaryTree.isInt((String)t.myRight.myItem)){
    		t.myItem = Integer.toString((Integer.parseInt(((String) optimizeHelper(t.myLeft).myItem)) + Integer.parseInt((String)optimizeHelper(t.myRight).myItem)));
    		t.myLeft = null;
        	t.myRight = null;
        	return t;
    	}
    	else if(t.myItem.equals("*") && BinaryTree.isInt((String)t.myLeft.myItem) && BinaryTree.isInt((String)t.myRight.myItem)){
    		t.myItem = Integer.toString(Integer.parseInt(((String) optimizeHelper(t.myLeft).myItem)) * Integer.parseInt((String)optimizeHelper(t.myRight).myItem));
    		t.myLeft = null;
        	t.myRight = null;
        	return t;
    	}
    	else{
    		t.myLeft = optimizeHelper(t.myLeft);
    		t.myRight = optimizeHelper(t.myRight);
    		return t;
    	}
    }
    
    private static boolean isInt(String s){
    	try{
    		Integer.parseInt(s);
    		return true;
    	}
    	catch(NumberFormatException e){
    		return false;
    	}
    }


    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        //print(t, "the empty tree");
        //System.out.println(t.check());
        t.fillSampleTree1();
        //System.out.println(t.check());
        //print(t, "sample tree 1");
        t.fillSampleTree2();
       // t.print();
        //System.out.println(t.check());
        //print(t, "sample tree 2");
        t.fillSampleTree3();
        //print(t, "sample tree 3");
        //System.out.println(t.check());
        t.myRoot= new TreeNode("a", new TreeNode("b", new TreeNode("d"), new TreeNode("d")), new TreeNode("b", new TreeNode("c"), new TreeNode("d")));
        //System.out.println(t.check());
        TreeNode s = new TreeNode("g");
       // t.myRoot = new TreeNode("h", new TreeNode("h", s, null), s);
        //System.out.println(t.check());
        //t = BinaryTree.exprTree("((a+(5*(9+1)))+(6*5))");
        //t.optimize();
        //(t).print();
       
        //BinaryTree.fibTree(5).print();
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
        
        public int height(){
        	if(myLeft == null && myRight==null){
        		return 1;
        	}
        	else if(myLeft==null){
        		return 1 + myRight.height();
        	}
        	else if(myRight==null){
        		return 1 + myLeft.height();
        	}
        	else{
        		return 1 + Math.max(myLeft.height(), myRight.height());
        	}
        }
        
        public boolean isCompletelyBalanced(){
        	if(myLeft == null && myRight == null){
        		return true;
        	}
        	else if(myLeft==null || myRight==null){
        		return false;
        	}
        	else{
        		return myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced();
        	}
        }

        //In TreeNode
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
