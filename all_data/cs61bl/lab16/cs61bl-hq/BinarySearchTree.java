
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	public boolean contains (T key) {
    	return this.contains(this.myRoot, key);
    }
	
	private boolean contains(TreeNode t, T key) {
		boolean result = false;
		if (t == null) {
    		result = false;
    	} else if (key.compareTo(t.myItem) == 0) {
    		result = true;
    	} else if (key.compareTo(t.myItem) < 0) {
    		result = contains(t.myLeft, key);
    	} else if (key.compareTo(t.myItem) > 0) {
    		result = contains(t.myRight, key);
    	}
		return result;
	}
	
	public void add(T key) {
		if (!this.contains(key)) {
	    	myRoot = add(myRoot, key);
		}
	}

	private TreeNode add(TreeNode t, T key) {
	    if (t == null) {
	        return new TreeNode(key);
	    } else if (key.compareTo(t.myItem) < 0) {
	        t.myLeft = add(t.myLeft, key);
	        return t;
	    } else {
	        t.myRight = add(t.myRight, key);
	        return t;
	    }
	}
	
}
