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
	

	public static void main(String[] args) {
		BinarySearchTree test = new BinarySearchTree();
		
		test.add("C") ;
		test.add("A");
		test.add("B");
		test.add("D");
		test.add("E");
		test.printPreorder();
	}
	
}
