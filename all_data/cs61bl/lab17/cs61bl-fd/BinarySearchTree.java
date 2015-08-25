public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	public boolean contains(T key) {
		if (myRoot != null) {
			return contains(myRoot, key);
		}
		return false;
	}

	private boolean contains(TreeNode t, T key) {
		if (t == null) {
			return false;
		}
		if (t.myItem.equals(key)) {
			return true;
		} else if (key.compareTo(t.myItem) < 0) {
			return contains(t.myLeft, key);
		} else {
			return contains(t.myRight, key);
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
			t.size += 1;
			return t;
		} else {
			t.myRight = add(t.myRight, key);
			t.size += 1;
			return t;
		}
	}

	public Comparable kthLargest(int k) {
		return kthHelper(myRoot, k);
	}

	private Comparable kthHelper(TreeNode t, int k) {
		if (t.size == 1 && k == 1) {
			return t.myItem;
		} else if (t.myLeft != null && t.myRight == null) {
			if (k == t.size) {
				return kthHelper(t.myLeft, k - 1);
			} else {
				return kthHelper(t.myLeft, k - 1);
			}
		} else if (t.myRight != null && t.myLeft == null) {
			if (k == t.size) {
				return t.myItem;
			} else {
				return kthHelper(t.myRight, k);
			}
		} else {
			if (k >= t.myRight.size) {
				return kthHelper(t.myRight, k);
			} else {
				if (t.myRight.size == (k - 1)) {
					return t.myItem;
				} else {
					return kthHelper(t.myLeft, k - (t.myRight.size - 1));
				}
			}
		}
	}

}
