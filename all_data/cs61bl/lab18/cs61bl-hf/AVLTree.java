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

		public int myitem;

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
		public abstract void rotateRight(boolean t);
		public abstract void rotateLeft(boolean t);
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
			if (item > item()) {
				if (myRight.isEmpty()) {
					myRight = new NonEmptyTreeNode(item, this);
				} else {
					myRight.insert(item);
				}
			} else {
				if (myLeft.isEmpty()) {
					myLeft = new NonEmptyTreeNode(item, this);
				} else {
					myLeft.insert(item);
				}
			}
			
			if (!isAlmostBalanced()){
				if (balanceFactor() < 0) {  // right rotate...
					if (myRoot.right().balanceFactor()>0){
						this.right().rotateRight(true);
						rotateLeft(true);
					}else{
						rotateLeft(false);
					}
					
				} else { 
					if (myRoot.left().balanceFactor() < 0){    // left rotate.. 
						this.left().rotateLeft(true);
						rotateRight(true);
						
					}else{
						
						rotateRight(false);
						
					}
				}
				while(!myRoot.parent().isEmpty()){
				myRoot= myRoot.parent();
				}
				System.out.println("hi");
			}
			
		}

		public void rotateRight(boolean t) {
			if (t){
			TreeNode N= this.parent();
			this.setParent(this.left());
			this.parent().setParent(N);
			this.setLeft(this.parent().right());
			this.parent().setRight(this);
			this.left().setParent(this);
			N.setRight(this.parent());
			}else{
				TreeNode N= this.parent();
				this.setParent(this.left());
				this.parent().setParent(N);
				this.setLeft(this.parent().right());
				this.parent().setRight(this);
				this.left().setParent(this);
				N.setLeft(this.parent());
			}
			
		}

		public void rotateLeft(boolean t) {
			if (t){
			TreeNode N= this.parent();
			this.setParent(this.right());
			this.parent().setParent(N);
			this.setRight(this.parent().left());
			this.parent().setLeft(this);
			this.right().setParent(this);
			N.setLeft(this.parent());
			}else{
				TreeNode N= this.parent();
				this.setParent(this.right());
				this.parent().setParent(N);
				this.setRight(this.parent().left());
				this.parent().setLeft(this);
				this.right().setParent(this);
				N.setRight(this.parent());
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
		}

		@Override
		public void setRight(TreeNode t) {
			myRight = t;
		}

		@Override
		public void setParent(TreeNode t) {
			myParent = t;
		}

	}

	private class EmptyTreeNode extends TreeNode {

		private EmptyTreeNode() {

		}
		public  void rotateRight(boolean t){
			
		}
		public void rotateLeft(boolean t){
			
		}

		@Override
		public int item() {
			throw new UnsupportedOperationException(
					"An empty node doesn't have an item!");
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

	}

	/**
	 * Main method that runs some simple tests. This is NOT sufficient to cover
	 * all of the cases.
	 */
	public static void main(String[] args) {
		AVLTree t1 = new AVLTree(1);
		System.out.println(t1.myRoot.item());
		t1.insert(2);
		// Should cause a rotation to occur.
		t1.insert(3);
		System.out.println(t1);
		System.out.println(t1.isAlmostBalanced());
		t1.insert(4);
		System.out.println(t1.myRoot.item());
		System.out.println(t1.myRoot.myitem);
		System.out.println(t1);
		// Should cause another rotation.
		t1.insert(5);
//		System.out.println(t1.myRoot.item());
		System.out.println(t1);
		System.out.println(t1.isAlmostBalanced());

		AVLTree t2 = new AVLTree(2);
		t2.insert(1);
		t2.insert(4);
		t2.insert(3);
		t2.insert(5);

		// Should cause a rotation to occur.
		t2.insert(6);
		System.out.println(t2);
		System.out.println(t2.isAlmostBalanced());
		
		// checking special case on the right side
		AVLTree t3 = new AVLTree(3);
		t3.insert(2);
		t3.insert(6);
		t3.insert(5);
		t3.insert(7);
		
		// Should cause a rotation to occur.
		t3.insert(4);
		System.out.println(t3);
		System.out.println(t3.isAlmostBalanced());
		
		
		
		// left heavy...
		AVLTree tr1= new AVLTree(5);
	tr1.insert(4);
		// Should cause a rotation to occur.
		tr1.insert(3);
		System.out.println(tr1);
		System.out.println(tr1.isAlmostBalanced());

		tr1.insert(2);
		System.out.println(tr1);
		// Should cause another rotation.
		tr1.insert(1);
		System.out.println(tr1);
		System.out.println(tr1.isAlmostBalanced());
		AVLTree tr2 = new AVLTree(2);
	tr2.insert(1);
		tr2.insert(4);
		tr2.insert(3);
		tr2.insert(5);

		// Should cause a rotation to occur.
		tr2.insert(6);
		System.out.println(tr2);
		System.out.println(tr2.isAlmostBalanced());
		
//		// checking special case on the right side
//		AVLTree tr3 = new AVLTree(3);
//		tr3.insert(2);
//		tr3.insert(6);
//		tr3.insert(5);
//		tr3.insert(7);
//		
//		// Should cause a rotation to occur.
//		tr3.insert(4);
//		System.out.println(tr3);
//		System.out.println(tr3.isAlmostBalanced());
		
	}
}