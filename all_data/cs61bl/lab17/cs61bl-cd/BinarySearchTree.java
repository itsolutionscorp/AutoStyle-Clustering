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

		
		System.out.println("mySize: " + ((BinaryTreeNode)myRoot).getSize());
	}
	
	Comparable kthLargest (int k){
		
		return (Comparable) ((BinaryTreeNode)myRoot).kthLargest(k);
	}
	
	
	private class BinaryTreeNode extends TreeNode{

		private int size;
		private boolean foundRightMost;
		
		
		public BinaryTreeNode(T item) {
			super(item);
			size = 1;
			
		}
		
		
		public int getSize(){
			return size;
		}
		
		Comparable kthLargest (int k){
			System.out.println(k);
			BinaryTreeNode nextNode;
			if(myRight != null){
				System.out.println("if");
				System.out.println("myRight's size: "+ ((BinaryTreeNode)myRight).getSize());
				if( k == ((BinaryTreeNode)myRight).getSize()){
					return this.myItem;
				} else if(k < ((BinaryTreeNode)myRight).getSize()){
					nextNode = (BinaryTreeNode)myRight;
				} else {
					nextNode = (BinaryTreeNode)myLeft;
					k = k - ((BinaryTreeNode)myRight).getSize() - 1;
				}
			} else{
				System.out.println("else");
				if(k == 0){
					return this.myItem;	
				} 
				else {
					nextNode = (BinaryTreeNode)myLeft;
					k = k - 1;
				}
			}
			
			return nextNode.kthLargest(k);
		}
		
//		void setSize(){
//			foundRightMost = false;
//			setSizeHelper();
//
//		}
//		
//		int setSizeHelper(){
//			int nextSize = 0;
//			if(myRight!=null){
//				nextSize = ((BinaryTreeNode)myRight).setSizeHelper();
//			} else if(!foundRightMost){
//				treeSize = 1;
//				foundRightMost = true;
//			} 
//			
//			treeSize = nextSize + 1;
//			
//			if(myLeft != null){
//				((BinaryTreeNode)myLeft).treeSize = ((BinaryTreeNode)myLeft).setSizeHelper() + treeSize;
//				nextSize = ((BinaryTreeNode)myLeft).setSizeHelper();
//			}
//
//			return treeSize;
//		}
		
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
			size++;
			if (myItem == null){
				return new BinaryTreeNode(key);
			}
			else if (key.compareTo(myItem) < 0) {
		        if (myLeft == null){
		        	myLeft = new BinaryTreeNode(key);
		        	return this;
		        }
				myLeft = ((BinaryTreeNode) myLeft).add(key);
				return this;
		    } 
			else {
				if (myRight == null){
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