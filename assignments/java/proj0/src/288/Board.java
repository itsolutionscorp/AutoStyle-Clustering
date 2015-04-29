public class Board {

	private Piece[] pieces = new Piece[24];
	private int[][] pieceLocations = new int[24][24];
	private Piece selected;
	private boolean hasMoved = false;
	private String whoseTurn = "fire";
	private int dumbFuckCounter=0;
	
	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty) {
			pieces = new Piece[24];

			return;
		}
		else {
			//buildShit();
			draw();
			//startGame();
			
		}

		
		
		//To be done
		//The rest!!!!
		//draw();
		
	}


	private void draw() {
		//Row1
		int arrayCounter = 0;

		for(int x=0;x<8;x++) {
			if(x%2!=0) {
				pieces[arrayCounter] = new Piece(false, this, x, 7, "pawn");
				pieceLocations[0][arrayCounter] = x;
				pieceLocations[1][arrayCounter] = 7;
				arrayCounter ++;
			}
		}

		//Row2
		for(int x=0;x<8;x++) {
			if(x%2==0) {
				pieces[arrayCounter] = new Piece(false, this, x, 6, "shield");
				pieceLocations[0][arrayCounter] = x;
				pieceLocations[1][arrayCounter] = 6;
				arrayCounter ++;
			}
		}

		//Row3
		for(int x=0;x<8;x++) {
			if(x%2!=0) {
				pieces[arrayCounter] = new Piece(false, this, x, 5, "bomb");
				pieceLocations[0][arrayCounter] = x;
				pieceLocations[1][arrayCounter] = 5;
				arrayCounter ++;
			}
		}

		//Row6
		for(int x=0;x<8;x++) {
			if(x%2==0) {
				pieces[arrayCounter] = new Piece(true, this, x, 2, "bomb");
				pieceLocations[0][arrayCounter] = x;
				pieceLocations[1][arrayCounter] = 2;
				arrayCounter ++;
			}
		}

		//Row7
		for(int x=0;x<8;x++) {
			if(x%2!=0) {
				pieces[arrayCounter] = new Piece(true, this, x, 1, "shield");
				pieceLocations[0][arrayCounter] = x;
				pieceLocations[1][arrayCounter] = 1;
				arrayCounter ++;
			}
		}

		//Row8
		for(int x=0;x<8;x++) {
			if(x%2==0) {
				pieces[arrayCounter] = new Piece(true, this, x, 0, "pawn");
				pieceLocations[0][arrayCounter] = x;
				pieceLocations[1][arrayCounter] = 0;
				arrayCounter ++;
			}
		}
		


	}
	private int getX(Piece p) {
		for (int i = 0; i< pieces.length;i++) {
			if (p==pieces[i]) {
				return pieceLocations[0][i];
			}
		}
		return -1; //Shouldn't happen!
	}

	private int getY(Piece p) {
		for (int i = 0; i< pieces.length;i++) {
			if (p==pieces[i]) {
				return pieceLocations[1][i];
			}
		}
		return -1; //Shouldn't happen!
	}

	private int getStupidIndex(Piece p) {
		for (int i = 0; i< pieces.length;i++) {
			if (p==pieces[i]) {
				return i;
			}
		}

		return -1; //Shouldn't happen!
	}

	private void buildShit() {
		buildEmpty();
		//for (Piece p: pieces) {
		//	//Don't try to initialize null pieces!!
		//	if (p!=null) {
		//		p = new Piece(p.isFire(), this, getX(p), getY(p), getType(p));
		//	}
		//}
	}

	private String getType(Piece p) {
		if (p.isShield()) {
			return "shield";
		}
		else if (p.isBomb()) {
			return "bomb";
		}
		return "pawn";
	}


	public static void main(String[] args) {
		buildEmpty();
		Board boner = new Board(false);

		while(true) {
			
            boner.buildShit();

            //Draw pieces
            for (int a = 0;a<8;a++) {
            	for (int b = 0;b<8;b++) {
            		Piece potentialPiece = boner.pieceAt(a,b);
            		if (potentialPiece!=null) {
            			String imgLoc = "";
            			if (potentialPiece.isFire()) {
							if (potentialPiece.isKing() && potentialPiece.isBomb())
								imgLoc = "img/bomb-fire-crowned.png";
							
							else if (potentialPiece.isKing() && potentialPiece.isShield())
								imgLoc = "img/shield-fire-crowned.png";
							else if (potentialPiece.isBomb())
								imgLoc = "img/bomb-fire.png";
							else if (potentialPiece.isShield())
								imgLoc = "img/shield-fire.png";
							
							else if (potentialPiece.isKing())
								imgLoc = "img/pawn-fire-crowned.png";
							else 
								imgLoc = "img/pawn-fire.png";
						}
						else {
							if (potentialPiece.isKing() && potentialPiece.isBomb())
								imgLoc = "img/bomb-water-crowned.png";
							
							else if (potentialPiece.isKing() && potentialPiece.isShield())
								imgLoc = "img/shield-water-crowned.png";
							else if (potentialPiece.isBomb())
								imgLoc = "img/bomb-water.png";
							else if (potentialPiece.isShield())
								imgLoc = "img/shield-water.png";
							else if (potentialPiece.isKing())
								imgLoc = "img/pawn-water-crowned.png";
							else
								imgLoc = "img/pawn-water.png";
						}
						StdDrawPlus.picture(boner.getX(potentialPiece)+0.5,  boner.getY(potentialPiece)+0.5, imgLoc, 1, 1);
            		}

            	}
            }
            //System.out.println(boner.pieceAt(0,0));
            if (StdDrawPlus.mousePressed()) {
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
                //System.out.println(selected);
                if (boner.canSelect(x,y)) {
                		boner.select(x,y);

                }
            }
               
            if (StdDrawPlus.isSpacePressed()) {
            	if (boner.canEndTurn()) {
            		boner.endTurn();
            	}
            }         
            if (boner.winner() != null) {
            	//Game over
            	return;
            }
            
            StdDrawPlus.show(20);
        }
	
	}


	private static void buildEmpty() {
		int N=8;
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);

            }
        }

	}

