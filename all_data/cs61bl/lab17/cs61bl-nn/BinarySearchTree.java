

public class BinarySearchTree<T extends Comparable> extends BinaryTree<T> {

	/**
	 * @param args
	 */

	public BinarySearchTree() {
		super();
	}

	public BinarySearchTree(TreeNode root) {
		super(root);
	}
	
	public T put(T kp){
		if (myRoot==null){
			myRoot=new TreeNode(kp);
			return null;
		}else if(myRoot.myItem.compareTo(kp)==0){
			return myRoot.myItem;
		}else if(myRoot.myItem.compareTo(kp)>0){
			return putHelper(kp,myRoot.myLeft);
		}else{
			return putHelper(kp,myRoot.myRight);
		}
		
		
	}
	
	private T putHelper(T kp, TreeNode t){
		if( t==null){
			System.out.println("test");
			t=new TreeNode(kp);
			return null;
		}else{
			if(t.myItem.compareTo(kp)==0){
				T temp=t.myItem;
				t.myItem=kp;
				return temp;
			}else if((t.myItem.compareTo(kp)>0)){
				return putHelper(kp,t.myLeft);
			}else{
				return putHelper(kp,t.myRight);
			}
		}
			
		
	}
	
	public T get(T kp){
		return getHelper(kp,myRoot);
	}
	
	public T getHelper(T kp, TreeNode t){
		if(t==null){
			System.out.println("test");
			return null;
		}else{
			if(t.myItem.compareTo(kp)==0){
				System.out.println("test");
				return t.myItem;
			}else if((t.myItem.compareTo(kp)>0)){
				return getHelper(kp,t.myLeft);
			}else{
				return getHelper(kp,t.myRight);
			}
		}
	}

	private boolean containsHelper(TreeNode t, T key) {
		if (t == null)
			return false;
		if (t.myItem.compareTo(key) == 0) {
			return true;
		} else if (t.myItem.compareTo(key) < 0) {
			return containsHelper(t.myLeft, key);
		} else {
			return containsHelper(t.myRight, key);
		}
	}

	public boolean contains(T key) {
		if (myRoot == null) {
			return false;
		} else if (key.compareTo(myRoot.myItem) == 0) {
			return true;
		} else if (key.compareTo(myRoot.myItem) < 0) {
			return containsHelper(myRoot.myLeft, key);
		} else {
			return containsHelper(myRoot.myRight, key);
		}
	}

	public void add(T key) {
		if (myRoot == null) {
			myRoot = new TreeNode(key);
		}
		if (!contains(key)) {
			if (key.compareTo(myRoot.myItem) < 0) {
				if (myRoot.myLeft != null)
					addHelper(key, myRoot.myLeft);
				else
					myRoot.myLeft = new TreeNode(key);
			} else {
				if (myRoot.myRight != null)
					addHelper(key, myRoot.myRight);
				else
					myRoot.myRight = new TreeNode(key);
			}
		}
	}

	public void addHelper(T key, TreeNode t) {

		if (key.compareTo(t.myItem) < 0) {
			if (t.myLeft == null) {
				t.myLeft = new TreeNode(key);
			} else {
				addHelper(key, t.myLeft);
			}
		} else {
			if (t.myRight == null) {

				t.myRight = new TreeNode(key);
			} else {

				addHelper(key, t.myRight);
			}
		}
	}

	public static void main(String[] args) {
		BinarySearchTree tree = new BinarySearchTree();

	}

}
