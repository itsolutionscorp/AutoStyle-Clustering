import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Your comment here
    // Takes an iterator and the size of the list it represents and builds the BST from it.
    // It adds all the items to an ArrayList and splits the list according to left and right branch.
    private static BSTNode linkedListToTree (Iterator iter, int n) {
        // TODO Your code here
    	ArrayList<Object> listItems = new ArrayList<Object>();
    	while (iter.hasNext()) {
    		listItems.add(iter.next());
    	}
    	int middle;
    	if (listItems.size() % 2 == 0) {
    		middle = listItems.size() / 2;
    	} else {
    		middle = (listItems.size() - 1) / 2;
    	}
    	List<Object> left = listItems.subList(0, middle);
    	List<Object> right = listItems.subList(middle + 1, listItems.size());
    	BSTNode leftTree = null;
    	BSTNode rightTree = null;
    	if (!left.isEmpty()) {
    		leftTree = linkedListToTree(left.iterator(), left.size());
    	}
    	if (!right.isEmpty()) {
    		rightTree = linkedListToTree(right.iterator(), right.size());
    	}
    	BSTNode actualTree = new BSTNode();
    	actualTree.myItem = listItems.get(middle);
    	actualTree.myLeft = leftTree;
    	actualTree.myRight = rightTree;
        return actualTree;
    }

    private static class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
        
        public void print() {
        	System.out.println(myItem.toString());
        	if (myLeft != null) {
        		System.out.println("LEFT");
            	myLeft.print();
        	}
        	System.out.println("other branch");
        	if (myRight != null) {
        		System.out.println("RIGHT");
            	myRight.print();
        	}
         }
    }
    
    public static void main(String[] args) {
    	LinkedList list = new LinkedList();
    	list.add(1);
    	list.add(2);
    	list.add(3);
    	list.add(4);
    	list.add(5);
    	list.add(6);
//    	list.add(7);
    	
    	BST test = new BST(list);
    	test.myRoot.print();
    }
}
