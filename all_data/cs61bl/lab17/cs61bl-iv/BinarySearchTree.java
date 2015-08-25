
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	public boolean contains(T key) {
		return contains(myRoot, key);
	}
	
	// Helper method
	private boolean contains(TreeNode t, T key) {
		if (t == null) {
			return false;
		} else if (key.compareTo(t.myItem) == 0) {
			return true;
		} else if (key.compareTo(t.myItem) < 0) { // if myItem greater than key
	        return contains(t.myLeft, key);
		} else {
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
	        return t;
	    } else {
	        t.myRight = add(t.myRight, key);
	        return t;
	    }
	}
	
	private Comparable kthLargest(int k) {
		if (myRoot != null) {
			return myRoot.kthLargest(k);
		} else {
			return null;
		}
	}
	
	public static void main(String[] args) {
		BinarySearchTree<String> t = new BinarySearchTree<String>();
		t.add("C");
		t.add("A");
		t.add("E");
		t.add("B");
		t.add("D");
		System.out.println(t.contains("A"));
		System.out.println(t.contains("B"));
		System.out.println(t.contains("C"));
		System.out.println(t.contains("D"));
		System.out.println(t.contains("E"));
		System.out.println(t.contains("F"));
		
		// test kthLargest for t
		System.out.println(t.kthLargest(4));
		System.out.println(t.kthLargest(3));
		System.out.println(t.kthLargest(2));
		System.out.println(t.kthLargest(1));
		System.out.println(t.kthLargest(0));
		
		BinarySearchTree<Integer> t1 = new BinarySearchTree<Integer>();
		t1.add(8);
		t1.add(4);
		t1.add(7);
		t1.add(6);
		System.out.println(t1.kthLargest(3));
		System.out.println(t1.kthLargest(2));
		System.out.println(t1.kthLargest(1));
		System.out.println(t1.kthLargest(0));
		
		BinarySearchTree<Integer> t2 = new BinarySearchTree<Integer>();
		t2.add(4);
		t2.add(2);
		t2.add(1);
		t2.add(3);
		t2.add(6);
		t2.add(5);
		t2.add(7);
		System.out.println(t2.kthLargest(6));
		System.out.println(t2.kthLargest(5));
		System.out.println(t2.kthLargest(4));
		System.out.println(t2.kthLargest(3));
		System.out.println(t2.kthLargest(2));
		System.out.println(t2.kthLargest(1));
		System.out.println(t2.kthLargest(0));
		
		BinarySearchTree<Integer> t3 = new BinarySearchTree<Integer>();
		t3.add(100);
		t3.add(1);
		System.out.println(t3.kthLargest(1));
		System.out.println(t3.kthLargest(0));
		
		BinarySearchTree<Integer> t4 = new BinarySearchTree<Integer>();
		t4.add(8);
		t4.add(15);
		t4.add(9);
		t4.add(12);
		System.out.println(t4.kthLargest(3));
		System.out.println(t4.kthLargest(2));
		System.out.println(t4.kthLargest(1));
		System.out.println(t4.kthLargest(0));
	}
}
