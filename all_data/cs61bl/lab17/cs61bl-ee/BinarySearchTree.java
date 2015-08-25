public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T>{

	public boolean contains(T key){
		if (myRoot != null){
			return containsHelper(myRoot, key);
		}
		return false;
	}
	public boolean containsHelper(TreeNode t, T key){
		if (t == null){
			return false;
		}
		else if (key.compareTo(t.myItem) > 0){
			return containsHelper(t.myRight, key);
		}
		else if (key.compareTo(t.myItem) < 0){
			return containsHelper(t.myLeft, key);
		}
		else{
			return true;
		}
	}

	public void add(T key) {
		if (!contains(key)){
	    myRoot = add(myRoot, key);
		}
	}

	private TreeNode add(TreeNode t, T key) {

	    if (t == null) {
	        return new TreeNode(key);
	    } else if (key.compareTo(t.myItem) < 0) {
	        t.myLeft = add(t.myLeft, key);
            t.size += 1;
	        return t;
	    } else {
	        t.myRight = add(t.myRight, key);
            t.size += 1;
	        return t;
	    }
	}

    public Comparable kthLargest(int k) {
        if (k > myRoot.size || k <= 0) {
            System.out.println("Invalid input k");
            return null;
        }

        TreeNode kLargestNode = kthLargest(myRoot, k);
        return kLargestNode.myItem;
    }

    private TreeNode kthLargest(TreeNode t, int k) {
        int leftCount = 0;
        if (t.myLeft != null) {
            leftCount = t.myLeft.size;
        }

        int nLargest = t.size - leftCount; // including the current root
        if (nLargest == k) {
            return t;
        } else if (nLargest < k) {
            return kthLargest(t.myLeft, k - nLargest);
        } else {
            return kthLargest(t.myRight, k);
        }
    }

	public static void main(String arg[]){
		BinarySearchTree<Integer> t = new BinarySearchTree<Integer>();
        t.add(1);
        t.add(2);
        t.add(3);
        t.add(4);
        t.add(5);

        System.out.println(t.kthLargest(3));
//		System.out.println(t.myRoot.myItem);
//		System.out.println(t.myRoot.myRight.myItem);
//		System.out.println(t.myRoot.myRight.myRight.myItem);
//		System.out.println(t.contains(6));
//		System.out.println(t.contains(4));
//		System.out.println(t.contains(7));
	}
}
