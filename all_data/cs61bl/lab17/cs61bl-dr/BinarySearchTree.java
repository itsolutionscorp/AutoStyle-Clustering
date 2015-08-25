import java.util.Iterator;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T>{
	
	
	
	public boolean contains(T key){
		return contains(myRoot, key);		
	}
	
	/**
	 * The following applies when this method is used in MyTreeMap:
	 * @param key
	 * 		A KV Pair
	 * @return
	 * 		Any KV Pair that has the same key (regardless of value).
	 */
	public T get(T key){
		return get(myRoot, key).myItem;
	}
	public void remove(T key){
		if(myRoot != null)
			myRoot = remove(myRoot, key);
	}
	
	private TreeNode remove(TreeNode t, T key){
		if(t == null) return t;
		int diff = key.compareTo(t.myItem);				
		if (diff > 0) t.myRight = remove(t.myRight, key);
		else if (diff < 0) t.myLeft = remove(t.myLeft, key);
		// is the key at the root
		else if (t.isLeaf()) return null;
		else if(t.myLeft == null) return t.myRight;
		else if (t.myRight == null) return t.myLeft;								
		else{
			BinaryTree<T> tree = new BinaryTree<T>(t);
			Iterator<T> iter = tree.iterator();			
			T successor = null;
			while(iter.hasNext()){ 
				if(iter.next().compareTo(key) == 0) break; 
			}
			if(iter.hasNext()) successor = iter.next();
			remove(successor);
			t.myItem = successor;
		}
		return t;

	}
	
	private TreeNode get (TreeNode t, T key){
		int diff = key.compareTo(t.myItem);
		if(diff == 0) return t;
		else if (diff < 0) return get(t.myLeft, key);
		else return get(t.myRight, key);		
	}
	
		
	private boolean contains (TreeNode t, T key){
		if(t == null) return false;
		int diff = key.compareTo(t.myItem);
		if(diff == 0) return true;
		else if (diff < 0) return contains(t.myLeft, key);
		else return contains(t.myRight, key);		
	}
	

	public void add(T key) {
	    myRoot = add(myRoot, key);	
	    myRoot.incrementSize(); 
	}

	private TreeNode add(TreeNode t, T key) {		
	    if (t == null) {	    	
	        return new TreeNode(key);
	    }else if (key.compareTo(t.myItem) == 0){
	    	t.myItem = key;
	    	return t;
	    } else if (key.compareTo(t.myItem) < 0) {
	        t.myLeft = add(t.myLeft, key);
	        t.myLeft.incrementSize();
	        return t;
	    } else {
	        t.myRight = add(t.myRight, key);
	        t.myRight.incrementSize();
	        return t;
	    }
	}
	
	public Comparable<T> kthLargest (int k){
		if(myRoot != null){
			return kthLargest(myRoot, k).myItem;
		}
		return null;
	}
	private TreeNode kthLargest(TreeNode t, int k){		
		int rightSize = 0;
		if(t.myRight != null) rightSize = t.myRight.size();			
		if(rightSize == k) return t;
		if (rightSize > k) return kthLargest(t.myRight, k);															
		else return kthLargest(t.myLeft, k - 1 - rightSize);					
	}

	
	
	
	
}