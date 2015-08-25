import java.util.ArrayList;

public class BinaryTree {

    private TreeNode myRoot;

    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
    }
    public int height(){
    	if(myRoot!=null){
    		return myRoot.height();
    	}
    	return 0;
    }
    public boolean isCompletelyBalanced (){
    	if(myRoot!=null){
    		return myRoot.isCompletelyBalanced();
    	}
    	return true;
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
    public void fill(TreeNode in){
    	myRoot=in;
    }

    public void fillSampleTree1() {
        myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
    }

    public void fillSampleTree2() {
        myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d",
                new TreeNode("e"), new TreeNode("f")), null), new TreeNode("c"));
    }
    public void fillSampleTree3(){
    	myRoot= new TreeNode("a", new TreeNode("b"),
    			
    	new TreeNode("c",new TreeNode("d",new TreeNode
    			("e"),new TreeNode("f")),null));
    }
    public void fillSampleTreeBalance(){
    	myRoot= new TreeNode("a",new TreeNode("b",
    			new TreeNode("e",new TreeNode("d"),new TreeNode("f")),
    			new TreeNode("z",new TreeNode("y"),new TreeNode("x")))
    	,new TreeNode("c",
    			new TreeNode("s",new TreeNode("t"),new TreeNode("r")),
    			new TreeNode("i",new TreeNode("q"),new TreeNode("k"))));
    }
    public void fillSampleTreeBalanceWithRep(){
    	myRoot= new TreeNode("a",new TreeNode("b",
    			new TreeNode("e",new TreeNode("d"),new TreeNode("f")),
    			new TreeNode("z",new TreeNode("y"),new TreeNode("x")))
    	,new TreeNode("c",
    			new TreeNode("s",new TreeNode("t"),new TreeNode("a")),
    			new TreeNode("i",new TreeNode("f"),new TreeNode("k"))));
    }
    public void fillSampleTreeHeight3(){
    	myRoot= new TreeNode("a",new TreeNode("b",
    			new TreeNode("e"),
    			new TreeNode("z"))
    	,new TreeNode("c",
    			new TreeNode("s"),
    			new TreeNode("i")));
    }
    public void fillSampleTreeHeight3WithRep(){
    	myRoot= new TreeNode("a",new TreeNode("b",
    			new TreeNode("e"),
    			new TreeNode("z"))
    	,new TreeNode("c",
    			new TreeNode("s"),
    			new TreeNode("a")));
    }
    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        
        print(t, "the empty tree");
        System.out.println(t.check());
        
        t.fillSampleTree1();
        
        print(t, "sample tree 1");
        t.fillSampleTree2();
       
        print(t, "sample tree 2");
        t.print();
        t.fillSampleTree3();
        t.print();
        print(t,"sample tree 3");
        System.out.println(t.isCompletelyBalanced());
      
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
        	if(myRoot==null){
        		return true;
        	}
            isOK(myRoot); 
            return true; 
        } catch (IllegalStateException e) { 
            return false; 
        } 
    }

    // Contains nodes already seen in the traversal. 
    private ArrayList alreadySeen;
    private void isOK(TreeNode t) throws IllegalStateException{
    	
    	if(alreadySeen.contains(t.myItem))
    	{throw new IllegalStateException();}else{
    		alreadySeen.add(t.myItem);
    		if(t.myLeft!=null){
    		isOK(t.myLeft);}
    		if(t.myRight!=null){
    		isOK(t.myRight);}
    	}       
    	};
    	public static BinaryTree fibTree(int n) {
    	    BinaryTree result = new BinaryTree();
    	    result.myRoot = result.fibTreeHelper(n);
    	    return result;
    	}

    	private TreeNode fibTreeHelper (int n){
    		if(n==0){return new TreeNode(0,null,null);}
    		if(n==1){return new TreeNode(1,null,null);}
    		return new TreeNode(
    				((Integer)(fibTreeHelper(n-1).myItem)+
    						((Integer)fibTreeHelper(n-2).myItem))
    				,fibTreeHelper(n-1),fibTreeHelper(n-2));
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
    	        //____; // you fill this in
    	    	return new TreeNode(expr);
    	    } else {
    	        // expr is a parenthesized expression.
    	        // Strip off the beginning and ending parentheses,
    	        // find the main operator (an occurrence of + or * not nested
    	        // in parentheses, and construct the two subtrees.
    	        int nesting = 0;
    	        int opPos = 0;
    	        for (int k = 1; k < expr.length() - 1; k++) {
    	            // you supply the missing code
    	        	if(expr.charAt(k)=='('){
    	        		nesting++;	
    	        	}
    	        	if(expr.charAt(k)==')'){
    	        		nesting--;
    	        	}
    	        	if(expr.charAt(k)=='+'||expr.charAt(k)=='*'){
    	        		if(nesting==0){opPos=k;
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
    	        return new TreeNode(op,exprTreeHelper(opnd1),exprTreeHelper(opnd2));
    	        //____; // you fill this in
    	    }
    	}
    public void optimize(){
    	if(myRoot!=null){
    		myRoot.optimize();
    	}
    }
    private static class TreeNode {
    	
        public Object myItem;
        public TreeNode myLeft;
        public TreeNode myRight;
       
        //In TreeNode
        private static final String indent1 = "    ";
        private  boolean IsInt(String s){
        	try{
        		int number = Integer.parseInt(s);
        		return true;
        	}catch(NumberFormatException e){
        		System.out.println(e);
        		return false;
        	}
        }
        private int toInt(String s){
        	if(IsInt(s)){
        		return Integer.parseInt(s);
        	}else{throw new NumberFormatException();}
        }
        private void optimize(){
        	
        	
        		if(((String)myItem).equals("+")){
        			if(myLeft!=null){
        			myLeft.optimize();}
        			if(myRight!=null){
        			myRight.optimize();}
        			if(IsInt((String)myLeft.myItem)&&IsInt((String)myRight.myItem)){
        				int ri = toInt((String)myRight.myItem);
        				int le = toInt((String)myLeft.myItem);
        				myItem=ri+le;
        				myLeft=null;
        				myRight =null;
        				return;
        			}}
        			
        		
        		if(((String)myItem).equals("*")){
        			if(myLeft!=null){
            			myLeft.optimize();}
            			if(myRight!=null){
            			myRight.optimize();}
            			if(IsInt((String)myLeft.myItem)&&IsInt((String)myRight.myItem)){
            				int ri = toInt((String)myRight.myItem);
            				int le = toInt((String)myLeft.myItem);
            				myItem=ri*le;
            				myLeft=null;
            				myRight =null;
            				}
            			return;
        		}
        		}
        
        
        private void print(int indent) {
            // TODO your code here
        	if(myRight!=null){
        	myRight.print(indent+1);}
            println (myItem, indent);
            // TODO your code here
            if(myLeft!=null){
            myLeft.print(indent+1);}
        }

        private static void println(Object obj, int indent) {
            for (int k=0; k<indent; k++) {
                System.out.print(indent1);
            }
            System.out.println(obj);
        }

        public TreeNode(Object obj) {
            myItem = obj;
            myLeft = myRight = null;
        }

        public TreeNode(Object obj, TreeNode left, TreeNode right) {
            myItem = obj;
            myLeft = left;
            myRight = right;
        }
        public int height(){
        	if(myLeft==null&&myRight==null){
        		return 1;
        	}
        	else{if(myLeft==null){
        		return 1+myRight.height();
        	}
        	if(myRight==null){
        		return 1+myLeft.height();
        	}
        	int max=Math.max(myLeft.height(), myRight.height());
        	return 1+max;
        		
        	}
        }
        public boolean isCompletelyBalanced (){
        	if(myLeft==null&&myRight==null){
        		return true;
        	}
        	if(myLeft==null||myRight==null){
        		return false;
        	}
        	if(myLeft.height()==myRight.height()){
        		return myLeft.isCompletelyBalanced()&&myRight.isCompletelyBalanced();
        	}
        	return false;
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
