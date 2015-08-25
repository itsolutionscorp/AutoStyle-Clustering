import java.util.*;


public class BST {
	BSTNode myRoot;

	public BST(LinkedList list) {
		myRoot = linkedListToTree(list.iterator(), list.size());
	}

	// Your comment here
	/**
	 * This method takes in an iterator for an ordered linked list and an
	 * integer representing the size of the linkedList. It returns a binary
	 * search tree node which represents the root node of a balanced BST. It
	 * will be at least almost balanced.
	 * 
	 * @param iter
	 *            - Iterator of a balacned linked list
	 * @param n
	 *            - Length of the linkedList
	 * @return - BST Node that will represent the root of an almost balanced
	 *         BST.
	 */
	private static BSTNode linkedListToTree (Iterator iter, int n) {
        // TODO Your code here
		ArrayList<Object> inOrder = new ArrayList<Object>();
		ArrayList<Object> preOrder;
		while (iter.hasNext()) {
			inOrder.add(iter.next());
		}
		preOrder = preOrderHelper(inOrder);
		return binaryTreeConstructorHelper(preOrder, inOrder);
		
    }

	private static ArrayList<Object> preOrderHelper(List inOrder) {
		int size = inOrder.size();
		if (size == 0) {
			return null;
		}
		if (size == 1) {
			ArrayList<Object> toReturn = new ArrayList<Object>();
			toReturn.add(inOrder.get(0));
			return toReturn;
		}
//		if (size % 2 == 0) {
		ArrayList<Object> toReturn = new ArrayList<Object>();
		toReturn.add(inOrder.get(size / 2));
		List leftTemp = inOrder.subList(0,
				size / 2);
		ArrayList<Object> left = new ArrayList<Object>();
		for (Object obj : leftTemp){
			left.add(obj);
		}
		List rightTemp = inOrder.subList(size / 2 + 1, size);
		ArrayList<Object> right = new ArrayList<Object>();
		for (Object obj : rightTemp) {
			right.add(obj);
		}
		ArrayList<Object> leftPreOrder = preOrderHelper(left);
		if (leftPreOrder != null) {
			for (Object obj : leftPreOrder) {
				toReturn.add(obj);
			}
		}
		ArrayList<Object> rightPreOrder = preOrderHelper(right);
		if (rightPreOrder != null) {
			for (Object obj : rightPreOrder) {
				toReturn.add(obj);
			}
		}
		return toReturn;

	}
	
    public static BSTNode binaryTreeConstructorHelper(ArrayList<Object> list1, ArrayList<Object> list2) {
    	if (list1.size() == 0) {
    		return null;
    	}
    	Object rootItem = list1.get(0);
    	list1.remove(rootItem);
    	int indexOfRoot = list2.indexOf(rootItem);
    	ArrayList<Object> leftList2 = new ArrayList<Object>();
    	ArrayList<Object> rightList2 = new ArrayList<Object>();
    	ArrayList<Object> leftList1 = new ArrayList<Object>();
    	ArrayList<Object> rightList1 = new ArrayList<Object>();

    	
    	for (int i = 0; i < indexOfRoot; i++) {
    		leftList2.add(list2.get(i));
    	}
    	for (int i = indexOfRoot + 1; i < list2.size(); i++) {
    		rightList2.add(list2.get(i));
    	}
    	int indexOfSplit = leftList2.size();
    	for (int i = 0; i < indexOfSplit; i++) {
    		leftList1.add(list1.get(i));
    	}
    	for (int i = indexOfSplit; i < list1.size(); i++) {
    		rightList1.add(list1.get(i));
    	}

    	return new BSTNode(rootItem, binaryTreeConstructorHelper(leftList1, leftList2), binaryTreeConstructorHelper(rightList1, rightList2));
    	
    }

	private static class BSTNode {
		Object myItem;
		BSTNode myLeft;
		BSTNode myRight;
		//BSTNode myParent;
		
		public BSTNode(Object item, BSTNode left, BSTNode right) {
			myItem = item;
			myLeft = left;
			myRight = right;
		}
		
	}
	
	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		for (int i = 1; i < 10; i++) {
			list.add(i);
		}
		BST myTree = new BST(list);
	}
}
