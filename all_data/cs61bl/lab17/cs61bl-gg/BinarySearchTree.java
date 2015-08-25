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

	public Comparable kthLargest (int k) {
		if (myRoot != null && k >= 0 && k < this.myRoot.size) 
			return kthLargest(k, myRoot);
		return null;
	}
	
	private Comparable kthLargest(int k, TreeNode t) {
		if (t.myRight == null && t.myLeft == null) {
			return t.myItem;
		}
		if (t.myRight == null) {
			if (k == 0)
				return t.myItem;
			return kthLargest(k-1, t.myLeft);
		}
			
		
		if (t.myRight.size == k)
			return t.myItem;
		if (t.myRight.size < k && t.myLeft != null)
			return kthLargest(k - t.myRight.size-1, t.myLeft);
		return kthLargest(k, t.myRight);
	}
	
	public void add(T key) {
		if (! this.contains(key))
			myRoot = add(myRoot, key);
	}

	private TreeNode add(TreeNode t, T key) {
	    if (t == null) {
	        return new TreeNode(key);
	    } else if (key.compareTo(t.myItem) < 0) {
	    	t.size++;
	        t.myLeft = add(t.myLeft, key);
	        return t;
	    } else {
	    	t.size++;
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
		
		BinarySearchTree<Integer> f = new BinarySearchTree<Integer>();
//		f.add(5);
//		f.add(4);
//		f.add(6);
		f.add(7);
		f.add(4);
		f.add(9);
		f.add(8);
		f.add(10);
		f.add(3);
		f.add(5);
		f.add(6);
		f.add(2);
		f.add(1);
		print(f, "");
		System.out.println(f.kthLargest(0));
	}
}
