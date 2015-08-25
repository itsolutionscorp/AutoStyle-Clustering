import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // build a balanced binary search tree out of an already sorted LinkedList
    private BSTNode linkedListToTree (Iterator iter, int n) {
    	BSTNode newNode = new BSTNode();
    	if (n == 1) {
    		newNode.myItem = iter.next();
    		return newNode;
    	}
    	int index = 0;
    	LinkedList leftside = new LinkedList();
    	while (index < n/2) { //split in half
    		leftside.add(iter.next());
    		index++;
    	}
    	newNode.myItem = iter.next();
    	if (leftside.size() == 1) {
    		newNode.myLeft = new BSTNode();
    	}
    	if (iter.hasNext()) {
    		newNode.myLeft = linkedListToTree(leftside.iterator(), leftside.size());
    		LinkedList rightside = new LinkedList();
    		while (index < n-1) {
    			rightside.add(iter.next());
    			index++;
    		}
    		newNode.myRight = linkedListToTree(rightside.iterator(), rightside.size());
    	}	
        return newNode;
    }

    class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
    }
}
