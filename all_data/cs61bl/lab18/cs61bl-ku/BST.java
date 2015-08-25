import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Your comment here
    private static BSTNode linkedListToTree (Iterator iter, int n) {
        ArrayList<Object> array = new ArrayList<Object>();
        while (iter.hasNext()) {
        	array.add(iter.next());
        }
        return linkedHelper(array, 0, array.size() - 1);
    }

    private static BSTNode linkedHelper(ArrayList<Object> array, int first, int last) {
    	if (first > last) {
    		return null;
    	}
    	int middle = (first + last) / 2;
    	BSTNode root = new BSTNode(array.get(middle));
    	root.myLeft = linkedHelper(array, first, middle - 1 );
    	root.myRight = linkedHelper(array, middle + 1, last);
    	return root;
    }
    private static class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
        
        private BSTNode(Object obj) {
        	myItem = obj;
        	myLeft = null;
        	myRight = null;
        }
    }
}
