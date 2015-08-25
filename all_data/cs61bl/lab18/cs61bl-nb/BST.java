import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Your comment here
    private BSTNode linkedListToTree (Iterator iter, int n) {
        // TODO Your code here
    		BSTNode node;
    		if(n == 0){
    			return null;
    		} else if(n == 1){
    			node = new BSTNode ();
    			node.myItem = iter.next();
    		} else if(n == 2){   			
    			node = new BSTNode();   			
    			node.myLeft = new BSTNode();
    			node.myLeft.myItem = iter.next();
    			node.myItem = iter.next();
    		} else {   		
	    		LinkedList list1 = new LinkedList();
	    		LinkedList list2 = new LinkedList();
	    		int i = 1;
	    		while ( i <= n/2 && iter.hasNext()){
	    			list1.add(iter.next());
	    			System.out.println(list1.toString());
	    			i++;
	    		}	    		
    			node = new BSTNode();
    			node.myItem = iter.next();
    			node.myLeft = linkedListToTree(list1.iterator(), list1.size());
	    		while (iter.hasNext()){
	    			list2.add(iter.next());
	    			System.out.println(list2.toString());
	    		}    		
	    		node.myRight = linkedListToTree(list2.iterator(), list2.size()); 
    		}
	    	return node;
    		
    }
    
    public void print() {
        if (myRoot != null) {
            myRoot.print(0);
        }
    }
    
    public void printInorder() {
        if (myRoot == null) {
            System.out.println("(empty tree)");
        } else {
            myRoot.printInorder();
            System.out.println();
        }
    }

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
        
        
        private void printInorder() {
            if (myLeft != null) {
                myLeft.printInorder();
            }
            System.out.print(myItem + " ");
            if (myRight != null) {
                myRight.printInorder();
            }
        }
        
        private static final String indent1 = "    ";
        private void print(int indent) {
            // TODO your code here
	        	if(myRight != null){
	        		myRight.print(indent + 1);
	        	}
	        		println (myItem, indent);
	    		if(myLeft != null){
	        		myLeft.print(indent + 1);
	        	}
            // TODO your code here
        }
        
        private  void println(Object obj, int indent) {
            for (int k=0; k<indent; k++) {
                System.out.print(indent1);
            }
            System.out.println(obj);
        }
    }
}
