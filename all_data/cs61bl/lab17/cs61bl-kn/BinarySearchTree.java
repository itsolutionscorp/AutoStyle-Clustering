public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
    public BinarySearchTree() {
        super();
    }

    public BinarySearchTree(TreeNode t) {
        super(t);
    }
    
    public T find(T p) {
    	return find(myRoot, p);
    }
    
    private T find(TreeNode t, T p) {
    	if (t == null) {
    		return null;
    	} else {
    		if (t.myItem.equals(p)) {
    			return t.myItem;
    		} 
    		else if (t.myItem.compareTo(p) < 0) {
    				return find(t.myRight, p);
    		} 
    		else {
    			return find(t.myLeft, p);
    		}
    	}
    }
    
    public T replace(T p) {
    	return replace(myRoot, p);
    }
    
    private T replace(TreeNode t, T p) {
    	if (t == null) {
    		return null;
    	} else {
    		if (t.myItem.equals(p)) {
    			T temp = t.myItem;
    			t.myItem = p;
    			return temp;
    		} 
    		else if (t.myItem.compareTo(p) < 0) {
    				return replace(t.myRight, p);
    		} 
    		else {
    			return replace(t.myLeft, p);
    		}
    	}
    }
    
    private T removeNode(TreeNode t) {
    	if (t.size > 1) {
			T key = t.myItem;
    		if (t.myRight == null) { 
    			t.myItem = removeNode(t.myLeft);
    		} else {
    			TreeNode p;
    			for ( p= t.myRight; p.size > 1;) {
    				p = p.myLeft;
    			}
    			t.myItem = removeNode(p);
    		}
    		return key;
    	} else {
    		t = null;
    		return t.myItem;
    	}
    }
    
    private T remove(TreeNode t, T key) {
    	if (t == null) {
    		return null;
    	}
		else if (t.myItem.compareTo(key) == 0) {
			return removeNode(t);
		}
		else if (t.myItem.compareTo(key) < 0) {
				return remove(t.myRight, key);
		}  else {
			return remove(t.myLeft, key);
		}
	}    
    
    public T remove(T key) {
    	return remove(myRoot, key);
    }
    
    public Comparable kthLargest (int k) {
    	return kthLargest(myRoot, k);
    }
    
    private Comparable kthLargest(TreeNode t, int k) {
    	if (t == null) {
    		throw new IllegalArgumentException();
    	} else {
    		if (t.myRight == null) {
    			if (k == 1) {
    			return t.myItem;
    			} else {
        			return kthLargest(t.myLeft, k - 1); 
    			}
    		}    					
    		else if (t.myRight.size == k - 1) {
   			return t.myItem;
    		}
    		else if (t.myRight.size < k - 1 ) {
    			return kthLargest(t.myLeft, k - (t.myRight.size+1));
    		} 
    		else {
    			return kthLargest(t.myRight, k);
    		}
    	}
    }
    
    public void add(T key) {
        myRoot = add(myRoot, key);
    }
    
    private TreeNode add(TreeNode t, T key) {
        if (t == null) {
           return new TreeNode(key);
        } else {
        	if (key.compareTo(t.myItem) < 0) {
                t.myLeft = add(t.myLeft, key);
                t.size = t.myLeft.size + 1;
                return t;
        	} else {
        		t.myRight = add(t.myRight, key);
                t.size = t.myRight.size + 1;
        		return t;
        	}
        }
    }
    
	public boolean contains(T key) {
		return contains(myRoot, key);
	}

	private  boolean contains(TreeNode t,T key) {
		if (t == null) {
			return false;
		}
		else if (t.myItem.compareTo(key) == 0) {
			return true;
		}
		else if (t.myItem.compareTo(key) < 0) {
				return contains(t.myRight, key);
		}  else {
			return contains(t.myLeft, key);
		}
	}
	
	public static void main (String[] args) {
		BinarySearchTree a = new BinarySearchTree();
		a.add(5);
		a.add(2);
		a.add(6);
		a.add(7);
		a.add(8);
		a.add(3);
		a.add(4);
		a.add(1);
		print(a, "myTree");
	}
}