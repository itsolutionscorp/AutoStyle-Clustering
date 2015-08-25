import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    /**
     * Iterates over the list to find the center node. Sets the center of 
     * the list and sets it to node. It sets the adjacent nodes to left and right
     * and recursively builds the tree by dividing the list in half. 
     * @param iter: the iterator over the objects of the list. 
     * @param n: the length of the list
     * @return the next BST Node in the list. 
     */
  
    private BSTNode linkedListToTree (Iterator iter, int n) {
    	if (n == 0) {
    		return null; 
    	}
    	
    	int counter = 0; 
    	while (counter != n/2) {
    		iter.next();
    		counter++; 
    	}
    	myRoot.myItem = iter.next(); 
    	myRoot.myLeft = linkedListToTree(iter,counter-1);
    	myRoot.myRight = linkedListToTree(iter,n-counter);
    	return myRoot; 
    }

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
    }
}
