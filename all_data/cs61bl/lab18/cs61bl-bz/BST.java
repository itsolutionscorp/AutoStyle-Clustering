import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }
    
     

    // In this part i try to use bst with making a new element
    //In this LinkedListToTree method we are finding our middle item in the list and searching our left and rights.
    //We are using recursion to do that.
    private static BSTNode linkedListToTree (Iterator iter, int n) {
    
    	
            // base case: empty tree -> empty list
            if (iter==null) return(null);
            
            // Recursively do the subtrees (leap of faith!)
            Iterator aList = linkedListToTree(iter.small);
            Iterator bList = linkedListToTree(iter.large);
             
            
            // Make the single root node into a list length-1
            // in preparation for the appending
            myRoot.small = myRoot;
            myRoot.large = myRoot;
            
            // At this point we have three lists, and it's
            // just a matter of appending them together
            // in the right order (aList, root, bList)
            aList = append(aList, myRoot);
            aList = append(aList, bList);
            
            return(root);
        }
    	
    
        return null;
        
        
    }

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
    }
}
