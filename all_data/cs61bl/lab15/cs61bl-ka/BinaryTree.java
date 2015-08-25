import java.util.*;

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

    public void fillSampleTree1() {
        myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
    }

    public void fillSampleTree2() {
        myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d",
                new TreeNode("e"), new TreeNode("f")), null), new TreeNode("c"));
    }
    
    public void fillSampleTree3() {
    	myRoot = new TreeNode("a", new TreeNode("b"), 
    			new TreeNode("c", new TreeNode("d", new TreeNode("e"), new TreeNode("f")), null));
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

    public static void main(String[] args) {
        BinaryTree t = new BinaryTree();
//        t = new BinaryTree();
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
//        	t.fillSampleTree3();
//        	t.print();
//        	System.out.println(t.check());
//        	TreeNode N3 = new TreeNode("3");
//        	TreeNode N2 = new TreeNode("2", N3, null);
//        	TreeNode N1 = new TreeNode("1", N2, N3);
//        	BinaryTree t1 = new BinaryTree(N1);
//        	t1.print();
//        	System.out.println(t1.check());  
        BinaryTree t1 = BinaryTree.fibTree(5);
        t1.print();
    }

    private static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
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
    	if (t != null) {
    		if (alreadySeen.contains(t)) {
    			throw new IllegalStateException("Node in tree multiple times");
    		}
    		alreadySeen.add(t);
    		if (t.myLeft != null) {
    			isOK(t.myLeft);
    		}
    		if (t.myRight != null) {
    			isOK(t.myRight);
    		}
    	}
//    	for (Object t1 : alreadySeen) {
//    		alreadySeen.remove(t1);
//    		for (Object t2 : alreadySeen) {
//    			if (t1 == t2) {
//    				throw new IllegalStateException("Node in tree multiple times");
//    			}
//    		}
//    	}
    }

    // Contains nodes already seen in the traversal. 
    private ArrayList alreadySeen; 
    
    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
    }

    private TreeNode fibTreeHelper (int n) {
    	if (n==0 || n==1) {
    		return new TreeNode(n);
    	}
    	else {
    		return new TreeNode(fibCalc(n), fibTreeHelper(n-1), fibTreeHelper(n-2));
    	}
    }
    
    private int fibCalc(int n) {
    	if (n == 0 || n==1) {
    		return n;
    	}
    	return fibCalc(n-1) + fibCalc(n-2);
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
            return TreeNode // you fill this in
        } else {
            // expr is a parenthesized expression.
            // Strip off the beginning and ending parentheses,
            // find the main operator (an occurrence of + or * not nested
            // in parentheses, and construct the two subtrees.
            int nesting = 0;
            int opPos = 0;
            for (int k = 1; k < expr.length() - 1; k++) {
                // you supply the missing code
            }
            String opnd1 = expr.substring(1, opPos);
            String opnd2 = expr.substring(opPos + 1, expr.length() - 1);
            String op = expr.substring(opPos, opPos + 1);
            System.out.println("expression = " + expr);
            System.out.println("operand 1  = " + opnd1);
            System.out.println("operator   = " + op);
            System.out.println("operand 2  = " + opnd2);
            System.out.println();
            ____; // you fill this in
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
        
        public int height() {
        	if (myLeft == null && myRight == null) {
        		return 1;
        	}
        	else if (myLeft != null && myRight == null) {
        		return 1 + myLeft.height();
        	}
        	else if (myLeft == null && myRight != null) {
        		return 1 + myRight.height();
        	}
        	return 1 + Math.max(myLeft.height(), myRight.height());
        	
        }
        
        public boolean isCompletelyBalanced() {
        	if (myLeft == null && myRight == null) {
        		return true;
        	}
        	else if (myLeft != null && myRight == null) {
        		return false;
        	}
        	else if (myLeft == null && myRight != null) {
        		return false;
        	}
        	return myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced();
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
        
//        private void checkMethod() throws IllegalStateException {
//        	alreadySeen = new ArrayList();
//        	
//        }
    }
    
}
