import java.util.*;

public class BinaryTree {

    private TreeNode myRoot;

    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
    }
    
    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
    }

    private TreeNode fibTreeHelper (int n) {
    	if (n == 0) {
    		return new TreeNode(0);
    	} else if (n == 1) {
    		return new TreeNode(1);
    	} else {
    		TreeNode left = fibTreeHelper(n-1);
    		TreeNode right = fibTreeHelper(n-2);
    		return new TreeNode((Integer) left.myItem + (Integer) right.myItem, left, right);
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
            	if (expr.charAt(k) == '('){
            		nesting ++;
            	} else if (nesting == 0) {
            		if (expr.charAt(k) == '*' || expr.charAt(k) == '+') {
            			opPos = k;
            		}
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
            if (nesting != 0){
            	System.err.println("Not a valid expression.");
            	return null;
            }
            return new TreeNode(op, exprTreeHelper(opnd1), exprTreeHelper(opnd2));
        }
    }
    
    public void optimize() {
    	myRoot = optimizeHelper(myRoot);
    }
    
    public TreeNode optimizeHelper(TreeNode tree) {
    	if (tree.myLeft != null && tree.myRight != null) {
    		try {
    			int left = Integer.parseInt((String) tree.myLeft.myItem);
    			int right = Integer.parseInt((String) tree.myRight.myItem);
    			if (tree.myItem.equals("*")) {
    				tree.myItem = left * right;
    				tree.myLeft = null;
    				tree.myRight = null;
    			} else if (tree.myItem.equals("+")){
    				tree.myItem = left + right;
    				tree.myLeft = null;
    				tree.myRight = null;
    			}
    		} catch(NumberFormatException e) {
    			//Do Nothing
    		}
    	}
    	if (tree.myLeft != null) {
    		tree.myLeft = optimizeHelper(tree.myLeft);
    	}
    	if (tree.myRight != null) {
    		tree.myRight = optimizeHelper(tree.myRight);
    	}
    	return tree;
    }
    
    public int height() {
    	if (myRoot == null) {
    		return 0;
    	} else if (myRoot.myLeft == null && myRoot.myRight == null) {
    		return 1;
    	} else if (myRoot.myLeft == null) {
    		BinaryTree right = new BinaryTree(myRoot.myRight);
    		return right.height() + 1;
    	} else if (myRoot.myRight == null) {
    		BinaryTree left = new BinaryTree(myRoot.myLeft);
    		return left.height() + 1;
    	} else {
    		BinaryTree left = new BinaryTree(myRoot.myLeft);
    		BinaryTree right = new BinaryTree(myRoot.myRight);
    		return left.height() + right.height();
    	}
    }
    
    public boolean isCompletelyBalanced() {
    	if (myRoot == null) {
    		return true;
    	} else if (myRoot.myLeft == null && myRoot.myRight == null) {
    		return true;
    	} else {
    		if (myRoot.myLeft != null && myRoot.myRight == null) {
    			return false;
    		} else if (myRoot.myLeft == null && myRoot.myRight != null) {
    			return false;
    		} else {
        		BinaryTree left = new BinaryTree(myRoot.myLeft);
        		BinaryTree right = new BinaryTree(myRoot.myRight);
        		if (left.height() == right.height()) {
        			return true;
        		} else {
        			return false;
        		}
    		}
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
    	myRoot = new TreeNode("a", new TreeNode("b", null, null), new TreeNode("c", 
    			new TreeNode("d", new TreeNode("e"), new TreeNode("f")), null));
    }
    
    public void fillSampleTree4() {
    	myRoot = new TreeNode("A", new TreeNode("B", null, null), new TreeNode("C",
    			new TreeNode("D", null, null), new TreeNode("E", null, null)));
    }
    
    public void print() {
        if (myRoot != null) {
            myRoot.print(0);
        }
    }
 
    public void isOK(TreeNode t) throws IllegalStateException {
    	if (t == null) {
    		return;
    	}
    	ArrayList<Object> items = new ArrayList<Object>();
    	duplicateMaker(items);
    	HashSet<Object> itemsCheck = new HashSet<Object>();
    	for (int i = 0; i < items.size(); i++) {
    		if (itemsCheck.contains(items.get(i))) {
    			throw new IllegalStateException("This tree is not valid due to duplicates.");
    		}
    		itemsCheck.add(items.get(i));
    	}
    	
    }
    
    
    public void duplicateMaker(ArrayList<Object> items) {
    	if (myRoot.myLeft == null && myRoot.myRight == null) {
    		items.add(myRoot.myItem);
    	} else if (myRoot.myLeft == null) {
    		items.add(myRoot.myItem);
    		BinaryTree right = new BinaryTree(myRoot.myRight);
    		right.duplicateMaker(items);
    	} else if (myRoot.myRight == null) {
    		items.add(myRoot.myItem);
    		BinaryTree left = new BinaryTree(myRoot.myLeft);
    		left.duplicateMaker(items);
    	} else {
    		items.add(myRoot.myItem);
    		BinaryTree left = new BinaryTree(myRoot.myLeft);
    		BinaryTree right = new BinaryTree(myRoot.myRight);
        	left.duplicateMaker(items);
        	right.duplicateMaker(items);
    	}
    }

    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.isOK(t.myRoot);
        print(t, "the empty tree");
        
        t = exprTree("((a+(5*(a+b)))+(6*5))");
        t.optimize();
        t.print();
        
        t = exprTree("((a+b)+c)");
        t.optimize();
        t.print();
        
        t = exprTree("((a+50)+30)");
        t.optimize();
        t.print();

        
        t.fillSampleTree1();
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.isOK(t.myRoot);
        print(t, "sample tree 1");
        
        t.fillSampleTree2();
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.isOK(t.myRoot);
        print(t, "sample tree 2");
        
        t.fillSampleTree3();
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.isOK(t.myRoot);
        print(t, "sample tree 3");
        
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
        
        //In TreeNode
        private static final String indent1 = "    ";

        private void print(int indent) {
            // TODO your code here
        	TreeNode temp = this;
        	if (myRight != null) {
        		indent++;
        		temp.myRight.print(indent);
        	}
        	if (myRight == null && myLeft == null) {
        		indent++;
        	}
        	Object myItem = this.myItem;
            println (myItem, indent);
            indent--;
            // TODO your code here
            if (myLeft != null) {
        		indent++;
            	temp.myLeft.print(indent);
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
