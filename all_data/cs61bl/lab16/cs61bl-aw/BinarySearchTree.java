

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	public boolean contains (T key) {
		if (myRoot == null) {
			return false;
		} else if (myRoot.myItem.equals(key)) {
			return true;
		} else if (myRoot.myItem.compareTo(key) > 0) {
			return this.contains(myRoot.myLeft, key);
		} else {
			return this.contains(myRoot.myRight, key);
		}
	}
	
	public void add(T key) {
		if (!this.contains(key)) {
			super.add(key);
			}
		}
	
	public static void main (String[] args) {
		BinarySearchTree<Integer> myTree = new BinarySearchTree<Integer>();
		myTree.add(10);
		myTree.add(9);
		myTree.add(11);
		myTree.add(8);
		myTree.add(9);
		myTree.add(12);
		System.out.println(myTree.contains(9));
		System.out.println(myTree.contains(2));
		System.out.println("In Preorder:");
		myTree.printPreorder();
		System.out.println("In order:");
		myTree.printInorder();
		
	}
}
