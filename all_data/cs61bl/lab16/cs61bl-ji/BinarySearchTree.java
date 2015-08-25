
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T>{

	/**
	 * @param args
	 */
	
	
	public static void main(String[] args) {
		
		

	}
	
	public boolean contains (TreeNode t, T key){
		if (t != null){
			if (key.compareTo(t.myItem) == 0){
				return true;
			}
			else if (key.compareTo( t.myItem )<0){
				return contains(t.myLeft, key);
			}
			else {
				return contains (t.myRight, key);
			}
		}
		return false;
	}
	public boolean contains(T key){
		if (myRoot == null){
			return false;
		}else{
			return contains(myRoot, key);
		}
		
	}
	
	
	

	
	
	
	public void add (T key){
		if (!contains(key)){
			myRoot = add(myRoot, key);
		}
		
	
	}
	public TreeNode add(TreeNode t, T key){
		if (t == null){
			return new TreeNode(key);
		}
		else if (key.compareTo(t.myItem) <0){
			t.myLeft = add(t.myLeft, key);
			return t;
		}
		else if (key.compareTo(t.myItem) >0){
			t.myRight = add(t.myRight, key);
			return t;
		}
		else{
			System.out.println("Not added");
		}
		return t;
	}
	
}
