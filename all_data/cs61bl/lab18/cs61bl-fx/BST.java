import java.util.*;

public class BST {
	BSTNode myRoot;

	public BST(LinkedList list) {
		myRoot = linkedListToTree(list.iterator(), list.size());
	}

	// Your comment here
	private BSTNode linkedListToTree(Iterator iter, int n) {
		if (n == 0) {
			return null;
		}

		BSTNode left = linkedListToTree(iter, n / 2);
		BSTNode root = new BSTNode();
		root.myItem = iter.next();
		if (iter.hasNext()) {
		BSTNode right = linkedListToTree(iter, n/ 2);
		root.myRight = right;
		}
		root.myLeft = left;
		return root;

	}

	public void printInorder() {
		if (myRoot == null) {
			System.out.println("(empty tree)");
		} else {
			myRoot.printInorder();
			System.out.println();
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

		private void printInorder() {
			if (myLeft != null) {
				myLeft.printInorder();
			}
			System.out.print(myItem + " ");
			if (myRight != null) {
				myRight.printInorder();
			}
		}

		private static final String indent1 = "    ";

		private void print(int indent) {
			// TODO your code here
			indent++;
			if (myRight != null) {
				myRight.print(indent);
			}
			println(myItem, indent);
			if (myLeft != null) {
				myLeft.print(indent);
			}

			// if (myRight == null && myLeft == null) {
			//
			// }
			// TODO your code here
		}

		private void println(Object obj, int indent) {
			for (int k = 0; k < indent; k++) {
				System.out.print(indent1);
			}
			System.out.println(obj);
		}
	}
}
