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
    
    public ArrayList<Integer> shortestPath (int startVertex, int endVertex){
        //your code here...
    	ArrayList<Integer> result = new ArrayList<Integer>();
    	result = Dijkstra (startVertex, endVertex);
    	return result;
    }
    	

    	
    	// suanfa
	public ArrayList<Integer> Dijkstra (Integer start, Integer end) {
		ArrayHeap <Integer> fringe;
		HashSet <Integer> settled;
		HashMap <Integer, Integer> PrevTrack;
		ArrayList<Integer> CompleteResult = new ArrayList<Integer>();
		Integer currDis;
		Integer curr;
		Integer process;
		
		fringe = new ArrayHeap <Integer>();
    	settled = new HashSet <Integer>();
   		PrevTrack = new HashMap <Integer, Integer>();
		
		int[] pre = new int[myAdjLists.length];
		Double[] distanceTrack = new Double[myAdjLists.length];
		for (int i=0; i<distanceTrack.length; i++) {
			distanceTrack[i] = Double.POSITIVE_INFINITY;
			if(i==start) {
				distanceTrack[i]= 0.0;
				fringe.insert(i, distanceTrack[i]);
			}
		}
		
		try {
		while (fringe.peek()!=null) {
				ArrayHeap<Integer>.Node current = fringe.removeMin();
				settled.add(current.item());
				for (Edge a: myAdjLists[current.item()]) {
					if (!settled.contains(a.to())) {
						if (fringe.contains(a.to())) {
							if((Double) a.myEdgeInfo + distanceTrack[current.item()]< distanceTrack[current.item()]) {
								distanceTrack[a.to()] = (Double) a.myEdgeInfo + distanceTrack[current.item()];
								fringe.changePriority(a.to(), distanceTrack[a.to()]);
								pre[a.to()] = current.item();
							}
						} else {
							fringe.insert(a.to(), (Double) a.myEdgeInfo + distanceTrack[current.item()]);
							distanceTrack[a.to()] =(Double) a.myEdgeInfo + distanceTrack[current.item()];
							pre[a.to()] = current.item();
						}
					}
				}
			
		}
		}catch (IndexOutOfBoundsException e){
		}
		
		
		CompleteResult = new ArrayList<Integer>();
		int replace = end;
			while(replace!=start) {
				CompleteResult.add(0,replace);
				replace = pre[replace];
				System.out.println("x");
			}
			CompleteResult.add(0,start);
			return CompleteResult;
			

		}

    		
