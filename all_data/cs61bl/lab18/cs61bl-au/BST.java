import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Your comment here
    private BSTNode linkedListToTree (Iterator iter, int n) {
        // TODO Your code here
    	if (n <= 0) return null;
    	BSTNode left = linkedListToTree(iter, n/2);
    	BSTNode root = new BSTNode(iter.next());
    	root.myLeft = left;
    	root.myRight = linkedListToTree(iter, n - n/2 -1);
    	return root;
    }
    
    public void printPreorder(){
    	myRoot.printPre();
    }

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
        
        public BSTNode(Object item){
        	myItem = item;
        	myLeft = myRight = null;
        }
        
        public void printPre(){
        	System.out.println(myItem);
        	if (myLeft != null) myLeft.printPre();
        	if (myRight != null) myRight.printPre();
        }
    }
    
    public static void main(String[] arg){
    	LinkedList a = new LinkedList();
        a.add(1);
        a.add(2);
        a.add(3);
    	BST b = new BST(a);
    	b.printPreorder();
    }
    
}
