import javax.swing.text.html.HTMLDocument.Iterator;


public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	public int size = 0;
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
		size++;
		if(!contains(myRoot, key)){
	    	myRoot = add(myRoot, key, size);
	    	}
	    else{
	    	System.out.println("its in there already ");
	    }
	}

	private TreeNode add(TreeNode t, T key, int size) {
		if (t == null) {
			TreeNode temp = new TreeNode(key);
			temp.mySize = size;
			return temp;
	    } else if (key.compareTo(t.myItem) < 0) {
	        t.myLeft = add(t.myLeft, key, size);
	        return t;
	    } else {
	        t.myRight = add(t.myRight, key, size);
	        return t;
	    }
	}
	
	public Comparable kthLargest(int k){
		int counter = 0;
		if (myRoot != null){
			TreeNode temp = helpCompare(myRoot, k);
			return temp.myItem;
			}
		return null;
		}
	public TreeNode helpCompare(TreeNode t, int k){
		if (t.myLeft == null || t.myRight == null) {
			return t;
		}
	    if (k == t.myRight.mySize - 1) {
	        return helpCompare(t.myRight, k);
	    } else {
	        return helpCompare(t.myLeft, k - t.myRight.mySize);
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
		
		
		BinarySearchTree T = new BinarySearchTree(); ///test printSize, which prints the inorder nodes sizes
		T.add("C");
		T.add("A");
		T.add("B");
		T.add("E");
		T.add("D");
		T.printSize(); //should print out 23154
		System.out.println(T.kthLargest(2));
		
		
	}
}
