public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T>{

	/**
	 * @param args
	 */
	
	public boolean contains(T key) {
		if (myRoot != null) {
    		return contains(myRoot, key);
		} return false;
	}
	
    private boolean contains (TreeNode t, T key) {
    	if (t == null) {
    		return false;
    	}
    	if (key.equals(t.myItem)) {
    		return true;
    	} if (key.compareTo(t.myItem) < 0) {
    		return contains (t.myLeft, key);
    	} if (key.compareTo(t.myItem) > 0) {
    		return contains(t.myRight, key); 
    	} return false;
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
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinarySearchTree<String> myTree = new BinarySearchTree<String>();
		myTree.add("C");
		myTree.add("A");
		myTree.add("E");
		myTree.add("B");
		myTree.add("D");
		myTree.print(myTree, "myTree looks like this");
		System.out.println(myTree.contains("E"));
	}

}
