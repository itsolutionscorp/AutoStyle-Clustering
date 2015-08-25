import java.util.ArrayList;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	public BinarySearchTree(ArrayList<T> preorder, ArrayList<T> inorder) {
		myRoot = constructHelper(preorder, inorder);
	}

	public TreeNode constructHelper(ArrayList<T> preorder, ArrayList<T> inorder) {
		T item = preorder.get(0);
		int index = inorder.indexOf(item);
		TreeNode left, right;
		if (index == 0) {
			left = null;
		} else {
			ArrayList<T> leftPreorder = sublist(preorder, 1, index + 1);
			ArrayList<T> leftInorder = sublist(inorder, 0, index);
			left = constructHelper(leftPreorder, leftInorder);
		}
		if (index == inorder.size() - 1) {
			right = null;
		} else {
			ArrayList<T> rightPreorder = sublist(preorder, index + 1,
					preorder.size());
			ArrayList<T> rightInorder = sublist(inorder, index + 1,
					inorder.size());
			right = constructHelper(rightPreorder, rightInorder);
		}
		return new TreeNode(item, left, right);
	}

	public ArrayList<T> sublist(ArrayList<T> list, int start, int end) {
		ArrayList<T> sublist = new ArrayList<T>();
		for (int i = start; i < end; i++) {
			sublist.add(list.get(i));
		}
		return sublist;
	}

	public boolean contains(T key) {
		return containsHelper(myRoot, key);
	}

	public boolean containsHelper(TreeNode t, T key) {
		if (t == null) {
			return false;
		} else if (key.compareTo(t.myItem) == 0) {
			return true;
		} else if (key.compareTo(t.myItem) < 0) {
			return containsHelper(t.myLeft, key);
		} else {
			return containsHelper(t.myRight, key);
		}
	}

	public void add(T key) {
		if (!containsHelper(myRoot, key)) {
			myRoot = addHelper(myRoot, key);
		}
	}

	private TreeNode addHelper(TreeNode t, T key) {
		if (t == null) {
			return new TreeNode(key);
		} else if (key.compareTo(t.myItem) < 0) {
			t.myLeft = addHelper(t.myLeft, key);
			return t;
		} else {
			t.myRight = addHelper(t.myRight, key);
			return t;
		}
	}

}
