import java.util.ArrayList;
//This is for lab15!!!!!!!!!!!!!!!!!!!!!!!!!
public class BinaryTree {

    private TreeNode myRoot;

    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
    }

    public int height() {
    	if(myRoot == null){
    		return 0;
    	}
    	return myRoot.height();
    	
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
    
    private ArrayList<TreeNode> alreadySeen; 
    private void isOK(TreeNode t) throws IllegalStateException{    	
    	for(TreeNode node: alreadySeen){
    		if( t == node){
    			throw new IllegalStateException();
    		}
    		
    	}
    	alreadySeen.add(t);
    	
    }
    
    public boolean check() { 
        alreadySeen = new ArrayList<TreeNode>(); 
        try { 
            isOK(myRoot); 
            return true; 
        } catch (IllegalStateException e) { 
            return false; 
        } 
    }
    

    public void fillSampleTree1() {
        myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
    }

    public void fillSampleTree2() {
        myRoot = new TreeNode("a", 
        		new TreeNode("b", 
        				new TreeNode("d", new TreeNode("e"), new TreeNode("f"))
        		, null)
        , new TreeNode("c"));
    }
    
    public void fillSampleTree3(){
    	myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c", new TreeNode("d", new TreeNode("E"), new TreeNode("f")), null) );
    	
    }
    
    public void fillSampleTree4(){
    	myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
    }

    public static void main(String[] args) {
//        BinaryTree t;
//        t = new BinaryTree();
//        print(t, "the empty tree");
//        t.fillSampleTree1();
//        print(t, "sample tree 1");
//        t.fillSampleTree2();
//        print(t, "sample tree 2");
//        t.fillSampleTree3();
//        print(t, "sample tree 3");
//        t.print();
//        t.fillSampleTree4();
//        System.out.println(t.isCompletelyBalanced());

        BinaryTree t = exprTree("((a+(5*(9+1)))+(6*5))");
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
    public void print() {
        if(myRoot != null){
            myRoot.print(1);
        }
    }
    public boolean isCompletelyBalanced(){
    	
    	return isCompletelyBalanced(myRoot);
    }
    
    public boolean isCompletelyBalanced(TreeNode N){
    	if(N == null || (N.myLeft == null && N.myRight == null)){
    		return true;
    	}else if(N.myLeft == null || N.myRight == null){
    		return false;
    	} else {
    		return (N.myLeft.height() == N.myRight.height() && isCompletelyBalanced(N.myLeft) && isCompletelyBalanced(N.myRight));
    	}
    }
    
    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
    }

    private TreeNode fibTreeHelper (int n){
    	
    	if(n < 2){
    		return new TreeNode(n);
    	}
    	TreeNode node = new TreeNode(n-1 ,fibTreeHelper(n-1), fibTreeHelper(n-2));
    	
    	
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
		if (expr.charAt(0) == '(' && expr.charAt(expr.length() - 1) == ')') {
			int count = 0;
			for (int k = 1; k < expr.length() - 1; k++) {
				if (expr.charAt(k) == '(') {
					count++;
				}
				if (expr.charAt(k) == ')') {
					count--;
				}
				if (count < 0) {
					break;
				}
			}
			if (count == 0)
				return exprTreeHelper(expr.substring(1, expr.length() - 1));
		}

		int opPos = -1;
		int nesting = 0;
		for (int k = 0; k < expr.length(); k++) {
			if (expr.charAt(k) == '(') {
				nesting++;
			}
			if (expr.charAt(k) == ')') {
				nesting--;
			}
			if (nesting == 0 && (expr.charAt(k) == '*' || expr.charAt(k) == '+')) {
				opPos = k;
			}
		}
		if (opPos == -1) {
			return new TreeNode(expr);
		}
		String opnd1 = expr.substring(0, opPos);
		String opnd2 = expr.substring(opPos + 1, expr.length());
		String op = expr.substring(opPos, opPos + 1);
		System.out.println("expression = " + expr);
		System.out.println("operand 1  = " + opnd1);
		System.out.println("operator   = " + op);
		System.out.println("operand 2  = " + opnd2);
		System.out.println();
		return new TreeNode(op, exprTreeHelper(opnd1), exprTreeHelper(opnd2));
    }
	
	public void optimize(){
    	if(myRoot != null){
    	optimizeHelper(myRoot);
    	}
    }
    
    public void optimizeHelper(TreeNode treeOpt){
    	if(treeOpt.myLeft != null){
    		optimizeHelper(treeOpt.myLeft);
    	}
    	if (treeOpt.myRight != null){
    	
    		optimizeHelper(treeOpt.myRight);
    	}
    	try{
    		int first = Integer.parseInt((String) treeOpt.myLeft.myItem);
    		int second = Integer.parseInt((String) treeOpt.myRight.myItem);
    		
    		
    		if(treeOpt.myItem.equals("+")){
    			treeOpt.myItem = Integer.toString(first + second);
    		} else 
    		if(treeOpt.myItem.equals("*")){
    			treeOpt.myItem = Integer.toString(first * second);
    		}
    		//Now assuming that calculation falls through.....
    		
    		treeOpt.myLeft = null;
    		treeOpt.myRight = null;
    	} catch (Exception e){
    		
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
        
        public int height() {
        	if(this.myLeft == null && this.myRight == null){
        		return 1;
        		
        	} else if(this.myLeft == null){
        		return this.myRight.height() + 1;
        	} else if(this.myRight == null){
        		return(this.myLeft.height()) + 1;
        	} else {
        		return 1 + Math.max(this.myRight.height(), this.myLeft.height());
        	}
        	
        	
        }
        

        //In TreeNode
        private static final String indent1 = "    ";

        private void print(int indent) {
        	
        	if (myRight != null) {
        		myRight.print(indent + 1);
        	}
        	println(myItem, indent);
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
