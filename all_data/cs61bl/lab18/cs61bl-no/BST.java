import java.util.*;


public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
    	if (list == null){
    		return;
    	}
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Your comment here
    private BSTNode linkedListToTree (Iterator iter, int n) {
    	if (n == 0){
    		return null;
    	}
    	if(n == 1){
    		return new BSTNode(iter.next());
    	} else if (n == 2) {
    		BSTNode a = new BSTNode(iter.next());
    		return new BSTNode(iter.next(), a, null);
    	} else if (n == 3){
    		BSTNode left = new BSTNode(iter.next());
    		Object root = iter.next();
    		BSTNode right = new BSTNode(iter.next());
    		return new BSTNode(root, left, right);		
    	} else {
    		if (n %2 != 0){
    		BSTNode left = linkedListToTree(iter, n/2);
    		Object root = iter.next();
    		BSTNode right = linkedListToTree(iter, (n/2));
    		return new BSTNode(root, left, right);
    		} else {
    			BSTNode left = linkedListToTree(iter, n/2);
        		Object root = iter.next();
        		BSTNode right = linkedListToTree(iter, (n/2)-1);
        		return new BSTNode(root, left, right);
    		}
    	} 
    }
    
    public static void main(String[] args) {
    	LinkedList a = new LinkedList();
    	a.add(1);
    	a.add(2);
    	a.add(3);
    	a.add(4);
    	a.add(5);
    	a.add(6);
    	a.add(7);
    	a.add(8);
    	a.add(9);
    	a.add(10);
    	a.add(11);
    	
    	LinkedList b = new LinkedList();
    	b.add(1);
    	b.add(2);
    	b.add(3);
    	b.add(4);

    	LinkedList c = new LinkedList();
    	c.add(1);
    	c.add(2);
    	c.add(3);
    	c.add(4);
    	c.add(5);
    	c.add(6);


  
    	BST test1 = new BST(a);
    	test1.myRoot.print();
    	System.out.println();
    	System.out.println();
    	System.out.println();
    	BST test2 = new BST(b);
    	test2.myRoot.print();
    	System.out.println();
    	System.out.println();
    	System.out.println();
    	BST test3 = new BST(c);
    	test3.myRoot.print();
    	
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
        
        public void print() {
    		if (myRoot != null) {
    			printHelper(myRoot, 0);
    		}
    	}

    	private static final String indent1 = "    ";

    	private void printHelper(BSTNode root, int indent) {
    		if (root.myRight != null) {
    			printHelper(root.myRight, indent + 1);
    		}
    		println(root.myItem, indent);
    		if (root.myLeft != null) {
    			printHelper(root.myLeft, indent + 1);
    		}
    	}
    	
    	private void println(Object obj, int indent) {
    		for (int k = 0; k < indent; k++) {
    			System.out.print(indent1);
    		}
    		System.out.println(obj);
    	}
        
        
    }
    
    
    
    
    
}


