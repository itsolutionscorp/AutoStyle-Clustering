import java.util.*;

public class BST {
    BSTNode myRoot;
    
    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    /**
     * transfers the first n elements returned by the iterator to a BSTNode
     *  
     * @param iter
     *     iterator that provides sorted elems to put into the BST
     *     
     * @param n
     *     number of element to handle
     *     
     * @return 
     *     a balanced with n elems from the iter
     */
    private BSTNode linkedListToTree (Iterator iter, int n) {
        // TODO Your code here
    	if(n==0){
    		return null;
    	}
    	if(n==1){
    		BSTNode node = new BSTNode();
    		node.myItem = iter.next();
    		return node;
    	}
    	int root = n/2+1;
    	BSTNode t = new BSTNode();
    	t.myLeft = linkedListToTree(iter, root-1);
    	t.myItem = iter.next();
    	t.myRight= linkedListToTree(iter, n-root);
        return t;
    }

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
    }
    public static void main(String [] args){
    	LinkedList<String> lst = new LinkedList<String>();
    	lst.add("a");
    	lst.add("b");
    	lst.add("c");
    	lst.add("d");
    	lst.add("e");
    	lst.add("f");
    	lst.add("g");
    	lst.add("h");
    	BST tree = new BST(lst);
    	System.out.println(tree.myRoot.myItem);

    	System.out.println(tree.myRoot.myLeft.myItem);
    	System.out.println(tree.myRoot.myLeft.myLeft.myItem);
    	System.out.println(tree.myRoot.myLeft.myLeft.myLeft.myItem);
    	System.out.println(tree.myRoot.myLeft.myRight.myItem);
    	System.out.println(tree.myRoot.myRight.myItem);
    	System.out.println(tree.myRoot.myRight.myLeft.myItem);
    	System.out.println(tree.myRoot.myRight.myRight.myItem);

    	
    }
}
