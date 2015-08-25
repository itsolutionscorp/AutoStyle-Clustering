
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	public boolean contains(T key) {
		if (myRoot == null) {
			return false;
		} else if (myRoot.myItem.compareTo(key) == 0) {
			return true;
		} else if (myRoot.myItem.compareTo(key) == 1) {
			return containsHelper(myRoot.myLeft, key);
		} else if (myRoot.myItem.compareTo(key) == -1) {
			return containsHelper(myRoot.myRight, key);
		} else return false;
	}
	
	private boolean containsHelper(TreeNode t, T key) {
		if (t == null) return false;
		int result = t.myItem.compareTo(key);
		if (result == 0) {
			return true;
		} else if (result == 1) {
			return containsHelper(t.myLeft, key);
		} else if (result == -1) {
			return containsHelper(t.myRight, key);
		} else return false;
	}
	
	public void add(T key) {
		if (contains(key)) {
			return;
		}
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
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinarySearchTree<String> tree = new BinarySearchTree<String>();
		tree.add("C");
		tree.add("A");
		tree.add("E");
		tree.add("B");
		tree.add("D");
		tree.printPreorder();
		System.out.println(tree.contains("B"));
		System.out.println(tree.contains("H"));

	}

}
