import java.util.ArrayList;

public class BinaryTree {

    private TreeNode myRoot;

    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
    }
    
    public int height() {
    	if (myRoot == null) {
            return 0;
        }
    	if  (myRoot.myLeft == null && myRoot.myRight == null) {
    		return 1;
    	}
    	if (myRoot.myLeft != null && myRoot.myRight == null) {
    		return 1 + myRoot.myLeft.heightOfBinaryTree(myRoot.myLeft);
    	}
    	if (myRoot.myLeft == null && myRoot.myRight != null) {
    		return 1 + myRoot.myRight.heightOfBinaryTree(myRoot.myRight);
    	}
    	
        else {
            return 1 +Math.max(myRoot.myLeft.heightOfBinaryTree(myRoot.myLeft), myRoot.myRight.heightOfBinaryTree(myRoot.myRight));
        }
    	
    }
    
    public boolean isCompletelyBalanced() {
    	if (myRoot == null) return true;
    	if (myRoot.myLeft == null && myRoot.myRight == null) return true;
    	if (myRoot.myLeft == null || myRoot.myRight == null) return false;
    	if (myRoot.myLeft.heightOfBinaryTree(myRoot.myLeft) == myRoot.myRight.heightOfBinaryTree(myRoot.myRight)) {
    		return true;
    	} else {
    		return false;
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
    
    public void print() {
        if (myRoot != null) {
            myRoot.print(0);
        }
    }
    
    public void fillSampleTree0() {
    	myRoot = new TreeNode("a", null, null);
    }
    

    public void fillSampleTree1() {
        myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
    }

    public void fillSampleTree2() {
        myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d",
                new TreeNode("e"), new TreeNode("f")), null), new TreeNode("c"));
    }
    
    public void fillSampleTree3() {
    	myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c", new TreeNode("d", new TreeNode("e"), new TreeNode("f")), null));
    }
    public void fillSampleTree4() {
    	myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d", new TreeNode("e"), new TreeNode("f")), null), 
    							   new TreeNode("c", new TreeNode("d", new TreeNode("e"), new TreeNode("f")), null));
    }
    
    public void fillSampleTree5() {
        myRoot = new TreeNode("a", new TreeNode("b"), null);
    }



    private static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
    }
    
    public boolean check() { 
        alreadySeen = new ArrayList();
        try { 
        	isOK(myRoot);
        	System.out.println(alreadySeen.size());
            return true; 
        } catch (IllegalStateException e) { 
            return false; 
        } 
    }
    
	private void isOK(TreeNode t) throws IllegalStateException {
		System.out.println(t.myItem);
		if (t != null) {
			if (alreadySeen.contains(t)) {
				throw new IllegalStateException();
			}
			if (alreadySeen.isEmpty()) {
				alreadySeen.add(t);
			}
			if (t.myLeft != null) {
				isOK(t.myLeft);
				alreadySeen.add(t.myLeft);
			}
			if (t.myRight != null) {
				isOK(t.myRight);
				alreadySeen.add(t.myRight);
			}
		}
	}
    
	public static BinaryTree fibTree(int n) {
	    BinaryTree result = new BinaryTree();
	    result.myRoot = result.fibTreeHelper(n);
	    return result;
	}
	
	private TreeNode fibTreeHelper (int n) {
		if (n == 0) return new TreeNode(0, null, null);
		if (n == 1) return new TreeNode(1, null, null);
		TreeNode result = new TreeNode(n - 1, fibTreeHelper(n-1), fibTreeHelper(n-2));
		result.myItem = (Integer)result.myLeft.myItem + (Integer)result.myRight.myItem;
		return result;
	}
	
	
	public static BinaryTree exprTree(String s) {
	    BinaryTree result = new BinaryTree();
	    result.myRoot = result.exprTreeHelper(s);
	    return result;
	}
	
    
