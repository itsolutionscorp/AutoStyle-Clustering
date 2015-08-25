public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
    	
        public BinarySearchTree() {
            super();
        }
    	public BinarySearchTree(TreeNode t){
    		super(t);
    	}
    	
    	public boolean contains(T key){
    		if (myRoot ==null){
    			return false;
    		}
   			return myRoot.contains(myRoot,key);
    	}
    	public void add(T key) {
    		if (contains(key)){
    			return;
    		} else {
        		if (myRoot ==null){
        			myRoot = new TreeNode(key);
        		} else {
        			myRoot.add(myRoot, key);    		}
        		}
    	}
}
