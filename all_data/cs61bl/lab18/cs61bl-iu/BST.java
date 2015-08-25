import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    /** Builds a balanced BST out of an linkedlist. 
    *  Assumes linkedlist is already sorted. 
    */ 
    private BSTNode linkedListToTree (Iterator iter, int n) {
    	BSTNode myTree = new BSTNode();
    	//base cases 
    	if(n == 0) {
    		return null; 
    	}
    	else if(n == 1) {
    		myTree.myItem = iter.next(); 
    		return myTree;
    	}
    	else if(n == 2) {
    		myTree.myRight = new BSTNode();
    		myTree.myItem = iter.next(); 
    		myTree.myRight.myItem = iter.next(); 
    		return myTree; 
    	}
    	else if(n==3) {
    		myTree.myRight = new BSTNode();
    		myTree.myLeft = new BSTNode();
    		Object temp = iter.next(); 
    		myTree.myItem = iter.next(); 
    		myTree.myLeft.myItem = temp; 
    		myTree.myRight.myItem = iter.next(); 
    		return myTree; 
    	}
    	else {
    		//making two new linked lists for the left and right subtrees
    		int start = n/2;
			LinkedList leftTree = new LinkedList(); 
			LinkedList RightTree = new LinkedList();
    		while(start > 0) {
    			leftTree.add(iter.next());
    			start --; 
    		}
    		Object middle = iter.next(); 
    		while(iter.hasNext()) {
    			RightTree.add(iter.next());
    		}
    		return new BSTNode(middle, linkedListToTree(leftTree.iterator(), leftTree.size()), linkedListToTree(RightTree.iterator(), RightTree.size()));
    	}
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
        public BSTNode(Object o) {
        	myItem = o;
        	myLeft = null; 
        	myRight = null;
        }
        public BSTNode(Object o, BSTNode left, BSTNode right) {
        	myItem = o;
        	myLeft = left; 
        	myRight = right;
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
		private void printPreorder() {
			System.out.print(myItem + " ");
			if (myLeft != null) {
				myLeft.printPreorder();
			}
			if (myRight != null) {
				myRight.printPreorder();
			}
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
	public void printPreorder() {
		if (myRoot == null) {
			System.out.println("(empty tree)");
		} else {
			myRoot.printPreorder();
			System.out.println();
		}
	}

    public static void main(String[] args) {
    	LinkedList<Integer> trylist = new LinkedList<Integer>(); 
    	trylist.add(1);
     	trylist.add(2);
     	trylist.add(3);
     	trylist.add(4);
     	trylist.add(5);
     	trylist.add(6);
     	trylist.add(7);
    	BST trythis = new BST(trylist);
    	trythis.printInorder(); 
    	trythis.printPreorder(); 
    }
}
