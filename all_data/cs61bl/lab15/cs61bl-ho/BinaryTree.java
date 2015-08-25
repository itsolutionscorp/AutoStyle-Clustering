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
        myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c", new TreeNode ("d", new TreeNode ("e"), new TreeNode ("f")), null));
    }
    
    public void fillSampleTree4() {
        myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("c"), new TreeNode("d")), new TreeNode("e", new TreeNode("f"), new TreeNode("g"))); 
    }
    
    
    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        print(t, "the empty tree");
        t.check();
        t.print();
        t.fillSampleTree1();
        print(t, "sample tree 1");
        t.check();
        t.print();
        t.fillSampleTree2();
        print(t, "sample tree 2");
        t.check();
        t.print();
        t.fillSampleTree3();
        print(t, "sample tree 3");
        t.check();
        t.print();
        t = fibTree(6);
        print(t, "sample fibTree");
        t.check();
        t.print();
        t = exprTree ("((a+(5*(a+b)))+(6*5))");
        t.check();
        t.print();
        t = exprTree ("((a+(5*(9+1)))+(6*5))");
        t.optimize();
        t.check();
        t.print();
    }

    private static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
    }
    
	public int height(){
		if (myRoot != null) {
			return myRoot.height();
		}
		return 0;
	}

	public boolean isCompletelyBalanced(){
		if (myRoot != null) {
			return myRoot.isCompletelyBalanced();
		}
		return true;
	}
	
	/**
	 *  New in lab15: print(), check(), isOK(),  
	 */
	
	public void print() {
	    if (myRoot != null) {
	        myRoot.print(0);
	    }
	}
	
	private ArrayList<TreeNode> alreadySeen; 
	
	public boolean check() { 
	    alreadySeen = new ArrayList<TreeNode>(); 
	    try { 
	        isOK(myRoot); 
	        return true; 
	    } catch (IllegalStateException e) { 
	        return false; 
	    } 
	}
	
	private void isOK(TreeNode t) throws IllegalStateException{
		if (t != null){
			if (alreadySeen.contains(t)){
				throw new IllegalStateException ("A treeNode cannot appear more than once.");
			}
			alreadySeen.add(t);
			isOK(t.myLeft);
			isOK(t.myRight);
		}
	}
	
	/**
	 *  New in lab15: Support Building a Fibonacci Tree
	 */
	
	public static BinaryTree fibTree(int n) {
	    BinaryTree result = new BinaryTree();
	    result.myRoot = result.fibTreeHelper(n);
	    return result;
	}

	private TreeNode fibTreeHelper (int n){
		if (n==0 || n==1){
			return new TreeNode (n);
		} else {
			return new TreeNode((Integer)(fibTreeHelper(n-1).myItem) + (Integer)(fibTreeHelper(n-2).myItem), fibTreeHelper(n-1), fibTreeHelper(n-2));
		}
	}
	
	/**
	 *  New in lab15: Support Building an Expression Tree
	 */
	
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
	            if (expr.charAt(k) == '('){
	            	nesting++;
	            } else if (expr.charAt(k) == ')'){
	            	nesting--;
	            } else if ((expr.charAt(k) == '+' || expr.charAt(k) == '-') && nesting == 0){
	            	opPos = k;
	            } else if ((expr.charAt(k) == '*' || expr.charAt(k) == '/') && opPos == 0 && nesting == 0){
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
	        return new TreeNode (op, exprTreeHelper(opnd1), exprTreeHelper(opnd2)); // you fill this in
	    }
	}
	
	// replaces all occurrences of an expression involving only integers with the computed value.
	public void optimize(){
		optimizeHelper(myRoot);
	}
	
    private void optimizeHelper(TreeNode t) {
		if (t.myLeft != null && t.myRight != null){
			try{
				int a = Integer.parseInt((String) t.myLeft.myItem);
				int b = Integer.parseInt((String) t.myRight.myItem);
				if (t.myItem.equals("+")){
					// Remember to use equals!!!
					t.myItem = (String)("" + (a+b));
				} else if (t.myItem.equals("-")){
					t.myItem = (String)("" + (a-b));
				} else if (t.myItem.equals("*")){
					t.myItem = (String)("" + (a*b));
				} else{
					t.myItem = (String)("" + (a/b));
				}
				t.myLeft = null;
				t.myRight = null;
				optimizeHelper(myRoot);
			} catch (NumberFormatException e){
				optimizeHelper(t.myLeft);
				optimizeHelper(t.myRight);
			}
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

    	/**
    	 *  New in lab15: print(), println(),  
    	 */
        
    	private static final String indent1 = "    ";

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
    	

		public boolean isCompletelyBalanced() {
        	if (myLeft == null && myRight == null){
        		return true;
        	} else if (myLeft == null || myRight == null){
        		return false;
        	} else {
        		if (myLeft.height() == myRight.height()){
        			return (myLeft.isCompletelyBalanced()) && (myRight.isCompletelyBalanced());
        		}
        		return false;
        		
        	}
		}

		public int height() {
        	int bestSoFar = 0;
        	if (myLeft == null && myRight != null) {
		    	return myRight.height()+1;
		    } else if (myLeft != null && myRight == null){
		    	return myLeft.height()+1;
		    } else if (myLeft != null && myRight != null){
		        bestSoFar = Math.max(myLeft.height(), myRight.height());
		    }
        	return bestSoFar + 1;
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
    }
}
