import java.util.*;

public class BST {
    BSTNode myRoot;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // Your comment here
    private BSTNode linkedListToTree (Iterator iter, int n) {
        // TODO Your code here
        BSTNode rtnNode = new BSTNode();
        if (n == 0){
            return null;
        }
        if(n==1){
            rtnNode.myItem = iter.next();
            return rtnNode;
        }
//        if (n==3){
//            rtnNode.myLeft = iter.next();
//            rtnNode.myItem = iter.next();
//            rtnNode.myRight = iter.next();
//            return rtnNode;
//        }
        LinkedList left = new LinkedList();
        LinkedList right = new LinkedList();
        Object rtn;
            for (int i = 1; i < (n/ 2)+1; i++) {
                left.add(iter.next());
            }
            rtn = iter.next();
            for (int i = (n/2)+1; i < n; i++) {
                right.add(iter.next());
            }
        rtnNode.myItem = rtn;
        rtnNode.myLeft = linkedListToTree(left.iterator(), left.size());
        rtnNode.myRight = linkedListToTree(right.iterator(), right.size());
        return rtnNode;
    }

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
    }
}
