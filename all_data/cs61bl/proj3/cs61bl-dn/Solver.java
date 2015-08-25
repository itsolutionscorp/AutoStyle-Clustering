import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Stack;

public class Solver {

	/*
	 * Instance variables
	 */
	Tray initialTray;  //Tray we start with
	Tray goalTray;		//Tray configuration we are trying to reach
	int height;			//Height of block
	int width;			//Width of block
	int hV;				// Hash value of tray



	/**
	 * Creates trays for both the initial tray and goal tray.
	 * Adds the trays to hashset.
	 */
	public void init(File initial, File goal) throws FileNotFoundException, IOException {
		HashSet<String> initialBlocks = convertToHashset(initial);
		initialTray = new Tray(initialBlocks, null, null, height, width);

		HashSet<String> goalBlocks = convertToHashset2(goal);
		goalTray = new Tray(goalBlocks, null, null, height, width);

		path(initialTray, goalTray);

	}


	/**
	 * Uses buffer reader to go through the lines of intial tray.
	 * Sets width and height from file.
	 * Adds blocks to Hashset and returns the Hashset.
	 */
	public HashSet<String> convertToHashset(File file) throws NumberFormatException, IOException {
		HashSet<String> toBeHashed = new HashSet<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			line = br.readLine();
			String[] boardDim = line.split(" ");
			height = Integer.parseInt(boardDim[0]);
			width = Integer.parseInt(boardDim[1]);
			if (height < 1 || height > 256){
				System.err.println("Invalid init and/or goal file");
			}
			if (width < 1 || width > 256){
				System.err.println("Invalid init and/or goal file");
			}

			try {while ((line = br.readLine()) != null) {
				toBeHashed.add(line);
			}
			} catch (NullPointerException e) {
				System.err.println ("Invalid init and/or goal file4444");
			}catch (ArrayIndexOutOfBoundsException e) {
				System.err.println ("Invalid init and/or goal file333333");
			}
		}

		return toBeHashed;
	}


	/**
	 * Used for goal tray.
	 * Reads through goal tray and adds it to a Hashset.
	 * 
	 */
	public HashSet<String> convertToHashset2(File file) throws NumberFormatException, IOException {
		HashSet<String> toBeHashed = new HashSet<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			try{ while ((line = br.readLine()) != null) {
				toBeHashed.add(line);
			}
			}catch (IOException e) {
				System.err.println ("Invalid init and/or goal file");
			}
		}catch (ArrayIndexOutOfBoundsException e) {
			System.err.println ("Invalid init and/or goal file");
		}
		return toBeHashed;
	}

	/**
	 * Creates a stack for the trays and moves.
	 * Checks if current tray matches goal tray
	 * Prints out moves
	 * 
	 */
	public void path(Tray startVertex, Tray stopVertex) {
		HashSet<HashSet> visited = new HashSet<HashSet>();      //Keeps tracks of all the visited blocks
		Stack<Tray> fringe = new Stack<Tray>();
		Stack<String> moves = new Stack<String>();
		Tray curr = null;
		fringe.push(startVertex);
		visited.add(startVertex.blocks);
		boolean solved = false;

		while (!fringe.isEmpty()) {
			curr = fringe.pop();        	
			if (curr.containsGoal(stopVertex)) {
				solved = true;
				break;
			}

			for (Tray a : curr.possibleMoves()) {			//keeps visited hashset up to date
				if (!visited.contains(a.blocks)) {
					visited.add(a.blocks);
					fringe.add(a);
				}
			}
		}

		if (solved) {
			while (curr != initialTray) {
				moves.add(curr.prevMove);
				curr = curr.prev;
			}

			while (!moves.isEmpty()) {
				String move = (String) moves.pop();
				if (move != null) {
					System.out.println(move);
				}
			}
		} else{ 
			return;
		}
	}


	/**
	 * Takes in files.
	 */
	public static void main(String[] args) {
		File init = new File(args[0]);
		File goal = new File(args[1]);
		Solver a = new Solver();
		try {
			a.init(init, goal);
		} catch (FileNotFoundException e) {
			System.out.println("Invalid init and/or goal file.");
			return;
		} catch (IOException e) {
			System.out.println("Invalid init and/or goal file.");
			return;
		}catch (NullPointerException e) {
			System.err.println ("Invalid init and/or goal file");
			return;
		}


	}
}
