import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Solver {
	private Tray initial;
	private int trayHeight;
	private int trayWidth;
	private boolean[][] goalTrayOccupied;
	private ArrayList<Block> goalTrayBlocks;
	private HashSet<Tray> visited;

	public Solver(String[] args) {
		if (args.length != 2) {
			System.out.println("Invalid init and/or goal file.");
			return;
		} else {
			Scanner initFileContents;
			Scanner finalFileContents;
			try {
				File initConfigFile = new File(args[0]);
				File finalConfigFile = new File(args[1]);
				initFileContents = new Scanner(initConfigFile);
				finalFileContents = new Scanner(finalConfigFile);
				trayHeight = initFileContents.nextInt();
				trayWidth = initFileContents.nextInt();
				initial = new Tray(trayHeight, trayWidth);
				goalTrayBlocks = new ArrayList<Block>();
				while (initFileContents.hasNextInt()) {
					initial.addBlock(new Block(initFileContents.nextInt(),
							initFileContents.nextInt(), initFileContents
									.nextInt(), initFileContents.nextInt()));
				}
				initFileContents.close();
				goalTrayOccupied = new boolean[trayHeight][trayWidth];
				while (finalFileContents.hasNextInt()) {
					int TLX = finalFileContents.nextInt();
					int TLY = finalFileContents.nextInt();
					int BRX = finalFileContents.nextInt();
					int BRY = finalFileContents.nextInt();
					if (TLX < 0 || TLX >= trayHeight || TLY < 0
							|| TLY >= trayWidth || BRX < 0 || BRX >= trayHeight
							|| BRY < 0 || BRY >= trayWidth) {
						System.out.println("Invalid init and/or goal file.");
						finalFileContents.close();
						return;
					}
					for (int i = TLX; i <= BRX; i++) {
						for (int j = TLY; j <= BRY; j++) {
							if (goalTrayOccupied[i][j]) {
								finalFileContents.close();
								throw new IllegalStateException();
							} else {
								goalTrayOccupied[i][j] = true;
							}
						}
					}
					Block toBeAdded = new Block(TLX, TLY, BRX, BRY);
					if (goalTrayBlocks.contains(toBeAdded)) {
						System.out.println("Invalid init and/or goal file.");
						finalFileContents.close();
						return;
					}
					goalTrayBlocks.add(toBeAdded);
				}
				finalFileContents.close();
				visited = new HashSet<Tray>();
			} catch (FileNotFoundException e) {
				System.out.println("Invalid init and/or goal file.");
				return;
			} catch (InputMismatchException e) {
				System.out.println("Invalid init and/or goal file.");
				return;
			} catch (NoSuchElementException e) {
				System.out.println("Invalid init and/or goal file.");
				return;
			} catch (IllegalStateException e) {
				System.out.println("Invalid init and/or goal file.");
				return;
			}

		}
	}

	public boolean reachedGoal(Tray t) {
		for (Block block : goalTrayBlocks) {
			if (!t.getBlocks().contains(block)) {
				return false;
			}
		}
		return true;
	}

	public Tray findSolutionWithSimpleGoal() {
		Stack<Tray> fringe = new Stack<Tray>();
		initial.setHashCode();
		Tray current = initial;
		fringe.push(current);
		while (!fringe.isEmpty()) {
			current = fringe.pop();
			if (reachedGoal(current)) {
				return current;
			}
			current.setPossibleMoves(goalTrayBlocks, visited);
			visited.add(current);
			for (Tray next : current.getPossibleMoves()) {
				if (!visited.contains(next)) {
					fringe.push(next);
					visited.add(next);
				}
			}
		}
		return null;
	}

	public Tray findSolutionWithComplexGoal() {
		Queue<Tray> fringe = new PriorityQueue<Tray>();
		initial.setPriority(goalTrayBlocks);
		initial.setHashCode();
		Tray current = initial;
		fringe.offer(current);
		while (!fringe.isEmpty()) {
			current = fringe.poll();
			if (reachedGoal(current)) {
				return current;
			}
			current.setPossibleMoves(goalTrayBlocks, visited);
			visited.add(current);
			for (Tray next : current.getPossibleMoves()) {
				if (!visited.contains(next)) {
					fringe.offer(next);
					visited.add(next);
				}
			}
		}
		return null;
	}

	public Stack<String> traceSteps(Tray current) {
		Stack<String> solution = new Stack<String>();
		if (current != null) {
			while (current.getParent() != null) {
				solution.push(current.getMoveString());
				current = current.getParent();
			}
		}
		return solution;
	}

	public void printSolution(Stack<String> moves) {
		while (!moves.empty()) {
			System.out.println(moves.pop());
		}
	}

	public static void main(String[] args) {
		try {
			Solver s = new Solver(args);
			Stack<String> solution;
			if (s.goalTrayBlocks.size() < (0.25 * s.initial.getBlocks().size())) {
				solution = s.traceSteps(s.findSolutionWithSimpleGoal());
			} else {
				solution = s.traceSteps(s.findSolutionWithComplexGoal());
			}
			if (solution.isEmpty()) {
				return;
			} else {
				s.printSolution(solution);
			}
		} catch (OutOfMemoryError e) {
			System.out.println("Out of memory.");
			return;
		}
	}
}