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
        myRoot = new TreeNode("A", new TreeNode("B"), new TreeNode("C",
                new TreeNode("D", new TreeNode("E"), new TreeNode("F")), null));
    }
    public void fillSampleTree4() {
        myRoot = new TreeNode("A", new TreeNode("B",new TreeNode("D"), new TreeNode("E")), new TreeNode("C",
                new TreeNode("F"),new TreeNode("G")));
    }
    public void fillSampleTree5() {
        myRoot = new TreeNode("A", new TreeNode("B"),new TreeNode("C", new TreeNode("D"), new TreeNode("E")));
    }
    public void fillSampleTree6() {
    	myRoot = new TreeNode("c");
        myRoot = new TreeNode("a", new TreeNode("b", null, myRoot),myRoot );
    }
    public void fillSampleTree7() {
    	myRoot = null;
    }
    
    public int height() {
		if (myRoot != null) {
			return myRoot.height();
		}
		return 0;
	}
    public boolean isCompletelyBalanced(){
    	if (myRoot != null){
    		return myRoot.isCompletelyBalanced();
    	}
    	return true;
    }
    public void print() {
        if (myRoot != null) {
            myRoot.print(0);
        }
    }
    private ArrayList alreadySeen; 
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
    	if(t == null){
    		return;
    	}
    	if(alreadySeen.contains(t)){
    		throw new IllegalStateException("Invalid Tree");
    	}else{
    		alreadySeen.add(t);
    		if(t !=  null){
    			isOK(t.myLeft);
    		
    		
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
    	TreeNode node;
    	if(n ==  0 ){
    		node  = new TreeNode(0);
    	}else if(n==1){
    		node  = new TreeNode(1);
    	}else{
    		int i = (int)fibTreeHelper(n-1).myItem;
    		int j = (int)fibTreeHelper(n-2).myItem;
    		node = new TreeNode(i+j, fibTreeHelper(n-1), fibTreeHelper(n-2));
    	
    	}
    	return node;
    	
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
            return (new TreeNode(expr)); // you fill this in
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
            	}
            	if(expr.charAt(k) == ')'){
            		opPos++;
            	}else if((nesting == opPos)) {
            			if( expr.charAt(k) == '+' || expr.charAt(k) == '*'){
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
            return new TreeNode(op, exprTreeHelper(opnd1), exprTreeHelper(opnd2)); // you fill this in
        }
    }
    public void optimize(){
    if(myRoot != null){
    	myRoot.optimizeHelper();
    }
    }
    

    // Contains nodes already seen in the traversal. 
  
    


    

    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        print(t, "the empty tree");
        t.fillSampleTree1();
        System.out.println(t.check());
        print(t, "sample tree 1");
        t.fillSampleTree2();
        print(t, "sample tree 2");
        System.out.println(t.check());
        t.fillSampleTree3();
        print(t, "sample tree 3");
        System.out.println(t.check());
        t.fillSampleTree5();
        print(t, "sample tree 5");
        System.out.println(t.check());
        t.print();
        t.fillSampleTree6();
        System.out.println(t.check());
        t.fillSampleTree7();
        System.out.println(t.check());
        fibTree(0).print();
        BinaryTree x = exprTree("((1+(5*(1+6)))+((6*5)+(6+7)))");
        x.optimize();
        x.print();
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
            if (myLeft == null && myRight == null) {
            	return 1;
            }else if (myLeft == null){
        		return 1;
        	} else if(myRight == null){
        		return myLeft.height() + 1;
        	}else{          
        	return Math.max(myLeft.height(), myRight.height()) + 1;
            }
        }
        
    public boolean isCompletelyBalanced(){
    	if (myLeft == null && myRight == null){
    		return true;
    	}else if(myLeft.height() != myRight.height()){
    		return false;
    	} 
    	return myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced();
    }
   

    //In TreeNode
    private static final String indent1 = "    ";

    private void print(int indent) {
        // TODO your code here

    	 if (myRight != null) {
             myRight.print(indent+1);
         }
         //System.out.print(myItem + " ");
    	 indent++;
    	 println (myItem, indent);
    	 indent--;
         if (myLeft != null) {
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
    
    public void optimizeHelper(){
    
    if(myRight != null && myRight.myRight != null&& myRight.myLeft != null){
    	myRight.optimizeHelper();
    }
    if(myLeft != null && myLeft.myLeft != null&& myLeft.myRight != null){
    	myLeft.optimizeHelper();
    }
     if(myRight == null || myLeft == null){
    	return;
    }else{
    	try{
    	int left = Integer.parseInt(myLeft.myItem.toString());
    	int right = Integer.parseInt(myRight.myItem.toString());
    	if(myItem.toString().equals("*")){
    		myItem =  (left*right);
    		myLeft = null;
    		myRight = null;
    	}
    	if(myItem.toString().equals("+")){
    		myItem =  (left+right);
    		myLeft = null;
    		myRight = null;
    	}
    	}catch(NumberFormatException e){
    		return;
    	}
    	
    }
    }
    
    
   
    }
    
}
