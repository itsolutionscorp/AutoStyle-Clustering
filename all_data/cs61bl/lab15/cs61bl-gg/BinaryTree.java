import java.util.ArrayList;

public class BinaryTree {

    private TreeNode myRoot;
    private ArrayList<Object> alreadySeen;

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
    	myRoot = new TreeNode("a", 
    			new TreeNode("b"), 
    			new TreeNode("c", new TreeNode("d", 
    			new TreeNode("e"), new TreeNode("f")), null));
    }
    
    public void fillSampleTree4() {
    	myRoot = new TreeNode("a", 
    			new TreeNode("b", new TreeNode("d"), new TreeNode("e")), 
    			new TreeNode("c",null, new TreeNode("f")));
    }
    
    public void fillSampleTree5() {
    	myRoot = new TreeNode("a", 
    			new TreeNode("b", new TreeNode("d"), new TreeNode("e")), 
    			new TreeNode("c",null, new TreeNode("a")));
    }
    
    public int height() {
    	if (myRoot != null)
    		return myRoot.height();
    	return 0;
    }
    
    public boolean isCompletelyBalanced() {
    	if (myRoot != null)
    		return myRoot.isCompletelyBalanced();
    	return true;
    }
    
    public void print() {
        if (myRoot != null) {
            myRoot.print(0);
        }
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
        t.fillSampleTree4();
        print(t, "sample tree 4");
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.print();
        
        BinaryTree n = fibTree(0);
        n.print();
        n = fibTree(1);
        n.print();
        n = fibTree(2);
        n.print();
        n = fibTree(3);
        n.print();
        n = fibTree(4);
        n.print();
        n = fibTree(5);
        n.print();
        n = fibTree(8);
        n.print();
        
        BinaryTree e = exprTree("((a+(5*(a+b)))+(6*5))");
        e.print();
        
        BinaryTree o = exprTree("((a+(5*(9+1)))+(6*5))");
        o.optimize();
        o.print();
        
        BinaryTree test = exprTree("((a+(5*(9+10)))+((6+1)*(5*5)))");
        test.optimize();
        test.print();
    }

    private static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
    }
    
    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
    }

    private TreeNode fibTreeHelper (int n) {
    	if (n == 0) {
    		return new TreeNode(0, null, null);
    	}
    	if (n == 1) {
    		return new TreeNode(1, null, null);
    	} else {
    		TreeNode left = fibTreeHelper(n-1);
    		TreeNode right = fibTreeHelper(n-2);
    		return new TreeNode(((Integer) left.myItem + (Integer) right.myItem), left, right);
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
            TreeNode result = null;
            for (int k = 1; k < expr.length() - 1; k++) {
            	if (expr.charAt(k) == '(') {
            		while (expr.charAt(k+1) != ')') {
            			k++;
            		}
            		k++;
            	}
                if (expr.charAt(k) == '+' || expr.charAt(k) == '*') {
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
    
    public void optimize() {
    	if (myRoot != null)
    		optimizeHelper(myRoot);
    }
    
    private static void optimizeHelper(TreeNode t) {
    	int myLeft = 0;
    	int myRight = 0;
    	boolean execute = true;
    	if(t.myLeft == null && t.myRight == null)
    		return;
    	try{
    		myRight = Integer.parseInt((String)(t.myRight.myItem));
    	} catch (NumberFormatException e){
    		execute = false;
    		optimizeHelper(t.myRight);
    		try {
    			myRight = Integer.parseInt((String) t.myRight.myItem);
    			execute = true;
    		} catch (NumberFormatException a) {
    		}


    	} finally{
    	try{
    		myLeft = Integer.parseInt((String)(t.myLeft.myItem));
    	} catch (NumberFormatException e){	
    		execute = false;
    		optimizeHelper(t.myLeft);
    		try {
    			myLeft = Integer.parseInt((String) t.myLeft.myItem);
    			execute = true;
    		} catch (NumberFormatException a) {
    		}
    		
    	} finally{
    		if (execute) {
	    	String operator = (String)t.myItem;
	    	Integer result = 0;
	    	if (operator.equals("+")) {
	    		result = myRight + myLeft;
	    	}
	    	if (operator.equals("*")) {
	    		result = myRight * myLeft;
	    	}
	    	t.myItem = result.toString();
	    	t.myLeft = null;
	    	t.myRight = null;
	    	}
    	}
    	}
    }
    
    public boolean check() { 
        alreadySeen = new ArrayList<Object>(); 
        try { 
            isOK(myRoot); 
            return true; 
        } catch (IllegalStateException e) { 
            return false; 
        } 
    }
    
    private void isOK(TreeNode t) throws IllegalStateException {
    	if (alreadySeen.contains(t.myItem)) {
    		throw new IllegalStateException();
    	}
    	alreadySeen.add(t.myItem);
    	if (t.myRight != null) {
    		isOK(t.myRight);
    	}
    	if (t.myLeft != null) {
    		isOK(t.myLeft);
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
        
        private int height() {
            if (myLeft == null && myRight == null) {
            	return 1;
            } else {
                int bestSoFar = 1;
                if (myLeft != null)
                	bestSoFar = Math.max(1 + myLeft.height(), bestSoFar);
        		if (myRight != null)
        			bestSoFar = Math.max(1 + myRight.height(), bestSoFar);
                return bestSoFar;
            }
        }
        
        private boolean isCompletelyBalanced() {
        	if (myLeft == null && myRight == null) {
            	return true;
        	} else {
        		boolean balanced = true;
        		if (myLeft != null && myRight != null) {
	        		if (myRight.height() != myLeft.height()) {
	        			return false;
	        		}
	        		return myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced();
        		}
                return false;
        	}
        }
        

        private void print(int indent) {
            if (myRight == null && myLeft == null) {
            	println(myItem,indent);
            } else {
            if(myRight != null)
            	this.myRight.print(indent+1);
            println(myItem, indent);
            if(myLeft != null)
            	this.myLeft.print(indent+1);
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
