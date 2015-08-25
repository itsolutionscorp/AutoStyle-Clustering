
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	public static void main(String[] args) {
		BinarySearchTree<String> t = new BinarySearchTree<String>();
		t.add("C");
		t.add("A");
		t.add("B");
		t.add("E");
		t.add("D");
		BinarySearchTree.print(t, "Binary Search Tree");
		System.out.println("t contains A? " + t.contains("A"));
		System.out.println("t contains B? " + t.contains("B"));
		System.out.println("t contains C? " + t.contains("C"));
		System.out.println("t contains D? " + t.contains("D"));
		System.out.println("t contains E? " + t.contains("E"));
		System.out.println("t contains F? " + t.contains("F"));
	}
	
	public boolean contains(T key) {
		return contains(myRoot, key);
	}
	
	private boolean contains (TreeNode t, T key) {
		if (t == null) {
			return false;
		}
		else if (key.compareTo(t.myItem) == 0) {
			return true;
		}
		else if (key.compareTo(t.myItem) < 0) {
			return contains(t.myLeft, key);
		}
		else {
			return contains(t.myRight, key);
		}
	}
	
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
	
}
