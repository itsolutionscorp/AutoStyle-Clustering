public class Board {
	private Piece [][] pieceCo = new Piece[8][8];
	private Piece thePiece;
	private boolean moved;
	private boolean pieceDesti=false;
	private boolean hasSelected=false;
	private boolean fireTurn = true;




	public static void main(String args[]){
		Board newboard= new Board(true);
	}
	public Board  (boolean shouldBeEmpty){
		if (shouldBeEmpty){
			return;
		}
		if (!shouldBeEmpty){
			for (int n=0; n<8;n++){
				for (int m=0; m<8;m++){
				if (((n + m) % 2 == 0)&&(m==0)){
					Piece p = new Piece(true, this,  n, m,"pawn");
					pieceCo[n][m] = p;
				}
				if (((n + m) % 2 == 0)&&(m==1)){
					Piece p = new Piece(true, this,  n, m, "shield");
					pieceCo[n][m] = p;
				}
				if (((n + m) % 2 == 0)&&(m==2)){
					Piece p = new Piece(true, this,  n, m, "bomb");
					pieceCo[n][m] = p;
				}
				if (((n + m) % 2 == 0)&&(m==5)){
					Piece p = new Piece(false, this,  n, m, "bomb");
					pieceCo[n][m] = p;

				}
				if (((n + m) % 2 == 0)&&(m==6)){
					Piece p = new Piece(false, this,  n, m, "shield");
					pieceCo[n][m] = p;
				}
				if (((n + m) % 2 == 0)&&(m==7)){
					Piece p = new Piece(false, this,  n, m, "pawn");
					pieceCo[n][m] = p;
				}
			}
		}
		

			//if shouldbeempty is true initializes an empty board. otherwise, initialize a board with the default configuratino.//
	}

	}
	public Piece pieceAt(int x, int y){
		//gets the piece at position (x,y) on the board, or null if there is no piece.//
		if ((x>=8)||(y>=8)){
			return null;
		}
		return pieceCo[x][y];

	}
	public boolean canSelect(int x, int y){
		//return true if the square at (x,y) can be selected//
		//a square with a piece--a)the corresponding player's turn and "the player has not selected a piece yet" or "the player 
		//has selected a piece but did not move it"
		//an empty square may be selcted if "the player has selected a piece which hasn't moved"
		// or "During this turn, the player has selected a Piece, captured, and has selected another 
		//valid capture destination. When performing multi-captures, you should only select the active piece once; 
		//all other selections should be valid destination points."
		if ((thePiece == null) && (pieceAt(x, y) == null)){
			return false;
		}

		if (((thePiece==null) && fireTurn)||((thePiece!=null)&&(moved==false) && fireTurn)){
			if ((pieceAt(x, y) != null) && (pieceAt(x, y).isFire()==true)){
			return true;
			}
		}
		if (((thePiece==null) && (!fireTurn))||((thePiece!=null)&&(moved==false) && (!fireTurn))){
			if ((pieceAt(x, y) != null) && (pieceAt(x, y).isFire()==false)){
			return true;
			}
		}

		if ((moved==false)||((thePiece!=null)&&(pieceDesti))){
			if (pieceCo[x][y] == null){
				return true;
			}
		}
		return false;

		
	}
	public void select(int x, int y){
		//Selects the square at (x, y). 
		//This method assumes canSelect (x,y) returns true. O
		//ptionally, it is recommended to color the background of the selected square white on the GUI via the pen color function. 
		//For any piece to perform a capture, that piece must have been selected first. 
		//If you select a square with a piece, you are prepping that piece for movement. 
		//If you select an empty square (assuming canSelect returns true), you should move your most recently selected piece to that square.
		if (thePiece==null){
			thePiece=pieceCo[x][y];
			return;
		}
		if (thePiece!=null && (thePiece!= pieceAt(x,y))){
			moved = true;
			thePiece.move(x,y);
			thePiece.doneCapturing();
		}


	}
	public void place(Piece p, int x, int y){
		//Places p at (x, y). If (x, y) is out of bounds or if p is null, does nothing. 
		//If another piece already exists at (x, y), p will replace that piece. 
		//(This method is potentially useful for creating specific test circumstances.)
		if (((x>=8)||(y>=8))||(p==null)){
			return ;	
		}
		for (int n = 0; n<8; n++){
			for (int m = 0 ; m<8; m++){
				if (pieceCo[n][m] == p){
					pieceCo[n][m] = null;
				}
			}
		}
		pieceCo[x][y] = p;
	}

	public Piece remove (int x,int y){
		//Executes a remove. Returns the piece that was removed. 
		//If the input (x, y) is out of bounds, returns null and prints an appropriate message. 
		//If there is no piece at (x, y), returns null and prints an appropriate message.
		if ((x>=8)||(y>=8)){
			System.out.println("the input (x , y) is out of bounds");
			return null;
		}
		if (pieceCo[x][y]==null){
			System.out.println("there is no piece at (x,y)");	
			return null;
		}
		if(pieceAt(x, y)!=null){
			Piece p = pieceCo[x][y];
			pieceCo[x][y] = null;
			return p;
			}
		return null;
	}

	public boolean canEndTurn(){

		//Returns whether or not the the current player can end their turn. 
		//To be able to end a turn, a piece must have moved or performed a capture.
		if ((moved==true)||((thePiece!=null) && (thePiece.hasCaptured()))){
			return true;
		}
		return false;
	}

	public void endTurn(){
		//Called at the end of each turn. Handles switching of players and anything else that should happen 
		//at the end of a turn.
		fireTurn = (!fireTurn);
		moved = false;
		thePiece = null;
		pieceDesti = false;
		hasSelected = false;


	}
	
	public String winner(){
		//Returns the winner of the game: "Fire", "Water", "No one", or null. 
		//If only fire pieces remain on the board, fire wins. 
		//If only water pieces remain, water wins. 
		//If no pieces remain (consider an explosion capture), no one wins. 
		//If the game is still in progress (ie there are pieces from both sides still on the board) return null. Assume there is no stalemate situation i
		//n which the current player has pieces but cannot legally move any of them (In this event, just leave it at null). 
		//Determine the winner solely by the number of pieces belonging to each team.
		int fire = 0;
		int water = 0;
		for (int n = 0; n<8; n++){
			for (int m = 0 ; m<8; m++){
				
				if ((pieceCo[n][m] != null) && (pieceCo[n][m].isFire())){
					fire++;
				}
				if ((pieceCo[n][m] != null) && (pieceCo[n][m].isFire()==false)){
					water++;
				}
				
			}
		}

		if ((fire!=0)&&(water!=0)){
			return null;
		}
		if ((fire==0)&&(water==0)){
			return "No one";
		}

		if ((fire!=0)&&(water==0)){
			return "Fire";
		}

		if ((water!=0)&&(fire == 0)){
			return "Water";
		}
		return "";


	}




}