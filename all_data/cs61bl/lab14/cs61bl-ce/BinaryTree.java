import java.util.*;

public class BinaryTree {

    private TreeNode myRoot;
    private ArrayList alreadySeen;

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
    	myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c",
    			new TreeNode("d", new TreeNode("e"), new TreeNode("f")), null));
    }
    
    public void fillSampleTree4() {
    	myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d"), new TreeNode("e")),
    			new TreeNode("c", new TreeNode("f"), new TreeNode("g")));
    }

    public static void main(String[] args) {
//        BinaryTree t;
//        t = new BinaryTree();
//        print(t, "the empty tree");
//        t.print();
//        System.out.println(t.height());
//        System.out.println(t.isBalanced());
//        t.fillSampleTree1();
//        print(t, "sample tree 1");
//        t.print();
//        System.out.println(t.height());
//        System.out.println(t.isBalanced());
//        t.fillSampleTree2();
//        print(t, "sample tree 2");
//        t.print();
//        System.out.println(t.height());
//        System.out.println(t.isBalanced());
//        t.fillSampleTree3();
//        print(t, "sample tree 3");
//        t.print();
//        System.out.println(t.height());
//        System.out.println(t.isBalanced());
//        t.fillSampleTree4();
//        print(t, "sample tree 4");
//        t.print();
//        System.out.println(t.height());
//        System.out.println(t.isBalanced());
    	BinaryTree t = new BinaryTree();
    	t.fibTree(7).print();
    	BinaryTree r = new BinaryTree();
    	r.exprTree("((a+(5*(a+b)))+(6*5))").optimize();
    	r.print();
    }
    
    private int height() {
    	if(myRoot == null) {
    		return 0;
    	}
    	if(myRoot.myLeft != null && myRoot.myRight != null) {
    		return heightHelper(myRoot);
    	}else if (myRoot.myLeft == null && myRoot.myRight != null){
    		return 1 + heightHelper(myRoot.myRight);
    	}else if (myRoot.myRight == null && myRoot.myLeft != null){
    		return 1 + heightHelper(myRoot.myLeft);
    	} else
    		return 0;
    }
    private static int heightHelper(TreeNode current) {
    	if (current == null) {
    		return 0;
    	}
    	else if (current.myLeft == null && current.myRight == null) {
        	return 1;
        } else{
            int bestOfLeft = 1;
            int bestOfRight = 1;
            bestOfLeft = Math.max(heightHelper(current.myLeft), bestOfLeft);
            bestOfRight = Math.max(heightHelper(current.myRight), bestOfLeft);
            return 1 + Math.max(bestOfLeft, bestOfRight);
            }  
        }
    
    private boolean isBalanced() {
    	if(myRoot == null) {
    		return true;
    	}else if(myRoot.myLeft != null && myRoot.myRight != null) {
    		return isBalancedHelper(myRoot);
    	}else if (myRoot.myLeft == null && myRoot.myRight != null){
    		return false;
    	}else if (myRoot.myRight == null && myRoot.myLeft != null){
    		return false;
    	} else
    		return true;
    }
    
    private static boolean isBalancedHelper(TreeNode current) {
    	if (current == null) {
    		return true;
    	} else if (current.myLeft == null && current.myRight == null) {
        	return true;
    	}else if (current.myLeft == null && current.myRight != null){
    		return false;
    	}else if (current.myRight == null && current.myLeft != null){
    		return false;
    	} else {
    		boolean bestOfLeft;
    		boolean bestOfRight;
    		bestOfLeft = isBalancedHelper(current.myLeft);
        	bestOfRight = isBalancedHelper(current.myRight);
        	return bestOfLeft && bestOfRight;
    	}
    }

    private static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
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
    
    private void isOK(TreeNode t) throws IllegalStateException {
    	if (alreadySeen.contains(t)) {
    		throw new IllegalStateException("node already traversed");
    	}else
    		if (t.myLeft != null) {
    			isOK(t.myLeft);
    		}if (t.myRight != null) {
    			isOK(t.myRight);
    		}
    		alreadySeen.add(t);
    }
    
    public void print() {
    	if (myRoot != null) {
    		myRoot.print(0);
    	}
    }
    
    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
    }

    private TreeNode fibTreeHelper (int n) {
    	TreeNode myNode = null;
    	if (n == 0) {
    		return new TreeNode(0);
    	} else if (n ==1) {
    		return new TreeNode(1);
    	}
    	else {
    		TreeNode tempLeft = fibTreeHelper(n - 1);
    		TreeNode tempRight = fibTreeHelper(n - 2);
    		myNode = new TreeNode(((Integer)tempLeft.myItem) + ((Integer)tempRight.myItem), tempLeft, tempRight);		
    	}
    	return myNode;
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
            	if(expr.charAt(k) == '('){
            		nesting++;	
            	}if(expr.charAt(k) == ')') {
            		nesting--;
            	}if(nesting == 0 && (expr.charAt(k) == '+' || expr.charAt(k) == '*')) {
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
    
    public void optimize() {
        this.myRoot = optimizeHelper(myRoot);
        }

        
    public TreeNode optimizeHelper(TreeNode optimizer) {
    	if (optimizer == null) {
    		return optimizer;
    	}
    	if (optimizer.myItem.equals("+")) {
    		if (isNumeric((String)optimizer.myLeft.myItem) && isNumeric((String)optimizer.myRight.myItem)) {
    			optimizer.myItem = (Integer)(optimizer.myLeft.myItem) + (Integer)(optimizer.myRight.myItem);
    			optimizer.myLeft = null;
    			optimizer.myRight = null;
    			return optimizer;
    		}if (optimizer.myLeft.myItem.equals("*") || optimizer.myLeft.myItem.equals("+")){
    			return optimizeHelper(optimizer.myLeft);
            }if (optimizer.myRight.myItem.equals("*") || optimizer.myRight.myItem.equals("+")){
            	return optimizeHelper(optimizer.myRight);
            }
        }if (optimizer.myItem.equals("*")) {
        	if (isNumeric((String)optimizer.myLeft.myItem) && isNumeric((String)optimizer.myRight.myItem)) {
        		optimizer.myItem = (Integer)(optimizer.myLeft.myItem) * (Integer)(optimizer.myRight.myItem);
        		optimizer.myLeft = null;
        		optimizer.myRight = null;
        		return optimizer;
        	}if (optimizer.myLeft.myItem.equals("*") || optimizer.myLeft.myItem.equals("+")){
        		return optimizeHelper(optimizer.myLeft);
            }if (optimizer.myRight.myItem.equals("*") || optimizer.myRight.myItem.equals("+")){
            	return optimizeHelper(optimizer.myRight);
            }
        return optimizer;
        }
		return optimizer;
    }
    
    public static boolean isNumeric(String str)  
    {  
      try  
      {  
        double d = Double.parseDouble(str);  
      }  
      catch(NumberFormatException nfe)  
      {  
        return false;  
      }  
      return true;  
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
    }
}
