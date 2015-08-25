
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	public void add(T key) {
		if (!contains(key)) {
			if (myRoot == null) {
				myRoot = new TreeNode(key);
			} else {
				add(myRoot, key);	
			}
		}
	}
	
	public boolean contains (T key) {
		if (myRoot != null) {
			return contains(myRoot, key);
		} else {
			return false;
		}
	}
	
	private boolean contains (TreeNode t, T key) {
    	if (key.compareTo(t.myItem) == 0) {
    		return true;
    	} else if (key.compareTo(t.myItem) < 0) {
    		if (t.myLeft != null) {
    			return contains(t.myLeft, key);
    		}
    	} else if (key.compareTo(t.myItem) > 0) {
    		if (t.myRight != null) {
    			return contains(t.myRight, key);
    		}
    	}
    	return false;
	}
	
	private void add (TreeNode t, T key) {
		if (key.compareTo(t.myItem) < 0) {
			if (t.myLeft != null) {
				add(t.myLeft, key);
			} else {
				t.myLeft = new TreeNode(key);
			}
		} else if (key.compareTo(t.myItem) > 0) {
			if (t.myRight != null) {
				add(t.myRight, key);
			} else {
				t.myRight = new TreeNode(key);
			}
		}
	}
	
	public static void main(String[] args) {
		BinarySearchTree b = new BinarySearchTree();
		b.add("C");
		b.add("A");
		b.add("B");
		b.add("E");
		b.add("D");
		print(b, "test tree");
		
	}
}
