import java.util.*;

public class BST {
	BSTNode myRoot;

	public BST(LinkedList list) {
		myRoot = linkedListToTree(list.iterator(), list.size());
	}

	/**
	 * Builds a balance binary search tree out of an already sorted LinkedList.
	 * 
	 * @param iter
	 * 			An iterator that returns the next item in the LinkedList
	 * @param n
	 * 			The size of the LinkedList
	 * @return a balance binary search tree
	 */
	private BSTNode linkedListToTree(Iterator iter, int n) {
		Object obj = new Object();
		LinkedList<Object> left = new LinkedList<Object>();
		LinkedList<Object> right = new LinkedList<Object>();
		int a = n / 2;
		BSTNode bstn = new BSTNode();
		if (n % 2 == 0) {
			a = n / 2;
		} else {
			a = n / 2 + 1;
		}
		if (n == 2) {
			bstn.myItem = iter.next();
			bstn.myRight = new BSTNode();
			bstn.myRight.myItem = iter.next();
			return bstn;
		}
		for (int i = 0; i < n; i++) {
			if (a == 1) {
				bstn.myItem = obj;
				return bstn;
			}
			if (i < a - 1) {
				obj = iter.next();
				left.add(obj);

			} else if (i == a - 1) {
				bstn.myItem = iter.next();
			} else {
				obj = iter.next();
				right.add(obj);
			}
		}
		if (!left.equals(null)) {
			bstn.myLeft = linkedListToTree(left.iterator(), left.size());
		}
		if (!right.equals(null)) {
			bstn.myRight = linkedListToTree(right.iterator(), right.size());
		}
		return bstn;
	}

	class BSTNode {
		Object myItem;
		BSTNode myLeft;
		BSTNode myRight;
	}
}
