import java.util.*;

public class BinaryTree {

    private TreeNode myRoot;
    private ArrayList<TreeNode> alreadySeen;

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
    	myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c", new TreeNode("d", 
    			 new TreeNode("e"), new TreeNode("f")), null));
    	
    }
    
    public void fillSampleTree4() {
    	myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("c"), new TreeNode("d")), 
    			new TreeNode("e", new TreeNode("f"), new TreeNode("g")));
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
    
    public boolean check() { 
        alreadySeen = new ArrayList<TreeNode>(); 
        try { 
            isOK(myRoot); 
            return true; 
        } catch (IllegalStateException e) { 
            return false; 
        } 
    }
    
    public void isOK(TreeNode t) throws IllegalStateException {
    	if (t == null) {
    		return;
    	}
    	if (alreadySeen.contains(t)) {
    		throw new IllegalStateException();
    	}
    	alreadySeen.add(t);
    	isOK(t.myLeft);
    	isOK(t.myRight);
    }
    
    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
    }
    
    private TreeNode fibTreeHelper(int n) {
    	if (n == 0 || n == 1) {
    		return new TreeNode (Integer.valueOf(n));
    	} else {
    		TreeNode left = fibTreeHelper(n-1);
    		TreeNode right = fibTreeHelper(n-2);
    		Integer leftValue = (Integer) left.myItem;
    		Integer rightValue = (Integer) right.myItem;
    		int value = leftValue.intValue() + rightValue.intValue();
    		return new TreeNode(Integer.valueOf(value), left, right);
    	}
    }
    
    public static BinaryTree exprTree(String s) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.exprTreeHelper(s);
        return result;
    }
    
    public static void optimize(BinaryTree t) {
    	if (t.myRoot != null) {
    		optimizeHelper(t.myRoot);
    	}
    }
    
    public static boolean optimizeHelper(TreeNode t) {
    	if (t == null) {
    		return false;
    	} else if (!isNumeric((String) t.myItem)) {
    		boolean check1 = optimizeHelper(t.myLeft);
    		boolean check2 = optimizeHelper(t.myRight);
    		if (check1 && check2) {
    			if (t.myItem.equals("+")) {
    				t.myItem = Integer.toString(Integer.parseInt((String) t.myLeft.myItem) + Integer.parseInt((String) t.myRight.myItem));
    			} else {
    				t.myItem = Integer.toString(Integer.parseInt((String) t.myLeft.myItem) * Integer.parseInt((String) t.myRight.myItem));
    			}
    			t.myLeft = null;
    			t.myRight = null;
    			return true;
    		}
    		return false;
    	} else {
    		return true;
    	}
    }
    
    //from  http://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-a-numeric-type-in-java
    public static boolean isNumeric(String str) {
    	for (char c : str.toCharArray()) {
    		if (!Character.isDigit(c)) return false;
    	}
    	return true;
    }
    
    // Return the tree corresponding to the given arithmetic expression.
    // The expression is legal, fully parenthesized, contains no blanks, 
    // and involves only the operations + and *.
    private TreeNode exprTreeHelper(String expr) {
        if (expr.charAt(0) != '(') {
           return new TreeNode(expr.substring(0, 1)); // you fill this in
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
                if (expr.charAt(k) == '+' || expr.charAt(k) == '*') {
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
            return new TreeNode(op, exprTreeHelper(opnd1), exprTreeHelper(opnd2)); // you fill this in
        }
    }

    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        print(t, "the empty tree");
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree1();
        System.out.println(t.check());
        print(t, "sample tree 1");
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.print();
        t.fillSampleTree2();
        System.out.println(t.check());
        print(t, "sample tree 2");
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.print();
        t.fillSampleTree3();
        System.out.println(t.check());
        print(t, "sample tree 3");
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.print();
        t.fillSampleTree4();
        System.out.println(t.check());
        print(t, "sample tree 4");
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.print();
        t = fibTree(7);
        t.print();
        t = exprTree("((a+(5*(a+b)))+(6*5))");
        t.print();
        t = exprTree("((a+(5*(9+1)))+(6*5))");
        t.print();
        optimize(t);
        t.print();
        String check = "a";
        String check2 = "10";
        System.out.println(isNumeric(check));
        System.out.println(isNumeric(check2));
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
        	if (myLeft == null && myRight == null) {
        		return 1;
        	} else if (myLeft == null) {
        		return 1 + myRight.height();
        	} else if (myRight == null) {
        		return 1 + myLeft.height();
        	} else {
        		return 1 + Math.max(myRight.height(), myLeft.height());
        	}
        }
        
        private boolean isCompletelyBalanced() {
        	if (myLeft == null && myRight == null) {
        		return true;
        	} else if (myLeft == null || myRight == null) {
        		return false;
        	} else {
        		return myLeft.height() == myRight.height() && myLeft.isCompletelyBalanced() & myRight.isCompletelyBalanced();
        	}
        }
        
        private void print(int indent) {
            if (myRight != null) {
            	myRight.print(indent+1);
            }
            println (myItem, indent);
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
        
    }
    
}
