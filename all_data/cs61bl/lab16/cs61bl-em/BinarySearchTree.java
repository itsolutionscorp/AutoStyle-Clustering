
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	public boolean contains (T key) {
		if (myRoot != null) {
			return contains(myRoot, key);
		}
		return false;
	}
	
	private boolean contains (TreeNode t, T key) {
    	if (t == null) {
    		return false;
    	}
    	else if (key.equals(t.myItem)) {
    		return true;
    	}
    	else if (key.compareTo(t.myItem) < 0) {
    		return contains(t.myLeft, key);
    	}
    	else {
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
	        t.myLeft = add(t.myLeft, key);
	        return t;
	    } else {
	        t.myRight = add(t.myRight, key);
	        return t;
	    }
	}
	
	public static void main (String args[]) {
		BinarySearchTree tree = new BinarySearchTree();
		tree.printPreorder();
		tree.add("C");
		tree.printPreorder();
		tree.add("A");
		tree.printPreorder();
		tree.add("B");
		tree.printPreorder();
		tree.add("E");
		tree.printPreorder();
		tree.add("D");
		tree.printPreorder();
	}
}
