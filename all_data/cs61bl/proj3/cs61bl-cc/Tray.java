
/**
 * CS61BL - UC Berkeley Summer 2015 Project 3 - Sliding Block Puzzles
 * 
 * Class 2/3:
 * 
 * Solver.java
 * 
 * Tray.java <<<
 * 
 * Block.java
 * 
 * 
 * @authors Jessica Larson, Brian Sakhuja, Eifu Tomita, Alex Yao
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

public class Tray {
	private Block[][] tray;
	private Set<Block> blockSet;
	private HashSet<Integer> trackingEmptySet;

	/**
	 * Tray
	 * 
	 * Tray constructor 1/2.
	 * 
	 * Is only called once in the beginning to initialize a tray from the file.
	 * 
	 * @param data
	 *            - the input from the easy/medium/hard tray puzzles
	 * @throws FileNotFoundException
	 * @throws Exception
	 */
	public Tray(String data) {

		File f = new File(data);
		Scanner sc;
		try {
			sc = new Scanner(f);

			Solver.myHeight = sc.nextInt();
			Solver.myWidth = sc.nextInt();
			tray = new Block[Solver.myHeight][Solver.myWidth];
			blockSet = new HashSet<Block>();
			trackingEmptySet = new HashSet<Integer>();

			while (sc.hasNext()) {
				int topP = sc.nextInt();
				int leftP = sc.nextInt();
				int botP = sc.nextInt();
				int rightP = sc.nextInt();
				for (int h = topP; h <= botP; h++) {
					for (int w = leftP; w <= rightP; w++) {
						tray[h][w] = new Block(topP, leftP, botP, rightP);
						if (!blockSet.contains(tray[h][w])) {
							blockSet.add(tray[h][w]);
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			return;
		} catch (NoSuchElementException ee) {
			return;
		} catch (Exception eee) {
			return;
		}

		// trackingESet will have an index
		// if you want to get the coordinate, height is the number divided by
		// the tray width(Solver.myWidth)
		// width is the number moduled by the tray width(Solver.myWidth)
		// if there is a tray 5 height 4 width, then the trackingE has 7, the
		// empty space's coordinate is (1,3).
		// if the number is 17, the coordinate is (4,1). Ex: (7/width, 7%width)
		// = (1, 3)
		for (int i = 0; i < Solver.myHeight; i++) {
			for (int j = 0; j < Solver.myWidth; j++) {
				if (tray[i][j] == null) {
					trackingEmptySet.add(i * Solver.myWidth + j);
				}
			}
		}
		// emptyArea = trackingEmptySet.size();
		//
		// Solver.ratioOfEmptySpace = (double) emptyArea / ((double)
		// Solver.myWidth * Solver.myHeight);
		// System.out.println("Density: " + Solver.ratioOfEmptySpace);

	}

	/**
	 * Tray
	 * 
	 * TRAY constructor 2/2.
	 * 
	 * Takes in a tray, and a block to move, and which direction to move the
	 * block.
	 * 
	 * 
	 * @param t
	 * @param ToBeDeleted
	 * @param direction
	 */
	public Tray(Tray t, Block ToBeDeleted, int direction) {
		tray = new Block[Solver.myHeight][Solver.myWidth];
		trackingEmptySet = new HashSet<Integer>();
		blockSet = new HashSet<Block>();
		// trackingEmptySet.addAll(t.getTrackingEmptySet());

		for (int i = 0; i < Solver.myHeight; i++) {
			for (int j = 0; j < Solver.myWidth; j++) {
				if (t.tray[i][j] != null) {
					tray[i][j] = new Block(t.tray[i][j]);
					if (!blockSet.contains(tray[i][j])) {
						blockSet.add(tray[i][j]);
					}
				} else {
					trackingEmptySet.add(i * Solver.myWidth + j);
				}

			}
		}
		setEmpty(ToBeDeleted);
		int dstH;
		int dstW;
		if (direction == 1) {// left
			dstH = ToBeDeleted.getTop();
			dstW = ToBeDeleted.getLeft() - 1;
		} else if (direction == 2) {// right
			dstH = ToBeDeleted.getTop();
			dstW = ToBeDeleted.getLeft() + 1;
		} else if (direction == 3) {// up
			dstH = ToBeDeleted.getTop() - 1;
			dstW = ToBeDeleted.getLeft();
		} else {// down
			dstH = ToBeDeleted.getTop() + 1;
			dstW = ToBeDeleted.getLeft();
		}
		addBlock(ToBeDeleted, dstH, dstW);
	}

	/**
	 * getHeight
	 * 
	 * @returns the height H of the tray.
	 */
	public int getHeight() {
		return Solver.myHeight;
	}

	/**
	 * getWidth
	 * 
	 * @returns the width W of the tray.
	 */
	public int getWidth() {
		return Solver.myWidth;
	}

	/**
	 * getTray
	 * 
	 * @returns the block matrix that describes the given tray.
	 */
	public Block[][] getTray() {
		return tray;
	}

	/**
	 * getTrackingESet
	 * 
	 * @returns the set containing the empty spaces in the tray.
	 */
	public HashSet<Integer> getTrackingEmptySet() {
		return trackingEmptySet;
	}

	/**
	 * setEmpty
	 * 
	 * Given a block @param b, sets it to null and adds the empty 'block' to
	 * TRACKINGESET.
	 */
	public void setEmpty(Block b) {
		int top = b.getTop();
		int bot = b.getBottom();
		int left = b.getLeft();
		int right = b.getRight();

		for (int i = top; i <= bot; i++) {
			for (int j = left; j <= right; j++) {
				trackingEmptySet.add(i * getWidth() + j);
				if (blockSet.contains(tray[i][j])) {
					blockSet.remove(tray[i][j]);
				}
				tray[i][j] = null;

			}
		}
	}

	/**
	 * addBlock
	 * 
	 * Is called by the second constructor. Adds a block @param b to the current
	 * tray at the locations provided:
	 * 
	 * @param dstH
	 * @param dstW
	 * 
	 */
	// TODO change tracking Empty position
	public void addBlock(Block b, int dstH, int dstW) {
		int newTop = dstH;
		int newLeft = dstW;
		int newBot = dstH + (b.getBottom() - b.getTop());
		int newRight = dstW + (b.getRight() - b.getLeft());

		for (int i = newTop; i <= newBot; i++) {
			for (int j = newLeft; j <= newRight; j++) {
				if (trackingEmptySet.contains(i * Solver.myWidth + j)) {
					trackingEmptySet.remove(i * Solver.myWidth + j);
				}

				tray[i][j] = new Block(newTop, newLeft, newBot, newRight);
				if (!blockSet.contains(tray[i][j])) {
					blockSet.add(tray[i][j]);
				}
			}
		}

	}

	/**
	 * isGoal
	 * 
	 * @param t
	 *            - the tray that we are inspecting.
	 * @param l
	 *            - the list of blocks associated with the goal configuration
	 * @return - true if the tray is in the goal configuration. False, if it is
	 *         not.
	 */
	public boolean isGoal(List<Block> l) {

		for (Block b : l) {
			if (!blockSet.contains(b)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * findMovedBlock
	 * 
	 * @param t
	 * @return
	 */
	public String findMovedBlock(Tray t) {
		int one;
		int two;
		int three;
		int four;
		for (int i = 0; i < t.getHeight(); i++) {
			for (int j = 0; j < t.getWidth(); j++) {
				if ((tray[i][j] == null && t.tray[i][j] != null) || (tray[i][j] != null && t.tray[i][j] == null)) {
					if (tray[i][j] == null) {
						three = i;
						four = j;
						// vertical
						try {
							if (tray[i][j + 1] != null && t.tray[i][tray[i][j + 1].getRight()] == null) {
								one = i;
								two = j + 1;
								return one + " " + two + " " + three + " " + four;
							}
						} catch (ArrayIndexOutOfBoundsException e) {

						}
						// horizontal
						try {
							if (tray[i + 1][j] != null && t.tray[tray[i + 1][j].getBottom()][j] == null) {
								one = i + 1;
								two = j;
								return one + " " + two + " " + three + " " + four;
							}
						} catch (ArrayIndexOutOfBoundsException ee) {

						}

					} else {
						one = i;
						two = j;
						try {
							// vertical
							if (t.tray[i][j + 1] != null && tray[i][t.tray[i][j + 1].getRight()] == null) {
								three = i;
								four = j + 1;
								return one + " " + two + " " + three + " " + four;
							}
						} catch (ArrayIndexOutOfBoundsException e) {

						}
						try {
							if (t.tray[i + 1][j] != null && tray[t.tray[i + 1][j].getBottom()][j] == null) {
								three = i + 1;
								four = j;
								return one + " " + two + " " + three + " " + four;
							}

						} catch (ArrayIndexOutOfBoundsException ee) {
						}

					}
				}

			}
		}
		return null;

	}

	/**
	 * toString
	 * 
	 * Returns a string representation of the tray.
	 */
	public String toString() {
		String result = "";
		for (int i = 0, h = Solver.myHeight; i < h; i++) {
			for (int j = 0, w = Solver.myWidth; j < w; j++) {
				if (tray[i][j] == null) {
					result += "empty";
				} else {
					result += tray[i][j].toString();
				}
				result += "\n";
			}
		}

		return result;
	}

	/**
	 * equals
	 * 
	 * Takes in tray @param t and checks if it is equal to another tray.
	 * 
	 * @returns true if the above condition is met. False if a block is not
	 *          stored at the same position.
	 */
	public boolean equals(Object t) {
		if (t == null) {
			return false;
		}

		for (Block b : blockSet) {
			if (!b.equals(((Tray) t).tray[b.getTop()][b.getLeft()])) {
				return false;
			}

		}

		return true;
	}

	/**
	 * hashCode
	 * 
	 * Generates a hashcode for the trays.
	 */
	public int hashCode() {
		int result = 0;
		for (int i = 0; i < Solver.myHeight; i++) {
			for (int j = 0; j < Solver.myWidth; j++) {
				if (tray[i][j] != null) {
					result = result * 2 + tray[i][j].hashCode();
				} else {
					result = result * 2;
				}
			}
			result = result * 3;
		}

		return result;
	}

}
