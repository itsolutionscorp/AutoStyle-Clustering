
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	private boolean contains(T key) {
		if (myRoot != null) {
			return containsHelper(key, myRoot);
		}
		return false;
	}
	
	private boolean containsHelper(T key, TreeNode t) {
		if (t == null) {
			return false;
		} else if (key.compareTo(t.myItem) == 0) {
			return true; 
		} else if (key.compareTo(t.myItem) < 0) {
			return containsHelper(key, t.myLeft); 
		} else {
			return containsHelper(key, t.myRight);
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
	
	public static void main(String[] args) {
		BinarySearchTree t = new BinarySearchTree();
		t.add("R");
		t.add("Y");
		t.add("I");
		t.add("S");
		t.add("G");
		t.add("A");
		t.add("C");
		t.add("K");
		print(t, "hi");
	}
}