//Gets the piece at position (x, y) on the board, or null if there is no piece. If (x, y)
	//are out of bounds, returns null.
public Piece pieceAt(int x, int y) {
	for(int i=0;i<pieces.length;i++) {
		if (x==pieceLocations[0][i] && y==pieceLocations[1][i])
			return pieces[i];
	}
	return null;
} 

// Returns true if there is a piece the piece at (x, y) and it can be selected.
//A piece may be selected if it is the corresponding player’s turn and one of the following is true:

//The player has not selected a piece yet.
//The player has selected a piece, but did not move it.
//An empty square may be selected if one of the following is true:

//During this turn, the player has selected a Piece which hasn’t moved yet and is selecting an empty
//spot which is a valid move for the previously selected Piece.
//During this turn, the player has selected a Piece, captured, and has selected another valid
//capture destination. When performing multi-captures, you should only select the active piece once;
//all other selections should be valid destination points.
public boolean canSelect(int x, int y) {
	Piece pieceCurrentlyThere = pieceAt(x,y);
	if (pieceCurrentlyThere == null && selected==null)
		return false;

	if (selected!=null && selected.hasCaptured() && pieceCurrentlyThere!=null)
		return false;

	//Select same piece twice
	if (pieceCurrentlyThere == selected && (hasMoved==false && selected.hasCaptured()==false))
		return true;
	if(pieceCurrentlyThere==null && selected!=null && hasMoved==true && selected.hasCaptured())
		return validCapture(getX(selected),getY(selected),x,y);

	//Trying to move too far
	if(pieceCurrentlyThere==null && selected!=null && hasMoved==true && selected.hasCaptured()==false)
		return false;

	//Make sure it's the same team
	if(selected==null && hasMoved==false && pieceCurrentlyThere!=null && ((whoseTurn.equals("fire") && pieceCurrentlyThere.isFire()==true)  ||  (whoseTurn.equals("water") && pieceCurrentlyThere.isFire()==false))) {
		return true;
	}
	if((whoseTurn.equals("fire") && pieceCurrentlyThere!= null && pieceCurrentlyThere.isFire()==false)  ||  (whoseTurn.equals("water") && pieceCurrentlyThere!=null && pieceCurrentlyThere.isFire()==true))
		return false;

	if(pieceCurrentlyThere==null && selected!=null && hasMoved==false)
		return validMove(getX(selected),getY(selected),x,y);
	
	

	//Reselecting a different piece of their own
	if(pieceCurrentlyThere!=null && selected!=null && hasMoved==false && selected.hasCaptured()==false &&

		(whoseTurn.equals("fire") && pieceCurrentlyThere.isFire()==true)  ||  (whoseTurn.equals("water") && pieceCurrentlyThere.isFire()==false)
		) {
		return true;
	}
	
	return false;
}

