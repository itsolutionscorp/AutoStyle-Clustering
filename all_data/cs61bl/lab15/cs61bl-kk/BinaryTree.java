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
    	myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c",
    			new TreeNode("d", new TreeNode("e"), new TreeNode("f")), null));
    }
    
    public void fillSampleTree4() {
    	myRoot = new TreeNode("I", 
    				new TreeNode("been", 
    					new TreeNode("out", 
    						new TreeNode("on"), 
    						new TreeNode("that")), 
    					new TreeNode("open",
    						new TreeNode("road"),
    						new TreeNode("you"))), 
					new TreeNode("can", 
	    					new TreeNode("be", 
	    						new TreeNode("my"), 
	    						new TreeNode("full")), 
	    					new TreeNode("time",
	    						new TreeNode("baby"),
	    						new TreeNode("Rahul")))); 
    }
    
    public void fillSampleTree5() {
    	myRoot = new TreeNode("A", 
    				new TreeNode ("B"), 
    				new TreeNode("C", 
    					new TreeNode("D"), 
    					new TreeNode("E")));
    }
    
    public void fillSampleTree6() {
    	TreeNode inner = new TreeNode("A", 
    				new TreeNode ("B"), 
    				new TreeNode("C", 
    					new TreeNode("D"), 
    					new TreeNode("E")));
    	myRoot = new TreeNode("HA", inner, inner);
    }
    
    public void fillSampleTree7() {
    	TreeNode inner = new TreeNode("+", 
    				new TreeNode ("5"), 
    				new TreeNode("*", 
    					new TreeNode("2"), 
    					new TreeNode("1")));
    	myRoot = new TreeNode("*", inner, inner);
    }
    
    public int height() {
    	if (myRoot == null) {
    		return 0;
    	} else {
    		return myRoot.height();
    	}
    }
    
    public boolean isCompletelyBalanced() {
    	if (myRoot == null) {
    		return true;
    	}
    	else {
    		return myRoot.isCompletelyBalanced();
    	}
    }
    

    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        print(t, "the empty tree");
        t.fillSampleTree1();
        print(t, "sample tree 1");
        t.print();
        System.out.println(t.check());
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree2();
        print(t, "sample tree 2");
        t.print();
        System.out.println(t.check());
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree3();
        print(t, "sample tree 3");
        t.print();
        System.out.println(t.check());
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree4();
        print(t, "sample tree 4");
        t.print();
        System.out.println(t.check());
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree5();
        print(t, "sample tree 5");
        t.print();
        System.out.println(t.check());
        t.fillSampleTree6();
        print(t, "sample tree 6");
        t.print();
        System.out.println(t.check());
        for (int i = 0; i <= 5; i++) {
        	t = fibTree(i);
        	t.print();
        	System.out.println("++++++");
        }
        t = exprTree("((a+(5*(a+b)))+(6*5))");
        t.optimize();
        t.print();
        System.out.println(t.check());
        System.out.println("---");
        t = exprTree("((a+(5*(9+1)))+(6*5))");
        t.optimize();
        t.print();
        System.out.println(t.check());
        System.out.println("---");
        t = exprTree("((a+(5*(a+b)))+((6*5)*(7+(z*8))))");
        t.optimize();
        t.print();
        System.out.println(t.check());
        System.out.println("---");
        t = exprTree("(5+1)");
        t.optimize();
        t.print();
        System.out.println(t.check());
        System.out.println("---");
        t = exprTree("((20+(5*(3+7)))+(7+(z*8)))");
        t.optimize();
        t.print();
        System.out.println(t.check());
        t = exprTree("((20+(5*(3+7)))+(7+(128*8)))");
        t.optimize();
        t.print();
        System.out.println(t.check());
        t = exprTree("((20+(5*(3+7)))+(7+(128*8)))");
        t.optimize();
        t.print();
        System.out.println(t.check());
        t = exprTree("((AY/YY+(BAE*(CUL8TR+DTWO)))+(E+(mommy*I<3YOU)))");
        t.optimize();
        t.print();
        System.out.println(t.check());
        t.fillSampleTree7();
        t.print();
        System.out.println(t.check());
        t.optimize();
        t.print();
        System.out.println(t.check());
    }

    private static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
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
    
    public void isOK(TreeNode t) throws IllegalStateException {
    	if (alreadySeen.contains(t)) {
    		throw new IllegalStateException ("this node already exists in the tree");
    	}
    	if (t != null) {
    		alreadySeen.add(t);
    		isOK(t.myLeft);
    		isOK(t.myRight);
    	}
    }
    	
    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
    }
    
    private TreeNode fibTreeHelper(int n) {
    	if (n == 0) {
    		return new TreeNode(0);
    	}
    	if (n == 1) {
    		return new TreeNode(1);
    	}
    	TreeNode left = fibTreeHelper(n-1);
    	TreeNode right = fibTreeHelper(n-2);
    	return new TreeNode((int) left.myItem + (int) right.myItem, left, right);
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
            	} else if (expr.charAt(k) == ')') {
            		nesting--;
            	} else if (nesting == 0 && (expr.charAt(k) == '+' || expr.charAt(k) == '*')) {
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
            return new TreeNode(op, 
            		exprTreeHelper(opnd1), 
            		exprTreeHelper(opnd2)); // you fill this in
        }
    }
    
    public void optimize() {
        this.optimizeHelper();
    }
    
    private TreeNode optimizeHelper() {
    	if (myRoot == null) {
    		return null;
    	}
        if (myRoot.myLeft == null && myRoot.myRight == null) {
        	try {
        		Integer.parseInt(myRoot.myItem.toString()); 
        		return myRoot;
        	}
        	catch (NumberFormatException e) {
        		return myRoot;
        	}
        } else {
        myRoot.myLeft = new BinaryTree(myRoot.myLeft).optimizeHelper();
        myRoot.myRight = new BinaryTree(myRoot.myRight).optimizeHelper();
        try {
        	int left = Integer.parseInt(myRoot.myLeft.myItem.toString()); 
        	int right = Integer.parseInt(myRoot.myRight.myItem.toString()); 
        	if (myRoot.myItem.equals("+")) {
        		myRoot.myItem = left + right;
        		myRoot.myLeft = null;
        		myRoot.myRight = null;
        	}
        	else if (myRoot.myItem.equals("*")) {
        		myRoot.myItem = left * right;
        		myRoot.myLeft = null;
        		myRoot.myRight = null;
        	}
        	return myRoot;
        }
        catch (NumberFormatException e) {
        	return myRoot;
        	}
        }
    }
    
    private static class TreeNode {

        public Object myItem;
        public TreeNode myLeft;
        public TreeNode myRight;
        private static final String indent1 = "    ";

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
        
        private void print(int indent) {
            // TODO your code here
        	if (myRight != null) {
        		myRight.print(indent + 1);
        	}
            println (myItem, indent + 1);
            // TODO your code here
            if (myLeft != null) {
            	myLeft.print(indent + 1);
            }
        }

        private static void println(Object obj, int indent) {
            for (int k=0; k<indent; k++) {
                System.out.print(indent1);
            }
            System.out.println(obj);
        }
        
        private int height() {
        	if (myLeft == null && myRight == null) {
        		return 1;
        	} else if (myLeft == null) {
        		return 1 + myRight.height();
        	} else if (myRight == null) {
        		return 1 + myLeft.height();
        	}
        	return Math.max(myLeft.height(), myRight.height()) + 1;
        }
        
        private boolean isCompletelyBalanced() {
        	if (myLeft == null && myRight == null) {
        		return true;
        	} else if (myLeft == null && myRight != null) {
            		return false;
        	} else if (myRight == null && myLeft != null) {
        		return false;
        	} else if (myLeft.height() != myRight.height()) {
        		return false;
        	}
        	return (myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced());
        }
        

    }
}
