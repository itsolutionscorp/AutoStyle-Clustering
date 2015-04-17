/**
 * Creates and runs the GUI
 * @Author Rohit Braganza
 **/
import java.lang.Math;

public class Board {

    public static void main(String[] args) {
        Board init = new Board();
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        drawingBoard(8, init);
        do {
             drawingBoard(8, init);
             StdDrawPlus.show(15);
             //Mouse
             boolean press = StdDrawPlus.mousePressed();
               if (press == true) {
                 int holdX = (int) StdDrawPlus.mouseX();
                 int holdY = (int) StdDrawPlus.mouseY();
                 //Don't allow selecting same spot over & over again.
                 if ((holdX != init.selectedX ^ holdY != init.selectedY) ||
                     (holdX != init.selectedX && holdY != init.selectedY))  {
                     //Select if allowed.
                     if (init.canSelect(holdX, holdY) == true) {
                         Piece holdP = init.pieceAt(holdX, holdY);
                         if (holdP != null) 
                             init.selectedPiece = holdP;
                         init.select(holdX, holdY);
                     }
                  }
               }
               //space bar ends turn
            if (StdDrawPlus.isSpacePressed() == true) {
                if (init.canEndTurn() == true) 
                    init.endTurn();
            }
        } while (init.winner() == null);
        System.exit(0);
        
    }
    
    private Piece[][] pieces = new Piece[8][8]; //y axis, then x
    private boolean shouldBeEmpty;
    private boolean hasSelected;
    private boolean playerTurn; //Fire = true, Water = false
    private boolean finishedMoving; //If current player has moved
    private Piece selectedPiece;
    private boolean white;
    private boolean bombExploded;
    //coordinates of last selected piece
    private int selectedX; 
    private int selectedY;
    private boolean hasMoved;
    /**
     * Board constructor. shouldBeEmpty=true creates a blank board.
     **/
    public Board(boolean shouldBeEmpty) {
        this.shouldBeEmpty = shouldBeEmpty;
        playerTurn = true;
        hasSelected = false;
        finishedMoving = false;
        selectedPiece = null;
        hasMoved = false;
        selectedY = -1;
        selectedX = -1;
        white = false;
        bombExploded = false;
        if (!shouldBeEmpty == true) {
            //Initializes all the pieces on the board.
            for (int y = 0; y < 8; y++) { //the y axis
                if (y == 3) //skip the middle rows with no initial pieces.
                    y = 5;
                for (int x = 0; x < 8; x++) { //the x axis  
                    //even rows
                    if (y % 2 == 0) { 
                        if (x % 2 == 0) {
                            pieces[y][x] = new Piece(chooseInitialSide(y), this, x, y,
                                                 initialPieceType(y)); }
                    }
                    //odd rows
                    else {
                        if (x % 2 == 1) {
                            pieces[y][x] = new Piece(chooseInitialSide(y), this, x, y,
                                                 initialPieceType(y));
                        }
                    }
                }
            }
        }
    }

    /**
     * Default contructor. Runs only when the game is started by the main.
     **/
    private Board() {
        this(false);
    }
    /**
     * Gives True if the piece is to be a fire piece, false if water.
     **/
    private boolean chooseInitialSide(int i) {
        if (i < 4) 
            return true;
        return false;
    }
    /**
     * Returns the piece type based on w.
     **/
    private String initialPieceType(int i) {
        if (i == 0 || i ==7) 
            return "pawn";
        else if (i == 1 || i == 6)
            return "shield";
        else //assume only valid rows are initiated
            return "bomb";
    }

    private static void drawingBoard(int N, Board b) {
        //Draws the basic board outline. Modified from  
        //author Josh Hug's StdDrawDemo.java.  

        for (int x = 0; x < N; x++) {//x axis
            for (int y = 0; y < N; y++) {//y axis
                if ((x + y) % 2 == 0) 
                    StdDrawPlus.setPenColor(StdDrawPlus.BOOK_LIGHT_BLUE);
                else                  
                    StdDrawPlus.setPenColor(StdDrawPlus.MAGENTA);
                StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                Piece p = b.pieceAt(x, y); 
                if (p != null)  //Draw the pieces' pictures
                    StdDrawPlus.picture(x + .5, y + .5, imgFile(p), 0.8, 0.8); 
                else { //Do not draw removed pieces
                     if ((x + y) % 2 == 0)
                         StdDrawPlus.setPenColor(StdDrawPlus.BOOK_LIGHT_BLUE);
                     else
                         StdDrawPlus.setPenColor(StdDrawPlus.MAGENTA);
                     StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                    } 
            }
        }
        //If a square is selected, make it white
        //and redraw the piece if applicable
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        if (b.white == true) {
            StdDrawPlus.filledSquare(b.selectedX + 0.5, b.selectedY + 0.5, 0.5);
            Piece reprint = b.pieceAt(b.selectedX, b.selectedY); 
            if (reprint != null) {
                StdDrawPlus.picture(b.selectedX + .5, b.selectedY + .5, 
                                    imgFile(reprint), 0.8, 0.8); 
            }
        }

        StdDrawPlus.show(20);
    }      

