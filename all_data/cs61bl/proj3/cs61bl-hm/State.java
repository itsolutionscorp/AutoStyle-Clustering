import java.util.*;
import java.awt.Point;

public class State {

	HashMap<Point, Block> byTopLeftCoord;
	HashSet<Point> emptyCoords;
	HashMap<Integer, Point> byBlockID = new HashMap<Integer, Point>();
	int length = 0;
	int width = 0;
	String moveString = "";
	int[][] tray;

	public State(HashMap<Point, Block> coords, HashSet<Point> empties,
			int length, int width) {
		emptyCoords = empties;
		byTopLeftCoord = coords;
		this.length = length;
		this.width = width;
	}

	public State(HashMap<Point, Block> coords, HashSet<Point> empties,
			int length, int width, int[][] representation,
			HashMap<Integer, Point> ids) {
		this(coords, empties, length, width);
		this.tray = new int[length][width];
		for (int i = 0; i < length; i++){
			tray[i] = representation[i].clone();
		}
		for (int x : ids.keySet()) {
			byBlockID.put(x, (Point) ids.get(x).clone());
		}
		
	}

	public State(HashMap<Point, Block> coords, int length, int width) {
		byTopLeftCoord = coords;
		emptyCoords = new HashSet<Point>(5);
		this.length = length;
		this.width = width;
	}

	public State(State toCopy, Point toMove, int movement) {
		length = toCopy.length;
		width = toCopy.width;
		emptyCoords = new HashSet<Point>(toCopy.emptyCoords.size());
		byTopLeftCoord = new HashMap<Point, Block>(toCopy.byTopLeftCoord.size());
		for (Point p : toCopy.emptyCoords) {
			emptyCoords.add((Point) p.clone());
		}
		for (Point p : toCopy.byTopLeftCoord.keySet()) {
			byTopLeftCoord.put((Point) p.clone(), new Block(
					toCopy.byTopLeftCoord.get(p)));
		}
		moveString = (int) toMove.getX() + " " + (int) toMove.getY() + " ";
		switch (movement) {
		case 0:
			moveUp(toMove);
			break;
		case 1:
			moveDown(toMove);
			break;
		case 2:
			moveLeft(toMove);
			break;
		case 3:
			moveRight(toMove);
			break;
		}
	}

	public State(State toCopy, Point toMove, int movement,
			int[][] representation, HashMap<Integer, Point> ids) {
		length = toCopy.length;
		width = toCopy.width;
		emptyCoords = new HashSet<Point>(toCopy.emptyCoords.size());
		byTopLeftCoord = new HashMap<Point, Block>(toCopy.byTopLeftCoord.size());
		for (Point p : toCopy.emptyCoords) {
			emptyCoords.add((Point) p.clone());
		}
		for (Point p : toCopy.byTopLeftCoord.keySet()) {
			byTopLeftCoord.put((Point) p.clone(), new Block(
					toCopy.byTopLeftCoord.get(p)));
		}
		tray = new int[length][width];
		for (int i = 0; i < length; i++){
			tray[i] = representation[i].clone();
		}
		for (int x : ids.keySet()) {
			byBlockID.put(x, (Point) ids.get(x).clone());
		}
		moveString = (int) toMove.getX() + " " + (int) toMove.getY() + " ";
		switch (movement) {
		case 0:
			moveUp(toMove);
			break;
		case 1:
			moveDown(toMove);
			break;
		case 2:
			moveLeft(toMove);
			break;
		case 3:
			moveRight(toMove);
			break;
		}
	}

	public boolean canMoveRight(Point topLeft) {
		Block b = byTopLeftCoord.get(topLeft);
		if (b.bottomRight.getY() + 1 >= width)
			return false;
		for (int i = (int) b.topLeft.getX(); i <= (int) b.bottomRight.getX(); i++) {
			if (!emptyCoords.contains(new Point(i,
					(int) b.bottomRight.getY() + 1)))
				return false;
		}
		return true;
	}

	public boolean canMoveLeft(Point topLeft) {
		Block b = byTopLeftCoord.get(topLeft);
		if (b.topLeft.getY() - 1 < 0)
			return false;
		for (int i = (int) b.topLeft.getX(); i <= (int) b.bottomRight.getX(); i++) {
			if (!emptyCoords.contains(new Point(i, (int) b.topLeft.getY() - 1)))
				return false;
		}
		return true;
	}

	public boolean canMoveUp(Point topLeft) {
		Block b = byTopLeftCoord.get(topLeft);
		if (b.topLeft.getX() - 1 < 0) {
			return false;
		}
		for (int i = (int) b.topLeft.getY(); i <= (int) b.bottomRight.getY(); i++) {
			if (!emptyCoords.contains(new Point((int) b.topLeft.getX() - 1, i)))
				return false;
		}
		return true;
	}

