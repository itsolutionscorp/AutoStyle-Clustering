import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Board {
	int[][] tray;
	IntArray2D blocks;
	int height;
	int width;
	HashSet<int[]> blocksSet;
	HashSet<int[]> validMoves;

	public Board(List<String> trayConfig) throws Exception {
		int space = trayConfig.get(0).indexOf(' ');
		height = Integer.parseInt(trayConfig.get(0).substring(0, space));
		width = Integer.parseInt(trayConfig.get(0).substring(space + 1));
		tray = new int[height][width];
		blocks = new IntArray2D(trayConfig.size(), 4); // Size has an extra
														// line!
		for (int index = 1; index < trayConfig.size(); index++) {
			String[] toCopy = trayConfig.get(index).split("\\s+");
			if (toCopy.length != 4) {
				throw new Exception();
			}
			for (int subIndex = 0; subIndex < 4; subIndex++)
				blocks.setCoord(index, subIndex,
						Integer.parseInt(toCopy[subIndex]));
		}
		for (int blockIndex = 1; blockIndex < blocks.length; blockIndex++) {
			int[] block = blocks.getPiece(blockIndex);
			int h1 = block[0];
			int w1 = block[1];
			int h2 = block[2];
			int w2 = block[3];
			for (int h = h1; h <= h2; h++) {
				for (int w = w1; w <= w2; w++)
					if (tray[h][w] == 0) {
						tray[h][w] = blockIndex;
					} else {
						throw new Exception();
					}
			}
		}
		validMoves = new HashSet<int[]>();
		betterGetMoves(getAdjacentToZero());
		blocksSet = new HashSet<int[]>();
		for (int piece = 1; piece < blocks.length; piece++)
			blocksSet.add(blocks.getPiece(piece));
	}

	public Board(IntArray2D newBlocks, int height, int width) throws Exception {
		blocks = newBlocks;
		tray = new int[height][width];
		for (int blockIndex = 1; blockIndex < blocks.length; blockIndex++) {
			int[] block = blocks.getPiece(blockIndex);
			int h1 = block[0];
			int w1 = block[1];
			int h2 = block[2];
			int w2 = block[3];
			for (int h = h1; h <= h2; h++) {
				for (int w = w1; w <= w2; w++)
					if (tray[h][w] == 0) {
						tray[h][w] = blockIndex;
					} else {
						throw new Exception();
					}
			}
		}
		this.width = width;
		this.height = height;
		validMoves = new HashSet<int[]>();
		betterGetMoves(getAdjacentToZero());
		blocksSet = new HashSet<int[]>();
		for (int piece = 1; piece < blocks.length; piece++)
			blocksSet.add(blocks.getPiece(piece));
	}

	public HashSet<int[]> getZeroes() {
		HashSet<int[]> rtn = new HashSet<int[]>();
		for (int w = 0; w < width; w++)
			for (int h = 0; h < height; h++)
				if (tray[h][w] == 0)
					rtn.add(new int[] { h, w });
		return rtn;
	}

	public HashSet<Integer> getAdjacentToZero() {
		HashSet<Integer> rtn = new HashSet<Integer>();
		for (int[] zero : getZeroes()) {
			int h = zero[0];
			int w = zero[1];
			if ((h + 1) >= 0 && (h + 1) < height) {
				if (tray[h + 1][w] != 0)
					rtn.add(tray[h + 1][w]);
			}
			if ((h - 1) >= 0 && (h - 1) < height) {
				if (tray[h - 1][w] != 0)
					rtn.add(tray[h - 1][w]);
			}
			if ((w - 1) >= 0 && (w - 1) < width) {
				if (tray[h][w - 1] != 0)
					rtn.add(tray[h][w - 1]);
			}
			if ((w + 1) >= 0 && (w + 1) < width) {
				if (tray[h][w + 1] != 0)
					rtn.add(tray[h][w + 1]);
			}
		}
		return rtn;
	}

	public void betterGetMoves(HashSet<Integer> toCheck) {
		for (int piece : toCheck) {
			int[] block = blocks.getPiece(piece);
			int h1 = block[0];
			int w1 = block[1];
			int h2 = block[2];
			int w2 = block[3];
			if (checkVertical(h1 - 1, w1, w2))
				validMoves.add(new int[] { piece, 0 });
			if (checkVertical(h2 + 1, w1, w2))
				validMoves.add(new int[] { piece, 1 });
			if (checkHorizontal(h1, h2, w1 - 1))
				validMoves.add(new int[] { piece, 2 });
			if (checkHorizontal(h1, h2, w2 + 1))
				validMoves.add(new int[] { piece, 3 });
		}
	}

	@Override
	public String toString() {
		String toBeReturned = new String();
		toBeReturned += "\n";
		for (int[] inner : tray) {
			for (int element : inner) {
				int length = String.valueOf(element).length();
				toBeReturned += element;
				for (int spaces = 6 - length; spaces > 0; spaces--) {
					toBeReturned += " ";
				}

			}
			toBeReturned += "\n";
		}
		return toBeReturned;
	}

	public void betterGetMoves() {
		for (int blockIndex = 1; blockIndex < blocks.length; blockIndex++) {
			int[] block = blocks.getPiece(blockIndex);
			int h1 = block[0];
			int w1 = block[1];
			int h2 = block[2];
			int w2 = block[3];
			if (checkVertical(h1 - 1, w1, w2))
				validMoves.add(new int[] { blockIndex, 0 });
			if (checkVertical(h2 + 1, w1, w2))
				validMoves.add(new int[] { blockIndex, 1 });
			if (checkHorizontal(h1, h2, w1 - 1))
				validMoves.add(new int[] { blockIndex, 2 });
			if (checkHorizontal(h1, h2, w2 + 1))
				validMoves.add(new int[] { blockIndex, 3 });
		}
	}

	public boolean checkVertical(int h, int w1, int w2) {
		if (h < 0 || h >= tray.length) {
			return false;
		}
		for (int w = w1; w <= w2; w++) {
			if (tray[h][w] != 0) {
				return false;
			}
		}
		return true;
	}

	public boolean checkHorizontal(int h1, int h2, int w) {
		if (w < 0 || w >= tray[0].length) {
			return false;
		}
		for (int h = h1; h <= h2; h++) {
			if (tray[h][w] != 0) {
				return false;
			}
		}
		return true;
	}

	// assumes getMoves returns true for the given piece/direction
	public IntArray2D move(int pieceIndex, int direction) {
		IntArray2D toBeReturned = new IntArray2D(blocks.length, 4);
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < 4; j++) {
				toBeReturned.setCoord(i, j, blocks.getCoord(i, j));
			}
		}

		switch (direction) {
		case 0: {
			toBeReturned.setCoord(pieceIndex, 0,
					toBeReturned.getCoord(pieceIndex, 0) - 1);
			toBeReturned.setCoord(pieceIndex, 2,
					toBeReturned.getCoord(pieceIndex, 2) - 1);
			break;
		}
		case 1: {
			toBeReturned.setCoord(pieceIndex, 0,
					toBeReturned.getCoord(pieceIndex, 0) + 1);
			toBeReturned.setCoord(pieceIndex, 2,
					toBeReturned.getCoord(pieceIndex, 2) + 1);
			break;
		}
		case 2: {
			toBeReturned.setCoord(pieceIndex, 1,
					toBeReturned.getCoord(pieceIndex, 1) - 1);
			toBeReturned.setCoord(pieceIndex, 3,
					toBeReturned.getCoord(pieceIndex, 3) - 1);
			break;
		}
		case 3: {
			toBeReturned.setCoord(pieceIndex, 1,
					toBeReturned.getCoord(pieceIndex, 1) + 1);
			toBeReturned.setCoord(pieceIndex, 3,
					toBeReturned.getCoord(pieceIndex, 3) + 1);
			break;
		}
		}
		return toBeReturned;
	}

	@Override
	public boolean equals(Object other) {
		Board b = (Board) other;
		for (int[] item1 : blocksSet) {
			boolean hasFound = false;
			for (int[] item2 : b.blocksSet) {
				if (Arrays.equals(item1, item2)) {
					hasFound = true;
				}
			}
			if (!hasFound) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		for (int[] piece : blocksSet)
			hash += Arrays.hashCode(piece);
		return hash;
	}

	public boolean goalFound(IntArray2D goal) {
		boolean hasAllGoals = true;
		for (int index = 0; index < goal.length; index++) {
			boolean hasGoal = false;
			for (int i = 1; i < blocks.length; i++) {
				boolean isEqual = true;
				for (int j = 0; j < 4; j++) {
					if (blocks.getCoord(i, j) != goal.getCoord(index, j)) {
						isEqual = false;
					}
				}
				if (isEqual) {
					hasGoal = true;
				}
			}
			if (!hasGoal) {
				return false;
			}
		}
		return hasAllGoals;
	}
}
