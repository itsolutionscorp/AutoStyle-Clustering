public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T>{

	public boolean contains(T key){
		return super.containHelper(key);
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
	
	public static void main(String[] args){
		
		BinarySearchTree<String> t = new BinarySearchTree<String>();
		t.myRoot = t.new TreeNode("C", null, null);
		t.add("A");
		t.add("B");
		t.add("E");
		t.add("D");		
        print(t, "sample tree 3");
		
	}
	
}
