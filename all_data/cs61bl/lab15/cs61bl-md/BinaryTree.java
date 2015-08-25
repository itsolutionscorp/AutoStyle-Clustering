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
    	myRoot = new TreeNode("a", new TreeNode("b"), 
    			new TreeNode("c", 
    					new TreeNode("d", new TreeNode("e"), new TreeNode("f")), null));
    }
    
    public void fillSampleTree4() {
    	myRoot = new TreeNode("a");
    }
    
    public int height() {
    	if (myRoot != null) {
    		return myRoot.height();
    	}
    	return 0;
    }
    
    public boolean isCompletelyBalanced() {
    	if (myRoot != null ) {
    		return myRoot.isCompletelyBalanced();
    	}
    	return true;
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
    	if (t == null) return;
    	for (Object i : alreadySeen) {
    		if (t.myItem.equals(((TreeNode)i).myItem)) throw new IllegalStateException();
    	}
    	alreadySeen.add(t);
    	isOK(t.myLeft);
    	isOK(t.myRight);
    }
    
    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
    }

    private TreeNode fibTreeHelper (int n) {
    	if (n == 0) return new TreeNode(0);
    	if (n == 1) return new TreeNode(1);
    	TreeNode left = fibTreeHelper(n - 1);
    	TreeNode right = fibTreeHelper(n - 2);
    	return new TreeNode((int)left.myItem + (int)right.myItem, 
    			fibTreeHelper(n-1), fibTreeHelper(n-2));
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
            	char c = expr.charAt(k);
            	if (c == '(') nesting ++;
            	if (c == ')') nesting --;
                if (nesting == 0 &&(expr.charAt(k) == '+' || expr.charAt(k) == '*')) {
                	opPos = k; break;
                }
            
            
            }
            System.out.println("opPos : "+opPos);
            String opnd1 = expr.substring(1, opPos);
            String opnd2 = expr.substring(opPos + 1, expr.length() - 1);
            String op = expr.substring(opPos, opPos + 1);
            System.out.println("expression = " + expr);
            System.out.println("operand 1  = " + opnd1);
            System.out.println("operator   = " + op);
            System.out.println("operand 2  = " + opnd2);
            System.out.println();
            // you fill this in
            return new TreeNode(op, exprTreeHelper(opnd1), exprTreeHelper(opnd2)); 
            
        }
    }
    
    public void optimize() {
    	if (myRoot != null) {
    		optimizeHelper(myRoot);
    	}
    }
    
    private boolean optimizeHelper(TreeNode node) {
    	if (node == null) return true;
    	if (node.myRight == null && node.myLeft == null) {
    		System.out.println("Hitting if");
    		String digit = "0123456789";
    		if (digit.indexOf((String)node.myItem) == -1) return false;
    		System.out.println("Hitting true");
    		return true;
    	}
    	
    	boolean l = optimizeHelper(node.myLeft);
    	boolean r = optimizeHelper(node.myRight);
    	if (l && r) {
    		if (node.myItem.equals("+")) {
    			node.myItem = "" + (Integer.parseInt((String)node.myLeft.myItem)
    					+ Integer.parseInt((String)node.myRight.myItem));
    		}
    		if (node.myItem.equals("*")) {
    			node.myItem = "" + Integer.parseInt((String)node.myLeft.myItem)
    					* Integer.parseInt((String)node.myRight.myItem);
    		}
    		node.myLeft = null;
    		node.myRight = null;
    		return true;
    	}
    	return false;
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
        t.fillSampleTree4();
        System.out.println(t.check());
        t.print();
//        BinaryTree fibo = fibTree(3);
//        fibo.print();
        BinaryTree e = exprTree("((a+(5*(9+1)))+(6*5))");
        e.optimize();
        e.print();
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

        //In TreeNode
        private static final String indent1 = "    ";

        private void print(int indent) {
        	if (myRight != null) myRight.print(indent + 1);
        	println (myItem, indent);
        	if (myLeft != null) myLeft.print (indent + 1);
        }

        private static void println(Object obj, int indent) {
            for (int k=0; k<indent; k++) {
                System.out.print(indent1);
            }
            System.out.println(obj);
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
        	if (myLeft == null && myRight == null) return 1;
        	if (myLeft == null) return 1 + myRight.height();
        	if (myRight == null) return 1 + myLeft.height();
        	return 1 + Math.max(myLeft.height(), myRight.height());
        }
        
        public boolean isCompletelyBalanced() {
        	if (myLeft == null && myRight == null) return true;
        	if (myLeft == null || myRight == null) return false;
        	if (myLeft.height() == myRight.height()) {
        		return myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced();
        	}
        	return false;
        }
        
    }
}
