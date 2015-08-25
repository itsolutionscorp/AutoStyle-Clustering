
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	public BinarySearchTree() {
		super();
	}
	
	public boolean contains(T key) {
		if (super.myRoot == null) {
			return false;
		}
		return containsHelper(super.myRoot, key);
	}
	
	private boolean containsHelper (TreeNode t, T key) {	
		if (key.compareTo(t.myItem) > 0) {
			if (t.myRight != null)
				return containsHelper(t.myRight, key);
		} else if (key.compareTo(t.myItem) < 0) {
			if (t.myLeft != null)
				return containsHelper(t.myLeft, key);
		} else {
			return true;
		}
		return false;
	}
	
	public void add(T key) {
		myRoot = addHelper(myRoot, key);
	}
	
	private TreeNode addHelper(TreeNode t, T key) {
	    if (t == null) {
	        return new TreeNode(key);
	    } else if (key.compareTo(t.myItem) < 0) {
	        t.myLeft = addHelper(t.myLeft, key);
	        return t;
	    } else if (key.compareTo(t.myItem) > 0) {
	        t.myRight = addHelper(t.myRight, key);
	        return t;
	    }
	    return t;
	}
}
