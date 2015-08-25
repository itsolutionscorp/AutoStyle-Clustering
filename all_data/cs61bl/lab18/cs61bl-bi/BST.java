import java.util.*;

public class BST {
    BSTNode myRoot;
    static int size;

    public BST(LinkedList list) {
    	if (list != null) {
    		size = list.size();
            myRoot = linkedListToTree(list.iterator(), list.size());
    	}
    }

	// Your comment here
	private static BSTNode linkedListToTree(Iterator iter, int n) {
		// TODO Your code here
		BSTNode left = null;
		BSTNode right = null;
		if ( n <= size/2 && n > 1)
			left = linkedListToTree(iter, n-1);	
		Object item = iter.next();
		if (n > size/2) 
			right = linkedListToTree(iter, n-1); 
		BST BSTtree = new BST(null);
		BSTNode bst = BSTtree.new BSTNode(item, left, right);
		return bst;
	}


//    private class BSTNode {
    class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
        
        public BSTNode(Object item, BSTNode left, BSTNode right) {
        	myItem = item;
        	myLeft = left;
        	myRight = right;
        	
        }
        
    	public void printInorder() {
            if (myLeft != null) {
                myLeft.printInorder();
            }
            System.out.print(myItem + " " );
            if (myRight != null) {
                myRight.printInorder();
            }
        }
    	

    }
}
