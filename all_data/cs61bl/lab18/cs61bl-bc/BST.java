import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Your comment here
    private BSTNode linkedListToTree (Iterator iter, int n) {
        // TODO Your code here
    	BSTNode root = new BSTNode();
    	if (n == 1) {
    		root.myItem = iter.next();
    	}
    	else {
    		root.myLeft = linkedListToTree(iter, n/2);
            root.myItem = iter.next();
            root.myRight = linkedListToTree(iter, n/2);
    	}
        return root;
    }
    
    public static void main(String[] args) {
    	LinkedList list = new LinkedList();
    	list.add("1");
    	list.add("2");
    	list.add("3");
    	list.add("4");
    	list.add("5");
    	list.add("6");
    	list.add("7");
    	BST bst = new BST(list);
    	System.out.println(bst.myRoot.myItem);
    	System.out.println(bst.myRoot.myLeft.myItem);
    	System.out.println(bst.myRoot.myLeft.myLeft.myItem);
    	System.out.println(bst.myRoot.myLeft.myRight.myItem);
    	System.out.println(bst.myRoot.myRight.myItem);
    	System.out.println(bst.myRoot.myRight.myLeft.myItem);
    	System.out.println(bst.myRoot.myRight.myRight.myItem);
    }

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
    }
}
