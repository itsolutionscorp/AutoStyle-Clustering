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
        myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c", 
        		new TreeNode("d", new TreeNode("e"), new TreeNode("f")), null));
    }
    public void fillSampleTree4() {
    	myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("c"), new TreeNode("d")),
        		new TreeNode("b", new TreeNode("c"), new TreeNode("d")));
    }
    
    public int height() {
    	if (myRoot == null) {
    		return 0;
    	}
    	return myRoot.height();
    }
    
    public boolean isCompletelyBalanced() {
    	if (myRoot == null) {
    		return true;
    	}
    	return myRoot.isCompletelyBalanced();
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
        t = fibTree(6);
        t.print();
        t = exprTree("((a+(5*(a+b)))+(6*5))");
        t.print();
        t = exprTree("((abb+(52*(9+1)))+(6*5))");
        t.print();
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
    
    private boolean isOK(TreeNode t) throws IllegalStateException{
    	if (alreadySeen.contains(t)) {
    		throw new IllegalStateException("");
    	}
    	alreadySeen.add(t);
    	isOK(t.myLeft);
    	isOK(t.myRight);
    	return true;
    }
    
    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
    }

    private TreeNode fibTreeHelper (int n) {
    	TreeNode root = new TreeNode(0);
    	root.fib(n);
    	return root;
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
            TreeNode t = new TreeNode(null); // you fill this in
            t.eprTree(expr);
            return t;
        } else {
            // expr is a parenthesized expression.
            // Strip off the beginning and ending parentheses,
            // find the main operator (an occurrence of + or * not nested
            // in parentheses, and construct the two subtrees.
            int nesting = 0;
            int opPos = 0;
            expr = expr.substring(0, expr.length());
            for (int k = 1; k < expr.length() - 1; k++) {
            	if (expr.charAt(k) == '(') {
            		nesting ++;
            	}
            	if (expr.charAt(k) == ')') {
            		nesting --;
            	}
                if ((expr.charAt(k) == '*' || expr.charAt(k) == '+') && (nesting == 0)) {
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
            TreeNode t = new TreeNode(null); // you fill this in
            t.myItem = op;
            t.myLeft = new TreeNode(null);
            t.myRight = new TreeNode(null);
            t.myLeft.eprTree(opnd1);
            t.myRight.eprTree(opnd2);
            return t;
        }
    }
    
    public void optimize() {
    	if (myRoot != null) {
    		myRoot.optimize();
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
        
        public int height() {
        	if (myLeft == null && myRight != null) {
        		return 1 + myRight.height();
        	}
        	if (myLeft != null && myRight == null) {
        		return 1 + myLeft.height();
        	}
        	if (myLeft != null && myRight != null) {
        		return 1 + Math.max(myRight.height(), myLeft.height());
        	}
        	return 1;
        }
        
        public boolean isCompletelyBalanced() {
        	if (myLeft == null && myRight != null) {
        		return false;
        	}
        	if (myLeft != null && myRight == null) {
        		return false;
        	}
        	if (myLeft != null && myRight != null) {
        		return myRight.isCompletelyBalanced() && myLeft.isCompletelyBalanced() ;
        	}
        	return true;
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
        
        private static final String indent1 = "    ";

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
        
        public void fib(int n) {
        	if (n == 0) {
    			myItem = 0;
    			return;
    		} else if (n == 1) {
    			myItem = 1;
    			return;
    		}
        	myLeft = new TreeNode(0);
        	myLeft.fib(n-1);
        	myRight = new TreeNode(0);
        	myRight.fib(n-2);
        	myItem = ((int) myLeft.myItem) + ((int) (myRight.myItem));
        	return;
        }
        
        public void eprTree(String expr) {
        		boolean b = false;
	        	for (int i = 0; i < expr.length(); i++) {
	        		if (!Character.isDigit(expr.charAt(i))) {
	        			if (!Character.isLetter(expr.charAt(i))) {
	        				b = true;
	        			}
	        		}
	        	}
	        	if (!b) {
	        		myItem = expr;
	        		return;
	        	}
	        	int nesting = 0;
	            int opPos = 0;
	            for (int k = 1; k < expr.length() - 1; k++) {
	            	if (expr.charAt(k) == '(') {
	            		nesting ++;
	            	}
	            	if (expr.charAt(k) == ')') {
	            		nesting --;
	            	}
	                if ((expr.charAt(k) == '*' || expr.charAt(k) == '+') && (nesting == 0)) {
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
	            myItem = op;
	            myLeft = new TreeNode(null);
	            myRight = new TreeNode(null);
	            myLeft.eprTree(opnd1);
	            myRight.eprTree(opnd2);
        }
        
        public void optimize() {
        	if (myLeft == null && myRight == null) {
        		return;
        	}
        	if (Character.isDigit(((String)myLeft.myItem).charAt(0)) && 
        			Character.isDigit(((String)myRight.myItem).charAt(0))) {
        		if (myItem.equals("+")) {
        			myItem = Integer.toString((Integer.parseInt((String)(myLeft.myItem)) + Integer.parseInt((String)(myRight.myItem))));
        		}
        		if (myItem.equals("*")) {
        			myItem = Integer.toString((Integer.parseInt((String)(myLeft.myItem)) * Integer.parseInt((String)(myRight.myItem))));
        		}
        		myLeft = null;
        		myRight = null;
        	} else {
        		myRight.optimize();
        		myLeft.optimize();
        	}
        	if (myLeft!= null && myRight !=null && Character.isDigit(((String)myLeft.myItem).charAt(0)) && 
        			Character.isDigit(((String)myRight.myItem).charAt(0))) {
        		optimize();
        		return;
        	}
        		
        }
    }
}
