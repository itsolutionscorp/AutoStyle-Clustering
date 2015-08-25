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
    
    public void fillSampleTree3(){
    	myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c", new TreeNode("d", new TreeNode("e"), new TreeNode("f")), null));
    }
    
    public void fillSampleTree4(){
    	myRoot = new TreeNode("A", new TreeNode("B"), new TreeNode("C", new TreeNode("D"), new TreeNode("E"))); 
    }
    
    public void fillSampleTree5(){
    	TreeNode t = new TreeNode("A");
    	myRoot = new TreeNode("A", new TreeNode("B"), new TreeNode("C", t, t)); 
    }
    
    public void fillSampleTree6(){
    	TreeNode n = new TreeNode("B");
    	TreeNode tn = new TreeNode("C", n, n);
    	myRoot = tn;
    }

    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        print(t, "the empty tree");
        t.fillSampleTree1();
        System.out.println(t.check());
        print(t, "sample tree 1");
        t.fillSampleTree2();
        System.out.println(t.check());
        print(t, "sample tree 2");
        t.fillSampleTree3();
        System.out.println(t.check());
        print(t, "sample tree 3");
        t.fillSampleTree4();
        t.print();
        System.out.println(t.check());
        t.fillSampleTree5();
        System.out.println(t.check());
        t.fillSampleTree6();
        System.out.println(t.check());
        t = fibTree(3);
        t.print();
        t = exprTree("((a+(5*(a+b)))+(6*5))");
        t.print();
    }

    private static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
    }
    
    public int height(){
    	if (myRoot != null){
    		return myRoot.height(myRoot);
    	}
    	return 0;
    }
    
    public boolean isCompletelyBalanced(){
    	if (myRoot != null){
    		return myRoot.isCompletelyBalanced(myRoot);
    	}
    	return true;
    }
    
    public void print() {
        if (myRoot != null) {
            myRoot.print(0);
        }
    }
    
 // Contains nodes already seen in the traversal. 
    private ArrayList alreadySeen; 
    //(IllegalStateException is provided in Java.)
    
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
    	for (Object obj : alreadySeen){
    		if (obj.equals(t)){
    			throw new IllegalStateException();
    		}
    	}
    	alreadySeen.add(t);
    	if (t.myRight != null){
    		isOK(t.myRight);
    	}
    	if (t.myLeft != null){
    		isOK(t.myLeft);
    	}
    }
    
    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
    }

    private TreeNode fibTreeHelper(int n){
    	BinaryTree zero = new BinaryTree();
    	zero.myRoot = new TreeNode(0);
    	BinaryTree one = new BinaryTree();
    	one.myRoot = new TreeNode(1);
    	if (n == 0){
    		return zero.myRoot;
    	}
    	if (n == 1){
    		return one.myRoot;
    	}
    	TreeNode fib = new TreeNode(null);
    	fib.myLeft = fibTreeHelper(n-1);
    	fib.myRight = fibTreeHelper(n-2);		
    	fib.myItem = (int)fib.myLeft.myItem + (int)fib.myRight.myItem;
    	return fib;
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
            TreeNode s = new TreeNode(expr.charAt(0));
            return s;// you fill this in
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
                }
                if (expr.charAt(k) == ')'){
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
            TreeNode tn = new TreeNode(op, exprTreeHelper(opnd1), exprTreeHelper(opnd2));
            return tn; // you fill this in
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
        
        public int height(TreeNode t){
        	if (t == null) {
    			return 0;
    		} else {			
    			return 1 + Math.max(height(t.myLeft), height(t.myRight));
    		}
        }
        
        public boolean isCompletelyBalanced(TreeNode t){
        	if (t == null) {
    			return true;
    		} else {
    			return (isCompletelyBalanced(t.myLeft) && isCompletelyBalanced(t.myRight) && (height(t.myLeft) == height(t.myRight)));
    		}
        }
        
        private static final String indent1 = "    ";

        private void print(int indent) {
            // TODO your code here
    
            // TODO your code here
            if (myRight != null){
            	myRight.print(indent+1);
            }
            println (myItem, indent);
            if (myLeft != null){
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
