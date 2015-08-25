
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Stack;



public class Graph implements Iterable<Integer>{

    private LinkedList<Edge>[] myAdjLists;
    private int myVertexCount;
    private int myStartVertex;
    private ArrayList<Edge> w;
    private ArrayList<Edge> values;
    private ArrayList<Edge> contents = new ArrayList<Edge>();
   

    // Initialize a graph with the given number of vertices and no edges.
    public Graph(int numVertices) {
        myAdjLists = new LinkedList[numVertices];
        myStartVertex = 0;
        for (int k = 0; k < numVertices; k++) {
            myAdjLists[k] = new LinkedList<Edge>();
        }
        myVertexCount = numVertices;
        values = new ArrayList<Edge>();
  
        w = new ArrayList<Edge>();
    }
	public void insert(Edge item) {
		if (contents.isEmpty()) {
			Edge n = null;
			contents.add(n);
			
			contents.add(item);
		} else {
			contents.add(item);
		}
		bubbleUp(contents.size() - 1);

	}
	public void changePriority(Edge item, Integer priority) {
		// TODO Complete this method!
//		System.out.println("changing priority");
		Edge node = null;
		int index = 0;
		for (int i = 1; i < contents.size(); i++) {
			if (getNode(i).equals(item)) {
				node = getNode(i);
				index = i;
				break;
			}
		}
		node.myEdgeInfo = priority;
		bubbleUp(index);
		bubbleDown(index);
		
	}
	

	private void swap(int index1, int index2) {
		Edge node1 = getNode(index1);
		Edge node2 = getNode(index2);
//		System.out.println("testing swap");
		this.contents.set(index1, node2);
		this.contents.set(index2, node1);
//		System.out.println("---------");
//		System.out.println(contents.toString());
//		System.out.println("---------");
	}
	private Edge getNode(int index) {
		if (index >= contents.size()) {
			return null;
		} else {
			return contents.get(index);
		}
	}
	private int getLeftOf(int i) {
		// TODO Complete this method!
		return 2 * i;
	}

	/**
	 * Returns the index of the node to the right of the node at i.
	 */
	private int getRightOf(int i) {
		// TODO Complete this method!
		return 2 * i + 1;
	}

	/**
	 * Returns the index of the node that is the parent of the node at i.
	 */
	private int getParentOf(int i) {
		// TODO Complete this method!
		return i/2;
	}
	public Edge removeMin() {
		// TODO Complete this method!
		swap(1, contents.size() - 1);
		Edge rtn = contents.remove(contents.size() - 1);
		bubbleDown(1);
		return rtn;
	}
	
	public boolean isEmpty() {
		return (contents.get(1) == null);
	}
	public boolean contains(Edge item) {
		for (int i = 1; i < contents.size(); i++) {
			if (contents.get(i).equals(item)) {
				return true;
			}
		}return false;
	}
	
	private void bubbleUp(int index) {
		// TODO Complete this method!
		while (index > 1 && min(getParentOf(index),index) == index) {
			swap(getParentOf(index), index);
			index = getParentOf(index);
		}
	}
	private int min(int index1, int index2) {
		Edge node1 = getNode(index1);
		Edge node2 = getNode(index2);
		if (node1 == null) {
			return index2;
		} else if (node2 == null) {
			return index1;
		} else if ((Integer)node1.myEdgeInfo < (Integer)node2.myEdgeInfo) {
			return index1;
		} else {
			return index2;
		}
	}
	private void bubbleDown(int index) {
		// TODO Complete this method!
		while (min(index,getLeftOf(index)) == getLeftOf(index)
				&& min(index,getRightOf(index)) == getRightOf(index)) {
			if (min(getRightOf(index),getLeftOf(index)) == getLeftOf(index)) {
				swap(getLeftOf(index), index);
				index = getLeftOf(index);
			} else {
				swap(getRightOf(index), index);
				index = getRightOf(index);
			}
		}
	}
	public List allEdges() {
		ArrayList<Edge> rtn = new ArrayList<Edge>();
		for (int i = 0; i < myAdjLists.length; i++) {
			rtn.addAll(allNeighbors(i));
		}
		return rtn;
	}
    public List allNeighbors(int vertex) {
    	ArrayList<Edge> rtnList = new ArrayList<Edge>();
 	   for (int i = 0; i < myAdjLists[vertex].size(); i++) {
 		   rtnList.add(myAdjLists[vertex].get(i));
 	   }
//        boolean sorted = false;
//        while (! sorted) {                                   //returns a list of Edge objects sorted based on to() values
//        	sorted = true;
//        	for (int k = 0; k < rtnList.size(); k++) {
//        		if (rtnList.get(k).to() > rtnList.get(k+1).to()) {
//        			Edge temp = rtnList.get(k+1);
//        			rtnList.set(k+1, rtnList.get(k));
//        			rtnList.set(k, temp);
//        			sorted = false;
//        		}
//        	}
//        }
 	   return rtnList;
    }
    
