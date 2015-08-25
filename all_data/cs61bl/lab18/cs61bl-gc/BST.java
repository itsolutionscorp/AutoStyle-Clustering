import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Makes a Binary Search Tree from a sorted Linked List
    private static BSTNode linkedListToTree (Iterator iter, int n) {
    	BSTNode returning = new BSTNode();
    	LinkedList l = new LinkedList();
    	LinkedList r = new LinkedList();
    	
    	for (int i = 0; i < n/2; i ++) {
    		l.add(iter.next());
    	}
    	returning.myItem = iter.next();
    	for (int i = (n/2 + 1); i < n; i++) {
    		r.add(iter.next());
    	}
    	if (! l.isEmpty())
    		returning.myLeft = linkedListToTree(l.iterator(), l.size());
    	if (! r.isEmpty())
    		returning.myRight = linkedListToTree(r.iterator(), r.size());
    	return returning;
    }
    
    public void printInorder() {
        if (myRoot == null) {
            System.out.println("(empty tree)");
        } else {
            myRoot.printInorder();
            System.out.println();
        }
    }


    private static class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
    
    
	    private void printInorder() {
	        if (myLeft != null) {
	            myLeft.printInorder();
	        }
	        System.out.print(myItem + " ");
	        if (myRight != null) {
	            myRight.printInorder();
	        }
	    }
    }
}
