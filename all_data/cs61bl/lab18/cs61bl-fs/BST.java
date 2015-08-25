import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Root node will be n/2 in case of even size, n/2 + 1 in case of odd size
    private BSTNode linkedListToTree (Iterator iter, int n) {
    	BSTNode result = new BSTNode();
        if (n == 0) {
        	return null;
        } else if (n == 1) {
        	result.myItem = iter.next();
        	return result;
        } else {
        	if (n%2 == 0) {
        		result.myLeft = linkedListToTree(iter, n/2-1);
            	result.myItem = iter.next();
            	result.myRight = linkedListToTree(iter, n/2);
        	} else {
        		result.myLeft = linkedListToTree(iter, n/2);
            	result.myItem = iter.next();
            	result.myRight = linkedListToTree(iter, n/2);
        	}
        	return result;
        }
    }

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
    }
}
