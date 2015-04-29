import java.lang.Math.*;

public class Board {

	private Piece[][] pieces;
	private Piece selected_piece;
	private int selected_x;
	private int selected_y;
	private boolean has_moved;
	private boolean has_selected;
	private boolean fire_turn;
	private boolean isFire;
	private String type;


	public static void main(String[] args) {
        int N = 8;
        boolean withPieces = true;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
		Board b = new Board(false);
		b.drawBoard(N, withPieces);

        while(true) {
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (b.canSelect(x, y)) {
                	b.select(x, y);
                	// StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	                // StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
                }
		        b.drawBoard(N, withPieces);
            }
            if (StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
            	b.endTurn();
            }
            StdDrawPlus.show(100);
        }
	}

	private void drawBoard(int N, boolean withPieces) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                if (selected_piece != null && selected_piece == pieces[i][j]) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
                if ((i + j) % 2 == 0) {
                	if (withPieces) {
                		String img;
                		String type;
                		if (pieces[i][j] != null) {
                			if (pieces[i][j].isFire()) {
                				img = "-fire";
                			} else {
                				img = "-water";
                			}
                			if (pieces[i][j].isKing()) {
                				img += "-crowned";
                			}
                			if (pieces[i][j].isBomb()) {
                				type = "bomb";
                			} else if (pieces[i][j].isShield()) {
                				type = "shield";
                			} else {
                				type = "pawn";
                			}
                			img = "img/" + type + img + ".png";
                			StdDrawPlus.picture(i + 0.5, j + 0.5, img, 1, 1);
                		}
                	}
                }
            }
        }
    }

    private Piece[][] makePieces(boolean all) {
    	pieces = new Piece[8][8];
    	if (all) {
	    	for (int i = 0; i < 8; i++) {
	    		for (int j = 0; j < 8; j++) {
					if ((i + j) % 2 == 0) {
						if (j < 3) isFire = true;
						else if (j > 4) isFire = false;
						double position = Math.abs(3.5 - j);
						if (position == 3.5) {
							type = "pawn";
						} else if (position == 2.5) {
							type = "shield";
						} else if (position == 1.5) {
							type = "bomb";
						}
						if (j != 3 && j != 4) {
							pieces[i][j] = new Piece(isFire, this, i, j, type);
						}
	    			}
	    		}
	    	}
	    }
	    return pieces;
    }

    public Board(boolean shouldBeEmpty) {
    	pieces = makePieces(!shouldBeEmpty);
    	has_moved = false;
    	has_selected = false;
    	fire_turn = true;
    	selected_piece = null;
    }

	public Piece pieceAt(int x, int y) {
		if (x > 7 || x < 0 || y > 7 || y < 0) {
			return null;
		} else {
			return pieces[x][y];
		}
	}

	public boolean canSelect(int x, int y) {
		if (pieceAt(x, y) != null && pieceAt(x, y).isFire() == fire_turn && !has_moved && (selected_piece == null || !selected_piece.hasCaptured())) {
			return true;
		} else if (pieceAt(x, y) == null && has_selected && validMove(selected_x, selected_y, x, y)) {
			System.out.println("can move here");
			return true;
		} else {
			System.out.println("can not move here");
			return false;
		}
	}
	

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (pieceAt(xi, yi) == null || pieceAt(xf, yf) != null || (xi == xf && yi == yf)) {
			return false;
		}
		if (!pieceAt(xi, yi).isKing()) {
			if (pieceAt(xi, yi).isFire()) {
				if (yf <= yi) return false;
			} else {
				if (yf >= yi) return false;
			}
		}
		if (Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2 && pieces[(xf + xi)/2][(yf + yi)/2] != null && pieces[(xf + xi)/2][(yf + yi)/2].isFire() != fire_turn && !has_moved) {
			return true;
		} else if (Math.abs(xf - xi) == 1 && Math.abs(yf - yi) == 1 && !selected_piece.hasCaptured()) {
			return true;
		} else {
			return false;
		}
	}

	public void select(int x, int y) {
		if (pieces[x][y] != null) {
			selected_piece = pieces[x][y];
			selected_x = x;
			selected_y = y;
			has_selected = true;
			System.out.println("selected new piece");
		} else {
			selected_piece.move(x, y);
			if (Math.abs(selected_x - x) == 1 && Math.abs(selected_y - y) == 1) {
				has_moved = true;
			}
			selected_x = x;
			selected_y = y;
			if (pieceAt(x, y) != null) {
				selected_piece = pieces[x][y];
			}
			System.out.println("finished moving piece");
		}
	}

	private String pieceImage(Piece p) {
		String img;
		String type;			
		if (p.isFire()) {
			img = "fire";
		} else {
			img = "water";
		}
		if (p.isBomb()) {
			type = "bomb";
		} else if (p.isShield()) {
			type = "shield";
		} else {
			type = "pawn";
		}
		img = "img/" + type + "-" + img + ".png";
		return img;
	}

	public void place(Piece p, int x, int y) {
		if (p != null && x >= 0 && x <= 7 && y >= 0 && y <= 7) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (pieces[i][j] == p) {
						remove(i, j);
					}
				}
			}
			pieces[x][y] = p;
			System.out.println("placed piece");
		}
	}

	public Piece remove(int x, int y) {
		if (x > 7 || x < 0 || y < 0 || y > 7) {
			System.out.println("out of bounds");
		} else if (pieceAt(x, y) == null) {
			System.out.println("no piece there");
		} else {
			Piece p = pieces[x][y];
			pieces[x][y] = null;
			System.out.println("removed");
			return p;
		}
		return null;
	}

	public boolean canEndTurn() {
		return selected_piece != null && (has_moved || selected_piece.hasCaptured());
	}

	public void endTurn() {
		fire_turn = !fire_turn;
		has_selected = false;
		has_moved = false;
		selected_piece.doneCapturing();
		selected_piece = null;
	}

	public String winner() {
		int fire_pieces = 0;
		int water_pieces = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (pieceAt(i, j) != null) {
					if (pieceAt(i, j).isFire()) fire_pieces++;
					else water_pieces++;
				}
			}
		}
		if (fire_pieces <= 0 && water_pieces > 0) {
			return "Water";
		} else if (water_pieces <= 0 && fire_pieces > 0) {
			return "Fire";
		} else if (fire_pieces <= 0 && water_pieces <= 0) {
			return "No one";
		} else {
			return null;
		}
	}
}
