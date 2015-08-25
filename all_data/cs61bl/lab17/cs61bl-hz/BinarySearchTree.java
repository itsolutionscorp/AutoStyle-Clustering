import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	public BinarySearchTree() {
		super();
	}
	
	public BinarySearchTree(TreeNode t) {
		super(t);
	}
	
	public boolean contains(T key) {
		if (myRoot == null) {
			return false;
		} else {
			return containsHelper(myRoot, key);
		}
	}
	
	public boolean containsHelper (TreeNode t, T key) {
		if (t.myItem == null) {
			return false;
		} else if (key.compareTo(t.myItem) < 0) {
			return containsHelper(t.myLeft, key);
		} else {
			return containsHelper(t.myRight, key);
		}
	}
	
	public void add(T key) {
		myRoot = add(myRoot, key);
	}
	
	private TreeNode add(TreeNode t, T key) {
	    if (t == null) {
	        return new TreeNode(key);
	    } else if (key.compareTo(t.myItem) < 0) {
	        t.myLeft = add(t.myLeft, key);
	        t.size += t.myLeft.size;
	        return t;
	    } else {
	        t.myRight = add(t.myRight, key);
	        t.size += t.myRight.size;
	        return t;
	    }
	}
	
	public Comparable<T> kthLargest (int k) {
		if (myRoot != null) {
			return kthLargest(myRoot, k).myItem;
		} else {
			return null;
		}
	}
	
	private TreeNode kthLargest(TreeNode t, int k) {
		if (k == 0 && t.myRight == null) {
			return t;
		} else if (t.myRight != null && k < t.myRight.size) {
			return kthLargest(t.myRight, k);
		} else if (t.myRight != null && k == t.myRight.size) {
			return t;
		} else if (t.myRight != null && t.myLeft != null) {
			return kthLargest(t.myLeft, k - 1 - t.myRight.size);
		} else if (t.myLeft != null) {
			return kthLargest(t.myLeft, k - 1);
		} else {
			return null;
		}
	}
}