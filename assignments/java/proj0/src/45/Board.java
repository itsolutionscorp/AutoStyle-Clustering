import java.lang.Math;

public class Board {
	private boolean shouldBeEmpty;
    private int N = 8; // size of board

    private boolean fire_player_turn = true;
    private boolean water_player_turn = false;
   
    private int selected_square_x;
    private int selected_square_y;

    private Piece selected_piece;
    private int selected_piece_origin_xi;
    private int selected_piece_origin_yi;

    private boolean move_occured = false;
    private boolean capture_occured = false;

    //Keeping tabs for winner()
    private int total_fire_pieces; 
    private int total_water_pieces;


    private Piece[][] pieces;

    /*The constructor for Board. 
     *If shouldBeEmpty is true, initializes an empty Board. 
     *Otherwise, initializes a Board with the default configuration
     */
    public Board(boolean beEmpty){
        pieces = new Piece[N][N];

        fire_player_turn = true;
        water_player_turn = false;

        fire_player_turn = true;
        water_player_turn = false;

        total_fire_pieces = 0;
        total_water_pieces = 0;

        this.shouldBeEmpty = beEmpty;

        // ****ADDING INITIAL PIECES TO THE LOCATION ARRAY*****
        if(!shouldBeEmpty)
        {
            for (int i = 0; i < pieces.length; i++) {
                for (int j = 0; j < pieces[0].length; j++) {

                    if(j%2 == 0) // even row
                    {
                        if( i%2 == 0) // even column
                        {
                            if(j == 0)
                            {
                                pieces[i][j] = new Piece(true, this, i , j, "pawn" );
                            }
                            else if (j == 2)
                            {
                                pieces[i][j] = new Piece(true, this, i , j, "bomb" );
                            }
                            else if( j == 6)
                            {
                                pieces[i][j] = new Piece(false, this, i , j, "shield" );
                            }
                        }
                    }
                    else //odd row
                    {
                        if( i%2 != 0) // odd column
                        {
                            if(j ==1)
                            {
                                pieces[i][j] = new Piece(true, this, i , j, "shield" );
                            }
                            else if (j == 5)
                            {
                                pieces[i][j] = new Piece(false, this, i , j, "bomb" );
                            }
                            else if (j ==7)
                            {
                                pieces[i][j] = new Piece(false, this, i , j, "pawn" );
                            } 
                        }
                    }
                }
            }
        }
    }
    private void drawFullBoard(int N) { 

    	//double for loops traverse each square of the board i = row, j = column
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	
                //empty board
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                
                //White background for selected piece
                if(selected_piece != null)
                {
                    if(i == selected_square_x && j == selected_square_y)
                    {
                        StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                    }
                }
                // ****ADDING  PIECES TO THE LOCATION ARRAY*****
                String type_piece;
                String if_isFire;

                if(pieceAt(i,j) != null)
                {
                    if(pieceAt(i, j).isFire())
                    {
                        if_isFire = "-fire";
                    }
                    else
                    {
                        if_isFire = "-water";
                    }
                    if(pieceAt(i, j).isBomb())
                    {
                        type_piece = "bomb";
                    }
                    else if(pieceAt(i, j).isShield())
                    {
                        type_piece = "shield";
                    }
                    else
                    {
                        type_piece = "pawn";
                    }
                    if(pieceAt(i, j).isKing())
                    {
                        if_isFire += "-crowned";
                    }
                    if_isFire += ".png";              
                    StdDrawPlus.picture(i + .5, j + .5, "img/" + type_piece + if_isFire, 1, 1);
                }
            }   
        }
    }

	/*Gets the piece at position (x, y) on the board, 
	 *or null if there is no piece. 
	 *If (x, y) are out of bounds, returns null. */
	public Piece pieceAt(int x, int y){
        boolean inBounds = ( x >= 0) && (x < pieces.length) && ( y >= 0) && (y < pieces[0].length);
		if(inBounds && pieces[x][y] != null)
        {
            return pieces[x][y];
        }
        return null;
	} 

	/*Returns true if there is a piece the piece at (x, y) and it can be selected.*/
	public boolean canSelect(int x, int y){
        Piece desired_piece = this.pieceAt(x, y);
        //*****Trying to select a NON EMPTY square****
        if(desired_piece != null)
        {
           //need to check if it's correct player's turn
            if(desired_piece.isFire() == fire_player_turn)
            {
                //The player has not selected a piece yet. OR
                //The player has selected a piece, but did not move it 
                if(this.selected_piece == null)
                {
                    return true;
                }
                else if(!move_occured)
                {
                    return true;
                }
            }
        }
        //******Trying to select an EMPTY square*******
        else
        {
            //selecting a place for piece to move to
            if( this.selected_piece != null && !move_occured)
            {
                //need to check this square is a valid move location
                if(validMove(selected_piece_origin_xi, selected_piece_origin_yi, x, y))
                {
                    return true;
                }
            }  
            //doing a mult-capture
            else if(this.selected_piece != null && selected_piece.hasCaptured())
            {
                if(validMove(selected_piece_origin_xi, selected_piece_origin_yi, x, y))
                {
                    return true; 
                }
            }
        }
		return false;
	}

	/*Returns true if the piece at (xi, yi) can either move to (xf, yf) 
	 *or capture to (xf, yf) in a valid fashion compatible with the rules */
	private boolean validMove(int xi, int yi, int xf, int yf){
        if(pieceAt(xi, yi).isKing())
        {
            //pieces are kings --> can move up or down 
            if(Math.abs(yf - yi) == 1 && Math.abs(xf - xi) == 1)
            {
                if(move_occured) //for case where you capture something and try to keep moving illegally afterwards
                {
                    return false;
                }
                return true;
            }
        }
        if(pieceAt(xi, yi).isFire() || pieceAt(xi, yi).isKing()) 
        {            
            //pieces are fire pieces--> can only move up 1
            if(yf - yi == 1 && Math.abs(xf - xi) == 1)
            {    
                if(move_occured) //for case where you capture something and try to keep moving illegally afterwards
                {
                    return false;
                }            
                return true;
            }
            //UP CAPTURES
            else if(yf - yi == 2) //piece moved up
            {                   
                if(xf - xi == 2) //dest is right & up
                {
                    if(pieceAt(xf - 1, yf - 1) != null && pieceAt(xf - 1, yf - 1).isFire() == false) // check that capture is correct piece type (opposite) right diag
                    {
                        return true;
                    }
                    else if(pieceAt(xf - 1, yf - 1) != null && pieceAt(xi, yi).isKing())
                    {
                        return true;
                    }
                }
                else if(xf - xi == -2) //moved left and up
                {
                    if(pieceAt(xf + 1, yf - 1) != null && pieceAt(xf + 1, yf - 1).isFire() == false) // // check that capture is correct piece type- left diag
                    {
                        return true;
                    }
                    else if(pieceAt(xf + 1, yf - 1) != null && pieceAt(xi, yi).isKing())
                    {
                        return true;
                    }
                }
            }
        }

        if(!pieceAt(xi, yi).isFire() || pieceAt(xi, yi).isKing()) 
        {
            //pieces are water pieces --> can only move down
            if(yf - yi == -1 && Math.abs(xf - xi) == 1)
            {
                if(move_occured) //for case where you capture something and try to keep moving illegally afterwards
                {
                    return false;
                } 
                return true;
            } 
            //DOWN CAPTURE
            else if(yf - yi == -2) //piece moved down -- must be a king or water
            {
                if(xf - xi == 2) //moved right & down
                {
                    if(pieceAt(xf - 1, yf + 1) != null && pieceAt(xf - 1, yf + 1).isFire() == true) //check correct type right diag capture
                    {
                        return true;
                    }
                    else if(pieceAt(xf - 1, yf + 1) != null && pieceAt(xi, yi).isKing())
                    {
                        return true;
                    } 
                }
                else if(xf - xi == -2) //moved left and down
                {
                    if(pieceAt(xf + 1, yf + 1) != null && pieceAt(xf + 1, yf + 1).isFire() == true) //check correct piece type left diag
                    {
                        return true;
                    }
                    else if(pieceAt(xf + 1, yf + 1) != null && pieceAt(xi, yi).isKing())
                    {
                        return true;
                    }
                }
            }
        }
		return false;
	}

	/* Selects the piece at (x, y) if possible. */
	public void select(int x, int y){
        //assumes canSelect (x,y) returns true
        selected_square_x = x;
        selected_square_y = y;

        //if square selected has a piece- get ready to move it
        if(this.pieceAt(x, y) != null)
        {
            selected_piece = pieceAt(x, y);
            selected_piece_origin_xi = x;
            selected_piece_origin_yi = y;
        }
        //slected an empty square
        else if(this.pieceAt(x, y) == null && selected_piece != null) 
        {
            //move your recently selected piece to that square
            selected_piece.move(x, y);
            move_occured = true;
            selected_piece_origin_xi = x; //Needed for multicaptures
            selected_piece_origin_yi = y; 

            if(selected_piece.hasCaptured())
            {
                capture_occured = true;    
            }
        }
	}

	/*Places p at (x, y). If (x, y) is out of bounds or if p is null, does nothing*/
	public void place(Piece p, int x, int y){
        // //http://stackoverflow.com/questions/19672301/checking-out-of-bounds-in-java
        boolean inBounds = ( x >= 0) && (x < pieces.length) && ( y >= 0) && (y < pieces[0].length);
        if(inBounds)
        {
            pieces[x][y] = p;
            if(p != null)
            {
                if(p.isFire())
                {
                    total_fire_pieces = total_fire_pieces + 1;
                }
                else
                {
                    total_water_pieces = total_water_pieces + 1;
                }   
            }
        }
	}

	/*Executes a remove. Returns the piece that was removed*/
	public Piece remove(int x, int y){
        boolean inBounds = ( x >= 0) && (x < pieces.length) && ( y >= 0) && (y < pieces[0].length);
        if(inBounds)
        {
            Piece oldPiece = pieces[x][y];
            if(oldPiece != null)
            {
                if(oldPiece.isFire())
                {
                    total_fire_pieces = total_fire_pieces - 1;
                }
                else
                {
                    total_water_pieces = total_water_pieces - 1;
                }
                pieces[x][y] = null;

                return oldPiece;
            }
            return null;
        }
		return null;
	}

    /*Returns whether or not the the current player can end their turn.
    /*To be able to end a turn, a piece must have moved or performed a capture.*/
	public boolean canEndTurn(){
		if(move_occured || capture_occured)
        {
            return true;
        }
        return false;
	}

	/*Handles switching of players and anything else that should happen at the end of a turn*/
	public void endTurn(){
        //switch the players
        this.fire_player_turn = !this.fire_player_turn;
        this.water_player_turn = !this.water_player_turn;

        //reset all the instance variables here for select & canselect
        selected_square_x = -1;
        selected_square_y = -1;

        if(selected_piece != null)
        {
            selected_piece.doneCapturing(); 
        }
        selected_piece = null;
        selected_piece_origin_xi = -1;
        selected_piece_origin_yi = -1;

        move_occured = false;
        capture_occured = false;
	}

	/*Returns the winner of the game*/
	public String winner(){
        //keep track of total number of peices on each side
        int total_pieces = total_fire_pieces + total_water_pieces;

        //game still in progress, pieces are on the board
        if(total_fire_pieces > 0 && total_water_pieces > 0)//game still in progess
        {
            return null;
        }
        //water wins
        else if(total_water_pieces > 0 && total_fire_pieces == 0)
        {
            return "Water";
        }
        //Fire wins
        else if(total_fire_pieces > 0 && total_water_pieces ==0)
        {
            return "Fire";
        }
        else if(total_pieces == 0) //explosion capture eliminates all pieces or empty board
        {
            return "No one";
        }
        return null;	
	}

    /*starts a GUI supported version of the game*/
    public static void main (String[] args) {
        Board mainBoard = new Board(false);
        int N = 8;        
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        
        while(true) {
            mainBoard.drawFullBoard(N);
            /** Monitors for mouse presses. */
            if (StdDrawPlus.mousePressed()) 
            {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if(mainBoard.canSelect((int) x, (int) y))
                {
                    mainBoard.select((int) x, (int) y);
                }
            }
            /** Monitors for spacebar presses. */
            else if(StdDrawPlus.isSpacePressed())
            {
                if(mainBoard.canEndTurn())
                {
                    mainBoard.endTurn();
                }
            } 
            StdDrawPlus.show(100); 
        }
    }
}
