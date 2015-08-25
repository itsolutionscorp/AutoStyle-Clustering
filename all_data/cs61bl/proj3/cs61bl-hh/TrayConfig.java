import java.util.HashSet;
import java.awt.Point;

public class TrayConfig implements Comparable<Object> {
	HashSet<Block> blocks;
	HashSet<Point> emptySpaces;
	double heuristic;
	TrayConfig parent;
	int[] moveMade;

	public TrayConfig(HashSet<Block> blocks, HashSet<Point> emptySpaces,
			TrayConfig parent, int[] moveMade) {
		this.blocks = blocks;
		this.emptySpaces = emptySpaces;
		this.parent = parent;
		this.moveMade = moveMade;
		this.setHeuristic();
	}

	public TrayConfig(HashSet<Block> blocks, HashSet<Point> emptySpaces, double heuristic,
			TrayConfig parent, int[] moveMade) {
		this.blocks = blocks;
		this.emptySpaces = emptySpaces;
		this.parent = parent;
		this.moveMade = moveMade;
		this.heuristic = heuristic;
	}

	public HashSet<TrayConfig> possibleMoves() {
		HashSet<TrayConfig> result = new HashSet<TrayConfig>();
		boolean leftOk, rightOk, upOk, downOk;
		int topLeft0, topLeft1, bottomRight0, bottomRight1;
		int bHeight, bWidth;
		int i, j;
		for (Block b : blocks) {
			topLeft0 = b.topLeft[0];
			topLeft1 = b.topLeft[1];
			bottomRight0 = b.bottomRight[0];
			bottomRight1 = b.bottomRight[1];
			bHeight = b.height;
			bWidth = b.width;

			leftOk = true;
			j = topLeft1 - 1;
			if (j == -1) { // move would be off the tray
				leftOk = false;
			} else {
				for (i = topLeft0; i < topLeft0 + bHeight; i++) {
					if (!emptySpaces.contains(new Point(i, j))) {
						leftOk = false;
					}
				}
			}
			rightOk = true;
			j = topLeft1 + bWidth;
			if (j > Solver.width - 1) { // move would be off the tray
				rightOk = false;
			} else {
				for (i = topLeft0; i < topLeft0 + bHeight; i++) {
					if (!emptySpaces.contains(new Point(i, j))) {
						rightOk = false;
					}
				}
			}
			upOk = true;
			i = topLeft0 - 1;
			if (i == -1) {
				upOk = false;
			} else {
				for (j = topLeft1; j < topLeft1 + bWidth; j++) {
					if (!emptySpaces.contains(new Point(i, j))) {
						upOk = false;
					}
				}
			}
			downOk = true;
			i = topLeft0 + bHeight;
			if (i > Solver.height - 1) {
				downOk = false;
			} else {
				for (j = topLeft1; j < topLeft1 + bWidth; j++) {
					if (!emptySpaces.contains(new Point(i, j))) {
						downOk = false;
					}
				}
			}

			if (leftOk) {
				HashSet<Block> leftResultBlocks = new HashSet<Block>();
				for (Block b2 : blocks) {
					if (!b2.equals(b)) {
						leftResultBlocks.add(b2);
					}
				}

				int[] newTopLeft1 = { topLeft0, topLeft1 - 1 };
				int[] newBottomRight1 = { bottomRight0,
						bottomRight1 - 1 };
				leftResultBlocks.add(new Block(newTopLeft1, newBottomRight1));

				HashSet<Point> leftResultEmptySpaces = new HashSet<Point>();
				for (Point p : emptySpaces) {
					leftResultEmptySpaces.add((Point) p.clone());
				}

				int y1 = newTopLeft1[1];
				for (int x1 = newTopLeft1[0]; x1 < newTopLeft1[0] + bHeight; x1++) {
					leftResultEmptySpaces.remove(new Point(x1, y1));
				}
				int y2 = newBottomRight1[1] + 1;
				for (int x2 = newBottomRight1[0]; x2 > newBottomRight1[0]
						- bHeight; x2--) {
					leftResultEmptySpaces.add(new Point(x2, y2));
				}

				result.add(new TrayConfig(leftResultBlocks,
						leftResultEmptySpaces, this, new int[] {
						topLeft0, topLeft1, newTopLeft1[0],
						newTopLeft1[1] }));
			}

			if (rightOk) {
				HashSet<Block> rightResultBlocks = new HashSet<Block>();
				for (Block b2 : blocks) {
					if (!b2.equals(b)) {
						rightResultBlocks.add(b2);
					}
				}

				int[] newTopLeft2 = { topLeft0, topLeft1 + 1 };
				int[] newBottomRight2 = { bottomRight0,
						bottomRight1 + 1 };
				rightResultBlocks.add(new Block(newTopLeft2, newBottomRight2));
				
				HashSet<Point> rightResultEmptySpaces = new HashSet<Point>();
				for (Point p : emptySpaces) {
					rightResultEmptySpaces.add((Point) p.clone());
				}

				int y1 = newBottomRight2[1];
				for (int x1 = newBottomRight2[0]; x1 > newBottomRight2[0]
						- bHeight; x1--) {
					rightResultEmptySpaces.remove(new Point(x1, y1));;
				}
				int y2 = newTopLeft2[1] - 1;
				for (int x2 = newTopLeft2[0]; x2 < newTopLeft2[0] + bHeight; x2++) {
					rightResultEmptySpaces.add(new Point(x2, y2));
				}

				result.add(new TrayConfig(rightResultBlocks,
						rightResultEmptySpaces, this, new int[] {
						topLeft0, topLeft1, newTopLeft2[0],
						newTopLeft2[1] }));
			}

			if (upOk) {
				HashSet<Block> upResultBlocks = new HashSet<Block>();
				for (Block b2 : blocks) {
					if (!b2.equals(b)) {
						upResultBlocks.add(b2);
					}
				}

				int[] newTopLeft3 = { topLeft0 - 1, topLeft1 };
				int[] newBottomRight3 = { bottomRight0 - 1,
						bottomRight1 };
				upResultBlocks.add(new Block(newTopLeft3, newBottomRight3));
				
				HashSet<Point> upResultEmptySpaces = new HashSet<Point>();
				for (Point p : emptySpaces) {
					upResultEmptySpaces.add((Point) p.clone());
				}

				int x1 = newTopLeft3[0];
				for (int y1 = newTopLeft3[1]; y1 < newTopLeft3[1] + bWidth; y1++) {
					upResultEmptySpaces.remove(new Point(x1, y1));
				}
				int x2 = newBottomRight3[0] + 1;
				for (int y2 = newBottomRight3[1]; y2 > newBottomRight3[1]
						- bWidth; y2--) {
					upResultEmptySpaces.add(new Point(x2, y2));
				}

				result.add(new TrayConfig(upResultBlocks, upResultEmptySpaces,
						this, new int[] { topLeft0, topLeft1,
						newTopLeft3[0], newTopLeft3[1] }));
			}

			if (downOk) {
				HashSet<Block> downResultBlocks = new HashSet<Block>();
				for (Block b2 : blocks) {
					if (!b2.equals(b)) {
						downResultBlocks.add(b2);
					}
				}

				int[] newTopLeft4 = { topLeft0 + 1, topLeft1 };
				int[] newBottomRight4 = { bottomRight0 + 1,
						bottomRight1 };
				downResultBlocks.add(new Block(newTopLeft4, newBottomRight4));
				
				HashSet<Point> downResultEmptySpaces = new HashSet<Point>();
				for (Point p : emptySpaces) {
					downResultEmptySpaces.add((Point) p.clone());
				}

				int x1 = newBottomRight4[0];
				for (int y1 = newBottomRight4[1]; y1 > newBottomRight4[1]
						- bWidth; y1--) {
					downResultEmptySpaces.remove(new Point(x1, y1));
				}
				int x2 = newTopLeft4[0] - 1;
				for (int y2 = newTopLeft4[1]; y2 < newTopLeft4[1] + bWidth; y2++) {
					downResultEmptySpaces.add(new Point(x2, y2));
				}

				result.add(new TrayConfig(downResultBlocks,
						downResultEmptySpaces, this, new int[] {
						topLeft0, topLeft1, newTopLeft4[0],
						newTopLeft4[1] }));
			}
		}
		return result;
	}

