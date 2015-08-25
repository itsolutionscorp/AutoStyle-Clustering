import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList<Object> list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Your comment here
    private static BSTNode linkedListToTree (Iterator iter, int n) {
        // TODO Your code here
    	int count = 0;
    	LinkedList<Object> left = new LinkedList<Object>();
    	LinkedList<Object> right = new LinkedList<Object>();
    	BSTNode root = new BSTNode();
    	if (n == 0) {
    		return new BSTNode();
    	} else if (n == 1) {
    		root.myItem = iter.next();
    		return root;
    	}
    	while (iter.hasNext()) {
    		Object item = iter.next();
    		count ++;
    		if (count < n / 2) {
    			left.add(item);
    		} else if (count > n / 2) {
    			right.add(item);
    		} else if (count == n / 2) {
    			root.myItem = item;
    			root.myLeft = linkedListToTree(left.iterator(), left.size());
    		}
    	}
    	root.myRight = linkedListToTree(right.iterator(), right.size());
        return root;
    }
    
    public static void main(String[] args) {
    	LinkedList test = new LinkedList();
    	test.add(1);
    	BST tree = new BST(test);
    	test.add(2);
    	BST tree1 = new BST(test);
    	test.add(3);
    	BST tree2 = new BST(test);
    	test.add(4);
    	BST tree3 = new BST(test);
    	test.add(5);
    	BST tree4 = new BST(test);
    	test.add(6);
    	BST tree5 = new BST(test);
    	test.add(7);
    	BST tree6 = new BST(test);
    	test.add(8);
    	BST tree7 = new BST(test);
    	test.add(9);
    	BST tree8 = new BST(test);
    	
    	System.out.println(tree6.myRoot.myItem);
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
