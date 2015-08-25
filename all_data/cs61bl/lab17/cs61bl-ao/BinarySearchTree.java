public class BinarySearchTree<T extends Comparable <T>> extends BinaryTree<T> {
	public BinarySearchTree() {
		super();
	}
	
	public boolean contains (T key) {
		if (myRoot == null) return false;
		else return contains(this.myRoot, key);
	}
	
	private boolean contains (TreeNode t, T key) {
		if (t == null) return false;
		else if (key == t.myItem) return true;
		else if (key.compareTo(t.myItem) < 0) return contains(t.myLeft, key);
		else return contains(t.myRight, key);
	}
	
	public void add (T key) {
		myRoot = add(myRoot, key);
	}
	
	private TreeNode add(TreeNode t, T key) {
	    if (t == null) {
	    	TreeNode root = new TreeNode(key);
	    	root.myDepth = 0;
	        return root;
	    } else if (key.compareTo(t.myItem) == 0) {
	    	
	    	return t;
	    } else if (key.compareTo(t.myItem) < 0) {
	    	t.myDepth ++;
	        t.myLeft = add(t.myLeft, key);
	        return t;
	    } else {
	    	t.myDepth ++;
	        t.myRight = add(t.myRight, key);
	        return t;
	    }
	}
	
	Comparable kthLargest (int k) {
		return kthmaximum(k, myRoot);
	}
	private Comparable kthmaximum(int k, TreeNode n) {
		if (n.myRight.myDepth == k -1 ) return n.myItem;
		if (n.myRight.myDepth <= k) return kthmaximum(k, n.myRight);
		else return kthmaximum(k-n.myRight.myDepth-1, n.myLeft);
	}
	
	
	
}