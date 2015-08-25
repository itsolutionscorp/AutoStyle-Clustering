import java.util.*;

public class BST {
	BSTNode myRoot;

	public BST(LinkedList list) {
		myRoot = linkedListToTree(list.iterator(), list.size());
	}

	

	// Your comment here
	private static BSTNode linkedListToTree(Iterator iter, int n) {
		// TODO Your code here
		if (n == 0) {
			return null;
		}
		if (n == 1) {
			BSTNode root = new BSTNode();
			root.myItem = iter.next();
			return root;
		}
		LinkedList firstHalf = new LinkedList();
		LinkedList secondHalf = new LinkedList();
		int counter = 0;
		while (iter.hasNext() && counter < n / 2) {
			firstHalf.add(iter.next());
			counter++;
		}
		BSTNode root = new BSTNode();
		root.myItem = iter.next();
		while (iter.hasNext()) {
			secondHalf.add(iter.next());
		}
		if (firstHalf.size() == 0 && secondHalf.size() > 0) {
			root.myLeft = null;
			root.myRight = linkedListToTree(secondHalf.iterator(),
					secondHalf.size());
			return root;
		}  if (firstHalf.size() > 0 && secondHalf.size() == 0) {
			root.myLeft = linkedListToTree(firstHalf.iterator(),
					firstHalf.size());
			root.myRight = null;
			return root;
		} else {
			root.myLeft = linkedListToTree(firstHalf.iterator(),
					firstHalf.size());
			root.myRight = linkedListToTree(secondHalf.iterator(),
					secondHalf.size());
			return root;
		}

	}

	private static class BSTNode {
		Object myItem;
		BSTNode myLeft;
		BSTNode myRight;

		

	}

}
