public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	public boolean contains(T key) {
    	return contains(myRoot, key);
    }

    private boolean contains(TreeNode t, T key) {
    	if (t == null) {
    		return false;
    	}
    	else if (t.myItem == key) {
    		return true;
    	}
    	else if (t.myItem.compareTo(key) < 0) {
    		return contains(t.myRight, key);
    	}
    	else {
    		return contains(t.myLeft, key);
    	}
    }
    
    public void add(T key) {
    	if (!contains(myRoot, key)) {
            myRoot = add(myRoot, key);
    	}
    }

    private TreeNode add(TreeNode t, T key) {
        if (t == null) {
        	TreeNode result = new TreeNode(key);
        	result.size ++;
            return result;
        } else if (key.compareTo(t.myItem) < 0) {
            t.myLeft = add(t.myLeft, key);
            t.size++;
            return t;
        } else {
            t.myRight = add(t.myRight, key);
            t.size++;
            return t;
        }
    }
    
    public Comparable kthLargest(int k) {
    	return kthHelper(myRoot, k); 	
    }
    
    private Comparable kthHelper(TreeNode t, int k) {
    	if ((k == 0 && t.myRight == null) || (t.myRight.size == k)) {
    		return t.myItem;
    	} else if (t.myRight.size > k){
    		return kthHelper(t.myRight, k);
    	} else {
    		return kthHelper(t.myLeft, k-t.myRight.size-1);
    	}
    }
}
