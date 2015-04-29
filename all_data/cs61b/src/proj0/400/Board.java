public class Board {
	
	private static int N = 8;
	private static Piece[][] pieces2 = new Piece[N][N];
	private Piece selectedPiece; //initialize
	
	private boolean hasMoved = false; //initialize
	private boolean validMove;
	private boolean validCapture;
	private int selectedX;
	private int selectedY;
	private boolean isFireTurn = true; //initialize


	//passes tests!
	private void drawBoard(int N) {
    	StdDrawPlus.setXscale(0, N);
    	StdDrawPlus.setYscale(0, N);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				pieces2[i][j] = null;
			}
		}

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                for (int k = 0; k < N; k += 2) {
                    StdDrawPlus.picture(k + .5, 0.5, "img/pawn-fire.png", 1, 1);
                    Piece pf = new Piece(true, this, k, 0, "pawn");
                    pieces2[k][0] = pf;

                    StdDrawPlus.picture(k + .5, 2.5, "img/bomb-fire.png", 1, 1);
                    Piece bf = new Piece(true, this, k, 2, "bomb");
                    pieces2[k][2] = bf;

                    StdDrawPlus.picture(k + .5, 6.5, "img/shield-water.png", 1, 1);
                    Piece sw = new Piece(false, this, k, 6, "shield");
                    pieces2[k][6] = sw;
                }

                for (int l = 1; l < N; l += 2) {
                    StdDrawPlus.picture(l + .5, 1.5, "img/shield-fire.png", 1, 1);
                    Piece sf = new Piece(true, this, l, 1, "shield");
                    pieces2[l][1] = sf;

                    StdDrawPlus.picture(l + .5, 5.5, "img/bomb-water.png", 1, 1);
                    Piece bw = new Piece(false, this, l, 5, "bomb");
                    pieces2[l][5] = bw;

                    StdDrawPlus.picture(l + .5, 7.5, "img/pawn-water.png", 1, 1);
                    Piece pw = new Piece(false, this, l, 7, "pawn");
                    pieces2[l][7] = pw;
                }
            }
        }
    }

    //passes tests!
    private static void drawEmptyBoard(int N) {
    	StdDrawPlus.setXscale(0, N);
    	StdDrawPlus.setYscale(0, N);
    	
    	for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				pieces2[i][j] = null;
			}
		}

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
    }
	
	private void updateBoard() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j<N; j++) {
				if (pieces2[i][j] != null) {

					if (pieces2[i][j].type == "bomb") {
						if (pieces2[i][j].isFire() == true) {
							StdDrawPlus.picture(pieces2[i][j].x + 0.5, pieces2[i][j].y + 0.5, "img/bomb-fire.png", 1, 1);
						} else {
							StdDrawPlus.picture(pieces2[i][j].x + 0.5, pieces2[i][j].y + 0.5, "img/bomb-water.png", 1, 1);
						}
					} else if (pieces2[i][j].type == "pawn") {
						if (pieces2[i][j].isFire() == true) {
							StdDrawPlus.picture(pieces2[i][j].x + 0.5, pieces2[i][j].y + 0.5, "img/pawn-fire.png", 1, 1);
						} else {
							StdDrawPlus.picture(pieces2[i][j].x + 0.5, pieces2[i][j].y + 0.5, "img/pawn-water.png", 1, 1);
						}
					} else if (pieces2[i][j].type == "shield") {
						if (pieces2[i][j].isFire() == true) {
							StdDrawPlus.picture(pieces2[i][j].x + 0.5, pieces2[i][j].y + 0.5, "img/shield-fire.png", 1, 1);
						} else {
							StdDrawPlus.picture(pieces2[i][j].x + 0.5, pieces2[i][j].y + 0.5, "img/shield-water.png", 1, 1);
						}
					}
				}
			}
		} 
	}


	public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        pieces2 = new Piece[N][N];
        Board b = new Board(false); //draws configured board: calls "b.drawBoard(N);"

        /** Monitors for mouse presses. Wherever the mouse is pressed,
        a new piece appears. */
        while (b.winner() == null) {    
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                Piece selectedPiece = pieces2[(int) x][(int) y];

	            
			        if (b.canSelect((int) x, (int) y) == true) {
			            b.select((int) x, (int) y);  //select runs move: places and removes 
			        }
			        if (b.canEndTurn())  { //&& spacebar has been pressed
			            b.endTurn();
			            b.updateBoard();
			        }
			        // StdDrawPlus.show(100);
	             
        	}            
        }
    }

	//passes tests!
	public Board(boolean shouldBeEmpty) {
		/* The constructor for Board. If shouldBeEmpty is true, initializes an empty Board.
		Otherwise, initializes a Board with the default configuration.*/
    	if (shouldBeEmpty == true){
    		this.drawEmptyBoard(N);
    	} else {
    		this.drawBoard(N);
    	}
  	}

  	//passes tests!
  	public Piece pieceAt(int x, int y) {
  		/* Gets the piece at position (x, y) on the board, or null if there 
  		is no piece. If (x, y) are out of bounds, returns null. */

  		if ((x > N) || (y > N) || (x < 0) || (y < 0)) {   //out of bounds case
  			return null;        	
  		} else {
  			//hasMoved = true;
  			return pieces2[x][y];
  		}
  	}

