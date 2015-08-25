public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	public boolean contains(T key) {
		return contains(myRoot, key);
	}

	private boolean contains(TreeNode t, T key) {
		if (t == null) {
			return false;
		}
		if (key.equals(t.myItem)) {
			return true;
		}
		if (key.compareTo(t.myItem) < 0) {
			return contains(t.myLeft, key);
		} else
			return contains(t.myRight, key);
	}

	public void add(T key) {
		myRoot = add(myRoot, key);

	}

	private TreeNode add(TreeNode t, T key) {
		if (t == null) {
			TreeNode tn = new TreeNode(key);
			System.out.println((String) tn.myItem + " " + tn.size);
			return tn;
		}
		System.out.println((String) t.myItem + " " + t.size);
		if (contains(key)) {
			return t;
		} else if (key.compareTo(t.myItem) < 0) {
			t.size++;
			t.myLeft = add(t.myLeft, key);
			return t;
		} else {
			t.size++;
			t.myRight = add(t.myRight, key);
			return t;
		}
	}

	Comparable kthLargest(int k) {
		return kthLargestHelper(k, myRoot);
	}

	Comparable kthLargestHelper(int k, TreeNode t) {
		int rsize;
		if (t.myRight == null)
			rsize = 0;
		else
			rsize = t.myRight.size;
		if (rsize == k) {
			return t.myItem;
		}
		if (rsize > k) {
			return kthLargestHelper(k, t.myRight);
		} else {
			return kthLargestHelper(rsize - k - 1, t.myLeft);
		}
	}
}
