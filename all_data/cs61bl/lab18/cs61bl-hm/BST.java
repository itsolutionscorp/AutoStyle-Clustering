import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Your comment here
    private BSTNode linkedListToTree (Iterator iter, int n) {
        BSTNode root = new BSTNode();    	
    	if (n == 0)
    		return null;
    	else if (n == 1) {
    		root.myItem = iter.next();
    	} else {
	        root.myLeft= linkedListToTree(iter, n/2);
	        root.myItem = iter.next();
	        root.myRight = linkedListToTree(iter, n-1-n/2);
    	}
        return root;
    }

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
    }
}
