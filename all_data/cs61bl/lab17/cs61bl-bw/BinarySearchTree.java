
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	
	
	public boolean contains(T key) {
		if (myRoot == null) {
			return false;
		} else if (key.compareTo(myRoot.myItem) == 0) {
			return true;
		} else {
			return contains(myRoot, key);
		}
	}
	
	private boolean contains(TreeNode t, T key) {
		if (t == null) {
			return false;
		}
		if (key.compareTo(t.myItem) == 0) {
			return true;
		} else if (key.compareTo(t.myItem) < 0) {
			return contains(t.myLeft, key);
		} else {
			return contains(t.myRight, key);
		}
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
	
	public static void main(String[] args) {
		BinarySearchTree<String> abcs = new BinarySearchTree<String>();
		abcs.add("c");
		abcs.add("a");
		abcs.add("e");
		abcs.add("b");
		abcs.add("d");
		print(abcs, "abc tree");
	}
}
