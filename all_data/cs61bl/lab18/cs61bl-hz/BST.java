import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }
    
    public void print() {
        if (myRoot != null) {
            myRoot.print(0);
        }
    }

    // builds a BST out of an already sorted linked list
    private BSTNode linkedListToTree (Iterator iter, int n) {
		// TODO Your code here
    	BSTNode left = null;
    	BSTNode top = null;
    	BSTNode right = null;
    	if (n == 1 && iter.hasNext()) {
    		return new BSTNode(iter.next());
    	} else if (n == 2) {
    		if (iter.hasNext()) {
    			left = new BSTNode(iter.next());
    		} if (iter.hasNext()) {
    			return new BSTNode(iter.next(), left, null);
    		}
    	} else if (n == 3) {
    		if (iter.hasNext()) {
    			left = new BSTNode(iter.next());
    		} else {
    			return null;
    		} if (iter.hasNext()) {
    			top = new BSTNode(iter.next(), left, null);
    		} if (iter.hasNext()) {
    			top.myRight  = new BSTNode(iter.next());
    			return top;
    		} else {
    			return null;
    		}
    	}
    		left = linkedListToTree(iter, n/2);
    		top = new BSTNode(iter.next());
    		if (n%2 == 1) {
    			right = linkedListToTree(iter, n/2);
    		} else {
    			right = linkedListToTree(iter, (n-1)/2);
    		}
    		top.myRight = right;
    		top.myLeft = left;
    		return top;
    	
    }

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
        
        BSTNode(Object item) {
        	myItem = item;
        }
        
        BSTNode(Object item, BSTNode left, BSTNode right) {
        	myItem = item;
        	myLeft = left;
        	myRight = right;
        }
        
        private static final String indent1 = "    ";

        private void print(int indent) {
            // TODO your code here
        	if (myRight != null) {
            	myRight.print(indent + 1);
            }
        	
            println (myItem, indent);
            // TODO your code here
            if (myLeft != null) {
        		myLeft.print(indent + 1);
        	}
        }

        private void println(Object obj, int indent) {
            for (int k=0; k<indent; k++) {
                System.out.print(indent1);
            }
            System.out.println(obj);
        }
    }
}
