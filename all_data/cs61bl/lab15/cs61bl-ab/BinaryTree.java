import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

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
    	myRoot = new TreeNode("a",new TreeNode("b"), new TreeNode("c",
    			new TreeNode("d",new TreeNode("e"), new TreeNode("f")),null));
    }

    public int height(){
    	if (myRoot!=null){
    		return myRoot.height();
    	}
    	return 0;
    }
    
    public boolean isCompletelyBalanced(){
    	if (myRoot==null) {
    		return true;
    	} else {
    		return myRoot.isCompletelyBalanced();
    	}
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
    private ArrayList<TreeNode> alreadySeen; 
    private void isOK(TreeNode t) throws IllegalStateException {
    	if(t!=null){
    		if(alreadySeen.contains(t)){
    			throw new IllegalStateException();
    		} else{
    			alreadySeen.add(t);
    			if(t.myLeft!=null) {
    				isOK(t.myLeft);
    			}
    			if (t.myRight!=null) {
    				isOK(t.myRight);
    			}
    		}
    	}
    }
    
    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
    }
    private TreeNode fibTreeHelper (int n){
    	if (n==0){
    		return new TreeNode(0);
    	} else if (n==1) {
    		return new TreeNode(1);
    	} else {
    		TreeNode left= fibTreeHelper(n-1);
    		TreeNode right = fibTreeHelper(n-2);
    		return new TreeNode((Integer)left.myItem+(Integer)right.myItem, left, right);
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
        	int pos=1;
        	boolean hasOp = false;
            for (int k=1; k<expr.length()-1;k++){
            	if (expr.charAt(k)=='+'| expr.charAt(k)=='*'){
            		pos = k;
            		hasOp=true;
            		break;
            	}
            }
            if(!hasOp){
            	return new TreeNode(expr);
            } else {
            	return new TreeNode(expr.substring(pos, pos+1), 
            		new TreeNode(expr.substring(0, pos)), 
            		exprTreeHelper(expr.substring(pos+1)));
            }
            
            
        } else {
            // expr is a parenthesized expression.
            // Strip off the beginning and ending parentheses,
            // find the main operator (an occurrence of + or * not nested
            // in parentheses, and construct the two subtrees.
            int nesting = 0;
            int opPos = 0;
            for (int k = 1; k < expr.length() - 1; k++) {
                if(expr.charAt(k)=='('){
                	nesting++;
                } else if (expr.charAt(k)==')'){
                	nesting--;
                } else if ((expr.charAt(k)=='+'|expr.charAt(k)=='*')&&nesting==0){
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
    	if(myRoot!=null){
    		myRoot=optimizeHelper(myRoot);
    	}
    }
    private TreeNode optimizeHelper(TreeNode t){
    	if(t.myLeft==null|t.myRight==null){
    		return t;
    	} else {
    		t.myLeft=optimizeHelper(t.myLeft);
    		t.myRight=optimizeHelper(t.myRight);
    		if(isInteger((String)t.myLeft.myItem)&&isInteger((String)t.myRight.myItem)){
    			if(((String)t.myItem).equals("+")){
    				t.myItem = ((Integer)(Integer.parseInt((String)t.myLeft.myItem)+ Integer.parseInt((String)t.myRight.myItem))).toString();
    			} else {
    				t.myItem = ((Integer)(Integer.parseInt((String)t.myLeft.myItem)* Integer.parseInt((String)t.myRight.myItem))).toString();
    			}
    			t.myLeft=null;
    			t.myRight=null;
    		}
    		return t;
    	}
    }

    
    
    private boolean isInteger(String s) {
    	try { 
            Integer.parseInt(s); 
        } catch(NumberFormatException e) { 
            return false; 
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
	}

	public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        print(t, "the empty tree");
        t.fillSampleTree1();
        print(t, "sample tree 1");
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.print();
        t.fillSampleTree2();
        print(t, "sample tree 2");
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.print();
        t.fillSampleTree3();
        print(t, "sample tree 3");
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.print();
        System.out.println(t.check());
        for(int i = 0; i<6; i++){
        	t=fibTree(i);
            t.print();
        }
        t = exprTree("((a+(5*(a+b)))+(6*5))");
        String s= "3";

        t.print();
        t=exprTree("((a+(5*(9+3)))+(6*5))");
        t.optimize();
        t.print();
        
    }
    

    private static class TreeNode {

        public Object myItem;
        public TreeNode myLeft;
        public TreeNode myRight;

        public TreeNode(Object obj) {
            myItem = obj;
            myLeft = myRight = null;
        }
        private static final String indent1 = "    ";

        private void print(int indent) {
            if(myRight!=null){
            	myRight.print(indent+1);
            }
            println (myItem, indent);
            if(myLeft!=null) {
            	myLeft.print(indent+1);
            }
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
        
        private int height(){
        	if (myLeft == null && myRight == null){
        		return 1;
        	}
        	if (myLeft == null){
        		return 1+myRight.height();
        	}
        	if (myRight == null){
        		return 1+myLeft.height();
        	}
            return Math.max(myLeft.height(), myRight.height()) + 1;
        }
        private boolean isCompletelyBalanced(){
        	if (myLeft==null) {
        		return myRight==null;
        	} else if (myRight==null){
        		return myLeft==null;
        	} else{
        		return myLeft.isCompletelyBalanced()&&
        				myRight.isCompletelyBalanced()&&
        				myLeft.height()==myRight.height();
        	}
        }
    }
}
