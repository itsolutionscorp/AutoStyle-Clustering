import java.util.*;

//import ArrayHeap.Node;

public class Graph implements Iterable<Integer>{

    private LinkedList<Edge>[] myAdjLists;
    private int myVertexCount;
    private int myStartVertex;

    // Initialize a graph with the given number of vertices and no edges.
    public Graph(int numVertices) {
        myAdjLists = new LinkedList[numVertices];
        myStartVertex = 0;
        for (int k = 0; k < numVertices; k++) {
            myAdjLists[k] = new LinkedList<Edge>();
        }
        myVertexCount = numVertices;
    }

    // Change the vertex the iterator will start DFS from
    public void setStartVertex(int v){
        if (v < myVertexCount && v >= 0){
            myStartVertex = v;
        } else {
            throw new IllegalArgumentException("Cannot set iteration start vertex to " + v + ".");
        }
    }


    // Add to the graph a directed edge from vertex v1 to vertex v2.
    public void addEdge(int v1, int v2) {
        addEdge(v1, v2, null);
    }

    // Add to the graph an undirected edge from vertex v1 to vertex v2.
    public void addUndirectedEdge(int v1, int v2) {
        addUndirectedEdge(v1, v2, null);
    }

    // Add to the graph a directed edge from vertex v1 to vertex v2,
    // with the given edge information.
    public void addEdge(int v1, int v2, Object edgeInfo) {
        //your code here
    	for (Edge e : myAdjLists[v1]) {
    		if (e.to() == v2) {
    			e.setObjectInfo(edgeInfo);
    			return;
    		}
    	}
    	Edge toAdd = new Edge(v1, v2, edgeInfo);
    	myAdjLists[v1].add(toAdd);	
    }

