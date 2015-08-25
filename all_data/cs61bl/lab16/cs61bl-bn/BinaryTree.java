import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinaryTree<T> implements Iterable<T> {

	protected TreeNode myRoot;

	/**
	 * Constructors
	 */
	public BinaryTree() {
		myRoot = null;
	}

	public BinaryTree(TreeNode t) {
		myRoot = t;
	}

	/**
	 * 
	 * @param list1
	 *            contains objects in PREORDER
	 * @param list2
	 *            contains objects in INORDER
	 */
	public BinaryTree(ArrayList<T> list1, ArrayList<T> list2) {
		if (list1 == null || list2 == null) {
			throw new IllegalArgumentException("list is null");
		}
		if (list1.size() != list2.size() || list1.size() == 0) {
			throw new IllegalArgumentException("incorrect list");
		}

		T firstitem = list1.get(0);
		myRoot = biHelper(firstitem, list1, list2);
	}

	public TreeNode biHelper(T item, ArrayList<T> list1, ArrayList<T> list2) {
		// System.out.println("done    " + list1.size() + "     " +
		// list2.size());
		if (list2.size() == 1) {
			return new TreeNode(item);
		} else { // find first letter in the left hand side

			T left = null;
			T right = null;
			ArrayList<T> list1forleft;
			ArrayList<T> list2forleft;

			ArrayList<T> list1forright;
			ArrayList<T> list2forright;

			if (list2.indexOf((list1.get(1))) < list2.indexOf(item)) {
				left = list1.get(1);
				int checkRight = 2;
				while (checkRight < list2.size()
						&& list2.indexOf(list1.get(checkRight)) < list2
								.indexOf(item)) {
					checkRight++;
				}
				if (checkRight == list2.size()) {
					list1forleft = new ArrayList<T>(list1.subList(1,
							list1.size()));
					list2forleft = new ArrayList<T>(list2.subList(0,
							list2.indexOf(item)));

					return new TreeNode(item, biHelper(left, list1forleft,
							list2forleft), null);

				} else {
					right = list1.get(checkRight);
					list1forright = new ArrayList<T>(list1.subList(2,
							list1.size()));
					list2forright = new ArrayList<T>(list2.subList(
							list2.indexOf(item) + 1, list2.size()));

					list1forleft = new ArrayList<T>(list1.subList(1,
							list1.size()));
					list2forleft = new ArrayList<T>(list2.subList(0,
							list2.indexOf(item)));

					return new TreeNode(item, biHelper(left, list1forleft,
							list2forleft), biHelper(right, list1forright,
							list2forright));
				}

			} else {
				int checkRight = 1;
				while (list2.indexOf(list1.get(checkRight)) <= list2
						.indexOf(item) && checkRight < list2.size()) {
					checkRight++;
				}
				right = list1.get(checkRight);

				list1forright = new ArrayList<T>(list1.subList(2,
						list1.size() - 1));
				list2forright = new ArrayList<T>(list2.subList(
						list2.indexOf(item) + 1, list2.size()));
				return new TreeNode(item, null, biHelper(right, list1forright,
						list2forright));

			}

		}

		// find first letter in the right hand side

		// if (inIndex == 0) {
		// return new TreeNode(list1.get(preIndex));
		// } else {
		// list1.remove(preIndex);
		// return new TreeNode(preE, biHelper(list1, list2, preIndex),
		// biHelper(list1, list2, preIndex + 1));
		// }
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

	public static BinaryTree<String> fillSampleTree1() {
		BinaryTree<String> t = new BinaryTree<String>();
		t.myRoot = t.new TreeNode("a", t.new TreeNode("b"), t.new TreeNode("c"));
		return t;
	}

	public static BinaryTree<String> fillSampleTree2() {
		BinaryTree<String> t = new BinaryTree<String>();
		t.myRoot = t.new TreeNode("a", t.new TreeNode("b", t.new TreeNode("d",
				t.new TreeNode("e"), t.new TreeNode("f")), null),
				t.new TreeNode("c"));
		return t;
	}

	public static void main(String[] args) {
		// ArrayList<String> a = new ArrayList<String>();
		// a.add("a");
		// a.add("b");
		// a.add("d");
		// a.add("e");
		// ArrayList<String> b = new ArrayList<String>();
		// b.add("d");
		// b.add("b");
		// b.add("e");
		// b.add("a");
		ArrayList<String> a = new ArrayList<String>(Arrays.asList(new String[] {
				"A", "B", "C", "D", "E" }));
		ArrayList<String> b = new ArrayList<String>(Arrays.asList(new String[] {
				"E", "D", "C", "B", "A" }));

		BinaryTree<String> t = new BinaryTree<String>(a, b);
		t.printPreorder();
		// print(t, "the empty tree");
		// BinaryTree<String> s = fillSampleTree1();
		// print(s, "sample tree 1");
		// BinaryTree<String> r = fillSampleTree2();
		// print(r, "sample tree 2");
	}

	protected static void print(BinaryTree<?> t, String description) {
		System.out.println(description + " in preorder");
		t.printPreorder();
		System.out.println(description + " in inorder");
		t.printInorder();
		System.out.println();
	}

	// Method for the BinaryTree class
	public Iterator<T> iterator() {
		return new InorderIterator();
	}

	// Inner class inside of the BinaryTree class.
	// Also, it uses java.util.Iterator and java.util.Stack.
	private class InorderIterator implements Iterator<T> {
		private Stack<TreeNode> nodeStack;
		private TreeNode currentNode;

		public InorderIterator() {
			nodeStack = new Stack<TreeNode>();
			currentNode = myRoot;
		}

		public boolean hasNext() {
			return !nodeStack.isEmpty() || (currentNode != null);
		}

		public T next() {
			TreeNode nextNode = null;

			// find leftmost node with no left child
			while (currentNode != null) {
				nodeStack.push(currentNode);
				currentNode = currentNode.myLeft;
			}

			// get leftmost node, then move to its right subtree
			if (!nodeStack.isEmpty()) {
				nextNode = nodeStack.pop();
				assert nextNode != null; // since nodeStack was not empty before
											// the pop
				currentNode = nextNode.myRight;
			} else {
				throw new NoSuchElementException();
			}

			return nextNode.myItem;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	protected class TreeNode {

		public T myItem;
		public TreeNode myLeft;
		public TreeNode myRight;

		public TreeNode(T item) {
			myItem = item;
			myLeft = myRight = null;
		}

		public TreeNode(T item, TreeNode left, TreeNode right) {
			myItem = item;
			myLeft = left;
			myRight = right;
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
}