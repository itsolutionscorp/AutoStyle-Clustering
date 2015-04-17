public class Board {
	private Piece[][] pieces;
	private int whoseturn = 0; 
	private boolean ifMoved = false;
	private boolean ifSelected = false;
	private int x_selected = -1;
	private int y_selected = -1;
	private void drawPieces() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (pieceAt(i, j) != null) {
					if (pieceAt(i, j).isFire() == true) {
						if (pieceAt(i, j).isBomb() == true) {
							if (pieceAt(i, j).isKing() == true){
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire-crowned.png", 1, 1);
							}
							StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire.png", 1, 1);
						}
						if (pieceAt(i, j).isShield() == true) {
							if (pieceAt(i, j).isKing() == true) {
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire-crowned.png", 1, 1);
							}
							StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire.png", 1, 1);
						}
						if (pieceAt(i, j).isBomb() == false && pieceAt(i, j).isShield() == false) {
							if (pieceAt(i, j).isKing() == true) {
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire-crowned.png", 1, 1);
							}
							StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire.png", 1, 1);
						}

					}
					if (pieceAt(i, j).isFire() == false) {
						if (pieceAt(i, j).isBomb() == true) {
							if (pieceAt(i, j).isKing() == true){
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water-crowned.png", 1, 1);
							}
							StdDrawPlus.picture(i + .5, j + 0.5, "img/bomb-water.png", 1, 1);
						}
						if (pieceAt(i, j).isShield() == true) {
							if (pieceAt(i, j).isKing() == true) {
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water-crowned.png", 1, 1);
							}
							StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water.png", 1, 1);
						}
						if (pieceAt(i, j).isBomb() == false && pieceAt(i, j).isShield() == false) {
							if (pieceAt(i, j).isKing() == true) {
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water-crowned.png", 1, 1);
							}
							StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water.png", 1, 1);
						}

					}
				}
			}

		}
	}

		private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (i == x_selected && j == y_selected) {
            		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            	}
                else if ((i + j) % 2 == 0)
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                

            }
        }
    }

    public static void main(String[] args) {
    	int N = 8; 
        Board b = new Board(false); 
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
       
        while(true) {
            b.drawBoard(N);
            b.drawPieces();
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int)x, (int)y)) {
                	b.select((int)x, (int)y);
                } 
            }
            if (StdDrawPlus.isSpacePressed()) {
            	if (b.canEndTurn()) {
            		b.endTurn();
            	}
            }

            StdDrawPlus.show(100);
        }

	}


	public Board (boolean shouldBeEmpty) {
		 pieces = new Piece [8] [8];
		if (shouldBeEmpty == false) {
			for (int i = 0; i < 8; i++) {
				if (i % 2 == 0) {
					pieces[i][0] = new Piece(true, this, i, 0, "pawn");
					pieces[i][2] = new Piece(true, this, i, 2, "bomb");
					pieces[i][6] = new Piece(false, this, i, 6, "pawn");
				}

				if (i % 2 != 0) {
					pieces[i][1] = new Piece(true, this, i, 1, "shield");
					pieces[i][5] = new Piece(false, this, i, 5, "bomb");
					pieces[i][7] = new Piece (false, this, i, 7, "pawn");
				}
			}
		}
	}
	public Piece pieceAt(int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0) {
			return null;
		}
		else {
			return pieces[x][y];
		}
	}


	public void select (int x, int y) {
		if (pieceAt(x, y) != null) {
			ifSelected = true;
			x_selected = x;
			y_selected = y; 
		}
		else {
			pieceAt(x_selected, y_selected).move(x, y);
			x_selected = x;
			y_selected = y;
			ifMoved = true;
		}
	}

	public void place(Piece p, int x, int y) {
		if (p == null || x > 7 || y > 7 || x < 0 || y < 0) {
		}
		if (p!= null) {
			pieces[x][y] = p;
		}
	} 

	public Piece remove (int x, int y) {
		if (x > 7 || y > 7|| x < 0 || y < 0) {
			System.out.println("Out of Bounds!");
			return null;
		}
		if (pieceAt(x, y) == null) {
			System.out.println("No piece here!");
			return null;
		}
		else {
			Piece temp = pieceAt(x, y);
			pieces[x][y] = null;
			return temp;
		}
	}
 	public boolean canSelect(int x, int y) {
 		if (pieces[x][y] != null && pieces[x][y].side()==whoseturn && !ifMoved) {
 			return true;
 		}
 		else if (pieces[x][y] == null && ifSelected && validMove(x_selected, y_selected, x, y)) {
 			return true;
 		}
 		return false;
 	}
	private boolean validMove(int xi, int yi, int xf, int yf) {
		int xDir = (xf - xi);
		int yDir = (yf - yi);
		Piece temp = pieceAt(xi, yi);
		if (temp.isFire() && yDir < 0 && !temp.isKing()) {
			return false;
		}
		else if (!temp.isFire() && yDir > 0 && !temp.isKing()) {
			return false;
		}
		if (Math.abs(xf - xi) == 1 && Math.abs(yf - yi) == 1 && ifMoved == false) {
 			return true;
 		}
 		else if (Math.abs(xi - xf) == 2 && Math.abs(yi - yf) == 2) {
 			if (pieceAt(xi+xDir/2, yi+yDir/2) != null && pieceAt(xi+xDir/2, yi+yDir/2).side()!=whoseturn) {
 				return true;
 			}
 		}

		return false;
	}

 	public boolean canEndTurn() {
 		return ifMoved; 
 	}

 	public void endTurn() {
 		if (whoseturn == 0) {
 			whoseturn = 1; 
 		}
 		else if (whoseturn == 1) {
 			whoseturn = 0;
 		}

		ifMoved = false;
		ifSelected = false;
		x_selected = -1;
		y_selected = -1;
 	}
 	public String winner() {
 		int winning_fire = 0;
 		int winning_water = 0;
 		for (int i = 0; i < 8; i++) {
 			for (int j = 0; j < 8; i++){
 				if (pieceAt(i, j) != null && whoseturn == 0) {
 					winning_fire+=1;
 				}
 				else if (pieceAt(i, j) != null && whoseturn == 1) {
 					winning_water +=1; 
 				}
 			}

 		}
 		if (winning_fire > winning_water) {
 			return "Fire";
 		}
 		if (winning_water > winning_fire) {
 			return "Water";
 		}
 		if (winning_water == 0 && winning_fire ==0) {
 			return "No One";
 		}
 		else {
 			return null; 
 		}
 	}

 	
}
