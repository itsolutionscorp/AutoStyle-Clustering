import java.util.*;

public class BST {
    BSTNode myRoot;
    public BST(){
    }

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }

    // I think this method is kind of tricky if BSTNode is not a static class.
    // But recursive part is easy to understand
    private static BSTNode linkedListToTree (Iterator iter, int n) {
        // TODO Your code here
        BST t = new BST();
        BSTNode g = t.new BSTNode();
        int middle = n/2;
        int index = 0;
        LinkedList<Object> myLeftList = new LinkedList<Object>();
        LinkedList<Object> myRightList = new LinkedList<Object>();
        if(n == 1){
        	g.myItem = iter.next();
        	return g;
        }
        while(iter.hasNext()){
            if(index < middle){
                myLeftList.add(iter.next());
            }
            else if(index == middle){
               g.myItem = iter.next();
          }
            else{
                myRightList.add(iter.next());
            }
            index++;
        }
//        System.out.print(myLeftList);
//        System.out.print(myRightList);
//        System.out.print(middle);
        if(myLeftList.size()!=0){
        	g.myLeft = linkedListToTree(myLeftList.iterator(), myLeftList.size());
        }
        if(myRightList.size()!=0){
        	g.myRight = linkedListToTree(myRightList.iterator(),myRightList.size());
        }
        return g;
    }
    

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;
    }
}
