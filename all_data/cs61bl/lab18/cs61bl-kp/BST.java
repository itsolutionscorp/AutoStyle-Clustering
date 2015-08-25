import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Your comment here Can make a maximally balanced tree using powers of two.
    private BSTNode linkedListToTree (Iterator iter, int n) {
        // TODO Your code here
        if (n == 0 || !iter.hasNext()) {
        	return null;
        }
        if (n / 2 == 0) {
        	BSTNode rtn = new BSTNode();
        	rtn.myItem = iter.next();
        	return rtn;
        }
        int k = 1;
        while (k < n) {
        	k = k * 2;
        }
        k = k / 2;
        BSTNode left = linkedListToTree(iter, k);
        BSTNode center = linkedListToTree(iter, 1);
        BSTNode right = linkedListToTree(iter, n - k - 1);
        center.myRight = right;
        center.myLeft = left;
        return center;
    }
    
    public static void print(BSTNode a) {
    	if (a == null) {
    		return;
    	}
    	print(a.myLeft);
    	System.out.println(a.myItem);
    	print(a.myRight);
    }

    private static class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
    }
    
    public static void main(String[] args) {
    	LinkedList alan = new LinkedList();
    	alan.add(1);
    	alan.add(2);
    	alan.add(3);
    	alan.add(4);
    	alan.add(5);
    	alan.add(6);
    	alan.add(7);
    	alan.add(8);
    	alan.add(9);
//    	alan.add(10);
//    	alan.add(11);
//    	alan.add(12);
//    	alan.add(13);
//    	alan.add(14);
//    	alan.add(15);
//    	alan.add(16);
    	print(new BST(alan).myRoot);
    }
}
