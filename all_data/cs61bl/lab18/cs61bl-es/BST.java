import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    /** 
    * Iterates through a sorted linkedList to create a balanced
    * binary search tree
    */
    private static BSTNode linkedListToTree (Iterator iter, int n) {
        if (!iter.hasNext() || n <= 0) {
        	return null;
        }
        if (n == 1) {
        	return new BSTNode(iter.next());
        }
        else {
        	BSTNode left = linkedListToTree(iter, n/2);
        	Object rootItem = iter.next();
        	BSTNode right = linkedListToTree(iter, n - 1 - n/2);
        	return new BSTNode(rootItem, left, right);
        }
    }
    
    // Print the values in the tree in preorder: root value first,
    // then values in the left subtree (in preorder), then values
    // in the right subtree (in preorder).
    public void printPreorder() {
        if (myRoot == null) {
            System.out.println("(empty tree)");
        } else {
            myRoot.printPreorder();
            System.out.println();
        }
    }
    
    // Print the values in the tree in inorder: values in the left
    // subtree first (in inorder), then the root value, then values
    // in the right subtree (in inorder).
    public void printInorder() {
        if (myRoot == null) {
            System.out.println("(empty tree)");
        } else {
            myRoot.printInorder();
            System.out.println();
        }
    }

    private static class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
        
        public BSTNode(Object item) {
        	myItem = item;
        	myLeft = null;
        	myRight = null;
        }
        
        public BSTNode(Object item, BSTNode left, BSTNode right) {
        	myItem = item;
        	myLeft = left;
        	myRight = right;
        }
        
        private void printPreorder() {
            System.out.print(myItem + " ");
            if (myLeft != null) {
                myLeft.printPreorder();
            }
            if (myRight != null) {
                myRight.printPreorder();
            }
        }

        private void printInorder() {
            if (myLeft != null) {
                myLeft.printInorder();
            }
            System.out.print(myItem + " ");
            if (myRight != null) {
                myRight.printInorder();
            }
        }
    }
}
