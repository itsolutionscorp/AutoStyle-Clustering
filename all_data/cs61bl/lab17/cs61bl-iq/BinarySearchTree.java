
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T>{

	public static void main(String[] args) {

	}
	
	public boolean contains(T key) {
		if (myRoot == null) {
			return false;
		}
		return containsHelper(myRoot, key);
	}
	
	public boolean containsHelper(TreeNode node, T key) {
		if (myRoot.myItem.compareTo(key) == 0) {
			return true;
		} else if (myRoot.myItem.compareTo(key) < 0) {
			if (myRoot.myLeft == null) {
				return false;
			}
			return containsHelper(myRoot.myLeft, key);
		} else {
			if (myRoot.myRight == null) {
				return false;
			}
			return containsHelper(myRoot.myRight, key);
		}
	}
	
	public void add(T key) {
		if (!this.contains(key)) {
			 myRoot = add(myRoot, key);
		}
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
		if (this.contains(key)) {
			 myRoot = remove(myRoot, key);
		}
	}

	private TreeNode remove(TreeNode t, T key) {
	    if (key.compareTo(t.myItem) == 0) {
	        return null;
	    } else if (key.compareTo(t.myItem) < 0) {
	        t.myLeft = remove(t.myLeft, key);
	        return t;
	    } else {
	        t.myRight = remove(t.myRight, key);
	        return t;
	    }
	}

}
