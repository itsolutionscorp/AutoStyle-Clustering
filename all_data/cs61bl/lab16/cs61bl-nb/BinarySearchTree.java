
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	public BinarySearchTree() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public boolean contains(T key){
		if(myRoot == null){
			return false;
		} else {
			return contains(myRoot, key);
		}
	}

	private boolean contains(TreeNode n, T key){
		if(n == null){
			return false;
		}
		if(n.myItem.compareTo(key) == 0){
			return true;
		} else if(n.myItem.compareTo(key) < 0){
			return contains(n.myRight, key);
		} else {
			return contains(n.myLeft, key);
		}
	}
	
	public void add(T key) {
		if(contains(key)){
			return;
		}
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
	
	
	
	
}
