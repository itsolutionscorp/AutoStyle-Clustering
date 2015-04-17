public  class Board {

	private Piece[][] pieces = new Piece[8][8];
	private int turn = 0;
	private boolean hasMoved = false;
	private boolean pieceSelected = false;
	private Piece selectedPiece;
	private int selectedX = 10;
	private int selectedY = 10;
	private String winningTeam;
	private int mouseX;
	private int mouseY;
	private boolean hasCaptured = false;

	public static void main(String[] args) {

		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		Board game = new Board(false);

		while(true) {
			game.render();
			StdDrawPlus.show(15);
			if (game.isGameOver()) {
				System.out.println(game.winner());
				return;
			}
		}
	}

	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty == false) {
		pieces[0][0] = new Piece(true, this, 0, 0, "pawn");
		pieces[2][0] = new Piece(true, this, 2, 0, "pawn");
		pieces[4][0] = new Piece(true, this, 4, 0, "pawn");
		pieces[6][0] = new Piece(true, this, 6, 0, "pawn");
		pieces[1][1] = new Piece(true, this, 1, 1, "shield");
		pieces[3][1] = new Piece(true, this, 3, 1, "shield");
		pieces[5][1] = new Piece(true, this, 5, 1, "shield");
		pieces[7][1] = new Piece(true, this, 7, 1, "shield");
		pieces[0][2] = new Piece(true, this, 0, 2, "bomb");
		pieces[2][2] = new Piece(true, this, 2, 2, "bomb");
		pieces[4][2] = new Piece(true, this, 4, 2, "bomb");
		pieces[6][2] = new Piece(true, this, 6, 2, "bomb");
		pieces[1][7] = new Piece(false, this, 1, 7, "pawn");
		pieces[3][7] = new Piece(false, this, 3, 7, "pawn");
		pieces[5][7] = new Piece(false, this, 5, 7, "pawn");
		pieces[7][7] = new Piece(false, this, 7, 7, "pawn");
		pieces[0][6] = new Piece(false, this, 0, 6, "shield");
		pieces[2][6] = new Piece(false, this, 2, 6, "shield");
		pieces[4][6] = new Piece(false, this, 4, 6, "shield");
		pieces[6][6] = new Piece(false, this, 6, 6, "shield");
		pieces[1][5] = new Piece(false, this, 1, 5, "bomb");
		pieces[3][5] = new Piece(false, this, 3, 5, "bomb");
		pieces[5][5] = new Piece(false, this, 5, 5, "bomb");
		pieces[7][5] = new Piece(false, this, 7, 5, "bomb");
		}
	}

	private void render() {
		if (StdDrawPlus.isNPressed()) {
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
			System.out.println(pieces[i][j]);
		}
		}
		}

		if (StdDrawPlus.mousePressed()) {
            mouseX = ((int) StdDrawPlus.mouseX());
            mouseY = ((int) StdDrawPlus.mouseY());
            if (canSelect(mouseX, mouseY)) {
         	   select(mouseX, mouseY);
         	   StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			   StdDrawPlus.filledSquare(mouseX+.5, mouseY+.5, .5);
        	}
        }
        if (StdDrawPlus.isSpacePressed()) {
        	if (canEndTurn()) {
        		endTurn();
        	}
        }

		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
                } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.MAGENTA);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (i==selectedX && j == selectedY) {
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    StdDrawPlus.filledSquare(i+.5, j+.5, .5);

                }
            	

                

        if (/**shouldBeEmpty == false*/ 5==5) { /*put in wrong place*/
        	Piece p = pieceAt(i, j);
        	if (p != null) {
        		if (p.isFire()) {
        			if (p.isShield()) {
        				if (p.isKing()) {
        					StdDrawPlus.picture(i+.5, j+.5, "img/shield-fire-crowned.png", 1, 1);
        				} else {
        					StdDrawPlus.picture(i+.5, j+.5, "img/shield-fire.png", 1, 1);
        				}
        			} else if (p.isBomb()) {
        				if (p.isKing()) {
        					StdDrawPlus.picture(i+.5, j+.5, "img/bomb-fire-crowned.png", 1, 1);
        				} else {
        					StdDrawPlus.picture(i+.5, j+.5, "img/bomb-fire.png", 1, 1);
        				}
        			} else {
        				if (p.isKing()) {
        					StdDrawPlus.picture(i+.5, j+.5, "img/pawn-fire-crowned.png", 1, 1);
        				} else {
        					StdDrawPlus.picture(i+.5, j+.5, "img/pawn-fire.png", 1, 1);
        				}
        			}
        		} else {
        			if (p.isShield()) {
        				if (p.isKing()) {
        					StdDrawPlus.picture(i+.5, j+.5, "img/shield-water-crowned.png", 1, 1);
        				} else {
        					StdDrawPlus.picture(i+.5, j+.5, "img/shield-water.png", 1, 1);
        				}
        			} else if (p.isBomb()) {
        				if (p.isKing()) {
        					StdDrawPlus.picture(i+.5, j+.5, "img/bomb-water-crowned.png", 1, 1);
        				} else {
        					StdDrawPlus.picture(i+.5, j+.5, "img/bomb-water.png", 1, 1);
        				}
        			} else {
        				if (p.isKing()) {
        					StdDrawPlus.picture(i+.5, j+.5, "img/pawn-water-crowned.png", 1, 1);
        				} else {
        					StdDrawPlus.picture(i+.5, j+.5, "img/pawn-water.png", 1, 1);
        				}
        			}	
        		}

       		}

        }
        	}
		}
	}

	public Piece pieceAt(int x, int y) {
		if ((x>7) || (y>7) || (x<0) || (y<0)) {
			return null;
		}
		return pieces[x][y];
	}

	public boolean canSelect(int x, int y) {	
		Piece p = pieceAt(x, y);
		if (p != null) {
			if ((p.side() == turn) && (hasMoved == false)) { /**select a piece initially */
				return true;
			}
		} else if ((p == null) && (pieceSelected == true) && hasMoved == false && hasCaptured == false) {/** select piece that hasn't moved yet*/
			if (validMove(selectedX, selectedY, x, y)) { /**piece can move there*/
				if (!selectedPiece.hasCaptured()) { /** good to go if the piece hasn't captured yet*/
					return true;
				} /**else if (selectedPiece.hasCaptured() && ((x-selectedX==2) || (x-selectedX ==-2))) { /**double capturing. REDUNDANT, see next block??*/
				/*	if (selectedPiece.isFire()) { /**team fire*/
				/*		if (y-selectedY == 2 || (selectedPiece.isKing() && selectedY-y==-2)) {/**correct y-values, backwards ok if king*/
				/*			return true;
				/*		}
				/*	} else { /**team water*/
				/*		if (y-selectedY == -2 || (selectedPiece.isKing() && y-selectedY==2)) { /**correct y-values, forwards ok if king*/
				/*			return true;
						}

					}
				}*/
			}
		} else if ((p == null) && (pieceSelected == true) && hasMoved==true && hasCaptured == true) { /** has already captured*/
			if (validMove(selectedX, selectedY, x, y)) {
				/**if (!selectedPiece.hasCaptured()) { /**piece hasn't captured yet, invalid??*/
				/*	return true;*/
				/*} else*/ if (selectedPiece.hasCaptured() && ((x-selectedX==2) || (x-selectedX ==-2))) { /**double capture*/
					if (selectedPiece.isFire()) {/**fire team*/
						if (y-selectedY == 2 || (selectedPiece.isKing() && selectedY-y==2)) { /**correct y-values, backwards ok if king*/
							return true;
						}
					} else { /*water team*/
						if (y-selectedY == -2 || (selectedPiece.isKing() && y-selectedY==2)) { /**correct y-values, forwards ok if king*/
							return true;
						}

					}
				}
			}
		}
		return false;

	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
			if (pieceSelected && selectedPiece==null) {
				System.out.println("pieceselected true, selectedpiece null");
			}
			if (!pieceSelected && selectedPiece!=null) {
				System.out.println("pieceselected false, selectedpiece not null");
			}
			if (pieceSelected && selectedPiece!=null && (selectedX==10 || selectedY==10)) {
				System.out.println("piece selected good, but selectedx: " + selectedX + " salectedy: " + selectedY);
			}		
		Piece p = pieceAt(xi, yi);
		if (pieceAt(xf, yf) != null) { /**cant move to occupied space*/
			return false;
		} else if (p == null) { /**cant move null piece*/
			return false;
		} else if (p.isFire()) { /**fire team*/
			if ((xf-xi==1 || xi-xf==1) && ((yf-yi==1) || (p.isKing() && yf-yi==-1))) { /**standard valid move*/
				return true;
			} else if ((xf-xi == 2 || xi-xf==2) && ((yf-yi == 2 || (p.isKing() && yf-yi==-2)))) { /**standard capture*/
				if (pieceAt((int) ((xf+xi)/2), (int) ((yf+yi)/2)) == null) { /**cant jump without capturing*/
					return false;
				} else if (pieceAt((int) ((xf+xi)/2), (int) ((yf+yi)/2)).side() != selectedPiece.side()) { /**no friendly fire capturing*/
					return true;
				}
			}
		} else if (!p.isFire()) { /**water team*/
			if ((xf-xi==1 || xi-xf==1) && ((yf-yi==-1) || (p.isKing() && yf-yi==1))) { /**standard valid move*/
				return true;
			} else if ((xf-xi == 2 || xi-xf==2) && ((yf-yi == -2 || (p.isKing() && yf-yi==2)))) { /**standard capture*/
				if (pieceAt((int) ((xf+xi)/2), (int) ((yf+yi)/2)) == null) { /**cant jump without capturing*/
					return false;
				} else if (pieceAt((int) ((xf+xi)/2), (int) ((yf+yi)/2)).side() != p.side()) { /**no friendly fire capturing*/
					return true;
				}
			}
		}
		return false;
	}

	public void select(int x, int y) {
			if (pieceSelected && selectedPiece==null) {
				System.out.println("pieceselected true, selectedpiece null");
			}
			if (!pieceSelected && selectedPiece!=null) {
				System.out.println("pieceselected false, selectedpiece not null");
			}
			if (pieceSelected && selectedPiece!=null && (selectedX==10 || selectedY==10)) {
				System.out.println("piece selected good, but selectedx: " + selectedX + " salectedy: " + selectedY);
			}		
		if (pieceAt(x, y) != null) {
			selectedPiece = pieceAt(x, y);
			selectedX = x;
			selectedY = y;
			pieceSelected = true;
		} else {
			if (pieceSelected) {
				selectedPiece.move(x, y);
				place(selectedPiece, x, y);
				pieces[selectedX][selectedY] = null;
				if (selectedX-x==2 || selectedX-x==-2) {
					hasCaptured = true;
				}
				selectedX = x;
				selectedY = y;
				hasMoved = true;
				}
			}
		
	}

	public void place(Piece p, int x, int y) {
		if ((x>7) || (y>7) || (p == null)) {
			/*intentionally blank*/
		} else {
			pieces[x][y] = p;
			remove(selectedX, selectedY);
			if (p.isBomb() && (x-selectedX==2 || x-selectedX==-2)) {
						for (int i=x-1; i<x+2; i++) {
							for (int j=y-1; j<y+2; j++) {
								if (pieceAt(i, j) != null) {
									if (!pieceAt(i, j).isShield()) {
										remove(i, j);
									}
								}
							}
							
						}
					}
		}

	}

	 public Piece remove(int x, int y) {
	 	if ((x>7) || (y>7)) {
	 		System.out.println("Cannot remove piece from space off board");
	 		return null;
	 	} else if (pieceAt(x, y) == null) {
	 		System.out.println("Cannot remove non-existant piece");
	 		return null;
	 	} else {
	 		Piece temp = pieceAt(x, y);
	 		pieces[x][y] = null;
	 		return temp;
	 	}


	 }

	 public boolean canEndTurn() {
	 	return hasMoved;
	 }

	 public void endTurn() {
	 	turn = 1 - turn;
	 	pieceSelected = false;
	 	hasMoved = false;
	 	hasCaptured = false;
	 	selectedPiece.doneCapturing();
	 	selectedPiece=null;
	 	selectedX = 10;
	 	selectedY = 10;
	 }

	 private boolean isGameOver() {
	 	int firePlayers = 0;
	 	int waterPlayers = 0;
	 	for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (pieceAt(i, j) != null) {
			 		if (pieceAt(i, j).isFire()) {
			 			firePlayers += 1;
			 		} else {
			 			waterPlayers += 1;
			 		}
			 	}
			}
		}
	
	 	if (firePlayers == 0 && waterPlayers == 0) {
	 		winningTeam = "No one";
	 		return true;
	 	} else if (waterPlayers == 0) {
	 		winningTeam = "Fire";
	 		return true;
	 	} else if (firePlayers==0) {
	 		winningTeam = "Water";
	 		return true;
	 	} else {
	 		return false;
	 	}
	 }

	 public String winner() {
	 	if (isGameOver()) {
	 		System.out.println(winningTeam);
	 		return winningTeam;
	 	} else {
	 		return null;
	 	}
	 }
}