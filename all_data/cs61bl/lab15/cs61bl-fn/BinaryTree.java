import java.util.ArrayList;

public class BinaryTree {

    private TreeNode myRoot;
    private ArrayList<TreeNode> alreadySeen; 
    
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
    	if (myRoot != null) {
    		return myRoot.isCompletelyBalanced();
    	}
    	return true;
    }

    public void fillSampleTree1() {
        myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
    }

    public void fillSampleTree2() {
        myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d",
                new TreeNode("e"), new TreeNode("f")), null), new TreeNode("c"));
    }
    public void fillSampleTree3() {
    	TreeNode a = new TreeNode("f");
        myRoot = new TreeNode("a", new TreeNode("b", a,
        		new TreeNode("e")), new TreeNode("c", 
                		a, new TreeNode("g")));
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
    	
    	if (alreadySeen.contains(t)) {
    		throw new IllegalStateException("Node appears more than once");
    	}
    	alreadySeen.add(t);
    	if (t.myLeft!=null) {
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
    
    private TreeNode fibTreeHelper (int n) {
    	if (n == 0) {
    		return new TreeNode(0);
    	} else if (n == 1) {
    		return new TreeNode(1);
    	} else {
    		TreeNode leftNode = fibTreeHelper(n-1);
    		TreeNode rightNode = fibTreeHelper(n -2);
    		int myNode = (int) leftNode.myItem + (int) rightNode.myItem;
    		return new TreeNode(myNode, leftNode, rightNode);
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
        	return new TreeNode(expr);
        } else {
            // expr is a parenthesized expression.
            // Strip off the beginning and ending parentheses,
            // find the main operator (an occurrence of + or * not nested
            // in parentheses, and construct the two subtrees.
            int nesting = 0;
            int opPos = 0;
            for (int k = 1; k < expr.length() - 1; k++) {
            	if (expr.charAt(k) =='(') {
            		nesting++;
            	} else if (expr.charAt(k) ==')') {
            		nesting--;
            	}
            	if (nesting == 0) {
	                if (expr.charAt(k) == '*' || expr.charAt(k)=='+') {
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
           	TreeNode leftNode = exprTreeHelper(opnd1);
           	TreeNode rightNode = exprTreeHelper(opnd2);
           	return new TreeNode(op, leftNode, rightNode);
        }
    }
    
    public void optimize() {
    	if (myRoot!= null) {
    		myRoot.optimize();
    	}
    }
    

    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        print(t, "the empty tree");
        t.fillSampleTree1();
        print(t, "sample tree 1");
        t.print();
        t.fillSampleTree2();
        print(t, "sample tree 2");
        t.print();
        t.fillSampleTree3();
        print(t, "sample tree 3");
        t.print();
        System.out.println("SampleTree3's Check : " + t.check()); 
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
        	int height = 1;
        	int leftHeight = 0, rightHeight = 0;
        	if (myLeft != null) {
                leftHeight = myLeft.height();
            }
            if (myRight != null) {
            	rightHeight = myRight.height();
            }
        	return height + Math.max(leftHeight, rightHeight);   	
        }
        private boolean isCompletelyBalanced() {
        	int leftHeight = 0, rightHeight = 0;
        	boolean left = true, right = true;
        	if (myLeft != null) {
                leftHeight = myLeft.height();
                left = myLeft.isCompletelyBalanced();
            }
            if (myRight != null) {
            	rightHeight = myRight.height();
            	right = myRight.isCompletelyBalanced();
            }
        	return leftHeight == rightHeight && left && right;
        }
        

        private void print(int indent) {
            if (myRight != null) {
            	myRight.print(indent+1);
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
        
        private void optimize() {
        	if (myItem.equals("*")) {
        		if (myLeft != null) {
        			myLeft.optimize();
        		}
        		if (myRight != null) {
        			myRight.optimize();
        		}
        		
        		if (isInteger((String) myLeft.myItem) 
        				&& isInteger((String) myRight.myItem)) {
        			myItem = Integer.toString(Integer.parseInt((String) myLeft.myItem) *
        					Integer.parseInt((String) myRight.myItem));
        			myLeft = myRight = null;
        		}
        		
        	} else if (myItem.equals("+")) {
        		if (myLeft != null) {
        			myLeft.optimize();
        		}
        		if (myRight != null) {
        			myRight.optimize();
        		}
        		
        		if (isInteger((String) myLeft.myItem) 
        				&& isInteger((String) myRight.myItem)) {
        			myItem =Integer.toString(Integer.parseInt((String) myLeft.myItem) +
        					Integer.parseInt((String) myRight.myItem));
        			myLeft = myRight = null;
        		}
        	}
        }
        
        private static boolean isInteger(String toParse) {
        	try  
        	  {  
        	    int d = Integer.parseInt(toParse);  
        	  }  
        	  catch(NumberFormatException nfe)  
        	  {  
        	    return false;  
        	  }  
        	  return true;  
        }
    }
}
