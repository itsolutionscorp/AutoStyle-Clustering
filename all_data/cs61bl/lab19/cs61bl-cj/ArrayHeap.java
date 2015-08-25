import java.util.ArrayList;

/**
 * A Generic heap class. Unlike Java's priority queue, this heap doesn't just
 * store Comparable objects. Instead, it can store any type of object
 * (represented by type T), along with a priority value. Why do it this way? It
 * will be useful later on in the class...
 */
public class ArrayHeap<T> {
    private ArrayList<Node> contents = new ArrayList<Node>();

    /**
     * Inserts an item with the given priority value. This is enqueue, or offer.
     */
    public void insert(T item, double priority) {
        if (contents.isEmpty()) {
            contents.add(new Node(item, 0));
            contents.add(new Node(item, priority));
            return;
        }
        contents.add(new Node(item, priority));
        int indexOfnew = contents.size() - 1;
        while (indexOfnew > 0) {
            if (contents.get(getParentOf(indexOfnew)).myPriority <= priority) {
                return;
            } else if (contents.get(getParentOf(indexOfnew)).myPriority > priority) {
                bubbleUp(indexOfnew);
                indexOfnew = getParentOf(indexOfnew);
            }       
        }
    }

    /**
     * Returns the Node with the smallest priority value, but does not remove it
     * from the heap.
     */
    public Node peek() {
        if (contents.size() == 1) {
            System.out.println("Empty already");
            return null;
        }
        return contents.get(1);
    }

    /**
     * Returns the Node with the smallest priority value, and removes it from
     * the heap. This is dequeue, or poll.
     */
    public Node removeMin() {
        if (contents.size() == 1) {
            System.out.println("Empty already");
            return null;
        } else {
            Node min = contents.get(1);
            int lastIndex = contents.size() - 1;
            swap(1, lastIndex);
            contents.remove(lastIndex);

            int index = 1;
            while (getLeftOf(index) < contents.size()) {
                if (contents.get(index).myPriority > contents.get(min(getLeftOf(index), getRightOf(index))).myPriority ) {              
                    //int temp = min(getLeftOf(index), getRightOf(index));
                    bubbleDown(index);
                    index = min(getLeftOf(index), getRightOf(index));
                } else if (contents.get(index).myPriority <= contents.get(min(getLeftOf(index), getRightOf(index))).myPriority) {
                    return min;
                }
            }
            return min;
        }
//      // TODO Complete this method!

    }

    /**
     * Change the node in this heap with the given item to have the given
     * priority. For this method only, you can assume the heap will not have two
     * nodes with the same item. Check for item equality with .equals(), not ==
     */
    public void changePriority(T item, double priority) {
        // TODO Complete this method!
        for (Node n : contents) {       
            if (n.myItem.equals(item)) {
                n.myPriority = priority;
                break;
            }
        }
    }

    /**
     * Prints out the heap sideways.
     */
    @Override
    public String toString() {
        return toStringHelper(1, "");
    }

    /* Recursive helper method for toString. */
    private String toStringHelper(int index, String soFar) {
        if (getNode(index) == null) {
            return "";
        } else {
            String toReturn = "";
            int rightChild = getRightOf(index);
            toReturn += toStringHelper(rightChild, "        " + soFar);
            if (getNode(rightChild) != null) {
                toReturn += soFar + "    /";
            }
            toReturn += "\n" + soFar + getNode(index) + "\n";
            int leftChild = getLeftOf(index);
            if (getNode(leftChild) != null) {
                toReturn += soFar + "    \\";
            }
            toReturn += toStringHelper(leftChild, "        " + soFar);
            return toReturn;
        }
    }

    private Node getNode(int index) {
        if (index >= contents.size()) {
            return null;
        } else {
            return contents.get(index);
        }
    }

    private void setNode(int index, Node n) {
        // In the case that the ArrayList is not big enough
        // add null elements until it is the right size
        while (index + 1 >= contents.size()) {
            contents.add(null);
        }
        contents.set(index, n);
    }

    /**
     * Swap the nodes at the two indices.
     */
    private void swap(int index1, int index2) {
        Node node1 = getNode(index1);
        Node node2 = getNode(index2);
        this.contents.set(index1, node2);
        this.contents.set(index2, node1);
    }

    /**
     * Returns the index of the node to the left of the node at i.
     */
    private int getLeftOf(int i) {
        // TODO Complete this method!
        return (i * 2);
    }

