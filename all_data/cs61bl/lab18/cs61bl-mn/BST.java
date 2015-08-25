import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Your comment here
    private BSTNode linkedListToTree (Iterator iter, int n) {
        // TODO Your code here
    	if (n == 0) {
    		return null;
    	}
    	int i = 0;
    	Object item = null;
    	LinkedList leftList = new LinkedList();
    	LinkedList rightList = new LinkedList();
    	while (i < n/2) {
    		item = iter.next();
    		leftList.add(item);
    		i++;
    	}
    	myRoot.myItem = item;
    	i++;
    	while (i < n) {
    		item = iter.next();
    		rightList.add(item);
    		i++;
    	}
    	myRoot.myLeft = linkedListToTree(leftList.iterator(), leftList.size());
    	myRoot.myRight = linkedListToTree(rightList.iterator(), rightList.size());
    	return myRoot;
    }

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
    }
}
