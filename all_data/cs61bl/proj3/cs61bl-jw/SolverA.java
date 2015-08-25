import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.math.*;
import java.lang.StringBuilder;

public class SolverA {

	private PriorityQueue<Tray> fringe;
	private Tray current;
	private HashSet<Tray> visited;

	public SolverA() {
		fringe = new PriorityQueue<Tray>();
		visited = new HashSet<Tray>();
	}

	public static ArrayList<int[]> tReader(String fileName) throws IOException {

		int lineCount = 0;
		ArrayList<int[]> ret = new ArrayList<int[]>();
		Path file = Paths.get(fileName);
		List<String> lines = Files.readAllLines(file);
		int[] row;

		for (String line : lines) {

			if (lineCount == 0) {
				row = new int[2];
			} else {
				row = new int[4];
			}
			int charCount = 0;
			int numCount = 0;
			char[] letters = line.toCharArray();
			String numberSoFar = "";
			for (char c : letters) {
				if (lineCount == 0 && numCount == 2) {
					throw new IllegalStateException(" whohooho");
				}
				if (charCount == 0) {
					if (c == ' ') {
						throw new IllegalStateException(
								" can't start with space");
					} else {
						numberSoFar = numberSoFar + Character.toString(c);
					}
				} else {
					if (c == ' ') {
						try {
							int i = Integer.parseInt(numberSoFar);
							row[numCount] = i;
							numCount += 1;
						} catch (NumberFormatException e) {
							throw new IllegalStateException("something + "
									+ numberSoFar);
						}
						numberSoFar = "";
					} else {
						numberSoFar = numberSoFar + Character.toString(c);
					}
				}
				charCount += 1;

			}
			try {
				int i = Integer.parseInt(numberSoFar);
				row[numCount] = i;
				numCount += 1;
			} catch (NumberFormatException e) {
				throw new IllegalStateException("something + " + numberSoFar);
			}

			if (lineCount != 0 && numCount != 4) {
				throw new IllegalStateException("idk if i should else");
			}
			if (lineCount == 0 && numCount != 2) {
				throw new IllegalStateException("idk if ill should else + "
						+ lineCount + "  -  " + numCount);
			}
			ret.add(row);
			lineCount += 1;
		}
		if (lineCount < 1) {
			throw new IllegalStateException("omg");
		}
		return ret;

	}

	public static ArrayList<int[]> gReader(String fileName) throws IOException {
		int lineCount = 0;
		ArrayList<int[]> stuff = new ArrayList<int[]>();
		Path file = Paths.get(fileName);
		List<String> lines = Files.readAllLines(file);
		int[] row;

		for (String line : lines) {

			row = new int[4];
			int charCount = 0;
			int numCount = 0;
			String numberSoFar = "";
			char[] letters = line.toCharArray();
			for (char c : letters) {

				if (charCount == 0) {
					if (c == ' ') {
						throw new IllegalStateException(
								" can't start with space");
					} else {
						numberSoFar = numberSoFar + Character.toString(c);
					}
				} else {
					if (c == ' ') {
						try {
							int i = Integer.parseInt(numberSoFar);
							row[numCount] = i;
							numCount += 1;
						} catch (NumberFormatException e) {
							throw new IllegalStateException("something + "
									+ numberSoFar);
						}
						numberSoFar = "";
					} else {
						numberSoFar = numberSoFar + Character.toString(c);
					}
				}
				charCount += 1;
			}
			try {
				int i = Integer.parseInt(numberSoFar);
				row[numCount] = i;
				numCount += 1;
			} catch (NumberFormatException e) {
				throw new IllegalStateException("something + " + numberSoFar);
			}

			if (numCount != 4) {
				throw new IllegalStateException("idk if i should else");
			}
			stuff.add(row);
			lineCount += 1;
		}
		if (lineCount == 0) {
			throw new IllegalStateException("need something in the goal file");
		}
		return stuff;
	}

	public static void results(Tray last) {
		Stack<int[]> backwords = new Stack<int[]>();
		Tray ref = last;
		while (ref.prev != null) {
			backwords.push(ref.lastMove);
			ref = ref.prev;
		}

		while (!backwords.isEmpty()) {
			int count = 0;
			int[] m = backwords.pop();
			for (int n : m) {
				System.out.print(n);
				count += 1;
				if (count < 4) {
					System.out.print(" ");
				}
			}
			System.out.println("");
		}
	}


	// TRAY CLASS //

	public static class Tray implements Comparable<Tray> {
		public int[][] tray;
		public LinkedList<Integer>[] empty;
		public HashMap<Integer, int[]> piecesReferenece;
		public ArrayList<int[]> pieces;
		public int[] lastMove;
		public double myHeuristic;
		public Tray prev;
		public int hash;
		public int stringHash;
		public int movessofar;

