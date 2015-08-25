import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class Solver {

	public static void main(String[] args) throws NumberFormatException, IOException {
		List<int[]> init = new ArrayList<int[]>();
		List<int[]> goal = new ArrayList<int[]>();
		if (args.length != 2) {
			System.out.println("Invalid init and/or goal file.");
			return;
		}
		String initFilePath = args[0];
		String goalFilePath = args[1];
		for (String line : Files.readAllLines(Paths.get(initFilePath))) {
			int[] coords = new int[4];
			init.add(coords);
			int index = 0;
			for (String part : line.split("\\s+")) {
				try {
					coords[index] = Integer.parseInt(part);
				} catch (NumberFormatException ex) {
					System.out.println("Invalid init and/or goal file.");
					return;
				}
				index++;
			}
		}

		for (String line : Files.readAllLines(Paths.get(goalFilePath))) {
			int[] goalCoords = new int[4];
			goal.add(goalCoords);
			int index = 0;
			for (String part : line.split("\\s+")) {
				try {
					goalCoords[index] = Integer.parseInt(part);
				} catch (NumberFormatException ex) {
					System.out.println("Invalid init and/or goal file.");
					return;
				}
				index++;
			}
		}
		
		if (goal.size() == 0 || init.size() == 1 || init.size() == 0) {
			System.out.println("Invalid init and/or goal file.");
			return;
		}
		
		if (init.get(0)[2] != 0 || init.get(0)[3] != 0){
			System.out.println("Invalid init and/or goal file.");
		}
		
		for(int x = 1; x < init.size(); x++){
			if (init.get(x)[0] > init.get(0)[0] || init.get(x)[2] > init.get(0)[0] || init.get(x)[1] > init.get(0)[1] || init.get(x)[3] > init.get(0)[1]){
				System.out.println("Invalid init and/or goal file.");
			}
		}

		Tray tray = new Tray(init.get(0)[0], init.get(0)[1]);
		for (int i = 1; i < init.size(); i++) {
			int topLeftCornerY = init.get(i)[0];
			int topLeftCornerX = init.get(i)[1];
			int bottomRightCornerY = init.get(i)[2];
			int bottomRightCornerX = init.get(i)[3];
			int[] block = { topLeftCornerY, topLeftCornerX, bottomRightCornerY, bottomRightCornerX, 0 };
			tray.put(block);
		}
		ArrayList<Tray> visited = new ArrayList<Tray>();
		Stack<Tray> fringe = new Stack<>();
		fringe.push(tray);
		while (!fringe.isEmpty()) {
			Tray currentTray = fringe.pop();
			while (alreadyVisited(visited, currentTray)){
				if(!fringe.isEmpty()) {
					currentTray = fringe.pop();
				}else{
					return;
				}
			}
			currentTray.setPossibleMoves();
			int totalMatch = 0;
			for (int[] goalCoords : goal) {
				for (int[] block : currentTray.allBlocks) {
					if (arrayMatch(goalCoords, block)) {
						totalMatch++;
					}
				}
			}
			if (totalMatch == goal.size()) {
				String rtn = "";
				Tray tracker = currentTray;
				while(tracker.previousTray != null){
					rtn = tracker.move[0] + " " + tracker.move[1] + " " + tracker.move[2] + " " + tracker.move[3] + "\n" + rtn;
					tracker = tracker.previousTray;
				}
				System.out.print(rtn);
				return;
			}
			
			visited.add(currentTray);
			ArrayList<Tray> heuristic = priority(currentTray.possibleMoves, goal);
			for (Tray t : heuristic) {
				if (!alreadyVisited(visited, t)) {
					t.previousTray = currentTray;
					fringe.push(t);
				}
			}
		}
	}

	public static ArrayList<Tray> priority(ArrayList<Tray> arr, List<int[]> goal) {
		Object[][] temp = new Object[arr.size()][3];
		int d = 0;
		for (Tray t : arr) {
			int status = 0;
			for (int[] b : t.allBlocks) {
				for (int[] i : goal) {
					if (arrayMatch(b, i)) {
						status++;
						t.distance += Math.sqrt(Math.pow(i[0] - b[0], 2) + Math.pow(i[1] - b[1], 2));

					}
				}
			}
			temp[d][0] = status;
			temp[d][1] = t.distance;
			temp[d][2] = t;
			d++;
		}

		final Comparator<Object[]> orderByStatus = new Comparator<Object[]>() {
			public int compare(Object[] o1, Object[] o2) {
				return (int) o1[0] - (int) o2[0];
			}
		};

		final Comparator<Object[]> orderByDistance = new Comparator<Object[]>() {
			public int compare(Object[] o1, Object[] o2) {
				return (int) o1[1] - (int) o2[1];
			}
		};

		final Comparator<Object[]> order = new Comparator<Object[]>() {
			public int compare(Object[] a1, Object[] a2) {
				int i = orderByStatus.compare(a1, a2);
				if (i == 0) {
					i = orderByDistance.compare(a1, a2);
				}
				return i;
			}
		};

		Arrays.sort(temp, order);
		ArrayList<Tray> heuristic = new ArrayList<Tray>();
		for (Object[] o : temp) {
			heuristic.add((Tray) o[2]);
		}
		return heuristic;
	}

	public static int[] reverseMove(int[] move) {
		int y = move[0];
		int x = move[1];
		int[] reverse = { move[2], move[3], y, x };
		return reverse;
	}

	public static boolean alreadyVisited(ArrayList<Tray> visited, Tray t) {
		for (Tray alreadySeen : visited) {
			if (sameTray(alreadySeen, t)) {
				return true;
			}
		}
		return false;
	}

	public static boolean sameTray(Tray t, Tray r) {
		if (t.allBlocks.size() != r.allBlocks.size()) {
			return false;
		}

		int q = t.allBlocks.size();
		int w = r.allBlocks.size();
		for (int[] b : t.allBlocks) {
			for (int[] l : r.allBlocks) {
				if (arrayMatch(b, l)) {
					q--;
					w--;
				}
			}
		}
		if (q == 0 && w == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean arrayMatch(int[] goalBlock, int[] block) {
		for (int i = 0; i < 4; i++) {
			if (goalBlock[i] != block[i]) {
				return false;
			}
		}
		return true;
	}
}