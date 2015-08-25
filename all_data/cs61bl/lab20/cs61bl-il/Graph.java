import java.util.ArrayList;
import java.util.Arrays;
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
    	myAdjLists[v1].add(new Edge(v1, v2, edgeInfo));
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
    	for (int i = 0; i < myAdjLists[from].size(); i ++) {
    		if (myAdjLists[from].get(i).to() == to) {
    			return true;
    		}
    	}
    	return false;
    }

    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public List neighbors(int vertex) {
        // your code here
    	ArrayList<Integer> rtnList = new ArrayList<Integer>();
        for (int i = 0; i < myAdjLists[vertex].size(); i++) {
        	if (isAdjacent(vertex,myAdjLists[vertex].get(i).to())) {
        	rtnList.add(myAdjLists[vertex].get(i).to());
        }
        }
        return rtnList;
    }
    /**
     * returns a list of all the edges of a vertex
     * 
     */
       public List connections(int vertex) {
    	   ArrayList<Integer> rtnList = new ArrayList<Integer>();
    	   for (int i = 0; i < myAdjLists[vertex].size(); i++) {
    		   rtnList.add(myAdjLists[vertex].get(i).to());
    	   }
    	   return rtnList;
       }

     

    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
        //your code here
        for (int i = 0; i < myVertexCount; i ++) {
        	if (isAdjacent(i, vertex)) {
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
    public class DFSIterator implements Iterator<Integer> {

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
          
              if (!hasNext()) {
              	throw new NoSuchElementException("No more vertices");
              }
             
          	Integer curr = fringe.pop();
          	
          	List adj = connections(curr);

      		for (int i = 0; i < adj.size(); i++) {       //adds the adjacent neighbors highest to lowest
      			
      			Integer currInt = (Integer) adj.get(i);
      			
      			if (!visited.contains(currInt)) {
      				visited.add(currInt);
      				fringe.push(currInt);
   
      			} 
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
    	
        if (startVertex == stopVertex) {
        	return true;
        } 
        ArrayList<Integer> path = visitAll(startVertex);
        for (int i = 0; i < path.size(); i++) {
        	if (path.get(i) == stopVertex) {
        		return true;
        	}
        }
        return false;

    }


    // Returns the path from startVertex to stopVertex.
    // If no path exists, returns an empty arrayList.
    // If startVertex == stopVertex, returns a one element arrayList.
    public ArrayList<Integer> path(int startVertex, int stopVertex) {
        ArrayList<Integer> rtnPath = new ArrayList<Integer>();
        // you supply the body of this method
        if (!pathExists(startVertex, stopVertex)) {
        	return rtnPath;
        } else {
        	ArrayList<Integer> visited = new ArrayList<Integer>();
        	Iterator<Integer> iter = new DFSIterator(startVertex);
        	while (iter.hasNext()) {
        		Integer num = iter.next();
        		if (num == stopVertex) {
        			visited.add(num);
        			break;
        		}else {
        			visited.add(num);
        		}
        	}
        
        	Integer curr = stopVertex;    //starts at visited.size() - 1
        	Integer count = visited.size() - 2;
        	Integer prev = visited.get(count);        	
        	while (count >= 0) {
        		if(isAdjacent(prev, curr)) {
        			rtnPath.add(0,curr);
        			curr = prev;		
        		}
        		count--;
        		if (count >= 0) {
        		prev = visited.get(count);
        		}
        	}
        	rtnPath.add(0, startVertex);
        }
        return rtnPath;
        
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
        private Integer[] currentInDegree;
        private ArrayList<Integer> results;

        // more instance variables go here

        public TopologicalIterator() {
            fringe = new Stack<Integer>();
            // more statements go here
            currentInDegree = new Integer[myVertexCount];
            results = new ArrayList<Integer>();
            for (int i = 0; i<myVertexCount;i++){
            	currentInDegree[i] = inDegree(i);
            }
           
            for (int i = 0; i < currentInDegree.length; i++) {
            	if (currentInDegree[i] == 0) {
            		fringe.push(i);
            	}
            }
        }

        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        public Integer next() {
     
            // you supply the real body of this method
            Integer curr = fringe.pop();

            results.add(curr);
           
            List adj = neighbors(curr);
            for (int i = 0; i < adj.size(); i++) {
        		currentInDegree[(int) adj.get(i)]--;

            	if (currentInDegree[(int) adj.get(i)] == 0) {
            		fringe.push((int) adj.get(i));
            
            	}
       
            
            }
            return curr;
//        public TopologicalIterator() {
//            fringe = new Stack<Integer>();
//         // more statements go here
//            results = new ArrayList<Integer>();
//            currentInDegree = new Integer[myVertexCount];
//            for (int i = 0; i<myVertexCount;i++){
//            	currentInDegree[i]=new Integer(0);
//            }
//            for (int i = 0; i<myVertexCount;i++){
//            	for (Edge e : myAdjLists[i]){    	
//            		currentInDegree[e.to()]++;           		            		
//            	}
//            }
//            for (int i = 0; i <myVertexCount;i++){
//            	if(currentInDegree[i]!=null && currentInDegree[i]==0){
//            		fringe.push(i);
//            	}
//            }
//            
//        }
//
//        public boolean hasNext() {
//            return !fringe.isEmpty();
//        }
//
//        public Integer next() {
//            //return new Integer(0);
//            // you supply the real body of this method
//            
//            Integer r = fringe.pop();
//            results.add(r);
//            for (Edge e : myAdjLists[r]){        	
//        		currentInDegree[e.to()]--;	
//        		if (currentInDegree[e.to()]==0){
//        			fringe.push(e.to());
//        		}
//        	}
//            
//            return r;
            
            
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
//        Graph g2 = new Graph(6);
//      g2.addEdge(0, 1);
//      g2.addEdge(2, 0);
//      g2.addEdge(0, 5);
//      g2.addEdge(2, 3);
//      g2.addEdge(5, 1);
//      g2.addEdge(1, 3);
//      
//      g2.addEdge(5, 3);
//      g2.addEdge(5, 4);
//      g2.addEdge(3, 4);
//        Graph g2 = new Graph(7);
//      g2.addEdge(0, 1);
//      g2.addEdge(0,2);
//      g2.addEdge(1, 2);
//      g2.addEdge(1, 6);
//      g2.addEdge(1, 4);
//      g2.addEdge(6, 4);
//      
//      g2.addEdge(4, 5);
//      g2.addEdge(2, 5);
//      g2.addEdge(3, 0);
//      g2.addEdge(3, 5);

        

    }

}
