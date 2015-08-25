import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Iterator;

/**
 * Solver class for Sliding Block Puzzles project.
 * @author Erik Riiska, Eric Huynh, Mingu Jo, Steven Truong 
 */

public class Solver {

	// INSTANCE VARIABLES 
	
	private PriorityQueue<Tray> fringe;
	private HashSet<Tray> visited;
	private ArrayList<Block> myGoal;
	private Tray init;

	public Solver(List<String> initial, List<String> goal) {
		fringe = new PriorityQueue<Tray>();
		visited = new HashSet<Tray>();
		if (initial.get(0).length() < 3) {																	// WTF 
			System.out.println("Invalid init and/or goal file.");
			return;
		}
		String config = initial.get(0);
		int height = Integer.parseInt(config.substring(0, config.indexOf(" ")));
		int width = Integer.parseInt(config.substring(config.indexOf(" ") + 1, config.length()));
		this.init = new Tray(null, height, width, new int[0], this);
		fringe.add(this.init);
		blockCreator(initial, false);
		myGoal = new ArrayList<Block>();
		blockCreator(goal, true);
	}

	public void blockCreator(List<String> blocks, boolean add_to_list) {
		int i = 1;
		if(add_to_list) {
			i = 0;
		}
		for (; i<blocks.size(); i++) {
			String block = blocks.get(i);
			try {
				int first_space = block.indexOf(" ");
				int tly = Integer.parseInt(block.substring(0, first_space));									// HEIGHT by WIDTH
				int second_space = block.indexOf(" ", first_space + 1);
				int tlx = Integer.parseInt(block.substring(first_space + 1, second_space));					// HEIGHT by WIDTH
				int third_space = block.indexOf(" ", second_space + 1);
				int bry = Integer.parseInt(block.substring(second_space+1, third_space));
				int brx = Integer.parseInt(block.substring(third_space+1, block.length()));
				if (add_to_list) {
					Block new_block = new Block(tly, tlx, bry, brx, null);										// HEIGHT by WIDTH
					myGoal.add(new_block);
				} else {
					Block new_block = new Block(tly, tlx, bry, brx, this.init);									// HEIGHT by WIDTH
				}
			} catch (Exception e) {
				System.out.println("Invalid init and/or goal file.");
				return;
			}
		}
	}

	public void solve() {
		Tray curr = null;
		while(!fringe.isEmpty()) {
			curr = fringe.poll();
			if (visited.contains(curr)) {
				continue;
			} 
			if (curr.isGoal()) {
				getPath(curr);
				return;
			}
			visited.add(curr);
			curr.createNeighbors(this);
		}
	}

	public void getPath(Tray t){
		LinkedList<int[]> moves = new LinkedList<int[]>();
	    while (t.getParent() != null){
	        moves.addFirst(t.getMove());
	        t = t.getParent();
	    }
	    moves.addFirst(t.getMove());
	    Iterator<int[]> iter = moves.iterator();
	    while(iter.hasNext()) {
	    	int[] move = iter.next();
	    	for(int i = 0; i < move.length; i++) {
	    		if (i + 1 == move.length) {
	    			System.out.print(move[i]);
	    	    	System.out.println();
	    		} else {
		    		System.out.print(move[i] + " ");
	    		}
	    	}
	    }
	}
	
	public PriorityQueue<Tray> getFringe() {
		return this.fringe;
	}
	
	public HashSet<Tray> getVisited() {
		return this.visited;
	}
	
	public ArrayList<Block> getGoal() {
		return this.myGoal;
	}
	

	//////// MAIN METHOD ////////
	
	public static void main (String[] args) {
		if (args.length != 2) {
			System.out.println("Invalid init and/or goal file.");
			return;
		} 
		File init = new File(args[0]);
		File goal = new File(args[1]);
		try {
			List<String> initial = Files.readAllLines(init.toPath());
			List<String> solved = Files.readAllLines(goal.toPath());
			Solver s = new Solver(initial, solved);
			s.solve();
		} catch (IOException e) {
			System.out.println("Invalid init and/or goal file.");
			return;
		}
	}
}