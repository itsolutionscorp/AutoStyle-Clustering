public class Board {
	private boolean shouldBeEmpty;
	private Piece[][] pieces = new Piece[8][8];
	private boolean turnisFire = true;
	private Piece piece_selected = null;
	private boolean has_moved = false;
	private Integer x_selected = -1;
	private Integer y_selected = -1;


	public Board(boolean empty) {
		this.shouldBeEmpty = empty;
		if (empty == false) {
			pieceGenerator();
		}		
	}

	public Piece pieceAt(int x , int y) {
		if (x > 8 || y > 8) {
			return null;		
		}
		return pieces[x][y];
	}

	public void place(Piece p , int x , int y) {
		if (p == null || x > 8 || y > 8) {
			return;
		}
		pieces[x][y] = p;
		//reDraw(); make this shit later fam.
	}

	public Piece remove(int x , int y) {
		if (x > 8 || y > 8) {
			System.out.println("Specified Square Out Of Bounds Fam!");
			return null;
		}
		Piece temp = pieceAt(x , y);
		if (temp == null) {
			System.out.println("No Piece At Specified Square Amigo");
			return null;
		}
		pieces[x][y] = null;
		return temp;
	}
	public String winner() {
		int countf = 0;
		int countw = 0;
		for (int i = 0 ; i < 8 ; i++) {
			for (int j = 0 ; j < 8  ; j++) {
				if (pieces[i][j] == null) {
				}
				else if (pieces[i][j].isFire() == true) {
					countf++;
				}	
				else {
					countw++;
				}
			}
		}
		if (countf == 0 && countw == 0) {
			return "No One";
		}

		else if (countf == 0) {
			return "Water";
		}

		else if (countw == 0) {
			return "Fire";
		}

		else {
			return null;
		}
	}
	

	public boolean canSelect(int x , int y) {
		Piece temp = pieceAt(x , y);
		if (temp != null && turnisFire == temp.isFire()) {
			if (piece_selected == null || has_moved == false) {
				return true;
			}
			else {
				return false;
			}
		}
		
		else {
			if (piece_selected != null && has_moved == false && this.validMove(x_selected , y_selected , x , y) == true) {
				return true;
			}
			else if (piece_selected != null && piece_selected.hasCaptured() == true && this.captured(x_selected , y_selected , x , y) == true) {
				return true;
			}
			else {
				return false;
			}
		}
	}

	private boolean captured(int xi , int yi , int xf , int yf) {
		int xdiff = xf - xi;
		int ydiff = yf - yi;
		if (piece_selected.isFire() == true) {
			if (piece_selected.isKing() == true) {
				if ((xdiff == 2 && ydiff == 2 && pieceAt(xi + 1 , yi + 1) != null && pieceAt(xi + 1 , yi + 1).isFire() == false) || (xdiff == -2 && ydiff == -2 && pieceAt(xi -1 , 
					yi -1) != null && pieceAt(xi -1 , yi -1).isFire() == false) || (xdiff == -2 && ydiff == 2 && pieceAt(xi - 1 , yi + 1) != null && 
					pieceAt(xi -1 , yi +1).isFire() == false) || (xdiff == 2 && ydiff == -2 && pieceAt(xi + 1 , yi - 1) != null && pieceAt(xi + 1 , yi - 1).isFire() == false)) {
					return true;
				}
				else {
					return false;
				}
			}	
			else {
					if ((xdiff == 2 && ydiff == 2 && pieceAt(xi+1 , yi+1) != null && pieceAt(xi+1 , yi+1).isFire() == false) || (xdiff == -2 && ydiff == 2 && 
					pieceAt(xi -1 , yi + 1) != null && pieceAt(xi -1 , yi +1).isFire() == false )) {
					return true;
				}
				else {
					return false;
				}
				}
			}
		else {
			if (piece_selected.isKing() == true) {
				if ((xdiff == 2 && ydiff == 2 && pieceAt(xi + 1 , yi + 1) != null && pieceAt(xi + 1 , yi + 1).isFire() == true) || (xdiff == -2 && ydiff == -2 && pieceAt(xi -1 , 
					yi -1) != null && pieceAt(xi -1 , yi -1).isFire() == true) || (xdiff == -2 && ydiff == 2 && pieceAt(xi - 1 , yi + 1) != null && 
					pieceAt(xi -1 , yi +1).isFire() == true) || (xdiff == 2 && ydiff == -2 && pieceAt(xi + 1 , yi - 1) != null && pieceAt(xi + 1 , yi - 1).isFire() == true) ) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				if ((xdiff == -2 && ydiff == -2 && pieceAt(xi-1 , yi-1) != null && pieceAt(xi-1 , yi-1).isFire() == true) || (xdiff == 2 && ydiff == -2 && 
					pieceAt(xi +1 , yi - 1) != null && pieceAt(xi +1 , yi -1).isFire() == true )) {
					return true;
				}
				else {
					return false;
				}
			}
		}
	}
	

	private boolean validMove(int xi , int yi , int xf , int yf) {
		int xdiff = xf - xi;
		int ydiff = yf - yi;
		if (piece_selected.isFire() == true) {			
			if (piece_selected.isKing() == true) {
				if ((xdiff == 1 && ydiff == 1 && pieceAt(xi +1 , yi +1) == null) || (xdiff == -1 && ydiff == -1 && pieceAt(xi -1 , yi -1) == null) 
					|| (xdiff == -1 && ydiff == 1 && pieceAt(xi -1 , yi + 1) == null) || (xdiff == 1 && ydiff == -1 && pieceAt(xi + 1 , yi -1) == null)) {
					return true;
				}
				else if ((xdiff == 2 && ydiff == 2 && pieceAt(xi + 1 , yi + 1) != null && pieceAt(xi + 1 , yi + 1).isFire() == false) || (xdiff == -2 && ydiff == -2 && pieceAt(xi -1 , 
					yi -1) != null && pieceAt(xi -1 , yi -1).isFire() == false) || (xdiff == -2 && ydiff == 2 && pieceAt(xi - 1 , yi + 1) != null && 
					pieceAt(xi -1 , yi +1).isFire() == false) || (xdiff == 2 && ydiff == -2 && pieceAt(xi + 1 , yi - 1) != null && pieceAt(xi + 1 , yi - 1).isFire() == false)) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				if ((xdiff == 1 && ydiff == 1 && pieceAt(xi + 1 , yi +1) == null) || (xdiff == -1 && ydiff == 1 && pieceAt(xi -1 , yi +1) == null)) {
					return true;
				}
				else if ((xdiff == 2 && ydiff == 2 && pieceAt(xi+1 , yi+1) != null && pieceAt(xi+1 , yi+1).isFire() == false) || (xdiff == -2 && ydiff == 2 && 
					pieceAt(xi -1 , yi + 1) != null && pieceAt(xi -1 , yi +1).isFire() == false )) {
					return true;
				}
				else {
					return false;
				}
			}
		}

		else {
			if (piece_selected.isKing() == true) {
				if ((xdiff == 1 && ydiff == 1 && pieceAt(xi + 1 , yi + 1) == null) || (xdiff == -1 && ydiff == -1 && pieceAt(xf , yf) == null) || 
					(xdiff == -1 && ydiff == 1 && pieceAt(xf , yf) == null) || (xdiff == 1 && ydiff == -1 && pieceAt(xf , yf) == null)) {
					return true;
				}
				else if ((xdiff == 2 && ydiff == 2 && pieceAt(xi + 1 , yi + 1) != null && pieceAt(xi + 1 , yi + 1).isFire() == true) || (xdiff == -2 && ydiff == -2 && pieceAt(xi -1 , 
					yi -1) != null && pieceAt(xi -1 , yi -1).isFire() == true) || (xdiff == -2 && ydiff == 2 && pieceAt(xi - 1 , yi + 1) != null && 
					pieceAt(xi -1 , yi +1).isFire() == true) || (xdiff == 2 && ydiff == -2 && pieceAt(xi + 1 , yi - 1) != null && pieceAt(xi + 1 , yi - 1).isFire() == true) ) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				if ((xdiff == -1 && ydiff == -1 && pieceAt(xf , yf) == null) || (xdiff == 1 && ydiff == -1 && pieceAt(xf , yf) == null)) {
					return true;
				}
				else if ((xdiff == -2 && ydiff == -2 && pieceAt(xi-1 , yi-1) != null && pieceAt(xi-1 , yi-1).isFire() == true) || (xdiff == 2 && ydiff == -2 && 
					pieceAt(xi +1 , yi - 1) != null && pieceAt(xi +1 , yi -1).isFire() == true )) {
					return true;
				}
				else {
					return false;
				}
			}
		}
	}	


	public void select(int x , int y) {
		if (pieceAt(x , y) == null && piece_selected != null) {
			piece_selected.move(x , y);
			x_selected = x;
			y_selected = y;
			has_moved = true;
		}
		else if (pieceAt(x , y) != null) {
			piece_selected = pieceAt(x , y);
			x_selected = x;
			y_selected = y;
		}
		return;
	}

	public boolean canEndTurn() {
		if (piece_selected == null && has_moved == true) {
			return true;
		}	
		else if (piece_selected == null) {
			return false;
		}
		
		return (has_moved || piece_selected.hasCaptured());
	}

	public void endTurn() {
		if (turnisFire == true) {
			turnisFire = false;
		}
		else {
			turnisFire = true;
		}
		piece_selected.doneCapturing();
		piece_selected = null;
		has_moved = false;
		x_selected = -1;
		y_selected = -1;
	}

	

	private void pieceGenerator() {
		pieces = new Piece[8][8];
		for (int i = 0 ; i < 8 ; i++) {
			for (int j = 0 ; j < 8 ; j++) {
				if (j == 0) {
					if (i % 2 == 0) {
						pieces[i][j] = new Piece(true , this , i , j , "pawn");
					}
					else {
						pieces[i][j] = null;
					}
				}
				if (j == 1) {
					if ((i + 1) % 2 == 0) {
						pieces[i][j] = new Piece(true , this , i , j , "shield");
					}
					else {
						pieces[i][j] = null;
					}
				}
				if (j == 2) {
					if (i % 2 == 0) {
						pieces[i][j] = new Piece(true , this , i , j , "bomb");
					}
					else {
						pieces[i][j] = null;
					}
				}
				if (j == 3 || j == 4) {
					pieces[i][j] = null;
				}
				if (j == 5) {
					if ((i + 1) % 2 == 0) {
						pieces[i][j] = new Piece(false , this , i , j , "bomb");
					}
					else {
						pieces[i][j] = null;
					}
				}
				if (j == 6) {
					if (i % 2 == 0) {
						pieces[i][j] = new Piece(false , this , i , j , "shield");
					}
					else {
						pieces[i][j] = null;
					}
				}
				if (j == 7) {
					if ((i + 1) % 2 == 0) {
						pieces[i][j] = new Piece(false , this , i , j , "pawn");
					}
					else {
						pieces[i][j] = null;
					}
				}
			}
		}
	}







	public static void main (String[] args) {
		Board b = new Board(false); 
		StdDrawPlus.setXscale(0 , 8);
		StdDrawPlus.setYscale(0 , 8);
		while (true) {
			if (b.winner() != null) {
				System.out.print(b.winner());
				break;
			}
			// executing non-draw things
			if (StdDrawPlus.mousePressed() == true) {
				int x = (int) StdDrawPlus.mouseX();
				int y = (int) StdDrawPlus.mouseY();
				if (b.canSelect(x , y) == true) {
				b.select(x , y);
				}
			}
			if (StdDrawPlus.isSpacePressed() == true) {
				if (b.canEndTurn()) {
					b.endTurn();
				}
			}
			
			if (b.shouldBeEmpty == true) {
			for (int i = 0 ; i < 8 ; i++) {
				for (int j = 0 ; j < 8 ; j++) {
					if ((i + j) % 2 == 0) {
						StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					}
					else 
					{
						StdDrawPlus.setPenColor(StdDrawPlus.RED);
					}
					

					StdDrawPlus.filledSquare(i + .5, j + .5, .5);
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				}
			}
			StdDrawPlus.show();
		}
		else {
			for (int i = 0 ; i < 8 ; i++) {
				for (int j = 0 ; j < 8 ; j++) {
					if ((i + j) % 2 == 0) {
						StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					}

					else {
						StdDrawPlus.setPenColor(StdDrawPlus.RED);
					}
					StdDrawPlus.filledSquare(i + .5, j + .5, .5);
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					
					if (b.pieces[i][j] == null) {
					}
					else if (b.pieces[i][j].isFire()) {
						if (b.pieces[i][j].isKing()) {
							if (b.pieces[i][j].isBomb()) {
								StdDrawPlus.picture(i + .5 , j + .5 , "img/bomb-fire-crowned.png" , 1 , 1);
							}
							else if (b.pieces[i][j].isShield()) {
								StdDrawPlus.picture(i + .5 , j + .5 , "img/shield-fire-crowned.png" , 1 , 1);
							}
							else {
								StdDrawPlus.picture(i + .5 , j + .5 , "img/pawn-fire-crowned.png" , 1 , 1);
							}
						}


						else {
							if (b.pieces[i][j].isBomb()) {
								StdDrawPlus.picture(i + .5 , j + .5 , "img/bomb-fire.png" , 1, 1);
							}
							else if (b.pieces[i][j].isShield()) {
								StdDrawPlus.picture(i + .5 , j + .5 , "img/shield-fire.png" , 1 , 1);
							}
							else {
								StdDrawPlus.picture(i + .5 , j + .5 , "img/pawn-fire.png", 1, 1);
							}
						}
				}
				else if (b.pieces[i][j].isFire() == false) {
					if (b.pieces[i][j].isKing()) {
							if (b.pieces[i][j].isBomb()) {
								StdDrawPlus.picture(i + .5 , j + .5 , "img/bomb-water-crowned.png" ,1 , 1);
							}
							else if (b.pieces[i][j].isShield()) {
								StdDrawPlus.picture(i + .5 , j + .5 , "img/shield-water-crowned.png" , 1, 1);
							}
							else {
								StdDrawPlus.picture(i + .5 , j + .5 , "img/pawn-water-crowned.png", 1 , 1);
							}
						}
						else {
							if (b.pieces[i][j].isBomb()) {
								StdDrawPlus.picture(i + .5 , j + .5 , "img/bomb-water.png" , 1 , 1);
							}
							else if (b.pieces[i][j].isShield()) {
								StdDrawPlus.picture(i + .5 , j + .5 , "img/shield-water.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(i + .5 , j + .5 , "img/pawn-water.png", 1, 1);
							}
						}
				}
			}
		}
		StdDrawPlus.show(16);
	}
	}
		
	}
}
