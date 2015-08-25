import java.awt.Point;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class Solver {
	private HashSet<TrayConfig> alreadyConsidered;
	private TrayConfig initial;
	public static TrayConfig goal;
	private PriorityQueue<TrayConfig> toConsider; 
	public static int width;
	public static int height;

	public Solver(String s1, String s2) {
		toConsider = new PriorityQueue<TrayConfig>();
		alreadyConsidered = new HashSet<TrayConfig>();

		List<String> lines = new ArrayList<String>();
		try {
			lines = Files.readAllLines(Paths.get(s1));
		} catch (IOException e) {
			System.out.println("Invalid init and/or goal file.");
			System.exit(0);
		}

		String[] widthHeight = lines.get(0).split(" ");
		if (widthHeight.length != 2){
			System.out.println("Invalid init and/or goal file.");
			System.exit(0);
		}
		try {
			Solver.width = Integer.parseInt(widthHeight[1]);
			Solver.height = Integer.parseInt(widthHeight[0]);
		}catch (NumberFormatException e){
			System.out.println("Invalid init and/or goal file.");
			System.exit(0);
		}catch (NullPointerException e){
			System.out.println("Invalid init and/or goal file.");
			System.exit(0);
		}
		
		HashSet<Block> initialBlocks = new HashSet<Block>();

		for (int i = 0; i < lines.size() - 1; i++) {
			int topLeft[] = new int[2];
			int bottomRight[] = new int[2];
			String line = lines.get(i + 1);
			String[] lineArr = line.split(" ");
			if (lineArr.length != 4){
				System.out.println("Invalid init and/or goal file.");
				System.exit(0);
			}
			
			try {
				topLeft[0] = Integer.parseInt(lineArr[0]);
				topLeft[1] = Integer.parseInt(lineArr[1]);
				bottomRight[0] = Integer.parseInt(lineArr[2]);
				bottomRight[1] = Integer.parseInt(lineArr[3]);
			}catch (NumberFormatException e){
				System.out.println("Invalid init and/or goal file.");
				System.exit(0);
			}catch (NullPointerException e){
				System.out.println("Invalid init and/or goal file.");
				System.exit(0);
			}
			
			Block newBlock = new Block(topLeft, bottomRight);
			initialBlocks.add(newBlock);
		}

		HashSet<Point> initialEmptySpaces = new HashSet<Point>();

		for (int i = 0; i < Solver.height; i++) {
			for (int j = 0; j < Solver.width; j++) {
				initialEmptySpaces.add(new Point(i,j));
			}
		}

		for (Block b : initialBlocks) {
			for (int i = b.topLeft[0]; i < b.topLeft[0] + b.height; i++) {
				for (int j = b.topLeft[1]; j < b.topLeft[1] + b.width; j++) {
					initialEmptySpaces.remove(new Point(i, j));
				}
			}
		}

		try {
			lines = Files.readAllLines(Paths.get(s2));
		} catch (IOException e) {
			System.out.println("Invalid init and/or goal file.");
			System.exit(0);
		}
		HashSet<Block> goalBlocks = new HashSet<Block>();

		for (int i = 0; i < lines.size(); i++) {
			int[] topLeft = new int[2];
			int[] bottomRight = new int[2];
			String line = lines.get(i);
			String[] lineArr = line.split(" ");
			
			if (lineArr.length != 4){
				System.out.println("Invalid init and/or goal file.");
				System.exit(0);
			}
			try {
				topLeft[0] = Integer.parseInt(lineArr[0]);
				topLeft[1] = Integer.parseInt(lineArr[1]);
				bottomRight[0] = Integer.parseInt(lineArr[2]);
				bottomRight[1] = Integer.parseInt(lineArr[3]);
			}catch (NumberFormatException e){
				System.out.println("Invalid init and/or goal file.");
				System.exit(0);
			}catch (NullPointerException e){
				System.out.println("Invalid init and/or goal file.");
				System.exit(0);
			}

			Block goalBlock = new Block(topLeft, bottomRight);
			goalBlocks.add(goalBlock);
		}

		HashSet<Point> goalEmptySpaces = new HashSet<Point>();

		for (int i = 0; i < Solver.height; i++) {
			for (int j = 0; j < Solver.width; j++) {
				goalEmptySpaces.add(new Point(i,j));
			}
		}

		for (Block b : goalBlocks) {
			for (int i = b.topLeft[0]; i < b.topLeft[0] + b.height; i++) {
				for (int j = b.topLeft[1]; j < b.topLeft[1] + b.width; j++) {
					goalEmptySpaces.remove(new Point(i,j));
				}
			}
		}
		goal = new TrayConfig(goalBlocks, goalEmptySpaces, 0, null, null);
		initial = new TrayConfig(initialBlocks, initialEmptySpaces, null,
				null);
	}

	public void consider(TrayConfig tc) {
		while (!tc.equals(goal)) {
			alreadyConsidered.add(tc);
			for (TrayConfig tc2 : tc.possibleMoves()) {
				if (! alreadyConsidered.contains(tc2) && ! toConsider.contains(tc2)) {
					toConsider.offer(tc2);
				}	
			}
			if (toConsider.isEmpty()) {
				System.exit(0);
			}
			tc = toConsider.poll();
		}
		printSolution(tc);

	}

	public void printSolution(TrayConfig tc) {
		Stack<int[]> reverser = new Stack<int[]>();
		reverser.push(tc.moveMade);
		while (tc.parent != null) {
			reverser.push(tc.parent.moveMade);
			tc = tc.parent;
		}
		int[] toPrint;
		reverser.pop();
		while (!reverser.isEmpty()) {
			toPrint = reverser.pop();
			System.out.println(toPrint[0] + " " + toPrint[1] + " " + toPrint[2]
					+ " " + toPrint[3]);
		}
		System.exit(0);
	}

	public static void main(String[] args) {
		// assuming args are in correct format for now
		if (args.length != 2){
			System.out.println("Invalid init and/or goal file.");
			System.exit(0);
		}else {
			Solver solver = new Solver(args[0], args[1]);
			solver.consider(solver.initial);
		}
	}
}
