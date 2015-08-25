import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Your comment here
    /**  It will make the node at the middle in case of odd or middle + 1 in the case of even nodes, to
     *   be the root node. Then it will do recursive call to do the same thing to the root's left and 
     *   right nodes.
     * 
     * @param iter
     * @param n
     * @return
     */
    private BSTNode linkedListToTree (Iterator iter, int n) {
        // TODO Your code here
    	if(n <= 0){
        return null;
    	}else if(n ==1){
    		return new BSTNode(iter.next());
    	}else{
    		BSTNode x = linkedListToTree(iter,n/2 );
    		Object rootItem = iter.next();
    		BSTNode y = linkedListToTree(iter,n-(n/2)-1);
    		return new BSTNode(x,y, rootItem);
    	}
    }

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
    
    public BSTNode(Object item) {
        myItem = item;
        myLeft = myRight = null;
    }

    public BSTNode( BSTNode x, BSTNode y, Object item) {
        myItem = item;
        myLeft = x;
        myRight = y;
    }
    
    private void print() {
    	if (myRoot != null){
    		printHelper(myRoot, 0);
    	}
    }
    
    private static final String indent1 = "      ";
    
    private void printHelper(BSTNode root, int indent ){
    	if (root.myRight != null){
    		printHelper(root.myRight, indent + 1);
    	}
    	println (root.myItem, indent);
    	if (root.myLeft != null) {
    		printHelper(root.myLeft, indent + 1);
    		
    	}
    }
    
    private void println(Object obj, int indent){
    	for (int k = 0; k < indent; k++){
    		System.out.print(indent1);
    	}
    	
    	 System.out.println(obj);
    }
    }
    
    public static void main(String[] args) {
    	LinkedList l1 = new LinkedList();
    	l1.add(1);
    	BST bst1 = new BST (l1);
    	bst1.myRoot.print();
    	System.out.println("-------------------------------");
    	
    	LinkedList l2 = new LinkedList();
    	l2.add(1);
    	l2.add(2);
    	l2.add(3);
    	l2.add(4);
    	l2.add(5);
    	l2.add(6);
    	BST bst2 = new BST (l2);
    	bst2.myRoot.print();
    	
    	System.out.println("-------------------------------");
    	LinkedList l3 = new LinkedList();
    	l3.add(0);
    	l3.add(1);
    	l3.add(2);
    	l3.add(3);
    	l3.add(4);
    	l3.add(5);
    	l3.add(6);
    	l3.add(7);
    	l3.add(8);
    	l3.add(9);
    	
    	BST bst3 = new BST (l3);
    	bst3.myRoot.print();
    	
    	System.out.println("-------------------------------");
    	
    	
    	LinkedList l4 = new LinkedList();
    	l4.add(0);
    	l4.add(1);
    	l4.add(2);
    	l4.add(3);
    	l4.add(4);

    	
    	BST bst4 = new BST (l4);
    	bst4.myRoot.print();
    }


}





