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
    	if (myRoot==null) {
    		return 0;
    	}
    	return myRoot.height();
    }
    public boolean isCompletelyBalanced() {
    	if (myRoot==null) {
    		return true;
    	}
    	return myRoot.isCompletelyBalanced();
    }
    public void fillSampleTree1() {
        myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
    }

    public void fillSampleTree2() {
        myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d",
                new TreeNode("e"), new TreeNode("f")), null), new TreeNode("c"));
    }
    public void fillSampleTree4() {
    	myRoot = new TreeNode("a");
    }
    public void fillSampleTree3() {
    	myRoot = new TreeNode("a",new TreeNode("b",new TreeNode("c"), new TreeNode("d")),new TreeNode("e", new TreeNode("f"), new TreeNode("g")));
    }
    public void fillSampleTree5() {
    	myRoot = new TreeNode("a", new TreeNode("b"),new TreeNode("c", new TreeNode("d"), new TreeNode("e")));
    }
    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        t.check();
        t.print();
        t = exprTree("((a+(5*(a+b)))+(6*5))");
        t.check();
        t.print();
        t.optimize();
        t.check();
        t.print();
        t = exprTree("((a+(5*(9+1)))+(6*5))");
        t.check();
        t.print();
        t.optimize();
        t.check();
        t.print();
        t = exprTree("(2+3)");
        t.check();
        t.print();
        t.optimize();
        t.check();
        t.print();
        t = exprTree("((7+50)+(6*5))");
        t.check();
        t.print();
        t.optimize();
        t.check();
        t.print();
        t = fibTree(5);
        t.check();
        t.print();
        t = fibTree(0);
        t.check();
        t.print();
        t = fibTree(1);
        t.check();
        t.print();
        t = fibTree(2);
        t.check();
        t.print();
        t.fillSampleTree5();
        t.check();
        t.print();
        t.fillSampleTree1();
        t.check();
        t.print();
        t.fillSampleTree2();
        t.check();
        t.print();
        t.fillSampleTree3();
        t.check();
        t.print();
        t.fillSampleTree4();
        t.check();
        t.print();
        t = new BinaryTree();
        print(t, "the empty tree");
        t.fillSampleTree1();
        print(t, "sample tree 1");
        t.fillSampleTree2();
        print(t, "sample tree 2");
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

    // Contains nodes already seen in the traversal. 
    private ArrayList alreadySeen; 
    
    private void isOK(TreeNode t) throws IllegalStateException {
    	if (alreadySeen.contains(t)) {
    		throw new IllegalStateException("A node has appeared more than once!");
    	}
    	alreadySeen.add(t);
    	if (t==null) {
    		return;
    	}
    	if (t.myLeft!=null) {
    		isOK(t.myLeft);
    	}
    	if (t.myRight!=null) {
    		isOK(t.myRight);
    	}
    }
    
    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
    }

    private TreeNode fibTreeHelper (int n) {
    	TreeNode node0= new TreeNode(0);
    	TreeNode node1 = new TreeNode(1);
    	if (n==0) {
    		return node0;
    	}
    	if (n==1) {
    		return node1;
    	}
    	TreeNode a = fibTreeHelper(n-1);
    	TreeNode b = fibTreeHelper(n-2);
    	TreeNode result = new TreeNode((Integer)a.myItem+(Integer)b.myItem);
    	result.myLeft = a;
    	result.myRight = b;
    	return result;
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
                if (expr.charAt(k)=='(') {
                	nesting++;
                }
                if (expr.charAt(k)==')'){
                	nesting--;
                }
                if (nesting==0&&(expr.charAt(k)=='+'||expr.charAt(k)=='*')) {
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
            return new TreeNode(op, exprTreeHelper(opnd1),exprTreeHelper(opnd2));
            // you fill this in
        }
    }
    
    public void optimize(){
    	if (myRoot!=null) {
    		myRoot = optimizeHelper(myRoot);
    	}    	
    }
    
    private static TreeNode optimizeHelper(TreeNode toOpt) {
    	String a;
    	String b;
    	int intA;
    	int intB;
    	if (toOpt == null) {
    		return null;
    	} else {
    		try {
    			Integer.parseInt(toOpt.myItem.toString());
    			return toOpt;
    		} catch (NumberFormatException e1) {
    			if (toOpt.myLeft==null) {
    	    		toOpt.myRight = optimizeHelper(optimizeHelper(toOpt.myRight));
    	    		return toOpt;
    	    	} else if (toOpt.myRight==null) {
    	    		toOpt.myLeft = optimizeHelper(optimizeHelper(toOpt.myLeft));
    	    		return toOpt;
    	    	} else {
    	    		try {
    	    			a = toOpt.myLeft.myItem.toString();
    	    			intA = Integer.parseInt(a);
    	    			b = toOpt.myRight.myItem.toString();
    	    			intB = Integer.parseInt(b);
    	    			if (toOpt.myItem.equals("+")) {
    	    				toOpt.myItem = intA+intB;
    	    			} else if (toOpt.myItem.equals("*")){
    	    				toOpt.myItem = intA*intB;
    	    			}
    	    			toOpt.myLeft = null;
    	    			toOpt.myRight = null;
    	    			return toOpt;
    	    		} catch (NumberFormatException e2) {
    	    			toOpt.myLeft = optimizeHelper(optimizeHelper(toOpt.myLeft));
    	    			toOpt.myRight = optimizeHelper(optimizeHelper(toOpt.myRight));
    	    			try {
    	    				int c = Integer.parseInt(toOpt.myLeft.myItem.toString());
    	    				int d = Integer.parseInt(toOpt.myRight.myItem.toString());
    	    				if (toOpt.myItem.equals("+")) {
        	    				toOpt.myItem = c+d;
        	    			} else if (toOpt.myItem.equals("*")){
        	    				toOpt.myItem = c*d;
        	    			}
        	    			toOpt.myLeft = null;
        	    			toOpt.myRight = null;
        	    			return toOpt;
    	    			} catch (NumberFormatException e) {
    	    				return toOpt;
    	    			}
    	    			
    	    		}
    	    	}
    		}
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
        	int left = 1;
        	int right = 1;
        	if (myLeft!=null) {
        		left+=myLeft.height();
        	}
        	if (myRight!=null) {
        		right+=myRight.height();
        	}
        	return Math.max(left, right);
        }
        private boolean isCompletelyBalanced() {
        	if(myLeft==null&&myRight==null) {
        		return true;
        	}
        	return myLeft.height()==myRight.height()&&myLeft.isCompletelyBalanced()&&myRight.isCompletelyBalanced();
        }
        //In TreeNode
        
        private void print(int indent) {
            // TODO your code here
        	if (myRight!=null) {
        		myRight.print(indent+1);
        	}
        	indent++;
            println (myItem, indent);
            indent--;
            // TODO your code here
            if (myLeft!=null) {
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
