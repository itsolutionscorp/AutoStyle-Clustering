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
    
    public int height() {
    	if (myRoot != null) {
    		return myRoot.height();
    	}
    	return 0;
    }
    
    public boolean isCompletelyBalanced() {
    	if (myRoot != null) {
    		return myRoot.isBalanced();
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
        if (myRoot != null) {
            try { 
                isOK(myRoot); 
                return true; 
            } catch (IllegalStateException e) { 
                return false; 
            }
        }
        return true;
    }
    
    private void isOK(TreeNode t) throws IllegalStateException {
    	if (alreadySeen.contains(t.myItem)) {
    		throw new IllegalStateException("Node is already in the binary tree.");
    	} else {
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

    private TreeNode fibTreeHelper (int n) {
    	if (n == 0) {
    		return new TreeNode(n);
    	} else if (n == 1) {
    		return new TreeNode(n);
    	} else {
    		TreeNode left = fibTreeHelper(n-1);
    		TreeNode right = fibTreeHelper(n-2);
    		return new TreeNode((int)left.myItem + (int)right.myItem, left, right);
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
            for (int k = 1; k < expr.length() - 1; k++) {
                // you supply the missing code
            	if (nesting == 0) {
            		if (expr.charAt(k) == '+' || expr.charAt(k) == '*') {
            			opPos = k;
            		}
            	}
            	if (expr.charAt(k) == '(') {
            		nesting++;
            	} else if (expr.charAt(k) == ')') {
            		nesting--;
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
    		myRoot.optimize();
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
    	myRoot = new TreeNode("a", new TreeNode("b",new TreeNode("d", 
    			new TreeNode("e"), new TreeNode("f")), new TreeNode("d", 
    			new TreeNode("e"), new TreeNode("f"))), new TreeNode("c",
    			new TreeNode("d", new TreeNode("e"), new TreeNode("f")), 
    			new TreeNode("d", new TreeNode("e"), new TreeNode("f"))));
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
        
        
        /*t.fillSampleTree2();
        t.print();
        System.out.println(t.check()); */
        
        /*t.fillSampleTree1();
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree2();
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree3();
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree4();
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());*/
        
        /*BinaryTree fib = BinaryTree.fibTree(5);
        fib.print(); */
        
        //BinaryTree b = t.exprTree("(a+(4+6))");
        //b.print();
        //b.optimize();
        //System.out.println("");
       // b.print();
        System.out.println("");
        System.out.println("");
        
        BinaryTree a = t.exprTree("((a+(5*(9+1)))+(6*5))");
        a.optimize();
        System.out.println("");
        a.print();
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
        
        public int height() {
        	if (myLeft == null && myRight == null) {
        		return 1;
        	} else {
        		int bestSoFarLeft;
        		int bestSoFarRight;
        		if (myLeft != null) {
            		bestSoFarLeft = 1 + myLeft.height();
        		} else {
        			bestSoFarLeft = 1;
        		}
        		if (myRight != null) {
        			bestSoFarRight = 1 + myRight.height();
        		} else {
        			bestSoFarRight = 1;
        		}
        		int bestSoFar = Math.max(bestSoFarLeft, bestSoFarRight);
            	return bestSoFar;
        	}
        }
        
        public boolean isBalanced() {
        	if (myLeft == null && myRight == null) {
        		return true;
        	} 
        	if (myLeft == null && myRight != null) {
        		return false;
        	}
        	if (myLeft != null && myRight == null) {
        		return false;
        	} else {
        		return (myLeft.height() == myRight.height()) &&(myLeft.isBalanced() && myRight.isBalanced());
        	}
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
        
        //(a+(4+6)) --> + a + 4 6
        public void optimize() {
        	if (myRight != null) {
            	myRight.optimize();
        	}
        	if (myLeft != null) {
        		myLeft.optimize();
        	}
        	if (myLeft != null && myRight != null) {
            	if (optimize_helper()) {
            		System.out.println("test");
            		if (myItem.equals("+")) {
            			myLeft.myItem = Integer.parseInt((String)myLeft.myItem);
            			myRight.myItem = Integer.parseInt((String)myRight.myItem);
            			myItem = Integer.toString((int)myLeft.myItem + (int)myRight.myItem);
            			myLeft = null;
            			myRight = null;
            		} else if (myItem.equals("*")) {
            			myLeft.myItem = Integer.parseInt((String)myLeft.myItem);
            			myRight.myItem = Integer.parseInt((String)myRight.myItem);
            			myItem = Integer.toString((int)myLeft.myItem * (int)myRight.myItem);
            			myLeft = null;
            			myRight = null;
            		}
            	}
        	}
        }
        
        public boolean optimize_helper() {
        	try {
        		Integer.parseInt((String)myLeft.myItem);
        		Integer.parseInt((String)myRight.myItem);
        		return true;
        	} catch (Exception e) {
        		return false;
        	}
        }
    }
}
