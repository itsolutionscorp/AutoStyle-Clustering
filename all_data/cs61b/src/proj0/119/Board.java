import java.lang.Math;

public class Board {
	private boolean isEmpty;
	private Piece[][] pieces;
	private Piece chosen;
	private int size = 8;
	private int[] position;
	private boolean moved;
	private String player = "Fire";

	public static void main (String[] args) {
		Board b = new Board(false);
		StdDrawPlus.setScale(0, b.size);
		b.drawBoard();
		while (true) {
			b.drawBoard();
			if (StdDrawPlus.mousePressed()) {
				int x = b.edgeCorrect(StdDrawPlus.mouseX());
				int y = b.edgeCorrect(StdDrawPlus.mouseY());
				if (b.canSelect(x, y)) {
					b.select(x, y);
				}
			} else if (StdDrawPlus.isSpacePressed()) {
				if (b.canEndTurn()) {
					b.endTurn();
				}
			}
			StdDrawPlus.show(10);
		}
	}

/*
			String winner = b.winner();
			while (winner != null) {
				b.drawBoard();
				if (StdDrawPlus.mousePressed()) {
					int x = (int) StdDrawPlus.mouseX();
					int y = (int) StdDrawPlus.mouseY();
					if ((b.canSelect(x, y)) && (b.chosen == null)) {
						b.select(x, y);
					} else if (b.canSelect(x, y) && 
						       b.validMove(b.chosen.xpos, b.chosen.ypos, x, y)) {
						b.chosen.move(x, y);
						b.place(b.chosen, x, y);
					}
					b.drawBoard();
				} else if (b.canEndTurn()) {
					b.endturn();
					winner = b.winner();
				}
            }
*/

	public Board(boolean shouldBeEmpty) {
		isEmpty = shouldBeEmpty;
		pieces = new Piece[size][size];
		position = new int[2];
		moved = false;
		if (!isEmpty) {
			setFirePieces();
			setWaterPieces();
		}
	}

	private int edgeCorrect(double c) {
		if (c < 0) {
			return -1;
		} else {
			return (int) c;
		}
	}

	private void drawBoard() {
		for (int i = 0; i < size; i = i + 1) {
				for (int j = 0; j < size; j = j + 1) {
					if ((i + j) % 2 == 0) {
						StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					} else {
						StdDrawPlus.setPenColor(StdDrawPlus.RED);
					}
					StdDrawPlus.filledSquare(i + .5, j + .5, .5);
					if (pieces[i][j] != null) {
						Piece p = pieces[i][j];
						if (chosen == p) {
							StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
							StdDrawPlus.filledSquare(position[0] + .5, position[1] + .5, .5);
						}
						StdDrawPlus.picture(i + .5, j + .5, imageForm(p), 1, 1);
					}
				}
			}
	}

	private String imageForm(Piece p) {
		String kind = "pawn";
		String img;
		if (p.isBomb()) {
			kind = "bomb";
		} else if (p.isShield()) {
			kind = "shield";
		}

		if (p.isFire() && p.isKing()) {
			img = "img/" + kind + "-fire-crowned.png";
		} else if (p.isFire()) {
			img = "img/" + kind + "-fire.png";
		} else if (p.isKing()) {
			img = "img/" + kind + "-water-crowned.png";
		} else {
			img = "img/" + kind + "-water.png";
		}

		return img;
	}

	private void drawEmptyBoard() {
		for (int i = 0; i < size; i = i + 1) {
				for (int j = 0; j < size; j = j + 1) {
					if ((i + j) % 2 == 0) {
						StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					} else {
						StdDrawPlus.setPenColor(StdDrawPlus.RED);
					}
					StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				}
			}
	}

	private void setFirePieces() {
		for (int j = 0; j < 3; j = j + 1) {
			for (int i = 0; i < size; i = i + 1) {
				String kind = "pawn";
				if (j == 1) {
					kind = "shield";
				} else if (j == 2) {
					kind = "bomb";
				}
				if ((i + j) % 2 == 0) {
					pieces[i][j] = new Piece(true, this, i, j, kind);
				}
			}
		}
	}

	private void setWaterPieces() {
		for (int j = size - 1; j > size - 4; j = j - 1) {
			for (int i = 0 ; i < size; i = i + 1) {
				String kind = "pawn";
				if (j == size - 2) {
					kind = "shield";
				} else if (j == size - 3) {
					kind = "bomb";
				}
				if ((i + j) % 2 == 0) {
					pieces[i][j] = new Piece(false, this, i, j, kind);
				}
			}
		}
	}

