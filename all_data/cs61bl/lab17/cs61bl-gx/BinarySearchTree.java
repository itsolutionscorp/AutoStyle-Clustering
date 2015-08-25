
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	public BinarySearchTree() {
		super();
	}
	
	public boolean contains(T key) {
		if (myRoot == null) return false;
		return containhelper(myRoot, key);
	}
	
	private boolean containhelper(TreeNode t, T key) {
		if (key.compareTo(t.myItem) == 0) return true;
		if (key.compareTo(t.myItem) < 0) {
			if (t.myLeft == null) return false;
			else return containhelper(t.myLeft, key);
		} else {
			if (t.myRight == null) return false;
			else return containhelper(t.myRight, key);
		}
	}
	
	public T getItem(T Key) {
		
	}
	
	public void add(T key) {
	    myRoot = add(myRoot, key);
	}

	Comparable kthLargest(int k) {		
		if (k < 0 || k >= myRoot.treeSize) {
			System.out.println("Input Error!");
			return 0;
		}
		
		return kthLargestHelper(myRoot, k, 0).myItem;
	}

	TreeNode kthLargestHelper(TreeNode input, int k, int rank) {
		if (input.myRight != null) {
			rank += input.myRight.treeSize;
		}

		if (k < rank) {
			rank -= input.myRight.treeSize;
			return kthLargestHelper(input.myRight, k, rank);
		} else if (k > rank) {
			rank++;
			return kthLargestHelper(input.myLeft, k, rank);
		}
		
		return input; 
	}
	
	private TreeNode add(TreeNode t, T key) {
	    if (t == null) {
	        return new TreeNode(key);
	    } else if (key.compareTo(t.myItem) < 0) {
	    	t.treeSize++;
	    	t.myLeft = add(t.myLeft, key);
	        return t;
	    } else {
	    	t.treeSize++;
	        t.myRight = add(t.myRight, key);
	        return t;
	    }
	}
}
