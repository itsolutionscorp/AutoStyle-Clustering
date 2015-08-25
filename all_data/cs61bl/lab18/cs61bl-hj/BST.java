import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Your comment here
    private BSTNode linkedListToTree (Iterator iter, int n) {
    	ArrayList<Object> list = new ArrayList<Object>();
    	while (iter.hasNext()) {
    		list.add(iter.next());
    	}
    	myRoot = helpMe(list);
    	return myRoot;
    }
    
    public BSTNode helpMe(ArrayList<Object> list) {
    	if (list.size() == 1) {
    		return new BSTNode(list.get(0));
    	}
    	int mid = (list.size() - 1) / 2;
    	return new BSTNode(list.get(mid),
    			   helpMe(new ArrayList<Object>(list.subList(0, mid))),
    			   helpMe(new ArrayList<Object>(list.subList(mid + 1, list.size()))));
    }

    public void printPreorder() {
        if (myRoot == null) {
            System.out.println("(empty tree)");
        } else {
            myRoot.printPreorder();
            System.out.println();
        }
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
        
        public BSTNode(Object item, BSTNode left, BSTNode right) {
        	myItem = item;
        	myLeft = left;
        	myRight = right;
        }

        public void printPreorder() {
            System.out.print(myItem + " ");
            if (myLeft != null) {
                myLeft.printPreorder();
            }
            if (myRight != null) {
                myRight.printPreorder();
            }
        }
    }
    
    public static void main(String[] args) {
    	LinkedList<Object> test = new LinkedList<Object>();
    	test.add(1);
    	test.add(2);
    	test.add(3);
    	test.add(4);
    	test.add(5);
    	test.add(6);
    	test.add(7);
    	System.out.println(test);
    	BST tree = new BST(test);
    	tree.printPreorder();
    }
}
