import java.util.ArrayList;

public class ArrayBST {
    private ArrayList<Node> contents = new ArrayList<Node>();

    public void insert(int value) {
        Node root = this.getNode(1);
        if (root != null) {
			insertInSubtree(1, value);
        } else {
			Node newNode = new Node(value);
            this.setNode(1, newNode);
        }
    }

	/**
	 * Used to print out the tree sideways.
	 */
	@Override
	public String toString() {
		return toStringHelper(1, "");
	}

	/* Recursive helper method for toString. */
	private String toStringHelper(int index, String soFar) {
		if (getNode(index) == null) {
			return "";
		} else {
			String toReturn = "";
			int rightChild = getRightOf(index);
			toReturn += toStringHelper(rightChild, "        " + soFar);
			if (getNode(rightChild) != null) {
				toReturn += soFar + "    /";
			}
			toReturn += "\n" + soFar + getNode(index) + "\n";
			int leftChild = getLeftOf(index);
			if (getNode(leftChild) != null) {
				toReturn += soFar + "    \\";
			}
			toReturn += toStringHelper(leftChild, "        " + soFar);
			return toReturn;
		}
	}

    private Node getNode(int index) {
        if (index >= contents.size()) {
            return null;
        } else {
            return contents.get(index);
        }
    }

    private void setNode(int index, Node n) {
		// In the case that the ArrayList is not big enough
		// add null elements until it is the right size
        while (index + 1 >= contents.size()) {
            contents.add(null);
        }
        contents.set(index, n);
    }

	/**
	 * Returns the index of the node to the left of the node at i.
	 */
	private int getLeftOf(int i) {
		
		// TODO Complete this method!
		return i * 2;
	}

	/**
	 * Returns the index of the node to the right of the node at i.
	 */
	private int getRightOf(int i) {
		// TODO Complete this method!
		int index =  i * 2;
		return index + 1;
	}

	/**
	 * Returns the index of the node that is the parent of the node at i.
	 */
	private int getParentOf(int i) {
		// TODO Complete this method!
		if ((i/2) == 0) {
			return 0;
		}
		return i/2;
	}

	/**
	 * Adds the given node as a left child of the node at the given index.
	 */
	private void setLeft(int index, Node n) {
		// TODO Complete this method!
		if (getNode(index) != null) {
			int leftIndex = index * 2;
			setNode(leftIndex, n);
			
		}
	}

	/**
	 * Adds the given node as the right child of the node at the given index.
	 */
	private void setRight(int index, Node n) {
		// TODO Complete this method!
		if (getNode(index) != null) {
			int rightIndex = index * 2;
			setNode(rightIndex + 1, n);
			
		}
	}

	/**
	 * Inserts a value into this subtree according to the BST invariants. This
	 * won't work until you complete the missing methods.
	 */
	private void insertInSubtree(int subTreeRootIndex, int value) {
		Node subTreeRoot = getNode(subTreeRootIndex);
		// is it already here in the tree?
		if (subTreeRoot.value() == value) {
			return;
		}
		// should it go in the left subtree?
		else if (value < subTreeRoot.value()) {
			int left = getLeftOf(subTreeRootIndex);
			if (getNode(left) != null) {
				insertInSubtree(left, value);
			} else {
				setLeft(subTreeRootIndex, new Node(value));
			}
		}
		// should it go in the right subtree?
		else {
			int right = getRightOf(subTreeRootIndex);
			if (getNode(right) != null) {
				insertInSubtree(right, value);
			} else {
				setRight(subTreeRootIndex, new Node(value));
			}
		}
	}
	
	public boolean contains (int value) {
		if (this.getNode(1) != null) {
			return containsHelper(1, value);
		}
		return false;
	}

	private boolean containsHelper(int index, int value) {
		Node BSTNode = getNode(index);
		if (BSTNode == null) {
			return false;
		}
		if (BSTNode.value() == value) {
			return true;
		} else if (BSTNode.value() < value) {
			return containsHelper((index * 2) + 1, value);
		} else if (BSTNode.value() > value) {
			return containsHelper(index * 2, value);
		}
		return false;
	}
		
	
	
	public class Node {
        private int myValue;

		private Node(int value) {
			myValue = value;
        }

		public int value() {
			return myValue;
		}

		@Override
		public String toString() {
			return Integer.toString(myValue);
		}
    }
	

    public static void main(String[] args) {
        ArrayBST bst = new ArrayBST();
        bst.insert(9);
        bst.insert(8);
        bst.insert(7);
        bst.insert(6);
        bst.insert(11);
        bst.insert(10);
		System.out.println(bst);
		System.out.println(bst.contains(11));
		System.out.println(bst.contains(18));
    }

}
