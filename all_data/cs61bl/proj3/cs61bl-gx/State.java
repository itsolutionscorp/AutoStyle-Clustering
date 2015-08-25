import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;

public class State implements Comparable<State> {
	public static short[] initX = new short[65536];
	public static short[] initY = new short[65536];
	public static short[] initWidth = new short[65536];
	public static short[] initHeight = new short[65536];
	public static short[] goalX = new short[65536];
	public static short[] goalY = new short[65536];
	public static short[] goalWidth = new short[65536];
	public static short[] goalHeight = new short[65536];
	public static boolean[][] blockMap = new boolean[256][256];
	public static HashMap<Integer, LinkedList<Integer>> initSizes = new HashMap<Integer, LinkedList<Integer>>();
	public static HashMap<Integer, LinkedList<Integer>> goalSizes = new HashMap<Integer, LinkedList<Integer>>();
	public static int trayWidth, trayHeight;
	public static int goalNum = 0, initNum = 0;
	public static final int WEST = 1, EAST = 2, NORTH = 3, SOUTH = 4;
	public static boolean general = true;
	public static int goalHoleX = 0, goalHoleY = 0;
	public byte[] blockX, blockY;
	public State prev;
	public double priority;
	
	public static void addInit(int[] input) {
		initX[initNum] = (short) input[1];
		initY[initNum] = (short) input[0];
		initWidth[initNum] = (short) (input[3] - input[1]);
		initHeight[initNum] = (short) (input[2] - input[0]);
		
		if (!initSizes.containsKey(initWidth[initNum] * 256 + initHeight[initNum])) {
			LinkedList<Integer> list = new LinkedList<Integer>();
			list.add(initNum);
			initSizes.put(initWidth[initNum] * 256 + initHeight[initNum], list);
		} else {
			LinkedList<Integer> list = initSizes.get(initWidth[initNum] * 256 + initHeight[initNum]);
			list.add(initNum);
		}
		
		initNum++;
	}
	
	public static void addGoal(int[] input) {
		goalX[goalNum] = (short) input[1];
		goalY[goalNum] = (short) input[0];
		goalWidth[goalNum] = (short) (input[3] - input[1]);
		goalHeight[goalNum] = (short) (input[2] - input[0]);
		
		if (!goalSizes.containsKey(goalWidth[goalNum] * 256 + goalHeight[goalNum])) {
			LinkedList<Integer> list = new LinkedList<Integer>();
			list.add(goalNum);
			goalSizes.put(goalWidth[goalNum] * 256 + goalHeight[goalNum], list);
		} else {
			LinkedList<Integer> list = goalSizes.get(goalWidth[goalNum] * 256 + goalHeight[goalNum]);
			list.add(goalNum);
		}
		
		goalNum++;
	}
	
	public static void checkTray(int width, int height) {
		trayWidth = width;
		trayHeight = height;
		
		if (initNum < goalNum) {
			Solver.error = true;
			return;
		}
		
		for (int i = 0; i < trayHeight; i++) {
			for (int j = 0; j < trayWidth; j++) {
				blockMap[i][j] = false;
			}
		}
		
		for (int i = 0; i < initNum; i++) {
			for (int j = initY[i]; j <= (initY[i] + initHeight[i]); j++) {
				for (int k = initX[i]; k <= (initX[i] + initWidth[i]); k++) {
					if (blockMap[j][k]) {
						Solver.error = true;
						return;
					}
					blockMap[j][k] = true;
				}
			}
		}
		
		for (int i = 0; i < trayHeight; i++) {
			for (int j = 0; j < trayWidth; j++) {
				blockMap[i][j] = false;
			}
		}
		
		for (int i = 0; i < goalNum; i++) {
			for (int j = goalY[i]; j <= (goalY[i] + goalHeight[i]); j++) {
				for (int k = goalX[i]; k <= (goalX[i] + goalWidth[i]); k++) {
					if (blockMap[j][k]) {
						Solver.error = true;
						return;
					}
					blockMap[j][k] = true;
				}
			}
		}
		
		if (initNum == goalNum) {
			LinkedList<Integer> singles = initSizes.get(0);
			if (singles != null) {
				if (singles.size() == initNum) {
					general = false;
					for (int i = 0; i < trayHeight; i++) {
						for (int j = 0; j < trayWidth; j++) {
							if (!blockMap[i][j]) {
								goalHoleX = j;
								goalHoleY = i;
								break;
							}
						}
					}
				}
			}
		}		
	}

	public State() {
		blockX = new byte[initNum];
		blockY = new byte[initNum];
		prev = null;
		
		for (int i = 0; i < initNum; i++) {
			blockX[i] = (byte) (initX[i] + Byte.MIN_VALUE);
			blockY[i] = (byte) (initY[i] + Byte.MIN_VALUE);
		}
		
		priority = priority();
	}
	
	public State(State input) {
		blockX = new byte[initNum];
		blockY = new byte[initNum];
		
		for (int i = 0; i < initNum; i++) {
			blockX[i] = input.blockX[i];
			blockY[i] = input.blockY[i];
		}
		prev = input;
	}

