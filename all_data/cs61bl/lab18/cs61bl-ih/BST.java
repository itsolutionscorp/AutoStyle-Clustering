import java.util.*;

public class BST {
    BSTNode myRoot;

    public  BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    //To convert a linked list to a tree, we use the iterator of the linked list and the size of the linked list.
    //Call a helper method to determine the start index and the end index of the linked list, where we call the iter.next() O(N) times.
    private BSTNode linkedListToTree (Iterator iter, int n) {
    	return linkedListToTree (iter, 0, n-1);
    }
    
    private BSTNode linkedListToTree (Iterator iter, int start, int end) {
    	//make a new BSTNode object
    	BSTNode my = new BSTNode();
    	//To avoid end < start
    	if(end < start){
    		return null;
    	}
    	//if end is equal to start, just make Node with no child with the item equal to the next in the linked list
    	if((end - start) == 0){
        	if(iter.hasNext()){
        		my.myItem = iter.next();
        		return my;
        	}
        }
    	//use recursive call to find all nodes, from the leftmost leaf to the root, and then to the rightmost leaf
        else{
        	int mid = start + (end-start)/2;
        	my.myLeft = linkedListToTree (iter, start, mid-1);
        	if(iter.hasNext()){
        		my.myItem = iter.next();
        	}
        	my.myRight = linkedListToTree (iter, mid+1, end);
        	return my;
        }
    	return my;
    }

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
    }
    
    public static void main(String args[]){
    	LinkedList a = new LinkedList();
    	a.add(0);
    	a.add(1);
    	a.add(2);
    	a.add(3);
    	a.add(4);
    	a.add(5);
    	a.add(6);
    	BST b = new BST(a);
    	System.out.println(b.myRoot.myItem);
    }
}