	@Override
	public boolean equals(Object o) {
		TrayConfig otherTc = (TrayConfig) o;
		int count = 0;
		for (Block b1 : blocks) {
			for (Block b2 : otherTc.blocks) {
				if (b1.equals(b2)) {
					count++;
				}
			}
		}
		if (count != otherTc.blocks.size()) {
			return false;
		}

		return true;
	}

	public  void setHeuristic(){
		int heuristicValue = 0;
		for (Block blockGoal: Solver.goal.blocks){
			int currentHeuristic = Integer.MAX_VALUE;
			for (Block blockThis: this.blocks){
				if (blockThis.width == blockGoal.width && blockThis.height == blockGoal.height){
					int comparison = Math.abs(blockGoal.topLeft[0] - blockThis.topLeft[0]) + Math.abs(blockGoal.topLeft[1] - blockThis.topLeft[1]);
					if (comparison < currentHeuristic){
						currentHeuristic = comparison;
					}
				}
			}
			heuristicValue += currentHeuristic;
		}
		this.heuristic = heuristicValue;
	}

	@Override
	public int compareTo(Object o) {
		TrayConfig otherTc = (TrayConfig) o;
		if (heuristic > otherTc.heuristic) {
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public int hashCode() {
		int blocksResult = 0;
		for (Block b : blocks) {
			blocksResult += b.hashCode();
		}
		return blocksResult;
	}

	@Override
	public String toString() {
		String result = Solver.height + " " + Solver.width + "\n";
		for (Block b : blocks) {
			result += b.toString() + "\n";
		}
		return result;
	}
}
