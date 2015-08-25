
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;
  
public class Graph implements Iterable<Integer>{
  
    private LinkedList<Edge>[] myAdjLists;
    private int myVertexCount;
    private int myEdgeCount;
    private int myStartVertex;
  
    // Initialize a graph with the given number of vertices and no edges.
    public Graph(int numVertices) {
        myAdjLists = new LinkedList[numVertices];
        myStartVertex = 0;
        myEdgeCount = 0;
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
        myAdjLists[v1].add(new Edge(v1,v2,edgeInfo));
        myEdgeCount+=1;
    }
  
    // Add to the graph an undirected edge from vertex v1 to vertex v2,
    // with the given edge information.
    public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
        //add both (v1,v2) & (v2,v1) because directed edges go both ways
        myAdjLists[v1].add(new Edge(v1,v2,edgeInfo));
        myAdjLists[v2].add(new Edge(v2,v1,edgeInfo));
        myEdgeCount+=2;
    }
  
    // Return true if there is an edge from vertex "from" to vertex "to";
    // return false otherwise.
    public boolean isAdjacent(int from, int to) {
       int i = 0;
       while(i < myAdjLists[from].size()){
           if(myAdjLists[from].get(i).to().equals(to))
               return true;
           i++;
       }return false;
    }
  
    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public List<Integer> neighbors(int vertex) {
        // your code here
        int i = 0;
        List<Integer> vertexList = new LinkedList<Integer>();
        while(i < myAdjLists[vertex].size()){
            vertexList.add(myAdjLists[vertex].get(i).to());
            i++;
        }return vertexList;
          
    }
  
    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
        for(int i = 0; i < myAdjLists.length; i++){
            int j = 0;
            while(j < myAdjLists[i].size()){
                if(myAdjLists[i].get(j).to().equals(vertex))
                    count++;
                j++;
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
            fringe.add(start);
        }
  
        public boolean hasNext() {
            for(Integer i : fringe){
                if(!visited.contains(i))
                    return true;
            }return false;
        }
  
        public Integer next() {
            Integer current = fringe.pop();
            if(!visited.contains(current)){
                for(Integer i : neighbors(current)){
                    if(!visited.contains(i)){
                        fringe.push(i);
                    }
                }
                 
            } visited.add(current);
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
        if(startVertex == stopVertex){
            return true;
        }else{
            ArrayList<Integer> visited = visitAll(startVertex);
            for(Integer i : visited){
                if(i == stopVertex)
                    return true;
            }return false;
        }
    }
  
  
    // Returns the path from startVertex to stopVertex.
    // If no path exists, returns an empty arrayList.
    // If startVertex == stopVertex, returns a one element arrayList.
    public ArrayList<Integer> path(int startVertex, int stopVertex) {
        if(pathExists(startVertex, stopVertex)){
            ArrayList<Integer> visited = new ArrayList<Integer>();
            Iterator<Integer> iter = new DFSIterator(startVertex);
            ArrayList<Integer> toRtn = new ArrayList<Integer>();
            while (iter.hasNext()) {
                int temp = iter.next();
                if(temp == stopVertex)
                    break;
                visited.add(temp);
            }
            toRtn.add(0,stopVertex);
            int lastVertex = stopVertex;
            int i = visited.size()-1;
            while(i >= 0){
                if(isAdjacent(visited.get(i), lastVertex)){
                    toRtn.add(0,visited.get(i));
                    lastVertex = visited.get(i);
                }i--;
            }return toRtn;
        }else
            return new ArrayList<Integer>();
        // you supply the body of this method
    }
     
    public ArrayList<Integer> shortestPath (int startVertex, int endVertex){
        if(!pathExists(startVertex, endVertex))
            return new ArrayList<Integer>();
        ArrayHeap<Integer> myHeap = new ArrayHeap<Integer>();
        ArrayList<Integer> finished = new ArrayList<Integer>(myVertexCount);
        HashMap<Integer,Integer> myPredecessors = new HashMap<Integer, Integer>(myVertexCount);
        myStartVertex = startVertex;
        myHeap.insert(startVertex, 0.0); //Add the startVertex with a priority of 0.0
        System.out.println(myEdgeCount);
        myHeap.setDistancesSize(myVertexCount);
        myHeap.setShortestDistance(startVertex,0);
        for(int i = 0; i < myAdjLists.length; i++){
            if(i != startVertex){
                myHeap.insert(i, Double.POSITIVE_INFINITY);
                myHeap.setShortestDistance(i,Integer.MAX_VALUE);
                 
            }
        }
        int currentVertex =-1;
        while(currentVertex != endVertex){
            currentVertex = myHeap.removeMin().item();
            List<Integer> neighbors = neighbors(currentVertex);
            for(Integer i : neighbors){
                int index = 0;
                while(!myAdjLists[currentVertex].get(index).to().equals(i))
                    index++;
                Integer recentEdgeLength = (Integer)myAdjLists[currentVertex].get(index).info();
                if(!finished.contains(i)){
                    if(myHeap.shortestDistance(i) > myHeap.shortestDistance(currentVertex) + recentEdgeLength){
                        myHeap.setShortestDistance(i,myHeap.shortestDistance(currentVertex) + recentEdgeLength);
                        myPredecessors.put(i, currentVertex);
                        myHeap.changePriority(i, recentEdgeLength);
                    }
                         
                }
             
            }finished.add(currentVertex);
             
        }
        return finished;
         
         
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
        private ArrayList<Integer> currentInDegrees;
        private HashSet<Integer> added;
        // more instance variables go here
  
        public TopologicalIterator() {
            fringe = new Stack<Integer>();
            currentInDegrees = new ArrayList<Integer>();
            added = new HashSet<Integer>();
            for(int i = 0; i < myVertexCount; i++){
                //System.out.println(inDegree(i));
                if(inDegree(i) == 0){
                    fringe.push(i);
                    added.add(i);
                }
                currentInDegrees.add(inDegree(i));
            }
            // more statements go here
        }
  
        public boolean hasNext() {
            return !fringe.isEmpty();
        }
  
        public Integer next() {
            // return new Integer(0);
            // you supply the real body of this method
            int currentInt = fringe.pop();
            List<Integer> myNeighbors = neighbors(currentInt);
            for(Integer i : myNeighbors){
                currentInDegrees.set(i, currentInDegrees.get(i)-1);
            }for(int j = 0; j < currentInDegrees.size(); j++){
                if(currentInDegrees.get(j) == 0 && !added.contains(j)){
                    fringe.push(j);
                    added.add(j);
                }
            }return currentInt;
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
        //Testing for isAdjacent
        System.out.println(g1.isAdjacent(1,2));
        System.out.println(g1.isAdjacent(0,2));
        System.out.println(g1.isAdjacent(2,0));
        System.out.println(g1.isAdjacent(3,4));
        //Testing for inDegree
        System.out.println(g1.inDegree(3));
        System.out.println(g1.inDegree(2));
        System.out.println(g1.inDegree(1));
        //Testing for neighbors
        System.out.println(g1.neighbors(3));
        System.out.println(g1.neighbors(0));
          
          
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
        //System.out.println(result);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
          
        Graph g3 = new Graph(30);
        g3.addUndirectedEdge(1, 3);
        g3.addUndirectedEdge(2, 5);
        g3.addUndirectedEdge(2, 4);
        g3.addUndirectedEdge(3, 4);
        g3.addUndirectedEdge(3, 7);
        g3.addUndirectedEdge(3, 9);
        g3.addUndirectedEdge(3, 6);
        g3.addUndirectedEdge(3, 1);
        g3.addUndirectedEdge(4, 8);
        g3.addUndirectedEdge(4,7);
        g3.addUndirectedEdge(4, 9);
        g3.addUndirectedEdge(4, 6);
        g3.addUndirectedEdge(4, 3);
        g3.addUndirectedEdge(5, 8);
        g3.addUndirectedEdge(5,7);
        g3.addUndirectedEdge(6, 7);
        g3.addUndirectedEdge(6, 9);
        g3.addUndirectedEdge(7,8);
        g3.addUndirectedEdge(7, 10);
        g3.addUndirectedEdge(7, 9);
         
        System.out.println("Traversal starting at 1");
        result = g3.visitAll(1);
        Iterator<Integer> iter10;
        iter10 = result.iterator();
        while (iter10.hasNext()) {
            System.out.println(iter10.next() + " ");
        }
          
        System.out.println();
        System.out.println();
          
        Graph g4 = new Graph(14);
        g4.addUndirectedEdge(0,2, 7); 
        g4.addUndirectedEdge(0,3, 5); 
        g4.addUndirectedEdge(1,4,1); 
        g4.addUndirectedEdge(1,5, 4); 
        g4.addUndirectedEdge(2,3, 9); 
        g4.addUndirectedEdge(2,6, 8); 
        g4.addUndirectedEdge(4,5,1);
        result = g4.visitAll(1);
        Iterator<Integer> iter11;
        iter11 = result.iterator();
        while (iter11.hasNext()) {
            System.out.println(iter11.next() + " ");
        }
        System.out.println(g4.path(1, 5));
        System.out.println(g4.shortestPath(1, 5 ));
        System.out.println(g4.pathExists(0, 6));
        System.out.println(g4.pathExists(5, 1));
        System.out.println(g4.pathExists(6, 0));
        System.out.println(g4.pathExists(0, 5));
        System.out.println(g4.pathExists(1, 6));
          
        System.out.println();
        System.out.println();
          
        Graph g5 = new Graph(15);
        g5.addEdge(0,3, 1); 
        g5.addEdge(1,3, 2); 
        g5.addEdge(2,1, 2); 
        g5.addEdge(2,3, 10); 
        g5.addEdge(4,2, 5); 
        g5.addEdge(4, 3,55);
        System.out.println(g5.pathExists(1, 3));
        System.out.println(g5.path(1,3));
        System.out.println(g5.shortestPath(2,3));
        System.out.println(g5.shortestPath(4,3));
        System.out.println(g5.shortestPath(2,4));
        System.out.println(g5.pathExists(4, 3));
        System.out.println(g5.pathExists(3, 4));
    }
  
}