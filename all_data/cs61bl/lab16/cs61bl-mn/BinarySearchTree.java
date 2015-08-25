
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	public boolean contains(T key) {
		return contains(myRoot, key);
	}
	
	private boolean contains (TreeNode t, T key) {
		if (t == null) {
			return false;
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
	        t.myLeft = add(t.myLeft, key);
	        return t;
	    } else {
	        t.myRight = add(t.myRight, key);
	        return t;
	    }
	}
	
	public static void main (String[] args) {
		BinarySearchTree<Integer> t = new BinarySearchTree<Integer>();
		t.add(3);
		t.add(1);
		t.add(2);
		t.add(4);
		t.add(5);
		t.printInorder();
		t.printPreorder();
	}
}
