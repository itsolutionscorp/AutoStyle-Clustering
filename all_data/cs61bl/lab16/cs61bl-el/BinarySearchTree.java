public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

//	public boolean contains(T key) {
//		if (myRoot == null) {
//			return false;
//		}
//		if (myRoot.myItem.equals(key)) {
//			return true;
//		} else if (contains(myRoot, key) != null) {
//			return true;
//		}
//		return false;
//	}
	public boolean contains(T key) {
		if (myRoot != null) {
			return contains(myRoot, key); 
		} else return false; 
	}

	private boolean contains(TreeNode t, T key) {
		if (t == null) {
			return false;
		} else if (t.myItem.compareTo(key) == 0) {
			return true; 
		} else if (t.myItem.compareTo(key) < 0) {
			return contains(t.myRight, key); 
		} else if (t.myItem.compareTo(key) > 0) {
			return contains(t.myLeft, key); 
		} else return false; 
	}

//	private TreeNode contains(TreeNode t, T key) {
//		if (t == null) {
//			return null;
//		}
//
//		if (t.myItem == key) {
//			return t;
//		}
//
//		if (key.compareTo(t.myItem) < 0 && t.myLeft != null) {
//			return contains(t.myLeft, key);
//		} else if (key.compareTo(t.myItem) > 0 && t.myRight != null) {
//			return contains(t.myRight, key);
//		} else
//			return null;
//	}

	public void add(T key) {
		myRoot = add(myRoot, key);
	}

	private TreeNode add(TreeNode t, T key) {
		if (t == null) {
			return new TreeNode(key);
		} else if (contains(t, key)) { //ADDED IN BY KELLY 
			return t;
		} else if (key.compareTo(t.myItem) < 0) {
			t.myLeft = add(t.myLeft, key);
			return t;
		} else {
			t.myRight = add(t.myRight, key);
			return t;
		}
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinarySearchTree<String> myTree = new BinarySearchTree<String>();
		myTree.add("D");
		myTree.add("A");
		myTree.add("E");
		myTree.add("B");
		myTree.add("C");
		myTree.add("D");
		myTree.printInorder();
		System.out.println(myTree.contains("A"));
		System.out.println(myTree.contains("Z"));
	}

}
