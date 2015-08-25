import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Solver {

	public static HashSet<Tray> visited;
	private static ArrayList<Integer> configuration;
	private static ArrayList<Integer> endConfig;
	private BufferedReader initial;
	public static PriorityQueue<Tray> pq;

	public Solver() {
		visited = new HashSet<Tray>();
		pq = new PriorityQueue<Tray>();
	}

	public static void addTray(Tray tray) {
		if (tray.solved()) {
			Tray iter = tray;
			Stack<Tray> solutions = new Stack<Tray>();
			while (iter != null) {
				solutions.add(iter);
				iter = iter.parent;
			}
			solutions.pop();
			LinkedList<Tray> list = new LinkedList<Tray>();
			while (!solutions.isEmpty()) {
				Tray current = solutions.pop();
				list.add(current);
				System.out.println(current.move);
			}
			System.exit(0);
			
		}
		add(tray);

	}
	
	public static void add(Tray tray) {
		if(!contain(tray)) {
			visited.add(tray);
			pq.offer(tray);
			//System.out.println(visited.size());
		}
	}
	
	public static boolean contain(Tray tray) {
		return visited.contains(tray);
	}

	public static Tray setUpTray(BufferedReader initial) throws IOException,
			NumberFormatException {
		configuration = new ArrayList<Integer>();
		try {
			// initial = new BufferedReader(new FileReader(fileName));
			String startConfig = initial.readLine();
			String[] start = startConfig.split(" ");
			for (String s : start) {
				int i = Integer.parseInt(s);
				if (i > 256 || i < 1) {
					System.out.println("Invalid init and/or goal file.");
					System.exit(0);
				}
				configuration.add(i);
			}
			if (configuration.size() != 2) {
				System.out.println("Invalid init and/or goal file.");
				System.exit(0);
			}
			Tray.length = configuration.get(1);
			Tray.height = configuration.get(0);
		} catch (Exception e) {
			System.out.println("Invalid init and/or goal file.");
			System.exit(0);
		}
		return checkValid(initial);
	}

	public static Tray checkValid(BufferedReader initial) throws IOException, NumberFormatException {
		ArrayList<Rectangle> blocks = new ArrayList<Rectangle>();
		try {
			for (String block = initial.readLine(); block != null; block = initial
					.readLine()) {
				String[] position = block.split(" ");
				if (position.length != 4) {
					System.out.println("Invalid init and/or goal file.");
					System.exit(0);
				}
				int x1 = Integer.parseInt(position[0]);
				int y1 = Integer.parseInt(position[1]);
				int x2 = Integer.parseInt(position[2]);
				int y2 = Integer.parseInt(position[3]);
				if (x1 < 0 || y1 > Tray.length || x2 < 0 || y2 > Tray.length
						|| y1 < 0 || x1 > Tray.height || y2 < 0
						|| x2 > Tray.height) {
					System.out.println("Invalid init and/or goal file.");
					System.exit(0);
				}
				// System.out.println("before valid" + y1 + " " + x1 + " " + (y2
				// + 1) + " " + (x2 + 1));
				Rectangle b = new Rectangle(y1, x1, y2 + 1 - y1, x2 + 1 - x1);
				for (Rectangle bb : blocks) {
					if (b.intersects(bb)) {
						System.out.println("Invalid init and/or goal file.");
						System.exit(0);
					}
				}
				blocks.add(b);
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}
		Tray puzzle = new Tray(blocks);

		return puzzle;

	}

	public void solve(Tray initial) {
		pq.offer(initial);
		while (!pq.isEmpty()) {
			Tray current = pq.remove();
			
			//System.out.println(current.priority);
			current.addNextMoves();
			//System.out.println(visited.size());
		}
		System.exit(0);
	}

	public static void main(String[] args) throws Exception,
			FileNotFoundException {
		try {
			BufferedReader initReader = new BufferedReader(new FileReader(
					args[0]));
			BufferedReader goalReader = new BufferedReader(new FileReader(
					args[1]));
//			 BufferedReader initReader = new BufferedReader(new
//			 FileReader("test2"));
//			 BufferedReader goalReader = new BufferedReader(new
			// FileReader("hard/goal.1.from.handout"));

			Tray initialState = setUpTray(initReader);
			Tray.goalConfig = checkValid(goalReader);
			Solver solver = new Solver();
			solver.solve(initialState);
			System.exit(0);
		} catch (Exception e) {
			System.out.println("Invalid init and/or goal file.");
		}
	}
}
