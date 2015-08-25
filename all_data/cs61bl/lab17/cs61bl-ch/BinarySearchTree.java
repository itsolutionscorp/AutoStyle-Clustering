
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	public boolean contains(T key){
		TreeNode node = myRoot;
		while(node != null){
			if(node.myItem.compareTo(key) == 0){
				return true;
			}else if (node.myItem.compareTo(key) < 0){
				node = node.myLeft;
				
			}else{
				node = node.myRight; 
			}
		}
		return false;
	}
	
	
	public void add(T key) {
			myRoot = add(myRoot, key);
			
	}

	private TreeNode add(TreeNode t, T key) {
	    if (t == null) {
	        return new TreeNode(key);
	    } else if (key.compareTo(t.myItem) < 0) {
	        t.myLeft = add(t.myLeft, key);
	        t.size = t.size+1;
	        return t;
	    } else {
	        t.myRight = add(t.myRight, key);
	        t.size = t.size+1;
	        return t;
	    }
	    
	}
	
	@SuppressWarnings("rawtypes")
	public Comparable kthLargest(int k){
		if (myRoot!=null){
			return myRoot.kthLargest(k);
		}
		return 0;
	}
}
