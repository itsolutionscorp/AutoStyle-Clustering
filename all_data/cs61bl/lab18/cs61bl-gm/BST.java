import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Takes a sorted Linked List and constructs a Binary Search Tree out of the values.
    private BSTNode linkedListToTree (Iterator iter, int n) {
    	BSTNode root = new BSTNode();
        // TODO Your code here
    	if (n == 0) {
    		return null;
    	}
    	if (n == 1) {
    		root.myItem = iter.next();
    		return root;
    	}
    	root.myLeft = linkedListToTree(iter, n/2);
    	root.myItem = iter.next();
    	root.myRight = linkedListToTree(iter, (n-1)/2);
        return root;
    }

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
    }
}
