
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T>  {

	public BinarySearchTree() {
		super();
	}
	
	public BinarySearchTree(TreeNode t) {
		super(t);
	}

	public boolean contains(T key) {
		return contains(myRoot, key);
	}
	
	private boolean contains (TreeNode t, T key) {
		if (t == null) {
			return false;
		}
		if (key.compareTo(t.myItem) == 0) {
			return true;
		} else if (key.compareTo(t.myItem) < 0) {
			return contains(t.myLeft, key);
		} else {
			return contains(t.myRight, key);
		}
	}
	
	public void add(T key) {
		if (!contains(key)) {
			myRoot = add(myRoot, key);
		}
	}

	private TreeNode add(TreeNode t, T key) {
	    if (t == null) {
	        return new TreeNode(key);
	    } else if (key.compareTo(t.myItem) < 0) {
	    	t.size++;
	        t.myLeft = add(t.myLeft, key);
	        return t;
	    } else {
	    	t.size++;
	        t.myRight = add(t.myRight, key);
	        return t;
	    }
	}
	
	public Comparable kthLargest (int k) {
		if (myRoot == null) {
			return null;
		}
		return kthLargest(k, myRoot);
	}
	
	private Comparable kthLargest(int k, TreeNode t) {
		if (t.myRight == null) {
			if (k == 0) {
				return t.myItem;
			} else {
				return kthLargest(k - 1, t.myLeft);
			}
		} else {
			if (t.myRight.size == k) {
				return t.myItem;
			} else if (t.myRight.size > k) {
				return kthLargest(k, t.myRight);
			} else {
				return kthLargest(k - t.myRight.size - 1, t.myLeft);
			}
		}
	}
	
	public static void main(String[] args) {
        BinarySearchTree<String> t = new BinarySearchTree<String>();
        t.add("C");
        t.add("A");
        t.add("B");
        t.add("E");
        t.add("D");
        print(t, "the example tree");
    }
}
