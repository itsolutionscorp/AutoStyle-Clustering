import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    /**
     * return a balanced binary search tree out of an already sorted LinkedLis
     * @param iter linkedList literator
     * @param n size of the linkedList
     * @return
     */
    private BSTNode linkedListToTree (Iterator iter, int n) {
    	if (n==1){
    		return new BSTNode(iter.next(),null,null);
    	}
    	else if(n==2){
    		BSTNode left=new BSTNode(iter.next(),null,null);
    		return new BSTNode(iter.next(), left, null);
    	}
    	else if(n==3){
    		BSTNode left=new BSTNode(iter.next(),null,null);
    		return new BSTNode(iter.next(), left, new BSTNode(iter.next(),null,null));
    	}
    	else if(n%2==1){
    		BSTNode left=linkedListToTree (iter,n/2);
    		return new BSTNode(iter.next(), left, linkedListToTree (iter,n/2));
    		
    	}
    	BSTNode left=linkedListToTree (iter,n/2);
    	return new BSTNode(iter.next(), left, linkedListToTree (iter,n/2-1)); 	
    }

    public void print() {
        if (myRoot != null) {
            myRoot.print(0);
        }
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



    
    
    
    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
        
        private BSTNode(){
        	myItem=null;
        	myLeft=null;
        	myRight=null;
        }
        
        private BSTNode(Object item, BSTNode left, BSTNode right ){
        	myItem=item;
        	myLeft=left;
        	myRight=right;
        }
        
        
        
    	private static final String indent1 = "    ";

    	private void print(int indent) {
    	    if (myRight != null) {
    			myRight.print(indent+1);
    	    }
    	    println (myItem, indent);
    	    if (myLeft != null) {
    			myLeft.print(indent+1);
    	    }
    	}

    	private void println(Object obj, int indent) {
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

    }
    
    
}
