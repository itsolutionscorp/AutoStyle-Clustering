
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
	        return new TreeNode(key);
	    } 
		else if (key.compareTo(t.myItem) < 0) {
	        t.myLeft = add(t.myLeft, key);
	        return t;
	    } 
		else {
	        t.myRight = add(t.myRight, key);
	        return t;
	    }
	}
	
	public static void main (String[] args) {
		BinarySearchTree<String> testTree = new BinarySearchTree<String>();
		testTree.add("A");
		testTree.add("B");
		testTree.add("C");
		testTree.add("D");
		testTree.add("E");
		System.out.println(testTree);
		
	}
}

