

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	public static void main(String[] args) {
		BinarySearchTree<String> a = new BinarySearchTree<String>();
		a.add("5");
		a.add("4");
		a.add("7");
		a.add("6");
		a.add("8");
		a.add("3");
		print(a, "Hey there");
		System.out.println(a.kthLargest(3));
	}
	
	Comparable kthLargest (int k) {
		if(myRoot!=null) {
			return helper(myRoot, k+1);
		}
		else {
			return null;
		}
	}
	
	public boolean contains(T key) {
		if(myRoot!=null) {
			return contains(myRoot, key);
		}
		return false;
	}
	
    private boolean contains (TreeNode t, T key) {
    	if(t==null) {
    		return false;
    	}
    	if(key.compareTo(t.myItem) == 0) {
    		return true;
    	}
    	else if (key.compareTo(t.myItem) < 0) {
    		return contains(t.myLeft, key);
    	}
    	else {
    		return contains(t.myRight, key);
    	}
    }
    
    public void add(T key) {
    	if(myRoot!=null) {
    		if(!contains(myRoot, key)) {
    			myRoot = add(myRoot, key);
    		}
    	}
    	else {
    		myRoot = add(myRoot, key);
    	}
    }
    
    public Comparable helper(BinaryTree.TreeNode t, int k) {
    	if(t.myRight == null && t.myLeft==null) {
    		return (Comparable) t.myItem;
    	}
    	int size = 0;
    	if(t.myRight != null) {
    		size = t.myRight.size;
    	}
    	if(size == k) {
    		return (Comparable) t.myItem;
    	}
    	else if (size > k) {
    		return helper(t.myRight, k);
    	}
    	else {
    		return helper(t.myLeft, k-size-1);
    	}
    }

    private TreeNode add(TreeNode t, T key) {
        if (t == null) {
            return new TreeNode(key);
        } else if (key.compareTo(t.myItem) < 0) {
            t.myLeft = add(t.myLeft, key);
            t.myLeft.size++;
            return t;
        } else {
            t.myRight = add(t.myRight, key);
            t.myRight.size++;
            return t;
        }
    }
}
