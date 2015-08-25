


public class BinarySearchTree < T extends Comparable <T> > extends BinaryTree <T>{

	
	public boolean contains(T key){
		if( this.myRoot == null){
			return false;
		}
		else{
			return contains(myRoot, key);
		}
	}
	
	public boolean contains(TreeNode t, T key){
		T temp= t.myItem;
		
		if (temp.compareTo(key) > 0){
			if (t.myRight == null){
				return false;
			}
			return contains(t.myRight, key);
			}
		else if( temp.compareTo(key) < 0){
			if (t.myLeft == null){
				return false;
			}
			return contains(t.myLeft, key);
		}
		else{
			return true;
		}
	}
	
	public void add(T key){
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
	
	public static void main( String[] args){
		
		
		BinarySearchTree<String> t = new BinarySearchTree<String>();
        t.myRoot = t.new TreeNode("c");
        t.add("a");
        t.add("b");
        t.add("e");
        t.add("d");
        t.print();
        
        
        
	}
	
	
	

}
