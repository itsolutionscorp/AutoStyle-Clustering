

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T>{
	
	int i = 0;
	
	public void printI(){
		System.out.println(i);
	}
	
	public TreeNode get(T key){
		if (! this.contains(key)){
			return null;
		}
		TreeNode tn = this.myRoot;
		while (! tn.myItem.equals(key)){
			if (tn.myItem.compareTo(key) < 0){
				tn = tn.myRight;
			} else {
				tn = tn.myLeft;
			}
		} return tn;
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
	        t.size+=1;
	        return t;
	    } else {
	        t.myRight = add(t.myRight, key);
	        t.size+=1;
	        return t;
	    }
	}
	
	Comparable kthLargest(int k){
		if (this.myRoot!= null){
			return kthHelper(myRoot, k);
		} else {
			return null;
		}
	}
	
	private Comparable kthHelper(TreeNode t, int k){
		if (k>t.size){
			System.out.println("error"); //TODO
			return null;
		} else if (t.size ==1){
			return t.myItem;
		} else if(t.myRight !=null){
			if (t.myRight.size >= k){
				return kthHelper(t.myRight, k);
			} else if (t.myRight.size == k-1){
				return t.myItem;
			} else {
				return kthHelper(t.myLeft, k - t.myRight.size - 1);
			}
		} else if (k == 1){
			return t.myItem;
		} else { //myRight is in fact null
			return kthHelper(t.myLeft, k-1);
		}
		
	}
}