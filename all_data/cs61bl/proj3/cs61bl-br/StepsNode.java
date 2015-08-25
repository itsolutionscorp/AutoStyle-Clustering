import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class StepsNode {
	StepsNode myParent;
	boolean[][] occupiedPos;
	LinkedHashSet<SquareBlock> squares;
	LinkedHashSet<StepsNode> theNexts;
	int hashCode;
	
	int xi;
	int yj;
	String moveMade;
	
	StepsNode(String file) {
		theNexts = new LinkedHashSet<StepsNode>();
		squares = new LinkedHashSet<SquareBlock>();
		try {
			FileReader reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			String s1 = null;
			String[] splited;
			s1 = br.readLine();
			splited = s1.split(" ");
			xi = Integer.parseInt(splited[0]);
			yj = Integer.parseInt(splited[1]);
			occupiedPos = new boolean[xi][yj];
			while((s1 = br.readLine()) != null) {
				splited = s1.split(" ");
				int x = Integer.parseInt(splited[0]);
				int y = Integer.parseInt(splited[1]); 
				int x2 = Integer.parseInt(splited[2]);
				int y2 = Integer.parseInt(splited[3]);
				SquareBlock sq = new SquareBlock(x, y, x2, y2);
				squares.add(sq);
				for (int i = x; i <= x2; i++) {
					for (int j = y; j <= y2; j++) {
						occupiedPos[i][j] = true;
					}
				}
			}

			br.close();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String s = "";
		for (SquareBlock sq : squares) {
			s += sq.hashCode + "";
		}
		hashCode = s.hashCode();
	}
	/**
	 * 
	 * @param prev
	 * @param x shape of the board
	 * @param y shape of the board
	 */
	StepsNode(StepsNode parent, int x, int y) {
		myParent = parent;
		occupiedPos = new boolean[x][y];
		squares = new LinkedHashSet<SquareBlock>();
		theNexts = new LinkedHashSet<StepsNode>();
		xi=x;
		yj=y;
	}

	public void makeMoves() {
		for (SquareBlock sqOld : squares) {
			if (sqOld.goDown) {
				StepsNode newNode = new StepsNode(this, xi, yj);
				SquareBlock sqNew = new SquareBlock(sqOld.fX + 1, sqOld.fY, sqOld.sX + 1, sqOld.sY);
				newNode.moveMade = "" + sqOld.fX + " " + sqOld.fY + " " + sqNew.fX + " " + sqNew.fY;
				String s = "";
				for (SquareBlock sq : squares) {
					if (sq == sqOld) {
						newNode.squares.add(sqNew);
						for (int i = sqNew.fX; i <= sqNew.sX; i++) {
							for (int j = sqNew.fY; j <= sqNew.sY; j++) {
								newNode.occupiedPos[i][j] = true;
							}
						}
						s += sqNew.hashCode + "";
					} else {
						newNode.squares.add(sq);
						for (int i = sq.fX; i <= sq.sX; i++) {
							for (int j = sq.fY; j <= sq.sY; j++) {
								newNode.occupiedPos[i][j] = true;
							}
						}
						s += sq.hashCode + "";
					}
				}
				newNode.hashCode = s.hashCode();
				//				newNode.shapeCode = sqOld.shape;
				theNexts.add(newNode);
			}
			if (sqOld.goUp) {
				StepsNode newNode = new StepsNode(this, xi, yj);
				SquareBlock sqNew = new SquareBlock(sqOld.fX - 1, sqOld.fY, sqOld.sX - 1, sqOld.sY);
				newNode.moveMade = "" + sqOld.fX + " " + sqOld.fY + " " + sqNew.fX + " " + sqNew.fY;
				String s = "";
				for (SquareBlock sq : squares) {
					if (sq == sqOld) {
						newNode.squares.add(sqNew);
						for (int i = sqNew.fX; i <= sqNew.sX; i++) {
							for (int j = sqNew.fY; j <= sqNew.sY; j++) {
								newNode.occupiedPos[i][j] = true;
							}
						}
						s += sqNew.hashCode + "";
					} else {
						newNode.squares.add(sq);
						for (int i = sq.fX; i <= sq.sX; i++) {
							for (int j = sq.fY; j <= sq.sY; j++) {
								newNode.occupiedPos[i][j] = true;
							}
						}
						s += sq.hashCode + "";
					}
				}
				newNode.hashCode = s.hashCode();
				theNexts.add(newNode);
			}
			if (sqOld.goLeft) {
				StepsNode newNode = new StepsNode(this, xi, yj);
				SquareBlock sqNew = new SquareBlock(sqOld.fX, sqOld.fY - 1, sqOld.sX, sqOld.sY - 1);
				newNode.moveMade = "" + sqOld.fX + " " + sqOld.fY + " " + sqNew.fX + " " + sqNew.fY;
				String s = "";
				for (SquareBlock sq : squares) {
					if (sq == sqOld) {
						newNode.squares.add(sqNew);
						for (int i = sqNew.fX; i <= sqNew.sX; i++) {
							for (int j = sqNew.fY; j <= sqNew.sY; j++) {
								newNode.occupiedPos[i][j] = true;
							}
						}
						s += sqNew.hashCode + "";
					} else {
						newNode.squares.add(sq);
						for (int i = sq.fX; i <= sq.sX; i++) {
							for (int j = sq.fY; j <= sq.sY; j++) {
								newNode.occupiedPos[i][j] = true;
							}
						}
						s += sq.hashCode + "";
					}
				}
				newNode.hashCode = s.hashCode();
				theNexts.add(newNode);
			}
			if (sqOld.goRight) {
				StepsNode newNode = new StepsNode(this, xi, yj);
				SquareBlock sqNew = new SquareBlock(sqOld.fX, sqOld.fY + 1, sqOld.sX, sqOld.sY + 1);
				newNode.moveMade = "" + sqOld.fX + " " + sqOld.fY + " " + sqNew.fX + " " + sqNew.fY;
				String s = "";
				for (SquareBlock sq : squares) {
					if (sq == sqOld) {
						newNode.squares.add(sqNew);
						for (int i = sqNew.fX; i <= sqNew.sX; i++) {
							for (int j = sqNew.fY; j <= sqNew.sY; j++) {
								newNode.occupiedPos[i][j] = true;
							}
						}
						s += sqNew.hashCode + "";
					} else {
						newNode.squares.add(sq);
						for (int i = sq.fX; i <= sq.sX; i++) {
							for (int j = sq.fY; j <= sq.sY; j++) {
								newNode.occupiedPos[i][j] = true;
							}
						}
						s += sq.hashCode + "";
					}
				}
				newNode.hashCode = s.hashCode();
				theNexts.add(newNode);
			}
		}
	}

	public void makeFinalPrint() {
		StepsNode cur = this;
		Stack<String> printer = new Stack<String>();
		while (cur != null) {
			if (cur.myParent != null)
				printer.push(cur.moveMade);
			cur = cur.myParent;
		}
		while (!printer.isEmpty()) {
			System.out.println(printer.pop());
		}
	}

	public boolean couldMove() {
		boolean value = false;
		for (SquareBlock sq : squares) {
			if (sq.fY > 0) {
				for (int i = sq.fX; i <= sq.sX; i++) {
					if (occupiedPos[i][sq.fY - 1]) {
						sq.goLeft = false;
						break;
					}
					sq.goLeft = true;
					value = true;
				}
			}
			if (sq.sY < yj - 1) {
				for (int i = sq.fX; i <= sq.sX; i++) {
					if (occupiedPos[i][sq.sY + 1]) {
						sq.goRight = false;
						break;
					}
					sq.goRight = true;
					value = true;
				}
			}
			if (sq.fX > 0) {
				for (int j = sq.fY; j <= sq.sY; j++) {
					if (occupiedPos[sq.fX - 1][j]) {
						sq.goUp = false;
						break;
					}
					sq.goUp = true;
					value = true;
				}
			}

			if (sq.sX < xi - 1) {
				for (int j = sq.fY; j <= sq.sY; j++) {
					if (occupiedPos[sq.sX + 1][j]) {
						sq.goDown = false;
						break;
					}
					sq.goDown = true;
					value = true;
				}
			}
		}
		return value;
	}

	public int hashCode() {
		return hashCode;
	}

	public boolean equals(Object obj) {
		return obj.hashCode() == this.hashCode;
	}

}