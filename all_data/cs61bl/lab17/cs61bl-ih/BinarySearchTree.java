
public class BinarySearchTree <T extends Comparable<T>> extends BinaryTree<T> {
	
	public boolean contains(T key){
		TreeNode temp = myRoot;
		while (temp != null){
			if (key.compareTo(temp.myItem) == 0){
				return true;
			} else if (key.compareTo(temp.myItem) < 0) {
		        temp = temp.myLeft;
		    } else {
		    	temp = temp.myRight;
		    }
		}
		return false;
	}
	
    //  Make a recursive call to modify a subtree— this call returns the root of the modified subtree. Store the reference to the modified subtree into the appropriate TreeNode field (myLeft or myRight), and return the root of the current tree. 
	
	public void add(T key){
		myRoot = add(myRoot, key);
	}
	
	public static void main(String[] args) {
		BinarySearchTree<String> t = new BinarySearchTree<String>();
        t.add("C");
        t.add("E");
        t.add("D");
        t.add("A");
        t.add("B");
        print(t, "test tree");
        System.out.println(t.kthLargest(0));
        System.out.println(t.kthLargest(1));
        System.out.println(t.kthLargest(2));
        System.out.println(t.kthLargest(3));
        System.out.println(t.kthLargest(4));
        
    }
	
	/**
	 * modified in lab 17
	 */
	private TreeNode add(TreeNode t, T key) {
	    if (t == null) {
	        return new TreeNode(key);
	    } else if (key.compareTo(t.myItem) < 0) {
	    	t.sizeofTree++;
	        t.myLeft = add(t.myLeft, key);
	    } else if (key.compareTo(t.myItem) > 0){
	    	t.sizeofTree++;
	        t.myRight = add(t.myRight, key);
	    }
	    return t;
	}
	
	/**
	 * new in lab17
	 */
	
	Comparable kthLargest (int k){
		return myRoot.kthLargest (k+1);
	}
}
