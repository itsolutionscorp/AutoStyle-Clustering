import java.util.ArrayList;
import java.util.List;

public class BinaryTree {

    private TreeNode myRoot;

    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
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
    	myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c", new TreeNode("d"), new TreeNode("e")));
    }
    
    private static List alreadySeen;
    
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
    	System.out.println("checking " + t.myItem);
    	if (alreadySeen.contains(t.myItem)) {
    		throw new IllegalStateException ("duplicate node!");
    	}
    	else {
    		alreadySeen.add(t.myItem);
    	}
    	if (t.myLeft != null) {
    		isOK(t.myLeft);
    	}
    	if (t.myRight != null) {
    		isOK(t.myRight);
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
           return new TreeNode (expr.charAt(0)); // you fill this in
        } else {
            // expr is a parenthesized expression.
            // Strip off the beginning and ending parentheses,
            // find the main operator (an occurrence of + or * not nested
            // in parentheses, and construct the two subtrees.
            int nesting = 0;
            int opPos = 0;
            
            for (int k = 1; k < expr.length() - 1; k++) {
            	if (expr.charAt(k) == '(') {
            		nesting ++;
            	}
            	else if (expr.charAt(k) == ')') {
            		nesting --;
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
            return new TreeNode (op, exprTreeHelper(opnd1), exprTreeHelper(opnd2)); // you fill this in
        }
    }
    
    public void optimize() {
    	if (myRoot != null) {
    		myRoot.optimize();
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
        //TreeNode root = new TreeNode("a", new TreeNode("b", new TreeNode("d"), new TreeNode("e")), new TreeNode("c", new TreeNode("f"), new TreeNode("g")));
        //BinaryTree myTree = new BinaryTree(root);
        //System.out.println(myTree.isCompletelyBalanced());
        //myTree.print();
        //System.out.println(myTree.check());
        
    	fibTree(0).print();
    	fibTree(1).print();
    	fibTree(2).print();
    	fibTree(3).print();
    	fibTree(4).print();
    	BinaryTree myExprTree = exprTree("((a+(5*(9+1)))+(6*5))");
    	myExprTree.optimize();
    	myExprTree.print();
    	
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
    		return new TreeNode(n - 1, fibTreeHelper(n - 1), fibTreeHelper(n - 2));
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
        
        public int height() {
        	if (myLeft == null && myRight == null) {
        		return 1;
        	}
        	else if (myLeft == null) {
        		return 1 + myRight.height();
        	}
        	else if (myRight == null) {
        		return 1 + myLeft.height();
        	}
        
        	return 1 + Math.max(myLeft.height(), myRight.height());
        }
        
        public boolean isCompletelyBalanced() {
        	if (myLeft == null && myRight == null) {
        		return true;
        	}
        	else if (myLeft.height() == myRight.height()) {
        		return true;
        	}
        	return false;
        }
        
        private static final String indent1 = "    ";
        
        private void print(int indent) {
        	if (myRight != null) {
        		indent ++;
        		myRight.print(indent);
        		indent --;
        	}
        	println (myItem, indent);
        	if (myLeft != null) {
        		indent ++;
        		myLeft.print(indent);
            	indent --;
        	}    	
        }

        private static void println(Object obj, int indent) {
            for (int k=0; k<indent; k++) {
                System.out.print(indent1);
            }
            System.out.println(obj);
        }
        
        public void optimize() {
        	if (myLeft != null) {
    			myLeft.optimize();
    		}
    		if (myRight != null) {
    			myRight.optimize();
    		}
        	if (myItem.equals("+")) {
        		try { 
        			myItem = Integer.parseInt(String.valueOf(myLeft.myItem)) + Integer.parseInt(String.valueOf(myRight.myItem));
        			myLeft = null;
            		myRight = null;
        		}
        		catch (Exception e) {}	
        	}
        	else if (myItem.equals("*")) {
        		try { 
        			myItem = Integer.parseInt(String.valueOf(myLeft.myItem)) * Integer.parseInt(String.valueOf(myRight.myItem));
        			myLeft = null;
            		myRight = null;
        		}
        		catch (Exception e) {}
        	}
        }
    }
}

