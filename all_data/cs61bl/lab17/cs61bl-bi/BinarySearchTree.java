import java.util.ArrayList;


public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {	
	
	
	//Contains Method Problem
	public boolean contains(T key) {
		return containsHelper(myRoot, key);	
	}
	

	public boolean containsHelper(TreeNode t, T key){
		if (t == null){
      		return false;
		} else if (key.compareTo(t.myItem) == 0) {
      		return true;
      	} else if (key.compareTo(t.myItem) < 0) {
      		return containsHelper(t.myLeft, key);
      	} else {
      		return containsHelper(t.myRight, key);
      	}
	}
	
	
	//Add Method Problem
	public void add(T key) {
		myRoot = addHelper(myRoot, key);
	}


	private TreeNode addHelper(TreeNode t, T key) {
	    if (t == null) {
	        t = new TreeNode(key, null, null);
	        t.reSize(t.myItem, t.myLeft, t.myRight);
	        return t;
	    } else if (key.compareTo(t.myItem) == 0) {
	    	t.myItem = key;
	    	return t;
	    } else if (key.compareTo(t.myItem) < 0) {
	        t.myLeft = addHelper(t.myLeft, key);
	        t.reSize(t.myItem, t.myLeft, t.myRight);
	        return t;

	    } else {
	    	t.myRight =  addHelper(t.myRight, key);
	    	t.reSize(t.myItem, t.myLeft, t.myRight);
	        return  t;
	    }
	}
	
	//Kth Largest Problem
	Comparable kthLargest (int k){
		if (k >= 0 && k < myRoot.size){
			return kthLargestHelper(myRoot, k);
		}
		return null;
	}
	
	Comparable kthLargestHelper(TreeNode t, int k) {
		int mySize;
		if (t.myLeft == null) {
			mySize = 0;
		} else {
			mySize = t.myLeft.size;
		}
		
		if (k < mySize) {
			return kthLargestHelper(t.myLeft, k);
		} else if (t.myRight != null && k > mySize) {
			return kthLargestHelper(t.myRight, k - 1 - mySize);
		}
		return t.myItem;
	}
	
	//Get the Item associated with the key
	public T get(T key) {
		if (contains(key)) {
			return getHelper(myRoot, key);
		}
		return null;
	}
	
	public T getHelper(TreeNode t, T key) {
	    if (key.compareTo(t.myItem) == 0) {
	    	return t.myItem;
	    } else if (key.compareTo(t.myItem) < 0) {
	    	return getHelper(t.myLeft, key);
	    } else if (key.compareTo(t.myItem) > 0) {
	    	return getHelper(t.myRight, key);
	    } 
	    return null;
	}
	
	//Removes and item from the BST
	public void remove(T key) {
		myRoot = removeHelper(myRoot, key);

	}

	TreeNode removeHelper(TreeNode tree, T key) {
		if (tree == null) {
			return null;
		}
		if (tree.myItem.compareTo(key) == 0) {
			if (tree.myLeft == null && tree.myRight == null) {
				return null;
			} else if (tree.myRight == null) {
				return tree.myLeft;
			} else if (tree.myLeft == null) {
				return tree.myRight;
			} else {
				T smallest = findSmallestTreeNode(tree.myRight);
				tree.myRight = removeHelper(tree.myRight, smallest);
				tree.myItem = smallest;
				tree.size--;
				return tree;
			}	
		} else if (tree.myItem.compareTo(key) < 0) {
			tree.myRight = removeHelper(tree.myRight, key);
			tree.size--;
			return tree;
		} else {
			tree.myLeft = removeHelper(tree.myLeft, key);
			tree.size--;
			return tree;
		}
	}
	
	
	public T findSmallestTreeNode(TreeNode tree) {
	   if (tree.myLeft != null) {
		   return findSmallestTreeNode(tree.myLeft);
	   }
	   return tree.myItem;
	}


	
	public static void main(String[] args) {
		System.out.println("FIREST TEST");
		BinarySearchTree<Integer> t =  new BinarySearchTree<Integer>();
		t.add(new Integer(4));
		t.printInorder();
		t.add(new Integer(5));
		t.printInorder();
		t.add(new Integer(6));
		t.printInorder();
		t.add(new Integer(3));
		t.printInorder();
		t.add(new Integer(1));
		t.printInorder();
		System.out.println(t.contains(5));
		System.out.println(t.contains(3));
		System.out.println(t.contains(4));
		System.out.println(t.contains(7));
		System.out.println(t.contains(-1));
		System.out.println(t.kthLargest(0));
		System.out.println(t.kthLargest(1));	
		System.out.println(t.kthLargest(2));
		System.out.println(t.kthLargest(3));
		System.out.println(t.kthLargest(4));
		
		System.out.println("SECOND TEST");
		BinarySearchTree<Integer> t2 =  new BinarySearchTree<Integer>();
		t2.add(10);
		t2.add(7);
		t2.add(5);
		t2.add(2);
		t2.add(6);
		t2.add(12);
		System.out.println(t2.kthLargest(0));
		System.out.println(t2.kthLargest(1));
		System.out.println(t2.kthLargest(2));
		System.out.println(t2.kthLargest(3));
		System.out.println(t2.kthLargest(4));
		System.out.println(t2.kthLargest(5));
		System.out.println(t2.kthLargest(6));

		System.out.println(t2.toString());
		t2.remove(12);
		
		System.out.println(t2.contains(12));
		System.out.println(t2.contains(10));
		System.out.println(t2.toString());
		
		System.out.println(t2.kthLargest(0));
		System.out.println(t2.kthLargest(1));
		System.out.println(t2.kthLargest(2));
		System.out.println(t2.kthLargest(3));
		System.out.println(t2.kthLargest(4));
		System.out.println(t2.kthLargest(5));
		System.out.println(t2.kthLargest(6));
			
		t2.remove(5);
		System.out.println(t2.kthLargest(0));
		System.out.println(t2.kthLargest(1));
		System.out.println(t2.kthLargest(2));
		System.out.println(t2.kthLargest(3));
		System.out.println(t2.kthLargest(4));
		System.out.println(t2.kthLargest(5));
		System.out.println(t2.kthLargest(6));
		System.out.println(t2.toString());
		
		
	

	}
	
	

	
}
