
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	public boolean contains(T key) {
		return contains(myRoot, key);
	}
	
	public boolean contains(TreeNode t, T key) {
		if (t!= null) {
			if (t.myItem.equals(key)) {
				return true;
			} 
			if (t.myItem.compareTo(key) > 0) {
				return contains(t.myLeft, key);
			} else {
				return contains(t.myRight, key); 
			}
		} else {
			return false; 
		}
	}
	
	public void add(T key) {
		if (!this.contains(key)) {
		    myRoot = add(myRoot, key);
		} else {
			System.err.println("Tree already contains this value");
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
	
	public static void main (String[] args) {
		BinarySearchTree<String> test = new BinarySearchTree<String>(); 
		test.add("C");
		test.add("A");
		test.add("E");
		test.add("B"); 
		test.add("D");
		test.print();
	}
}
