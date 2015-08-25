import java.util.ArrayList;
import java.util.Stack;

public class BinaryTree {

	private TreeNode myRoot;

	public BinaryTree() {
		myRoot = null;
	}

	public BinaryTree(TreeNode t) {
		myRoot = t;
	}

	public int getRootItem() {
		return (int) this.myRoot.myItem;
	}

	public int getDeepLeft() {
		return (int) this.myRoot.myLeft.myLeft.myItem;
	}

	public int getLeft() {
		return (int) this.myRoot.myLeft.myItem;
	}

	public int getRight() {
		return (int) this.myRoot.myRight.myItem;
	}

	public int getDeeperLeft() {
		return (int) this.myRoot.myLeft.myLeft.myLeft.myRight.myItem;
	}

	public int getDeepRight() {
		return (int) this.myRoot.myRight.myRight.myItem;
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

	public int height() {
		if (myRoot != null) {
			return myRoot.height();
		}

		return 0;
	}

	public boolean isCompletelyBalanced() {
		if (myRoot == null) {
			return true;
		}
		return myRoot.isCompletelyBalanced();
	}

	public void fillSampleTree1() {
		myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
	}

	public void fillSampleTree2() {
		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d",
				new TreeNode("e"), new TreeNode("f")), null), new TreeNode("c"));
	}

	public void fillSampleTree3() {
		myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c",
				new TreeNode("d", new TreeNode("e"), new TreeNode("f")), null));
	}

	public void fillWithOne() {
		myRoot = new TreeNode("a");
	}

	public void fillSizeThree() {
		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d"), null),
				new TreeNode("c", new TreeNode("e"), null));
	}

	public void fillClockwise() {
		myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c",
				new TreeNode("d"), new TreeNode("e")));
	}

	public void fillSampleTree7() {
		myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c",
				new TreeNode("d"), new TreeNode("e")));
	}

	public void print() {
		if (myRoot != null) {
			myRoot.print(1);
		}
	}

	public boolean check() {
		alreadySeen = new ArrayList();
		try {
			isOK(myRoot);
			return true;
		} catch (IllegalStateException e) {
			return false;
		}
	}

	public static BinaryTree fibTree(int n) {
		BinaryTree result = new BinaryTree();
		result.myRoot = result.fibTreeHelper(n);
		return result;
	}

	private TreeNode fibTreeHelper(int n) {
		if (n == 0 || n == 1) {
			return new TreeNode((Integer) n, null, null);
		} else {
			return new TreeNode(
					((Integer) fibTree(n - 1).myRoot.myItem + (Integer) fibTree(n - 2).myRoot.myItem),
					fibTreeHelper(n - 1), fibTreeHelper(n - 2));
		}
	}

	public void optimize() {
		optimizeHelper(myRoot);

	}

	private static boolean isInt(String item) {
		try {
			Integer.parseInt(item);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private static void optimizeHelper(TreeNode root) {
		String castItem = (String) root.myItem;
		if (root.myLeft == null && root.myRight == null) {
			if (isInt(castItem)) {
				root.myItem = Integer.parseInt(castItem);
			} else {
				root.myItem = (String) root.myItem;
			}
		} else {
			String castLeftItem = (String) root.myLeft.myItem;
			String castRightItem = (String) root.myRight.myItem;

			if (castItem.equals("+")) {
				if (isInt(castLeftItem) && isInt(castRightItem)) {
					root.myItem = (Integer.parseInt(castLeftItem) + Integer
							.parseInt(castRightItem)) + "";
					root.myLeft = null;
					root.myRight = null;
				} else {
					optimizeHelper(root.myLeft);
					optimizeHelper(root.myRight);
				}
			} else if (castItem.equals("*")) {
				if (isInt(castLeftItem) && isInt(castRightItem)) {
					root.myItem = (Integer.parseInt(castLeftItem) * Integer
							.parseInt(castRightItem)) + "";
					
					root.myLeft = null;
					root.myRight = null;
				} else {
					
					optimizeHelper(root.myLeft);
					optimizeHelper(root.myRight);
					System.out.println("got here in multiplication");
				}
			}
		}

	}
	// Contains nodes already seen in the traversal.
	private ArrayList alreadySeen;

	private void isOK(TreeNode t) throws IllegalStateException {
		if (t == null) {
			return;
		}
		if (alreadySeen.contains(t.myItem)) {
			throw new IllegalStateException();
		} else {
			alreadySeen.add(t.myItem);
			if (t.myLeft != null) {
				isOK(t.myLeft);
			}
			if (t.myRight != null) {
				isOK(t.myRight);
			}
		}
	}
	
	public static BinaryTree exprTree(String s) {
		BinaryTree result = new BinaryTree();
		result.myRoot = result.exprTreeHelper(s);
		return result;
	}


	private TreeNode exprTreeHelper(String expr) {
		if (expr.charAt(0) != '(') {
			return new TreeNode(expr);
		} else {
			// expr is a parenthesized expression.
			// Strip off the beginning and ending parentheses,
			// find the main operator (an occurrence of + or * not nested
			// in parentheses, and construct the two subtrees.
			int nesting = 0;
			int opPos = 0;
			for (int k = 1; k < expr.length() - 1; k++) {
				if (expr.charAt(k) == '(') {
					nesting++;
				}
				if (expr.charAt(k) == ')') {
					nesting--;
				}
				if ((expr.charAt(k) == '*' || expr.charAt(k) == '+')
						&& nesting == 0) {
					opPos = k;

				}
			}
			String opnd1 = expr.substring(1, opPos);
			String opnd2 = expr.substring(opPos + 1, expr.length() - 1);
			String op = expr.substring(opPos, opPos + 1);
			System.out.println("expression = " + expr);
			System.out.println("operand 1  = " + opnd1);
			System.out.println("operator   = " + op);
			System.out.println("operand 2  = " + opnd2);
			System.out.println();
			return new TreeNode(op, exprTreeHelper(opnd1),
					exprTreeHelper(opnd2));
		}
	}

	public static void main(String[] args) {
		BinaryTree t;
		t = new BinaryTree();
		print(t, "the empty tree");
		t.fillSampleTree1();
		print(t, "sample tree 1");
		t.fillSampleTree2();
		print(t, "sample tree 2");
		t.fillSampleTree3();
		print(t, "sample tree 3");
	}

	private static void print(BinaryTree t, String description) {
		System.out.println(description + " in preorder");
		t.printPreorder();
		System.out.println(description + " in inorder");
		t.printInorder();
		System.out.println();
	}

	private static class TreeNode {

		public Object myItem;
		public TreeNode myLeft;
		public TreeNode myRight;
		public ArrayList<TreeNode> myNodes;

		public TreeNode(Object obj) {
			myItem = obj;
			myLeft = myRight = null;
		}

		public TreeNode(Object obj, TreeNode left, TreeNode right) {
			myNodes = new ArrayList<TreeNode>();
			myItem = obj;
			myLeft = left;
			myRight = right;
			myNodes.add(left);
			myNodes.add(right);

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

		private int height() {
			if (myItem == null) {
				return 0;
			} else if (myLeft == null && myRight == null) {
				return 1;
			} else {
				int bestSoFar = 1;
				for (TreeNode v : myNodes) {
					if (v != null) {
						bestSoFar = Math.max(v.height(), bestSoFar);
					}
				}
				return bestSoFar + 1;
			}
		}

		private boolean isCompletelyBalanced() {
			boolean leftBalance = false;
			boolean rightBalance = false;
			if (myLeft == null && myRight != null) {
				return false;
			} else if (myLeft != null && myRight == null) {
				return false;
			} else if (myLeft != null) {
				leftBalance = myLeft.isCompletelyBalanced();
			}
			if (myRight != null) {
				rightBalance = myRight.isCompletelyBalanced();
			}
			if (leftBalance != rightBalance) {
				return false;
			} else {
				return true;
			}
		}

		// In TreeNode
		private static final String indent1 = "    ";

		private void print(int indent) {
			if (myRight != null) {
				myRight.print(indent + 1);
			}

			println(myItem, indent);
			if (myLeft != null) {
				myLeft.print(indent + 1);
			}
		}

		private static void println(Object obj, int indent) {
			for (int k = 0; k < indent; k++) {
				System.out.print(indent1);
			}
			System.out.println(obj);
		}

	}
}