    /**
     * Returns the index of the node to the right of the node at i.
     */
    private int getRightOf(int i) {
        // TODO Complete this method!
        return (i * 2 + 1); 
    }

    /**
     * Returns the index of the node that is the parent of the node at i.
     */
    private int getParentOf(int i) {
        // TODO Complete this method!
        if (i % 2 == 0) {
            return i / 2;
        } else {
            return (i - 1) / 2;
        }
    }

    /**
     * Adds the given node as a left child of the node at the given index.
     */
    private void setLeft(int index, Node n) {
        // TODO Complete this method!
        setNode(getLeftOf(index), n);
    }

    /**
     * Adds the given node as the right child of the node at the given index.
     */
    private void setRight(int index, Node n) {
        // TODO Complete this method!
        setNode(getRightOf(index), n);
    }

    /**
     * Bubbles up the node currently at the given index.
     */
    private void bubbleUp(int index) {
        // TODO Complete this method!
        swap(index, getParentOf(index));
    }


    /**
     * Bubbles down the node currently at the given index.
     */
    private void bubbleDown(int index) {
        // TODO Complete this method!
        swap(min(getLeftOf(index), getRightOf(index)), index);
    }

    /**
     * Returns the index of the node with smaller priority. Precondition: Not
     * both of the nodes are null.
     */
    private int min(int index1, int index2) {
        Node node1 = getNode(index1);
        Node node2 = getNode(index2);
        if (node1 == null) {
            return index2;
        } else if (node2 == null) {
            return index1;
        } else if (node1.myPriority < node2.myPriority) {
            return index1;
        } else {
            return index2;
        }
    }

    public class Node {
        private T myItem;
        private double myPriority;

        private Node(T item, double priority) {
            myItem = item;
            myPriority = priority;
        }

        public T item() {
            return myItem;
        }

        public double priority() {
            return myPriority;
        }

        @Override
        public String toString() {
            return item().toString() + ", " + priority();
        }
    }

    public static void main(String[] args) {
        ArrayHeap<String> heap = new ArrayHeap<String>();
//      heap.insert("g", 7);
//      heap.insert("a", 9);
      heap.insert("b", 8);
        heap.insert("c", 3);
        heap.insert("i", 19);
        heap.insert("g", 7);
        heap.insert("d", 4);
        heap.insert("a", 1);
        heap.insert("h", 8);
        heap.insert("e", 5);
        heap.insert("b", 2);
//      heap.insert("c", 3);
//      heap.insert("d", 4);

        System.out.println(heap);
        System.out.print(heap.removeMin().myPriority);
        System.out.print("  ");
        System.out.print(heap.removeMin().myPriority);
        System.out.print("  ");
        System.out.print(heap.removeMin().myPriority);
        System.out.print("  ");
        System.out.print(heap.removeMin().myPriority);
        System.out.print("  ");
        System.out.print(heap.removeMin().myPriority);
        System.out.print("  ");
        System.out.print(heap.removeMin().myPriority);
        System.out.print("  ");
        System.out.print(heap.removeMin().myPriority);
        System.out.print("  ");
        System.out.print(heap.removeMin().myPriority);
        System.out.print("  ");
        System.out.print(heap.removeMin().myPriority + "\n\n\n");
//        System.out.println(heap.peek());
        
        
        ArrayHeap<Integer> sort = new ArrayHeap<Integer>();
        sort.insert(6, 6);
        sort.insert(3, 3);
        sort.insert(7, 7);
        sort.insert(16, 16);
        sort.insert(36, 36);
        sort.insert(26, 26);
        sort.insert(46, 46);
        sort.insert(64, 64);
        sort.insert(612, 612);
        sort.insert(63, 63);
        
        System.out.print(sort.removeMin().myItem);
        System.out.print("  ");
        System.out.print(sort.removeMin().myItem);
        System.out.print("  ");
        System.out.print(sort.removeMin().myItem);
        System.out.print("  ");
        System.out.print(sort.removeMin().myItem);
        System.out.print("  ");
        System.out.print(sort.removeMin().myItem);
        System.out.print("  ");
        System.out.print(sort.removeMin().myItem);
        System.out.print("  ");
        System.out.print(sort.removeMin().myItem);
        System.out.print("  ");
        System.out.print(sort.removeMin().myItem);
        System.out.print("  ");
        System.out.print(sort.removeMin().myItem);
        System.out.print("  ");
        System.out.print(sort.removeMin().myItem);
        
        
    }

}