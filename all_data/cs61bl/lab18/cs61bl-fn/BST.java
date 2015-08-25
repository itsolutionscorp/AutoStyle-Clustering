import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Your comment here
    
    private BSTNode linkedListToTree (Iterator iter, int n) {
    	BSTNode l = new BSTNode(); 
    	if (n==0) {
    		return null;
    	} else if (n==1) {
    		l.myItem = iter.next();
    		return l;
    	} else{
    		int half = n/2;
    		l.myLeft = linkedListToTree(iter, half);
    		l.myItem = iter.next();
    		l.myRight = linkedListToTree(iter, n-half-1);
    		return l;
    	}
    }
    
    
    public void print() {
		if (myRoot != null) {
			myRoot.print(0);
		}
	}
    
    public int height() {
    	if (myRoot != null) {
    		return myRoot.height();
    	}
    	return 0;
    }
    
    public boolean isCompletelyBalanced() {
    	if (myRoot != null) {
    		return myRoot.isCompletelyBalanced();
    	}
    	return true;
    }

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
        private static final String indent1 = "    ";
        
        public BSTNode() {
			myItem = null;
			myLeft = myRight = null;
		}
        
        public BSTNode(Object item) {
			myItem = item;
			myLeft = myRight = null;
		}

		public BSTNode(Object item, BSTNode left, BSTNode right) {
			myItem = item;
			myLeft = left;
			myRight = right;
		}
		
		private void print(int indent) {
			if (myRight != null) {
				myRight.print(indent + 1);
			}
			println(myItem, indent);
			if (myLeft != null) {
				myLeft.print(indent + 1);
			}
		}
		
		private void println(Object obj, int indent) {
			for (int k = 0; k < indent; k++) {
				System.out.print(indent1);
			}
			System.out.println(obj);
		}
		
		 private int height() {
	        	int height = 1;
	        	int leftHeight = 0, rightHeight = 0;
	        	if (myLeft != null) {
	                leftHeight = myLeft.height();
	            }
	            if (myRight != null) {
	            	rightHeight = myRight.height();
	            }
	        	return height + Math.max(leftHeight, rightHeight);   	
	        }
	        private boolean isCompletelyBalanced() {
	        	int leftHeight = 0, rightHeight = 0;
	        	boolean left = true, right = true;
	        	if (myLeft != null) {
	                leftHeight = myLeft.height();
	                left = myLeft.isCompletelyBalanced();
	            }
	            if (myRight != null) {
	            	rightHeight = myRight.height();
	            	right = myRight.isCompletelyBalanced();
	            }
	        	return leftHeight == rightHeight && left && right;
	        }

    }
}
