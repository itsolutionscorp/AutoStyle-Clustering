import java.util.ArrayList;
import java.util.Stack;

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
    
    public int height() {
    	if (myRoot == null) {
    		return 0;
    	} else {
    		return myRoot.height();
    	}
    }
    
    public boolean isCompletelyBalanced(){
    	if (myRoot == null) {
    		return true;
    	} else {
    		return myRoot.isCompletelyBalanced();
    	}
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

    // Contains nodes already seen in the traversal. 
    private ArrayList alreadySeen; 
    
    private void isOK(TreeNode t) throws IllegalStateException {
    	Stack<TreeNode> fringe = new Stack<TreeNode>();
    	TreeNode current = t;
    	while (current != null || !fringe.isEmpty()) {
    		if (current != null) {
    			if (alreadySeen.contains(current.myItem)) {
    				throw new IllegalStateException("Already exists.");
    			}
    			fringe.add(current);
    			alreadySeen.add(current.myItem);
    			current = current.myRight;
    		} else {
    			current = fringe.pop();
    			current = current.myLeft;
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
    		return new TreeNode(new Integer(0));
    	}
    	if (n == 1) {
    		return new TreeNode(new Integer(1));
    	}
    	TreeNode left = fibTreeHelper(n-1);
    	TreeNode right = fibTreeHelper(n-2);
    	return new TreeNode(new Integer(((Integer) left.myItem).intValue() + ((Integer) right.myItem).intValue()), left, right);
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
            	if (nesting == 0 && (expr.charAt(k) == '+' || expr.charAt(k) == '*')) {
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
            return new TreeNode(op, exprTreeHelper(opnd1), exprTreeHelper(opnd2)); // you fill this in
        }
    }
    
    private void optimize() {
		optimizeHelper(this.myRoot);
    }
    
    private void optimizeHelper(TreeNode current) {
    	int left, right;
    	if (current == null || (current.myLeft == null & current.myRight == null)) {
    		return;
    	}
    	if (current.myLeft != null) {
    		optimizeHelper(current.myLeft);
    	}
    	if (current.myRight != null) {
    		optimizeHelper(current.myRight);
    	}
		try {
			left = Integer.parseInt((String) current.myLeft.myItem);
			right = Integer.parseInt((String) current.myRight.myItem);
			if (current.myItem.equals("*")) {
				current.myItem = left * right;
			} else {
				current.myItem = left + right;
			}
			current.myItem = current.myItem.toString();
			current.myLeft = null;
			current.myRight = null;
		} catch (NumberFormatException e) {
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
    	myRoot = new TreeNode("a");
    }
    
    public void fillSampleTree5() {
    	myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("c"), new TreeNode("d")), new TreeNode("e", new TreeNode("f"), new TreeNode("g")));
    }

    public void fillSampleTree6() {
    	myRoot = new TreeNode("a", new TreeNode("b"), null);
    }
    
    public void fillSampleTree7() {
    	myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c", new TreeNode("d"), new TreeNode("e")));
    }
    
    public void fillSampleTree8() {
        myRoot = new TreeNode("a", new TreeNode("a"), new TreeNode("b"));
    }
    
    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        print(t, "the empty tree");
        t.fillSampleTree1();
        print(t, "sample tree 1");
        System.out.println(t.check());
        t.fillSampleTree2();
        print(t, "sample tree 2");
        System.out.println(t.check());
        t.fillSampleTree3();
        print(t, "sample tree 3");
        System.out.println(t.check());
        t.fillSampleTree7();
        t.print();
        t.fillSampleTree8();
        System.out.println(t.check());
        t = exprTree("((a+(5*(a+b)))+(6*5))");
        t.print();
		t = exprTree("((a+(5*(9+1)))+(6*5))");
		t.optimize();
		t.print();
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
			} else {
				int bestSoFar = 0;
				if (myLeft != null) {
					bestSoFar = myLeft.height();
				}
				if (myRight != null) {
					bestSoFar = Math.max(myLeft.height(), myRight.height());
				}
				return bestSoFar + 1;
			}
		}
        
		public boolean isCompletelyBalanced() {
			if (myLeft == null && myRight == null) { 
				return true;
			}
			if (myLeft == null || myRight == null) {
				return false;
			} else if (myLeft.height() == myRight.height() && myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced()) {
				return true;
			}
			return false;
		}
    	
		private static final String indent1 = "    ";

		private void print(int indent) {
		    if (myRight != null) {
		    	myRight.print(indent + 1);
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
