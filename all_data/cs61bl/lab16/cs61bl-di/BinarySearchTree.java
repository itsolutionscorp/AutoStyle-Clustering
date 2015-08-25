import java.util.Iterator;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	public boolean contains(T key) {
		if (myRoot == null) {
			return false;
		}else if (key.equals(myRoot.myItem)) {
			return true;
		}else if (key.compareTo(myRoot.myItem) < 1){
			return LeftContains(key);
		}else {
			return RightContains(key);
		}
	}
	
	public boolean LeftContains(T key) {
		BinaryTree<T> f = new BinaryTree<T> (myRoot.myLeft); 
		Iterator<T> iterated = f.iterator();
		while (iterated.hasNext()) {
			if (key.equals(iterated.next())) {
				return true;
			}
		}
		return false;
	}
	

	public boolean RightContains(T key) {
		BinaryTree<T> f = new BinaryTree<T> (myRoot.myRight); 
		Iterator<T> iterated = f.iterator();
		while (iterated.hasNext()) {
			if (key.equals(iterated.next())) {
				return true;
			}
		}
		return false;
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
	
	public static void main(String[] args) {
		BinarySearchTree<String> b1 = new BinarySearchTree<String>();
		b1.add("c");
		b1.add("a");
		b1.add("e");
		b1.add("d");
		print(b1, "this is my tree");
	}
}

