import java.lang.Math;
public class Board {


//1. end turn with space pressing.
//3. winners


	private Piece[][] pieces;
	private Piece selected;
	private int turn = 0;
	private boolean moved = false;
	private int selected_x;
	private int selected_y;
	private boolean takeAction;
	private int fire_num = 12;
	private int water_num = 12;
	private boolean captured;

	private int[] find_piece(Piece p, Piece[][] this_board) {
		int[] l = {-1, -1};
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				if (this_board[i][j] == p) {
					l[0]=i;
					l[1]=j;
				}
			}
		}
		return l;
	}
	private void drawBoard() {

        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);

		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				if ((i+j)%2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				else			  StdDrawPlus.setPenColor(StdDrawPlus.RED);
				Piece p = pieces[i][j];
				if (selected != null && p == selected) {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					StdDrawPlus.filledSquare(i + .5, j+ .5, .5);
				}
				else {
					StdDrawPlus.filledSquare(i + .5, j+ .5, .5);
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				}
				
				if (p != null) {
					if (p.isFire()) {
						if (p.isBomb()) 
							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
						else if (p.isShield())
							StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
						else
							StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
					}
					else {
						if (p.isBomb())
							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
						else if (p.isShield())
							StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
						else 
							StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
					} 
				}
			}
		}
	}

	private void init_board() {
		int N = 8;
		pieces = new Piece[N][N];
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				
				if (i%2 == 0) {
					if (j == 0) {
	            		pieces[i][j] = new Piece(true, this, i, j, "pawn");
	            	}
	            	if (j == 2) {
	            		pieces[i][j] = new Piece(true, this, i, j, "bomb");
	            	}
	            	if (j == 6) {
	            		pieces[i][j] = new Piece(false, this, i, j, "shield");
	            	}
	        	}
	        	else {
	        		if (j == 1) {
	        			pieces[i][j] = new Piece(true, this, i, j, "shield");
	        		}
	        		if (j == 5) {
	        			pieces[i][j] = new Piece(false, this, i, j, "bomb");
	        		}
	        		if (j == 7) {
	        			pieces[i][j] = new Piece(false, this, i, j, "pawn");
	        		}
	        	}
			}	
		}
	}

	public Board(boolean shouldBeEmpty) {

        if(shouldBeEmpty) {
        	int N = 8;
			pieces = new Piece[N][N];
        }
 		
		else {
			init_board();
		}
		
	}

	public Piece pieceAt(int x, int y) {
		if (x >= 8 || y >= 8 || x<0 || y<0) {
			return null;
		}
		else {
			return pieces[x][y];
		}

	}

	public boolean canSelect(int x, int y) {
		
		//1. Empty space: when this place has no piece, and if player didnt't select anything.
		//push again;
		if (pieceAt(x,y) == null) {
			if (selected == null) {
				return false;
			}
			//else if he has selected a piece;
			else {
				//During this turn, the player has selected a Piece which hasn’t moved yet
				//and is selecting an empty spot which is a valid move for the previously selected Piece.
				if (moved == false) {
					if (validMove(selected_x,selected_y,x,y)) {
						return true;
					}
				}
				//During this turn, the player has selected a Piece, captured, and has selected another valid capture destination.
				else {
					if(selected.hasCaptured()) {
						if(Math.abs(x-selected_x)!=2 || Math.abs(y-selected_y)!=2) {
							return false;
						}
						else if (validMove(selected_x, selected_y, x, y)) {
							return true;
						}
					
					}
					
				}
			}
		}
		//2. A piece may be selected if it is the corresponding player’s turn and one of the following is true:
		else {
			if (pieces[x][y].side() == turn) {
				if (selected == null && moved == false) {
					return true;
				}
				if (moved == false && selected != null && selected.hasCaptured() == false) {
					if (pieceAt(x,y).side() == turn) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean valid_fire_move(int xi, int yi, int xf, int yf) {
		if ((xf == xi + 1) && (yf == yi + 1)) {
			return true;
		}
		if ((xf == xi - 1) && (yf == yi + 1)) {
			return true;
		}
		else {
			return false;
		}
	}

	private boolean valid_fire_capture_move(int xi, int yi, int xf, int yf) {
		if ((xf == xi + 2) && (yf == yi + 2) && (pieceAt(xi+1, yi+1)!= null) && (pieceAt(xi+1, yi+1).isFire() == false)) {
			return true;
		}
		if ((xf == xi - 2) && (yf == yi + 2) && (pieceAt(xi-1, yi+1)!= null) && (pieceAt(xi-1, yi+1).isFire() == false)) {
			return true;
		}
		else {
			return false;
		}
	}

	private boolean valid_fire_king_capture_move(int xi, int yi, int xf, int yf) {
		if ((xf == xi + 2) && (yf == yi - 2) && (pieceAt(xi+1, yi-1)!= null) && (pieceAt(xi+1, yi-1).isFire() == false)) {
			return true;
		}
		if ((xf == xi - 2) && (yf == yi - 2) && (pieceAt(xi-1, yi-1)!= null) && (pieceAt(xi-1, yi-1).isFire() == false)) {
			return true;
		}
		else {
			return valid_fire_capture_move(xi, yi, xf, yf);
		}
	}

	private boolean valid_water_move(int xi, int yi, int xf, int yf) {
		if ((xf == xi + 1) && (yf == yi - 1)) {
			return true;
		}
		if ((xf == xi - 1) && (yf == yi - 1)) {
			return true;
		}
		else {
			return false;
		}
	}

	private boolean valid_water_capture_move(int xi, int yi, int xf, int yf) {
		if ((xf == xi + 2) && (yf == yi - 2) && (pieceAt(xi+1, yi-1)!= null) && (pieceAt(xi+1, yi-1).isFire())) {
			return true;
		}
		if ((xf == xi - 2) && (yf == yi - 2) && (pieceAt(xi-1, yi-1)!= null) && (pieceAt(xi-1, yi-1).isFire())) {
			return true;
		}
		else {
			return false;
		}
	}

	private boolean valid_water_king_capture_move(int xi, int yi, int xf, int yf) {
		if ((xf == xi + 2) && (yf == yi + 2) && (pieceAt(xi+1, yi+1)!= null) && (pieceAt(xi+1, yi+1).isFire())) {
			return true;
		}
		if ((xf == xi - 2) && (yf == yi + 2) && (pieceAt(xi-1, yi+1)!= null) && (pieceAt(xi-1, yi+1).isFire())) {
			return true;
		}
		else {
			return valid_water_capture_move(xi, yi, xf, yf);
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		
		Piece pointer = pieceAt(xi, yi);
		if(xf >= 8 || yf >= 8 || xf < 0 || yf < 0) {
			return false;
		}
		if (pieceAt(xf, yf) != null) {
			return false;
		}
		if (pointer == null) {
			return false;
		}
		else if (pointer.isFire()) {
			if (pointer.isKing()) {
				return valid_fire_move(xi, yi, xf, yf) || valid_water_move(xi,yi,xf,yf) || valid_fire_king_capture_move(xi, yi, xf,yf);
			}
			else {
				return valid_fire_move(xi, yi, xf, yf) || valid_fire_capture_move(xi, yi, xf, yf);
			}
		}
		else {
			if (pointer.isKing()) {
				return valid_water_move(xi, yi, xf, yf) || valid_fire_move(xi,yi,xf,yf) || valid_water_king_capture_move(xi,yi,xf,yf);
			}
			else {
				return valid_water_move(xi, yi, xf, yf) || valid_water_capture_move(xi, yi, xf, yf);
			}			
		}
	}

	public void select(int x, int y) {
		if(pieceAt(x,y) != null) {
			if (!takeAction) {
				selected_x = x;
				selected_y = y;
				selected = pieceAt(x,y);
				takeAction = true;
			} else if (!moved && selected != null) {
				selected_x = x;
				selected_y = y;
				selected = pieceAt(x,y);
				takeAction = false;
			}
		} else if (takeAction) {
			selected_x = x;
			selected_y = y;
			selected.move(x,y);
			moved = true; 	
			if (!selected.hasCaptured())
				takeAction = false;
			if (selected.hasCaptured())
				captured = true;
		}
	}



	/*
	void place(Piece p, int x, int y) - Places p at (x, y). If (x, y) is out of bounds or if p is null, does nothing.
 	If p already exists in the current Board, first removes it from its initial position. 
 	If another piece already exists at (x, y),p will replace that piece. 
 	(This method is potentially useful for creating specific test circumstances.)
	*/
	public void place(Piece p, int x, int y) {
		if (x >= 8 || y >= 8 || p == null) {
			return;
		}
		else {
			int[] l = find_piece(p, pieces);
			if (l[0] != -1) {
				pieces[x][y] = remove(l[0],l[1]);
				if(pieces[x][y].isFire())
					fire_num += 1;
				else
					water_num += 1;
			}
			else {
				pieces[x][y] = p;
			}
		}
	}

	//Piece remove(int x, int y) - Executes a remove. Returns the piece that was removed. 
	//If the input (x, y) is out of bounds, returns null and prints an appropriate message. 
	//If there is no piece at (x, y), returns null and prints an appropriate message.
	public Piece remove(int x, int y) {
		if(x >= 8 || y >= 8) {
			System.out.println("index out of bounds. <remove-method>");
			return null;
		}

		Piece p = pieceAt(x, y);

		if (p == null) {
			System.out.println("null piece found. <remove-method>");
			return null;
		}

		if (p.isFire()) {
			fire_num -= 1;
			System.out.println(fire_num);
		} 
		else {
			water_num -= 1;
		}

		pieces[x][y] = null;
		return p;
	}

	//Returns whether or not the the current player can end their turn.
	//To be able to end a turn, a piece must have moved or performed a capture.
	public boolean canEndTurn() {
		if (moved || captured) {
			return true; 
		}
		return false;
	}

	
	private void switch_player() {
		if(turn == 0) {
			turn = 1;
		}
		else {
			turn = 0;
		}

	}

	//Called at the end of each turn. Handles switching of players and anything else that should happen at the end of a turen. 
	public void endTurn() {
		switch_player();
		selected.doneCapturing();
		selected = null;
		System.out.println("that's it ");
		moved = false;
		takeAction = false;
		captured = false;
	}

	public String winner() {
		/*if (fire_num == 0 && water_num == 0) {
			return "No one";
		}
		else if (fire_num == 0) {
			return "water";
		}
		else if (water_num == 0) {
			return "fire";
		}
		else {
			return null;
		} */
		int fire = 0;
		int water = 0;
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				if (pieces[i][j] != null && pieces[i][j].isFire()) {
					fire += 1;
				}
				if (pieces[i][j] != null && !pieces[i][j].isFire()) {
					water += 1;
				}
			}
		}
		if(fire > 0 && water == 0)
			return "Fire";
		if(water > 0 && fire == 0)
			return "Water";
		if(fire == 0 && water == 0)
			return "No one";
		else
			return null;

	}
	
	public static void main(String args[]) {        
		Board b = new Board(false);

		while(true) {
			b.drawBoard();
			//if (b.winner() != null)
			//	System.out.println(b.winner());
			if (!b.takeAction) {
				if (StdDrawPlus.mousePressed()) {
                	int x = (int)StdDrawPlus.mouseX();
                	int y = (int)StdDrawPlus.mouseY();
                	if(b.canSelect(x,y)) {
                		b.select(x,y);
                	}
            	}
        	}	
        	else {
        		if (StdDrawPlus.mousePressed() && b.selected != null) {
        			System.out.println("10086");
	            	int x = (int)StdDrawPlus.mouseX();
	                int y = (int)StdDrawPlus.mouseY();
	                if(b.canSelect(x,y)) {
	                	b.select(x,y);			
	                }
            	}
        	}
        	if(StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
            	b.endTurn();
            }
        	StdDrawPlus.show(100);
		}
		
        //while(true) {
        //	drawBoard(N);
        //	StdDrawPlus.show(100);
        //}
	}
}