public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	public TreeNode myRoot;
	
	public BinarySearchTree(TreeNode root) {
		myRoot = root;
	}
	
	public BinarySearchTree() {
		myRoot = null;
	}
	
	public boolean contains(T key) {
		return contains(myRoot, key);
	}
	
    private boolean contains (TreeNode t, T key) {
    	if (t == null) {
    		return false;
    	} else if (key.compareTo(t.myItem) > 0) {
    		return contains(t.myRight, key);
    	} else if (key.compareTo(t.myItem) < 0) {
    		return contains(t.myLeft, key);
    	} else {
    		return true;
    	}
    }
    
    public void add(T key) {
        myRoot = add(myRoot, key);
        myRoot.size++;
    }

    private TreeNode add(TreeNode t, T key) {
        if (t == null) {
        	t = new TreeNode(key);
            return t;
        } else if (key.compareTo(t.myItem) < 0) {
            t.myLeft = add(t.myLeft, key);
            return t;
        } else {
            t.myRight = add(t.myRight, key);
            return t;
        }
    }
    
    public Comparable kthLargest (int k) {
    	if (k > myRoot.determineSize()) {
    		return null;
    	}
    	TreeNode t = myRoot;
    	int left_size = -1;
    	if (t.myLeft != null) {
    		left_size = t.myLeft.determineSize();
    	}
    	int right_size = -1;
    	if (t.myRight != null) {
    		right_size = t.myRight.determineSize();
    	}
    	if (left_size == -1 && right_size == -1) { // Both empty
        	return myRoot.myItem;
    	} else if (right_size == -1) { //Empty right
    		if (k == left_size) {
    			return myRoot.myItem;
    		}
    		BinarySearchTree tree = new BinarySearchTree(myRoot.myLeft);
    		return tree.kthLargest(k);
    	} else if (left_size == -1) { //Empty left
    		if (k == 0) {
    			return myRoot.myItem;
    		}
    		BinarySearchTree tree = new BinarySearchTree(myRoot.myRight);
    		return tree.kthLargest(k - 1);
    	} else { //both nonempty
        	if (k == left_size) {
        		return myRoot.myItem;
        	} else if (k > left_size) {
        		BinarySearchTree tree = new BinarySearchTree(myRoot.myRight);
        		return tree.kthLargest(k - (left_size + 1));
        	} else {
        		BinarySearchTree tree = new BinarySearchTree(myRoot.myLeft);
        		return tree.kthLargest(k);
        	}
    	}
    }
}
