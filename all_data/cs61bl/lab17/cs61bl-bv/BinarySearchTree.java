
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	public boolean contains (T key) {
		if (myRoot != null) {
			return contains(myRoot, key);
		} else {
			return false;
		}
	}
	
	private boolean contains (TreeNode t, T key) {
    	if (key.compareTo(t.myItem) == 0) {
    		return true;
    	} else if (key.compareTo(t.myItem) < 0) {
    		if (t.myLeft != null) {
    			return contains(t.myLeft, key);
    		}
    	} else if (key.compareTo(t.myItem) > 0) {
    		if (t.myRight != null) {
    			return contains(t.myRight, key);
    		}
    	}
    	return false;
	}
	
	public void add(T key) {
	    if (!contains(key)) {
	    	myRoot = add(myRoot, key);
	    }
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
	
	public T remove(T key) {
		if (contains(key)) {
			myRoot = remove(myRoot, key);
		}
		return null;
	}
	
	public TreeNode remove(TreeNode t, T key) {
		if (t.myItem.compareTo(key) == 0) {
			if (t.myLeft == null && t.myRight == null) {
				return null;
			} else {
				t.myItem = findsuccessor(t).myItem;
				return t;
			}
		} else if (key.compareTo(t.myItem) > 0) {
			if (t.myRight != null) {
				return remove(t.myRight, key);
			}
		} else if (key.compareTo(t.myItem) < 0) {
			if (t.myLeft != null) {
				return remove(t.myLeft, key);
			}
		}
		return null;
	}
	
	public TreeNode findsuccessor(TreeNode t) {
		if (t.myLeft == null && t.myRight == null) {
			return null;
		} else if (t.myLeft != null && t.myRight == null) {
			return t.myLeft;
		} else if (t.myLeft == null && t.myRight != null) {
			return t.myRight;
		} else { //find in order successor
			return helper(t.myRight);
		}
	}
	
	private TreeNode helper(TreeNode t) {
		if (t.myLeft == null) {
			return t;
		} else {
			return helper(t.myLeft);
		}
	}
	
	public Comparable<T> kthLargest(int k) {
		if (myRoot != null) {
			return kthLargest(myRoot, k);
		}
		return null;
	}
	
	private Comparable<T> kthLargest(TreeNode t, int k) {
		if (t.size == 1 && k == 0) {
			return t.myItem;
		}
		else if (t.myRight != null && t.myLeft == null) {
			if (k == t.size-1) {
				return t.myItem;
			} else {
				return kthLargest(t.myRight, k);
			}
		}
		else if (t.myLeft != null && t.myRight == null) {
			if (k == 0) {
				return t.myItem;
			} else {
				return kthLargest(t.myLeft, k - 1);
			}
		}
		else if (t.myLeft != null && t.myRight != null) {
			if (k < t.myRight.size) {
				return kthLargest(t.myRight, k);
			} else if (k > t.myRight.size) {
				return kthLargest(t.myLeft, k - t.myRight.size - 1);
			}
		}
		return t.myItem;
	}
	
	
	public static void main(String[] args) {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
//		b.add("C");
//		b.add("A");
//		b.add("B");
//		b.add("E");
//		b.add("D");

		b.add(7);
		b.add(10);
		b.add(12);
		b.add(11);
		b.add(6);
		b.add(3);
		b.add(1);
		b.add(4);
		b.add(5);
		b.add(13);
		print(b, "test tree");
		for (int i = 0; i < b.myRoot.size; i++) {
			System.out.println(b.kthLargest(i));
		}
		
		//12, 11, 10, 7, 6, 4, 3, 1
		
	}
}
