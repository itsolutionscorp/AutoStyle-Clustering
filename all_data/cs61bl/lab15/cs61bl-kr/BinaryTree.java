import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

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
    public int height() {
		if (myRoot != null) {
			return myRoot.height();
		}
		return 0;
	}
    
    public void print() {
        if (myRoot != null) {
            myRoot.print(0);
        }
    }
  
    public void printPreorder() {
        if (myRoot == null) {
            System.out.println("(empty tree)");
        } else {
            myRoot.printPreorder();
            System.out.println();
        }
    }
    
    public boolean isCompletelyBalanced() {
    	if (myRoot == null) {
    		return true;
    	} else if (myRoot != null) {
    		return myRoot.isCompletelyBalanced();
    	} return false;
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
    public void filEvenTree() {
    	myRoot = new TreeNode("n", 
    			new TreeNode("i", new TreeNode("c", new TreeNode("o"), new TreeNode("l")),
    			new TreeNode("e", new TreeNode("x"), new TreeNode("x"))),
    			new TreeNode("x", new TreeNode("n", new TreeNode("g"), new TreeNode("u")),
    	    	new TreeNode("y", new TreeNode("e"), new TreeNode("n"))));
    }
    
    private void isOK(TreeNode t) throws IllegalStateException {
    	if (myRoot !=null) {
    		myRoot.isOkay(t); 
    	}
    }
    
    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
    }

    private TreeNode fibTreeHelper (int n) {
    	if(n == 0) {
    		myRoot.myItem= 0; 
    		return myRoot; 
    	}
    	if(n == 1) {
    		myRoot.myItem = 1; 
    		return myRoot; 
    	}
    	else {
    		myRoot.myLeft = fibTreeHelper(n-1); 
    		myRoot.myRight = fibTreeHelper(n-2); 
    	}
    	return myRoot; 
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
    	Stack<TreeNode> fringe = new Stack<TreeNode>(); 
    	TreeNode current = null;
    	if (expr.length() < 1 || expr == null) {
    		return null; 
    	}
        if (expr.charAt(0) != '(') {
        		current = new TreeNode(expr.charAt(0)); 
        		fringe.push(current); 
        } else {
            // expr is a parenthesized expression.
            // Strip off the beginning and ending parentheses,
            // find the main operator (an occurrence of + or * not nested
            // in parentheses, and construct the two subtrees.
            int nesting = 0;
            int opPos = 0;
            for (int k = 1; k < expr.length() - 1; k++) {
            	if (expr.charAt(k) == '(') {
            		nesting ++; 
            	}
            	if (expr.charAt(k) == ')') {
            		nesting --; 
            	}
            	if (expr.charAt(k) == '+' || expr.charAt(k) == '*' && nesting == 0) {
            		opPos = k; 
            	}
            	String left = expr.substring(1, opPos); 
            	String right = expr.substring(opPos+1, expr.length()-1); 
            	exprTreeHelper(left); 
            	exprTreeHelper(right); 
            }
            String opnd1 = expr.substring(1, opPos);
            String opnd2 = expr.substring(opPos + 1, expr.length() - 1);
            String op = expr.substring(opPos, opPos + 1);
            System.out.println("expression = " + expr);
            System.out.println("operand 1  = " + opnd1);
            System.out.println("operator   = " + op);
            System.out.println("operand 2  = " + opnd2);
            System.out.println();
        }
		return current;  
    }
    
    public void optimize() {
    	BinaryTree result = new BinaryTree(); 
    	optimizeHelper(result.myRoot); 
    }

    public void optimizeHelper(TreeNode t) {
    	if (t.myLeft != null && t.myRight != null) {
    		if (t.myItem == '+') {
    		t.myItem = t.myLeft.myItem + t.myRight.myItem; 
    	}
    		if (t.myItem == '*') {
    			t.myItem = t.myLeft.myItem*t.myRight.myItem; 
    		}
    	
    }


    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
//        t.filEvenTree();
//        print(t, "fillEvenTree");
//        System.out.println(t.isCompletelyBalanced());
//        System.out.println(t.height());
//        t.fillSampleTree1();
//        print(t, "sample tree 1");
//        System.out.println(t.isCompletelyBalanced());      
//        t.height();
//        System.out.println(t.height());
//        t.fillSampleTree2();
//        System.out.println(t.isCompletelyBalanced());
//        print(t, "sample tree 2");
//        t.height();
//        System.out.println(t.height());
//        t.fillSampleTree3();
//        System.out.println(t.isCompletelyBalanced());
//        print(t, "sample tree 3");
//        t.height();
//        System.out.println(t.height());
          t = exprTree("((a+(5*(9+1)))+(6*5))");
          t.printPreorder();
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

        public boolean isCompletelyBalanced() {
        	if (myLeft != null && myRight != null) {
        		if (myLeft.height() == myRight.height()) {
        			if (myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced()) {
        				return true;
        			}
        		}
        	} else if (myLeft == null && myRight == null) {
        		return true;
        	} else {
        		return false;
        	}
			return false;
		}

		public TreeNode(Object obj, TreeNode left, TreeNode right) {
            myItem = obj;
            myLeft = left;
            myRight = right;
        }
        int bestSoFar = 1;
        
  private static final String indent1 = "    ";
        
        private void print(int indent) {
        	indent++;     	
            if (myRight != null) {
            	myRight.print(indent);
           // 	println(myRight,indent);
            }
            println (myItem, indent);
            if(myLeft != null) {
        		myLeft.print(indent);	
     //   		println(myLeft,indent);
        	}
         
        }
        private static void println(Object obj, int indent) {
            for (int k=0; k<indent; k++) {
                System.out.print(indent1);
            }
            System.out.println(obj);
        }
        
        private int height() {
            if (myLeft == null && myRight == null) {
            	return bestSoFar;
            }
            if (myLeft != null){
            bestSoFar = Math.max(myLeft.height()+1, bestSoFar);
            }
           
            if (myRight != null) {
            bestSoFar = Math.max(myRight.height()+1, bestSoFar);
            }
            return bestSoFar;
        }
    	    
        private ArrayList alreadySeen;
      
        private boolean isOkay(TreeNode t) {
        	return alreadySeen.contains(t); 
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
