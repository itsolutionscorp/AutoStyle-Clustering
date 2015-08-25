import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Your comment here
	private static BSTNode linkedListToTree (Iterator iter, int n) {
        // TODO Your code here
    	BSTNode temp = null;
    	Object val = null;
    	LinkedList leftList = null;
    	LinkedList rightList = null;
    	for (int i = 0; i < n/2 + 1; i++) {
    		val = iter.next();
    		leftList.add(val);
    	}
    	for (int i = n/2 +1; i < n + 1; i++) {
    		rightList.add(iter.next());
    	}
    	temp.myItem = val;
    	temp.myLeft = linkedListToTree(leftList.iterator(), n/2);
    	temp.myRight = linkedListToTree(rightList.iterator(), n/2);
    	return temp;
    }

    private static class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
    }
}
