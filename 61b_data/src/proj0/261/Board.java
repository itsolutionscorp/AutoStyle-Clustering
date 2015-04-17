// import java.StdDraw;

/**click
wait
click2
update
*/

public class Board {

	private boolean boardselected; 
    private boolean moved;
    private boolean hascaptured;


    private boolean isfireturn;

    private boolean emptyvalue;

    private int selectx;
    private int selecty;

    private Piece[][] pieces;

	public static void main(String[] args) {
		/** starts a GUI supported version of the game.
		*/
        Board b=new Board(false);
        StdDrawPlus.setXscale(0,8);
        StdDrawPlus.setYscale(0,8);
        
        while (true){
            b.drawBoard(8);
            if (StdDrawPlus.mousePressed()){
                int mousex=(int) StdDrawPlus.mouseX(); 
                int mousey=(int) StdDrawPlus.mouseY(); 
                if (b.canSelect(mousex,mousey)){
                    b.select(mousex,mousey);
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    StdDrawPlus.filledSquare(mousex + .5, mousey + .5, .5);
                    StdDrawPlus.show(100);
                  
                }
            }
                   
            if (StdDrawPlus.isSpacePressed()){
                if (b.canEndTurn()){
                    b.endTurn();
                }
            }

            // if (b.winner()!=null){
            //    b.winner();
            // }

            StdDrawPlus.show(100);
        }   
}

    private void drawBoard(int N){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
            }
    
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(pieces[i][j]!=null){
                    if (pieces[i][j].isFire()&&pieces[i][j].isKing()&&pieces[i][j].isBomb()) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                    if (pieces[i][j].isFire()&&!pieces[i][j].isKing()&&pieces[i][j].isBomb()) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                    if (pieces[i][j].isFire()&&pieces[i][j].isKing()&&pieces[i][j].isShield()) StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                    if (pieces[i][j].isFire()&&!pieces[i][j].isKing()&&pieces[i][j].isShield()) StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                    if (pieces[i][j].isFire()&&pieces[i][j].isKing()&&!pieces[i][j].isShield()&&!pieces[i][j].isBomb()) StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                    if (pieces[i][j].isFire()&&!pieces[i][j].isKing()&&!pieces[i][j].isShield()&&!pieces[i][j].isBomb()) StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);

