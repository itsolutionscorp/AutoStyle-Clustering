

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	public static void main(String[] args) {
		BinarySearchTree<String> a = new BinarySearchTree<String>();
		a.add("C");
		a.add("A");
		a.add("E");
		a.add("B");
		a.add("D");
		print(a, "Hey there");
	}
	
	public boolean contains(T key) {
		if(myRoot!=null) {
			return contains(myRoot, key);
		}
		return false;
	}
	
    private boolean contains (TreeNode t, T key) {
    	if(t==null) {
    		return false;
    	}
    	if(key.compareTo(t.myItem) == 0) {
    		return true;
    	}
    	else if (key.compareTo(t.myItem) < 0) {
    		return contains(t.myLeft, key);
    	}
    	else {
    		return contains(t.myRight, key);
    	}
    }
    
    public void add(T key) {
    	if(myRoot!=null) {
    		if(!contains(myRoot, key)) {
    			myRoot = add(myRoot, key);
    		}
    	}
    	else {
    		myRoot = add(myRoot, key);
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
}
