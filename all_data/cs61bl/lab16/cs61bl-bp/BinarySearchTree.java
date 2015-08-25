public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	public boolean contains(T key) {
		if (myRoot != null) {
			return containsHelper(myRoot, key);
		}
		return false;
	}

	private boolean containsHelper(TreeNode t, T key) {
		if (t == null) {
			return false;
		}
		int compare = key.compareTo(t.myItem);
		if (compare < 0) {
			return containsHelper(t.myLeft, key);
		} else if (compare > 0) {
			return containsHelper(t.myRight, key);
		} else {
			return true;
		}

	}

	public void add(T key) {
		if (!contains(key)) {
			myRoot = addHelper(myRoot, key);
		}
	}

	private TreeNode addHelper(TreeNode t, T key) {
		if (t == null) {
			return new TreeNode(key);
		} else if (key.compareTo(t.myItem) < 0) {
			t.myLeft = addHelper(t.myLeft, key);
			return t;
		} else {
			t.myRight = addHelper(t.myRight, key);
			return t;
		}
	}
	
	public static void main(String[] args) {
		BinarySearchTree<String> tree = new BinarySearchTree<String>();
		tree.add("C");
		tree.add("A");
		tree.add("B");
		tree.add("E");
		tree.add("D");
		tree.printInorder();
	}
}
