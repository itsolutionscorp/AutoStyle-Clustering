
import java.util.ArrayList;
 
/**
 * A Generic heap class. Unlike Java's priority queue, this heap doesn't just
 * store Comparable objects. Instead, it can store any type of object
 * (represented by type T), along with a priority value. Why do it this way? It
 * will be useful later on in the class...
 */
public class ArrayHeap<T> {
    private ArrayList<Node> contents = new ArrayList<Node>();
    private int myIndex = 0;
    private int [] shortestDistances;
 
     
    /**
     * Inserts an item with the given priority value. This is enqueue, or offer.
     */
    public void insert(T item, double priority) {
        //contents.add(new Node(item, priority));
        /*if(contents.size() == 0)
            setNode(contents.size()+1 , new Node(item, priority));
        else
            setNode(contents.size(), new Node(item, priority));
        //System.out.println(contents);
        if(contents.size() >= 1)
            bubbleUp(contents.size()-1);*/
        if(contents.size() == 0){
            setNode(0, null);
            myIndex++;
        }else{
            //System.out.println(contents);
            myIndex = contents.size();
        }
         
        /*contents.add(null);
        //System.out.println(contents);
        int myindex = contents.size();*/
        setNode(myIndex, new Node(item, priority));
        bubbleUp(myIndex);
         
        //System.out.println(contents);
 
    }
     
    public void setDistancesSize(int i){
        shortestDistances = new int[i];
    }
     
    public boolean isEmpty(){
        return (contents.size()==1);
    }
     
    public int shortestDistance(int i){
        return shortestDistances[i];
    }
     
    public void setShortestDistance(int index, int content){
        shortestDistances[index]=content;
    }
    /**
     * Returns the Node with the smallest priority value, but does not remove it
     * from the heap.
     */
    public Node peek() {
        // TODO Complete this method!
        return contents.get(1);
    }
 
    /**
     * Returns the Node with the smallest priority value, and removes it from
     * the heap. This is dequeue, or poll.
     */
    public Node removeMin() {
        // TODO Complete this method!
        /*Node lastNode = getNode(contents.size()-1);
        Node firstNode = getNode(0);
        System.out.println(firstNode);
        swap(0, contents.size()-1);
        contents.remove(contents.size()-1);
        bubbleDown(0);
        return firstNode;*/
        Node removed = null;
        if(contents.size() > 2){
            removed = getNode(1);
            Node lastNode = contents.remove(contents.size()-1);
            setNode(1,lastNode);
            //System.out.println(contents.get(1));
            bubbleDown(1);
        }else if(contents.size() == 2){
            removed = contents.remove(1);
        }
        return removed;
         
         
    }
 
