public class Board{
	private Piece[][] pieces;
	private boolean itsFireTurn=true;
	private static int N = 8;
	private boolean hasMoved=false;
	private Piece selectedPiece;
	private int selectX,selectY;

	/* The constructor for Board. If shouldBeEmpty is true, initializes an empty 
	 * Board. Otherwise, initializes a Board with the default configuration. 
	 * Note that an empty Board could possibly be useful for testing purposes.
	 *
	**/
	public Board (boolean shouldBeEmpty){
		this.pieces = new Piece[8][8];
		if (!shouldBeEmpty){
			pieces[0][0] = new Piece (true, this,0,0,"pawn");
			pieces[2][0] = new Piece (true, this,2,0,"pawn");
			pieces[4][0] = new Piece (true, this,4,0,"pawn");
			pieces[6][0] = new Piece (true, this,6,0,"pawn");			
			
			pieces[0][6] = new Piece (false, this,0,6,"shield");
			pieces[2][6] = new Piece (false, this,2,6,"shield");
			pieces[4][6] = new Piece (false, this,4,6,"shield");
			pieces[6][6] = new Piece (false, this,6,6,"shield");
			
			pieces[0][2] = new Piece (true, this,0,2,"bomb");
			pieces[2][2] = new Piece (true, this,2,2,"bomb");
			pieces[4][2] = new Piece (true, this,4,2,"bomb");
			pieces[6][2] = new Piece (true, this,6,2,"bomb");			
			
			pieces[1][1] = new Piece (true, this,1,1,"shield");
			pieces[3][1] = new Piece (true, this,3,1,"shield");
			pieces[5][1] = new Piece (true, this,5,1,"shield");
			pieces[7][1] = new Piece (true, this,7,1,"shield");
			
			pieces[1][5] = new Piece (false, this,1,5,"bomb");
			pieces[3][5] = new Piece (false, this,3,5,"bomb");
			pieces[5][5] = new Piece (false, this,5,5,"bomb");
			pieces[7][5] = new Piece (false, this,7,5,"bomb");
			
			pieces[1][7] = new Piece (false, this,1,7,"pawn");
			pieces[3][7] = new Piece (false, this,3,7,"pawn");
			pieces[5][7] = new Piece (false, this,5,7,"pawn");
			pieces[7][7] = new Piece (false, this,7,7,"pawn");
		}

	}

	// Gets the piece at position (x, y) on the board, or null if there is no piece. 
	// If (x, y) are out of bounds, returns null.
	public Piece pieceAt(int x, int y){
		if ((x>7) || (y>7) || (x<0) || (y<0)) {
			return null;
		}
		else{
			return pieces[x][y];
		}
	}

	/* Returns true if the square at (x,y) can be selected.
	 * A square with a piece may be selected if it is the corresponding player’s turn 
	 * and one of the following is true:
	    -The player has not selected a piece yet.
	    -The player has selected a piece, but did not move it.
	 * An empty square may be selected if one of the following is true:
	    -During this turn, the player has selected a Piece which hasn’t moved yet and 
	    is selecting an empty spot which is a valid move for the previously selected 
	    Piece.
	    -During this turn, the player has selected a Piece, captured, and has selected 
	    another valid capture destination. When performing multi-captures, you should 
	    only select the active piece once; all other selections should be valid 
	    destination points.
	*/
	public boolean canSelect(int x, int y){
		Piece selection = pieceAt(x,y);
//		if ((selectX==x)&&(selectedPiece != null)){
//			return false;
//		}else
		if (selection != null) {
			if (selectedPiece==null){
				if ((selection.side()==1) && itsFireTurn){
					return true;
				}
				else if ((selection.side()==0) && !itsFireTurn){
					return true;
				}
			} 
			else if (!hasMoved){
				if ((selection.side()==1) && itsFireTurn){
					return true;
				}
				else if ((selection.side()==0) && !itsFireTurn){
					return true;
				}
			}
		} 
		else{
			int xDisplacement=x-selectX;
			int yDisplacement=y-selectY;
			if (validSquare(xDisplacement,yDisplacement)){
				if (selectedPiece.isKing()){
					if (!hasMoved){
						if (Math.abs(yDisplacement)==2){
							return canCapture(xDisplacement,yDisplacement);
						}
						else if(Math.abs(yDisplacement)==1){
							return (this.pieces[x][y]==null);
						}
						else{
							return false;
						}
					}
					else{
						return (selectedPiece.hasCaptured() && canCapture(xDisplacement,yDisplacement));
					}
				}else{
					if (inRightDirection(xDisplacement,yDisplacement)){
						if (!hasMoved){
							if (Math.abs(yDisplacement)==2){
								return canCapture(xDisplacement,yDisplacement);
							}
							else if(Math.abs(yDisplacement)==1){
								return (this.pieces[x][y]==null);
							}
							else{
								return false;
							}
						}
						else{
							return (selectedPiece.hasCaptured() && canCapture(xDisplacement,yDisplacement));
						}
					}
					else{return false;}
				}
			}else{return false;}			
		}
		return false;//for all other cases
	}
	
	private boolean validSquare(int xDisplacement, int yDisplacement){
		if (xDisplacement==0){
			return false;
		}
		return (Math.abs(xDisplacement)==Math.abs(yDisplacement));
	}
	
	private boolean inRightDirection(int xDisplacement, int yDisplacement){
		if (selectedPiece.isFire()){
			return yDisplacement>0;
		}else{
			return yDisplacement<0;
		}
	}
	
