public class Board {

	private Piece[][] piece_array;
	private int player= 1; 
	private boolean has_selected_1= false;
	private boolean has_selected_0= false; 
	private boolean hasselected; 
	private int xval; 
	private int yval; 
	private boolean hasmoved= false; 
	private boolean has_moved_0= false; 
	private boolean has_moved_1= false; 
	private boolean prevselected= false; 
	private Piece prevselectedpiece= null; 
	private boolean has_captured_1= false; 
	private boolean has_captured_0= false; 
	private boolean hascaptured;
	private Piece selectedpiece= null;
	private Piece prepped_piece_4move= null;  

	private static boolean[][] pieces; 


	public static void main(String [] args) {
		int scale= 8;
		StdDrawPlus.setXscale(0, scale);
		StdDrawPlus.setYscale(0, scale);
		pieces= new boolean[scale][scale]; 
		Board board= new Board(false); 
		
		while (true) {
			board.updateBoard();
			if (StdDrawPlus.mousePressed()) {
				double x= StdDrawPlus.mouseX(); 
				double y= StdDrawPlus.mouseY();
				if (board.canSelect((int) x, (int) y)) {
					board.select((int) x, (int) y); 

				}
			}
			if (StdDrawPlus.isSpacePressed() ) {
				if (board.canEndTurn()) {
					board.endTurn(); 
				}
			}
			StdDrawPlus.show(100);
		}
		
	}

	private void updateBoard() {
		this.drawBoard(8); 
	}

	public Board(boolean shouldBeEmpty) { // Constructor
		piece_array= new Piece[8][8]; 
		if (shouldBeEmpty== false) {
			// Default configuration of the board
			for(int i=0; i<8; i= i+1) { // Rows
				for(int j=0; j<8; j= j+1) { // Columns 
					if (i==0 && j%2==0) {
						piece_array[i][j]= new Piece(true, this, j, i, "pawn"); 
					}
					else if (i==1 && j%2==1) {
						piece_array[i][j]= new Piece(true, this, j, i, "shield"); 
					}
					else if (i==2 && j%2==0) {
						piece_array[i][j]= new Piece(true, this, j, i, "bomb"); 
					}
					else if (i==5 && j%2==1) {
						piece_array[i][j]= new Piece(false, this, j, i, "bomb");
					}
					else if (i==6 && j%2==0) {
						piece_array[i][j]= new Piece(false, this, j, i, "shield");
					}
					else if (i==7 && j%2==1) {
						piece_array[i][j]= new Piece(false, this, j, i, "pawn"); 
					}
					else {
						piece_array[i][j]= null; 
					}	
					
				}
			}
		}
		else {
			return; 
		}
	}

