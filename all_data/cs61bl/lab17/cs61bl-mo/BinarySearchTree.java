public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	public BinarySearchTree() {
		super();
	}

	public boolean contains(T key) {
		return contains(myRoot, key);
	}

	private boolean contains(TreeNode t, T key) {
		if (t == null) {
			return false;
		} else if (key == t.myItem) {
			return true;
		} else if (key.compareTo(t.myItem) < 0) {
			return contains(t.myLeft, key);
		} else {
			return contains(t.myRight, key);
		}
	}

	public void add(T key) {
		myRoot = add(myRoot, key);
	}

	private TreeNode add(TreeNode t, T key) {
		if (t == null) {
			return new TreeNode(key);
		} else if (key.compareTo(t.myItem) < 0) {
			t.size += 1;
			t.myLeft = add(t.myLeft, key);
			return t;
		} else if (key.compareTo(t.myItem) == 0){
			t.myItem = key;
			return t;
		}
		else {
			t.size += 1;
			t.myRight = add(t.myRight, key);
			return t;
		}
	}
	
	public TreeNode getNode(T key) {
		if (myRoot != null){
			return getNodeHelper(myRoot, key);
		}
		return null;
	}
	
	private TreeNode getNodeHelper(TreeNode t, T key) {
		if (this.contains(key)) {
			if (t.myItem.compareTo(key) == 0) {
				return t;
			}
			if (t.myLeft != null) {
				return getNodeHelper(t.myLeft, key);				
			}
			if (t.myRight != null) {
				return getNodeHelper(t.myRight, key);
			}
		}
		return null;
	}
	
	public Comparable<T> kthLargest(int k) {
		if (myRoot != null) {
			return kthHelper(myRoot, k);
		}
		return null;
	}
	
	
	private Comparable<T> kthHelper(TreeNode t, int k) {
		if(t.myRight == null || t.myLeft == null){
			return t.myItem;
		}
		if(t.myRight.size + 1 == k ){
			return t.myItem;
		}if (k <= t.myRight.size) {
			return kthHelper(t.myRight, k);
		} else {
			return kthHelper(t.myLeft, (k-t.myRight.size-1));
		}
	}
	
}
