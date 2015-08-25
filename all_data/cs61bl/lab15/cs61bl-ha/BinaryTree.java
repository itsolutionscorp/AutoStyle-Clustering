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
    	TreeNode d = new TreeNode("d", new TreeNode("e"), new TreeNode("f"));
    	TreeNode c = new TreeNode("c", d, null);
    	myRoot = new TreeNode("a", new TreeNode("b"), c);
    }
    
    public void fillSampleTree4() {
    	TreeNode d = new TreeNode("d", new TreeNode("e"), new TreeNode("f"));
    	TreeNode c = new TreeNode("c", d, null);
    	myRoot = new TreeNode("a", new TreeNode("b", null, d), c);
    }
    
    public int height() {
    	if (myRoot != null) {
    		return myRoot.height();
    	}
    	return 0;
    }
    
    public boolean isCompletelyBalanced() {
    	if (myRoot == null)
    		return true;
    	return myRoot.isCompletelyBalanced();
    }
    
    public void print() {
        if (myRoot != null) {
            myRoot.print(0);
        }
    }
    
    // Contains nodes already seen in the traversal. 
    private ArrayList<TreeNode> alreadySeen;
    public boolean check() { 
        alreadySeen = new ArrayList<TreeNode>(); 
        try { 
            isOK(myRoot); 
            return true; 
        } catch (IllegalStateException e) { 
            return false; 
        } 
    }
    
    /* The isOK method, in the worst case, runs in time proportional to 
     * N , where N is number of tree node in the tree. 
     */
    private void isOK(TreeNode t) throws IllegalStateException {
    	if (alreadySeen.contains(t)) {
    		throw new IllegalStateException("Node " + t + " already seen in the traveral");
    	}
    	alreadySeen.add(t);
    	if (t.myLeft != null) {
    		isOK(t.myLeft);
    	}
    	if (t.myRight != null) {
    		isOK(t.myRight);
    	}
    }
    
    public static void main(String[] args) {
//        BinaryTree t;
//        t = new BinaryTree();
//        print(t, "the empty tree");
//        t.fillSampleTree1();
//        print(t, "sample tree 1");
//        t.fillSampleTree2();
//        print(t, "sample tree 2");
//        t.fillSampleTree3();
//        print(t, "sample tree 3");
        
        BinaryTree t9;
        t9 = exprTree("(((a+10)+(5*(2*3)))+(((6*(3+5))+c)*b))");
        t9.optimize();
        t9.print();
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
    		return new TreeNode(new Integer(0));
    	}
    	if (n == 1) {
    		return new TreeNode(new Integer(1));
    	}
    	
    	TreeNode left = fibTreeHelper(n-1);
    	TreeNode right = fibTreeHelper(n-2);
    	Integer k = (Integer)left.myItem + (Integer)right.myItem;
    	return new TreeNode(k, left, right);
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
            return new TreeNode(expr); 
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
            	} else if (expr.charAt(k) == '+' || expr.charAt(k) == '*') {
            		if (nesting == 0) {
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
            return new TreeNode(op, exprTreeHelper(opnd1), exprTreeHelper(opnd2));
        }
    }
    
    public void optimize() {
    	optimizeHelper(myRoot);
    }
    
    private static void optimizeHelper(TreeNode t) {
    	//a legal expression must have both left and right
    	if (t.myLeft == null || t.myRight == null) {
    		return;
    	}
    	
    	if (t.myLeft != null) {
    		optimizeHelper(t.myLeft);
    	}
    	if (t.myRight != null) {
    		optimizeHelper(t.myRight);
    	}
    	
    	try {
    		int left, right;
    		left = Integer.parseInt((String)t.myLeft.myItem);
    		right = Integer.parseInt((String)t.myRight.myItem);
    		if (t.myItem.equals("+")) {
    			t.myItem = String.valueOf(left + right);
    		} else if (t.myItem.equals("*")) {
    			t.myItem = String.valueOf(left * right);
    		}
    		t.myLeft = null;
    		t.myRight = null;
    	} catch (NumberFormatException e) {
    		
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
        	if (myLeft == null && myRight == null)
        		return 1;
        	if (myLeft == null && myRight != null)
        		return 1 + myRight.height();
        	if (myLeft != null && myRight == null)
        		return 1 + myLeft.height();
        	return 1 + Math.max(myLeft.height(), myRight.height());
        }
        
        private boolean isCompletelyBalanced() {
        	if (myLeft == null && myRight == null)
        		return true;
        	if (myLeft == null && myRight != null)
        		return false;
        	if (myLeft != null && myRight == null)
        		return false;
        	return myLeft.height() == myRight.height() && myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced();
        }
        
        private void print(int indent) {
            // TODO your code here
        	if (myRight != null) {
        		myRight.print(indent+1);
        	}
            println (myItem, indent);
            // TODO your code here
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
    }
}
