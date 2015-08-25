import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
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

    
    // Your comment here
    private static BSTNode linkedListToTree (Iterator iter, int n) {
        // TODO Your code here
    	while(iter.hasNext()) {
    		if (n == 0) return null;
    		if (n == 1) {
        		BSTNode leaf = new BSTNode();
        		leaf.myItem = iter.next();
        		return leaf;
        	}
        	BSTNode left = linkedListToTree(iter, n/2);
        	BSTNode result = new BSTNode();
        	result.myItem = iter.next();
        	result.myLeft = left;
        	BSTNode right = linkedListToTree(iter, n-(n/2)-1);
        	result.myRight = right;
        	return result;
    	}
    	return null;
    }
    
    
    private static BSTNode buildHelper(BSTNode t) {
    	int half = t.myHeight / 2;
    	int i = 0;
    	BSTNode newRoot = t.myLeft;
    	while (i < half) {
    		t.myLeft = newRoot.myRight;
    		newRoot.myRight = t;
    	}
    	return newRoot;
    }
    
    
    private static class BSTNode {
        int myHeight;
    	Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
        
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
