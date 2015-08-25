
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
		if(!isAlmostBalanced()) {
			fixBalance(myRoot);
			
		}
	}
	
	public void fixBalance(TreeNode current) {
		//System.out.println(current);
		int balance = current.balanceFactor();
		//System.out.println(balance + " " + current.item());
		if(balance == 2 || !current.left().isAlmostBalanced()) {
			fixBalance(current.left());
		} else if(balance == -2 || !current.right().isAlmostBalanced()) {
			fixBalance(current.right());
		} else {
			rotate(current.parent());
		}
		
	}
	
	public void rotate(TreeNode node) {

		//System.out.println(node.item());
		if(node.right().height() > node.left().height()) {
			//System.out.println(node.right().right().height() + " " + node.right().left().height());
			if(node.right().right().height() < node.right().left().height()) {
				//System.out.println("double left");
				node.doubleRotateLeft();
			} else {
				//System.out.println("left");
				node.rotateLeft();
			}
		} else {
			if(node.left().left().height() < node.left().right().height()) {
			//	System.out.println("double right");
				node.doubleRotateRight();
			} else {
				//System.out.println("right");
				node.rotateRight();
			}
		}
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
		
		public abstract void rotateRight();
		public abstract void rotateLeft();
		public abstract void doubleRotateLeft();
		public abstract void doubleRotateRight();
		public abstract void insert(TreeNode n, boolean right, int item);
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
		
		public void test() {
			TreeNode head = new NonEmptyTreeNode(9);
			myRoot = head;
			//TreeNode head = tree.myRoot;
			head.setLeft(new NonEmptyTreeNode(7));
			head.setRight(new NonEmptyTreeNode(15));
			head.right().setLeft(new NonEmptyTreeNode(13));
			head.right().setRight(new NonEmptyTreeNode(17));
			head.right().right().setRight(new NonEmptyTreeNode(22));
			head.right().left().setRight(new NonEmptyTreeNode(14));
			head.right().left().setLeft(new NonEmptyTreeNode(11));
			System.out.println(head.toString());
			System.out.println("------------------");
			head.doubleRotateLeft();
			System.out.println(myRoot.toString());
		}
		
		public void reassignCurrent(TreeNode newHead) {
			if(this.parent() == theEmptyTreeNode) {
				myRoot = newHead;
			} else {
				if(this == this.parent().left()) {

					this.parent().setLeft(newHead);
				} else {
					this.parent().setRight(newHead);
				}
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
		 * In order to do a rotation, all we have to do is move the references at 
		 * the top of the unbalanced subtrees. No matter how big the subtrees are, 
		 * just moving the references at the top moves the whole subtree.
		 */
		

		
		@Override
		public void insert(int item) {
			insert(parent(), false, item);
			
		}
	
		
		@Override
		public void insert(TreeNode n, boolean right, int item) {
			if(item > item()) {
				right().insert(this, true, item);
			} else {
				left().insert(this, false, item);
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

		// -2 if right side is greater, 2 is left side is greater
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
		public void rotateRight() {
			TreeNode head = this;
			TreeNode left = this.left();
			TreeNode leftRight = left.right();
			
			head = left;
			head.setRight(this);
			head.right().setLeft(leftRight);
			reassignCurrent(head);
			
		}
		
		

		@Override
		public void rotateLeft() {
			TreeNode head = this;
			TreeNode right = this.right();
			TreeNode rightLeft = right.left();
			
			head = right;
			head.setLeft(this);
			head.left().setRight(rightLeft);
			reassignCurrent(head);
		}

		@Override
		public void doubleRotateLeft() {
			TreeNode rightLeft = right().left();
			
			TreeNode right = right();
			TreeNode rightleftleft = right().left().left();
			TreeNode rightleftright = right().left().right();
			
			TreeNode newHead = rightLeft;
			newHead.setRight(right);
		
			newHead.setLeft(this);
			newHead.left().setRight(rightleftleft);
			newHead.right().setLeft(rightleftright);
			reassignCurrent(newHead);
		}

		@Override
		public void doubleRotateRight() {

			TreeNode leftRight = left().right();
			
			TreeNode left = left();
			TreeNode leftRightRight = left().right().right();
			TreeNode leftRightLeft = left().right().left();
			
			TreeNode newHead = leftRight;
			newHead.setLeft(left);
		
			newHead.setRight(this);
			newHead.right().setLeft(leftRightRight);
			newHead.left().setRight(leftRightLeft);
			reassignCurrent(newHead);
			
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
			insert(parent(), false, item);
		}
		
		@Override
		public void insert(TreeNode n, boolean right, int item) {
			if(right) {
				n.setRight(new NonEmptyTreeNode(item, n));
			} else {
				n.setLeft(new NonEmptyTreeNode(item, n));
			}
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
		public void rotateRight(){
		}
		public void rotateLeft(){
		}
		public void doubleRotateLeft(){
		}
		public void doubleRotateRight(){
		}



	}

	/**
	 * Main method that runs some simple tests. This is NOT sufficient to cover
	 * all of the cases.
	 */
	public static void main(String[] args) {
		
		AVLTree t1 = new AVLTree(10);
		/*AVLTree tree = new AVLTree(20);
		tree.insert(10);
		tree.insert(30);
		tree.insert(5);

		tree.insert(25);

		tree.insert(40);
		tree.insert(35);
		tree.insert(45);
		tree.insert(34);
		System.out.println(tree);
		//System.out.println(tree);
		//new AVLTree(10).myRoot.test();
*/

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

		// Should cause a rotation to occur.

		System.out.println(t2);
		t2.insert(6);
		//System.out.println(t2);

		t2.insert(7);
		System.out.println(t2);
		System.out.println(t2.isAlmostBalanced());
	}
}