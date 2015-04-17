public class Board {
	public static void main(String[] args) {
		
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board b = new Board(false);
        b.drawBoard(8);

        while(b.winner() == null) {
            if (StdDrawPlus.mousePressed()) {
            	double xxx = StdDrawPlus.mouseX();
            	double yyy = StdDrawPlus.mouseY();
            	int xx = (int) xxx;
            	int yy = (int) yyy;
            	if (b.canSelect(xx, yy)) {
            		b.select(xx, yy);
            	}
            }       
            b.updateGUI();
            StdDrawPlus.show(10);  
            if (StdDrawPlus.isSpacePressed()) {
            	if (b.canEndTurn()) {
            		b.endTurn();
            	}
        	}
        	b.updateGUI();
        	StdDrawPlus.show(10);

        	if (StdDrawPlus.isNPressed()) {
				Board c = new Board(false);
				b = c;
			}
			b.updateGUI();

        }
        	

	}

    private Piece[][] Piecearray = new Piece[8][8];
    private boolean[][] pieces = new boolean[8][8];
    private Piece previousSelect;
    // original cooridnates of the selected moving piece
    private int selectedx;
    private int selectedy;
    private boolean selected = false;
    private boolean moved = false;
    private int side = 0;

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (pieces[i][j]) {
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
                if (Piecearray[i][j] == null) {
                	continue;
                }
                if (Piecearray[i][j].isFire()) {
               		if (Piecearray[i][j].isBomb()) {
               			if (Piecearray[i][j].isKing()) {
               				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
               			} else {
               				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
               			}
               		} else if (Piecearray[i][j].isShield()) {
               			if (Piecearray[i][j].isKing()) {
               				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
               			} else {
               				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
               			}
               		} else {
               			if (Piecearray[i][j].isKing()) {
               				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
               			} else {
               				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
               			}
               		}
               	} else {
               		if (Piecearray[i][j].isBomb()) {
               			if (Piecearray[i][j].isKing()) {
               				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
               			} else {
               				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
               			}
               		} else if (Piecearray[i][j].isShield()) {
               			if (Piecearray[i][j].isKing()) {
               				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
               			} else {
               				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
               			}
               		} else {
                		if (Piecearray[i][j].isKing()) {
                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
               			} else {
               				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
               			}
               		}
               	}
            }
        }
    }


	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					Piecearray[i][j] = null;
					pieces[i][j] = false;
				}
			}
		} else {
			for (int i = 0; i < 8; i++) {
            	for (int j = 0; j < 8; j++) {
					if ((j == 0) && ((i % 2) == 0)) {
            			Piecearray[i][j] = new Piece(true, this, i, j, "pawn");  
            			pieces[i][j] = false;
            		} else if ((j == 1) && (i % 2 != 0)) {
            			Piecearray[i][j] = new Piece(true, this, i, j, "shield");
            			pieces[i][j] = false;
            		} else if ((j == 2) && (i % 2 == 0)) {
            			Piecearray[i][j] = new Piece(true, this, i, j, "bomb");
            			pieces[i][j] = false;
            		} else if ((j == 5) && (i % 2 != 0)) {
            			Piecearray[i][j] = new Piece(false, this, i, j, "bomb");
            			pieces[i][j] = false;
            		} else if ((j == 6) && (i % 2 == 0)) {
            			Piecearray[i][j] = new Piece(false, this, i, j, "shield");
            			pieces[i][j] = false;
            		} else if ((j == 7) && (i % 2 != 0)) {
            			Piecearray[i][j] = new Piece(false, this, i, j, "pawn");
            			pieces[i][j] = false;
            		} else {
            			Piecearray[i][j] = null;
            			pieces[i][j] = false;
            		}
            	}
            }
		}
	}


	private void updateGUI() {
		drawBoard(8);
	}


	private boolean outofbound(int x, int y) {
		if ((x < 0) || (y < 0) || (x > 7) || (y > 7)) {
			return true;
		} else {
			return false;
		}
	}

	private void changeside() {
		if (side == 0) {
			side = 1;
		} else {
			side = 0;
		}
	} 


	public Piece pieceAt(int x, int y) {
		if (outofbound(x, y)) {
			return null;
		} else if (Piecearray[x][y] != null) {
			return Piecearray[x][y];
		} else {
			return null;
		}
	}

	public boolean canSelect(int x, int y) {
		if (pieceAt(x, y) != null) {
			if (side != Piecearray[x][y].side()) {
				return false;
			} else if (!selected) {
				return true;
			} else if (selected && !moved) {
				return true;
			} else {
				return false;
			}
		} else {
			if (selected) {
				if (!moved && validMove(selectedx, selectedy, x, y)) {
					return true;
				} else if (moved && (previousSelect == null)) {
					return false;
				} else if (moved && previousSelect.hasCaptured() && validMove(selectedx, selectedy, x, y)) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (outofbound(xi, yi) || outofbound(xf, yf)) {
			return false;
		} else if (Piecearray[xf][yf] == null) {
			if (!moved) {
				if (Piecearray[xi][yi] == null) {
					return false;
				} else {
					if (Piecearray[xi][yi].isKing()) {
						if (((xi+1 == xf) || (xi-1 == xf)) && ((yi+1 == yf) || (yi-1 == yf))) {
							return true;
						} else if ((xi+2 == xf) && (yi+2 == yf) && (Piecearray[xi+1][yi+1] != null) && 
							(Piecearray[xi+1][yi+1].side() != Piecearray[xi][yi].side())) {
							return true;
						} else if ((xi-2 == xf) && (yi+2 == yf) && (Piecearray[xi-1][yi+1] != null) && 
							(Piecearray[xi-1][yi+1].side() != Piecearray[xi][yi].side())) {
							return true;
						} else if ((xi+2 == xf) && (yi-2 == yf) && (Piecearray[xi+1][yi-1] != null) && 
							(Piecearray[xi+1][yi-1].side() != Piecearray[xi][yi].side())) {
							return true;
						} else if ((xi-2 == xf) && (yi-2 == yf) && (Piecearray[xi-1][yi-1] != null) && 
							(Piecearray[xi-1][yi-1].side() != Piecearray[xi][yi].side())) {
							return true;
						} else {
							return false;
						}
					} else if (Piecearray[xi][yi].isFire()) {
						if (((xi-1 == xf) || (xi+1 == xf)) && (yi+1 == yf)) {
							return true;
						} else if ((xi+2 == xf) && (yi+2 == yf) && (Piecearray[xi+1][yi+1] != null) && 
							(Piecearray[xi+1][yi+1].side() != Piecearray[xi][yi].side())) {
							return true;
						} else if ((xi-2 == xf) && (yi+2 == yf) && (Piecearray[xi-1][yi+1] != null) && 
							(Piecearray[xi-1][yi+1].side() != Piecearray[xi][yi].side())) {
							return true;
						} else {
							return false;
						}
					} else if (!Piecearray[xi][yi].isFire()) {
						if (((xi-1 == xf) || (xi+1 == xf)) && (yi-1 == yf)) {
							return true;
						} else if ((xi+2 == xf) && (yi-2 == yf) && (Piecearray[xi+1][yi-1] != null) && 
							(Piecearray[xi+1][yi-1].side() != Piecearray[xi][yi].side())) {
							return true;
						} else if ((xi-2 == xf) && (yi-2 == yf) && (Piecearray[xi-1][yi-1] != null) && 
							(Piecearray[xi-1][yi-1].side() != Piecearray[xi][yi].side())) {
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				}					
			} else {
				if (Piecearray[xi][yi] == null) {
					return false;
				} else {
					System.out.println("hello!");
					if (((Math.abs(xi-xf)==2) && (Math.abs(yi-yf)==2))) {
						if ((Piecearray[(xi+xf)>>>1][(yi+yf)>>>1] != null) && (Piecearray[(xi+xf)>>>1][(yi+yf)>>>1].side() != Piecearray[xi][yi].side())) {
							if (pieceAt((2*xi-(xi+xf)/2), (2*yi-(yi+yf)/2)) == null) {
								return true;
							} else {
								return false;
							}
						} else {
							return false;
						}
					} else {
						return false;
					}
				}
			}
		} else {
			return false;
		}
	}

	public void select(int x, int y) {
		if (Piecearray[x][y] == null) {
			previousSelect.move(x, y);
			pieces[selectedx][selectedy] = false;
			Piecearray[selectedx][selectedy] = null;
			moved = true;
			if (pieceAt(x, y) != null) {
				pieces[x][y] = true;
			}
			selectedx = x;
			selectedy = y;
		} else {
			pieces[selectedx][selectedy] = false;
			selectedx = x;
			selectedy = y;
			pieces[x][y] = true;
		}
		previousSelect = pieceAt(x, y);	
		selected = true;
	}
	

	public void place(Piece p, int x, int y) {
		String type;
		if ((p != null) || !outofbound(x, y)) {
			Piecearray[x][y] = p;
		}
	}

	public Piece remove(int x, int y) {
		if (outofbound(x, y)) {
			System.out.println("Oops, out of the game boundary!");
			return null;
		} else if (pieceAt(x, y) == null) {
			System.out.println("Oops, no piece at selected place (" + x + ", " + y + "):)");
			return null;
		} else {
			Piece t = Piecearray[x][y];
			Piecearray[x][y] = null;
			return t;
		}
	}

	public boolean canEndTurn() {
		if (moved) {
			return true;
		} else {
			return false;
		}
	}

	public void endTurn() {
		this.selected = false;
		this.moved = false;
		this.changeside();
		this.previousSelect = null;
		this.pieces[selectedx][selectedy] = false;
		if (pieceAt(selectedx, selectedy) != null) {
			Piecearray[selectedx][selectedy].doneCapturing();
		}
	}

 
	public String winner() {
		int f = 0;
		int w = 0;
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (Piecearray[i][j] != null) {
            		if (Piecearray[i][j].isFire()) {
            			f += 1;
            		} else {
            			w += 1;
            		}
            	} else {
            		continue;
            	}
            }
        }
		if ((f > 0) && (w > 0)) {
			return null;
		} else if ((f == 0) && (w > 0)) {
			return "Water";
		} else if ((f > 0) && (w == 0)) {
			return "Fire";
		} else {
			return "No one";
		}
	}

}

