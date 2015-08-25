import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    /**
     * Creates a subtree from the elements of the linked list passed in 
     * during initialization. 
     * 
     * @param iter iterator over the linked list in the constructor
     * @param n the size of the desired subtree
     * @return the subtree
     */
    private static BSTNode linkedListToTree(Iterator iter, int n) {
    	if (n == 0) {
    		return null;
    	} else {
    		BSTNode node = new BSTNode();
    		node.myLeft = linkedListToTree(iter, n/2);
    		node.myItem = iter.next();
    		node.myRight = linkedListToTree(iter, n-n/2-1);
    		return node;
    	}
    }

    private static class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
    }
    
}
