/**
* @author Xiaofei Zhou
*/

public class Board {
	private int size = 8;
	private Piece[][] pieces = new Piece[this.size][this.size];
	private boolean isFireTurn = true;
	private boolean hasSelected = false;
	private int xselected = -1;
	private int yselected = -1;
	private boolean[][] selected = new boolean[this.size][this.size];
	private boolean hasMoved = false;
	private boolean hasCaptured = false;
	private int nFire = 0;
	private int nWater = 0;
	private String winner = null;

	public Board(boolean shouldBeEmpty) {
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				pieces[i][j] = null;
				selected[i][j] = false;
			}
		}
		if (shouldBeEmpty == false) {
			for (int j = 0; j < this.size; j++) {
				for (int i = 0; i < this.size; i ++) {
					if ((i + j) % 2 == 0) {
						/* the switch statement failed to be compiled for the error:
						* constant expression required 
						switch (j) {
							case 0: pieces[i][j] = new Piece(true, this, i, j, "pawn");
							case 1: pieces[i][j] = new Piece(true, this, i, j, "shield");
							case 2: pieces[i][j] = new Piece(true, this, i, j, "bomb");
							case this.size - 3: pieces[i][j] = new Piece(false, this, i, j, "bomb");
							case this.size - 2: pieces[i][j] = new Piece(false, this, i, j, "shield");
							case this.size - 1: pieces[i][j] = new Piece(false, this, i, j, "pawn");
						}
						*/
						if (j == 0) {
							pieces[i][j] = new Piece(true, this, i, j, "pawn");
							this.nFire += 1;
						}
						if (j == 1) {
							pieces[i][j] = new Piece(true, this, i, j, "shield");
							this.nFire += 1;
						}
						if (j == 2) {
							pieces[i][j] = new Piece(true, this, i, j, "bomb");
							this.nFire += 1;
						}
						if (j == this.size - 3) {
							pieces[i][j] = new Piece(false, this, i, j, "bomb");
							this.nWater += 1;
						}
						if (j == this.size - 2) {
							pieces[i][j] = new Piece(false, this, i, j, "shield");
							this.nWater += 1;
						}
						if (j == this.size - 1) {
							pieces[i][j] = new Piece(false, this, i, j, "pawn");
							this.nWater += 1;
						}
					}
				}
			}
		}
	}
	
	private boolean isInBoard(int x, int y) {
		/* return that whether (x, y) is in Board */
		if (x < 0 || x > this.size -1  || y < 0 || y > this.size - 1) {
			return false;
		} else {
			return true;
		}
	}

	public Piece pieceAt(int x, int y) {
		if (this.isInBoard(x, y)) {
			return pieces[x][y];
		} else {
			return null;
		}
		
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (this.isInBoard(xi, yi) == false || this.isInBoard(xf, yf) == false) {
			return false;
		}
		int forward;
		/* define the forward direction for the current player */
		if (this.isFireTurn) {
			forward = 1;
		} else {
			forward = -1;
		}
		if (this.pieceAt(xi, yi) != null && this.pieceAt(xi, yi).isFire() == this.isFireTurn) {
			/* general situation for a piece whenever crowned or not */
			if (xf - xi == 1 && yf - yi == forward * 1 && this.pieceAt(xf, yf) == null) {
				/* the piece moves up right */
				return true;
			} else if (xf - xi == -1 && yf - yi == forward * 1 && this.pieceAt(xf, yf) == null) {
				/* the piece moves up left */
				return true;
			} else if (xf - xi == 2 && yf - yi == forward * 2 && this.pieceAt(xf, yf) == null) {
				/* the piece moves up right and captures another piece */
				if (this.pieceAt(xi + 1, yi + forward * 1) != null && this.pieceAt(xi + 1, yi + forward * 1).isFire() != this.isFireTurn) {
					this.hasCaptured = true;
					this.remove(xi + 1, yi + forward * 1);
					return true;
				}
			} else if (xf - xi == -2 && yf - yi == forward * 2 && this.pieceAt(xf, yf) == null) {
				/* the piece moves up left and captures another piece */
				if (this.pieceAt(xi - 1, yi + forward * 1) != null && this.pieceAt(xi - 1, yi + forward * 1).isFire() != this.isFireTurn) {
					this.hasCaptured = true;
					this.remove(xi - 1, yi + forward * 1);
					return true;
				}
			}
			if (this.pieceAt(xi, yi).isKing()) {
				/* special situation for crowned piece, which can perform a backward movement */
				if (xf - xi == 1 && yf - yi == -forward * 1 && this.pieceAt(xf, yf) == null) {
					/* the crowned piece moves down right */
					return true;
				} else if (xf - xi == -1 && yf - yi == -forward * 1 && this.pieceAt(xf, yf) == null) {
					/* the crowned piece moves down left */
					return true;
				} else if (xf - xi == 2 && yf - yi == -forward * 2 && this.pieceAt(xf, yf) == null) {
					/* the crowned piece moves down right and captures another piece */
					if (this.pieceAt(xi + 1, yi - forward * 1) != null && this.pieceAt(xi + 1, yi - forward * 1).isFire() != this.isFireTurn) {
						this.hasCaptured = true;
						this.remove(xi + 1, yi - forward * 1);
						return true;
					}
				} else if (xf - xi == -2 && yf - yi == -forward * 2 && this.pieceAt(xf, yf) == null) {
					/* ctowned the piece moves down left and captures another piece */
					if (this.pieceAt(xi - 1, yi - forward * 1) != null && this.pieceAt(xi - 1, yi - forward * 1).isFire() != this.isFireTurn) {
						this.hasCaptured = true;
						this.remove(xi - 1, yi - forward * 1);
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean canSelect(int x, int y) {
		if (this.isInBoard(x, y) == false) {
			return false;
		}
		if (this.pieceAt(x, y) != null && this.pieceAt(x, y).isFire() == this.isFireTurn) {
			/* a square with a piece */
			if (this.hasSelected == false) {
				/* The player has not selected a piece yet */
				return true;
			}
			if (this.hasMoved == false) {
				/* The player has selected a piece, but did not move it. */
				return true;
			}
		} else if (this.pieceAt(x, y) == null) {
			/* an empty square */
			if (this.hasSelected == true && this.hasMoved == false && this.validMove(xselected, yselected, x, y)) {
				/* During this turn, the player has selected a Piece which hasn’t moved yet
				*  and is selecting an empty spot which is a valid move for the previously selected Piece. */
				this.pieces[xselected][yselected].move(x, y);
				this.checkBomb(x, y);
				this.hasMoved = true;
				return true;
			}
			if (this.hasSelected == true && this.hasMoved == true && (x - this.xselected == 2 || x - this.xselected == -2) && this.validMove(xselected, yselected, x, y)) {
				/* During this turn, the player has selected a Piece, captured, and has selected another valid capture destination.
				*  When performing multi-captures, you should only select the active piece once
				*  all other selections should be valid destination points. */
				this.pieces[xselected][yselected].move(x, y);
				this.checkBomb(x, y);
				this.hasMoved = true;
				return true;
			}
		}
		return false;
	}

	private void checkBomb(int x, int y) {
		if (this.pieces[x][y].isBomb() && this.hasCaptured) {
			/* launch an explosion if the moved piece which performed a capture is a bomb */
			System.out.println("💣💣💣 Bomb! 💣💣💣");
			for (int i = x - 1; i <= x + 1; i++) {
				for (int j = y - 1; j <= y + 1; j++) {
					if (this.isInBoard(i, j)) {
						if (this.pieces[i][j] != null) {
							/* remove the piece unless the piece is shield */
							if (!this.pieces[i][j].isShield()) {
								this.remove(i, j);
							}
						}
					}
				}
			}
		}
	}

	public void select(int x, int y) {
		if (this.canSelect(x, y)) {
			if (xselected != -1 && yselected != -1) {
				/* if the player have selected some square before
				* calcel the white boc effect */
				selected[xselected][yselected] = false;
			}
			selected[x][y] = true;
			this.hasSelected = true;
			xselected = x;
			yselected = y;
		}
	}

	public void place(Piece p, int x, int y) {
		if (this.isInBoard(x, y)) {
			this.pieces[x][y] = p;
		}
		if (p.isFire()) {
			nFire += 1;
		} else {
			nWater += 1;
		}
	}

	public Piece remove(int x, int y) {
		if (this.isInBoard(x, y) == false) {
			return null;
		}
		Piece p = this.pieces[x][y];
		if (p.isFire()) {
			nFire -= 1;
		} else {
			nWater -= 1;
		}
		pieces[x][y] = null;
		return p;
	}

	public boolean canEndTurn() {
		if (this.hasMoved) {
			return true;
		}
		return false;
	}

	public void endTurn() {
		if (this.canEndTurn()) {
			if (this.isFireTurn) {
				System.out.println("💧💧💧💧💧💧  Now is the water's turn!💧💧💧💧💧💧");
			} else {
				System.out.println("🔥🔥🔥🔥🔥🔥  Now is the fire's turn! 🔥🔥🔥🔥🔥🔥");
			}/*
			System.out.println(this.nFire + " Fire Pieces; " + this.nWater + " Water Pieces");*/
			this.isFireTurn = !this.isFireTurn;
			this.hasSelected = false;
			if (this.xselected != -1 && this.yselected != -1) {
				/* if the player have selected some square before
				* calcel the white boc effect */
				this.selected[xselected][yselected] = false;
			}
			this.xselected = -1;
			this.yselected = -1;
			this.hasMoved = false;
			this.hasCaptured = false;
		}
	}
	/*
	private int checkFirePiece() {
		int nFire = 0;
		for(int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (this.pieceAt(i, j) != null) {
					if (this.pieceAt(i, j).isFire()) {
						nFire += 1;
					}
				}
			}
		}
		return nFire;
	}

	private int checkWaterPiece() {
		int nWater = 0;
		for(int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (this.pieceAt(i, j) != null) {
					if (!this.pieceAt(i, j).isFire()) {
						nWater += 1;
					}
				}
			}
		}
		return nWater;
	}
	*/
	private int checkWinner() {
		if (this.nWater == 0 && this.nFire == 0) {
			this.winner = "No one";
			return 0;
		} else if (this.nWater == 0) {
			this.winner = "Fire";
			return 1;
		} else if (this.nFire == 0) {
			this.winner = "Water";
			return 2;
		} else {
			return -1;
		}
	}

	private void checkStalement() {

	}

	public String winner() {
		return this.winner;
	}

	private void drawBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (selected[i][j]) {
                	/* if this square is selected, it appears as white */
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
                if (this.pieces[i][j] != null) {
                	if (this.pieces[i][j].isFire()) {
                		/* fire piece */
                		if (this.pieces[i][j].isKing()) {
                			/* fire crowned piece */
                			if (this.pieces[i][j].isBomb()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                			} else if (this.pieces[i][j].isShield()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                			} else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                			}
                		} else {
                			/* fire uncrowned piece */
                			if (this.pieces[i][j].isBomb()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                			} else if (this.pieces[i][j].isShield()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                			} else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                			}
                		}
                	} else {
                		/* water piece */
                		if (this.pieces[i][j].isKing()) {
                			/* water crowned piece */
                			if (this.pieces[i][j].isBomb()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                			} else if (this.pieces[i][j].isShield()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                			} else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                			}
                		} else {
                			/* water uncrowned piece */
                			if (this.pieces[i][j].isBomb()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                			} else if (this.pieces[i][j].isShield()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                			} else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                			}
                		}
                		
                	}
                	
                }
            }
        }
    }

    
	public static void main(String[] args) {
		boolean anExperimentalGame = !!true;
		Board b = new Board(!anExperimentalGame);
		if (!anExperimentalGame) {
			Piece p  = new Piece(false, b, 1, 3, "bomb");
			b.place(p, 1, 3);
			Piece q  = new Piece(true, b, 2, 2, "bomb");
			b.place(q, 2, 2);
		}
		
        StdDrawPlus.setXscale(0, b.size);
        StdDrawPlus.setYscale(0, b.size);
        b.drawBoard();
        System.out.println("✨✨✨✨✨✨✨✨✨  Game Start! ✨✨✨✨✨✨✨✨✨");
        System.out.println("🔥🔥🔥🔥🔥🔥 Now is the fire's turn!  🔥🔥🔥🔥🔥🔥");

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(b.checkWinner() == -1) {
            if (StdDrawPlus.mousePressed()) {
                /* after done all the operation, draw the Board again after every nouse click */
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                b.select((int)x, (int)y);
            }
            if (StdDrawPlus.isSpacePressed()) {
            	b.endTurn();
            }
            b.drawBoard();
            StdDrawPlus.show(100);
        }
        System.out.println("✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨");
        System.out.println("Game over! The winner is " + b.winner() + "!");
    }


}