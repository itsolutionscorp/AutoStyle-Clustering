import java.util.ArrayList;

public class BinaryTree {

    private TreeNode myRoot;

    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
    }
    
    public void optimize() {
    	if (myRoot != null) {
    		myRoot.optimize(); 
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
            	if (expr.charAt(k) == '(') {
            		nesting++;
            	} else if (expr.charAt(k) == ')') {
            		nesting--; 
            	} else if (nesting == 0) {
            		if (expr.charAt(k) == '+' || expr.charAt(k) == '*') {
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
            return new TreeNode(op, exprTreeHelper(opnd1), exprTreeHelper(opnd2)); // you fill this in
        }
    }
    
    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
    }

    private TreeNode fibTreeHelper (int n) {
    	TreeNode rtn;
    	if (n < 0) {
    		return null; 
    	} else if (n == 0) {
    		rtn = new TreeNode(0); 
    	} else if (n == 1) {
    		rtn = new TreeNode(1);
    	} else {
    		TreeNode prev = fibTreeHelper(n - 1);
    		TreeNode prev2 = fibTreeHelper(n - 2);
    		rtn = new TreeNode((int) prev.myItem + (int) prev2.myItem, prev, prev2); 
    	}
    	return rtn; 
    	
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

    // Contains nodes already seen in the traversal. 
    private ArrayList<TreeNode> alreadySeen;
    
    private void isOK(TreeNode t) throws IllegalStateException {
//    	alreadySeen = new ArrayList<TreeNode>(); 
    	if (t != null) {
    		if (alreadySeen.contains(t)) {
    			throw new IllegalStateException("already contains this node");
    		}
    		else {
    			alreadySeen.add(t); 
    			if (t.myRight != null) {
    				isOK(t.myRight);
    			} if (t.myLeft != null) {
    	    		isOK(t.myLeft); 
    			}
    		}
    	}
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
    	myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c", 
    			new TreeNode("d", new TreeNode("e"), new TreeNode("f")), null));
    }
    
    public void fillSampleTree4() {
    	myRoot = new TreeNode("a", null, null); 
    }
    
    public void fillSampleTree5() {
    	myRoot = new TreeNode("a",
    					new TreeNode("b",
    							new TreeNode("d"), 
    							null),
    					new TreeNode("c",
    							new TreeNode("e"),
    							new TreeNode("f"))
    					);
    }
    
    public void fillSampleTree6() {
    	TreeNode repeat = new TreeNode("z");
    	myRoot = new TreeNode("a", repeat, new TreeNode("e", new TreeNode("d"), repeat));
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
    	return false; 
    }

    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
//        print(t, "the empty tree");
//        System.out.println(t.height()); 
//        System.out.println(t.isCompletelyBalanced());
//        t.fillSampleTree1();
//        print(t, "sample tree 1");
//        System.out.println(t.height()); 
//        System.out.println(t.isCompletelyBalanced());
//        t.fillSampleTree2();
//        print(t, "sample tree 2");
//        System.out.println(t.height());
//        System.out.println(t.isCompletelyBalanced());
//        t.fillSampleTree3();
//        print(t, "sample tree 3"); 
//        System.out.println(t.height());
//        System.out.println(t.isCompletelyBalanced());
//        t.fillSampleTree4(); 
//        print(t, "sample tree 4");
//        System.out.println(t.height());
//        System.out.println(t.isCompletelyBalanced());
//        t.fillSampleTree5(); 
//        print(t, "sample tree 5");
//        System.out.println(t.height());
//        System.out.println(t.isCompletelyBalanced());
//        t.fillSampleTree6(); 
//        t.print();
//        //testing fib
//        int i = 5;
//        System.out.println("testing " + i); 
//        t = fibTree(i); 
        t = exprTree("((a+(5*(9+1)))+(6*5))");
//        t = exprTree("(a+(5+5))");
//        t.print();
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
        	if (myItem == null) {
        		return 0; 
        	} else if (myLeft == null && myRight == null) {
        		return 1;
        	} else {
        		int best = 1; 
        		if (myLeft != null) {
        			best = Math.max(best, myLeft.height() + 1); 
        		} 
        		if (myRight != null) {
        			best = Math.max(best, myRight.height() + 1); 
        		}
        		return best; 
        	}
        }
        
        public boolean isCompletelyBalanced() {
        	if (myLeft == null && myRight == null) {
        		return true; 
        	} else if (myLeft != null && myRight != null) {
        		return myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced(); 
        	} else return false; 
        }
      
      //LAB15
        
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
                
        public void optimize() {       	
        	if (myRight != null) {
        		myRight.optimize(); 
        	}
        	if (myLeft != null) {
        		myLeft.optimize(); 
        	}
        	int right;
        	int left;
        	if (myItem.equals("+") || myItem.equals("*")) {
        		try {
        			right = Integer.parseInt(myRight.myItem.toString());
        			left = Integer.parseInt(myLeft.myItem.toString());
        		}
        		catch (NumberFormatException e) {
        			return;
        		}
        		
        		if (myItem.equals("+")) {
        			myItem = right + left; 
        		} else {
        			myItem = right * left; 
        		}
        		myRight = null;
        		myLeft = null; 
        	
        		
        		
        	}
        	
        }
        
    }
}
