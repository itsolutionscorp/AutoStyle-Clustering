import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinaryTree<T> {

    private TreeNode myRoot;
    private ArrayList alreadySeen;

    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
    }
    
    public BinaryTree(ArrayList<T> preorder, ArrayList<T> inorder) {
    	
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
    	myRoot = new TreeNode("A", new TreeNode("B"), new TreeNode("C", new TreeNode("D", new TreeNode("E"), new TreeNode("F")), null));
    }
    
    public void fillCompletelyBalancedTree() {
    	myRoot = new TreeNode("A", new TreeNode("B", new TreeNode("C"), new TreeNode("D")), new TreeNode("B", new TreeNode("C"), new TreeNode("D")));
    }
    
    public void fillCompletelyBalancedTree2() {
    	myRoot = new TreeNode("A", new TreeNode("B"), new TreeNode("C"));
    }

    public static void main(String[] args) {
    	/*
        BinaryTree t;
        t = new BinaryTree();
        print(t, "the empty tree");
        t.fillSampleTree1();
        print(t, "sample tree 1");
        t.fillSampleTree2();
        print(t, "sample tree 2");
        t.fillSampleTree3();
        print(t, "sample tree 3");
        System.out.println(t.isCompletelyBalanced());
        t.fillCompletelyBalancedTree();
        System.out.println(t.isCompletelyBalanced());
        t.fillCompletelyBalancedTree2();
        System.out.println(t.isCompletelyBalanced());
        BinaryTree t2 = fibTree(3);
        t2.print();
        */
        String expr = "((a+(5*(9+1)))+(6*5))";
        BinaryTree t3 = exprTree(expr);
        t3.optimize();
        t3.print();
    }

    private static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
    }
    
    public int height() {
    	if (myRoot == null) {
    		return 0;
    	} else {
    		return myRoot.height();
    	}
    }
    
    public boolean isCompletelyBalanced() {
    	if (myRoot == null) {
    		return true;
    	} else {
    		return myRoot.isCompletelyBalanced();
    	}
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
    
    private void isOK(TreeNode t) throws IllegalStateException {
    	if (alreadySeen.contains(t.myItem)) {
    		throw new IllegalStateException("Check Failed");
    	} else {
    		alreadySeen.add(t.myItem);
    		if (t.myLeft != null) {
    			isOK(t.myLeft);
    		}
    		if (t.myRight != null) {
    			isOK(t.myRight);
    		}
    	}
    }
    
    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
    }

    private TreeNode fibTreeHelper (int n) {
    	if (n == 0) {
    		return new TreeNode(0);
    	} if (n == 1) {
    		return new TreeNode(1);
    	} else {
    		TreeNode tn = new TreeNode(null, fibTreeHelper(n-1), fibTreeHelper(n-2));
    		tn.myItem = (Integer) tn.myLeft.myItem + (Integer) tn.myRight.myItem;
    		return tn;
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
                if (expr.charAt(k) == '+' || expr.charAt(k) == '*') {
                	if (nesting == 0) {
                		opPos = k;
                	}
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
            return new TreeNode(op, exprTreeHelper(opnd1), exprTreeHelper(opnd2));// you fill this in
        }
    }
    
    public void optimize() {
    	if (myRoot.myLeft != null && myRoot.myRight != null) {
    		while (myRoot.optimizeHelper() >= 1) {
    			myRoot.optimizeHelper();
    		}
    		myRoot.optimizeHelper();
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
        	if (myLeft == null && myRight ==  null) {
        		return 1;
        	} else if (myLeft == null) {
        		return 1 + myRight.height();
        	} else if (myRight == null) {
        		return 1 + myLeft.height();
        	} else {
        		return 1 + Math.max(myRight.height(), myLeft.height());
        	}
        }
        
        private boolean isCompletelyBalanced() {
        	if (myLeft == null && myRight ==  null) {
        		return true;
        	} else if (myLeft == null && myRight != null) {
        		return false;
        	} else if (myRight == null && myLeft != null) {
        		return false;
        	} else {
        		return myRight.isCompletelyBalanced() && myLeft.isCompletelyBalanced();
        	}
        }
        
      //In TreeNode
        private static final String indent1 = "    ";

        private void print(int indent) {
            if (myRight != null) {
            	myRight.print(indent + 1);
            }
            println (myItem, indent);
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
        
        public int optimizeHelper() {
        	int counter = 0;
        	if (this.myRight == null || this.myLeft == null) {
        		
        	} else {
        		try {
        			int itemL = Integer.parseInt((String) this.myLeft.myItem);
        			int itemR = Integer.parseInt((String) this.myRight.myItem);
        			if (this.myItem.equals("+")) {
        				this.myItem = itemL + itemR;
        				this.myItem = this.myItem.toString();
        				this.myRight = null;
        				this.myLeft = null;
        				counter++;
        			} else if (this.myItem.equals("*")) {
        				this.myItem = itemL * itemR;
        				this.myItem = this.myItem.toString();
        				this.myRight = null;
        				this.myLeft = null;
        				counter++;
        			}
        		} catch (NumberFormatException e){
        			this.myRight.optimizeHelper();
        			this.myLeft.optimizeHelper();
        		}
        	}
        	return counter;
        }
        
    }
    
 // This code gets put inside the BinaryTree class.

 // Method for the BinaryTree class
 public Iterator iterator(){
     return new InorderIterator();
 }

 // Inner class inside of the BinaryTree class.
 // Also, it uses java.util.Iterator and java.util.Stack.
 private class InorderIterator implements Iterator<T> {
     private Stack<TreeNode> nodeStack;
     private TreeNode currentNode;
     
     public InorderIterator() {
         nodeStack = new Stack<TreeNode>();
         currentNode = myRoot;
     }
     
     public boolean hasNext() {
         return !nodeStack.isEmpty() || (currentNode != null);
     }
     
     public T next() {
         TreeNode nextNode = null;
         
         // find leftmost node with no left child
         while (currentNode != null) {
             nodeStack.push(currentNode);
             currentNode = currentNode.myLeft;
         }
         
         // get leftmost node, then move to its right subtree
         if (!nodeStack.isEmpty()) {
             nextNode = nodeStack.pop();
             assert nextNode != null;    // since nodeStack was not empty before the pop
             currentNode = nextNode.myRight;
         } else {
             throw new NoSuchElementException();
         }

         return (T) nextNode.myItem; 
     }
     
     public void remove() {
         throw new UnsupportedOperationException();
     }

 }
    
}
