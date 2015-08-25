
public class BinarySearchTree <T extends Comparable<T>> extends BinaryTree<T> {
	
	public BinarySearchTree() {
        myRoot = null;
    }

    public BinarySearchTree(TreeNode t) {
        myRoot = t;
    }
    
	public boolean contains(T key) {
		return containsHelper(myRoot, key);
	}
	
	public boolean containsHelper(TreeNode t, T key) {
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
		if (contains(key)) {
			return;
		}
		myRoot = addHelper(myRoot, key);
	}
	
	public TreeNode addHelper(TreeNode t, T key) {
		if (t == null) {
			return new TreeNode(key);
		} else if (key.compareTo(t.myItem) < 0) {
	        t.myLeft = addHelper(t.myLeft, key);
	        return t;
	    } else {
	        t.myRight = addHelper(t.myRight, key);
	        return t;
	    }
	}
	
	public static void main(String[] args) {
		BinarySearchTree t = new BinarySearchTree();
		t.add("C");
		t.add("E");
		t.add("A");
		t.add("B");
		t.add("D");
		t.printInorder();
	}
}
