import java.util.ArrayList;

//import AmoebaFamily.Amoeba;

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
            	else if (expr.charAt(k) == ')') {
            		nesting--;
            	}
            	else if (expr.charAt(k) == '+' || expr.charAt(k) == '*') {
            		if (nesting == 0) {
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
            TreeNode myTree = new TreeNode(op, exprTreeHelper(opnd1), exprTreeHelper(opnd2));
            return myTree;// you fill this in
        }
    }
    
    public void optimize() {
    	this.simplify(myRoot);
    }
    
    public void simplify(TreeNode t) {
    	if (t.myLeft != null) {
    		simplify(t.myLeft);
    	}
    	if (t.myRight != null) {
    		simplify(t.myRight);
    	}
    	if (t.myItem.equals("+") && t.myLeft != null && t.myRight != null) {
    		if (((String) t.myLeft.myItem).matches("\\d+") && ((String) t.myRight.myItem).matches("\\d+")) {
    			t.myItem = Integer.toString(Integer.parseInt((String) t.myLeft.myItem) + Integer.parseInt((String) t.myRight.myItem));
    			t.myLeft = null;
    			t.myRight = null;
    		}
    	}
    	else if (t.myItem.equals("*") && t.myLeft != null && t.myRight != null) {
    		if (((String) t.myLeft.myItem).matches("\\d+") && ((String) t.myRight.myItem).matches("\\d+")) {
    			t.myItem = Integer.toString(Integer.parseInt((String) t.myLeft.myItem) * Integer.parseInt((String) t.myRight.myItem));
    			t.myLeft = null;
    			t.myRight = null;
    		}
    	}
    }
    
    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
    }

    private TreeNode fibTreeHelper (int n) {
    	TreeNode fibNode;
    	if (n == 0) {
    		fibNode = new TreeNode(0);
    		return fibNode;
    	}
    	else if (n == 1) {
    		fibNode = new TreeNode(1);
    		return fibNode;
    	}
    	else {
    		fibNode = new TreeNode((Integer)(fibTreeHelper(n-1).myItem)+((Integer)fibTreeHelper(n-2).myItem), fibTreeHelper(n-1), fibTreeHelper(n-2));
    		return fibNode;
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
    	if (t != null) {
    		if (alreadySeen.contains(t.myItem)) {
        		throw new IllegalStateException("invalid binary tree");
        	}
        	else if (t.myLeft != null) {
        		alreadySeen.add(t.myItem);
        		isOK(t.myLeft);
        	}
        	else if (t.myRight != null) {
        		alreadySeen.add(t.myItem);
        		isOK(t.myRight);
        	}
        }
    }
    
    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        print(t, "the empty tree");
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree1();
        print(t, "sample tree 1");
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree2();
        print(t, "sample tree 2");
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree3();
        print(t, "sample tree 3");
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        
        t.check();
        
        BinaryTree fibT = (fibTree(4));
        fibT.print();
        
        BinaryTree testTree = exprTree("((a+(5*(a+b)))+(6*5))");
        testTree.print();
        
        testTree.optimize();
        testTree.print();
        
        BinaryTree testTree2 = exprTree("((a+(5*(9+1)))+(6*5))");
        testTree2.optimize();
        testTree2.print();
    }

    private static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
    }

    private class TreeNode {

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
            // TODO your code here
        	indent++;
            if (myRight != null) {
                myRight.print(indent);
            }
            println (myItem, indent);
            // TODO your code here
            if (myLeft != null) {
                myLeft.print(indent);
            }
        }

        private void println(Object obj, int indent) {
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
       
