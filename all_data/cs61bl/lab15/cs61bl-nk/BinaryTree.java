import java.util.ArrayList;

public class BinaryTree {

    private TreeNode myRoot;
    private ArrayList alreadySeen; 

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
        myRoot = new TreeNode("a", new TreeNode("a", new TreeNode("d",
                new TreeNode("e"), new TreeNode("f")), null), new TreeNode("c"));
    }
    
    public void fillSampleTree3() {
        myRoot = new TreeNode("a", new TreeNode("b"), 
        						   new TreeNode("c", new TreeNode("d", 
        								   						  new TreeNode("e"), new TreeNode("f")), null));
    }
    public void fillSampleTree4() {
        myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d"), new TreeNode("e")), 
        						   new TreeNode("c", new TreeNode("f"), new TreeNode("g")));
    }
    public void fillSampleTree5() {
        myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("a"), null), 
        						   new TreeNode("c", new TreeNode("g"), new TreeNode("h")));
    }
    public int height() {
    	if (myRoot == null) {
    		return 0;
    	}
    	return myRoot.height(myRoot, 1);
    }
    
    public boolean isCompletelyBalanced() {
    	if (myRoot == null) {
    		return true;
    	} return myRoot.isBalanced(myRoot);
    }
    
    public boolean check() { 
//    (IllegalStateException is provided in Java.)
        alreadySeen = new ArrayList(); 
        try { 
            isOK(myRoot); 
            System.out.println(alreadySeen);
            return true; 
        } catch (IllegalStateException e) { 
        	System.out.println(alreadySeen); 
            return false; 
        }
        
    }
 
    private void isOK(TreeNode t) throws IllegalStateException {
        	if (t == null) {
        		return;
        	} if (alreadySeen.contains(t.myItem)) {
        		throw new IllegalStateException();
        	} alreadySeen.add(t.myItem);
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

    private TreeNode fibTreeHelper (int n) {
    	if (n == 0) {
    		return new TreeNode(0);
    	} else if (n == 1) {
    		return new TreeNode(1);
    	} else {
    		return new TreeNode(((Integer) (fibTreeHelper(n - 1).myItem)) + ((Integer) (fibTreeHelper(n - 2).myItem)), fibTreeHelper(n - 1), fibTreeHelper(n - 2));
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
            	if ((expr.charAt(k) == '+' || expr.charAt(k) == '*') && nesting == 0) {
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
            return new TreeNode(op, exprTreeHelper(opnd1), exprTreeHelper(opnd2)); // you fill this in
        }
    }
    public void optimize() {
        optimizeHelper(myRoot);
    }
    
    public void optimizeHelper(TreeNode t) {
    	if (t.myLeft != null) {
    		optimizeHelper(t.myLeft);
    	} if (t.myRight != null) {
    		optimizeHelper(t.myRight);
    	} if (t.myItem.equals("+") && t.myLeft != null && t.myRight != null) {
    		if (((String) t.myLeft.myItem).matches("\\d+") && ((String) t.myRight.myItem).matches("\\d+")) {
    			t.myItem = Integer.toString(Integer.parseInt((String) t.myLeft.myItem) + Integer.parseInt((String) t.myRight.myItem));
    			t.myLeft = null;
    			t.myRight = null;
    		}
    	} if (t.myItem.equals("*") && t.myLeft != null && t.myRight != null) {
    		if (((String) t.myLeft.myItem).matches("\\d+") && ((String) t.myRight.myItem).matches("\\d+")) {
    			t.myItem = Integer.toString(Integer.parseInt((String) t.myLeft.myItem) * Integer.parseInt((String) t.myRight.myItem));    			
    			t.myLeft = null;
    			t.myRight = null;
    		}
    	}
    }
   
    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        print(t, "the empty tree");
        System.out.println("empty height: " + t.height());
        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree1();
        print(t, "sample tree 1");
        System.out.println("sample 1 height: " + t.height());
        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree2();
        print(t, "sample tree 2");
        System.out.println("sample 2 height: " + t.height());
        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree3();
        print(t, "sample tree 3");
        System.out.println("sample 3 height: " + t.height());
        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree4();
        print(t, "sample tree 4");
        System.out.println("sample 4 height: " + t.height());
        System.out.println(t.isCompletelyBalanced());
        t.print();
        System.out.println(t.check());
        t.fillSampleTree1();
        t.print();
        System.out.println(t.check());
        t.fillSampleTree2();
        t.print();
        System.out.println(t.check());
        t.fillSampleTree3();
        t.print();
        System.out.println(t.check());
        t.fillSampleTree4();
        t.print();
        System.out.println(t.check());
        t.fillSampleTree5();
        t.print();
        System.out.println(t.check());
        print(t, "sample tree 5");
        System.out.println("sample 5 height: " + t.height());
        System.out.println(t.isCompletelyBalanced());
        fibTree(5).print();
        BinaryTree expression = exprTree("((a+(5*(a+b)))+(6*5))");
        expression.optimize();
        expression.print();
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
        
        private static final String indent1 = "    ";

        private void print(int indent) {
        	indent++;
        	if (myRight != null) {
        		myRight.print(indent);
        	}
        	println (myItem, indent);
        	if (myLeft != null) {
        		myLeft.print(indent);
            }
            
        }

        private static void println(Object obj, int indent) {
            for (int k=0; k<indent; k++) {
                System.out.print(indent1);
            }
            System.out.println(obj);
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
        public int height(TreeNode t, int bestSoFar) {
        	if (t.myLeft == null && t.myRight == null) {
            	return bestSoFar;
            } 
        	else if (t.myLeft == null) {
        		return height(t.myRight, bestSoFar + 1);
        	}
        	else if (t.myRight == null) {
        		return height(t.myLeft, bestSoFar + 1);
        	}
        	else {
        		return Math.max(height(t.myLeft, bestSoFar + 1), height(t.myRight, bestSoFar + 1));
        	}
        }   
        public boolean isBalanced(TreeNode t) {
        	if (t.myLeft == null && t.myRight == null) {
        		return true;
        	} 
        	else if (t.myLeft == null || t.myRight == null) {
        		return false;
        	}
        	return (t.height(t.myRight, 1) == t.height(t.myLeft, 1) && t.isBalanced(t.myLeft) && t.isBalanced(t.myRight));
        }
    }
}
