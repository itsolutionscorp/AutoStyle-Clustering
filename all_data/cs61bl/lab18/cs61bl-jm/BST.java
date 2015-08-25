import java.util.*;

public class BST {
	BSTNode myRoot;

	public BST(LinkedList list) {
		myRoot = linkedListToTree(list.iterator(), list.size());
	}

	/**
	 * Creates a BSTNode given an iterator for a sorted Linked List
	 * 
	 * @param iter
	 * 		iterator for a linked list
	 * @param n
	 * 		the size of the given iterator
	 * @return
	 * 		the BSTNode that was created
	 */
	private BSTNode linkedListToTree(Iterator iter, int n) {
		if (n == 0) {
			return null;
		} else if (n == 1) {
			return new BSTNode(iter.next(), null, null);
		} else if (n == 2) {
			Object left = iter.next();
			return new BSTNode(iter.next(), new BSTNode(left, null, null), null);
		}
		int middlePos = n / 2;
		int counter = 0;
		LinkedList left = new LinkedList();
		LinkedList right = new LinkedList();
		while (counter < middlePos) {
			left.add(iter.next());
			counter++;
		}
		Object rootItem = iter.next();
		while (iter.hasNext()) {
			right.add(iter.next());
		}
		return new BSTNode(rootItem, linkedListToTree(left.iterator(),
				left.size()), linkedListToTree(right.iterator(), right.size()));
	}
	
	public void printInOrder() {
		if (myRoot != null) {
			printHelper(myRoot, 0);
		} else {
			return;
		}
	}
	
	private void printHelper(BSTNode t, int indent) {
		if (t == null) {
			return;
		}
		printHelper(t.myRight, indent + 1);
		int counter = 0;
		System.out.println("    ");
		while (counter < indent) {
			System.out.print("    ");
			counter++;
		}
		System.out.print(t.myItem);
		printHelper(t.myLeft, indent + 1);
	}
	
	private class BSTNode {
		Object myItem;
		BSTNode myLeft;
		BSTNode myRight;
		
		public BSTNode(Object item, BSTNode left, BSTNode right) {
			myItem = item;
			myLeft = left;
			myRight = right;
		}
	}
}
