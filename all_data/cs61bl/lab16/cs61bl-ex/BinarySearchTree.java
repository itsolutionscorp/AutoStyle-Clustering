public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	public boolean contains(T key){
		return contains(myRoot, key);
	}
	
	private boolean contains(TreeNode t, T key){
		if (t == null){
			return false;
		}
		if (key.equals(t.myItem)){
			return true;
		}
		if (key.compareTo(t.myItem) < 0){
			return contains(t.myLeft, key);
		}else
			return contains(t.myRight, key);
	}
	
	public void add(T key) {
	    myRoot = add(myRoot, key);
	}

	private TreeNode add(TreeNode t, T key) {
	    if (t == null) {
	        return new TreeNode(key);
	    } if (contains(key)){
	    	return t;
		}else if (key.compareTo(t.myItem) < 0) {
	        t.myLeft = add(t.myLeft, key);
	        return t;
	    } else {
	        t.myRight = add(t.myRight, key);
	        return t;
	    }
	}
}
