import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());

    }
    
    public void printPreorder() {
        if (myRoot == null) {
            System.out.println("(empty tree)");
        } else {
            myRoot.printPreorder();
            System.out.println();
        }
    }
    // Your comment here
    private BSTNode linkedListToTree (Iterator iter, int n) {
    	ArrayList list = iterToArrayList(iter);
    	myRoot = linkedListToTree (list, n);
        return myRoot;
    }
    
    private BSTNode linkedListToTree (ArrayList list, int n) {
    	if(list.size() == 0) {
    		return null;
    	}
    	if (n % 2 == 1 ) {
    		BSTNode left = linkedListToTree(new ArrayList(list.subList(0, n/2)), (n-1)/2);
    		BSTNode right = linkedListToTree(new ArrayList(list.subList(n/2 + 1, n)), (n-1)/2);
    		return new BSTNode(list.get((n-1)/2), left, right);
    	} else {
    		BSTNode left = linkedListToTree(new ArrayList(list.subList(0, n/2)), n/2);
    		BSTNode right = linkedListToTree(new ArrayList(list.subList(n/2 + 1, n)), n/2 - 1);
    		return new BSTNode(list.get(n/2), left, right);
    	}
    }
    
    private ArrayList iterToArrayList (Iterator iter) {
    	ArrayList list = new ArrayList();
    	while(iter.hasNext()) {
    		list.add(iter.next());
    	}
        return list;
    }

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
        
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
    }
    
    public static void main(String[] arg) {
    	LinkedList<Integer> list = new LinkedList<Integer>();
    	list.add(1);
    	list.add(3);
    	list.add(5);
    	list.add(7);
    	list.add(9);
    	list.add(11);
    	list.add(13);
    	BST b = new BST(list);
    	b.printPreorder();
    	
    }
    
    
    
    
    
    
}
