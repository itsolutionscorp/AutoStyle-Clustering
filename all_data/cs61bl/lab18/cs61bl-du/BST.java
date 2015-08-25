import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    //Set 
    private BSTNode linkedListToTree(Iterator iter, int n) {
        if(!iter.hasNext()){
        	return null;
        }
    	return linkedListToTreeHelper(iter, 0, n - 1); //index - 1
    }
    
    private BSTNode linkedListToTreeHelper(Iterator iter, int start, int end) {
        if (start > end) {
        	return null;
        }
        int mid = (start + end) / 2;
        BSTNode leftTree = linkedListToTreeHelper(iter, start, mid - 1);
        BSTNode root = new BSTNode(iter.next());
        BSTNode rightTree = linkedListToTreeHelper(iter, mid + 1, end);
        root.myLeft = leftTree;
        root.myRight = rightTree;
        return root;
    }

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
        
        public BSTNode(Object item){
        	myItem = item;
        }
    }
}
