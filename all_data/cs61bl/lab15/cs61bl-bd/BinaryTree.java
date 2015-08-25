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
    
    public int height() {
    	if (myRoot != null) {
    		return myRoot.height();
    	}
    	return 0;
    }
    
    public boolean isCompletelyBalanced() {
    	if (myRoot == null) {
    		return true;
    	}
    	else if (myRoot.myLeft == null && myRoot.myRight == null) {
    		return true;
    	}
    	else {
    		return myRoot.myLeft.height() == myRoot.myRight.height();
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

    // Contains nodes already seen in the traversal. 
    private ArrayList alreadySeen; 
    
    private void isOK(TreeNode t) throws IllegalStateException {
    	if (t != null) {
    		if (alreadySeen.contains(t.myItem)) {
    			throw new IllegalStateException();
    		}
    		alreadySeen.add(t.myItem);
    	}
    	if (t.myLeft != null) {
    		isOK(t.myLeft);
    	}
    	if (t.myRight != null) {
    		isOK(t.myRight);
    	}
    }
    
    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
    }

    private TreeNode fibTreeHelper(int n) {
    	if (n == 0 || n == 1) {
    		return new TreeNode(n);
    	}
    	TreeNode toReturn = new TreeNode(0, fibTreeHelper(n-1), fibTreeHelper(n-2));
    	toReturn.myItem = (Integer) toReturn.myLeft.myItem + (Integer) toReturn.myRight.myItem;
    	return toReturn;
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
            	if (expr.charAt(k) == '(') {
            		nesting++;
            	}
            	else if (expr.charAt(k) == ')') {
            		nesting--;
            	}
            	else if (nesting == 0 && (expr.charAt(k) == '+' || expr.charAt(k) == '*')) {
            		opPos = k;
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
            TreeNode result = new TreeNode(op, exprTreeHelper(opnd1), exprTreeHelper(opnd2)); // you fill this in
            return result;
        }
    }
    
    public void optimize()  {
    	if (myRoot != null) {
        	optimizeHelper(myRoot);
    	}
    }
    
    // citation: http://stackoverflow.com/questions/237159/whats-the-best-way-to-check-to-see-if-a-string-represents-an-integer-in-java
    private static boolean isInteger( String input ) {
        try {
            Integer.parseInt( input );
            return true;
        }
        catch(NumberFormatException e ) {
            return false;
        }
    }
    
    private static TreeNode optimizeHelper(TreeNode t) {
    	if (t.myLeft == null || t.myRight == null) {
    		return t;
    	}
    	if (t.myLeft != null && t.myRight != null) {
    		optimizeHelper(t.myLeft);
    		optimizeHelper(t.myRight);
    	}
    	if (t.myItem.equals("+")) {
    		if (isInteger((String)t.myLeft.myItem) && isInteger((String)t.myRight.myItem)) {
        		t.myItem = "" + (Integer.parseInt((String) t.myLeft.myItem) + Integer.parseInt((String) t.myRight.myItem));
        		t.myLeft = null;
        		t.myRight = null;
    		}
    	}
    	if (t.myItem.equals("*")) {
    		if (isInteger((String)t.myLeft.myItem) && isInteger((String)t.myRight.myItem)) {
        		t.myItem = "" + (Integer.parseInt((String) t.myLeft.myItem) * Integer.parseInt((String) t.myRight.myItem));
        		t.myLeft = null;
        		t.myRight = null;
    		}
    	}
    	return t;
    }

    public void fillSampleTree1() {
        myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
    }

    public void fillSampleTree2() {
        myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d",
                new TreeNode("e"), new TreeNode("f")), null), new TreeNode("c"));
    }
    
    public void fillSampleTree3() {
    	myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c", 
    			new TreeNode("d", new TreeNode("e"), new TreeNode("f")), null));
    }
    
    public void fillSampleTree4() {
        myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d",
                new TreeNode("e"), new TreeNode("a")), null), new TreeNode("c"));
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
        
        BinaryTree bt = new BinaryTree();
        bt.fillSampleTree4();
        bt.print();
        
        System.out.println(bt.check());
        
        fibTree(5).print();
        
        System.out.println("testing expression tree:");
        String expr = "((a+(5*(a+b)))+(6*5))";
        exprTree(expr).print();
        
        System.out.println("========================");
        
        String expr2 = "((a+(5*(9+1)))+(6*5))";
        BinaryTree bintr = exprTree(expr2);
        bintr.optimize();
        System.out.println("testing optimize:");
        bintr.print();
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
        
        public int height() {
        	int bestSoFar = 0;
        	if (myItem == null) {
    	    	return 0;
    	    } 
        	else if (myLeft == null && myRight == null) {
        		return 1;
        	}
        	else if (myLeft == null) {
        		bestSoFar = 1 + myRight.height();
        	}
        	else if (myRight == null) {
        		bestSoFar = 1 + myLeft.height();
        	}
        	else {
    	        bestSoFar = 1 + Math.max(myLeft.height(), myRight.height());
    	    }
        	return bestSoFar;
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

        //In TreeNode
        private static final String indent1 = "    ";

        private void print(int indent) {
            // TODO your code here
        	if (myLeft == null && myRight == null) {
        		println(myItem, indent);
        	}
        	else {
        		if (myRight != null) {
        			myRight.print(indent+1);
        		}
        		println(myItem, indent);
        		if (myLeft != null) {
        			myLeft.print(indent+1);
        		}
        	}
        	
            //println (myItem, indent);
            // TODO your code here
            //indent++;
        }

        private static void println(Object obj, int indent) {
            for (int k=0; k<indent; k++) {
                System.out.print(indent1);
            }
            System.out.println(obj);
        }
    }
}
