import java.util.Iterator;


public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	public BinarySearchTree() {
		super();
	}
	
	public BinarySearchTree(TreeNode t) {
		super(t);
	}
	
	public boolean contains (T key) {
		return contains(myRoot, key);
	}
	
	private boolean contains (TreeNode t, T key) {
		if (t == null) {
			return false;
		}
		int comparison = key.compareTo(t.myItem);
		if (comparison == 0) {
			return true;
		}
		else if (comparison < 0) {
			return contains(t.myLeft, key);
		}
		else {
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
	        t.myLeft = add(t.myLeft, key);
	        t.mySize++;
	        return t;
	    } else {
	        t.myRight = add(t.myRight, key);
	        t.mySize++;
	        return t;
	    }
	}
	
	public Comparable<T> kthLargest (int k) {
		if (myRoot != null && k <= myRoot.mySize) {
			return kthLargestHelper(myRoot, k);
		}
		return null;
	}
	
	private Comparable<T> kthLargestHelper(TreeNode t, int k) {
		int leftSize = 0;
		int rightSize = 0;
		if (t.myLeft != null) {
			leftSize = t.myLeft.mySize;
		}
		if (t.myRight != null) {
			rightSize = t.myRight.mySize;
		}
		int compare = t.mySize - leftSize;
		if (compare == k) {
			return t.myItem;
		}
		else if (compare > k) {
			return kthLargestHelper(t.myRight, k);
		}
		else {
			return kthLargestHelper(t.myLeft, k - rightSize - 1);
		}
	}
	
	
	
	// ADDITIONAL METHODS FOR MYTREEMAP
	public T get(T key) {
		if (contains(key)) {
			return get(myRoot, key);
		}
		return null;
	}
	
	private T get(TreeNode t, T key) {
		if (t == null) {
			return null;
		}
		int comparison = key.compareTo(t.myItem);
		if (comparison == 0) {
			return t.myItem;
		}
		else if (comparison < 0) {
			return get(t.myLeft, key);
		}
		else {
			return get(t.myRight, key);
		}

	}
	
	public void remove(T key) {
		if (contains(key)) {
			myRoot = remove(myRoot, key);
		}
	}

	private TreeNode remove(TreeNode t, T key) {
		if (!contains(t, key)) {
			return t;
		}
		if (t == null) {
	        return null;
	    }
		int comparison = key.compareTo(t.myItem);
	    if (comparison == 0){ // trying to remove the TreeNode t
	    	if (t.myLeft == null && t.myRight == null) { // t is a leaf node
	    		return null;
	    	}
	    	T succ = findInorderSuccessor(t.myItem);
	    	if (succ == null) { // the current TreeNode is the last one inorder
	    		return t.myLeft;
	    	}
	    	else { // the current TreeNode is not the last one
	    		t.myItem = succ;
	    		t.myRight = remove(t.myRight, succ);
	    		return t;
	    	}
	    }
	    else if (comparison < 0) { // trying to remove a key in the left subtree
	        t.myLeft = remove(t.myLeft, key);
	        t.mySize--;
	        return t;
	    }
	    else { // trying to remove a key in the right subtree
	        t.myRight = remove(t.myRight, key);
	        t.mySize--;
	        return t;
	    }
	}
	
	private T findInorderSuccessor(T key) {
		Iterator<T> iter = this.iterator();
		T succ = null;
		T curr = null;
		while (iter.hasNext()) {
			curr = iter.next();
			if (key == curr) {
				if (iter.hasNext()) {
					succ = iter.next();
				}
				break;
			}
		}
		return succ;
	}
}