//Extra methods (private):
//———————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
  	private boolean validMove(Piece p, int newx, int newy) {   //p contains oldx and oldy
		if ((isFireTurn == false) && (p.isKing() == true) && (Math.abs(newx - p.x) == 1) && (Math.abs(newy - p.y) == 1) && (pieces2[newx][newy] == null)) { //water king move
			validMove = true;
			return true;
		} else if ((isFireTurn == true) && (p.isKing() == true) && (Math.abs(newx - p.x) == 1) && (Math.abs(newy - p.y) == 1) && (pieces2[newx][newy] == null)) { //fire king move
			validMove = true;
			return true;
		} else if ((isFireTurn == false) && (p.isKing() == false) && (Math.abs(newx - p.x) == 1) && (p.y - newy == 1) && (pieces2[newx][newy] == null)) { //water non-king move (down)
			validMove = true;
			return true;
		} else if ((isFireTurn == true) && (p.isKing() == false) && (Math.abs(newx - p.x) == 1) && (newy - p.y == 1) && (pieces2[newx][newy] == null)) { //fire non-king move (up)
  			validMove = true;
  			return true;
  		} else {
  			validMove = false;
  			return false;
  		}
  	}

  	private Piece pieceToCapture(Piece p, int newx, int newy) {
  		int dx = p.x - newx;
  		int dy = p.y - newy;
  		if ((dx < 0) && (dy < 0)) {        // moving down,left
  			return pieces2[newx+1][newy+1];
  		} else if((dx > 0) && (dy < 0)) {  // moving down,right
  			return pieces2[newx-1][newy+1];
  		} else if((dx > 0) && (dy < 0)) {  // moving up,left
  			return pieces2[newx+1][newy-1];
  		} else {                           // moving up,right
			return pieces2[newx-1][newy-1];
  		}
  	}

  	private boolean validCapture(Piece p, int newx, int newy) {   //p contains oldx and oldy	
		if ((isFireTurn == false) && (p.isKing() == true) && (p.isFire() == false) && (Math.abs(newx - p.x) == 2) && (Math.abs(newy - p.y) == 2) && (pieceToCapture(p, newx, newy).isFire() == true)) { //water king capture
			validCapture = true;
			System.out.println("cond1");
			return true;
		} else if ((isFireTurn == true) && (p.isKing() == true) && (Math.abs(newx - p.x) == 2) && (Math.abs(newy - p.y) == 2) && (p.isFire() == true) && (pieceToCapture(p, newx, newy).isFire() == false)) { //fire king capture
			validCapture = true;
			System.out.println("cond2");
			return true;
		} else if ((isFireTurn == false) && (p.isKing() == false) && (Math.abs(newx - p.x) == 2) && (p.y - newy == 2) && (p.isFire() == false) && (pieceToCapture(p, newx, newy).isFire() == true)) { //water non-king capture (down)
			validCapture = true;
			System.out.println("cond3");
			return true;
		} else if ((isFireTurn == true) && (p.isKing() == false) && (Math.abs(newx - p.x) == 2) && (newy - p.y == 2) && (p.isFire() == true) && (pieceToCapture(p, newx, newy).isFire() == false)) { //fire non-king capture (up)
			validCapture = true;
			System.out.println("cond4");
			return true;
  		} else {
  			validCapture = false;
  			return false;
  		}
    }
