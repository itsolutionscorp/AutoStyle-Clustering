import java.util.*;

public class BinaryTree {

    private TreeNode myRoot;
    private ArrayList<TreeNode> alreadySeen;

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

    public int fib(int n) {
    	if (n == 0 || n == 1) {
    		return n;
    	} else {
    		return fib(n-1) + fib(n-2);
    	}
    }

    private TreeNode fibTreeHelper (int n) {
    	int rootNode = fib(n);
        if (n == 0 || n == 1)
            return new TreeNode(n);
        else
            return new TreeNode(rootNode, fibTreeHelper(n-1),fibTreeHelper(n-2));
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
        	return new TreeNode(expr.charAt(0));
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
            	} else if (expr.charAt(k) == ')') {
            		nesting--;
            	} else if ((expr.charAt(k) == '+' || expr.charAt(k) == '*') && nesting == 0) {
                	opPos = k;
                }
            }
            if (opPos == 0) {
            	return new TreeNode(expr);
            }
            String opnd1 = expr.substring(1, opPos);
            String opnd2 = expr.substring(opPos + 1, expr.length() - 1);
            String op = expr.substring(opPos, opPos + 1);
            System.out.println("expression = " + expr);
            System.out.println("operand 1  = " + opnd1);
            System.out.println("operator   = " + op);
            System.out.println("operand 2  = " + opnd2);
            System.out.println();
            TreeNode result = new TreeNode(op);
            if (opnd1 != null) {
            	 result.myLeft = exprTreeHelper(opnd1);
            }
            if (opnd2 != null) {
            	result.myRight = exprTreeHelper(opnd2);
            }
            return result;
        }
    }
    
    private void isOK(TreeNode t) {
    	if (alreadySeen.contains(t)) {
    		throw new IllegalStateException();
    	} else {
    		if (t != null) {
    			alreadySeen.add(t);
        		if (t.myLeft != null) {
        			isOK(t.myLeft);
        		} else if (t.myRight != null) {
        			isOK(t.myRight);
        		} else {
        			return;
        		}
    		}
    	}
    }
    
    public void optimize() {
    	this.myRoot.optimizeHelper();
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
    
    public void printTree(){
    	if (myRoot == null)
    		System.out.println();
    	else
    		myRoot.print(0);
    }
    
    public void fillSampleTree1() {
        myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
    }

    public void fillSampleTree2() {
        myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d",
                new TreeNode("e"), new TreeNode("f")), null), new TreeNode("c"));
    }
    public void fillSampleTree3(){
    	myRoot = new TreeNode("a",new TreeNode("b"), new TreeNode("c", new TreeNode("d", new TreeNode("e"), new TreeNode("f")), null));
    	}
    
    public int height(){
    	if(myRoot !=null){
			return myRoot.height();
		}
		return 0;
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
    
    public boolean isCompletelyBalanced(){
    	if(myRoot !=null){
    		return myRoot.isCompletelyBalance();
    	}
    	return true;
    }
    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        print(t, "the empty tree");
        System.out.println("The height of fill empty is"+t.height());
        System.out.println("balanced tree? "+t.isCompletelyBalanced());
        
        
        t.fillSampleTree1();
        print(t, "sample tree 1");
        System.out.println("The height of fill sample1 is"+t.height());
        System.out.println("balanced tree? "+t.isCompletelyBalanced());
        
        t.fillSampleTree2();
        print(t, "sample tree 2");
        System.out.println("The height of fill sample2 is"+t.height());
        System.out.println("balanced tree? "+t.isCompletelyBalanced());
        
        t.fillSampleTree3();
        print(t,"sample tree 3");
        System.out.println("The height of fill sample3 is"+t.height());
        System.out.println("balanced tree? "+t.isCompletelyBalanced());
        System.out.println(t.check());
        
        BinaryTree f = fibTree(6);
        print(f,"Fib Tree");
        System.out.println("The height of fibTree is"+f.height());
        System.out.println("balanced tree? "+f.isCompletelyBalanced());
        System.out.println(f.check());
       
        BinaryTree q = exprTree("((3+5)+((b*2)*2))");
        print(q,"Expr Tree");
        System.out.println("The height of exprTree is"+q.height());
        System.out.println("balanced tree? "+q.isCompletelyBalanced());
        System.out.println(q.check());
        
        q.optimize();
        print(q,"Optimized Expr Tree");
        System.out.println("The height of optimized exprTree is"+q.height());
        System.out.println("balanced tree? "+q.isCompletelyBalanced());
        System.out.println(q.check());
       
    }

    private static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
        t.printTree();
    }

    private static class TreeNode {

        public Object myItem;
        public TreeNode myLeft;
        public TreeNode myRight;

        public TreeNode(Object obj) {
            myItem = obj;
            myLeft = myRight = null;
        }

        public boolean isCompletelyBalance() {
			if(myRight ==null && myLeft ==null){
				return true;
			}else if(myRight !=null && myLeft !=null){
				if(myRight.height()==myLeft.height()){
					return true;
				}
			}
			return false;
		}

		public int height() {
			int bestSoFar = 0;
		    if (myRight ==null && myLeft ==null) {
		    	bestSoFar ++;
		    }else if(myRight ==null && myLeft !=null){
		    	bestSoFar = bestSoFar + myLeft.height()+1;
		    }else if(myRight !=null && myRight ==null){
		    	bestSoFar = bestSoFar+ myRight.height()+1;
		    }else{
		    	bestSoFar = Math.max(myRight.height(), myLeft.height())+1;
		    }
		    return bestSoFar;
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
        	if (myRight != null) {
        		myRight.print(indent+1);
        	}
            println(myItem, indent);
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
        
        public boolean isInteger(String s){
        	String digits = "0123456789";
        	for (int i = 0; i < s.length(); i++)
        		if (!digits.contains(s.substring(i,i+1)))
        			return false;
        	return true;
        }
        
        public void optimizeHelper() {
        	int leftNode = 0;
        	int rightNode = 0;
        	if (myLeft == null || myRight == null)
        		return;
        	if (myLeft.myItem.toString().equals("+") || myLeft.myItem.toString().equals("*"))
        		myLeft.optimizeHelper();
        	if (myRight.myItem.toString().equals("+") || myRight.myItem.toString().equals("*"))
        		myRight.optimizeHelper();
        	if (isInteger(myLeft.myItem.toString()) && isInteger(myRight.myItem.toString())){
        		System.out.println("Strings: " + myLeft.myItem.toString() + "  " + myRight.myItem.toString());
        		leftNode = Integer.parseInt(myLeft.myItem.toString());
        		rightNode = Integer.parseInt(myRight.myItem.toString());
        		myLeft = null;
        		myRight = null;
        		if (myItem.toString().equals("*"))
        			myItem = "" + (leftNode*rightNode);
        		else
        			myItem = "" + (leftNode+rightNode);
        	}
        	return;
       	}
        
    }
}
