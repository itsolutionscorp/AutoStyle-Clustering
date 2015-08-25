//import BinaryTree.TreeNode;


public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public boolean contains(T key) {
		if (myRoot != null) {
			return containsHelp(myRoot, key);
		}
		return false;
	}
	
	public boolean containsHelp(TreeNode t, T key) {
		if (t == null) {
			return false;
		} if (t.myItem.equals(key)) {
			return true;
		} if (key.compareTo(t.myItem) < 0) {
			if (t.myLeft == null) {
				return false;
			}
			return containsHelp(t.myLeft, key);
		} else {
			if (t.myRight == null) {
				return false;
			}
			return containsHelp(t.myRight, key);
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

}