//———————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

  	public boolean canSelect(int x, int y) {
  		//Read Spec for description.
  		if (pieces2[x][y] != null) { //turns!!
  			
  			if (isFireTurn == true) {
	  			if (selectedPiece == null) {
	  				return true;
	  			} else if ((selectedPiece != null) && (selectedPiece.isFire() == true) && (hasMoved == false)) {
	  				return true;
	  			} else {
	  				return false;
	  			}
	  		} else {
	  			if (selectedPiece == null) {
	  				return true;
	  			} else if ((selectedPiece != null) && (selectedPiece.isFire() == false) && (hasMoved == false)) {
	  				return true;
	  			} else {
	  				return false;
	  			}
	  		}

  		} else { //choose blank tile as destination: "else if (pieces2[x][y] == null)""
  			if ((selectedPiece != null) && (hasMoved == false) && (this.validMove(selectedPiece,x,y) == true)) {
  				return true;
  			} else if ((selectedPiece != null) && (hasMoved == false) && (this.validCapture(selectedPiece,x,y) == true) && (selectedPiece.hasCaptured() == false)) {
  				return true;
  			} else {
  				return false;
  			}
  		}
	}

	//passes tests!
   	public void select(int x, int y) {
   			if (selectedPiece == null) {
   				selectedPiece = pieces2[x][y];
	   			selectedX = x;
	   			selectedY = y;
	   			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	   			StdDrawPlus.filledSquare(selectedX + .5, selectedY + .5, .5);
   			} else {
   				selectedPiece = pieces2[x][y];
	   			selectedX = x;
	   			selectedY = y;
   				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	   			StdDrawPlus.filledSquare(selectedX + .5, selectedY + .5, .5);
   				selectedPiece.move(x, y);
   			}
   	}

   	//passes tests!
  	public void place(Piece p, int x, int y) {
  		/* Places p at (x, y). If (x, y) is out of bounds, does nothing. 
  		If another piece already exists at (x, y), p will replace that piece. */
  		
  		if ((x > N) || (y > N) || (x < 0) || (y < 0)) {    //out of bounds case
  			//does nothing        	
  		} else {
  			pieces2[x][y] = p;
  			hasMoved = true;
  		}
  	}

  	//passes tests!
  	public Piece remove(int x, int y) {  
  		/* Executes a remove. Returns the piece that was removed. If the input (x, y) is out of bounds, 
  		returns null and prints an appropriate message. If there is no piece at (x, y), returns null and 
  		prints an appropriate message. */

  		if ((x > N) || (y > N) || (x < 0) || (y < 0)) {    //out of bounds case
  			System.out.println("Remove could not be executed. Out of bounds.");
  			return null;        	
  		} else if (pieces2[x][y] == null) {
  			System.out.println("Remove could not be executed. No piece to remove.");
  			return null;
  		} else {
  			String pieceType = pieces2[x][y].type;
  			boolean pieceIsFire = pieces2[x][y].isFire();

  			Piece pieceOut = new Piece(pieceIsFire, this, x, y, pieceType);
  			pieces2[x][y] = null;
  			return pieceOut;   
  		}
  	}

  	public boolean canEndTurn() {
  		/* Returns whether or not the the current player can end their turn. To be able to end a turn, 
  		a piece must have moved or performed a capture. */
  		
  		if ((selectedPiece != null) && ((hasMoved == true) || (selectedPiece.hasCaptured() == true))) {
  			return true;
  		} else {
  			return false;
  		}
  	}

  	public void endTurn() {
  		/*  Called at the end of each turn. Handles switching of players and anything 
  		else that should happen at the end of a turn. */
  		
  		if (isFireTurn == true) {           //don't need: ((this.canEndTurn() == true)
  			isFireTurn = false;
  		} else if (isFireTurn == false) {   //don't need: ((this.canEndTurn() == true)
  			isFireTurn = true;
  		}
  	}

  	//passes tests!
	public String winner() {
		/* Returns the winner of the game. "Fire", "Water", "No one" (tie / no pieces 
		on the board), or null if the game is not yet over. Assume there is no 
		stalemate situation in which the current player has pieces but cannot legally 
		move any of them (In this event, just leave it at null). Determine the winner 
		solely by the number of pieces belonging to each team. */
		
		int fireCounter = 0;
		int waterCounter = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((pieces2[i][j] != null) && (pieces2[i][j].isFire() == true)) {
					fireCounter++;
				} else if ((pieces2[i][j] != null) && (pieces2[i][j].isFire() == false)) {
					waterCounter++;
				}
			}
		}
		
		if ((fireCounter >= 1) && (waterCounter >= 1)) {
			return null;
		}

		if ((waterCounter == 1) && (fireCounter == 0)) {
			return "Water";
		} else if ((waterCounter == 0) && (fireCounter == 1)) {
			return "Fire";
		} else if ((fireCounter == 0) && (waterCounter == 0)) {
				return "No one";
		} else {
			return null;
		} 		
	}
}