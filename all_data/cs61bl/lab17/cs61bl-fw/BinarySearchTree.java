
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	public BinarySearchTree() {
		super();
	}
	
	public boolean contains(T key) {
		if (super.myRoot == null) {
			return false;
		}
		return containsHelper(super.myRoot, key);
	}
	
	private boolean containsHelper (TreeNode t, T key) {	
		if (key.compareTo(t.myItem) > 0) {
			if (t.myRight != null)
				return containsHelper(t.myRight, key);
		} else if (key.compareTo(t.myItem) < 0) {
			if (t.myLeft != null)
				return containsHelper(t.myLeft, key);
		} else {
			return true;
		}
		return false;
	}
	
	public void add(T key) {
		if (myRoot != null)
			myRoot.size++;
		myRoot = addHelper(myRoot, key);
	}
	
	private TreeNode addHelper(TreeNode t, T key) {
		int temp = 0;
	    if (t == null) {
	    	TreeNode cur = new TreeNode(key);
	        return cur;
	    } else if (key.compareTo(t.myItem) < 0) {
	    	if (t.myLeft != null) {
	    		temp = 1;
	    	}
	        t.myLeft = addHelper(t.myLeft, key);
	        t.myLeft.size+= temp;
	        return t;
	    } else if (key.compareTo(t.myItem) > 0) {
	    	if (t.myRight != null) {
	    		temp = 1;
	    	}
	        t.myRight = addHelper(t.myRight, key);
	        t.myRight.size += temp;
	        return t;
	    } else {
	    	t.myItem = key;
	    }
	    return t;
	}
	
	public T getHelper(T node) {
		if (super.myRoot == null) 
			return null;
		return getTHelper(super.myRoot, node);
	}
	
	public T getTHelper(TreeNode t, T key) {
		if (key.compareTo(t.myItem) > 0) {
			if (t.myRight != null)
				return getTHelper(t.myRight, key);
		} else if (key.compareTo(t.myItem) < 0) {
			if (t.myLeft != null)
				return getTHelper(t.myLeft, key);
		} else {
			return t.myItem;
		}
		return null;
	}
	
	public void removeKey(T node) {
		if (super.myRoot == null) 
			return;
		if (removeKeyHelper(super.myRoot, node) != null) 
			super.myRoot = removeKeyHelper(super.myRoot, node);
	}
	
	public TreeNode removeKeyHelper(TreeNode t, T key) {
		if (t == null) {
			return null;
		}
		
		if (key.compareTo(t.myItem) > 0) {
			t.myRight = removeKeyHelper(t.myRight, key);
		} else if (key.compareTo(t.myItem) < 0) {
			t.myLeft = removeKeyHelper(t.myLeft, key);
		} else {
			if (t.myRight == null) 
				return t.myLeft;
            if (t.myLeft  == null) 
            	return t.myRight;
            t.myItem = dataReverse(t.myLeft);
            t.myLeft = removeKeyHelper (t.myLeft, key);
		}
		return t;
	}
	
	private T dataReverse(TreeNode p) {
		while (p.myRight != null)
			p = p.myRight;
		return p.myItem;
	}

	public Comparable kthLargest (int k) {
		return kthLargestHelper(myRoot, k);
	}
	
	public Comparable kthLargestHelper(TreeNode cur, int k) {
    	if (cur == null) 
    		return null; 

    	int countRight = 0;
    	
    	if (cur.myRight != null) {
    		countRight = cur.myRight.size;
    	}

    	if (k == countRight)  
    		return (Comparable)(cur.myItem) ; 
    	
    	if (k < countRight) {
    		return kthLargestHelper(cur.myRight, k);
    	} else {
    		return kthLargestHelper(cur.myLeft, k-countRight-1);
    	}

    }
}
