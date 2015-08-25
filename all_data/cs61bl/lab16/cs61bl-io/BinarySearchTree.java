public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	public boolean contains(T key) {
		return contains(myRoot, key);
	}
	
	public void add(T key) {
		if (!this.contains(key))
			myRoot = add(myRoot, key);
	}

	private TreeNode add(TreeNode t, T key) {
	    if (t == null) {
	        return new TreeNode(key);
	    } else if (key.compareTo(t.myItem) < 0) {
	        t.myLeft = add(t.myLeft, key);
	        //System.out.println(t.myLeft.myItem);
	        return t;
	    } else {
	        t.myRight = add(t.myRight, key);
	        //System.out.println(t.myRight.myItem);
	        return t;
	    }
	}
	
	private boolean contains(TreeNode t, T key) {
		if (t == null)
			return false;
		else if (t.myItem.equals(key)) {
			return true;
		}
		else if (t.myItem.compareTo(key) > 0)
			return contains(t.myLeft, key);
		else if (t.myItem.compareTo(key) < 0)
			return contains(t.myRight, key);
		else
			return false;
	}
	
	public int compareTo(T o2) {
		return myRoot.myItem.compareTo(o2);
	}
	
	public static void main(String[] args) {
		BinarySearchTree<String> t = new BinarySearchTree<String>();
		print(t, "the empty tree");
		t.add("w");
		t.add("a");
		t.add("b");
		t.add("e");
		t.add("f");
		t.add("c");
		t.add("d");
		t.add("a"); // duplicate element, should not be added
		print(t, "lalala");
		System.out.println(t.contains("a"));
		System.out.println(t.contains("W"));
		
	}

}
