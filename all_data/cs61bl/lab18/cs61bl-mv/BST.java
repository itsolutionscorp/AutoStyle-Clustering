import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Your comment here
    private BSTNode linkedListToTree (Iterator iter, int n) {
        // TODO Your code here
    	BSTNode node = new BSTNode();
    	if(n == 1){
    		BSTNode node2 = new BSTNode();
    		node2.myItem = iter.next();
    		node2.myLeft = node2.myRight = null;
    		return node2;
    	}
    	node.myLeft = linkedListToTree(iter,n/2);
    	node.myItem = iter.next();
    	node.myRight = linkedListToTree(iter,n/2);
    	return node;
    	
    }

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
    }
}
