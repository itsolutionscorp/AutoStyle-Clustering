public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	public BinarySearchTree() {
        super();
    }

    public BinarySearchTree(TreeNode t) {
    	super(t);
    }
	
	public boolean contains(T key) {
		return contains(this.myRoot, key);
	}
	
	private boolean contains (TreeNode t, T key) {
		if (t == null)
			return false;
		if (key.compareTo(t.myItem) == 0)
			return true;
		if (key.compareTo(t.myItem) < 0)
			return contains(t.myLeft, key);
		if (key.compareTo(t.myItem) > 0)
			return contains(t.myRight, key);
		return false;
	}

	
	
	public void add(T key) {
		if (! this.contains(key))
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
		BinarySearchTree<String> b = new BinarySearchTree<String>();
		b.add("C");
		b.printInorder();
		b.add("A");
		b.printInorder();
		b.add("E");
		b.printInorder();
		b.add("B");
		b.printInorder();
		b.add("D");
		b.printInorder();
		b.add("A");
		b.printInorder();
		BinarySearchTree<String> x = new BinarySearchTree<String>();
		x.add("A");
		x.printInorder();
		x.add("B");
		x.printInorder();
		x.add("C");
		x.printInorder();
		x.add("D");
		x.printInorder();
		x.add("E");
		x.printInorder();
	}
}
