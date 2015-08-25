public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	public boolean contains(T key) {
		return contains(myRoot, key);
	}

	private boolean contains(TreeNode t, T key) {
		if (t != null) {
			int a = t.myItem.compareTo(key);
			if (a == 0) {
				return true;
			} else if (a < 0) {
				return contains(t.myRight, key);
			} else {
				return contains(t.myLeft, key);
			}
		}
		return false;
	}

	public void add(T key) {
		myRoot = add(myRoot, key);
	}

	private TreeNode add(TreeNode t, T key) {
		if (t != null) {
			int a = t.myItem.compareTo(key);
			if (a == 0) {
				throw new IllegalStateException("Cannot add duplicate element");
			} else if (a < 0) {
				t.myRight = add(t.myRight, key);
				t.size++;
				return t;
			} else {
				t.myLeft = add(t.myLeft, key);
				t.size++;
				return t;
			}
		}
		return new TreeNode(key);
	}

	Comparable<T> kthLargest(int k) {
		if (myRoot == null) {
			throw new IllegalArgumentException("empty tree");
		}
		return kthLargest(myRoot, k);
	}

	Comparable<T> kthLargest(TreeNode t, int k) {
		if (k >= t.size || k < 0) {
			throw new IllegalArgumentException("invalid k");
		}
		if (t.myRight == null) {
			if (k == 0) {
				return t.myItem;
			} else {
				return kthLargest(t.myLeft, k - 1);
			}
		} else if (t.myLeft == null) {
			if (k == t.size - 1) {
				return t.myItem;
			} else {
				return kthLargest(t.myRight, k);
			}
		} else {
			if (k == 0) {
				return kthLargest(t.myRight, k);
			} else if (k == t.myRight.size) {
				return t.myItem;
			} else if (k < t.myRight.size) {
				return kthLargest(t.myRight, k);
			} else {
				return kthLargest(t.myLeft, k - t.myRight.size - 1);
			}
		}

	}

	public T get(T key) {
		if (myRoot != null) {
			return get(myRoot, key);
		}
		return null;
	}

	private T get(TreeNode t, T key) {
		if (t != null) {
			int a = t.myItem.compareTo(key);
			if (a == 0) {
				return t.myItem;
			} else if (a < 0) {
				return get(t.myRight, key);
			} else {
				return get(t.myLeft, key);
			}
		}
		return null;
	}

	public T remove(T key) {
		if (myRoot != null) {
			if (contains(myRoot, key)) {
				T toReturn = get(myRoot, key);
				myRoot = remove(myRoot, key);
				return toReturn;
			} else {
				return null;
			}
		}
		return null;
	}

	private TreeNode remove(TreeNode t, T key) {
		int a = t.myItem.compareTo(key);
		if (a == 0) {
			t = pushInOrder(t);
		} else if (a < 0) {
			t.myRight = remove(t.myRight, key);
		} else {
			t.myLeft = remove(t.myLeft, key);
		}
		return t;
	}

	private TreeNode pushInOrder(TreeNode t) {
		if (t.myLeft == null && t.myRight == null) {
			return null;
		} else if (t.myLeft == null) {
			return t.myRight;
		} else if (t.myRight == null) {
			return t.myLeft;
		} else {
			t.myItem = getRemNode(t.myRight);
			t.myRight = moveInOrder(t.myRight);
			return t;
		}
	}

	private T getRemNode(TreeNode t) {
		if (t.myLeft == null) {
			return t.myItem;
		} else {
			return getRemNode(t.myLeft);
		}
	}
	private TreeNode moveInOrder(TreeNode t) {
		if (t.myLeft == null) {
			t = t.myRight;
		} else {
			t.myLeft = moveInOrder(t.myLeft);
		}
		return t;
	}

}
