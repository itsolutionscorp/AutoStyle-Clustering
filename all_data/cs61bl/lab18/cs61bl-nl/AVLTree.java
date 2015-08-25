
public class AVLTree {

	private TreeNode myRoot;
	private final TreeNode theEmptyTreeNode = new EmptyTreeNode();

	/**
	 * Construct an AVL tree with one item.
	 */
	public AVLTree(int item) {
		myRoot = new NonEmptyTreeNode(item);
	}

	/**
	 * Returns whether the AVL tree contains the given item. This isn't useful
	 * for writing the insert method, but it's useful for testing. You'll want
	 * to make sure that after rotations, you don't accidentally delete things
	 * from your tree.
	 */
	public boolean contains(int item) {
		return myRoot.contains(item);
	}

	/**
	 * Inserts a new item into the AVL tree. Should maintain both the BST search
	 * order invariants and the almost balanced height invariant.
	 */
	public void insert(int item) {
		myRoot.insert(item);
	}

	/**
	 * Returns whether this tree satisfies the AVL tree almost balanced
	 * requirement.
	 */
	public boolean isAlmostBalanced() {
		return myRoot.isAlmostBalanced();
	}

	/**
	 * Prints out the AVL tree sideways. May be helpful to visualize your tree
	 * to check if it's balanced.
	 */
	@Override
	public String toString() {
		return myRoot.toString();
	}

	private abstract class TreeNode {

		/**
		 * Returns the item that belongs to this node.
		 */
		public abstract int item();

		/**
		 * Returns whether the item is contained in the subtree rooted at this
		 * node.
		 */
		public abstract boolean contains(int item);

		/**
		 * Inserts an item into the subtree rooted at this node.
		 */
		public abstract void insert(int item);

		/**
		 * Returns the balance factor of this node. Useful as a helper method to
		 * insert.
		 */
		public abstract int balanceFactor();

		/**
		 * Returns the height of the subtree with root t.
		 */
		public abstract int height();

		/**
		 * Returns whether this subtree satisfies the AVL tree almost balanced
		 * requirement.
		 */
		public abstract boolean isAlmostBalanced();

		/**
		 * Returns whether this node is an empty node (does not represent an
		 * item).
		 */
		public abstract boolean isEmpty();

		/**
		 * Returns the parent of this node in the tree.
		 */
		public abstract TreeNode parent();

		/**
		 * Returns the left child of this node.
		 */
		public abstract TreeNode left();

		/**
		 * Returns the right child of this node.
		 */
		public abstract TreeNode right();

		/**
		 * Sets the left child of this node.
		 */
		public abstract void setLeft(TreeNode t);

		/**
		 * Sets the right child of this node.
		 */
		public abstract void setRight(TreeNode t);

		/**
		 * Sets the parent of this node.
		 */
		public abstract void setParent(TreeNode t);

		/*
		 * TODO Add more abstract methods here if you like. By putting helper
		 * methods in TreeNode, you can avoid doing any casting in this problem.
		 */
		public abstract void checkRotate(TreeNode t);
		
		public abstract TreeNode findUnbalancedTreeNode(TreeNode t);

		@Override
		public String toString() {
			return toStringHelper("");
		}

		/** Recursive helper method for toString. */
		private String toStringHelper(String soFar) {
			if (isEmpty()) {
				return "";
			} else {
				String toReturn = "";
				toReturn += right().toStringHelper("    " + soFar);
				if (!right().isEmpty()) {
					toReturn += soFar + " /";
				}
				toReturn += "\n" + soFar + item() + "\n";
				if (!left().isEmpty()) {
					toReturn += soFar + " \\";
				}
				toReturn += left().toStringHelper("    " + soFar);
				return toReturn;
			}
		}

		public void rotateLeft() {
			// TODO Auto-generated method stub
			
		}

		public void rotateRight() {
			// TODO Auto-generated method stub
			
		}

	}

	private class NonEmptyTreeNode extends TreeNode {

		private int myItem;
		private TreeNode myLeft;
		private TreeNode myRight;
		private TreeNode myParent;

		private NonEmptyTreeNode(int item) {
			this(item, theEmptyTreeNode);
		}

		private NonEmptyTreeNode(int item, TreeNode parent) {
			myItem = item;
			myLeft = theEmptyTreeNode;
			myRight = theEmptyTreeNode;
			myParent = parent;
		}

