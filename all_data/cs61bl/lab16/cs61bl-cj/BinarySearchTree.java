//import BinaryTree.TreeNode;


public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	public boolean contains(T key) {
		if (myRoot == null) {
			return false;
		}
		return contains(myRoot, key);
	}
	
	private boolean contains (TreeNode t, T key) {
		if (t == null) {
			return false;
		}
		if (t.myItem.compareTo(key) == 0) {
			return true;
		} else if (t.myItem.compareTo(key) < 0) {
			return contains(t.myRight, key);
		} else {
			return contains(t.myLeft, key);
		}
	}

	public void add(T key) {
		if (!contains(key)) {
			myRoot = add(myRoot, key);
		}
	}
	
	private TreeNode add(TreeNode t, T key) {
		if (t == null) {
			return new TreeNode(key);
		} else if (t.myItem.compareTo(key) < 0) {
			t.myRight = add(t.myRight,key);
			return t;
		} else {
			t.myLeft = add(t.myLeft,key);
			return t;
		}
	}
	
	public static void main(String[] args) {
		BinarySearchTree<String> t = new BinarySearchTree<String>();
        t.add("C");
        t.add("A");
        t.add("B");
        t.add("E");
        t.add("D");
        System.out.println(t.contains("E"));
        System.out.println(t.contains("G"));
        t.printPreorder();
        System.out.println("C A B E D ");
        t.add("G");
        t.printPreorder();
        t.add("F");
        t.printPreorder();
        t.add("D");
        t.printPreorder();
	}
}
