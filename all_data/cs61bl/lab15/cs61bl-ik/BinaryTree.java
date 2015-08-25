import java.util.*;
public class BinaryTree implements Iterable{

    private TreeNode myRoot;

    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
    }
    
    public Iterator<TreeNode> iterator() {
    	return new DepthFirstIterator();
    }
    public class DepthFirstIterator implements Iterator<TreeNode> {

		private Stack<TreeNode> fringe = new Stack<TreeNode>();

		public DepthFirstIterator() {
		    if (myRoot != null) {
		        fringe.push (myRoot);
		    }
		}

		public boolean hasNext() {
		    return !fringe.empty();
		}

		public TreeNode next() {
		    /*if (!hasMoreElements()) {
		        throw new NoSuchElementException("tree ran out of elements");
		    }*/
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
    private void isOK(TreeNode t) throws IllegalStateException{
		ArrayList<TreeNode> list = new ArrayList<TreeNode>();
			
		Iterator<TreeNode> i = iterator();
		while (i.hasNext()){
			TreeNode n = i.next();
			if (list.contains(n)){
				throw new IllegalStateException("traversed through node more than once");
			}else{
				list.add(n);
			}
		}		
	}
	
	public boolean check() {  
	    try { 
	        isOK(myRoot); 
	        return true; 
	    } catch (IllegalStateException e) { 
	        return false; 
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
            return new TreeNode(Character.toString(expr.charAt(0))); // you fill this in
           
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
            		nesting++;
            	}
            	if (expr.charAt(k) == ')') {
            		nesting--;
            	}
            	if ((expr.charAt(k) == '+' || expr.charAt(k) == '*') && nesting== 0) {
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
            
            return new TreeNode(op, exprTreeHelper(opnd1), exprTreeHelper(opnd2)); // you fill this in
        }
    }
    public boolean isNumeric(String s) {  
        return s.matches("[-+]?\\d*\\.?\\d+");  
    }  
    public void optimize(){
    	optimizeHelper(myRoot);
    }
    
    private void optimizeHelper(TreeNode t){
    	int left = 0;
    	int right = 0; 
    	if (t.myLeft == null || t.myRight == null){
    		return; 
    	}
    	optimizeHelper(t.myLeft);
    	optimizeHelper(t.myRight);
		try {
			left = Integer.parseInt((String) t.myLeft.myItem);
			right = Integer.parseInt((String) t.myRight.myItem);
			if (t.myItem.equals("*")) {
				t.myItem = left * right;
			} else {
				t.myItem = left + right;
			}
			t.myItem = t.myItem.toString();
			t.myLeft = null;
			t.myRight = null;
		} catch (NumberFormatException e) {
		}
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
     	if (n == 0 || n == 1){
    		return new TreeNode(n); 
    	} return new TreeNode ((((Integer)fibTreeHelper(n-1).myItem) + 
    							((Integer)fibTreeHelper(n-2).myItem)), 
    							fibTreeHelper(n-1), fibTreeHelper(n-2));
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
    	if (myRoot != null) {
    		return myRoot.height();
    	}return 0;
    }
    public boolean isCompletelyBalanced() {
    	if (myRoot != null) {
    		return myRoot.isCompletelyBalanced();
    	}return true;
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
    public void fillSampleTree4() {                                                          //balanced
    	myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d"), new TreeNode("e")),
    			new TreeNode("c", new TreeNode("f"), new TreeNode("g")));
    }
    public void fillSampleTree5() {
    	myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("q", new TreeNode("t"), new TreeNode("m")), new TreeNode("w")), new TreeNode("c",
    			new TreeNode("d", new TreeNode("e", null, new TreeNode("p")), new TreeNode("f")), null));
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
        print(t, "sample tree 3");
        t.fillSampleTree4(); 
        t.print();
        BinaryTree t2;
        System.out.println("-------------------------");
        fibTree(6).print();
        System.out.println("-------------------------");
        BinaryTree t3;
//        System.out.println(t3.isNumeric("t"));
//        System.out.println(t3.isNumeric("12"));
        t3 = exprTree("((a+(5*(a+b)))+(6*5))");
        t3.optimize();
        t3.print();
        System.out.println("-------------------------");
        BinaryTree t4;
        t4 = exprTree("((a+(5*(9+1)))+(6*5))");
        t4.optimize();
        t4.print();
        
       
        
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
        	if (myLeft == null && myRight == null) {
        		return 1;
        	} else {
        		int bestSoFar = 1;
        		if (myLeft == null) {
        			bestSoFar = Math.max(myRight.height() + 1, bestSoFar);
        		} else if (myRight == null) {
        			bestSoFar = Math.max(myLeft.height() + 1, bestSoFar);
        		} else {
        			bestSoFar = Math.max(Math.max(myLeft.height() + 1, myRight.height() + 1), bestSoFar);
        	}return bestSoFar;
        	
        }
        }
        private boolean isCompletelyBalanced() {
        	if (myLeft == null && myRight == null) {
        		return true;
        	} else {
        		if (myLeft.height() == myRight.height()) {
        		  return (myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced());
        	}return false;
        	}
        }
    }
}
