public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

    public boolean contains(T key) {
        return contains(myRoot, key);
    }

    public void add(T key) {
        myRoot = add(myRoot, key);
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
        return t;
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
        tree.add(3);
        tree.add(3);
        tree.add(3);
        tree.add(3);
        tree.add(3);
        tree.add(3);
        tree.add(3);
        System.out.println(!(tree.contains(4)));
        print(tree, "tree");
    }

}