    /**
     * Change the node in this heap with the given item to have the given
     * priority. For this method only, you can assume the heap will not have two
     * nodes with the same item. Check for item equality with .equals(), not ==
     */
    public void changePriority(T item, double priority) {
        // TODO Complete this method!
        for(int i = 1; i < contents.size(); i++){
            if(item.equals(contents.get(i).item())){
                if(priority > contents.get(i).myPriority){
                    contents.get(i).myPriority = priority;
                    bubbleDown(i);
                }else if(priority < contents.get(i).myPriority){
                    contents.get(i).myPriority = priority;
                    bubbleUp(i);
                }else{
                    contents.get(i).myPriority = priority;
                }
                 
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
        while (index + 1 > contents.size()) {
            contents.add(null);
        }
        contents.set(index, n);
    }
 
    /**
     * Swap the nodes at the two indices.
     */
    private void swap(int index1, int index2) {
        //System.out.println("In swap");
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
        return 2*i;
    }
 
    /**
     * Returns the index of the node to the right of the node at i.
     */
    private int getRightOf(int i) {
        // TODO Complete this method!
        return 2*i + 1;
    }
 
    /**
     * Returns the index of the node that is the parent of the node at i.
     */
    private int getParentOf(int i) {
        // TODO Complete this method!
        if(i > 0){
            return i/2;
        }return -1;
    }
     
    public void isPracticallyEmpty(){
         
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
        int temp = index;
        //System.out.prtln("In bubble up");
        while(temp > 1 && min(temp, getParentOf(temp)) == temp){
            //System.out.println("In the loop");
            swap(temp, getParentOf(temp));
            temp = getParentOf(temp);
            //System.out.println(temp);
        }
             
    }
 
    /**
     * Bubbles down the node currently at the given index.
     */
    private void bubbleDown(int index) {
        // TODO Complete this method!
        //System.out.print(this);
        int temp = index;
        //System.out.println(temp);
        //Case1: If both the right and left nodes are null, return
        if(getNode(getRightOf(temp))==null && getNode(getLeftOf(temp))==null)
            return;
         
        //Case2: If temp == min(temp,getRightOf(temp)) && temp == min(temp,getLeftOf(temp)), return
        //System.out.println(getRightOf(temp));
        //System.out.println(getLeftOf(temp));
         
         
        if(temp == min(temp,getRightOf(temp)) && temp == min(temp,getLeftOf(temp)))
            return;
        //Case3: If both Left and Right are NOT null, then go to the minimum of the two
        if(getNode(getRightOf(temp))!=null && getNode(getLeftOf(temp))!=null){
            //System.out.println(getNode(getRightOf(temp)).equals(getNode(getLeftOf(temp))));
            //System.out.println("Looking at both priorities " + getNode(getRightOf(temp)).priority() + " " + getNode(getLeftOf(temp)).priority());
            if(getNode(getRightOf(temp)).equals(getNode(getLeftOf(temp)))){
                //System.out.println("Left and Right equal.");
                if(temp != min(temp,getRightOf(temp))){
                    swap(temp,getRightOf(temp));
                    bubbleDown(getRightOf(temp));
                }
            }else{
                int temp2 = min(getRightOf(temp), getLeftOf(temp));
                swap(temp, min(getRightOf(temp), getLeftOf(temp)));
                //System.out.println(min(getRightOf(temp), getLeftOf(temp)));
                bubbleDown(temp2);
            }
        }else if(getNode(getRightOf(temp))!=null){//Case4: Only Right Node is not null
            //System.out.println("Looking at right priority " + getNode(getRightOf(temp)).priority());
            int temp2 = getRightOf(temp);
            swap(temp, temp2);
            bubbleDown(temp2);
        }else{//Case5: Only the Left Node is not null
            //System.out.println("Looking at left priority " + getNode(getLeftOf(temp)).priority());
            int temp2 = getLeftOf(temp);
            //System.out.println(getLeftOf(temp));
            swap(temp, temp2);
            //System.out.println(getLeftOf(temp));
            bubbleDown(temp2);
        }
         
         
         
        /*if(temp == min(temp, getRightOf(temp)) && temp == min(temp, getLeftOf(temp))){
            return;
        }
            //return;
        if(getNode(getRightOf(temp))!= null && getNode(getLeftOf(temp)) != null){
            System.out.println("Looking at both priorities " + getNode(getRightOf(temp)).priority() + " " + getNode(getLeftOf(temp)).priority());
                System.out.println("Left and Right equal.");
            if(temp == min(temp, getRightOf(temp))){
                swap(temp, getRightOf(temp));
                bubbleDown(getRightOf(temp));
                }return;
            }else{
                if(temp != min(temp, min(getRightOf(temp), getLeftOf(temp)))){
                    swap(temp,min(getRightOf(temp), getLeftOf(temp)));
                    bubbleDown(min(getRightOf(temp), getLeftOf(temp)));
                }
            }
        }else if(getNode(getLeftOf(temp))!= null && temp != min(temp,getLeftOf(temp))){
            System.out.println("Looking at Left's priority " + getNode(getLeftOf(temp)).priority());
            swap(temp,getLeftOf(temp));
            bubbleDown(getLeftOf(temp));
        }else if(getNode(getRightOf(temp)) != null && temp != min(temp,getRightOf(temp))){
            System.out.println("Looking at Right's priorities " + getNode(getRightOf(temp)).priority());
            swap(temp, getRightOf(temp));
            bubbleDown(getRightOf(temp));
        }return;*/
                 
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
        } else if (node1.myPriority <= node2.myPriority) {
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
         
        public boolean equals(Node other){
            if(myItem.equals(other.item()) && myPriority == other.priority())
                return true;
            return false;
        }
 
     
        @Override
        public String toString() {
            return item().toString() + ", " + priority();
        }
    }
 
    public static void main(String[] args) {
        ArrayHeap<String> heap = new ArrayHeap<String>();
        //System.out.println("Adding c");
        //heap.insert("a", 1);
        //heap.insert("a", 1);
        heap.insert("c", 3);
        heap.insert("i", 9);
        heap.insert("g", 7);
        heap.insert("d", 4);
        heap.insert("a", 1);
        heap.insert("h", 8);
        heap.insert("e", 5);
        heap.insert("b", 2);
        heap.insert("c", 3);
        heap.insert("d", 4);
        System.out.println(heap);
        System.out.println(heap.peek());
        heap.changePriority("c", 1.0);
        heap.changePriority("a", 10.0);
        heap.changePriority("g", 2.0);
        System.out.println(heap);
            //System.out.println(heap.contents);
             
        }
}