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
    	if (t == null) return;
    	if (alreadySeen.contains(t.myItem)) throw new IllegalStateException("This item already exists!");
    	else {
    		alreadySeen.add(t.myItem);
    		isOK(t.myLeft);
    		isOK(t.myRight);
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

    public int height() {
    	if (myRoot != null) return myRoot.height();
    	return 0;
    }
    
    public Boolean isCompletelyBalanced() {
    	if (myRoot == null) return true;
    	return myRoot.isCompletelyBalanced();
    }
    
    public void fillSampleTree1() {
        myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
    }

    public void fillSampleTree2() {
        myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d",
                new TreeNode("e"), new TreeNode("f")), null), new TreeNode("c"));
    }

    public void fillSampleTree3() {
    	myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c", new 
    			TreeNode("d", new TreeNode ("e"), new TreeNode("f")), null));
    }
    
    public void fillSampleTree4() {
    	myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c", new TreeNode("d"), new TreeNode("e")));
    }
    
    public void fillSampleTree5() {
    	myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("f"), new TreeNode("g")), new TreeNode("c", new TreeNode("d"), new TreeNode("e")));
    }
    
    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
    }
    
    private TreeNode fibTreeHelper (int n) {
    	if (n == 0) return new TreeNode("0");
    	else if (n == 1) return new TreeNode("1");
    	else {
    		TreeNode right = fibTreeHelper(n-2);
    		TreeNode left = fibTreeHelper(n-1);
    		String leftString = (String)left.myItem;
    		int leftNumber = Integer.parseInt(leftString);
    		String rightString = (String)right.myItem;
    		int rightNumber = Integer.parseInt(rightString);
    		int newNumber = leftNumber + rightNumber;
    		String item = ((Integer)newNumber).toString();
    		return new TreeNode(item, left, right);
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
            return new TreeNode(expr);
        } else {
            // expr is a parenthesized expression.
            // Strip off the beginning and ending parentheses,
            // find the main operator (an occurrence of + or * not nested
            // in parentheses, and construct the two subtrees.
            int nesting = 0;
            int opPos = 0;
            for (int k = 1; k < expr.length() - 1; k++) {
                if (expr.charAt(k) == '(') nesting++;
                else if (expr.charAt(k) == ')') nesting--;
                
                if (nesting == 0 && (expr.charAt(k) == '*' || expr.charAt(k) == '+')) {
                	opPos = k;
                	break;
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
            TreeNode a = exprTreeHelper(opnd1);
            TreeNode b = exprTreeHelper(opnd2);
            return new TreeNode(op, a, b);
        }
    }
    
    public void optimize() {
    	if (myRoot != null) optimizeHelper(myRoot);
    }
    
    private static void optimizeHelper(TreeNode a) {
    	if (a.myLeft == null) return; // all have either 0 or 2 children
		optimizeHelper(a.myLeft);
		optimizeHelper(a.myRight);
		try {
			int leftString = Integer.parseInt((String) a.myLeft.myItem);
			int rightString = Integer.parseInt((String) a.myRight.myItem);
			if (a.myItem.equals("*")) a.myItem = leftString * rightString;
			else a.myItem = leftString + rightString;
			a.myItem = a.myItem.toString();
			a.myLeft = a.myRight = null;
		} catch (NumberFormatException e){ 
			// this is because the first 2 lines in the try might result in an exception if the myItem is not a number (i.e a)
		}
	}

	public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        print(t, "the empty tree");
        System.out.println(t.check());
        t.fillSampleTree1();
        print(t, "sample tree 1");
        t.print();
        System.out.println(t.check());
        t.fillSampleTree2();
        print(t, "sample tree 2");
        t.print();
        System.out.println(t.check());
        t.fillSampleTree3();
        print(t, "sample tree 3");
        t.print();
        System.out.println(t.check());
        t.fillSampleTree4();
        print(t, "sample tree 4");
        t.print();
        System.out.println(t.check());
        t.fillSampleTree5();
        print(t, "sample tree 5");
        t.print();
        System.out.println(t.check());
    	
    	fibTree(0).print();
    	System.out.println();
    	fibTree(1).print();
    	System.out.println();
    	fibTree(2).print();
    	System.out.println();
    	fibTree(3).print();
    	System.out.println();
    	fibTree(4).print();
    	System.out.println();
    	fibTree(5).print();
    	System.out.println();
    	
    	BinaryTree alpha = exprTree("((a+(5*(9+1)))+(6*5))");
    	alpha.optimize();
    	alpha.print();
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
        	if (this.myLeft == null) {
        		if (this.myRight == null) {
        			return 1;
        		} else {
        			return 1 + this.myRight.height();
        		}
        	} else {
        		if (this.myRight == null) {
        			return 1 + this.myLeft.height();
        		} else {
        			return 1 + Math.max(this.myLeft.height(), this.myRight.height());
        		}
        	} 
        }
        
        public boolean isCompletelyBalanced() {
        	if (this.myLeft == null ^ this.myRight == null) return false;
        	if (this.myLeft == null && this.myRight == null) return true;
        	if (this.myLeft.height() != this.myRight.height()) return false;
        	else return (this.myLeft.isCompletelyBalanced() && this.myRight.isCompletelyBalanced());
        }
        
        private static final String indent1 = "    ";

        private void print(int indent) {
            if(myRight  != null) this.myRight.print(indent+1);
            println (myItem, indent);
            if(myLeft != null) this.myLeft.print(indent+1);
        }

        private static void println(Object obj, int indent) {
            for (int k=0; k<indent; k++) {
                System.out.print(indent1);
            }
            System.out.println(obj);
        }
    }
}
