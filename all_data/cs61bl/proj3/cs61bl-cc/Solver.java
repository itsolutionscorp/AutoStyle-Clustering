
/**
 * CS61BL - UC Berkeley Summer 2015 Project 3 - Sliding Block Puzzles
 * 
 * Class 1/3:
 * 
 * Solver.java <<<
 * 
 * Tray.java
 * 
 * Block.java
 * 
 * 
 * @authors Jessica Larson, Brian Sakhuja, Eifu Tomita, Alex Yao
 *
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class Solver {

	private List<Block> goalPlaceList;
	private Tray initialTray;
	protected static int myHeight;
	protected static int myWidth;
//	protected static double ratioOfEmptySpace;

	/**
	 * Solver
	 * 
	 * Solver onstructor. Takes in a the two argument files. Initializes:
	 * ADJACENCYLISTMAP, TRAYTOINDEXMAP, INDEXTOTRAYMAP
	 * 
	 * It adds the initial tray to TRAYTOINDEXMAP, and creates a list of the
	 * neighbors of that tray. Then it adds the neighbors to ADJACENCYLISTMAP
	 * 
	 * @param init
	 *            - string representation of the tray
	 * @param goal
	 *            - string representation of the goal position
	 * @throws FileNotFoundException 
	 */
	public Solver(String init, String goal)   {
		
		initialTray = new Tray(init);
		goalPlaceList = goalList(goal);

	}

	/**
	 * goalList
	 * 
	 * @param goal
	 *            - the string representation of the goal position
	 * @return - List<Block> of the goal configuration
	 * @throws FileNotFoundException
	 */
	public List<Block> goalList(String goal) {
		List<Block> result = new ArrayList<Block>();
		File f = new File(goal);
		try {
			Scanner sc = new Scanner(f);

			while (sc.hasNext()) {
				result.add(new Block(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt()));
			}
		} catch (NoSuchElementException e) {
			result = null;
		} catch (FileNotFoundException ee) {
			result = null;
		} catch(Exception eee){
			result = null;
		}

		return result;
	}


	/**
	 * neighbors
	 * 
	 * @param t
	 *            - input tray
	 *
	 * @return LinkedList<Tray> - the neighbor trays, calling MOVE to create the
	 *         neighbor trays.
	 */
	public LinkedList<Tray> neighbors(Tray t) {
		LinkedList<Tray> nextTrayList = new LinkedList<Tray>();
		Set<Tray> counter = new HashSet<Tray>();
		for (int i : t.getTrackingEmptySet()) {
			counter = move(t, i, nextTrayList, counter);
		}
		return nextTrayList;
	}

	/**
	 * move
	 * 
	 * @param t
	 *            - tray in which we want to move.
	 * @param emptyBlock
	 *            - the empty block location.
	 * @param nextTrayList
	 *            - the linkedlist of trays in which we add the possible moves.
	 */
	public Set<Tray> move(Tray t, int emptyBlock, LinkedList<Tray> nextTrayList, Set<Tray> countedSoFar) {
		int eH = emptyBlock / myWidth;
		int eW = emptyBlock % myWidth;

		Set<Tray> counter = countedSoFar;
		Block[][] trayToBeChecked = t.getTray();
		if (eW % myWidth != myWidth - 1 && canMoveBlockLeft(t, trayToBeChecked[eH][eW + 1])) {
			Tray newTL = new Tray(t, trayToBeChecked[eH][eW + 1], 1);  // left
			if (!counter.contains(newTL)) {
				nextTrayList.addLast(newTL);
				counter.add(newTL);
			}
		}
		if (eW % myWidth != 0 && canMoveBlockRight(t, trayToBeChecked[eH][eW - 1])) {
			Tray newTR = new Tray(t, trayToBeChecked[eH][eW - 1], 2); // right
			if (!counter.contains(newTR)) {
				nextTrayList.addLast(newTR);
				counter.add(newTR);
			}
		}

		if (eH != myHeight - 1 && canMoveBlockUp(t, trayToBeChecked[eH + 1][eW])) {
			Tray newTU = new Tray(t, trayToBeChecked[eH + 1][eW], 3); // up
			if (!counter.contains(newTU)) {
				nextTrayList.addLast(newTU);
				counter.add(newTU);
			}
		}
		if (eH != 0 && canMoveBlockDown(t, trayToBeChecked[eH - 1][eW])) {
			Tray newTD = new Tray(t, trayToBeChecked[eH - 1][eW], 4); // down
			if (!counter.contains(newTD)) {
				nextTrayList.addLast(newTD);
				counter.add(newTD);
			}
		}
		return counter;

	}

	/**
	 * canMoveBlockRight
	 * 
	 * @param t
	 *            - the tray that we are inspecting.
	 * @param bL
	 *            - the block that we are checking can move right.
	 * @return - true if the block can move right. False if it can't or the
	 *         block is null.
	 */
	private boolean canMoveBlockRight(Tray t, Block bL) {
		if (bL == null) {
			return false;
		}
		for (int i = bL.getTop(); i <= bL.getBottom(); i++) {
			if (t.getTray()[i][bL.getRight() + 1] != null)
				return false;
		}
		return true;
	}

	/**
	 * canMoveBlockLeft
	 * 
	 * @param t
	 *            - the tray that we are inspecting.
	 * @param bR
	 *            - the block that we are checking can move left.
	 * @return - true if the block can move left. False if it can't or the block
	 *         is null.
	 */
	private boolean canMoveBlockLeft(Tray t, Block bR) {
		if (bR == null) {
			return false;
		}
		for (int i = bR.getTop(); i <= bR.getBottom(); i++) {
			if (t.getTray()[i][bR.getLeft() - 1] != null)
				return false;
		}
		return true;
	}

	/**
	 * canMoveBlockUp
	 * 
	 * @param t
	 *            - the tray that we are inspecting.
	 * @param bB
	 *            - the block that we are checking can move up.
	 * @return - true if the block can move up. False if it can't or the block
	 *         is null.
	 */
	private boolean canMoveBlockUp(Tray t, Block bB) {
		if (bB == null) {
			return false;
		}
		for (int i = bB.getLeft(); i <= bB.getRight(); i++) {
			if (t.getTray()[bB.getTop() - 1][i] != null)
				return false;
		}
		return true;
	}

	/**
	 * canMoveBlockDown
	 * 
	 * @param t
	 *            - the tray that we are inspecting.
	 * @param bT
	 *            - the block that we are checking can move down.
	 * @return - true if the block can move down. False if it can't or the block
	 *         is null.
	 */
	private boolean canMoveBlockDown(Tray t, Block bT) {
		if (bT == null) {
			return false;
		}
		for (int i = bT.getLeft(); i <= bT.getRight(); i++) {
			if (t.getTray()[bT.getBottom() + 1][i] != null)
				return false;
		}
		return true;
	}

	


	/**
	 * breadthFirstSearch
	 * 
	 * Inspects the graph of tray configurations via a breadth first search
	 * traversal
	 */
	public void breadthFirstSearch() {
		double start = System.currentTimeMillis();
		Set<Tray> visited = new HashSet<Tray>();
		Map<Tray, Tray> parent = new HashMap<Tray, Tray>();
		Map<Integer, LinkedList<Tray>> levelMap = new HashMap<Integer, LinkedList<Tray>>();

		Tray initialT = initialTray;
		LinkedList<Tray> initialList = new LinkedList<Tray>();
		initialList.add(initialT);
		levelMap.put(0, initialList);

		for (int lev = 0; lev < 100; lev++) {
			LinkedList<Tray> levelList = new LinkedList<Tray>();
			List<Tray> placeHolderList = levelMap.get(lev);
			for (Tray placeHolder : placeHolderList) {
				visited.add(placeHolder);
				List<Tray> neighborsList = neighbors(placeHolder);
				for (Tray t : neighborsList) {
					if (!visited.contains(t)) {
						levelList.add(t);
						parent.put(t, placeHolder);
						if (t.isGoal (goalPlaceList)) {

							while (!t.equals(initialT)) {
								// System.out.println(t.toEdge());
								t = parent.get(t);
							}
							double end = System.currentTimeMillis();
							System.out.println("time: " + (end - start));
							return;
						}
					}
				}
			}
			levelMap.put(lev + 1, levelList);
		}
		return;

	}

	/**
	 * depthFirstSearch
	 * 
	 * Inspects the graph of tray configurations via a depth first search
	 * traversal.
	 */
	public void depthFirstSearch() {
//		 double start = System.currentTimeMillis();
		ArrayDeque<Tray> fringe = new ArrayDeque<Tray>();
//		 Stack<Tray> fringe = new Stack<Tray>();
		Set<Tray> hasBeenAddedToFringe = new HashSet<Tray>();
		Tray parentHolder = null;
		if (goalPlaceList == null || initialTray.getTray() == null) {
			return;
		}
		Map<Tray, Tray> parentMap = new HashMap<Tray, Tray>();
		fringe.push(initialTray);
		hasBeenAddedToFringe.add(initialTray);

		while (!fringe.isEmpty()) {
			Tray temp = fringe.pop();
			parentMap.put(temp, parentHolder);
			if (temp.isGoal( goalPlaceList)) {
				List<String> result = new LinkedList<String>();
				while (parentMap.get(temp) != null) {
					Tray pp = parentMap.get(temp);
					List<Tray> l = neighbors(temp);
					while (!l.contains(pp)) {
						pp = parentMap.get(pp);
					}
					result.add(0, pp.findMovedBlock(temp));
					
					temp = pp;
				}
				for (String s : result) {
					System.out.println(s);
				}
				
//				 double end = System.currentTimeMillis();
//				 System.out.println("time: " + (end - start));
				return;
			}
			LinkedList<Tray> placeHolderList = neighbors(temp);
			for (Tray placeHolder : placeHolderList) {
				if (!hasBeenAddedToFringe.contains(placeHolder)) {
					fringe.push(placeHolder);
					hasBeenAddedToFringe.add(placeHolder);
				}
			}
			parentHolder = temp;
		}

	}

	/**
	 * main
	 * 
	 * @param args
	 *            - the array of strings from the terminal input
	 * @throws Exception
	 */
	public static void main(String[] args)  {
		if(args.length != 2){
			return;
		}
		Solver s = new Solver(args[0], args[1]);
		
		// System.out.println("result of DFS : ");
		s.depthFirstSearch();
		// System.out.println("result of BFS : ");
		// s.breadthFirstSearch();

	}

}
