


public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	public BinarySearchTree() {
		super();
	}
	public BinarySearchTree(TreeNode t) {
		super(t);
	}
	

	public boolean contains(T key) {
		if (myRoot != null) {
			return contains(myRoot, key);
		}
		return false;
		
	}
	private boolean contains(TreeNode t, T key) {
		if (t == null) {
			return false;
		}
		if (key.compareTo((T) t.myItem) == 0) {
			return true;
		}
		if (key.compareTo((T) t.myItem) < 0) {
		
			return contains(t.myLeft, key);
		}
		if (key.compareTo((T) t.myItem) > 0) {
			
			return contains(t.myRight, key);
		}
		return false;
	}
	
	public void add(T key) {
		if (key != null  && contains(key)) {
			return;
		}
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

//	private TreeNode add(TreeNode t, T key) {
//        if (t == null) {
//            return new TreeNode(key);
//        } else if (key.compareTo(t.myItem) < 0) {
//            t.myLeft = add(t.myLeft, key);
//            return t;
//        } else {
//            t.myRight = add(t.myRight, key);
//            return t;
//        }
//    }
	public static void main(String[] args) {
		
		BinarySearchTree<String> testTree = new BinarySearchTree<String>();
		testTree.add("C");
		testTree.printPreorder();
		testTree.add("A");
		testTree.add("B");
		testTree.printPreorder();
		testTree.add("E	");
		testTree.add("D");
		testTree.printPreorder();
		System.out.print(testTree.contains("Z"));
		
		
	}
	
}