                    if (!pieces[i][j].isFire()&&pieces[i][j].isKing()&&pieces[i][j].isBomb()) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                    if (!pieces[i][j].isFire()&&!pieces[i][j].isKing()&&pieces[i][j].isBomb()) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                    if (!pieces[i][j].isFire()&&pieces[i][j].isKing()&&pieces[i][j].isShield()) StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                    if (!pieces[i][j].isFire()&&!pieces[i][j].isKing()&&pieces[i][j].isShield()) StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                    if (!pieces[i][j].isFire()&&pieces[i][j].isKing()&&!pieces[i][j].isShield()&&!pieces[i][j].isBomb()) StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                    if (!pieces[i][j].isFire()&&!pieces[i][j].isKing()&&!pieces[i][j].isShield()&&!pieces[i][j].isBomb()) StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                }
            }
        }
    }



    public Board(boolean shouldBeEmpty){
    	/** The constructor for Board. 
		If shouldBeEmpty is true, initializes an empty Board. 
   		Otherwise, initializes a Board with the default configuration. 
    	Note that an empty Board could possibly be useful for testing purposes.
    	*/
        pieces=new Piece[8][8];

        boardselected=false; 
        moved=false;
        hascaptured=false;

        isfireturn=true;

        emptyvalue=shouldBeEmpty;

        selectx=1000;
        selecty=1000;

        if (!shouldBeEmpty){

            for (int i = 0; i<8; i++) {
                if (i%2==0){
                    pieces[i][0]=new Piece(true, this, i, 0, "pawn");
                } else {
                    pieces[i][0]=null;
                }
            }

            for (int i = 0; i<8; i++) {
                if ((i+1)%2==0){
                    pieces[i][1]=new Piece(true, this, i, 1, "shield");
                } else {
                    pieces[i][1]=null;
                }
            }

            for (int i = 0; i<8; i++) {
                if (i%2==0){
                    pieces[i][2]=new Piece(true, this, i, 2, "bomb");
                } else {
                    pieces[i][2]=null;
                }
            }


            for (int i = 0; i<8; i++) {
                if ((i+1)%2==0){
                    pieces[i][7]=new Piece(false, this, i, 7, "pawn");
                } else {
                    pieces[i][7]=null;
                }
            }

            for (int i = 0; i<8; i++) {
                if (i%2==0){
                    pieces[i][6]=new Piece(false, this, i, 6, "shield");
                } else {
                    pieces[i][6]=null;
                }
            }

            for (int i = 0; i<8; i++) {
                if ((i+1)%2==0){
                    pieces[i][5]=new Piece(false, this, i, 5, "bomb");
                } else {
                    pieces[i][5]=null;
                }
            }

            for (int i = 0; i<8; i++) {
                for (int j=3;j<5;j++){
                    pieces[i][j]=null;
                }
            }
        }
    }


    public Piece pieceAt(int x, int y){
    	/**Gets the piece at position (x, y) on the board, 
    	or null if there is no piece. 
    	If (x, y) are out of bounds, returns null.
    	*/
    	if ((x>7)||(y>7)||(x<0)||(y<0)||(pieces[x][y]==null)){
    		return null;
    	} else {
    	return pieces[x][y];
        }
    }



    public boolean canSelect(int x, int y){
    	/**Returns true if the square at (x, y) can be selected.
		
		A piece may be selected if it is the corresponding player’s turn and one of the following is true:
		The player has not selected a piece yet.
		The player has selected a piece, but did not move it.
		
		An empty square may be selected if one of the following is true:
		During this turn, the player has selected a Piece which hasn’t moved yet 
			and is selecting an empty spot which is a valid move for the previously selected Piece.
		During this turn, the player has selected a Piece, captured, and has selected another valid capture destination. 
			When performing multi-captures, you should only select the active piece once; 
			all other selections should be valid destination points. 
		*/
        Piece tryselectpiece=pieceAt(x,y);
        // System.out.println(currpiece);
        if ((tryselectpiece!=null)&&(isfireturn==tryselectpiece.isFire())&&(!boardselected||(boardselected&&!moved))){
            return true;
        } else if (
            (boardselected&&(tryselectpiece==null)&&!moved&&validMove(selectx,selecty,x,y)
                )||
                (boardselected&&(tryselectpiece==null)&&hascaptured&&validMove(selectx,selecty,x,y))
            ){
            // System.out.println(boardselected);
            // System.out.println(!moved);
            // System.out.println(validMove(currx,curry,x,y));
            return true;
        } else {
        return false;
        }
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
        /**Returns true if the piece at (xi, yi) can either move to (xf, yf) or capture to (xf, yf), 
        strictly from a geometry/piece-race point of view. 
        validMove does not need to take into consideration whose turn it is 
            or if a move has already been made, but rather can. 
        */
        Boolean cancapture = false;
        Piece oripiece=pieceAt(xi, yi);
        Piece movetopiece=pieceAt(xf, yf);
        if (moved && Math.abs(xf - xi)==1) {
            return false;
        }

        if (Math.abs(xi-xf)==2&&Math.abs(yi-yf)==2){
            int midx=(xi+xf)/2;
            int midy=(yi+yf)/2;
            Piece midpiece=pieceAt(midx,midy);
            if (midpiece!=null){
                if ((midpiece.side()!=oripiece.side())){
                    cancapture=true;
                }
            }
        }

        if (oripiece==null){
            return false;
        }

        if (oripiece.isKing()&&oripiece.side()==0){
            if ((Math.abs(xf-xi)==1&&(Math.abs(yf-yi)==1)&&(movetopiece==null))||(cancapture&&(Math.abs(xf-xi)==2&&(Math.abs(yf-yi)==2)))){
                return true; 
            }
            return false;
        } else if (oripiece.isKing()&&oripiece.side()==1){
            if ((Math.abs(xf-xi)==1&&(Math.abs(yf-yi)==1)&&(movetopiece==null))||(cancapture&&(Math.abs(xf-xi)==2&&(Math.abs(yf-yi)==2)))){
                return true; 
            }
            return false;
        } else if (!oripiece.isKing()&&oripiece.side()==0){
            if ((yf-yi==1&&(Math.abs(xf-xi)==1)&&(movetopiece==null))||(cancapture&&(yf-yi==2&&(Math.abs(xf-xi)==2))) ){
                return true;
            }
            return false;
        } else if (!oripiece.isKing()&&oripiece.side()==1){
            if ((yi-yf==1&&(Math.abs(xf-xi)==1)&&(movetopiece==null))||(cancapture&&(yi-yf==2&&(Math.abs(xf-xi)==2)))){
                return true;
            }
            return false;
        } else {
            return false;
        }
    }



	public void select(int x, int y){
		/**Selects the piece at (x, y) if possible. 
		Optionally, it is recommended to color the background 
			of the selected square white on the GUI via the pen color function. 
		For any piece to perform a capture, that piece must have been selected first.
        */
            if (pieceAt(x,y)!=null){
                boardselected=true;
                selectx=x;
                selecty=y;
            } else if ((pieceAt(x, y) == null && pieceAt(selectx, selecty) != null)){
                pieceAt(selectx,selecty).move(x,y);


                moved=true;
                if (Math.abs(x-selectx)==2) {
                    hascaptured = true;
                } 
                selectx = x;
                selecty = y;  
                }
            }

        
   
    public void place(Piece p, int x, int y) {
    /**Places p at (x, y). 
    If (x, y) is out of bounds or if p is null, does nothing. 
    If another piece already exists at (x, y), p will replace that piece. 
    (This method is potentially useful for creating specific test circumstances.)
    */
    
    if (!(x>8||y>8||x<0||y<0||p==null)){
        if (pieceAt(x,y)!=null){
            remove(x, y);
            pieces[x][y]=p;
        } else {
            pieces[x][y]=p;
        }
    }
} 

	public Piece remove(int x, int y){
		/**Executes a remove. Returns the piece that was removed. 
		If the input (x, y) is out of bounds, returns null and prints an appropriate message. 
		If there is no piece at (x, y), returns null and prints an appropriate message.
		*/
        if (pieceAt(x,y)==null){
            // System.out.print("try to remove "+x);
            // System.out.println("try to remove "+y);
            // System.out.println("No piece at this place to remove");
            return null;
        }
        if (x>7||y>7||x<0||y<0){
            // System.out.println("Out of bound to remove");
            return null;
        }
        Piece temp=pieces[x][y];
        pieces[x][y]=null;
        
        // System.out.print("remove "+x);
        // System.out.println("remove "+y);
		return temp;
    }


	public boolean canEndTurn(){
		/**Returns whether or not the the current player can end their turn. 
		To be able to end a turn, a piece must have moved or performed a capture.
		*/
        if (moved||hascaptured){
            return true;  
        }else{
            return false;
        }
        
    }


	public void endTurn(){
		/**Called at the end of each turn. 
		Handles switching of players and anything else that should happen at the end of a turn.
		*/
        boardselected=false; 
        moved=false;
        hascaptured=false;
        isfireturn=!isfireturn;

        if (pieceAt(selectx,selecty)!=null){
        pieceAt(selectx,selecty).doneCapturing();
        }

        selectx=1000;
        selecty=1000;


	}
	public String winner(){
		/**Returns the winner of the game. 
		"Fire", "Water", "No one" (tie / no pieces on the board), 
		or null if the game is not yet over. 
		Assume there is no stalemate situation 
			in which the current player has pieces 
			but cannot legally move any of them (In this event, just leave it at null). 
			Determine the winner solely by the number of pieces belonging to each team.
		*/
        int firecount=0;
        int watercount=0;
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                if (pieces[i][j]!=null){
                    if (pieces[i][j].side()==0){
                        firecount++;
                    }
                    else{
                        watercount++;
                    }
                }
            }
        }
        if (firecount>0&&watercount==0){
            return "Fire";
        } else if (watercount>0&&firecount==0){
            return "Water";
        } else if (firecount==watercount&&watercount==0){
            return "No one";
        } else {
            return null;
        }


	}





}