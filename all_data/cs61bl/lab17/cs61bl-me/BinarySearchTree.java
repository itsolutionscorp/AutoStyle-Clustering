
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	public boolean contains(T key) {
		return contains(myRoot, key);
	}
	
	private boolean contains(TreeNode t, T key) {
	    if (t == null) {
	        return false;
	    } else if (t.myItem.equals(key)) {
	    	return true;
	    } else return contains(t.myLeft, key) || contains(t.myRight, key);
	}
	
	public void add(T key) {
	    myRoot = add(myRoot, key);
	}

	private TreeNode add(TreeNode t, T key) {
	    if (t == null) {
	        return new TreeNode(key);
	    } else if (t.myItem.equals(key)) {
	    	t.myItem = key;
	    	return t;
	    } else if (key.compareTo(t.myItem) < 0) {
	        t.myLeft = add(t.myLeft, key);
	        t.treeSize += 1;
	        return t;
	    } else {
	        t.myRight = add(t.myRight, key);
	        t.treeSize += 1;
	        return t;
	    }
	}
	
	public Comparable kthLargest (int k) {
		return kthLargest(myRoot, k);
	}
	private Comparable kthLargest(TreeNode t, int k) {
		if (t.myRight != null && k <= t.myRight.treeSize) {
			return kthLargest(t.myRight, k);
		}
		if (t.myLeft != null && (t.treeSize - t.myLeft.treeSize) < k) {
			return kthLargest(t.myLeft, k - (t.treeSize - t.myLeft.treeSize));
		}
		return t.myItem;
	}
	
	public T get(T key) {
		TreeNode get = get(myRoot, key);
		if (get == null) {
			return null;
		}
		return get.myItem;
	}
	private TreeNode get(TreeNode t, T key) {
		if (t == null) {
			return null;
		}
		if (t.myItem.equals(key)) {
			return t;
		}
		TreeNode leftGet = get(t.myLeft, key);
		TreeNode rightGet = get(t.myRight, key);
		return leftGet != null ? leftGet : rightGet;
	}

	public void remove(T key) {
		if (myRoot == null) {
			return;
		}
		TreeNode tempRoot = new TreeNode(null, myRoot, null);
		TreeNode keyNode = get(myRoot, key);
		if (!easyDeleteChild(getParent(tempRoot, key), key)) {
			TreeNode successor = inorderSuccessor(keyNode);
			T succesorItem = successor.myItem;
			easyDeleteChild(getParent(tempRoot, successor.myItem), successor.myItem);
			keyNode.myItem = succesorItem;
		}
		myRoot = tempRoot.myLeft;
	}
	private boolean easyDeleteChild(TreeNode t, T key) {
		if (t == null) {
			return false;
		}
		if (t.myLeft != null && t.myLeft.myItem.equals(key) && 
				(t.myLeft.myLeft == null || t.myLeft.myRight == null)) {
			t.myLeft = t.myLeft.myLeft != null ?
				t.myLeft.myLeft : t.myLeft.myRight;
			return true;
		}
		if (t.myRight != null && t.myRight.myItem.equals(key) && 
				(t.myRight.myLeft == null || t.myRight.myRight == null)) {
			t.myRight = t.myRight.myLeft != null ?
				t.myRight.myLeft : t.myRight.myRight;
			return true;
		}
		return false;
	}
	private TreeNode getParent(TreeNode t, T key) {
		if (t == null) {
			return null;
		}
		if ((t.myLeft != null && t.myLeft.myItem.equals(key)) || 
				(t.myRight != null && t.myRight.myItem.equals(key))) {
			return t;
		}
		TreeNode leftGet = getParent(t.myLeft, key);
		TreeNode rightGet = getParent(t.myRight, key);
		return leftGet != null ? leftGet : rightGet;
	}
	private TreeNode inorderSuccessor(TreeNode t) {
		if (t == null || t.myRight == null) {
			return null;
		}
		TreeNode currentNode = t.myRight;
		while (currentNode.myLeft != null) {
			currentNode = currentNode.myLeft;
		}
		return currentNode;
	}
}