		public int compareTo(Tray b) {
			if(myHeuristic<b.myHeuristic){
				return -1;
			}
			if(myHeuristic==b.myHeuristic){
				return 0;
			}
			return 1;
		}

		public Tray(ArrayList<int[]> parsed) {
			movessofar=0;
			int[] temp = parsed.get(0);
			tray = new int[temp[0]][temp[1]];
			empty = new LinkedList[temp[0]];
			for (int i = 0; i < empty.length; i++) {
				empty[i] = new LinkedList<Integer>();
			}
			piecesReferenece = new HashMap<Integer, int[]>();
			pieces = new ArrayList<int[]>();
			int count = 0;
			for (int[] alpha : parsed) {
				if (count != 0) {
					fillTray(alpha[0], alpha[1], alpha[2], alpha[3], count);
				}
				count++;
			}
			for (int x = 0; x < temp[0]; x++) {
				for (int y = 0; y < temp[1]; y++) {
					if (tray[x][y] == 0) {
						empty[x].add(y);
					}
				}
			}
			myHeuristic = 0.0;
			hash = Arrays.deepHashCode(tray);
			stringHash = stringHash();
			
		}

		public boolean isGoal(ArrayList<int[]> a) {
			for (int[] z : a) {
				if (!goalHelper(z)) {
					return false;
				}
			}
			return true;
		}

		public boolean goalHelper(int[] a) {
			for (int[] b : pieces) {
				if (Arrays.equals(a, b)) {
					return true;
				}
			}
			return false;
		}

		public Tray(int[][] newtray, LinkedList<Integer>[] newempty,
				HashMap<Integer, int[]> newpieces, ArrayList<int[]> newpiecee,
				int[] move, Tray old, int movessofars) {
			
			empty = newempty;
			piecesReferenece = newpieces;
			lastMove = move;
			pieces = newpiecee;
			prev = old;
			tray = new int[newtray.length][newtray[0].length];
			for (int alpha : newpieces.keySet()) {
					fillTrayp(newpieces.get(alpha)[0], newpieces.get(alpha)[1], newpieces.get(alpha)[2], newpieces.get(alpha)[3], alpha);
			}
			hash = Arrays.deepHashCode(tray);
			stringHash = stringHash();
			movessofar=movessofars+1;
		}
		public void fillTrayp(int firstarg, int secondarg, int thirdarg,
				int fourtharg, int value) {

			for (int y = firstarg; y <= thirdarg; y++) {
				for (int z = secondarg; z <= fourtharg; z++) {
					tray[y][z] = value;
				}
			}

		}
		public void fillTray(int firstarg, int secondarg, int thirdarg,
				int fourtharg, int value) {
			if (firstarg<0||thirdarg<0||secondarg<0||fourtharg<0||firstarg>=tray.length||thirdarg>=tray.length||secondarg>=tray[0].length||fourtharg>=tray[0].length){
				throw new IllegalStateException();
			}
			for (int y = firstarg; y <= thirdarg; y++) {
				for (int z = secondarg; z <= fourtharg; z++) {
					if (tray[y][z] != 0) {
						throw new IllegalStateException();
					}
					tray[y][z] = value;
				}
			}
			int[] temp = new int[4];
			temp[0] = firstarg;
			temp[1] = secondarg;
			temp[2] = thirdarg;
			temp[3] = fourtharg;
			piecesReferenece.put(value, temp);
			pieces.add(temp);
		}

		public ArrayList<int[]> validMoves() {
			ArrayList<int[]> toReturn = new ArrayList<int[]>();
			for (int x = 0; x < empty.length; x++) {
				for (int y : empty[x]) {
					// System.out.println("empty" + x + " " + y);
					int[] left = possibleMovesL(x, y);
					if (left != null) {
						boolean switcher = true;
						for (int[] a : toReturn) {
							if (Arrays.equals(left, a)) {
								switcher = false;
							}
						}
						if (switcher) {
							toReturn.add(left);
						}
					}
					int[] right = possibleMovesR(x, y);
					if (right != null) {
						boolean switcher = true;
						for (int[] a : toReturn) {
							if (Arrays.equals(right, a)) {
								switcher = false;
							}
						}
						if (switcher) {
							toReturn.add(right);
						}
					}
					int[] up = possibleMovesU(x, y);
					if (up != null) {
						boolean switcher = true;
						for (int[] a : toReturn) {
							if (Arrays.equals(up, a)) {
								switcher = false;
							}
						}
						if (switcher) {
							toReturn.add(up);
						}
					}
					int[] down = possibleMovesD(x, y);
					if (down != null) {
						boolean switcher = true;
						for (int[] a : toReturn) {
							if (Arrays.equals(down, a)) {
								switcher = false;
							}
						}
						if (switcher) {
							toReturn.add(down);
						}
					}
				}
			}
			return toReturn;
		}

