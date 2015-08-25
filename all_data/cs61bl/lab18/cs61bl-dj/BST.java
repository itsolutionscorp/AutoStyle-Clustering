import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Your comment here
    private BSTNode linkedListToTree (Iterator iter, int n) {
        // TODO Your code here
    	
    	BSTNode a1 = new BSTNode();
   
    	if (n == 0) {
    		return null;
    	}
    	
    	LinkedList copy = new LinkedList();
    	while (iter.hasNext()) {
    		copy.add(iter.next());
    	}
    	
    	Object item = copy.get(n/2);
    	
    	a1.myItem = item;
    	a1.myLeft = linkedListToTree(copy.subList(0, (n/2)).iterator(), copy.subList(0, (n/2)).size());
    	a1.myRight = linkedListToTree(copy.subList((n/2)+1, copy.size()).iterator(), copy.subList((n/2)+1, copy.size()).size());
    			
        return a1;
    }

    class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
        
    }
}
