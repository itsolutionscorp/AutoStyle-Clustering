import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
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
    	Edge a = new Edge(v1,v2, edgeInfo);
    	myAdjLists[v1].add(a);
    }


	// Add to the graph an undirected edge from vertex v1 to vertex v2,
    // with the given edge information.
    public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
    	Edge a = new Edge(v1,v2, null);
    	Edge b = new Edge(v2,v1, null);
    	myAdjLists[v1].add(a);
    	myAdjLists[v2].add(b);
    }

    // Return true if there is an edge from vertex "from" to vertex "to";
    // return false otherwise.
    public boolean isAdjacent(int from, int to) {
    	Edge friends = new Edge(from, to, null);
        for (Edge a: myAdjLists[from]){
        	if(a.equals(friends)){
        		return true;
        	}
        }
        return false;
    }

    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public List neighbors(int vertex) {
        // your code here
    	List friends = new LinkedList<Integer>();
    	for(Edge a: myAdjLists[vertex]){
    		friends.add(a.to());
    	}
    	return friends;
    }

    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
        for (LinkedList<Edge> a: myAdjLists){
        	for(Edge b: a){
        		if(b.to() == vertex){
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
        	fringe = new Stack<Integer>();
        	visited = new HashSet<Integer>();
            fringe.push(start);
        }

        public boolean hasNext() {
    
            return !fringe.empty();
        }

        public Integer next() {
        	if (hasNext()){
            int me = fringe.pop();
            for (int friend: (List<Integer>)neighbors(me)){
            	if(!visited.contains(friend)){
            		fringe.push(friend);
            	}
            }
            visited.add(me);
            return me;
        	}
            return null;
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
    	LinkedList<Integer> amigos = new LinkedList<Integer>();
        if(startVertex == stopVertex){
        	return true;
        }
        for(int acquaintance: visitAll(startVertex)){
        	if(acquaintance == stopVertex){
        		return true;
        	}
        }
        return false;
    }


    // Returns the path from startVertex to stopVertex.
    // If no path exists, returns an empty arrayList.
    // If startVertex == stopVertex, returns a one element arrayList.
    public ArrayList<Integer> path(int startVertex, int stopVertex) {
        ArrayList<Integer> path = new ArrayList<Integer>();
        if(!pathExists(startVertex, stopVertex)){
        	return path;
        }
        if(startVertex == stopVertex){
        	path.add(startVertex);
        	return path;
        }
        Iterator<Integer> iter = new DFSIterator(startVertex);
        while (iter.hasNext()) {
        	int me = iter.next();
        	if(me == stopVertex){
        		path.add(me);
        		return pathhelper(me, path);	
        	}
        	path.add(me);
        }
        
        return path;
    }
    public ArrayList<Integer> pathhelper(int me, ArrayList<Integer> path){
    	int pointer = path.size() -1;
    	int n = pointer - 1;
 
    	while(n >= 0){
    	Edge check = new Edge(path.get(n), path.get(pointer), null);
    	boolean checker = false;
    	for(Edge a: myAdjLists[path.get(n)]){
    		if(a.toString().equals(check.toString())){
    			checker = true;
    		}
    		
    	}
    	if(!checker){
    		path.remove(n);
    	}
    	pointer--;
    	n--;
    	}
    	return path;
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
        private ArrayList<Integer> currentInDegree;
        // more instance variables go here

        public TopologicalIterator() {
            fringe = new Stack<Integer>();
            currentInDegree = new ArrayList<Integer>();
            for (int i = 0; i < myAdjLists.length; i++){
            	currentInDegree.add(inDegree(i));
            	if(inDegree(i) == 0){
            		fringe.push(i);
            	}
            }
        }

        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        public Integer next() {
            if(hasNext()){
            	int me = fringe.pop();
            	for(int a: (List<Integer>)neighbors(me)){
            		int oldie = currentInDegree.get(a);
            		currentInDegree.set(a, oldie-1);
            		if(oldie-1 == 0){
            			fringe.push(a);
            		}
            	}
            	return me;
            }
            return 1000000;
        }

        public void remove() {
            throw new UnsupportedOperationException(
                    "vertex removal not implemented");
        }

    }

    public ArrayList<Integer> shortestPath (int startVertex, int endVertex){
    	int sofar = 0;
    	ArrayList<Integer> result = new ArrayList<Integer>();
    	ArrayList<Edge> fringe = new ArrayList<Edge>();
		double inf = Double.POSITIVE_INFINITY;
		
		for(int i = 0; i < myAdjLists.length; i++){
			if(i == startVertex){
				fringe.add(new Edge(0, startVertex, 0));
			}
			else{
				fringe.add(new Edge(0, i, inf));
			}
		}
		
		while(!fringe.isEmpty()){
	
			Edge me = shortdistance(fringe);
			
			result.add(me.myTo);
			fringe.remove(me);
			sofar += (Integer)me.myEdgeInfo;
			if(me.myTo == endVertex){
				break;
			}

					for(Edge checker: myAdjLists[me.myTo]){
					
						if(inside(checker.myTo, fringe)){
						if(fringe.get(position(checker.myTo, fringe)).myEdgeInfo instanceof Double){
							fringe.get(position(checker.myTo, fringe)).changedistance((sofar + (Integer)checker.myEdgeInfo));
							fringe.get(position(checker.myTo, fringe)).changefrom(me.myTo);
						
						}
						else if((sofar + (Integer)checker.myEdgeInfo) < (Integer)fringe.get(position(checker.myTo, fringe)).myEdgeInfo){
							fringe.get(position(checker.myTo, fringe)).changedistance((sofar + (Integer)checker.myEdgeInfo));
							fringe.get(position(checker.myTo, fringe)).changefrom(me.myTo);
						}
					}
				}
			}
				
		
		return result;
    }
    public boolean inside(int w, ArrayList<Edge> fringe){
    	for(Edge a: fringe){
    		if(a.myTo == w){
    			return true;
    		}	
    	}
    	return false;
    }
    public int position(int w, ArrayList<Edge> fringe){
    	int l = 0;
    	for(Edge a: fringe){
    		if(a.myTo == w){
    			return l;
    		}
    		l++;
    	}
    	return 100000;
    }
    public Edge shortdistance(ArrayList<Edge> fringe){
    	int i = 10000000;
    	Edge smallest = null; 
    	for(Edge a: fringe){
    		if(a.myEdgeInfo.equals(Double.POSITIVE_INFINITY)){
    			
    		}
    		else if((Integer)a.myEdgeInfo < i){
    			i = (Integer)a.myEdgeInfo;
    			smallest = a;
    		}
    	}
    	return smallest;
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
        public void changefrom(int a){
        	myFrom = a;
        }
        public Integer to() {
            return myTo;
        }
        
        public Integer from(){
        	return myFrom;
        }
        public void changedistance(Object a){
        	myEdgeInfo = a;
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
//        Graph weirdo = new Graph(7);
//        weirdo.addUndirectedEdge(0,2); 
//        weirdo.addUndirectedEdge(0,3); 
//        weirdo.addUndirectedEdge(1,4); 
//        weirdo.addUndirectedEdge(1,5); 
//        weirdo.addUndirectedEdge(2,3); 
//        weirdo.addUndirectedEdge(2,6); 
//        weirdo.addUndirectedEdge(4,5); 
//        weirdo.path(6, 0);
//        Graph newbie = new Graph(5);
//        newbie.addEdge(0,1); 
//        newbie.addEdge(1,2); 
//        newbie.addEdge(2,0); 
//        newbie.addEdge(2,3); 
//        newbie.addEdge(4,2); 

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
        Graph newbie = new Graph(4);
        newbie.addEdge(0, 1, 5);
        newbie.addEdge(1, 2, 10);
        newbie.addEdge(2, 3, 15);
        newbie.addEdge(0, 2, 2);
        
        System.out.println();
        System.out.println("Dijkstra's");
        System.out.print(newbie.shortestPath(0,2));
    }

}
