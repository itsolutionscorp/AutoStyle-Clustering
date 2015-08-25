public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	

	
	public boolean contains(T key){
		return contains(myRoot,key);
	}
	
	public boolean contains (TreeNode t, T key){
    	if(t==null)
    		return false;
    	if(key.equals(t.myItem))
    		return true;
    	if(((Comparable<T>) key).compareTo(t.myItem) < 0){
    		return contains(t.myLeft,key);
    	}
    	return contains(t.myRight,key);
    }
	
	public void add(T key) {
	    myRoot = add(myRoot, key);
	}

	private TreeNode add(TreeNode t, T key) {
		if (t == null) {
	        return new TreeNode(key);
	    }else if(t.myItem.equals(key)){
	    	return t;
	    } else if (key.compareTo(t.myItem) < 0) {
	        t.myLeft = add(t.myLeft, key);
	        return t;
	    } else {
	        t.myRight = add(t.myRight, key);
	        return t;
	    }
	}
	
	public static void main(String[] args) {
		BinarySearchTree<String> t = new BinarySearchTree<String>();
		t.add("C");
		t.add("A");
		t.add("B");
		t.add("E");
		t.add("D");
		t.add("F");
		t.add("D");
		t.add(",");
		System.out.println(t.contains("C"));
		t.printInorder();
		t.printPreorder();
//		t.print();
	}

}
