import java.util.Iterator;

public class BinarySearchTree<K extends Comparable<K>> extends BinaryTree<K> {

	
	public boolean contains(K key) {
		if (myRoot == null) {
			return false;
		}else if (key.compareTo(myRoot.myItem) == 0) {
			return true;
		}else if (key.compareTo(myRoot.myItem) < 1){
			return LeftContains(key);
		}else {
			return RightContains(key);
		}
	}
	
	public boolean LeftContains(K key) {
		BinaryTree<K> f = new BinaryTree<K> (myRoot.myLeft); 
		Iterator<K> iterated = f.iterator();
		while (iterated.hasNext()) {
			if (key.compareTo(iterated.next())==0) {
				return true;
			}
		}
		return false;
	}
	

	public boolean RightContains(K key) {
		BinaryTree<K> f = new BinaryTree<K> (myRoot.myRight); 
		Iterator<K> iterated = f.iterator();
		while (iterated.hasNext()) {
			K rtn = iterated.next();
			if (key.compareTo(rtn) == 0) {
				return true;
			}
		}
		return false;
	}
	
	public Comparable kthLargest (int k) {
		int soFar = (int) myRoot.myItem;
		
		if (myRoot == null){
			return null;
		}
		if ((k == 0 || k == 1) && myRoot.mySize == 1){
			return soFar;	
			
		} else if (k == myRoot.myRight.mySize ){
			return soFar;
			
		} else if (k > myRoot.myRight.mySize ){
			int reducedK = k-myRoot.myRight.mySize;
			myRoot = myRoot.myLeft;	
			return kthLargest(reducedK);
			
		} else if (k <= myRoot.myRight.mySize ){
			myRoot = myRoot.myRight;
			return kthLargest(k);
			
		}return (int) soFar;
	}
	
	
	
	public void add(K key) {
	    myRoot = add(myRoot, key);
	
	}

	private TreeNode add(TreeNode t, K key) {
	    if (t == null) {
	    	t = new TreeNode(key);
	    	t.mySize +=1;
	        return t;
	    } else if (key.compareTo(t.myItem) < 0) {
	    	t.mySize +=1;
	        t.myLeft = add(t.myLeft, key);
	        return t;
	    } else {
	    	t.mySize +=1;
	        t.myRight = add(t.myRight, key);
	        return t;
	    }
	}
	
	
	public TreeNode remove(TreeNode myRoot, K key) {	
		if (myRoot.myItem.compareTo(key) == 0) {
			if (myRoot.myLeft == null && myRoot.myRight == null) {
				return null;
			} else if (myRoot.myRight == null) {
				return myRoot.myLeft;
			} else if (myRoot.myLeft == null) {
				return myRoot.myRight;
			}
		} else if (myRoot.myItem.compareTo(key) < 1){
			myRoot.myRight = remove(myRoot.myRight, key);
		} else {
			myRoot.myLeft = remove(myRoot.myLeft, key);
		}
		myRoot.mySize --;
		return myRoot;
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
