public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	public boolean contains(T key) {
		TreeNode pointer = myRoot;
		if (key == null || myRoot == null) {
			return false;
		}
		if (myRoot.myLeft == null && myRoot.myLeft == null) {
			return key.compareTo(myRoot.myItem) == 0;
		}
		while (pointer != null) {
			if (key.compareTo(pointer.myItem) < 0) { // key is less than
														// root.myitem
				pointer = pointer.myLeft;
			} else if (key.compareTo(pointer.myItem) == 0) { // key is equal to
				return true; // root.myitem
			} else if (key.compareTo(pointer.myItem) > 0) { // key is greater
															// than
				pointer = pointer.myRight; // root.myitem

			}
		}
		return false;
	}

	public T kthLargest(int k) {
		return myRoot.myItem;
	}

	public void add(T key) {
		if (myRoot == null) {
			myRoot = new TreeNode(key);
			return;
		}

		TreeNode placeHolder = myRoot;
		while (true) {
			if (key.compareTo(placeHolder.myItem) < 0) { // key is less than
				// root.myitem
				if (placeHolder.myLeft != null) {
					placeHolder = placeHolder.myLeft;
				} else {
					placeHolder.myLeft = new TreeNode(key);
					return;
				}
			} else if (key.compareTo(placeHolder.myItem) == 0) { // key is equal
																	// to
				return; // root.myitem
			} else if (key.compareTo(placeHolder.myItem) > 0) { // key is
																// greater
				// than
				if (placeHolder.myRight != null) {
					placeHolder = placeHolder.myRight; // root.myitem
				} else {
					placeHolder.myRight = new TreeNode(key);
					return;
				}
			}
		}

	}

	public static void main(String[] args) {
		BinarySearchTree<String> t = new BinarySearchTree<String>();
		// t.add("C");
		// t.add("A");
		// t.add("B");
		// t.add("E");
		// t.add("D");
		t.add("C");
		t.add("X");
		t.add("Y");
		t.add("A");
		t.add("B");

		t.add("Z");

		System.out.println(t.contains("c"));
	}
	// public boolean contains(T key) {
	// if(this == null){
	// return false;
	// }else if(myRoot.myItem.compareTo(key) == 0){
	// return true;
	// }else if(myRoot.myItem.compareTo(key) < 0){
	// contains(myLeft,key);
	// }else {
	// contains(current.myRight,key);
	// }
	// }

}
