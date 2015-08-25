import java.util.Iterator;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	public boolean contains(T key) {
		return contains(myRoot, key);
	}
	
	public boolean contains(TreeNode t, T key) {
		if (t == null) {
			return false;
		} else if (t.myItem.compareTo(key) == 0) {
			return true;
		} else if (t.myItem.compareTo(key) > 0) {
			return contains(t.myLeft, key);
		} else {
			return contains(t.myRight, key);
		}
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
	        t.myLeft = add(t.myLeft, key);
	        t.mySize++;
	        return t;
	    } else {
	        t.myRight = add(t.myRight, key);
	        t.mySize++;
	        return t;
	    }
	}
	
	public T put(T key) {
		if (!contains(key)) {
			myRoot = add(myRoot, key);
			return null;
		} else {
			return put(myRoot, key);
		}
	}
	
	private T put(TreeNode t, T key) {
		if (key.compareTo(t.myItem) == 0) {
			T temp = t.myItem;
			t.myItem = key;
			return temp;
		} else if (key.compareTo(t.myItem) < 0) {
			return put(t.myLeft, key);
		} else {
			return put(t.myRight, key);
		}
	}
	
	public T remove(T key) {
		if (!contains(key)) {
			return null;
		} else {
			return remove(myRoot, key);
		}
	}
	
	private T remove(TreeNode t, T key) {
		if (key.compareTo(t.myItem) == 0) {
			myRoot = remove(myRoot, t);
			return t.myItem;
		} else if (key.compareTo(t.myItem) < 0) {
			return remove(t.myLeft, key);
		} else {
			return remove(t.myRight, key);
		}
	}
	
	private TreeNode remove(TreeNode t, TreeNode toBeDeleted) {
		if (t == toBeDeleted) {
			if (!(t.myRight == null || t.myLeft == null)) {
				Iterator<T> iter = iterator();
				T current = iter.next();
				while (current != t.myItem) {
					current = iter.next();
				}
				T next = iter.next();
				remove(next);
				t.myItem = next;
				return t;
			} else if (t.myLeft == null) {
				return t.myRight;
			} else if (t.myRight == null) {
				return t.myLeft;
			} else {
				return null;
			}
		} else {
			t.myRight = remove(t.myRight, toBeDeleted);
			t.myLeft = remove(t.myLeft, toBeDeleted);
			return t;
		}
	}
		
	public T get(T key) {
		if (!contains(key)) {
			return null;
		} else {
			return get(myRoot, key);
		}
	}
	
	private T get(TreeNode t, T key) {
		if (key.compareTo(t.myItem) == 0) {
			return t.myItem;
		} else if (key.compareTo(t.myItem) < 0) {
			return get(t.myLeft, key);
		} else {
			return get(t.myRight, key);
		}
	}
	
	public Comparable<T> kthLargest (int k) {
		if (myRoot != null) {
			return kthLargest(myRoot, k);
		}
		return null;
	}
	
	public Comparable<T> kthLargest(TreeNode t, int k) {
		if (t.myRight == null) {
			if (k == 1) {
				return t.myItem;
			} else {
				return kthLargest(t.myLeft, k-1);
			}
		} else {
			if (k <= t.myRight.mySize) {
				return kthLargest(t.myRight, k);
			} else if (k == 1 + t.myRight.mySize) {
				return t.myItem;
			} else {
				return kthLargest(t.myLeft, k - (1 + t.myRight.mySize));
			}
		}
	}
	
	public static void main(String[] args) {
		BinarySearchTree<String> b = new BinarySearchTree<String>();
		b.add("c");
		b.add("a");
		b.add("b");
		b.add("e");
		b.add("e");
		b.add("d");
		print(b, "hello");
		System.out.println(b.kthLargest(5));
	}

}
