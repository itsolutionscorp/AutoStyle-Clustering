
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	public boolean contains(T key){
		if (this.myRoot == null){
			return false;
		}
		return containsHelper(myRoot, key);
	}
	private boolean containsHelper (TreeNode t, T key){
		if (t.myItem == key){
			return true;
		}else if (t.myItem.compareTo(key) < 0){
			if (t.myRight == null){
				return false;
			}
			return containsHelper(t.myRight, key);
		}else if (t.myItem.compareTo(key) > 0){
			if (t.myLeft == null){
				return false;
			}
			return containsHelper(t.myLeft, key);
		}
		return false;
	}
	public void add(T key) {
	    if (!this.contains(key)){
	    	myRoot = add(myRoot, key);
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
	public static void main(String[] args) {
        BinarySearchTree<Character> t = new BinarySearchTree<Character>();
        t.add('C');
        t.add('A');
        t.add('B');
        t.add('E');
        t.add('D');
        t.add('D');
        t.add('F');
        System.out.println(t.contains('F'));
        print(t, "test");
	}
}
