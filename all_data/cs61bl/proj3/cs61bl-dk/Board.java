import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Board implements Comparable<Board>{

	static short maxX, maxY;
	static HashSet<Block> goalBlocks;
	static int goalId;
	static HashSet<Board> processed;
	static HashMap<Integer, HashSet<Block>> goalMap;

	HashSet<Block> blocks;
	boolean[][] occupied;
	Board parent;
	Move lastMove;
	LinkedList<Board> children;
	int score;
	int id;
	int gId;

	public Board(HashSet<Block> blocks, boolean[][] occupied) {
		this.blocks = blocks;
		this.occupied = occupied;
		this.parent = null;
		this.lastMove = null;
		this.score = score();
		this.id = blocks.hashCode() + Arrays.deepHashCode(occupied);
		this.gId = getGID();
		processed = new HashSet<Board>();
		processed.add(this);
	}

	public Board(Board start, Move move) {
		this.blocks = new HashSet<Block>();
		this.blocks.addAll(start.blocks);
		this.occupied = new boolean[maxX][maxY];
		for (int i = 0; i < maxX; i++)
			for (int j = 0; j < maxY; j++)
				this.occupied[i][j] = start.occupied[i][j];
		this.parent = start;
		this.lastMove = move;
		this.score = start.score - 2;
		this.id = start.id - Arrays.deepHashCode(occupied);
		this.gId = start.gId;
		moveBlock(move);
	}

	public void moveBlock(Move move) {

		int initx1 = move.blk.x1;
		int inity1 = move.blk.y1;
		int initx2 = move.blk.x2;
		int inity2 = move.blk.y2;
		int endx1 = initx1 + move.dx;
		int endy1 = inity1 + move.dy;
		int endx2 = initx2 + move.dx;
		int endy2 = inity2 + move.dy;
		int blockX = move.blk.height;
		int blockY = move.blk.width;

		for (int i = 0; i < move.dx; i++) {
			for (int j = 0; j < blockY; j++) {
				occupied[initx1 + i][inity1 + j] = false;
				occupied[initx2 + i + 1][inity1 + j] = true;
			}
		}

		for (int i = 0; i > move.dx; i--) {
			for (int j = 0; j < blockY; j++) {
				occupied[initx2 + i][inity1 + j] = false;
				occupied[initx1 + i - 1][inity1 + j] = true;
			}
		}

		for (int i = 0; i < blockX; i++) {
			for (int j = 0; j < move.dy; j++) {
				occupied[initx1 + i][inity1 + j] = false;
				occupied[initx1 + i][inity2 + j + 1] = true;
			}
		}

		for (int i = 0; i < blockX; i++) {
			for (int j = 0; j > move.dy; j--) {
				occupied[initx1 + i][inity2 + j] = false;
				occupied[initx1 + i][inity1 + j - 1] = true;
			}
		}

		blocks.remove(move.blk);
		Block endBlk = new Block(endx1, endy1, endx2, endy2);
		blocks.add(endBlk);
		id += endBlk.hashCode() - move.blk.hashCode() + Arrays.deepHashCode(occupied);

		HashSet<Block> hs = goalMap.get((blockX << 16) + blockY);
		if (hs != null) {
			for (Block b : goalMap.get((blockX << 16) + blockY)) {
				score += manhattanDist(endBlk, b) - manhattanDist(move.blk, b);
			}
		}

		if (goalBlocks.contains(move.blk))
			gId -= move.blk.hashCode();
		if (goalBlocks.contains(endBlk))
			gId += endBlk.hashCode();
	}

	public LinkedList<Move> getValidMoves() {
		LinkedList<Move> moveSet = new LinkedList<Move>();
		for (Block blk : blocks) {

			for (int i = 1; i < maxX; i++) {
				Move mov = new Move(blk, i, 0);
				if (isValidMove(mov))
					moveSet.add(mov);
				else
					break;
			}

			for (int i = 1; i < maxX; i++) {
				Move mov = new Move(blk, -i, 0);
				if (isValidMove(mov))
					moveSet.add(mov);
				else
					break;
			}

			for (int i = 1; i < maxY; i++) {
				Move mov = new Move(blk, 0, i);
				if (isValidMove(mov))
					moveSet.add(mov);
				else
					break;
			}

			for (int i = 1; i < maxY; i++) {
				Move mov = new Move(blk, 0, -i);
				if (isValidMove(mov))
					moveSet.add(mov);
				else
					break;
			}
		}

		return moveSet;
	}

	public boolean isValidMove(Move move) {

		int initx1 = move.blk.x1;
		int inity1 = move.blk.y1;
		int initx2 = move.blk.x2;
		int inity2 = move.blk.y2;
		int endx1 = initx1 + move.dx;
		int endy1 = inity1 + move.dy;
		int endx2 = initx2 + move.dx;
		int endy2 = inity2 + move.dy;

		if (endx1 < 0 || endx2 >= maxX)
			return false;

		if (endy1 < 0 || endy2 >= maxY)
			return false;

		if (move.dx < 0) {
			for (int i = initx1 - 1; i >= endx1; i--) {
				for (int j = inity1; j <= inity2; j++) {
					if (occupied[i][j])
						return false;
				}
			}
			return true;
		}

		if (move.dx > 0) {
			for (int i = initx2 + 1; i <= endx2; i++) {
				for (int j = inity1; j <= inity2; j++) {
					if (occupied[i][j])
						return false;
				}
			}
			return true;
		}

		if (move.dy < 0) {
			for (int i = initx1; i <= initx2; i++) {
				for (int j = inity1 - 1; j >= endy1; j--) {
					if (occupied[i][j])
						return false;
				}
			}
			return true;
		}

		if (move.dy > 0) {
			for (int i = initx1; i <= initx2; i++) {
				for (int j = inity2 + 1; j <= endy2; j++) {
					if (occupied[i][j])
						return false;
				}
			}
			return true;
		}

		return false;
	}

	public void getChildren() {
		LinkedList<Move> validMoves = getValidMoves();
		this.children = new LinkedList<Board>();
		for (Move mov : validMoves) {
			Board b = new Board(this, mov);
			if (processed.add(b)) {
				children.add(b);
			}
		}
	}

	public int score() {
		int sum = 0;
		for (Block a : blocks) {
			for (Block b : goalBlocks) {
				if (a.height == b.height && a.width == b.width) {
					sum += manhattanDist(a, b);
				}
			}
		}
		return sum;
	}

	public boolean isGoal() {
		return gId == goalId;
	}

	public int getGID() {
		int sum = 0;
		for (Block blk : blocks) {
			if (goalBlocks.contains(blk))
				sum += blk.hashCode();
		}
		return sum;
	}

	// public static float sqDist(Block a, Block b) {
	// return (float) Math.sqrt((a.x1 - b.x1) * (a.x1 - b.x1) + (a.y1 - b.y1) *
	// (a.y1 - b.y1));
	// }

	public static int manhattanDist(Block a, Block b) {
		return Math.abs(a.x1 - b.x1) + Math.abs(a.y1 - b.y1);
	}

	public int compareTo(Board o) {
		return this.score - o.score;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		return blocks.equals(((Board) (o)).blocks);
	}

	static void setGoal(HashSet<Block> goal, HashMap<Integer, HashSet<Block>> goalMp) {
		goalBlocks = goal;
		goalId = goal.hashCode();
		goalMap = goalMp;
	}

	static void setMaxXY(short x, short y) {
		maxX = x;
		maxY = y;
	}
}
