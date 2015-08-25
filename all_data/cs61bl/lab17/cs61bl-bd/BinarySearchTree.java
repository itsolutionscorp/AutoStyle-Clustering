
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
		
	public boolean contains(T key) {
		return contains(myRoot, key);
	}
	
	private boolean contains(TreeNode t, T key) {
		if (t == null) {
			return false;
		}
		if (key.compareTo(t.myItem) == 0) {
			return true;
		}
		if (key.compareTo(t.myItem) < 0) {
			return contains(t.myLeft, key);
		}
		else {
			return contains(t.myRight, key);
		}
	}
	
	public void add(T key) {
		if (!this.contains(key)) {
			myRoot = add(myRoot, key);
		}
	}

	private TreeNode add(TreeNode t, T key) {
	    if (t == null) {
	    	TreeNode toReturn = new TreeNode(key);
	    	toReturn.nodeSize = 1;
	        return toReturn;
	    } else if (key.compareTo(t.myItem) < 0) {
	        t.myLeft = add(t.myLeft, key);
	        t.nodeSize = t.nodeSize + 1;
	        return t;
	    } else {
	        t.myRight = add(t.myRight, key);
	        t.nodeSize = t.nodeSize + 1;
	        return t;
	    }
	}
	
	private int size(TreeNode t) {
		if (t == null) {
			return 0;
		}
		return t.nodeSize;
	}
	
	/* returns the kth largest item in the tree, where k >= 0 
	 * and k < the number of keys in the tree
	 */
	public Comparable kthLargest(int k) {
		return kthLargest(myRoot, k);
	}
	
	private Comparable kthLargest(TreeNode t, int k) {
		if (size(t) == 1) {
			return t.myItem;
		}
		else if (k == size(t.myRight)) {
			return t.myItem;
		}
		else if (k > size(t.myRight)) {
			return kthLargest(t.myLeft, k - (size(t.myRight)+1));
		}
		else {
			return kthLargest(t.myRight, k);
		}
	}
	
	public static void main(String[] args) {
		BinarySearchTree<String> bst = new BinarySearchTree<String>();
		bst.add("C");
		bst.add("A");
		bst.add("E");
		bst.add("B");
		bst.add("D");
		bst.printPreorder();
		System.out.println(bst.kthLargest(0));
		System.out.println(bst.kthLargest(1));
		System.out.println(bst.kthLargest(2));
		System.out.println(bst.kthLargest(3));
		System.out.println(bst.kthLargest(4));
	}
	
}
