import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

class Solver {
	private Tray initTray;
	private Tray GoalTray;
	private Integer[] BoardSize;
	private HashSet<Integer> visited;
	private HashMap<ArrayList<Integer>, LinkedList<Block>> goalWHtoBlock;
	private int searchType;
	/**
	 * Initializes the solver.
	 * 
	 * @param init
	 * @param goal
	 * @param dimension
	 */
	public Solver(ArrayList<ArrayList<Integer>> init, ArrayList<ArrayList<Integer>> goal, Integer[] dimension){
		BoardSize = dimension;
		GoalTray = new Tray(dimension, goal, null);
		initTray = new Tray(dimension, init, GoalTray);
		visited = new HashSet<Integer>();
		goalWHtoBlock = GoalTray.getHashBySize();
		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.add(1);
		temp.add(1);
		
		if (initTray.getBlockSet().size() == GoalTray.getBlockSet().size() && 
				initTray.getHashBySize().keySet().size() == 1 &&
				initTray.getHashBySize().get(temp) != null){
			searchType = 1;
		} else{
			searchType = 0;
		}
	}
	
	/**
	 * Iterates through our fringe of next moves and makes sure
	 * that we store the visited Trays so that we do not add them
	 * to the fringe again.
	 * 
	 * @return Tray
	 */
	public Tray move(){
		if (searchType == 1){
			altTray();
		}
		PriorityQueue<Tray> fringe = new PriorityQueue<Tray>();
		fringe.offer(initTray);

		while (!fringe.isEmpty()) {
			Tray currTray = fringe.poll();
			if(currTray.GoalEquals(GoalTray)){
				return currTray;
			}
			visited.add(currTray.hashCode());
			createMoves(currTray);
			for(Tray t: currTray.getChildren()){
				if(!visited.contains(t.hashCode()) && !fringe.contains(t)){
					fringe.offer(t);
				}
			}
			currTray.reset();
		}
		return null;
	}
	
	public void altTray(){
		ArrayList<ArrayList<Integer>> newInitBlock = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> newGoalBlock = new ArrayList<ArrayList<Integer>>();
		
		for (Block b: initTray.getBlockSet()){
			int i = b.getX1();
			int j = b.getY1();
			if (!GoalTray.getBlocks()[i][j]){
				newGoalBlock.add(b.getCoords());
				break;
			}
		}
		
		for (Block b: GoalTray.getBlockSet()){
			int i = b.getX1();
			int j = b.getY1();
			if (!initTray.getBlocks()[i][j]){
				newInitBlock.add(b.getCoords());
				break;
			}
		}
		
		GoalTray = new Tray(BoardSize, newGoalBlock, null);
		initTray = new Tray(BoardSize, newInitBlock, GoalTray);
	}
	
	/**
	 * Adds children to myTray, which are the possible moves after
	 * the current configuration.
	 * @param myTray
	 */
	public void createMoves(Tray myTray){
		HashSet<Tray> TrayChildren = new HashSet<Tray>();
		for(Block b: myTray.getBlockSet()){
			HashSet<ArrayList<Integer>> possibleMoves = getValidMoves(myTray, b);
			if (!possibleMoves.isEmpty()){
				for(ArrayList<Integer> i: possibleMoves){
					ArrayList<Block> newBlocks = moveBlock(myTray.getBlockSet(), b.getCoords(), i);
					Tray temp = new Tray(myTray, i, BoardSize, newBlocks);
					if (temp.GoalEquals(GoalTray)){
						temp.forceUP();
					} 
					TrayChildren.add(temp);
					newBlocks = null;
				}
			}
		}
		myTray.addChildren(TrayChildren);
		TrayChildren = null;
		
	}

	
	public HashSet<ArrayList<Integer>> getValidMoves(Tray myTray, Block b){
		HashSet<ArrayList<Integer>> possibleMoves = new HashSet<ArrayList<Integer>>();
		int height = b.getHeight();
		int width = b.getWidth();
		ArrayList<Integer> myCoords = b.getCoords();
		ArrayList<Integer> moveToGetHere = myTray.getMoveToGetHere();
		ArrayList<Integer> movingBack = new ArrayList<Integer>();
		if (moveToGetHere!= null){
			movingBack.add(moveToGetHere.get(2));
			movingBack.add(moveToGetHere.get(3));
			movingBack.add(moveToGetHere.get(0));
			movingBack.add(moveToGetHere.get(1));
		}
		//chk left
			HashSet<ArrayList<Integer>> checkEmpty = new HashSet<ArrayList<Integer>>();
			ArrayList<Integer> newMove = new ArrayList<Integer>();
			for (int i = 0; i < height; i++) {
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.add(myCoords.get(0) + i);
				temp.add(myCoords.get(1) - 1);
				if(temp.get(1) < 0){
					break;
				}
				checkEmpty.add(temp);
			}
			if(myTray.isEmpty(checkEmpty)){
				newMove.add(myCoords.get(0));
				newMove.add(myCoords.get(1));
				newMove.add(myCoords.get(0));
				newMove.add(myCoords.get(1) - 1);
				possibleMoves.add(newMove);
			}
		//chk right
			checkEmpty = new HashSet<ArrayList<Integer>>();
			newMove = new ArrayList<Integer>();
			for (int i = 0; i < height; i++) {
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.add(myCoords.get(0) + i);
				temp.add(myCoords.get(3) + 1);
				if(temp.get(1) > BoardSize[1] - 1){
					break;
				}
				checkEmpty.add(temp);
			}
			if(myTray.isEmpty(checkEmpty)){
				newMove.add(myCoords.get(0));
				newMove.add(myCoords.get(1));
				newMove.add(myCoords.get(0));
				newMove.add(myCoords.get(1) + 1);
				possibleMoves.add(newMove);
			}
		
		//chk up
			checkEmpty = new HashSet<ArrayList<Integer>>();
			newMove = new ArrayList<Integer>();
			for (int i = 0; i < width; i++) {
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.add(myCoords.get(0) - 1);
				temp.add(myCoords.get(1) + i);
				if(temp.get(0) < 0){
					break;
				}
				checkEmpty.add(temp);
			}
			if(myTray.isEmpty(checkEmpty)){
				newMove.add(myCoords.get(0));
				newMove.add(myCoords.get(1));
				newMove.add(myCoords.get(0) - 1);
				newMove.add(myCoords.get(1));
				possibleMoves.add(newMove);
			}
		
		//chk down
			checkEmpty = new HashSet<ArrayList<Integer>>();
			newMove = new ArrayList<Integer>();
			for (int i = 0; i < width; i++) {
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.add(myCoords.get(2) + 1);
				temp.add(myCoords.get(1) + i);
				if(temp.get(0) > BoardSize[0] - 1){
					break;
				}
				checkEmpty.add(temp);
			}
			if(myTray.isEmpty(checkEmpty)){
				newMove.add(myCoords.get(0));
				newMove.add(myCoords.get(1));
				newMove.add(myCoords.get(0) + 1);
				newMove.add(myCoords.get(1));
				possibleMoves.add(newMove);
			}
			possibleMoves.remove(movingBack);
			return possibleMoves;
	}
	
