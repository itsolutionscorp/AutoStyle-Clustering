import java.util.*;
import java.io.*;

public class Solver {
	private Tray initTray;
	private HashSet<Integer> visited;
	private ArrayList<Block> finishConfig;

	public void init(File start, File goal) {
		visited = new HashSet<Integer>();
		if (goal.exists()) {
			finishConfig = new ArrayList<Block>();
			Scanner fileScanner = null;
			try {
				fileScanner = new Scanner(goal);
				while (fileScanner.hasNext()) {
					try {
						finishConfig.add(new Block(fileScanner.nextInt(), fileScanner.nextInt(), fileScanner.nextInt(),
								fileScanner.nextInt()));
					} catch (InputMismatchException e) {
						return;
					}
				}
				fileScanner.close();
			} catch (FileNotFoundException e) {
				return;
			}
			
		} else
			return;
		if (start.exists()) {
			initTray = new Tray(start);
			visited.add(initTray.hashCode);
		} else
			return;
		solve();
	}

	public void solve() {
		Stack<Tray> bfs = new Stack<Tray>();
		if (!initTray.isPossible()) {
			return;
		}
		bfs.add(initTray);
		Tray rtn = null;
		while (!bfs.peek().blocks.containsAll(finishConfig)) {
			rtn = bfs.pop();
			if (rtn.isPossible()) {
				for (Tray t : rtn.myNexts){
					bfs.push(t);
				}
			} else {
				rtn.myPrev.myNexts.remove(rtn);
			}
			if (bfs.size() == 0) {
				return;				
			}
		}
		rtn = bfs.pop();
		rtn.makePath();
	}

	public static void main(String[] args) {
		Solver s = new Solver();
		s.init(new File(args[0]), new File(args[1]));
	}

	private class Tray {
		private boolean[][] blockPos;
		private HashSet<Block> blocks;
		private Scanner fileScanner;
		private HashSet<Tray> myNexts;
		private int hashCode;
		private Tray myPrev;
		private String moveTaken;

		Tray(File start) {
			myNexts = new HashSet<Tray>();
			blocks = new HashSet<Block>();
			try {
				fileScanner = new Scanner(start);
			} catch (FileNotFoundException e) {
				return;
			}
			try {
				blockPos = new boolean[fileScanner.nextInt()][fileScanner.nextInt()];
			} catch (NoSuchElementException e) {
				return;
			}
			while (fileScanner.hasNext()) {
				int y, x, y2, x2;
				try {
					y = fileScanner.nextInt();
					x = fileScanner.nextInt();
					y2 = fileScanner.nextInt();
					x2 = fileScanner.nextInt();
				} catch (NoSuchElementException e) {
					return;
				}
				Block b = new Block(y, x, y2, x2);
				blocks.add(b);
				for (int i = y; i <= y2; i++) {
					for (int j = x; j <= x2; j++) {
						blockPos[i][j] = true;
					}
				}
			}
			int hash = 0;
			for (Block b : blocks) {
				hash += (b.hashCode() * 5);
			}
			hashCode = hash;
		}

		Tray(Tray prev, int y, int x) {
			myPrev = prev;
			blockPos = new boolean[y][x];
			blocks = new HashSet<Block>();
			myNexts = new HashSet<Tray>();
		}

		public void makePath() {
			Tray pointer = this;
			Stack<String> printer = new Stack<String>();
			while (pointer != null) {
				if (pointer.myPrev != null)
					printer.push(pointer.moveTaken);
				pointer = pointer.myPrev;
			}
			while (!printer.isEmpty()) {
				System.out.println(printer.pop());
			}
		}


