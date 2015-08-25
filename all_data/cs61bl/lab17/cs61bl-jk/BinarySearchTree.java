public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	private boolean containshelper(TreeNode t, T key) {
		if (t == null) {
			return false;
		} else if (t.myItem.compareTo(key) == 0) {
			return true;
		} else if (t.myItem.compareTo(key) > 0) {
			return containshelper(t.myLeft, key);
		} else {
			return containshelper(t.myRight, key);
		}
	}

	public boolean contains(T key) {
		if (myRoot != null) {
			return containshelper(myRoot, key);
		}
		return false;
	}
	
	public T get(T t){
		if (myRoot != null) {
			return gethelper(myRoot, t);
		}
		return null;
	}

	private T gethelper(TreeNode t, T key) {
		if (t == null) {
			return null;
		} else if (t.myItem.compareTo(key) == 0) {
			return t.myItem;
		} else if (t.myItem.compareTo(key) > 0) {
			return gethelper(t.myLeft, key);
		} else {
			return gethelper(t.myRight, key);
		}
	}
	public void add(T key) {
			myRoot = add(myRoot, key);
		
	}
	
	public void delete(T key) {
		myRoot = delete(myRoot, key);
	}
	private TreeNode delete(TreeNode t, T key) {
		if (t == null) {
			return null;
		} 
		int comp = key.compareTo(t.myItem);
		if (comp < 0) {
			t.myLeft = delete (t.myLeft, key);
		} else if (comp > 0) {
			t.myRight = delete(t.myRight, key);
		} else {
			if (t.myRight == null) {
				return t.myLeft;
			}
			if (t.myLeft == null) {
				return t.myRight;
			}
			TreeNode x = t;
			t = min(x.myRight);
			t.myRight = deleteMin(t.myRight);
			t.myLeft = x.myLeft;
			
		}
		return t;
	}
	private TreeNode min(TreeNode t) { 
        if (t.myLeft == null) {
        	return t; 
        } else {
        	return min(t.myLeft); 
        }
    } 
	private TreeNode deleteMin(TreeNode t) {
        if (t.myLeft == null) {
        	return t.myRight;
        }
        t.myLeft = deleteMin(t.myLeft);
        return t;
	}
	private TreeNode add(TreeNode t, T key) {
		if (t == null) {
			return new TreeNode(key);
		} else if (key.compareTo(t.myItem) < 0) {
			t.mySize++;
			t.myLeft = add(t.myLeft, key);
			return t;
		}else if(key.compareTo(t.myItem) == 0) {
			t.myItem= key;
			return t;
		}
		else {
			t.mySize++;
			t.myRight = add(t.myRight, key);
			return t;
		}
	}

	private Comparable kthLargest(int k) {
		if (myRoot != null) {
			return kthlargesthelper(myRoot, k);
		}
		return null;
	}

	private Comparable kthlargesthelper(TreeNode t, int k) {
		if(t.myRight == null && t.myLeft == null){
			return t.myItem;
		}
		if(t.myRight == null){
			if(k == 0){
				return t.myItem;
			}
			else{
				return kthlargesthelper(t.myLeft, k);
			}
			
		}
		if (t.myRight.mySize > k) {
			return kthlargesthelper(t.myRight, k);
		} else if (t.myRight.mySize == k) {
			return t.myItem;
		} else {
			return kthlargesthelper(t.myLeft, k-t.myRight.mySize-1);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinarySearchTree<Integer> a = new BinarySearchTree<Integer>();
		a.add(new Integer(3));
		a.add(new Integer(1));
		a.add(new Integer(2));
		a.add(new Integer(5));
		a.add(new Integer(4));
		a.printPreorder();
		System.out.println(a.kthLargest(4));
	}

}