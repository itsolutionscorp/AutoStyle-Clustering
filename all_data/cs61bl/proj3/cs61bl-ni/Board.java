import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Board {

	private int boardWidth;
	private int boardLength;
	private HashSet<Block> blocks;
	private HashSet<Point> empty;
	private HashMap<Point, Block> tray;
	private ArrayList<String> moves;

	public Board(int length, int width, Board parent) {
		boardWidth = width;
		boardLength = length;
		tray = new HashMap<Point, Block>();
		moves = new ArrayList<String>();
		blocks = new HashSet<Block>();
		empty = new HashSet<Point>();
		blocks.addAll(parent.blocks);
		moves.addAll(parent.moves);
		empty.addAll(parent.empty);
		if (parent.tray != null) {
			tray.putAll(parent.tray);
		}
	}

	public Board(int length, int width) {
		boardWidth = width;
		boardLength = length;
		tray = new HashMap<Point, Block>();
		moves = new ArrayList<String>();
		blocks = new HashSet<Block>();
		empty = new HashSet<Point>();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < length; j++) {
				empty.add(new Point(i, j));
			}
		}
	}

	public void addBlock(Block block) {
		for (int j = block.getStart().x; j <= block.getFinish().x; j++) {
			for (int k = block.getStart().y; k <= block.getFinish().y; k++) {
				tray.put(new Point(j, k), block);
				blocks.add(block);
				empty.remove(new Point(j, k));
			}
		}
	}

	public void removeBlock(Block block) {
		for (int j = block.getStart().x; j <= block.getFinish().x; j++) {
			for (int k = block.getStart().y; k <= block.getFinish().y; k++) {
				tray.remove(new Point(j, k));
				empty.add(new Point(j, k));
				blocks.remove(block);
			}
		}
	}

	public boolean validMove(Block block, Point point) {
		if (point.x < 0 || point.x > boardWidth || point.y < 0 || point.y > boardLength) {
			return false;
		}
		removeBlock(block);
		for (int i = point.x; i < point.x + block.getWidth(); i++) {
			for (int j = point.y; j < point.y + block.getLength(); j++) {
				if (tray.containsKey(new Point(i, j))) {
					addBlock(block);
					return false;
				}
			}
		}
		return true;
	}

	public Board makeMove(Block block, Point point) throws IllegalStateException {
		Block tempBlock = new Block(block.getStart(), block.getFinish());
		if (validMove(block, point)) {
			addBlock(block);
			tempBlock.move(point, new Point(point.x + block.getWidth() - 1, point.y + block.getLength() - 1));
			Board board = new Board(boardLength, boardWidth, this);
			board.removeBlock(block);
			board.addBlock(tempBlock);
			String moveMade = Integer.toString(block.getStart().y) + " " + Integer.toString(block.getStart().x) + " "
					+ Integer.toString(point.y) + " " + Integer.toString(point.x);
			board.moves.add(moveMade);
			return board;
		} else {
			return null;
		}
	}

	public int hashCode() {
		int temp = 0;
		for (Block block : tray.values()) {
			temp += block.hashCode();
		}
		return temp;
	}

	public boolean equals(Object o) {
		Board board = (Board) o;
		if (board.boardLength != boardLength || board.boardWidth != boardWidth)
			return false;
		else if (!tray.equals(board.tray)) {
			return false;
		} else {
			return true;
		}
	}

	public HashMap<Point, Block> getTray() {
		return tray;
	}

	public HashSet<Point> getEmpty() {
		return empty;
	}

	public String printMoves() {
		String allMoves = "";
		for (int i = 0; i < moves.size(); i++) {
			allMoves += moves.get(i) + "\n";
		}
		return allMoves;
	}

	public int getLength() {
		return boardLength;
	}

	public int getWidth() {
		return boardWidth;
	}

	public HashSet<Block> getBlocks() {
		return blocks;
	}
}