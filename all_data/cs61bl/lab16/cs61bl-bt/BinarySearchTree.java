public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	public static void main(String[] args) {
    	BinarySearchTree t = new BinarySearchTree();
    	t.add("C");
    	t.add("A");
    	t.add("E");
    	t.add("B");
    	t.add("D");
    	t.printPreorder();
    	
    }
	
	public boolean contains(T key){
		return contains(myRoot, key);
	}
	
	private boolean contains (TreeNode t, T key){
    	if (t == null){
    		return false;
    	}
    	else if (t.myItem.compareTo(key) == 0){
    		return true;
    	}
    	else if (t.myItem.compareTo(key) > 0){
    		return contains(t.myLeft, key);
    	}
    	else{
    		return contains(t.myRight, key);
    	}
    }

	public void add(T key) {
	    myRoot = add(myRoot, key);
	}

	private TreeNode add(TreeNode t, T key) {
	    if (t == null) {
	        return new TreeNode(key);
	    } else if (contains(key)){
	    	return t;
	    } else if (key.compareTo(t.myItem) < 0) {
	        t.myLeft = add(t.myLeft, key);
	        return t;
	    } else {
	        t.myRight = add(t.myRight, key);
	        return t;
	    }
	}
}