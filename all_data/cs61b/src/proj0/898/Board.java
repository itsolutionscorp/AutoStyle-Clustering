
public class Board {

	private Piece[][] squares;
	private Piece selected;
	private int player, selectedX, selectedY;
	private boolean hasMoved;

	public static void main(String[] args) {
		Board game = new Board(false);
		StdDrawPlus.setScale(0, 8);
        while(true) {
            game.drawBoard();
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (game.canSelect(x, y)) {
                	game.select(x, y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
            	if (game.canEndTurn()) {
            		game.endTurn();
            	}
            }
            StdDrawPlus.show(100);
        }
	}

	private void drawBoard() {
		Piece piece;
		String type, side, king;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (i == selectedX && j == selectedY) {
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
                piece = this.pieceAt(i, j);
                if (piece != null) {
					type = piece.isShield() ? "shield" : piece.isBomb() ? "bomb" : "pawn";
                	side = piece.isFire() ? "-fire" : "-water";
                	king = piece.isKing() ? "-crowned" : "";
                	StdDrawPlus.picture(i + .5, j + .5, "img/" + type + side + king + ".png", 1, 1);
                }
            }
        }
    }

	public Board(boolean shouldBeEmpty) {
		this.squares = new Piece[8][8];
		this.selected = null;
		this.selectedX = -1;
		this.selectedY = -1;
		this.player = 0;
		this.hasMoved = false;
		if (!shouldBeEmpty) {
			for (int i = 0; i < 8; i += 2) {
				this.squares[i][0] = new Piece(true, this, i, 0, "pawn");
			}
			for (int i = 1; i < 8; i += 2) {
				this.squares[i][1] = new Piece(true, this, i, 1, "shield");
			}
			for (int i = 0; i < 8; i += 2) {
				this.squares[i][2] = new Piece(true, this, i, 2, "bomb");
			}
			for (int i = 1; i < 8; i += 2) {
				this.squares[i][5] = new Piece(false, this, i, 5, "bomb");
			}
			for (int i = 0; i < 8; i += 2) {
				this.squares[i][6] = new Piece(false, this, i, 6, "shield");
			}
			for (int i = 1; i < 8; i += 2) {
				this.squares[i][7] = new Piece(false, this, i, 7, "pawn");
			}
		}
	}

	public Piece pieceAt(int x, int y) {
		if (this.out(x, y)) {
			return null;
		}
		return this.squares[x][y];
	}

	public boolean canSelect(int x, int y) {
		if (this.out(x, y)) {
			return false;
		}
		Piece dstPiece = this.pieceAt(x, y);
		if (this.selected == null) {
			if (this.hasMoved) {
				return false;
			}
			if (dstPiece != null && dstPiece.side() == this.player) {
				return true;
			}
			return false;
		} 
		if (this.hasMoved) {
			if (this.selected.hasCaptured()) {
				return validMoveCapture(x - selectedX, y - selectedY);
			}
			return false;
		} 
		if (this.sameSide(dstPiece, this.selected)) {
			return true;
		} 
		int dx = x - selectedX, dy = y - selectedY;
		return validMoveNonCapture(dx, dy) || validMoveCapture(dx, dy);
	}

	private boolean validMoveNonCapture(int dx, int dy) {
		if ((dx == 1 || dx == -1) && (dy == 1 || dy == -1)) {
			if (notKingBackward(dy)){
				return false;
			}
			if (this.pieceAt(selectedX + dx, selectedY + dy) == null) {
				return true;
			}
			return false;
		}
		return false;
	}

	private boolean validMoveCapture(int dx, int dy) {
		if ((dx == 2 || dx == -2) && (dy == 2 || dy == -2)) {
			if (notKingBackward(dy)){
				return false;
			}
			if (this.pieceAt(selectedX + dx, selectedY + dy) != null) {
				return false;
			}
			Piece p = this.pieceAt(selectedX + dx / 2, selectedY + dy / 2);
			if (p == null) {
				return false;
			}
			if (this.sameSide(this.selected, p)) {
				return false;
			}
			return true;
		}
		return false;
	}

	private boolean notKingBackward(int dy) {
		Piece p = this.selected;
		return (p.side() == 0 && dy < 0 || p.side() == 1 && dy > 0) && !p.isKing();
	}

	public void select(int x, int y) {
		if (this.selected == null) {
			this.setSelected(x, y);
		} else {
			if (this.pieceAt(x, y) != null) {
				this.setSelected(x, y);
			} else {
				this.selected.move(x, y);
				if (this.pieceAt(x, y) == null) {
					this.selected = null;
					this.selectedX = -1;
					this.selectedY = -1;
				} else {
					this.selectedX = x;
					this.selectedY = y;
				}
				this.hasMoved = true;
			}
		}
	}

	public void place(Piece p, int x, int y) {
		if (p != null && !this.out(x, y)) {
			this.squares[x][y] = p;
		}
	}

	public Piece remove(int x, int y) {
		if (this.out(x, y)) {
			System.out.println("Out of bounds");
			return null;
		}
		if (this.pieceAt(x, y) == null) {
			System.out.println("No piece to remove");
			return null;
		}
		Piece removed = this.squares[x][y];
		this.squares[x][y] = null;
		return removed;
	}

	public boolean canEndTurn() {
		return this.hasMoved;
	}

	public void endTurn() {
		this.player = 1 - this.player;
		this.hasMoved = false;
		if (this.selected != null) {
			this.selected.doneCapturing();
		}
		this.selected = null;
		this.selectedX = -1;
		this.selectedY = -1;
	}

	public String winner() {
		int fires = 0, waters = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece p = this.squares[i][j];
				if (p != null) {
					if (p.isFire()) {
						fires++;
					} else {
						waters++;
					}
				}
			}
		}
		if (fires != 0 && waters != 0) {
			return null;
		}
		return fires == 0 ? (waters == 0 ? "No one" : "Water") : "Fire";
	}

	private boolean out(int x, int y) {
		return x < 0 || x > 7 || y < 0 || y > 7;
	}

	private boolean sameSide(Piece a, Piece b) {
		if (a == null || b == null) {
			return false;
		}
		return a.side() == b.side();
	}

	private void setSelected(int x, int y) {
		this.selected = this.pieceAt(x, y);
		this.selectedX = x;
		this.selectedY = y;
	}

}