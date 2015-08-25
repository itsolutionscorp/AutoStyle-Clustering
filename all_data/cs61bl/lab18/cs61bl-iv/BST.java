import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Converts LinkedList to tree by dividing the list in half and recursively
    // creating the tree
    private BSTNode linkedListToTree (Iterator iter, int n) {
        // TODO Your code here
    	int counter = -1;
    	Object item = null;
    	LinkedList temp1 = new LinkedList();
    	LinkedList temp2 = new LinkedList();
    	while(iter.hasNext()) {
        	counter++;
        	if (counter == n/2) {
        		item = iter.next();
        	}
        	else if (counter < n/2) {
        		temp1.add(iter.next());
        	}
        	else {
        		temp2.add(iter.next());
        	}
        }
    	if (temp1.size() != 0 || temp2.size() != 0) {
        	return new BSTNode(item,linkedListToTree(temp1.iterator(),n/2), linkedListToTree(temp2.iterator(),n/2));
    	}
    	else {
    		return null;
    	}
    }

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
        
        public BSTNode(Object item) {
        	myItem = item;
        	myLeft = null;
        	myRight = null;
        }
        
        public BSTNode(Object item, BSTNode left, BSTNode right) {
        	myItem = item;
        	myLeft = left;
        	myRight = right;
        }
    }
    
    public static void main (String[] args) {
    	LinkedList a = new LinkedList();
    	for (int i = 1; i <= 10; i ++) {
    		a.add(i);
    	}
    	BST tree = new BST(a);
    	System.out.println(tree.myRoot.myItem.toString());
    	System.out.println(tree.myRoot.myLeft.myItem.toString());
    	System.out.println(tree.myRoot.myRight.myItem.toString());
    	
    }
}
