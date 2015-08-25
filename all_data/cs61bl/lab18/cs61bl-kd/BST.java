import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    //This method takes in an Iterator and an integer that is the size of the list,
    //and builds a balanced binary search tree out of an already ordered LinkedList.
    private BSTNode linkedListToTree (Iterator iter, int n) {
        // TODO Your code here
    	BSTNode myNode = new BSTNode();
    	if (n == 0) {
    		return null;
    	}
    	else {
    		myNode.myLeft = linkedListToTree(iter, n/2);
    		myNode.myItem = iter.next();
    		if (iter.hasNext()) {
        		myNode.myRight = linkedListToTree(iter, n/2);
    		}
    		return myNode;
    	}
    }

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
    }
}
