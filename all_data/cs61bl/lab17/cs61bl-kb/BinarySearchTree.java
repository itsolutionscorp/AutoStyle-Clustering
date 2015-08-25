public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	/** Takes a Comparable object as an argument and checks whether the tree contains it. */
	public boolean contains(T key) {
		if (myRoot == null) {
			return false;
		} else {
			return contains(myRoot, key);
		}
	}

	private boolean contains(TreeNode t, T key) {
		if (t == null) {
			return false;
		} else if (key.compareTo(t.myItem) < 0) {
			return contains(t.myLeft, key);
		} else if (key.compareTo(t.myItem) > 0) {
			return contains(t.myRight, key);
		} else {
			return true;
		}
	}

	/** Takes a Comparable object as an argument and adds it to the tree
		only if it isn't already there. Tree does not contain duplicate elements.
	 */
	public void add(T key) {
		if (!contains(key)) {
			myRoot = add(myRoot, key);
		}
	}

	private TreeNode add(TreeNode t, T key) {
	    if (t == null) {
	        return new TreeNode(key);
	    } else if (key.compareTo(t.myItem) < 0) {
	        t.myLeft = add(t.myLeft, key);
	        t.size += 1;
	        return t;
	    } else {
	        t.myRight = add(t.myRight, key);
	        t.size += 1;
	        return t;
	    }
	}
	
	Comparable kthLargest (int k) {
		if (myRoot.myRight.size == k) {
			return myRoot.myRight.myItem;
		}
		else if (myRoot.myRight.size > k) {
			BinaryTree temp = new BinaryTree(myRoot.myLeft);
			temp.kthLargest(k);
		}
	}

	public static void main(String[] args) {
		// Make tree.
	}
}