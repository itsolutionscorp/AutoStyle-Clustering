import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.List;

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
    	myAdjLists[v1].add (new Edge (v1, v2, edgeInfo));
    	
    }

    // Add to the graph an undirected edge from vertex v1 to vertex v2,
    // with the given edge information.
    public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
        //your code here
    	addEdge (v1, v2, edgeInfo);
		addEdge (v2, v1, edgeInfo);
    }

    // Return true if there is an edge from vertex "from" to vertex "to";
    // return false otherwise.
    public boolean isAdjacent(int from, int to) {
        //your code here
//    	Iterator<Edge> iter = myAdjLists[from].iterator();
//		while (iter.hasNext ( )) {
//			Edge temp = iter.next ( );
//			if (temp.to ( ) == to) {
//				return true;
//			}
//		}
//		
//        return false;
    	for(Edge temp: myAdjLists[from]){
    		if(temp.myTo==to)
    			return true;
    	}
        return false;
    }

    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public List neighbors(int vertex) {
        // your code here
    	return myAdjLists[vertex];
    }

    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
        //your code here
		for (int i=0; i<myVertexCount; i++) {
			if (isAdjacent (i, vertex)) {
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
//        int x = 0;

        public DFSIterator(Integer start) {
            //your code here
        	fringe = new Stack();
        	fringe.push (start);
        	visited = new HashSet<Integer>();
        	visited.add (start);
        }

        public boolean hasNext() {
            //your code here
        	return !fringe.isEmpty();
        }

        public Integer next() {
            //your code here
        	if (!hasNext ( )) {
				throw new NoSuchElementException ("No more elements");
			}
        	Integer temp = fringe.pop();
//        	while (visited.contains(temp) && x ==1 ){
//        		temp = fringe.pop();
//        	}
        	
			Iterator<Edge> iter = myAdjLists[temp.intValue()].iterator();
			while (iter.hasNext()) {
				Edge e = iter.next();
				if (visited.contains(e.to()) == false) {
					fringe.push(e.to());
					visited.add(e.to());			
					}
			}
//			x =1 ;
			visited.add(temp);
			return temp;
        	
//        	for(Edge e :myAdjLists[temp]){
//        		if(!visited.contains(e.to())){
//        			fringe.push(e.to());
////        			visited.add(e.to());
////        			e.myEdgeInfo = 1;
//        		}		
//        	}
//        	visited.add(temp);
//            return temp;
          
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
    		if(visitAll(startVertex).contains(stopVertex)){
    			return true;
    		}
    		return false;
    	}
       
    }


    // Returns the path from startVertex to stopVertex.
    // If no path exists, returns an empty arrayList.
    // If startVertex == stopVertex, returns a one element arrayList.
    public ArrayList<Integer> path(int startVertex, int stopVertex) {
//        return new ArrayList<Integer>();
        // you supply the body of this method
        ArrayList<Integer> result = new ArrayList<Integer>();
        Iterator<Integer> iter = new DFSIterator(startVertex);
        ArrayList<Integer> temp = new ArrayList<Integer>();
    	Stack<Integer> stack = new Stack<Integer>();
        int current = stopVertex;
        int x = 0;
        while (iter.hasNext()) {
        	int nextNode = iter.next();
        	if(nextNode==stopVertex){
        		x =1 ;
        		break;
        	}
            temp.add(nextNode);
        }
        while(current!=startVertex && (x ==1 )){
        	for(Integer i: temp){
            	if(isAdjacent(i,current)){
            		stack.push(current);
        			current = i;
        			break;
            	}
            }
        }
        result.add(startVertex);
        while(!stack.isEmpty()){
        	result.add(stack.pop());
        }
        return result;
    }
    
    public ArrayList<Integer> shortestPath (int startVertex, int endVertex){
    	dijIterator iter = new dijIterator(startVertex, endVertex);
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	while(iter.hasNext()){
    		iter.next();
    	}
    	 Map<Integer, Integer> map = iter.map();
    	 Stack<Integer> rev = new  Stack<Integer>();
    	 Integer cur = endVertex;
    	 while(cur != null){
    		 rev.push(cur);
    		 cur = map.get(cur);
    	 }
    	 
             
         while (rev.isEmpty() == false) {
             list.add(rev.pop());
         }
        
    	return list;
    }
    	
    
    
    public class dijIterator implements Iterator<Integer>{
    		
    	Queue<Edge> fringe;
         Set<Integer> visited = new HashSet<Integer>();
         Map<Integer, Integer> map = new HashMap<Integer, Integer>();
         int[] priorities = new int[myVertexCount];
         
         public dijIterator(int start, int end){
        	
        	 Comparator<Edge> sort = new Edges();
        	 fringe = new PriorityQueue<Edge>(myVertexCount*(myVertexCount -1), sort);
        	 Arrays.fill(priorities, Integer.MAX_VALUE);
        	 priorities[start] = 0;
        	 Edge sent = new Edge(start, start, 0);
        	 fringe.add(sent);
         }
         
		@Override
		public boolean hasNext() {
			return !fringe.isEmpty();
		}
		@Override
		public Integer next() {
			if(hasNext()){
				Edge node = fringe.poll();
				visited.add(node.to());
				List<Edge> dst = myAdjLists[node.to()];
				for(Edge temp: dst){
					
					if((Integer)temp.info() + priorities[node.to()] < priorities[temp.to()]  ){
						priorities[temp.to()] = priorities[node.to()] + (Integer) temp.info();
						map.put(temp.to(), node.to());
					}
					if(visited.contains(temp.to()) == false){
					fringe.add(temp);
					}
					
				}
				return node.to();
			}
			return null;
		}
		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		public  Map<Integer, Integer> map(){
			return map;
		}
		
		
		private class Edges implements Comparator<Edge>{
	    	public int compare (Edge x, Edge y){
	    		return priorities[x.to()] - priorities[y.to()];
	    	}
	    	public boolean equals(Edge x, Edge y){
	    		return x.myFrom == y.myFrom && x.to() == y.to() && x.info() == y.info();
	    	}
	    }
   
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
        private int[] currentInDegree;

        // more instance variables go here

        public TopologicalIterator() {
            fringe = new Stack<Integer>();
            // more statements go here
            currentInDegree = new int[myVertexCount];
            for(int i=0;i<myVertexCount;i++){
            	if(inDegree(i)==0){
            		fringe.push(i);
            	}else{
            		currentInDegree[i] = inDegree(i);
            	}
            }
        }

        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        public Integer next() {
            
        	Integer temp = fringe.pop();
//        	currentInDegree[temp]=-1;
        	for(Edge e:myAdjLists[temp]){
				currentInDegree[e.myTo]--;
				if(currentInDegree[e.myTo]==0)
					fringe.push(e.myTo);
			}
        	return temp;
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
        System.out.println("");
        System.out.println("");
        System.out.println("new");
       
        System.out.println("");
        Graph x = new Graph(5);
        x.addEdge(0, 1, 10);
        x.addEdge(0,3, 30);
        x.addEdge(0,4, 100);
        x.addEdge(1, 2, 50);
        x.addEdge(2,4, 10);
        x.addEdge(3,2, 20);
        x.addEdge(3,4, 60);
        ArrayList<Integer> itera = x.shortestPath(0, 4);
        for(int i = 0 ; i < itera.size(); i++){
        	System.out.println(itera.get(i));
        }
        
         
    }

}
