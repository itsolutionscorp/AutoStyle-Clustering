
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	public boolean contains(T key) {
		return containsHelper(myRoot, key);
	}

	public boolean containsHelper(TreeNode t,T key) {
		if (t == null) {
			return false;
		} else if (key.compareTo(t.myItem) == 0) {
			return true;
		} else if (key.compareTo(t.myItem) < 0) {
			return containsHelper(t.myLeft,key);
		} else {
			return containsHelper(t.myRight,key);
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

	public static void main(String[] args) {
		BinarySearchTree<String> a = new BinarySearchTree<String>();
		System.out.println(a.contains("A"));
		a.add("A");
		a.add("C");
		System.out.println(a.myRoot.myLeft);
		System.out.println(a.myRoot.myRight);
		System.out.println(a.contains("D"));
		a.add("E");
		System.out.println(a.myRoot.myRight.myLeft);
		System.out.println(a.myRoot.myRight.myRight);
		a.add("D");
		System.out.println(a.contains("E"));
		System.out.println(a.contains("D"));
		System.out.println(a.contains("A"));
	}

}
