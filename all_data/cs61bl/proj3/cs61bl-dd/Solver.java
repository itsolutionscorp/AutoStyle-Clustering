import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Solver implements Iterable<Tray> {
	public static Tray initTray;
	public static HashSet<Integer> visitedBoard;
	public ArrayList<String> goal;
	public ArrayList<String> init;
	public static boolean checkPiece;
	public static boolean bigTraySpecialCase;

	public Solver() {
		initTray = null;
		visitedBoard = new HashSet<Integer>();
		goal = new ArrayList<String>();
		init = new ArrayList<String>();
		checkPiece = false;
		bigTraySpecialCase = false;
	}

	public void solve(String args[]) {
		if (args.length == 2) {
			this.init = readInput(args[0]);
			this.goal = readInput(args[1]);
			initTray = new Tray(this.init, this.goal);
		} else {
			System.out.println("Invalid init and/or goal file.");
		}
	}

	public static ArrayList<String> readInput(String s) {
		try {
			ArrayList<String> rtn = new ArrayList<String>();
			Scanner sc = new Scanner(new File(s));
			while (sc.hasNextLine()) {
				rtn.add(sc.nextLine());
			}
			return rtn;
		} catch (FileNotFoundException e) {
			System.out.println("Invalid init and/or goal file.");
			return new ArrayList<String>();
		}
	}

	public Iterator<Tray> iterator() {
		return new SearchIteratorDFS();
	}

	//Breath first iterator
	public class SearchIteratorBFS implements Iterator<Tray> {
		private LinkedList<Tray> fringe;

		public SearchIteratorBFS() {
			fringe = new LinkedList<Tray>();
			if (initTray != null) {
				fringe.add(initTray);
			}
		}

		@Override
		public boolean hasNext() {
			return !fringe.isEmpty();
		}

		@Override
		public Tray next() {
			Tray rtn = fringe.get(0);
			Tray.solve(rtn);
			fringe.remove(0);
			for (Tray child : rtn.myChildren) {
				fringe.add(child);
			}
			return rtn;
		}

	}
	
	//Depth first iterator
	public class SearchIteratorDFS implements Iterator<Tray>{
		private Stack<Tray> fringe;
		
		public SearchIteratorDFS(){
			fringe = new Stack<Tray>();
			if(initTray != null){
				fringe.push(initTray);
			}
		}

		@Override
		public boolean hasNext() {
			return !fringe.isEmpty();
		}

		@Override
		public Tray next() {
			Tray rtn = fringe.pop();
			Tray.solve(rtn);
			for(int i = rtn.myChildren.size() - 1; i >= 0; i--){
				fringe.push(rtn.myChildren.get(i));
			}
			return rtn;
		}
	}

	public static void main(String args[]) {
		Solver s = new Solver();
		s.solve(args);
		Iterator iter = s.iterator();
		Tray t = null;
		while (iter.hasNext()) {
			t = (Tray) iter.next();
			// Break if the iterator find the first solution
			if (t.isGoal) {
				break;
			}
		}
		// Return nothing if does not find goal
		if (!t.isGoal) {
			return;
		}
		Stack<String> rtn = new Stack<String>();
		while (t.currentMove != null) {
			rtn.push(t.currentMove);
			t = t.myParent;
		}
		String move = "";
		while (!rtn.isEmpty()) {
			move = rtn.pop();
			System.out.println(move);
		}
	}
}
