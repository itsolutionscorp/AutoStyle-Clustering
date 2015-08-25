


public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

    public BinarySearchTree() {
        super();
    }

    public BinarySearchTree(TreeNode t) {
        super(t);
    }
	
    public boolean contains(T key) {
    	return containshelper(myRoot, key);
    }
    
    public void add(T key) {
        myRoot = add(myRoot, key);
    }

    private TreeNode add(TreeNode t, T key) {
        if (t == null) {
            return new TreeNode(key);
        } 
        t.size++;
        if (key.compareTo(t.myItem) == 0) {
        	return t;
        } else if (key.compareTo(t.myItem) < 0) {
            t.myLeft = add(t.myLeft, key);
            return t;
        } else {
            t.myRight = add(t.myRight, key);
            return t;
        }
    }

	public Comparable<T> kthLargest(int k) { 
		TreeNode temp = this.myRoot;
		while (temp != null && k >= 0) {
			if (k == temp.myRight.size || temp.size == 1) {
				return temp.myItem;
			} else if (temp.myRight.size > k) {
				temp = temp.myLeft;
				k -= temp.myRight.size + 1;
			} else {
				temp = temp.myRight;
			}
		}
		return temp.myItem;
	}

    private boolean containshelper(TreeNode t, T key) {
    	if (t == null) {
    		return false;
    	} else if (key.compareTo(t.myItem) == 0) {
    		return true;
    	} else if (key.compareTo(t.myItem) < 0) {
    		return containshelper(t.myLeft, key);
    	} else {
    		return containshelper(t.myRight, key);
    	}
    }
    
    public static void main(String[] args) {
    	BinarySearchTree<String> blah = new BinarySearchTree<String>();
    	blah.add("C");
    	blah.add("A");
    	blah.add("B");
    	blah.add("E");
    	blah.add("D");
    	print(blah, "blah");
    }
    
}
