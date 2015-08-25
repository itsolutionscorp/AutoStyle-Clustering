import java.util.ArrayList;
import java.util.Stack;

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
    	if (myRoot == null) {
    		return true;
    	} 
    	return myRoot.isCompletelyBalanced();
    }

    private ArrayList alreadySeen;
    
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
    	}
    	
    	alreadySeen.add(t);
 
    	if (t.myLeft != null) {
    		isOK(t.myLeft);
    	} if (t.myRight != null) {
    		isOK(t.myRight);
    	}
    }
    
    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
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
            	if (expr.charAt(k) == '(') {
            		nesting++;
            	} else if (expr.charAt(k) == ')') {
            		nesting--;
            	}
            	if (nesting == 0 && (expr.charAt(k) == '+' || expr.charAt(k) == '*')) {
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
            
            return new TreeNode(op, exprTreeHelper(opnd1), exprTreeHelper(opnd2));
            
        }
    }
    
    public void optimize() {
    	optimizeHelper(myRoot);
    }
    
    public void optimizeHelper(TreeNode t) {
    	
    	String operator = (String) t.myItem;
        if (t.myLeft != null && t.myRight != null) {
        	optimizeHelper(t.myLeft);
        	optimizeHelper(t.myRight);
        	try {
        		int i = Integer.parseInt((String)(t.myLeft.myItem));
        		int j = Integer.parseInt((String)(t.myRight.myItem));
        		if (operator.equals("*")) {
        			t.myItem = Integer.toString(i*j);
        			t.myLeft = null;
        			t.myRight = null;
        		} else {
        			t.myItem = Integer.toString(i+j);
        			t.myLeft = null;
        			t.myRight = null;
        		}
        	} catch (NumberFormatException e) {
        		
        	}
        }
    }
    
    
    
    private TreeNode fibTreeHelper (int n) {
    	if (n == 0) {
    		return new TreeNode(0);
    	} else if (n == 1) {
    		return new TreeNode(1);
    	} else {
    		return new TreeNode(fib(n), fibTreeHelper(n-1), fibTreeHelper(n-2));
    	}
    }
    
    private int fib(int n) {
    	if (n == 0) {
    		return 0;
    	} else if (n == 1) {
    		return 1;
    	} else {
    		return fib(n-1) + fib(n-2);
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
    	myRoot = new TreeNode("A", new TreeNode("B"), new TreeNode("C", 
    			new TreeNode("D", new TreeNode("E"), new TreeNode("F")), null));
    }
    
    public void fillSampleTree4() {
        myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d",
                new TreeNode("e"), new TreeNode("f", new TreeNode("g", 
                new TreeNode("j"), null),null)), null), new TreeNode("c"));
    }
    
    public void filllab15() {
    	myRoot = new TreeNode("A", new TreeNode("B"), new TreeNode("C",
    			new TreeNode("D"), new TreeNode("E")));
    }
    public void fillOneTreeNode() {
    	myRoot = new TreeNode("a");
    }

    public void fillBalancedHeight3() {
    	myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d"), new TreeNode("e")), 
    			new TreeNode("c", new TreeNode("f"), new TreeNode("g")));
    }
    
    public static void main(String[] args) {
   
    	BinaryTree t = new BinaryTree();
    	t.filllab15();
        t.print();
        
        System.out.print("\n\n");
        BinaryTree.fibTree(5).print();
        
//    	BinaryTree exp = BinaryTree.exprTree("((a+(5*(a+b)))+(6*5))");
    	BinaryTree exp = BinaryTree.exprTree("((a+(5*(9+10)))+(60*50))");
    	exp.print();
    	exp.optimize();
    	System.out.print("\n\n\n\n\n\n");
    	exp.print();
        
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
        
        private static final String indent1 = "    ";
        private static Stack stack = new Stack();
        public void addElement(int n) {
        	 if (myLeft != null) {
                 myLeft.addElement(n+1);
             }
        	 stack.push(myItem);
        	 stack.push(n);
             if (myRight != null) {
            	 myRight.addElement(n+1);
             }    
        }
        
        private void print(int indent) {
        	addElement(1);
        	int allHeight = height();
        	while (!stack.isEmpty()) {
        		indent = (Integer) stack.pop();
        		Object t =  stack.pop();
        		println(t, indent);
        	}

        }

        private static void println(Object obj, int indent) {
            for (int k=0; k<indent; k++) {
                System.out.print(indent1);
            }
            System.out.println(obj);
        }
        
        private int height() {
        	if (myLeft == null && myRight == null) {
        		return 1;
        	} else {
        		int heightSoFar = 1;
        		if (myLeft != null) {
        			heightSoFar = Math.max(heightSoFar, myLeft.height());
        		}
        		if (myRight != null) {
        			heightSoFar = Math.max(heightSoFar, myRight.height());
        		}
        		return heightSoFar + 1;
        	}
        }
        
        
//        private boolean isCompletelyBalanced() {
//        	if (myLeft == null && myRight == null) {
//        		return true;
//        	} else if (myLeft == null && myRight != null){
//        		return false;
//        	} else if (myLeft != null && myRight == null) {
//        		return false;
//        	} else {
//        		return (myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced());
//        	}    
//        }
        
        private boolean isCompletelyBalanced() {
        	if (myLeft == null && myRight == null) {
        		return true;
        	} else if (myLeft.height() != myRight.height()){
        		return false;
        	} else {
        		return (myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced());
        	}
        }
        
  
        
    }
}
