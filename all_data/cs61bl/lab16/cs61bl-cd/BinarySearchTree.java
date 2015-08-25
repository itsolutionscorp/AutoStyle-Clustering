public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	public boolean contains(T key){
		return ((BinaryTreeNode) myRoot).contains(key);
	}
	
	public void add(T key){
		if (myRoot == null){
			myRoot = new BinaryTreeNode(key);
		}
		else{
			myRoot = ((BinaryTreeNode) myRoot).add(key);
		}
	}
	
	private class BinaryTreeNode extends TreeNode{

		public BinaryTreeNode(T item) {
			super(item);
		}
		
		public boolean contains(T key){
			if (myItem == null){
				return false;
			}
			if (key.compareTo((T) myItem) < 0){
				if (myLeft == null){
					return false; 
				}
				return ((BinaryTreeNode) myLeft).contains(key);
			}
			else if (key.compareTo((T) myItem) == 0){
				return true;
			}
			else{
				if (myRight == null){
					return false; 
				}
				return ((BinaryTreeNode) myRight).contains(key);
			}
		}
		
		public TreeNode add(T key){
			if (myItem == null){
				return new BinaryTreeNode(key);
			}
			else if (key.compareTo(myItem) < 0) {
		        if (myLeft == null){
		        	System.out.println("in left null");
		        	myLeft = new BinaryTreeNode(key);
		        	return this;
		        }
		        System.out.println("in left not null");
				myLeft = ((BinaryTreeNode) myLeft).add(key);
				return this;
		    } 
			else {
				System.out.println("inserting in to right");
				if (myRight == null){
					System.out.println("in right null");
		        	myRight = new BinaryTreeNode(key);
		        	return this;
		        }
				myRight = ((BinaryTreeNode) myRight).add(key);
				return this;
		    }
		}
	}
	
	public static void main(String[] args){
		BinarySearchTree<String> myTree = new BinarySearchTree<String>();
		myTree.add("C");
		myTree.printPreorder();
		myTree.add("A");
		myTree.printPreorder();
		myTree.add("B");
		myTree.printPreorder();
		myTree.add("E");
		myTree.printPreorder();
		myTree.add("D");
		myTree.printPreorder();
	}
}