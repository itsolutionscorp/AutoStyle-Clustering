import javax.swing.text.html.HTMLDocument.Iterator;


public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	public boolean contains(T key){
		return contains(myRoot, key);
	}
	private boolean contains(TreeNode var, T key){
		if (var == null){
			return false;
		}
		if (var.myItem.compareTo(key) == 0){
				return true;
				}
		if (var.myItem.compareTo(key) < 0){
			return contains(var.myRight, key);
		}
		else{ 
			return contains(var.myLeft, key);
		}		
	}
	
	public void add(T key) {
	    if(!contains(myRoot, key)){
	    	myRoot = add(myRoot, key);
	    	}
	    else{
	    	System.out.println("its in there already ");
	    }
	    
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
	public static void main(String[] arg){
		BinarySearchTree b = new BinarySearchTree();
		b.add("C");
		b.add("A");
		b.add("B");
		b.add("E");
		b.add("D");
		b.printInorder();
		
		BinarySearchTree c = new BinarySearchTree(); //test contains
		c.add("C");
		c.add("A");
		c.add("B");
		c.add("E");
		c.add("D");
		c.add("D");
		c.add("A");
		c.add("B");
		c.printInorder();
	}
	
}
