import java.io.*;
import java.util.*;

public class Solver {

	HashSet<Board> visited;
	PriorityQueue<Board> open;
	Board start;
	Board end;
	static ArrayList<Block> goalBlocks;
	
	public Solver() {
		visited = new HashSet<Board>(); 
		goalBlocks = new ArrayList<Block>();
		open = new PriorityQueue<Board>();
	}

	public void pathSearch(Board start) {
		while(open.peek() != null){
			Board curr = open.poll();
			if (curr.prevBoard == null) {
				Board.calcDistance(curr, end);
			}
			goooal(curr);
			
			curr.potentialMoves();
			visited.add(curr);
		
		for (Map.Entry<Block, boolean[]> entry : curr.validMoves.entrySet()) {
			Block block = entry.getKey();
			boolean[] directions = entry.getValue();

			if (directions[0]) {
				Board zero = curr.makeAMove(block, 0);
				Board.calcDistance(zero, end);
				if (!visited.contains(zero)) {
					visited.add(zero);
					open.add(zero);		
				}
			}
			if (directions[1]) {
				Board one = curr.makeAMove(block, 1);
				Board.calcDistance(one, end);
				
				if (!visited.contains(one)) {
					visited.add(one);
					open.add(one);
				}
			}
			if (directions[2]) {
				Board two = curr.makeAMove(block, 2);
				Board.calcDistance(two, end);
	
				if (!visited.contains(two)) {
					visited.add(two);
					open.add(two);
				}
			}
			if (directions[3]) {
				Board three = curr.makeAMove(block, 3);
				Board.calcDistance(three, end);

				if (!visited.contains(three)) {
					visited.add(three);
					open.add(three);
				}
			}
		}
	}

}

	public void printMoves(Board b) {
		Board pointer = b;
		ArrayList<String> reversePath = new ArrayList<String>(); 

		while (pointer != start) {
			reversePath.add(pointer.move);
			pointer = pointer.prevBoard;
		}

		for (int i = reversePath.size() - 1; i >= 0; i--) {
			System.out.println(reversePath.get(i));
		}
		System.exit(0);
	}
	private void goooal(Board b) {
		for (Block g : goalBlocks) {
			if (!b.blocks.contains(g)) {
				return;
			}
		}
		printMoves(b);
	}

	public static void main(String[] args) {
		Solver s = new Solver();

		Scanner initFile = null;
		Scanner goalFile = null;
		
		if (args.length<2 || args.length>2){
			System.out.println("Invalid init and/or goal file.");
			System.exit(0);
		}

		try {
			File a = new File(args[0]);
			File b = new File(args[1]);
			initFile = new Scanner(a);
			goalFile = new Scanner(b);
		} catch (FileNotFoundException e) {
			System.out.println("Invalid init and/or goal file.");
		}

		Integer height = initFile.nextInt();
		Integer width = initFile.nextInt();

		Board start = new Board(height, width);

		while (initFile.hasNextInt()) {
			try{
			start.placeBlock(new Block(initFile.nextInt(), initFile.nextInt(),
					initFile.nextInt(), initFile.nextInt()));
			}catch(Exception e){
				System.out.println("Invalid init and/or goal file.");
			}
		}
	

		Board end = new Board(height, width);

		while (goalFile.hasNextInt()) {
			try{
			end.placeBlock(new Block(goalFile.nextInt(), goalFile.nextInt(),
					goalFile.nextInt(), goalFile.nextInt()));
			}catch(Exception e){
				System.out.println("Invalid init and/or goal file.");
			}
		}

		s.end = end;
		s.start = start;

		for (Block g : end.blocks) {
			Solver.goalBlocks.add(g);
			
		}
		
		s.open.add(start);
		s.pathSearch(s.start);

	}

}