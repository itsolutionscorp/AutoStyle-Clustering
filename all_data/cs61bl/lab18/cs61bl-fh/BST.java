import java.util.Iterator;
import java.util.LinkedList;

public class BST {
	BSTNode myRoot;

	public BST(LinkedList list) {
		myRoot = linkedListToTree(list.iterator(), list.size());
	}

	// Makes a BSTNode tree structure using the iterator passed to the
	// constructor be recursing on the size of the list.
	private BSTNode linkedListToTree(Iterator iter, int n) {
		if (n == 0) {
			return null;
		} else if (n == 1) {
			BSTNode temp = new BSTNode();
			temp.myItem = iter.next();
			return temp;
		} else {
			BSTNode left = linkedListToTree(iter, n / 2);
			BSTNode temp = new BSTNode();
			temp.myItem = iter.next();
			BSTNode right = linkedListToTree(iter, (n - 1) / 2);
			temp.myLeft = left;
			temp.myRight = right;
			return temp;
		}
	}

	// // Your comment here
	// private BSTNode linkedListToTree (Iterator iter, int n) {
	// // TODO Your code here
	// Object[] itemList = new Object[n];
	// for (int i = 0; i < n; i++) { //Copy everything
	// itemList[i] = iter.next();
	// }
	// return null;
	// }
	//
	// private BSTNode toTreeHelper(Object[] itemList) {
	// if (itemList.length == 1) {
	// BSTNode temp = new BSTNode();
	// temp.myItem = itemList[0;
	// return temp;
	// } else {
	// int middle = itemList.size()/2;
	// BSTNode temp = new BSTNode();
	// temp.myItem = itemList.get(middle);
	// temp.myLeft = toTreeHelper()
	// return temp;
	//
	//
	// }
	// }

	public void print() {
		myRoot.print(0);
	}

	public class BSTNode {
		Object myItem;
		BSTNode myLeft;
		BSTNode myRight;

		public void print(int indent) {
			if (myRight != null) {
				myRight.print(indent + 1);
			}
			int count = indent;
			while (count > 0) {
				System.out.print("    ");
				count--;
			}

			System.out.println(myItem.toString());
			if (myLeft != null) {
				myLeft.print(indent + 1);
			}
		}
	}
}
