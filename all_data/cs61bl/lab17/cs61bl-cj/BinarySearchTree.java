
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	public boolean contains(T key) {
		if (myRoot == null) {
			return false;
		}
		return contains(myRoot, key);
	}
	
	private boolean contains (TreeNode t, T key) {
		if (t == null) {
			return false;
		}
		if (t.myItem.compareTo(key) == 0) {
			return true;
		} else if (t.myItem.compareTo(key) < 0) {
			return contains(t.myRight, key);
		} else {
			return contains(t.myLeft, key);
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
		} else if (t.myItem.compareTo(key) < 0) {
			t.myRight = add(t.myRight,key);
			t.subtreeSize++;
			return t;
		} else {
			t.myLeft = add(t.myLeft,key);
			t.subtreeSize++;
			return t;
		}
	}
	
	Comparable kthLargest (TreeNode t, int k) {
		int rightSize = 0;
		if (t.myRight != null) {
			rightSize = subtreeSize(t.myRight);
		}
		if ( rightSize == k) {
			return t.myItem;
		} else if (k <= rightSize) { 
			return kthLargest(t.myRight, k);
		} else {
			return kthLargest(t.myLeft, k - rightSize - 1);
		}
	}
	
	Comparable kthLargest (int k) {
		return kthLargest(myRoot, k);
	}
	
	private int subtreeSize(TreeNode t) {
		int size = 0;
    	if(t == null) {
    		return 0;
    	} else {
    		size = 1 + subtreeSize(t.myLeft) + subtreeSize(t.myRight);
    	} 
    	return size;
	}
	
	public static void main(String[] args) {
		BinarySearchTree<String> t = new BinarySearchTree<String>();
        t.add("C");
        t.add("A");
        t.add("E");
        t.add("B");
        t.add("D");    
        t.add("F");
        t.add("T");
        t.printPreorder();
        System.out.println(t.kthLargest(2));
        System.out.println(t.kthLargest(0));
        System.out.println(t.kthLargest(1));
        System.out.println(t.kthLargest(6));
        System.out.println(t.kthLargest(5));
        
	}
}