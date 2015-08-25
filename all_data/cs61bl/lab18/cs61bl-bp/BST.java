import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    /**
     * Converts a sorted linked list into a balanced tree.
     * @param iter Iterator of the linked list.
     * @param n Size of the linked list.
     * @return Root node of a tree.
     */
    private static BSTNode linkedListToTree (Iterator iter, int n) {
    	if(n == 0) {
    		return null;
    	}
    	BSTNode left = linkedListToTree(iter,n/2);
    	Object obj = iter.next();
    	BSTNode right = linkedListToTree(iter,n-n/2-1);
    	return new BSTNode(obj,left,right);
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
        
        public BSTNode(Object myItem, BSTNode myLeft, BSTNode myRight) {
        	this.myItem = myItem;
        	this.myLeft = myLeft;
        	this.myRight = myRight;
        }
        
        public void printInorder() {
        	if(myLeft != null) {
        		myLeft.printInorder();
        	}
        	System.out.println(myItem);
        	if(myRight != null) {
        		myRight.printInorder();
        	}
        }
    }
    
    public static void main(String[] args) {
    	LinkedList<Integer> ll = new LinkedList<Integer>();
    	for(int i = 1; i < 10; i++) {
    		ll.add(i);
        	linkedListToTree(ll.iterator(),i).printInorder();
    	}
    }
}
