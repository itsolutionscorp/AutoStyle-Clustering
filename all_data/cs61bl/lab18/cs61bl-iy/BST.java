import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Your comment here
    private BSTNode linkedListToTree (Iterator iter, int n) {
        // TODO Your code here
    	if (n == 1 && iter.hasNext()) {
    		return new BSTNode(iter.next(), null, null);
    	} else if (n == 1 && !iter.hasNext()) {
    		return null;
    	}
    	int count = 0;
    	Object item      = null;
    	Object parent    = null;
    	LinkedList left  = new LinkedList();
    	LinkedList right = new LinkedList();
    	while (iter.hasNext()) {
    		item = iter.next();
    		if (count == n/2 + 1) {
    			parent = item;
    		} else if (count > n/2 + 1) {
    			right.add(item);
    		} else {
    		    left.add(item);
    		}
    		count++;
    	}
    	
        return new BSTNode(parent, linkedListToTree(left.iterator(), n/2), 
        		             linkedListToTree(right.iterator(), n - n/2 - 1));
    }

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
        
        public BSTNode(Object Item) {
        	myItem = Item;
        	myLeft = myRight = null;
        }
        
        public BSTNode(Object Item, BSTNode Left, BSTNode Right) {
        	myItem = Item;
        	myLeft = Left;
        	myRight = Right;
        }
    }
}
