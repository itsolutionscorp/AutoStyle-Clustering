import java.util.*;

import java.io.*;

public class Solver {

	static HashMap<Board, Board> boards;

	static Board initBoard;

	static Board goalBoard;

	public static void main(String[] args) {
		boards = new HashMap<Board, Board>();
		if (args.length != 2) {
			System.out.println("Invalid init and/or goal file.");
		} else {
			String initFileName = args[0];
			String goalFileName = args[1];
			// Initialize Solver here
			Solver mySolver = new Solver();
			// Call Solver with init and goal
			String line = null;
			boolean firstLine = true;

			try {
				// FileReader reads text files in the default encoding.
				FileReader fileReader = new FileReader(initFileName);

				// Always wrap FileReader in BufferedReader.
				BufferedReader bufferedReader = new BufferedReader(fileReader);

				while ((line = bufferedReader.readLine()) != null) {
					String[] aLine = line.split(" ");
					
					if (firstLine) {
						if (aLine.length!=2) {
							throw new Exception("1");
						}
						initBoard = new Board(Integer.parseInt(aLine[0]), Integer.parseInt(aLine[1]));

						goalBoard = new Board(Integer.parseInt(aLine[0]), Integer.parseInt(aLine[1]));
						firstLine = false;
					} else {
						if (aLine.length!=4) {
							throw new Exception("1");
						}
						initBoard.addPiece(Integer.parseInt(aLine[0]), Integer.parseInt(aLine[1]),
								Integer.parseInt(aLine[2]), Integer.parseInt(aLine[3]));

					}
				}
				// initBoard.setPriority(goalBoard);
				mySolver.boards.put(initBoard, null);
				fileReader = new FileReader(goalFileName);
				bufferedReader = new BufferedReader(fileReader);
				while ((line = bufferedReader.readLine()) != null) {
					String[] aLine = line.split(" ");
					if (aLine.length!=4) {
						throw new Exception("1");
					}
					goalBoard.addPiece(Integer.parseInt(aLine[0]), Integer.parseInt(aLine[1]),
							Integer.parseInt(aLine[2]), Integer.parseInt(aLine[3]));

				}

				// Always close files.
				bufferedReader.close();
				// System.out.println(initBoard);
				// System.out.println(goalBoard);
				if (initBoard.isGoal(goalBoard)) {
					// System.out.println("here");
					return;
				}
				// Board finalB = mySolver.makeGraph();
				// Board finalB = mySolver.makeGraphStack();
				double takenUp = 0;
				for (Board.Piece p : initBoard.myPieces) {
					takenUp += p.myWidth * p.myLength;
				}
				Board finalB;
				if (takenUp / (double) (initBoard.height * initBoard.width) > .95) {
					initBoard.full = true;
					finalB = mySolver.makeGraph();
				} else {

					finalB = mySolver.makeGraphPriority();
				}

				//System.out.println(finalB);
				if (finalB != null) {
					mySolver.solve(finalB);
				}

			} catch (Exception ex) {
				System.out.println("Invalid init and/or goal file.");
			}

		}
	}

	private void solve(Board finalB) {
		Stack<Board> path = new Stack<Board>();
		Board curr = finalB;
		while (curr != null) {
			// System.out.println(curr);
			path.push(curr);
			curr = boards.get(curr);
		}

		path.pop();
		Board next;
		while (!path.isEmpty()) {
			next = path.pop();
			System.out.println(next.myMove);
		}

	}

	private Board makeGraphPriority() throws IOException {
		// TODO Auto-generated method stub
		PriorityQueue<Board> q = new PriorityQueue<Board>();
		for (Board move : initBoard.possibleMoves()) {
			move.setPriority(goalBoard, initBoard);
			boards.put(move, initBoard);
			q.offer(move);
			if (move.isGoal(goalBoard)) { // if one of initBoard's children is
											// goal
				return move;
			}

		}
		while (!q.isEmpty()) {
			Board curr = q.poll();
			for (Board move : curr.possibleMoves()) {
				move.setPriority(goalBoard, initBoard);
				if (!boards.containsKey(move)) {
					q.offer(move);
					if (!boards.get(curr).equals(move)) {
						boards.put(move, curr);
						if (move.isGoal(goalBoard)) {
							return move;
						}
					}
				}
			}
		}
		return null;
	}

	private Board makeGraph() throws IOException {
		// TODO Auto-generated method stub
		Queue<Board> q = new LinkedList<Board>();
		//PriorityQueue<Board> q = new PriorityQueue<Board>();
		for (Board move : initBoard.possibleMoves()) {
			//move.setPriority(goalBoard, initBoard);
			move.full = true;
			boards.put(move, initBoard);
			q.offer(move);
			if (move.isGoal(goalBoard)) { // if one of initBoard's children is
											// goal
				return move;
			}

		}
		while (!q.isEmpty()) {
			Board curr = q.poll();
			for (Board move : curr.possibleMoves()) {
				//move.setPriority(goalBoard, initBoard);
				move.full = true;
				if (!boards.containsKey(move)) {

					q.offer(move);
					if (!boards.get(curr).equals(move)) {
						boards.put(move, curr);
						if (move.isGoal(goalBoard)) {
							return move;
						}
					}
				}
			}
		}
		//System.out.println("I FOUND NOTHING");
		return null;
	}

	// private Board makeGraphStack() throws IOException {
	// Stack<Board> q = new Stack<Board>();
	// for (Board move : initBoard.possibleMoves()) {
	// boards.put(move, initBoard);
	// q.push(move);
	// if (move.isGoal(goalBoard)) {
	// return move;
	// }
	// }
	// while (!q.isEmpty()) {
	// Board curr = q.pop();
	// for (Board move : curr.possibleMoves()) {
	// if (!boards.containsKey(move)) {
	// q.push(move);
	// if (!boards.get(curr).equals(move)) {
	// boards.put(move, curr);
	// if (move.isGoal(goalBoard)) {
	// return move;
	// }
	// }
	// }
	// }
	// }
	// return null;
	// }
}
