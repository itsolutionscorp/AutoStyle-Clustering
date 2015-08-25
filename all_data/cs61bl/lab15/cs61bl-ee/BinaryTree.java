
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

	public int height(){
		if(myRoot!= null){
			return myRoot.height();
		}
		else{
			return 0;
		}
	}
	public boolean  isCompletelyBalanced(){
		if(myRoot!= null){
			return myRoot.isCompletelyBalanced();
		}
		else{
			return true;
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
        myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c",new TreeNode("d", new TreeNode("e"), new TreeNode("f")),null));
    }
    
  //###############################################################################
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
            	
            	if((expr.charAt(k) == '*'|| expr.charAt(k) == '+') && nesting == 0 ){
            		opPos = k;
            	}
            	if(expr.charAt(k) == '('){
            		nesting ++;
            	}
            	if(expr.charAt(k) == ')'){
            		nesting --;
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
    //##################################################################################
    

    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
       // print(t, "the empty tree");
//        t.fillSampleTree1();
//        print(t, "sample tree 1");
       t.fillSampleTree2();
    // print(t, "sample tree 2");
//        t.fillSampleTree3();
     // t.myRoot.forStack();

      t.print();
//       t.check();
       
       BinaryTree Fib = fibTree(5);
       Fib.printPreorder();
       Fib.print();
      
       
        System.out.print(Fib);
        print(Fib, "Fib Tree");
        
        BinaryTree a = exprTree("((a+(5*(a+b)))+(6*5))");
        
        a.print();
       System.out.println( a.myRoot.getForHeight());
        
        
    }

    private static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
    }
    //###########################################################################
    private ArrayList alreadySeen; 
    public boolean check() { 
        alreadySeen = new ArrayList(); 
        try { 
            isOK(myRoot); 
            return true; 
        } catch (IllegalStateException e) { 
            return false; 
        } 
    }
    private void isOK(TreeNode t) throws IllegalStateException{
    	if(t != null){
	    	if( alreadySeen.contains(t)){
	    		throw new IllegalStateException();
	    	}
	    	else{
	    	alreadySeen.add(t);
	    
	    	//###########################Pattern height
	      	ArrayList<TreeNode> myChidren = new ArrayList<TreeNode>();
        	if(t.myLeft != null){
        	myChidren.add(t.myLeft);
        	}
        	if(t.myRight != null){
        	myChidren.add(t.myRight);
        	}
            if (!myChidren.isEmpty()) {           	
            
                for (TreeNode a : myChidren) {
                	alreadySeen.add(a);
                    isOK(a);
                }
      
        
            }
	    	}
	    	
	    	 
	    	
	    	}
    }
    //###########################################################################
  public void print() {
  if (myRoot != null) {
	  myRoot.forStack();
      myRoot.print(0);
  }
}
  //############################################################################
  public static BinaryTree fibTree(int n) {
	    BinaryTree result = new BinaryTree();
	    result.myRoot = result.fibTreeHelper(n);
	    return result;
	}
  
  private TreeNode fibTreeHelper (int n){
	  if(n == 0){
		  return new TreeNode(0);
	  }
	  else if(n==1){
		  return new TreeNode(1);
	  }
	  else{
		  return new TreeNode((int) fibTreeHelper(n-1).myItem+ (int) fibTreeHelper(n-2).myItem, fibTreeHelper(n-1),fibTreeHelper(n-2));
	  }
  }

//############################################################################
	

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


//  //In TreeNode
  private static final String indent1 = "    ";
  private static Stack<Object> forPrint = new Stack<Object>();
  private static  Stack<Integer>forHeight = new  Stack<Integer>();
 private static int myCount = 1;
  public Stack getForPrint(){
	  return forPrint;
  }
  public Stack getForHeight(){
	  return forHeight;
  }
//
  private void forStack() {
	 
      if (myLeft != null) {
    	  myCount ++;
          myLeft.forStack();
      }
  
      forPrint.push(myItem); 
      forHeight.push(myCount);
      myCount--;
  
      if (myRight != null) {
    	    myCount+= 2;
          myRight.forStack();
          myCount --;
      }
      
  }
  
  private void print(int indent) {
     // TODO your code here
	  int size =  forPrint.size();
	  for(int i =0 ; i < size; i++)	 { 
	  myItem = forPrint.pop();
	  indent = forHeight.pop()+1;
	  
      println (myItem, indent);
      // TODO your code here
      
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
        	ArrayList<TreeNode> myChidren = new ArrayList<TreeNode>();
        	if(myLeft != null){
        	myChidren.add(myLeft);}
        	if(myRight != null){
        	myChidren.add(myRight);
        	}
            if (myChidren.isEmpty()) {
            	return 1;
            } else {
                int bestSoFar = 1;
                
                for (TreeNode a : myChidren) {
                    bestSoFar =  Math.max(a.height(), bestSoFar);
                }
                bestSoFar ++;
                return bestSoFar;
            }
        }
        public boolean isCompletelyBalanced(){
        	ArrayList<TreeNode> myChidren = new ArrayList<TreeNode>();
        	if(myLeft != null){
        	myChidren.add(myLeft);}
        	if(myRight != null){
        	myChidren.add(myRight);
        	}
        	if (myChidren.isEmpty()) {
            	return true;}
        	else if(myLeft == null || myRight == null ){
        		  return false;
        	}
        	else{
        		if(myLeft.height() == myRight.height()){
        			if(myLeft.isCompletelyBalanced() == false ||myRight.isCompletelyBalanced()==false){
        				return false;
        			}
        			else{
        				return true;
        			}
        		}
        		else{
        			return false;
        		}
        	}
        	
        	
        }
    }
}
