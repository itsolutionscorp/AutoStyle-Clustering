import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Your comment here
    /**
     * Helps construct a new BST from a Linked List that is
     * almost or maximally balanced
     * @param iter Iterator passed in from constructor
     * @param n size of the LinkedList passed in
     */
    private static BSTNode linkedListToTree (Iterator iter, int n) {
        // TODO Your code here
    	if (n == 0) {
    		return null;
    	} else if (n == 1) {
    		BSTNode root = new BSTNode();
    		root.myItem = iter.next();
    		return root;
    	} else {
    		BSTNode root = new BSTNode();
    		int count = 0;
    		LinkedList firstHalf = new LinkedList();
            while (count < n/2) {
            	firstHalf.add(iter.next());
            	count ++;
            }
            
            root.myItem = iter.next();
//            LinkedList secondHalf = new LinkedList();
//            while (iter.hasNext()) {
//            	secondHalf.add(iter.next());
//            }
            root.myLeft = linkedListToTree(firstHalf.iterator(), firstHalf.size());
            root.myRight = linkedListToTree(iter, n-count-1);
            return root;
    	}
        
    }

    private static class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
    }
    
    public static void main(String[] args) {
    	LinkedList list = new LinkedList();
    	list.add(1);
    	list.add(2);
    	list.add(3);
    	list.add(4);
    	list.add(5);
    	list.add(6);
    	list.add(7);
    	list.add(8);
    	list.add(9);
    	list.add(10);
    	BST test = new BST(list);
    	System.out.println(test.myRoot.myItem);
    	System.out.println(test.myRoot.myLeft.myItem);
    	System.out.println(test.myRoot.myRight.myItem);
    	System.out.println(test.myRoot.myLeft.myLeft.myItem);
    	System.out.println(test.myRoot.myLeft.myRight.myItem);
    	System.out.println(test.myRoot.myRight.myLeft.myItem);
    	System.out.println(test.myRoot.myRight.myRight.myItem);
    	System.out.println(test.myRoot.myLeft.myLeft.myLeft.myItem);//1
    	System.out.println(test.myRoot.myLeft.myRight.myLeft.myItem);//4
    	System.out.println(test.myRoot.myRight.myLeft.myLeft.myItem);//7
    }
}
