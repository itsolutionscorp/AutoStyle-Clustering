import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.LinkedHashSet;
import java.io.IOException;

public class Solver {

	private Stack<Tray> fringe;
	private HashSet<Tray> visited;

	public Solver(Tray i) {
		fringe = new Stack<Tray>();
		visited = new HashSet<Tray>();
		fringe.push(i);
	}

	public static void main(String[] args) {
		try {
			String initFile = args[0];
			String goalFile = args[1];
			Scanner scan = new Scanner(new File(initFile));
			Scanner scan2 = new Scanner(new File(goalFile));
			ArrayList<String> initialConfig = new ArrayList<String>();
			ArrayList<String> goalConfig = new ArrayList<String>();
			while (scan.hasNextLine()) {
				initialConfig.add(scan.nextLine());
			}
			scan.close();
			while (scan2.hasNextLine()) {
				goalConfig.add(scan2.nextLine());
			}
			scan2.close();
			String[] dimensions = initialConfig.remove(0).split(" ");
			Tray initial = new Tray(Integer.parseInt(dimensions[0]),
					Integer.parseInt(dimensions[1]));
			Tray goal = new Tray(Integer.parseInt(dimensions[0]),
					Integer.parseInt(dimensions[1]));
			addToTray(initial, initialConfig);
			addToTray(goal, goalConfig);
			Solver work = new Solver(initial);
			work.solve(initial, goal);
		} catch (IOException | RuntimeException e) {
			System.out.println("Invalid init and/or goal file");
			System.exit(1);
		}
	}

	private static void addToTray(Tray t, ArrayList<String> config)
			throws IllegalArgumentException {
		String[] blocks;
		int[] coordinates;
		for (int k = 0; k < config.size(); k++) {
			blocks = config.get(k).split(" ");
			coordinates = new int[4];
			for (int l = 0; l < 4; l++) {
				coordinates[l] = Integer.parseInt(blocks[l]);
			}
			t.add(coordinates);
		}
	}

	private void solve(Tray i, Tray g) {
		while (!fringe.isEmpty()) {
			Tray t = fringe.pop();
			visited.add(t);
			t.getMoves();
			if (t.trayMatch(g)) {
				Tray sol = t;
				Stack<int[]> solution = new Stack<int[]>();
				while (!sol.equals(i)) {
					solution.push(sol.getMoveMade());
					sol = sol.getParent();
				}
				while (!solution.isEmpty()) {
					int[] move = solution.pop();
					for (int k = 0; k < 4; k++) {
						System.out.print(move[k] + " ");
					}
					System.out.println("");
				}
				return;
			}
			ArrayList<Tray> possibles = t.neighbors();
			for (Tray tr : possibles) {
				if (!visited.contains(tr)) {
					fringe.push(tr);
				}
			}

		}
	}

}