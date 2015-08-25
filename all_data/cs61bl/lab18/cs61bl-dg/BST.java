import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    /**
     * Creates a tree out of a sorted linked list. Does so by recursively
     * constructing the left side of the tree, then adding the middle element,
     * then recursively contructing the right side of the tree, and finally
     * creating a new BSTNode with those parameters. Empty nodes are represented
     * as nulls.
     *
     * Does NOT construct a maximally balanced tree.
     *
     * @param iter: An iterator for the linked list
     * @param n: The length of the input linked list
     */

    private BSTNode linkedListToTree (Iterator iter, int n) {
        if (n < 1) {
            return null;
        }
        BSTNode left = linkedListToTree(iter, n/2);
        Object item = iter.next();
        BSTNode right = linkedListToTree(iter, n - 1 - n/2);
        return new BSTNode(item, left, right);
    }

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;

        public BSTNode(Object value, BSTNode left, BSTNode right) {
        	myItem = value;
        	myLeft = left;
        	myRight = right;
        }

        public BSTNode(Object value) {
        	myItem = value;
        	myLeft = null;
        	myRight = null;
        }

        public BSTNode linkedListToNode(Iterator iter, int n) {
            if (n < 1) {
                return null;
            }
            BSTNode left = linkedListToTree(iter, n/2);
            Object item = iter.next();
            BSTNode right = linkedListToTree(iter, n - 1 - n/2);
            return new BSTNode(item, left, right);
        }
    }


    public static void main(String[] args) {
    	LinkedList myList = new LinkedList();
    	myList.add(1);
    	myList.add(2);
    	myList.add(3);
    	myList.add(4);
    	myList.add(5);
        myList.add(6);
        myList.add(7);
        myList.add(8);
    	BST myTree = new BST(myList);
        System.out.println(myTree.myRoot.myLeft.myLeft.myLeft.myItem);
        System.out.println(myTree.myRoot.myLeft.myLeft.myItem);
        System.out.println(myTree.myRoot.myLeft.myItem);
        System.out.println(myTree.myRoot.myLeft.myRight.myItem);
        System.out.println(myTree.myRoot.myItem);
        System.out.println(myTree.myRoot.myRight.myLeft.myItem);
        System.out.println(myTree.myRoot.myRight.myItem);
        System.out.println(myTree.myRoot.myRight.myRight.myItem);

    }
}
