
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	public boolean contains(TreeNode t, T key) {
		if (t == null) {
			return false;
		}
		if (t.myItem.equals(key)) {
			return true;
		}
		else if (key.compareTo(t.myItem)< 0) {
			return contains(t.myLeft, key);
		}
		else {
			return contains(t.myRight, key);
		}
	}
	
	public boolean contains(T key) {
		return contains(myRoot, key);
	}
	
	//This is implemented in the case that we were supposed to implement contains as in previous labs. However, contains works fine as is.
//	public boolean contains (T key) {
//		if (myRoot != null) {
//			return myRoot.contains(key);
//		}
//		return false;
//	}
	
	public void add(T key) {
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
	
	public static void main(String[] args) {
		BinarySearchTree<String> t = new BinarySearchTree<String>();
		t.add("c");
		t.add("a");
		t.add("b");
		t.add("e");
		t.add("d");
		print(t, "test");
		System.out.println(t.contains("a"));
		System.out.println(t.contains("ab"));
		System.out.println(t.contains("e"));
		
	}
	
	
}