		//We got the idea for hashcode prediction from Sam and Frances! :)
		private boolean isPossible() {
			boolean bo = false;
			for (Block b : blocks) {
				if (b.myX > 0) {
					for (int i = b.myY; i <= b.myY2; i++) {
						if (blockPos[i][b.myX - 1]) {
							b.moveLeft = false;
							break;
						}
						b.moveLeft = true;
					}
					if (b.moveLeft){
						bo = true;
						Block block = new Block(b.myY, b.myX - 1, b.myY2, b.myX2 - 1);
					if (!visited.contains((this.hashCode - (b.hashCode() * 5)) + (block.hashCode() * 5))) {
						Tray t = new Tray(this, blockPos.length, blockPos[0].length);
						t.moveTaken = "" + b.myY + " " + b.myX + " " + block.myY + " " + block.myX;
						int hashCode2 = 0;
						for (Block b2 : blocks) {
							if (b2 == b) {
								t.blocks.add(block);
								for (int i = block.myY; i <= block.myY2; i++) {
									for (int j = block.myX; j <= block.myX2; j++) {
										t.blockPos[i][j] = true;
									}
								}
								hashCode2 += (block.hashCode() * 5);
							} else {
								t.blocks.add(b2);
								for (int i = b2.myY; i <= b2.myY2; i++) {
									for (int j = b2.myX; j <= b2.myX2; j++) {
										t.blockPos[i][j] = true;
									}
								}
								hashCode2 += (b2.hashCode() * 5);
							}
						}
						t.hashCode = hashCode2;
						visited.add(t.hashCode);
						myNexts.add(t);
					}
					}
				}
				if (b.myY > 0) {
					for (int i = b.myX; i <= b.myX2; i++) {
						if (blockPos[b.myY - 1][i]) {
							b.moveUp = false;
							break;
						}
						b.moveUp = true;
					}
					if (b.moveUp){
						bo = true;
						Block block = new Block(b.myY - 1, b.myX, b.myY2 - 1, b.myX2);
					if (!visited.contains((this.hashCode - (b.hashCode() * 5)) + (block.hashCode() * 5))) {
						Tray t = new Tray(this, blockPos.length, blockPos[0].length);
						t.moveTaken = "" + b.myY + " " + b.myX + " " + block.myY + " " + block.myX;
						int hashCode2 = 0;
						for (Block b2 : blocks) {
							if (b2 == b) {
								t.blocks.add(block);
								for (int i = block.myY; i <= block.myY2; i++) {
									for (int j = block.myX; j <= block.myX2; j++) {
										t.blockPos[i][j] = true;
									}
								}
								hashCode2 += (block.hashCode() * 5);
							} else {
								t.blocks.add(b2);
								for (int i = b2.myY; i <= b2.myY2; i++) {
									for (int j = b2.myX; j <= b2.myX2; j++) {
										t.blockPos[i][j] = true;
									}
								}
								hashCode2 += (b2.hashCode() * 5);
							}
						}
						t.hashCode = hashCode2;
						visited.add(t.hashCode);
						myNexts.add(t);
					}
					}
				}
				if (b.myX2 < blockPos[0].length - 1) {
					for (int i = b.myY; i <= b.myY2; i++) {
						if (blockPos[i][b.myX2 + 1]) {
							b.moveRight = false;
							break;
						}
						b.moveRight = true;
					}
					if (b.moveRight){
						bo = true;
						Block block = new Block(b.myY, b.myX + 1, b.myY2, b.myX2 + 1);
					if (!visited.contains((this.hashCode - (b.hashCode() * 5)) + (block.hashCode() * 5))) {
						Tray t = new Tray(this, blockPos.length, blockPos[0].length);
						t.moveTaken = "" + b.myY + " " + b.myX + " " + block.myY + " " + block.myX;
						int hashCode2 = 0;
						for (Block b2 : blocks) {
							if (b2 == b) {
								t.blocks.add(block);
								for (int i = block.myY; i <= block.myY2; i++) {
									for (int j = block.myX; j <= block.myX2; j++) {
										t.blockPos[i][j] = true;
									}
								}
								hashCode2 += (block.hashCode() * 5);
							} else {
								t.blocks.add(b2);
								for (int i = b2.myY; i <= b2.myY2; i++) {
									for (int j = b2.myX; j <= b2.myX2; j++) {
										t.blockPos[i][j] = true;
									}
								}
								hashCode2 += (b2.hashCode() * 5);
							}
						}
						t.hashCode = hashCode2;
						myNexts.add(t);
						visited.add(t.hashCode);
					}
					}
				}
				if (b.myY2 < blockPos.length - 1) {
					for (int i = b.myX; i <= b.myX2; i++) {
						if (blockPos[b.myY2 + 1][i]) {
							b.moveDown = false;
							break;
						}
						b.moveDown = true;
					}
					if (b.moveDown){
						bo = true;
						Block block = new Block(b.myY + 1, b.myX, b.myY2 + 1, b.myX2);
					if (!visited.contains((this.hashCode - (b.hashCode() * 5)) + (block.hashCode() * 5))) {
						Tray t = new Tray(this, blockPos.length, blockPos[0].length);
						t.moveTaken = "" + b.myY + " " + b.myX + " " + block.myY + " " + block.myX;
						int hashCode2 = 0;
						for (Block b2 : blocks) {
							if (b2 == b) {
								t.blocks.add(block);
								for (int i = block.myY; i <= block.myY2; i++) {
									for (int j = block.myX; j <= block.myX2; j++) {
										t.blockPos[i][j] = true;
									}
								}
								hashCode2 += (block.hashCode() * 5);
							} else {
								t.blocks.add(b2);
								for (int i = b2.myY; i <= b2.myY2; i++) {
									for (int j = b2.myX; j <= b2.myX2; j++) {
										t.blockPos[i][j] = true;
									}
								}
								hashCode2 += (b2.hashCode() * 5);
							}
						}
						t.hashCode = hashCode2;
						visited.add(t.hashCode);
						myNexts.add(t);
					}
					}
				}
			}
			return bo;
		}

		@Override
		public int hashCode() {
			return hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			return obj.hashCode() == this.hashCode();
		}

	}

	private class Block {
		private int myX, myY, myX2, myY2;
		private boolean moveUp, moveDown, moveLeft, moveRight;

		public Block(int y, int x, int y2, int x2) {
			myX = x;
			myY = y;
			myX2 = x2;
			myY2 = y2;
		}

		@Override
		public int hashCode() {
			int hash = 0;
			hash += myX;
			hash *= 53;
			hash += myY;
			hash *= 47;
			hash += myX2;
			hash *= 43;
			hash += myY2;
			hash *= 41;
			hash += myX * myY2;
			hash *= 37;
			hash += myX2 * myY;
			hash *= 31;
			hash += myX * myX2;
			hash *= 29;
			hash += myY * myY2;
			hash *= 23;
			hash += myY2 * myX2;
			hash *= 19;
			hash += myX * myY;
			hash *= 17;
			hash += myX2 * myY2;
			hash *= 13;
			hash += myX * myX;
			hash *= 11;
			hash += myY * myY;
			hash *= 7;
			return hash;
		}

		@Override
		public boolean equals(Object obj) {
			return obj.hashCode() == this.hashCode();
		}

	}

}
