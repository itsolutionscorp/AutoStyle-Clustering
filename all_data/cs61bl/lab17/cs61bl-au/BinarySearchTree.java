
public class BinarySearchTree <T extends Comparable<T>> extends BinaryTree<T>{

	public BinarySearchTree() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public BinarySearchTree(TreeNode t){
		super(t);
	}
	
	public boolean contains(T key){
		if (myRoot == null) return false;
		return super.contains(key);
	}
    
	public void add(T key){
		if (!contains(key)) super.add(key);
	}

}
