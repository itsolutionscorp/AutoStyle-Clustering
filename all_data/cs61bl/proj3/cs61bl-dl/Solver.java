import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Stack;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.lang.IllegalArgumentException;
import java.util.Comparator;

public class Solver {
	private Tray curr;

	public Solver() {
		curr = null;
	}

	public void setTray(int height, int width, HashSet<int[]> goal) {
		curr = new Tray(height, width, goal);
	}

	public static void main(String... args) {

		if (args == null || args.length != 2) {
			System.out.println("Invalid init and/or goal file.");
			return;
		}

		Solver solver = new Solver();
		File init = new File(args[0]);
		File goal = new File(args[1]);

		try {

			Scanner lineRead = new Scanner(init);
    		String nextBlock = lineRead.nextLine();
			if (nextBlock == null) {
				System.out.println("Invalid init and/or goal file.");
				return;
			}
			String[] initialCoords = nextBlock.split(" ");

			if ((initialCoords.length) != 2) {
				System.out.println("Invalid init and/or goal file.");
				return;
			}
	        int height = Integer.parseInt(initialCoords[0]);
			int width = Integer.parseInt(initialCoords[1]);

			Scanner goalRead = new Scanner(goal);

			ArrayList<int[]> goals = new ArrayList<int[]>();
			HashSet<int[]> goalSet = new HashSet<int[]>();
			HashSet<int[]> goalSet2 = new HashSet<int[]>();

			Tray goalTest = new Tray(height, width, null);

			while (goalRead.hasNext()) {

				String nextGoal = goalRead.nextLine();

				String[] goalStringArray = nextGoal.split(" ");
				if ((goalStringArray.length) != 4) {
					System.out.println("Invalid init and/or goal file.");
					return;
				}
				int c1Y = Integer.parseInt(goalStringArray[0]);
				int c1X = Integer.parseInt(goalStringArray[1]);
				int c2Y = Integer.parseInt(goalStringArray[2]);
				int c2X = Integer.parseInt(goalStringArray[3]);
				int[] nextGoalIntArray = new int[]{c1Y, c1X, c2Y, c2X};

				if (!goalTest.canAddBlock(nextGoalIntArray)) {
					System.out.println("Invalid init and/or goal file.");
					return;
				}

				goalTest.addBlock(nextGoalIntArray);

				for (int c = 0; c < 4; c++) {
					if (nextGoalIntArray[c] < 0) {
						System.out.println("Invalid init and/or goal file.");
						return;
					}
					if ((c % 2 == 0 && nextGoalIntArray[c] >= height) ||
						(c % 2 == 1 && nextGoalIntArray[c] >= width)) {
						System.out.println("Invalid init and/or goal file.");
						return;
					}
				}
				goals.add(nextGoalIntArray);
				goalSet.add(nextGoalIntArray);
				goalSet2.add(nextGoalIntArray);
			}

			int[][] goalArray = goals.toArray(new int[goals.size()][]);

			solver.setTray(height, width, goalSet2);

    		while (lineRead.hasNext()) {

				nextBlock = lineRead.nextLine();

				String[] blockCoords = nextBlock.split(" ");

				if ((blockCoords.length) != 4) {
					System.out.println("Invalid init and/or goal file.");
					return;
				}
		        int tlY = Integer.parseInt(blockCoords[0]);
				int tlX = Integer.parseInt(blockCoords[1]);
				int brY = Integer.parseInt(blockCoords[2]);
				int brX = Integer.parseInt(blockCoords[3]);
				int[] coords = new int[]{tlY, tlX, brY, brX};
				for (int c = 0; c < 4; c++) {
					if (coords[c] < 0) {
						System.out.println("Invalid init and/or goal file.");
						return;
					}
					if ((c % 2 == 0 && coords[c] >= height) ||
						(c % 2 == 1 && coords[c] >= width)) {
						System.out.println("Invalid init and/or goal file.");
						return;
					}
				}
				if (!solver.curr.canAddBlock(coords)) {
					System.out.println("Invalid init and/or goal file.");
					return;
				}
				int[] temp = null;
				for (int[] g: goalSet) {
					if ((tlY-brY) == (g[0] - g[2]) && (tlX - brX) == (g[1] - g[3])) {
						solver.curr._heur += solver.curr.heur(tlY, tlX, brY, brX);
						temp = g;
						break;
					}
				}
				if (temp != null) {
					goalSet.remove(temp);
				}

				solver.curr.addBlock(coords);
			}

			solver.solve(solver.curr, goalArray);

		} catch(NumberFormatException e) {
			System.out.println("Invalid init and/or goal file.");
			return;
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid init and/or goal file.");
			return;
		} catch (IOException e) {
			System.out.println("Invalid init and/or goal file.");
			return;
		} catch (NoSuchElementException e) {
			System.out.println("Invalid init and/or goal file.");
			return;
    	}

	}

	private class BFSIterator implements Iterator<Tray> {

	    private PriorityQueue<Tray> fringe;
	    private HashSet<Tray> visited;

	    class trayComparator implements Comparator<Tray> {

	    	@Override
	    	public int compare(Tray a, Tray b) {
	    		return (a._heur - b._heur);
	    	}

	    }

	    public BFSIterator(Tray start) {
	        fringe = new PriorityQueue<Tray>(100, new trayComparator());
	        fringe.offer(start);
	        visited = new HashSet<Tray>();
	    }

	    public boolean hasNext() {
	        return !fringe.isEmpty();
	    }

	    public Tray next() {
	        Tray nextTray = fringe.poll();
	        for (Tray t: nextTray.children().keySet()) {
	            if (!visited.contains(t)) {
					visited.add(t);
	                fringe.offer(t);
	            }
	        }
	        visited.add(nextTray);
	        return nextTray;
	    }

	    //ignore this method
	    public void remove() {
	        throw new UnsupportedOperationException(
	                "vertex removal not implemented");
	    }

	}

	public void solve(Tray initialTray, int[][] goal) {
        Stack<String> solution = new Stack<String>();
        ArrayList<Tray> visited = new ArrayList<Tray>();
        Iterator<Tray> iter = new BFSIterator(initialTray);

        Tray next = null;
        boolean found = false;
        while (iter.hasNext()) {
			next = iter.next();
			visited.add(next);
            if (next.solved(goal)) {
                found = true;
                break;
            }
        }

        if (!found || initialTray.solved(goal)) {
            return;
        }

        while (visited.size() > 0) {
            for (Tray t: visited) {
                if (t.children().keySet().contains(next)) {
                    solution.push(t.children().get(next));
                    next = t;
                    visited.subList(visited.indexOf(t), visited.size()).clear();
                    break;
                }
            }
        }

		while (!solution.empty()) {
			System.out.println(solution.pop());
		}

    }

}
