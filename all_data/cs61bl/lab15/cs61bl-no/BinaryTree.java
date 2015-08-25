import java.util.*;

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
    
    public int height(){
    	if (myRoot == null){
    		return 0;
    	}
    	return myRoot.height();
    }
    
    public boolean isCompletelyBalance(){
    	if (myRoot == null){
    		return true;
    	}
    	return myRoot.isCompletelyBalance();
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

	private ArrayList alreadySeen;

	private void isOK(TreeNode t) throws IllegalStateException {
		if (t == null) {
			return;
		}
		if (alreadySeen.contains(t)) {
			throw new IllegalStateException();
		}
		alreadySeen.add(t);
		if (t.myLeft != null) {
			isOK(t.myLeft);
		}
		if (t.myRight != null) {
			isOK(t.myRight);
		}
	}
    
	public static BinaryTree fibTree(int n) {
	    BinaryTree result = new BinaryTree();
	    result.myRoot = result.fibTreeHelper(n);
	    return result;
	}
    
    private TreeNode fibTreeHelper(int n) {
		if (n == 0) {
			return new TreeNode(0);
		}
		if (n == 1) {
			return new TreeNode(1);
		}
		TreeNode left = fibTreeHelper(n - 1);
		TreeNode right = fibTreeHelper(n - 2);
		return new TreeNode( (Integer)left.myItem + (Integer) right.myItem, left, right);
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
            	if (expr.charAt(k) == '(') {
					nesting++;
				}
				if (expr.charAt(k) == ')') {
					nesting--;
				}
				if (nesting == 0
						&& (expr.charAt(k) == '*' || expr.charAt(k) == '+')) {
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
            TreeNode left = exprTreeHelper(opnd1);
			TreeNode right = exprTreeHelper(opnd2);
            return new TreeNode(op, left, right);
        }
    }
    
    public void optimize() {
		BinaryTree.optimizeHelper(myRoot);
	}

	private static void optimizeHelper(TreeNode current) {
		int left, right;
		if (current.myLeft == null && current.myRight == null) { 
			return;
		}
		optimizeHelper(current.myLeft);
		optimizeHelper(current.myRight);
		//System.out.println(current.myLeft.myItem);
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
			// Handle characters in this case
		}
	}

    public void fillSampleTree1() {
        myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
    }

    public void fillSampleTree2() {
        myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d",
                new TreeNode("e"), new TreeNode("f")), null), new TreeNode("c"));
    }
        
    public void fillSampleTree3(){
    	myRoot = new TreeNode("A", new TreeNode("B"), 
    			new TreeNode("C", new TreeNode("D", new TreeNode("E"), new TreeNode("F")), null));
    	}
    
    public void fillSampleTree4() {
		myRoot = new TreeNode("A");
	}


	public void fillSampleTree5() {
		myRoot = new TreeNode("A", new TreeNode("B", new TreeNode("D"),
				new TreeNode("E")), new TreeNode("C", new TreeNode("F"), null));
	}

	public void fillSampleTree6() {
		myRoot = new TreeNode("A", new TreeNode("B", new TreeNode("D"),
				new TreeNode("E")), new TreeNode("C", new TreeNode("F"),
				new TreeNode("G")));
	}
	
	public void fillSampleTree7() {
		TreeNode a = new TreeNode("D", null, null);
		myRoot = new TreeNode("A", new TreeNode("B", null, a), new TreeNode("C", a, null));
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
        print(t,  "sample tree 3");
        t.fillSampleTree4();
        print(t,  "sample tree 4");
        t.fillSampleTree5();
        print(t,  "sample tree 5");
        t.fillSampleTree6();
        print(t,  "sample tree 6");
        t.fillSampleTree7();
        print(t,  "sample tree 7");
        
        System.out.println();
        fibTree(2).print();
        System.out.println();

        
        BinaryTree a = exprTree("((a+(5*(9+1)))+(6*5))");
        a.print();
        
        BinaryTree b = exprTree("((a+(5*(9+1)))+(6*5))");
        b.optimize();
        b.print();

    }

    private static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalance());
        System.out.println(t.check());
        t.print();
        
    }

    private static class TreeNode {

        public Object myItem;
        public TreeNode myLeft;
        public TreeNode myRight;
        private static final String indent1 = "    ";
       // private ArrayList alreadySeen; 
        
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
        
        private int height(){

        	if (myLeft == null && myRight == null){
        		return 1;
        	} 
        	
        	if (myLeft == null){
        		return myRight.height() +1;
        	}
        	
        	if(myRight == null){
        		return myLeft.height() + 1;
        	}
        	
            return Math.max(myLeft.height(), myRight.height()) + 1;
        }
        
        private boolean isCompletelyBalance(){
        	if (myLeft == null && myRight == null){
        		return true;
        	}
        	
        	if (myLeft != null && myRight == null){
        		return false;
        	}
        	if (myRight != null && myLeft == null){
        		return false;
        	}
        	if(myLeft.height() != myRight.height()){
        		return false;
        	} 
        	
        	return myLeft.isCompletelyBalance() && myRight.isCompletelyBalance();
        }
        
        private void print(int indent) {
            if(myRight !=null){
            	myRight.print(indent+1);
            }
            println (myItem, indent);
            if(myLeft != null){
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
