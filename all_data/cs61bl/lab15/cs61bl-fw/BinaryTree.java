import java.util.ArrayList;

public class BinaryTree {

    private TreeNode myRoot;
    private ArrayList alreadySeen;
    public static boolean check = false;
    public static int countUpdate = 0;
    
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
    	myRoot = new TreeNode("A", new TreeNode("B"), new TreeNode("C", new TreeNode("D",
    			new TreeNode("E"), new TreeNode("F")), null));
    }
    
    public void fillSampleTree4() {
    	myRoot = new TreeNode("A", new TreeNode("B", new TreeNode("D"), new TreeNode("E")), 
    			new TreeNode("C", new TreeNode("F"), new TreeNode("G")));
    }
    
    public void fillSampleTree5() {
    	myRoot = new TreeNode("A", new TreeNode("B"), new TreeNode("C", new TreeNode("D"), new TreeNode("E"))); 
    }
    
    public void fillSampleTree6() {
    	myRoot = new TreeNode("F");
        myRoot = new TreeNode("a", new TreeNode("b", myRoot, myRoot), myRoot);
    }
    

    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        System.out.println("sample tree: " + t.check());
        print(t, "the empty tree");
//        System.out.println(t.height());
//        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree1();
        System.out.println("sample tree 1: " + t.check());
        print(t, "sample tree 1");
//        System.out.println(t.height());
//        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree2();
        System.out.println("sample tree 2: " + t.check());
        print(t, "sample tree 2");
//        System.out.println(t.height());
//        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree3();
        System.out.println("sample tree 3: " + t.check());
        print(t, "sample tree 3");
//        System.out.println(t.height());
//        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree4();
        print(t, "sample tree 4");
        System.out.println("sample tree 4: " + t.check());
//        System.out.println(t.height());
//        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree5();
//        t.print();
        
        t.fillSampleTree6();
        System.out.println("sample tree 6: " + t.check());
        
//        fibTree(5).print();
//        exprTree("((a+(5*(a+b)))+(6*5))").print();
        
        BinaryTree t1 = exprTree("((10+(1*(9+1)))+(6*5))");
        t1.optimize();
        t1.print();
        
    }

    private static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
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
    	if (t == null) {
    		return;
    	}
    	if (alreadySeen.contains(t)) {
    		throw new IllegalStateException();
    	} else {
    		alreadySeen.add(t);
    	}	
    	if (t != null) {
    		isOK(t.myLeft);
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
    		return new TreeNode(n);
    	} else if (n == 1) {
    		return new TreeNode(n);
    	} else {
    		
    		return new TreeNode((int)(fibTreeHelper(n-1).myItem)+(int)(fibTreeHelper(n-2).myItem), 
    				fibTreeHelper(n-1), fibTreeHelper(n-2));
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
            	} 
            	if (expr.charAt(k) == ')') {
            		nesting--;
            	}
            	
            	if (nesting == 0) {
            		if (expr.charAt(k) == '+' || expr.charAt(k) == '*') {
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
            return new TreeNode(op, exprTreeHelper(opnd1), exprTreeHelper(opnd2)); // you fill this in
        }
    }
    
    public void optimize() {
    	if (myRoot != null) {
    		myRoot.optimizeHelper();
    		
    		while (check) {
    			int temp = countUpdate;
    			myRoot.optimizeHelper();
    			if (temp == countUpdate) {
    				check = false;
    			}
    			countUpdate = 0;
    		}
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
        
        private int height() {
        	if (myItem == null) {
        		return 0;
        	} else if (myLeft == null && myRight != null) {
            	return 1 + myRight.height();
            } else if (myRight == null && myLeft != null) {
            	return 1 + myLeft.height();
            } else if (myRight == null && myLeft == null) {
            	return 1;
            } else {
            	return 1 + Math.max(myLeft.height(), myRight.height());
            }
        }
        
        private boolean isCompletelyBalanced() {
        	if (myLeft == null && myRight == null) {
        		return true;
        	} else if (myLeft == null && myRight != null) {
        		return false;
        	} else if (myLeft != null && myRight == null) {
        		return false;
        	} else {
        		return myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced();
        	}
        }
        
        private static final String indent1 = "    ";

        private void print(int indent) {
            // TODO your code here
        	if (myRight != null) {
        		myRight.print(indent+1);
        	}
        	indent++;
            println (myItem, indent);
            // TODO your code here
            indent--;
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
        
        public void optimizeHelper() {
        	if (myItem == null) {
        		return;
        	}
        	if (myLeft == null && myRight == null) {
        		return;
        	}
        	if (myLeft != null && myRight != null) {
	        	if (Character.isDigit(myLeft.myItem.toString().charAt(0)) && 
	        			Character.isDigit((char)(myRight.myItem.toString().charAt(0)))){
	        		if (myItem.toString().equals("*"))
	        			myItem = Integer.parseInt(myLeft.myItem.toString()) * Integer.parseInt(myRight.myItem.toString());
	        		else 
	        			myItem = Integer.parseInt(myLeft.myItem.toString()) + Integer.parseInt(myRight.myItem.toString());
	        		myLeft = null;
	        		myRight = null;
	        		check = true;
	        		countUpdate++;
	        	} 
        	}
        	if (myLeft != null) {
	        	myLeft.optimizeHelper();
        	}
        	if (myRight != null) {
	        	myRight.optimizeHelper();
        	} 	
        }
    }
}