	private void drawBoard(int N) {
		for (int i=0; i<N; i= i+1) { // Rows
			for (int j=0; j<N; j= j+1) { // Columns
				// Decide which color the square will be
				if ((i%2==0 && j%2==0) || (i%2!=0 && j%2!=0)) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				}
				else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				// Fill in the Square color
				StdDrawPlus.filledSquare(j+0.5, i+0.5, 0.5);
			}
		}
		for (int i=0; i<N; i= i+1) {
			for (int j=0; j<N; j= j+1) {
				if (piece_array[i][j]==null) {
						continue; 
				}
				else if (piece_array[i][j].isFire() && !piece_array[i][j].isBomb() && !piece_array[i][j].isShield()) {
					if (piece_array[i][j].isKing()) {
						StdDrawPlus.picture(j+0.5, i+0.5, "img/pawn-fire-crowned.png", 1, 1); 
					}
					else {
						StdDrawPlus.picture(j+0.5, i+0.5, "img/pawn-fire.png", 1, 1); 
					}
				}
				else if (piece_array[i][j].isFire() && piece_array[i][j].isShield()) {
					if (piece_array[i][j].isKing()) {
						StdDrawPlus.picture(j+0.5, i+0.5, "img/shield-fire-crowned.png", 1, 1); 
					}
					else {
						StdDrawPlus.picture(j+0.5, i+0.5, "img/shield-fire.png", 1, 1);
					}
				}
				else if (piece_array[i][j].isFire() && piece_array[i][j].isBomb()) {
					if (piece_array[i][j].isKing()) {
						StdDrawPlus.picture(j+0.5, i+0.5, "img/bomb-fire-crowned.png", 1, 1);
					}
					else {
						StdDrawPlus.picture(j+0.5, i+0.5, "img/bomb-fire.png", 1, 1);
					}
				}
				else if (piece_array[i][j].isFire()== false && piece_array[i][j].isBomb()) {
					if (piece_array[i][j].isKing()) {
						StdDrawPlus.picture(j+0.5, i+0.5, "img/bomb-water-crowned.png", 1, 1);
					}
					else {
						StdDrawPlus.picture(j+0.5, i+0.5, "img/bomb-water.png", 1, 1);
					}
				}
				else if (piece_array[i][j].isFire()== false && piece_array[i][j].isShield()) {
					if (piece_array[i][j].isKing()) {
						StdDrawPlus.picture(j+0.5, i+0.5, "img/shield-water-crowned.png", 1, 1);
					}
					else {
						StdDrawPlus.picture(j+0.5, i+0.5, "img/shield-water.png", 1, 1);
					}
				}
				else if (piece_array[i][j].isFire()== false && piece_array[i][j].isBomb()==false && piece_array[i][j].isShield()==false) {
					if (piece_array[i][j].isKing()) {
						StdDrawPlus.picture(j+0.5, i+0.5, "img/pawn-water-crowned.png", 1, 1); 
					}
					else {
						StdDrawPlus.picture(j+0.5, i+0.5, "img/pawn-water.png", 1, 1);
					}
				}
			}
		}
	}

	public Piece pieceAt(int x, int y) {
		if (x>7 || y>7 || x<0 || y<0 || piece_array[y][x]==null) {
			return null; 
		}
		else {
			return piece_array[y][x];
		}
	}

	public boolean canSelect(int x, int y) {
		Piece piece= piece_array[y][x];
		if (player==0) {
			hasselected= has_selected_0;
			hascaptured= has_captured_0;
			hasmoved= has_moved_0; 
		}
		else if (player==1) {
			hasselected= has_selected_1;
			hascaptured= has_captured_1; 
			hasmoved= has_moved_1; 
		}
		if (piece!=null) { // Square w/a piece
			// Side() does 0 for fire, while I made it 1, so have to flip
			if (piece.side() == player) {
				return false; 
			}
			if (piece.side()!= player && !hasselected) { 
				prevselectedpiece= piece; 
				return true; 
			}
			else if (piece.side()!= player && hasselected && !hasmoved) {
				prevselectedpiece= piece; 
				hasselected= true;
				return true; 
			}
			return false;
		}
		else { // Trying to select an empty square
			if (prevselectedpiece== null) {
				return false; 
			}
			if (prevselectedpiece!=null) {
				int xi= getXPos(prevselectedpiece); 
				int yi= getYPos(prevselectedpiece); 
				if (hascaptured && hasselected) {
					hascaptured= true; 
					if (player==0) {
						has_captured_0= true;  
					}
					else if (player==1) {
						has_captured_1= true; 
					}
					return true; 
				}
				if (piece==null && validMove(xi, yi, x, y)) {
					hascaptured= true; 
					if (player==0) {
						has_captured_0= true; 
					}
					else if (player==1) { 
						has_captured_1= true; 
					}
					return true; 
				}
			}
			return false; 
		}
	}


	private boolean validMove(int xi, int yi, int xf, int yf) {
		/* Make sure not trying to move it out of bounds */
		if (xf >=8 || yf>=8) {
			return false;
		}
		
		// Single Step
		if (Math.abs(xf-xi)==1 && Math.abs((yf-yi))==1) {
			if (player==0) {
				hasmoved= has_moved_0; 
			}
			else if (player==1) {
				hasmoved= has_moved_1; 
			}
			if (hasmoved) {
				return false;
			}
			return single_step(xi, yi, xf, yf); 
		}
		/* Capture */
		if (Math.abs(xf-xi)==2 && Math.abs(yf-yi)==2) {
			return single_capture(xi, yi, xf, yf);
		}
		else {
			return false; 
		}		
	}

	public void select(int x, int y) {
		selectedpiece= pieceAt(x, y); 
		if (selectedpiece==null && prepped_piece_4move==null) {
			return; 
		}
		else if (selectedpiece!=null) { // Square with a piece
			prepped_piece_4move= selectedpiece; 
			int xpos= getXPos(selectedpiece); 
			int ypos= getYPos(selectedpiece);
		}
		else if (selectedpiece==null && prepped_piece_4move!=null) {
			// Going to move the prepped piece to the empty square
			int xpos= getXPos(prepped_piece_4move); 
			int ypos= getYPos(prepped_piece_4move);
			prepped_piece_4move.move(x, y);
			remove(xpos, ypos); 
			hasmoved= true; 
			if (player==0) {
				has_moved_0= true; 
			}
			else if (player==1) {
				has_moved_1= true; 
			}
			if (prepped_piece_4move.hasCaptured()) {
				hascaptured= true; 
				if (player==0) {
					has_captured_0= true; 
				}
				else if (player==1) {
					has_captured_1= true; 
				}
			}
		}
		
		if (player==0) {
			has_selected_0= true; 

		}
		else if (player==1) {
			has_selected_1= true;
		}
		hasselected= true;
	}

	public void place(Piece p, int x, int y) {
		if (x>7 || y>7 || x<0 || y<0 || p==null) {
			return;
		}
		else {
			piece_array[y][x]= p; 
		}

	}

	public Piece remove(int x, int y) {
		if (x>7 || y>7) {
			System.out.println("Input (" + x + ", " + y + ") is out of bounds.");
			return null; 
		}
		else if (piece_array[y][x]== null) {
			System.out.println("There is no piece at (" + x + ", " + y + ")."); 
			return null; 
		}
		else {
			Piece removed_piece= piece_array[y][x]; 
			piece_array[y][x]= null; 
			return removed_piece; 
		}
	}

	

	public boolean canEndTurn() {
		if (hasmoved || hascaptured) {
			return true; 
		}
		return false; 
	}
	

	public void endTurn() {
		if (player==1) {
			player= 0; 
		}
		else if (player==0) {
			player=1; 
		}
		has_selected_1= false; 
		has_selected_0= false; 
		hasselected= false; 
		hasmoved= false; 
		has_moved_1= false; 
		has_moved_0= false; 
		prevselected= false; 
		prevselectedpiece= null; 
		hascaptured= false; 
		has_captured_1= false;
		has_captured_0= false; 
		selectedpiece= null; 
		prepped_piece_4move.doneCapturing();
		prepped_piece_4move= null; 
		// Basically I'm just setting them all back to default, for a clean slate for the next turn 
		
	}

	private int getXPos(Piece p) {
		for(int i=0; i<8; i= i+1) { // Rows
			for(int j=0; j<8; j= j+1) { // Columns
				if (piece_array[i][j]==p) {
					xval= j; 
				}
			}
		}
		return xval; 
	}

	private int getYPos(Piece p) {
		for(int i=0; i<8; i= i+1) {
			for(int j=0; j<8; j= j+1) {
				if (piece_array[i][j]==p) {
					yval= i;
				}
			}
		}
		return yval; 
	}

	private boolean single_step(int xi, int yi, int xf, int yf) {
		Piece curr_piece= pieceAt(xi, yi);
		if (curr_piece.isKing()==false) {
			if (curr_piece.isFire()) { // Fire
				if (Math.abs(xf-xi)==1 && (yf-yi)==1 && pieceAt(xf, yf)==null) {
					return true;
				}
				else {
					return false; 
				}
			}
			else { // Water
				if (Math.abs(xf-xi)==1 && (yf-yi)== -1 && pieceAt(xf, yf)==null) {
					return true; 
				}
				else {
					return false;
				}
			}
		}
		else { // Is a king- can move forward & back
			if (pieceAt(xf, yf)==null) {
				return true; 
			}
			return false; 
		}
	}

	private boolean single_capture(int xi, int yi, int xf, int yf) {
		Piece curr_piece= pieceAt(xi, yi);
		if (curr_piece.isKing()==false) {
			if (curr_piece.isFire()) { // Fire 
				if ((yf-yi)==2 && !pieceAt((xi+xf)/2, (yi+yf)/2).isFire() && pieceAt(xf, yf)==null) {
						hascaptured= true; 
						return true;
					}
					return false;
				}
			else { // Water
				if (Math.abs(xf-xi)==2 && (yf-yi)== -2 && pieceAt((xi+xf)/2, yi-1).isFire() && pieceAt(xf, yf)==null) {
					hascaptured= true; 
					return true;
				}
				return false; 
			}
		}
		else { // King can move forward & backward
			if (pieceAt(((xi+xf)/2), ((yi+yf)/2)).isFire() != curr_piece.isFire() && pieceAt(xf, yf)==null) {
				hascaptured= true;
				return true;
			}
			return false;
		}
	}

	public String winner() {
		int fire_count= 0; 
		int water_count= 0;
		for (int i=0; i<8; i= i+1) { // Rows
			for (int j=0; j<8; j= j+1) { // Columns
				Piece curr_piece= piece_array[j][i];
				if (curr_piece==null) {
					continue;
				}
				else if (curr_piece.isFire()==true) {
					fire_count= fire_count +1;
				}
				else if (curr_piece.isFire()==false) {
					water_count= water_count+1; 
				}
			}
		}
		if (fire_count==0 && water_count==0) {
			return "No one"; 
		}

		else if (fire_count>0 && water_count==0) {
			return "Fire"; 
		}

		else if (water_count>0 && fire_count==0) {
			return "Water";
		}

		else {
			return null; 
		}
	}
	
}