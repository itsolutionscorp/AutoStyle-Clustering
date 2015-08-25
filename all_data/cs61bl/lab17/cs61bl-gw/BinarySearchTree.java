public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	public boolean contains(T key) {
		return containsHelper(myRoot, key);
	}

	public boolean containsHelper(TreeNode t, T key) {
		if (t == null) {
			return false;
		} else if (key.compareTo(t.myItem) == 0) {
			return true;
		} else if (key.compareTo(t.myItem) < 0) {
			return containsHelper(t.myLeft, key);
		} else {
			return containsHelper(t.myRight, key);
		}
	}

	public void add(T key) {
		myRoot = add(myRoot, key);
	}

	private TreeNode add(TreeNode t, T key) {
		if (t == null) {
			TreeNode result = new TreeNode(key);
			result.size++;
			return result;
		} else if (key.compareTo(t.myItem) < 0) {
			t.myLeft = add(t.myLeft, key);
			t.myLeft.size++;
			return t;
		} else if (key.equals(t.myItem)) {
			return null;
		} else {
			t.myRight = add(t.myRight, key);
			t.myRight.size++;
			return t;
		}
	}

	private Comparable kthLargest(int k) {
		return kthLargestHelper(myRoot, k);
	}

	private Comparable kthLargestHelper(TreeNode t, int k) {
		if (t == null || k < 0) {
			return null;
		}
		if (t.myRight == null && k == 0) {
			return t.myItem;
		}
		if (t.myRight == null && k != 0) {
			return kthLargestHelper(t.myLeft, k);
		}

		if (k == t.myRight.size - 1) {
			return t.myItem;
		} else if (k < t.myRight.size) {
			return kthLargestHelper(t.myRight, k);
		} else {
			return kthLargestHelper(t.myLeft, k - t.myRight.size);
		}
	}
	
	

	public static void main(String[] args) {
		BinarySearchTree<Integer> t1 = new BinarySearchTree<Integer>();
		t1.add(4);
		t1.add(2);
		t1.add(6);
		t1.add(1);
		t1.add(3);
		t1.add(5);
		t1.add(7);
		System.out.println(t1.myRoot.myItem); // 4
		System.out.println(t1.myRoot.myLeft.myItem); // 2
		System.out.println(t1.myRoot.myRight.myItem); // 6

		System.out.println(t1.myRoot.myRight.myRight.myItem); // 7
		System.out.println(t1.myRoot.myRight.myLeft.myItem); // 5

		System.out.println(t1.myRoot.myLeft.myLeft.myItem); // /1
		System.out.println(t1.myRoot.myLeft.myRight.myItem); // 3

		System.out.println("============================");

		System.out.println(t1.kthLargest(0)); // 7
		System.out.println(t1.kthLargest(1)); // 6
		System.out.println(t1.kthLargest(2)); // 5
		System.out.println(t1.kthLargest(3)); // 4
		System.out.println(t1.kthLargest(4)); // 3
		System.out.println(t1.kthLargest(5)); // 3
		System.out.println(t1.kthLargest(6)); // 3

	}

}
