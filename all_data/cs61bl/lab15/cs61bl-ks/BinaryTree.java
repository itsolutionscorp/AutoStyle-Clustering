import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinaryTree {

    private TreeNode myRoot;

    public BinaryTree() {
        myRoot = null;
    }
    public void print() {
        if (myRoot != null) {
            myRoot.print(0);
        }
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
    	TreeNode node = new TreeNode(0);
    	if (n == 0) {
    		node.myItem = 0;
    	} else if (n == 1) {
    		node.myItem = 1;
    	} else {
    		node.myLeft = fibTreeHelper(n-1);
    		node.myRight = fibTreeHelper(n-2);
    		node. myItem = (int) node.myLeft.myItem + (int) node.myRight.myItem;
    	}
    	return node;
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
            		nesting += 1;
            	}
            	if (expr.charAt(k) == ')') {
            		nesting -= 1;
            	}
            	if (expr.charAt(k) == '+' || expr.charAt(k) == '-' || expr.charAt(k) == '*' || expr.charAt(k) == '/' || expr.charAt(k) == '%') {
            		if (nesting == 0) {
            			opPos = k;
            		}
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
    	if (myRoot != null) {
    		myRoot.optimize();
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
        myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c", new TreeNode("D", new TreeNode("E"), new TreeNode("F")), null));
    }
    
    public void fillSampleTree4() {
    	myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("c", new TreeNode("d", new TreeNode("E"), null), null), new TreeNode("f")), new TreeNode("g"));
    }
    
    public void fillSampleTree5() {
    	myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d"), new TreeNode("e")), new TreeNode("c", new TreeNode("f"), new TreeNode("g")));
    }
    public void fillSampleTree6() {
        myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c", new TreeNode("d"), new TreeNode("e")));
    }
    
   
    
    private int height() {
    	if (myRoot != null){
    		return myRoot.height();
    	}
    	return 0;
    }
    
    private boolean isCompletelyBalanced() {
    	if (myRoot != null) {
    		return myRoot.isCompletelyBalanced();
    	}
    	return true;
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
    	if (t!=null) {
    		DepthFirstIterator iter = new DepthFirstIterator(); 
    		t.isOK(alreadySeen, iter);
    	}    	
    }
    
    
    public class DepthFirstIterator implements Iterator {

    	private Stack<TreeNode> fringe = new Stack<TreeNode>();

    	public DepthFirstIterator() {
    	    if (myRoot != null) {
    	        fringe.push (myRoot);
    	    }
    	}

    	public boolean hasNext() {
    	    return !fringe.empty();
    	}

    	public Object next() {
    	    if (!hasNext()) {
    	        throw new NoSuchElementException("tree ran out of elements");
    	    }
    	    TreeNode node = (TreeNode) fringe.pop();
    	    if (node.myRight != null) {
    	        fringe.push(node.myRight);
    	    }
    	    if (node.myLeft != null) {
    	        fringe.push(node.myLeft);
    	    }
    	    return node;
    	}

        // We've decided not to use it for this example
        public void remove(){
            throw new UnsupportedOperationException();
        }
    }
    
    public static void main(String[] args) {
    	//tests for lab 14
        BinaryTree t;
        t = new BinaryTree();
        print(t, "the empty tree");
        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree1();
        print(t, "sample tree 1");
        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree2();
        print(t, "sample tree 2");
        t.fillSampleTree3();
        System.out.println(t.isCompletelyBalanced());
        print(t, "sample tree 3");
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree4();
        print(t, "sample tree 4");
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree5();
        System.out.println(t.isCompletelyBalanced()); 
        
        
        //Tests for lab 15
        t.fillSampleTree6();
        t.print();
        System.out.println(t.check()); 
        t = fibTree(0);
        System.out.println("fib tree 0");
        t.print();
        System.out.println("fib tree 1");
        t = fibTree(1);
        t.print();
        System.out.println("fib tree 2");
        t = fibTree(2);
        t.print();
        System.out.println("fib tree 5");
        t = fibTree(5);
        t.print(); 
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
        
        
        private int height(){
        
        	if (myLeft == null && myRight == null) {
        		return 1;
        	}
        	if (myLeft != null && myRight == null){
        		return 1 + myLeft.height();     	}
        	if (myRight != null && myLeft == null){
        		return 1 + myRight.height();
        	} else {
        		return 1 + Math.max(myLeft.height(), myRight.height());
        	}
        	
        }
        
        private boolean isCompletelyBalanced() {
        	if (myLeft == null && myRight == null) {
        		return true;
        	} 
        	if (myLeft != null && myRight == null){
        		return false;
        	}
        	if (myRight != null && myLeft == null){
        		return false;
        	} else {
        		return (myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced());
        	}
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
            if (myRight != null){
            	myRight.print(indent+1);
            }
            
            println (myItem, indent);
            // TODO your code here
            if (myLeft != null){
            	myLeft.print(indent+1);
            }
        }

        private static void println(Object obj, int indent) {
            for (int k=0; k<indent; k++) {
                System.out.print(indent1);
            }
            System.out.println(obj);
        }
        
        private void isOK(ArrayList seen, DepthFirstIterator iter) throws IllegalStateException {	
        	while (iter.hasNext()) {
        		TreeNode temp = (TreeNode) iter.next();
        		//seen.add(temp);
        		if (seen.contains(temp)) {
        			throw new IllegalStateException();
        		}
        		seen.add(temp);
        		
        	}        	
        }
        
        private void optimize() {
        	if (myLeft != null) {
        		myLeft.optimize();
        	}
        	if (myRight != null) {
        		myRight.optimize();
        	}
        	if (myLeft != null && myRight != null) {
        		//System.out.println(myItem);
        		if (isInt(myLeft.myItem.toString()) && isInt(myRight.myItem.toString())) {
        			if (myItem.toString().equals("+")) {
        				myItem = Integer.parseInt(myLeft.myItem.toString()) + Integer.parseInt(myRight.myItem.toString());
        			} else if (myItem.toString().equals("-")) {
        				myItem = Integer.parseInt(myLeft.myItem.toString()) - Integer.parseInt(myRight.myItem.toString());;
        			} else if (myItem.toString().equals("*")) {
        				myItem = Integer.parseInt(myLeft.myItem.toString()) * Integer.parseInt(myRight.myItem.toString());
        			} else if (myItem.toString().equals("/")) {
        				myItem = Integer.parseInt(myLeft.myItem.toString()) / Integer.parseInt(myRight.myItem.toString());
        			} else if (myItem.toString().equals("%")) {
        				myItem = Integer.parseInt(myLeft.myItem.toString()) % Integer.parseInt(myRight.myItem.toString());
        			}
        			myLeft = null;
        			myRight = null;
        		}
        	}
        }
        
        private boolean isInt(String s) {
        	try{
                int x = Integer.parseInt(s);
                return true;
        	} catch(NumberFormatException e) {
        		return false;
        	}
        }
    }
}
