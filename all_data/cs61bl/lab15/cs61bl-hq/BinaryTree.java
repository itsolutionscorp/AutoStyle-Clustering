import java.util.ArrayList;

public class BinaryTree {

    // Contains nodes already seen in the traversal. 
    private ArrayList<Object> alreadySeen; 
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
    	myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c", new TreeNode("d", 
    			new TreeNode("e"), new TreeNode("f")), null));
    }
    
    public void fillSampleTree4() {
    	myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("b"));
    }

    public int height() {
		if (myRoot != null) {
			return myRoot.height();
		}
		return 0;
	}
    
    public boolean isCompletelyBalanced() {
    	if (myRoot == null || myRoot.myLeft == null & myRoot.myRight == null) {
    		return true;
    	} else {
    		return (myRoot.myLeft.height() == myRoot.myRight.height());
    	}
    }
    
    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        print(t, "the empty tree");
        
        t.fillSampleTree1();
        print(t, "sample tree 1");
        
        t.fillSampleTree2();
        print(t, "sample tree 2");
        
        t.fillSampleTree3();
        print(t, "sample tree 3");
        System.out.println(t.check());
        
        t.fillSampleTree4();
        print(t, "sample tree 4");
        System.out.println(t.check());
        
        BinaryTree.fibTree(5).print();
        
        BinaryTree.exprTree("((a+(5*(a+b)))+(6*5))").print();
        
        BinaryTree b = BinaryTree.exprTree("((a+(5*(9+1)))+(6*5))");
        b.optimize();
        b.print();
        
    }

    private static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
        t.print();
    }
    
    public void print() {
        if (myRoot != null) {
            myRoot.print(0);
        }
    }
    
    public boolean check() { 
        alreadySeen = new ArrayList<Object>(); 
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
    	} else {
    		if (alreadySeen.contains(t.myItem)) {
    			throw new IllegalStateException();
    		}
    		alreadySeen.add(t.myItem);
    		isOK(t.myLeft);
    		isOK(t.myRight);
    	}
    }
    
    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
    }
    
    private TreeNode fibTreeHelper (int n) {
    	if (n <= 1) {
			return new TreeNode(n);
		} else {
			TreeNode left = fibTreeHelper(n - 1);
			TreeNode right = fibTreeHelper(n - 2);
			TreeNode main = new TreeNode((Integer) left.myItem + (Integer) right.myItem, left, right);
			return main;
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
            } else if (nesting == 0 && (expr.charAt(k) == '+' || expr.charAt(k) == '*')) {
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
        TreeNode left;
        if (opnd1.length() > 1) {
        	left = exprTreeHelper(opnd1);
        } else {
        	left = new TreeNode(opnd1);
        }
        TreeNode right;
        if (opnd2.length() > 1) {
        	right = exprTreeHelper(opnd2);
        } else {
        	right = new TreeNode(opnd2);
        }
        TreeNode main = new TreeNode(op, left, right);
        return main;
    }
    
    public void optimize() {
		BinaryTree.optimizeHelper(myRoot);
	}

	private static void optimizeHelper(TreeNode t) {
		if (t.myLeft == null || t.myRight == null) {
			return;
		}
		optimizeHelper(t.myLeft);
		optimizeHelper(t.myRight);
		try {
			int left = Integer.parseInt((String) t.myLeft.myItem);
			int right = Integer.parseInt((String) t.myRight.myItem);
			if (t.myItem.equals("*")) {
				t.myItem = left * right;
			}
			if (t.myItem.equals("+")) {
				t.myItem = left + right;
			}
			t.myItem = t.myItem.toString();
			t.myLeft = null;
			t.myRight = null;
		} catch (NumberFormatException e) {
			e.getMessage();
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
        	if (myLeft == null) {
        		return 1;
        	} else if (myRight == null) {
        		return (1 + myLeft.height());
        	} else {
        		return Math.max(1 + myLeft.height(), 1 + myRight.height());
        	}
        }
        
        private void print(int indent) {
            if (myRight != null) {
            	myRight.print(indent + 1);;
            }
            println (myItem, indent);
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
      
    }
}
