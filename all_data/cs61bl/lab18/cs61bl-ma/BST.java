import java.util.*;

public class BST {
    BSTNode myRoot;

    public static void main(String[] args) {
    	LinkedList list = new LinkedList();
    	list.add(1);
    	list.add(2);
    	list.add(3);
    	list.add(4);
    	list.add(5);
    	list.add(6);
    	list.add(7);
    	list.add(8);
    	
    	BST bst = new BST(list);
    	bst.print();
    	
    }
    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Uses the iterator to split up the linked list into a root node, and left and right linked lists
    // Then recursively calls itself on left and right, attaching the results to the root
    private BSTNode linkedListToTree (Iterator iter, int n) {
    	if (n == 0) {
    		return null;
    	}
    	if (n == 1) {
    		return new BSTNode(iter.next());
    	}
    	LinkedList left = new LinkedList();
    	LinkedList right = new LinkedList();
    	BSTNode rootNode = new BSTNode();
    	int i = 1;
    	int root = n/2 + 1;
    	while (iter.hasNext()) {
    		if (i < root) {
    			left.add(iter.next());
    		} else if (i == root) {
    			rootNode = new BSTNode(iter.next()); 
    		} else {
    			right.add(iter.next());
    		}
    		i++;
    	}
    	rootNode.setLeft(linkedListToTree(left.iterator(), left.size()));
    	rootNode.setRight(linkedListToTree(right.iterator(), right.size()));
    	return rootNode;
    }
    
    public void print() {
    	myRoot.printHelper(0);
    }

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
        
        public BSTNode() {
        	
        }
        
        public BSTNode(Object myItem) {
        	this.myItem = myItem;
        }
        
        public BSTNode(Object myItem, BSTNode myLeft, BSTNode myRight) {
        	this.myItem = myItem;
        	this.myLeft = myLeft;
        	this.myRight = myRight; 
        }
        
        public void setLeft(BSTNode n) {
        	myLeft = n; 
        }
        
        public void setRight(BSTNode n) {
        	myRight = n; 
        }
        
        public void printHelper(int level) {
        	for (int i = 0; i < level*4; i++) {
        		System.out.print(" ");
        	}
        	System.out.println(myItem);
        	if (myLeft != null) {
	        	myLeft.printHelper(level + 1);
        	}
        	if (myRight != null) {
        		myRight.printHelper(level + 1);
        	}
        }
    }
}
