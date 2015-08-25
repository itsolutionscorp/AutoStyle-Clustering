import java.util.ArrayList;

public class BinaryTree {

    private TreeNode myRoot;
    private static ArrayList<TreeNode> alreadySeen;

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
    	myRoot = new TreeNode("A", new TreeNode("B"), new TreeNode("C", new TreeNode("D", new TreeNode("E"), new TreeNode("F")), null));
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
        alreadySeen = new ArrayList<TreeNode>(); 
        try { 
            isOK(myRoot); 
            return true; 
        } catch (IllegalStateException e) {
            return false; 
        } 
    }
    private void isOK(TreeNode t) throws IllegalStateException {
    	if (myRoot != null) {
    		myRoot.isOK(myRoot);
    	}
    }
    
    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
    }
    
    private TreeNode fibTreeHelper (int n) {
    	if (n == 0 || n == 1) {
    		return new TreeNode(n, null, null);
    	} else {
    		return new TreeNode(fiber(n), fibTreeHelper(n - 1), fibTreeHelper(n - 2));
    	}
    }
    private int fiber(int n) {
    	if (n == 0 || n == 1) {
    		return n;
    	} else {
    		return fiber(n - 1) + fiber(n - 2);
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
        	return new TreeNode(expr, null, null); // you fill this in
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
            	} else if (nesting == 0) {
            		if (expr.charAt(k) == '*' || expr.charAt(k) == '+') {
            		opPos = k;
            		}
            	}
            }
            String opnd1 = expr.substring(1, opPos);
            String opnd2 = expr.substring(opPos + 1, expr.length() - 1);
            String op = expr.substring(opPos, opPos + 1);
//            System.out.println("expression = " + expr);
//            System.out.println("operand 1  = " + opnd1);
//            System.out.println("operator   = " + op);
//            System.out.println("operand 2  = " + opnd2);
//            System.out.println();
            return new TreeNode(op, exprTreeHelper(opnd1), exprTreeHelper(opnd2));
            // you fill this in
        }
    }
    
    
    public void optimize() {
    	if (myRoot != null) {
    		optimizeHelper(myRoot);
    	}
    }
    
    private void optimizeHelper(TreeNode cur) {
    	if (cur.myLeft != null && cur.myRight != null) {

    		if (isOp((String) cur.myLeft.myItem)) {
    			optimizeHelper(cur.myLeft);
    		}

    		if (isOp((String) cur.myRight.myItem)) {
    			optimizeHelper(cur.myRight);
    		}

    		if (isInteger((String) cur.myLeft.myItem) && isInteger((String) cur.myRight.myItem)) {
    			if (((String) cur.myItem).contains("+")) {
    				cur.myItem = Integer.toString(Integer.parseInt((String) cur.myLeft.myItem) + Integer.parseInt((String) cur.myRight.myItem));
    			}
    			
    			if (((String) cur.myItem).contains("-")) {
    				cur.myItem = Integer.toString(Integer.parseInt((String) cur.myLeft.myItem) - Integer.parseInt((String) cur.myRight.myItem));
    			}
    			
    			if (((String) cur.myItem).contains("*")) {
    				cur.myItem = Integer.toString(Integer.parseInt((String) cur.myLeft.myItem) * Integer.parseInt((String) cur.myRight.myItem));
    			}
    			
    			if (((String) cur.myItem).contains("/")) {
    				cur.myItem = Integer.toString(Integer.parseInt((String) cur.myLeft.myItem) / Integer.parseInt((String) cur.myRight.myItem));
    			}
    			
    			
    			cur.myLeft = null;
    			cur.myRight = null;
    		}
    	}
    }
    
    private static boolean isOp(String s) {
    	if (s.contains("+")) return true;
    	if (s.contains("-")) return true;
    	if (s.contains("*")) return true;
    	if (s.contains("/")) return true;
    	
    	return false;
    }
    
    private static boolean isInteger(String s) {
    	try {
    		Integer.parseInt(s);
    	} catch (NumberFormatException e) {
    		return false;
    	} catch (NullPointerException e) {
    		return false;
    	}
    	
    	return true;
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
        BinaryTree fib = t.fibTree(5);
        fib.print();
        BinaryTree expr = t.exprTree("((a+(5*(9+1)))+(6*5))");
        expr.print();
        expr.optimize();
        expr.print();
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
        	int bestSoFar = 0;
        	if (myLeft != null) {
        		bestSoFar = Math.max(myLeft.height(), bestSoFar);
        	}
        	
        	if (myRight != null) {
        		bestSoFar = Math.max(myRight.height(), bestSoFar);
        	}
        	
        	return bestSoFar + 1;
        }
        
        private boolean isCompletelyBalanced() {
        	if (myLeft == null && myRight == null) {
        		return true;
        	} else if (myLeft == null || myRight == null) {
        		return false;
        	} else {
        		if (myLeft.height() == myRight.height()) {
        			if (myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced()) {
        				return true;
        			}
        		}
        	}
        	
        	return false;
        }
        
        
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
        }

        private static void println(Object obj, int indent) {
            for (int k=0; k<indent; k++) {
                System.out.print(indent1);
            }
            System.out.println(obj);
        }
        
        private void isOK(TreeNode t) throws IllegalStateException {
        	if (myLeft != null) {
        		if (alreadySeen.contains(myLeft.myItem)) {
        			throw new IllegalStateException("Node Appears more than once");
        		}
        		alreadySeen.add((TreeNode) myLeft.myItem);
        	}
        	if (myRight != null) {
        		if (alreadySeen.contains(myRight.myItem)) {
        			throw new IllegalStateException("Node Appears more than once");
        		}
        		alreadySeen.add((TreeNode) myRight.myItem);
        	}
        }
        
//        private void optimizeHelper() {
//        	if (myLeft != null){
//        		myLeft.optimizeHelper();
//        	} if (myRight != null) {
//        		myRight.optimizeHelper();
//        	}
//        	if (myLeft != null || myRight != null) {
//                    if (myLeft.is_leaf() && myRight.is_leaf()) {
//                    	if (Character.isDigit(((String) myRight.myItem).charAt(0)) && Character.isDigit(((String) myLeft.myItem).charAt(0))) {
//                            if (myItem.equals("+")) {
//                            myItem = optimizeAdd(Integer.parseInt((String) myRight.myItem), Integer.parseInt((String) myLeft.myItem));
//                            myRight = null;
//                            myLeft = null;
//                            } else if (myItem.equals("*")) {
//                            myItem = optimizeMultiply(Integer.parseInt((String) myRight.myItem), Integer.parseInt((String) myLeft.myItem));
//                            myRight = null;
//                            myLeft = null;
//                            }
//                    	}
//                    }
//            } if (myLeft != null){
//                    myLeft.optimizeHelper();
//            } if (myRight != null) {
//                    myRight.optimizeHelper();
//            }
//    }
//
//
//        private boolean is_leaf() {
//        	if (myRight == null && myLeft == null) {
//        		return true;
//        	}
//        	return false;
//        }
//        private String optimizeAdd(int a, int b) {
//        	int i = a + b;
//        	return String.valueOf(i);
//        }
//        private String optimizeMultiply(int a, int b) {
//        	int i = a * b;
//        	return String.valueOf(i);
//        }
//    }   
    }
}
