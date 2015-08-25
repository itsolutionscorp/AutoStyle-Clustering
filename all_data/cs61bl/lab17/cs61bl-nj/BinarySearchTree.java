import java.util.Iterator;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	public BinarySearchTree() {
		super();
	}

	public BinarySearchTree(TreeNode t) {
		super(t);
	}

	public boolean contains(T key) {
		return contains(myRoot, key);
	}

	private boolean contains(TreeNode t, T key) {
		if (t == null) {
			return false;
		}
		if (t.myItem.equals(key)) {
			return true;
		}
		if (key.compareTo(t.myItem) < 0) {
			return contains(t.myLeft, key);
		} else {
			return contains(t.myRight, key);
		}
	}

	public void add(T key) {
		myRoot = add(myRoot, key);
		myRoot.sizeUnder++;
	}

	private TreeNode add(TreeNode t, T key) {
		if (t == null) {
			TreeNode result = new TreeNode(key);
			return result;
		} else if (key.compareTo(t.myItem) < 0) {
			t.myLeft = add(t.myLeft, key);
			t.myLeft.sizeUnder++;
			return t;
		} else if (key.equals(t.myItem)) {
			return t;
		} else {
			t.myRight = add(t.myRight, key);
			t.myRight.sizeUnder++;
			return t;
		}
	}

	public Comparable kthLargest(int k) {
		return largestHelper(myRoot,k);
	}
	
	private Comparable largestHelper(TreeNode n, int k) {
		if (n.myRight!=null) {
			if (n.myRight.sizeUnder>k) {
				return largestHelper(n.myRight,k);
			} else if (n.myRight.sizeUnder==k) {
				return n.myItem;
			} else {
				return largestHelper(n.myLeft, k-n.myRight.sizeUnder-1);
			}
		} else {
			if (k==0) {
				return n.myItem;
			} else {
				return largestHelper(n.myLeft, k-1);
			}
		}
	}
	
	public TreeNode get(T key) {
		return getHelper(myRoot, key);
	}
	
	private TreeNode getHelper(TreeNode a, T key) {
		if (a.myItem.equals(key)) {
			return a;
		} else if (a.myItem.compareTo(key)>0) {
			return getHelper(a.myLeft, key);
		} else {
			return getHelper(a.myRight, key);
		}
	}
	public TreeNode delete(T key) {
		return deleteHelper(myRoot, key);
	}
	private TreeNode deleteHelper(TreeNode node, T key) {
		TreeNode toRem = get(key);
		if (toRem.myLeft==null&&toRem.myRight==null) {
			TreeNode parent = parent(toRem);
			if (parent == null) {
				myRoot = null;
				return myRoot;
			}
			if (parent.myLeft==toRem) {
				parent.myLeft = null;
			} else {
				parent.myRight = null;
			}
			return null;
		}
		if (node.myItem.equals(key)) {
			T temp = inorderSuccesor(node);
			if (temp==null) {
				if (node==myRoot) {
					myRoot = myRoot.myLeft;
					return node;
				}
			} 
			delete(inorderSuccesor(node));
			node.myItem = temp;
			return node;
		} else {
			if (node.myItem.compareTo(key)<0) {
				return deleteHelper(node.myRight,key);
			} else {
				return deleteHelper(node.myLeft, key);
			}
		}
	}
	
	private T inorderSuccesor(TreeNode a) {
		Iterator<T> iter = iterator();
		while (iter.hasNext()) {
			T temp = iter.next();
			if (temp.equals(a.myItem)) {
				if (iter.hasNext()) {
					temp = iter.next();
					return temp;
				} else return null;
			}
		}
		return null;
	}
	
	public TreeNode parent(TreeNode a) {
		if (a==myRoot) {
			return null;
		}
		TreeNode temp = myRoot;
		TreeNode left = myRoot.myLeft;
		TreeNode right = myRoot.myRight;
		while (temp!=null) {
			if (a.myItem.compareTo(temp.myItem)<0) {
				if (a.myItem.equals(left.myItem)) {
					return temp;
				} else {
					temp = left;
					left = temp.myLeft;
					right = temp.myRight;
				}
			} else {
				if (a.myItem.equals(right.myItem)) {
					return temp;
				} else {
					temp = right;
					left = temp.myLeft;
					right = temp.myRight;
				}
			}
		}
		return null;
	}
}
