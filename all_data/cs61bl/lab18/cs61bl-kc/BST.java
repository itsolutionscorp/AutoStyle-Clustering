import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Build a balanced binary search tree out of an already sorted Linked List. If list has size 0, it will return null. If list is even, then the tree is maximally balanced. If the tree is odd, then the tree is almost balanced.
    private static BSTNode linkedListToTree (Iterator iter, int n) {
        // TODO Your code here
    	
    	int middle;
    	if (n <= 0)
    		return null;
    	if (n%2!=0) {
    		middle = (n/2)+1;
    	} else {
    		middle = n/2;
    	int i = 1;	
    	while (iter.hasNext()) {
    		
    	}
    	Object mid = list.get(middle);
    	
    	BSTNode myLeft = linkedListToTree(iter, middle - 1);
    	
    
    		
    		list.add(iter.next());
    	}
    }
    	

    private static class BSTNode {
		Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
        
    }
}
