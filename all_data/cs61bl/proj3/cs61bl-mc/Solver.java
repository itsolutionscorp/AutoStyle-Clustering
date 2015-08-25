import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;

public class Solver {

	private Tray initTray;
	private ArrayList<Piece> goal;
	private HashSet<Tray> visited;
	private int vBound;
	private int hBound;
	private PriorityQueue<Tray> moves;
	private boolean keepWorking;
	private boolean badBoard;
	private int nextPieceId;
	private boolean[][] goalOccupied;

	public Solver() {
		goal = new ArrayList<Piece>();
		keepWorking = true;
		badBoard = false;
		nextPieceId = 1;
		visited = new HashSet<Tray>();

	}

	public void readTray(String file) {
		File f = new File(file);
		if (!f.exists()) {
			badBoard = true;
			return;
		}
		try {
			initTray = new Tray();
			int i = 0;
			for (String line : Files.readAllLines(Paths.get(file),
					StandardCharsets.US_ASCII)) {
				if (i == 0) {
					initTray.setBounds(line.split(" "));
					i++;
				} else {
					initTray.addPiece(line.split(" "));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readGoal(String file) {
		File f = new File(file);
		if (!f.exists()) {
			badBoard = true;
			return;
		}
		try {
			for (String line : Files.readAllLines(Paths.get(file),
					StandardCharsets.US_ASCII)) {
				addToGoal(line.split(" "));
			}
		} catch (IOException e) {
		}
	}

	private void addToGoal(String[] coords) {
		
		goalOccupied = new boolean[vBound][hBound];
		if (coords.length != 4) {
			badBoard = true;
			return;
		}
		int[] parsed = new int[4];
		for (int i = 0; i < 4; i++) {
			try {
				parsed[i] = Integer.parseInt(coords[i]);
				if (parsed[i] < 0) {
					badBoard = true;
					return;
				}
			} catch (NumberFormatException e) {
				badBoard = true;
				return;
			}
		}
		for (int i = 0; i < 4; i++) {
			if (i % 2 == 0) {
				if (parsed[i] >= vBound || parsed[i] < 0) {
					badBoard = true;
					return;
				}
			} else {
				if (parsed[i] >= hBound || parsed[i] < 0) {
					badBoard = true;
					return;
				}
			}
		}
		for (int i = parsed[0]; i <= parsed[2]; i++) {
			for (int j = parsed[1]; j <= parsed[3]; j++) {
				if (goalOccupied[i][j]) {
					badBoard = true;
					return;
				}
				goalOccupied[i][j] = true;

			}
		}
		goal.add(new Piece(parsed, -1));
	}

	public void traverse() {
		visited.add(initTray);
		initTray.getMoves();
		initTray.calculateTotalH();
		traverseHelper();
	}

	public void printMoves(Tray current) {
		Stack<String> stack = new Stack<String>();
		while (current != initTray) {
			stack.push(current.move);
			current = current.getParent();
		}
		while (!stack.empty()) {
			System.out.println(stack.pop());
		}
	}

	private void traverseHelper() {

		while (keepWorking && !moves.isEmpty()) {
			Tray child = moves.poll();
			if (!visited.contains(child)) {
				if (child.satisfies(goal)) {
					printMoves(child);
					keepWorking = false;
				} else {
					visited.add(child);
					child.getMoves();
				}
			}
		}
	}

	public class Tray implements Comparable {
		private String move;
		private HashSet<Piece> pieces;
		private Tray parent;
		private Piece[][] piecesTable;
		private int f;

		public Tray() {
			pieces = new HashSet<Piece>();
			parent = null;
			move = null;
			moves = new PriorityQueue<Tray>();
		}

		private void calculateTotalH() {
			int totalH = 0;
			for (Piece piece : pieces) {
				piece.setH(calculateHeuristic(piece));
				totalH += piece.h;
			}
			f = totalH;
		}

		// called from Move
		public Tray(Piece toRemove, Piece toPlace, Tray p, Piece[][] pt,
				int newF) {
			parent = p;
			pieces = new HashSet<Piece>();
			for (Piece piece : p.pieces) {
				pieces.add(new Piece(piece.coords, piece.id));
			}
			pieces.remove(toRemove);
			pieces.add(toPlace);
			piecesTable = pt;
			f = newF;
			move = toRemove.coords[0] + " " + toRemove.coords[1] + " "
					+ toPlace.coords[0] + " " + toPlace.coords[1];
		}

		public Tray getParent() {
			return parent;
		}

		public void setBounds(String[] bounds) {
			if (bounds.length != 2) {
				badBoard = true;
				return;
			}
			try {
				vBound = Integer.parseInt(bounds[0]);
				hBound = Integer.parseInt(bounds[1]);
			} catch (NumberFormatException e) {
				badBoard = true;
				return;
			}
			if (vBound < 0 || hBound < 0) {
				System.out.println("q");
				badBoard = true;
				return;
			}
			piecesTable = new Piece[vBound][hBound];
		}

		public boolean satisfies(ArrayList<Piece> goal) {
			for (Piece goalPiece : goal) {
				if (!pieces.contains(goalPiece)) {
					return false;
				}
			}
			return true;
		}

		public void addPiece(String[] piece) {
			if (piece.length != 4) {
				badBoard = true;
				return;
			}
			int[] p = new int[4];
			for (int i = 0; i < piece.length; i++) {
				try {
					p[i] = Integer.parseInt(piece[i]);
				} catch (NumberFormatException e) {
					badBoard = true;
					return;
				}
			}
			Piece nPiece = new Piece(p, nextPieceId);
			nextPieceId++;
			if (isOK(nPiece)) {
				pieces.add(nPiece);
			} else {
				badBoard = true;
				return;
			}
			for (int i = p[0]; i <= p[2]; i++) { //
				for (int j = p[1]; j <= p[3]; j++) {
					if (piecesTable[i][j] != null) {
						badBoard = true;
						return;
					}
					piecesTable[i][j] = nPiece;
				}
			}

		}

		public boolean isOK(Piece piece) {
			for (int i = 0; i < 4; i++) {
				if (i % 2 == 0) {
					if (piece.coords[i] >= vBound || piece.coords[i] < 0) {
						return false;
					}
				} else {
					if (piece.coords[i] >= hBound || piece.coords[i] < 0) {
						return false;
					}
				}
			}
			return true;
		}

		public int hashCode() {
			int hashCode = 0;
			for (Piece[] pieces : piecesTable) {
				for (Piece piece : pieces) {
					if (piece != null) {
						hashCode += 31 * hashCode + piece.height * 31
								+ piece.width;
					} else {
						hashCode += 31 * hashCode;
					}
				}
			}
			return hashCode;
		}

		public boolean equals(Object o) {
			Tray t = (Tray) o;
			return pieces.equals(t.pieces);
		}

		@Override
		public int compareTo(Object o) {
			Tray move = (Tray) o;
			if (f < move.f) {
				return -1;
			} else if (f > move.f) {
				return 1;
			}
			return 0;
		}

		public void getMoves() {
			for (Piece piece : pieces) {
				checkMoves(piece);
			}
		}

		private void checkMoves(Piece piece) {
			Piece pD = piece.getDown();
			Piece pU = piece.getUp();
			Piece pR = piece.getRight();
			Piece pL = piece.getLeft();

			Piece[][] L = occupied(pL, piece);
			if (L != null) {
				addMove(piece, pL, L);
			}
			Piece[][] R = occupied(pR, piece);
			if (R != null) {
				addMove(piece, pR, R);
			}
			Piece[][] U = occupied(pU, piece);
			if (U != null) {
				addMove(piece, pU, U);
			}
			Piece[][] D = occupied(pD, piece);
			if (D != null) {
				addMove(piece, pD, D);
			}
		}

		private void addMove(Piece piece, Piece movedPiece, Piece[][] ipt) {
			int oldH = piece.h;
			int newH = calculateHeuristic(movedPiece);
			movedPiece.h = newH;
			int updateTotalF = f - oldH + newH;
			Tray nMove = new Tray(new Piece(piece.coords, piece.id),
					movedPiece, this, ipt, updateTotalF);
			if (!visited.contains(nMove)) {
				moves.offer(nMove);
			}
		}

		private Piece[][] occupied(Piece potentialMove, Piece piece) {
			if (potentialMove == null) {
				return null;
			}
			int[] coords = piece.coords;
			Piece[][] temp = new Piece[vBound][hBound];
			for (int i = 0; i < vBound; i++) {
				for (int j = 0; j < hBound; j++) {
					temp[i][j] = piecesTable[i][j];
				}
			}
			for (int i = coords[0]; i <= coords[2]; i++) {
				for (int j = coords[1]; j <= coords[3]; j++) {
					temp[i][j] = null;
				}
			}
			int p0 = potentialMove.coords[0];
			int p1 = potentialMove.coords[1];
			int p2 = potentialMove.coords[2];
			int p3 = potentialMove.coords[3];
			for (int i = p0; i <= p2; i++) {
				for (int j = p1; j <= p3; j++) {
					if (temp[i][j] != null) {
						return null;
					} else {
						temp[i][j] = potentialMove;
					}
				}
			}
			return temp;
		}

		private int calculateHeuristic(Piece move) {
			int lowestSeenH = vBound + hBound;
			for (Piece goalPiece : goal) {
				int distance = move.distanceTo(goalPiece);
				if (move.getType().equals(goalPiece.getType())) {
					if (distance < lowestSeenH) {
						lowestSeenH = distance;
					}
				}
			}
			return lowestSeenH;
		}

	}

	public class Piece {

		private int[] coords;
		private String type;
		private int id;
		private int h;
		private int height;
		private int width;

		public Piece(int[] pieceCoords, int pid) {
			coords = pieceCoords;
			int translatedV = coords[2] - coords[0];
			int translatedH = coords[3] - coords[1];
			height = translatedV + 1;
			width = translatedH + 1;
			type = "" + translatedV + translatedH;
			id = pid;
		}

		public String getType() {
			return type;
		}

		public void setH(int update) {
			h = update;
		}

		public Piece(int zero, int one, int two, int three, int pid) {
			coords = new int[] { zero, one, two, three };
			int translatedV = coords[2] - coords[0];
			int translatedH = coords[3] - coords[1];
			type = "" + translatedV + translatedH;
			height = translatedV + 1;
			width = translatedH + 1;
			id = pid;
		}

		public boolean equals(Object o) {
			Piece f = (Piece) o;
			for (int i = 0; i < 4; i++) {
				if (coords[i] != f.coords[i]) {
					return false;
				}
			}
			return true;
		}

		public int hashCode() {
			int hashCode = 0;
			for (int coord : coords) {
				hashCode += 92821 * hashCode + coord;
			}
			return hashCode;
		}

		public int distanceTo(Piece piece) {
			int mDistance = 0;
			for (int i = 0; i < 2; i++) {
				mDistance += Math.abs(coords[i] - piece.coords[i]);
			}
			return mDistance;
		}

		public Piece getLeft() {
			if (coords[1] - 1 < 0) {
				return null;
			} else {
				return new Piece(coords[0], coords[1] - 1, coords[2],
						coords[3] - 1, id);
			}
		}

		public Piece getRight() {
			if (coords[3] + 1 >= hBound) {
				return null;
			} else {
				return new Piece(coords[0], coords[1] + 1, coords[2],
						coords[3] + 1, id);
			}
		}

		public Piece getUp() {
			if (coords[0] - 1 < 0) {
				return null;
			} else {
				return new Piece(coords[0] - 1, coords[1], coords[2] - 1,
						coords[3], id);
			}
		}

		public Piece getDown() {
			if (coords[2] + 1 >= vBound) {
				return null;
			} else {
				return new Piece(coords[0] + 1, coords[1], coords[2] + 1,
						coords[3], id);
			}
		}

		public String toString() {
			return "" + coords[0] + " " + coords[1] + " " + coords[2] + " "
					+ coords[3];
		}

	}

	public static void main(String[] args) {
		Solver solver = new Solver();
		if (args.length != 2) {
			System.out.println("Invalid init and/or goal file.");
			return;
		}
		solver.readTray(args[0]);
		if (solver.badBoard) {
			System.out.println("Invalid init and/or goal file.");
			return;
		}
		solver.readGoal(args[1]);
		if (solver.badBoard) {
			System.out.println("Invalid init and/or goal file.");
			return;
		}
		solver.traverse();

	}

}