	public boolean canMoveDown(Point topLeft) {
		Block b = byTopLeftCoord.get(topLeft);
		if (b.bottomRight.getX() + 1 >= length) {
			return false;
		}
		for (int i = (int) b.topLeft.getY(); i <= (int) b.bottomRight.getY(); i++) {
			if (!emptyCoords.contains(new Point((int) b.bottomRight.getX() + 1,
					i)))
				return false;
		}
		return true;
	}

	public void moveLeft(Point topLeft) {
		Block b = byTopLeftCoord.get(topLeft);
		for (int i = (int) b.topLeft.getX(); i <= (int) b.bottomRight.getX(); i++) {
			emptyCoords.remove(new Point(i, (int) b.topLeft.getY() - 1));
			emptyCoords.add(new Point(i, (int) b.bottomRight.getY()));
			if (Solver.usingEmptyIterator){
				tray[i][(int) b.topLeft.getY() - 1] = b.id;
				tray[i][(int) b.bottomRight.getY()] = 0;
			}
		}
		byTopLeftCoord.remove(topLeft);
		b.topLeft.y -= 1;
		b.bottomRight.y -= 1;
		byTopLeftCoord.put(b.topLeft, b);
		if (Solver.usingEmptyIterator){
			byBlockID.remove(b.id);
			byBlockID.put(b.id, b.topLeft);
		}
		moveString += (int) b.topLeft.getX() + " " + (int) b.topLeft.getY();
	}

	public void moveRight(Point topLeft) {
		Block b = byTopLeftCoord.get(topLeft);
		for (int i = (int) b.topLeft.getX(); i <= (int) b.bottomRight.getX(); i++) {
			emptyCoords.remove(new Point(i, (int) b.bottomRight.getY() + 1));
			emptyCoords.add(new Point(i, (int) b.topLeft.getY()));
			if (Solver.usingEmptyIterator){
				tray[i][(int) b.bottomRight.getY()+ 1] = b.id;
				tray[i][(int) b.topLeft.getY()] = 0;
			}
		}
		byTopLeftCoord.remove(topLeft);
		b.topLeft.y += 1;
		b.bottomRight.y += 1;
		byTopLeftCoord.put(b.topLeft, b);
		if (Solver.usingEmptyIterator){
			byBlockID.remove(b.id);
			byBlockID.put(b.id, b.topLeft);
		}
		moveString += (int) b.topLeft.getX() + " " + (int) b.topLeft.getY();
	}

	public void moveUp(Point topLeft) {
		Block b = byTopLeftCoord.get(topLeft);
		for (int i = (int) b.topLeft.getY(); i <= (int) b.bottomRight.getY(); i++) {
			emptyCoords.remove(new Point((int) b.topLeft.getX() - 1, i));
			emptyCoords.add(new Point((int) b.bottomRight.getX(), i));
			if (Solver.usingEmptyIterator){
				tray[(int) b.topLeft.getX() - 1][i] = b.id;
				tray[(int) b.bottomRight.getX()][i] = 0;
			}
		}
		byTopLeftCoord.remove(topLeft);
		b.topLeft.x -= 1;
		b.bottomRight.x -= 1;
		byTopLeftCoord.put(b.topLeft, b);
		if (Solver.usingEmptyIterator){
			byBlockID.remove(b.id);
			byBlockID.put(b.id, b.topLeft);
		}
		moveString += (int) b.topLeft.getX() + " " + (int) b.topLeft.getY();
	}

	public void moveDown(Point topLeft) {
		Block b = byTopLeftCoord.get(topLeft);
		for (int i = (int) b.topLeft.getY(); i <= (int) b.bottomRight.getY(); i++) {
			emptyCoords.remove(new Point((int) b.bottomRight.getX() + 1, i));
			emptyCoords.add(new Point((int) b.topLeft.getX(), i));
			if (Solver.usingEmptyIterator){
				tray[(int) b.bottomRight.getX() + 1][i] = b.id;
				tray[(int) b.topLeft.getX()][i] = 0;
			}
		}
		byTopLeftCoord.remove(topLeft);
		b.topLeft.x += 1;
		b.bottomRight.x += 1;
		byTopLeftCoord.put(b.topLeft, b);
		if (Solver.usingEmptyIterator){
			byBlockID.remove(b.id);
			byBlockID.put(b.id, b.topLeft);
		}
		moveString += (int) b.topLeft.getX() + " " + (int) b.topLeft.getY();
	}

	public Iterator<State> iterator() {
		return new PossibilityIterator(this);
	}
	
	public Iterator<State> emptyIterator(){
		return new EmptySquaresIterator(this);
	}

	public class PossibilityIterator implements Iterator<State> {
		int count = 0;
		Iterator<Point> topLeftIterator;
		Point currentPoint;
		boolean hasNext = true;
		State state;

		public PossibilityIterator(State state) {
			topLeftIterator = byTopLeftCoord.keySet().iterator();
			currentPoint = topLeftIterator.next();
			this.state = state;
		}

		public boolean hasNext() {
			return hasNext;
		}

