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
    	myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c",
    			new TreeNode("d", new TreeNode("e", new TreeNode("g"), new TreeNode("h")), new TreeNode("f")), null)); 
    }
    
    public void fillSampleTree5() { // balanced tree of height 3
    	myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d"), new TreeNode("e")), new TreeNode("c", new TreeNode("f"), new TreeNode("g")));
    }
    
    public void fillSampleTree6() { // tree with only one node
    	myRoot = new TreeNode("a");
    }
    
    public void fillSampleTree7() {
    	myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c", new TreeNode("d"), new TreeNode("e")));
    }
    
    public void fillSampleTree8() { // looped tree to make isOK fail
    	TreeNode t = new TreeNode("a", new TreeNode("b"), null);
    	myRoot = t;
    	myRoot.myRight = t;
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
    	else {
    		return myRoot.isCompletelyBalanced();
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
    
    public void isOK(TreeNode t) {
    	if (t != null) {
    		if (alreadySeen.contains(t)) {
    			throw new IllegalStateException("repeated node");
    		}
    		alreadySeen.add(t);
    		if (t.myLeft != null) {
    			isOK(t.myLeft);
    		}
    		if (t.myRight != null) {
    			isOK(t.myRight);
    		}
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
        t.fillSampleTree7();
        t.print();
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
    		return new TreeNode(0);
    	}
    	else if (n == 1) {
    		return new TreeNode(1);
    	}
    	else {
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
            // you fill this in
        	int opPos = 0;
        	for (int k = 1; k < expr.length() - 1; k++) {
        		char c = expr.charAt(k);
        		if (c == '+' || c == '*') {
                	opPos = k;
                	break;
                }
        	}
        	String opnd1 = expr.substring(0, opPos);
            String opnd2 = expr.substring(opPos, expr.length() - 1);
            String op = expr.substring(opPos, opPos + 1);
            return new TreeNode(op, new TreeNode(opnd1), new TreeNode(opnd2));
        } else {
            // expr is a parenthesized expression.
            // Strip off the beginning and ending parentheses,
            // find the main operator (an occurrence of + or * not nested
            // in parentheses, and construct the two subtrees.
            int nesting = 0;
            int opPos = 0;
            for (int k = 1; k < expr.length() - 1; k++) {
            	// you fill this in
            	char c = expr.charAt(k);
            	if (c == '(') {
            		nesting++;
            	}
            	else if (c == ')') {
            		nesting--;
            	}
            	else if (nesting == 0 && (c == '+' || c == '*')) {
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
    	optimizeHelper(myRoot);
    }
    
    public void optimizeHelper(TreeNode t) {
    	if (t != null && (t.myLeft != null || t.myRight != null)) {
    		optimizeHelper(t.myLeft);
    		optimizeHelper(t.myRight);
    		if (BinaryTree.isInteger((String) t.myLeft.myItem) && BinaryTree.isInteger((String) t.myRight.myItem)) {
    			int l = Integer.parseInt((String) t.myLeft.myItem);
    			int r = Integer.parseInt((String) t.myRight.myItem);
    			if (((String) t.myItem).equals("+")) {
    				t.myItem = "" + (l + r);
    			}
    			else if (((String) t.myItem).equals("*")) {
    				t.myItem = "" + (l * r);
    			}
    			t.myLeft = null;
				t.myRight = null;
    		}
    	}
    }
    
    // Helper method for optimize, returns true if String s is an integer
    public static boolean isInteger(String s) {
        try { 
            Integer.parseInt(s); 
        } catch(NumberFormatException e) { 
            return false; 
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
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
        	int lHeight = 0;
        	int rHeight = 0;
        	if (myLeft != null) {
        		lHeight = myLeft.height();
        	}
        	if (myRight != null) {
        		rHeight = myRight.height();
        	}
        	return 1 + Math.max(lHeight, rHeight);
        }
        
        private boolean isCompletelyBalanced() {
        	if (myLeft == null && myRight == null) {
        		return true;
        	}
        	if (myLeft == null || myRight == null) {
        		return false;
        	}
        	if (myLeft.height() == myRight.height() && myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced()) {
        		return true;
        	}
        	return false;
        }
        
        private void print(int indent) {
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
    }
}
