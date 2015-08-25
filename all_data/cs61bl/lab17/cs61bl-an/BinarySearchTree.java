

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	public boolean contains(T key) {
		return containsHelper(myRoot, key);
	}
	
	 private boolean containsHelper (TreeNode t, T key) {
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
	        return new TreeNode(key);
	    } else if (key.compareTo(t.myItem) < 0) {
	        t.myLeft = add(t.myLeft, key);
	        return t;
	    } else {
	        t.myRight = add(t.myRight, key);
	        return t;
	    }
	}
	 
	 public void remove(T key) {
		 if (!contains(key)) {
			 return;
		 }
		 remove(myRoot, key);
	 }
	 
	 public void remove(TreeNode t, T key) {
		 if (t.myLeft.myItem.compareTo(key) == 0) {
			 t.myLeft = sucNode(myRoot, t.myLeft);
		 } else if (t.myRight.myItem.compareTo(key) == 0) {
			 t.myRight = sucNode(myRoot, t.myRight);
		 } else if (t.myItem.compareTo(key) < 0) {
			 remove(t.myRight, key);
		 } else {
			 remove(t.myLeft, key);
		 }
	 }
	 
	 private TreeNode sucNode(TreeNode t, TreeNode remNode) {
		 if (remNode.myLeft == null && remNode.myRight == null) {
			 return remNode;
		 } else if (t.myLeft == null) {
			 return t;
		 } else if (t.myItem.compareTo(remNode.myItem) <= 0) {
			 return sucNode(t.myRight, remNode);
		 } else {
			 return sucNode(t.myLeft, remNode);
		 }
	 }
	 
	 public T get(T key) {
		 return get(myRoot, key);
	 }
	 
	 public T get(TreeNode t, T key) {
		 if (t == null) {
			 return null;
		 } else if (t.myItem.compareTo(key) == 0) {
			 return t.myItem;
		 } else if (t.myItem.compareTo(key) < 0) {
			 return get(t.myRight, key);
		 } else {
			 return get(t.myLeft, key);
		 }
	 }
	 
	 public Comparable kthLargest(int k) {
	    	return comparableHelper(myRoot, k);
	    }
	    
	    private Comparable comparableHelper(TreeNode t, int k) {
	    	if (k == 0) {
	    		return t.myItem;
	    	} else if (t.myLeft.size < k) {
	    		return comparableHelper(t.myRight, k - 1);
	    	} else {
	    		return comparableHelper(t.myLeft, k - 1);
	    	}
	    }
}
