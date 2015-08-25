
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	/**
	 * @param args
	 */
	
    private boolean contains (T key) {
    	if (myRoot == null) return false;
    	return contains(myRoot, key);
    }
    
    private boolean contains (TreeNode t, T key) {
    	if (t == null) return false;
    	if (t.myItem.equals(key)) return true;
    	if (!t.myItem.equals(key) && t.myLeft == null && t.myRight == null) return false;
    	
    	if (t.myItem.compareTo(key) > 0 && t.myLeft != null) {
    		return contains(t.myLeft, key);
    	} else if (t.myRight != null) {
    		return contains(t.myRight, key);
    	}
    	return false;
    }
    
    public void add(T key) {
        myRoot = add(myRoot, key);
    }
    
    Comparable kthLargest (int k) {
    	if (k >= myRoot.size()) {
    		System.out.println("out of bounds");
    		return null;
    	}
    	if (myRoot == null) {
    		return null;
    	}
    	if (myRoot.myLeft == null && myRoot.myRight == null) {
    		return myRoot.myItem;
    	}
    	if (myRoot.myRight == null) {
    		if (k == 0) {
    			return myRoot.myItem;
    		}
    		BinarySearchTree left = new BinarySearchTree();
    		left.myRoot = (BinaryTree.TreeNode) myRoot.myLeft;
    		return left.kthLargest(k - 1);
    	}
    	if (k == myRoot.myRight.size()) {
    		return myRoot.myItem;
    	}
    	if (k > myRoot.myRight.size()) {
    		BinarySearchTree left = new BinarySearchTree();
    		left.myRoot = (BinaryTree.TreeNode) myRoot.myLeft;
    		return left.kthLargest(k - myRoot.myRight.size() - 1);
    	} else {
    		BinarySearchTree right = new BinarySearchTree();
    		right.myRoot = (BinaryTree.TreeNode) myRoot.myRight;
    		return right.kthLargest(k);
    	}
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
		// TODO Auto-generated method stub
		BinarySearchTree test = new BinarySearchTree();
		test.add(1);
		test.add(2);
		test.add(3);
		test.add(4);
		test.add(5);
		test.printInorder();
		System.out.println(test.kthLargest(3));
	}

}
