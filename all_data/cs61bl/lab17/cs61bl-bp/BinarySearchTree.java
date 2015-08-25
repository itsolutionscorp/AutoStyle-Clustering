public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	public boolean contains(T key) {
		if (myRoot != null) {
			return containsHelper(myRoot, key);
		}
		return false;
	}

	private boolean containsHelper(TreeNode t, T key) {
		if (t == null) {
			return false;
		}
		int compare = key.compareTo(t.myItem);
		if (compare < 0) {
			return containsHelper(t.myLeft, key);
		} else if (compare > 0) {
			return containsHelper(t.myRight, key);
		} else {
			return true;
		}
	}

	public void add(T key) {
		if (!contains(key)) {
			myRoot = addHelper(myRoot, key);
		}
	}

	private TreeNode addHelper(TreeNode t, T key) {
		if (t == null) {
			return new TreeNode(key);
		} else if (key.compareTo(t.myItem) < 0) {
			t.myLeft = addHelper(t.myLeft, key);
			t.size++;
			return t;
		} else {
			t.size++;
			t.myRight = addHelper(t.myRight, key);
			return t;
		}
	}

	public T remove(T key) {
		TreeNode n = find(key);
		T temp = n.myItem;
		TreeNode parent = findParent(key);
		if(n.size == 1) {
			if(n == parent.myRight)
				parent.myRight = null;
			else
				parent.myLeft = null;
		}
		else if(n.size == 2) {
			if (n.myLeft != null) {
				if(n == parent.myRight)
					parent.myRight = n.myLeft;
				else
					parent.myLeft = n.myLeft;
			}
			else {
				if(n == parent.myRight)
					parent.myRight = n.myRight;
				else
					parent.myLeft = n.myRight;
			}
		}
		//find in order successor
		else {
			TreeNode succ = findInOrderSucc(n);
			remove(succ.myItem);
			n.myItem = succ.myItem;
		}
		return temp;
		
	}

	public TreeNode find(T key) {
		return findHelper(myRoot, key);
	}

	private TreeNode findHelper(TreeNode t, T key) {
		if (t == null) {
			return null;
		} else if (key.compareTo(t.myItem) < 0) {
			return findHelper(t.myLeft, key);
		} else if (key.compareTo(t.myItem) == 0) {
			return t;
		} else {
			return findHelper(t.myRight, key);
		}
	}
	
	private TreeNode findParent(T key) {
		TreeNode t = myRoot;
		TreeNode prev = null;
		if(t == null) {
			return null;
		}
		else {
			int comp = key.compareTo(t.myItem);
			while(comp != 0) {
				prev = t;
				if(comp < 0)
					t = t.myLeft;
				else
					t = t.myRight;
				comp = key.compareTo(t.myItem);
			}
			return prev;
		}
	}
	//must have right child
	public TreeNode findInOrderSucc(TreeNode t) {
		TreeNode succ = t.myRight;
		while (succ.myLeft != null) {
			succ = succ.myLeft;
		}
		return succ;
	}

	public Comparable<T> kthLargest(int k) {
		if (myRoot != null && k >= 0 && k < myRoot.size) {
			return kthLargestHelper(myRoot, k);
		}
		return null;
	}

	private Comparable<T> kthLargestHelper(TreeNode t, int k) {
		if (k == 0) {
			if (t.myLeft != null) {
				return kthLargestHelper(t.myLeft, k);
			} else {
				return t.myItem;
			}
		} else if (t.myLeft != null && k < t.myLeft.size) {
			return kthLargestHelper(t.myLeft, k);
		} else if (t.myLeft != null && k == t.myLeft.size) {
			return t.myItem;
		} else {
			if (t.myLeft == null) {
				return kthLargestHelper(t.myRight, k - 1);
			} else {
				return kthLargestHelper(t.myRight, k - t.myLeft.size - 1);
			}
		}
	}

	public static void main(String[] args) {
		BinarySearchTree<String> tree = new BinarySearchTree<String>();
		tree.add("E");
		tree.add("A");
		tree.add("B");
		tree.add("R");
		tree.add("F");
		tree.printInorder();
		for (int i = 0; i < tree.myRoot.size; i++) {
			System.out.print(tree.kthLargest(i) + " ");
		}
		System.out.println();
	}
}
