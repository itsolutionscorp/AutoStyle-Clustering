import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

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

    public void fillSampleTree1() {
        myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
    }

    public void fillSampleTree2() {
        myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d",
                new TreeNode("e"), new TreeNode("f")), null), new TreeNode("c"));
    }
    
    public void fillSampleTree3() {
    	myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode ("c", new TreeNode("d", new TreeNode("e"), new TreeNode("f")), null));
    	
    }
    
    public void fillSampleTree4() {
        myRoot = new TreeNode("a", 
        		 new TreeNode("a", 
        				 new TreeNode("a", new TreeNode(new TreeNode("a", 
        		        		 new TreeNode("a", new TreeNode("a", new TreeNode("b"), new TreeNode("c")), new TreeNode("a", new TreeNode("b"), new TreeNode("c"))), 
        		        		 new TreeNode("a", new TreeNode("a", new TreeNode("b"), new TreeNode("c")), new TreeNode("a", new TreeNode("b"), new TreeNode("c"))))), 
        		        		 new TreeNode(new TreeNode("a", 
        		                		 new TreeNode("a", new TreeNode("a", new TreeNode("b"), new TreeNode("c")), new TreeNode("a", new TreeNode("b"), new TreeNode("c"))), 
        		                		 new TreeNode("a", new TreeNode("a", new TreeNode("b"), new TreeNode("c")), new TreeNode("a", new TreeNode("b"), new TreeNode("c")))))), 
        				 new TreeNode("a", new TreeNode(new TreeNode("a", 
        		        		 new TreeNode("a", new TreeNode("a", new TreeNode("b"), new TreeNode("c")), new TreeNode("a", new TreeNode("b"), new TreeNode("c"))), 
        		        		 new TreeNode("a", new TreeNode("a", new TreeNode("b"), new TreeNode("c")), new TreeNode("a", new TreeNode("b"), new TreeNode("c"))))), 
        		        		 new TreeNode(new TreeNode("a", 
        		                		 new TreeNode("a", new TreeNode("a", new TreeNode("b"), new TreeNode("c")), new TreeNode("a", new TreeNode("b"), new TreeNode("c"))), 
        		                		 new TreeNode("a", new TreeNode("a", new TreeNode("b"), new TreeNode("c")), new TreeNode("a", new TreeNode("b"), new TreeNode("c"))))))), 
        		 new TreeNode("a", 
        				 new TreeNode("a", new TreeNode(new TreeNode("a", 
        		        		 new TreeNode("a", new TreeNode("a", new TreeNode("b"), new TreeNode("c")), new TreeNode("a", new TreeNode("b"), new TreeNode("c"))), 
        		        		 new TreeNode("a", new TreeNode("a", new TreeNode("b"), new TreeNode("c")), new TreeNode("a", new TreeNode("b"), new TreeNode("c"))))), 
        		        		 new TreeNode(new TreeNode("a", 
        		                		 new TreeNode("a", new TreeNode("a", new TreeNode("b"), new TreeNode("c")), new TreeNode("a", new TreeNode("b"), new TreeNode("c"))), 
        		                		 new TreeNode("a", new TreeNode("a", new TreeNode("b"), new TreeNode("c")), new TreeNode("a", new TreeNode("b"), new TreeNode("c")))))), 
        				 new TreeNode("a", new TreeNode(new TreeNode("a", 
        		        		 new TreeNode("a", new TreeNode("a", new TreeNode("b"), new TreeNode("c")), new TreeNode("a", new TreeNode("b"), new TreeNode("c"))), 
        		        		 new TreeNode("a", new TreeNode("a", new TreeNode("b"), new TreeNode("c")), new TreeNode("a", new TreeNode("b"), new TreeNode("c"))))), 
        		        		 new TreeNode(new TreeNode("a", 
        		                		 new TreeNode("a", new TreeNode("a", new TreeNode("b"), new TreeNode("c")), new TreeNode("a", new TreeNode("b"), new TreeNode("c"))), 
        		                		 new TreeNode("a", new TreeNode("a", new TreeNode("b"), new TreeNode("c")), new TreeNode("a", new TreeNode("b"), new TreeNode("c"))))))));
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
        alreadySeen = new ArrayList<TreeNode>(); 
        try {
        	BinaryTreeIterator asdf = this.blah();
            while (asdf.hasNext()) {
            	isOK(asdf.next()); 
            }
            return true; 
        } catch (IllegalStateException e) { 
            return false; 
        } 
    }
    
    private void isOK(TreeNode t) throws IllegalStateException {
		if (alreadySeen.contains(t)){
			throw new IllegalStateException("IS NOT OKAY YOU B BETTER");
		}
		alreadySeen.add(t);
//    	this.alreadySeen.add(t);
//    	if this.myLeft
//		for (BinaryTree a : myChildren) {
//			String name = a.longestName();
//			if (myName.length() < name.length()) {
//				longestSeen = name;
//			}
//		}
//		return longestSeen;
    }
    	
    private BinaryTreeIterator blah() {
    	return new BinaryTreeIterator();
    }
    
	public class BinaryTreeIterator implements Iterator<TreeNode>{
		LinkedList<TreeNode> fringe;

		public BinaryTreeIterator() {
			fringe = new LinkedList<TreeNode>();
			if (myRoot != null) {
				fringe.add(myRoot);
			}
		}

		public boolean hasNext() {
			return (!this.fringe.isEmpty());
		}

		public TreeNode next() {
			TreeNode bob = fringe.remove();
			if (bob.myRight != null) {
				fringe.add(bob.myRight);
			}
			if (bob.myLeft != null) {
				fringe.add(bob.myLeft);
			}
			return bob;
		}

		public void remove() {
			// Not used for now -- removal from a tree can be difficult.
			// Once you've learned about different ways to remove from
			// trees, it might be a good exercise to come back and 
			// try to implement this.
		}

	}
	
	public static BinaryTree fibTree(int n) {
	    BinaryTree result = new BinaryTree();
	    result.myRoot = result.fibTreeHelper(n);
	    return result;
	}

	private TreeNode fibTreeHelper(int n) {
		if (n == 0 || n == 1) {
			return new TreeNode(n);
		} else {
			TreeNode left = fibTreeHelper(n-1);
			TreeNode right = fibTreeHelper(n-2);
			return new TreeNode(((Integer) left.myItem) + ((Integer) right.myItem), left, right);
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
	        	if ((expr.charAt(k) == '+' || expr.charAt(k) == '*') && (nesting == 0)) {
	        		opPos = k;
	        		break;
	        	} else if (expr.charAt(k) == '(') {
	        		nesting ++;
	        	} else if (expr.charAt(k) == ')') {
	        		nesting --;
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
		this.optimizeHelper(this.myRoot);
	}
	private void optimizeHelper(TreeNode x) {
		if (!(x.myItem.equals("+") || x.myItem.equals("*"))) {
			return;// (String) x.myItem;
		} else {
			optimizeHelper(x.myLeft);
			optimizeHelper(x.myRight);
			try {
				if (x.myItem.equals("+")){
					x.myItem = Integer.toString(Integer.parseInt((String) x.myLeft.myItem) + Integer.parseInt((String) x.myRight.myItem));
					x.myLeft = null;
					x.myRight = null;
				} else if (x.myItem.equals("*")){
					x.myItem = Integer.toString(Integer.parseInt((String) x.myLeft.myItem) * Integer.parseInt((String) x.myRight.myItem));
					x.myLeft = null;
					x.myRight = null;
				}
			} catch (NumberFormatException e) {
				return;
			}
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
        print(t, "adsfas");
        System.out.println(t.height());
        t.print();
        t.fillSampleTree4();
        t.print();
        System.out.println(t.check());
        BinaryTree fib = fibTree(3);
        fib.print();
        exprTree("((a+(5*(a+b)))+(6*5))").print();
        BinaryTree pls = exprTree("((a+(5*(9+1)))+(6*5))");
        pls.optimize();
        pls.print();
        exprTree("((a+50)+30)").print();
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
        	int curr = 1; int l = 0; int r = 0;
        	if (myLeft != null) {
        		curr = myLeft.height() + 1;
        	}
        	if (myRight != null) {
        		curr = Math.max(myRight.height() + 1, curr);
        	}
        	return curr;
        }
        
        public boolean isCompletelyBalanced() {
        	if (myLeft == null && myRight == null) {
        		return true;
        	} else if(myLeft == null || myRight == null) {
        		return false;
        	} else if (myLeft.height() == myRight.height()) {
        		return myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced();        		
        	} 
        	return false;
        }
        
        private static final String indent1 = "    ";

        private void print(int indent) {
            // TODO your code here
        	if (this.myRight != null) {
        		this.myRight.print(indent + 1);
        	}
            println (myItem, indent);
            // TODO your code here
            if (this.myLeft != null) {
            	this.myLeft.print(indent + 1);
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
