public class Board{

	private Piece[][] pieces;
	private int N;
	private boolean have_moved;
	private int current_side;
	private Piece selected_piece;
	private int selected_x;
	private int selected_y;
	private String dir;
	
	public Board(boolean shouldBeEmpty) {
		current_side =3;
       	selected_piece = null;
        N = 8;
        pieces = new Piece[N][N];
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
       	for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
            }
       	if (shouldBeEmpty == false) {
       		current_side = 0;
	       	for (int x = 0; x < 8; x++) {
	            for (int y = 0; y < 8; y++) {
					if ((x == 0 && y == 0) || (x == 1 && y == 7) || (x == 2 && y == 0) || (x == 3 && y == 7) ||
						(x == 4 && y == 0) || (x == 5 && y == 7) || (x == 6 && y == 0) || (x == 7 && y == 7)) {

						if (y == 0) {
							place(new Piece(true, this, x, y, "pawn"), x, y);

						} else {
							place(new Piece(false, this, x, y, "pawn"), x, y);
						}

					} 
					if ((x == 0 && y == 2) || (x == 1 && y == 5) || (x == 2 && y == 2) || (x == 3 && y == 5) ||
						(x == 4 && y == 2) || (x == 5 && y == 5) || (x == 6 && y == 2) || (x == 7 && y == 5)) {
						if (y ==2) {
							place(new Piece(true, this, x, y, "bomb"), x, y);
						} else {
							place(new Piece(false, this , x, y, "bomb"), x, y);
						}
					} 
					if ((x == 0 && y == 6) || (x == 1 && y == 1) || (x == 2 && y == 6) || (x == 3 && y == 1) ||
						(x == 4 && y == 6) || (x == 5 && y == 1) || (x == 6 && y == 6) || (x == 7 && y == 1)) {
						if (y ==1) {
							place(new Piece(true, this, x, y, "shield"), x, y);
						} else {
							place(new Piece(false, this, x, y, "shield"), x, y);
						}
					} 

	            }	

			}
		}
	}


	public Piece pieceAt(int x , int y){
		if (x >= N || y >= N) {
			return null;
		} 
		if (pieces[x][y] == null) {
			return null;
		}
		return pieces[x][y];
	}



	public boolean canSelect(int x, int y){
		if ((pieceAt(x, y) != null) && (current_side != 1 - pieceAt(x, y).side()) && (have_moved == false)) {
				return true;
		}
		if (pieceAt(x, y) == null) {
			if ((pieceAt(selected_x, selected_y) != null)  && 
					validMove(selected_x, selected_y, x, y) && (have_moved == false)) {
				if (selected_piece != null) {
					if (selected_piece.hasCaptured() == false) {
						return true;
					}
				}

			}
			if ((selected_piece != null) && (selected_piece.hasCaptured() == true) && validMove(selected_x, selected_y, x, y) &&
				(Math.abs(selected_x - x) == 2)) {
				return true;
			}
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (pieceAt(xf, yf) != null) {
			return false;
		}
		if (pieceAt(xi, yi).isKing() == true) {
			if ((Math.abs(xf - xi) == 1) && (Math.abs(yf - yi) == 1)) {
				return true;
			} else if ((Math.abs(xf - xi) == 2) && (Math.abs(yf - yi) == 2) && (pieceAt((xi + xf) / 2, (yi + yf) / 2) != null)) {
				if (pieceAt((xi + xf) / 2, (yi + yf) / 2).side() + pieceAt(xi, yi).side() == 1) {
					return true;
				}
			}
			return false;			
		} else if (pieceAt(xi, yi).isFire() == true) {
			if ((Math.abs(xf - xi) == 1) && (yf - yi == 1)) {
				return true;
			} else if ((Math.abs(xf - xi) == 2) && (yf - yi == 2) && (pieceAt((xi + xf) / 2, (yi + yf) / 2) != null)) {
				if (pieceAt((xi + xf) / 2, (yi + yf) / 2).side() + pieceAt(xi, yi).side() == 1) {
					return true;
				}
			}
			return false;
		} else {
			if ((Math.abs(xf - xi) == 1) && (yi - yf == 1)) {
				return true;
			} else if ((Math.abs(xf - xi) == 2) && (yi - yf == 2) && (pieceAt((xi + xf) / 2, (yi + yf) / 2) != null)) {
				if (pieceAt((xi + xf) / 2, (yi + yf) / 2).side() + pieceAt(xi, yi).side() == 1) {
					return true;
				}
			}
			return false;
		}

	}

	public void select(int x, int y) {
		if (canSelect(x, y)) {
			if (selected_piece == null && pieceAt(x, y) != null) {
				selected_piece = pieceAt(x, y);
				selected_x = x;
				selected_y = y;
				StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
				place(pieceAt(x, y), x, y);
			}
			if (selected_piece != null && pieceAt(x, y) != null && have_moved == false) {
				if ((selected_x + selected_y) % 2 == 0){
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(selected_x + 0.5, selected_y + 0.5, 0.5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				place(pieceAt(selected_x, selected_y), selected_x, selected_y);
				StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
				place(pieceAt(x, y), x, y);
				selected_piece = pieceAt(x, y);
				selected_x = x;
				selected_y = y;				
			}
			if (pieceAt(x, y) == null) {
				if ((selected_x + selected_y) % 2 == 0){
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(selected_x + 0.5, selected_y + 0.5, 0.5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				pieceAt(selected_x , selected_y).move(x , y);
				have_moved = true;
				if (pieceAt(x , y) != null) {
					StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
					place(pieceAt(x, y), x, y);
					selected_piece = pieceAt(x, y);
					selected_x = x;
					selected_y = y;
				}
			}
		}
	}	

	public void place(Piece p, int x, int y) {
		if (x < N && y < N) {
			pieces[x][y] = p;
			if (p.isBomb() && (p.side() == 0)) {
			dir = "img/bomb-fire.png";
			}
			if (p.isBomb() && (p.side() == 1)) {
			dir = "img/bomb-water.png";
			}
			if (p.isShield() && (p.side() == 0)) {
			dir = "img/shield-fire.png";
			}
			if (p.isShield() && (p.side() == 1)) {
			dir = "img/shield-water.png";
			}
			if (p.isShield() == false && p.isBomb() == false && p.side() == 0) {
				dir = "img/pawn-fire.png";
			}
			if (p.isShield() == false && p.isBomb() == false && p.side() == 1) {
				dir = "img/pawn-water.png";
			}
			StdDrawPlus.picture(x + .5, y + .5, dir, 1, 1);
		}
	}

	public Piece remove(int x, int y) {
		if (x > 8 || y > 8) {
			System.out.print("Out of bounds");
			return null;
		} 
		if (pieceAt(x, y) == null) {
			System.out.print("No pieces here");
			return null;
		}
		Piece temp_piece = pieceAt(x, y); 
		pieces[x][y] = null;
		if ((x + y) % 2 == 0){
			StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		} else {
			StdDrawPlus.setPenColor(StdDrawPlus.RED);
		}
		StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		if (temp_piece.isFire()) {
		} else {
		}
		return temp_piece;
	}

	public boolean canEndTurn() {
		return have_moved;
	}

	public void endTurn() {
		if (current_side == 3) {
			current_side = selected_piece.side();
		}
		current_side = 1 - current_side;
		have_moved = false;
		if (selected_piece != null) {
			selected_piece.doneCapturing();
		}
		if (pieceAt(selected_x, selected_y) != null) {
			if ((selected_x + selected_y) % 2 == 0){
				StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
			} else {
				StdDrawPlus.setPenColor(StdDrawPlus.RED);
			}
			StdDrawPlus.filledSquare(selected_x + 0.5, selected_y + 0.5, 0.5);
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			place(pieceAt(selected_x, selected_y), selected_x, selected_y);
		} else {
			
		}
		selected_piece = null;
		selected_x = 10;
		selected_y = 10;
	}

	public String winner() {
       	int fire_num =0;
		int water_num = 0;
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (pieceAt(i , j) != null) {
            		if (pieceAt(i , j).side() == 0){
            			fire_num += 1;
            		} else {
            			water_num += 1;
            		}
            	}
            }
		}
		if (fire_num == water_num && fire_num == 0) {
			return "No one";
		} else if (fire_num == 0) {
			return "Water";
		} else if (water_num == 0) {
			return "Fire";
		} else {
			return null;
		}
	}

    public static void main(String[] args) {
    	Board game_board = new Board(false) ;
        while(game_board.winner() == null) {         
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                game_board.select((int)x , (int)y);
            }
            if (StdDrawPlus.isSpacePressed()) {
            	if (game_board.canEndTurn()) {
            		game_board.endTurn();
            	}
            }            
            StdDrawPlus.show(100);
        }
    }
}