		// return null if can't move right, else returns the move.
		public int[] possibleMovesR(int x, int y) {
			int[] toReturn = new int[4];
			if (x - 1 != -1) {
				int pieceNo = tray[x - 1][y];
				int[] currPiece = piecesReferenece.get(pieceNo);
				if (currPiece != null) {
					for (int z = currPiece[1]; z <= currPiece[3]; z++) {
						if (tray[x][z] != 0) {
							return null;
						}
					}
					toReturn[0] = currPiece[0];
					toReturn[1] = currPiece[1];
					toReturn[2] = currPiece[0] + 1;
					toReturn[3] = currPiece[1];
					return toReturn;

				}
			}
			return null;
		}

		public int[] possibleMovesL(int x, int y) {
			int[] toReturn = new int[4];
			if (x + 1 != tray.length) {
				int pieceNo = tray[x + 1][y];
				int[] currPiece = piecesReferenece.get(pieceNo);
				if (currPiece != null) {
					for (int z = currPiece[1]; z <= currPiece[3]; z++) {
						if (tray[x][z] != 0) {
							return null;
						}
					}
					toReturn[0] = currPiece[0];
					toReturn[1] = currPiece[1];
					toReturn[2] = currPiece[0] - 1;
					toReturn[3] = currPiece[1];
					return toReturn;

				}
			}
			return null;
		}

		public int[] possibleMovesU(int x, int y) {
			int[] toReturn = new int[4];
			if (y + 1 != tray[0].length) {
				int pieceNo = tray[x][y + 1];
				int[] currPiece = piecesReferenece.get(pieceNo);
				if (currPiece != null) {
					for (int z = currPiece[0]; z <= currPiece[2]; z++) {
						if (tray[z][y] != 0) {
							return null;
						}
					}
					toReturn[0] = currPiece[0];
					toReturn[1] = currPiece[1];
					toReturn[2] = currPiece[0];
					toReturn[3] = currPiece[1] - 1;
					return toReturn;

				}
			}
			return null;
		}

		public int[] possibleMovesD(int x, int y) {
			int[] toReturn = new int[4];
			if (y != 0) {
				int pieceNo = tray[x][y - 1];
				int[] currPiece = piecesReferenece.get(pieceNo);
				if (currPiece != null) {
					for (int z = currPiece[0]; z <= currPiece[2]; z++) {
						if (tray[z][y] != 0) {
							return null;
						}
					}
					toReturn[0] = currPiece[0];
					toReturn[1] = currPiece[1];
					toReturn[2] = currPiece[0];
					toReturn[3] = currPiece[1] + 1;
					return toReturn;

				}
			}
			return null;
		}

		public Tray move(int[] move) {
			LinkedList<Integer>[] newempty = emptyCopy(empty);
			HashMap<Integer, int[]> newpieces = pieceRefCopy(piecesReferenece);

			ArrayList<int[]> newpiecee = pieceCopy(pieces);
			int pieceNo = tray[move[0]][move[1]];

			int[] currPiece = { newpieces.get(pieceNo)[0],
					newpieces.get(pieceNo)[1], newpieces.get(pieceNo)[2],
					newpieces.get(pieceNo)[3] };

			removeSpecial(newpiecee, currPiece);

			if (move[2] - move[0] == 0) {
				if (move[3] > move[1]) {// move down
					for (int z = currPiece[0]; z <= currPiece[2]; z++) {

						Integer bs = currPiece[3] + 1;
						newempty[z].remove(bs);
						newempty[z].add(currPiece[1]);
					}
					currPiece[1] += 1;
					currPiece[3] += 1;
					// System.out.println("down");
				} else {// move up
					for (int z = currPiece[0]; z <= currPiece[2]; z++) {

						Integer bs = move[3];
						newempty[z].remove(bs);
						newempty[z].add(currPiece[3]);
					}
					currPiece[1] -= 1;
					currPiece[3] -= 1;
					// System.out.println("up");
				}
			} else {// move right
				if (move[2] > move[0]) {
					for (int z = currPiece[1]; z <= currPiece[3]; z++) {

						Integer bs = z;
						newempty[currPiece[2] + 1].remove(bs);
						newempty[currPiece[0]].add(z);
					}
					currPiece[0] += 1;
					currPiece[2] += 1;
				} else {// move left
					for (int z = currPiece[1]; z <= currPiece[3]; z++) {

						Integer bs = z;
						newempty[move[2]].remove(bs);
						newempty[currPiece[2]].add(z);
					}
					currPiece[0] -= 1;
					currPiece[2] -= 1;
				}
			}
			newpiecee.add(currPiece);
			newpieces.put(pieceNo, currPiece);

			return new Tray(tray, newempty, newpieces, newpiecee, move, this, movessofar);
		}

