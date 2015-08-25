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
    	myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c", new TreeNode("d", new TreeNode("e"), new TreeNode("f")), null));
    }

    public void fillSampleTree4() {
    	myRoot = new TreeNode("a", 
    			new TreeNode ("b", new TreeNode("c"), new TreeNode("d")), 
    			new TreeNode("e", new TreeNode("f"), new TreeNode("g")));
    }
    
    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
//        print(t, "the empty tree");
//        t.fillSampleTree1();
//        System.out.println(t.isCompletelyBalanced());
//        print(t, "sample tree 1");
//        t.fillSampleTree2();
//        System.out.println(t.isCompletelyBalanced());
//        print(t, "sample tree 2");
//        t.fillSampleTree3();
//        System.out.println(t.isCompletelyBalanced());
//        print(t, "sample tree 3");
//        t.fillSampleTree4();
//        System.out.println(t.isCompletelyBalanced());
//        t.myRoot = new TreeNode ("A",
//        		   new TreeNode ("B"),
//        			new TreeNode ("C", new TreeNode("D"), new TreeNode("E"))
//        			);
//        t.print();
        exprTree("(5*(6+10))").print();
        BinaryTree a = new BinaryTree();
        a = exprTree("(a+(5*(6+10)))");
        a.optimize();
        a.print();
    }

    private static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
    }

    public int height() {
    	return myRoot.height();
    }
    
    public boolean isCompletelyBalanced(){
    	return myRoot.isCompletelyBalanced();
    }
    
    public void print() {
        if (myRoot != null) {
            myRoot.print(0);
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
    	} if (n == 1) {
    		return new TreeNode(1);
    	} else {
    		return new TreeNode((int) fibTreeHelper(n-1).myItem + (int) fibTreeHelper(n-2).myItem,
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
	       return new TreeNode(expr.substring(0, 1)); // you fill this in
	    } else {
	        // expr is a parenthesized expression.
	        // Strip off the beginning and ending parentheses,
	        // find the main operator (an occurrence of + or * not nested
	        // in parentheses, and construct the two subtrees.
	        int nesting = 0;
	        int opPos = 0;
	        for (int k = 1; k < expr.length() - 1; k++) {
	            if (expr.charAt(k) == '(') nesting += 1;
	            if (expr.charAt(k) == ')') nesting -= 1;
	            if ((expr.charAt(k) == '*' || expr.charAt(k) == '+') && nesting == 0) {
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
		if (myRoot != null) {
			optimizeHelper(myRoot);
		}
	}
	
	public void optimizeHelper(TreeNode tree) {
		if (tree.myLeft == null && tree.myRight == null) {
			return;
		} try {
			int left = 	Integer.parseInt((String) tree.myLeft.myItem);
			int right = Integer.parseInt((String) tree.myRight.myItem);
			if (tree.myItem.equals("*")) tree.myItem = (left * right) + "";
			else if (tree.myItem.equals("+")) tree.myItem = (left + right) + "";
			tree.myLeft = null;
			tree.myRight = null;
		} catch (NumberFormatException e) {
			optimizeHelper(tree.myLeft);
			optimizeHelper(tree.myRight);
			try { 
				int left = 	Integer.parseInt((String) tree.myLeft.myItem);
				int right = Integer.parseInt((String) tree.myRight.myItem);
				if (tree.myItem.equals("*")) tree.myItem = (left * right) + "";
				else if (tree.myItem.equals("+")) tree.myItem = (left + right) + "";
				tree.myLeft = null;
				tree.myRight = null;
			} catch (NumberFormatException f) {
				return;
			}
		}
	}
	
    private static class TreeNode {

        public Object myItem;
        public TreeNode myLeft;
        public TreeNode myRight;
        public ArrayList<TreeNode> holders;
        
        public TreeNode(Object obj) {
            myItem = obj;
            myLeft = myRight = null;
        }

        public TreeNode(Object obj, TreeNode left, TreeNode right) {
            myItem = obj;
            myLeft = left;
            myRight = right;
        }

        private int height(){
        	if (myItem == null) {
        		return 0;
        	} else if (myLeft == null && myRight == null) {
        		return 1;
        	} else if (myLeft == null) {
        		return 1 + myRight.height();
        	} else if (myRight == null) {
        		return 1 + myLeft.height();
        	}else {
        		return 1 + Math.max(myLeft.height(), myRight.height());
        	}
        }
        
        private boolean isCompletelyBalanced(){
        	if (myItem == null || (myLeft == null && myRight == null)) {
        		return true;
        	} else if (myLeft == null || myRight == null) {
        		return false;
        	} else {
        		return (myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced());
        	}
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

        private void print(int indent) {
            if (myRight != null) myRight.print(indent + 1);
            println (myItem, indent);
            if (myLeft != null) myLeft.print(indent + 1);
        }

        private static void println(Object obj, int indent) {
            for (int k=0; k<indent; k++) {
                System.out.print(indent1);
            }
            System.out.println(obj);
        }
        
        private void isOK(TreeNode t) throws IllegalStateException{
        	holders = new ArrayList<TreeNode>();
        	if (holders.contains(this)) {
        		throw new IllegalStateException ("This node has already appearded");
        	} else {
        		if (myLeft != null) isOK(myLeft);
        		if (myRight != null) isOK(myRight);
        	}
        }
    }
    
}