private boolean validCapture(int startx, int starty, int destx, int desty) {
	return validMove(startx, starty, destx, desty) && ((startx + 2 ==destx || startx -2==destx) &&
			(starty + 2 ==desty || starty-2==desty)) ;
	
}


//Returns true if the piece at (xi, yi) can either move to (xf, yf) or capture to (xf, yf),
//strictly from a geometry/piece-race point of view. validMove does not need to take into
//consideration whose turn it is or if a move has already been made, but rather can
private boolean validMove(int xi, int yi, int xf, int yf) {
	Piece initialPiece = pieceAt(xi,yi);
	if (initialPiece==null)
		return false;
	if (pieceAt(xf,yf)!=null)
		return false;
	if (xf>7 || yf>7 || xf<0 || yf < 0)
		return false;

	boolean king = initialPiece.isKing();
	boolean team = initialPiece.isFire();

	if (king) {
		//Regular Move
		if (xi + 1 ==xf || xf==xi-1) {
			if (yi + 1 ==yf || yi-1==yf) {
				return true;
			}
		}
		//Capture
		if (xi + 2 ==xf || xf-2==xi) {
			if (yi + 2 ==yf || yi-2==yf) {
				Piece intermediatePiece = pieceAt((int)(xf+xi)/2,(int)(yf+yi)/2);
				if (intermediatePiece==null) {
				}
				//Make sure it's the opposite team for the jump
				else if (intermediatePiece.isFire() != initialPiece.isFire()) {
					return true;
				}
				}
		}
		return false;
	}

	
	//Team fire
	if (team) {
		if (xi + 1 ==xf|| xf==xi-1) {
			if (yi + 1 ==yf) {
				return true;
			}
		}

		//Capture
		if (xi + 2 ==xf || xf==xi-2) {
			if (yi + 2 ==yf) {
				Piece intermediatePiece = pieceAt((int)(xf+xi)/2,(int)(yf+yi)/2);
				if (intermediatePiece==null){
					}
				//Make sure it's the opposite team for the jump
				else if (intermediatePiece.isFire() != initialPiece.isFire())
					return true;
			}
		}
	}
	//Team water
	else if (team!=true) {
		if (xi - 1 ==xf|| xf==xi+1) {
			if (yi - 1 ==yf) {
				return true;
			}
		}

		//Capture
		if (xi + 2 ==xf || xf==xi-2) {
			if (yi - 2 ==yf) {
				Piece intermediatePiece = pieceAt((int)(xf+xi)/2,(int)(yf+yi)/2);
				if (intermediatePiece==null){
					return false; //Nothing to capture
				}
				//Make sure it's the opposite team for the jump
				else if (intermediatePiece.isFire() != initialPiece.isFire()) {
					return true;
				}
			}
		}
	}



	return false;

}

