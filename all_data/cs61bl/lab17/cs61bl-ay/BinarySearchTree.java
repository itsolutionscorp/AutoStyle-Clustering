public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree {

	public boolean contains(T key) {
		if (myRoot == null) {
			return false;
		} else {
			return contains(this.myRoot, key);
		}
	}

	private boolean contains(TreeNode t, T key) {
		if (t == null) {
			return false;
		} else if (key.equals(t.myItem)) {
			return true;
		} else if (key.compareTo((T) t.myItem) < 0) {
			return contains(t.myLeft, key);
		} else {
			return contains(t.myRight, key);
		}
	}

	public void add(T key) {
		myRoot = add(myRoot, key);
	}
	
	public T get(T toFind){
		return get(myRoot, toFind);
	}
	
	private T get(TreeNode node, T toFind){
		if (node.myItem.equals(toFind))
			return (T) node.myItem;
		else {
			if (((Comparable<T>) node.myItem).compareTo(toFind) < 0){
				return get(node.myRight, toFind);
			}
			return get(node.myLeft, toFind);
		}
	}

	public T remove(TreeNode node, T toRemove, TreeNode previous){
		T to_return = null;
		if (node == null)
			return null;
		if (node.myItem.equals(toRemove)){
			to_return = (T) node.myItem;
			if (node.myLeft == null && node.myRight == null) {
				to_return = (T) node.myItem;
				if (((Comparable<T>) node.myItem).compareTo((T) previous.myItem) < 0) {
					previous.myLeft = null;
				}
				else
					previous.myRight = null;
				return to_return;
			}
			else if (node.myLeft == null){
				if (previous == null)
					myRoot = node.myRight;
				else if (((Comparable<T>) node.myItem).compareTo((T) previous.myItem) < 0) {
					previous.myLeft = node.myRight;
				}
				else
					previous.myRight = node.myRight;
			}
			else if (node.myRight == null) {
				if (previous == null)
					myRoot = node.myLeft;
				if (((Comparable<T>) node.myItem).compareTo((T) previous.myItem) < 0) {
					previous.myLeft = node.myLeft;
				}
				else
					previous.myRight = node.myLeft;
			}
			else {
				T successValue = veryLeft(node.myRight, node);
				node.myItem = successValue;
			}
		} else {
			if (((Comparable<T>) node.myItem).compareTo(toRemove) < 0)
				return remove(node.myRight, toRemove, node);
			else
				return remove(node.myLeft, toRemove, node);
		}
			
		return to_return;
	}
	
	public T veryLeft(TreeNode node, TreeNode previous) {
		if (node.myLeft == null) {
			T to_return = (T) node.myItem;
			if (((Comparable<T>) node.myItem).compareTo((T) previous.myItem) < 0) {
				previous.myLeft = null;
			}
			else
				previous.myRight = null;
			return to_return;
		}
		else
			return veryLeft(node.myLeft, node);
	}
	
	private TreeNode add(TreeNode t, T key) {
		if (t == null) {
			return new TreeNode(key);
		} else if (key.compareTo((T) t.myItem) == 0) {
			return t;
		} else if (key.compareTo((T) t.myItem) < 0) {
			t.myLeft = add(t.myLeft, key);
			t.mySize++;
			return t;
		} else {
			t.myRight = add(t.myRight, key);
			t.mySize++;
			return t;
		}
	}

	public Comparable kthLargest(int k) {
		return kthLargest(myRoot, k);

	}

	private Comparable kthLargest(TreeNode node, int k) {
		if (k < 0)
			return null;
		int selfK;
		if (node.myRight == null) {
			selfK = 0;
		} else {
			selfK = node.myRight.mySize;
		}
		if (selfK == k)
			return (Comparable) node.myItem;
		else if (selfK > k) {
			return kthLargest(node.myRight, k);
		} else {
			return kthLargest(node.myLeft, k - selfK - 1);
		}

	}

}