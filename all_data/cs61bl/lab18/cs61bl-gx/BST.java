import java.util.*;

public class BST {
    BSTNode myRoot;
    
    
    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }
    
    // Your comment here
    private BSTNode linkedListToTree (Iterator iter, int n) {
    	if (n == 0) {
    		return null;
    	}
    	if (n%2 == 1) {
	    	int middle = n/2;
	    	BSTNode left = linkedListToTree(iter, middle);
	    	BSTNode root = new BSTNode();
	    	root.myLeft = left;
	    	if (iter.hasNext()) {
	    		root.myItem = iter.next();
	    	}
	    	root.myRight = linkedListToTree(iter, middle);
	    	return root;
	    } else {
	    	int middle = n/2;
	    	BSTNode left = linkedListToTree(iter, middle);
	    	BSTNode root = new BSTNode();
	    	root.myLeft = left;
	    	if (iter.hasNext()) {
	    		root.myItem = iter.next();
	    	}
	    	root.myRight = linkedListToTree(iter, middle - 1);
	    	return root;
	    }
    }
    public void printInorder() {
    	myRoot.printInorderHelper();
    }
    
    
    
    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
        
        
		public void printInorderHelper() {
	        if (myLeft != null) {
	            myLeft.printInorderHelper();
	        }
	        System.out.print(myItem + " ");
	        if (myRight != null) {
	            myRight.printInorderHelper();
	        }
	    }		
    }
}
