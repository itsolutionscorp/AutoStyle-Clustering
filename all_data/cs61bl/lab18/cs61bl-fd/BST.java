import java.util.Iterator;
import java.util.LinkedList;

public class BST {
	BSTNode myRoot;

	public BST(LinkedList list) {
		myRoot = linkedListToTree(list.iterator(), list.size());
	}

	public BSTNode right() {
		return myRoot.myRight;
	}

	// Your comment here
	/**
	 * Makes a balanced BST out of an already sorted LinkedList.
	 * 
	 * @param iter
	 *            an iterator object over the LinkedList
	 * @param n
	 *            the size of the list
	 * @return a BSTNode that is the root of the new tree
	 */
	private BSTNode linkedListToTree(Iterator iter, int n) {
		// TODO Your code here
		if (n <= 0) {
			return null;
		} else if (n == 1) {
			BSTNode rtn = new BSTNode();
			rtn.myItem = iter.next();
			return rtn;
		} else {
			BSTNode rtn = new BSTNode();
			rtn.myLeft = linkedListToTree(iter, n / 2);
			rtn.myItem = iter.next();
			if (n % 2 != 0) {
				rtn.myRight = linkedListToTree(iter, (n / 2));
			} else {
				rtn.myRight = linkedListToTree(iter, (n / 2) - 1);
			}
			return rtn;
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

		public void print(int indent) {
			int count = indent;

			if (myRight != null) {
				myRight.print(indent + 1);
			}

			while (count > 0) {
				System.out.print("    ");
				count--;
			}
			System.out.println(myItem);

			if (myLeft != null) {
				myLeft.print(indent + 1);
			}
		}
	}
}
