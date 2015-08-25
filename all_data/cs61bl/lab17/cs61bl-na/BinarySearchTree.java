
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
	    		t.mySize++;
	        t.myLeft = add(t.myLeft, key);	
	        
	        return t;
	    } else {
	    		t.mySize++;
	        t.myRight = add(t.myRight, key);	  
	        return t;
	    }
	}
	
	Comparable<T> kthlargest (int k){
		if(k > myRoot.mySize || k <= 0){
			throw new IllegalArgumentException("k is out of bound.");
		}
		return kthLargest(myRoot, k);
	}
	
	Comparable<T> kthLargest (TreeNode t, int k){ //should we add <T> here?
		
		if(t.myLeft == null && t.myRight ==null){
			return t.myItem;
		}
		int i = 0;	
		int interval = t.mySize - k;
		if(t.myRight != null){
			i = t.myRight.mySize;
		} else {
			i = 0;
		}
		if(k <= i){
			return kthLargest(t.myRight, k);
		} else if(k == i + 1){
			return t.myItem;
		} else {
			return kthLargest(t.myLeft, t.myLeft.mySize - interval);
		}
		
	}
	
	
	
}