//  print(t, "the empty tree");
//  t.fillSampleTree1();
//  print(t, "sample tree 1");
//  System.out.println(t.check());
//  t.fillSampleTree2();
//  print(t, "sample tree 2");
//  System.out.println(t.check());
//  t.fillSampleTree3();
//  print(t, "sample tree 3");
//  System.out.println("height : " + t.height());
//  System.out.println("isbalanced : " + t.isCompletelyBalanced());
//  t.print();
//  System.out.println(t.check());
	public static boolean isNumeric(String str) {  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	
    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
//        t = fibTree(7);
        t = exprTree("((a+(5*(9+1)))+(6*5))");
        t.optimize();
        t.print();
    }
    
    public void optimize() {
    	if (myRoot == null) {
    		return;
    	}
    	if (myRoot.myLeft == null && myRoot.myRight == null) {
    		return;
    	}
    	if ((myRoot.myItem.equals("*") || myRoot.myItem.equals("+"))
    			&& isNumeric((String)myRoot.myLeft.myItem)
    			&&isNumeric((String)myRoot.myRight.myItem)){
    			if (myRoot.myItem.equals("+")) {
    				myRoot.myItem =""+ Integer.parseInt((String) myRoot.myLeft.myItem) + Integer.parseInt((String) myRoot.myRight.myItem);
    				myRoot.myLeft = null;
    				myRoot.myRight = null;
    			}
    			if (myRoot.myItem.equals("*")) {
    				myRoot.myItem =""+ Integer.parseInt((String) myRoot.myLeft.myItem) + Integer.parseInt((String) myRoot.myRight.myItem);
    				myRoot.myLeft = null;
    				myRoot.myRight = null;
    			}
    			
    	} else {
    		if (myRoot.myLeft != null) {
    			myRoot.myLeft.optimizeHelper();
    		}
    		if (myRoot.myRight != null) {
    			myRoot.myRight.optimizeHelper();
    		}
    	}
    	int i = this.height();
    	while (i >= 0) {
    		myRoot.optimizeHelper();
    		i--;
    	}
    }
	// Return the tree corresponding to the given arithmetic expression.
	// The expression is legal, fully parenthesized, contains no blanks, 
	// and involves only the operations + and *.
	private TreeNode exprTreeHelper(String expr) {
	    if (expr.charAt(0) != '(') {
	    	return new TreeNode(Character.toString(expr.charAt(0)),  null, null);
	    } else {
	        // expr is a parenthesized expression.
	        // Strip off the beginning and ending parentheses,
	        // find the main operator (an occurrence of + or * not nested
	        // in parentheses, and construct the two subtrees.
	    	// t = exprTree("(5*(a+b))");
	        int nesting = 0;
	        int temp = 0;
	        int opPos = 0;
	        boolean b = false;
	        boolean one = false;
	        for (int k = 1; k < expr.length() - 1; k++) {
	            // you supply the missing code
	        	//( () + () )
	        	if ((expr.charAt(k) == '*' || expr.charAt(k) == '+') 
	        			&& expr.charAt(k+1) =='(' 
	        			&& expr.charAt(k-1) ==')') {
	        		temp = k;
	        	}
	        	// (a + ())
	        	if ((expr.charAt(k) == '*' || expr.charAt(k) == '+')
	        			&& !b) {
	        		temp = k;
	        	}
	        	// (() +a)
	        	if ((expr.charAt(k) == '*' || expr.charAt(k) == '+') 
	        			&& nesting == 0 && b && expr.length() - k < 5) {
	        		temp = k;
	        		if (nesting == 0 && temp != 0) {
	        			return new TreeNode(Character.toString(expr.charAt(temp)),
		        				exprTreeHelper(expr.substring(1,temp)),
		        				exprTreeHelper(expr.substring(temp+1, expr.length() - 1)));
	        		}
	        		System.out.println("temp : " +temp);
	        	}
	        	
	        	if (expr.charAt(k) == '(') {
	        		nesting ++;
	        		b = true;
	        	}
	        	if (expr.charAt(k) == ')') {
	        		nesting --;
	        		if (nesting == 0 && temp != 0) {
	        			return new TreeNode(Character.toString(expr.charAt(temp)),
		        				exprTreeHelper(expr.substring(1,temp)),
		        				exprTreeHelper(expr.substring(temp+1, expr.length() - 1)));
	        		}
	        	}
	        	if (expr.charAt(k) == '*' || expr.charAt(k) == '+') {
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
	        return new TreeNode(op, new TreeNode(opnd1), new TreeNode(opnd2)); // you fill this in
	    }
	}

    // Contains nodes already seen in the traversal. 
    private ArrayList alreadySeen; 

    private static class TreeNode {

        public Object myItem;
        public TreeNode myLeft;
        public TreeNode myRight;
        private static final String indent1 = "    ";
       
        private void print(int indent) {
            // TODO your code here
        	if (myRight != null) {
        		myRight.print(indent+1);
        	}
            println (myItem, indent);
            // TODO your code here
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
        
        public int heightOfBinaryTree(TreeNode tree) {
            if (tree == null) {
                return 0;
            }
            else {
                return 1 +
                Math.max(heightOfBinaryTree(tree.myLeft),
                    heightOfBinaryTree(tree.myRight));
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
        
        
        public void optimizeHelper() {
        	if (myItem == null) {
        		return;
        	}
        	if (myLeft == null && myRight == null) {
        		return;
        	}
        	if ((myItem.equals("*") || myItem.equals("+"))
        			&& isNumeric((String)myLeft.myItem)
        			&&isNumeric((String)myRight.myItem)){
        			if (myItem.equals("+")) {
        				int myItemValue = Integer.parseInt((String) myLeft.myItem) + Integer.parseInt((String) myRight.myItem);
        				myItem = myItemValue + "";
        				myLeft = null;
        				myRight = null;
        			}
        			if (myItem.equals("*")) {
        				int myItemValue = Integer.parseInt((String) myLeft.myItem) * Integer.parseInt((String) myRight.myItem);
        				myItem = myItemValue + "";
        				myLeft = null;
        				myRight = null;
        			}
        	} else {
        		if (myLeft != null) {
        			myLeft.optimizeHelper();
        		}
        		if (myRight != null) {
        			myRight.optimizeHelper();
        		}
        	}
        	
        }
    }
}
