
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T>{
	
	public BinarySearchTree(){
		super();
	}

	
	public boolean contains(T key){
		if(super.myRoot!= null){
			return containsHelper(super.myRoot, key);
		}
		return false;
	}
	
	
	private boolean containsHelper (TreeNode t, T key){
		if(key.compareTo(t.myItem)== 0){
			return true;
		}
		if(key.compareTo(t.myItem)> 0){
			if(t.myRight != null)
			return containsHelper(t.myRight, key);
		}
		if(key.compareTo(t.myItem)< 0){
			if(t.myLeft != null)
			return containsHelper(t.myLeft, key);
		}
		return false;
		
	}
	
//	public void add(T key){
//		if(!contains(key)){
//			myRoot = addHelper(super.myRoot, key);
//		}
//		
//	}
//	
//	
//	private TreeNode addHelper (TreeNode t, T key){
//		if(t == null){
//			t = new TreeNode(key);
//		}else{
//			if(key.compareTo(t.myItem) > 0){
//				 t.myRight = addHelper(t.myRight, key);
//			}
//			if(key.compareTo(t.myItem) < 0){
//				t.myLeft = addHelper(t.myLeft, key);
//			}
//		}
//		return t;
//	}
	
	
	public void add(T key) {
	    myRoot = add(myRoot, key);
	}

	private TreeNode add(TreeNode t, T key) {
	    if (t == null) {
	        return new TreeNode(key);
	    } else if (key.compareTo(t.myItem) < 0) {
	        t.myLeft = add(t.myLeft, key);
	        return t;
	    } else if (key.compareTo(t.myItem) > 0) { 
	        t.myRight = add(t.myRight, key);
	        return t;
	    }
	    return t;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
