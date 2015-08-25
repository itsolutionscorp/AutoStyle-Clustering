import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Your comment here
    private static BSTNode linkedListToTree (Iterator iter, int n) {
        // TODO Your code here
    	if (n > 0) {
    		BSTNode t = new BSTNode();
    		t.myLeft = linkedListToTree(iter, n/2); 
   		   	t.myItem = iter.next();
   			t.myRight = linkedListToTree(iter, (int) Math.ceil(n/2.0)-1);
   			return t;
   		} 
   		return null;
    }
    

    private static class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
    
        public BSTNode() {
        	myItem = null;
        	myLeft = null;
        	myRight = null;
        }
        
    }
}
