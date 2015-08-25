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
    	myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c", new TreeNode("d"), new TreeNode("e")));
    }
    
    public void fillSampleTree5() {
    	myRoot = new TreeNode("a", new TreeNode("a"), null);
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
        print(t, "sample tree 2");
        t.fillSampleTree4();
        t.print();
    }

    private static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
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
    
    public void isOK(TreeNode t) throws IllegalStateException {
    	if (alreadySeen.contains(t.myItem)) {
    		throw new IllegalStateException();
    	}
    	alreadySeen.add(t.myItem);
    	if (t.myLeft != null) {
    		isOK(t.myLeft);
    	}
    	if (t.myRight != null) {
    		isOK(t.myRight);
    	}
    }

    // Contains nodes already seen in the traversal. 
    private ArrayList alreadySeen; 
    
    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
    }

    private TreeNode fibTreeHelper(int n) {
    	if (n == 0) {
    		return new TreeNode(0);
    	}
    	else if (n == 1) {
    		return new TreeNode(1);
    	}
    	else {
    		TreeNode left = fibTreeHelper(n-1);
    		TreeNode right = fibTreeHelper(n-2);
    		TreeNode result = new TreeNode((int) left.myItem + (int) right.myItem);
    		result.myLeft = left;
    		result.myRight = right;
    		return result;
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
            return new TreeNode(expr.charAt(0));
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
                }
                else if (expr.charAt(k) == ')') {
                	nesting--;
                }
                else if ((expr.charAt(k) == '+' || expr.charAt(k) == '*') && nesting == 0) {
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
            return new TreeNode(op, exprTreeHelper(opnd1), exprTreeHelper(opnd2));
        }
    }
    
    public void optimize() {
    	BinaryTree.optimizeHelper(myRoot);
    }
    
    private static void optimizeHelper(TreeNode cur) {
    	int left, right;
		if (cur.myLeft == null) { // nodes in expression tree must either have 0
									// or 2 children so checking only the left
									// node is sufficient
			return;
		}
		optimizeHelper(cur.myLeft);
		optimizeHelper(cur.myRight);
		try {
			left = Integer.parseInt(String.valueOf(cur.myLeft.myItem));
			right = Integer.parseInt(String.valueOf(cur.myRight.myItem));
			if (cur.myItem.equals("*")) {
				cur.myItem = left * right;
			} else {
				cur.myItem = left + right;
			}
			cur.myItem = cur.myItem.toString();
			cur.myLeft = null;
			cur.myRight = null;
		} catch (NumberFormatException e) {
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
        	else if (myLeft == null) {
        		return 1 + myRight.height();
        	}
        	else if (myRight == null) {
        		return 1 + myLeft.height();
        	}
        	else {
        		return 1 + Math.max(myLeft.height(), myRight.height());
        	}
        }
        
        public boolean isCompletelyBalanced() {
        	if (myLeft == null && myRight == null) {
        		return true;
        	}
        	else if (myLeft == null || myRight == null) {
        		return false;
        	}
        	else {
        		return myRight.height() == myLeft.height() && myRight.isCompletelyBalanced() && myLeft.isCompletelyBalanced();
        	}
        }
        
        //In TreeNode
        private static final String indent1 = "    ";

        private void print(int indent) {
            if (myRight == null && myLeft == null) {
            	println(myItem, indent);
            }
            else {
	            if (myRight != null) {
	            	myRight.print(indent+1);
	            }
	            println(myItem, indent);
	            if (myLeft != null) {
	            	myLeft.print(indent+1);
	            }
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