	private boolean canCapture(int xDisplacement, int yDisplacement){
		Piece capturedPiece=this.pieces[selectX+(xDisplacement/2)][selectY+(yDisplacement/2)];
		if (capturedPiece != null){
			return (selectedPiece.isFire() ^ capturedPiece.isFire());
		}
		else{
			return false;
		}
	}
	

	/* Selects the square at (x, y). This method assumes canSelect (x,y) returns true. 
	 * Optionally, it is recommended to color the background of the selected square 
	 * white on the GUI via the pen color function. For any piece to perform a capture, 
	 * that piece must have been selected first. If you select a square with a piece, 
	 * you are prepping that piece for movement. If you select an empty square (assuming 
	 * canSelect returns true), you should move your most recently selected piece to 
	 * that square.
	*/
	public void select(int x, int y){
		if (selectedPiece==null){
			selectedPiece=pieces[x][y];
			selectX=x;
			selectY=y;
		}else if (pieceAt(x,y)==null){
			selectedPiece.move(x, y);
			hasMoved=true;
			selectX=x;
			selectY=y;
		}else if (!(selectedPiece.isFire()^pieceAt(x,y).isFire())){
			selectedPiece=pieces[x][y];
			selectX=x;
			selectY=y;
		}
	}


	/* Places p at (x, y). If (x, y) is out of bounds or if p is null, does nothing. 
	 * If p already exists in the current Board, first removes it from its initial 
	 * position. If another piece already exists at (x, y), p will replace that piece. 
	 * (This method is potentially useful for creating specific test circumstances.)
	*/
	public void place(Piece p, int x, int y){
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (p==this.pieces[i][j]){
            		p=this.remove(i,j);
            	}
            }
		}
		if (!((p==null)||(x<0)||(y<0)||(x>7)||(y>7))){
			this.pieces[x][y]=p;
		}
	}

	/* Executes a remove. Returns the piece that was removed. If the input (x, y) is out of
	 * bounds, returns null and prints an appropriate message. If there is no piece at 
	 * (x, y), returns null and prints an appropriate message.
	*/
	public Piece remove(int x, int y){
		Piece myPiece=pieceAt(x,y);
		if (myPiece==null){
			System.out.println("There's either no piece at (x,y), or (x,y) is out of bounds");
			return null; //this line may be useless
		}
		else{
			pieces[x][y]=null;
		}
		
		return myPiece;
	}

	/* Returns whether or not the the current player can end their turn. To be able to end a 
	 * turn, a piece must have moved or performed a capture.
	*/
	public boolean canEndTurn(){
		return hasMoved;
	}


	/* Called at the end of each turn. Handles switching of players and anything else that 
	 * should happen at the end of a turn.
	*/
	public void endTurn(){
		hasMoved=false;
		selectedPiece=null;
		itsFireTurn=!itsFireTurn;
		selectX=0;selectY=0;
		
	}

	/* Returns the winner of the game. "Fire", "Water", "No one" (tie / no pieces on the board), 
	 * or null if the game is not yet over. Assume there is no stalemate situation in which 
	 * the current player has pieces but cannot legally move any of them (In this event, just 
	 * leave it at null). Determine the winner solely by the number of pieces belonging to each 
	 * team.
	*/
	public String winner(){
		int waters=waterPieces(this);
		int fires=firePieces(this);
		if (waters+fires==0){
			return "No one";
		}
		else if(waters==0){
			return "Fire";
		} else if (fires==0){
			return "Water";
		}else return null;

	}
	
	private int firePieces(Board b){
		int counter=0;
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (b.pieces[i][j] != null){
            		if (b.pieces[i][j].isFire()){
            			counter+=1;
            		}
            	}
            }
		}
		return counter;
	}
	
	private int waterPieces(Board b){
		int counter=0;
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (b.pieces[i][j] != null){
            		if (!b.pieces[i][j].isFire()){
            			counter+=1;
            		}
            	}
            }
		}
		return counter;
	}


    private static void drawBoard(Board b) {
        int N = 8;
    	for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(92,204,123);
                else                  StdDrawPlus.setPenColor(23,187,57);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(187,233,200);
                if ((b.selectedPiece!=null)&&(i==b.selectX)&&(j==b.selectY)){
                	StdDrawPlus.filledSquare(b.selectX + .5, b.selectY + .5, .5);
                }
                Piece currentBox=b.pieces[i][j];
                if (currentBox != null){
                	if (currentBox.isFire()){
                		if (currentBox.isBomb()){
                			if (currentBox.isKing()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                			}else{
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                			}
                		}
                		else if (currentBox.isShield()){
                			if (currentBox.isKing()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                			}else{
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                			}
                		}
                		else{
                			if(currentBox.isKing()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                			}else{
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                			}
                		}
                	} 
                	else {
                		if (currentBox.isBomb()){
                			if(currentBox.isKing()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                			}else{
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                			}
                		}
                		else if (currentBox.isShield()){
                			if(currentBox.isKing()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                			}else{
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                			}
                		}
                		else{
                			if(currentBox.isKing()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                			}else{
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                			}
                		}
                	}
                }
            }
        }
    }

	//starts a GUI supported version of the game.
	public static void main (String[] args){
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board game = new Board(false);

        while(true) {
            drawBoard(game);
             if (StdDrawPlus.mousePressed()) {
                 double x = StdDrawPlus.mouseX();
                 double y = StdDrawPlus.mouseY();
                 if (game.canSelect((int)x,(int)y)){
                	 game.select((int)x,(int) y);
                 }
             }
             if (game.canEndTurn() && StdDrawPlus.isSpacePressed()){
            	 game.endTurn();
            	 
             }
             String winner = game.winner();
             if (winner != null){
            	 System.out.println(winner);
            	 break;
             }
            StdDrawPlus.show(100);
        }

	}
		
}