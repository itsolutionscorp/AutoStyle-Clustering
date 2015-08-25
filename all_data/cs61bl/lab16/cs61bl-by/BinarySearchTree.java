

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T>{
	
	int i = 0;
	
	public void printI(){
		System.out.println(i);
	}
	
	public boolean contains(T key){
		i = 0;
		if (key == null){
			return false;
		}
		if (myRoot != null){
			return this.contains(myRoot, key);
		} else {
			return false;
		}
	}
	
	private boolean contains (TreeNode t, T key){
		i++;
		if (t.myItem.equals(key)){
			return true;
		} else if (t.myItem.compareTo(key) < 0){
			if (t.myRight != null){
				return this.contains(t.myRight, key);
			}
		} else {
			if (t.myLeft != null){
				return this.contains(t.myLeft, key);
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
	        return t;
	    } else {
	        t.myRight = add(t.myRight, key);
	        return t;
	    }
	}
}