	private Piece[] getFirePieces() {
		int set = 0;
		Piece[] piece_lst = new Piece[12];
		for (int j = 0; j < size; j = j + 1) {
			for (int i = 0; i < size; i = i + 1) {
				Piece p = pieces[i][j];
				if ((p != null) && (p.isFire())) {
					piece_lst[set] = p;
					set = set + 1;
				}
			}
		}
		return piece_lst;
	}

	private Piece[] getWaterPieces() {
		int set = 0;
		Piece[] piece_lst = new Piece[12];
		for (int j = 0; j < size; j = j + 1) {
			for (int i = 0; i < size; i = i + 1) {
				Piece p = pieces[i][j];
				if ((p != null) && (!p.isFire())) {
					piece_lst[set] = p;
					set = set + 1;
				}
			}
		}
		return piece_lst;
	}

	public Piece pieceAt(int x, int y) {
		if (((0 <= x) && (x < size)) && ((0 <= y) && (y < size))) {
			return pieces[x][y];
		} else {
			return null;
		}
	}

	public boolean canSelect(int x, int y) {
		Piece p = pieceAt(x, y);
		if ((p == null) && (chosen == null)) {
			return false;
		}
		if (p != null) {
			if (p.isFire() && (player == "Fire")) {
				return ((chosen == null) || (!moved));
			} else if ((!p.isFire()) && (player == "Water")) {
				return ((chosen == null) || (!moved));
			} else {
				return false;
			}
		} else if (((!moved) || (chosen.hasCaptured())) && 
			      (validMove(position[0], position[1], x, y))) {
			return true;
		} else {
			return false;
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece initial = pieceAt(xi, yi);
		Piece terminal = pieceAt(xf, yf);
		if ((initial == null) || (terminal != null)) {
			return false;
		}
		boolean immediate = ((Math.abs(xf - xi) == 1) && (Math.abs(yf - yi) == 1));
		boolean distant = ((Math.abs(xf - xi) == 2) && (Math.abs(yf - yi) == 2));
		if ((immediate) && (!moved)) {
			if (initial.isKing()) {
				return true;
			} else if (initial.isFire() && (yf > yi)) {
				return true;
			} else if ((!initial.isFire()) && (yf < yi)) {
				return true;
			}
			return false;
		} else if (distant) {
			int inter_x = (xf + xi)/2;
			int inter_y = (yf + yi)/2;
			Piece intermediate = pieceAt(inter_x, inter_y);
			if (intermediate != null) {
				if (initial.isKing()) {
					return (initial.isFire() ^ intermediate.isFire());
				} else if (initial.isFire() && (yf > yi)) {
					return (initial.isFire() ^ intermediate.isFire());
				} else if ((!initial.isFire()) && (yf < yi)) {
					return (initial.isFire() ^ intermediate.isFire());
				}
			} else {
				return false;
			}
		}
		return false;
	}

	public void select(int x, int y) {
		if ((chosen != null) && (pieceAt(x, y) == null)) {
			moved = true;
			position[0] = x;
			position[1] = y;
			chosen.move(x, y);
		} else {
			position[0] = x;
			position[1] = y;
			chosen = pieceAt(x, y);
		}
	}

	public void place(Piece p, int x, int y) {
		if (p == null) {
			System.out.println("Piece is null");
			return;
		} else if (((x < 0) || (size <= x)) || ((y < 0) || (size <= y))) {
			System.out.format("Coordinates (%d, %d) out of bounds", x, y);
			return;
		} else {
			pieces[x][y] = p;
		}
	}

	public Piece remove(int x, int y) {
		if (((x < 0) || (size <= x)) || ((y < 0) || (size <= y))) {
			System.out.format("Coordinates (%d, %d) out of bounds", x, y);
			return null;
		}
		Piece reject = pieces[x][y];
		if (reject == null) {
			System.out.format("No Piece at (%d, %d)", x, y);
			return null;
		}
		pieces[x][y] = null;
		return reject;
	}

	public boolean canEndTurn() {
		if (chosen == null) {
			return false;
		} else {
			return ((moved) || (chosen.hasCaptured()));
		}
	}

	public void endTurn() {
		chosen.doneCapturing();
		moved = false;
		chosen = null;
		position = new int[2];
		String win = winner();
		if (win != null) {
			System.out.println(win);
		}
		if (player == "Fire") {
			player = "Water";
		} else {
			player = "Fire";
		}
	}

	public String winner() {
		Piece[] fire_pieces = getFirePieces();
		Piece[] water_pieces = getWaterPieces();
		if ((fire_pieces[0] == null) && (water_pieces[0] == null)) {
			return "No one";
		} else if ((fire_pieces[0] != null) && (water_pieces[0] == null)) {
			return "Fire";
		} else if ((fire_pieces[0] == null) && (water_pieces[0] != null)) {
			return "Water";
		} else {
			return null;
		}
	}
 

}