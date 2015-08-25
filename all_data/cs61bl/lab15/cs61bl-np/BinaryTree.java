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

    public void fillSampleTree1() {
        myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
    }

    public void fillSampleTree2() {
        myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d",
                new TreeNode("e"), new TreeNode("f")), null), new TreeNode("c"));
    }
    public void fillSampleTree3(){
    	myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c", 
    				new TreeNode("D", new TreeNode("e"), new TreeNode("F")), null));
    	
    }
    public int height(){
    	if (myRoot == null)
    		return 0;
    	else{
    		return myRoot.height();
    	}
    	
    }
    
    public boolean isCompletelyBalanced(){
    	if (myRoot == null){
    		return true;
    	}else{
    		return myRoot.isCompletelyBalanced();
    		}
    	}
    
    
    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        print(t, "the empty tree");
        t.fillSampleTree1();
        print(t, "sample tree 1");
        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree2();
        print(t, "sample tree 2");
        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree3();
        print(t, "sample tree 3");
        System.out.println(t.isCompletelyBalanced());
        System.out.println(t.height());
        t.print();
    }

    private static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
    }
    public void print() {
        if (myRoot != null) {
            myRoot.print(0);
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

        public int height() {
        	if (myLeft == null && myRight == null){
        		return 1;
        	}else{
        		if (myLeft == null){
        			return 1 + myRight.height();
        		}
        		if(myRight == null){
        			return 1 + myLeft.height();
        		}
        		return 1 + Math.max(myLeft.height(), myRight.height());
        	}
        }
        
        public boolean isCompletelyBalanced() {
        	if (myLeft == null && myRight == null){
        		return true;
        	}else{
        		if (myLeft == null){
        			return false;
        		}
        		if(myRight == null){
        			return false;
        		}
        		return (myRight.isCompletelyBalanced() && myLeft.isCompletelyBalanced());
        	}
        }
        //In TreeNode
        private static final String indent1 = "    ";
        private Stack<TreeNode> tree = new Stack<TreeNode>();
        private void print(int indent) {
        	TreeNode temp = this;
        	tree.push(temp);
            while(temp.myLeft != null){
            		indent++;
            		tree.push(myLeft);
            		if (temp.myLeft.myLeft != null ){
            		temp = temp.myLeft;
            		}
            		else if(temp.myLeft.myRight !=null){
            			temp = temp.myLeft;
            		}
            		else if(temp.myLeft.myLeft == null && temp.myLeft.myRight == null){
            			temp = temp.myRight;
            		}
            	
            }
            while(!tree.isEmpty()){
            println (tree.pop().myItem, indent);
            }
            // TODO your code here
        }

        private static void println(Object obj, int indent) {
            for (int k=0; k<indent; k++) {
                System.out.print(indent1);
            }
            System.out.println(obj);
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
            ____; // you fill this in
        } else {
            // expr is a parenthesized expression.
            // Strip off the beginning and ending parentheses,
            // find the main operator (an occurrence of + or * not nested
            // in parentheses, and construct the two subtrees.
            int nesting = 0;
            int opPos = 0;
            for (int k = 1; k < expr.length() - 1; k++) {
                // you supply the missing code
            }
            String opnd1 = expr.substring(1, opPos);
            String opnd2 = expr.substring(opPos + 1, expr.length() - 1);
            String op = expr.substring(opPos, opPos + 1);
            System.out.println("expression = " + expr);
            System.out.println("operand 1  = " + opnd1);
            System.out.println("operator   = " + op);
            System.out.println("operand 2  = " + opnd2);
            System.out.println();
            ____; // you fill this in
        }
    }
}
