
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> { 
	//(The BinarySearchTree class shouldn't have any instance variables. Why not?)
	public boolean contains(T key) {
		return containsHelper(myRoot, key);
	}
	
	private boolean containsHelper(TreeNode t, T key) {
		if (t == null) {
			return false; 
		}else if(key.compareTo(t.myItem) == 0) {
			return true; 
		}else if(key.compareTo(t.myItem) < 0) {
			return containsHelper(t.myLeft, key);
		}else{
			return containsHelper(t.myRight, key);
		}
	}
	
	public TreeNode add(T key){
		if(!contains(key)){
			myRoot = add(myRoot, key);
		} else {
			throw new IllegalArgumentException("The tree already contains the key!");
		}	
		return myRoot;
	}
	
	private TreeNode add(TreeNode t, T key){
		if(t == null){
			return new TreeNode(key);
		} else if(key.compareTo(t.myItem) < 0){
			t.size++;
			t.myLeft = add(t.myLeft, key);
			return t;
		} else{
			t.size++;
			t.myRight = add(t.myRight, key);
			return t;
		}
	}
	
	public Comparable kthlargest(int k){
		if(myRoot == null){
			throw new IllegalStateException("e1");
		} else if(k < 0 || k > myRoot.size) {
			throw new IllegalArgumentException("e2");
		} else {
			return kthlargest(myRoot, k);
		}
	}
	
	private Comparable kthlargest(TreeNode t, int k){
		if((t.myLeft == null && t.myRight==null) || k == t.myRight.size + 1)
			return t.myItem;
		else if(k <= t.myRight.size)
			return kthlargest(t.myRight, k); 
		else
			return kthlargest(t.myLeft, k-t.myRight.size-1);
	}
}
