/* Creates a board for the pieces to play on */

import java.util.Arrays;

public class Board {
	private Piece[][] pArray;
    private String player = "Fire";
    private Piece selected = null;
    private int selectedX;
    private int selectedY;
    private int numMoves = 0;
    /** Location of pieces. */
    private static boolean[][] pieces;

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                //checks for pieces and redraws board
                if (this.pArray[i][j] != null) {
                    StdDrawPlus.picture(i + .5, j + .5, getImage(this.pArray[i][j]), 1, 1);
                }
                if (this.pieces[i][j]){
                    StdDrawPlus.picture(i + .5, j + .5, getImage(this.selected), 1, 1);
                    pieces[i][j] = false;
                }
            }
        }
    } 

    //gets image of piece
    private static String getImage(Piece p){
    	if (p.isFire() == true) {
            if (p.isBomb()){
                if (p.isKing()) {
                    return "img/bomb-fire-crowned.png";
                }
    			return "img/bomb-fire.png";
    		} else if (p.isShield()){
                if (p.isKing()) {
                    return "img/shield-fire-crowned.png";
                }
    			return "img/shield-fire.png";
            } else {
                if (p.isKing()) {
                    return "img/pawn-fire-crowned.png";
                }
                return "img/pawn-fire.png";
            }
    	} else {
    		if (p.isBomb()){
                if (p.isKing()) {
                    return "img/bomb-water-crowned.png";
                }
                return "img/bomb-water.png";
            } else if (p.isShield()){
                if (p.isKing()) {
                    return "img/shield-water-crowned.png";
                }
                return "img/shield-water.png";
            } else {
                if (p.isKing()) {
                    return "img/pawn-water-crowned.png";
                }
                return "img/pawn-water.png";
            }
    	}
    }

    //Initializes default board
    private void defaultBoard(){
        pArray = new Piece[8][8];
        int j = 0;
        int i;
        while (j < 8) {
        	if (j % 2 == 0){
        		i = 0;
        	} else {
        		i = 1;
        	}
        	while (i < 8){
	        	if (j == 0) {
	        		pArray[i][j] = new Piece(true, this, i, j, "pawn");
	        	} else if (j == 1) {
	        		pArray[i][j] = new Piece(true, this, i, j, "shield");
	        	} else if (j == 2) {
	        		pArray[i][j] = new Piece(true, this, i, j, "bomb");
	        	} else if ( (j == 3) || (j == 4)) {
	        		i = 8;
	        	} else if (j == 5) {
	        		pArray[i][j] = new Piece(false, this, i, j, "bomb");
	        	} else if (j == 6) {
	        		pArray[i][j] = new Piece(false, this, i, j, "shield");
	        	} else if (j == 7) {
	        		pArray[i][j] = new Piece(false, this, i, j, "pawn");
	        	} 
	        	i += 2;
	        }
	        j += 1;
        }
    }
 
    // Board constructor. 
    public Board(boolean shouldBeEmpty) {
            if (shouldBeEmpty == false){
                defaultBoard();
            } else {
                pArray = new Piece[8][8];
            }
        }

    //Get the piece at position (x, y) 
    public Piece pieceAt(int x, int y) {
        int i = 0;
        int j = 0;
        while (i < 8) {
    		for (Piece p : pArray[i]){
    			if ((p != null) && (i == x) && (j == y))
    				return p;
                j++;
            }
            i++;
            j = 0;
    	}
        return null;
    }  

    private int[][] validSpots(int x, int y){
        int[][] spots = new int[8][2];      //  _________________
        spots[0] = new int[]{x - 1, y + 1}; //  | | | | | | | | |               
        spots[1] = new int[]{x + 1, y + 1}; //  | | | | | | | | |  
        spots[2] = new int[]{x - 1, y - 1}; //  | |4| | | |5| | |
        spots[3] = new int[]{x + 1, y - 1}; //  | | |0| |1| | | |
        spots[4] = new int[]{x - 2, y + 2}; //  | | | |P| | | | |
        spots[5] = new int[]{x + 2, y + 2}; //  | | |2| |3| | | |
        spots[6] = new int[]{x - 2, y - 2}; //  | |6| | | |7| | |
        spots[7] = new int[]{x + 2, y - 2}; //  |_|_|_|_|_|_|_|_|
        return spots;                        
    }

    private boolean validMove(int x, int y){
        int difX;
        int difY;
        //possible x,y pairings
        int[][] spots = validSpots(selectedX, selectedY);
        //makes sure not trying to move too far
        difX = Math.abs(selectedX - x);
        difY = Math.abs(selectedY - y);
        if ((difX > 2) || (difY > 2)){
            return false;
        }
        //loop through valid spots to find intended move spot
        int i;
        for (i = 0; i < 8; i++){
            difX = Math.abs(spots[i][0] - x);
            difY = Math.abs(spots[i][1] - y);
            if ((difX == 0) && (difY == 0)){
                break;
            }
        }
        //doesn't allow jumps if not a capture
        if (i > 3) {
            Piece inbetween = pieceAt(spots[i - 4][0], spots[i - 4][1]);
            if ((inbetween == null) || (selected.isFire() == inbetween.isFire())) {
                return false;
        }

        }
        //erased down moving spots for fire
        if (selected.isFire() && !selected.isKing()) {
            spots[2][0] = -1;
            spots[3][0] = -1;
            spots[6][0] = -1;
            spots[7][0] = -1;
        //erases up moving spots for water
        } else if (!selected.isFire() && !selected.isKing()){
            spots[4][0] = -1;
            spots[0][0] = -1;
            spots[1][0] = -1;
            spots[5][0] = -1;
        } 
    
        //doesn't allow opposite direction movement
        if (spots[i][0] == -1){
            return false;
        }
        return true;
    }

    private boolean captureMove(int x, int y) {
        int difX;
        int difY;
        //possible x,y pairings
        int[][] spots = validSpots(selectedX, selectedY);

        //loop through valid spots to find intended move spot
        int i;
        for (i = 0; i < 8; i++){
            difX = Math.abs(spots[i][0] - x);
            difY = Math.abs(spots[i][1] - y);
            if ((difX == 0) && (difY == 0)){
                break;
            }
        }
        if (i < 4) {
            return false;
        }
        //if validMove, then checks if possible capture
        if (validMove(x, y)) {
            Piece inbetween = pieceAt(spots[i - 4][0], spots[i- 4][1]);
            if ((inbetween != null) && (selected.isFire() != inbetween.isFire())) {
                return true;
            }
        }
        return false;
    }

    //returns true if square (x, y) is selectable
    public boolean canSelect(int x, int y) {
        Piece p = pieceAt(x, y);
        //has player selected a piece yet?
        //no
        if ((selected == null) && (p != null) && (p.isFire() == player.equals("Fire"))){
            return true;
        //yes 
        //does the player want to move the piece?
        //no --> wants to change piece selection
        } else if ((selected != null) && (!canEndTurn()) && (p != null) && (p.isFire() == player.equals("Fire"))) {
            return true;
        //yes (must be valid movement)
        } else if ((selected != null) && (!canEndTurn()) && (p == null) && (validMove(x, y))) {
            return true;
        //double capture for kings (why are kings so special who knows)
        } else if ((selected != null) && (selected.isKing()) && (p == null) && (captureMove(x,y))) {
            return true;
        //double capture
        } else if ((selected != null) && (selected.hasCaptured()) && (captureMove(x, y))) {
                return true;
        }
        return false;
    } 
        
    
    //select sqaure
    public void select(int x, int y) {
        if (selected == null) {
            if (pieceAt(x, y) != null){
                selected = pieceAt(x, y);
            }
        } else if ((pieceAt(x, y) != null) && (selected != pieceAt(x, y))){
            selected = pieceAt(x, y);
        } else {
            selected.move(x, y);
            numMoves += 1;
        }
        selectedX = x;
        selectedY = y;
    }

    //places Piece p at (x, y)
    public void place(Piece p, int x, int y) {
        pArray[x][y] = p;
    }

    //removes and return a piece at (x, y)
    public Piece remove(int x, int y) {
        Piece removed = pieceAt(x, y);
    	if ( (x > 8) || (y > 8) ) {
            System.out.println("Selection out of bounds.");
            return null;
        } else if (removed == null) {
            System.out.println("No piece at: " + x + "," + y);
            return null;
        }
        pArray[x][y] = null;
        return removed;
    }

    public boolean canEndTurn() {
        if (numMoves == 0) {
            return false;
        }
    	return true;
    }

    public void endTurn() {
        if (canEndTurn()) {
            if (player == "Fire") {
                player = "Water";
            } else {
                player = "Fire";
            }
            selected.doneCapturing();
            this.selected = null;
            this.selectedX = 0;
            this.selectedY = 0;
            numMoves = 0;
        }
    } 

    public String winner() {
    	int fireCount = 0;
        int waterCount = 0; 
        int placeholder = 0;
        for (int i = 0; i < 8; i++){
            for (Piece p : pArray[i]){
                if ((p != null) && p.isFire()){
                    fireCount += 1;
                } else if ((p != null) && (!p.isFire()) ){
                    waterCount += 1;
                } else {
                    placeholder += 1;
                }
            }
        }
        if ((waterCount == 0) && (fireCount == 0)){
            return "No one";
        } else if (fireCount == 0) {
            return "Water";
        } else if (waterCount == 0) {
            return "Fire";
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        Board b = new Board(false);

        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        pieces = new boolean[N][N];

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)) {
                    System.out.println((int) x + " " + (int) y);
                    b.select((int) x, (int) y);
                    if (b.selected != null) {
                        pieces[(int) x][(int) y] = true;
                        b.drawBoard(N);
                    }
                }
            //redraw piece
            } else if (StdDrawPlus.isSpacePressed() && b.canEndTurn()){ 
                b.endTurn();
                System.out.println("Turn ended. Player: " + b.player);
            }         
            StdDrawPlus.show(100);
        }
    }
}

 
/* Problems
 * - NONE HEELLLA YAS
 *
 * FAILED AG TESTS 
 * ALL DONEEEEEEEEEEEEEEEEEEEEEEE
 *
 * FAILED SUBMISSION TESTS 
 * YOU GOT THEM ALL GO YOU FAN TAS TIC   
 *  
 *                     
 */