		public void setHeuristic(ArrayList<int[]> goalConfig) {
			this.myHeuristic = this.generateHeuristic(goalConfig);
		}

		private double generateHeuristic(ArrayList<int[]> goalConfig) {

			Iterator<int[]> goalPieceIterator = goalConfig.iterator();
			int[] goalPiece;
			double result = 0.0;
			double minMD = 0.0;
			int[] minMDPiece = null;

			// iterate through the pieces of the goal tray configuration to
			// determine the heuristic
			while (goalPieceIterator.hasNext()) {
				goalPiece = goalPieceIterator.next();
				for (int[] currentPiece : pieces) {
					if ((Math.abs(currentPiece[0] - currentPiece[2]) == Math
							.abs(goalPiece[0] - goalPiece[2]))
							&& (Math.abs(currentPiece[1] - currentPiece[3]) == Math
									.abs(goalPiece[1] - goalPiece[3]))) {

						double tempMD = Tray.getManhattanDistance(currentPiece,
								goalPiece);
						if (minMDPiece == null && minMD == 0.0) {
							minMDPiece = currentPiece;
							minMD = tempMD;
						} else {
							if (tempMD < minMD) {
								minMD = tempMD;
							}
						}
					}
				}

				
				result += minMD;
			}

			return result;
		}

		private static int getManhattanDistance(int[] one, int[] other) {
			return (Math.abs(one[0] - other[0]) + Math.abs(one[1] - other[1]));
		}


		public static LinkedList<Integer> copyLinked(LinkedList<Integer> l) {
			LinkedList<Integer> copy = new LinkedList<Integer>();
			Iterator<Integer> iter = l.iterator();
			while (iter.hasNext()) {
				copy.add(iter.next());
			}
			return copy;
		}

		public static LinkedList<Integer>[] emptyCopy(LinkedList<Integer>[] l) {
			LinkedList<Integer>[] copy = new LinkedList[l.length];
			int count = 0;
			for (LinkedList<Integer> i : l) {
				copy[count] = copyLinked(i);
				count += 1;
			}
			return copy;
		}

		public static void removeSpecial(ArrayList<int[]> a, int[] b) {
			int index = 0;
			for (int[] x : a) {
				if (Arrays.equals(x, b)) {
					break;
				}
				index++;
			}
			a.remove(index);
		}

		public static HashMap<Integer, int[]> pieceRefCopy(
				HashMap<Integer, int[]> h) {
			HashMap<Integer, int[]> copy = new HashMap<Integer, int[]>();
			for (Integer key : h.keySet()) {
				int[] b = h.get(key);
				int[] copyB = new int[b.length];
				for (int i = 0; i < b.length; i++) {
					copyB[i] = b[i];
				}
				copy.put(key, copyB);
			}
			return copy;
		}

		public static ArrayList<int[]> pieceCopy(ArrayList<int[]> m) {
			ArrayList<int[]> copy = new ArrayList<int[]>();
			for (int[] b : m) {
				int[] copyB = new int[b.length];
				for (int i = 0; i < b.length; i++) {
					copyB[i] = b[i];
				}
				copy.add(copyB);
			}
			return copy;
		}


		@Override
		public boolean equals(Object t) {
			if (!(t instanceof Tray)) {
				return false;
			}
			Tray confirmed = (Tray) t;
			return hash == confirmed.hash && stringHash == confirmed.stringHash;
		}
		
		public int stringHash() {
			StringBuilder s = new StringBuilder("[");
			for (int[] t: tray) {
				s.append(Arrays.toString(t));
			}
			s.append("]");
			return s.toString().hashCode();
		}

		@Override
		public int hashCode() {
			return hash;
		}

	}

	public static void main(String[] args) throws IOException {
		// Make bullet proof
		if (args.length != 2) {
			System.out.println("Invalid init and/or goal file.");
			return;
		}

		try {
			SolverA s = new SolverA();
			ArrayList<int[]> initialConfig = tReader(args[0]);

			ArrayList<int[]> goal = gReader(args[1]);

			Tray start = new Tray(initialConfig);
			s.fringe.offer(start);
			s.visited.add(start);
			while (!s.fringe.isEmpty()) {
				s.current = s.fringe.poll();				
				if (s.current.isGoal(goal)) {

					results(s.current);

					return;
				} else {
					for (int[] m : s.current.validMoves()) {
						Tray t = s.current.move(m);

						if (!s.visited.contains(t)) {
							s.fringe.offer(t);
							s.visited.add(t);
						}
					}
					s.current.tray = null;
					s.current.pieces = null;
					s.current.piecesReferenece = null;
					s.current.empty = null;
				}

			}

		} catch (IOException e) {
			System.out.println("Invalid init and/or goal file.");
		} catch (IllegalStateException e) {
			System.out.println("Invalid init and/or goal file.");
		}

	}
}