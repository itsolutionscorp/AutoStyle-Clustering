import java.util.Stack;

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
    
    public void fillSampleTree3(){
    	myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c", 
    			new TreeNode("d", new TreeNode("f"), new TreeNode("g")), null));
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
        t.fillSampleTree2();
        print(t, "sample tree 2");
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree3();
        print(t, "sample tree 3");
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        
        
        t.print();
        
        BinaryTree mrFib = fibTree(5);
        mrFib.print();
        
        BinaryTree mathShit = exprTree("((a+(5*(a+b)))+(6*5))");
        mathShit.print();
    }

    private static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
    }
    
    public int height(){
    	if(myRoot != null){
    		return myRoot.height();
    	} else {
    		return 0;
    	}
    }
    
    public boolean isCompletelyBalanced(){
    	if(myRoot == null){
    		return true;
    	}
    	return myRoot.isCompleteleyBalanced();
    }

    
    
    public void print() {
        if (myRoot != null) {
            myRoot.print(0);
        }
    }

    //In TreeNode
    private static final String indent1 = "    ";

    private void print(int indent) {
    	
    	//Stack<TreeNode> items = new Stack<TreeNode>();   	
        myRoot.print(indent);
        
        // TODO your code here
        print();
    }

    private static void println(Object obj, int indent) {
        for (int k=0; k<indent; k++) {
            System.out.print(indent1);
        }
        System.out.println(obj);
    }
    
    private void isOK(TreeNode t) throws IllegalStateException{
    	myRoot.isOK(t);
    }

    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
    }
    
    private TreeNode fibTreeHelper (int n){
    	TreeNode newNode;
    	if(n == 0){
    		newNode = new TreeNode(0);
    	} else if(n ==1){
    		newNode = new TreeNode(1);
    	} else{
    		TreeNode leftNode = fibTreeHelper(n-1);
    		TreeNode rightNode = fibTreeHelper(n-2);
    		int sum  = (Integer)leftNode.myItem+ (Integer)rightNode.myItem;
    		newNode = new TreeNode(sum,leftNode,rightNode);
    	}
    	return newNode;
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
            	System.out.println(nesting);
                if(expr.charAt(k) == '('){
                	nesting++;
                } else if( expr.charAt(k) == ')'){
                	nesting--;
                } 
                if (nesting == 0){
                	opPos = k+1;
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
    
    public void optimize() {
    	
    	
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
        	if(myLeft == null && myRight == null){
        		return 1;
        	} else {
        		int greatestSoFar = 0;
        		int greatestLeft = 0;
        		int greatestRight = 0;
        		if (myLeft != null){
        			greatestLeft = myLeft.height();
        		} 
        		if (myRight != null){
        			greatestRight = myRight.height();
        		}
        		greatestSoFar = Math.max(greatestLeft, greatestRight) + 1;
        		return greatestSoFar;
        	}
        }
        
        public boolean isCompleteleyBalanced(){
        	if(myLeft == null && myRight == null){
        		return true;
        	}
        	if(myLeft.height() == myRight.height() ){
        		if(myLeft.isCompleteleyBalanced() && myRight.isCompleteleyBalanced()){
        			return true;
        		}
        	}
        	return false; 
        }
        
        private void print(int indent){
        	
        	TreeNode current = this;

        	if (current.myRight != null){

        		indent ++;
        		current.myRight.print(indent);
        		indent --;
        	}

        	
            BinaryTree.println (myItem, indent);
            
            if(current.myLeft != null){
            	indent ++;
            	current.myLeft.print(indent);
            	indent --;
            }

        }
        
        private void isOK(TreeNode t) throws IllegalStateException{
        	if(myItem == t.myItem){
        		System.out.println("Encoutered duplicate");
        	}
            if (myLeft != null) {
                myLeft.isOK(t);
            }
            if (myRight != null) {
                myRight.isOK(t);
            }
        }
        

        
        
    }
}
