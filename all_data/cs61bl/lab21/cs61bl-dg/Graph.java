import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.lang.ClassCastException;
import java.util.Stack;
import java.util.PriorityQueue;

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
        myAdjLists[v1].add(new Edge(v1, v2, edgeInfo));
    }

    // Add to the graph an undirected edge from vertex v1 to vertex v2,
    // with the given edge information.
    public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
        myAdjLists[v1].add(new Edge(v1, v2, edgeInfo));
        myAdjLists[v2].add(new Edge(v2, v1, edgeInfo));
    }

    // Return true if there is an edge from vertex "from" to vertex "to";
    // return false otherwise.
    public boolean isAdjacent(int from, int to) {
        for (Edge e: myAdjLists[from]) {
            if (e.to() == to) {
                return true;
            }
        }
        return false;
    }

    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public List<Integer> neighbors(int vertex) {
        ArrayList<Integer> neighborList = new ArrayList<Integer>();
        for (Edge e: myAdjLists[vertex]) {
            neighborList.add(e.to());
        }
        return neighborList;
    }

    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
        for (LinkedList<Edge> edges: myAdjLists) {
            for (Edge e: edges) {
                if (e.to() == vertex) {
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
            fringe = new Stack ();
            fringe.push(start);
            visited = new HashSet<Integer>();
        }

        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        public Integer next() {
            int nextVertex = fringe.pop();
            for (Edge e: myAdjLists[nextVertex]) {
                if (!visited.contains(e.myTo)) {
                    fringe.push(e.myTo);
                }
            }
            visited.add(nextVertex);
            return nextVertex;
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
        ArrayList<Integer> startChildren = visitAll(startVertex);
        return startChildren.contains(stopVertex);
    }


    // Returns the path from startVertex to stopVertex.
    // If no path exists, returns an empty arrayList.
    // If startVertex == stopVertex, returns a one element arrayList.
    public ArrayList<Integer> path(int startVertex, int stopVertex) {
        ArrayList<Integer> path = new ArrayList<Integer>();
        ArrayList<Integer> visited = new ArrayList<Integer>();
        Iterator<Integer> iter = new DFSIterator(startVertex);

        int next = 0;
        boolean found = false;
        while (iter.hasNext()) {
            next = iter.next();
            if (next == stopVertex) {
                found = true;
                break;
            }
            visited.add(next);
        }

        if (!found) {
            return path;
        }

        path.add(stopVertex);
        while (visited.size() != 0) {
            for (int v: visited) {
                if (isAdjacent(v, next)) {
                    path.add(0, v);
                    next = v;
                    visited.subList(visited.indexOf(v), visited.size()).clear();
                    break;
                }
            }
        }
        return path;

    }

    public ArrayList<Integer> shortestPath (int startVertex, int endVertex) {
        DijkstraIterator paths = new DijkstraIterator(startVertex);
        Stack<Integer> reverse = new Stack<Integer>();
        ArrayList<Integer> path = new ArrayList<Integer>();
        Node last = null;
        while (paths.hasNext()) {
            Node n = paths.next();
            if (n.value() == endVertex) {
                last = n;
            }
        }
        for (Node next = last; next != null; next = next.parent()) {
            reverse.push(next.value());
        }
        while (!reverse.empty()) {
            path.add(reverse.pop());
        }
        return path;
    }

    private class DijkstraIterator implements Iterator<Node> {

        private PriorityQueue<Node> fringe;

        public DijkstraIterator(int startVertex) {
            fringe = new PriorityQueue<Node>();
            fringe.add(new Node(startVertex, 0));
            for (int i = 0; i < myVertexCount; i++) {
                fringe.add(new Node(i, Double.POSITIVE_INFINITY));
            }
        }

        public boolean hasNext() {
            return fringe.size() > 0;
        }

        public Node next() {
            Node next = fringe.poll();
            for (int i: neighbors(next.value())) {
                Node toChange = null;
                for (Node n: fringe) {
                    if (n.value() == i) {
                        if (next.distance()+distance(next, n) < n.distance()) {
                            toChange = n;
                        }
                    }
                }
                if (toChange != null) {
                    fringe.remove(toChange);
                    toChange.reduceDistance(next.distance() + distance(next, toChange));
                    toChange.setParent(next);
                    fringe.add(toChange);
                }
            }
            return next;
        }

    }

    private class Node implements Comparable<Node> {

        private int myValue;
        private double myDistance;
        private Node myParent = null;

        public Node(int value, double distance) {
            myValue = value;
            myDistance = distance;
        }

        public void setParent(Node p) {
            myParent = p;
        }

        public void reduceDistance(double d) {
            myDistance = d;
        }

        public int value() {
            return myValue;
        }

        public double distance() {
            return myDistance;
        }

        public Node parent() {
            return myParent;
        }

        public int compareTo(Node n) {
            if (this.distance() > n.distance()) {
                return 1;
            }
            if (this.distance() < n.distance()) {
                return -1;
            }
            return 0;
        }

        public String toString() {
            return myValue + ", " + myDistance;
        }

    }

    public double distance(Node n1, Node n2) {
        for (Edge e: myAdjLists[n1.value()]) {
            if (e.to() == n2.value()) {
                try {
                    return (double)e.info();
                } catch(ClassCastException f) {
                    return (int)e.info();
                }
            }
        }
        return Double.POSITIVE_INFINITY;
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

//         The algorithm for taking a graph and finding a topological sort uses an array named currentInDegree with one element per vertex. currentInDegree[v] is initialized with the in-degree of each vertex v.

// The algorithm also uses a fringe. The fringe is initialized with all vertices whose in-degree is 0. When a vertex is popped off the fringe and added to a results list, the currentInDegree value of its neighbors are reduced by 1. Then the fringe is updated again with vertices shows in-degree is now 0.

        private Stack<Integer> fringe;
        private int[] currentInDegree;

        public TopologicalIterator() {
            fringe = new Stack<Integer>();
            currentInDegree = new int[myVertexCount];
            for (int v = 0; v < myVertexCount; v++) {
                currentInDegree[v] = inDegree(v);
            }
            for (int v = 0; v < myVertexCount; v++) {
                if (currentInDegree[v] == 0) {
                    fringe.push(v);
                }
            }
        }

        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        public Integer next() {
            int next = fringe.pop();
            for (Object i: neighbors(next)) {
                int vertex = (int)i; // You didn't specify that the list returned by neighbors is one of ints...
                currentInDegree[vertex]--;
                if (currentInDegree[vertex] == 0) {
                    fringe.push(vertex);
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

        Graph undir = new Graph(7);
        undir.addUndirectedEdge(0,3);
        undir.addUndirectedEdge(1,4);
        undir.addUndirectedEdge(2,3);
        undir.addUndirectedEdge(2,6);
        undir.addUndirectedEdge(4,5);

        System.out.println();
        System.out.println();
        System.out.println("Graph 2: Path from 0 to 6");
        result = undir.path(0, 6);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }

        System.out.println();
        System.out.println();
        System.out.println("Graph 2: Path from 6 to 0");
        result = undir.path(6, 0);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }

        System.out.println();
        System.out.println();
        System.out.println("Graph 2: Path from 5 to 1");
        result = undir.path(5, 1);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }

        System.out.println();
        System.out.println();
        System.out.println("Graph 2: Should be Empty");
        result = undir.path(0, 5);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
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

        Graph g3 = new Graph(8);
        g3.addEdge(0, 2, 3);
        g3.addEdge(0, 3, 7);
        g3.addEdge(1, 3, 4);
        g3.addEdge(1, 4, 9);
        g3.addEdge(2, 5, 2);
        g3.addEdge(3, 5, 8);
        g3.addEdge(4, 6, 2);
        g3.addEdge(5, 6, 1);
        g3.addEdge(6, 7, 5);
        System.out.println();
        System.out.println();
        System.out.println("Topological sort 2");
        result = g3.topologicalSort();
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }

        Graph dijk = new Graph(6);
        dijk.addEdge(0, 1, 7);
        dijk.addEdge(0, 2, 9);
        dijk.addEdge(0, 5, 14);
        dijk.addEdge(1, 2, 10);
        dijk.addEdge(1, 3, 15);
        dijk.addEdge(2, 3, 11);
        dijk.addEdge(2, 5, 2);
        dijk.addEdge(3, 4, 6);
        dijk.addEdge(4, 5, 9);
        System.out.println();
        System.out.println();
        System.out.println("Dijkstra");
        System.out.println(dijk.shortestPath(0, 4));

        Graph dijk2 = new Graph(10);
        dijk2.addEdge(0, 1, 5);
        dijk2.addEdge(0, 2, 10);
        dijk2.addEdge(1, 2, 1);
        dijk2.addEdge(1, 3, 2);
        dijk2.addEdge(2, 4, 4);
        dijk2.addEdge(3, 4, 7);
        dijk2.addEdge(4, 2, 4);
        System.out.println();
        System.out.println();
        System.out.println("Dijkstra 2");
        System.out.println(dijk2.shortestPath(0, 4));

        Graph dijk3 = new Graph(10);
        dijk3.addEdge(0, 1, 10);
        dijk3.addEdge(0, 7, 2);
        dijk3.addEdge(1, 7, 1);
        dijk3.addEdge(1, 2, 5);
        dijk3.addEdge(2, 1, 5);
        dijk3.addEdge(2, 4, 2);
        dijk3.addEdge(2, 3, 1);
        dijk3.addEdge(3, 5, 11);
        dijk3.addEdge(4, 5, 8);
        dijk3.addEdge(4, 7, 18);
        dijk3.addEdge(7, 4, 18);
        dijk3.addEdge(5, 3, 11);
        dijk3.addEdge(4, 2, 4);
        System.out.println();
        System.out.println();
        System.out.println("Dijkstra 3");
        System.out.println(dijk3.shortestPath(0, 3));

        Graph dijk4 = new Graph(10);
        dijk4.addEdge(0, 1, 3);
        dijk4.addEdge(0, 2, 5);
        dijk4.addEdge(1, 2, 1);
        dijk4.addEdge(2, 6, 50);
        dijk4.addEdge(3, 4, 5);
        dijk4.addEdge(4, 5, 5);
        dijk4.addEdge(5, 4, 5);
        dijk4.addEdge(5, 6, 3);
        dijk4.addEdge(6, 7, 10);
        dijk4.addEdge(7, 6, 10);
        dijk4.addEdge(1, 3, 7);
        System.out.println();
        System.out.println();
        System.out.println("Dijkstra 4");
        System.out.println(dijk4.shortestPath(0, 7));

    }

}
