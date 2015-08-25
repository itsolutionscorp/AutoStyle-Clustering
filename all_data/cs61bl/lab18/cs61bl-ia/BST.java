import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }
    /**
     * just to check if makeTree works.
     */
    public void printInorder() {
        if (myRoot == null) {
            System.out.println("(empty tree)");
        } else {
            myRoot.printInorder();
            System.out.println();
        }
    }
	public static boolean isBalanced(BSTNode root) {
		if (root == null)
			return true;
 
		if (getHeight(root) == -1)
			return false;
 
		return true;
	}
	public static int getHeight(BSTNode root) {
		if (root == null)
			return 0;
 
		int left = getHeight(root.myLeft);
		int right = getHeight(root.myRight);
 
		if (left == -1 || right == -1)
			return -1;
 
		if (Math.abs(left - right) > 1) {
			return -1;
		}
 
		return Math.max(left, right) + 1;
 
	}
    

	/**
	 * Constructs the tree bottom up in O(N) time. Splits the list in half 
	 * and constructs the left subtree by recursively calling linkedListToTree
	 * to the first half of the list.
	 * Then attach this subtree to the root. Do the same for the upper half of the
	 * list to construct the right subtree.
	 * @param iter
	 * 			Iterator of the linkedlist
	 * @param n
	 * 			size of the linkedlist
	 * @return BSTNode
	 */
    private BSTNode linkedListToTree (Iterator iter, int n) {
        // TODO Your code here
//    	ArrayList<T> link = new ArrayList<T>();
    	ArrayList<Integer> link = new ArrayList<Integer>();
    	BSTNode result = new BSTNode();
    	int leftSize = n/2;
    	int rightSize = n - leftSize - 1; 
    	while (iter.hasNext()){
    		link.add((Integer) iter.next());
    	}
    	if (n == 0){
    		return result; 
    	}
    	
    	return result; 
    	
//        if n == 0:
//            return BinaryTree.empty, s
//        left_size = (n-1)//2
//        right_size = n - left_size - 1
//        right, rest = partial_tree(rest, right_size)
//        return BinaryTree(entry, left, right), rest
//        "*** YOUR CODE HERE ***"
//        left, rest = partial_tree(s, left_size)
//        entry, rest = rest.first, rest.rest
//    	System.out.println(link);
//    	BSTNode interest = new BSTNode();
//    	int count = 0;
//    	return interest; 
//    	if (n == 0){
//    		return interest;
//    	}
//    	if (n == 1){
//    		interest.myItem = iter.next();
//    	}
//    	if (n == 2){
//    		interest.myItem = iter.next();
//    		interest.myRight.myItem = iter.next(); 
//    	}
//    	if (n > 1) {
//	    	interest.myLeft = linkedListToTree(iter, (n / 2) - 1);
//	    	while (iter.hasNext() && count < n/2) {
//	    			interest.myItem = iter.next();
//	    			count++;
//	    		
//	    	}
//	    	interest.myRight = linkedListToTree(iter, (n / 2));
//    	}
//    	return interest;
//        
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
    }
    
    public static void main(String [] args){

		LinkedList l3 = new LinkedList();
		l3.add(5);
		BST bst2 = new BST(l3);
		bst2.printInorder();
    	
    	LinkedList l4 = new LinkedList();
		l4.add(1);
		l4.add(2);
		BST bst3 = new BST(l4);
		bst3.printInorder();
		
    	LinkedList ll = new LinkedList();
		ll.add(1);
		ll.add(2);
		ll.add(3);
		ll.add(5);
		ll.add(6);
		ll.add(7);
		ll.add(8);
		BST bst = new BST(ll);
		bst.printInorder();
		//bst.printInorder();
		System.out.println(BST.isBalanced(bst.myRoot));
		
		
		LinkedList l2 = new LinkedList();
		l2.add(1);
		l2.add(2);
		l2.add(3);
		l2.add(4);
		l2.add(5);
		l2.add(6);
		l2.add(7);
		l2.add(8);
		BST bst1 = new BST(l2);
		bst1.printInorder();

    }
}
