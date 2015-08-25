import java.io.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Solver {

	/**
	 * @param args
	 */
	private Board myBoard;
	private ArrayList<int[]> myTarget;

	public Solver(Board b, ArrayList<int[]> target) {
		myBoard = b;
		myTarget = target;
	}

	public Solver() {

	}

	public Board board() {
		return myBoard;
	}

	public boolean getToTarget(Board current, ArrayList<int[]> target) {
		boolean result = true;
		for (int[] tar : target) {
			Piece p = current.get(tar[0], tar[1]);
			if (p == null)
				return false;
			if (!((p.getX() == tar[0] && p.getY() == tar[1]
					&& p.breadth() == tar[2] - tar[0] + 1 && p.length() == tar[3]
					- tar[1] + 1))) {
				result = false;
				break;
			}
		}
		return result;
	}

	public void printPath() {// int[4]
		Board last = new Board();
		boolean found = false;
		if (myBoard.pieces().size() > 30) {
			Board init = myBoard;
			ArrayList<int[]> target = myTarget;
			Set<Integer> seen = new HashSet<>();
			Stack<Board> fringe = new Stack<>();
			fringe.push(init);
			while (!fringe.isEmpty()) {
				Board b = fringe.pop();
				if (getToTarget(b, target)) {
					return;
				}
				seen.add(b.hashCode());
				for (Board next : b.mynextBoards()) {
					if (getToTarget(next, target)) {
						found = true;
						last = next;
						break;
					}
					if (!seen.contains(next.hashCode())) {
						fringe.push(next);
						seen.add(next.hashCode());
					}
				}
				if (found == true) {
					break;
				}
			}
		} else {
			Board init = myBoard;
			ArrayList<int[]> target = myTarget;
			Set<Board> seen = new HashSet<>();
			Stack<Board> fringe = new Stack<>();
			fringe.push(init);
			while (!fringe.isEmpty()) {
				Board b = fringe.pop();
				if (getToTarget(b, target)) {
					return;
				}
				seen.add(b);
				for (Board next : b.mynextBoards()) {
					if (getToTarget(next, target)) {
						found = true;
						last = next;
						break;
					}
					if (!seen.contains(next)) {
						fringe.push(next);
						seen.add(next);
					}
				}
				if (found == true) {
					break;
				}
			}
		}
		if (!found) {
			return;
		}
		Stack<int[]> reversePath = new Stack<>();
		while (last.myPrevPath() != null) {
			reversePath.push(last.myPrevPath());
			last = last.myPrev();
		}
		int[] result = new int[4];
		while (!reversePath.isEmpty()) {
			result = reversePath.pop();
			System.out.println(result[0] + " " + result[1] + " " + result[2]
					+ " " + result[3]);
		}
	}

	public void FileReader(String[] FileInfo, ArrayList<int[]> Block,
			int[] size, ArrayList<int[]> Goal) {
		File InitFile = new File(FileInfo[0]);
		File GoalFile = new File(FileInfo[1]);
		BufferedReader reader = null;
		int StringIndex;
		int numberIndex;
		try {
			reader = new BufferedReader(new FileReader(InitFile));
			String tempString = null;
			if ((tempString = reader.readLine()) != null) {
				numberIndex = 0;
				for (StringIndex = 0; StringIndex < tempString.length(); StringIndex++) {
					if (tempString.charAt(StringIndex) == ' ') {
						numberIndex++;
					}
					if (tempString.charAt(StringIndex) >= 48
							&& tempString.charAt(StringIndex) <= 57)
						size[numberIndex] = size[numberIndex] * 10
								+ (tempString.charAt(StringIndex) - 48);
				}
			}
			while ((tempString = reader.readLine()) != null) {
				int[] number = new int[4];
				numberIndex = 0;
				for (StringIndex = 0; StringIndex < tempString.length(); StringIndex++) {
					if (tempString.charAt(StringIndex) == ' ') {
						numberIndex++;
					}
					if (tempString.charAt(StringIndex) >= 48
							&& tempString.charAt(StringIndex) <= 57)
						number[numberIndex] = number[numberIndex] * 10
								+ (tempString.charAt(StringIndex) - 48);
				}
				Block.add(number);

			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

		try {
			reader = new BufferedReader(new FileReader(GoalFile));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				int[] number = new int[4];
				numberIndex = 0;
				for (StringIndex = 0; StringIndex < tempString.length(); StringIndex++) {
					if (tempString.charAt(StringIndex) == ' ') {
						numberIndex++;
					}
					if (tempString.charAt(StringIndex) >= 48
							&& tempString.charAt(StringIndex) <= 57)
						number[numberIndex] = number[numberIndex] * 10
								+ (tempString.charAt(StringIndex) - 48);
				}
				Goal.add(number);

			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<int[]> Block = new ArrayList<int[]>();
		int[] size = new int[2];
		ArrayList<int[]> Goal = new ArrayList<int[]>();
		Solver s = new Solver();
		s.FileReader(args, Block, size, Goal);
		Board b = new Board(size, Block, Goal);
		s = new Solver(b, Goal);
		s.printPath();
	}

}
