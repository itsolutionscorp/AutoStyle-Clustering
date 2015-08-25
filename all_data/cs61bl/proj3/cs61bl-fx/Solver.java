import java.io.*;
import java.util.*;

class Solver {
	private Tray startTray;
	private Tray goalTray;
	private HashSet<Integer> visited;
	private Stack<Tray> trays;
	private Stack<String> pathtogoal;

	public Solver(Tray s, Tray g) {
		this.startTray = s;
		this.goalTray = g;
		visited = new HashSet<Integer>();
		pathtogoal = new Stack<String>();
		trays = new Stack<Tray>();
	}

	public static void main(String[] args) {
		Tray startTray = null;
		Tray goalTray = null;
		try {
			File trayConfig = new File(args[0]);
			FileInputStream tray = new FileInputStream(trayConfig);
			BufferedReader trayConfiguration = new BufferedReader(new InputStreamReader(tray));
			String line0 = trayConfiguration.readLine();
			String[] size = line0.split(" ");
			if (size.length != 2) {
				trayConfiguration.close();
				throw new Exception();
			}
			short height = Short.parseShort(size[0]);
			short width = Short.parseShort(size[1]);
			if (height < 0 || width < 0) {
				trayConfiguration.close();
				throw new Exception();
			}
			startTray = new Tray(height, width);
			String line;
			while ((line = trayConfiguration.readLine()) != null) {
				String[] numbers = line.split(" ");
				if (numbers.length != 4) {
					trayConfiguration.close();
					throw new Exception();
				}
				short tleftx = Short.parseShort(numbers[0]);
				short tlefty = Short.parseShort(numbers[1]);
				short brightx = Short.parseShort(numbers[2]);
				short brighty = Short.parseShort(numbers[3]);
				Block b = new Block(tleftx, tlefty, brightx, brighty);
				startTray.addBlock(b);
			}
			trayConfiguration.close();

			File goalConfig = new File(args[1]);
			FileInputStream goal = new FileInputStream(goalConfig);
			BufferedReader goalConfiguration = new BufferedReader(new InputStreamReader(goal));
			String line2;
			goalTray = new Tray(height, width);
			while ((line2 = goalConfiguration.readLine()) != null) {
				String[] numbers = line2.split(" ");
				if (numbers.length != 4) {
					goalConfiguration.close();
					throw new Exception();
				}
				short tleftx = Short.parseShort(numbers[0]);
				short tlefty = Short.parseShort(numbers[1]);
				short brightx = Short.parseShort(numbers[2]);
				short brighty = Short.parseShort(numbers[3]);

				Block b = new Block(tleftx, tlefty, brightx, brighty);
				goalTray.addBlock(b);
			}
			goalConfiguration.close();
		} catch (Exception e) {
			System.err.println("Invalid init and/or goal file.");
			System.exit(0);
		}
		Solver s = new Solver(startTray, goalTray);
		try {
			s.solvePuzzle();
		} catch (Exception e) {
			System.err.println("Invalid init and/or goal file.");
			System.exit(0);
		}
	}

	public boolean isGoal(Tray tray) {
		if (goalTray.getMyBlocks().size() > tray.getMyBlocks().size()) {
			System.exit(0);
		}
		for (Block b : goalTray.getMyBlocks()) {
			if (!tray.getMyBlocks().contains(b)) {
				return false;
			}
		}
		return true;
	}

	public void solvePuzzle() throws Exception {
		trays.push(startTray);
		if (isGoal(trays.peek())) {
			System.exit(0);
		}
		while (!trays.isEmpty()) {
			Tray t = trays.pop();
			visited.add(t.representation());
			ArrayList<moveBlock> NextMoves = t.nextMoves();
			for (moveBlock move : NextMoves) {
				Tray newTray = new Tray(t.height(), t.width(), t);
				for (Block b : t.getMyBlocks()) {
					newTray.addBlock(b);
				}
				Block block = move.b;
				newTray.removeBlock(block);
				Block newBlock = new Block(move.newTopLeftJ, move.newTopLeftI, move.newBotRightJ, move.newBotRightI);
				newTray.addBlock(newBlock);
				newTray.memorizeMove(move);
				if (isGoal(newTray)) {
					Tray temp = newTray;
					while (temp.parent() != null) {
						pathtogoal.push(temp.recallMove().toString());
						temp = temp.parent();
					}
					while (!pathtogoal.isEmpty()) {
						System.out.println(pathtogoal.pop());
					}
					System.exit(0);
				}
				if (!visited.contains(newTray.representation())) {
					trays.push(newTray);
				}
			}
		}
	}
}
