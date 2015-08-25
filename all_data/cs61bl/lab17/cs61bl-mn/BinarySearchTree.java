import java.util.Iterator;


public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	public boolean contains(T key) {
		return contains(myRoot, key);
	}
	
	private boolean contains (TreeNode t, T key) {
		if (t == null) {
			return false;
		} else if (key.compareTo(t.myItem) < 0) {
			return contains(t.myLeft, key);
		} else {
			return contains(t.myRight, key);
		}
	}
	
	
	public void add(T key) {
	    myRoot = add(myRoot, key);
	    myRoot.size += 1;	    
	}

	private TreeNode add(TreeNode t, T key) {
	    if (t == null) {
	        return new TreeNode(key);
	    } else if (key.compareTo(t.myItem) < 0) {
	        t.myLeft = add(t.myLeft, key);
	        t.myLeft.size += 1;
	        return t;
	    } else {
	        t.myRight = add(t.myRight, key);
	        t.myRight.size += 1;
	        return t;
	    }
	}
	
	public void remove(T key) {
		
		myRoot = remove(myRoot, key);
		myRoot.size -= 1;
	}

	private TreeNode remove(TreeNode t, T key) {
		
		
		if (t.myItem.compareTo(key) == -1) {
			t.myRight = remove(t.myRight, key);
			return t.myRight;
		} else if (t.myItem.compareTo(key) == 1) {
			t.myLeft = remove(t.myLeft, key);
			return t.myLeft;
		} else {
			if (t.size == 1) {
				return null;
			} else if (t.size == 2) {
				if (t.myLeft == null) {return t.myRight;}
				else {return t.myLeft;}
			} else {
				Iterator<T> treeIter = iterator();
				T nextKey = treeIter.next();
				while (nextKey.compareTo(key)!=0) {
					nextKey = treeIter.next();
				}
				nextKey = treeIter.next();
				TreeNode result = remove(t, nextKey);
				t.myItem = nextKey;
				return result;
			}
		}
		
	}
	
	public static void main (String[] args) {
		BinarySearchTree<Integer> t = new BinarySearchTree<Integer>();
		t.add(3);
		t.add(1);
		t.add(2);
		t.add(4);
		t.add(5);
		t.printInorder();
		t.printPreorder();
	}
}
