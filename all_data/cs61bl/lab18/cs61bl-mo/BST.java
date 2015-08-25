import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    /**
     * This constructor iterates through the list adding each item to a left list until it gets to 
     * the middle item + 1, where it adds that item to a node. It then continues iterating through the
     * rest of the list adding each item to a right list. It then sets the right and left of the node 
     * by calling the constructor again on the left and right list.
     * 
     * @param iter - the list iterator
     * @param n - the size of the list
     * @return - a new node with the middle + 1 item
     */
    private BSTNode linkedListToTree (Iterator iter, int n) {
    	BSTNode node = new BSTNode();
    	if (n == 0) {
    		return node;
    	}
        Object curr = iter.next();
    	if (n == 1) {
    		node.myItem = curr;
    		return node;
    	}
    	else {
	        int middle = n/2 + 1;
	        int count = 1;
	        LinkedList l = new LinkedList();
	        LinkedList r = new LinkedList();
	        while (count < middle) {
	        	l.add(curr);
	        	curr = iter.next();
	        	count++;
	        }
	        node.myItem = curr;
	        while (count < n && iter.hasNext()) {
	        	curr = iter.next();
	        	r.add(curr);
	        	count++;
	        }

	        node.myLeft = linkedListToTree(l.iterator(), l.size());
	        node.myRight = linkedListToTree(r.iterator(), r.size());
    	}
        return node;  
    }

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
    }
}
