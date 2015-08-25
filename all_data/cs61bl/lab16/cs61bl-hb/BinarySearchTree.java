
public class BinarySearchTree <T extends Comparable<T>>extends BinaryTree<T>{
	
	public BinarySearchTree(){
		super();
	}
	
	public boolean contains(T key){
		BinarySearchTree<T> myTree = new BinarySearchTree<T>();
		if(myRoot == null){
			return false;
		}else{
			if(myRoot.myItem.compareTo(key)==0){
				return true;
			}else if(myRoot.myItem.compareTo(key)>0){
				myTree.myRoot = myRoot.myLeft;
				return myTree.contains(key);
			}else{
				myTree.myRoot = myRoot.myRight;
				return myTree.contains(key);
			}
		}
	}
	
	public void add(T key) {
		if(!this.contains(key)){
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
	public static void main(String[]args){
		BinarySearchTree<String> t = new BinarySearchTree<String>();
        print(t, "the empty tree");
        t.add("C");
        print(t, "contains C");
        t.add("A");
        t.add("B");
        t.add("E");
        t.add("D");
        print(t, "ABCDE");
        //System.out.println(t.contains("F"));
	}
}
 