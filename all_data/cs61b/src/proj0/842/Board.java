
public class Board {
    
    private Piece[][] pieces;
    
    private boolean hasMoved;
    
    private Piece pieceHeld;
    
    private int pieceX;
    
    private int pieceY;
    
    private boolean fireTurn;

    public static void main(String[] args) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board newBoard = new Board(false);
        
        while (true) { //Keeps going
            newBoard.drawBoard();
            if (StdDrawPlus.mousePressed() && newBoard.canSelect((int)StdDrawPlus.mouseX(), (int)StdDrawPlus.mouseY())) {
                newBoard.select((int)StdDrawPlus.mouseX(), (int)StdDrawPlus.mouseY());
            }
            if (StdDrawPlus.isSpacePressed() && newBoard.canEndTurn()) { //Let's you end turn if you can
                newBoard.endTurn();
                if (newBoard.winner() != null) { //Is there a winner? 
                    System.out.println(newBoard.winner());
                    return;
                }
            }
            StdDrawPlus.show(10);
        }
    }
    
    private void drawBoard() { //Thank you StdDrawDemo!
        Piece testPiece = null;
        String image = null; //Used to find image file
        for (int i = 0; i < 8; i++) { //Loops though x-positions
            for (int j = 0; j < 8; j++) { //Loops through y-positions
                testPiece = pieces[i][j];
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (testPiece != null) { //Colors selected square white
                    if (testPiece == pieceHeld) {
                        StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                    }
                    if (testPiece.isBomb()) { //Check for type
                        image = "bomb-";
                    }
                    else if (testPiece.isShield()) {
                        image = "shield-";
                    }
                    else {
                        image = "pawn-";
                    }
                    if (testPiece.isFire()) { //Check for team
                        image = image + "fire";
                    }
                    else {
                        image = image + "water";
                    }
                    if (testPiece.isKing()) { //Check for royal blood
                        image = image + "-crowned";
                    }
                    StdDrawPlus.picture(i + .5, j + .5, "img/" + image + ".png", 1, 1); //Paints image
                }
            }
        }
    }
    
    private void pieceMaker(int x, int y, boolean fire, String type) { //Helper method to create pieces
        pieces[x][y] = new Piece(fire, this, x, y, type);
    }
    
    public Board(boolean shouldBeEmpty) {
        hasMoved = false;
        fireTurn = true;
        pieceHeld = null;
        pieces = new Piece[8][8];
        if (shouldBeEmpty) {
            return;
        }
        for (int x = 0; x < 8; x += 2) {
            pieceMaker(x, 0, true, "pawn");
            pieceMaker(x, 6, false, "shield");
            pieceMaker(x, 2, true, "bomb");
        }
        for (int x = 1; x < 8; x += 2) {
            pieceMaker(x, 1, true, "shield");
            pieceMaker(x, 7, false, "pawn");
            pieceMaker(x, 5, false, "bomb");
        }
    }
    
    public Piece pieceAt(int x, int y) {
        if (x > 7 || y > 7 || x < 0 || y < 0) { //Out of bounds check
            return null;
        }
        return pieces[x][y];
    }
    
    private boolean validMove(int xi, int yi, int xf, int yf) {
        if (Math.abs(xi - xf) == 1 && Math.abs(yi - yf) == 1) { //Not capturing
            if (pieces[xf][yf] != null) { //Is this space occupied?
                return false;
            }
            if (pieceHeld.isKing() == false) { //Kings can go wherever they please
                if (fireTurn && yi > yf) { //Dirty  Peasant Fire troops cannot move down
                    return false;
                }
                if (!fireTurn && yf > yi) { //Filthy Plebeian Water soldiers cannot go up
                    return false;
                }
            }
            return true;
        }
        if (Math.abs(xi - xf) == 2 && Math.abs(yi - yf) == 2) { //Going for the capture
            int xAvg =  ((xf - xi) / 2) + xi;
            int yAvg =  ((yf - yi) / 2) + yi;
            if (pieces[xf][yf] != null) { //Cannot land on a piece
                return false;
            }
            if (pieces[xAvg][yAvg] == null || pieces[xAvg][yAvg].isFire() == pieces[xi][yi].isFire()) {
                //Checks to see if there is a piece being jumped as well as if it is an enemy piece
                return false;
            }
            if (!pieces[xi][yi].isKing()) { //Kings do not worry about up or down
                if (fireTurn && yi > yf) { //Peasant Fire troops cannot move down
                    return false;
                }
                if (!fireTurn && yf > yi) { //Plebian Water soldiers cannot go up
                    return false;
                }
            }
            return true;
        }
        return false; //There are no other valid scenarios
    }
    
    public boolean canSelect(int x, int y) {
        if (x > 7 || y > 7 || x < 0 || y < 0) { //Out of bounds
            return false;
        }
        if (pieceHeld != null) { //Are you already holding a piece?
            if (pieces[x][y] != null) {  
                if (pieceHeld.isFire() == pieces[x][y].isFire()) { //Switching pieces
                    return !hasMoved;
                }
            }
            if (validMove(pieceX, pieceY, x, y)) { //Valid move check
                if (hasMoved) { //Multiple captures
                    return pieceHeld.hasCaptured() && (Math.abs(pieceX - x) == 2); 
                }
                return true;
            }
        }
        if (pieces[x][y] == null) { //If you aren't holding a piece, you cannot select an empty space
            return false;
        }
        if (fireTurn) { //Who's turn is it?
            if (pieces[x][y].isFire()) { //Fire pieces moved when it is Fire's turn
                return true;
            }
            return false;
        }
        if (pieces[x][y].isFire()) { //Water cannot move Fire pieces
            return false;
        }
        return true;
    }
    
    public void select(int x, int y) {
        if (pieceHeld == null) { //Selecting a piece to pick up
            pieceHeld = pieces[x][y];
            pieceX = x;
            pieceY = y;
        }
        if (pieces[x][y] != null) { //Changing pieces
            if (pieceHeld.isFire() == pieces[x][y].isFire()) {
                pieceHeld = pieces[x][y];
                pieceX = x;
                pieceY = y;
            }
        }   
        else { //Selecting a location to move
            pieceHeld.move(x, y);
            hasMoved = true;
            pieceX = x;
            pieceY = y;
        }
    }
    
    public void place(Piece p, int x, int y) {
        if (p != null) { //Does nothing if there is no piece to place
            pieces[x][y] = p; 
        }
    }
    
    public Piece remove(int x, int y) {
        if (x > 7 || y > 7 || x < 0 || y < 0) { //Out of bounds check
            System.out.println("Out of bounds removal.");
            return null;
        }
        if (pieces[x][y] == null) { //Check there is a piece to remove
            System.out.println("No piece to remove.");
            return null;
        }
        Piece temp = pieces[x][y]; //Store removed piece to return it
        pieces[x][y] = null;
        return temp;
    }
    
    public boolean canEndTurn() {
        return hasMoved;
    }
    
    public void endTurn() {
        fireTurn = !fireTurn;
        if (pieceHeld != null) {
            pieceHeld.doneCapturing();
        }
        pieceHeld = null;
        hasMoved = false;
    }
    
    public String winner() {
        boolean waterRepresent = false;
        boolean fireRepresent = false;
        boolean ghostTown = true;
        for (int a = 0; a < 8; a += 1) { //Loops through every position on the board
            for (int b = 0; b < 8; b += 1) {
                if (pieces[a][b] != null) { //Is there a piece there?
                    if (pieces[a][b].isFire()) {
                        fireRepresent = true; //Fire detected
                    }
                    else {
                        waterRepresent = true; //Water detected
                    }
                    ghostTown = false; //Piece detected
                }
            }
        }
        if (ghostTown) { //Are there no pieces?
            return "No one";
        }
        if (waterRepresent) { //Is there water?
            if (fireRepresent) { //Is there fire?
                return null;
            }
            return "Water";
        }
        return "Fire";
    }
}
