import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Tray {

	private boolean[][] tray;
	private short width;
	private short height;
	private HashSet<Block> myBlocks;
	private Tray myParent;
	private moveBlock movedTo;

	public Tray(short height, short width) {
		tray = new boolean[height][width];
		this.width = width;
		this.height = height;
		myBlocks = new HashSet<Block>();
		myParent = null;
	}

	public Tray(short height, short width, Tray parent) {
		this(height, width);
		myParent = parent;
	}

	public void addBlock(Block b) throws Exception {
		for (short j = (short) b.getTopLeft().getX(); j <= (short) b.getBottomRight().getX(); j++) {
			for (short i = (short) b.getTopLeft().getY(); i <= (short) b.getBottomRight().getY(); i++) {
				if (tray[j][i] == true) {
					throw new Exception();
				} else {
					tray[j][i] = true;
				}
			}
		}
		myBlocks.add(b);
	}

	public short height() {
		return height;
	}

	public short width() {
		return width;
	}

	public void removeBlock(Block b) {
		for (short j = (short) b.getTopLeft().getX(); j <= (short) b.getBottomRight().getX(); j++) {
			for (short i = (short) b.getTopLeft().getY(); i <= (short) b.getBottomRight().getY(); i++) {
				tray[j][i] = false;
			}
		}
		myBlocks.remove(b);
	}

	public void print() {
		for (short j = 0; j < height; j++) {
			for (short i = 0; i < width; i++) {
				System.out.print(" " + tray[j][i] + " |");
			}
			System.out.println();
			System.out.println("-----------------");
		}
	}

	public moveBlock recallMove() {
		return movedTo;
	}

	public void memorizeMove(moveBlock move) {
		movedTo = move;
	}

	public boolean validMove(Block b, String direction, short spaces) {
		short upJ = (short) b.getTopLeft().getX();
		short botJ = (short) b.getBottomRight().getX();
		short upI = (short) b.getTopLeft().getY();
		short botI = (short) b.getBottomRight().getY();
		boolean validmove = true;
		switch (direction) {
		case "up":
			for (short i = upI; i <= botI; i++) {
				if (upJ - spaces < 0 || tray[upJ - spaces][i]) {
					validmove = false;
				}
			}
			break;
		case "down":
			for (short i = upI; i <= botI; i++) {
				if (botJ + spaces >= height || tray[botJ + spaces][i]) {
					validmove = false;
				}
			}
			break;
		case "right":
			for (short j = upJ; j <= botJ; j++) {
				if (botI + spaces >= width || tray[j][botI + spaces]) {
					validmove = false;
				}
			}
			break;
		case "left":
			for (short j = upJ; j <= botJ; j++) {
				if (upI - spaces < 0 || tray[j][upI - spaces]) {
					validmove = false;
				}
			}
			break;
		}
		return validmove;
	}

	public short maxMoves(Block b, String direction) {
		short maxmoves = 0;
		for (short i = 1; validMove(b, direction, i) == true; i++) {
			maxmoves++;
		}
		return maxmoves;
	}

	public Tray parent() {
		return myParent;
	}

	public HashSet<Block> getMyBlocks() {
		return myBlocks;
	}

	public ArrayList<moveBlock> nextMoves() {
		ArrayList<moveBlock> moves = new ArrayList<moveBlock>();
		for (Block b : myBlocks) {
			short maxMovesU = maxMoves(b, "up");
			short maxMovesD = maxMoves(b, "down");
			short maxMovesR = maxMoves(b, "right");
			short maxMovesL = maxMoves(b, "left");
			if (maxMovesU > 0) {
				for (short i = 1; i <= maxMovesU; i++) {
					moves.add(new moveBlock(b, "up", i));
				}
			}
			if (maxMovesD > 0) {
				for (short i = 1; i <= maxMovesD; i++) {
					moves.add(new moveBlock(b, "down", i));
				}
			}
			if (maxMovesR > 0) {
				for (short i = 1; i <= maxMovesR; i++) {
					moves.add(new moveBlock(b, "right", i));
				}
			}
			if (maxMovesL > 0) {
				for (short i = 1; i <= maxMovesL; i++) {
					moves.add(new moveBlock(b, "left", i));
				}
			}
		}
		return moves;
	}

	@Override
	public boolean equals(Object o) {
		if (this.hashCode() == ((Tray) o).hashCode()) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		Block[] blockarray = new Block[myBlocks.size()];
		int i = 0;
		while (i < blockarray.length) {
			for (Block b : myBlocks) {
				blockarray[i] = b;
				i++;
			}
		}
		return Arrays.deepHashCode(blockarray);
	}

	public int representation() {
		int repr = 0;
		for (Block b : getMyBlocks()) {
			repr = 31 * repr + 31 + b.hashCode() * 31;
		}
		return repr;
	}
}
