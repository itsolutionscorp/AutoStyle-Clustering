public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	public BinarySearchTree(){
		super();
	}

	public boolean contains(T key){
		if(myRoot != null){
			return contains(myRoot, key);
		} else {
			return false;
		}
	}
	
	private boolean contains(TreeNode t, T key){
		if(key.compareTo(t.myItem) < 0){
			return contains(t.myLeft, key);
		} else if(key.compareTo(t.myItem) > 0){
			return contains(t.myRight, key);
		} else{
			return true;
		}
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
			
}