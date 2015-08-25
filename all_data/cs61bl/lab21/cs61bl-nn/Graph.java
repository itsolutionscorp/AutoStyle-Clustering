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
    private int [] currentInDegree;

    // Initialize a graph with the given number of vertices and no edges.
    public Graph(int numVertices) {
        myAdjLists = new LinkedList[numVertices];
        myStartVertex = 0;
        for (int k = 0; k < numVertices; k++) {
            myAdjLists[k] = new LinkedList<Edge>();
        }
        myVertexCount = numVertices;
        currentInDegree=new int[numVertices];
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
        currentInDegree[v2]+=1;
    }

    // Add to the graph an undirected edge from vertex v1 to vertex v2.
    public void addUndirectedEdge(int v1, int v2) {
        addUndirectedEdge(v1, v2, null);
        addUndirectedEdge(v2, v1, null);
    
    }

    // Add to the graph a directed edge from vertex v1 to vertex v2,
    // with the given edge information.
    public void addEdge(int v1, int v2, Object edgeInfo) {
    	myAdjLists[v1].add(new Edge(v1,v2,edgeInfo));
        //your code here
    }

    // Add to the graph an undirected edge from vertex v1 to vertex v2,
    // with the given edge information.
    public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
        //your code here
    	myAdjLists[v1].add(new Edge(v1,v2,edgeInfo));
    }

    // Return true if there is an edge from vertex "from" to vertex "to";
    // return false otherwise.
    public boolean isAdjacent(int from, int to) {
        //your code here 	
        LinkedList<Edge> egs=myAdjLists[from];
        for(Edge e:egs){
        	if(e.myTo==to){
        		return true;
        	}
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
        for(LinkedList<Edge> v: myAdjLists){
        	for(Edge e:v){
        		if(e.myTo==vertex){
        			count++;
        		}
        	}
        }
        
        return count;
    }

    public Iterator<Integer> iterator(){
        return new TopologicalIterator();
    }
    
    public ArrayList<Integer> shortestPath (int startVertex, int endVertex){
    	if(endVertex>=myAdjLists.length||endVertex<0||startVertex>=myAdjLists.length||startVertex<0){
    		throw new IllegalArgumentException("endVertex and startVertex can only be in this range:[0,"+(myAdjLists.length-1)+"]");
    	}
    	if(!pathExists(startVertex,endVertex)){
    		throw new IllegalArgumentException("Path does not exist!");
    	}
    	
    	int startV=startVertex;
        ArrayHeap<Integer> fringe=new ArrayHeap<Integer>();
        ArrayList<Integer> result=new ArrayList<Integer>();       
        HashMap<Integer,Integer> resultPath=new HashMap<Integer,Integer>();
        double[] oriDis=new double[myVertexCount];
        int[] nearestPoints=new int[myVertexCount];

        resultPath.put(startV, startV);
        //initialization,add startV to the fringe, give neighbor a distance, while others are infinity
        for(int i=0;i<myAdjLists.length;i++){
        	if(!isAdjacent(startVertex,i)){
        		fringe.insert(i, Double.POSITIVE_INFINITY);
        		oriDis[i]=Double.POSITIVE_INFINITY;
        	}
        }
        for(Edge neighbour:myAdjLists[startVertex]){
        	fringe.insert(neighbour.myTo, (double)neighbour.myEdgeInfo);
        	oriDis[neighbour.myTo]=(double)neighbour.myEdgeInfo;
        	nearestPoints[neighbour.myTo]=startVertex;
        }
        
        //remove one ele from PQ, add it to result, then set it as the new startVertex
        //save the dis 
        
        while(startV!=endVertex){
	        startV=fringe.peek().item();
	        double dis=fringe.peek().priority();
	        fringe.removeMin();
	        resultPath.put(startV, nearestPoints[startV]);
	        //update the vertex in the fringe that is neighbor to the new startVertex
	        //but also is not in the result list
	        updatePQ(startV,dis,fringe,oriDis,resultPath,nearestPoints);  
	    }
        int cur=endVertex;
        result.add(endVertex);
        while(cur!=startVertex){
        	result.add(resultPath.get(cur));
        	cur=resultPath.get(cur);
        }
        ArrayList<Integer> reverseResult=new ArrayList<Integer>();
        for(int j=result.size()-1;j>=0;j--){
        	reverseResult.add(result.get(j));
        }
        
        return reverseResult;
    }
    
    /**
     * update the node that is neighbor to the new startVertex
     * @param startV
     * @param dis
     * @param myfringe
     */
    public void updatePQ(int startV, double dis, ArrayHeap<Integer> myfringe,double [] oriDis,HashMap<Integer,Integer> resultPath,int [] nearestPoints){
    	 // go through each neighbor,  change their dis if it is a better choice
    	 	//create an array of original priority   		
    	 for(Edge e: myAdjLists[startV]){
    		 if(!resultPath.keySet().contains(e.myTo)){
    			 double newDis;
        		 if(oriDis[e.myTo]==Double.POSITIVE_INFINITY){
        			 newDis=dis+(double)e.myEdgeInfo;
        		 }else{
        			 newDis=oriDis[e.myTo]+dis;
        		 }
        		 
        		 if(newDis<oriDis[e.myTo]){
        			 myfringe.changePriority(e.myTo, newDis);
        			 nearestPoints[e.myTo]=startV;
        		 }
    		 }
    		 
    	 }

    	
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
        	visited=new HashSet<Integer>();
        	fringe=new Stack<Integer>();
        	fringe.push(start);
        }

        public boolean hasNext() {
            if(!fringe.isEmpty()){
            	int nextVal=fringe.pop();
            	while(visited.contains(nextVal)){
            		if(fringe.empty()){
            			return false;
            		}else{
            			nextVal=fringe.pop();
            		}
            	}
            	fringe.push(nextVal);
            	return true;
            }else{
            	return false;
            }
        }

        public Integer next() {
            int nextVal=fringe.pop();
            for(Edge neighbor : myAdjLists[nextVal]){
        		fringe.push(neighbor.myTo);
        	}
        	visited.add(nextVal);
        	return nextVal;
            
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
          	
    	if(visitAll(startVertex).contains(stopVertex)){
    			return true;   		
    	}else{
    		return false;  		
    	}
    }


    // Returns the path from startVertex to stopVertex.
    // If no path exists, returns an empty arrayList.
    // If startVertex == stopVertex, returns a one element arrayList.
    public ArrayList<Integer> path(int startVertex, int stopVertex) {
    	ArrayList<Integer> result=new ArrayList<Integer>();
        if(startVertex==stopVertex){  	
        	result.add(startVertex);
        	return result;
        }else{
        	if(pathExists(startVertex,stopVertex)){
        		ArrayList<Integer> traversal=visitAll(startVertex);
        		Iterator<Integer> iter=traversal.iterator();
        		Stack<Integer> fringe=new Stack<Integer>();
        		Stack<Integer> endToStart=new Stack<Integer>();
        		while(iter.hasNext()){
        			int next=iter.next();
        			fringe.push(next);
        			if(next==stopVertex){
        				break;
        			}  			       			
        		}
        		//get the tracing back vertex in the stack
        		int finish=fringe.pop();
        		int adjV=fringe.pop();
        		endToStart.push(finish);
        		while(!fringe.isEmpty()){
	        		if(isAdjacent(adjV,finish)){
	        			endToStart.push(adjV);
	        			finish=adjV;
	        		}
	        		adjV=fringe.pop();	        		
        		}
        		endToStart.push(adjV);
        		while(!endToStart.isEmpty()){
        			result.add(endToStart.pop());
        		}
        		return result;
        		       		
        	}else{
        		return result;
        	}
        	
        }
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
        private Stack<Integer> visited;

        // more instance variables go here

        public TopologicalIterator() {
            fringe = new Stack<Integer>();
            visited=new Stack<Integer>();
            for(int i=0;i<currentInDegree.length;i++){
            	if(currentInDegree[i]==0){            		
            		fringe.add(i);
            		visited.add(i);
            	}
            }
            
            
            // more statements go here
        }

        public boolean hasNext() {
            if(!fringe.isEmpty()){
            	return true;
            }else{
            	return false;
            }
        }

        public Integer next() {
          
            // you supply the real body of this method
        	int next=fringe.pop();
        	for(Edge neighbor:myAdjLists[next]){
        		currentInDegree[neighbor.myTo]-=1;
        	}
        	
        	
        	for(int i=0;i<currentInDegree.length;i++){
            	if(currentInDegree[i]==0&&!visited.contains(i)){
            		fringe.add(i);
            		visited.add(i);
            	}
            }     	
        	return next;
                	
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
    /**
     * A Generic heap class. Unlike Java's priority queue, this heap doesn't just
     * store Comparable objects. Instead, it can store any type of object
     * (represented by type T), along with a priority value. Why do it this way? It
     * will be useful later on in the class...
     */
    public class ArrayHeap<T> {
    	private ArrayList<Node> contents = new ArrayList<Node>();

    	/**
    	 * Inserts an item with the given priority value. This is enqueue, or offer.
    	 */
    	private  int lastIndex=0;//the next position after the last element
    	public void insert(T item, double priority) {		
    		int insertIndex=lastIndex+1;
    		lastIndex++;
    		setNode(insertIndex,new Node(item,priority));
    		bubbleUp(insertIndex);
    		
    		

    	}

    	/**
    	 * Returns the Node with the smallest priority value, but does not remove it
    	 * from the heap.
    	 */
    	public Node peek() {
    		// TODO Complete this method!
    		return contents.get(1);
    	}

    	/**
    	 * Returns the Node with the smallest priority value, and removes it from
    	 * the heap. This is dequeue, or poll.
    	 */
    	public Node removeMin() {
    		// TODO Complete this method!
    		Node min;
    		 if(lastIndex!=2){			 
    			 swap(1,lastIndex-1);
    			 //remember to update lastIndex here!!!!!
    			 min=contents.remove(lastIndex-1);
    			 bubbleDown(1);
    			 lastIndex--;
    		 }else{
    			 
    			 min=contents.remove(1);
    		 }
    		 return min;
    		 
    		 
    	}

    	/**
    	 * Change the node in this heap with the given item to have the given
    	 * priority. For this method only, you can assume the heap will not have two
    	 * nodes with the same item. Check for item equality with .equals(), not ==
    	 */
    	public void changePriority(T item, double priority) {
    		// TODO Complete this method!
    		int index=0;
    		for(int i=1;i<contents.size();i++){
    			if(contents.get(i)!=null&&contents.get(i).myItem.equals(item)){
    				index=i;
    				break;
    			}
    		}
    		Node changeN = contents.get(index);	
    		changeN.myPriority = priority;
    		bubbleUp(index);
    	}

    	/**
    	 * Prints out the heap sideways.
    	 */
    	@Override
    	public String toString() {
    		return toStringHelper(1, "");
    	}

    	/* Recursive helper method for toString. */
    	private String toStringHelper(int index, String soFar) {
    		if (getNode(index) == null) {
    			return "";
    		} else {
    			String toReturn = "";
    			int rightChild = getRightOf(index);
    			toReturn += toStringHelper(rightChild, "        " + soFar);
    			if (getNode(rightChild) != null) {
    				toReturn += soFar + "    /";
    			}
    			toReturn += "\n" + soFar + getNode(index) + "\n";
    			int leftChild = getLeftOf(index);
    			if (getNode(leftChild) != null) {
    				toReturn += soFar + "    \\";
    			}
    			toReturn += toStringHelper(leftChild, "        " + soFar);
    			return toReturn;
    		}
    	}

    	private Node getNode(int index) {
    		if (index >= contents.size()||index<0) {
    			return null;
    		} else {
    			return contents.get(index);
    		}
    	}

    	private void setNode(int index, Node n) {
    		// In the case that the ArrayList is not big enough
    		// add null elements until it is the right size
    		while (index + 1 >= contents.size()) {
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
    		return i / 2;
    	}

    	/**
    	 * Adds the given node as a left child of the node at the given index.
    	 */
    	private void setLeft(int index, Node n) {
    		// TODO Complete this method!
    		contents.set(getLeftOf(index), n);
    	}

    	/**
    	 * Adds the given node as the right child of the node at the given index.
    	 */
    	private void setRight(int index, Node n) {
    		// TODO Complete this method!
    		contents.set(getRightOf(index), n);
    	}

    	/**
    	 * Bubbles up the node currently at the given index.
    	 */
    	private void bubbleUp(int index) {
    		// TODO Complete this method!
    		Node parent = getNode(getParentOf(index));
    		Node current = getNode(index);
    		//System.out.println("test");
    		if (parent != null) {
    			if (parent.priority() > current.priority()) {
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
    		Node leftChild = getNode(getLeftOf(index));
    		Node rightChild = getNode(getRightOf(index));
    		Node current = getNode(index);
    		Node min;
    		int minIndex;
    		if (leftChild == null && rightChild == null) {
    			return;
    		} else {

    			minIndex=min(getLeftOf(index),getRightOf(index));
    			min=getNode(minIndex);
    			if (min.priority() < current.priority()) {
    				swap(index, minIndex );
    				bubbleDown(minIndex);
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

        Graph g1 = new Graph(5);
        //directed graph 1
//        System.out.println("=====directed acyclic graph 1 ====");
//        g1.addEdge(0, 1);
//        g1.addEdge(0, 2);
//        g1.addEdge(0, 4);
//        g1.addEdge(1, 2);
//        g1.addEdge(2, 0);
//        g1.addEdge(2, 3);
//        g1.addEdge(4, 3);
        // directed graph 2
//        System.out.println("=======directed graph 2=====");
//        g1.addEdge(0,1); 
//        g1.addEdge(1,2); 
//        g1.addEdge(2,0); 
//        g1.addEdge(2,3); 
//        g1.addEdge(4,2);
        System.out.println("=====directed acyclic graph 1 ====");
        g1.addEdge(0, 1);
        g1.addEdge(0, 2);
        g1.addEdge(0, 4);
        g1.addEdge(1, 2);
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
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        
        System.out.println();
        System.out.println();
        System.out.println("Path from 2 to 4");
        result = g1.path(2, 4);
        iter = result.iterator();
        if (result.size() != 0) {
            System.out.println("*** should be no path!");
        }
        //Topological test
        
//        Graph g2 = new Graph(5);
//        g2.addEdge(0, 1);
//        g2.addEdge(0, 2);
//        g2.addEdge(0, 4);
//        g2.addEdge(1, 2);
//        g2.addEdge(2, 3);
//        g2.addEdge(4, 3);
//        System.out.println();
//        System.out.println();
//        System.out.println("Topological sort 1");
//       
//        result = g2.topologicalSort();
//        iter = result.iterator();
//        
//        while (iter.hasNext()) {       	
//            System.out.println(iter.next() + " ");
//        }
        // nornal test cases
        Graph g3 = new Graph(8);
        g3.addEdge(0, 2,4.0);
        g3.addEdge(0, 3,5.0);
        g3.addEdge(1, 3,6.0);
        g3.addEdge(1, 4,2.0);
        g3.addEdge(2, 5,7.0);
        g3.addEdge(3, 5,1.0);
        g3.addEdge(3, 2,4.0);
        g3.addEdge(5, 2,1.0);
        g3.addEdge(3, 6,5.0);
        g3.addEdge(4, 6,3.0);
        g3.addEdge(5, 7,2.0);
        g3.addEdge(6, 7,8.0);
        System.out.println();
        System.out.println();
        System.out.println("shortest path ");
       
        result=g3.shortestPath(1,6 );
        iter = result.iterator();
        
        while (iter.hasNext()) {       	
            System.out.println(iter.next() + " ");
        }
        
        
        //edge test cases, where there are no edge in the graph
        Graph g4 = new Graph(8);
        
        System.out.println();
        System.out.println();
        System.out.println("shortest path ");
       
        try{
        result=g4.shortestPath(1, 6);
        // if there is no path, should throw an exception
        iter = result.iterator(); 
        
        while (iter.hasNext()) {   
        	System.out.println("shortest path ");
            System.out.println(iter.next() + " ");
        }
        }catch(IllegalArgumentException e){
        	System.out.println(e.getMessage());
        }
        
        //edge test cases, startVertex = endVertex
        Graph g6 = new Graph(8);
        
	      g6.addEdge(0, 2,4.0);
	      g6.addEdge(0, 3,5.0);
	      g6.addEdge(1, 3,6.0);
	      g6.addEdge(1, 4,2.0);
	      g6.addEdge(2, 5,7.0);
        
        System.out.println();
        System.out.println();
        System.out.println("shortest path ");
       
        try{
        result=g6.shortestPath(1, 1);
        iter = result.iterator(); 
        
        while (iter.hasNext()) {   
            System.out.println(iter.next() + " ");
        }
        }catch(IllegalArgumentException e){
        	System.out.println(e.getMessage());
        }
        
  
        
    }

}
