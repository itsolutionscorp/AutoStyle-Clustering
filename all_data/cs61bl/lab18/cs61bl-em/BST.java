import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Your comment here
    private static BSTNode linkedListToTree (Iterator iter, int n) {
        if (n <= 0) {
        	return null;
        }
    	return new BSTNode (linkedListToTree(iter, n/2), iter.next(), linkedListToTree(iter, n - n/2 - 1));
    }

    public void print() {
        if (myRoot != null) {
            myRoot.print(0);
        }
    }
    
    private static class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
        
        public BSTNode (BSTNode left, Object item, BSTNode right) {
        	myLeft = left;
        	myItem = item;
        	myRight = right;
        }
        
        private static final String indent1 = "    ";
        
        private void print(int indent) {
        	if (myRight != null) {
        		indent ++;
        		myRight.print(indent);
        		indent --;
        	}
        	println (myItem, indent);
        	if (myLeft != null) {
        		indent ++;
        		myLeft.print(indent);
            	indent --;
        	}    	
        }
        private void println(Object obj, int indent) {
            for (int k=0; k<indent; k++) {
                System.out.print(indent1);
            }
            System.out.println(obj);
        }
    }
    
    public static void main (String[] args) {
    	LinkedList l = new LinkedList<Object> ();
    	l.add(1);
    	l.add(2);
    	l.add(3);
    	l.add(4);
    	l.add(5);
    	l.add(6);
    	l.add(7);
    	l.add(8);
    	l.add(9);
    	BST b = new BST(l);
    	b.print();
    }
   
}