		/**
		 * Inserts the item into the subtree rooted at this node.
		 * 
		 * Hints: Be sure to take advantage of the balanceFactor() and
		 * isAlmostBalanced() helper methods, which are already written for you!
		 * They will help you decide when and what rotations to apply.
		 * 
		 * You'll also probably want to write helper methods rotateRight() and
		 * rotateLeft().
		 */
		@Override
		public void insert(int item) {
			// TODO Your code here!
			// insert item into the tree rooted by this Node
			
			if (item < item()) {
				if (left().isEmpty()) {					
					setLeft(new NonEmptyTreeNode(item));
					//start rotation if find any parents that are unbalanced

					rotate(this.findUnbalancedTreeNode(this));				
				} else {
					left().insert(item);
				}
			} else if (item > item()) {

				if (right().isEmpty()) {

					setRight(new NonEmptyTreeNode(item));
					//start rotation if find any parents that are unbalanced
					rotate(this.findUnbalancedTreeNode(this));		
				} else {
					right().insert(item);
				}
			} else {
				// item is equal ,do not insert then
			}

		}
		
		@Override
		public TreeNode findUnbalancedTreeNode(TreeNode t) {
			// TODO Auto-generated method stub
			
			if(t.isEmpty())
				return new EmptyTreeNode();
			if(!t.isAlmostBalanced()){
				return t;
			}else{				
				return t.findUnbalancedTreeNode(t.parent());
			}
		}
		
		

		/**
		 * recursively check the balance state of the ParentNode for the given
		 * TreeNode.if found a node unbalanced then apply one of the two
		 * rotation cases on it
		 * 
		 * @param t
		 */
		public void rotate(TreeNode t) {
			
			if (t.isEmpty()) {
				// this handle the case when the para passed
				// by t is empty
				return;
			}
//			System.out.println("checkRotate"+t.item());
			if (t.balanceFactor()<-1) {
				// right>left	
				
				TreeNode rightChild=t.right();
				if(rightChild.left().height()>rightChild.right().height()){
					//outer, do rightrotate on itsChild self then leftRotate
					rightChild.rotateRight();
					t.rotateLeft();
					
				}else{
					System.out.println("test");
					//inner , do a leftRotate
					t.rotateLeft();
				}
				
								
			} else if(t.balanceFactor()>1){
				// left>right
				TreeNode leftChild=t.left();
				if(leftChild.left().height()>leftChild.right().height()){
					//inner, do rightrotate on itself
					t.rotateRight();
					
				}else{
					//outer , do leftrotate on child and then rightrotate on itself
					leftChild.rotateLeft();
					t.rotateRight();
				}
				
			}
			
		}
		//left>right
		public void rotateRight() {
			TreeNode leftTemp=this.left();
			if(!this.parent().isEmpty()){		
				if(this.parent().item()>this.myItem)
				this.parent().setLeft(leftTemp);
				else  this.parent().setRight(leftTemp);
			}
			setLeft(leftTemp.right());
			//if do don't reset the parent, you might 
			//end up get a stackoverflow
			leftTemp.setParent(this.parent());// be careful with all these pointers !!
			leftTemp.setRight(this);		
			if(this==myRoot){
				myRoot=leftTemp;
			}
			
		}
		// right>left
		public void rotateLeft() {
			//System.out.println("left");
			TreeNode rightTemp=this.right();
			if(!this.parent().isEmpty()){
				if(this.parent().item()>this.myItem)
					this.parent().setLeft(rightTemp);
				else  this.parent().setRight(rightTemp);
			}
			setRight(rightTemp.left());
			rightTemp.setParent(this.parent());
			rightTemp.setLeft(this);		
			if(this==myRoot){
				myRoot=rightTemp;
			}
		}

		@Override
		public boolean isAlmostBalanced() {
			if (Math.abs(balanceFactor()) > 1) {
				return false;
			} else if (!left().isAlmostBalanced()) {
				return false;
			} else if (!right().isAlmostBalanced()) {
				return false;
			} else {
				return true;
			}
		}

		@Override
		public int balanceFactor() {
			return left().height() - right().height();
		}

		@Override
		public int height() {
			return 1 + Math.max(left().height(), right().height());
		}

		@Override
		public boolean isEmpty() {
			return false;
		}

		@Override
		public TreeNode parent() {
			return myParent;
		}

		@Override
		public TreeNode left() {
			return myLeft;
		}

		@Override
		public TreeNode right() {
			return myRight;
		}

		@Override
		public int item() {
			return myItem;
		}

		@Override
		public boolean contains(int item) {
			if (item() == item) {
				return true;
			} else if (item > item()) {
				return right().contains(item);
			} else {
				return left().contains(item);
			}
		}

