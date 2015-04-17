public class Board {
    private Piece initPieces[][];
    private int selected = -1;  //can select based heavily off selected number
        //begin as -1, 
        //0 when selecting pieces, 
        //-2 after empty move, 
        //+= 1 after first capture, etc., 
        //-2 for when can't select again
    private int sX = -1; //store selected X coordinate, initialize off board so treated as null
    private int sY = -1; //store selected Y coordinate, ""
    private boolean moved = false; //if a turn involved a move, used for canEndTurn condition
    private String turn = "fire"; //fire begins, turn alternates with endTurn
    private int fireCount;
    private int waterCount; //keep track of # of each type of piece on the board, used for winner

    private void redrawBoard(int N) { //citation: received assistance from Nandita Sampath for this method
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                //from StdDrawDemo
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                //Redrawing pieces array to update based on selection/move
                if (initPieces[i][j] != null) {
                    drawImages(initPieces[i][j], i, j);
                }
            }
        }
        //if a piece has been selected, change square to white
        if (selected != -1) {
            if (initPieces[sX][sY] != null) { //sX and sY denote selected pieces
                StdDrawPlus.filledSquare(sX + .5, sY + .5, .5);  
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                drawImages(initPieces[sX][sY], sX, sY);
            }
        }

    }


	public static void main (String args[]) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board board = new Board(false);
        board.drawBoard(N);

            //monitors for mouse presses -> select conditions
            //monitors for space pressed -> end turn conditions
            while(true) {
                StdDrawPlus.show(100);
                if (StdDrawPlus.mousePressed()) {       //
                    double x = StdDrawPlus.mouseX();    //
                    double y = StdDrawPlus.mouseY();    //
// System.out.println("mouse pressed x, y: " + ((int) x) + ", " + ((int) y)); //TAKE THIS AWAY LATER
                    if (board.canSelect((int) x, (int) y)) {
// System.out.println("can select: " + ((int) x) + " and " + ((int) y)); //TAKE IT OUT LASTER
                        board.select((int) x, (int) y);
// System.out.println("selected pieces: " + ((int) x) + ", " + ((int) y));
// System.out.println("selected count = " + board.selected);
// System.out.println("sX = " + board.sX);
// System.out.println("sY = " + board.sY);
                        board.redrawBoard(N); //redraw board after every selection.
 // System.out.println("Something is selected");
                    }
                }

                //checking if spacebar is pressed
                if (StdDrawPlus.isSpacePressed() == true) {
                    if (board.canEndTurn()) { //checking if can end the turn
                        board.endTurn();
//System.out.println("turn ended");
                    }
                    board.redrawBoard(N);
                }

                //running winner method for every turn, will return a value when a winning condition is reached
                if (board.winner() != null) {
                    board.winner();
                }
        }
	}


    private void drawBoard(int N) { //from StdDrawDemo
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                drawImages(initPieces[i][j], i, j);
                }
            }
        }


    //board constructor
	public Board(boolean shouldBeEmpty) { 
        //initialize pieces in initial positions on board
        int N = 8;
        if (shouldBeEmpty == true) {
            initPieces = new Piece[N][N]; //just making a blank board
        } else {
            //creating pieces array to be used and updated in other methods
            initPieces = new Piece[N][N];
            for(int i = 0; i < N; i += 1) {
                for (int j = 0; j < N; j += 1) {
                    if (i % 2 == 0) { //on rows 1, 3, 5, 7
                        if (j == 0) { //bottom row fire pawns
                            initPieces[i][j] = new Piece(true, this, i, j, "pawn");
                        } else if (j == 2) { //3rd row fire bombs
                            initPieces[i][j] = new Piece(true, this, i, j, "bomb");
                        } else if (j == 6) { //7th row water shield
                            initPieces[i][j] = new Piece(false, this, i, j, "shield");
                        }
                    } else if (Math.abs(i) % 2 == 1) { //rows 2, 4, 6, 8
                        if (j == 1) { //2nd row fire shield
                            initPieces[i][j] = new Piece(true, this, i, j, "shield");
                        } else if (j == 5) { //6th row water bomb
                            initPieces[i][j] = new Piece(false, this, i, j, "bomb");
                        } else if (j == 7) { //top row water pawns
                            initPieces[i][j] = new Piece(false, this, i, j, "pawn");
                        }
                    }
                }
            }
        }
    }

    //draw actual piece images individually on the board
    //called everytime selection is made and drawing updated
    private void drawImages(Piece p, int x, int y) {
        if (initPieces[x][y] != null) {
            if (initPieces[x][y].isFire()) { //for all the fire pieces
                if (initPieces[x][y].isKing()) { //if they have been kinged
                    if (initPieces[x][y].isShield() == true) {
                        StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire-crowned.png", 1, 1);
                    } else if (initPieces[x][y].isBomb() == true) { //if it's a fire bomb
                        StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire-crowned.png", 1, 1);                        
                    } else { //if its a crowned fire pawn
                        StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire-crowned.png", 1, 1);                        
                    }    
                }
                else if (initPieces[x][y].isShield() == true) {
                    StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
                } 
                else if (initPieces[x][y].isBomb() == true) { //if it's a fire bomb
                    StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);                        
                } 
                else { //if its a fire pawn
                    StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);                        
                }
            } 
            else { //if it's a water piece (isFire is false)
                if (initPieces[x][y].isKing()) { //these are kinged water pieces
                    if (initPieces[x][y].isShield() == true) {
                        StdDrawPlus.picture(x + .5, y + .5, "img/shield-water-crowned.png", 1, 1);
                    } else if (initPieces[x][y].isBomb() == true) { //if it's a fire bomb
                        StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water-crowned.png", 1, 1);                        
                    } else { //if its a fire pawn
                        StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water-crowned.png", 1, 1);                        
                    }                                            
                }
                else if (initPieces[x][y].isShield() == true) {
                    StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
                } 
                else if (initPieces[x][y].isBomb() == true) { //if it's a fire bomb
                    StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);                        
                } 
                else { //if its a fire pawn
                    StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);                        
                }                        
            } 
        }
    }

    //returns the piece at a given position on the board
	public Piece pieceAt(int x, int y) {
        if (x >= 8 || x < 0 || y >= 8|| y < 0) { //if out of bounds
            return null;
        } 
        return initPieces[x][y]; //returning the piece from initial array
    }

    //checks if a piece or square can be selected - contingent on many conditions
    //tracked heavily using selected incrementer
	public boolean canSelect(int x, int y) {
        //can't click out of bounds or red square
        if ((x >= 8 || x < 0 || y >= 8|| y < 0) || (Math.abs(x + y) % 2 == 1)) {
            return false;
        }

        //can't continue turn if player has moved one square already
        if (selected == -2) { //-2 -> a piece has moved one square
            return false;
        }

        //can't select another piece after capture
        if (selected > 0 && pieceAt(x, y) != null) { //>0 -> a capture has been made
            return false;
        }
        
        //can select if piece has made capture and selecting another valid square
        if (selected > 0 && pieceAt(x, y) == null) { //finished capture and selected blank space
            if (pieceAt((sX+x)/2, (sY+y)/2) != null) { //if there's a piece between the two
                if ((turn == "fire" && !pieceAt((sX+x)/2, (sY+y)/2).isFire())
                || (turn == "water" && pieceAt((sX+x)/2, (sY+y)/2).isFire())) { //its an opponent piece in between
                    return true;
                }
            }
        }

        //can select if there's a piece there and nothing's been selected yet
        if (initPieces[x][y] != null) {
            if (selected == -1 || (selected != -1 && moved == false)) { //if nothing has been selected yet and there's a piece there 
                //or if piece has been selected but not moved
                if ((turn == "fire" && initPieces[x][y].isFire()) ||
                    (turn == "water" && initPieces[x][y].isFire() == false)) { //can select pieces on respective sides
                    return true;
                }
            }
        }
        //can select empty square if haven't moved if 
            //1. can move one square
            //2. can capture
        else if (pieceAt(x, y) == null && selected != -1) { //click on empty square, when piece has been selected
            if (moved == false) {
                if (pieceAt(sX, sY).isKing() && turn == "fire" && pieceAt(sX, sY).isFire() == true) { //if selected piece is a fire king
                    //can move 4 directions
                    if ((Math.abs(x - sX) == 1 && Math.abs(y - sY) == 1) //if empty square diagonally above selected piece
                    || ((sX + 1 < 8) && (sY + 1 < 8) && pieceAt(sX+1, sY+1) != null && !pieceAt(sX+1, sY+1).isFire() && (x - sX == 2) && (y - sY == 2))
                        //can move 2 spots diagonally up/right and there's a piece water piece between the two
                    || ((sX - 1 >= 0) && (sY + 1 < 8) && pieceAt(sX-1, sY+1) != null && !pieceAt(sX-1, sY+1).isFire() && (x - sX == -2) && (y - sY == 2))
                        //can move 2 spots diagonally up/left and water piece between
                    || ((sX - 1 < 8) && (sY - 1 >= 0) && pieceAt(sX-1, sY-1) != null && !pieceAt(sX-1, sY-1).isFire() && (x - sX == -2) && (y - sY == -2))
                         //can move 2 spots diagonally Down/left and there's a water piece between the two
                    || ((sX + 1 < 8) && (sY - 1 >= 0) && pieceAt(sX+1, sY-1) != null && !pieceAt(sX+1, sY-1).isFire() && (x - sX == 2) && (y - sY == -2))) {
                        //can move 2 spots diagonally Down/Right and water piece between
                        return true;
                    }
                }
                else if (pieceAt(sX, sY).isKing() && turn == "water" && !pieceAt(sX, sY).isFire()) { //if selected piece is a water king
                    if ((Math.abs(x - sX) == 1 && Math.abs(y - sY) == 1) //if empty square diagonally above selected piece
                    || ((sX + 1 < 8) && (sY + 1 < 8) && pieceAt(sX+1, sY+1) != null && pieceAt(sX+1, sY+1).isFire() && (x - sX == 2) && (y - sY == 2))
                        //can move 2 spots diagonally up/right and there's a piece fire piece between the two
                    || ((sX - 1 >= 0) && (sY + 1 < 8) && pieceAt(sX-1, sY+1) != null && pieceAt(sX-1, sY+1).isFire() && (x - sX == -2) && (y - sY == 2))
                        //can move 2 spots diagonally up/left and fire piece between
                    || ((sX - 1 < 8) && (sY - 1 >= 0) && pieceAt(sX-1, sY-1) != null && pieceAt(sX-1, sY-1).isFire() && (x - sX == -2) && (y - sY == -2))
                         //can move 2 spots diagonally Down/left and there's a fire piece between the two
                    || ((sX + 1 < 8) && (sY - 1 >= 0) && pieceAt(sX+1, sY-1) != null && pieceAt(sX+1, sY-1).isFire() && (x - sX == 2) && (y - sY == -2))) {
                        //can move 2 spots diagonally Down/Right and fire piece between
                        return true;
                    }
                }
                
                //fire turn selecting fire, fire must move upwards
                else if (turn == "fire" && pieceAt(sX, sY).isFire()) { 
                    if ((Math.abs(x - sX) == 1 && (y - sY == 1)) 
                    || ((sX + 1 < 8) && (sY + 1 < 8) && pieceAt(sX+1, sY+1) != null && !pieceAt(sX+1, sY+1).isFire() && (x - sX == 2) && (y - sY == 2))
                        //can move 2 spots diagonally up/right and there's a piece water piece between the two
                    || ((sX - 1 >= 0) && (sY + 1 < 8) && pieceAt(sX-1, sY+1) != null && !pieceAt(sX-1, sY+1).isFire() && (x - sX == -2) && (y - sY == 2))) {
                        //can move 2 spots diagonally up/left and water piece between
                        return true;
                    }
                }

                //water turn selecting water, must move down the board
                else if (turn == "water" && !pieceAt(sX, sY).isFire()) {
                    if ((Math.abs(x - sX) == 1 && (y - sY == -1))       
                        //if empty square diagonally below              
                    || ((sX - 1 < 8) && (sY - 1 >= 0) && pieceAt(sX-1, sY-1) != null && pieceAt(sX-1, sY-1).isFire() && (x - sX == -2) && (y - sY == -2))
                        //can move 2 spots diagonally Down/left and there's a fire piece between the two
                    || ((sX + 1 < 8) && (sY - 1 >= 0) && pieceAt(sX+1, sY-1) != null && pieceAt(sX+1, sY-1).isFire() && (x - sX == 2) && (y - sY == -2))) {
                        //can move 2 spots diagonally Down/Right and fire piece between
                        return true;
                    }
                }                
            }
        }
		return false; //if none of the selectable conditions are true
	}

    //assuming can select is true, 
	public void select(int x, int y) {
        if (selected == -1) { //if nothing's been selected yet
            sX = x; //update selected coordinates
            sY = y;
            selected += 1; //increment selected to 0
        }

        //if a piece has been selected
        else if (selected != -1) {
// System.out.println("entering the select else if");
// System.out.println("x and y are: " + x + ", " + y);
// System.out.println("sx and sy are: " + sX + ", " + sY);
// System.out.println("old pieceAt(sx, sy) = " + pieceAt(sX, sY)); //ok so there's a piece there

            //if selecting a different empty space
            if (pieceAt(x, y) == null && sX != x && sY != y) {
// System.out.println("am i entering this loop?");

                if (Math.abs(x - sX) == 1 && Math.abs(y - sY) == 1) { //move one space and end turn
// System.out.println("am i entering this inner loop?");                
                    selected = -2; //-2 so canselect from now on will return false
                }
                else if (Math.abs(x - sX) == 2 && Math.abs(y - sY) == 2) { //capture has happened
                    if (pieceAt(sX, sY).isBomb()) { //everything explodes and turn ends
                        selected = -2; //reset so can't select anymore
                    }
                    else if (!pieceAt(sX, sY).isBomb()) {
                        selected += 1; //increment select to > 0 so can now test if more pieces can be selected
                    }
                }
                pieceAt(sX, sY).move(x, y);
//System.out.println("moved to: " + x + ", " + y);
                moved = true; //used for endturn - show that a move has been made in this turn
// System.out.println("new pieceAt(x, y) = " + pieceAt(x, y));
// System.out.println("new pieceAt(sx, sy) = " + pieceAt(sX, sY));
            }
            //switching selections
            else if (pieceAt(x, y) != null && (sX != x || sY != y)) { //if you've selected a piece but want to select another
//System.out.println("selecting another piece");
                selected = 0;  //0 when only selecting pieces
                moved = false; //haven't moved it, just selecting another piece
            }
            sX = x;
            sY = y; //update selected piece coordinates
        }
	}

    //place a piece at board location x and y
	public void place(Piece p, int x, int y) {
        if (p == null || x >= 8 || x < 0 || y >= 8|| y < 0) {
            return;
        } 
        else { 
            initPieces[x][y] = p; //reassign initial array to p
        }
	}

    //remove whatever is at x, y
	public Piece remove(int x, int y) {
        if (initPieces[x][y] == null) {
            System.out.println("No piece here to remove");
            return null;
        }
        if (x >= 8 || x < 0 || y >= 8|| y < 0) {
            System.out.println("Input out of bounds");
            return null;
        }        
        Piece temp = pieceAt(x, y); //temporarily stores removed piece to be returned       
//System.out.println(pieceAt(x, y) + " has been removed."); //for testing purposes
        initPieces[x][y] = null; //clears that space on the board
        return temp; //return the removed piece
	}

    //if a move (controlled in select) has been executed during the turn, can end it
	public boolean canEndTurn() {
        return moved;
	}

    //switch player, reset moved to false and reset selection
	public void endTurn() {
        if (turn == "fire") {
            turn = "water";
        }
        else if (turn == "water") {
            turn = "fire";
        }
        moved = false;
        selected = -1;
        sX = -1;
        sY = -1; 

        //reset every piece's hasCaptured to false to begin another turn
        //FIXING AG TEST 13 FINAL FAILED TEST
        for (int i = 0; i < 8; i += 1) {
            for (int j = 0; j < 8; j += 1) {
                if (pieceAt(i, j) != null) {
                    pieceAt(i, j).doneCapturing();
//System.out.println(pieceAt(i, j).hasCaptured());
                }
            }
        }

	}

    //loop through pieces array to determine how many of each type are left, return when one or both reach 0.
	public String winner() {
        fireCount = 0;
        waterCount = 0;
        for (int i = 0; i < 8; i += 1) {
            for (int j = 0; j < 8; j += 1) {
                if (pieceAt(i, j) != null) {
                    if (pieceAt(i, j).isFire()) { // counting fire pieces
                        fireCount += 1;
                    }
                    else if (!pieceAt(i, j).isFire()) { //counting water pieces
                        waterCount += 1;
                    }
                }
            }
        }
    
        if (fireCount == 0 && waterCount > 0) {
            return "Water";
        }
        else if (waterCount == 0 && fireCount > 0) {
            return "Fire";
        } 
        else if (waterCount == 0 && fireCount == 0) {
            return "No one";
        }
        else {
            return null;
        }
	}
}







