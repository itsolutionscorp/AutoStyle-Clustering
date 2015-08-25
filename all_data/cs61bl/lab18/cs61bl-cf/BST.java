import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Your comment here
    private static BSTNode linkedListToTree (Iterator iter, int n) {
        // TODO Your code here
        if (n == 0){
        	BSTNode myRoot;
        	myRoot = null;
        	return myRoot;
        }
    	if (n == 1){
    		BSTNode myRoot;
    		myRoot = (BSTNode) iter.next();
    		return myRoot;
        }else{
    	int halfway = n/2;
    	BSTNode left = linkedListToTree(iter, halfway);
    	BSTNode myRoot = null;
    	myRoot.myItem = iter.next();
    	myRoot.myLeft = left;
    	BSTNode right = linkedListToTree(iter, halfway);
    	myRoot.myRight = right;
    	return myRoot;
        }
    }
    	

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
    }
    
    
    
}
