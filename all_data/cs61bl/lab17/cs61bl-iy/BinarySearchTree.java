

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	public BinarySearchTree() {
		super();
	}
	public BinarySearchTree(TreeNode t) {
		super(t);
	}
	
	public boolean contains(T key) {
		return contains(myRoot, key);
	}
	private boolean contains(TreeNode t, T key) {
		if (t == null) {
			return false;
		} else if (key.compareTo(t.myItem) == 0) {
			return true;
		} else if (key.compareTo(t.myItem) < 0) {
			return contains(t.myLeft, key);
		} else if (key.compareTo(myRoot.myItem) > 0) {
			return contains(t.myRight, key);
		} else {
		    return false;
		}
	}
	
	public void add(T key) {
	    myRoot = add(myRoot, key);
	}

	@SuppressWarnings("unused")
	private TreeNode add(TreeNode t, T key) {
		if (contains(key)) {
			return myRoot;
		} 
		t.size++;
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
	
	public Comparable<T> kthLargest(int k) {
		return kthLargest(myRoot, k);
	}
	
	private Comparable<T> kthLargest(TreeNode t, int k) {
		if (t == null) {
			return null;
		} else if (t.myRight == null) {
			if (k == t.size) {
				return t.myItem;
			} else {
				return kthLargest(t.myLeft, k);
			}			
		} else if (k == 1 + t.myRight.size) {
			return t.myItem;
		} else if (k < 1 + t.myRight.size) {
			return kthLargest(t.myLeft, k);
		} else {
			return kthLargest(t.myRight, k);
		}
	}
	
	public void d_min() {
		if (myRoot == null) {
		}
		myRoot = d_min(myRoot);
	}
	
	public TreeNode d_min(TreeNode t) {
		if (t.myLeft == null) {
			return t.myRight;
		}
		t.myLeft = d_min(t.myLeft);
		t.size = t.myLeft.size +t.myRight.size + 1;
		return t;	
	}
	
	public void d_max() {
		if (myRoot == null) {
		}
		myRoot = d_max(myRoot);
	}
	
	public TreeNode d_max(TreeNode t) {
		if (t.myRight == null) {
			return t.myLeft;
		}
		t.myRight = d_max(t.myRight);
		t.size = t.myLeft.size +t.myRight.size + 1;
		return t;	
	}
	
	public void delete(T key) {
		myRoot = delete(myRoot, key);
	}
	
	public TreeNode delete(TreeNode t, T key) {
		if (t == null) {
			return null;
		}
		if (key.compareTo(t.myItem) < 0) {
			t.myLeft = delete(t.myLeft, key);
		} else if (key.compareTo(t.myItem) > 0) {
			t.myRight = delete(t.myRight, key);
		} else {
			if (t.myRight == null) {
				return t.myLeft;
			} else if (t.myLeft == null) {
				return t.myRight;
			}
			TreeNode new_t = t;
			t = min(new_t.myRight);
			t.myRight = d_min(new_t.myRight);
			t.myLeft = new_t.myLeft;
		}
		t.size = t.myLeft.size + t.myRight.size + 1;
		return t;
	} 
	
	public TreeNode min(TreeNode t) {
		if (t.myLeft == null) {
			return t;
		} else {
			return min(t.myLeft);
		}
	}
	
	
	
   
	
	
	
	
	
	public static void main(String[] args) {
	}
}
