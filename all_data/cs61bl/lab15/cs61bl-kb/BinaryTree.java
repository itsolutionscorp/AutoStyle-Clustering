import java.util.Iterator;
import java.util.Stack;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.NoSuchElementException;

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
    	myRoot = new TreeNode("a", new TreeNode("b", null, null), new TreeNode("c", 
    			new TreeNode("d"), new TreeNode("e")));
    }
    
    public void fillSampleTree4() {
    	myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("a"), new TreeNode("f")), new TreeNode("c", 
    			new TreeNode("d"), new TreeNode("e")));
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
    	if (t.myLeft == null && t.myRight == null) {
    		if (alreadySeen.contains(t.myItem)) {
    			throw new IllegalStateException("Already seen");
    		}
    		else {
    			return;
    		}
    	}
    	if (t.myLeft != null) {
    		if (alreadySeen.contains(t.myItem)) {
    			throw new IllegalStateException("Already seen");
    		}
    		else {
    			alreadySeen.add(t.myItem);
    			isOK(t.myLeft);
    		}
    	}
    	else if (t.myRight != null) {
    		if (alreadySeen.contains(t.myItem)) {
    			throw new IllegalStateException("Already seen");
    		}
    		else {
    			alreadySeen.add(t.myItem);
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
    	if (n == 0) {
    		return new TreeNode(0);
    	}
    	if (n == 1) {
    		return new TreeNode(1);
    	}
    	return new TreeNode((Integer) fibTreeHelper(n - 1).myItem + (Integer) fibTreeHelper(n - 2).myItem, 
    			fibTreeHelper(n - 1), fibTreeHelper(n - 2));
    	
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
                char current = expr.charAt(k);
                if (current == '(') {
                	nesting += 1;
                }
                if (current == ')') {
                	nesting -= 1;
                }
                if (nesting == 0) {
                	if ((current == '*') || (current == '+')) {
                		opPos = k;
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
            if (nesting == 0) {
            	return new TreeNode(op, exprTreeHelper(opnd1), exprTreeHelper(opnd2));         
            }
            else {
            	return null;
            }
        }
    }
    

    public static void main(String[] args) {
        BinaryTree t;
        BinaryTree f;
        t = new BinaryTree();
        f = new BinaryTree();
        print(t, "the empty tree");
        t.fillSampleTree1();
        print(t, "sample tree 1");
        t.fillSampleTree2();
        print(t, "sample tree 2");
        t.fillSampleTree3();
        print(t, "sample tree 3");
        t.print();
        f.fillSampleTree4();
        f.print();

        
//        BinaryTree f = new BinaryTree(new TreeNode("s"));
//        System.out.println(f.isCompletelyBalanced());
//        System.out.println(t.height());
//        BinaryTree f = new BinaryTree();
//        System.out.println(f.height());
//        f.fillSampleTree4();
//        System.out.println(f.isCompletelyBalanced());
//        System.out.println(t.isCompletelyBalanced());
        
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
        	int bestSoFar = 1;
        	if (myLeft == null && myRight == null) {
            	return bestSoFar;
            } else {
            	if (myRight == null) {
            		bestSoFar = Math.max(myLeft.height() + 1, bestSoFar);
            	}
            	else if (myLeft == null) {
            		bestSoFar = Math.max(bestSoFar, myRight.height() + 1);
            	}
            	else {
            		bestSoFar = Math.max(myLeft.height() + 1, myRight.height() + 1);
            	}
                return bestSoFar;
            }
        }
        
        private boolean isCompletelyBalanced() {
        	if (myLeft == null && myRight == null) {
        		return true;
        	}
        	else if (myLeft != null && myRight != null) {
        		if (myLeft.height() == myRight.height()) {
        			return myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced();	
        		}
        		return false;
        	}	
        	return false;
        }
        
        private static final String indent1 = "    ";

        private void print(int indent) {
            // TODO your code here
        	if (myRight != null) {
        		myRight.print(indent + 1);
        	}
        	println (myItem, indent);
            // TODO your code here
            if (myLeft != null) {
            	myLeft.print(indent + 1);
            }
//        	if (myRight == null && myLeft == null) {
//        		println(myItem, indent);
//        	}
        }

        private static void println(Object obj, int indent) {
            for (int k=0; k<indent; k++) {
                System.out.print(indent1);
            }
            System.out.println(obj);
        }
    }
}
