

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	public boolean contains(T key) {
		return contains(myRoot, key);
	}

	private boolean contains(TreeNode t, T key) {
		if (t == null)
			return false;
		if (t.myItem.equals(key))
			return true;
		if (t.myItem.compareTo(key) > 0) {
			return contains(t.myLeft, key);
		} else {
			return contains(t.myRight, key);
		}

	}

	public void add(T key) {
		if (contains(key)) return;
		if(myRoot == null) {
			myRoot = new TreeNode(key);
		} else {
			add(myRoot, key);
		}

	}

	public void add(TreeNode t, T key) {
		if (t.myItem.compareTo(key) > 0) {
			if (t.myLeft == null) {
				t.myLeft = new TreeNode(key);
			} else {
				add(t.myLeft, key);
			}
		} else {
			if (t.myRight == null) {
				t.myRight = new TreeNode(key);
			} else {
				add(t.myRight, key);

			}
		}
	}

	public static void main(String[] args) {
		BinarySearchTree<String> tree = new BinarySearchTree<String>();
		//tree.myRoot = tree.new TreeNode(5, tree.new TreeNode(3),
			//	tree.new TreeNode(9, tree.new TreeNode(6), null));
		//System.out.println(tree.contains(9));
		tree.add("C");
		tree.add("A");
		tree.add("B");
		tree.add("E");
		tree.add("D");
		System.out.println(tree.myRoot.myItem);
	}
}