		public State next() {
			while (true) {
				if (!hasNext())
					return null;
				if (count == 4) {
					count = 0;
					if (topLeftIterator.hasNext())
						currentPoint = topLeftIterator.next();
					else
						return null;
				}
				switch (count) { // 0: up, 1: down, 2: left, 3: right
				case 0:
					if (canMoveUp(currentPoint)) {
						count++;
						return new State(state, currentPoint, 0);
					}
					break;
				case 1:
					if (canMoveDown(currentPoint)) {
						count++;
						return new State(state, currentPoint, 1);
					}
					break;
				case 2:
					if (canMoveLeft(currentPoint)) {
						count++;
						return new State(state, currentPoint, 2);
					}
					break;
				case 3:
					if (canMoveRight(currentPoint)) {
						count++;
						return new State(state, currentPoint, 3);
					}
					break;
				}
				count++;
			}
		}
	}

	public class EmptySquaresIterator implements Iterator<State> {

		Iterator<Point> emptyIterator;
		int count = 0;
		Point currentPoint;
		boolean hasNext;
		int currentX;
		int currentY;
		State state;

		public EmptySquaresIterator(State state) {
			emptyIterator = emptyCoords.iterator();
			hasNext = emptyIterator.hasNext();
			if (hasNext) {
				currentPoint = emptyIterator.next();
				currentX = (int) currentPoint.getX();
				currentY = (int) currentPoint.getY();
			}
			this.state = state;
		}

		public boolean hasNext() {
			return hasNext;
		}

		public State next() {
			while (true) {
				if (!hasNext())
					return null;
				if (count == 4) {
					count = 0;
					if (emptyIterator.hasNext()) {
						currentPoint = emptyIterator.next();
						currentX = (int) currentPoint.getX();
						currentY = (int) currentPoint.getY();
					} else
						return null;
				}
				switch (count) { // 0: up, 1: down, 2: left, 3: right
				case 0:
					if (currentX - 1 >= 0 && tray[currentX - 1][currentY] != 0) {
						int blockID = tray[currentX - 1][currentY];
						if (canMoveDown(byBlockID.get(blockID))) {
							count++;
							return new State(state, byBlockID.get(blockID), 1, tray, byBlockID);
						}
					}
					break;
				case 1:
					if (currentX + 1 < length
							&&  tray[currentX + 1][currentY] != 0) {
						int blockID =  tray[currentX + 1][currentY];
						if (canMoveUp(byBlockID.get(blockID))) {
							count++;
							return new State(state, byBlockID.get(blockID), 0, tray, byBlockID);
						}
					}
					break;
				case 2:
					if (currentY - 1 >= 0 && tray[currentX][currentY - 1] != 0) {
						int blockID = tray[currentX][currentY - 1];
						if (canMoveRight(byBlockID.get(blockID))) {
							count++;
							return new State(state, byBlockID.get(blockID), 3, tray, byBlockID);
						}
					}
					break;
				case 3:
					if (currentY + 1 < width
							&& tray[currentX][currentY + 1] != 0) {
						int blockID = tray[currentX][currentY + 1];
						if (canMoveLeft(byBlockID.get(blockID))) {
							count++;
							return new State(state, byBlockID.get(blockID), 2, tray, byBlockID);
						}
					}
					break;
				}
				count++;
			}
		}
	}

	public boolean equals(Object o) {
		State other = (State) o;
		for (Point topLeft : other.byTopLeftCoord.keySet()) {
			if (!byTopLeftCoord.containsKey(topLeft))
				return false;
			else {
				Point theirBottomRight = other.byTopLeftCoord.get(topLeft).bottomRight;
				Point ourBottomRight = byTopLeftCoord.get(topLeft).bottomRight;
				if (!ourBottomRight.equals(theirBottomRight)) {
					return false;
				}
			}
		}
		return true;
	}

	public int hashCode() {
		int runningCountX = 0;
		int runningCountY = 0;
		for (Point p : emptyCoords) {
			runningCountX += p.getX();
			runningCountY += p.getY();
		}
		return runningCountX * 10 + runningCountY;
	}

	public String toString() {
		int[][] rep = new int[length][width];
		String runningString = "";
		runningString += "Blocks: \n";
		for (Point x : byTopLeftCoord.keySet()) {
			Block b = byTopLeftCoord.get(x);
			runningString += b.toString();
			Point topLeftCoord = b.topLeft;
			Point bottomRightCoord = b.bottomRight;
			for (int i = (int) topLeftCoord.getX(); i <= (int) bottomRightCoord
					.getX(); i++) {
				for (int j = (int) topLeftCoord.getY(); j <= (int) bottomRightCoord
						.getY(); j++) {
					rep[i][j] = b.id;
				}
			}
		}
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				runningString += rep[i][j] + "    ";
			}
			runningString += "\n";
		}
		runningString += "Empty:  \n";
		if (emptyCoords != null)
			for (Point x : emptyCoords) {
				runningString += x.toString() + "\n";
			}
		runningString += "MoveString: " + moveString + "\n";
		return runningString;
	}
}
