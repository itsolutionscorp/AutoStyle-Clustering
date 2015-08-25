
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	
	public static void main(String [] args){
		BinarySearchTree<String> alphabet = new BinarySearchTree<String>();
		alphabet.add("C");
		alphabet.add("A");
		alphabet.add("B");
		alphabet.add("E");
		alphabet.add("D");
		
		System.out.println(alphabet.contains("C"));
		System.out.println(alphabet.contains("A"));
		System.out.println(alphabet.contains("B"));
		System.out.println(alphabet.contains("E"));
		System.out.println(alphabet.contains("D"));
		System.out.println(alphabet.contains("F"));
		
		alphabet.printPreorder();
	}
	
	
	
	
	public boolean contains(T key){
		if(myRoot != null)
			return contains(myRoot, key);
		return false;
	}
	
	private boolean contains (TreeNode t, T key){
		if(key == null || t == null)
			return false;
		else if((t.myItem).compareTo(key) == 0)
			return true;
		else if((t.myItem).compareTo(key) < 0)
			return contains(t.myRight, key);
		return contains(t.myLeft, key);
	}
	
	/*public void add(T key){
		if(myRoot != null && !contains(key)){ //Checks if this BinarySearchTree doesn't already contain this key
			add(myRoot, key);
		}
		myRoot = new TreeNode(key);
	}
	
	public void add(TreeNode t, T key){
		if(key != null){
			if((t.myItem).compareTo(key) > 0){ //If t.myItem is greater than key, add it to left subtree
				if(t.myLeft != null)
					add(t.myLeft, key);
				else
					t.myLeft = new TreeNode(key);
			}
			else if((t.myItem).compareTo(key) < 0){
				if(t.myRight != null)
					add(t.myRight, key);
				else
					t.myRight = new TreeNode(key);	
			}
				
		}
	}*/
	
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

}