//    		for (int i=0; i<myAdjLists[start].size(); i++) {
//        		for (int j=0; j<myAdjLists.length; j++) {
//    			fringe.insert(myAdjLists[start].get(i).to(), (Integer) myAdjLists[start].get(i).info());
//
//    				if (j!=myAdjLists[start].get(i).to()) {
//    					fringe.insert(j, Double.POSITIVE_INFINITY);
//    				}
//        		}
//    		}
//			PrevTrack.put(start, null);
//			settled.add(start);
//			
//			
//			while (fringe.peek()!=null) {
//				currDis = (int) fringe.peek().priority();
//				curr = fringe.removeMin().item();
//				List ListOfNeighbors = neighbors(curr);
//				
//				if (!settled.contains(curr)) {
//
//					for (int n=0; n<myAdjLists[curr].size(); n++) {
//					if (!settled.contains(myAdjLists[curr].get(n).to())); {
//						if (currDis + (Integer) myAdjLists[curr].get(n).info() < fringe.getByKey(myAdjLists[curr].get(n).to()).priority()) {
//							fringe.changePriority(myAdjLists[curr].get(n).to(), currDis + (Integer) myAdjLists[curr].get(n).info());
//							PrevTrack.put(fringe.peek().item(), curr);
//						}else{
//							PrevTrack.put(fringe.peek().item(), curr);
//						}
//					}
//					}
//										
//				}
//				
//				if (fringe.peek().item() == end) {
//					break;
//				}
//			}
//				process = PrevTrack.get(end);
//				CompleteResult.add(end);
//			while (PrevTrack.get(process)!=null) {
//				
//				CompleteResult.add(PrevTrack.get(process));
//				process = PrevTrack.get(process);
//			}
//			
//			ArrayList<Integer> Result = new ArrayList<Integer>();
//			while (!CompleteResult.isEmpty()) {
//				Result.add(CompleteResult.remove(CompleteResult.size()-1));
//			}
//			
//			return Result;		    	
//			
//    }

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
    	Edge E = new Edge(v1, v2 , edgeInfo);
    	myAdjLists[v1].add(E);
    	
    }

    // Add to the graph an undirected edge from vertex v1 to vertex v2,
    // with the given edge information.
    public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
        //your code here
    	Edge E = new Edge(v1, v2 , edgeInfo);
    	Edge DE = new Edge(v2, v1 , edgeInfo);
    	 myAdjLists[v1].add(E);
    	 myAdjLists[v2].add(DE);

    	
    }

    // Return true if there is an edge from vertex "from" to vertex "to";
    // return false otherwise.
    public boolean isAdjacent(int from, int to) {
        //your code here
    	for (int i=0; i<myAdjLists[from].size();i++) {
    		if (myAdjLists[from].get(i).to() == to) {
    			return true;
    		}
    	}return false;
    }

    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public List neighbors(int vertex) {
        // your code here
    	List<Integer> L = new ArrayList<Integer>();
    	for (int i=0; i<myAdjLists[vertex].size();i++) {
    		L.add(myAdjLists[vertex].get(i).to());
    		
    	}
    	
        return L;
    }

    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
        //your code here
        for (int i=0; i<myAdjLists.length;i++) {
        	for (int j=0; j<myAdjLists[i].size();j++) {
        		if	(myAdjLists[i].get(j).to() == vertex) {
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
        	Integer X;

           	if(hasNext()) {	
           		X = fringe.pop();        	
           		for (int i=0; i<myAdjLists[X].size();i++) {
           			if (!visited.contains(myAdjLists[X].get(i).to())) {
           				fringe.add(myAdjLists[X].get(i).to());
           				visited.add(myAdjLists[X].get(i).to());
           			}
           		}
           		return X;
           	}
          	throw new IllegalStateException();

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
    	if (startVertex == stopVertex){
    		return true;
    	}else{
    		Iterator iter = new DFSIterator(startVertex);
    		while (iter.hasNext()) {
    			Integer next = (Integer) iter.next();
    			if (next == stopVertex) {
    				return true;
    			}
    			
    		}return false;
    		
    	}
    }


    // Returns the path from startVertex to stopVertex.
    // If no path exists, returns an empty arrayList.
    // If startVertex == stopVertex, returns a one element arrayList.
    public ArrayList<Integer> path(int startVertex, int stopVertex) {
        
        // you supply the body of this method
    	ArrayList<Integer> path = new ArrayList<Integer>();
    	if (pathExists(startVertex, stopVertex)) {
    		
    		if (startVertex == stopVertex) {
    			path.add(startVertex);
    			return path;
    		}
    		
    		Iterator iter = new DFSIterator(startVertex);
    		while (iter.hasNext()) {
    			Integer next = (Integer) iter.next();
    			path.add(next);
    			if (next == stopVertex) {
    				break;
    			}
    		}return path;	
    		
    	}else{
    		return path;
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
        private ArrayList<Integer> currentInDegree;
        private HashSet<Integer> visited;


        // more instance variables go here
        

        public TopologicalIterator() {
            fringe = new Stack<Integer>();
            // more statements go here
            currentInDegree = new ArrayList<Integer>();
            visited = new HashSet<Integer>();
            
            for (int z=0; z<myVertexCount;z++) {
            	currentInDegree.add(inDegree(z));
            }
          
            for (int i=0; i<myVertexCount;i++) {
            	if (inDegree(i) == 0) {
            		fringe.push(i);
            	}
            }
            
            visited.add(0);
            
            
        }

        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        public Integer next() {
        	
            // you supply the real body of this method
           	if(hasNext()) {	
           		Integer X = fringe.pop(); 
           		
           		for (int i=0; i<myVertexCount;i++) {
           			if (isAdjacent(X, i)) {
           				currentInDegree.set(i,currentInDegree.get(i)-1);
           			}
           		}
           		
           		for (int j=0; j<myVertexCount;j++) {
           			if (!visited.contains(j)) {
           				if (currentInDegree.get(j)==0) {
           					fringe.push(j);
           	           		visited.add(j);
           					
           					
           				}
           			}
           		}

           		return X;
           	}
           	return null;
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
        g2.addEdge(0, 1, 10.0);
        g2.addEdge(0, 2, 5.0);
        g2.addEdge(0, 4, 40.0);
        g2.addEdge(1, 2, 5.0);
        g2.addEdge(2, 3, 10.0);
        g2.addEdge(4, 3, 5.0);
        System.out.println(g2.shortestPath(0, 2));
        System.out.println();
        System.out.println("Topological sort");
        result = g2.topologicalSort();
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
    }

}