		@Override
		public void setLeft(TreeNode t) {
			myLeft = t;
			t.setParent(this);
		}

		@Override
		public void setRight(TreeNode t) {
			if(t!=null){
				
			}else{
				
			}
			myRight = t;
			t.setParent(this);
		}

		@Override
		public void setParent(TreeNode t) {
			myParent = t;
		}

		@Override
		public void checkRotate(TreeNode t) {
			// TODO Auto-generated method stub
			
		}
		
//		public String toString(){
//			return Integer.toString(this.myItem);
//		}



	

	}

	private class EmptyTreeNode extends TreeNode {

		private EmptyTreeNode() {

		}

		@Override
		public int item() {
			throw new UnsupportedOperationException("An empty node doesn't have an item!");
		}

		@Override
		public void insert(int item) {
		}

		@Override
		public int balanceFactor() {
			return 0;
		}

		@Override
		public int height() {
			return 0;
		}

		@Override
		public boolean isAlmostBalanced() {
			return true;
		}

		@Override
		public boolean isEmpty() {
			return true;
		}

		@Override
		public TreeNode parent() {
			return theEmptyTreeNode;
		}

		@Override
		public TreeNode left() {
			return theEmptyTreeNode;
		}

		@Override
		public TreeNode right() {
			return theEmptyTreeNode;
		}

		@Override
		public boolean contains(int item) {
			return false;
		}

		@Override
		public void setLeft(TreeNode t) {
		}

		@Override
		public void setRight(TreeNode t) {
		}

		@Override
		public void setParent(TreeNode t) {
		}


		@Override
		public void checkRotate(TreeNode t) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public TreeNode findUnbalancedTreeNode(TreeNode t) {
			// TODO Auto-generated method stub
			return null;
		}
		//for testing purpose
//		public String toString(){
//			return "empty";
//		}

	}

	/**
	 * Main method that runs some simple tests. This is NOT sufficient to cover
	 * all of the cases.
	 */
	public static void main(String[] args) {
		AVLTree t1 = new AVLTree(1);
		t1.insert(2);
		System.out.println(t1);
		// Should cause a rotation to occur.
		t1.insert(3);
		System.out.println(t1);
		System.out.println(t1.isAlmostBalanced());

		t1.insert(4);

		// Should cause another rotation.
		t1.insert(5);
		System.out.println(t1);
		System.out.println(t1.isAlmostBalanced());
//
		AVLTree t2 = new AVLTree(2);
		t2.insert(1);
		t2.insert(4);
		System.out.println(t2);
		System.out.println(t2.isAlmostBalanced());
		t2.insert(3);
		t2.insert(5);
		System.out.println(t2);
		System.out.println(t2.isAlmostBalanced());
//
//		// Should cause a rotation to occur.
//		t2.insert(6);
		System.out.println(t2);
		System.out.println(t2.isAlmostBalanced());
//		
//		// Should cause a rotation to occur.
//		t2.insert(7);
//		System.out.println(t2);
//		System.out.println(t2.isAlmostBalanced());
//		
//		//check for case 2
//		AVLTree t3 = new AVLTree(20);
//		t3.insert(10);
//		System.out.println(t3);
//		t3.insert(13);
//		System.out.println(t3);
//		System.out.println(t3.isAlmostBalanced());
//		t3.insert(25);
//		t3.insert(12);
//		System.out.println(t3);
//		System.out.println(t3.isAlmostBalanced());
//		t3.insert(26);
//		t3.insert(27);
//		System.out.println(t3);
//		System.out.println(t3.isAlmostBalanced());
//		t3.insert(28);
//		t3.insert(29);
//		System.out.println(t3);
//		System.out.println(t3.isAlmostBalanced());
//		t3.insert(5);
//		t3.insert(6);
//		System.out.println(t3);
//		System.out.println(t3.isAlmostBalanced());
//		t3.insert(1);
//		t3.insert(2);
//		System.out.println(t3);
//		System.out.println(t3.isAlmostBalanced());
//		t3.insert(-9);
//		t3.insert(-10);
//		System.out.println(t3);
//		System.out.println(t3.isAlmostBalanced());
//		t3.insert(50);
//		t3.insert(25);
//		System.out.println(t3);
//		System.out.println(t3.isAlmostBalanced());
//		t3.insert(20);
//		t3.insert(1);
//		System.out.println(t3);
//		System.out.println(t3.isAlmostBalanced());
		
	}
}