	public ArrayList<Block> moveBlock(ArrayList<Block> mylist, ArrayList<Integer> origin, ArrayList<Integer> moveToGetHere){
		ArrayList<Block> toReturn = new ArrayList<Block>();
		ArrayList<Integer> destination = new ArrayList<Integer>();
		int i = moveToGetHere.get(2) - moveToGetHere.get(0);
		int j = moveToGetHere.get(3) - moveToGetHere.get(1);
		
		destination.add(moveToGetHere.get(2));
		destination.add(moveToGetHere.get(3));
		destination.add(origin.get(2) + i);
		destination.add(origin.get(3) + j);
		
		for(Block b: mylist){
			if((b.getCoords()).equals(origin)){
				toReturn.add(new Block(destination, goalWHtoBlock.get(b.getWH()), BoardSize));
			} else {
				toReturn.add(new Block(b.getCoords(), goalWHtoBlock.get(b.getWH()), BoardSize));
			}
		}
		return toReturn;
	}
	
	public void printPath(){
		Tray curr = move();
		Stack<Tray> pathToGoal = new Stack<Tray>();
		while(curr != null){
			pathToGoal.push(curr);
			curr = curr.getParent();
		}
		curr = null;
		
		while (!pathToGoal.isEmpty()) {
			ArrayList<Integer> rtn = pathToGoal.pop().getMoveToGetHere();
			if (searchType == 1){
				rtn = reverseMove(rtn);
			}
			
			String toReturn = "";
			if(rtn != null){
				for (Integer i: rtn){
					toReturn += (i + " ");
				}
				System.out.println(toReturn.substring(0, toReturn.length() - 1));
			}
		}
		
	}
	
	public ArrayList<Integer> reverseMove(ArrayList<Integer> myMove){
		if (myMove == null){
			return null;
		}
		
		ArrayList<Integer> toRtn = new ArrayList<Integer>();
		toRtn.add(myMove.get(2));
		toRtn.add(myMove.get(3));
		toRtn.add(myMove.get(0));
		toRtn.add(myMove.get(1));
		return toRtn;
	}
	
	
	public static void main(String[] args){
		if(args.length != 2){
			System.out.println("Invalid init and/or goal file.");
			return;
		}
		try {
			ArrayList<ArrayList<Integer>> myBlocks = new ArrayList<ArrayList<Integer>>();
			FileReader init = new FileReader(args[0]);
			FileReader goal = new FileReader(args[1]);
			BufferedReader brinit = new BufferedReader(init);
			BufferedReader brgoal = new BufferedReader(goal);
			String line;
			Integer[] dimension = new Integer[2];
		    while ((line = brinit.readLine()) != null) {
		    	ArrayList<Integer> temp = new ArrayList<Integer>();
		    	String[] splitArray = line.split("\\s+");
	    	    if (splitArray.length == 2){
			    	dimension[0] = (Integer.parseInt(splitArray[0]));
		    	    dimension[1] = (Integer.parseInt(splitArray[1]));
	    	    } else{
			    	if (splitArray.length != 4){
			    		System.out.println("Invalid init and/or goal file.");
						return;
			    	}
	    	    	for(int i = 0; i < splitArray.length; i ++){
			    		temp.add(Integer.parseInt(splitArray[i]));
			    	}
			    	myBlocks.add(temp);
	    	    }
		    }
		    
		    ArrayList<ArrayList<Integer>> goalBlocks = new ArrayList<ArrayList<Integer>>();
			String goalline;
		    while ((goalline = brgoal.readLine()) != null) {
		    	ArrayList<Integer> temp = new ArrayList<Integer>();
		    	String[] splitArray = goalline.split("\\s+");
		    	if (splitArray.length != 4){
		    		System.out.println("Invalid init and/or goal file.");
					return;
		    	}
		    	for(int i = 0; i < splitArray.length; i ++){
		    		temp.add(Integer.parseInt(splitArray[i]));
		    	}
		    	goalBlocks.add(temp);
		    }
		    
		    Solver MYTHANG = new Solver(myBlocks, goalBlocks, dimension);
		    myBlocks = null;
		    goalBlocks = null;
		    init = null;
		    goal = null;
		    
		    MYTHANG.printPath();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		
	}
}
//