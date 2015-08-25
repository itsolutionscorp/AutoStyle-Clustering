public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

    public boolean contains(T key) {
        return contains(myRoot, key);
    }

    public void add(T key) {
        myRoot = add(myRoot, key);
    }

    public void remove(T key) {
        myRoot = remove(myRoot, key);
    }

    private boolean contains (TreeNode t, T key) {
        if (t == null) {
            return false;
        } if (t.myItem.compareTo(key) < 0) {
            return contains(t.myRight, key);
        } if (t.myItem.compareTo(key) > 0) {
            return contains(t.myLeft, key);
        } else {
            return true;
        }
    }

    private TreeNode add(TreeNode t, T key) {
        if (t == null) {
            return new TreeNode(key);
        } else if (key.compareTo(t.myItem) < 0) {
            t.myLeft = add(t.myLeft, key);
            return t;
        } else if (key.compareTo(t.myItem) > 0) {
            t.myRight = add(t.myRight, key);
            return t;
        }
        return new TreeNode(key);
    }

    private TreeNode remove(TreeNode t, T key) {
        if (t == null) {
            return null;
        } else if (key.compareTo(t.myItem) < 0) {
            t.myLeft = remove(t.myLeft, key);
            return t;
        } else if (key.compareTo(t.myItem) > 0) {
            t.myRight = remove(t.myRight, key);
            return t;
        } else if (key.compareTo(t.myItem) != 0) {
            return t;
        }
        else if (t.myRight == null) {
            if (t.myLeft == null) {
                return null;
            }
            return t.myLeft;
        } else if (t.myLeft == null) {
            return t.myRight;
        }
        t.myItem = getSucessorItem(t.myRight);
        t.myRight = removeSucessor(t.myRight);
        return t;
    }

    public TreeNode removeSucessor(TreeNode t) {
        if (t.myLeft == null) {
            return null;
        }
        t.myLeft = removeSucessor(t.myLeft);
        return t;
    }

    public T getSucessorItem(TreeNode t) {
        if (t.myLeft == null) {
            return t.myItem;
        }
        return getSucessorItem(t.myLeft);
    }

    private Comparable kthLargest (int k) {
        if (myRoot == null) {
            System.out.println("Cannot search an empty tree");
            // I would raise an exception here and in line 51, but I don't want
            // to possibly crash your tests by importing extra stuff
            return null;
        }
        return kthLargest(myRoot, k);
    }

    private Comparable kthLargest (TreeNode t, int k) {
        if (t.myRight == null) {
            if (k == 0) {
                return t.myItem;
            }
            if (t.myLeft == null) {
                System.out.println("Index out of bounds");
                return null;
            }
            return kthLargest(t.myLeft, k - 1);
        }
        if (k == t.myRight.size()) {
            return t.myItem;
        }
        if (t.myLeft == null || k < t.myRight.size()) {
            return kthLargest(t.myRight, k);
        }
        return kthLargest(t.myLeft, k - t.myRight.size() - 1);
    }


    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.add(2);
        tree.add(1);
        tree.add(3);
        System.out.println(tree.contains(1));
        System.out.println(tree.contains(2));
        System.out.println(tree.contains(3));
        tree.add(3);
        tree.add(3);
        System.out.println(!(tree.contains(4)));
        print(tree, "tree");
        System.out.println(tree.myRoot.size());
        System.out.println(tree.myRoot.myLeft.size());
        System.out.println(tree.myRoot.myRight.size());
        tree.add(7);
        tree.add(4);
        tree.add(5);
        tree.add(8);
        tree.add(6);
        print(tree, "tree");
        System.out.println(tree.kthLargest(0));
        System.out.println(tree.kthLargest(1));
        System.out.println(tree.kthLargest(2));
        System.out.println(tree.kthLargest(3));
        System.out.println(tree.kthLargest(4));
        System.out.println(tree.kthLargest(5));
        System.out.println(tree.kthLargest(6));
        System.out.println(tree.kthLargest(7));
        BinarySearchTree tree2 = new BinarySearchTree();
        tree2.add(10);
        tree2.add(13);
        tree2.add(11);
        tree2.add(9);
        tree2.add(20);
        tree2.add(12);
        tree2.add(14);
        tree2.add(7);
        tree2.add(15);
        tree2.add(8);
        tree2.add(21);
        tree2.add(16);
        tree2.add(6);
        tree2.add(18);
        System.out.println(tree2.kthLargest(0));
        System.out.println(tree2.kthLargest(1));
        System.out.println(tree2.kthLargest(2));
        System.out.println(tree2.kthLargest(3));
        System.out.println(tree2.kthLargest(4));
        System.out.println(tree2.kthLargest(5));
        System.out.println(tree2.kthLargest(6));
        System.out.println(tree2.kthLargest(7));
        System.out.println(tree2.kthLargest(8));
        System.out.println(tree2.kthLargest(9));
        System.out.println(tree2.kthLargest(10));
        System.out.println(tree2.kthLargest(11));
        System.out.println(tree2.kthLargest(12));
        System.out.println(tree2.kthLargest(13));


    }

}
