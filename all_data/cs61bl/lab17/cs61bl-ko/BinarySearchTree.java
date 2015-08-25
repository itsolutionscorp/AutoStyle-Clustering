
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	public boolean contains(T key) {
		return contains(myRoot, key);
	}
	
	private boolean contains (TreeNode t, T key) {
		if (t == null) {
			return false;
		}
		if (key.compareTo(t.myItem) == 0) {
			return true;
		}
		if (key.compareTo(t.myItem) == -1) {
			return contains (t.myLeft, key);
		}
		return contains (t.myRight, key);
	}
	
	public void add(T key) {
		if (!contains(key)) {
			myRoot = add(myRoot, key);
		}
	}

	private TreeNode add(TreeNode t, T key) {
	    if (t == null) {
	        return new TreeNode(key);
	    } 
	    else if (key.compareTo(t.myItem) < 0) {
	        t.myLeft = add(t.myLeft, key);
	        t.mySize++;
	        return t;
	    } else {
	        t.myRight = add(t.myRight, key);
	        t.mySize++;
	        return t;
	    }
	}
	
	public T find(T key) {
		return findHelper(myRoot, key);
	}
	
	private T findHelper(TreeNode t, T key) {
		if (t == null) {
			return null;
		}
		if (key.compareTo(t.myItem) == 0) {
			return t.myItem;
		}
		if (key.compareTo(t.myItem) == -1) {
			return findHelper(t.myLeft, key);
		}
		return findHelper(t.myRight, key);
	}
	
	public T remove(T key) {
		if (key.compareTo(myRoot.myItem) == 0) {
			if (myRoot.myLeft == null && myRoot.myRight == null) {
				T ret = myRoot.myItem;
				myRoot = null;
				return ret;
			}
		}
		return removeHelper(key, myRoot).myItem;
	}
	
	public TreeNode removeHelper(T key, TreeNode remNode) {
		if (key.compareTo(remNode.myItem) == -1) {
			remNode.myLeft = removeHelper(key, remNode.myLeft);
			return remNode;
		}
		else if (key.compareTo(remNode.myItem) == 1) {
			remNode.myRight = removeHelper(key, remNode.myRight);
			return remNode;
		} else {
			if (remNode.myLeft == null && remNode.myRight == null) {
				remNode = null;
				return remNode;
			} else if (remNode.myLeft != null && remNode.myRight == null) {
				return remNode.myLeft;
			} else if (remNode.myRight != null && remNode.myLeft == null) {
				return remNode.myRight;
			} else {
				TreeNode iOS = iOS(remNode.myRight);
				T holder = iOS.myItem;
				removeHelper(iOS.myItem, iOS);
				remNode.myItem = holder;
				return remNode;
			}
		}
	}
	
	private TreeNode iOS(TreeNode d) {
		if (d == null) {
			return d;
		}
		if (d.myLeft == null) {
			return d;
		}
		else {
			return iOS(d.myLeft);
		}
	}
	
	public Comparable<T> kthLargest (int k) {
		return kthLargestHelper(myRoot, k);
	}
	
	private Comparable<T> kthLargestHelper(TreeNode t, int k) {
		if (myRoot == null) {
			return null;
		}
		if (t.myLeft == null && t.myRight == null) {
			return t.myItem;
		}
		if (t.myRight == null) { 
			if (k == 0) {
				return t.myItem;
			}
			return kthLargestHelper(t.myLeft, k-1);
		}
		if (t.myLeft == null) {
			if (k == t.myRight.mySize) {
				return t.myItem;
			}
			return kthLargestHelper(t.myRight, k);
		}
		if (k == t.myRight.mySize) {
			return t.myItem;
		}
		if (k <  t.myRight.mySize) {
			return kthLargestHelper(t.myRight, k);
		}
		return kthLargestHelper(t.myLeft, k - t.myRight.mySize - 1);
	}
}
