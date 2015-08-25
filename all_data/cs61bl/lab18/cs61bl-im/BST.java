import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Your comment here
    private BSTNode linkedListToTree (Iterator iter, int n) {
    	if (!iter.hasNext() || n < 1) {
    		return null;
    	}
    	if (n == 1) {
    		return new BSTNode(iter.next());
    	}
    	
    	BSTNode left = linkedListToTree(iter, n/2);
    	BSTNode current = new BSTNode(iter.next());
    	BSTNode right = linkedListToTree(iter, n-n/2-1);
    	current.myLeft = left;
    	current.myRight = right;
    	return current;
    }

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
    
    public BSTNode (Object obj) {
    	myItem = obj;
    }
    
    }
    
    public static void main (String[] args) {
    	LinkedList list = new LinkedList();
    	for (int i = 0; i < 4; i++) {
    		list.add(i);
    	}
    	BST test = new BST(list);
    	System.out.println(test.myRoot.myItem);
    	System.out.println(test.myRoot.myLeft.myItem);
    	System.out.println(test.myRoot.myRight.myItem);
    	System.out.println(test.myRoot.myLeft.myLeft.myItem);
    }
}
