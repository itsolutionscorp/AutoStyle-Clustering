import java.util.*;

public class BST {
	BSTNode myRoot;

	public BST(LinkedList list) {
		myRoot = linkedListToTree(list.iterator(), list.size());
	}

	/**
	 * Produces a balanced binary tree of size n from an Iterator of node
	 * elements.
	 * 
	 * @param iter
	 *            Iterator that returns nodes of the tree
	 * @param n
	 *            Size of the tree
	 * @return The root node of the newly created BST
	 */
	private BSTNode linkedListToTree(Iterator iter, int n) {
		if (n == 0) {
			return null;
		} else if (n == 1) {
			return new BSTNode(iter.next());
		} else {
			int current = 0;
			LinkedList less = new LinkedList();
			LinkedList more = new LinkedList();
			while (iter.hasNext()) {
				if (current <= (n + 1) / 2) {
					Object o = iter.next();
					less.add(o);
				} else {
					Object o = iter.next();
					more.add(o);
				}
				current++;
			}
			if (less.size() != 0) {
				if (more.size() != 0) {
					return new BSTNode(less.pollLast(), linkedListToTree(
							less.iterator(), less.size()), linkedListToTree(
							more.iterator(), more.size()));
				} else {
					return new BSTNode(less.pollLast(), linkedListToTree(
							less.iterator(), less.size()), null);
				}
			} else {
				if (more.size() != 0) {
					return new BSTNode(less.pollLast(), null, linkedListToTree(
							more.iterator(), more.size()));
				} else {
					return new BSTNode(less.pollLast());
				}
			}

		}

	}

	public void print() {
	    if (myRoot != null) {
	        myRoot.print(0);
	    }
	}

	private class BSTNode {
		Object myItem;
		BSTNode myLeft;
		BSTNode myRight;

		public BSTNode(Object item) {
			myItem = item;
			myLeft = myRight = null;
		}

		public BSTNode(Object item, BSTNode left, BSTNode right) {
			myItem = item;
			myLeft = left;
			myRight = right;
		}

		private void printInorder() {
			if (myLeft != null) {
				myLeft.printInorder();
			}
			System.out.print(myItem + " ");
			if (myRight != null) {
				myRight.printInorder();
			}
		}
		
		private void printPreorder() {
			System.out.print(myItem + " ");
			if (myLeft != null) {
				myLeft.printPreorder();
			}
			if (myRight != null) {
				myRight.printPreorder();
			}
		}
		private static final String indent1 = "    ";

		private void print(int indent) {
		    if (myRight != null){
		    	myRight.print(indent + 1);
		    }
		    println (myItem, indent);
		    if (myLeft != null) {
		    	myLeft.print(indent + 1);
		    }
		}

		private void println(Object obj, int indent) {
		    for (int k=0; k<indent; k++) {
		        System.out.print(indent1);
		    }
		    System.out.println(obj);
		}
	}
}
