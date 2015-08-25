//import AVLTree.TreeNode;


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
		return isAlmostBalanced();
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

		public abstract void insertHelper(TreeNode t1, TreeNode t2);

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
		public abstract TreeNode rotateLeft(); 
		public abstract TreeNode rotateRight();
		public abstract TreeNode rightThenLeft();
		public abstract TreeNode leftThenRight();
		public abstract void rebalance(TreeNode t);

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
		 * m
		 * You'll also probably want to write helper methods rotateRight() and
		 * rotateLeft().
		 */
		@Override
		public void insert(int item) {
			TreeNode t = new NonEmptyTreeNode(item);
			insertHelper(myRoot, t);
		}
		
		@Override
		public void insertHelper(TreeNode t1, TreeNode t2){
			if (t1 == null){
				myRoot = t2;
			} else if (t2 == null){
				myRoot = t1;
			}
			else {
				if (t2.item() < t1.item()){
					if(t1.left()==null){
						t1.setLeft(t2);
						t2.setParent(t1);
						rebalance(t1);
					} else {
						insertHelper(t1.left(), t2);
					}
				} else if (t2.item() > t1.item()){
					if (t1.right() == null){
						t1.setRight(t2);
						t2.setParent(t1);
						rebalance(t1);
					} else {
						insertHelper(t1.right(), t2);
					}
				}
				else {
				}
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

		@Override
		public TreeNode rotateLeft() {

			
			TreeNode right = right();
			right.setParent(parent());
			setRight(right.left());
			if (right() != null) {
				right().setParent(this);
			}
			right.setLeft(this);
			setParent(right);
			if (right.parent() != null) {
				if (right.parent().right().equals(this)) {
					right.parent().setRight(right);
				}
			}
			
			this.balanceFactor();
			right.balanceFactor();
			return right;
			
		}

		@Override
		public TreeNode rotateRight() {
			// TODO Auto-generated method stub

//			return b; 
			TreeNode left = this.left();
			left.setParent(this.parent());
			left.setLeft(left.right());
			if (left.left() != null) {
				left.left().setParent(left);
			}
			left.setRight(this);
			this.setParent(left);
			if (left.parent() != null) {
				if (left.parent().right().equals(this)) {
					left.parent().setLeft(left);
				}
			}
			this.balanceFactor();
			left.balanceFactor();
			return left;
		}

		@Override
		public void rebalance(TreeNode t) {

			int i = t.balanceFactor();
			if (i == 2) {
				if (t.right().right().height() >= t.right().left().height()) {
					t = t.rotateLeft();
				} else {
					t = t.rightThenLeft();
				}
			} else if (i == -2) {
				if (t.left().left().height() >= t.left().right().height()) {
					t = t.rotateRight();
				} else {
					t = t.leftThenRight();
				}
			}
			if (t.parent() != null) {
				t.parent().balanceFactor();
			} else {
			myRoot = t;
		}
		}

		@Override
		public TreeNode rightThenLeft() {
			// TODO Auto-generated method stub
//			 setRight(right().rotateRight());
//		     return rotateLeft();
			setRight(this.right().rotateRight());
			return this.rotateLeft();
		}

		@Override
		public TreeNode leftThenRight() {
			// TODO Auto-generated method stub
//			 setLeft(left().rotateLeft());
//		     return rotateRight();
			setLeft(this.left().rotateLeft());
			return this.rotateRight();
		}

	

	}

	private class EmptyTreeNode extends TreeNode {
		
		private EmptyTreeNode() {
			
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
		public void setRight(TreeNode t){
		}

		@Override
		public void setParent(TreeNode t) {
		}

		@Override
		public TreeNode rotateLeft() {
			return null;
		}

		@Override
		public TreeNode rotateRight() {
			return null;
		}

		@Override
		public void rebalance(TreeNode t) {
		}

		@Override
		public TreeNode rightThenLeft() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public TreeNode leftThenRight() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void insertHelper(TreeNode t1, TreeNode t2) {
			// TODO Auto-generated method stub
			
		}



	}

	/**
	 * Main method that runs some simple tests. This is NOT sufficient to cover
	 * all of the cases.
	 */
	public static void main(String[] args) {
		AVLTree t1 = new AVLTree(1);
		t1.insert(2);

		// Should cause a rotation to occur.
		t1.insert(3);
		System.out.println(t1);
		System.out.println(t1.isAlmostBalanced());

		t1.insert(4);

		// Should cause another rotation.
		t1.insert(5);
		System.out.println(t1);
		System.out.println(t1.isAlmostBalanced());

		AVLTree t2 = new AVLTree(2);
		t2.insert(1);
		t2.insert(4);
		t2.insert(3);
		t2.insert(5);
		System.out.println(t2.toString());

		// Should cause a rotation to occur.
		t2.insert(6);
		System.out.println(t2);
		System.out.println(t2.isAlmostBalanced());
	}
}