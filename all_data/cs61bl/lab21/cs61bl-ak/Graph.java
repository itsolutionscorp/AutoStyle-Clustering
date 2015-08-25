package lab21;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;
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
    	Edge newEdge = new Edge(v1, v2, edgeInfo);
    	myAdjLists[v1].add(newEdge);
    }

    // Add to the graph an undirected edge from vertex v1 to vertex v2,
    // with the given edge information.
    public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
        //your code here
    	Edge to = new Edge(v1, v2, edgeInfo);
    	Edge from = new Edge(v2, v1, edgeInfo);
    	myAdjLists[v1].add(to);
    	myAdjLists[v2].add(from);
    	
    }

    // Return true if there is an edge from vertex "from" to vertex "to";
    // return false otherwise.
    public boolean isAdjacent(int from, int to) {
        //your code here
    	List<Edge> adjacents = myAdjLists[from];
    	for (int i = 0; i < adjacents.size(); i++) {
    		if (adjacents.get(i).myTo == to) {
    			return true;
    		}
    	}
    	return false;
    }

    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public List<Integer> neighbors(int vertex) {
        // your code here
    	ArrayList<Integer> result = new ArrayList<Integer>();
    	for (int i = 0; i < myAdjLists[vertex].size(); i++) {
    		result.add(myAdjLists[vertex].get(i).to());
    	}
        return result;
    }

    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
        //your code here
        for (int i = 0; i < myAdjLists.length; i++) {
        	for (int j = 0; j < myAdjLists[i].size(); j++) {
        		if (myAdjLists[i].get(j).myTo == vertex) {
        			count ++;
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
        	fringe = new Stack<>();
        	visited = new HashSet<>();
        	fringe.push(start);
        }

        public boolean hasNext() {
            //your code here
            return !fringe.empty();
        }

        public Integer next() {
            //your code here
        	Integer current = fringe.pop();
        	visited.add(current);
        	List<Edge> adjacents = myAdjLists[current];
        	for (int i = 0; i < adjacents.size(); i++) {
        		Integer next = adjacents.get(i).myTo;
        		if (!visited.contains(next)) {
        			fringe.push(next);
        		}
        	}
            return current;
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
    	ArrayList<Integer> all = visitAll(startVertex);
        return all.contains(stopVertex);
    }


    // Returns the path from startVertex to stopVertex.
    // If no path exists, returns an empty arrayList.
    // If startVertex == stopVertex, returns a one element arrayList.
    public ArrayList<Integer> path(int startVertex, int stopVertex) {
    	if (!pathExists(startVertex, stopVertex)) {
    		return new ArrayList<Integer>();
    	}
    	ArrayList<Integer> result = new ArrayList<>();
    	Iterator<Integer> iter = new DFSIterator(startVertex);
    	while (iter.hasNext()) {
    		result.add(iter.next());
    		if (result.get(result.size() -1) == stopVertex) {
    			break;
    		}
    	}
    	Integer prev = result.get(result.size() - 1);
    	ArrayList<Integer> toRemove = new ArrayList<>();
    	for (int i = result.size() - 2; i >= 0; i--) {
    		Integer current = result.get(i);
    		if (!isAdjacent(current, prev)) {
    			toRemove.add(i);
    		}
    		prev = current;
    	}
    	for (Integer i : toRemove) {
    		result.remove(i);
    	}
        return result;
        // you supply the body of this method
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
        Integer[] currentInDegree;
        
        public TopologicalIterator() {
            fringe = new Stack<Integer>();
            // more statements go here
            currentInDegree = new Integer[myVertexCount];
            for (int i = 0; i < myAdjLists.length; i++) {
            	currentInDegree[i] = inDegree(i);
            	if (currentInDegree[i] == 0) {
            		fringe.push(i);
            	}
            }
        }

        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        public Integer next() {
        	Integer current = fringe.pop();
        	for (int i = 0; i < currentInDegree.length; i++) {
        		if (isAdjacent(current, i)) {
        			currentInDegree[i] -= 1;
        		}
        	}
        	for (Edge e : myAdjLists[current]) {
        		if (currentInDegree[e.myTo] == 0) {
        			fringe.push(e.myTo);
        		}
        	}
            return current;
            // you supply the real body of this method
        }

        public void remove() {
            throw new UnsupportedOperationException(
                    "vertex removal not implemented");
        }
    }
    
    public ArrayList<Integer> shortestPath (int startVertex, int endVertex){
        //your code here...
    	Iterator iter = new ShortestPathIterator(startVertex);
    	while (iter.hasNext()) {
    		Path next = (Path) iter.next();
    		if (next.myEnd == endVertex && next.path().size() > 1) {
    			return next.path();
    		}
    	}
    	return new ArrayList<Integer>();
    }
    
    private class ShortestPathIterator implements Iterator<Path> {
    	private Integer myStart;
    	private Queue<Path> myPaths;
    	private ArrayList<Path> leftOver;
    	
    	private ShortestPathIterator(Integer start) {
    		myStart = start;
    		myPaths = new PriorityQueue<>();
    		Path startPath = new Path(start);
    		leftOver = new ArrayList<Path>();
    		startPath.update(0, new ArrayList<Integer>());
    		myPaths.offer(startPath);
    		leftOver.add(startPath);
    		for (int i = 0; i < myAdjLists.length; i++) {
    			if (i != start) {
    				Path toAdd = new Path(i);
    				myPaths.offer(toAdd);
    				leftOver.add(toAdd);
    			}
    		}
    	}
    	
    	public boolean hasNext() {
    		return !myPaths.isEmpty();
    	}
    	
    	public Path next() {
    		Path current = myPaths.poll();
    		leftOver.remove(current);
    		while (!myPaths.isEmpty()) {
    			myPaths.poll();
    		}
    		Integer soFar = current.dist();
    		List<Integer> adjacencies = neighbors(current.end());
    		for (int i = 0; i < leftOver.size(); i++) {
    			Path currentPath = leftOver.get(i);
    			//for all leftOver paths, check if their ends are adjacent to the current path
    			if (adjacencies.contains(currentPath.end())) {
    				//if so, get the distance from the correct edge and update the path
    				for (int j = 0; j < myAdjLists[current.end()].size(); j++) {
    					if ((int) myAdjLists[current.end()].get(j).to() == (int) currentPath.end()) {
	    					Integer newDist = (Integer) myAdjLists[current.end()].get(j).myEdgeInfo + soFar;
	    					if (newDist < currentPath.dist()) {
	    						currentPath.update(newDist, new ArrayList<Integer>(current.path()));
	    					}
    					}
    				}
    			} else {
    				continue;
    			}
    		}
    		for (Path p : leftOver) {
    			myPaths.offer(p);
    		}
    		return current;
    	}
    }

    private class Path implements Comparable<Path>{
    	
    	private ArrayList<Integer> myPath;
    	private Integer myEnd;
    	private Integer myDist;
    	
    	private Path(Integer end) {
    		myEnd = end;
    		myPath = new ArrayList<>();
    		myPath.add(myEnd);
    		myDist = Integer.MAX_VALUE;
    	}
    	
    	private void update(Integer dist, ArrayList<Integer> newPath) {
    		myPath = newPath;
    		myPath.add(myEnd);
    		myDist = dist;
    	}
    	
    	private Integer end() {
    		return myEnd;
    	}
    	
    	private Integer dist() {
    		return myDist;
    	}
    	
    	private ArrayList<Integer> path() {
    		return myPath;
    	}
    	
    	public int compareTo(Path other) {
    		if (other.dist() == dist()) {
    			return 0;
    		} else if (other.dist() > dist()) {
    			return -1;
    		} else {
    			return 1;
    		}
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
    }

    public static void main(String[] args) {
        ArrayList<Integer> result;

        Graph g1 = new Graph(5);
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
        
        Graph g3 = new Graph(7);
        g3.addUndirectedEdge(0,2, 4); 
        g3.addUndirectedEdge(0,3, 7); 
        g3.addUndirectedEdge(1,4, 1); 
        g3.addUndirectedEdge(1,5, 5); 
        g3.addUndirectedEdge(2,3, 2); 
        g3.addUndirectedEdge(2,6, 1); 
        g3.addUndirectedEdge(4,5, 5); 
        
        g3.addEdge(0,1); 
        g3.addEdge(1,2); 
        g3.addEdge(2,0); 
        g3.addEdge(2,3); 
        g3.addEdge(4,2); 
    
        Graph g4 = new Graph(7);
        g4.addEdge(0,2, 9); 
        g4.addUndirectedEdge(0,3, 1);
        g4.addEdge(3,1, 1);
        g4.addUndirectedEdge(1,4, 1); 
        g4.addEdge(1,5, 5); 
        g4.addUndirectedEdge(2,3, 2); 
        g4.addEdge(6,2, 1); 
        g4.addEdge(4,5, 1);
        g4.addEdge(5,6, 1);
        System.out.println(g4.shortestPath(0, 6));
        System.out.println(g4.shortestPath(0, 1));
        System.out.println(g4.shortestPath(6, 0));
        System.out.println(g4.shortestPath(5, 0));
        System.out.println(g4.shortestPath(5, 2));
        System.out.println(g4.shortestPath(5, 4));
        System.out.println();
        
        Graph g5 = new Graph(7);
        g5.addEdge(0,2, 9); 
        g5.addEdge(3,1, 1); 
        g5.addEdge(1,5, 5); 
        g5.addUndirectedEdge(2,3, 2); 
        g5.addEdge(6,2, 1); 
        g5.addEdge(4,5, 1);
        g5.addEdge(5,6, 1);
        
        System.out.println(g5.shortestPath(0, 2));
        System.out.println(g5.shortestPath(0, 6));
        System.out.println(g5.shortestPath(0, 1));
        System.out.println(g5.shortestPath(6, 0));
        System.out.println(g5.shortestPath(5, 0));
        System.out.println(g5.shortestPath(5, 2));
        System.out.println(g5.shortestPath(5, 4));
        System.out.println(g5.shortestPath(4, 5));
    }
    
}
