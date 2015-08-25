import java.util.*;

public class BST {
    BSTNode myRoot;
    
    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }
    public BST(){
    	myRoot =null;
    }
    // Yes ,cause the left child has been constructed before the node,
    //   so at least it guarantee it has most one difference in number
    private static BSTNode linkedListToTree (Iterator iter, int n) {
    	BST temp = new BST();
    	if(n <= 0){
        return null;}
        
    	BSTNode curr = BST.linkedListToTree(iter, n/2);
    	BSTNode root = temp.new BSTNode(iter.next());
    	root.myLeft = curr;
    	root.myRight = BST.linkedListToTree(iter, n-n/2-1);
    	return root;
    }

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
        public BSTNode(Object myItem){
        	this.myItem = myItem;
        	myLeft = null;
        	myRight = null;
        }
        public BSTNode(Object myItem,BSTNode myLeft,BSTNode myRight){
             this.myItem = myItem;
             this.myLeft = myLeft;
             this.myRight = myRight;
        }
    }
    public static void main(String[] args){
    	LinkedList curr = new LinkedList();
    	curr.add(1);
    	curr.add(2);
    	curr.add(3);
    	curr.add(4);
    	curr.add(5);
    	curr.add(6);
    	curr.add(7);
    	BST myTree = new BST(curr);
    	
    	
    	
    }
}
