
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T>{
	
	public boolean contains(T key) {
		if (myRoot == null) {
			return false;
		} else {
			return containsH(myRoot, key);
		}
	}

	public boolean containsH(TreeNode t, T key) {
		if (t == null) {
			return false;
		} else if (key.compareTo(t.myItem) == 0) {
			return true;
		} else if (key.compareTo(t.myItem) < 0) {
			return containsH(t.myLeft, key);
		} else {
			return containsH(t.myRight, key);
		}
	}
	
	public void add(T key){
		if (contains(key)){
			return;
		}
		myRoot = addH(myRoot, key);
	}
	
	public TreeNode addH(TreeNode t, T key) {
		if (t == null) {
			return new TreeNode(key);
		} else if (key.compareTo(t.myItem) < 0) {
			t.myLeft = addH(t.myLeft, key);
			return t;
		} else {
			t.myRight = addH(t.myRight, key);
			return t;
		}
	}

}
