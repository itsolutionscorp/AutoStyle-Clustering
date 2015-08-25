package lab15;

import java.util.ArrayList;

public class BinaryTree {

    private TreeNode myRoot;
    private ArrayList alreadySeen; 

    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
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
    	} else {
    		return myRoot.isCompletelyBalanced();
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
    	myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c",
    			new TreeNode("d", new TreeNode("e"), new TreeNode("f")), null));
    }
    
    public void fillSampleTree4() {
    	myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d"),
        		new TreeNode("e")), new TreeNode("c", new TreeNode("f"),
        		new TreeNode("g")));
    }
    
    public void fillIllegalTree1() {
    	myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d"),
        		new TreeNode("e")), new TreeNode("b", new TreeNode("f"),
        		new TreeNode("g")));
    }
    
    public void fillIllegalTree2() {
    	myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("b"),
        		new TreeNode("e")), new TreeNode("c", new TreeNode("f"),
        		new TreeNode("g")));
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
    
    private void isOK(TreeNode t) throws IllegalStateException {
    	if (alreadySeen.contains(t.myItem)) {
    		throw new IllegalStateException("No node can appear more than once.");
    	}
    	else {
    		alreadySeen.add(t.myItem);
    		if (t.myLeft != null) {
    			isOK(t.myLeft);
    		}
    		if (t.myRight != null) {
    			isOK(t.myRight);
    		}
    	}
    }
    
    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
    }

    private TreeNode fibTreeHelper(int n) {
    	if (n == 0) {
    		return new TreeNode(0, null, null);
    	}
    	else if (n == 1) {
    		return new TreeNode(1, null, null);
    	}
    	else {
    		TreeNode t1 = fibTreeHelper(n - 1);
    		TreeNode t2 = fibTreeHelper(n - 2);
    		int item =  (int) (t1.myItem) + (int) (t2.myItem);
    		return new TreeNode(item, t1, t2);
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
            // you fill this in
        	return new TreeNode(expr, null, null);
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
            	if ((nesting == 0) && ((expr.charAt(k) == '+') || (expr.charAt(k) == '*'))) {
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
            return new TreeNode(op, exprTreeHelper(opnd1), exprTreeHelper(opnd2)); // you fill this in
        }
    }
    
    public void optimize() {
    	if (myRoot != null) {
    		myRoot.optimizeHelper();
    	}
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
        t.print();
    }

    public void print() {
        if (myRoot != null) {
            myRoot.print(0);
        }
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
        	int leftHeight = 1;
        	int rightHeight = 1;
        	if (myLeft != null) {
        		leftHeight += myLeft.height();
        	}
        	if (myRight != null) {
        		rightHeight += myRight.height();
        	}
        	return Math.max(leftHeight, rightHeight);
        }
        
        private boolean isCompletelyBalanced() {
        	if (myLeft == null && myRight == null) {
        		return true;
        	} else if (myLeft.height() == myRight.height()) {
        		return (myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced());
        	} else {
        		return false;
        	}
        }
        
      //In TreeNode
        private static final String indent1 = "    ";

        private void print(int indent) {
            // TODO your code here
        	if (myRight != null) {
        		myRight.print(indent + 1);
        	}
            println (myItem, indent);
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
        
        private void optimizeHelper() {
        	if (myRight != null) {
        		myRight.optimizeHelper();
        	}
        	if (myLeft != null) {
        		myLeft.optimizeHelper();
        	}
        	if ((myRight != null) && (myLeft != null)) {
        		try {
        			int right = Integer.parseInt((String) myRight.myItem);
        			int left = Integer.parseInt((String) myLeft.myItem);
        			if (((String) myItem).equals("*")) {
            			myItem = right * left;
            		}
            		else if (((String) myItem).equals("+")) {
            			myItem = right + left;
            		}
        			myItem = myItem.toString();
            		myRight = null;
            		myLeft = null;
        		}
        		catch (NumberFormatException e) {
        			
        		}
        	}
        	
        }
        
    }
}
