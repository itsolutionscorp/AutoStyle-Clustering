
public class BinarySearchTree <T extends Comparable<T>> extends BinaryTree<T> {
	
	public BinarySearchTree() {
        myRoot = null;
    }

    public BinarySearchTree(TreeNode t) {
        myRoot = t;
    }
    
	public boolean contains(T key) {
		return containsHelper(myRoot, key);
	}
	
	public boolean containsHelper(TreeNode t, T key) {
		if (t == null) {
	        return false;
	    } else if (key.compareTo(t.myItem) == 0) {
	    	return true;
	    } else if (key.compareTo(t.myItem) < 0) {
	        return containsHelper(t.myLeft, key);
	    } else {
	        return containsHelper(t.myRight, key);
	    }
	}
	
	public T putHelper(T kp) {
		return putHelperHelper(myRoot, kp);
	}
	
	public T putHelperHelper(TreeNode t, T kp) {
		if (t == null) {
			t.myItem = kp;
			return null;
		} else if (kp.compareTo(t.myItem) == 0) {
			T p = t.myItem;
			t.myItem = kp;
			return p;
		} else if (kp.compareTo(t.myItem) < 0) {
	        return putHelperHelper(t.myLeft, kp);
	    } else {
	        return putHelperHelper(t.myRight, kp);
	    }
	}
	
	public T remove(T kp) {
		return removeHelper(myRoot, kp);
	}
	
	public T removeHelper(TreeNode t, T kp) {
		if (t == null) {
			return null;
		} else if (kp.compareTo(t.myItem) == 0) {
			T p = t.myItem;
			T successor = successorHelper(t, kp);
			t.myItem = successor;
			return p;
		} else if (kp.compareTo(t.myItem) < 0) {
	        return putHelperHelper(t.myLeft, kp);
	    } else {
	        return putHelperHelper(t.myRight, kp);
	    }
	}
	
	public T successorHelper(TreeNode t, T kp) {
		if (t.myLeft == null && t.myRight == null) {
			return kp;
		}
		if (t.myLeft != null) {
			return successorHelper(t.myLeft, kp);
		} else {
			return successorHelper(t.myRight, kp);
		}
	}
	
	public T get(T kp) {
		return getHelper(myRoot, kp);
	}
	
	public T getHelper(TreeNode t, T kp) {
		if (t == null) {
			return null;
		} else if (kp.compareTo(t.myItem) == 0) {
			return t.myItem;
		} else if (kp.compareTo(t.myItem) < 0) {
	        return putHelperHelper(t.myLeft, kp);
	    } else {
	        return putHelperHelper(t.myRight, kp);
	    }
	}
	

	public void add(T key) {
		if (contains(key)) {
			return;
		}
		myRoot = addHelper(myRoot, key);
	}
	
	public TreeNode addHelper(TreeNode t, T key) {
		if (t == null) {
			return new TreeNode(key);
		} else if (key.compareTo(t.myItem) < 0) {
			t.size ++;
	        t.myLeft = addHelper(t.myLeft, key);
	        return t;
	    } else {
	    	t.size ++;
	        t.myRight = addHelper(t.myRight, key);
	        return t;
	    }
	}
	
	Comparable kthLargest (int k) {
		return kthHelper(myRoot, k);
	}
	
	Comparable kthHelper (TreeNode t, int k) {
		if (t.myRight !=null && k == t.myRight.size) {
			return t.myItem;
		}
		if (t.size == 2 && k <= 1) {
			if (t.myRight == null) {
				if (k == 0) {
					return t.myItem;
				} else if (k == 1) {
					return t.myLeft.myItem;
				}
			}
			if (t.myLeft == null) {
				if (k == 0) {
					return t.myRight.myItem;
				} else if (k == 1) {
					return t.myItem;
				}
				
			}
		} 
		if (t.size == 3 && k <= 2 && t.myRight != null && t.myLeft !=null) {
			if (k == 0) {
				return t.myRight.myItem;
			} else if (k == 1) {
				return t.myItem;
			} else if (k == 2) {
				return t.myLeft.myItem;
			}
		}
		if ( t.myRight != null && k < t.myRight.size) {
			return kthHelper(t.myRight, k);
		}
		if (t.myRight != null && k > t.myRight.size) {
			return kthHelper(t.myLeft, k - t.myRight.size - 1);
		}
		if (t.myLeft != null && t.myRight == null) {
			return kthHelper(t.myLeft, k);
		}
		return t.myItem;
	}
	
	
	public static void main(String[] args) {
		BinarySearchTree t = new BinarySearchTree();
		t.add(4);
		t.add(2);
		t.add(8);
		t.add(6);
		t.add(9);
		t.add(5);
		t.add(7);
		t.add(3);
		t.add(1);
		t.add(10);
		t.add(11);
		t.add(20);
		t.add(21);
		t.add(30);
		t.add(25);
		t.add(40);
		t.add(50);
		t.add(55);
		t.add(15);
		t.add(37);
		t.add(60);
		t.add(70);
		t.add(27);
		
//		System.out.println(t.myRoot.size);
		for (int i = 0; i < 25; i++) {
			System.out.println(t.kthLargest(i));
		}
	}
}
