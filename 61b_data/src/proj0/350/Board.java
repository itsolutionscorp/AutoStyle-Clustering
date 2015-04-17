public class Board {

	private boolean[][] pieces;
	private Piece[][] field;
	private boolean[][] highlight;

	// private boolean shouldBeEmpty;

	private boolean player0turn = true;
	private boolean canEndTurn = false;
	private boolean hasSelectedPiece = false;
	private boolean hasMoved = false;
    private Piece selectedPiece = null;
    private boolean canEndGame = false;
    private int[] sCoords;

    private int pieces0;
    private int pieces1;


	public Board(boolean shouldBeEmpty) {
		int N = 8;
        pieces = new boolean[N][N];
        field = new Piece[N][N];
        highlight = new boolean[N][N];
        sCoords = new int[2];
        if (shouldBeEmpty == true) {
        	pieces0 = 0;
        	pieces1 = 0;
        }
        else {
        	pieces0 = 12;
        	pieces1 = 12; 
		//Row 1: Fire pawns
	        field[0][0] = new Piece(true, this, 0, 0, "pawn");
	        pieces[0][0] = true;
	        field[2][0] = new Piece(true, this, 2, 0, "pawn");
	        pieces[2][0] = true;
	        field[4][0] = new Piece(true, this, 4, 0, "pawn");
	        pieces[4][0] = true;
	        field[6][0] = new Piece(true, this, 6, 0, "pawn");
	        pieces[6][0] = true;
		//Row 2: Fire shields
	        field[1][1] = new Piece(true, this, 1, 1, "shield");
	        pieces[1][1] = true;
	        field[3][1] = new Piece(true, this, 3, 1, "shield");
	        pieces[3][1] = true;
	        field[5][1] = new Piece(true, this, 5, 1, "shield");
	        pieces[5][1] = true;
	        field[7][1] = new Piece(true, this, 7, 1, "shield");
	        pieces[7][1] = true;
		//Row 3: Fire bombs
	        field[0][2] = new Piece(true, this, 0, 2, "bomb");
	        pieces[0][2] = true;
	        field[2][2] = new Piece(true, this, 2, 2, "bomb");
	        pieces[2][2] = true;
	        field[4][2] = new Piece(true, this, 4, 2, "bomb");
	        pieces[4][2] = true;
	        field[6][2] = new Piece(true, this, 6, 2, "bomb");
	        pieces[6][2] = true;

		//Row 8: Water pawns
	        field[1][7] = new Piece(false, this, 1, 7, "pawn");
	        pieces[1][7] = true;
	        field[3][7] = new Piece(false, this, 3, 7, "pawn");
	        pieces[3][7] = true;
	        field[5][7] = new Piece(false, this, 5, 7, "pawn");
	        pieces[5][7] = true;
	        field[7][7] = new Piece(false, this, 7, 7, "pawn");
	        pieces[7][7] = true;
		//Row 7: Water shields
	        field[0][6] = new Piece(false, this, 0, 6, "shield");
	        pieces[0][6] = true;
	        field[2][6] = new Piece(false, this, 2, 6, "shield");
	        pieces[2][6] = true;
	        field[4][6] = new Piece(false, this, 4, 6, "shield");
	        pieces[4][6] = true;
	        field[6][6] = new Piece(false, this, 6, 6, "shield");
	        pieces[6][6] = true;
		//Row 6: Water bombs
	        field[1][5] = new Piece(false, this, 1, 5, "bomb");
	        pieces[1][5] = true;
	        field[3][5] = new Piece(false, this, 3, 5, "bomb");
	        pieces[3][5] = true;
	        field[5][5] = new Piece(false, this, 5, 5, "bomb");
	        pieces[5][5] = true;
	        field[7][5] = new Piece(false, this, 7, 5, "bomb");
	        pieces[7][5] = true;   
        }
	}

    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);



        //Variables at the game start.
        b.player0turn = true;
        b.hasSelectedPiece = false;
        b.hasMoved = false;
        b.canEndGame = false;

        while(true) {

            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int xpos = (int) x;
                int ypos = (int) y;

                if (b.canSelect(xpos, ypos)) {
					b.select(xpos, ypos);
				}
			}

            if (StdDrawPlus.isSpacePressed()) {
				if (b.canEndTurn() == true) {
					System.out.println("Ending turn.");
					b.endTurn();
				}
				else {
					System.out.println("You must make a move first.");
				}
            }

            if (((b.pieces0 == 0) || (b.pieces1 == 0)) && b.canEndGame) {
            	System.out.println(b.winner());
            	return;
            }

            b.drawBoard(N);
            StdDrawPlus.show(100);
        }
    }

    public Piece pieceAt(int x, int y) {
		if ((x < 0) || (x > 7) || (y < 0) || (y > 7)) {
			return null;
		}
		else if (pieces[x][y] == true) {
			return field[x][y];
		}
		return null;
	}

	public boolean canSelect(int x, int y) {
		if ((pieces[x][y] == true) && (hasMoved == false)) {
			Piece temp = pieceAt(x, y);
			if (player0turn == true) {
				if (temp.isFire() == true) {
					return true;
				}
				else {
					return false;
				}
			}
			else if (player0turn == false) {
				if (temp.isFire() == false) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}

		else if ((hasMoved == true) && (selectedPiece.hasCaptured() == true) && (canCapture(x, y))) {
			return true;
		}

	    else if ((hasSelectedPiece == true) && (hasMoved == false) && (validMove(x, y))){
    		return true;
    	}
    	return false;
	}

	public void select(int x, int y) {
		if (pieces[x][y] == true) {
			if ((hasSelectedPiece == true) && (hasMoved == false)) {
				unhighlighter(sCoords[0], sCoords[1]);
			}
			highlighter(x,y);
			selectedPiece = pieceAt(x, y);
			sCoords[0] = x;
			sCoords[1] = y;
			hasSelectedPiece = true;
		}
		if ((pieces[x][y] == false) && (hasSelectedPiece)) {
			unhighlighter(sCoords[0], sCoords[1]);
			selectedPiece.move(x,y);
			sCoords[0] = x;
			sCoords[1] = y;
			hasMoved = true; //critical for game ending mechanic
		}
	}

	public void place(Piece p, int x, int y) {
		pieces[x][y] = true;
		field[x][y] = p;
		if (p.isFire() == true) {
				pieces0 = pieces0 + 1;
		}
		else {
			pieces1 = pieces1 + 1;
		}
	}

	public Piece remove(int x, int y) {
		if ((x < 0) || (x > 7) || (y < 0) || (y > 7)) {
			System.out.println("Input for removal is out of bounds.");
			return null;
		}
		else if (pieces[x][y] == false) {
			System.out.println("No piece at input location.");
			return null;
		}
		else {
			Piece temp = pieceAt(x, y);
			if (temp.isFire() == true) {pieces0 = pieces0 - 1;}
			else {pieces1 = pieces1 - 1;}
			pieces[x][y] = false;
			field[x][y] = null;
			return temp;
		}
	}

	public boolean canEndTurn() {
		canEndGame = true;
		if (hasMoved == true) {
			canEndTurn = true;
		}
		else {
			canEndTurn = false;
		}
		return canEndTurn;
	}

	public void endTurn() {
		hasSelectedPiece = false;
        hasMoved = false;
        canEndTurn = false;
        selectedPiece.doneCapturing();
        player0turn = !player0turn;
	}

	public String winner() {
		if (pieces0 == 0 && pieces1 == 0) {
			return "No one";
		} else if (pieces0 == 0) {
			return "Water";
		} else if (pieces1 == 0) {
			return "Fire";
		} else {
			return null;
		}
	}


/**PRIVATE METHODS */

	private void highlighter(int x, int y) {
        highlight[x][y] = true;
    }
    private void unhighlighter(int x, int y) {
    	highlight[x][y] = false;
    }

	private boolean validMove(int x, int y) {
		Piece p = selectedPiece;
		if ((p.isFire() == true) || (p.isKing() == true)) {
			if (((x == sCoords[0]-1) || (x == sCoords[0]+1)) && (y == sCoords[1]+1)) {
				if (pieces[x][y] == false) {return true;}
				else {return false;}
			}
			else if (canCapture(x, y)) {
				return true;}
		}
		if ((p.isFire() == false) || (p.isKing() == true)) {
			if (((x == sCoords[0]-1) || (x == sCoords[0]+1)) && (y == sCoords[1]-1)) {
				if (pieces[x][y] == false) {return true;}
				else {return false;}
			}
			else if (canCapture(x, y)) {
				return true;}
		}
		return false;
	}

	private boolean canCapture(int x, int y) {
		if ((Math.abs(sCoords[0] - x) == 2) && (Math.abs(sCoords[1] - y) == 2)) {
			Piece p = selectedPiece;
			Piece temp;
			if (x > sCoords[0]) {
				if (y > sCoords[1]) {
					temp = pieceAt(sCoords[0]+1, sCoords[1]+1);
				}
				else {
					temp = pieceAt(sCoords[0]+1, sCoords[1]-1);
				}
			}
			else {
				if (y > sCoords[1]) {
					temp = pieceAt(sCoords[0]-1, sCoords[1]+1);
				}
				else {
					temp = pieceAt(sCoords[0]-1, sCoords[1]-1);
				}
			}
			if (temp != null) {
				if (p.isFire() == true) {
					if (((y>sCoords[1]) || (p.isKing() == true)) && (temp.isFire() == false)) {
						return true;
					}
					else {
						return false;
					}
				}
				else if (p.isFire() == false) {
					if (((y<sCoords[1]) || (p.isKing() == true)) && (temp.isFire() == true)) {
						return true;
					}
					else {
						return false;
					}
				}
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
		return false;
	}

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else {
                	StdDrawPlus.setPenColor(StdDrawPlus.BOOK_BLUE);
                }
                // Highlighting when selected
                if (highlight[i][j] == true) {
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                
                if (pieces[i][j]) {
                	Piece currentPiece = field[i][j];
                	if (currentPiece.isFire() == true) {
                		if (currentPiece.isKing() == true) {
                			if (currentPiece.isShield() == true) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
	                		}
	                		else if (currentPiece.isBomb() == true) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
	                		}
	                		else {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
	                		}
	                	}
	                	else {
	                		if (currentPiece.isShield() == true) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
	                		}
	                		else if (currentPiece.isBomb() == true) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
	                		}
	                		else {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
	                		}
	                	}
	                }
                	else {
                		if (currentPiece.isKing() == true) {
                			if (currentPiece.isShield() == true) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
	                		}
	                		else if (currentPiece.isBomb() == true) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
	                		}
	                		else {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
	                		}
	                	}
	                	else {
	                		if (currentPiece.isShield() == true) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
	                		}
	                		else if (currentPiece.isBomb() == true) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
	                		}
	                		else {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
	                		}
	                	}
                	}
                }
            }
        }
    }
}

