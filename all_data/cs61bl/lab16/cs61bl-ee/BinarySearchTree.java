

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	public boolean contains(T key){
		if(myRoot != null){
			return containHelper(myRoot, key);
		}
		else{
			return false;
		}
	}
	public boolean containHelper(TreeNode t, T key){
		if(t == null){
			return false;
		}
		if(key.compareTo(t.myItem) < 0){
			return containHelper(t.myLeft,key);
		}
		else if(key.compareTo(t.myItem) > 0){
			return containHelper(t.myRight,key);
	}
		else{
			return true;
		}
	}
	
	public void add(T key) {
	    if(!this.contains(key)){
		   
		   
	    myRoot = add(myRoot, key);
	    }
	}

	private TreeNode add(TreeNode t, T key) {
	    if (t == null) {
	        return new TreeNode(key);
	    
	    }	       
	    else if (key.compareTo(t.myItem) < 0) {
	        t.myLeft = add(t.myLeft, key);
	        return t;
	    } else {
	        t.myRight = add(t.myRight, key);
	        return t;
	    }
	}
	
	
	public static void main(String[] args){
		BinarySearchTree test = new BinarySearchTree();
		
		test.add("C");
		test.add("A");
		test.add("E");
		test.add("D");
		test.add("B");
		
		
		
		test.printInorder();
	
		System.out.println(test.contains("A"));
		
	}
}
