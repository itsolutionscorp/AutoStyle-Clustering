import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Makes a BST object from an ordered linked-list.
    private BSTNode linkedListToTree (Iterator iter, int n) {
    		if (n == 0) {
    			return null;
    		}
    		BSTNode node = new BSTNode();
    		if (n == 1) {
        		node.myItem = iter.next();
        } else {
        		int left = (n)/2;
        		int right = n - 1 - left;
        		BSTNode leftTree = linkedListToTree(iter, left);
        		node.myItem = iter.next();
        		BSTNode rightTree = linkedListToTree(iter, right);
        		node.myLeft = leftTree;
        		node.myRight = rightTree;
        }
        return node;
    }

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
    }
    
}
