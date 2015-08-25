public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
    
	private boolean contains (T key){
		return containsHelper(myRoot, key);
	}
	
	private boolean containsHelper (TreeNode t, T key){
    	if (t == null){
    		return false;
    	} else if (t.myItem.equals(key)){
    		return true;
    	} else if (t.myItem.compareTo(key) < 0){
    		return containsHelper(t.myLeft, key);
    	} 
    	return containsHelper(t.myRight, key);
    }
    
    public void add(T key) {
        if (!contains(key)){
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
    
    public static void main(String[] args){
    	BinarySearchTree<String> t = new BinarySearchTree<String>();
    	t.add("C");
    	t.add("E");
    	t.add("D");
    	t.add("A");
    	t.add("B");
    	
    	t.printInorder();
    	t.printPreorder();
    }
    
}
