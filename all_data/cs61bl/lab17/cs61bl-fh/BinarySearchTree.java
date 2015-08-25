public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	public static BinarySearchTree<Integer> fillSampleTreeBinarySearch() {
		BinarySearchTree<Integer> t = new BinarySearchTree<Integer>();
		t.myRoot = t.new TreeNode(15, t.new TreeNode(13, t.new TreeNode(11,
				t.new TreeNode(9), t.new TreeNode(12)), null), t.new TreeNode(
				20));
		return t;
	}

	public boolean contains(T key) {
		if (myRoot != null)
			return contains(myRoot, key);
		else
			return false;
	}

	private boolean contains(TreeNode t, T key) {
		if (t == null)
			return false;
		if (t.myItem.compareTo(key) == 0)
			return true;
		else if (t.myItem.compareTo(key) < 0)
			return contains(t.myRight, key);
		else
			return contains(t.myLeft, key);
	}

	public void add(T key) {
		myRoot = add(myRoot, key);
	}

	private TreeNode add(TreeNode t, T key) {
		if (t == null) {
			return new TreeNode(key);
		} else if (key.compareTo(t.myItem) == 0) {
			return t;
		} else if (key.compareTo(t.myItem) < 0) {
			int tempSize;
			if (t.myLeft == null) {
				tempSize = 0;
			} else {
				tempSize = t.myLeft.size;
			}
			t.myLeft = add(t.myLeft, key);
			if (t.myLeft.size == tempSize + 1) {
				t.size++;
			}
			return t;
		} else {
			int tempSize;
			if (t.myRight == null) {
				tempSize = 0;
			} else {
				tempSize = t.myRight.size;
			}
			t.myRight = add(t.myRight, key);
			if (t.myRight.size == tempSize + 1) {
				t.size++;
			}
			return t;
		}
	}

	public Comparable kthLargest(int k) {
		return myRoot.kthLargestHelper(k + 1); // gets rid of 0 indexing
	}

	public static void main(String[] args) {
		BinarySearchTree<String> t = new BinarySearchTree<String>();
		t.add("C");
		t.add("A");
		t.add("B");
		t.add("E");
		t.add("D");
	}

}
