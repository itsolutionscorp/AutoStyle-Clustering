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
                } else if (expr.charAt(k) == ')') {
                    nesting -= 1;
                } else if ((expr.charAt(k)== '*' || expr.charAt(k) == '+')
                    && nesting == 0) {
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
            return new TreeNode(op,exprTreeHelper(opnd1),exprTreeHelper(opnd2)); // you fill this in
        }
    }

    public void optimize() {
        /*
        if myRoot is not a var or operand, and its a leaf, return TreeNode
        with number as entry. 

        if exception thrown by adding String and int, handle it. 

        else recursively call optimize on left and right branches of tree 
            if + or *, add left and right and replace myLeft or myRight with that value
        */ 
        myRoot = optimize_helper(myRoot); 
    }

    public TreeNode optimize_helper(TreeNode t) {
        if (t.myLeft == null && t.myRight == null) {
        return new TreeNode(t.myItem); 
        }
        else if (t.myItem.equals("*")) {
            try { 
                t.myLeft = optimize_helper(t.myLeft); 
                t.myRight = optimize_helper(t.myRight); 
                int newRoot = Integer.parseInt((String) t.myLeft.myItem) * Integer.parseInt((String) t.myRight.myItem); 
                t.myItem = newRoot; 
                t.myLeft = null; 
                t.myRight = null; 
            }
            catch (IllegalArgumentException e) {
                return t; 
            }
        }
        else if (t.myItem.equals("+")) {
            try { 
                t.myLeft = optimize_helper(t.myLeft); 
                t.myRight = optimize_helper(t.myRight); 
                int newRoot = Integer.parseInt((String)t.myLeft.myItem) * Integer.parseInt((String)t.myRight.myItem); 
                t.myItem = newRoot; 
                t.myLeft = null; 
                t.myRight = null; 
            }
            catch (IllegalArgumentException e) {
                return t; 
            }
        }
        return t; 
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

    public void print() {
        if (myRoot != null) {
            myRoot.print(0);
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
        	myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c", new TreeNode("d", new TreeNode("e"), new TreeNode("f")), null));
        }

        public static void main(String[] args) {
            BinaryTree t;
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
        
        public int height() {
        	if (myRoot != null) {
        		return myRoot.height(0);
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

        private void isOK(TreeNode t) {
            alreadySeen.add(t.myItem); 
            if (t.myLeft == null && t.myRight == null) {
                return; 
            }
            if (t.myLeft != null) {
                if (alreadySeen.contains(t.myLeft.myItem)) {
                    throw new IllegalStateException(t.myItem + "already in tree"); 
                }} 
            if (t.myRight != null) {
                if (alreadySeen.contains(t.myRight.myItem)) {
                    throw new IllegalStateException(t.myItem + "already in tree"); 
                }
            }

            isOK(t.myRight);  
            isOK(t.myLeft); 
              
            } 
        
         
        public static BinaryTree fibTree(int n) {
            BinaryTree result = new BinaryTree();
            result.myRoot = result.fibTreeHelper(n);
            return result;
        }

        private TreeNode fibTreeHelper(int n) {
            if (n == 0) {
                return  new TreeNode(0);
            } else if (n == 1) {
                return new TreeNode(1);
            } else {
                TreeNode left = fibTreeHelper(n-1); 
                TreeNode right = fibTreeHelper(n-2); 
                return new TreeNode ((int) left.myItem + (int) right.myItem, left, right);
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

        //In TreeNode
        private static final String indent1 = "    ";

        private void print(int indent) {
        // TODO your code here
            for (TreeNode b = this; b != null; b = myRight) {
                b.print(indent+1); 
            }

            println(myItem, indent);

            for (TreeNode a = this; a != null; a = myLeft) {
                a.print(indent+1);
            }
        // TODO your code here
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
    
    public int height(int start) {
    	start ++;
    	if (myRight != null && myLeft != null) {
    		return Math.max(myRight.height(start), myLeft.height(start));
    	} else if (myRight != null) {
    		return myRight.height(start);
    	} else if (myLeft != null) {
    		return myLeft.height(start);
    	}
    	return start;
    }
}}
