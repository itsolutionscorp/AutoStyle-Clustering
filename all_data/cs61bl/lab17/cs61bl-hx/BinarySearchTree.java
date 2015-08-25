import apple.laf.JRSUIUtils;

/**
 * Created by Mitchell on 7/21/15.
 */
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

    public BinarySearchTree(){
        super();
    }

    public BinarySearchTree(TreeNode t){
        super(t);
    }

    public boolean contains(T key){
        return contains(myRoot, key);
    }

    private boolean contains (TreeNode t, T key){
        if(t!=null) {
            if (key.compareTo(t.myItem) == 0) {
                return true;
            }
            if (key.compareTo(t.myItem) < 0) {
//                if (t.myLeft == null) {
//                    return false;
//                }
                return contains(t.myLeft, key);
            }
            if (key.compareTo(t.myItem) > 0) {
//                if (t.myRight == null) {
//                    return false;
//                }
                return contains(t.myRight, key);
            }
        }
        return false;
    }


    public void add(T key){
        if (contains(key)!= true){
            myRoot = add(myRoot, key);
        }
    }

    private TreeNode add(TreeNode t, T key) {
        if (t == null) {
            return new TreeNode(key);
        } else if (key.compareTo(t.myItem) < 0) {
            t.size++;
            t.myLeft = add(t.myLeft, key);
            return t;
        } else {
            t.size++;
            t.myRight = add(t.myRight, key);
//            t.myRight.size = t.size-1;
            return t;
        }
    }
//
    public Comparable kthLargest (int k) {
        return largestHelper(myRoot, k);
    }

    private Comparable largestHelper(TreeNode t, int k){
            if(t.myRight == null&&t.myLeft==null){
                return t.myItem;
            }
            if(k==t.size) {
                return t.myItem;
            }
            if(t.myRight != null) {
                if (k - t.myRight.size == 0) {
                    return t.myItem;
                }
                if (k - t.myRight.size > 0) {
                    return largestHelper(t.myLeft, k - t.myRight.size+1);
                }
                if (k - t.myRight.size < 0) {
                    return largestHelper(t.myRight, k);
                }
            }
        if (t.myLeft != null) {
            if(k==0){
                return t.myItem;
            }
//            if (t.myLeft.size - k > 0) {
//                return largestHelper(t.myLeft, t.myLeft.size -k);
//            }
            return largestHelper(t.myLeft, k-1);
        }
            return null;

    }


}
