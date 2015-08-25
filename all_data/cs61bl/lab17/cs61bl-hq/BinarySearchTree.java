
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	private int treeSize;
	public TreeNode foundNode;
	public T foundKV;
	public TreeNode succNode;
	public TreeNode succParent;
	public TreeNode parent = null;
	
	public boolean contains (T key) {
		boolean result = false;
		TreeNode point = this.myRoot;
		while (point != null) {
			if (key.compareTo(point.myItem) == 0) {
				result = true;
				foundNode = point;
				foundKV = point.myItem;
				if (foundNode.myRight != null) {
	        		succParent = foundNode;
	        		succNode = foundNode.myRight;
	        		while (succNode.myLeft != null) {
	        			succParent = succNode;
	        			succNode = succNode.myLeft;
	        		}
	        	} else if (foundNode.myLeft != null) {
	        		succParent = foundNode;
	        		succNode = foundNode.myLeft;
	        		while (succNode.myRight != null) {
	        			succParent = succNode;
	        			succNode = succNode.myRight;
	        		}
	        	} else {
	        		succNode = null;
	        	}
				break;
			} else if (key.compareTo(point.myItem) < 0) {
				parent = point;
				point = point.myLeft;
			} else {
				parent = point;
				point = point.myRight;
			}
		}
		return result;
    }
	
	public void add(T key) {
		if (!this.contains(key)) {
	    	myRoot = add(myRoot, key);
		}
	}

	private TreeNode add(TreeNode t, T key) {
	    if (t == null) {
	    	treeSize++;
	        return new TreeNode(key);
	    } else if (key.compareTo(t.myItem) < 0) {
	        t.myLeft = add(t.myLeft, key);
	        return t;
	    } else {
	        t.myRight = add(t.myRight, key);
	        return t;
	    }
	}
	
	private int size() {
		return treeSize;
	}
	
	Comparable kthLargest (int k) {
		if (k > size() - 1) {
			return null;
		}
		return myRoot.kthLargest(k, myRoot);
	}
	
	public static void main(String[] args) {
		BinarySearchTree<Integer> large = new BinarySearchTree<Integer>();
		large.add(4);
		large.add(2);
		large.add(6);
		large.add(1);
		large.add(3);
		large.add(5);
		large.add(7);
		System.out.println(large.kthLargest(0));
		System.out.println(large.kthLargest(1));
		System.out.println(large.kthLargest(2));
		System.out.println(large.kthLargest(3));
		System.out.println(large.kthLargest(4));
		System.out.println(large.kthLargest(5));
		System.out.println(large.kthLargest(6));
		System.out.println(large.kthLargest(7));
	}
}
