import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Tray implements Comparable<Tray> {
	private int myHeight;
	private int myWidth;
	private boolean[][] occupied;
	private double myPriority;
	private Tray myParent;
	private String myMoveString;
	private int hashCode;
	private Set<Tray> possibleMoves;
	private HashSet<Block> myBlocks;
	private HashMap<Integer, HashSet<Block>> shapes;

	public Tray(int height, int width) {
		myHeight = height;
		myWidth = width;
		occupied = new boolean[height][width];
		myBlocks = new HashSet<Block>();
		shapes = new HashMap<Integer, HashSet<Block>>();
		possibleMoves = new HashSet<Tray>();
	}

	public void setMoveString(String moveString) {
		myMoveString = moveString;
	}

	public String getMoveString() {
		return myMoveString;
	}

	public void setHashCode() {
		hashCode = myBlocks.hashCode();
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	public void setPriority(int priority) {
		myPriority = priority;
	}

	public double getPriority() {
		return myPriority;
	}

	public void addBlock(Block b) {
		myBlocks.add(b);
		for (int i = b.getTopLeft().x; i <= b.getBotRight().x; i++) {
			for (int j = b.getTopLeft().y; j <= b.getBotRight().y; j++) {
				if (i < 0 || i >= myHeight || j < 0 || j >= myWidth) {
					System.out.println("Invalid goal and/or init file.");
					throw new IllegalStateException();
				} else if (occupied[i][j] == true) {
					System.out.println("Invalid goal and/or init file.");
					throw new IllegalStateException();
				} else {
					occupied[i][j] = true;
				}
			}
		}
		Integer blockShape = b.shape();
		if (shapes.get(blockShape) == null) {
			shapes.put(blockShape, new HashSet<Block>());
		}
		shapes.get(blockShape).add(b);
	}

	private boolean isMovePossible(String direction, Block block) {
		Point topLeft = block.getTopLeft();
		Point botRight = block.getBotRight();
		Point topRight = new Point(topLeft.x, botRight.y);
		Point botLeft = new Point(botRight.x, topLeft.y);
		if (direction.equals("up")) {
			if (topLeft.x - 1 < 0) {
				return false;
			} else {
				for (int i = topLeft.y; i <= topRight.y; i++) {
					if (occupied[topLeft.x - 1][i]) {
						return false;
					}
				}
			}
		} else if (direction.equals("down")) {
			if (botRight.x + 1 >= myHeight) {
				return false;
			} else {
				for (int i = botLeft.y; i <= botRight.y; i++) {
					if (occupied[botRight.x + 1][i]) {
						return false;
					}
				}
			}
		} else if (direction.equals("left")) {
			if (topLeft.y - 1 < 0) {
				return false;
			} else {
				for (int i = topLeft.x; i <= botLeft.x; i++) {
					if (occupied[i][topLeft.y - 1]) {
						return false;
					}
				}
			}
		} else {
			if (botRight.y + 1 >= myWidth) {
				return false;
			} else {
				for (int i = topRight.x; i <= botRight.x; i++) {
					if (occupied[i][botRight.y + 1]) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public void setPriority(ArrayList<Block> goalTray) {
		double priority = 0;
		HashMap<Integer, HashSet<Block>> blocksCopy = new HashMap<Integer, HashSet<Block>>();
		Set<Block> relevantBlocks = new HashSet<Block>();
		if (shapes.keySet().size() == 1) {
			relevantBlocks.addAll(myBlocks);
		}
		for (Block goal : goalTray) {
			Integer blockShape = goal.shape();
			if (shapes.keySet().size() > 1) {
				if (blocksCopy.get(blockShape) == null) {
					blocksCopy.put(blockShape, shapes.get(blockShape));
				}
				relevantBlocks = blocksCopy.get(blockShape);
			}
			if (relevantBlocks.contains(goal)) {
				relevantBlocks.remove(goal);
				continue;
			}
			Block closestBlock = null;
			Double minDistance = Double.POSITIVE_INFINITY;
			for (Block b : relevantBlocks) {
				if (b.getHeight() == goal.getHeight()
						&& b.getWidth() == goal.getWidth()) {
					double distance = distance(b, goal);
					if (distance < minDistance) {
						minDistance = distance;
						closestBlock = b;
					}
				}
			}
			priority += minDistance;
			relevantBlocks.remove(closestBlock);
		}
		myPriority = priority;
	}

	private double distance(Block block1, Block block2) {
		return Math.abs(block1.getTopLeft().x - block2.getTopLeft().x)
				+ Math.abs(block1.getTopLeft().y - block2.getTopLeft().y);
	}

	public HashSet<Block> getBlocks() {
		return myBlocks;
	}

	private int range(String direction, Block block) {
		int range = 0;
		if (direction.equals("up")) {
			while (isMovePossible("up", block)) {
				block = moveBlock("up", block, 1);
				range++;
			}
		} else if (direction.equals("down")) {
			while (isMovePossible("down", block)) {
				block = moveBlock("down", block, 1);
				range++;
			}
		} else if (direction.equals("left")) {
			while (isMovePossible("left", block)) {
				block = moveBlock("left", block, 1);
				range++;
			}
		} else if (direction.equals("right")) {
			while (isMovePossible("right", block)) {
				block = moveBlock("right", block, 1);
				range++;
			}
		}
		return range;
	}

	private String makeMoveString(String direction, Block block, int distance) {
		String blockCoords = block.getTopLeft().x + " " + block.getTopLeft().y
				+ " ";
		if (direction.equals("up")) {
			return blockCoords + (block.getTopLeft().x - distance) + " "
					+ block.getTopLeft().y;
		} else if (direction.equals("down")) {
			return blockCoords + (block.getTopLeft().x + distance) + " "
					+ block.getTopLeft().y;
		} else if (direction.equals("left")) {
			return blockCoords + block.getTopLeft().x + " "
					+ (block.getTopLeft().y - distance);
		} else {
			return blockCoords + block.getTopLeft().x + " "
					+ (block.getTopLeft().y + distance);
		}
	}

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else if (this == o) {
			return true;
		} else {
			Tray other = (Tray) o;
			if (myHeight != other.myHeight || myWidth != other.myWidth) {
				return false;
			} else {
				return myBlocks.equals(other.myBlocks);
			}
		}
	}

	private Block moveBlock(String direction, Block block, int distance) {
		Block newBlock;
		if (direction.equals("up")) {
			newBlock = new Block(block.getTopLeft().x - distance,
					block.getTopLeft().y, block.getBotRight().x - distance,
					block.getBotRight().y);
		} else if (direction.equals("down")) {
			newBlock = new Block(block.getTopLeft().x + distance,
					block.getTopLeft().y, block.getBotRight().x + distance,
					block.getBotRight().y);
		} else if (direction.equals("left")) {
			newBlock = new Block(block.getTopLeft().x, block.getTopLeft().y
					- distance, block.getBotRight().x, block.getBotRight().y
					- distance);
		} else {
			newBlock = new Block(block.getTopLeft().x, block.getTopLeft().y
					+ distance, block.getBotRight().x, block.getBotRight().y
					+ distance);
		}
		return newBlock;
	}

	private Tray makeNewTray(String direction, Block block,
			HashSet<Block> currBlocks, int distance, ArrayList<Block> goalTray,
			HashSet<Tray> visited) {
		Tray rv = new Tray(myHeight, myWidth);
		Block newBlock = moveBlock(direction, block, distance);
		for (Block copied : currBlocks) {
			if (!block.equals(copied)) {
				rv.addBlock(copied);
			}
		}
		rv.addBlock(newBlock);
		if (visited.contains(rv)) {
			return null;
		}
		rv.setParent(this);
		rv.setPriority(goalTray);
		rv.setHashCode();
		return rv;
	}

	public void setParent(Tray parent) {
		myParent = parent;
	}

	public Tray getParent() {
		return myParent;
	}

	public void setPossibleMoves(ArrayList<Block> goalTray,
			HashSet<Tray> visited) {
		for (Block block : myBlocks) {
			String moveString;
			Tray newTray;
			for (int i = 0; i < range("up", block); i++) {
				moveString = makeMoveString("up", block, i + 1);
				newTray = makeNewTray("up", block, myBlocks, i + 1, goalTray,
						visited);
				newTray.setMoveString(moveString);
				if (newTray != null)
					possibleMoves.add(newTray);

			}
			for (int i = 0; i < range("down", block); i++) {
				moveString = makeMoveString("down", block, i + 1);
				newTray = makeNewTray("down", block, myBlocks, i + 1, goalTray,
						visited);
				newTray.setMoveString(moveString);
				if (newTray != null) {
					possibleMoves.add(newTray);
				}
			}
			for (int i = 0; i < range("left", block); i++) {
				moveString = makeMoveString("left", block, i + 1);
				newTray = makeNewTray("left", block, myBlocks, i + 1, goalTray,
						visited);
				newTray.setMoveString(moveString);
				if (newTray != null) {
					possibleMoves.add(newTray);
				}
			}
			for (int i = 0; i < range("right", block); i++) {
				moveString = makeMoveString("right", block, i + 1);
				newTray = makeNewTray("right", block, myBlocks, i + 1,
						goalTray, visited);
				newTray.setMoveString(moveString);
				if (newTray != null) {
					possibleMoves.add(newTray);
				}
			}
		}
	}

	public Set<Tray> getPossibleMoves() {
		return possibleMoves;
	}

	@Override
	public int compareTo(Tray otherTray) {
		if (myPriority < otherTray.myPriority) {
			return -1;
		} else if (myPriority > otherTray.myPriority) {
			return 1;
		} else {
			return 0;
		}
	}
}