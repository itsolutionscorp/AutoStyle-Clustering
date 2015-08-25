public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	
	// No Instance Variables
	
	public boolean contains(T key) {
		return contains(myRoot, key);
	}
	public boolean contains(TreeNode t, T key){
		if(t == null){
			return false;
		}if(t.myItem.equals(key)){
			return true;
		}
		if(t.myItem.compareTo(key) > 0 && t.myLeft != null){
			return contains(t.myLeft, key);
		}if(t.myItem.compareTo(key) < 0 && t.myRight != null){
			return contains(t.myRight, key);
		}
		return false;
		
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

}
