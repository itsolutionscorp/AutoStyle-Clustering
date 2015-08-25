import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

public class Solver {

	PriorityQueue<Board> pq;

	public Solver(String initialFileName, String goalFileName) throws FileNotFoundException {

		File initialFile = new File(initialFileName);
		Scanner sc = new Scanner(initialFile);
		Scanner lineSc = new Scanner(sc.nextLine());
		int lineCountInitialFile = 0, lineCountGoalFile = 0;

		short maxX = lineSc.nextShort();
		short maxY = lineSc.nextShort();
		if (lineSc.hasNext())
			throw new InputMismatchException();

		HashSet<Block> initBlocks = new HashSet<Block>();
		boolean[][] initOccupied = new boolean[maxX][maxY];

		while (sc.hasNextLine()) {
			lineCountInitialFile++;
			lineSc = new Scanner(sc.nextLine());
			int x1 = lineSc.nextInt();
			int y1 = lineSc.nextInt();
			int x2 = lineSc.nextInt();
			int y2 = lineSc.nextInt();

			if (x1 < 0 || x1 >= maxX || x2 < 0 || x2 >= maxX || y1 < 0 || y1 >= maxY || y2 < 0 || y2 >= maxY || x1 > x1
					|| y1 > y2)
				throw new InputMismatchException();
			if (lineSc.hasNext())
				throw new InputMismatchException();

			initBlocks.add(new Block(x1, y1, x2, y2));
			for (int i = x1; i <= x2; i++) {
				for (int j = y1; j <= y2; j++) {
					if(initOccupied[i][j])
						throw new InputMismatchException();
					initOccupied[i][j] = true;
				}
			}
		}
		lineSc.close();
		sc.close();

		File goalFile = new File(goalFileName);
		sc = new Scanner(goalFile);
		HashSet<Block> goalBlocks = new HashSet<Block>();

		while (sc.hasNextLine()) {
			lineCountGoalFile++;
			lineSc = new Scanner(sc.nextLine());
			int x1 = lineSc.nextInt();
			int y1 = lineSc.nextInt();
			int x2 = lineSc.nextInt();
			int y2 = lineSc.nextInt();

			if (x1 < 0 || x1 >= maxX || x2 < 0 || x2 >= maxX || y1 < 0 || y1 >= maxY || y2 < 0 || y2 >= maxY || x1 > x1
					|| y1 > y2)
				throw new InputMismatchException();
			if (lineSc.hasNext())
				throw new InputMismatchException();

			goalBlocks.add(new Block(x1, y1, x2, y2));
		}
		lineSc.close();
		sc.close();
		
		if (lineCountGoalFile > lineCountInitialFile)
			throw new InputMismatchException();
		
		HashMap<Integer, HashSet<Block>> initMap = new HashMap<Integer, HashSet<Block>>();
		for (Block b : initBlocks) {
			int key = (b.height << 16) + b.width;
			HashSet<Block> hs = initMap.get(key);
			if (hs == null) {
				hs = new HashSet<Block>();
				hs.add(b);
				initMap.put(key, hs);
			}
			hs.add(b);
		}
		
		HashMap<Integer, HashSet<Block>> goalMap = new HashMap<Integer, HashSet<Block>>();
		for (Block b : goalBlocks) {
			int key = (b.height << 16) + b.width;
			HashSet<Block> hs = goalMap.get(key);
			if (hs == null) {
				hs = new HashSet<Block>();
				hs.add(b);
				goalMap.put(key, hs);
			}
			hs.add(b);
		}
		
		for(Integer hs : goalMap.keySet()) {
			if(goalMap.get(hs).size() > initMap.get(hs).size())
				throw new InputMismatchException();
		}
		
		Board.setMaxXY(maxX, maxY);
		Board.setGoal(goalBlocks, goalMap);
		pq = new PriorityQueue<Board>();
		pq.offer(new Board(initBlocks, initOccupied));
	}
	
	public boolean hasNext() {
		return !pq.isEmpty();
	}
	
	public Board next(){
		Board ret = pq.poll();
		ret.getChildren();
		for(Board brd : ret.children) {
			pq.offer(brd);
		}
		return ret;
	}
	
	public void solve() {
		Board brd = null;
		while (hasNext()) {
			brd = next();
			if(brd.isGoal())
				break;
		}
		
		if(!brd.isGoal())
			return;
		
		Stack<Board> moveHistory = new Stack<Board>();
		while (brd != null) {
			moveHistory.push(brd);
			brd = brd.parent;
		}
		
		moveHistory.pop();
		while (!moveHistory.isEmpty()) {
			Board b = moveHistory.pop(); 
			System.out.println(b.lastMove);
		}
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Invalid init and/or goal file.");
			System.exit(1);
		}
	
		try {
			Solver mySolver = new Solver(args[0], args[1]);
			mySolver.solve();
		} catch (Exception e) {
			System.err.println("Invalid init and/or goal file.");
			System.exit(1);
		}
	}
}
