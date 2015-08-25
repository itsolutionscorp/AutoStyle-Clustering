
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

		public abstract void fix();

		public abstract void rotateLeft();

		public abstract void rotateRight();


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
			if (item > myItem) {
				if (myRight.isEmpty()) {
					setRight(new NonEmptyTreeNode(item, this));
					myRight.fix();
				} else {
					myRight.insert(item);
				}
			} else {
				// assume no duplicates
				if (myLeft.isEmpty()) {
					setLeft(new NonEmptyTreeNode(item, this));
					myLeft.fix();
				} else {
					myLeft.insert(item);
				}
			}
		}

		@Override
		public void fix() {
			if (!isAlmostBalanced()) {
				// fix it now
				if (left().height() < right().height()) {
					// right is heavy,
					// check if we need 1 or 2 rotations
					if (right().left().height() > right().right().height()) {
						// 2 rotations!
						right().rotateRight();
						rotateLeft();
					} else {
						// 1 rotation
						rotateLeft();
					}
				} else {
					// left is heavy,
					// check if we need 1 or 2 rotations
					if (left().right().height() > left().left().height()) {
						// 2 rotations!
						left().rotateLeft();
						rotateRight();
					} else {
						// 1 rotation
						rotateRight();
					}
				}
			}
			if (!parent().isEmpty()) {
				// try to fix its parent
				parent().fix();
			} else {
				// stop
				myRoot = this;
				return;
			}
		}

		@Override
		public void rotateLeft() {
			// step 1: transfer child from B to A
			TreeNode son_save = this.right();
			this.setRight(son_save.left());
			this.right().setParent(this); // child has to recognize its new parent
			// step 2: son B becomes A's parent
			// before that, store A's parent
			TreeNode parent_save = this.parent();
			setParent(son_save); // A wants B to be its parent
			this.parent().setLeft(this); // B says yes
			// step 3: check if parent is empty
			if (!parent_save.isEmpty()) {
				if (this.item() == parent_save.left().item()) {
					// if this method is called from the first of the double rotations, 
					// it is its parent's left child
					// so set righ for its parent
					parent_save.setLeft(son_save); // set son 
				} else {
					// if this method is called from the single rotation, 
					// it is its parent's right child
					// so set right for its parent
					parent_save.setRight(son_save); // set son 
				}
				son_save.setParent(parent_save); // son says yes 
			} else {
				son_save.setParent(theEmptyTreeNode);
			}
		}

		@Override
		public void rotateRight() {
			// step 1: transfer child from B to A
			TreeNode son_save = this.left();
			this.setLeft(son_save.right());
			this.left().setParent(this); // child has to recognize its new parent
			// step 2: son B becomes A's parent
			// before that, store A's parent
			TreeNode parent_save = this.parent();
			setParent(son_save); // A wants B to be its parent
			this.parent().setRight(this); // B says yes
			// step 3: check if parent is empty
			if (!parent_save.isEmpty()) {
				if (this.item() == parent_save.right().item()) {
					// if this method is called from the first of the double rotations, 
					// it is its parent's right child
					// so set right for its parent
					parent_save.setRight(son_save); // set son 
				} else {
					// if this method is called from the single rotation, 
					// it is its parent's left child
					// so set left for its parent
					parent_save.setLeft(son_save); // set son 
				}
				son_save.setParent(parent_save); // son says yes 
			} else {
				son_save.setParent(theEmptyTreeNode);
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

		@Override
		public int item() {
			throw new UnsupportedOperationException(
					"An empty node doesn't have an item!");
		}

		@Override
		public void insert(int item) {
		}



		@Override
		public void fix() {

		}

		@Override
		public void rotateLeft() {

		}

		@Override
		public void rotateRight() {

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

		// Should cause a rotation to occur.
		t2.insert(6);
		System.out.println(t2);
		System.out.println(t2.isAlmostBalanced());


		System.out.println("*********quiz*************"); 
		AVLTree t_quiz = new AVLTree(8);
		t_quiz.insert(6);
		t_quiz.insert(12);
		t_quiz.insert(4);
		t_quiz.insert(10);
		t_quiz.insert(14);
		t_quiz.insert(2);
		System.out.println(t_quiz); 
		System.out.println(t_quiz.isAlmostBalanced()); 

		System.out.println("*********test3*************"); 
		AVLTree t_3 = new AVLTree(3);
		t_3.insert(2);
		t_3.insert(1);
		System.out.println("******************************" + t_3); 
		System.out.println(t_3.isAlmostBalanced()); 
		t_3.insert(4);
		t_3.insert(5);
		System.out.println("******************************" + t_3); 
		System.out.println(t_3.isAlmostBalanced()); 
		t_3.insert(6);
		t_3.insert(7);
		System.out.println("******************************" + t_3); 
		System.out.println(t_3.isAlmostBalanced()); 
		t_3.insert(16);
		t_3.insert(15);
		System.out.println("******************************" + t_3); 
		System.out.println(t_3.isAlmostBalanced()); 
		t_3.insert(14);
		System.out.println("******************************" + t_3); 
		System.out.println(t_3.isAlmostBalanced()); 
		t_3.insert(18);
		System.out.println("******************************" + t_3); 
		System.out.println(t_3.isAlmostBalanced()); 
		t_3.insert(0);
		System.out.println("******************************" + t_3); 
		System.out.println(t_3.isAlmostBalanced()); 
		t_3.insert(-1);
		System.out.println("******************************" + t_3); 
		System.out.println(t_3.isAlmostBalanced()); 


	}
}