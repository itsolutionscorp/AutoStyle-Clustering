import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Your comment here
    private BSTNode linkedListToTree (Iterator iter, int n) {
        // TODO Your code here
       	if (n <= 0){
    		return null;
    	}
    	if (n == 1){
    		return new BSTNode(iter.next()); 
    	}
    	else{
    	BSTNode left = linkedListToTree(iter, (n / 2));
    	Object root = iter.next();
    	BSTNode right = linkedListToTree (iter, n - (n / 2) - 1);
    	
    	return new BSTNode (root, left, right);
    	}


    }

    public static void main(String[] args) {
    	LinkedList l1 = new LinkedList();
    	l1.add(1);
    	BST bst1 = new BST (l1);
    	bst1.myRoot.print();
    	
    	
    	LinkedList l2 = new LinkedList();
    	l2.add(1);
    	l2.add(2);
    	l2.add(3);
    	l2.add(4);
    	l2.add(5);
    	l2.add(6);
    	BST bst2 = new BST (l2);
    	bst2.myRoot.print();
    	
    	
    	LinkedList l3 = new LinkedList();
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
    	l4.add(1);
    	l4.add(2);
    	l4.add(3);
    	l4.add(4);

    	
    	BST bst4 = new BST (l4);
    	bst4.myRoot.print();
    }

    
    
    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
        
        public BSTNode(Object item) {
            myItem = item;
            myLeft = myRight = null;
        }

        public BSTNode(Object item, BSTNode left, BSTNode right) {
            myItem = item;
            myLeft = left;
            myRight = right;
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

}
