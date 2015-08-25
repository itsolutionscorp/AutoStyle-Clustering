
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	public boolean contains (T key){
		return contains (key, myRoot);
	}
	
	public boolean contains (T key, TreeNode t){
		if (t == null){
			return false; 
		} 
		if (key.compareTo(t.myItem) == 0){
			return true; 
		} else if (key.compareTo(t.myItem)> 0){
				return contains(key, t.myLeft);
		} else {
				return contains (key, t.myRight);
		} 
	}
	
	
	public void add (T key){
		myRoot = add(myRoot, key);
	}
	
	private TreeNode add (TreeNode t, T key){
		if (t == null){
			return new TreeNode(key);
		} else if (t.myItem.compareTo(key)< 0){
			t.myLeft = add (t.myLeft, key);
			return t;
		} else {
			t.myRight = add (t.myRight, key);
			return t; 
		}
	}
	
	public static void main (String[] args){
		BinarySearchTree bst = new BinarySearchTree();
		bst.add("C");
		bst.add("A");
		bst.add("E");
		bst.add("B");
		bst.add("D");
		//print(bst, "example tree");
		System.out.println(bst.contains("C"));
//		
		BinarySearchTree bst1 = new BinarySearchTree();
		bst1.add("F");
		bst1.add("A");
		bst1.add("T");
		bst1.add("B");
		bst1.add("C");
		System.out.println(bst1.contains("B"));
		
	}
}
