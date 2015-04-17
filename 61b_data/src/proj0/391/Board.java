public class Board {

	private Piece[][] all_pieces;
	private boolean game_played = false;
	// player variables:
	private boolean selected;
	private boolean moved_piece = false;
	private Piece selected_piece;
	private Piece piece_just_moved;
	private int selected_x;
	private int selected_y;
	private boolean fire_is_winner;
	private boolean water_is_winner;
	private boolean tie;
	private int number_fire_pieces = 0;
	private int number_water_pieces = 0;
	//player 1 is fire
	private int player1 = 0;
	//
	private boolean winner_exists = false;

	public static void main(String[] args) {
		Board new_board = new Board(true);
		// Piece p1 = new Piece(true, new_board	, 1, 1, "pawn");
  //       Piece p2 = new Piece(false, new_board, 3, 3, "pawn");
		Piece p1 = new Piece(true, new_board, 0, 0, "shield");
		Piece p2 = new Piece(false, new_board, 1, 1, "shield");
		Piece p3 = new Piece(false, new_board, 3, 3, "shield");
		new_board.place(p2, 1,1);
		new_board.place(p3, 3, 3);
		new_board.place(p1, 0, 0);
		while(true) {
			new_board.drawBoard(8);
			if (new_board.winner_exists) {
				new_board.winner();
			}
            if (StdDrawPlus.mousePressed()) {
            	//System.out.println(new_board.number_water_pieces);
            	//System.out.println(new_board.number_fire_pieces);
            	double x = StdDrawPlus.mouseX();
            	double y = StdDrawPlus.mouseY();

            	//double-capture case

            	if (new_board.selected_piece != null && new_board.moved_piece) {
            		new_board.updateSelectCoordinates(new_board.selected_piece);
            		//System.out.println("there is a selected piece.");
            		boolean can_select = new_board.canSelect((int) x, (int) y);
	            	if (can_select) {
	            		new_board.select((int) x, (int) y);
	            		// System.out.println(new_board.selected_piece);
	            		// System.out.println(new_board.selected_x);
	            		// System.out.println(new_board.selected_y);
	            		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	            		StdDrawPlus.filledSquare((int) x + .5, (int) y + .5, .5);
	            	}

            		//boolean valid = new_board.validMove(new_board.selected_x, new_board.selected_y, (int) x, (int) y);
            		// if (!valid) {
            		// 	//System.out.println("not a valid move");
            		// } else {
            		// 	//System.out.println("piece has been moved");
            		// 	new_board.place(new_board.selected_piece,(int) x, (int) y);
            		// }
            	} else if (new_board.selected_piece != null) {
            		boolean valid = new_board.validMove(new_board.selected_x, new_board.selected_y, (int) x, (int) y);
            		if (!valid) {
            			//System.out.println("not a valid move");
            		}
            		if (valid) {
            			//System.out.println("piece has been moved");
            			new_board.select((int) x, (int) y);
            			//new_board.place(new_board.selected_piece, (int) x, (int) y);
  
            		}
            	} else {
	            	boolean can_select = new_board.canSelect((int) x, (int) y);
	            	if (can_select) {
	            		new_board.select((int) x, (int) y);
	            		//System.out.println("piece selected");
	            		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	            		StdDrawPlus.filledSquare((int) x + .5, (int) y + .5, .5);
	            	}
	            }
	        }
	        
            if (StdDrawPlus.isSpacePressed()) {
            	boolean can_end_turn = new_board.canEndTurn();
            	if (can_end_turn) {
            		System.out.println("turn ended");
            		new_board.endTurn();
            	}
            }
                       
            StdDrawPlus.show(10);
        }
	}

	private void drawBoard(int N){
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                for (int k = 0; k <8; k++) {
	                for (int l = 0; l < 8; l++) {
	                	Piece p = all_pieces[k][l];
	                	String type;
	                	if (p == null) {
	                		type = null;
	                	} else if (p.isBomb()) {
							type = "bomb";
						} else if (p.isShield()) {
							type = "shield";
						} else {
							type = "pawn";
						}
						
						if (p != null && (p.side() == 0)) {
							if (p.isKing()) {
								StdDrawPlus.picture(k + 0.5, l + 0.5, "img/" + type + "-fire-crowned.png", 1, 1);
							} else {
								StdDrawPlus.picture(k + 0.5, l + 0.5, "img/" + type + "-fire.png", 1, 1);
							}
						} else if (p != null) {
							if (p.isKing()) {
								StdDrawPlus.picture(k + 0.5, l + 0.5, "img/" + type + "-water-crowned.png", 1, 1);
							} else {
								StdDrawPlus.picture(k + 0.5, l + 0.5, "img/" + type + "-water.png", 1, 1);
							}
						}
					}
				}
			}
		}   
    }

        
    
	
	private void setPieces(String type, boolean isFire, int row, int start){
		for (int i = 0; i < 8; i++) {
			if (start == 0) {
				if (i % 2 == 0) {
					//all_pieces[i][row] = new Piece(isFire, this, i, row, type);
					Piece p = new Piece(isFire, this, i, row, type);
					place(p, i, row);
				}
			} else {
				if (i % 2 == 1 || i % 2 == -1) {
					//all_pieces[i][row] = new Piece(isFire, this, i, row, type);
					Piece p = new Piece(isFire, this, i, row, type);
					place(p, i, row);
				}
			}
		}
	}

	public Board(boolean shouldBeEmpty) {
		all_pieces = new Piece[8][8];
		if (shouldBeEmpty) {
			//initializes an empty board
		} else {
			setPieces("pawn", true, 0, 0);
			setPieces("shield", true, 1, 1);
			setPieces("bomb", true, 2, 0);
			setPieces("pawn", false, 7, 1);
			setPieces("shield", false, 6, 0);
			setPieces("bomb", false, 5, 1);

		}
	}

	public Piece pieceAt(int x, int y) {
		if (x > 7 || y > 7) return null;
		else if (x < 0 || y < 0) return null;
		else                return all_pieces[x][y];
	}

	public boolean canSelect(int x, int y) {

		Piece p = pieceAt(x, y);
		if (p == null) {
			if (selected_piece != null && !moved_piece) {
				updateSelectCoordinates(selected_piece);
				if (validMove(selected_x, selected_y, x, y)) {
					return true;
				}
			} else if (selected_piece != null && selected_piece.hasCaptured()) {
				updateSelectCoordinates(selected_piece);
				if (validMove(selected_x, selected_y, x, y)) {
					return true;
				}
			} else return false;
		} else {
			if (selected_piece != null && !moved_piece) {
				return p.side() == player1;
			}
			if (selected_piece == null) {
				return (p.side() == player1);
			}
			return false;
		}
		return false;
	}
	// 	if (pieceAt(x, y) == null) {
	// 		return selected_piece != null;
	// 	}
	// 	if (all_pieces[x][y] != null) {
	// 		if (selected_piece == null) {
	// 			return (all_pieces[x][y].side() == player1);
	// 		} else return false;
	// 	} else {
	// 		if (selected && !moved_piece && validMove(selected_x, selected_y, x, y)) {
	// 			return true;
	// 		} else if (moved_piece && piece_just_moved.hasCaptured() && validMove(selected_x, selected_y, x, y)) {
	// 			return true;
	// 		} else {
	// 			return false;
	// 		}
	// 	}
	// }

	private boolean checkCaptureMove(int xi, int yi, int xf, int yf, Piece p) {
		int capture_coordinate_x = (xf + xi) >>> 1 ;
		int capture_coordinate_y = (yf + yi) >>> 1;
		Piece capture_piece = all_pieces[capture_coordinate_x][capture_coordinate_y];
		if (capture_piece == null) {
			return false;
		}
		return (p.side() != capture_piece.side()) && (!moved_piece || p.hasCaptured());
	}

	private boolean checkNull(int x, int y) {
		return (all_pieces[x][y] == null);
	}


	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece p = all_pieces[xi][yi];
		if (p.isKing()) {
			if ((Math.abs(xi - xf)) == 1 && (Math.abs(yi - yf) == 1)) {
				return checkNull(xf, yf) && !moved_piece;
			}
			if ((Math.abs(xi - xf)) == 2 && (Math.abs(yi -yf)) == 2) {
				return checkCaptureMove(xi, yi, xf, yf, p) && checkNull(xf, yf);
			} else return false;
		} else if (p.side() == 0) {
			if ((Math.abs(xf - xi)) == 1 && (yf - yi == 1)) {
					return checkNull(xf, yf) && !moved_piece;
				} 
			if (((Math.abs(xf - xi)) == 2) && (yf - yi == 2)) {
				return checkCaptureMove(xi, yi, xf, yf, p) && checkNull(xf, yf);
			} else {
				return false;
			}
		} else {
				if ((Math.abs(xf - xi)) == 1 && (yi - yf == 1)) {
					return checkNull(xf, yf) && !moved_piece;
				}
				if (((Math.abs(xf - xi)) == 2) && (yi - yf == 2)) {
					return checkCaptureMove(xi, yi, xf, yf, p) && checkNull(xf, yf);
				} else {
					return false;
				}
			}
		}
	private void updateSelectCoordinates(Piece p) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (pieceAt(i, j) == p) {
					selected_x = i;
					selected_y = j;
				}
			}
		}
	}

	public void select(int x, int y){
		if (x < 8 && y < 8 && all_pieces[x][y] != null){
			selected_x = x;
			selected_y = y;
			selected_piece = all_pieces[x][y];
			selected = true;
			moved_piece = false;
		}
		if (x < 8 && y < 8 && checkNull(x, y)) {
			if (selected_piece != null) {
				selected_piece.move(x, y);
			}
		}
	}

	private int xPos(Piece p) {
		for (int i = 0; i < 8; i ++) {
			for (int j = 0; j < 8; j++) {
				if (all_pieces[i][j] == p) {
					return i;
				}
			}
		}
		return -1;
	}

	private int yPos(Piece p) {
		for (int i = 0; i < 8; i ++) {
			for (int j = 0; j < 8; j++) {
				if (all_pieces[i][j] == p) {
					return j;
				}
			}
		}
		return -1;
	}

	public void place(Piece p, int x, int y) {
		if (x < 8 && y < 8 && p != null) {
			game_played = true;
			all_pieces[x][y] = p;
			if (selected_piece != null) {
				moved_piece = true;
			}
			if (p != null && p.side() == 0) {
				number_fire_pieces += 1;
			} else if (p != null && p.side() == 1) {
				number_water_pieces += 1;
			}
			// if (!piecesLeft()){
			// //System.out.println("End turn calls winner method");
			// 	winner_exists = true;
			// }
			//piece_just_moved = p;
		}
		// if (x < 8 && y < 8 && p != null) {
		// 	moved_piece = true;
		// 	// int original_x = xPos(p);
		// 	// int original_y = yPos(p);
		// 	p.move(x, y);
		// 	all_pieces[x][y] = p;
		// 	// if (original_x > -1 && original_y > -1) {
		// 	// 	remove(original_x, original_y);
		// 	// }
		// 	piece_just_moved = p;
		// 	if (piece_just_moved != null && !piece_just_moved.hasCaptured()) {
		// 		selected_piece = null;
		// 	}
		// }
	}

	public Piece remove(int x, int y) {
		if (x > 7 || y > 7) {
			System.out.print("index out of bounds");
			return null;
		}
		else {
			Piece p = all_pieces[x][y];
			if (p != null) {
				if (p.side() == 0) {
					number_fire_pieces -= 1;
				} else {
					number_water_pieces -= 1;
				}
				//number_of_pieces = number_of_pieces - 1;
			}
			all_pieces[x][y] = null;
			return p;
		}
	}

	public boolean canEndTurn(){
		return moved_piece;
	}

	private boolean piecesLeft(){
		if (number_water_pieces == 0 && number_fire_pieces == 0) {
			tie = true;
			System.out.println("No more pieces left.");
			return false;
		}
		if (number_water_pieces == 0) {
			fire_is_winner = true;
			System.out.println("No more water pieces.");
			return false;
		}
		if (number_fire_pieces == 0) {
			water_is_winner = true;
			System.out.println("No more fire pieces");
			return false;
		}
		return true;
		// boolean found_fire = false;
		// boolean found_water = false;
		// for (int i = 0; i < 8; i++) {
		// 	for (int j = 0; j < 8; j++) {
		// 		if (pieceAt(i, j) != null) {
		// 			Piece p = pieceAt(i, j);
		// 			if (p.side() == 0) {
		// 				found_fire = true;
		// 			} else {
		// 				found_water = true;
		// 			}
		// 		}
		// 	}
		// }
		// if (!found_fire && !found_water) {
		// 	water_is_winner = true;
		// 	fire_is_winner = true;
		// 	return false;
		// }
		// if (!found_fire) {
		// 	water_is_winner = true;
		// 	return false;
		// } else if (!found_water) {
		// 	fire_is_winner = true;
		// 	return false;
		// } else {
		// 	return true;
		// }

	}

	public void endTurn(){
		selected = false;
		moved_piece = false;
		if (selected_piece != null) {
			selected_piece.doneCapturing();
		}
		selected_piece = null;
		selected_x = 8;
		selected_y = 8;
		player1 = 1 - player1;
		// if (!piecesLeft()){
		// 	//System.out.println("End turn calls winner method");
		// 	winner_exists = true;
		// 	winner();
		// }

	}

	public String winner(){
		if (!piecesLeft()) {
			if (water_is_winner) {
				return "Water";
			} else if (fire_is_winner) {
				return "Fire";
			} else if (tie) {
				System.out.println("Winner method returns tie");
				return "No one";
			}
		} else if (number_fire_pieces > 0 && number_water_pieces > 0) {
				return null;
		} else return "No one";
	return null;
	}
	

}