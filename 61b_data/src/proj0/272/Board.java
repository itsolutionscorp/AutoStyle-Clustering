public class Board {

	private Piece[][] pieces;
	private boolean empty;
	private boolean pieceMoved;
	private boolean firesTurn;
	private Piece pieceSelected;
	private int selectedX;
	private int selectedY;


	public static void main(String[] args) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		Board b = new Board(false);
		b.drawBoard();

        while(true) {
        	if (b.winner() != null) {
            	System.out.println(b.winner());
            	break;
            }
          	if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)) {
                	b.select((int) x, (int) y);  
                } 
            } 

        	if (StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
        		b.endTurn();
        	}
        	b.drawBoard();
        	
            StdDrawPlus.show(100);
        }

	}

	private void drawBoard() {
        for (int row = 0; row < 8; row += 1) {
            for (int col = 0; col < 8; col += 1) {
                if ((row + col) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(row + .5, col + .5, .5);  
            }
        }

        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        if (selectedX >= 0 && selectedX < 8 && selectedY >= 0 && selectedY < 8) {
            StdDrawPlus.filledSquare(selectedX + .5, selectedY + .5, .5);
        }

        drawPieces();
                    
    }

	public Board(boolean shouldBeEmpty) {
		empty = shouldBeEmpty;
		pieces = new Piece[8][8];
        firesTurn = true;
        pieceMoved = false;
        pieceSelected = null;
        selectedX = -1;
        selectedY = -1;

        if (!empty) {
        	for (int x = 0; x < 8; x += 2) {
        		pieces[x][0] = new Piece(true, this, x, 0, "pawn");
        		pieces[x][2] = new Piece(true, this, x, 2, "bomb");
        		pieces[x][6] = new Piece(false, this, x, 6, "shield");
        	}

        	for (int x = 1; x < 8; x += 2) {
        		pieces[x][1] = new Piece(true, this, x, 1, "shield");
        		pieces[x][5] = new Piece(false, this, x, 5, "bomb");
        		pieces[x][7] = new Piece(false, this, x, 7, "pawn");
        	}
        }
	}

	public Piece pieceAt(int x, int y) {
		if (x < 0 || x >= 8 || y < 0 || y >= 8) {
			return null;
		}

		if (pieces[x][y] != null) {
			return pieces[x][y];
		}

		return null;
	}

	public boolean canSelect(int x, int y) {

		Piece selection = pieceAt(x,y);

		if (selection != null && !pieceMoved) {
			if (firesTurn && selection.isFire()) {
				return true;
			} else if (!firesTurn && !selection.isFire()) {
				return true;
			} else {
				return false;
			}
		} else if (selection == null && pieceSelected != null) {
			if (validMove(selectedX, selectedY, x, y)) {
				return true;	
			} else {
				return false;
			}	
		} else {
			return false;
		}	
	}

	private boolean oppositePieces(Piece p, Piece other) {
		if (p == null || other == null) {
			return false;
		}

		if ((p.isFire() && !other.isFire()) || (!p.isFire() && other.isFire())) {
			return true;
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		
		if (xf < 0 || xf >= 8 || yf < 0 || yf >= 8) {
			return false;
		}

		if (pieceAt(xf,yf) != null) {
			return false;
		}

		Piece p = pieceAt(xi, yi);
		if (p == null) {
			return false;
		}


		if (pieceMoved && !p.hasCaptured()) {
			return false;
		}

		if (p.isKing()) {
			if (!p.hasCaptured() && (xf == xi - 1 || xf == xi + 1) && (yf == yi + 1 || yf == yi - 1)) {
				return true;
			} 
			if (xf == xi - 2  && yf == yi + 2  && oppositePieces(p, pieceAt(xf+1,yf-1))) { //upper left
				return true;
			} else if (xf == xi + 2 && yf == yi + 2 && oppositePieces(p, pieceAt(xf-1, yf-1))) { //upper right
				return true;
			} else if (xf == xi + 2 &&  yf == yi - 2 && oppositePieces(p, pieceAt(xf-1, yf+1))) { //lower right
				return true;
			} else if (xf == xi - 2  &&  yf == yi - 2 && oppositePieces(p, pieceAt(xf+1, yf+1))) { //lower left
				return true;
			} else {
				return false;
			}
		} else {
			if (p.isFire()) {
				if (!p.hasCaptured() && (xf == xi - 1 || xf == xi + 1) && yf == yi + 1) {
					return true;
				} else if (xf == xi - 2  && yf == yi + 2  && oppositePieces(p, pieceAt(xf+1,yf-1))) { //upper left
					return true;
				} else if (xf == xi + 2 && yf == yi + 2 && oppositePieces(p, pieceAt(xf-1, yf-1))) { //upper right
					return true;
				} else {
					return false;
				}
			} else {
				if (!p.hasCaptured() && (xf == xi - 1 || xf == xi + 1) && (yf == yi - 1)) {
					return true;
				} else if (xf == xi + 2 &&  yf == yi - 2 && oppositePieces(p, pieceAt(xf-1, yf+1))) { //lower right
					return true;
				} else if (xf == xi - 2  &&  yf == yi - 2 && oppositePieces(p, pieceAt(xf+1, yf+1))) { //lower left
					return true;
				} else {
					return false;
				}			
			}
		}
	}

	public void select(int x, int y) {
		
		Piece selected = pieceAt(x,y);
		if (selected == null) {
			pieceSelected.move(x,y);

			if (pieceSelected.hasCaptured()) {
				if (selectedX+2 == x && selectedY+2==y) { //upperRight
					remove(selectedX+1, selectedY+1);
				} else if (selectedX-2 == x && selectedY+2==y) { //upperLeft
					remove(selectedX-1, selectedY+1);
				} else if (selectedX-2 == x && selectedY-2==y) {  //lowerLeft
					remove(selectedX-1, selectedY-1);
				} else { //lowerRight
					remove(selectedX+1, selectedY-1);
				}
			}
			

			pieceMoved = true;
			selectedX = x;
			selectedY = y;

			if (pieceSelected.isBomb() && pieceSelected.hasCaptured()) {
				remove(x,y);
				selectedX = -1;
				selectedY = -1;
				for(int i = x-1; i <= x+1; i += 1) {
					for (int j = y-1; j <= y+1; j += 1) {
						if (pieceAt(i,j) != null) {
							if (!pieceAt(i,j).isShield() && (x >= 0 &&  x < 8 && y >= 0 && y < 8)){
								remove(i,j);
							}
						}
					}
				}
			} 

		} else {
			pieceSelected = selected;
			selectedX = x;
			selectedY = y;
		}
	}


	public void place(Piece p, int x, int y) {
		if (x < 0 || x >= 8 || y < 0 || y >= 8) {
			return;
		} else {
			pieces[x][y] = p;
		}
	}

	public Piece remove(int x, int y) {

		if (x < 0 || x >= 8 || y < 0 || y >= 8) {
			System.out.println("Out of bounds: No piece removed.");
			return null;
		}

		Piece p = pieces[x][y];

		if (p == null) {
			System.out.println("No piece to be removed");
			return null;
		}


		pieces[x][y] = null;
		return p;
	}

	public boolean canEndTurn() {
		if (pieceSelected != null) {
			if (pieceMoved || pieceSelected.hasCaptured()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public void endTurn() {
		pieceSelected.doneCapturing();
		firesTurn = !firesTurn;
		pieceSelected = null;
		selectedX = -1;
		selectedY = -1;
		pieceMoved = false;
	}

	public String winner() {
		int firePieces = 0;
		int waterPieces = 0;
		for (int x = 0; x < 8; x += 1) {
            for (int y = 0; y < 8; y += 1) {
            	Piece p = pieceAt(x,y);
            	if (p != null) {
            		if (p.isFire()) {
            			firePieces += 1;
            		} else {
            			waterPieces += 1;
            		}
            	}
            }
        }

        if (firePieces == 0 && waterPieces == 0) {
        	return "No one";
        } else if (firePieces == 0) {
        	return "Water";
        } else if (waterPieces == 0) {
        	return "Fire";
        } else {
        	return null;
        }

	}

	private void drawPieces() {
    	for (int x = 0; x < 8; x += 1) {
            for (int y = 0; y < 8; y += 1) {
            	if (pieces[x][y] != null) {
            		StdDrawPlus.picture(x + .5, y + .5, getImage(pieces[x][y]), 1, 1);
            	}
            }
        }
    }

    private String getImage(Piece p) {
    	String image;
    	if (p.isKing()) {
    		if (p.isFire()) {
    			if (p.isBomb()) {
    				image = "img/bomb-fire-crowned.png";
    			} else if (p.isShield()) {
    				image = "img/shield-fire-crowned.png";
    			} else {
    				image = "img/pawn-fire-crowned.png";
    			}
    		} else {
    			if (p.isBomb()) {
    				image = "img/bomb-water-crowned.png";
    			} else if (p.isShield()) {
    				image = "img/shield-water-crowned.png";
    			} else {
    				image = "img/pawn-water-crowned.png";
    			}
    		}
    	} else {
    		if (p.isFire()) {
    			if (p.isBomb()) {
    				image = "img/bomb-fire.png";
    			} else if (p.isShield()) {
    				image = "img/shield-fire.png";
    			} else {
    				image = "img/pawn-fire.png";
    			}
    		} else {
    			if (p.isBomb()) {
    				image = "img/bomb-water.png";
    			} else if (p.isShield()) {
    				image = "img/shield-water.png";
    			} else {
    				image = "img/pawn-water.png";
    			}
    		}
    	}
    	return image;
    }

}