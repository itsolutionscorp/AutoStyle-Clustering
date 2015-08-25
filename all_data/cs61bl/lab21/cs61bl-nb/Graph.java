import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
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
    		for(int i = 0; i < myAdjLists[from].size(); i++){
    			if(myAdjLists[from].get(i).to() == to){
    				return true;
    			}
    		}
        return false;
    }

    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public List neighbors(int vertex) {
        // your code here
    		if(!myAdjLists[vertex].isEmpty()){
    			return myAdjLists[vertex];
    		}
        return null;
    }

    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
        //your code here
        for(int i = 0; i < myVertexCount; i++){
        		for(int j = 0; j < myAdjLists[i].size(); j++){
        			if(myAdjLists[i].get(j).to() == vertex){
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
        		fringe.add(start);
        }
        


       public boolean hasNext() {
            //your code here
    	   		Iterator<Integer> stackIter = fringe.iterator();
    	   		while(stackIter.hasNext()){
    	   			if(!visited.contains(stackIter.next())){
    	   				return true;
    	   			}
    	   		}
            return false;
        }

       public Integer next() {
            //your code here
            if(hasNext()){          		
            		Integer next = fringe.pop();
            		while(!fringe.isEmpty()){
            			if(visited.contains(next)){
            				next = fringe.pop();           				
            			} else {            				
            				break;
            			}
            		}  
            		if(!visited.contains(next)){
            			visited.add(next);           				
	    				for(Edge e : myAdjLists[next]){
	            			fringe.push(e.to());	            	
	            		}
	    				return next;
            		}           		
            }
            return null;
        }

 /*       public Integer next() {
        		Integer next = fringe.pop();
        		while(!hasNext()){
        			
        		}
        }
 */       
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
    		ArrayList<Integer> path = visitAll(startVertex);
    		if(path.contains(stopVertex)){
    			return true;
    		} else {
    			return false;
    		}
    }


    // Returns the path from startVertex to stopVertex.
    // If no path exists, returns an empty arrayList.
    // If startVertex == stopVertex, returns a one element arrayList.
    public ArrayList<Integer> shortestPath (int startVertex, int endVertex){
	    	ArrayList<Integer> result = new ArrayList<Integer>();
	    	if(!visitAll(startVertex).contains(endVertex)){
	    		return result;
	    	}    		
    		Queue<Item> q = new PriorityQueue<Item>();
    		Integer [] back = new Integer[myVertexCount];
    		Item [] reserve = new Item[myVertexCount];
    		HashSet<Item> visited = new HashSet<Item>();
    		for(int j = 0; j < myVertexCount; j++){
    			if(j == startVertex){
    				reserve[j] = new Item(j, 0);
    			} else {
    				reserve[j] = new Item(j, Integer.MAX_VALUE);
    			}
    			q.offer(reserve[j]);
    		}
    		Item current = q.poll();
    		visited.add(current);
    		while(current.myVertex != endVertex){    			
	    		for(int i = 0; i < myAdjLists[current.myVertex].size(); i++ ){
	    			if(!visited.contains(reserve[myAdjLists[current.myVertex].get(i).to()])){
		    			reserve[myAdjLists[current.myVertex].get(i).to()].setPathLength(current.myPathLength + (Integer)myAdjLists[current.myVertex].get(i).info());
		    			back[myAdjLists[current.myVertex].get(i).to()] = current.myVertex;
		    			q.remove(reserve[myAdjLists[current.myVertex].get(i).to()]);
		    			q.offer(reserve[myAdjLists[current.myVertex].get(i).to()]);
	    			}
	    		}
	    		current = q.poll();
	    		visited.add(current);
    		}
    		Stack<Integer> temp = new Stack<Integer>();
    		int p = endVertex;
    		while (p != startVertex){
    			temp.push(p);
    			p = back[p];    			
    		}
    		temp.push(p);
    		while (!temp.isEmpty()){
    			result.add(temp.pop());
    		}
    		return result;
    }
    
    
    private class Item implements Comparable{
    		Integer myVertex;
    		Integer myPathLength;
    		
	    	public Item(Integer number, Integer path){
	    		myVertex = number;
	    		myPathLength = path;
	    	}
	    	
	    	public void setPathLength(Integer p){
	    		myPathLength = p;
	    	}
	    	@Override
	    	public int compareTo(Object obj){
	    		if(myPathLength == ((Item)obj).myPathLength){
	    			return 0;
	    		} else if(myPathLength > ((Item)obj).myPathLength){
	    			return 1;
	    		} else {
	    			return -1;
	    		}
	    	}			
    }
 
    
    
    public ArrayList<Integer> path(int startVertex, int stopVertex) {       
        // you supply the body of this method
	    	HashSet<Integer> visited = new HashSet<Integer>();	    	
        Iterator<Integer> iter = new DFSIterator(startVertex);
        HashMap<Integer, Integer> pair = new HashMap<Integer, Integer>();
        Stack<Integer> reverse = new Stack<Integer>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(!visitAll(startVertex).contains(stopVertex)){
        		return result;
        }
        while (iter.hasNext()) {
        		Integer current = iter.next();
        		visited.add(current);
        		for(Edge e : myAdjLists[current]){
        			if(!visited.contains(e.to())){
        				pair.put(e.to(), current);
        			}        				
        		}
        		if(current == stopVertex){
        			break;
        		}
        }
        Integer i = stopVertex;
        reverse.push(i);
        while(i != startVertex){
        		reverse.push(pair.get(i));
        		i = pair.get(i);
        }
        while(!reverse.isEmpty()){
        		result.add(reverse.pop());
        }
        return result;
    }

    public ArrayList<Integer> topologicalSort() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Iterator<Integer> iter = new TopologicalIterator();
        int j = 0;
        while (iter.hasNext()) {
        		int i = iter.next();
            result.add(i);

            j++;
        }
        return result;
    }

    private class TopologicalIterator implements Iterator<Integer> {

        private Stack<Integer> fringe;
        private int[]currentInDegree;
        private HashSet<Integer> visited;
        // more instance variables go here

        public TopologicalIterator() {
            fringe = new Stack<Integer>();
            currentInDegree = new int[myVertexCount];
            visited = new HashSet<Integer>();
            for(int i = 0; i < myVertexCount; i++){
            		currentInDegree[i] = inDegree(i);
            }
            for(int i = 0; i < myVertexCount; i++){       			
    				if(currentInDegree[i] == 0){       				
        				fringe.push(i);
        				visited.add(i);
    			}
    		}
    	}
            // more statements go here
        

        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        public Integer next() {
	        	Integer current = fringe.pop();
	    		for(Edge e : myAdjLists[current]){
	    			currentInDegree[e.to()] -= 1;
	    		}
        		for(int i = 0; i < myVertexCount; i++){   
        			if(currentInDegree[i] == 0){    
        				if(!visited.contains(i)){
	        				fringe.push(i);
	        				visited.add(i);
        				}
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
        g1.addEdge(0, 1, 10);
        g1.addEdge(0, 4, 5);
        g1.addEdge(1, 4, 2);
        g1.addEdge(1, 2, 1);
        g1.addEdge(2, 3, 4);
        g1.addEdge(3, 2, 6);
        g1.addEdge(3, 0, 7);
        g1.addEdge(4, 3, 2);
        g1.addEdge(4, 2, 9);
        g1.addEdge(4, 1, 3);
//        System.out.println("Traversal starting at 1");
//        System.out.println(g1.isAdjacent(0, 4));
//        System.out.println(g1.inDegree(2) +" in degree");
//        
//        result = g1.visitAll(1);
        Iterator<Integer> iter;
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
        System.out.println("Path from 3 to 1");
        result = g1.shortestPath(3, 1);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 0 to 4");
        result = g1.shortestPath(0, 4);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 1 to 3");
        result = g1.shortestPath(1, 3);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 0 to 2");
        result = g1.shortestPath(0, 2);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 4 to 0");
        result = g1.shortestPath(4, 0);
//        if (result.size() != 0) {
//            System.out.println("*** should be no path!");
//        }
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }

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
//        Graph g3 = new Graph(7);
//        g3.addUndirectedEdge(0,2); 
//        g3.addUndirectedEdge(0,3); 
//        g3.addUndirectedEdge(1,4); 
//        g3.addUndirectedEdge(1,5); 
//        g3.addUndirectedEdge(2,3); 
//        g3.addUndirectedEdge(2,6); 
//        g3.addUndirectedEdge(4,5);
//        System.out.println(g3.pathExists(4,6));
//        
//        
//        Graph g4 = new Graph(5);
//		g4.addEdge(0,1); 
//		g4.addEdge(1,2); 
//		g4.addEdge(2,0); 
//		g4.addEdge(2,3); 
//		g4.addEdge(4,2);
//		System.out.println(g4.pathExists(1,3));

    }

}
