//import AmoebaFamily.Amoeba;
import java.util.ArrayList;

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

    public void print() {
        if (myRoot != null) {
            myRoot.print(0);
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

    public int height() {
    	if (myRoot == null) {
            return 0;
        }
        return myRoot.height();
    }

    public int size() {
    	if (myRoot == null) {
    		return 0;
    	}
    	return myRoot.size();
    }

    public boolean isCompletelyBalanced() {
    	if (myRoot == null) {
            return true;
        }
        return myRoot.isBalanced();
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

    private ArrayList alreadySeen;

    private boolean isOK(TreeNode t) throws IllegalStateException {
        if (t == null) {
            return true;
        }
    	if (alreadySeen.contains(t.myItem)) {
    		throw new IllegalStateException();
    	}
    	alreadySeen.add(t.myItem);
        return isOK(t.myLeft) && isOK(t.myRight);
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
    	}
    	else {
    		TreeNode tree = new TreeNode(fib(n),fibTreeHelper(n-1),fibTreeHelper(n-2));
    		return tree;
    	}
    }

    private int fib(int n) {
    	if (n == 0) {
    		return 0;
    	}
    	else if (n == 1) {
    		return 1;
    	}
    	else {
    		return fib(n-2) + fib(n-1);
    	}
    }

    public static BinaryTree exprTree(String s) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.exprTreeHelper(s);
        return result;
    }

    public void optimize() {
    	myRoot = evalFunction(myRoot);
    }

    private TreeNode evalFunction(TreeNode t) {
        if (t == null) {
            return t;
        }
        if (t.myItem.equals("*")) {
            t = multiplyNodes(t, t.myLeft, t.myRight);
        }
        if (t.myItem.equals("+")) {
            t = addNodes(t, t.myLeft, t.myRight);
        }
        t.myLeft = evalFunction(t.myLeft);
        t.myRight = evalFunction(t.myRight);
        return t;
    }

    private TreeNode addNodes (TreeNode parent, TreeNode leftNode,
                TreeNode rightNode) throws NumberFormatException {
        leftNode = evalFunction(leftNode);
        rightNode = evalFunction(rightNode);
        try {
            int left = Integer.parseInt(leftNode.myItem.toString());
            int right = Integer.parseInt(rightNode.myItem.toString());
            parent.myItem = left + right;
            parent.myLeft = parent.myRight = null;
        } catch (NumberFormatException e) {}
        return parent;
    }

    private TreeNode multiplyNodes(TreeNode parent, TreeNode leftNode,
                TreeNode rightNode) throws NumberFormatException {
        leftNode = evalFunction(leftNode);
        rightNode = evalFunction(rightNode);
        try {
            int left = Integer.parseInt(leftNode.myItem.toString());
            int right = Integer.parseInt(rightNode.myItem.toString());
            parent.myItem = left * right;
            parent.myLeft = parent.myRight = null;
        } catch (NumberFormatException e) {}
        return parent;
    }

    // Return the tree corresponding to the given arithmetic expression.
    // The expression is legal, fully parenthesized, contains no blanks,
    // and involves only the operations + and *.
    private TreeNode exprTreeHelper(String expr) {
        if (expr.charAt(0) != '(') {
            return null; // you fill this in
        }
        else {
            // expr is a parenthesized expression.
            // Strip off the beginning and ending parentheses,
            // find the main operator (an occurrence of + or * not nested
            // in parentheses, and construct the two subtrees.
            int nesting = 0;
            int opPos = 0;
            boolean hasBeenSet = false;
            boolean hasOperator = false;
            for (int k = 1; k < expr.length() - 1; k++) {
                if (expr.charAt(k-1) == ')' && expr.charAt(k+1) == '(' && (expr.charAt(k) == '+' || expr.charAt(k) == '*')) {
                	opPos = k;
                	hasOperator = true;
                }
                if ((expr.charAt(k) == '+' || expr.charAt(k) == '*') && !hasBeenSet) {
                	nesting = k;
                	hasBeenSet = true;
                	hasOperator = true;
                }
            }
            if (opPos == 0) {
            	opPos = nesting;
            }
            if (!hasOperator) {
            	return null;
            }
            String opnd1 = expr.substring(1, opPos);
            String opnd2 = expr.substring(opPos + 1, expr.length() - 1);
            String op = expr.substring(opPos, opPos + 1);
            System.out.println("expression = " + expr);
            System.out.println("operand 1  = " + opnd1);
            System.out.println("operator   = " + op);
            System.out.println("operand 2  = " + opnd2);
            System.out.println();
            if (exprTreeHelper(opnd1) == null && exprTreeHelper(opnd2) == null) {
            	return new TreeNode(op, new TreeNode(opnd1), new TreeNode(opnd2));
            }
            else if (exprTreeHelper(opnd1) == null) {
            	return new TreeNode(op, new TreeNode(opnd1), exprTreeHelper(opnd2));
            }
            else if (exprTreeHelper(opnd2) == null) {
            	return new TreeNode(op, exprTreeHelper(opnd1), new TreeNode(opnd2));
            }
            return new TreeNode(op, exprTreeHelper(opnd1), exprTreeHelper(opnd2));
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

    public static void main(String[] args) {
//        BinaryTree t;
//        t = new BinaryTree();
//        print(t, "the empty tree");
//        t.fillSampleTree1();
//        print(t, "sample tree 1");
//        t.fillSampleTree2();
//        print(t, "sample tree 2");
//        t.fillSampleTree3();
//        print(t, "sample tree 3");
//
        BinaryTree tree = new BinaryTree(new TreeNode("A", new TreeNode("B"), new TreeNode("C", new TreeNode("D"), new TreeNode("E"))));
        BinaryTree badTree = new BinaryTree(new TreeNode("A", new TreeNode("B"), new TreeNode("C", new TreeNode("C"), new TreeNode("E"))));
        BinaryTree toExpr = BinaryTree.exprTree("((a+(5*(9+1)))+(6*5))");
        toExpr.print();
        System.out.println(tree.check());
        System.out.println(badTree.check());
        toExpr.optimize();
        toExpr.print();

    }

    private static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
    }

    public static class TreeNode {

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
        	int left = 0;
        	int right = 0;
        	if (myLeft != null) {
        		left = myLeft.height();
        	}
        	if (myRight != null) {
        		right = myRight.height();
        	}
        	return Math.max(left, right) + 1;
        }

        public int size() {
        	if (myLeft == null && myRight == null) {
        		return 0;
        	}
        	else if (myLeft == null) {
        		return 1 + myRight.size();
        	}
        	else if (myRight == null) {
        		return 1 + myLeft.size();
        	}
        	else {
        		return (2 + myLeft.size() + myRight.size());
        	}
        }

        public boolean isBalanced() {
        	if (myLeft == null) {
        		if (myRight == null) {
        			return true;
        		}
        		return false;
        	}
        	if (myRight == null) {
        		return false;
        	}
        	return myLeft.isBalanced() && myRight.isBalanced();
        }

        private static final String indent1 = "    ";

        private void print(int indent) {
        	if (myRight != null) {
        		myRight.print(indent+1);
            }
        	println(myItem, indent);
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