//Selects the piece at (x, y) if possible. Optionally, it is recommended to color
//the background of the selected square white on the GUI via the pen color function.
//For any piece to perform a capture, that piece must have been selected first.
public void select(int x, int y) {

	if (selected == null) {
		selected = pieceAt(x,y);
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
	}

	//Selecting a different one of their pieces
	else if (pieceAt(x,y)!=null && (pieceAt(x,y).isFire() == selected.isFire())) {
		selected = pieceAt(x,y);
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
    }


    //Doing a move
	else if (pieceAt(x,y)==null) {
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
		pieceLocations[0][getStupidIndex(selected)] = x;//Update X
		pieceLocations[1][getStupidIndex(selected)] = y;//Update Y
		selected.move(x,y); //Doing last is safer?
        hasMoved=true;
    }

}

 // Places p at (x, y). If (x, y) is out of bounds or if p is null, does nothing. If p already exists
//in the current Board, first removes it from its initial position. If another piece already exists at (x, y),
//p will replace that piece. (This method is potentially useful for creating specific test circumstances.)

public void place(Piece p, int x, int y) {
	//We need to add p to pieces if it's not already in there!!
	

	/////////
	if (p==null || x>7 || y>7 || x<0 || y<0)
		return;
	
	//See if p already exists
	for (int i =0;i<pieces.length;i++) {
		if (pieces[i] == p) {
			pieces[i] = null;
		}
	}
	
	pieces[dumbFuckCounter] = p;
	pieceLocations[0][dumbFuckCounter] = x;//Update X
	pieceLocations[1][dumbFuckCounter] = y;//Update Y

	dumbFuckCounter++;

}

 // Executes a remove. Returns the piece that was removed. If the input (x, y) is out of bounds,
//returns null and prints an appropriate message. If there is no piece at (x, y), returns null and
//prints an appropriate message.
public Piece remove(int x, int y) {
	if (x>7 || y>7) {
		System.out.println("Out of Bounds Can't be removed!");
		return null;
	}
	if(pieceAt(x,y)==null) {
		System.out.println("No piece to be removed");
		return null;
	}
	Piece destinationPiece = pieceAt(x,y);

	for(int i=0;i<pieces.length;i++) {
			if (pieces[i]==destinationPiece) {
				pieces[i]=null;
				pieceLocations[0][i] = -1; //Shouldn't happen
				pieceLocations[1][i] = -1; //Shouldn't happen
			}
		}
	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
	StdDrawPlus.filledSquare(x + .5, y + .5, .5);
	return destinationPiece;
}

 // Returns whether or not the the current player can end their turn. To be able to end a turn, a
//piece must have moved or performed a capture.
public boolean canEndTurn() {
	if (hasMoved)
		return true;
	if (selected!=null) {
		if (selected.hasCaptured())
			return true;
	}
	return false;

}

// Called at the end of each turn. Handles switching of players and anything else that should happen at
//the end of a turn.
public void endTurn() {
	if (whoseTurn.equals("fire")) {
		whoseTurn = "water";
	}
	else {
		whoseTurn = "fire";
	}
	//if (selected==null) {}
	selected.doneCapturing();
	selected = null;
	hasMoved = false;
}

 // Returns the winner of the game. "Fire", "Water", "No one" (tie / no pieces on the board), or null if the game
//is not yet over. Assume there is no stalemate situation in which the current player has pieces but cannot
//legally move any of them (In this event, just leave it at null). Determine the winner solely by the number of pieces
//belonging to each team.
public String winner() {
	int numFireLeft =0;
	int numWaterLeft =0;
	for (Piece p:pieces ) {
		if (p==null) {

		}
		else if (p.isFire())
			numFireLeft++;
		else if (p.isFire()==false)
			numWaterLeft++;
	}
	if (numWaterLeft==0 && numFireLeft!=0)
		return "Fire";
	else if (numWaterLeft!=0 && numFireLeft==0)
		return "Water";
	else if (numWaterLeft==0 && numFireLeft==0)
		return "No one";
	return null;
	

}

}