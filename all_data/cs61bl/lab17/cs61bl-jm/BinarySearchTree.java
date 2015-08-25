
public class BinarySearchTree<T extends Comparable> extends BinaryTree<T> {

	public boolean contains(Object key) {
		if (myRoot == null) {
			return false;
		} else if (myRoot.myItem.compareTo(key) == 0) {
			return true;
		} else if (myRoot.myItem.compareTo(key) > 0) {
			return containsHelper(myRoot.myLeft, key);
		} else if (myRoot.myItem.compareTo(key) < 0) {
			return containsHelper(myRoot.myRight, key);
		} else return false;
	}
	
	private boolean containsHelper(TreeNode t, Object key) {
		if (t == null) return false;
		int result = t.myItem.compareTo(key);
		if (result == 0) {
			return true;
		} else if (result > 0) {
			return containsHelper(t.myLeft, key);
		} else if (result < 0) {
			return containsHelper(t.myRight, key);
		} else return false;
	}
	
	public T find(Object key) {
		if (myRoot == null) {
			return null;
		} else if (myRoot.myItem.compareTo(key) == 0) {
			return myRoot.myItem;
		} else if (myRoot.myItem.compareTo(key) > 0) {
			return findHelper(myRoot.myLeft, key);
		} else if (myRoot.myItem.compareTo(key) < 0) {
			return findHelper(myRoot.myRight, key);
		} else return null;
	}
	
	private T findHelper(TreeNode t, Object key) {
		if (t == null) return null;
		int result = t.myItem.compareTo(key);
		if (result == 0) {
			return t.myItem;
		} else if (result > 0) {
			return findHelper(t.myLeft, key);
		} else if (result < 0) {
			return findHelper(t.myRight, key);
		} else return null;
	}
	
	public void remove(Object key) {
		if (myRoot == null) {
			return;
		} else if (myRoot.myItem.compareTo(key) == 0) {
			rmHelper(myRoot);
		} else if (myRoot.myItem.compareTo(key) > 0) {
			rmHelper(rmFindHelper(myRoot.myLeft, key));
		} else if (myRoot.myItem.compareTo(key) < 0) {
			rmHelper(rmFindHelper(myRoot.myRight, key));
		} else return;
	}
	
	private TreeNode rmFindHelper(TreeNode t, Object key) {
		if (t == null) return null;
		int result = t.myItem.compareTo(key);
		if (result == 0) {
			return t;
		} else if (result > 0) {
			return rmFindHelper(t.myLeft, key);
		} else if (result < 0) {
			return rmFindHelper(t.myRight, key);
		} else return null;
	}
	
	private void rmHelper(TreeNode t) {
		if (t  == null) return;
		else if (t.myLeft == null && t.myRight == null) {
			if (t.myParent.myItem.compareTo(t.myItem) < 0) t.myParent.myRight = null;
			else if (t.myParent.myItem.compareTo(t.myItem) > 0) t.myParent.myLeft = null;
		} else {
			t.myItem = findSuccessor(t).myItem;
		}
		
	}
	
	private TreeNode findSuccessor(TreeNode t) {
		if (t.myRight == null) {
			if (t.myItem.compareTo(largestNode(myRoot)) == 0) {
				t.myParent.myRight = t.myLeft;
				return t.myLeft;
			} else {
				t.myParent.myRight = t.myLeft;
				return t.myParent;
			}
		} else {
			TreeNode current = t.myRight;
			while ((current.myLeft != null)) {
				current = current.myLeft;
			}
			if (current.myParent == t) {
				current.myParent.myRight = current.myRight;
			} else {
				current.myParent.myLeft = current.myRight;
			}
			return current;
		}
	}
	
	private TreeNode largestNode(TreeNode t) {
		while (t.myRight != null) {
			t = t.myRight;
		}
		return t;
	}
	
	
	public void add(T key) {
		if (contains(key)) {
			return;
		}
	    myRoot = add(myRoot, key);
	}

	private TreeNode add(TreeNode t, T key) {
	    if (t == null) {
	        return new TreeNode(key);
	    } else if (key.compareTo(t.myItem) < 0) {
	    	t.size += 1;
	        t.myLeft = add(t.myLeft, key);
	        if (t.myLeft != null) t.myLeft.myParent = t;
	        return t;
	    } else {
	    	t.size += 1;
	        t.myRight = add(t.myRight, key);
	        if (t.myRight != null) t.myRight.myParent = t;
	        return t;
	    }
	}
	
	public Comparable<T> kthLargest(int k) {
		if (myRoot != null) return kthHelper(myRoot, k);
		return null;
	}
	
	private Comparable<T> kthHelper (TreeNode n, int k){
		int leftSize = 0;
		if (n.myLeft == null) leftSize = 0;
		else leftSize = n.myLeft.size();
		int rootSize = leftSize + 1;
		if (k == rootSize) return n.myItem;
		else if (k > leftSize) return kthHelper (n.myRight, k-rootSize);
		else return kthHelper (n.myLeft, k);
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinarySearchTree<String> tree = new BinarySearchTree<String>();
		tree.add("C");
		tree.add("A");
		tree.add("E");
		tree.add("B");
		tree.add("D");
		System.out.println(tree.kthLargest(3));
		System.out.println(tree.kthLargest(4));
		System.out.println(tree.kthLargest(5));

	}

}
