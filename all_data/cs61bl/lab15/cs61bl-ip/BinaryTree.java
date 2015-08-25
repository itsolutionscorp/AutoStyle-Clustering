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
        myRoot = new TreeNode("a", 
        			new TreeNode("b", new TreeNode("c", new TreeNode("d"), null), null),
        			new TreeNode("dumb", new TreeNode("tree", new TreeNode("is long"), null), null));
    }

    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        print(t, "the empty tree");
        System.out.println("Height :" + t.height());
        System.out.println("Balanced: " + t.isCompletelyBalanced());
        t.print();
        
        t.fillSampleTree1();
        print(t, "sample tree 1");
        System.out.println("Height :" + t.height());
        System.out.println("Balanced: " + t.isCompletelyBalanced());
        t.print();
        
        t.fillSampleTree2();
        print(t, "sample tree 2");
        System.out.println("Height :" + t.height());
        System.out.println("Balanced: " + t.isCompletelyBalanced());
        t.print();
        
        t.fillSampleTree3();
        print(t, "sample tree 3");
        System.out.println("Height :" + t.height());
        System.out.println("Balanced: " + t.isCompletelyBalanced());
        t.print();
        System.out.println(t.check());
        
        BinaryTree fib = fibTree(5);
        fib.print();
    	BinaryTree expr = exprTree("((a+(5*(9+1)))+(6*5))");
    	BinaryTree exprop = exprTree("((a+50)+30)");
    	expr.optimize();
    	expr.print();
    	System.out.println("OPTIMIZED:");
    	exprop.print();
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

    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
    }

    private TreeNode fibTreeHelper (int n) {
    	if (n == 0) {
    		return new TreeNode(n);
    	} else if (n == 1) {
    		return new TreeNode(n);
    	} else {
    		TreeNode prev0 = fibTreeHelper(n-1);
    		TreeNode prev1 = fibTreeHelper(n-2);
    		int head = (int) prev0.myItem + (int) prev1.myItem;
    		
    		return new TreeNode(head, prev1, prev0);
    	}
    }
    
    // Contains nodes already seen in the traversal. 
    private ArrayList alreadySeen;
    
    private void isOK(TreeNode t) throws IllegalStateException {
    	if (t != null) {
    		if (t.myLeft != null) {
    			isOK(t.myLeft);
    		}
    		if (alreadySeen.contains(t.myItem))
    			throw new IllegalStateException("repeat node "+t.myItem);
    		else
    			alreadySeen.add(t.myItem);
    		if (t.myRight != null) {
    			isOK(t.myRight);
    		}
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
            int nesting = 0;
            int opPos = 0;
            for (int k = 1; k < expr.length() - 1; k++) {
            	char current = expr.charAt(k);
            	if (current == '(')
            		nesting++;
            	if (current == ')')
            		nesting--;
            	
                if (nesting == 0 && (current == '+' || current == '*')) {
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
            return new TreeNode(op, exprTreeHelper(opnd2), exprTreeHelper(opnd1));
        }
    }
    
    public void optimize() {
    	if (myRoot != null) {
    		myRoot.optimize();
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
        
        public int height() {
        	if (myLeft == null && myRight == null)
        		return 0;
        	else if (myLeft == null)
        		return 1 + myRight.height();
        	else if (myRight == null)
        		return 1 + myLeft.height();
        	else
        		return 1 + Math.max(myLeft.height(), myRight.height());
        }
        
        public boolean isCompletelyBalanced() {
        	if (myLeft == null && myRight == null)
        		return true;
        	
        	int left = myLeft.height();
        	int right = myRight.height();
        	
        	if (left == right)
        		return true;
        	return false;
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

        private void print(int indent) {
        	if (myLeft != null) {
        		myLeft.print(indent + 1);
        	}
            println (myItem, indent);
        	if (myRight != null) {
        		myRight.print(indent + 1);
        	}
        }

        private static void println(Object obj, int indent) {
            for (int k=0; k<indent; k++) {
                System.out.print(indent1);
            }
            System.out.println(obj);
        }
        
        private void optimize() {
        	char left = ' ';
        	char right = ' ';

        	if (myLeft != null) {
        		myLeft.optimize();
        	}
        	
        	if (myLeft != null && myRight != null) {
            	left = ((String) myLeft.myItem).charAt(0);
            	right = ((String) myRight.myItem).charAt(0);
        	}
        	
        	if ((int) left >= 48 && (int) left <= 57
        			&& (int) right >= 48 && (int) right <= 57) {
        		
        		int t1 = Integer.parseInt((String) myLeft.myItem);
        		int t2 = Integer.parseInt((String) myRight.myItem);
        		
        		if (myItem.equals("*"))
        			myItem = Integer.toString(t1 * t2);
        		else if (myItem.equals("+"))
        			myItem = Integer.toString(t1 + t2);
        	
        		myLeft = null;
        		myRight = null;
        	}

        	if (myRight != null) {
        		myRight.optimize();
        	}
        	
        }

    }
}
