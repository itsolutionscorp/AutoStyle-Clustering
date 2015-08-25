import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Your comment here
    private BSTNode linkedListToTree (Iterator iter, int n) {
        // TODO Your code here
    	Object[] items = new Object[n];
    	for (int i = 0; i < n; i++) {
    		items[i] = iter.next();
    	}
        return helper(items, n);
    }
    
    private BSTNode helper(Object[] items, int n) {
    	if (items == null || items.length == 0) {
    		return null;
    	} else if (items.length == 1) {
    		return new BSTNode(items[0], null, null);
    	}
    	Object[] left = Arrays.copyOfRange(items, 0, n/2);
    	Object[] right = Arrays.copyOfRange(items, n/2 + 1, items.length);
    	return new BSTNode(items[n/2], helper(left, left.length), helper(right, right.length));
    }
    
    public void printMe() {
    	if (myRoot != null) {
    		myRoot.printMe(0);
    	}
    }

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
        
        private BSTNode(Object item, BSTNode left, BSTNode right) {
        	myItem = item;
        	myLeft = left;
        	myRight = right;
        }
        
        private void printMe(int n) {
        	for (;n != 0; n--) {
        		System.out.print('	');
        	}
        	System.out.println(myItem);
        	if (myLeft != null) {
        		myLeft.printMe(n + 1);
        	}
        	if (myRight != null) {
        		myRight.printMe(n + 1);
        	}
        }
    }
}
