import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Your comment here
    // Creates a new node and
    // Iterates through the first n/2 elements and sets the left tree to a tree based on them
    // sets myItem to the middle element
    // sets myRight to the last n - n/2 -1 elements
    private static BSTNode linkedListToTree (Iterator iter, int n) {
        // TODO Your code here
    	//build a new linked list out of all of the elements before the middle node,
    	//make the middle element its own node with its value as myItem
    	//set myLeft to recursive call on the new linked list
    	//set myRight to recursive call on the list where we were
    	//return the middle node
    	BSTNode root= new BSTNode();
    	if (n>1){
    		root.myLeft = linkedListToTree(iter, n/2);
    	}
    	root.myItem = iter.next();
    	if (n>2){
    		root.myRight = linkedListToTree(iter, n - n/2 -1);
    	}
        return root;
    }

    private static class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
        //default constructor sets all of these to null
        
    }
}
