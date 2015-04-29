public class Board {
	private boolean empty;
	private Piece[][] pieces = new Piece[8][8];
	private boolean playerFire = true;
	private boolean havemoved = false;
	private boolean haveCaptured = false;
	private int selectedX;
	private int selectedY;
	private Piece selected = null;

	public static void main(String [] args) {
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board b = new Board(false);
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
           		if (b.pieceAt(i, j) == null){
           		} else if (b.pieceAt(i, j).isFire() && b.pieceAt(i, j).isKing() == false) {
           			if (b.pieceAt(i, j).isBomb()) {
           				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
    	       		} else if (b.pieceAt(i, j).isShield()) {
        	   			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
            		} else {
           				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
           			}
	           	} else if (b.pieceAt(i, j).isFire() == false && b.pieceAt(i, j).isKing() == false) {
    	       		if (b.pieceAt(i, j).isBomb()) {
        	   			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
            		} else if (b.pieceAt(i, j).isShield()) {
            			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
            		} else {
           				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
            		}
    	       	} else if (b.pieceAt(i, j).isFire() && b.pieceAt(i, j).isKing()) {
           			if (b.pieceAt(i, j).isBomb()) {
           				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
    	       		} else if (b.pieceAt(i, j).isShield()) {
        	   			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
            		} else {
           				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
           			}
	           	} else if (b.pieceAt(i, j).isFire() == false && b.pieceAt(i, j).isKing()) {
    	       		if (b.pieceAt(i, j).isBomb()) {
        	   			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
            		} else if (b.pieceAt(i, j).isShield()) {
            			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
            		} else {
           				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
            		}
            	}
        	}
        }
		while (true) {
			if (StdDrawPlus.mousePressed()) {
			int xCor = (int)StdDrawPlus.mouseX();
				int yCor = (int)StdDrawPlus.mouseY();
				if (b.canSelect(xCor, yCor) == true) {
					b.select(xCor, yCor);
				}
			} 
			if (StdDrawPlus.isSpacePressed() == true) {
				if (b.canEndTurn() == true) {
					b.endTurn();
				}
			}
		}
	}
	

	private void drawPieces(int N) {
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
           		if (pieces[i][j] == null){
           		} else if (pieces[i][j].isFire() && pieces[i][j].isKing() == false) {
           			if (pieces[i][j].isBomb()) {
           				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
    	       		} else if (pieces[i][j].isShield()) {
        	   			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
            		} else {
           				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
           			}
	           	} else if (pieces[i][j].isFire() == false && pieces[i][j].isKing() == false) {
    	       		if (pieces[i][j].isBomb()) {
        	   			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
            		} else if (pieces[i][j].isShield()) {
            			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
            		} else {
           				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
            		}
    	       	} else if (pieces[i][j].isFire() && pieces[i][j].isKing()) {
           			if (pieces[i][j].isBomb()) {
           				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
    	       		} else if (pieces[i][j].isShield()) {
        	   			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
            		} else {
           				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
           			}
	           	} else if (pieces[i][j].isFire() == false && pieces[i][j].isKing()) {
    	       		if (pieces[i][j].isBomb()) {
        	   			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
            		} else if (pieces[i][j].isShield()) {
            			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
            		} else {
           				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
            		}
            	}
        	}
        }
	}

	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
    }

	public Board(boolean shouldBeEmpty) {
		empty = shouldBeEmpty;
		if (empty == false) {
			for (int i = 0; i < 8; i = i + 2) {
        		pieces[i][0] = new Piece(true, this, i, 0, "pawn");
        		pieces[i][2] = new Piece(true, this, i, 2, "bomb");
        		pieces[i][6] = new Piece(false, this, i, 6, "shield");
       			}
       		for (int i = 1; i < 8; i = i + 2) {
        		pieces[i][1] = new Piece(true, this, i, 1, "shield");
        		pieces[i][5] = new Piece(false, this, i, 5, "bomb");
        		pieces[i][7] = new Piece(false, this, i, 7, "pawn");
        	}
       	}
	}

	public Piece pieceAt(int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0) {
			return null;
		} else if (pieces[x][y] != null) {
			return pieces[x][y];
		} else {
			return null;
		}
	}

	public boolean canSelect(int x, int y) {
		if (pieces[x][y] != null) {
			if (haveCaptured == true) {
				return false;
			} else if (havemoved == true) {
				return false;
			} else if (playerFire == true && pieces[x][y].isFire() == true) {
				return true;
			} else if (playerFire == false && pieces[x][y].isFire() == false) {
				return true;
			} else {
				return false;
			}
		} else {
			if (haveCaptured == false) {
				if (selected == null) {
					return false;
				} else if (havemoved == true) {
					return false;
				} else if (selected != null && validMove(selectedX, selectedY, x, y) && 
					       havemoved == false) {
					return true;
				} else {
					return false;
				}
			} else {
				if (selected != null && validMove(selectedX, selectedY, x, y) && 
					Math.abs(x - selectedX) == 2 && 
					pieces[(selectedX + x)/2][(selectedY + y)/2] != null) {
					return true;
				} else {
					return false;
				}
			}
		}
	}

	public void select(int x, int y) {
		if (pieces[x][y] != null) {
			selectedX = x;
			selectedY = y;
			selected = pieces[x][y];
		} else if (pieces[x][y] == null && selected != null) {
			selected.move(x, y);
			havemoved = true;
			remove(selectedX, selectedY);
			if (Math.abs(selectedX - x) == 2 && 
				pieces[(selectedX + x)/2][(selectedY + y)/2] != null) {
				remove((selectedX + x)/2, (selectedY + y)/2);
				haveCaptured = true;
				if (selected.isBomb()) {
					remove(x, y);
					explosion(x, y);
				}
			}
			selectedX = x;
			selectedY = y;
			drawBoard(8);
			drawPieces(8);
		}
	}

	public void place(Piece p, int x, int y) {
		if (inRange(x, y) == false) {
		} else if (p == null) {
		} else if (pieces[x][y] == null) {
			pieces[x][y] = p;
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece item = pieces[xi][yi];
		if (item != null && pieces[xf][yf] == null) {
			if (item.isKing() == false) {
				if (item.isFire() == true) {
					if (Math.abs(xf - xi) == 1 && yf - yi == 1) {
						return true;
					} else if (Math.abs(xf - xi) == 2 && yf - yi == 2 && 
						       pieces[(xi + xf)/2][yi + 1] != null) {
						return true;
					} else {
						return false;
					}
				} else {
					if (Math.abs(xf - xi) == 1 && yi - yf == 1) {
						return true;
					} else if (Math.abs(xf - xi) == 2 && yi - yf == 2 && 
						       pieces[(xi + xf)/2][yi - 1] != null) {
						return true;
					} else {
						return false;
					}
				}
			} else {
				if (Math.abs(xf - xi) == 1 && Math.abs(yf - yi) == 1) {
					return true;
				} else if (Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2 && 
					       pieces[(xi + xf)/2][(yi + yf)/2] != null) {
					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}

	private boolean inRange(int x, int y) {
		if (x < 8 && x >= 0 && y < 8 && y >= 0) {
			return true;
		} else {
			return false;
		}
	}

	private void explosion(int x, int y) {
		if (inRange(x + 1, y + 1) && pieces[x + 1][y + 1] != null && 
			pieces[x + 1][y + 1].isShield() == false) {
			remove(x + 1, y + 1);
		}
		if (inRange(x + 1, y) && pieces[x + 1][y] != null && 
			pieces[x + 1][y].isShield() == false) {
			remove(x + 1, y);
		}
		if (inRange(x + 1, y - 1) && pieces[x + 1][y - 1] != null && 
			pieces[x + 1][y - 1].isShield() == false) {
			remove(x + 1, y - 1);
		}
		if (inRange(x, y + 1) && pieces[x][y + 1] != null && 
			pieces[x][y + 1].isShield() == false) {
			remove(x, y + 1);
		}
		if (inRange(x, y - 1) && pieces[x][y - 1] != null && 
			pieces[x][y - 1].isShield() == false) {
			remove(x, y - 1);
		}
		if (inRange(x - 1, y + 1) && pieces[x - 1][y + 1] != null && 
			pieces[x - 1][y + 1].isShield() == false) {
			remove(x - 1, y + 1);
		}
		if (inRange(x - 1, y) && pieces[x - 1][y] != null && 
			pieces[x - 1][y].isShield() == false) {
			remove(x - 1, y);
		}
		if (inRange(x - 1, y - 1) && pieces[x - 1][y - 1] != null && 
			pieces[x - 1][y - 1].isShield() == false) {
			remove(x - 1, y - 1);
		}
	}

	public Piece remove(int x, int y) {
		Piece deleted = pieces[x][y];
		if (x > 7 || y > 7 || x < 0 || y < 0) {
			System.out.println("Sorry coordinate out of bound.");
			return null;
		} else if (deleted == null) {
			System.out.println("Sorry no peice on indicated coordinate.");
			return null;
		} else {
			pieces[x][y] = null;
			return deleted;
		}
	}

	public boolean canEndTurn() {
		if (havemoved == true) {
			return true;
		} else if (haveCaptured == true) {
			return true;
		} else {
			return false;
		}
	}

	public void endTurn() {
		winner();
		havemoved = false;
		haveCaptured = false;
		selected = null;
		if (playerFire == true) {
			playerFire = false;
		} else {
			playerFire = true;
		}
	}

	public String winner() {
		int pieceFire = 0;
		int pieceWater = 0;
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++){
            	if (pieces[i][j] != null) {
            		if (pieces[i][j].isFire() == true) {
            			pieceFire = pieceFire + 1;
            		} else {
            			pieceWater = pieceWater + 1;
            		} 
            	}
	        }
	    }
		if (pieceWater != 0 && pieceFire == 0) {
			return "Water";
		} else if (pieceFire != 0 && pieceWater == 0) {
			return "Fire";
		} else if (pieceFire == 0 && pieceWater == 0) {
			return "No one";
		} else {
			return null;
		}
	}
}