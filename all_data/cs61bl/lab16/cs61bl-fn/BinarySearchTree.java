
public class BinarySearchTree<T extends Comparable <T>> extends BinaryTree<T> {
	public boolean contains(T key) {
		return contains(myRoot, key);
	}
	private boolean contains (TreeNode t, T key) {
		if (t != null) {
			int a = t.myItem.compareTo(key);
			if (a == 0) {
				return true;
			} else if (a < 0 ) {
				return contains(t.myRight, key);
			} else {
				return contains(t.myLeft, key);
			}
		}
		return false;
	}
	public void add(T key) {
		myRoot = add(myRoot, key);
	}
	
	private TreeNode add(TreeNode t, T key) {
		if (t != null) {
			int a = t.myItem.compareTo(key);
			if (a == 0) {
				throw new IllegalStateException("Cannot add duplicate element");
			} else if (a < 0 ) {
				t.myRight = add(t.myRight, key);
				return t;
			} else {
				t.myLeft = add(t.myLeft, key);
				return t;
			}
		}
		return new TreeNode(key);
	}
}
