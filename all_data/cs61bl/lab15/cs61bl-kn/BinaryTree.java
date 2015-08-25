import java.util.ArrayList;

public class BinaryTree {

    private TreeNode myRoot;
    
    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
    }
    
    public void print() {
        if (myRoot != null) {
            myRoot.print(0);
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
    public void fillSampleTree4() {
    	myRoot = new TreeNode("A", new TreeNode("B",new TreeNode("C"), new TreeNode("D")), new TreeNode("E", new TreeNode("F"), new TreeNode("G")));
    }
    
    public void fillSampleTree5() {
    	myRoot = new TreeNode("A", new TreeNode("B"), new TreeNode("C", new TreeNode("D"), new TreeNode("E")));
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
    	} else {
    		TreeNode myLeft = fibTreeHelper(n-1);
    		TreeNode myRight = fibTreeHelper(n-2);
    		return new TreeNode(((int) myLeft.myItem) +((int)myRight.myItem),  myLeft,  myRight);
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
                	opPos++;
                } 
                else if (expr.charAt(k) == ')') {
                	opPos++;
                	nesting--;
                }
                else if (expr.charAt(k) == '*' || expr.charAt(k) == '+') {
                	if (nesting == 0) {
                		opPos++;
                		break;
                	} else {
                		opPos++;
                	}
                } else {
                	opPos++;
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
    		return new TreeNode(expr.charAt(opPos), exprTreeHelper(expr.substring(1, opPos)), exprTreeHelper(expr.substring(opPos+1,expr.length()-1))); // you fill this in
        }
    }
    
    public void optomize() {
    	if  (myRoot != null) {
    		myRoot.optomize();
    	}
    }

    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        t.isOK(t.myRoot);
        print(t, "the empty tree");
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree1();
        t.isOK(t.myRoot);
        print(t, "sample tree 1");
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree2();
        t.isOK(t.myRoot);
        print(t, "sample tree 2");
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
       
        t.fillSampleTree3();
        t.isOK(t.myRoot);
        print(t, "sample tree 3");
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());

        t.fillSampleTree4();
        t.isOK(t.myRoot);
        print(t, "sample tree 4");
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        
        t.fillSampleTree5();
        t.isOK(t.myRoot);
        t.print();
        
       t.myRoot = t.exprTreeHelper("((a+(5*(9+1)))+(6*5))");
       t.print();
       
       t.optomize();
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

        private boolean isInteger() {
        	for (int a = 0; a < myItem.toString().length(); a++) {
        		if (!Character.isDigit(myItem.toString().charAt(a))) {
        			return false;
        		}
        	}
        	return true;
        }
        public void optomize() {
        	if (myRight.myItem.toString().charAt(0) == '*' || myRight.myItem.toString().charAt(0) == '+') {
				myRight.optomize();
			}
        	if (myLeft.myItem.toString().charAt(0) == '*' || myLeft.myItem.toString().charAt(0) == '+') {
				myLeft.optomize();
			}
        	if (myRight.isInteger() && myLeft.isInteger()) {
					if ((Character) myItem == '*') {
						myItem =(Integer.parseInt((String) myLeft.myItem) *  Integer.parseInt((String) myRight.myItem))+"";
					} else {
						myItem = (Integer.parseInt((String) myLeft.myItem) + Integer.parseInt((String) myRight.myItem))+"";
					}
					myLeft = null;
					myRight = null;
        	}
		}

		public TreeNode(Object obj, TreeNode left, TreeNode right) {
            myItem = obj;
            myLeft = left;
            myRight = right;
        }
        
        public int height() {
        	if (myLeft == null) {        
        		if (myRight == null) {
        			return 1;
        		} else {
        			return myRight.height()+1;
        		}
        	} 
        	else if (myRight == null) {        
    			return myLeft.height()+1;
        	} else {
        		int bestSoFar;
        		bestSoFar = Math.max(myLeft.height(), myRight.height()) + 1;
        		return bestSoFar;
        	}
        }
        
        public boolean isCompletelyBalanced() {
        	if (myRight == null || myLeft == null) {
        		return myRight == null && myLeft == null;
        	}
        	return (myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced() && myLeft.height() == myRight.height());
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
        
        //In TreeNode
        private static final String indent1 = "    ";

        private void print(int indent) {
            // TODO your code here
        	if (myRight != null) {
        	myRight.print(indent+2);
        	}
            println (myItem, indent);
            // TODO your code here
            if (myLeft != null) {
            myLeft.print(indent+2);
            }
        }

        private static void println(Object obj, int indent) {
            for (int k=0; k<indent; k++) {
                System.out.print(indent1);
            }
            System.out.println(obj);
        }
    }
    
    private ArrayList<Object> alreadySeen = new ArrayList<Object>();

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
    	if (t != null) {
            	if (alreadySeen.contains(t)) {
            		throw new IllegalStateException("Not a Tree");
            	} else {
            		alreadySeen.add(t);
            		if (t.myRight != null) {
            		isOK(t.myRight);
            		}
            		if (t.myLeft != null) {
            		isOK(t.myLeft);
            		}
            	}   
            }    	
    	}
    



}
