import java.awt.Point; 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.Map.Entry;

public class Tray {
	static List<int[]> myGoal = new ArrayList<int[]>();
	static Set<TrayBoard> visited = new HashSet<TrayBoard>();
	static Map<TrayBoard, Status> steps = new HashMap<TrayBoard, Status>();
	static List<Point> targetEmpty = new ArrayList<Point>();
	static int LengthOfTray, WidthOfTray;
	TrayBoard myTray;
	Map<Point, Point> coordinateSize;
	Set<Point> space;
	double CostInDepth;

	public Tray(String path1, String path2) {
		coordinateSize = new HashMap<Point, Point>();
		myTray = new TrayBoard(InitialArraySetUp(path1, coordinateSize));
		GoalArray(path2);
		CheckGoal();
		space = new HashSet<Point>();
		
		Point[][] board = myTray.myTracker;
		for (int length = 0; length < board.length; length++) {
			for (int width = 0; width < board[0].length; width++) {
				if (board[length][width] == null) {
					space.add(new Point(width, length));
				}
			}
		}
		
		int[][] temp = new int[myTray.myTracker.length][myTray.myTracker[0].length];
		int m = 1;
		for (int[] k : myGoal) {
			for (int y = k[0]; y <= k[2]; y++) {
				for (int x = k[1]; x <= k[3]; x++) {
					if (temp[y][x] != 0) {
						throw new IllegalArgumentException("wrong board");
					}
					temp[y][x] = m;
				}
			}
			m++;
		}
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[0].length; j++) {
				if (temp[i][j] == 0) {
					targetEmpty.add(new Point(j, i));
				}
			}
		}
	
	}

	private Tray(TrayBoard myTray, Map<Point, Point> coordinateSize, Set<Point> space) {
		this.myTray = myTray;
		this.coordinateSize = coordinateSize;
		this.space = space;
	}

	private Point[][] InitialArraySetUp(String path, Map<Point, Point> coordinateSize)  {

			Scanner scan;
			try {
				scan = new Scanner(new File(path));
			} catch (FileNotFoundException e) {
				System.out.println("Invalid init and/or goal file.");
				System.exit(1);
				return null;
			}
			Point[][] temp = new Point[scan.nextInt()][scan.nextInt()];
			LengthOfTray = temp.length;
			WidthOfTray = temp[0].length;
			while (scan.hasNext()) {
				int[] Coordinate = new int[4];
				for (int i = 0; i < 4; i++) {
					if (!scan.hasNext()) {
						scan.close();
						System.out.println("Invalid init and/or goal file.");
						System.exit(1);
						return null;
					}
					Coordinate[i] = scan.nextInt();
				}
				if (Coordinate[0] >= LengthOfTray 
						|| Coordinate[0] < 0 
						|| Coordinate[2] >= LengthOfTray 
						|| Coordinate[2] < 0
						|| Coordinate[1] >= WidthOfTray 
						|| Coordinate[1] < 0 
						|| Coordinate[3] >= WidthOfTray 
						|| Coordinate[3] < 0) {
					scan.close();
					System.out.println("Invalid init and/or goal file.");
					System.exit(1);
					return null;
				}
				if (Coordinate[1] > Coordinate[3] || Coordinate[0] > Coordinate[2]) {
					scan.close();
					System.out.println("Invalid init and/or goal file.");
					System.exit(1);
					return null;
				}
				Point p = new Point(Coordinate[1], Coordinate[0]);
				Point size = new Point(Coordinate[3] - Coordinate[1] + 1, Coordinate[2] - Coordinate[0] + 1);
				for (int length = Coordinate[0]; length <= Coordinate[2]; length++) {
					for (int width = Coordinate[1]; width <= Coordinate[3]; width++) {
						if (temp[length][width] != null) {
							scan.close();
							System.out.println("Invalid init and/or goal file.");
							System.exit(1);
							return null;
						}
						temp[length][width] = p;
						coordinateSize.put(p, size);
					}
				}
			}
			scan.close();
			return temp;
	}

	
	private static void GoalArray(String path) {
		Scanner FileScanner;
		try {
			FileScanner = new Scanner(new File(path));
			myGoal = new ArrayList<int[]>();
			while (FileScanner.hasNext()) {
				int[] coordinate = new int[4];
				for (int i = 0; i < 4; i++) {
					if (!FileScanner.hasNext()) {
						FileScanner.close();
						System.out.println("Invalid init and/or goal file.");
						System.exit(1);
					}
					coordinate[i] = FileScanner.nextInt();
				}
				if (coordinate[0] >= LengthOfTray 
						|| coordinate[0] < 0 
						|| coordinate[2] >= LengthOfTray 
						|| coordinate[2] < 0
						|| coordinate[1] >= WidthOfTray 
						|| coordinate[1] < 0 
						|| coordinate[3] >= WidthOfTray 
						|| coordinate[3] < 0) {
					FileScanner.close();
					System.out.println("Invalid init and/or goal file.");
					System.exit(1);
				}
				if (coordinate[1] > coordinate[3] || coordinate[0] > coordinate[2]) {
					FileScanner.close();
					System.out.println("Invalid init and/or goal file.");
					System.exit(1);
				}
				myGoal.add(coordinate);
			}
			FileScanner.close();
		}catch(FileNotFoundException e) {
			System.out.println("Invalid init and/or goal file.");
			System.exit(1);
		}
	}

	private void CheckGoal() {

			Map<Point, Integer> blockF = new HashMap<Point, Integer>();
			for (Entry<Point, Point> entry : coordinateSize.entrySet()) {
				if (blockF.containsKey(entry.getValue())) {
					blockF.put(entry.getValue(), blockF.get(entry.getValue()) + 1);
				} else {
					blockF.put(entry.getValue(), 1);
				}
			}
			Map<Point, Integer> goalBlockF = new HashMap<Point, Integer>();
			for (int[] k : myGoal) {
				Point temp = new Point(k[3] - k[1] + 1, k[2] - k[0] + 1);
				if (goalBlockF.containsKey(temp)) {
					goalBlockF.put(temp, blockF.get(temp) + 1);
				} else {
					goalBlockF.put(temp, 1);
				}
			}
			for (Entry<Point, Integer> entry : goalBlockF.entrySet()) {
				if (!blockF.containsKey(entry.getKey())) {
					System.out.println();
					System.exit(1);
				} else if (blockF.get(entry.getKey()) < entry.getValue()) {
					System.out.println();
					System.exit(1);
				}
			}

	}

	private static boolean isDone(Tray t) {
		for (int[] i : myGoal) {
			int y1, y2, x1, x2;
			y1 = i[0];
			x1 = i[1];
			y2 = i[2];
			x2 = i[3];
			Point p1 = new Point(x1, y1);
			// first check if the point at x1,y1 has a block existing;
			if (t.coordinateSize.containsKey(p1)) {
				// then check if the existing block has the exact same shape as the goal required;
				Point size = t.coordinateSize.get(p1);
				if (((y2 == y1 + size.y - 1) && (x2 == x1 + size.x - 1))) {
					return true;
				}
			}
		}
		return false;
	}
	
	private void movePiece(Motion Move) {
		Point[][] board = myTray.myTracker;
		int[] move = Move.myMove;
		int x1, x2, y1, y2;
		y1 = move[0];
		x1 = move[1];
		y2 = move[2];
		x2 = move[3];
		Point p1 = new Point(x1, y1);
		Point p2 = new Point(x2, y2);
		Point size = coordinateSize.remove(p1);
		int v = (int) size.getY();
		int h = (int) size.getX();
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < v; j++) {
				board[y1 + j][x1 + i] = null;
			}
		}
		if (y2 - y1 == 1) {
			int i=0;
			while (i<h) {
				space.add(new Point(x1 + i, y1));
				space.remove(new Point(x1 + i, y1 + v));
				for (int j = 0; j < v; j++) {
					board[y2 + j][x1 + i] = p2;
				}
				i++;
			}
			coordinateSize.put(p2, size);
			
		} else if (y1 - y2 == 1) {
			int i=0;
			while (i<h) {
				space.add(new Point(x1 + i, y2 + v));
				space.remove(new Point(x1 + i, y2));
				for (int j = 0; j < v; j++) {
					board[y2 + j][x1 + i] = p2;
				}
				i++;
			}
			coordinateSize.put(p2, size);
			
		} else if (x1 - x2 == 1) {
			int i=0;
			while (i<v) {
				space.add(new Point(x2 + h, y1 + i));
				space.remove(new Point(x2, y1 + i));
				for (int j = 0; j < h; j++) {
					board[y1 + i][x2 + j] = p2;
				}
				i++;
			}
			coordinateSize.put(p2, size);
			
		} else if (x2 - x1 == 1) {
			int i=0;
			while (i<v) {
				space.add(new Point(x1, y1 + i));
				space.remove(new Point(x1 + h, y1 + i));
				for (int j = 0; j < h; j++) {
					board[y1 + i][x2 + j] = p2;
				}
				i++;
			}
			coordinateSize.put(p2, size);
		}
	}

	private static List<Motion> possibleMove(Tray tray) {
		Set<Motion> moves = new HashSet<Motion>();
		List<Motion> toReturn = new ArrayList<Motion>();
		for (Point spaceCoor : tray.space) {
			int ycoor = (int) spaceCoor.getY();
			int xcoor = (int) spaceCoor.getX();
			
			if (xcoor > 0) {
				if (tray.myTray.myTracker[ycoor][xcoor - 1] != null) {
					Point coor = tray.myTray.myTracker[ycoor][xcoor - 1];
					Point size = tray.coordinateSize.get(coor);
					boolean CouldBeAbleToMove = true;
					int vcoor = (int) coor.getY();
					int y=0;
					while (y<size.getY()) {
						try {
							if (tray.myTray.myTracker[vcoor + y][xcoor] != null) {
								CouldBeAbleToMove = false;
							}
						} catch (ArrayIndexOutOfBoundsException e) {
							CouldBeAbleToMove = false;
						}
						y++;
					}
					if (CouldBeAbleToMove) {
						int[] temp = new int[4];
						temp[0] = (int) coor.getY();
						temp[1] = (int) coor.getX();
						temp[2] = temp[0];
						temp[3] = temp[1] + 1;
						moves.add(new Motion(temp));
					}
				}
			}
			if (xcoor + 1 < tray.myTray.myTracker[0].length) {
				if (tray.myTray.myTracker[ycoor][xcoor + 1] != null) {
					Point coor = tray.myTray.myTracker[ycoor][xcoor + 1];
					Point size = tray.coordinateSize.get(coor);
					boolean CouldBeAbleToMove = true;
					int vcoor = (int) coor.getY();
					int y=0;
					while (y<size.getY()) {
						try {
							if (tray.myTray.myTracker[vcoor + y][xcoor] != null) {
								CouldBeAbleToMove = false;
							}
						} catch (ArrayIndexOutOfBoundsException e) {
							CouldBeAbleToMove = false;
						}
						y++;
					}
					if (CouldBeAbleToMove) {
						int[] temp = new int[4];
						temp[0] = (int) coor.getY();
						temp[1] = (int) coor.getX();
						temp[2] = temp[0];
						temp[3] = temp[1] - 1;
						moves.add(new Motion(temp));
					}
				}
			}
			if (ycoor > 0) {
				if (tray.myTray.myTracker[ycoor - 1][xcoor] != null) {
					Point coor = tray.myTray.myTracker[ycoor - 1][xcoor];
					Point size = tray.coordinateSize.get(coor);
					boolean CouldBeAbleToMove = true;
					int hcoor = (int) coor.getX();
					int x=0;
					while(x < size.getX()) {
						try {
							if (tray.myTray.myTracker[ycoor][hcoor + x] != null) {
								CouldBeAbleToMove = false;
							}
						} catch (ArrayIndexOutOfBoundsException e) {
							CouldBeAbleToMove = false;
						}
						x++;
					}
					if (CouldBeAbleToMove) {
						int[] temp = new int[4];
						temp[0] = (int) coor.getY();
						temp[1] = (int) coor.getX();
						temp[2] = temp[0] + 1;
						temp[3] = temp[1];
						moves.add(new Motion(temp));
					}
				}
			}
			if (ycoor + 1 < tray.myTray.myTracker.length) {
				if (tray.myTray.myTracker[ycoor + 1][xcoor] != null) {
					Point coor = tray.myTray.myTracker[ycoor + 1][xcoor];
					Point size = tray.coordinateSize.get(coor);
					boolean CouldBeAbleToMove = true;
					int hcoor = (int) coor.getX();
					int x=0;
					while(x < size.getX()) {
						try {
							if (tray.myTray.myTracker[ycoor][hcoor + x] != null) {
								CouldBeAbleToMove = false;
							}
						} catch (ArrayIndexOutOfBoundsException e) {
							CouldBeAbleToMove = false;
						}
						x++;
					}
					if (CouldBeAbleToMove) {
						int[] temp = new int[4];
						temp[0] = (int) coor.getY();
						temp[1] = (int) coor.getX();
						temp[2] = temp[0] - 1;
						temp[3] = temp[1];
						moves.add(new Motion(temp));
					}
				}
			}
		}
		for (Motion i : moves) {
			toReturn.add(i);
		}
		return toReturn;
	}

	private static List<Motion> possibleMoveFromPiece(Tray tray) {
		List<Motion> movesMade = new ArrayList<Motion>();
		for (Entry<Point, Point> entry : tray.coordinateSize.entrySet()) {
			Point coor = entry.getKey();
			Point size = entry.getValue();
			if (coor.getX() > 0) {
				int xcoor = (int) coor.getX() - 1;
				boolean CouldBeAbleToMove = true;
				int vcoor = (int) coor.getY();
				int y=0;
				while (y<size.getY()) {
					try {
						if (tray.myTray.myTracker[vcoor + y][xcoor] != null) {
							CouldBeAbleToMove = false;
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						CouldBeAbleToMove = false;
					}
					y++;
				}
				if (CouldBeAbleToMove) {
					int[] temp = new int[4];
					temp[0] = (int) coor.getY();
					temp[1] = (int) coor.getX();
					temp[2] = temp[0];
					temp[3] = temp[1] - 1;
					movesMade.add(new Motion(temp));
				}
			}
			if (coor.getX() + size.getX() < tray.myTray.myTracker[0].length) {
				int xcoor = (int) coor.getX() + (int) size.getX();
				boolean CouldBeAbleToMove = true;
				int vcoor = (int) coor.getY();
				int y=0;
				while (y<size.getY()) {
					try {
						if (tray.myTray.myTracker[vcoor + y][xcoor] != null) {
							CouldBeAbleToMove = false;
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						CouldBeAbleToMove = false;
					}
					y++;
				}
				if (CouldBeAbleToMove) {
					int[] temp = new int[4];
					temp[0] = (int) coor.getY();
					temp[1] = (int) coor.getX();
					temp[2] = temp[0];
					temp[3] = temp[1] + 1;
					movesMade.add(new Motion(temp));
				}
			}
			if (coor.getY() > 0) {
				int ycoor = (int) coor.getY() - 1;
				boolean CouldBeAbleToMove = true;
				int hcoor = (int) coor.getX();
				int x=0;
				while(x < size.getX()) {
					try {
						if (tray.myTray.myTracker[ycoor][hcoor + x] != null) {
							CouldBeAbleToMove = false;
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						CouldBeAbleToMove = false;
					}
					x++;
				}
				if (CouldBeAbleToMove) {
					int[] temp = new int[4];
					temp[0] = (int) coor.getY();
					temp[1] = (int) coor.getX();
					temp[2] = temp[0] - 1;
					temp[3] = temp[1];
					movesMade.add(new Motion(temp));
				}
			}
			if (coor.getY() + size.getY() < tray.myTray.myTracker.length) {
				int ycoor = (int) coor.getY() + (int) size.getY();
				boolean CouldBeAbleToMove = true;
				int hcoor = (int) coor.getX();
				int x=0;
				while(x < size.getX()) {
					try {
						if (tray.myTray.myTracker[ycoor][hcoor + x] != null) {
							CouldBeAbleToMove = false;
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						CouldBeAbleToMove = false;
					}
					x++;
				}
				if (CouldBeAbleToMove) {
					int[] temp = new int[4];
					temp[0] = (int) coor.getY();
					temp[1] = (int) coor.getX();
					temp[2] = temp[0] + 1;
					temp[3] = temp[1];
					movesMade.add(new Motion(temp));
				}
			}
		}
		return movesMade;
	}

	public static List<Motion> Path(Tray tray) {
		TrayBoard Board = null;
		if (targetEmpty.size() == 1) {
			Board = pathLarge(tray);
		} else if (tray.space.size() <= tray.coordinateSize.size()){
			Board = pathNormal(tray);
		} else if (tray.space.size() > tray.coordinateSize.size()) {
			Board = pathFromPiece(tray);
		}

		if (Board != null) {
			ArrayList<Motion> tempO = new ArrayList<Motion>();
			Status i = Tray.steps.get(Board);
			while (i.prevTray != null) {
				tempO.add(0, i.move);
				Board = i.prevTray.myTray;
				i = Tray.steps.get(Board);
			}
			List<Motion> moves = new ArrayList<Motion>();
			while (!tempO.isEmpty()) {
				moves.add(tempO.remove(0));
			}
			return moves;
		}
		return new ArrayList<Motion>();
	}

	private static TrayBoard pathNormal(Tray tray) {
		if (Tray.visited.contains(tray.myTray)) {
			return null;
		} else {
			steps.put(tray.myTray, new Status(null, tray, null));
			List<Motion> possibleMoves = possibleMove(tray);
			Stack<Status> possibleMovements = new Stack<Status>();
			possibleMovements.push(new Status(null, tray, null));
			while (!possibleMovements.isEmpty()) {
				Status e = possibleMovements.pop();
				tray = e.currentTray;
				if (!visited.contains(tray.myTray)) {
					visited.add(tray.myTray);
					if (Tray.isDone(tray)) {
						return e.currentTray.myTray;
					}
					possibleMoves = possibleMove(tray);
					for (Motion move : possibleMoves) {
						
					// copyFromSpace a 
						Set<Point> a = new HashSet<Point>();
						for (Point k : tray.space) {
							a.add(new Point(k.x, k.y));
						}
					//copyFromSize b
						Map<Point, Point> b = new HashMap<Point, Point>();
						for (Entry<Point, Point> entry : tray.coordinateSize.entrySet()) {
							Point p = entry.getKey();
							Point k = entry.getValue();

							b.put(new Point(p.x, p.y), new Point((int) k.x, (int) k.y));
						}
					//copyTrayBoard c
						Point[][] c1 = tray.myTray.myTracker;
						Point[][] c2 = new Point[c1.length][c1[0].length];
						for (int i = 0; i < c1.length; i++) {
							for (int j = 0; j < c1[0].length; j++) {
								if (c1[i][j] != null) {
									Point p = c1[i][j];
									c2[i][j] = new Point(p.x, p.y);
								}
							}
						}
						
						TrayBoard c = new TrayBoard(c2);

						Tray temp = new Tray(c, b, a);
						temp.movePiece(move);
						Status tempStatus = new Status(tray, temp, move);
						if (!steps.containsKey(temp.myTray)) {
							steps.put(temp.myTray, tempStatus);
							possibleMovements.add(tempStatus);
						}
					}
				}
			}
			return null;
		}
	}

	private static TrayBoard pathLarge(Tray tray) {
		if (Tray.visited.contains(tray.myTray)) {
			return null;
		} else {
			steps.put(tray.myTray, new Status(null, tray, null));
			tray.UpdateCostBigTray();
			Comparator<Status> PriorityComparator = new ComparatorForPriority();
			List<Motion> possibleMoves = possibleMove(tray);
			Queue<Status> possibleMovements = new PriorityQueue<Status>(10, PriorityComparator);
			possibleMovements.add(new Status(null, tray, null));
			
			while (!possibleMovements.isEmpty()) {
				Status e = possibleMovements.remove();
				tray = e.currentTray;
				if (!visited.contains(tray.myTray)) {
					visited.add(tray.myTray);
					if (Tray.isDone(tray)) {
						return e.currentTray.myTray;
					}
					possibleMoves = possibleMove(tray);
					for (Motion move : possibleMoves) {
						
					// copyFromSpace a 
						Set<Point> a = new HashSet<Point>();
						for (Point k : tray.space) {
							a.add(new Point(k.x, k.y));
						}
					//copyFromSize b
						Map<Point, Point> b = new HashMap<Point, Point>();
						for (Entry<Point, Point> entry : tray.coordinateSize.entrySet()) {
							Point p = entry.getKey();
							Point k = entry.getValue();

							b.put(new Point(p.x, p.y), new Point((int) k.x, (int) k.y));
						}
					//copyTrayBoard c
						Point[][] c1 = tray.myTray.myTracker;
						Point[][] c2 = new Point[c1.length][c1[0].length];
						for (int i = 0; i < c1.length; i++) {
							for (int j = 0; j < c1[0].length; j++) {
								if (c1[i][j] != null) {
									Point p = c1[i][j];
									c2[i][j] = new Point(p.x, p.y);
								}
							}
						}
						
						TrayBoard c = new TrayBoard(c2);

						Tray temp = new Tray(c, b, a);

						temp.movePiece(move);
						temp.UpdateCostBigTray();
						Status tempStatus = new Status(tray, temp, move);
						if (!steps.containsKey(temp.myTray)) {
							steps.put(temp.myTray, tempStatus);
							possibleMovements.add(tempStatus);
						}
					}
				}
			}
			return null;
		}
	}

	private void UpdateCostBigTray() {
		double CostSoFar = 0;
		Iterator<Point> iter = targetEmpty.iterator();
		while (iter.hasNext()) {
			Point hehe = iter.next();
			for (Point currentEmpty : space) {
				CostSoFar += Math.abs(hehe.getX() - currentEmpty.getX())
						+ Math.abs(hehe.getY() - currentEmpty.getY());
			}
		}
		CostInDepth = CostSoFar;
	}

	private static TrayBoard pathFromPiece(Tray tray) {
		if (Tray.visited.contains(tray.myTray)) {
			return null;
		} else {
			steps.put(tray.myTray, new Status(null, tray, null));
			tray.UpdateCostFromPiece();
			Comparator<Status> PriorityComparator = new ComparatorForPriority();
			List<Motion> TryMoves = possibleMoveFromPiece(tray);
			Queue<Status> possibleMovements = new PriorityQueue<Status>(10, PriorityComparator);
			
			possibleMovements.add(new Status(null, tray, null));
			while (!possibleMovements.isEmpty()) {
				Status e = possibleMovements.remove();
				tray = e.currentTray;
				if (!visited.contains(tray.myTray)) {
					visited.add(tray.myTray);
					if (Tray.isDone(tray)) {
						return e.currentTray.myTray;
					}
					TryMoves = possibleMoveFromPiece(tray);
					
					for (Motion move : TryMoves) {
					// copyFromSpace a 
						Set<Point> a = new HashSet<Point>();
						for (Point k : tray.space) {
							a.add(new Point(k.x, k.y));
						}
					//copyFromSize b
						Map<Point, Point> b = new HashMap<Point, Point>();
						for (Entry<Point, Point> entry : tray.coordinateSize.entrySet()) {
							Point p = entry.getKey();
							Point k = entry.getValue();

							b.put(new Point(p.x, p.y), new Point((int) k.x, (int) k.y));
						}
					//copyTrayBoard c
						Point[][] c1 = tray.myTray.myTracker;
						Point[][] c2 = new Point[c1.length][c1[0].length];
						for (int i = 0; i < c1.length; i++) {
							for (int j = 0; j < c1[0].length; j++) {
								if (c1[i][j] != null) {
									Point p = c1[i][j];
									c2[i][j] = new Point(p.x, p.y);
								}
							}
						}
						
						TrayBoard c = new TrayBoard(c2);

						Tray temp = new Tray(c, b, a);
						temp.movePiece(move);
						temp.UpdateCostFromPiece();
						Status tempStatus = new Status(tray, temp, move);
						if (!steps.containsKey(temp.myTray)) {
							steps.put(temp.myTray, tempStatus);
							possibleMovements.add(tempStatus);
						}
					}
				}
			}
			return null;
		}
	}

	private void UpdateCostFromPiece() {
		double CostSoFar = 0;
		for (int[] i : myGoal) {
			int y = i[3] - i[1] + 1;
			int x = i[2] - i[0] + 1;
			for (Entry<Point, Point> entry : coordinateSize.entrySet()) {
				Point coordinate = entry.getKey();
				Point size = entry.getValue();
				if (size.equals(new Point(y, x))) {
					CostSoFar += Math.abs(coordinate.getX() - i[1]) + Math.abs(coordinate.getY() - i[0]);
				}
			}
		}
		CostInDepth = CostSoFar;
	}

	


	public String toString() {
		StringBuilder X = new StringBuilder();
		X.append(myTray.toString());
		return X.toString();
	}

	@Override
	public int hashCode() {
		return myTray.hashCode();
	}

	@Override
	public boolean equals(Object m) {
		return myTray.equals(((Tray) m).myTray);
	}
}



