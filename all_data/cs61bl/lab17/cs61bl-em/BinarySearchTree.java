
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	public boolean contains (T key) {
		if (myRoot != null) {
			return contains(myRoot, key);
		}
		return false;
	}
	
	private boolean contains (TreeNode t, T key) {
    	if (t == null) {
    		return false;
    	}
    	else if (key.equals(t.myItem)) {
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
	    myRoot = add(myRoot, key);
	}

	private TreeNode add(TreeNode t, T key) {
		if (t == null) {
	        return new TreeNode(key);
	    } else if (key.compareTo(t.myItem) < 0) {
	        t.myLeft = add(t.myLeft, key);
	        t.size ++;
	        return t;
	    } else {
	        t.myRight = add(t.myRight, key);
	        t.size ++;
	        return t;
	    }
	}
	
	public Comparable kthLargest (int k) {
		if (myRoot == null) {
			return null;
		}
		else {
			return kthHelper(myRoot, k);
		}
	}
	public Comparable kthHelper(TreeNode t, int k) {
		if (k == 0) {
			return t.myItem;
		}
		if (k > 0 && k <= t.size) {
			if (t.myRight == null || k > t.myRight.size + 1) {
				if (t.myLeft != null) {
					return kthHelper (t.myLeft, k - (t.myRight.size + 1));
				} 
				else {
					return t.myItem;
				}
			}
			else if (k == t.myRight.size + 1) {
				return t.myItem;
			}
			else if (k <= t.myRight.size) {
				return kthHelper (t.myRight, k);
			}
		}
		return null;
	}
	
	
	public static void main (String args[]) {
		BinarySearchTree tree = new BinarySearchTree();
		tree.add("5");
		tree.add("2");
		tree.add("1");
		tree.add("3");
		tree.add("8");
		tree.add("6");
		tree.add("9");
		tree.add("7");
		tree.print();
		System.out.println(tree.kthLargest(1));
		System.out.println(tree.kthLargest(2));
		System.out.println(tree.kthLargest(3));
		System.out.println(tree.kthLargest(4));
		System.out.println(tree.kthLargest(5));
		System.out.println(tree.kthLargest(6));
		System.out.println(tree.kthLargest(7));
		System.out.println(tree.kthLargest(8));
	}
}
