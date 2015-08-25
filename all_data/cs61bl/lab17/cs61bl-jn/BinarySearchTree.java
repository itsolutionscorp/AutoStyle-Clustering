
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T>{
	
	public boolean contains(T key) {
		if (myRoot == null) {
			return false;
		} else {
			return containsH(myRoot, key);
		}
	}

	public boolean containsH(TreeNode t, T key) {
		if (t == null) {
			return false;
		} else if (key.compareTo(t.myItem) == 0) {
			return true;
		} else if (key.compareTo(t.myItem) < 0) {
			return containsH(t.myLeft, key);
		} else {
			return containsH(t.myRight, key);
		}
	}
	
	public void add(T key){
		if (contains(key)){
			return;
		}
		myRoot = addH(myRoot, key);
	}
	
	public TreeNode addH(TreeNode t, T key) {
		if (t == null) {
			return new TreeNode(key);
		} else if (key.compareTo(t.myItem) < 0) {
			t.myLeft = addH(t.myLeft, key);
			t.mySize++;
			return t;
		} else {
			t.myRight = addH(t.myRight, key);
			t.mySize++;
			return t;
		}
	}
	public Comparable kthLargest(int k){
		if (myRoot==null){
			return 0;
		}
		if (k<0){
			return 0;
		}
		if (k>myRoot.mySize){
			return 0;
		}
		k++;
		return largeH(myRoot, k).myItem;
	}
	public TreeNode largeH(TreeNode t, int k){
		if(t.myLeft==null&&t.myRight==null){
			return t;
		}
		if (t.myRight==null){
			if (k==1){
				return t;
			}
			return largeH(t.myLeft, k-1);
		}
		if (t.myLeft==null){
			if(k==t.mySize){
				return t;
			}
			return largeH(t.myRight,k);
		}
		if (k<=t.myRight.mySize){
			return largeH(t.myRight,k);
		}
		if(k==t.myRight.mySize+1){
			return t;
		}
		return largeH(t.myLeft,k-t.myRight.mySize-1);
	}
}
