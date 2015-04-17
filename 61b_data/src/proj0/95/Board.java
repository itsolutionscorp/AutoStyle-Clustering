import java.math.*;

public class Board {
    
    private Piece[][] pieces;
    private boolean moved;
    private boolean fire_turn;
    private Piece selectedPiece;
    private int selectedX;
    private int selectedY;
    private int num_fire;
    private int num_water;

    private void drawBoard() {
        
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);         //initial setup.
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(pieces[i][j] != null) {
                    if (!pieces[i][j].isFire()) {
                        if (pieces[i][j].isBomb()) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                        else if (pieces[i][j].isShield()) StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);        //initial setup.
                        else                    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                    } else {
                        if (pieces[i][j].isBomb()) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                        else if (pieces[i][j].isShield()) StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                        else                    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                    }
                }
            }
        } 
}

    public Board (boolean shouldBeEmpty) {
        pieces = new Piece[8][8];
        moved = false;
        selectedPiece = null;
        fire_turn = true;
        num_fire = 0;
        num_water = 0;

        if(!shouldBeEmpty) {
            num_fire = 12;
            num_water = 12;      

            pieces[1][7] = new Piece(false, this, 1, 7, "pawn");
            pieces[3][7] = new Piece(false, this, 3, 7, "pawn");
            pieces[5][7] = new Piece(false, this, 5, 7, "pawn");
            pieces[7][7] = new Piece(false, this, 7, 7, "pawn");
            
            pieces[0][6] = new Piece(false, this, 0, 6, "shield");
            pieces[2][6] = new Piece(false, this, 2, 6, "shield");
            pieces[4][6] = new Piece(false, this, 4, 6, "shield");
            pieces[6][6] = new Piece(false, this, 6, 6, "shield");

            pieces[1][5] = new Piece(false, this, 1, 5, "bomb");           //stores info about each piece in an array of pieces, especially location and type.
            pieces[3][5] = new Piece(false, this, 3, 5, "bomb");
            pieces[5][5] = new Piece(false, this, 5, 5, "bomb");
            pieces[7][5] = new Piece(false, this, 7, 5, "bomb");

            pieces[0][0] = new Piece(true, this, 0, 0, "pawn");
            pieces[2][0] = new Piece(true, this, 2, 0, "pawn");
            pieces[4][0] = new Piece(true, this, 4, 0, "pawn");
            pieces[6][0] = new Piece(true, this, 6, 0, "pawn");

            pieces[1][1] = new Piece(true, this, 1, 1, "shield");
            pieces[3][1] = new Piece(true, this, 3, 1, "shield");
            pieces[5][1] = new Piece(true, this, 5, 1, "shield");
            pieces[7][1] = new Piece(true, this, 7, 1, "shield");

            pieces[0][2] = new Piece(true, this, 0, 2, "bomb");
            pieces[2][2] = new Piece(true, this, 2, 2, "bomb");
            pieces[4][2] = new Piece(true, this, 4, 2, "bomb");
            pieces[6][2] = new Piece(true, this, 6, 2, "bomb");
        }
    }

    public Piece pieceAt(int x, int y) {
        if(x >= 0 && x <= 7 && y >= 0 && y <= 7) {
            if (pieces[x][y] != null)
                return pieces[x][y]; 
        }
        return null;
    }

    public void place(Piece p, int x, int y) {
        if (x >= 0 && x <= 7 && y >= 0 && y <= 7)
            pieces[x][y] = p;
        if(p.isFire())
            num_fire += 1;
        else
            num_water += 1;
    }

    public Piece remove(int x, int y) {
        if(pieces[x][y] == null)
            System.out.println("There is no piece to be removed.");
        else if(x<0 || y<0 || x>7 || y>7)
            System.out.println("Location does not exist on the board.");
        else {
            Piece p = pieces[x][y];

            if(p.isFire())
                num_fire -= 1;
            else if(!p.isFire())
                num_water -= 1;

            pieces[x][y] = null;
            return p; }
        return null;
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        if(this.pieceAt(xf, yf) != null || this.pieceAt(xi, yi) == null)
            return false;

        if(this.pieceAt(xi, yi).isFire() && yf > yi) {      //if fire type.
            if((yf == yi + 1) && (xf == xi + 1 || xf == xi - 1)) {     //new position is accessible for fire types. one unit step.
                return true;
            } else if ((Math.abs(yf - yi) == 2) && (Math.abs(xf - xi) == 2)) {  //if capture move available. two unit step.
                Piece p = this.pieceAt((xi + xf)/2, (yi + yf)/2);
                if(p != null && !p.isFire())        //if there exists a capturable enemy piece.
                    return true;
                else return false;
            } return false;

        } else if (this.pieceAt(xi, yi).isFire() == false && yf < yi) {     //if water type.
            if((yf == yi - 1) && (xf == xi + 1 || xf == xi - 1)) {     //new position is accessible for water types.
                return true;              
            } else if ((Math.abs(yf - yi) == 2) && (Math.abs(xf - xi) == 2)) {  //if capture move available.
                Piece p = this.pieceAt((xi + xf)/2, (yi + yf)/2);
                if(p != null && p.isFire())     //if there exists a capturable enemy piece.
                    return true;
                else return false;
            } return false;


        //KING
        } else if (this.pieceAt(xi, yi).isKing()) {             //if king type.
            if((Math.abs(yf - yi) == 1) && (Math.abs(xf - xi) == 1)) {      //king can move in any four corner directions.
                return true;                    
            } else if ((Math.abs(yf - yi) == 2) && (Math.abs(xf - xi) == 2)) {  //if capture move available.
                Piece p = this.pieceAt((xi + xf)/2, (yi + yf)/2);
                if(p != null && (p.isFire() != this.pieceAt(xi, yi).isFire()))      //if there exists a capturable enemy piece. also, generic type checking.
                    return true;
                else return false;
            } return false;

        } return false;
    }


     public boolean canSelect(int x, int y) {
        if(this.pieceAt(x, y) != null) {       //there is a piece on the square.
            if((fire_turn && this.pieceAt(x, y).isFire()) || (!fire_turn && !this.pieceAt(x, y).isFire())) {    //same type and correct turn.
                if(selectedPiece == null) {     //no previously selected piece.
                    selectedPiece = this.pieceAt(x, y);
                    selectedX = x;
                    selectedY = y;      //store the selected piece.
                    selectedPiece = null;
                    return true;
                } else {                
                    if (!moved && (this.pieceAt(x, y).isFire() == selectedPiece.isFire()))  //have selected a piece, but haven't moved it yet.  check same type.
                        return true; } }
        } else {                                //selected an empty square.
            if(selectedPiece != null){              //if there exists a previously selcted piece.
                if (selectedPiece.hasCaptured() && Math.abs(selectedX - x) == 2 && Math.abs(selectedY - y) == 2 && this.pieceAt((selectedX + x)/2, (selectedY + y)/2) != null) {
                    if(selectedPiece.isBomb()){
                        selectedPiece = null;
                        return false;
                    } return true;

                } else if (!moved && this.validMove(selectedX, selectedY, x, y)) {    //checks if currently selected piece can move.
                    return true;
                }
            }
        } 
        return false;
    }


    public void select(int x, int y) {
        if((!moved && this.pieceAt(x, y) != null) || selectedPiece == null){     //if you selected a piece,
            selectedPiece = this.pieceAt(x, y);
            selectedX = x;                      //set selectedPiece to this piece, regardless of whether it was originally empty or not.
            selectedY = y;                      //obviously did not move.
            moved = false;
        } else {
            selectedPiece.move(x, y);           //if you selected an empty square, you know you selected a piece beforehand.
            selectedX = x;                      //set selectedPiece to this piece, regardless of whether it was originally empty or not.
            selectedY = y;
            moved = true;
        }
    }

    public boolean canEndTurn() {
        if(selectedPiece == null)
            return false;
        if(!moved)
            return false;

        if(moved || selectedPiece.hasCaptured())
            return true;

        return false;
    }

    public void endTurn(){

        selectedPiece.doneCapturing();
        selectedPiece = null;
        moved = false;

        if(fire_turn){
            fire_turn = false;
        } else {
            fire_turn = true;
        }
    }

    public String winner() {
        if(num_fire == 0 && num_water == 0)
            return "No One";
        else if(num_fire == 0)
            return "Water";
        else if(num_water == 0)
            return "Fire";
        return null;
    }

// The GUI:

public static void main(String[] args) {
    Board b = new Board(false);
    b.drawBoard();
    
    while(b.winner() == null) {
        if (StdDrawPlus.mousePressed()) {
            double x = StdDrawPlus.mouseX();
            double y = StdDrawPlus.mouseY(); 

            if(b.canSelect((int) x, (int) y)) {
                b.select((int) x, (int) y);
                b.drawBoard();
                }
        } else if(StdDrawPlus.isSpacePressed()) {
            if (b.canEndTurn()) 
                b.endTurn();
        }

    StdDrawPlus.show(10);
    }
    System.out.println(b.winner());
}
}