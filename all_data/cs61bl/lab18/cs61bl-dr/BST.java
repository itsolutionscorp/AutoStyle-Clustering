import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }
    
    public BSTNode root(){
    	return myRoot;
    }

    /**
     * This method takes an iterator over a LinkedList and constructs
     * a balanced binary search tree from it. 
     * @param iter
     * 		The iterator over the linked list.
     * @param n
     * 		The length of the linked list.
     * @return
     * 		The constructed balanced binary search tree.
     */
    private BSTNode linkedListToTree (Iterator iter, int n) {
    	ArrayList<Object> arr = new ArrayList<Object>();
    	while(iter.hasNext()) arr.add(iter.next());
    	BSTNode node = new BSTNode();
    	node.myItem = arr.get(n/2);
    	node.myLeft = listToNode(new ArrayList<Object>(arr.subList(0, n/2)));
    	node.myRight = listToNode(new ArrayList<Object>(arr.subList(n/2 + 1, n)));                
    	return node;
    }
    private BSTNode listToNode(ArrayList<Object> arr){    	
    	int n = arr.size();
    	if(n == 0) return null; 
    	BSTNode node = new BSTNode();
    	node.myItem = arr.get(n/2);
    	node.myLeft = listToNode(new ArrayList<Object>(arr.subList(0, n/2)));
    	node.myRight = listToNode(new ArrayList<Object>(arr.subList(n/2 + 1, n)));      	
    	return node;
    }

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
        
        public Object item(){
        	return myItem;
        }
    }
}
