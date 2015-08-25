import java.util.*;

public class BST {
	BSTNode myRoot;

	public BST(LinkedList list) {
		myRoot = linkedListToTree(list.iterator(), list.size());
	}

	// Your comment here
	private static BSTNode linkedListToTree (Iterator iter, int n) {
		if (n == 1) {
			return new BSTNode(iter.next(), null, null);
		}
		int travel_times = n / 2;
		BSTNode left_subtree = linkedListToTree(iter, travel_times);
		Object middle = iter.next();
		BSTNode right_subtree = linkedListToTree(iter, n - 1 - travel_times);
		return new BSTNode(middle, left_subtree, right_subtree);
	}

	static private class BSTNode {
		Object myItem;
		BSTNode myLeft;
		BSTNode myRight;

		public BSTNode(Object m, BSTNode l, BSTNode r) {
			myItem = m;
			myLeft = l;
			myRight = r;
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


		private void printInorder() {
			if (myLeft != null) {
				myLeft.printInorder();
			}
			System.out.print(myItem + " ");
			if (myRight != null) {
				myRight.printInorder();
			}
		}
	}

	public static void main(String[] args) {
		LinkedList<Integer> l = new LinkedList<Integer>();
		l.add(1);
		l.add(2);
		l.add(3);
		l.add(4);
		l.add(5);
		l.add(6);
		l.add(7);
		BST b1 = new BST(l);
		b1.printPreorder();
		b1.printInorder();
	}


















	// Print the values in the tree in preorder: root value first,
	// then values in the left subtree (in preorder), then values
	// in the right subtree (in preorder).
	public void printPreorder() {
		if (myRoot == null) {
			System.out.println("(empty tree)");
		} else {
			myRoot.printPreorder();
			System.out.println();
		}
	}



	// Print the values in the tree in inorder: values in the left
	// subtree first (in inorder), then the root value, then values
	// in the right subtree (in inorder).
	public void printInorder() {
		if (myRoot == null) {
			System.out.println("(empty tree)");
		} else {
			myRoot.printInorder();
			System.out.println();
		}
	}

}
