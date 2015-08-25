import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;

public class Solver {

	public Solver() {
		
	}
	
	/**
	 * Solves the sliding block puzzle, given the start and goal trays.
	 * 
	 * @param startTray
	 *            The tray state that the puzzle starts at
	 * @param goalTray
	 *            The tray state to reach
	 * @return
	 *            An ArrayList of moves needed to 
	 */
	
	public ArrayList<Move> solve(Tray startTray, Tray goalTray) {
        // Do a breadth first search of the tray states
        // Start at startTray, and use a FIFO queue for the fringe
        // Hash the visited states (via their ids)
        // When reaching goalTray, traverse backwards to get the list of moves
        //LinkedList<Tray> fringe = new LinkedList<Tray>();
		PriorityQueue<Tray> fringe = new PriorityQueue<Tray>();
        HashSet<String> visited = new HashSet<String>();
        LinkedList<Move> path = new LinkedList<Move>();
        // Uncomment one of the following:
        // //do nothing
        //startTray.setHeuristicDistance(getMisplacedTilesHeuristic(startTray, goalTray));
        //startTray.setHeuristicDistance(getManhattanHeuristicSum(startTray, goalTray));
        // Heuristics end
        fringe.add(startTray);

        while (!fringe.isEmpty()) {
        	//System.out.println(visited.size() + ": ");
            Tray currentTray = fringe.poll();
            if (currentTray.equalsGoal(goalTray)) {
                Move move = currentTray.getMove();
                while (move != null) {
                    path.push(move);
                    move = move.getPrevMove();
                }
                break;
            }
            visited.add(currentTray.getID());
            for (Move move : currentTray.getPossibleMoves()) {
                Tray nextTray = new Tray(currentTray, move);
                if (!visited.contains(nextTray.getID())) {
                    // Uncomment one of the following:
                	// //do nothing
                    //nextTray.setHeuristicDistance(getMisplacedTilesHeuristic(nextTray, goalTray));
                    //nextTray.setHeuristicDistance(getManhattanHeuristicSum(nextTray, goalTray));
                    // Heuristics end
                    fringe.add(nextTray);
                }
            }
        }
        return new ArrayList<Move>(path);
    }
	
	public int getMisplacedTilesHeuristic (Tray curr, Tray goal) {
        int score = 0;
        for (int x = 0; x < goal.getWidth(); x++) {
            for (int y = 0; y < goal.getHeight(); y++) {
                Block goalBlock = goal.getBlocks()[y][x];
                Block currBlock = curr.getBlocks()[y][x];
                if (goalBlock != null && currBlock != null && goal.getBlocks()[y][x].hashCode() == curr.getBlocks()[y][x].hashCode()) {
                    score++;
                }
            }
        }
        return score;
    }
	   public int getManhattanHeuristicSum (Tray curr, Tray goal) {
	        int score = 0;
	        int width = curr.getWidth();
	        int height = curr.getHeight();
	        for (int x = 0; x < width; x++) {
	            for (int y = 0; y < height; y++) {
	                Block goalBlock = goal.getBlocks()[y][x];
	                if (goalBlock != null) {
	                    for (int z = x; x < width; x++) {
	                        for (int a = y; y < height; y++) {
	                            Block currBlock = curr.getBlocks()[a][z];
	                            if (currBlock != null && currBlock.hashCode() == goalBlock.hashCode()) {
	                                score += Math.abs((z - x));
	                                score += Math.abs((a - y));
	                            }
	                        }
	                    }
	                }
	            }
	        }
	        return score;
	    }
	/**
	 * Creates and loads the tray from the given tray configuration file.
	 * 
	 * The first line is the height and width of the tray.
	 * 
	 * @param fileName
	 *            Name of the file with the tray
	 * @throws IOException
	 *            If the file does not exist
	 * @throws IllegalArgumentException
	 *            If the file is not a valid tray configuration file
	 */
	public Tray loadTray(String fileName)
			throws IOException, IllegalArgumentException{
		
		// The first line should be the height and width
		// The next lines should be block configurations
		// Create a Block[][] and put in a new Block for each line
		// Make sure to put in reference in NW corner and SE corner
		// If anything fails checks, throw an exception
		
		Tray tray = null;

		try (BufferedReader br = new BufferedReader(new FileReader(fileName));) {
		
		String line = null;;
		int[] blockArray = new int[4];
		int height = 0;
		int width = 0;

		// create an empty tray from specified dimensions
		line = br.readLine();
		String[] dimensions = line.split(" ");
		if (dimensions.length != 2) {
			br.close();
			throw new IllegalArgumentException("must include digits for height and width");
		}
		height = Integer.parseInt(dimensions[0]);
		width = Integer.parseInt(dimensions[1]);
			tray = new Tray(height, width);

		// read each line of blocks and add each block to the tray
		while ((line = br.readLine()) != null) {
			String[] tokens = line.split(" ");
			if (tokens.length != 4) {
				br.close();
				throw new IllegalArgumentException("blocks must be four digits long");
			}
			for (int i=0; i < 4; i++) {
				blockArray[i] = Integer.parseInt(tokens[i]);
			}	
			tray.addBlock(blockArray);
		}
		tray.generateEmptySpacesandID();
		
		} catch (IllegalArgumentException | IOException e) {
			throw e;
		}
		return tray;
	}
	
	/**
	 * Creates and loads the tray from the given tray configuration file, 
	 * with the given height and width.
	 * 
	 * @param fileName
	 *            Name of the file with the tray
	 * @throws IOException
	 *            If the file does not exist
	 * @throws IllegalArgumentException
	 *            If the file is not a valid tray configuration file
	 */
	public Tray loadTray(String fileName, int width, int height)
			throws IOException, IllegalArgumentException{
		
		// Every line should be a block configuration
		// Create a Block[][] and put in a new Block for each line
		// Make sure to put in reference in NW corner and SE corner
		// If anything fails checks, throw an exception
		Tray tray = null;
		
		try (BufferedReader br = new BufferedReader(new FileReader(fileName));) {
			
		String line = null;
		int[] blockArray = new int[4];

		tray = new Tray(height, width);

		// read each line of blocks and add each block to the tray
		while ((line = br.readLine()) != null) {
			String[] tokens = line.split(" ");
			if (tokens.length != 4) {
				throw new IllegalArgumentException("blocks must be four digits long");
			}
			for (int i=0; i < 4; i++) {
				blockArray[i] = Integer.parseInt(tokens[i]);
			}	
			tray.addBlock(blockArray);
		} 
		
		} catch (IllegalArgumentException | IOException e) {
			throw e;
		}
		return tray;
	}

	public static void main(String[] args) {
        if (args.length == 0) {
        	System.out.println("Invalid init and/or goal file.");
            return;
        }
        Solver solver = new Solver();
        try {
            Tray start = solver.loadTray(args[0]);
            ArrayList<Move> moves = solver.solve(start, solver.loadTray(args[1], start.getWidth(), start.getHeight()));
            for (Move move : moves) {
                System.out.println(move);
            }
        } catch (IllegalStateException | IOException | IllegalArgumentException e) {
            System.out.println("Invalid init and/or goal file.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid init and/or goal file.");
        } catch (Exception e) {
            System.out.println("Invalid init and/or goal file.");
        }
    }
}