	public int isMovable(int block) {
		int result = 0;
		
		for (int i = 0; i < trayHeight; i++) {
			for (int j = 0; j < trayWidth; j++) {
				blockMap[i][j] = false;
			}
		}
		
		for (int i = 0; i < initNum; i++) {
			if (i != block) {
				for (int j = blockY[i]; j <= (blockY[i] + initHeight[i]); j++) {
					for (int k = blockX[i]; k <= (blockX[i] + initWidth[i]); k++) {
						blockMap[j - Byte.MIN_VALUE][k - Byte.MIN_VALUE] = true;
					}
				}
			}
		}
		
		int leftBorder = blockX[block] - Byte.MIN_VALUE;
		if (leftBorder > 0) { // West
			boolean canWest = true;
			for (int i = blockY[block]; i <= blockY[block] + initHeight[block]; i++) {
				if (blockMap[i - Byte.MIN_VALUE][leftBorder - 1]) {
					canWest = false;
					break;
				}
			}
			
			if (canWest) result += 8;
			
		}
		
		int rightBorder = blockX[block] - Byte.MIN_VALUE + initWidth[block];
		if (rightBorder < (trayWidth - 1)) { // East
			boolean canEast = true;
			for (int i = blockY[block]; i <= blockY[block] + initHeight[block]; i++) {
				if (blockMap[i - Byte.MIN_VALUE][rightBorder + 1]) {
					canEast = false;
					break;
				}
			}
			
			if (canEast) result += 4;
		}
		
		int topBorder = blockY[block] - Byte.MIN_VALUE;
		if (topBorder > 0) { // North
			boolean canNorth = true;
			for (int i = blockX[block]; i <= blockX[block] + initWidth[block]; i++) {
				if (blockMap[topBorder - 1][i - Byte.MIN_VALUE]) {
					canNorth = false;
					break;
				}
			}
			
			if (canNorth) result += 2;
		}
		
		int bottomBorder = blockY[block] - Byte.MIN_VALUE + initHeight[block];
		if (bottomBorder < (trayHeight - 1)) { // South
			boolean canSouth = true;
			for (int i = blockX[block]; i <= blockX[block] + initWidth[block]; i++) {
				if (blockMap[bottomBorder + 1][i - Byte.MIN_VALUE]) {
					canSouth = false;
					break;
				}
			}
			
			if (canSouth) result += 1;
		}
		
		return result;
	}

	public State move(int block, int dir) {
		State moved = new State(this);

		switch(dir) {
		case WEST:
			moved.blockX[block]--;
			break;
		case EAST:
			moved.blockX[block]++;
			break;
		case NORTH:
			moved.blockY[block]--;
			break;
		case SOUTH:
			moved.blockY[block]++;
			break;
		}
		
		moved.priority = moved.priority();
		return moved;
	}
	
	public static String stringID(State input) {
		final char[] str = new char[trayWidth * trayHeight];
		
		for (int i = 0; i < initNum; i++) {
			for (int j = input.blockY[i]; j <= input.blockY[i] + initHeight[i]; j++) {
				for (int k = input.blockX[i]; k <= input.blockX[i] + initWidth[i]; k++) {
					str[(j - Byte.MIN_VALUE) * trayWidth + k - Byte.MIN_VALUE] = (char) (initHeight[i] * 256 + initWidth[i] + 1);
				}	
			}
		}

		return new String(str);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof State) {
			String input1 = stringID(this);
			String input2 = stringID((State) obj);
			return (input1.equals(input2));
		}
		
		return false;
	}

	public boolean goalMeet() {
		HashSet<Long> initBlocks = new HashSet<Long>();
		
		for (int i = 0; i < initNum; i++) {
			long temp = 256 * 256 * 256 * (blockX[i] - Byte.MIN_VALUE) + 256 * 256 * (blockY[i] - Byte.MIN_VALUE) + 256 * initWidth[i] + initHeight[i];
			initBlocks.add(temp);
		}

		for (int i = 0; i < goalNum; i++) {
			long temp = 256 * 256 * 256 * goalX[i] + 256 * 256 * goalY[i] + 256 * goalWidth[i] + goalHeight[i];
			if (!initBlocks.contains(temp)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		return stringID(this).hashCode();
	}
			
	public double priority() {
		double total = 0;		
		if(!general) {
			int currentHoleX = 0, currentHoleY = 0;
			for (int i = 0; i < trayHeight; i++) {
				for (int j = 0; j < trayWidth; j++) {
					blockMap[i][j] = false;
				}
			}
				
			for (int i = 0; i < initNum; i++) {
				blockMap[blockY[i] - Byte.MIN_VALUE][blockX[i] - Byte.MIN_VALUE] = true;
			}
					
			for (int i = 0; i < trayHeight; i++) {
				for (int j = 0; j < trayWidth; j++) {
					if (!blockMap[i][j]) {
						currentHoleX = j;
						currentHoleY = i;
						break;
					}
				}
			}
			total += Math.abs(currentHoleX - goalHoleX) + Math.abs(goalHoleY - currentHoleY);
		} else {
		
		for (int i = 0; i < trayHeight; i++) {
			for (int j = 0; j < trayWidth; j++) {
				if (goalSizes.containsKey(256 * j + i)) {
					int sumGoalX = 0, sumGoalY = 0, goalCount = 0;
					for (int id : goalSizes.get(256 * j + i)) {
						sumGoalX += goalX[id];
						sumGoalY += goalY[id];
						goalCount++;
					}
					
					int sumBlockX = 0, sumBlockY = 0, blockCount = 0;
					for (int id : initSizes.get(256 * j + i)) {
						sumBlockX += blockX[id] - Byte.MIN_VALUE;
						sumBlockY += blockY[id] - Byte.MIN_VALUE;
						blockCount++;
					}
					
					total += Math.abs(sumBlockX / blockCount - sumGoalX / goalCount) * goalCount + Math.abs(sumBlockY / blockCount - sumGoalY / goalCount) * goalCount;
				}
			}
		}
		}
				
		return total;
	}
	
	@Override
	public int compareTo(State other) {
		double dist1 = priority;
		double dist2 = other.priority;
		if (dist1 > dist2) {
			return 1;
		} else if (dist1 == dist2) {
			return 0;
		}
		
		return -1;
	}
}