    // Add to the graph an undirected edge from vertex v1 to vertex v2,
    // with the given edge information.
    public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
        //your code here
    	addEdge(v1, v2, edgeInfo);
    	addEdge(v2, v1, edgeInfo);
    	
    }

    // Return true if there is an edge from vertex "from" to vertex "to";
    // return false otherwise.
    public boolean isAdjacent(int from, int to) {
        //your code here
    	for (Edge e : myAdjLists[from]) {
    		if (e.to() == to) {
    			return true;
    		}
    	}
        return false;
    }

    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public List neighbors(int vertex) {
        // your code here
    	List toReturn = new ArrayList<Integer>();
    	for (Edge e : myAdjLists[vertex]) {
    		toReturn.add(e.to());
    	}
        return toReturn;
    }

    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
        //your code here
        for (LinkedList<Edge> l : myAdjLists) {
        	for (Edge e : l) {
        		if (e.to() == vertex) {
        			count++;
        			break;
        		}
        	}
        }
        return count;
    }

    public Iterator<Integer> iterator(){
        return new TopologicalIterator();
    }

    // A class that iterates through the vertices of this graph, starting with a given vertex.
    // Does not necessarily iterate through all vertices in the graph: if the iteration starts
    // at a vertex v, and there is no path from v to a vertex w, then the iteration will not
    // include w
    private class DFSIterator implements Iterator<Integer> {

        private Stack<Integer> fringe;
        private HashSet<Integer> visited;
        private int startVertex;

        public DFSIterator(Integer start) {
            //your code here
        	fringe = new Stack<Integer>();
        	visited = new HashSet<Integer>();
        	startVertex = start;
        	fringe.push(start);
        }

        public boolean hasNext() {
            //your code here
        	return !fringe.isEmpty();
            //return false;
        }

        public Integer next() {
            //your code here
        	ArrayList<Integer> connected = new ArrayList<Integer>();
        	Integer toReturn = fringe.pop();
        	for (Edge e: myAdjLists[toReturn]) {
        		connected.add(e.to());
        		
        		//fringe.add(e.to());
        	}
        	Collections.sort(connected);
        	Collections.reverse(connected);
        	for (Integer i : connected) {
        		fringe.add(i);
        	}
        	visited.add(toReturn);
        	//start popping all visited
	        while (!fringe.isEmpty() && visited.contains(fringe.peek())) {
	       		fringe.pop();
	       	}
            return toReturn;
        }

        //ignore this method
        public void remove() {
            throw new UnsupportedOperationException(
                    "vertex removal not implemented");
        }

    }

    // Return the collected result of iterating through this graph's
    // vertices as an ArrayList.
    public ArrayList<Integer> visitAll(int startVertex) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Iterator<Integer> iter = new DFSIterator(startVertex);

        while (iter.hasNext()) {
            result.add(iter.next());
        }
        return result;
    }

    // Returns true iff there exists a path from STARVETEX to
    // STOPVERTEX. Assumes both STARTVERTEX and STOPVERTEX are
    // in this graph. If STARVERTEX == STOPVERTEX, returns true.
    public boolean pathExists(int startVertex, int stopVertex) {
        // your code here
    	Iterator iter = new DFSIterator(startVertex);
    	Integer i = stopVertex;
    	while (iter.hasNext()) {
    		if (iter.next().equals(i)) {
    			return true;
    		}
    	}
        return false;
    }


    // Returns the path from startVertex to stopVertex.
    // If no path exists, returns an empty arrayList.
    // If startVertex == stopVertex, returns a one element arrayList.
    public ArrayList<Integer> path(int startVertex, int stopVertex) {
    	ArrayList<Integer> toReturn = new ArrayList<Integer>();
    	ArrayList<Integer> temporary = new ArrayList<Integer>();
    	if (!pathExists(startVertex, stopVertex)) {
    		return toReturn;
    	}
    	if (startVertex == stopVertex) {
    		toReturn.add(startVertex);
    		return toReturn;
    	}
    	Iterator<Integer> iter = new DFSIterator(startVertex);
    	while (iter.hasNext()) {
    		Integer toAdd = iter.next();
    		temporary.add(toAdd);
    		if (toAdd.equals(stopVertex)) {
    			break;
    		}
    	}
    	toReturn.add(0, stopVertex);
    	return pathHelper(temporary, toReturn);
    }
    
    public ArrayList<Integer> pathHelper(ArrayList<Integer> possible, ArrayList<Integer> toReturn) {
    	Integer start = possible.get(0);
    	Integer end = possible.get(possible.size() - 1);
    	//isAdjacent(from, to);
    	//ArrayList<Integer> toReturn = new ArrayList<Integer>();
    	//toReturn.add(0, end);
    	
    	if (isAdjacent(start, end)) {
    		toReturn.add(0, start);
    		return toReturn;
    	}	
    	int currIndex = 0;
    	for (Integer i : possible) {
    		if (isAdjacent(i, end)) {
    			toReturn.add(0, i);
    			currIndex = possible.indexOf(i);
    			break;
    		}
    	}
    	int size = possible.size() - 1;
    	for (int i = size; i > currIndex; i--) {
    		possible.remove(i);
    	}	
    	return pathHelper(possible, toReturn);
    }

    public ArrayList<Integer> topologicalSort() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Iterator<Integer> iter = new TopologicalIterator();
        while (iter.hasNext()) {
            result.add(iter.next());
        }
        return result;
    }
    
    public int inDegreeCopy(int vertex, LinkedList<Edge>[] edgeCopy) {
        int count = 0;
        //your code here
        for (LinkedList<Edge> l : edgeCopy) {
        	for (Edge e : l) {
        		if (e.to() == vertex) {
        			count++;
        			break;
        		}
        	}
        }
        return count;
    }

    private class TopologicalIterator implements Iterator<Integer> {

        private Stack<Integer> fringe;
        private LinkedList<Edge>[] edgeCopy;
        private ArrayList<Integer> alreadyReturned;
        
        
        // more instance variables go here

        public TopologicalIterator() {
            fringe = new Stack<Integer>();
            edgeCopy = new LinkedList[myAdjLists.length];
            int count = 0;
            for (LinkedList l : myAdjLists) {
            	LinkedList toAdd = new LinkedList();
            	for (Object e : l) {
            		toAdd.add(e);
            	}
            	edgeCopy[count] = toAdd;
            	count++;
            }
            for (int i = 0; i < myVertexCount; i++) {
            	if (inDegreeCopy(i, edgeCopy) == 0) {
            		fringe.add(i);
            	}
            }
            alreadyReturned = new ArrayList<Integer>();
            // more statements go here
        }

        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        public Integer next() {
        	Integer toReturn = fringe.pop();
        	edgeCopy[toReturn] = new LinkedList<Edge>();
        	fringe = new Stack<Integer>();
        	alreadyReturned.add(toReturn);
        	for (int i = 0; i < myVertexCount; i++) {
            	if (inDegreeCopy(i, edgeCopy) == 0 && !alreadyReturned.contains(i)) {
            		fringe.add(i);
            	}
            }
            return toReturn;
            // you supply the real body of this method
        }

        public void remove() {
            throw new UnsupportedOperationException(
                    "vertex removal not implemented");
        }

    }

    private class Edge {

        private Integer myFrom;
        private Integer myTo;
        private Object myEdgeInfo;

        public Edge(int from, int to, Object info) {
            myFrom = new Integer(from);
            myTo = new Integer(to);
            myEdgeInfo = info;
        }

        public Integer to() {
            return myTo;
        }

        public Object info() {
            return myEdgeInfo;
        }

        public String toString() {
            return "(" + myFrom + "," + myTo + ",dist=" + myEdgeInfo + ")";
        }
        
        public void setObjectInfo(Object info) {
        	myEdgeInfo = info;
        }
        
        public Integer from() {
        	return myFrom;
        }

    }
    
    public class IntPriorityPair implements Comparable {
    	
    	Integer myItem;
    	int priority;
    	Integer myPrev;
    	
    	public IntPriorityPair(Integer i, int p) {
    		myItem = i;
    		priority = p;
    	}
    	
    	public void setPrev(Integer i) {
    		myPrev = i;
    	}
    	
    	public void setPriority(int i) {
    		priority = i;
    	}
    	
    	@Override
    	public int compareTo(Object p) {
    		if (p instanceof IntPriorityPair) {
    			IntPriorityPair toCompare = (IntPriorityPair) p;
    			if (priority > toCompare.priority) {
    				return 1;
    			}
    			else if (priority < toCompare.priority){
    				return -1;
    			}
    			else {
    				return 0;
    			}
    		}
    		return 0;
    	}
    	
    	public String toString() {
    		return new String("myItem = " + myItem + ", priority = " + priority + ", myPrev = " + myPrev);
    	}
    }
    
    public ArrayList<Integer> shortestPath (int startVertex, int endVertex){
    	if (!pathExists(startVertex, endVertex)) {
    		return new ArrayList<Integer>();
    	}
    	ArrayList<Integer> toReturn = new ArrayList<Integer>();
    	ArrayList<IntPriorityPair> toAdd = new ArrayList<IntPriorityPair>();
    	PriorityQueue<IntPriorityPair> fringe = new PriorityQueue<IntPriorityPair>();
	    LinkedList<Edge>[] edgeCopy;
	    edgeCopy = new LinkedList[myAdjLists.length];
	    int count = 0;
	    boolean init = false;
	    for (LinkedList l : myAdjLists) {
	    	LinkedList LLToAdd = new LinkedList();
	    	for (int i = 0; i < myVertexCount; i++) {
	    		Integer toCheck = new Integer(i);
	    		for (Object e : l) {
	    			Edge edge = (Edge) e;
	    			if (edge.to().equals(toCheck)) {
	    				LLToAdd.add(new Edge(edge.from(), edge.to(), edge.info()));
	    				init = true;
	    			}
	    		}
	    		if (init) {
	    			init = false;
	    			continue;
	    		}
	    		if ((new Integer(count)).equals(new Integer(i))) {
	    			LLToAdd.add(new Edge(new Integer(count), new Integer(i), new Integer(0)));
	    		}
	    		else {
	    			LLToAdd.add(new Edge(new Integer(count), new Integer(i), Integer.MAX_VALUE ));
	    		}
	    	}
	    	edgeCopy[count] = LLToAdd;
	    	count++;
	    }
	    count = 0;
	    for (int i = 0; i < myVertexCount; i++) {
	    	if (i == startVertex){
	    		toAdd.add(new IntPriorityPair(new Integer(i), 0));
	    		continue;
	    	}
	    	toAdd.add(new IntPriorityPair(new Integer(i), Integer.MAX_VALUE));
	    }
	    fringe.add(toAdd.get(startVertex));
	    ArrayList<IntPriorityPair> visited = new ArrayList<IntPriorityPair>();
	    while (!fringe.isEmpty()) {
	    	IntPriorityPair currNode = fringe.remove();
	    	visited.add(currNode);
	    	int count2 = 0;
	    	for (Object e : edgeCopy[currNode.myItem]) {
	    		Edge currEdge = (Edge) e;
	    		if (currEdge.to().equals(new Integer(currNode.myItem))) {
	    			count2++;
	    			continue;
	    		}
	    		if (currEdge.info().equals(Integer.MAX_VALUE)) {
	    			count2++;
	    			continue;
	    		}
	    		Integer currDistance = new Integer(toAdd.get(currNode.myItem).priority);
	    		int currMin = toAdd.get(count2).priority;
	    		Integer toCompare = new Integer(currMin);
	    		Integer minFromNode = (Integer) currEdge.info();
	    		Integer throughThisNode = new Integer(currDistance + minFromNode);
	    		if (throughThisNode.compareTo(toCompare) < 0) {
	    			toAdd.get(count2).setPriority(throughThisNode);
	    			toAdd.get(count2).setPrev(currNode.myItem);
	    		}
	    		if (!visited.contains(toAdd.get(count2))) {
	    			fringe.add(toAdd.get(count2));
	    		}
	    		count2++;
	    	}	
	    }
	    IntPriorityPair currNode = toAdd.get(endVertex);
	    while (currNode != null) {
	    	toReturn.add(0, currNode.myItem);
	    	if (currNode.myPrev == null) {
	    		currNode = null;
	    		continue;
	    	}
	    	currNode = toAdd.get(currNode.myPrev);
	    }
	    return toReturn;
    }
    
    //Copied in from lab 19
    public class ArrayHeap<T> {
    	private ArrayList<Node> contents = new ArrayList<Node>();
    	
    	private int indexOfLeastPriority = 1;

    	/**
    	 * Inserts an item with the given priority value. This is enqueue, or offer.
    	 */
    	public void insert(T item, int priority) {
    		Node toInsert = new Node(item, priority);
    		setNode(indexOfLeastPriority, toInsert);
    		bubbleUp(indexOfLeastPriority);
    		indexOfLeastPriority++;
    	}

    	/**
    	 * Returns the Node with the smallest priority value, but does not remove it
    	 * from the heap.
    	 */
    	public Node peek() {
    		// TODO Complete this method!
    		return getNode(1);
    	}

    	/**
    	 * Returns the Node with the smallest priority value, and removes it from
    	 * the heap. This is dequeue, or poll.
    	 */
    	public Node removeMin() {
    		// TODO Complete this method!
    		if (indexOfLeastPriority == 2) {
    			Node toReturn = getNode(1);
    			setNode(1, null);
    			return toReturn;
    		}
    		int indexOfReplacement = indexOfLeastPriority - 1;
    		Node nodeOfReplacement = getNode(indexOfReplacement);
    		Node toRemove = getNode(1);
    		setNode(indexOfReplacement, null);
    		setNode(1, nodeOfReplacement);
    		bubbleDown(1);
    		//decrement IoLP
    		indexOfLeastPriority--;
    		return toRemove;
    	}

    	/**
    	 * Change the node in this heap with the given item to have the given
    	 * priority. For this method only, you can assume the heap will not have two
    	 * nodes with the same item. Check for item equality with .equals(), not ==
    	 */
    	public void changePriority(T item, int priority) {
    		// TODO Complete this method!
    		Node toAdd = new Node(item, priority);
    		setNode(getNode2(item), toAdd);
    		
    	}
    	
    	private int getNode2(T item) {
    		for (int i = 1; i < indexOfLeastPriority; i++) {
    			if (getNode(i).item().equals(item)) {
    				return i;
    			}
    		}
    		return 0;
    	}
    	
    	public boolean isEmpty() {
    		return indexOfLeastPriority != 1;
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
    		return i / 2;
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
    		bubbleUpHelper(index, getNode(index).item());
    	}
    	
    	private void bubbleUpHelper(int index, T item) {
    		if (getParentOf(index) == 0) {
    			return;
    		}
    		T parentItem = getNode(getParentOf(index)).item();
    		if (getNode(getParentOf(index)).priority() < getNode(index).priority()) {
    			return;
    		}
    		else {
    			int newPos = getParentOf(index);
    			swap(index, newPos);
    			bubbleUpHelper(newPos, item);
    		}
    		
    	}

    	/**
    	 * Bubbles down the node currently at the given index.
    	 */
    	private void bubbleDown(int index) {
    		// TODO Complete this method!
    		if (getNode(index) != null) {
    			bubbleDownHelper(index, getNode(index).item());
    		}
    	}
    	
    	public void bubbleDownHelper(int index, T item) {
    		//always swap with min child
    		if (getNode(getLeftOf(index)) == null && getNode(getRightOf(index)) == null) {
    			return;
    		}
    		if (getNode(getLeftOf(index)) == null) {
    			if (getNode(getRightOf(index)).priority() < getNode(index).priority()) {
    				int newPos = getRightOf(index);
    				swap(index, newPos);
    				bubbleDownHelper(newPos, item);
    			}
    		}
    		else if (getNode(getRightOf(index)) == null) {
    			if (getNode(getLeftOf(index)).priority() < getNode(index).priority()) {
    				int newPos = getLeftOf(index);
    				swap(index, newPos);
    				bubbleDownHelper(newPos, item);
    			}
    		}
    		else {
    			//if (getNode(getLeftOf(index)).item().compareTo(getNode(getRightOf(index)).item()) < 0) {
    			if (getNode(min(getLeftOf(index), getRightOf(index))).priority() < getNode(index).priority()) {
    				int newPos = min(getLeftOf(index), getRightOf(index));
    				swap(index, newPos);
    				bubbleDownHelper(newPos, item);
    			}
//    			if (getNode(getLeftOf(index)).priority() < getNode(getRightOf(index)).priority()) {
//    				bubbleDownHelper(getLeftOf(index), item);
//    			}
//    			else {
//    				bubbleDownHelper(getRightOf(index), item);
//    			}
    		}
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
    		private int myPriority;

    		private Node(T item, int priority) {
    			myItem = item;
    			myPriority = priority;
    		}

    		public T item() {
    			return myItem;
    		}

    		public int priority() {
    			return myPriority;
    		}
    		
    		public void setPriority(int priority) {
    			myPriority = priority;
    		}

    		@Override
    		public String toString() {
    			return item().toString() + ", " + priority();
    		}
    	}
    }

    public static void main(String[] args) {
//        ArrayList<Integer> result;
//
//        Graph g1 = new Graph(5);
//        g1.addEdge(0, 1);
//        g1.addEdge(0, 2);
//        g1.addEdge(0, 4);
//        g1.addEdge(1, 2);
//        g1.addEdge(2, 0);
//        g1.addEdge(2, 3);
//        g1.addEdge(4, 3);
//        System.out.println("Traversal starting at 0");
//        result = g1.visitAll(0);
//        Iterator<Integer> iter;
//        iter = result.iterator();
//        while (iter.hasNext()) {
//            System.out.println(iter.next() + " ");
//        }
//        System.out.println();
//        System.out.println();
//        System.out.println("Traversal starting at 2");
//        result = g1.visitAll(2);
//        iter = result.iterator();
//        while (iter.hasNext()) {
//            System.out.println(iter.next() + " ");
//        }
//        System.out.println();
//        System.out.println();
//        System.out.println("Traversal starting at 3");
//        result = g1.visitAll(3);
//        iter = result.iterator();
//        while (iter.hasNext()) {
//            System.out.println(iter.next() + " ");
//        }
//        System.out.println();
//        System.out.println();
//        System.out.println("Traversal starting at 4");
//        result = g1.visitAll(4);
//        iter = result.iterator();
//        while (iter.hasNext()) {
//            System.out.println(iter.next() + " ");
//        }
//        System.out.println();
//        System.out.println();
//        System.out.println("Path from 0 to 3");
//        result = g1.path(0, 3);
//        iter = result.iterator();
//        while (iter.hasNext()) {
//            System.out.println(iter.next() + " ");
//        }
//        System.out.println();
//        System.out.println();
//        System.out.println("Path from 0 to 4");
//        result = g1.path(0, 4);
//        iter = result.iterator();
//        while (iter.hasNext()) {
//            System.out.println(iter.next() + " ");
//        }
//        System.out.println();
//        System.out.println();
//        System.out.println("Path from 1 to 3");
//        result = g1.path(1, 3);
//        iter = result.iterator();
//        while (iter.hasNext()) {
//            System.out.println(iter.next() + " ");
//        }
//        System.out.println();
//        System.out.println();
//        System.out.println("Path from 1 to 4");
//        result = g1.path(1, 4);
//        iter = result.iterator();
//        while (iter.hasNext()) {
//            System.out.println(iter.next() + " ");
//        }
//        System.out.println();
//        System.out.println();
//        System.out.println("Path from 4 to 0");
//        result = g1.path(4, 0);
//        if (result.size() != 0) {
//            System.out.println("*** should be no path!");
//        }
//
//        Graph g2 = new Graph(5);
//        g2.addEdge(0, 1);
//        g2.addEdge(0, 2);
//        g2.addEdge(0, 4);
//        g2.addEdge(1, 2);
//        g2.addEdge(2, 3);
//        g2.addEdge(4, 3);
//        System.out.println();
//        System.out.println();
//        System.out.println("Topological sort");
//        result = g2.topologicalSort();
//        iter = result.iterator();
//        while (iter.hasNext()) {
//            System.out.println(iter.next() + " ");
//        }
//        
//        Graph g3 = new Graph(8);
//        g3.addEdge(0, 2);
//        g3.addEdge(0, 3);
//        g3.addEdge(1, 3);
//        g3.addEdge(1, 4);
//        g3.addEdge(2, 5);
//        g3.addEdge(3, 5);
//        g3.addEdge(4, 6);
//        g3.addEdge(5, 7);
//        g3.addEdge(6, 7);
//        System.out.println();
//        System.out.println();
//        System.out.println("Topological sort");
//        result = g3.topologicalSort();
//        iter = result.iterator();
//        while (iter.hasNext()) {
//            System.out.println(iter.next() + " ");
//        }
//    }
    	Graph g = new Graph(8);
    	g.addEdge(0, 1, 20);
    	g.addEdge(0, 3, 80);
    	g.addEdge(0, 6, 90);
    	g.addEdge(1, 5, 10);
    	g.addEdge(2, 5, 50);
    	g.addEdge(2, 7, 20);
    	g.addEdge(2, 3, 10);
    	g.addEdge(3, 2, 10);
    	g.addEdge(3, 6 ,20);
    	g.addEdge(4, 1, 50);
    	g.addEdge(4, 6, 30);
    	g.addEdge(5, 2, 10);
    	g.addEdge(5, 3, 40);
    	g.addEdge(6, 0, 20);
    	for (int i = 0; i < 8; i++) {
    		for (int j = 0; j < 8; j++) {
    			System.out.println("Path from " + i + " to " + j + ":");
    			System.out.println(g.shortestPath(i, j));
    			System.out.println("");
    		}
    	}    	
    }

}
