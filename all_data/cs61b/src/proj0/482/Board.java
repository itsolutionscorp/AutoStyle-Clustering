public class Board {
    private Piece [][] pieces = new Piece[8][8];
    private boolean hasSelected, hasMoved, hasCaptured;
    private int previousX, previousY;
    private Piece selectPiece;

    
    
    public static void main(String[] args) {
        Board b = new Board(false);
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N); 
        // pieces = new Piece[N][N]; T.A. said not to use this line because it resets my pieces array to null.


        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            b.drawBoard(N, b);
            double x = StdDrawPlus.mouseX();
            double y = StdDrawPlus.mouseY();
            if (StdDrawPlus.mousePressed()) {

                }
                if (b.canSelect(b.previousX, b.previousY) == true) {
                    b.select(b.previousY, b.previousY);
                    b.pieceAt(b.previousX, b.previousY).move((int) x, (int) y);
                    b.hasMoved = true;
                    b.hasSelected = true;
                }
                if ((b.canEndTurn() == true) && StdDrawPlus.isSpacePressed()) {
                    b.endTurn();
                    b.hasMoved = false; 
                    b.hasSelected = false;
                
                }           
            StdDrawPlus.show(100);
        
        }
    }


    public Board(boolean shouldBeEmpty) {
        previousX = (int) StdDrawPlus.mouseX();
        previousY = (int) StdDrawPlus.mouseY();
        if (shouldBeEmpty == true) {
           pieces = new Piece[8][8];
        }
                        
    	else if (shouldBeEmpty == false) {
             int N = 8;
             for (int i = 0; i < N; i++) { 
                 for (int j = 0; j < N; j++) {
                       
                    if ((j == 7) && (i % 2 == 1)) {
                        pieces[i][j] = new Piece(false, this, i, j, "Pawn");
                                                    }
                    
                    else if ((j == 6) && (i % 2 == 0)) {
                        pieces[i][j] = new Piece(false, this, i, j, "Shield");
                                              
                                               }
                    else if ((j == 5) && (i % 2 == 1)) {
                        pieces[i][j] = new Piece(false, this, i, j, "Bomb"); 
                                                    }

                    else if ((j == 2) && (i % 2 == 0)) {
                        pieces[i][j] = new Piece(true, this, i, j, "Bomb");
                                                    }

                    else if ((j == 1) && (i % 2 == 1)) {
                        pieces[i][j] = new Piece(true, this, i, j, "Shield");
                                                    }

                    else if ((j == 0) && (i % 2 == 0)) {
                        pieces[i][j] = new Piece(true, this, i, j, "Pawn"); 
                                                    }
             
                                            }                            
          
                                        }
                                    }
        }



    private void drawBoard(int N, Board b) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N); 
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                   
                    Piece currentPiece = b.pieces[i][j];

                    if (currentPiece != null) {
                            // System.out.println("hi");

                        if ((currentPiece.isBomb()) && (currentPiece.side() == 0)) {
                            // System.out.println("hi");
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                                                                            }

                        else if ((currentPiece.isShield()) && (currentPiece.side() == 0)) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                                                                                    }

                        else if (currentPiece.side() == 0) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                                                            }

                        else if ((currentPiece.isBomb()) && (currentPiece.side() == 1)) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                                                                            }

                        else if ((currentPiece.isShield()) && (currentPiece.side() == 1)) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                                                                                    }

                        else if (currentPiece.side() == 1) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                                                    }
                                                        }
                                        
                                            }
                                        }
                                    }
    
    private boolean inBounds(int x) {
        if (x >= 0 && x <= 7) {
            return true;
        }
        else {
            return false;
        }
    }
    public Piece pieceAt(int x, int y) {
        if ((inBounds(x) == false) || (inBounds(y) == false)) {
            return null;
        }
        else if (pieces[x][y] == null) {
            return null;
        }
        else {
            return pieces[x][y];
        }
    }
    public boolean canSelect(int x, int y) {
        hasSelected = false;
        hasMoved = false;
        Piece canSelectPiece;
        canSelectPiece = pieceAt(x,y);
        // if (canSelectPiece != null && (canSelectPiece.side() == 0 || canSelectPiece.side() == 1)) {
        
            if (((canSelectPiece != null) && (hasSelected == false)) || ((hasSelected == false) && (hasMoved == false))) {
                    hasSelected = true;
                return true;
                                                                                            }                                                               
            else if (((canSelectPiece == null) && (hasSelected == true) && (hasMoved == false) && (validMove(previousX, previousY, x, y) == true))) {
                    hasSelected = true;
                return true;
                }
                
            // else {
            //     return true;
            //             }
                    // }

            else {
                return false;
                    }

        }


    private boolean validMove(int xi, int yi, int xf, int yf) {
        Piece currentMove = this.pieces[xi][yi];
        hasCaptured = true;
        if (currentMove != null && (pieceAt(xf, yf) == null)) {
            if ((currentMove.side() == 0) && currentMove.isFire() && ((pieceAt(xi + 1, yi + 1).isFire() == false) || (pieceAt(xi - 1, yi + 1).isFire() == false)) && (((xi + 2) == xf) || ((Math.abs(xi - 2)) == xf)) && ((yi + 2) == yf)) {
                hasCaptured = true;
                return true;
            }
            else if ((currentMove.side() == 0) && (currentMove.isFire() == true) && (((xi + 1) == xf) || ((Math.abs(xi - 1)) == xf)) && ((yi + 1) == yf) && currentMove.isShield()) {
                return true;
                                                                                        }
            else if ((currentMove.side() == 0) && (currentMove.isFire() == true) && (((xi + 1) == xf) || ((Math.abs(xi - 1)) == xf)) && ((yi + 1) == yf) && currentMove.isBomb()) {
                return true;
                                                                                            }
            else if ((currentMove.side() == 0) && (currentMove.isFire() == true) && (((xi + 1) == xf) || ((Math.abs(xi - 1)) == xf)) && ((yi + 1) == yf)) {
                return true;
                                                                                        }
            else if ((currentMove.side() == 1) && ((pieceAt(xi + 1, yi - 1).isFire() == false) || (pieceAt(xi - 1, yi - 1).isFire() == true)) && (((xi + 2) == xf) || ((Math.abs(xi - 2)) == xf)) && (Math.abs(yi - 2) == yf)) {
                hasCaptured = true;
                return true;
            }
            else if ((currentMove.side() == 1) && (currentMove.isFire() == false) && (((xi + 1) == xf) || ((Math.abs(xi - 1)) == xf)) && (((yi + 1) == yf) || ((Math.abs(yi - 1)) == yf)) && currentMove.isShield()) {
                return true;
                                                                                        }
            else if ((currentMove.side() == 1) && (currentMove.isFire() == false) && (((xi + 1) == xf) || ((Math.abs(xi - 1)) == xf)) && (((yi + 1) == yf) || ((Math.abs(yi - 1)) == yf)) && currentMove.isBomb()) {
                return true;
                                                                                            }
            else if ((currentMove.side() == 1) && (currentMove.isFire() == false) && (((xi + 1) == xf) || ((Math.abs(xi - 1)) == xf)) && (((yi + 1) == yf) || ((Math.abs(yi - 1)) == yf))) {
                return true;
            }
        }                                                                                        
        return false;

        


     }
    

    public void select(int x, int y) {
        selectPiece = pieceAt(x, y);
        if (selectPiece != null) {       
            previousX = x;
            previousY = y;
            selectPiece.move(previousX, previousY);
            hasSelected = true;
        }
        // if (selectPiece == null) {
        //     selectPiece.move(previousX, previousY);
        // }
        
    }

    public void place(Piece p, int x, int y) {
        if ((inBounds(x) == false) || (inBounds(y) == false) || p == null) {
                                                    }
        else if (this.pieceAt(x, y) == null) { 
            pieces[x][y] = p; 
                                                }
        else if (this.pieceAt(x, y) != null) {
            remove(x, y);
            pieces[x][y] = p;
                                                }                              
            }

    

    public Piece remove(int x, int y) {
        Piece removedPiece;
        if ((inBounds(x) == false) || (inBounds(y) == false)) {
            System.out.println("Error: The input (x,y) is out of bounds.");
            return null;
        }
        else if (pieces[x][y] == null) {
            System.out.println("Error: There is no piece at (x,y).");
            return null;
        }

        else {
            removedPiece = pieces[x][y];
            pieces[x][y] = null;
            return removedPiece;
        }


    }

    public boolean canEndTurn() {
        if (hasMoved == true) {
            return true;
        }
        else {
            return false;
        }
	}

    public void endTurn() {
        if (canEndTurn() == true) {
            hasMoved = false;
        }

	}

    public String winner() {
        return "winner";
	}

}













	
