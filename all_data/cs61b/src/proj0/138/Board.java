public class Board{
	/*makes a new array of booleans to determine whether there is a piece at a 
	certain position*/
	//private  boolean[][] is_there_a_piece = new boolean[8][8];
	//makes an array of pieces to determine which piece is at which position//
	private Piece[][] pieces = new Piece[8][8];
	// An integer whose parity determines whose turn it is
	private int whose_turn = 0;
	// Determines whether the board should be empty or not when creating it
	private boolean shouldBeEmpty;
	// Determines whether or not a piece is selected to move
	private Piece selected;
	// integer which keeps track of the x coordinate of the selected piece
	private int selected_x;
	// integer which keeps track of the y coordinate of the selected piece
	private int selected_y;
	// Boolean which determines whether or not a move has been made
	private boolean has_moved;

	private boolean piece_is_selected;
    // Determines whether or not an empty square has been selected
	private boolean square_is_selected;


	public Board(boolean shouldBeEmpty){
		//shouldBeEmpty = true;
            if(! shouldBeEmpty){
			for (int m = 0; m < 8; m++){
			/*Loops over and creates the initial board setup and updating 
			is_there_a_piece and pieces accordingly*/
			for (int j = 0; j < 8; j++){
				if((j == 0) && ((m % 2) == 0)){
					

					pieces[m][j] = new Piece(true, this, m, j, "pawn");
					}
				if((j == 1) && ((m % 2) == 1)){

					 pieces[m][j] = new Piece(true, this, m, j, "shield");	
					}
				if((j == 2) && ((m % 2) == 0)){

					 pieces[m][j] = new Piece(true, this, m, j, "bomb");
					}
				if((j == 5) && ((m % 2) == 1)){

					 pieces[m][j] = new Piece(false, this, m, j, "bomb");
					}
				if((j == 6) && ((m % 2) == 0)){

					 pieces[m][j] = new Piece(false, this, m, j, "shield");

					}
				if((j == 7) && ((m % 2) == 1)){

					 pieces[m][j] = new Piece(false, this, m, j, "pawn");
					}
				
				}
			}
			
		}
}
	
private void drawBoard(){
   for (int i = 0; i < 8; i++){
				for (int j = 0; j < 8; j++){
					if ((i + j) % 2 == 0){
						StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					}
					else{
						StdDrawPlus.setPenColor(StdDrawPlus.RED);
						
					}
					StdDrawPlus.filledSquare(i + .5, j + .5, .5);
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				}
		}
for(int i = 0; i<8; i++){
	for (int j = 0; j < 8; j++){
		if(pieces[i][j] != null){
			if(pieces[i][j].isFire()){
				if(pieces[i][j].isKing()){
					if(pieces[i][j].isShield()){
						StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
					}
					else if (pieces[i][j].isBomb()){
						StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
					}
					else{
						StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
					}
				}
				else{
					if(pieces[i][j].isShield()){
						StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
					}
					else if (pieces[i][j].isBomb()){
						StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
					}
					else{
						StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
					}
				}
			}
			else{
				if(pieces[i][j].isKing()){
					if(pieces[i][j].isShield()){
						StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
					}
					else if (pieces[i][j].isBomb()){
						StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
					}
					else{
						StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
					}
				}
				else{
					if(pieces[i][j].isShield()){
						StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
					}
					else if (pieces[i][j].isBomb()){
						StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
					}
					else{
						StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
					}
				}

			}
		}

	}
}
}

	
	
public Piece remove(int x, int y){
	/*Looks at the entry in position [x][y] in is_there_a_piece to determine 
	whether a remove is valid, if it is then I determine whether the square in
	question is red or white and finds the entry in [x][y] in pieces
	and updates both is_there_a_piece and pieces accordingly */ 	
    

	if(pieceAt(x,y) == null){
		System.out.println("There is no piece at this position");

		return null;
	}
	else{
		Piece m = pieces[x][y];
			pieces[x][y] = null;
			return m;


	}
}


public Piece pieceAt(int x, int y){
	if(x > 7 || x < 0 || y > 7 || y < 0){
		return null;
	}
	if(pieces[x][y] != null){
		return pieces[x][y];
	}
	else{
      return null;
	}
	

	}
public void place(Piece p, int x, int y){
	/* first checks to make sure that x and y are within the boundaries
	of the board. Then checks to see if there is already a piece at x,y
	if so the piece existing there is removed.
	Then loops over every piece to see if it is already on
	the board. If so it removes the piece from its current position and
	draws it into the position of x,y. Which is done by checking whether
	it is a fire piece and then checking the type and pulling up the 
	corresponding image and uses the picture method the StdDraw library*/
	if((x >= 8) || (y >= 8)){
		return;
	}
	else{
		if(pieces[x][y] != null){
			remove(x, y);
		}
		for(int i=0; i < 8; i++){
			for(int j=0; j < 8; j++){
				if(pieces[i][j] == p){
					remove(i, j);
				}				
			}
			}
			pieces[x][y] = p;
			//is_there_a_piece[x][y] = true;

		}


	}

	private boolean validCapture(int xi, int yi, int xf, int yf){
	if((xf >= 8) || (yf >= 8) || (yf < 0) || (xf < 0)){
		return false;
	}
	else if(pieces[xf][yf] != null){
		return false;
	}

	else if(pieces[xi][yi] == null){
		return false;
	}
	else{
		int x_mid = ((xi + xf) / 2);
		int y_mid = ((yi + yf) / 2);
		Piece m = pieces[xi][yi];

		if(m.isFire()){
			if(m.isKing()){
				// Checks to see if it is a valid fire king capture
				if((Math.abs(xf - xi) == 2) && (Math.abs(yf - yi) == 2) && (pieceAt(x_mid, y_mid)  != null) && (!(pieceAt(x_mid, y_mid).isFire()))){
					return true;
				}
				else{
					return false;
				}
			}
			else{
				// Checks to see if it is a valid normal water capture
				if((Math.abs(xf - xi) == 2) && ((yf - 2) == yi) && (pieceAt(x_mid, y_mid) != null) && (!pieceAt(x_mid, y_mid).isFire())){
					return true;
				}
				else{
					return false;
				}
			}
		}
		else{
			if(m.isKing()){
				// Checks to see if it is a valid water king capture
				if((Math.abs(xf - xi) == 2) && (Math.abs(yf - yi) == 2) && (pieceAt(x_mid, y_mid) != null) && (pieceAt(x_mid, y_mid).isFire())){
					return true;
				}
				else{
					return false;
				}
			}
			else{
				// Checks to see if it is a valid normal water capture
				if((Math.abs(xf - xi) == 2) && ((yi - 2) == yf) && (pieceAt(x_mid, y_mid) != null) && (pieceAt(x_mid, y_mid).isFire())){
					return true;
				}
				else{
					return false;
				}

			}

		}
	}
}
	private boolean validMove(int xi, int yi, int xf, int yf){
		if((xf >= 8) || (yf >= 8) || (yf < 0) || (xf < 0)){
		return false;
	}
	else if(pieces[xf][yf] != null){
		return false;
	}

	else if(pieces[xi][yi] == null){
		return false;
	}
	else{
		Piece m = pieces[xi][yi];
		if(has_moved){
			if(!m.hasCaptured()){
				return false;
			}
			else{
				if(validCapture(xi, yi, xf, yf)){
					return true;
				}
				else{
					return false;
				}
			}
		}
		else{
			if(m.isKing()){
				// Checks to see if it is a valid normal king move
				if((Math.abs(yf - yi) == 1) && (Math.abs(xf - xi) == 1)){
					return true;
				}
				// Checks to see if it is a valid king capture
				else if(validCapture(xi, yi, xf, yf)){
					return true;
				}

				else{
					return false;
				}
			}
			else if(m.isFire()){
				// Checks to see if it is a valid normal fire move
				if((Math.abs(xf - xi) == 1) && ((yf - 1) == yi)){
					return true;
				}
				// Checks to see if it is a valid fire capture
				else if(validCapture(xi, yi, xf, yf)){
					return true;
				}
				else{
					return false;
				}
			}
			else{
				// Checks to see if it is a valid normal water move
				if((Math.abs(xf - xi) == 1) && ((yi - 1) == yf)){
					return true;
				}
				// Checks to see if it is a valid water capture
				else if(validCapture(xi, yi, xf, yf)){
					return true;
				}
				else{
					return false;
				}

			}
		}
	}

	}
   
 public boolean canEndTurn(){
 	if(has_moved){
 		return true;
 	}
 	return false;

 } 
	

public void endTurn(){
	/* increments whose turn by 1,
	makes sure no piece is selected,
	sets has_moved to false and resets
	the selected_x and selected_y to 0
	and makes sure that every piece is done capturing
	*/
	whose_turn++;
	selected = null;
	has_moved = false;
	piece_is_selected = false;
	square_is_selected = false;
	selected_x = 0;
	selected_y = 0;
	for(int k=0; k<8; k++){
		for(int a=0; a<8; a++){
			if(pieces[a][k] != null){
				pieces[a][k].doneCapturing();
			}
		}
	}
}

public boolean canSelect(int x, int y){
	/* First determines if a piece has been selected. If not
	then look and see if there is a piece at (x, y)
	then see whose turn it is and make sure the right player
	is selecting. Then if a piece has been selected looks to see
	if a valid move is made */
    
    // Checks to see if a piece has been selected
	if(has_moved){
		if(!selected.hasCaptured()){
			return false;
		}
		else if(validMove(selected_x, selected_y, x, y)){
			return true;
		}
		else{
			return false;
		}
	}
	if(!(piece_is_selected)){
		// Checks to see if our spot has a piece
		if(pieces[x][y] == null){
			return false;
		}
		else{
			/* Checks to make sure that we are selecting a fire piece
			during an even numbered turn or a water piece during
			an odd numbered turn */
			if((pieces[x][y].isFire() && ((whose_turn % 2) == 0)) || ((!pieces[x][y].isFire()) && ((whose_turn % 2) == 1))){
				return true;
			}
			else{
				return false;
			}
		}
	}
	else{

		
		// Checks to see if the piece we want to select is a valid piece
		if(pieceAt(x,y) != null){
		if((pieceAt(x,y).isFire() && ((whose_turn % 2) == 0)) || ((!pieces[x][y].isFire()) && ((whose_turn % 2) == 1))){
				return true;
		}
		else{
			return false;
		}
	}
		/* Checks to see if it is legal to move our piece
		to our intended spot */
		else if(validMove(selected_x, selected_y, x, y) && (pieces[x][y] == null)){
			return true;
		}
		else{
			return false;
		}
	}
	}

public void select(int x, int y){
	/* Checks to make sure we can
	select our spot in question */
		/* If a piece has not already been selected
		I get the x and y coordinates of the piece,
		the piece itself and I say that I have 
		selected a piece */
		if(!piece_is_selected){
			selected = pieces[x][y];
             selected_x = x;
            selected_y = y;
             piece_is_selected = true;
    }  
    /* I check to see if I am selecting an empty square */  
    else{
      if(pieces[x][y] == null){
	  square_is_selected = true;
	  selected.move(x, y);
	  if(pieces[selected_x][selected_y] != null){
	  pieces[x][y] = selected;
	  pieces[selected_x][selected_y] = null;
	}

	  selected_x = x;
	  selected_y = y;
	  square_is_selected = false;
	  has_moved = true;
     }
     else{
     	selected = pieces[x][y];
     	selected_x = x;
     	selected_y = y;
     } 
 }

 

	}
       



public String winner(){
	/*Loops over the board and checks each piece and counts
	the number of water pieces and fire pieces. If either is 
	nonzero there is no winner, otherwise the one that has a non
	zero number is the winner */
	int water = 0;
	int fire = 0;
	for(int i=0;i<8;i++){
		for(int j=0;j<8;j++){
           if(pieceAt(i,j) != null){
           	if(pieces[i][j].isFire()){
           		fire++;
           	}
           	else{
           		water++;
           	}
           }
		}

	}
	if((water == 0) && (fire != 0)){
			return "Fire";
		}
		if((water != 0) && (fire == 0)){
			return "Water";
		}
		if((water > 0) && (fire > 0)){
			return null;
		}
		else{
			return "No one";
		}
}

public static void main(String args[]){
		StdDrawPlus.setXscale(0, 8);
		StdDrawPlus.setYscale(0, 8);
    Board c = new Board(false);
		while(true){
			c.drawBoard();		
			if(StdDrawPlus.mousePressed()){
				double x = StdDrawPlus.mouseX();
				double y = StdDrawPlus.mouseY();
				if(c.canSelect((int) x, (int) y)){
					c.select((int) x, (int) y);
				}
				
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				StdDrawPlus.filledSquare((int) x + .5, (int) y + .5, .5);
			}
								
				if(StdDrawPlus.isSpacePressed()){
                  if(c.canEndTurn()){
                  	c.endTurn();
                  }
				}
				if(c.winner() != null){
					break;
				}

			
			StdDrawPlus.show(10);
		}
	}
}