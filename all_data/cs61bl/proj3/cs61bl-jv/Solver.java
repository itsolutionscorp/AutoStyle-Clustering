import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solver {

	private HashMap<Integer, Board> boards; // future when evolving the algo
												// should try many boards and
												// compositions so i need to
												// store them
	private int boardCounter;
	private Board myBoard;
	private Tray myTray;
	private Tray myTray_non_goals;
	private ArrayList<String> moves;
	private int myNodes;
	private HashMap <Integer, Double> board_distances;
	private ArrayList<Board> visited;
	private Comparator<Board> min_comparator;
	private PriorityQueue <Board> fringe; 
	private ArrayList<String> goal_types;
	
	public Solver(Integer[] board_dim,
			HashMap<Integer, ArrayList<Integer>> blocks,
			HashMap<Integer, ArrayList<Integer>> goal_blocks)
			throws CloneNotSupportedException {

		goal_types = new ArrayList<String>();
		boards = new HashMap<Integer, Board>();
		ArrayList<String> moves = new ArrayList<String>();
		board_distances = new HashMap<Integer, Double>();
		boardCounter = 0;

		moves.add("UP");
		moves.add("DOWN");
		moves.add("RIGHT");
		moves.add("LEFT");
		
		
		myBoard = new Board(board_dim[0], board_dim[1], blocks);
		myTray = new Tray(board_dim[0]*800);
		myTray_non_goals = new Tray(board_dim[0]*800);
		ArrayList<Block> goals = GoalBlocks(goal_blocks);
		
		for(int k=0; k <goals.size(); k++){
			if(!goal_types.contains(goals.get(k).getType())){
				goal_types.add(goals.get(k).getType());
			}
		}
		visited = new ArrayList<Board>();
		min_comparator = new MinComparator();
		fringe = new PriorityQueue<Board>(1000, min_comparator);
		Solve(myBoard, goals,goal_blocks, moves);
		

		/**
		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.add(1);
		temp.add(0);
		temp.add(1);
		temp.add(0);
		Block block = new Block(1, temp);
		boards.put(boardCounter, myBoard);
		myBoard.getBoard()[0][1].setGoalDistance(goals.get(0));
		double d = myBoard.getBoard()[0][1].getGoalDistance();
		myBoard.setBoardDistance(d);
		System.out.println(myBoard.getDistance());
		System.out.println(myBoard.CanMove(block.getNumber(), "DOWN"));
		
		Board b = myBoard.Move(block.getNumber(),"DOWN", goals.get(0));
		System.out.println(b.getDistance());
		System.out.println(Arrays.deepToString(b.getBoard()));
//		Board clone1 = myBoard.Move(block.getNumber(), "UP", goals.get(0));
		System.out.println(Arrays.deepToString(myBoard.getBoard()));
		System.out.println(boardCounter);

//		boards.put(boardCounter, clone1);
		System.out.println(boards);
		**/
		
	}

	

	private void Solve(Board board, ArrayList<Block> goals, HashMap<Integer, ArrayList<Integer>> goal_blocks,
			ArrayList<String> move) throws CloneNotSupportedException {
		int limit = 5;
		createTray(board, goals, goal_blocks, move);
			
	}// end Solve

	

	private void createTray(Board board, ArrayList<Block> goals, HashMap<Integer, ArrayList<Integer>> goal_blocks,
			ArrayList<String> moves) throws CloneNotSupportedException {
		
		int current_vertex = boardCounter;
		
		for (int i = 0; i < board.getBoard().length; i++) {
			for (int j = 0; j < board.getBoard()[i].length; j++) {
				if (board.getBoard()[i][j] != null) {
					for (int k = 0; k < moves.size(); k++) {	
						for (int d = 0; d < goals.size(); d++) {	
								
								board.getBoard()[i][j].setGoalDistance(goals.get(d));
								double board_dist = board.getBoard()[i][j].getGoalDistance();
								board.setBoardDistance(board_dist);
								System.out.println(board.getDistance());

								if(!board_distances.containsKey(current_vertex)){
									board_distances.put(current_vertex, board_dist);
								}
								if(!visited.contains(board)){
									fringe.add(board);
									visited.add(board);
								}
								
								if (board.CanMove(board.getBoard()[i][j].getNumber(), moves.get(k))) {
										
										if (!boards.containsValue(board)){
											boards.put(boardCounter, board);
										}
										Board board_move = board.Move(board.getBoard()[i][j].getNumber(),moves.get(k), goals.get(d));
										boards.put(boardCounter, board_move);
										System.out.println("MOVE COORS: "+ moves.get(k)+ ": "+ board_move.toString());
										
										if(board_dist == 0 ){
											ArrayList<Integer> goal_resolved = board.getBoard()[i][j].getCurrentCoords();
											if(goal_blocks.isEmpty()){
												System.out.println("GOAL REACHED: "+ board.toString());
												return;
											}else{
											for(Map.Entry<Integer, ArrayList<Integer>> item : goal_blocks.entrySet()){
													if(item.getValue() == goal_resolved){
														int key = item.getKey();
														goal_blocks.remove(key);
														
													}
												}
											}
										}else if(board_move.getDistance() == 0){
											
											ArrayList<Integer> goal_resolved2 = board_move.getBoard()[i][j].getCurrentCoords();
											if(goal_blocks.isEmpty()){
												System.out.println("GOAL REACHED: "+ board_move.toString());
												return;
											}else{
											for(Map.Entry<Integer, ArrayList<Integer>> item : goal_blocks.entrySet()){
													if(item.getValue() == goal_resolved2){
														int key = item.getKey();
														goal_blocks.remove(key);
														
													}
											}
											}
											
										}
										if(!visited.contains(board_move)){
											fringe.add(board_move);
											visited.add(board_move);
										}
										
									//	EdgeInfo info = new EdgeInfo();
									//	info.setDistance(board_dist, move_distance);
									//	myTray.addUndirectedEdge(current_vertex, boardCounter,  info);
									//	Solve(movement, goals, moves);
		
								}
						}
					}
				}
			}
		}		
	//	ArrayList<Integer> neighbors = myTray.neighbors(current_vertex);
	//	System.out.println(boards);
	//	System.out.println(board_distances);
	//	System.out.println(neighbors);
	//	System.out.println(fringe);
	//	createTray(fringe.poll(), goals, moves);
	}//end createTray

	
	

	private ArrayList<Block> GoalBlocks(
			HashMap<Integer, ArrayList<Integer>> goal_blocks) {
		ArrayList<Block> blocks = new ArrayList<Block>();
		for (Map.Entry<Integer, ArrayList<Integer>> item : goal_blocks
				.entrySet()) {
			Block goal_block = new Block(item.getKey(), item.getValue());
			blocks.add(goal_block);
		}
		return blocks;
	}

	private class EdgeInfo {
		
		private Block neightbors;
		private double myDistance_prev;
		private double weight_PA;
		private double weight_RA;
		private double weight_gamma;
		private String type;
		private ArrayList<Integer> coordinates;
		private int counts;

		public EdgeInfo() {
			
		}
		
		private double getDistance(){
			return myDistance_prev;
		}
		
		private void setDistance(Block current, Block prev){
			myDistance_prev = (double) Math.abs(Math.abs(current.getx1() - prev.getx1()) + Math.abs(current.gety1() - prev.gety1()));
		}
		
	}

	public class Tray {
		private LinkedList<Edge>[] myAdjLists;
		private int myVertexCount;
		private int myStartVertex;

		// Initialize a graph with the given number of vertices and no edges.
		public Tray(int numVertices) {
			myAdjLists = new LinkedList[numVertices];
			myStartVertex = 0;
			for (int k = 0; k < numVertices; k++) {
				myAdjLists[k] = new LinkedList<Edge>();
			}
			myVertexCount = numVertices;
		}

		// Change the vertex the iterator will start DFS from
		public void setStartVertex(int v) {
			if (v < myVertexCount && v >= 0) {
				myStartVertex = v;
			} else {
				throw new IllegalArgumentException(
						"Cannot set iteration start vertex to " + v + ".");
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
			// your code here
			Edge edge = new Edge(v1, v2, edgeInfo);
			myAdjLists[v1].add(edge);
		}

		// Add to the graph an undirected edge from vertex v1 to vertex v2,
		// with the given edge information.
		public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
			// your code here
			Edge edge = new Edge(v1, v2, edgeInfo);
			Edge rev_edge = new Edge(v2, v1, edgeInfo);
			myAdjLists[v1].add(edge);
			myAdjLists[v2].add(rev_edge);
		}

		// Return true if there is an edge from vertex "from" to vertex "to";
		// return false otherwise.
		public boolean isAdjacent(int from, int to) {
			// your code here
			if (myAdjLists[from].contains(to)) {
				return true;
			} else {
				return false;
			}
		}

		// Returns a list of all the neighboring vertices 'u'
		// such that the edge (VERTEX, 'u') exists in this graph.

		public ArrayList<Integer> neighbors(int vertex) {
			// your code here
			if (myAdjLists[vertex] != null) {
				ArrayList<Integer> mylist = new ArrayList<Integer>();
				for (int i = 0; i < myAdjLists[vertex].size(); i++) {
					if (myAdjLists[vertex].get(i).to() != null) {
						if (!mylist.contains(myAdjLists[vertex].get(i).to())) {
							mylist.add(myAdjLists[vertex].get(i).to());
						}
					}
				}
				return mylist;
			}
			return null;
		}

		// Return the number of incoming vertices for the given vertex,
		// i.e. the number of vertices v such that (v, vertex) is an edge.
		public int inDegree(int vertex) {
			int count = 0;
			// your code here
			for (int i = 0; i < myAdjLists.length; i++) {
				for (int j = 0; j < myAdjLists[i].size(); j++) {
					if (myAdjLists[i].get(j).to() == vertex) {
						count++;
					}
				}

			}
			return count;
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

			public void setFrom(Integer from) {
				this.myFrom = from;
			}

			public void setTo(Integer to) {
				this.myTo = to;
			}

			public Integer from() {
				return myFrom;
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

	}

	private class Block implements Cloneable {
		private ArrayList<Integer> myCoordinates;
		private int myNumber;
		private int myHeight;
		private int myWidth;
		private String myType;
		private int x1;
		private int y1;
		private int x2;
		private int y2;
		private Double myGoalDistance;

		public Block(int node, ArrayList<Integer> dim) {
			myNumber = node;
			// System.out.println(dim.get(3));
			x1 = dim.get(0);
			y1 = dim.get(1);
			x2 = dim.get(2);
			y2 = dim.get(3);
			myCoordinates = dim;
		}

		protected Object clone() throws CloneNotSupportedException {
			return super.clone();
		}

		private int getNumber() {
			return myNumber;
		}
		private void setx1(int another_x1){
			x1 = another_x1;
		}
		private int getx1() {
			return x1;
		}
		private void setx2(int another_x2){
			x2 = another_x2;
		}
		private int getx2() {
			return x2;
		}
		private void sety1(int another_y1){
			y1 = another_y1;
		}
		private int gety1() {
			return y1;
		}
		private void sety2(int another_y2){
			y2 = another_y2;
		}
		private int gety2() {
			return y2;
		}
		
		private ArrayList<Integer> getCurrentCoords(){
			ArrayList<Integer> c_coords = new ArrayList<Integer>();
			
			c_coords.add(getx1());
			c_coords.add(gety1());
			c_coords.add(getx2());
			c_coords.add(gety2());
			return c_coords;
		}
		private ArrayList<Integer> getCoords(){
			
			return myCoordinates;

		}
		
		private void setGoalDistance(Block goal_distance){
			
			myGoalDistance = (double) Math.abs(Math.abs(this.getx1() - goal_distance.getx1()) + Math.abs(this.gety1() - goal_distance.gety1()));
		}
		
		private double getGoalDistance(){
			
			return myGoalDistance;
		}
		private int getHeight() {
			myHeight = Math.abs(x2 - x1) ;
			return myHeight;
		}

		private int getWidth() {
			myWidth = Math.abs(y2 - y1) ;
			return myWidth;
		}

		private String getType() {
			if (getWidth() == getHeight()) {
				myType = "Square";
				return myType;
			} else if (getWidth() > getHeight()) {
				myType = "Rect_parallilo";
				return myType;
			} else {
				myType = "Rect_katheto";
				return myType;
			}

		}

	}

	private class Board implements Cloneable{
		private ArrayList<Integer> myCoordinates;
		private ArrayList<Integer> myCurrentCoords;
		private Block[][] myBoard;
		private Double myDistance;
		private ArrayList<Block> myBlocks = new ArrayList<Block>();

		public Board(int x, int y,
				HashMap<Integer, ArrayList<Integer>> pieces_dim) {
			myBoard = new Block[x][y];

			for (int i = 0; i < pieces_dim.size(); i++) {
				myBlocks.add(new Block(i, pieces_dim.get(i)));

			}
			
			for (int s = 0; s < myBlocks.size(); s++) {
				for (int i = 0; i < x; i++) {
					for (int j = 0; j < y; j++) {
						if (myBlocks.get(s).getx1() == i
								&& myBlocks.get(s).gety1() == j) {
							for (int k = 0; k <= myBlocks.get(i).getHeight(); k++) {
								for (int l = 0; l <= myBlocks.get(i).getWidth(); l++) {
									myBoard[i][j + l] = myBlocks.get(s);
									myBoard[i + k][j] = myBlocks.get(s);

								}
							}
						}
					}
				}
			}
			// boards.put(boardCounter, myBoard);
			boardCounter++;
		}// end constructor

		public Board(Block[][] blocks) {
			myBoard = blocks;
			
		}

		protected Object clone() throws CloneNotSupportedException {
			return super.clone();
		}

		private Block[][] getBoard() {
			System.out.println();
			return myBoard;
		}
		
		private void setCurrentCoords(ArrayList<Integer> coors){
			myCurrentCoords =coors;
			
		}
		private ArrayList<Integer> getCurrentCoords(){
			return myCurrentCoords;
		}
		private void setCoordinates(ArrayList<Integer> coords){
			
			myCoordinates = coords;


		}
		
		private ArrayList<Integer> getCoordinates(){
			return myCoordinates;
		}
		
		public String toString(){
			return "" + getCurrentCoords().get(0) + " " + getCurrentCoords().get(1) + " " +getCurrentCoords().get(2) + " " + getCurrentCoords().get(3)+ "";
			
		}
		private void setBoardDistance(Double distance) {
		
			myDistance =  distance;

		}
		
		private Double getDistance() {
			return myDistance;
		}

		private Boolean CanMove(Integer vertex, String move) {

			for (int i = 0; i < getBoard().length; i++) {
				for (int j = 0; j < getBoard()[i].length; j++) {

					if (getBoard()[i][j] != null
							&& getBoard()[i][j].getNumber() == vertex) { 
						switch (move) {
						case "UP":
						
						if (i != 0  && getBoard()[i][j].getx1() > 0 && getBoard()[i][j].getx2() > 0 ) {
							for (int v=0; v <= getBoard()[i][j].getWidth(); v++){
								if (getBoard()[i - 1][j + v] != null){
									return false;
								} 
							}
							return true;
						} else {
							return false;
						} 
						case "DOWN":
							
							 if (i + getBoard()[i][j].getHeight() < getBoard().length -1 
									 && getBoard()[i][j].getx2() < getBoard().length-1 && getBoard()[i][j].getx1() < getBoard().length-1) {
								 System.out.println(getBoard()[i][j].getHeight());
								 for (int v=0; v <= getBoard()[i][j].getWidth(); v++){
										if (getBoard()[i + getBoard()[i][j].getHeight() +1][j + v] != null){
											return false;
										} 
									}
								 	
								return true;
							} else {
								return false;
							} 
						case "RIGHT":
							
							if(j + getBoard()[i][j].getWidth() < getBoard()[i].length -1 && getBoard()[i][j].gety2() < getBoard()[i].length-1
									&& getBoard()[i][j].gety1() < getBoard()[i].length-1){     		
								for(int v =0; v <= getBoard()[i][j].getHeight(); v++){
									if(getBoard()[i + v][j + getBoard()[i][j].getWidth() + 1] != null){
										return false;
									}
								}
								return true;
							}else{	
								return false;
							}
						case "LEFT":
							
							if (j != 0 && getBoard()[i][j].gety1() > 0  && getBoard()[i][j].gety2() > 0 ) {
								     	
								for(int v =0; v <= getBoard()[i][j].getHeight(); v++){
									if(getBoard()[i + v][j - 1] != null){
										return false;
									}
								}
									return true;
							}else{	
									return false;
							}
						}
					}
				}
			}

			return false;

		}

		private Board Move(Integer vertex, String move, Block goal_distance)
				throws CloneNotSupportedException {

			Block[][] boardCopy = new Block[myBoard.length][];
			for (int i = 0; i < myBoard.length; i++) {
				boardCopy[i] = new Block[myBoard[i].length];
				for (int j = 0; j < myBoard[i].length; j++) {
					if (myBoard[i][j] != null) {
						boardCopy[i][j] = (Block) myBoard[i][j].clone();
					}
				}
			}

			// do whatever

			if (CanMove(vertex, move)) {

				for (int i = 0; i < getBoard().length; i++) {
					for (int j = 0; j < getBoard()[i].length; j++) {
					

						if (getBoard()[i][j] != null
								&& getBoard()[i][j].getNumber() == vertex) {

							switch (move) {
							case "UP":
								ArrayList<Integer> coors = new ArrayList<Integer>();
								ArrayList<Integer> c_coords = new ArrayList<Integer>();
								coors.add(boardCopy[i][j].getx1());   
								coors.add(boardCopy[i][j].gety1());
								for (int l = 0; l <= myBoard[i][j].getWidth(); l++) {
									
									boardCopy[i - 1][j + l] = getBoard()[i][j];
									boardCopy[i - myBoard[i][j].getHeight()][j
											+ l] = null;
								//	boardCopy[i-1][j].setx1(boardCopy[i-1][j].getx1() -1);
								//	boardCopy[i-1][j].setx2(boardCopy[i-1][j].getx2() -1);
								}
								boardCopy[i-1][j].setx1(boardCopy[i-1][j].getx1() -1);
								boardCopy[i-1][j].setx2(boardCopy[i-1][j].getx2() -1);
								coors.add(boardCopy[i-1][j].getx1());
								coors.add(boardCopy[i-1][j].gety1());
								c_coords.add(boardCopy[i-1][j].getx1());
								c_coords.add(boardCopy[i-1][j].gety1());
								c_coords.add(boardCopy[i-1][j].getx2());
								c_coords.add(boardCopy[i-1][j].gety2());
								boardCounter++;
								boardCopy[i - 1][j].setGoalDistance(goal_distance);
								double distance = boardCopy[i-1][j].getGoalDistance();
								Board board = new Board(boardCopy);
								board.setBoardDistance(distance);
								board.setCoordinates(coors);
								board.setCurrentCoords(c_coords);
								System.out.println(board.getCurrentCoords());
								System.out.println(board.getCoordinates());

								return board;

							case "DOWN":
								ArrayList<Integer> coors1 = new ArrayList<Integer>();
								ArrayList<Integer> c_coords2 = new ArrayList<Integer>();
								coors1.add(boardCopy[i][j].getx1());
								coors1.add(boardCopy[i][j].gety1());
					/**
								System.out.println(boardCopy[i][j].getCoords());
								System.out.println(boardCopy[i][j].getx1());
								System.out.println(boardCopy[i][j].gety1());
								System.out.println(boardCopy[i][j].getx2());
								System.out.println(boardCopy[i][j].gety2());
						**/		
								for (int l = 0; l <= myBoard[i][j].getWidth(); l++) {
										boardCopy[i + myBoard[i][j].getHeight()+ 1][j + l ] = getBoard()[i][j];
										boardCopy[i][j + l] = null;
								//		boardCopy[i+1][j+l].setx1(boardCopy[i+1][j].getx1() +1);
								//		boardCopy[i+1][j+l].setx2(boardCopy[i+1][j].getx2() +1);

								}
								boardCopy[i+1][j].setx1(boardCopy[i+1][j].getx1() +1);
								boardCopy[i+1][j].setx2(boardCopy[i+1][j].getx2() +1);
					/**
								System.out.println(boardCopy[i+1][j].getx1());
								System.out.println(boardCopy[i+1][j].gety1());
								System.out.println(boardCopy[i+1][j].getx2());
								System.out.println(boardCopy[i+1][j].gety2());
					**/
								coors1.add(boardCopy[i+1][j].getx1());
								coors1.add(boardCopy[i+1][j].gety1());
								c_coords2.add(boardCopy[i+1][j].getx1());
								c_coords2.add(boardCopy[i+1][j].gety1());
								c_coords2.add(boardCopy[i+1][j].getx2());
								c_coords2.add(boardCopy[i+1][j].gety2());
								boardCounter++;
								boardCopy[i + 1][j].setGoalDistance(goal_distance);
								double distance1 = boardCopy[i+1][j].getGoalDistance();
								Board board1 = new Board(boardCopy);
								board1.setBoardDistance(distance1);
								board1.setCoordinates(coors1);
								board1.setCurrentCoords(c_coords2);
								System.out.println(board1.getCurrentCoords());
								System.out.println(board1.getCoordinates());
								
								return board1;
								
							case "RIGHT":
								ArrayList<Integer> coors2 = new ArrayList<Integer>();
								ArrayList<Integer> c_coords3 = new ArrayList<Integer>();
								coors2.add(boardCopy[i][j].getx1());
								coors2.add(boardCopy[i][j].gety1());
								for (int l = 0; l <= myBoard[i][j].getHeight(); l++) {
									boardCopy[i + l][j
											+ myBoard[i][j].getWidth() + 1] = getBoard()[i][j];
									boardCopy[i + l][j] = null;
								//	boardCopy[i][j+1].sety1(boardCopy[i][j+1].gety1() +1);
								//	boardCopy[i][j+1].sety2(boardCopy[i][j+1].gety2() +1);
								}
								boardCopy[i][j+1].sety1(boardCopy[i][j+1].gety1() +1);
								boardCopy[i][j+1].sety2(boardCopy[i][j+1].gety2() +1);
								coors2.add(boardCopy[i][j+1].getx1());
								coors2.add(boardCopy[i][j+1].gety1());
								c_coords3.add(boardCopy[i][j+1].getx1());
								c_coords3.add(boardCopy[i][j+1].gety1());
								c_coords3.add(boardCopy[i][j+1].getx2());
								c_coords3.add(boardCopy[i][j+1].gety2());
								boardCounter++;
								boardCopy[i][j +1].setGoalDistance(goal_distance);
								double distance2= boardCopy[i][j+1].getGoalDistance();
								Board board2 = new Board(boardCopy);
								board2.setBoardDistance(distance2);
								board2.setCoordinates(coors2);
								board2.setCurrentCoords(c_coords3);
								System.out.println(board2.getCoordinates());
								System.out.println(board2.getCurrentCoords());

								return board2;
							case "LEFT":
								ArrayList<Integer> coors3 = new ArrayList<Integer>();
								ArrayList<Integer> c_coords4 = new ArrayList<Integer>();
								coors3.add(boardCopy[i][j].getx1());
								coors3.add(boardCopy[i][j].gety1());
								for (int l = 0; l <= myBoard[i][j].getHeight(); l++) {
									boardCopy[i + l][j - 1] = getBoard()[i][j];
									boardCopy[i + l][j
											+ myBoard[i][j].getWidth()] = null;
								//	boardCopy[i][j-1].sety1(boardCopy[i][j-1].gety1() -1);
								//	boardCopy[i][j-1].sety2(boardCopy[i][j-1].gety2() -1);

								}
								boardCopy[i][j-1].sety1(boardCopy[i][j-1].gety1() -1);
								boardCopy[i][j-1].sety2(boardCopy[i][j-1].gety2() -1);
								coors3.add(boardCopy[i][j-1].getx1());
								coors3.add(boardCopy[i][j-1].gety1());
								c_coords4.add(boardCopy[i][j-1].getx1());
								c_coords4.add(boardCopy[i][j-1].gety1());
								c_coords4.add(boardCopy[i][j-1].getx2());
								c_coords4.add(boardCopy[i][j-1].gety2());
								boardCounter++;
								boardCopy[i][j-1].setGoalDistance(goal_distance);
								double distance3 = boardCopy[i][j-1].getGoalDistance();
								Board board3 = new Board(boardCopy);
								board3.setBoardDistance(distance3);
								board3.setCoordinates(coors3);
								board3.setCurrentCoords(c_coords4);
								System.out.println(board3.getCurrentCoords());
								System.out.println(board3.getCoordinates());

								return board3;
							}
						}
					}
				}
			}
			return null;
		}
		
		

		
	}// end Board

	public class MinComparator implements Comparator<Board> {

		@Override public int compare(Board o1, Board o2) { 
			// TODO Auto-generated method stub 
	           			if( o1.getDistance() > o2.getDistance()){
	           				return 0;
	           			}else{
	           				return 1;
	           			}
	           			
	         	 }
	}
	
	private static Integer[] BoardDimentions(String arg)
			throws FileNotFoundException {
		Integer[] board_dim = new Integer[2];
		File input = new File(arg);
		Scanner sc = new Scanner(input);
		board_dim[0] = Integer.parseInt(sc.next());
		board_dim[1] = Integer.parseInt(sc.next());
		sc.close();
		return board_dim;
	}

	private static HashMap<Integer, ArrayList<Integer>> ParseFile(String arg)
			throws FileNotFoundException {
		try {
			HashMap<Integer, ArrayList<Integer>> pieces = new HashMap<Integer, ArrayList<Integer>>();
			File input = new File(arg);
			Scanner sc = new Scanner(input);
			ArrayList<Integer> piece;
			sc.nextLine();
			int i = 0;
			while (sc.hasNextLine()) {
				String[] tokens = sc.nextLine().split("\\s");
				piece = new ArrayList<Integer>();
				int j = 0;
				while (j < tokens.length) {
					piece.add(Integer.parseInt(tokens[j]));
					j++;
				}
				pieces.put(i, piece);
				i++;
			}

			sc.close();
			return pieces;

		} catch (Exception e) {
			throw new FileNotFoundException("File Not found" + e.getMessage());
		}

	}

	private static HashMap<Integer, ArrayList<Integer>> ParseGoal(String arg)
			throws FileNotFoundException {
		try {
			HashMap<Integer, ArrayList<Integer>> pieces = new HashMap<Integer, ArrayList<Integer>>();
			File input = new File(arg);
			Scanner sc = new Scanner(input);
			ArrayList<Integer> piece;
			int i = 0;
			while (sc.hasNextLine()) {
				String[] tokens = sc.nextLine().split("\\s");
				piece = new ArrayList<Integer>();
				int j = 0;
				while (j < tokens.length) {
					piece.add(Integer.parseInt(tokens[j]));
					j++;
				}
				pieces.put(i, piece);
				i++;
			}

			sc.close();
			return pieces;

		} catch (Exception e) {
			throw new FileNotFoundException("File Not found" + e.getMessage());
		}

	}

	public static void main(String[] args) throws IllegalArgumentException,
			FileNotFoundException, CloneNotSupportedException {

		if (args.length != 2) {
			throw new IllegalArgumentException(
					"Wrong parameters given. please try two files!");
		} else if (!args[0].toString().toLowerCase().endsWith("")
				|| !args[1].toString().toLowerCase()
						.endsWith(args[0].toString().toLowerCase() + ".goal")) {
			throw new IllegalArgumentException("Invalid format.");
		} else {

			Integer[] board_dim = BoardDimentions(args[0]);
			HashMap<Integer, ArrayList<Integer>> pieces_dim = ParseFile(args[0]);
			HashMap<Integer, ArrayList<Integer>> goal_pieces_dim = ParseGoal(args[1]);

			Solver solve = new Solver(board_dim, pieces_dim, goal_pieces_dim);

			/**
			 * if(solve.CanSolve(1000)){ solve.Solve(); }else{ System.exit(1); }
			 **/
		}

	}

}
