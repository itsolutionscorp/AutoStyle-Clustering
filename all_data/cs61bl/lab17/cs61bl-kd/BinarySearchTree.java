
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	public boolean contains(T key) {
    	if (myRoot == null) {
    		return false;
    	}
    	else {
    		return containsHelp(myRoot, key);
    	}
    }
	
	private boolean containsHelp(TreeNode t, T key) {
		if (t == null) {
			return false;
		}
		if (t.myItem.equals(key)) {
			return true;
		}
		if (key.compareTo(t.myItem)>0) {
			return containsHelp(t.myRight, key);
		}
		if (key.compareTo(t.myItem)<0) {
			return containsHelp(t.myLeft, key);
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
			TreeNode myTree = new TreeNode(key);
			myTree.mySize = 1;
	        return myTree;
	    } 
		else if (key.compareTo(t.myItem) < 0) {
	        t.myLeft = add(t.myLeft, key);
	        t.mySize++;
	        return t;
	    } 
		else {
	        t.myRight = add(t.myRight, key);
	        t.mySize++;
	        return t;
	    }
	}
	
	private Comparable kthLargest (int k) {
		return kHelp(k, myRoot);
	}
	
	private Comparable kHelp (int k, TreeNode t) {
		if (t.myRight != null) {
			if (t.myRight.mySize > k) {
				return kHelp(k, t.myRight);
			}
			else if (t.myRight.mySize == k) {
				return min(t.myRight).myItem;
			}
			else if (k == t.myRight.mySize + 1) {
				return t.myItem;
			}
			else {
				return kHelp(k-(t.myRight.mySize+1), t.myLeft);
			}
		}
		else {
			return kHelp(k - 1, t.myLeft);
		}
	}
	
	private TreeNode min (TreeNode t) {
		if (t.myLeft == null) {
			return t;
		}
		else {
			return min(t.myLeft);
		}
	}
	
	public void remove(T tee) {
		myRoot = remHelp(tee, myRoot);
	}
	
	private TreeNode remHelp(T tee, TreeNode t) {
		if (t == null) {
			return null;
		}
		if (t.myItem.equals(tee)) {
			if (t.myRight == null && t.myLeft == null) {
				return null;
			}
			else if (t.myLeft == null) {
				return t.myRight;
			} 
			else if (t.myRight == null) {
				return t.myLeft;
			}
			else {
				TreeNode successor = successor(t);
				remove(successor.myItem);
				t.myItem = successor.myItem;
			}
		}
		if (tee.compareTo(t.myItem)>0) {
			t.myRight = remHelp(tee, t.myRight);
			return t;
		}
		if (tee.compareTo(t.myItem)<0) {
			t.myLeft = remHelp(tee, t.myLeft);
			return t;
		}
		return null;
	}
	
	private TreeNode successor(TreeNode t) {
		if (t.myRight != null) {
			return min(t.myRight);
		}
		else {
			return null;
		}
	}
	
	public static void main (String[] args) {
		BinarySearchTree<String> testTree = new BinarySearchTree<String>();
		testTree.add("A");
		testTree.add("B");
		testTree.add("C");
		testTree.add("D");
		testTree.add("E");
		BinarySearchTree.print(testTree, "test");
		System.out.println(testTree.kthLargest(2));
		System.out.println(testTree.kthLargest(4));
		
		
		BinarySearchTree<Integer> test1 = new BinarySearchTree<Integer>();
		test1.add(5);
		test1.add(3);
		test1.add(2);
		test1.add(4);
		test1.add(1);
		test1.add(7);
		test1.add(6);
		test1.add(9);
		test1.add(8);
		test1.add(10);
		System.out.println(test1.kthLargest(2));
		System.out.println(test1.kthLargest(6));
		System.out.println(test1.kthLargest(3));
		System.out.println(test1.kthLargest(7));
	}
}

