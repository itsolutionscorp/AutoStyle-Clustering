import java.util.ArrayList;

public class BinaryTree {

    private TreeNode myRoot;
    private ArrayList<Object> alreadySeen;
//    // Contains nodes already seen in the traversal. 
//    private ArrayList alreadySeen;

    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
    }
    
    public boolean check() { 
        alreadySeen = new ArrayList<Object>(); 
        fillNodeTree(myRoot);
        try { 
            isOK(myRoot); 
            return true; 
        } catch (IllegalStateException e) { 
            return false; 
        } 
    }
    
    private void isOK(TreeNode t) throws IllegalStateException {
    	for (int i = 0; i < alreadySeen.size(); i++) {
    		Object temp = alreadySeen.get(i);
    		for (int j = 0; j < alreadySeen.size(); j++) {
    			if (temp.equals(alreadySeen.get(j)) && j != i){
    				throw new IllegalStateException ("more than one node with same object");
    			}
    		}
    	}
    }
    
    public void fillNodeTree(TreeNode a) {
    	if (myRoot != null) {
    		alreadySeen.add(a.myItem);
    		if (a.myLeft != null) {
    			fillNodeTree (a.myLeft);
    		}
    		if (a.myRight != null) {
    			fillNodeTree (a.myRight);
    		}
    	}
    }

	public static BinaryTree fibTree(int n) {
		BinaryTree result = new BinaryTree();
		result.myRoot = result.fibTreeHelper(n);
		return result;
	}

	private TreeNode fibTreeHelper (int n) {
		if (n == 0) {
			TreeNode fib0 = new TreeNode (0);
			return fib0;
		} else if (n == 1) {
			TreeNode fib1 = new TreeNode (1);
			return fib1;
		} else {
			TreeNode a = new TreeNode (0);
			a.myLeft = fibTreeHelper (n - 1);
			a.myRight = fibTreeHelper (n - 2);
			a.myItem = (int)a.myLeft.myItem + (int)a.myRight.myItem;
			return a;
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
    	myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode ("c", new TreeNode ("d", new TreeNode("e"), new TreeNode ("f")), null));
    }
    
    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        print(t, "the empty tree");
        t.fillSampleTree1();
        System.out.println (t.height());
        System.out.println (t.isCompletelyBalanced());
        print(t, "sample tree 1");
        t.fillSampleTree2();
        print(t, "sample tree 2");
        t.fillSampleTree3();
        print(t, "sample tree 3");
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

    public int height() {
    	if (myRoot != null) {
    		return TreeNode.height(myRoot);
    	}
    	return 0;
    }
    
    public boolean isCompletelyBalanced (){
    	if (myRoot == null) {
    		return true;
    	} else {
    		return TreeNode.isCompletelyBalanced(myRoot);
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
            return new TreeNode (expr); // you fill this in
        } else {
            // expr is a parenthesized expression.
            // Strip off the beginning and ending parentheses,
            // find the main operator (an occurrence of + or * not nested
            // in parentheses, and construct the two subtrees.
            int nesting = 0;
            int opPos = 0;
            for (int k = 1; k < expr.length() - 1; k++) {
                if (expr.charAt(k) == '(') {
                	nesting += 1;
                } else if (expr.charAt(k) == ')') {
                	nesting -= 1;
                }
                 
                if (nesting == 0 && (expr.charAt(k) == '*' || expr.charAt(k) == '+')) {
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
            
            TreeNode a = new TreeNode(op);
            a.myLeft = exprTreeHelper(opnd1);
            a.myRight = exprTreeHelper (opnd2);
            return a;
        }
    }
    
    public void optimize () {
    	if (myRoot != null) {
    		myRoot = optimizeHelper(myRoot);
    	}
    }
    
    public static TreeNode optimizeHelper (TreeNode a) {
    	Object sign = a.myItem;
    	TreeNode left = a.myLeft;
    	TreeNode right = a.myRight;
    	if (sign.equals("*")) {
    		a = multiplyBranches(optimizeHelper(left), optimizeHelper(right));
    	} else if (sign.equals("+")) {
    		a = addBranches(optimizeHelper(left), optimizeHelper(right));
    	}
    	return a;
    }
    
    public static TreeNode addBranches (TreeNode left, TreeNode right) {
    	TreeNode tree = new TreeNode (null);
    	try {
    		int leftVal = Integer.parseInt(left.toString());
    		int rightVal = Integer.parseInt(right.toString());
    		Integer sum = leftVal + rightVal;
    		tree.myItem = "" + sum;
    	} catch (NumberFormatException e) {
    		tree.myItem = "+";
    		tree.myLeft = left;
    		tree.myRight = right;
    	}
    	return tree;
    }
    
    public static TreeNode multiplyBranches (TreeNode left, TreeNode right) {
    	TreeNode tree = new TreeNode (null);
    	try {
    		int leftVal = Integer.parseInt(left.toString());
    		int rightVal = Integer.parseInt(right.toString());
    		int multiple = leftVal * rightVal;
    		tree.myItem = "" + multiple;
    	} catch (NumberFormatException e) {
    		tree.myItem = "*";
    		tree.myLeft = left;
    		tree.myRight = right;
    	}
    	return tree;
    }
//    
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
        
        private static int height(TreeNode a) {
        	if (a == null) {
        		return 0;
        	} else {
        		return 1 + Math.max(height(a.myRight), height(a.myLeft));
        	}
        }
        
        private static boolean isCompletelyBalanced(TreeNode node) {
        	 if (node == null) {
        		 return true;
        	 }
        	
        	 int leftHeight = height(node.myLeft);
        	 int rightHeight = height(node.myRight);
        	 return leftHeight == rightHeight && isCompletelyBalanced(node.myLeft) && isCompletelyBalanced(node.myRight);
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
        
        public String toString() {
        	return (String) myItem;        	
        }
    }
}
