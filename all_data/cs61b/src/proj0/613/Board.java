/** Project 0 Checkers
 *  @author Jeffrey Lau
 */

public class Board {
		/** Empty Board. */
	
	public Piece [][] piece;
	public Boolean shouldBeEmpty;
	// create string variable to keep track of file path
	private String s;
	// create boolean variable to check whether or not you have moved
	private Boolean ifMoved = false;
	// create boolean variable to keep track of whose turn it is (0 is fire, 1 is water)
	private Boolean urTurn = 0;
	// create variable to store piece that is selected
	private Piece selectedPiece;
	
	public static void main (String[] args) {
		Board b = new Board(Boolean.parseBoolean(args[0]));	
		int N = 8;
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
		while (true) {
			b.drawBoard(N);
		    StdDrawPlus.show(100);
		}
	}

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (piece[i][j] != null) {
                	s = "img/";
                	// check to see what piece it is; then draw that piece
                    if (piece[i][j].isBomb() == true) {
                    	s = s + "bomb-";
                    } else if (piece[i][j].isShield() == true) {
                    	s = s + "shield-";
                    } else {
                    	s = s + "pawn-";
                    }
                    if (piece[i][j].isFire == true) {
                    	s = s + "fire.png";
                    } else {
                    	s = s + "water.png";
                    }
               StdDrawPlus.picture(i + .5, j + .5, s, 1, 1);
                }
            }
        }
    }

     public Board(Boolean shouldBeEmpty) {
    	int N = 8;
    	this.shouldBeEmpty = shouldBeEmpty;
    	piece = new Piece[N][N]; 
        for (int i = 0; i < N; i += 2) {
            piece[i][0] = new Piece(true, this, i, 0, "pawn");
        } for (int i = 1; i < N; i += 2) {
            piece[i][1] = new Piece(true, this, i, 1, "shield");
        } for (int i = 0; i < N; i += 2) {
            piece[i][2] = new Piece(true, this, i, 2, "bomb");
        } for (int i = 1; i < N; i += 2) {
            piece[i][5] = new Piece(false, this, i, 5, "bomb");
        } for (int i = 0; i < N; i += 2) {
            piece[i][6] = new Piece(false, this, i, 6, "shield");
        } for (int i = 1; i < N; i += 2) {
            piece[i][7] = new Piece(false, this, i, 7, "pawn");
        }
     }

     public Piece pieceAt(int x, int y) {
         // gets piece at position (x,y) on the board
         // null if there is no piece or (x,y) are out of bounds
         if ((x >= 0) && (y >= 0) && (x < 8) && (y < 8)) {  // use 8 or N
             if (piece[x][y] != null) {
            	 return piece[x][y];
             } else {
            	 return null;
             }
         } else {
             return null;
         }
     }

     public boolean canSelect(int x, int y) {
    		 // check if it's the correct person's turn (0/1)
    	     // check if the square is empty or not (null/not null)
    	 	 // check if the piece has moved or not (true/false)

        if ((piece.side() == urTurn) && (pieceAt(x,y) == null) && (ifMoved == false)) {
    		if ((selectedPiece != null) && (piece.validMove() == true) {
    			 return true;
    		} else if ((selectedPiece.hasCaptured() == true) && (piece.validMove() == true) {
    			 // validMove(int xi, int yi, int xf, int yf)
    			 // validMove(selectedPieceX, selectedPieceY, x, y)

            } 
            else if(piece.side() == urTurn) && (pieceAt(x,y) != null) && (selectedPiece == null)) {
    			 return true;
    		 } else if((piece[x][y].side() == urTurn) && (pieceAt(x,y) != null) && (selectedPiece != null) && (ifMoved == false)) {
    			 return true;
    		 }
    	}   	 
     }

     // helper method to access the coordinates of the selectedPiece
     // look through board array to find selectedPiece
     private int selectedPieceX(Piece piece) {
        int tempX = 0;
     	for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (piece == piece[i][j]) {
                    tempX = i;
                }
            }
        }
        return tempX;    
     }

     private int selectedPieceY(Piece piece) {
        int tempY = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (piece == piece[i][j]) {
                    tempY = j;
                }
            }
        }
        return tempY;    
     }

     private boolean validMove(int xi, int yi, int xf, int yf) {
        // check direction (up or down)
        // check if capture (move 2 spots), if piece in between is of opposite team

        // check directionality
        if ((yf - yi == 1) && (xf != xi) && (selectedPiece.side() == 0) {
                return true;
        } elseif ((yf - yi == -1) && (xf != xi) && (selectedPiece.side() == 1)  {
            return true;
        }
        // check if capture
        if ((yf - yi == 2) && (xf - xi == 2) && (pieceAt( (xf+xi)/2, (yf+yi/2).side() != selectedPiece.side())) {
            return true;
        } else if ((yf - yi == 2) && (xf - xi == -2) && (pieceAt( (xf+xi)/2, (yf+yi)/2).side() != selectedPiece.side())) {
            return true;
        } else {
            return false;
        }
     }
    
     public void select(int x, int y) {
    	 // select square at (x,y)
         // assumes canSelect (x,y) returns true 
        

     }

    public void place(Piece p, int x, int y) {
        /* places p at (x,y)
            does nothing: if (x,y) is out of bounds or p is null
            p replaces existing piece at (x,y)
        */
        if ((p != null) && (x < N) && (y < N) {
            Piece p = Piece pieceAt(x,y);  
        }
    }

    public Piece remove(int x, int y) {
        /* return piece that is removed
            return null and prints appropriate message if input(x,y) out of bounds
        */
        if ()
    }

    // public boolean canEndTurn() {
    //     /* returns whether or not current player can end turn
    //         piece must have been moved or perform capture 
    //     */
    // }

    // public void endTurn() {
    //     // handles switching of players and end of turn moves
    // }

    // public String winner() {
    //     // return winner of game "Fire", "Water", "No one", or "null"
    // }
}