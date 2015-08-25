public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	public boolean contains(T key) {
		return contains(myRoot, key); 
	}
	
	private boolean contains (TreeNode t, T key) {
		if (t == null) {
			return false;
		}
		else if (key.compareTo(t.myItem) == 0) {
			return true;
		}
		else if (key.compareTo(t.myItem) < 0) {
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
	        t.myLeft.size++;
	        return t;
	    } else {
	        t.myRight = add(t.myRight, key);
	        t.myRight.size++;
	        return t;
	    }
	}
	
	public T find(T key) {
		return find(myRoot, key);
	}
	
	private T find(TreeNode t, T key) {
		if (t == null) {
			return null;
		}
		else if (key.compareTo(t.myItem) == 0) {
			return t.myItem;
		} else if (key.compareTo(t.myItem) < 0) {
			return find(t.myLeft, key);
		} else {
			return find(t.myRight, key);
		}
		
	}
	
	public void remove(T key) {
	    myRoot = remove(myRoot, key);
	}

	private TreeNode remove(TreeNode t, T key) {
	    if (t == null) {
	        return t;
	    } else if (key.compareTo(t.myItem) < 0) {
	        t.myLeft = remove(t.myLeft, key);
	        t.myLeft.size--;
	        return t;
	    } else if (key.compareTo(t.myItem) > 0) {
	        t.myRight = add(t.myRight, key);
	        t.myRight.size--;
	        return t;
	    } else {
	    	if (t.myLeft == null) {
	    		return t.myRight;
	    	} else if (t.myRight == null) {
	    		return t.myRight;
	    	} else { 
	    		if (t.myLeft.myRight == null) {
	    			t.myItem = t.myLeft.myItem;
	    			t.myLeft = t.myLeft.myLeft;
	    			return t;
	    		} else {
	    			t.myItem = largest(t.myLeft);
	    			return t;
	    		}
	    	}
	    }
	}
	
	private T largest(TreeNode t) {
		if (t.myRight.myRight == null) {
			T retVal = t.myRight.myItem;
			t.myRight = t.myRight.myLeft;
			return retVal;
		} else {
			return largest(t.myRight);
		}
	}
	
	public Comparable kthLargest (int k) {
		return kthLargest (myRoot, k + 1);
		}
		
	private Comparable kthLargest(TreeNode t, int k) {
		int rsize = 0;
		
		if (t == null) {
			return null;
		}
		
		if (t.myRight != null) {
			rsize = t.myRight.size;
		}
		if ((rsize + 1) == k) {
			return t.myItem;
		}	
		else if ((rsize) >= k) {
			return kthLargest(t.myRight, k);
		} else {
			return kthLargest(t.myLeft, (k - rsize - 1));
		}
	}
	
	
	public static void main(String[] args) {
		BinarySearchTree<String> pent = new BinarySearchTree<String>();
		pent.add("C"); 
		pent.add("A");
		pent.add("B");
		pent.add("E");
		pent.add("D");
		print(pent, "Pent");
		
		BinarySearchTree<Integer> testk = new BinarySearchTree<Integer>();
		testk.add(50);
		testk.add(30);
		testk.add(20);
		testk.add(40);
		testk.add(70);
		testk.add(60);
		testk.add(80);
		print(testk, "test kth largest - left & right branch");
		int k = 7;
		for (int i = 0; i < k; i++) {
		System.out.println(testk.kthLargest(i));
		}
		
		BinarySearchTree<Integer> testk2 = new BinarySearchTree<Integer>();
		testk2.add(50);
		testk2.add(30);
		testk2.add(20);
		testk2.add(40);
		print(testk2, "test kth largest - left branch only");
		int s = 4;
		for (int j = 0; j < s; j++) {
		System.out.println(testk2.kthLargest(j));
		}
		
		BinarySearchTree<Integer> testk3 = new BinarySearchTree<Integer>();
		testk3.add(50);
		testk3.add(70);
		testk3.add(60);
		testk3.add(80);
		print(testk3, "test kth largest - right branch only");
		int x = 4;
		for (int y = 0; y < x; y++) {
		System.out.println(testk3.kthLargest(y));
		}
		
		BinarySearchTree<Integer> testk4 = new BinarySearchTree<Integer>();
		testk4.add(10);
		print(testk4, "test kth largest - root only");
		System.out.println(testk4.kthLargest(0));
	}
	
}
