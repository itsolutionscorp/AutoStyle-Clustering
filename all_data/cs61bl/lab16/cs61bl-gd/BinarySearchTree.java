
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T>{
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
	public boolean contains(T key){
		if(myRoot!=null){
			return contains(myRoot,key);
			}
		
		return false;
	}
	public boolean contains(TreeNode t, T key){
		if(t==null){
			return false;
		}
		if(t.myItem.compareTo(key)==0){
			return true;
		}
		if(t.myItem.compareTo(key)<0){
			return contains(t.myRight,key);
		}
		else{
			return contains(t.myLeft,key);
		}
	}
}