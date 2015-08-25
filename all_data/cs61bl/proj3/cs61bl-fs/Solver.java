import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

public class Solver {
	private static int width, height;
	public static boolean error = false;

	private static void loadInit(String input) {
		BufferedReader initLine = readFile(new File(input));
		int[] blockArray;
		String line = null;

		try {
			line = initLine.readLine();
			if (line == null) {
				error = true;
				return;
			}

			blockArray = toArray(line, 2);
			height = blockArray[0];
			width = blockArray[1];
			if (height > 256 || width > 256 || height < 1 || width < 1) {
				error = true;
				return;
			}

			while ((line = initLine.readLine()) != null) {
				blockArray = toArray(line, 4);
				checkBlock(blockArray);
				State.addInit(blockArray);
			}

			initLine.close();
		} catch (IOException e) {
			error = true;
			return;
		}
	}

	private static void checkBlock(int[] arr) {
		if (arr[0] >= height || arr[0] < 0) {
			error = true;
			return;
		}

		if (arr[1] >= width || arr[1] < 0) {
			error = true;
			return;
		}

		if (arr[2] >= height || arr[2] < 0) {
			error = true;
			return;
		}

		if (arr[3] >= width || arr[3] < 0) {
			error = true;
			return;
		}

		if (arr[0] > arr[2]) {
			error = true; 
			return;
		}

		if (arr[1] > arr[3]) {
			error = true;
			return;
		}
	}

	private static void loadGoal(String input) {
		BufferedReader goalLine = readFile(new File(input));
		int[] blockArray;
		String line = null;

		try {
			while ((line = goalLine.readLine()) != null) {
				blockArray = toArray(line, 4);
				checkBlock(blockArray);
				State.addGoal(blockArray);
			}

			goalLine.close();
		} catch (IOException e) {
			error = true;
			return;
		}
	}

	private static BufferedReader readFile(File name) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(name));
		} catch (FileNotFoundException e) {
			error = true;
			return null;
		}
		return br;
	}

	private static int[] toArray(String line, int size) {
		if (line == null || size <= 0) {
			error = true;
			return null;
		}

		Scanner sc = new Scanner(line.trim());
		int[] values = new int[size];

		for (int i = 0; i < size; i++) {
			if (!sc.hasNextInt()) {
				error = true;
				return null;
			}

			values[i] = sc.nextInt();
		}

		if (sc.hasNext()) {
			error = true;
			return null;
		}
		sc.close();
		return values;
	}

	private static void setup(String[] args) {
		if (args.length != 2) {
			error = true;
			return;
		}
		
		
		loadInit(args[0]);
		if (error) {
			return;
		}
		
		loadGoal(args[1]);
		if (error) {
			return;
		}
		
		State.checkTray(width, height);
		if (error) {
			return;
		}
	}

	public static void main(String[] args) {
		setup(args);
		if (error) {
			System.out.println("Invalid init and/or goal file.");
			return;
		}
		
		work();
	}

	public static void work() {

		PriorityQueue<State> fringe = new PriorityQueue<State>();
		HashSet<State> visited = new HashSet<State>();
		fringe.offer(new State());
		State current = null;
		boolean solved = false;
	
		int[][] goalMap = new int[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				goalMap[i][j] = -1;
			}
		}
		
		for (int i = 0; i < State.goalNum; i++) {
			goalMap[State.goalY[i]][State.goalX[i]] = State.goalWidth[i] * 256 + State.goalHeight[i];
		}
		
		while (!fringe.isEmpty()) {
			current = fringe.poll();
			visited.add(current);
			if (current.goalMeet()) {
				solved = true;
				break;
			}

			for (int i = 0; i < State.initNum; i++) {
				int possibleMoves = current.isMovable(i);
				State temps;

				if ((possibleMoves % 8) / 4 == 1) {
					temps = current.move(i, State.EAST);
					if (!visited.contains(temps)) {
						fringe.offer(temps);
					}

				}
				
				if ((possibleMoves / 8) == 1) {
					temps = current.move(i, State.WEST);
					if (!visited.contains(temps)) {
						fringe.offer(temps);
					}
				}

				if ((possibleMoves % 2) == 1) {
					temps = current.move(i, State.SOUTH);
					if (!visited.contains(temps)) {
						fringe.offer(temps);
					}
				}
				
				if ((possibleMoves % 4) / 2 == 1) {
					temps = current.move(i, State.NORTH);
					if (!visited.contains(temps)) {
						fringe.offer(temps);
					}
				}
			}

		}

		if (solved) {
			Stack<int[]> history = new Stack<int[]>();
			
			while (current.prev != null) {
				for (int i = 0; i < State.initNum; i++) {
					if (current.blockX[i] != current.prev.blockX[i] || current.blockY[i] != current.prev.blockY[i] ) {
						history.push(new int[]{current.prev.blockY[i] - Byte.MIN_VALUE, current.prev.blockX[i] - Byte.MIN_VALUE, current.blockY[i] - Byte.MIN_VALUE, current.blockX[i] - Byte.MIN_VALUE });
					}
				}
				current = current.prev;
			}

			while (!history.isEmpty()) {
				int[] arr = history.pop();
				for (int i = 0; i < 4; i++) {
					System.out.print(arr[i] + " ");
				}
				System.out.println();
			}
		}

	}

}
