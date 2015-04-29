public class Board {

	private Piece[][] board_pieces = new Piece[8][8];
	private int turn = 0;
	private Piece selected_piece;
	private int selected_piece_x = -1;
	private int selected_piece_y = -1;
	private boolean selected_piece_moved = false;

    private void drawBoard(int N) {
    	if (selected_piece != null) {
        	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			StdDrawPlus.filledSquare(selected_piece_x + .5, selected_piece_y + .5, .5);
		}
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (i != selected_piece_x || j != selected_piece_y) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
            	}
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				if (this.board_pieces[i][j] != null) {
					if (this.board_pieces[i][j].isFire() == true) {
           				if (this.board_pieces[i][j].isKing() == true) {
           					if (board_pieces[i][j].isBomb() == true) {
           					StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire-crowned.png", 1, 1);
           					}
           					else if (board_pieces[i][j].isShield() == true) {
           					StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire-crowned.png", 1, 1);
           					}
           					else StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire-crowned.png", 1, 1);
           				}
           				else {
           					if (board_pieces[i][j].isBomb() == true) {
           					StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire.png", 1, 1);
           					}
           					else if (board_pieces[i][j].isShield() == true) {
           					StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire.png", 1, 1);
           					}
           					else StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire.png", 1, 1);
           				}
            		}
           			else {
           				if (this.board_pieces[i][j].isKing() == true) {
							if (board_pieces[i][j].isBomb() == true) {
           					StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water-crowned.png", 1, 1);
           					}
           					else if (board_pieces[i][j].isShield() == true) {
           					StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water-crowned.png", 1, 1);
           					}
           					else StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water-crowned.png", 1, 1);
           				}
           					else {
           					if (board_pieces[i][j].isBomb() == true) {
           					StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water.png", 1, 1);
           					}
           					else if (board_pieces[i][j].isShield() == true) {
           					StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water.png", 1, 1);
           					}
           					else StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water.png", 1, 1);
           				}
           			}	
           		}                	
           	}
        }
    }

	public static void main (String args[]) {
		Board B = new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);

        /**Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            B.drawBoard(8);
            if (StdDrawPlus.mousePressed()) {
                double dx = StdDrawPlus.mouseX();
                double dy = StdDrawPlus.mouseY();
                long lx = Math.round(dx - 0.5);
                long ly = Math.round(dy - 0.5);
                int x = (int) lx;
                int y = (int) ly;
                if (B.canSelect(x, y)) {
                B.select(x, y);
            	}
            }  
            if (StdDrawPlus.isSpacePressed()) {
              	B.endturn();
              	B.winner();
            }          
            StdDrawPlus.show(100);
        } 
    }


	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty == false) {

			for (int i = 0; i < 8; i = i + 2) {
				this.board_pieces[i][0] = new Piece(true, this, i, 0, "pawn");
			}

			for (int i = 1; i < 8; i = i + 2) {
				this.board_pieces[i][1] = new Piece(true, this, i, 1, "shield");
			}

			for (int i = 0; i < 8; i = i + 2) {
				this.board_pieces[i][2] = new Piece(true, this, i, 2, "bomb");
			}

			for (int i = 1; i < 8; i = i + 2) {
				this.board_pieces[i][7] = new Piece(false, this, i, 7, "pawn");
			}

			for (int i = 0; i < 8; i = i + 2) {
				this.board_pieces[i][6] = new Piece(false, this, i, 6, "shield");
			}

			for (int i = 1; i < 8; i = i + 2) {
				this.board_pieces[i][5] = new Piece(false, this, i, 5, "bomb");
			}		
		}
	}

	public Piece pieceAt(int x, int y) {
		if (x < 8 && y < 8 && x >= 0 && y >= 0) {
			return board_pieces[x][y];
		}
		return null;
	}

	public boolean canSelect(int x, int y) {

		if (pieceAt(x, y) != null) { //select a piece
			if (turn % 2 == pieceAt(x, y).side()) { //correct player's turn
				if (selected_piece == null || selected_piece_moved == false) { //no selected or selected hasn't moved
					return true;
				}
				return false; //already selected and moved a piece
			}
			return false; //wrong player's turn
		}

		else { //select an empty square

		if (selected_piece != null) { // select empty square after selecting a piece

			if (selected_piece_moved == false) { //no prior moves
				
				if (selected_piece.isKing() == true) { //King
					if (Math.abs(selected_piece_x - x) == 1 && Math.abs(selected_piece_y - y) == 1) { //moves to adjacent square
						return true;
					}
					else if (Math.abs(y - selected_piece_y) == 2 && Math.abs(x - selected_piece_x) == 2) { // hop 2 spaces
						if (pieceAt((x + selected_piece_x) / 2, (y + selected_piece_y) / 2) != null) { //if piece in between
							if (pieceAt((x + selected_piece_x) / 2, (y + selected_piece_y) / 2).side() != selected_piece.side()) { //if piece hopped is opponent
								return true;
							}
							return false; //piece hopped is same color
						}
						return false; //no piece hopped
					}
					else return false; //invalid move by king piece
				}
				
				else if (selected_piece.isFire() == true) { //fire piece
					if (y - selected_piece_y == 1 && Math.abs(selected_piece_x - x) == 1) { //move to empty adjacent spot
						return true;
					}
					else if (y - selected_piece_y == 2 && Math.abs(x - selected_piece_x) == 2) { // hop 2 spaces
						if (pieceAt((x + selected_piece_x) / 2, (y + selected_piece_y) / 2) != null) { //if piece in between
							if (pieceAt((x + selected_piece_x) / 2, (y + selected_piece_y) / 2).isFire() == false) { //if piece hopped is water
								return true;
							}
							return false; //piece hopped is fire
						}
						return false; //no piece hopped
					}
					else return false; //invalid move by uncrowned fire piece
				}
				
				else { //water piece
					if (selected_piece_y - y == 1 && Math.abs(selected_piece_x - x) == 1) { //move to empty adjacent spot
						return true;
					}
					else if (selected_piece_y - y == 2 && Math.abs(x - selected_piece_x) == 2) { // hop 2 spaces
						if (pieceAt((x + selected_piece_x) / 2, (y + selected_piece_y) / 2) != null) { //if piece in between
							if (pieceAt((x + selected_piece_x) / 2, (y + selected_piece_y) / 2).isFire() == true) { //if piece hopped is fire
								return true;
							}
							return false; //piece hopped is water
						}
						return false; //no piece hopped
					}
					else return false; //invalid move by uncrowned fire piece
				}
			}
			
			else if (selected_piece.hasCaptured()) { //piece has already captured
				
				if (selected_piece.isKing() == true) { //King
					if (Math.abs(y - selected_piece_y) == 2 && Math.abs(x - selected_piece_x) == 2) { // hop 2 spaces
						if (pieceAt((x + selected_piece_x) / 2, (y + selected_piece_y) / 2) != null) { //if piece in between
							if (pieceAt((x + selected_piece_x) / 2, (y + selected_piece_y) / 2).side() != selected_piece.side()) { //if piece hopped is opponent
								return true;
							}
							return false; //piece hopped is same color
						}
						return false; //no piece hopped
					}
					else return false; //invalid move by king piece
				}
				
				else if (selected_piece.isFire() == true) { //fire piece
					if (y - selected_piece_y == 2 && Math.abs(x - selected_piece_x) == 2) { // hop 2 spaces
						if (pieceAt((x + selected_piece_x) / 2, (y + selected_piece_y) / 2) != null) { //if piece in between
							if (pieceAt((x + selected_piece_x) / 2, (y + selected_piece_y) / 2).isFire() == false) { //if piece hopped is water
								return true;
							}
							return false; //piece hopped is fire
						}
						return false; //no piece hopped
					}
					else return false; //invalid move by uncrowned fire piece
				}
				
				else { //water piece
					if (selected_piece_y - y == 2 && Math.abs(x - selected_piece_x) == 2) { // hop 2 spaces
						if (pieceAt((x + selected_piece_x) / 2, (y + selected_piece_y) / 2) != null) { //if piece in between
							if (pieceAt((x + selected_piece_x) / 2, (y + selected_piece_y) / 2).isFire() == true) { //if piece hopped is fire
								return true;
							}
							return false; //piece hopped is water
						}
						return false; //no piece hopped
					}
					else return false; //invalid move by uncrowned fire piece
				}
			}
		}
		else return false; //selected empty square with no piece selected
		}
		return false; //invalid move
	}

	public void select(int x, int y) {
		if (pieceAt(x, y) == null && selected_piece != null) {
			selected_piece.move(x, y);
			selected_piece_moved = true;
			selected_piece_y = y;
			selected_piece_x = x;
			}
		else {
			selected_piece = pieceAt(x, y);
			selected_piece_y = y;
			selected_piece_x = x;
			}
		}

	public void place(Piece p, int x, int y) {
		if (x < 8 && y < 8 && x >= 0 && y >= 0) {
			board_pieces[x][y] = p;
		}
	}

	public Piece remove(int x, int y) {
		if (!(x < 8 && y < 8 && x >= 0 && y >= 0)) {
			System.out.println("out of bounds");
			return null;
		}
		if (pieceAt(x, y) == null) {
			System.out.println("no piece in this square");
			return null;
		}
		Piece p = pieceAt(x, y);
		board_pieces[x][y] = null;
		return p;
	}

	public boolean canEndTurn() {
		if (selected_piece != null && selected_piece_moved == true) {
			return true;
		}
		return false;
	}

	public void endturn() {
		if (canEndTurn()) {
			selected_piece.doneCapturing();
			selected_piece = null;
			selected_piece_x = -1;
			selected_piece_y = -1;
			selected_piece_moved = false;
			turn += 1;
		}

	}

	public String winner() {
		int fire = 0;
		int water = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board_pieces[i][j] != null) {
					if (board_pieces[i][j].isFire()) {
						fire += 1;
					}
					else if (board_pieces[i][j].isFire() == false) {
					water += 1;
					} 
				}
			}
		}
		if (fire == 0 && water == 0) {
			return "No one";			
		}
		else if (fire != 0 && water == 0) {
			return "Fire";
		}
		else if (fire == 0 && water != 0) {
			return "Water";
		}
		else return null;
	}
}