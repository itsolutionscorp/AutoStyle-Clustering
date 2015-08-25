import java.util.Iterator;
import java.util.LinkedList;

public class BST {
	BSTNode myRoot;

	public BST(LinkedList<?> list) {
		myRoot = linkedListToTree(list.iterator(), list.size());
	}

	public BST() {
		BSTNode node = new BSTNode();
		myRoot = null;
	}

	/**
	 * linkedListToTree
	 * 
	 * Creates a new BSTNode from a sorted linkedList. Called recursively so
	 * that length n decreases by half each time.
	 * 
	 * @param iter
	 *            . An iterator object to iterate through the linked list.
	 * @param n
	 *            . The length of the linked list. Tree should contain n total
	 *            nodes. Height should be approx. log(n).
	 * @returns the BSTNode root of the tree.
	 */
	private static BSTNode linkedListToTree(Iterator<?> iter, int n) {
		// create new tree
		BST tree = new BST();
		tree.myRoot = new BSTNode();

		if (n > 1) {
			tree.myRoot.myLeft = linkedListToTree(iter, (n / 2));
		}
		if (iter.hasNext()) {
			tree.myRoot.myItem = iter.next();
		}
		if (n > 1) {
			tree.myRoot.myRight = linkedListToTree(iter, (n / 2) - 1);
		}
		return tree.myRoot;
	}

	private static class BSTNode {
		Object myItem;
		BSTNode myLeft;
		BSTNode myRight;

		public BSTNode() {
			myItem = null;
			myLeft = null;
			myRight = null;
		}
	}

	public static void main(String[] args) {
		// simple test with integers
		// LinkedList<Integer> list1 = new LinkedList<Integer>();
		// list1.add(1);
		// list1.add(2);
		// list1.add(3);
		// list1.add(4);
		// // list1.add(5);
		// // list1.add(6);
		// BST tree1 = new BST(list1);
		// printBinaryTree(tree1.myRoot, 0);
		//
		// more integers
		// LinkedList<Integer> list2 = new LinkedList<Integer>();
		// list2.add(1);
		// list2.add(2);
		// list2.add(3);
		// list2.add(4);
		// list2.add(5);
		// list2.add(6);
		// list2.add(7);
		// list2.add(8);
		// list2.add(9);
		// list2.add(10);
		// list2.add(11);
		// list2.add(12);
		// list2.add(13);
		// list2.add(14);
		// BST tree2 = new BST(list2);
		// printBinaryTree(tree2.myRoot, 0);

		// test strings
		LinkedList<String> list3 = new LinkedList<String>();
		list3.add("a");
		list3.add("b");
		list3.add("c");
		// list3.add("d");
		list3.add("e");
		list3.add("f");
		// list3.add("g");
		// list3.add("h");
		list3.add("i");
		list3.add("j");
		list3.add("k");
		BST tree3 = new BST(list3);
		printBinaryTree(tree3.myRoot, 0);

		// LinkedList<String> list4 = new LinkedList<String>();
		// list4.add("aardvark");
		// list4.add("brian");
		// list4.add("cat");
		// list4.add("dog");
		// list4.add("eifu");
		// list4.add("france");
		// list4.add("gary");
		// list4.add("hello");
		// list4.add("inside");
		// list4.add("junk");
		// list4.add("kilimanjaro");
		// list4.add("lemur");
		// list4.add("moonshine");
		// list4.add("niceman");
		// list4.add("opposite");
		// list4.add("pikachu"); // double p
		// list4.add("pokemon");
		// list4.add("quest");
		// list4.add("reset");
		// list4.add("silver");
		// list4.add("teletubbies");
		// list4.add("utopia");
		// list4.add("venus");
		// list4.add("windows 98");
		// list4.add("xtraterrestrial");
		// list4.add("yams");
		// list4.add("zoophobia");
		// BST tree4 = new BST(list4);
		// printBinaryTree(tree4.myRoot, 0);
		//
		// System.out.println("");
		// System.out.println("");
		// LinkedList<Integer> list5 = new LinkedList<Integer>();
		// list5.add(1);
		// list5.add(3);
		// list5.add(4);
		// list5.add(5);
		// list5.add(7);
		// list5.add(9);
		// BST tree5 = new BST(list5);
		// printBinaryTree(tree5.myRoot, 0);
	}

	/**
	 * PRINTBINARYTREE Adapted from Anurag Agarwal
	 * http://stackoverflow.com/questions
	 * /4965335/how-to-print-binary-tree-diagram
	 * 
	 * @param root
	 * @param level
	 */
	public static void printBinaryTree(BSTNode root, int level) {
		if (root == null)
			return;
		printBinaryTree(root.myRight, level + 1);
		if (level != 0) {
			for (int i = 0; i < level - 1; i++)
				System.out.print("|\t");
			System.out.println("|-------" + root.myItem);
		} else
			System.out.println(root.myItem);
		printBinaryTree(root.myLeft, level + 1);
	}
}
