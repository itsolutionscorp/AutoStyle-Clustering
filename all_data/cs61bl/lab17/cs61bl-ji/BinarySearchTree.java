
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	public boolean contains(TreeNode t, T key) {
		if (t != null) {
			if (key.compareTo(t.myItem) == 0) {
				return true;
			} else if (key.compareTo(t.myItem) < 0) {
				return contains(t.myLeft, key);
			} else {
				return contains(t.myRight, key);
			}
		}
		return false;
	}

	public boolean contains(T key) {
		if (myRoot == null) {
			return false;
		} else {
			return contains(myRoot, key);
		}

	}

	public Comparable kthLargest(int k) {
		if (myRoot == null) {
			return null;
		} else {
			return helper(myRoot, k);
		}
	}

	public Comparable helper(TreeNode node, int k) {
		// System.out.println(node.myItem);
		if (node.myRight != null) {
			if (node.myRight.size() + 1 == k) {
				return node.myItem;
			} else if (k < node.myRight.size() + 1) {
				return helper(node.myRight, k);
			} else if (node.myLeft != null) {
				return helper(node.myLeft, k - node.myRight.size() - 1);
			}
		} else if (node.myLeft != null) {
			return helper(node.myLeft, k - 1);
		} else if (k == 1) {
			return node.myItem;
		}
		return null;
	}

	public void add(T key) {
		if (!contains(key)) {
			myRoot = add(myRoot, key);
		}

	}

	public TreeNode add(TreeNode t, T key) {
		if (t == null) {
			return new TreeNode(key);
		} else if (key.compareTo(t.myItem) < 0) {
			t.mySize += 1;
			t.myLeft = add(t.myLeft, key);
			return t;
		} else if (key.compareTo(t.myItem) > 0) {
			t.mySize += 1;
			t.myRight = add(t.myRight, key);
			return t;
		} else {
			System.out.println("Not added");
		}
		return t;
	}

	/**
	 * @param args
	 */

	public static void main(String[] args) {

	}
}
