import java.util.*;

public class BST {
	BSTNode myRoot;

	public BST(LinkedList list) {
		myRoot = linkedListToTree(list.iterator(), list.size());
	}

	// Your comment here
	private BSTNode linkedListToTree(Iterator iter, int n) {
		Iterator copy = iter;
		Object rtn = null;
		for (int i = 0; i == n / 2; i++) {
			rtn = iter.next();
		}
		myRoot.myItem = rtn;
		myRoot.myLeft = linkedListToTree(copy, n / 2);
		myRoot.myRight = linkedListToTree(iter, n / 2);
		return myRoot;
	}

	private class BSTNode {
		Object myItem;
		BSTNode myLeft;
		BSTNode myRight;
	}
}
