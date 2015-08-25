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
        //your code here
    	Edge NewEdge = new Edge(v1,v2,edgeInfo);
    	for(Edge e:myAdjLists[v1]){
    		if(e.myFrom == v1 && e.myTo == v2){
    			return;
    		}
    	}
    	myAdjLists[v1].add(NewEdge);
    	return;
    }
 
    // Add to the graph an undirected edge from vertex v1 to vertex v2,
    // with the given edge information.
    public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
        //your code here
    	Edge NewEdge1 = new Edge(v1,v2,edgeInfo);
    	Edge NewEdge2 = new Edge(v2,v1,edgeInfo);
    	
    	for(Edge e:myAdjLists[v1]){
    		if(e.myFrom == v1 && e.myTo == v2){
    			addEdge(v2,v1);
    			return;
    		}
    	}
    	for(Edge e:myAdjLists[v2]){
    		if(e.myFrom == v2 && e.myTo == v1){
    			addEdge(v1,v2);
    			return;
    		}
    	}
    	
    	myAdjLists[v1].add(NewEdge1);
    	myAdjLists[v2].add(NewEdge2);

    	return;
    }

    // Return true if there is an edge from vertex "from" to vertex "to";
    // return false otherwise.
    public boolean isAdjacent(int from, int to) {
        //your code here
    	Iterator temp = myAdjLists[from].iterator();
        while(temp.hasNext()){
        	if(((Edge) temp.next()).to() == to){
        		return true;
        	}
        }
        return false;
    }

    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public List neighbors(int vertex) {
        // your code here
    	ArrayList<Integer> vertices = new ArrayList<Integer>();
    	if(vertex >= 0 && vertex < myVertexCount){
    		Iterator curr = myAdjLists[vertex].iterator();
        	while(curr.hasNext()){
        		vertices.add(((Edge)curr.next()).to());
        	}

        	return vertices;
    	}
    	return vertices;
    }

    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
        //your code here
        for(int i = 0; i<myVertexCount;i++ ){
        	if(isAdjacent(i,vertex)){
        		count++;
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
        	fringe.push(start);
        	visited = new HashSet<Integer>();
        }

        public boolean hasNext() {
            //your code here
        	int temp = fringe.peek();
        	while(visited.contains(temp)){	
        		fringe.pop();
        		if(!fringe.empty()){
        			temp = fringe.peek();
        		} else {
        			return false;
        		}
        	}

            return !fringe.isEmpty();
        }

        public Integer next() {
        	int curr = fringe.peek();
        	if(!hasNext()){
        		return null;
        	}
			visited.add(curr);
			ArrayList neighbors = (ArrayList) neighbors(curr);
			int[] nums = new int[neighbors.size()];
			for(int i = 0;i<neighbors.size();i++){
				nums[i] = (int) neighbors.get(i);	
			}	
			for(int i=1; i < nums.length;i++){
				int temp = nums[i];
				int j;
				for(j = i - 1; j >= 0 && temp > nums[j]; j--)
					nums[j+1] = nums[j];
				nums[j+1] = temp;
			}
			for(int i = 0;i < nums.length;i++){
				fringe.push(nums[i]);
			}
            return curr;
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
    	Iterator path = visitAll(startVertex).iterator();
    	while(path.hasNext()){
    		if((int) path.next() == stopVertex){
    			return true;
    		}
    	}
    	return false;
    }


    // Returns the path from startVertex to stopVertex.
    // If no path exists, returns an empty arrayList.
    // If startVertex == stopVertex, returns a one element arrayList.
    public ArrayList<Integer> path(int startVertex, int stopVertex) {
    	ArrayList<Integer> Path = new ArrayList<Integer>();
    	int curr = 0;
    	if(pathExists(startVertex, stopVertex)){
    		Iterator Paths = visitAll(startVertex).iterator();
    		while((curr = (int)(Paths.next())) != stopVertex){
    			Path.add(curr);
    		}
    		Path.add(curr);
    	}
    	return Path;
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
        private int[] Degree;
        private int minDegree;
        private int index;
        private ArrayList<Integer> visited;

        public TopologicalIterator() {
        	visited = new ArrayList<Integer>();
            fringe = new Stack<Integer>();     	
            minDegree = inDegree(0);
            Degree = new int[myVertexCount];
            for(int i = 1; i< myVertexCount; i++){
            	Degree[i] = inDegree(i);
            	if(minDegree > Degree[i]){
            		minDegree = Degree[i];
            	}
            }
            for(int i = 0; i < myVertexCount; i++){
            	if(Degree[i] == minDegree){
            		fringe.push(i);
            		visited.add(i);
            	}
            }
        }

        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        public Integer next() {
        	boolean check = false;
        	for(int i =0; i < myVertexCount; i++){
        		System.out.print(Degree[i]+"*");
        		
        	}
        	System.out.println();
        	int curr = fringe.pop();
        	Iterator Next = neighbors(curr).iterator();
        	while(Next.hasNext()){
        		int temp = (int) Next.next();
        		Degree[temp] = Degree[temp]-1;
        	}

        	if(fringe.isEmpty()){
        		for(int i = 0; i < myVertexCount;i++){
        			if(Degree[i] == minDegree && !visited.contains(i)){
        				check = true;
        				fringe.push(i);
        				visited.add(i);
        			}
        		}
        		if(!check){
        			minDegree = minDegree+1;
        			for(int i = 0; i < myVertexCount;i++){
        				if(Degree[i] == minDegree && !visited.contains(i)){
        					fringe.push(i);
        					visited.add(i);
        				}
        			}
        		}
        	}
        	return curr;
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

    }

    public static void main(String[] args) {
        ArrayList<Integer> result;
        Graph gg1 = new Graph(11);
        gg1.addUndirectedEdge(1, 3);
        gg1.addUndirectedEdge(2, 5);
        gg1.addUndirectedEdge(2, 4);
        gg1.addUndirectedEdge(3, 4);
        gg1.addUndirectedEdge(3, 7);
        gg1.addUndirectedEdge(3, 9);
        gg1.addUndirectedEdge(3, 6);
        gg1.addUndirectedEdge(3, 1);
        gg1.addUndirectedEdge(4, 8);
        gg1.addUndirectedEdge(4, 7);
        gg1.addUndirectedEdge(4, 9);
        gg1.addUndirectedEdge(4, 6);
        gg1.addUndirectedEdge(5, 8);
        gg1.addUndirectedEdge(5, 7);
        gg1.addUndirectedEdge(6, 7);
        gg1.addUndirectedEdge(6, 9);
        gg1.addUndirectedEdge(7, 8);
        gg1.addUndirectedEdge(7, 10);
        gg1.addUndirectedEdge(7, 9);
        System.out.println("Traversal starting at Try");
        result = gg1.visitAll(1);
        Iterator<Integer> iter11;
        iter11 = result.iterator();
        System.out.println(gg1.pathExists(0, 4));
        System.out.println(gg1.pathExists(1, 4));
        while (iter11.hasNext()) {
            System.out.println(iter11.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        
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
        System.out.println(g1.pathExists(0, 3));

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
        System.out.println(g1.pathExists(0, 4));
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
