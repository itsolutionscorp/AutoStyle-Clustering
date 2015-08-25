public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	public boolean contains(T key) {
		if (myRoot == null) {
			// even if key == null, still return false
			return false;
		} else if (key == null) {
			// no way to find null in a non-empty tree ???????????????????????/
			return false;
		} else {
			return contains(myRoot, key);
		}
	}
	
	private boolean contains(TreeNode t, T key) {
		// assuming key != null
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
	    myRoot = add(myRoot, key);
	}

	private TreeNode add(TreeNode t, T key) {
	    if (t == null) {
	        return new TreeNode(key);
	    } else if (key.compareTo(t.myItem) == 0) {
	    	return t;
	    } else if (key.compareTo(t.myItem) < 0) {
	        t.myLeft = add(t.myLeft, key);
	        return t;
	    } else {
	        t.myRight = add(t.myRight, key);
	        return t;
	    }
	}
	
	
	
    public static BinaryTree<String> fillSampleTree3() {
        BinaryTree<String> t = new BinarySearchTree<String>();
        t.myRoot = t.new TreeNode("C", t.new TreeNode("A", null, t.new TreeNode("B")), t.new TreeNode("E", t.new TreeNode("D"), null));
        // Note: why t.new TreeNode(...)? NON-STATIC TreeNode class now!
        return t;
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
        BinaryTree<String> t = new BinarySearchTree<String>();
        print(t, "the empty tree");
        
        BinaryTree<String> tree3 = fillSampleTree3();
        print(tree3, "sample tree 3");
        
        // recreate using add
        
        BinarySearchTree<String> tree3_re = new BinarySearchTree<String>();
        tree3_re.add("C");
        tree3_re.add("A");
        tree3_re.add("D");
        tree3_re.add("E");
        tree3_re.add("B");
        tree3_re.add("D");
        print(tree3_re, "sample tree 3 reconstructed");
        
	}

}
