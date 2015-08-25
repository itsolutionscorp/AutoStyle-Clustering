import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;


public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
    	
        public BinarySearchTree() {
            super();
        }
    	public BinarySearchTree(TreeNode t){
    		super(t);
    	}
    	
    	public boolean contains(T key){
    		if (myRoot ==null){
    			return false;
    		}
   			return myRoot.contains(myRoot,key);
    	}
    	public void add(T key) {
    		if (contains(key)){
    			return;
    		} else {
        		if (myRoot ==null){
        			myRoot = new TreeNode(key);
        			myRoot.mySize = 1;
        		} else {
        			myRoot.add(myRoot, key);
        		}
        	}
    	}
    	

    	public TreeNode remove(T key){
    		TreeNode toRemove = null;
    		TreeNode toRemoveParent = null;
    		TreeNode current = myRoot;
    		if(myRoot.myItem.compareTo(key) == 0){
    			myRoot = null;
    			return null;
    		}
    		while(true){
    			if (current == null) {
    				return null;
    			}
    			if(current.myLeft != null && current.myLeft.myItem.compareTo(key) == 0){
	    			toRemove = current.myLeft;
	    			toRemoveParent = current;
	    			break;
	    		} 
	    		if(current.myRight != null && current.myRight.myItem.compareTo(key) == 0){
	    			toRemove = current.myRight;
	    			toRemoveParent = current;
	    			break;
	    		} else {
	    			if(myRoot.myItem.compareTo(key) > 0){
	    				current = current.myLeft;
	    			
	    			}
	    			if(myRoot.myItem.compareTo(key) < 0){
	    				current = current.myRight;
	    			}
	    		}
    		}
    		//If it's leaf
    		if (toRemove.myLeft==null && toRemove.myRight==null){
				if(toRemoveParent.myLeft == toRemove){
					toRemoveParent.myItem = toRemove.myItem;
					toRemoveParent.myLeft = null;
				}
				if(toRemoveParent.myRight == toRemove){
					toRemoveParent.myItem = toRemove.myItem;
					toRemoveParent.myRight = null;
				}
				return null;
    		}
    		//If it has only one child
    		if (toRemove.myLeft==null){
    			toRemove.myItem = remove(toRemove.myRight.myItem).myItem;
    			return null;
    		}
    		if(toRemove.myRight == null){
				toRemove.myItem = remove(toRemove.myLeft.myItem).myItem;
				return null;
			}
    		//Other
    		TreeNode removed = remove(inorderPredOf(toRemove).myItem);
    		if (removed != null) toRemove.myItem = removed.myItem;
    		return toRemove;
    	}
    		
    		
//    		TreeNode toRemove = null;
//    		TreeNode toRemoveParent = null;
//    		int k = 1;
//    		while (kthHelper(myRoot,k).myItem!=key){
//    			if(kthHelper(myRoot,k).myLeft.myItem==key){
//    				toRemoveParent = kthHelper(myRoot,k);
//    				toRemove = kthHelper(myRoot,k).myLeft;
//    				
//    			}
//    			if(kthHelper(myRoot,k).myRight.myItem==key){
//    				toRemoveParent = kthHelper(myRoot,k);
//    				toRemove = kthHelper(myRoot,k).myLeft;
//    				
//    			}
//    			k++;
//    		}
//    		if (toRemove.myLeft==null && toRemove.myRight==null){
//    			if(toRemoveParent.myLeft == toRemove){
//    				toRemoveParent.myLeft = null;
//    			}
//    			if(toRemoveParent.myRight == toRemove){
//    				toRemoveParent.myRight = null;
//    			}
//    		}
    		
    	public Comparable<T> kthLargest (int k) {
    		if (myRoot == null) return null;
    		TreeNode t = kthHelper (myRoot, k);
    		return t.myItem;
    	}
    	
    	public TreeNode kthHelper (TreeNode t, int k) {
    		if (t.myRight == null && t.myLeft == null) return t;
    		if (t.myRight == null && k != 1) return kthHelper(t.myLeft, k - 1);  
    		if (t.myRight == null && k == 1) return t;  
    		
    		if (t.myRight.mySize + 1 == k) return t;
    		
    		if (t.myRight.mySize >= k) 
    			return kthHelper(t.myRight, k);
    		else return kthHelper(t.myLeft, k - t.myRight.mySize - 1);
    	}
    	
    	public TreeNode inorderPredOf (TreeNode t) {
    		InorderIterator iter = new InorderIterator();
    		TreeNode prev = null;
    		TreeNode current = null;
    		while(iter.hasNext()) {
    			current = iter.next();
    			if (current.equals(t)) {
    				return prev;
    			}
    			prev = current;
    		}
    		return prev;
    	}
    	
        private class InorderIterator implements Iterator<TreeNode> {
            private Stack<TreeNode> nodeStack;
            private TreeNode currentNode;

            public InorderIterator() {
                nodeStack = new Stack<TreeNode>();
                currentNode = myRoot;
            }

            public boolean hasNext() {
                return !nodeStack.isEmpty() || (currentNode != null);
            }

            public TreeNode next() {
                TreeNode nextNode = null;

                // find leftmost node with no left child
                while (currentNode != null) {
                    nodeStack.push(currentNode);
                    currentNode = currentNode.myLeft;
                }

                // get leftmost node, then move to its right subtree
                if (!nodeStack.isEmpty()) {
                    nextNode = nodeStack.pop();
                    assert nextNode != null;    // since nodeStack was not empty before the pop
                    currentNode = nextNode.myRight;
                } else {
                    throw new NoSuchElementException();
                }

                return nextNode; 
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
            
        }
}

