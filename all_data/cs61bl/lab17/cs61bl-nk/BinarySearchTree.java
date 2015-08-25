public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T>{

	/**
	 * @param args
	 */
	
	public boolean contains(T key) {
		if (myRoot != null) {
    		return contains(myRoot, key);
		} return false;
	}
	
    private boolean contains (TreeNode t, T key) {
    	if (t == null) {
    		return false;
    	}
    	if (key.equals(t.myItem)) {
    		return true;
    	} if (key.compareTo(t.myItem) < 0) {
    		return contains (t.myLeft, key);
    	} if (key.compareTo(t.myItem) > 0) {
    		return contains(t.myRight, key);
    	} return false;
    }
    
    public void add(T key) {
    	if (! contains(key)) {
    		myRoot = add(myRoot, key);
    	}
    }
    
    Comparable kthLargest (int k) {
    	return kthLargestHelper (k, myRoot);
    }

    Comparable kthLargestHelper (int k, TreeNode t) {
    	if (t.myRight != null) {
    		if (t.myRight.myTreeSize > k) {
    			return kthLargestHelper (k, t.myRight);
    	    } if (t.myRight.myTreeSize == k) {
    	    	return retrieveLeft (t.myRight).myItem;
    		} if (k == t.myRight.myTreeSize + 1) {
    			return t.myItem;
    	    } else {
    	    	return kthLargestHelper (k - t.myRight.myTreeSize - 1, t.myLeft);
    	    }
    	} else {
    		if (t.myTreeSize - k == 1) {
    			return t.myItem;
    		} return kthLargestHelper (k - 1, t.myLeft);
    	}
    }
    
    TreeNode retrieveLeft (TreeNode t) {
    	if (t.myLeft == null) {
    		return t;
    	} else {
    		return retrieveLeft (t.myLeft);
    	}
    }

    private TreeNode add(TreeNode t, T key) {
        if (t == null) {
        	TreeNode newNode = new TreeNode(key);
        	newNode.myTreeSize = 1;
            return newNode;
        } else if (key.compareTo(t.myItem) < 0) {
            t.myLeft = add(t.myLeft, key);
            t.myTreeSize++;
            return t;
        } else {
        	t.myRight = add(t.myRight, key);
            t.myTreeSize++;
        	return t;
        }
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinarySearchTree<String> myTree = new BinarySearchTree<String>();
		myTree.add("C");
		myTree.add("A");
		myTree.add("E");
		myTree.add("B");
		myTree.add("D");
		myTree.print(myTree, "myTree looks like this");
		System.out.println(myTree.contains("E"));
		System.out.println(myTree.myRoot.myTreeSize);
		System.out.println(myTree.kthLargest(3));
		System.out.println(myTree.kthLargest(2));
		System.out.println(myTree.kthLargest(5));
		System.out.println(myTree.kthLargest(4));
		System.out.println(myTree.kthLargest(1));
	}
	
    public void remove(T key) {
    	myRoot = removeHelper(key, myRoot);
    }
    
    public TreeNode removeHelper(T key, TreeNode t) {
    	if (t.myLeft == null && t.myRight == null) {
    		return null;
    	} else if (t.myLeft == null && t.myItem.equals(key)) {
    		return t.myRight;
    	} else if (t.myRight == null && t.myItem.equals(key)) {
    		return t.myLeft;
    	} else {
    		
    		if (key.compareTo(t.myItem)>0) {
    			t.myRight = removeHelper(key, t.myRight);
    			return t;
    		}
    		if (key.compareTo(t.myItem)<0) {
    			t.myLeft = removeHelper(key, t.myLeft);
    			return t;
    		}
    		else {
    			T var = findSuccessor(t).myItem;
    			t.myItem = var;
    			var = null;	
    		}
    	}
    	return null;
    }
    public TreeNode findSuccessor(TreeNode t) {
    	if (t == null) {
    		return null;
    	}
    	if (t.myRight == null) {
    		return null;
    	}
    	else {
    		return leftmost(t.myRight);
    	}
    }
    public TreeNode leftmost(TreeNode t) {
    	if (t.myLeft == null) {
    		return t.myLeft;
    	}
    	return leftmost(t.myLeft);
    }
}
    