    public ArrayList<Integer> shortestPath (int startVertex, int endVertex){
        //your code here...
    	ArrayList<Integer> rtn = new ArrayList<Integer>();
    	Edge startEdge = new Edge(-1, startVertex, 0);
    	insert(startEdge);  //-1 to indicate the start
  
    	List alledges = allEdges();                     //all Vertices;
    	for (int i = 0; i < alledges.size(); i++ ) {
    		Edge e = (Edge) alledges.get(i);
    		values.add(new Edge(e.myFrom, e.to(), e.myEdgeInfo));

                                   //storing the given myEdgeInfo. 
    	
    		e.myEdgeInfo = Integer.MAX_VALUE;
    		insert(e);
    		
    	}
    
//    	System.out.println(contents.toString());
    	while (!isEmpty()) {
    		Integer curr = costCalculator(startVertex, endVertex).to();
    		rtn.add(curr);
    		if (curr == endVertex) {
    			break;
    		}
    	}
    	return rtn;
    	
    	
    	
    }
    public Edge costCalculator(int startVertex, int endVertex) {
    	
    
		Edge curr = removeMin();
//		Edge currcorr = null;
//		for (int j  = 0; j <myAdjLists[curr.to()].size(); j++) {
//			if (curr.to().equals(myAdjLists[curr.to()].get(j).myFrom) && curr.myFrom.equals(myAdjLists[curr.to()].get(j).to())) {
//			currcorr= myAdjLists[curr.to()].get(j);
//		}
//			if (currcorr != null) {
//				System.out.println("this is curr " + curr.toString());
//				System.out.println("the corresponding edge " + currcorr.toString());
//				changePriority(currcorr, (Integer)curr.myEdgeInfo);
//				removeMin();
//				myAdjLists[(Integer)curr.to()].remove(currcorr);
//				System.out.println("curr corr exists " +contents.toString());
//				
//				
//			}
//		}
		for (int j = 0; j < values.size(); j++) {
			if (curr.to().equals(values.get(j).myFrom)) {
				w.add(values.get(j));
			}
		}
    	System.out.println("this is w before removing " + w.toString());
		for (int i = 0; i < myAdjLists[(Integer)curr.to()].size(); i++) {
			Edge neighbor = myAdjLists[(Integer)curr.to()].get(i);
			Integer corr = 0;                                        //get the corresponding myInfo of the neighbor edge
		
//			System.out.println("this is w " + w.toString());
			for (int k = 0; k < w.size(); k++) {
				
				if (w.get(k).to().equals(neighbor.to())) {
				
					corr = (Integer)w.get(k).info();
					w.remove(w.get(k));
					break;
				}
			}
			if (curr.pred != null) {
				System.out.println("this is predecessor's " + curr.pred.info());
			}
			System.out.println("this is w " + w.toString());
			System.out.println(contents.toString());
			System.out.println("this is curr " + curr.toString());
			
			System.out.println( " this is the neighbor " + neighbor.toString());
			System.out.println("testing corr " + corr);
			System.out.println("this is size of contents " + contents.size());
			System.out.println("----------------------------");
			if (contains(neighbor)) {
    			if (curr.pred == null) {
    				
    				curr.pred = new Edge(-1,-1, 0);     //a distance of 0 for the previous path/edge
    			}
    			
    			if ((Integer)neighbor.info() > corr + (Integer)curr.pred.info()) {
					changePriority(neighbor, corr + (Integer)curr.pred.info());
					neighbor.pred = neighbor;
    				}
			}
		}
		return curr;
    	
    		
    	
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
        boolean sorted = false;
        while (! sorted) {                                   //returns a sorted list
        	sorted = true;
        	for (int k = 0; k < rtnList.size(); k++) {
        		if (rtnList.get(k) > rtnList.get(k+1)) {
        			Integer temp = rtnList.get(k+1);
        			rtnList.set(k+1, rtnList.get(k));
        			rtnList.set(k, temp);
        			sorted = false;
        		}
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
           boolean sorted = false;
           while (! sorted) {                                   //returns a sorted list
           	sorted = true;
           	for (int k = 0; k < rtnList.size(); k++) {
           		if (rtnList.get(k) > rtnList.get(k+1)) {
           			Integer temp = rtnList.get(k+1);
           			rtnList.set(k+1, rtnList.get(k));
           			rtnList.set(k, temp);
           			sorted = false;
           		}
           	}
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
            
        }

        public void remove() {
            throw new UnsupportedOperationException(
                    "vertex removal not implemented");
        }

    }

    class Edge implements Comparable<Edge>{

        private Integer myFrom;
        private Integer myTo;
        private Object myEdgeInfo;
        private Edge pred;
        private Integer myPriority;

        public Edge(int from, int to, Object info) {
            myFrom = new Integer(from);
            myTo = new Integer(to);
            myEdgeInfo = info;
            pred = null;
            myPriority = null;
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
        public boolean equals(Edge o) {
        	return ((Integer)to()== (Integer) o.to());
        }

	

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			if ((Integer) info() < (Integer)o.info()) {
				return -1;
			}
			else {
				return 1;
			}
       
    }
    }
//    private class Wrapper {
//    	private Edge edge;
//    	private Integer priority;
//    	public Wrapper(Edge e, Integer prior) {
//    		edge = e;
//    		priority = prior;
//    	}
//    }
//    

    public static void main(String[] args) {
        ArrayList<Integer> result;

//        Graph g1 = new Graph(5);
//        g1.addEdge(0, 1);
//        g1.addEdge(0, 2);
//        g1.addEdge(0, 4);
//        g1.addEdge(1, 2);
//        g1.addEdge(2, 0);
//        g1.addEdge(2, 3);
//        g1.addEdge(4, 3);
        Graph g1 = new Graph(7);
        g1.addUndirectedEdge(0, 1, 4);
        g1.addUndirectedEdge(0, 2, 3);
        g1.addUndirectedEdge(0, 4, 7);
        g1.addUndirectedEdge(1, 3, 5);
        g1.addUndirectedEdge(1, 2, 6);
        g1.addUndirectedEdge(2, 4, 8);
        g1.addUndirectedEdge(2, 3, 11);
        g1.addUndirectedEdge(3, 4, 2);
        g1.addUndirectedEdge(3, 6, 2);
        g1.addUndirectedEdge(3, 5, 2);
        g1.addUndirectedEdge(5, 6, 3);
        g1.addUndirectedEdge(4, 6, 5);
        
        
        System.out.println(g1.shortestPath(0, 5).toString());

        
//        
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
