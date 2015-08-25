import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Construct a tree by recursively calling linkedListToTree method on the left and right halves of the list
    private BSTNode linkedListToTree (Iterator iter, int n) {
        if (n == 1) {
        	BSTNode leaf = new BSTNode();
        	leaf.myItem = iter.next();
        	return leaf;
        }
        if (n == 2) {
        	BSTNode r = new BSTNode();
        	r.myItem = iter.next();
        	r.myRight = new BSTNode();
        	r.myRight.myItem = iter.next();
        	return r;
        }
    	ArrayList<Integer> left = new ArrayList<Integer>();
    	ArrayList<Integer> right = new ArrayList<Integer>();
    	for(int i = 0; i < n/2; i++) {
    		left.add((Integer)iter.next());
    	}
    	BSTNode root = new BSTNode();
    	root.myItem = iter.next();
    	while(iter.hasNext()) {
    		right.add((Integer)iter.next());
    	}
    	if (left.size() > 0) {
    		root.myLeft = linkedListToTree(left.iterator(), left.size());
    	}
    	if (right.size() > 0) {
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
    	LinkedList<Integer> l = new LinkedList<Integer>();
    	l.add(1);
    	l.add(2);
    	l.add(3);
    	l.add(4);
    	l.add(5);
    	BST b = new BST(l);
    	System.out.println(b.myRoot.myItem);
    	System.out.println(b.myRoot.myLeft.myItem);
    	System.out.println(b.myRoot.myRight.myItem);
    	System.out.println(b.myRoot.myRight.myRight.myItem);
    	System.out.println(b.myRoot.myLeft.myRight.myItem);
    }
}
