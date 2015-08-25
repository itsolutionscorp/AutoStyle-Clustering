import java.util.*;
 
public class BST {
    BSTNode myRoot;
 
    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }
 
    // Your comment here
    private BSTNode linkedListToTree (Iterator iter, int n) {
        // TODO Your code here
        if(n <= 0)
            return null;
        else{
            BSTNode left = linkedListToTree(iter, n/2);
            BSTNode root = new BSTNode();
            root.myItem = iter.next();
            root.myLeft = left;
            root.myRight = linkedListToTree(iter, n/2-1);
            return root;
        }
    }
 
    public class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
         
        public BSTNode(Object myItem){
            this.myItem = myItem;
            myLeft = null;
            myRight = null;
        }
         
        public BSTNode(){
            this.myItem = null;
            myLeft = null;
            myRight = null;
        }
         
        public BSTNode(Object myItem, BSTNode myLeft, BSTNode myRight){
            this.myItem = myItem;
            this.myLeft = myLeft;
            this.myRight = myRight;
        }
    }
}