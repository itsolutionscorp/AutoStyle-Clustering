public class Board {
	private Piece[][] pieces;                  //location of all pieces
	private String player = "fire"; 	       //keep track of current player
	private boolean hasSelectedPiece = false;  //current player selected piece or not
	private boolean hasMovedPiece = false;     //current player moved piece or not
	private int chosenX = -10;  //x-coordinate of currently selected square
	private int chosenY = -10;  //y-coordinate of currently selected square

	public static void main(String[] args) {
		Board b = new Board(false);
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        int x = 20;
        int y = 20;
        String won = "Start Game";

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {    		
            b.drawBoard();

            if (StdDrawPlus.mousePressed()) {
                double xDouble = StdDrawPlus.mouseX();
                double yDouble = StdDrawPlus.mouseY();
                x = (int) xDouble;
                y = (int) yDouble;
                
                if(b.canSelect(x, y)){
					b.select(x,y);
                }
            }

            if (b.hasSelectedPiece == true) {
                StdDrawPlus.filledSquare(b.chosenX + .5, b.chosenY + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                b.drawPieceOnBoard(b.chosenX, b.chosenY);
            }
                
            if (StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
            	b.endTurn(); 

            	won = b.winner();
            	if( won == null )                 continue;
            	if( won.equals("Fire") )          break;
            	else if( won.equals("Water") )    break;
            	else if( won.equals("No one") )   break;
            }
            	
            StdDrawPlus.show(100);
        }
	}

	private void drawPieceOnBoard(int i, int j) {
		if ( pieces[i][j] == null)  return;

		if ( pieces[i][j].isKing() && pieces[i][j].isFire() ) {  //fire kings
			if(pieces[i][j].isShield())
            	StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
            else if(pieces[i][j].isBomb())
            	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
           	else
           		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
		} else if ( pieces[i][j].isKing() && !pieces[i][j].isFire() ) {
			if(pieces[i][j].isShield())
	        	StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
	        else if(pieces[i][j].isBomb())
	        	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
	       	else
	       		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
		}

        if ( pieces[i][j].isFire() ) {
        	if(pieces[i][j].isShield())
            	StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
            else if(pieces[i][j].isBomb())
            	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
           	else
           		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
        } else {
	    	if(pieces[i][j].isShield())
	        	StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
	        else if(pieces[i][j].isBomb())
	        	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
	       	else
	       		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
	    }

	}

	private void drawBoard() {
		int N = 8;
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
  
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                drawPieceOnBoard(i, j);
            }
        }
	}

	public Board(boolean shouldBeEmpty) {
		int N = 8;
		if(shouldBeEmpty) {

			pieces = new Piece[N][N];

		} else {

			pieces = new Piece[N][N];

			for(int y = 0; y < N; y++) {
				for(int x = 0; x < N; x++) {

					if( (y==0) && (x%2 == 0) ) {
						pieces[x][y] = new Piece(true, this, x, y, "pawn");

					} else if ( (y==1) && (x%2 != 0) ) {
						pieces[x][y] = new Piece(true, this, x, y, "shield");

					} else if ( (y==2) && (x%2 == 0) ) {
						pieces[x][y] = new Piece(true, this, x, y, "bomb");

					} else if ( (y==(N-3)) && (x%2 != 0) ) {
						pieces[x][y] = new Piece(false, this, x, y, "bomb");

					} else if ( (y==(N-2)) && (x%2 == 0) ) {
						pieces[x][y] = new Piece(false, this, x, y, "shield");

					} else if ( (y==(N-1)) && (x%2 != 0) ) {
						pieces[x][y] = new Piece(false, this, x, y, "pawn");
					}

				}
			}

		}
	}

	public Piece pieceAt(int x, int y) {
		if ( (x > 7) || (x < 0) || (y > 7) || (y < 0) || (pieces[x][y] == null)) {
			return null;
		} else {
			return pieces[x][y];
		}
	}

	private boolean canSelectEmptySquare(int x, int y) {
		/** Assumes 1) (x,y) location is empty, i.e. pieces[x][y] == null
			        2) (x,y) is inbounds 
					3) a piece has been selected */
		if(!validMove(chosenX, chosenY, x, y))  return false;

		if(hasMovedPiece == false)  
			return true;

		Piece pieceToMove = pieces[chosenX][chosenY];
		if( pieceToMove.hasCaptured() && (Math.abs(x-chosenX) == 2)  && (Math.abs(y-chosenY) == 2) )
			return true;

		return false;
	}

	private boolean canSelectNonEmptySquare(int x, int y) {
		/** Assumes 1) (x,y) location non-empty, i.e. pieces[x][y] != null
			        2) (x,y) is inbounds. */
		Piece p = pieces[x][y];
		boolean playerIsFire = player.equals("fire");
		if( (playerIsFire && p.isFire()) || (!playerIsFire && !p.isFire()) ) {
			if(hasSelectedPiece == false)    return true;
			else if(hasMovedPiece == false)  return true;
			else                             return false;
		}
		return false;
	}

	public boolean canSelect(int x, int y) {
		if( (x < 0) || (y < 0) || (x > 7) || (y > 7) )  return false;  //check if inbounds
		
		if(pieces[x][y] == null && hasSelectedPiece == true)
			return canSelectEmptySquare(x, y);
		else if(pieces[x][y] != null)
			return canSelectNonEmptySquare(x, y);

		return false;
	}

	private boolean validMoveNotKing(int xi, int yi, int xf, int yf, boolean isFire) {

		if( (xi < 0) || (yi < 0) || (xi > 7) || (yi > 7) )  return false;
		if( (xf < 0) || (yf < 0) || (xf > 7) || (yf > 7) )  return false;
		if(pieces[xi][yi] == null)  return false;
		if(isFire  && yf-yi < 0)    return false;   //make sure fire moves upward
		if(!isFire && yf-yi > 0 )   return false;  //make sure water moves downward
		if(pieces[xf][yf] != null)  return false;  //if move to non-empty square 

		/** if moving diagonally one square. */
		if(Math.abs(xi-xf) == 1) {
			return true;  //already know square is empty
		}

		/** if moving diagonally two squares. */
		int xShift = xf-xi;
		int upOrDown;
		int leftOrRight;
		Piece initial = pieces[xi][yi];  //current piece trying to move
		Piece pieceJumped;           

		if(isFire)  upOrDown = 1;  //make sure fire moves upward
		else        upOrDown = -1; //make sure water moves downard

		if(xShift > 0)  leftOrRight = 1;   //right shift
		else            leftOrRight = -1;  //left shift

		pieceJumped = pieces[xi + leftOrRight][yi + upOrDown];

		if(pieceJumped == null)                             return false;  //cannot jump empty square
		else if(initial.isFire() && pieceJumped.isFire())   return false;  //cannot jump same type
		else if(!initial.isFire() && !pieceJumped.isFire()) return false;  //cannot jump same type
		else                                                return true;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if( (xi < 0) || (yi < 0) || (xi > 7) || (yi > 7) )	   return false;
		if( (xf < 0) || (yf < 0) || (xf > 7) || (yf > 7) )     return false;
		if( pieces[xi][yi] == null )                           return false;  
		if( (Math.abs(xi-xf) > 2) || (Math.abs(yi-yf) > 2) )   return false;
		if( Math.abs(xi-xf) != Math.abs(yi-yf) )               return false;

		Piece p = pieces[xi][yi];  //grab piece in initial spot (xi,yi)

		if(p.isKing()){
			boolean moveUp = validMoveNotKing(xi, yi, xf, yf, true);
			boolean moveDown = validMoveNotKing(xi, yi, xf, yf, false);
			return (moveUp || moveDown);	
		}
		return validMoveNotKing(xi, yi, xf, yf, p.isFire());
		
	}

	public void select(int x, int y) {
		if ( hasSelectedPiece == false ) {  //set (x,y) coordinates of first select
			chosenX = x;
			chosenY = y;
			hasSelectedPiece = true;
		} else if ( hasMovedPiece == false) {  /** grab new coordinates then
												   1) move to new square OR
												   2) new coordinates becomes new selected piece */

			if ( pieces[x][y] == null ) {             //piece already selected, choose an empty square to move piece to
				pieces[chosenX][chosenY].move(x, y);  //move selected piece to empty square
				hasMovedPiece = true;

				chosenX = x;  //current piece selected changes to new selected square
				chosenY = y;

			} else {
				chosenX = x;  //capture new selected piece
				chosenY = y;
			}

		} else {  //has selected piece and has moved it
			if(validMove(chosenX, chosenY, x, y)) {
				pieces[chosenX][chosenY].move(x, y);

				chosenX = x;
				chosenY = y;
			}
		}
	}

	public void place(Piece p, int x, int y) {
		if( (p == null) || (x < 0) || (y < 0) || (x > 7) || (y > 7) ) {
			return;
		}
		
		pieces[x][y] = p;
	}

	public Piece remove(int x, int y) {
		boolean isOutOfBounds = (x < 0) || (y < 0) || (x > 7) || (y > 7);
		boolean isEmptySquare = (pieces[x][y] == null);

		if(isOutOfBounds){
			System.out.println("Error: (x,y) is out of bounds.");
			return null;
		} else if(isEmptySquare){
			System.out.println("Error: There is no piece to remove.");
			return null;
		}

		Piece removedPiece = pieces[x][y];  //save removed piece
		pieces[x][y] = null;                //remove piece

		return removedPiece;

	}

	public boolean canEndTurn() {
		if(hasSelectedPiece == false)  return false;
		if(hasMovedPiece == true || pieces[chosenX][chosenY].hasCaptured())
			return true;

		return false;
	}

	public void endTurn() {
		if(player.equals("fire"))
			player = "water";
		else
			player = "fire";

		pieces[chosenX][chosenY].doneCapturing();
		hasSelectedPiece = false;
		hasMovedPiece = false;
		chosenX = -10;
		chosenY = -10;
	}

	public String winner() {
		int firePieces = 0;
		int waterPieces = 0;
		int N = 8;

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {

				if(pieces[i][j] == null)  continue;

				if(pieces[i][j].isFire())
					firePieces += 1;
				else
					waterPieces += 1;

			}
		}

		if(firePieces > 0 && waterPieces > 0)
			return null;
		else if(firePieces == 0 && waterPieces == 0)
			return "No one";
		else if(firePieces == 0)
			return "Water";
		else 
			return "Fire";

	}


}