
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	public boolean contains(T key) {
		return containsHelper(myRoot, key);
	}
	
	public boolean containsHelper(TreeNode t, T key) {
		if (t == null) {
			return false;
		} else if (key == t.myItem) {
			return true;
		} else if (key.compareTo(t.myItem) < 0) {
			return containsHelper(t.myLeft, key);
		} else
			return containsHelper(t.myRight, key);
	}
	
	public void add(T key) {
	    myRoot = add(myRoot, key);
	    myRoot.size++;
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
	
	public Comparable<T> kthLargest(int k) {
			  int R = (this.myRoot.myLeft.size) + 1;
			  if (k == R) {
			   return this.myRoot.myLeft.myItem;
			  }
			  if (k < R) {
				  return kthLargestHelper(k, this.myRoot.myLeft);
			  } else {
				  return kthLargestHelper(k, this.myRoot.myRight);
			  }
	}
	
	public Comparable<T> kthLargestHelper(int k, TreeNode myNode) {
		int R = (this.myRoot.myLeft.size) + 1;
		  if (k == R) {
		   return this.myRoot.myLeft.myItem;
		  }
		  if (k < R) {
			  return kthLargestHelper(k, this.myRoot.myLeft);
		  } else {
			  return kthLargestHelper(k, this.myRoot.myRight);
		  }
	}
	 
	public static void main(String[] args) {
		BinarySearchTree<String> myTree = new BinarySearchTree<String>();
		myTree.add("C");
		myTree.add("A");
		myTree.add("B");
		myTree.add("E");
		myTree.add("D");
		myTree.printInorder();
		//Should return true
		System.out.println(myTree.contains("C"));
		//Should return true
		System.out.println(myTree.contains("D"));
		//Should return false
		System.out.println(myTree.contains("F"));
	}
	
	
}
