public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
    public BinarySearchTree() {
        super();
    }

    public BinarySearchTree(TreeNode t) {
        super(t);
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
    
	public boolean contains(T key) {
		return contains(myRoot, key);
	}

	private  boolean contains(TreeNode t,T key) {
		if (t == null) {
			return false;
		}
		else if (t.myItem.compareTo(key) == 0) {
			return true;
		}
		else if (t.myItem.compareTo(key) < 0) {
				return contains(t.myRight, key);
		}  else {
			return contains(t.myLeft, key);
		}
	}
	
	public static void main (String[] args) {
		BinarySearchTree a = new BinarySearchTree();
		a.add("C");
		a.add("A");
		a.add("B");
		a.add("E");
		a.add("D");
		print(a, "myTree");
	}
}