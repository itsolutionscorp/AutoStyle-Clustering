import java.util.HashSet;
import java.util.Stack;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solver {

	private Board board;
	private Board goalBoard;
	private int height;
	private int width;
	private HashSet<Board> alreadySeen;
	private ArrayHeap<Board> fringe;
	
	public Solver() {
		alreadySeen = new HashSet<Board>();
		fringe = new ArrayHeap<Board>();
	}

	public Board getBoard() {
		return board; 
	}
	
	public ArrayHeap<Board> getFringe() {
		return fringe;
	}
	
	public boolean win(Board b) {
		for (Block block: goalBoard.getAllBlocks()) {
			if (!b.getAllBlocks().contains(block)) {
				return false;
			}
		}
		return true;
	}
	
	public void initialEmpty() {
//		for (ShortPoint p: board.getTray().keySet()) {
//			System.out.println(p);
//		}
		for (int i = 0; i < board.getHeight(); i++) {
			for (int j = 0; j < board.getWidth(); j++) {
				ShortPoint p = new ShortPoint(j, i);
				if (board.getTray().get(p) == null) {
					board.getEmptySet().add(p);
					
				}
			}
		}
	}
	
	//reads input init file and ensures it follows guidelines 
	private boolean readInit(File fin) {
		try {
			FileInputStream fis = new FileInputStream(fin);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String line = br.readLine();
			int lineCount = 1;
			String[] change;
			board = new Board(0,0);
			short topLeftY;
			short topLeftX;
			short botRightY;
			short botRightX;
			ShortPoint topLeft = null;
			ShortPoint botRight = null;
			while (line != null) {
				change = line.split(" ");
				if (lineCount == 1) {
					if (change.length != 2) {
						br.close();
						return false;
					} else {
						short h;
						short w;
						try {
							h = Short.parseShort(change[0]);
							w = Short.parseShort(change[1]);
							if (h > 256 || w > 256 || h < 0 || w < 0) {
								br.close();
								return false;
							}
						} catch (Exception e) {
							br.close();
							return false;
						}
						board = new Board(h, w);
						height = board.getHeight();
						width = board.getWidth();
					}
				} else if (change.length == 4) {
					try {
						topLeftY = Short.parseShort(change[0]);
						topLeftX = Short.parseShort(change[1]);
						botRightY = Short.parseShort(change[2]);
						botRightX = Short.parseShort(change[3]);
						if (topLeftX > botRightX || topLeftY > botRightY) {
							br.close();
							return false;
						}
						topLeft = new ShortPoint(topLeftX, topLeftY);
						botRight = new ShortPoint(botRightX, botRightY);
						Block b = new Block(topLeft, botRight);
						if (board.validInsert(b)) {
							board.insertBlock(b);
						} else {
							br.close();
							return false;
						}
					} catch (Exception e) {
						br.close();
						return false;
					}
				} else {
					br.close();
					return false;
				}
				lineCount++;
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
		if (board.getAllBlocks().isEmpty()) {
			return false;
		}
		initialEmpty();
		return true;
	}
	
	private boolean readGoal(File fin) {
		try {
			FileInputStream fis = new FileInputStream(fin);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String line = br.readLine();
			String change[];
			goalBoard = new Board(height, width);
			short topLeftX;
			short topLeftY;
			short botRightX;
			short botRightY;
			ShortPoint topLeft = null;
			ShortPoint botRight = null;
			while (line != null) {
				change = line.split(" ");
				if (change.length == 4) {
					try {
						topLeftY = Short.parseShort(change[0]);
						topLeftX = Short.parseShort(change[1]);
						botRightY = Short.parseShort(change[2]);
						botRightX = Short.parseShort(change[3]);
						topLeft = new ShortPoint(topLeftX, topLeftY);
						botRight = new ShortPoint(botRightX, botRightY);
						Block b = new Block(topLeft, botRight);
						if (goalBoard.validInsert(b)) {
							goalBoard.insertBlock(b);
						} else {
							br.close();
							return false;
						}
					} catch (Exception e) {
						br.close();
						return false;
					}
				} else {
					br.close();
					return false;
				}
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
		if (goalBoard.getAllBlocks().isEmpty()) {
			return false;
		}
		return true;
	}
	
	/**
	 * LOOP WHILE POPPING OF FRINGE:    //fringe uses BFS (queue): more effective that DFS
	 * 	0. Pop off fringe and add to alreadySeen
	 * 	1. look for empty spaces
	 * 		a. if none: return failure
	 * 	2. for each empty space: search if valid move for a block
	 * 		a. if valid: create a new board with new block location
	 * 			i. check to see if alreadySeen
	 * 			ii. add to history
	 * 			iii. if not alreadySeen: add to fringe
	 * 			iv. fringe uses priority (Manhattan Distance heuristic): 
	 * 					calculate distance of goal blocks to end goal, shorter = "more" priority
	 * 					may need to create another node class??? 
	 * 		b. check to see if win()/.equals(endGoal) ==> victory procedure
	 * 	3. If fringe is empty: return impossible puzzle
	 */
	public void solve() {
		fringe.insert(board, heuristic(board));
		board.findAdjacent();
		alreadySeen.add(board);
		while (!fringe.isEmpty()) {
			//ArrayHeap.Node n = fringe.removeMin();
			Board b = (Board) fringe.removeMin().item();
		
			if (win(b)) {
				for (String s: b.getHistory()) {
					System.out.println(s);
				}
				return;
			}

			//look for empty spaces and add possible moves to fringe
			//findEmpty(b);
//			for (ShortPoint p: b.getEmptySet()) {
//				System.out.println(p);
//			}
			
			for (Block bl: b.getAdjacentEmpty()) {
				findMoves(bl, b);
			}
		}
		//went through entire fringe and no solution found
	}
	
	/**
	 * Iterates through all Blocks in given Board.
	 * If the Block can shift left,right,up,down, add the
	 * new Board to the fringe.
	 * @param b
	 */
	public void findMoves(Board b) {
		ShortPoint p;
		Board leftBoard;
		Board rightBoard;
		Board upBoard;
		Board downBoard;
		for (Block block: b.getAllBlocks()) {			
			p = block.getTopLeft();
			leftBoard = new Board(b);
			rightBoard = new Board(b);
			upBoard = new Board(b);
			downBoard = new Board(b);
			if (leftBoard.validAndChange(p, "left")) {
				if (!alreadySeen.contains(leftBoard)) {
					alreadySeen.add(leftBoard);
					fringe.insert(leftBoard, heuristic(leftBoard));
				}
			}
			if (rightBoard.validAndChange(p, "right")) {
				if (!alreadySeen.contains(rightBoard)) {
					alreadySeen.add(rightBoard);
					fringe.insert(rightBoard, heuristic(rightBoard));
				}
			}	
			if (upBoard.validAndChange(p, "up")) {
				if (!alreadySeen.contains(upBoard)) {
					alreadySeen.add(upBoard);
					fringe.insert(upBoard, heuristic(upBoard));
				}
			}
			if (downBoard.validAndChange(p, "down")) {
				if (!alreadySeen.contains(downBoard)) {
					alreadySeen.add(downBoard);
					fringe.insert(downBoard, heuristic(downBoard));
				}
			}
		}
	}
	
	public void findMoves(Block block, Board board) {
		ShortPoint p = block.getTopLeft();
		Board leftBoard = new Board(board);
		Board rightBoard = new Board(board);
		Board upBoard = new Board(board);
		Board downBoard = new Board(board);
		if (leftBoard.validAndChange(p, "left")) {
			if (!alreadySeen.contains(leftBoard)) {
				alreadySeen.add(leftBoard);
				leftBoard.findAdjacent();
				fringe.insert(leftBoard, heuristic(leftBoard));
			}
		}
		if (rightBoard.validAndChange(p, "right")) {
			if (!alreadySeen.contains(rightBoard)) {
				alreadySeen.add(rightBoard);
				rightBoard.findAdjacent();
				fringe.insert(rightBoard, heuristic(rightBoard));
			}
		}	
		if (upBoard.validAndChange(p, "up")) {
			if (!alreadySeen.contains(upBoard)) {
				alreadySeen.add(upBoard);
				upBoard.findAdjacent();
				fringe.insert(upBoard, heuristic(upBoard));
			}
		}
		if (downBoard.validAndChange(p, "down")) {
			if (!alreadySeen.contains(downBoard)) {
				alreadySeen.add(downBoard);
				downBoard.findAdjacent();
				fringe.insert(downBoard, heuristic(downBoard));
			}
		}
	}
	
	public short heuristic(Board b) {
		short h = 0;
		short lowestSeen;
		ShortPoint tl2;
		ShortPoint tl1;
		int distance = 0;
		for (Block block: goalBoard.getAllBlocks()) {
			lowestSeen = Short.MAX_VALUE;
			tl2 = block.getTopLeft();
			for (Block b1 : b.getAllBlocks()) {
				if (b1.getHeight() == block.getHeight() && b1.getWidth() == block.getWidth()) {
					tl1 = b1.getTopLeft();
					//int distance = Math.abs(tl1.getX() - tl2.getX()) + Math.abs(tl1.getY() - tl2.getY());
					distance = Math.abs(((tl1.getX() - tl2.getX()) + (tl1.getY() - tl2.getY())));
					if (distance < lowestSeen) {
						lowestSeen = (short) distance;
					}
				}
			}
			h += lowestSeen;
		}
		return h;
	}
	
	public void setBoard(Board b) {
		board =  b;
	}
	
	public void setGoal(Board b) {
		goalBoard = b;
	}
	
	public double setRatio() {
		double empty = board.getEmptySet().size();
		double total = height * width;
		return empty/total;
	}
	
	public static void main(String[] args) {
		try {
			//long startTime = System.nanoTime();
			// Base case for whether there are any arguments
			if (args.length != 2) {
				System.out.println("Invalid init and/or goal file.");
				return;
			} else if (args[0] == null || args[1] == null
					|| args[0].trim().isEmpty() || args[1].trim().isEmpty()) {
				System.out.println("Invalid init and/or goal file.");
				return;
			}
			File init = new File(args[0]);
			File goal = new File(args[1]);
			Solver solv = new Solver();
			// check to see that the two args are files
			if (init.exists() && goal.exists()) {
				// Ensures valid arguments are used
				if (!(solv.readInit(init) && solv.readGoal(goal))) {
					System.out.println("Invalid init and/or goal file.");
				} else {
					//if (solv.getBoard().getAllBlocks().size() < 130) {
						solv.solve();
					//} else {
					//	solv.solveDepth();
					//}
				}
			}
			//long stopTime = System.nanoTime();
			//System.out.println((double) (stopTime - startTime)/(double) 1000000000.0);
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
}
