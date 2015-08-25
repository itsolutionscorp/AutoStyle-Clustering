import java.util.HashSet;
public class BinaryTree {

    private TreeNode myRoot;
    private HashSet<TreeNode> alreadySeen;

    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
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
        	TreeNode t = new TreeNode(null);
            t.myItem = expr.substring(0, 1); // you fill this in
            return t;
        } else {
            // expr is a parenthesized expression.
            // Strip off the beginning and ending parentheses,
            // find the main operator (an occurrence of + or * not nested
            // in parentheses, and construct the two subtrees.
            int nesting = 0;
            int opPos = 0;
            for (int k = 1; k < expr.length() - 1; k++) {
                // you supply the missing code
            	if ((expr.charAt(k) == '+' || expr.charAt(k) == '*') && nesting == 0) {
            		opPos = k;
            	} else if (expr.charAt(k) == '(') {
            		nesting++;
            	} else if (expr.charAt(k) == ')') {
            		nesting--;
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
            TreeNode t = new TreeNode(null); // you fill this in
            t.myItem = op;
            t.myLeft = exprTreeHelper(opnd1);
            t.myRight = exprTreeHelper(opnd2);
            return t;
        }
    }
    
  //Taken from Stackexchange. Checks if an string int is Int parsable
  		public static boolean isParsable(String input){
  		    boolean parsable = true;
  		    try{
  		        Integer.parseInt(input);
  		    }catch(NumberFormatException e){
  		        parsable = false;
  		    }
  		    return parsable;
  		}

    public void optimize() {
    		myRoot = optimizeHelper(myRoot);
    }
    
    public TreeNode optimizeHelper(TreeNode t) {
    	
    	if (t.myLeft != null) {
    	t.myLeft = optimizeHelper(t.myLeft);
    	} if (t.myRight != null) {
    	t.myRight = optimizeHelper(t.myRight);
    	}
    	if (t.myLeft != null && t.myRight != null && isParsable((String) t.myLeft.myItem) && isParsable((String)t.myRight.myItem)) {
    		if (t.myItem.equals("+")) {
    			int sum = Integer.parseInt((String)t.myLeft.myItem) + Integer.parseInt((String) t.myRight.myItem);
    			t.myItem = "" + sum;
    			t.myLeft = null;
    			t.myRight = null;
    		} else {
    			int prod = Integer.parseInt((String)t.myLeft.myItem) * Integer.parseInt((String) t.myRight.myItem);
    			t.myItem = "" + prod;
    			t.myLeft = null;
    			t.myRight = null;
    		}
    	}
    	return t;
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
    		TreeNode t = new TreeNode(0);
    		t.myLeft = fibTreeHelper(n - 1);
    		t.myRight = fibTreeHelper(n - 2);
    		t.myItem = (Integer) t.myLeft.myItem + (Integer) t.myRight.myItem;
    		return t;
    		
    	}
    }
    
    public void print() {
        if (myRoot != null) {
            myRoot.print(0);
        }
    }
    
    public boolean check() { 
        alreadySeen = new HashSet<TreeNode>(); 
        try { 
            isOK(myRoot); 
            return true; 
        } catch (IllegalStateException e) { 
            return false; 
        } 
    }
    
    private void isOK(TreeNode t) throws IllegalStateException {
    	if (alreadySeen.contains(t)) {
    		throw new IllegalStateException();
    	} else {
    		alreadySeen.add(t);
    	}
    	if (t.myLeft != null) {
    		isOK(t.myLeft);
    	}
    	if (t.myRight != null) {
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
    
    public boolean isCompletelyBalanced() {
    	if (myRoot == null) {
    		return true;
    	} else {
    		return myRoot.isCompletelyBalanced();
    	}
    }
    
    public int height() {
    	if (myRoot == null) {
    		return 0;
    	} else {
    		return myRoot.height();
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
    	myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d"), new TreeNode("e")),
    	new TreeNode("c", new TreeNode("f"), new TreeNode("g")));
    }
    
    public void fillSampleTree5() {
    	myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c", new TreeNode("d"), new TreeNode("e")));
    }

    public static void main(String[] args) {
//        BinaryTree t;
//        t = new BinaryTree();
//        print(t, "the empty tree");
//        System.out.println(t.height() + " height = 0");
//        System.out.println(t.isCompletelyBalanced() + " true");
//        t.fillSampleTree1();
//        System.out.println(t.check());
//        print(t, "sample tree 1");
//        System.out.println(t.height() + " height = 2");
//        System.out.println(t.isCompletelyBalanced() + " true");
//        t.fillSampleTree2();
//        System.out.println(t.check());
//        print(t, "sample tree 2");
//        System.out.println(t.height() + " height = 4");
//        System.out.println(t.isCompletelyBalanced() + " false");
//        t.fillSampleTree3();
//        System.out.println(t.check());
//        print(t, "sample tree 3");
//        System.out.println(t.height() + " height = 4");
//        System.out.println(t.isCompletelyBalanced() + " false");
//        t.fillSampleTree4();
//        print(t, "sample tree 4");
//        System.out.println(t.check());
//        System.out.println(t.height() + " height = 3");
//        System.out.println(t.isCompletelyBalanced() + " true");
//        t.fillSampleTree5();
//        t.print();
//        System.out.println(t.check());
//        	fibTree(6).print();
//        exprTree("((a+(5*(a+b)))+(6*5))").optimize().print();
//    	exprTree("((a+(5*(9+1)))+(6*5))").optimize().print();
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
        
        public int height() {
            int bestSoFar = 1;
            if (myLeft != null) {
            bestSoFar = Math.max(1 + myLeft.height(), bestSoFar);
            } if (myRight != null) {
            bestSoFar = Math.max(1 + myRight.height(), bestSoFar);
            }
            return bestSoFar;
        }
        
        public boolean isCompletelyBalanced() {
        	return (myLeft == null && myRight == null || myLeft.height() == myRight.height() && 
        			myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced());
        }

        public TreeNode(Object obj, TreeNode left, TreeNode right) {
            myItem = obj;
            myLeft = left;
            myRight = right;
        }
        
      //In TreeNode
        private static final String indent1 = "    ";

        private void print(int indent) {
            // TODO your code here
        	if (myRight != null) {
            	myRight.print(indent + 1);
            }
        	
            println (myItem, indent);
            // TODO your code here
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
    }
}
