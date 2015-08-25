import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size(), 0);
    }

    // Your comment here
    private BSTNode linkedListToTree (Iterator iter, int n, int indent) {
    	if (n==0){
    		return null;
    	} else {
    		BSTNode node = new BSTNode();    		
    		node.myLeft = linkedListToTree(iter, n/2, indent+1);
    		node.myItem = iter.next();
    		String ind = "";
    		for (int i = 0; i < indent; i++){
    			ind = ind + "    ";
    		}
    		System.out.println(ind + node.myItem);
    		node.myRight = linkedListToTree(iter, n-n/2-1, indent+1);
    		return node;
    	}
    }

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
    }
}