    /** 
     * matches piece to imgFile.
     **/
    private static String imgFile(Piece p) {
        String name;
        if (p.isShield() == true)
            name = "/img/shield-" + imgName(p);
        else if (p.isBomb() == true)
            name = "/img/bomb-" + imgName(p);
        else
            name = "/img/pawn-" + imgName(p);
        return name;
    }
    /**
     * matches piece to end of the img filename
     **/
    private static String imgName(Piece p) {
        if (p.isFire() == true) {
            if (p.isKing() == true)
                return "fire-crowned.png";
            else
                return "fire.png";
            }
        else {
            if (p.isKing() == true)
                return "water-crowned.png";
            else
                return "water.png";
        }

    }

    /**
     * Returns the piece at a certain position, null if none.
     * If x and y are out of bounds, returns null.
     **/
    public Piece pieceAt(int x, int y) {
        //if out of bounds
        if (x >=8 || y >= 8 || x < 0 || y < 0)
            return null;
        //If there is no piece
        if (pieces[y][x] == null) 
            return null;
        else //Pieces organized [y-axis][x-axis]
            return pieces[y][x];
    }
    /**
     * Returns whether a square can be selected
     */
    public boolean canSelect(int x, int y) {
        Piece p = pieceAt(x, y);
        if (bombExploded == true) //cannot select once a bomb explodes.
            return false;
        //Cannot move a selected piece if it has already moved
        //and is not capable of a multicapture
        if (selectedPiece != null) {
            if (hasMoved == true) {
                if (selectedPiece.hasCaptured() != true)
                    return false;
            }
        }
        //if an empty square
        if (p == null) {
            //must select a piece first
            if (hasSelected == false)
                return false;
            else {//determine if a move can be made.
                if (selectedPiece == null) {
                    return false;                }
                return inRadius(selectedPiece, selectedX, selectedY, x, y, this);
            }
        }
        else {//there is a piece:
            //Cannot select another piece if the current piece has captured a piece.
            if (selectedPiece != null) {
                if (selectedPiece.hasCaptured() == true)
                    return false;
            }
            if (hasMoved == true)
                return false;
            
            if (hasSelected == true) { //Choosing a new piece
                if (playerTurn == p.isFire()) {
                    hasSelected = false;
                    return true;
                }
                else
                    return false;
            }   
            
        //If the wrong side piece is chosen, return false.
            if (playerTurn == p.isFire())
                return true;
            else
                return false;
        }   
    }
    /**
     * Returns whether a selected piece can move to a specific coordinate, given its position.
     * Does not consider whether a piece occupies the given location.
     * Does however consider whether the given location can be reached via one capture.
     **/
    private static boolean inRadius(Piece p, int posX, int posY, int x, int y, Board b) {
        if (x > 7 || y > 7 || x < 0 || y < 0) //off the grid
            return false;
        int moveX = x - posX;
        int moveY = y - posY;
        //can never move sideways or straight up.
        if (moveX == 0 || moveY == 0)
            return false;
        //Cannot move farther than 2 spaces diagonally in one click.
        if (Math.abs(moveX) > 2 || Math.abs(moveY) > 2)
            return false;
        //Cannot move only one space once the piece has captured.
        if (p.hasCaptured() == true && (Math.abs(moveX) == 1 || Math.abs(moveY) == 1))
            return false;
        //Cannot move like a knight in chess
        if (Math.abs(moveX) == 2 || Math.abs(moveY) == 2)
            if ((Math.abs(moveX) == Math.abs(moveY)) == false) {
                return false; }
        //basic radius move
        if (Math.abs(moveX) == 1 || Math.abs(moveY) == 1) {
            if (p.isKing() == true) 
                return true; //King can move in any direction.
            //fire goes up, water down
            else if (p.isFire() == true  && moveY > 0) 
                return true;
            else if (p.isFire() == false && moveY < 0)
                return true;
            else                              
                return false;
            }
        else { //If attempting to move two squares diagonally (when capturing a piece).
            Piece middle = b.pieces[(posY + y)/2][(posX + x)/2];
            if (middle == null) //Must be a piece to be captured
                return false;
            if (p.isKing() == true)
                return canCapture(p, middle);
            if (p.isFire() == true && moveY > 0) 
                return canCapture(p, middle);
            else if (p.isFire() == false && moveY< 0)
                return canCapture(p, middle);
            else
                return false;
        }
    }
    /**
     * Returns whether piece p can capture piece middle.
     */
    private static boolean canCapture(Piece p, Piece middle) {
        if (p.side() == middle.side())
            return false;
        else
            return true;
    }
    /**
     * Places a piece at a certain location.
     **/
    public void place(Piece p, int x, int y) {
        if (p == null)
            return;
        if (x > 7 || x < 0 || y < 0 || y > 7) 
            return;
        if (pieceAt(x, y) == null) {
            pieces[y][x] = p;
            selectedPiece = p;
        }
    }
    /**
     * Selects a square.  Assumes can select is true. M1
     **/
    public void select(int x, int y) {
        //if selecting the first time.
        white = true;
        if (hasSelected == false) {
            selectedX = x; 
            selectedY = y;
            selectedPiece = pieceAt(x, y);
            hasSelected = true;
        }
        else { //If selecting a square (assuming a piece is already selected)
               //Move the piece to selected square.
            Piece moving = remove(selectedX, selectedY);
            selectedPiece.move(x, y);
            //If a capture occurs:
            if (selectedPiece.hasCaptured() == true) {
                capture(selectedX, selectedY, x, y);
                //Bombs explode
                if (selectedPiece.isBomb() == true) {
                    explode(x, y);
                    selectedPiece = null;
                }
            }
            //save the new location (in case of multicapture)
            selectedX = x; 
            selectedY = y;
            hasMoved = true;
        }
    }
    /**
     * Removes piece at selected square.
     **/
    public Piece remove(int x, int y) {
        if (x > 7 || x < 0 || y < 0 || y > 7)  {
            return null;
        }
        Piece hold = pieceAt(x, y);
        if (hold == null) {
            return null;
        }
        pieces[y][x] = null;
        return hold;
    }
    /**
     * Player must move/capture to end turn
     **/
    public boolean canEndTurn() {
        if (hasMoved == true) 
            return true;
        else
            return false;
    }
    /**
     * Ends the turn
     **/
    public void endTurn() {
        if (selectedPiece != null)
            selectedPiece.doneCapturing();
        playerTurn = !playerTurn;
        boolean shouldBeEmpty;
        hasSelected = false;
        finishedMoving = false; 
        selectedPiece = null;
        hasMoved = false;
        white = false;
        selectedX = -1;
        selectedY = -1;
        bombExploded = false;
    }
    /**
     * Returns name of winner
     **/
    public String winner() {
        int countF = 0;
        int countW = 0;
        //Count the number of pieces for each side.
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++) {
                Piece p = pieceAt(j, i);
                if (p != null) {
                    if (p.isFire() == true)
                        countF += 1;
                    else
                        countW += 1;
                }
            }
        }
        if (countW == 0 && countF > 0) 
            return "Fire";
        else if (countF == 0 && countW > 0)
            return "Water";
        else if (countW == 0 && countF == 0)
            return "no one";
        else //Stalemate
            return null;
    }
    /**
     * Captures pieces
     **/
    private void capture(int startX, int startY, int x, int y) {
        int moveX = x - startX; 
        int moveY = y - startY; 
        Piece notNeeded = remove((startX + x) / 2, (startY + y) / 2);
    }
    
    /**
     * Bombs explode when capturing, destroying themselves and nearby non-shield pieces.
     **/
    private void explode(int x, int y) {
        //Remove the bomb.
        Piece notNeeded = remove(x, y);
        //All the squares in a 1x1 radius of the bomb.
        Piece[] bombRadius = new Piece[4];
        bombRadius[0] = pieceAt(x + 1, y + 1);
        bombRadius[1] = pieceAt(x + 1, y - 1);
        bombRadius[2] = pieceAt(x - 1, y + 1);
        bombRadius[3] = pieceAt(x - 1, y - 1);
        bombExploded = true;
        //Remove the pieces if applicable
        for (int i=0; i < 4; i++) {
            if (bombRadius[i] != null) {
                if (bombRadius[i].isShield() == true)
                    continue;
                else if (i == 0)
                    remove(x + 1, y + 1);
                else if (i == 1)
                    remove(x + 1, y - 1);
                else if (i == 2)
                    remove(x - 1, y + 1);
                else
                    remove(x - 1, y - 1);
            } 
        }
    }
}
  


