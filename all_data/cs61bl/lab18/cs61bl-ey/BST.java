import java.util.*;

public class BST {
	public BSTNode myRoot;

	public BST(LinkedList list) {
		myRoot = linkedListToTree(list.iterator(), list.size());
	}

	// Your comment here
	private static BSTNode linkedListToTree(Iterator iter, int n) {
		BSTNode bst = new BSTNode();
		if (n == 1) {

			bst.myItem = iter.next();
			return bst;
		}
		if (n == 0) {
			return null;
		}
		// if (n == n/2 - 1){
		// bst.myRight.myItem = iter.next();
		// return bst;
		// }
		bst.myLeft = linkedListToTree(iter, (n - 1) / 2);
		bst.myItem = iter.next();
		// if (iter.hasNext()){
		// System.out.print(n);
		// System.out.print("dog");
		// bst.myRight.myItem = iter.next();
		// }
		bst.myRight = linkedListToTree(iter, n / 2);
		return bst;
	}

	static class BSTNode {
		public Object myItem;
		public BSTNode myLeft;
		public BSTNode myRight;
	}
}