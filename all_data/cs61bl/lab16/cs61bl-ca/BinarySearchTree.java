
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	public BinarySearchTree() {
		super();
	}
	private boolean contains (TreeNode t, T key) {
    	if (t.myItem.equals(key)) {
    		return true;
    	} else if (t.myRight != null && t.myItem.compareTo(key) < 0) {
    		return contains(t.myRight, key);
    	} else if (t.myLeft != null){
    		return contains(t.myLeft, key);
    	} else { 
    		return false;
    	}
	}

	public boolean contains(T key) {
		return contains(myRoot, key);
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
		BinarySearchTree test = new BinarySearchTree();
		test.add("hello");
		test.add("how are you");
		test.add("i'm doing well");
		System.out.println(test.contains("hello"));
		print(test, null);
	}
	
}

