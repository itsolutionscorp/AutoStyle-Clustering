import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

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
    	myAdjLists[v1].add(new Edge(v1, v2, edgeInfo));
    }

    // Add to the graph an undirected edge from vertex v1 to vertex v2,
    // with the given edge information.
    public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
        //your code here
    	myAdjLists[v1].add(new Edge(v1, v2, edgeInfo));
    	myAdjLists[v2].add(new Edge(v2, v1, edgeInfo));

    }

    // Return true if there is an edge from vertex "from" to vertex "to";
    // return false otherwise.
    public boolean isAdjacent(int from, int to) {
        //your code here
        for (Edge a: myAdjLists[from] ) {
        	if (a.to() == to) {
        		return true;
        	}
        }
        return false;
    }

    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public List neighbors(int vertex) {
    	List<Integer> p = new LinkedList<Integer>();
    	for (Edge a : myAdjLists[vertex]) {
    		p.add(a.to());
    	}
        return p;
    }

    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
        for (LinkedList<Edge> p : myAdjLists) {
        	for (Edge q : p) {
        		if (q.to() == vertex) {
        			count++;
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

        public DFSIterator(Integer start) {
            //your code here
        	fringe = new Stack<Integer>();
        	visited = new HashSet<Integer>();
        	fringe.push(start);
        	visited.add(start);
        }

        public boolean hasNext() {
            //your code here
            return !fringe.isEmpty();
        }

        public Integer next() {
            //your code here
        	int k = fringe.pop();
        	for (Edge p : myAdjLists[k]) {
        		if (!visited.contains(p.to())) {
        			fringe.push(p.to());
                	visited.add(p.to());
        		}
        	}
            return k;
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
        return visitAll(startVertex).contains(stopVertex);
    }


    // Returns the path from startVertex to stopVertex.
    // If no path exists, returns an empty arrayList.
    // If startVertex == stopVertex, returns a one element arrayList.
    public ArrayList<Integer> path(int startVertex, int stopVertex) {
        // you supply the body of this method
        Iterator<Integer> iter = new DFSIterator(startVertex);
    	while(iter.hasNext()) {
    		int k = iter.next();
    		if (neighbors(k).contains(stopVertex)) {
        		ArrayList<Integer> path = path(startVertex, k);
        		path.add(stopVertex);
    			return path;
    		}
    		if (k == stopVertex) {
        		ArrayList<Integer> path = new ArrayList<Integer>();
        		path.add(k);
        		return path;
    		}
    	}
    	return new ArrayList<Integer>();
    }

    public ArrayList<Integer> topologicalSort() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Iterator<Integer> iter = new TopologicalIterator();
        while (iter.hasNext()) {
            result.add(iter.next());
        }
        return result;
    }

    private class TopologicalIterator implements Iterator<Integer> {

        private Stack<Integer> fringe;

        // more instance variables go here
        private int[] currentInDegree;

        public TopologicalIterator() {
            fringe = new Stack<Integer>();
            // more statements go here
            currentInDegree= new int[myVertexCount];
            for (int k = 0; k < myAdjLists.length; k++) {
            	currentInDegree[k] = inDegree(k);
            	if (currentInDegree[k] == 0) {
            		fringe.push(k);
            	}
            }
        }

        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        public Integer next() {
        	int k = fringe.pop();
        	// you supply the real body of this method
        	for (Edge p : myAdjLists[k]) {
        		currentInDegree[p.to()] = currentInDegree[p.to()]-1;
        		if (currentInDegree[p.to()]== 0) {
        			fringe.push(p.to());
        		}
        	}
        	return k;
        }

        public void remove() {
            throw new UnsupportedOperationException(
                    "vertex removal not implemented");
        }

    }
    
    public ArrayList<Integer> shortestPath (int startVertex, int endVertex){
        //your code here
    	ArrayHeap<Integer> fringe = new ArrayHeap<Integer>();
    	Integer[] myPreds =new Integer[myVertexCount];
    	for (int k = 0; k < myVertexCount; k++) {
    		if (k == startVertex) {
    			fringe.insert(k, 0);
    		} else {
    			fringe.insert(k, Double.POSITIVE_INFINITY);
    		}
    		myPreds[k] = null;
    	}
    	
		ArrayList<Integer> myShortestPath = new ArrayList<Integer>();
    	while (!fringe.isEmpty()) {
    		ArrayHeap<Integer>.Node k = fringe.removeMin();
    		if (k == null) {
    			break;
    		}
    		if (k.item() == endVertex) {
    			Stack<Integer> myShortestPathStack = new Stack<Integer>();
    			for (Integer j = k.item(); j != null; j = myPreds[j]) {
    				myShortestPathStack.push(j);
    			}
    			while (!myShortestPathStack.isEmpty()) {
    				myShortestPath.add(myShortestPathStack.pop());
    			}
    		} 
    		else {
    			for (Edge p: myAdjLists[k.item()]) {
    				if (p.myEdgeInfo != null && fringe.changePriority(p.to(), (Integer) p.myEdgeInfo + k.myPriority)) {
    					myPreds[p.to()] = (Integer) k.item();
    				}
    			}
    		}
    	}
    	if (!myShortestPath.contains(endVertex)|| !myShortestPath.contains(startVertex)) {
    		return new ArrayList<Integer>();
    	}
    	return myShortestPath;
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

    }
    
    
    /**
     * A Generic heap class. Unlike Java's priority queue, this heap doesn't just
     * store Comparable objects. Instead, it can store any type of object
     * (represented by type T), along with a priority value. Why do it this way? It
     * will be useful later on in the class...
     */
    private class ArrayHeap<T> {
    	private ArrayList<Node> contents = new ArrayList<Node>();

    	/**
    	 * Inserts an item with the given priority value. This is enqueue, or offer.
    	 */
    	public void insert(T item, double priority) {
    		if (contents.isEmpty()) {
    			setNode(1, new Node(item, priority));
    		} else {
    			setNode(contents.size(),new Node(item, priority));
    			bubbleUp(contents.size() - 1);
    		}
    	}
    	
    	public int size() {
    		int count = 0;
    		for (int i =0; i < contents.size();i ++) {
    			if (contents.get(i) != null) {
    				count ++;
    			}
    		}
    		return count;
    	}
    	
    	public boolean isEmpty() {
    		return size() == 0;
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
    		Node p = getNode(1);
    		if (contents.size() > 2) {
    			swap(1, contents.size()-1);
    			contents.remove(contents.size()-1);
    			bubbleDown(1);
    		}
    		else if (contents.size() == 2) {
    			contents.remove(1);
    		}
    		return p;
    	}

    	/**
    	 * Change the node in this heap with the given item to have the given
    	 * priority. For this method only, you can assume the heap will not have two
    	 * nodes with the same item. Check for item equality with .equals(), not ==
    	 */
    	public boolean changePriority(T item, double priority) {
    		// TODO Complete this method!
    		for(int i = 1; i < contents.size(); i++) {
    			if (getNode(i).myItem.equals(item)) {
    				Double p = getNode(i).priority();
    				if (p > priority)  {
    					setNode(i, new Node(item, priority));
    					bubbleUp(i);
    					return true;
    				}
    				return false;
    				}
    			}
    		return false;
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
    		return 2*i+1;
    	}

    	/**
    	 * Returns the index of the node that is the parent of the node at i.
    	 */
    	private int getParentOf(int i) {
    		// TODO Complete this method!
    		return i/2;
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
    		if (index > 1) {
    			if (min(getParentOf(index),index) == index) {
    			swap(index, getParentOf(index));
    			bubbleUp(getParentOf(index));
    			}
    		}
    	}

    	/**
    	 * Bubbles down the node currently at the given index.
    	 */
    	private void bubbleDown(int index) {
    		// TODO Complete this method!
    		if (index < contents.size()) {
    			int p = min(min(getLeftOf(index),getRightOf(index)), index);
    			if (p != index) {
    				swap(p, index);
    				bubbleDown(p);
    			}
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
    }
    

    public static void main(String[] args) {
        ArrayList<Integer> result;

        Graph g1 = new Graph(7);
        g1.addEdge(0, 1);
        g1.addEdge(0, 2);
        g1.addEdge(0, 4);
        g1.addEdge(1, 2);
        g1.addEdge(2, 0);
        g1.addEdge(2, 3);
        g1.addEdge(4, 3);

     
        System.out.println("Traversal starting at 0");
         result = g1.visitAll(0);
         Iterator<Integer> iter;
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Traversal starting at 2");
        result = g1.visitAll(2);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Traversal starting at 3");
        result = g1.visitAll(3);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Traversal starting at 4");
        result = g1.visitAll(4);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 0 to 3");
        result = g1.path(0, 3);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 0 to 4");
        result = g1.path(0, 4);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 1 to 3");
        result = g1.path(1, 3);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 1 to 4");
        result = g1.path(1, 4);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 4 to 0");
        result = g1.path(4, 0);
        if (result.size() != 0) {
            System.out.println("*** should be no path!");
        }

        Graph g2 = new Graph(5);
        g2.addEdge(0, 1);
        g2.addEdge(0, 2);
        g2.addEdge(0, 4);
        g2.addEdge(1, 2);
        g2.addEdge(2, 3);
        g2.addEdge(4, 3);
        System.out.println();
        System.out.println();
        System.out.println("Topological sort");
        result = g2.topologicalSort();
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
    }

}
