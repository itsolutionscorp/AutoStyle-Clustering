import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Solver {

	private ArrayList<String> piecePositionsInGoalConfiguration;
	private HashSet<BoardConfiguration> visited;

	public void solve(BoardConfiguration init) {
		visited = new HashSet<BoardConfiguration>();
		PriorityQueue<BoardConfiguration> fringe = new PriorityQueue<BoardConfiguration>(
				256 * 256, new BoardComparator(
						piecePositionsInGoalConfiguration));
		boolean win = true;
		if ((init.checkConfiguration(piecePositionsInGoalConfiguration) != piecePositionsInGoalConfiguration
				.size())) {
			win = false;
		}
		if (win == true) {
			init.printPath();
		} else {
			visited.add(init);
			fringe.offer(init);
		}
		while (!fringe.isEmpty()) {
			BoardConfiguration front = fringe.poll();
			HashSet<String> possibleMoves = front.possibleMoves();
			for (String move : possibleMoves) {
				ArrayList<Integer> temp = lineParser(move);
				BoardConfiguration newConfig = front.move(new Point(
						temp.get(0), temp.get(1)),
						new Point(temp.get(2), temp.get(3)));
				if (!visited.contains(newConfig)) {
					newConfig.setMyParent(front);
					newConfig.setMove(move);
					win = true;
					
					if ((newConfig.checkConfiguration(piecePositionsInGoalConfiguration) != piecePositionsInGoalConfiguration
							.size())) {
						win = false;
					}
					
					if (win == true) {
						newConfig.printPath();
						break;
					}
					fringe.offer(newConfig);
					visited.add(newConfig);
				}
			}
			if (win == true) {
				break;
			}
		}
	}

	public static ArrayList<Integer> lineParser(String s) {
		ArrayList<Integer> rtn = new ArrayList<Integer>();
		int index = 0;
		String currentString = "";
		while (index < s.length()) {
			char current = s.charAt(index);
			if (current != ' ') {
				if (Character.isDigit(current)) {
					currentString = currentString + current;
				}
			} else {
				rtn.add(Integer.parseInt(currentString));
				currentString = "";
			}
			index++;
		}
		if (currentString != "") {
			rtn.add(Integer.parseInt(currentString));
		}
		return rtn;
	}

	public void setGoal(File f, int x, int y) throws IllegalArgumentException {
		try {
			this.piecePositionsInGoalConfiguration = setGoalHelper(f, x, y);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("file not formatted correctly");
		}
	}

	public static ArrayList<String> setGoalHelper(File f, int x, int y)
			throws IllegalArgumentException {
		try {
			BoardConfiguration test = new BoardConfiguration(x, y);
			BufferedReader b = new BufferedReader(new FileReader(f));
			ArrayList<String> rtn = new ArrayList<String>();
			while (b.ready()) {
				String temp = b.readLine();
				String[] result = temp.split(" ");
				if (result.length != 4) {
					b.close();
					throw new IllegalArgumentException(
							"pieces are not formatted correctly.");
				}
				temp = new String();
				for (int i = 0; i < result.length; i++) {
					int current = Integer.parseInt(result[i]);
					if (current < 0) {
						b.close();
						throw new IllegalArgumentException(
								"pieces are not formatted correctly.");
					}
					if (i % 2 == 0 && current >= x) {
						b.close();
						throw new IllegalArgumentException(
								"pieces are not formatted correctly.");
					}
					if (i % 2 != 0 && current >= y) {
						b.close();
						throw new IllegalArgumentException(
								"pieces are not formatted correctly.");
					}
					if (i == result.length - 1) {
						temp += result[i];
					} else {
						temp += result[i] + " ";
					}
				}
				ArrayList<Integer> i = lineParser(temp);
				test.addPiece(new Point(i.get(0), i.get(1)), new Point(
						i.get(2), i.get(3)));
				rtn.add(temp);
			}
			b.close();
			return rtn;
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(
					"file contains letters/characters");
		} catch (IllegalArgumentException d) {
			throw new IllegalArgumentException("numbers are not on board");

		} catch (IOException e) {
			throw new IllegalArgumentException("file cannot be parsed");
		}
	}

	private class BoardComparator implements Comparator<BoardConfiguration> {
		ArrayList<String> moves;

		public BoardComparator(ArrayList<String> e) {
			moves = e;
		}

		public int compare(BoardConfiguration b, BoardConfiguration c) {
			if (b.checkConfiguration(moves) > c.checkConfiguration(moves)) {
				return 1;
			} else if (b.checkConfiguration(moves) < c
					.checkConfiguration(moves)) {
				return -1;
			} else {
				return 0;
			}
		}

	}

	public static void main(String[] args) {
		//
		// System.out.println("running the program now");
		// Solver s = new Solver();
		// File init = new File("slidingblocks/easy/140x140");
		// File goal = new File("slidingblocks/easy/140x140.goal");
		// BoardConfiguration start = BoardConfiguration.parseFile(init);
		// s.setGoal(goal);
		// s.solve(start);

		Solver s = new Solver();
		if (args.length != 2) {
			System.out.println("Invalid init and/or goal file.");
		} else {
			File init = new File(args[0]);
			File goal = new File(args[1]);
			if (!init.exists() || !goal.exists()) {
				System.out.println("Invalid init and/or goal file.");
			} else {
				try {
					BoardConfiguration initial = BoardConfiguration
							.parseFile(init);
					s.setGoal(goal, initial.getDimensions().x,
							initial.getDimensions().y);
					s.solve(initial);
				} catch (IllegalArgumentException e) {
					System.out.println("Invalid init and/or goal file.");
				}
			}
		}
	}
}

