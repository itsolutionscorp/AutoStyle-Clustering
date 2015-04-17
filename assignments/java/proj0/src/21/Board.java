public class Board {
	
	private Piece[][] pieces;
	private boolean shouldBeEmpty;
	private Piece piece_selected;
	private boolean fire_turn;
	private boolean has_piece_moved;
	private int x_piece_selected;
	private int y_piece_selected;
	private double x;
	private double y;
	private boolean has_bomb_exploded;


	public Board(boolean shouldBeEmpty){
		this.shouldBeEmpty = shouldBeEmpty;
		pieces = new Piece[8][8];
		fire_turn = true;
		piece_selected = null;
		has_bomb_exploded = false;
		has_piece_moved = false;

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
	public Piece pieceAt(int x, int y) {
		if (x >= 8 || y >= 8 || x < 0 || y < 0) {
			return null;
		}
		else if (pieces[x][y] != null){
			return pieces[x][y];
		}
		else {
			return null;
		}

	}

	public boolean canSelect(int x, int y) {
		if (x >= 8 || y >= 8 || x < 0 || y < 0) {
			return false;
		} 
		if (pieces[x][y] != null) {
			if ((pieces[x][y].isFire() && fire_turn) || (!pieces[x][y].isFire() && !fire_turn)) { /* piece is able to be selected if it is fire's turn and it is a fire piece */
				if ((piece_selected == null || (piece_selected != null && has_piece_moved == false)) && !has_bomb_exploded) { /* erased "&& x_piece_selected != x && y_piece_selected != y" */
					return true;
				}
				return false;
			}
		}
		else {
			if (has_piece_moved == false && piece_selected != null && pieces[x][y] == null && validMove(x_piece_selected, y_piece_selected, x,y) ) {
				return true;
			}
			else if (piece_selected != null && piece_selected.hasCaptured() && validMove(x_piece_selected, y_piece_selected, x, y)){
				return true;
			}
			
			return false;

		}
		/*WHY DO I NEED THIS????? */
		return false;
		

	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		/* Valid moves if the piece is a water piece*/
		if (pieces[xi][yi].isFire() == false) {
				if (pieces[xi][yi].isKing()) { /*additional valid moves available if a king */
					if ( ((xi ==  xf + 1 && yi == yf + 1) || (xi == xf - 1 && yi == yf + 1) || (xi ==  xf + 1 && yi == yf - 1) || (xi == xf - 1 && yi == yf - 1)) && !piece_selected.hasCaptured()) { /* */
						if (pieces[xf][yf] == null) {
							return true; /* viable move for a water piece king to move in all four directions if the square is empty */
						}
							return false;

					}
					else if ((xi == xf + 2 && yi == yf + 2)) { 
						if ((pieces[xf][yf] == null && pieces[xi - 1][yi - 1] != null && pieces[xi - 1][yi -1].isFire())){
							return true; 
						}
							return false;		 
					}

					else if ((xi == xf - 2 && yi == yf + 2)) {
						if ((pieces[xf][yf] == null) && pieces[xi + 1][yi - 1] != null && pieces[xi + 1][yi - 1].isFire()) {
							return true;  
						}
							return false;
					}
					else if ((xi == xf + 2 && yi == yf - 2)) { 
						if ((pieces[xf][yf] == null && pieces[xi - 1][yi + 1] != null && pieces[xi - 1][yi + 1].isFire())){
							return true; 
						}
							return false;		 
					}

					else if ((xi == xf - 2 && yi == yf - 2)) {
						if ((pieces[xf][yf] == null) && pieces[xi + 1][yi + 1] != null && pieces[xi + 1][yi + 1].isFire()) {
							return true;  /* viable move for a water piece to take a piece to its right */
						}
							return false;
					}
					else {
							return false;
					}



					/* ***********/
				}	

				else if (((xi ==  xf + 1 && yi == yf + 1) || (xi == xf - 1 && yi == yf + 1)) && !piece_selected.hasCaptured()) { /* */
					if (pieces[xf][yf] == null) {
						return true; /* viable move for a water piece and the square is empty */
					}
					return false;

				}
				else if ((xi == xf + 2 && yi == yf + 2)) { 
					if ((pieces[xf][yf] == null && pieces[xi - 1][yi - 1] != null && pieces[xi - 1][yi -1].isFire())){
						return true; /*viable move for a water piece to take a piece to its left */
					}
					return false;		 
				}

				else if ((xi == xf - 2 && yi == yf + 2)) {
					if ((pieces[xf][yf] == null) && pieces[xi + 1][yi - 1] != null && pieces[xi + 1][yi - 1].isFire()) {
						return true;  /* viable move for a water piece to take a piece to its right */
					}
					return false;
				}
				
				
				
				else {
					return false;
				}
		}
		/* Valid moves if the piece is a fire piece */
		else {
			if (pieces[xi][yi].isKing()) {
				if (((xi ==  xf + 1 && yi == yf + 1) || (xi == xf - 1 && yi == yf + 1) || (xi ==  xf + 1 && yi == yf - 1) || (xi == xf - 1 && yi == yf - 1)) && !piece_selected.hasCaptured() ) { /* */
					if (pieces[xf][yf] == null) {
						return true; /* viable move for a fire piece and the square is empty */
					}
					return false;

				}
				else if ((xi == xf + 2 && yi == yf + 2)) { 
					if ((pieces[xf][yf] == null && pieces[xi - 1][yi - 1] != null && !pieces[xi - 1][yi -1].isFire())){
						return true; /*viable move for a fire piece to take a piece to its left */
					}
					return false;		 
				}

				else if ((xi == xf - 2 && yi == yf + 2)) {
					if ((pieces[xf][yf] == null) && pieces[xi + 1][yi - 1] != null && !pieces[xi + 1][yi - 1].isFire()) {
						return true;  /* viable move for a fire piece to take a piece to its right */
					}
					return false;
				}

				else if ((xi == xf + 2 && yi == yf - 2)) { 
					if ((pieces[xf][yf] == null && pieces[xi - 1][yi + 1] != null && !pieces[xi - 1][yi + 1].isFire())){
						return true; /*viable move for a water piece to take a piece to its left */
					}
						return false;		 
					}

				else if ((xi == xf - 2 && yi == yf - 2)) {
					if ((pieces[xf][yf] == null) && pieces[xi + 1][yi + 1] != null && !pieces[xi + 1][yi + 1].isFire()) {
						return true;  /* viable move for a water piece to take a piece to its right */
					}
						return false;
					}
			}
			else if (((xi ==  xf + 1 && yi == yf - 1) || (xi == xf - 1 && yi == yf - 1)) && !piece_selected.hasCaptured()) { /* */
				if (pieces[xf][yf] == null) {
					return true; /* viable move for a fire piece and the square is empty */
				}
					return false;

			}
			else if ((xi == xf + 2 && yi == yf - 2)) { 
				if ((pieces[xf][yf] == null && pieces[xi - 1][yi + 1] != null && !pieces[xi - 1][yi + 1].isFire())){
					return true; /*viable move for a fire piece to take a piece to its left */
				}
				return false;		 
			}

			else if ((xi == xf - 2 && yi == yf - 2)) {
				if ((pieces[xf][yf] == null) && pieces[xi + 1][yi + 1] != null && !pieces[xi + 1][yi + 1].isFire()) {
					return true;  /* viable move for a fire piece to take a piece to its right */
				}
				return false;
			}

			

			else {
				return false;
			}
		/*WHY DO I NEED THIS */
		return false;
		}
	}




	public void select(int x, int y) {
		if (x >= 8 || y >= 8 || x < 0 || y < 0) {
			System.out.println("Out of Bounds");
		}
		else {
			if (pieces[x][y] != null) {
				piece_selected = pieces[x][y];
            	x_piece_selected = x;
            	y_piece_selected = y; 
			}
			else {
				piece_selected.move(x,y);
				if (piece_selected.hasCaptured() && piece_selected.isBomb()){
            		piece_selected = null;
            		has_bomb_exploded = true;
            		has_piece_moved = true;
            	}
				else {
					x_piece_selected = x;
					y_piece_selected = y;
					has_piece_moved = true; /* JUST ADDED */
				}
				
				
			}
			
            // piece_selected = pieces[x][y];
            // x_piece_selected = x;
            // y_piece_selected = y;

		}
	}

	public void place(Piece P, int x, int y){
		if (x >= 8 || y >= 8 || P == null || x < 0 || y < 0) {
			System.out.println("Out of Bounds or Piece is Null");
		}

		else {
			for (int i = 0; i < 8; i++) {
            	for (int j = 0; j < 8; j++) {
            		if (pieces[i][j] != null && pieces[i][j] == P)  /*&& (has_piece_moved == false || P.hasCaptured())) {  MADE ADDITIONAL CONDITION P.hasCaptured()*/
            			
            			pieces[i][j] = null;
            			if (pieces[x][y] != null) {
            				remove(x,y);
            			}

            			pieces[x][y] = P;

            			x_piece_selected = x;
            			y_piece_selected = y;


            			// has_piece_moved = true;
            			
           				// has_piece_moved = true; /* WTFFFFF */
           				
           				
            	}
           	}	
        }
    }

	

	

	public Piece remove(int x, int y) {
		if (x >= 8 || y >= 8 || x < 0 || y < 0) {
			System.out.println("Out of Bounds");
			return null;
		}
		else if (pieces[x][y] == null) {
			System.out.println("There is no piece there");
			return null;
		}
		else {
			Piece alpha = pieces[x][y];
			pieces[x][y] = null;
			return alpha;
		}

	}

	public boolean canEndTurn() {
		if (piece_selected == null) {
			return has_piece_moved;
		}
		else if (has_piece_moved || piece_selected.hasCaptured()) {
			return true;
		}
		return false;

	}

	public void endTurn() {
		fire_turn = !fire_turn;
		has_piece_moved = false;
		if (piece_selected != null) {
			piece_selected.doneCapturing();
		}
		piece_selected = null;
		has_bomb_exploded = false;
		x_piece_selected = 100;
		y_piece_selected = 100;
		


	}

	public String winner() {
		int num_fire_pieces = 0;
		int num_water_pieces = 0;
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (pieces[i][j] != null) {
            		if (pieces[i][j].isFire()) {
            			num_fire_pieces += 1;
            		}
            		else {
            			num_water_pieces += 1;
            		}
            	}
            }
        }
        if (num_fire_pieces == 0 && num_water_pieces == 0) {
        	return "No one";
        }
        else if (num_water_pieces == 0){
        	return "Fire";
        }
        else if (num_fire_pieces == 0) {
        	return "Water";
        }
        else {
        	return null;
        }


	}

	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (piece_selected != null && x_piece_selected == i && y_piece_selected == j) {
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                } 
                else if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                	if (pieces[i][j] != null){
                		// if (pieces[i][j].type == "pawn" && pieces[i][j].isFire && pieces[i][j].isKing()) {
                		// 	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                		// }
                		if (pieces[i][j].isBomb() && pieces[i][j].isFire() && pieces[i][j].isKing()) {
                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                		}
                		else if (pieces[i][j].isShield() && pieces[i][j].isFire() && pieces[i][j].isKing()) {
                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                		}
                		// if ((pieces[i][j].type == "pawn") && (pieces[i][j].isFire())) {
                		// 	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                		// }
                		else if (pieces[i][j].isBomb() && pieces[i][j].isFire()) {
							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                		}
                		else if (pieces[i][j].isShield() && pieces[i][j].isFire()) {
							StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1); /*Change BACK */
                		}
                		else if (pieces[i][j].isFire() && pieces[i][j].isKing()) {
                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                		}
                		else if (pieces[i][j].isFire()) {
                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                		}
                		

                		// if (pieces[i][j].type == "pawn" && !pieces[i][j].isFire() && pieces[i][j].isKing()) {
                		// 	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                		// }
                		else if (pieces[i][j].isBomb() && !pieces[i][j].isFire() && pieces[i][j].isKing()) {
                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                		}
                		else if (pieces[i][j].isShield() && !pieces[i][j].isFire() && pieces[i][j].isKing()) {
                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                		}
       //          		if (pieces[i][j].type == "pawn" && !pieces[i][j].isFire()) {
							// StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
       //          		}
                		else if (pieces[i][j].isBomb() && !pieces[i][j].isFire()) {
							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                		}
                		else if (pieces[i][j].isShield() && !pieces[i][j].isFire()) {
							StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                		}
                		else if (!pieces[i][j].isFire() && pieces[i][j].isKing()) {
                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                		}
                		else if (!pieces[i][j].isFire()) {
							StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                		}
                		
                	}
                	
                }
            }
        }
    


	public static void main (String args[]) {
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        Board my_board = new Board(false);
        
        while (true) {
			my_board.x = 100;
        	my_board.y = 100;
        	my_board.drawBoard(N);
        	if (StdDrawPlus.mousePressed()) {
                my_board.x = StdDrawPlus.mouseX();
                my_board.y = StdDrawPlus.mouseY();
                
            }
            if (my_board.canSelect((int) my_board.x, (int) my_board.y) && (my_board.piece_selected == null) && my_board.has_piece_moved == false) { /* */
                my_board.select((int) my_board.x, (int) my_board.y);
                
                 
                	
            }
            else if (my_board.canSelect((int) my_board.x, (int) my_board.y) && (my_board.piece_selected != null)) { /*  && my_board.has_piece_moved == false  */
                my_board.select((int) my_board.x, (int) my_board.y); /*piece_selected.move*/
                // my_board.x_piece_selected = (int) my_board.x;
                // my_board.y_piece_selected = (int) my_board.y;
                   
            }
            else if (StdDrawPlus.isSpacePressed() ){ /*  && my_board.piece_selected != null*/
            	if (my_board.canEndTurn()) {
            		my_board.endTurn();
            	}
            	
            }

                	
              

            StdDrawPlus.show(100);    
            }
            

            // StdDrawPlus.show(10);

        }


}





