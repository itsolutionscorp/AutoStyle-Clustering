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
    		return root;
    	}
       	int count = 0;
    	LinkedList left = new LinkedList();
       	while (count < (n / 2)) {
       		left.add(iter.next());
       		count++;
       	}
       	root.myItem = iter.next();
       	if (left.size() == 1) {
       		root.myLeft = new BSTNode();
       		root.myLeft.myItem = left.getFirst();;
       	}
       	if (iter.hasNext()) {
       		root.myLeft = linkedListToTree(left.iterator(), left.size());
       		LinkedList right = new LinkedList();
           	while (count < n - 1) {
           		right.add(iter.next());
           		count++;
           	}
           	root.myRight = linkedListToTree(right.iterator(), right.size());
       	}
        return root;
    }

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
    }
    
    public static void main(String[] args) {
    	LinkedList l = new LinkedList();
    	l.add(1);
    	l.add(2);
    	l.add(3);
    	l.add(4);
    	l.add(5);
    	l.add(6);
    	l.add(7);
    	BST b = new BST(l);
    	System.out.println(b.myRoot.myItem);
    	System.out.println(b.myRoot.myLeft.myItem);
    	System.out.println(b.myRoot.myRight.myItem);
    	System.out.println(b.myRoot.myLeft.myRight.myItem);
    	System.out.println(b.myRoot.myRight.myLeft.myItem);
    	System.out.println(b.myRoot.myLeft.myLeft.myItem);
    	System.out.println(b.myRoot.myRight.myRight.myItem);
    }
}
