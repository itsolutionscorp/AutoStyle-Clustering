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

    public void fillSampleTree1() {
        myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
    }

    public void fillSampleTree2() {
        myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d",
                new TreeNode("e"), new TreeNode("f")), null), new TreeNode("c"));
    }
    
    public void fillSampleTree3() {
    	myRoot = new TreeNode("a", 
    			new TreeNode("b"), new TreeNode("c",
    					new TreeNode("d", 
    							new TreeNode("e"), new TreeNode("f")), null));	
    }
    
    public void fillSampleTree4() {
    	myRoot = new TreeNode("a", 
    			new TreeNode("b"), new TreeNode("c",
    					new TreeNode("d", 
    							new TreeNode("e"), new TreeNode("f", new TreeNode ("g", null, new TreeNode ("lol")),null)), null));
    }
    
    public void fillSampleTree5() {
    	myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("e", new TreeNode("g"), new TreeNode("h")), new TreeNode("f"
    			, new TreeNode("i"), new TreeNode("j"))), new TreeNode("c"));
    }
    
    public void fillSampleTree6() {
    	myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("e", new TreeNode("g"), new TreeNode("h")), new TreeNode("f"
    			, new TreeNode("i"), new TreeNode("j"))), new TreeNode("c", new TreeNode("1", new TreeNode("3"), new TreeNode("4"))
    			, new TreeNode("2", new TreeNode("5"), new TreeNode("6"))));
    }
    
    public int height() {
    	if (myRoot != null) {
    		return myRoot.height(); 
    	}
    	return 0; 
    }
    
    public boolean isCompletelyBalanced() {
    	if (myRoot != null) {
    		return myRoot.isCompletelyBalanced();
    	}
    	return true;
    }

    public static void main(String[] args) {
        BinaryTree t;
        
        t = new BinaryTree();
        System.out.println(t.isCompletelyBalanced());
        print(t, "the empty tree");
        
        t.fillSampleTree1();
        System.out.println(t.isCompletelyBalanced());
        print(t, "sample tree 1");
        
        t.fillSampleTree2();
        System.out.println(t.isCompletelyBalanced());
        print(t, "sample tree 2");
        
        t.fillSampleTree3();
        System.out.println(t.isCompletelyBalanced());
        print(t, "sample tree 3");

        t.fillSampleTree4();
        System.out.println(t.isCompletelyBalanced());
        print(t, "sample tree 4");
        
        t.fillSampleTree5();
        System.out.println(t.isCompletelyBalanced());
        print(t, "sample tree 5");
        
        t.fillSampleTree6();
        System.out.println(t.isCompletelyBalanced());
        print(t, "sample tree 6"); 
        
        t = exprTree("((a+(5*(9+1)))+(6*5))");
        t.optimize(); 
        t.print();

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
    
    private void isOK(TreeNode t) throws IllegalStateException {
    	if (t != null) {
	    	if (alreadySeen.contains(t.myLeft) || alreadySeen.contains(t.myRight)) {
	    		throw new IllegalStateException("there is a repeat node");
	    	} 
	    	if (t.myLeft != null) {
	    		alreadySeen.add(t.myLeft);
	    		isOK(t.myLeft);
	    	}
	    	if (t.myRight != null) {
	    		alreadySeen.add(t.myRight);
	    		isOK(t.myRight);
	    	}
    	} 
    }

    
    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
    }

    private TreeNode fibTreeHelper (int n) {
    	if (n == 0 || n == 1) {
    		return new TreeNode(new Integer(n));
    	} else {
    		TreeNode left = fibTreeHelper(n-1);
    		TreeNode right = fibTreeHelper(n-2);
    		return new TreeNode((Integer) left.myItem + (Integer) right.myItem, left, right); 
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
            return new TreeNode(expr.substring(0,1)); // you fill this in
        } else {
            // expr is a parenthesized expression.
            // Strip off the beginning and ending parentheses,
            // find the main operator (an occurrence of + or * not nested
            // in parentheses, and construct the two subtrees.
            int nesting = 0;	
            int opPos = 0;
            for (int k = 1; k < expr.length() - 1; k++) {
            	if (expr.charAt(k) == '(') {
            		nesting++;
            	}
            	if (expr.charAt(k) == ')') {
            		nesting--;
            	}
                if ( (expr.charAt(k) == '+' || expr.charAt(k) == '*') && (nesting == 0)) {
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
    	optimizeHelper(this.myRoot);
    	}
    
    public void optimizeHelper(TreeNode t) {
    	if (t.myLeft != null) {
        	optimizeHelper(t.myLeft);
    	}
    	if (t.myRight != null) {
    		optimizeHelper(t.myRight);
    	}
    	if (t != null) {
    		if (t.myLeft != null && t.myRight != null) {
    			if ( isInteger( (String) t.myLeft.myItem) && isInteger((String)t.myRight.myItem)) {
    				if (t.myItem.equals("+")) {
        				t.myItem = Integer.parseInt( (String) t.myLeft.myItem) + Integer.parseInt( (String) t.myRight.myItem);
    				} else {
        				t.myItem = Integer.parseInt( (String) t.myLeft.myItem) * Integer.parseInt( (String) t.myRight.myItem);
    				}
    				t.myItem = t.myItem.toString();
    				t.myLeft = null;
    				t.myRight = null;
    			}
    		}
    	}
    }
    
    public boolean isInteger(String s) {
    	try {
    		Integer.parseInt(s);
    		return true;
    	} catch (NumberFormatException e) {
    		return false;
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
        	if (myRight != null) {
        		myRight.print(indent + 1);
        	}
            println (myItem, indent);
            if (myLeft != null) {
            	myLeft.print(indent+1);
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
        	} else {
        		int left = 0; 
        		int right = 0; 
        		if (myLeft != null) {
        			left = myLeft.height();
        		}
        		if (myRight != null) {
        			right = myRight.height(); 
        		}
        		return Math.max(left, right) + 1; 
        	}
        }
        
        private boolean isCompletelyBalanced() {
        	if (myLeft == null && myRight == null) {
        		return true; 
        	} 
        	if (myLeft == null) {
        		return false;
        	}
        	if (myRight == null) {
        		return false;
        	}
        	else {
        		int leftHeight = myLeft.height();
        		int rightHeight = myRight.height(); 
        		boolean left = myLeft.isCompletelyBalanced();
        		boolean right = myRight.isCompletelyBalanced(); 
        		return ((left && right) && (leftHeight == rightHeight));
        	}
        }
    }
}
