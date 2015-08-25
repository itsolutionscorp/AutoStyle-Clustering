import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Your comment here
    // 
    private BSTNode linkedListToTree (Iterator iter, int n) {
        // TODO Your code here
        if (n==0) {
        	return null;
        }
    	if (n==1) {
        	Object a = iter.next();
        	BSTNode result = new BSTNode(a);
        	return result;
        } else {
        	BSTNode left = linkedListToTree(iter, n/2);
        	BSTNode result = linkedListToTree(iter, 1);
        	BSTNode right = linkedListToTree(iter,n-1-n/2);
        	result.myLeft = left;
        	result.myRight = right;
        	return result;
        }
    }
    public static void main (String[] args) {
    	LinkedList<Integer> test = new LinkedList<Integer>();
		test.add(0);
		test.add(1);
		test.add(2);
		test.add(3);
		test.add(4);
		test.add(5);
		test.add(6);
		BST result = new BST(test);
		System.out.println(result.myRoot.myItem);
		System.out.println(result.myRoot.myRight.myItem);
		System.out.println(result.myRoot.myRight.myRight.myItem);
		System.out.println(result.myRoot.myRight.myLeft.myItem);
		System.out.println(result.myRoot.myLeft.myItem);
		System.out.println(result.myRoot.myLeft.myRight.myItem);
		System.out.println(result.myRoot.myLeft.myLeft.myItem);
		LinkedList<Integer> test2 = new LinkedList<Integer>();
		test2.add(0);
		test2.add(1);
		test2.add(2);
		test2.add(3);
		test2.add(4);
		test2.add(5);
		result = new BST(test2);
		System.out.println("new line");
		System.out.println(result.myRoot.myItem);
		System.out.println(result.myRoot.myRight.myItem);
		System.out.println(result.myRoot.myRight.myLeft.myItem);
		System.out.println(result.myRoot.myLeft.myItem);
		System.out.println(result.myRoot.myLeft.myRight.myItem);
		System.out.println(result.myRoot.myLeft.myLeft.myItem);
    }
    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
        public BSTNode(Object item) {
        	myItem = item;
        	myLeft = null;
        	myRight = null;
        }
    }
}
