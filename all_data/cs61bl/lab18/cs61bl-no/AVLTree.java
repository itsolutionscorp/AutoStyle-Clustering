

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
		
		public abstract void add(int n);
		public abstract TreeNode rotateLeft(TreeNode t);
		public abstract TreeNode rotateRight(TreeNode t);
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
		 
		@Override
		public void add(int item){
			if (item == myItem){
            return;
			} else if (item < myItem) {
				if (myLeft == null) {
					myLeft = new NonEmptyTreeNode(item);
                  return;
				} else
					myLeft.add(item);
			} else if (item > myItem) {
				if (myRight == null) {
					myRight = new NonEmptyTreeNode(item);
					return;
            } else
                  myRight.add(item);
			}
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
			//System.out.println("____________________");
			//System.out.println("Item is: ");
			//System.out.println(item);
			
			
			
			if (myRoot.left().isEmpty() && item < myRoot.item()){
				TreeNode temp = new NonEmptyTreeNode(item, theEmptyTreeNode);
	            myRoot.setLeft(temp);
	            myRoot.left().setParent(myRoot);
	            return;

			} else if (myRoot.right().isEmpty() && item > myRoot.item()){
				TreeNode temp = new NonEmptyTreeNode(item, theEmptyTreeNode);
				myRoot.setRight(temp);
				myRoot.right().setParent(myRoot);
				return;

			} else {
				
				TreeNode n = myRoot;
				TreeNode parent;

				while (true) {
					
					parent = n;
					
					
					boolean goLeft = n.item() > item;
					n = goLeft ? n.left() : n.right();
				
					if (n.isEmpty()) {
						// System.out.println(parent);
						TreeNode a = new NonEmptyTreeNode(item, parent);

						if (goLeft) {
							parent.setLeft(a);

						} else {
							parent.setRight(a);

						}
						
						rebalance(parent);
						break;
	                }
	            }
	            
	        }
	        return;
          
		}
		
		
		
		@Override
		public void rebalance(TreeNode t) {
			
			//System.out.println(!t.parent().isAlmostBalanced());
	        if (!t.parent().isAlmostBalanced() && t.parent().balanceFactor() > 0) {
	        	
	            if (t.parent().left().balanceFactor() >= 0){
	                t = rotateRight(t.parent());
	            } else {
	                t = rotateLeftThenRight(t.parent());
	            }
	 
	        } else if (!t.parent().isAlmostBalanced() && t.parent().balanceFactor() < 0) {
	        	//System.out.println(t.right());
	            if (t.right().balanceFactor() >= 0){
	                t = rotateLeft(t.parent());
	            } else { 
					t = rotateRightThenLeft(t.parent());
				}
			}

			if (!t.parent().isEmpty()) {
				rebalance(t.parent());
			} else if (!t.isAlmostBalanced()) {

				if (t.right().balanceFactor() < 0) {
					rebalance(t.right().right());
				} else if (t.right().balanceFactor() > 0){
					rebalance(t.right().left());
				} else if (t.left().balanceFactor() > 0){
					rebalance(t.left().right());
				} else if (t.left().balanceFactor() < 0){
					rebalance(t.left().left());
				}

			} else {

				myRoot = t;
			}
		}

		@Override
		public TreeNode rotateLeft(TreeNode a) {		
			
			 
	        TreeNode b = a.right();
	        b.setParent(a.parent());
	        a.setRight(b.left());
	 
	        if (!a.right().isEmpty())
	        	a.right().setParent(a);
	 
	        b.setLeft(a);
	        a.setParent(b);
	        
	        if (!b.parent().isEmpty()) {
	            if (b.parent().right() == a) {
	                b.parent().setRight(b);
	            } else {
	                b.parent().setLeft(b);
	            }
	        }

	        return b;
	    }
		
		public TreeNode rotateRight(TreeNode a) {

			TreeNode b = a.left();
			b.setParent(a.parent());

			a.setLeft(b.right());

			if (!a.left().isEmpty())
				a.left().setParent(a);

			b.setRight(a);
			a.setParent(b);

			if (!b.parent().isEmpty()) {
				if (b.parent().right() == a) {
					b.parent().setRight(b);
				} else {
					b.parent().setLeft(b);
				}
			}

			return b;
		}	
		
		public TreeNode rotateLeftThenRight(TreeNode t) {
	        t.setLeft(rotateLeft(t.left()));
	        return rotateRight(t);
	    }
		
		public TreeNode rotateRightThenLeft(TreeNode t) {
	        t.setRight(rotateRight(t.right()));
	        return rotateLeft(t);
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
		public void add(int item){
		}
		
		@Override
		public TreeNode rotateLeft(TreeNode t){
			return null;
		}
		
		@Override
		public TreeNode rotateRight(TreeNode t){
			return null;
			
		}
		
		@Override
		public void rebalance(TreeNode t){
			
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
		
		
		
		AVLTree t3 = new AVLTree(7);
		t3.insert(1);
		t3.insert(4);
		t3.insert(3);
		t3.insert(5);
		
		System.out.println(t3);
		System.out.println(t3.isAlmostBalanced());
		
		AVLTree t4 = new AVLTree(7);
		t4.insert(6);
		t4.insert(5);
		t4.insert(4);
		t4.insert(3);
		t4.insert(2);
		
		System.out.println(t4);
		System.out.println(t4.isAlmostBalanced());
		
		
	}
}