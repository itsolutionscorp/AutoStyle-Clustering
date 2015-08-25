public class BinarySearchTree <T extends Comparable<T>> extends BinaryTree<T>{

	private boolean containshelper(TreeNode t, T key){
    	if(t == null){
    		return false;
    	}
    	else if(t.myItem.compareTo(key)==0){
    		return true;
    	}
    	else if(t.myItem.compareTo(key)>0){
    		return containshelper(t.myLeft, key);
    	}else{
    		return containshelper(t.myRight, key);
    	}
    }
	
	public boolean contains(T key){
		if(myRoot != null){
			return containshelper(myRoot, key);
		}
		return false;
	}
	
	public void add(T key) {
		if(!contains(key)){
	    myRoot = add(myRoot, key);}
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinarySearchTree<String> a = new BinarySearchTree<String>();
		a.add("C");
		a.add("A");
		a.add("B");
		a.add("E");
		a.add("D");
		a.printPreorder();
		System.out.println(a.contains("A"));
	}

}
