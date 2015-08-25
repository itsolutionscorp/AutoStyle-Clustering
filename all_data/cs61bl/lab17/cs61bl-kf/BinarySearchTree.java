import java.util.Iterator;
import java.util.Stack;
import java.util.NoSuchElementException;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	public boolean contains(T key){
		if (myRoot == null) {
			return false;
		}
		else if (this.myRoot.myItem.equals(key)) {
    		return true;
    	}
    	else if (key.compareTo(this.myRoot.myItem) < 0) {
    		return this.containsHelper(this.myRoot.myLeft, key);
    	}
    	else {
    		return this.containsHelper(this.myRoot.myRight, key);
    	}      	
	}
	
	private boolean containsHelper (TreeNode t, T key){
		if (t == null) {
			return false;
		}
		else if (t.myItem.equals(key)) {
			return true;
		}
		else if (t.myItem.compareTo(key) < 0) {
			return containsHelper(t.myRight, key);
		}
		else {
			return containsHelper(t.myLeft, key);
			
		}
	}
	
	

	public void add(T key) {
		if (!(this.contains(key))) {
			myRoot = add(myRoot, key);
		}
	}

	public TreeNode add(TreeNode t, T key) {
	    if (t == null) {
	        return new TreeNode(key);
	    } else if (key.compareTo(t.myItem) < 0) {
	    	t.mySize ++;
	        t.myLeft = add(t.myLeft, key);
	        return t;
	    } else {
	    	t.mySize ++;
	        t.myRight = add(t.myRight, key);
	        return t;
	    }
	}
	
	
	public Comparable kthLargest (int k) {
		if (myRoot != null) {		
			return kthLargestHelper(k, myRoot);
		} else {
			return null;
		}
	}
	
	private Comparable kthLargestHelper(int k, TreeNode t) {
		if (t.mySize == 1) {
			return t.myItem;
		} else if (t.myLeft != null && k == t.myLeft.mySize) {
			return t.myItem;
		} else if (t.myLeft == null && k == 0) {
			return t.myItem;
		}
		else if (t.myLeft != null && k < t.myLeft.mySize) {
			return kthLargestHelper(k, t.myLeft);
		} else {
			int subtract = 0;
			if (t.myLeft != null) {
				subtract = t.myLeft.mySize;
			}
			return kthLargestHelper (k - subtract - 1, t.myRight);
		}
	}
	

	public static void main(String[] args) {
		BinarySearchTree test = new BinarySearchTree();
		
		test.add("C") ;
		test.add("A");
		test.add("B");
		test.add("D");
		test.add("E");
		test.add("G");
		test.add("F");
		test.printPreorder();
//		System.out.println(test.myRoot.mySize);
//		System.out.println(test.myRoot.myLeft.mySize);
//		System.out.println(test.myRoot.myRight.mySize);
		System.out.println(test.kthLargest(0));
		System.out.println(test.kthLargest(1));
		System.out.println(test.kthLargest(2));
		System.out.println(test.kthLargest(3));
		System.out.println(test.kthLargest(4));
		System.out.println(test.kthLargest(5));
		System.out.println(test.kthLargest(6));
	}
	
}
