import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Your comment here
    private BSTNode linkedListToTree (Iterator iter, int n) {
        // TODO Your code here
    	if (!iter.hasNext()) return null;
    	return sortedListToBST(iter, 0, n - 1);
    }
    
    private BSTNode sortedListToBST(Iterator iter, int start, int end) {
		if (start > end) return null;
		// we want to find the middle of the linked list. that's where the Root node is	
		int mid = (start + end) / 2;
		BSTNode current = (BSTNode)iter.next();
		// left side of the root node is the first half of the linked list 
		// this would not recurse if start and mid-1 are equal which will eventually happen
		BSTNode left = sortedListToBST(iter, start, mid - 1);
		BSTNode root = new BSTNode(current.myItem);
		// right side of the root nodes is the right side of the linked list
		// this would not recurse if start and mid-1 are equal which will eventually happen
		BSTNode right = sortedListToBST(iter, mid + 1, end);
		root.myLeft = left;
		root.myRight = right;
		return root;
	}

    
    
    private class BSTNode {
		Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
        
        public BSTNode (Object item){
        	myItem = item;
            myLeft = myRight = null;
        }
    }
}
