import java.awt.Color;

/**
 *  @author Jack Thakar
 */

public class Board{

    private static final int FPS = 60;

    /** Starts a new game */
    public static void main(String[] args){
        Board b = new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        while(true) {
            b.draw();
            if(StdDrawPlus.mousePressed()){
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if(b.canSelect(x, y)){
                    b.select(x, y);
                }
            } else if(StdDrawPlus.isSpacePressed()){
                if(b.canEndTurn()) b.endTurn();
            }
            StdDrawPlus.show(1000/FPS);
        }
    }

    /** Helper method for getting the proper image for a piece */
    private static String getImage(Piece p){
        String image = "";
        if(p.isShield()){
            image += "shield";
        }else if(p.isBomb()){
            image += "bomb";
        }else{
            image += "pawn";
        }
        if(p.isFire()){
            image += "-fire";
        }else{
            image += "-water";
        }
        if(p.isKing()){
            image += "-crowned";
        }
        return "img/"+image+".png";
    }

    private Piece[][] pieces;
    private int selectX = -1, selectY = -1;
    private boolean isFireTurn = true;
    private boolean hasMoved = false;

    /** Creates empty board (and adds pieces if shouldBeEmpty is false) */
    public Board(boolean shouldBeEmpty){
        pieces = new Piece[8][8];
        if(!shouldBeEmpty){
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

            pieces[1][7] = new Piece(false, this, 1, 7, "pawn");
            pieces[3][7] = new Piece(false, this, 3, 7, "pawn");
            pieces[5][7] = new Piece(false, this, 5, 7, "pawn");
            pieces[7][7] = new Piece(false, this, 7, 7, "pawn");

            pieces[0][6] = new Piece(false, this, 0, 6, "shield");
            pieces[2][6] = new Piece(false, this, 2, 6, "shield");
            pieces[4][6] = new Piece(false, this, 4, 6, "shield");
            pieces[6][6] = new Piece(false, this, 6, 6, "shield");

            pieces[1][5] = new Piece(false, this, 1, 5, "bomb");
            pieces[3][5] = new Piece(false, this, 3, 5, "bomb");
            pieces[5][5] = new Piece(false, this, 5, 5, "bomb");
            pieces[7][5] = new Piece(false, this, 7, 5, "bomb");
        }
    }

    /** Redraws the board */
    private void draw(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(selectX == i && selectY == j){
                    StdDrawPlus.setPenColor(new Color(0xffff88));
                }else if((i + j) % 2 == 0){
                    StdDrawPlus.setPenColor(new Color(0xeeeeee));
                }else{
                    StdDrawPlus.setPenColor(new Color(0x00b888));
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if(pieceAt(i, j) != null){
                    StdDrawPlus.picture(i + .5, j + .5, getImage(pieceAt(i, j)), 1, 1);
                }
            }
        }
    }

    /** Returns the piece at the given coordinates if any */
    public Piece pieceAt(int x, int y){
        if(outOfBounds(x, y)) return null;
        return pieces[x][y];
    }

    /** Returns true if selection is valid */
    public boolean canSelect(int x, int y){
        Piece p = pieceAt(x, y);
        if(p == null){
            Piece selected = pieceAt(selectX, selectY);
            if(selected == null){
                return false;
            }
            return validMove(selectX, selectY, x, y);
        }
        return !hasMoved && p.isFire() == isFireTurn;
    }

    /** Returns whether the give move is valid */
    private boolean validMove(int xi, int yi, int xf, int yf){
        if(outOfBounds(xi, yi) || outOfBounds(xf, yf)) return false;
        Piece from = pieceAt(xi, yi);
        Piece to = pieceAt(xf, yf);
        if(from == null || to != null) return false;
        if(!from.isKing()){
            if(from.isFire() && yi > yf) return false;
            if(!from.isFire() && yi < yf) return false;
        }
        if(Math.abs(xi-xf) == 1 && Math.abs(yi-yf) == 1){
            return !hasMoved && !from.hasCaptured();
        }
        if(Math.abs(xi-xf) == 2 && Math.abs(yi-yf) == 2){
            Piece middle = pieceAt((xi+xf)/2, (yi+yf)/2);
            if(middle!=null && middle.isFire()!=from.isFire()) return true;
        }
        return false;
    }

    /** Changes the selection (and makes a move if applicable) */
    public void select(int x, int y){
        Piece p = pieceAt(x, y);
        if(p == null){
            Piece moving = pieceAt(selectX, selectY);
            moving.move(x, y);
            hasMoved = true;
            if(pieceAt(x, y) != null){
                selectX = x;
                selectY = y;
            }else{
                selectX = -1;
                selectY = -1;
            }
        }else{  
            selectX = x;
            selectY = y;
        }
    }

    /** Helper method that determines if the coordinates are out of bounds */
    private boolean outOfBounds(int x, int y){
        return x < 0 || y < 0 || x >= 8 || y >= 8;
    }

    /** Places a piece on the board at x, y*/
    public void place(Piece p, int x, int y){
        if(outOfBounds(x, y)) return;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(pieces[i][j] == p){
                    pieces[i][j] = null;
                }
            }
        }      
        pieces[x][y] = p;
    }                

    /** Removes the piece at x, y */
    public Piece remove(int x, int y){
        if(outOfBounds(x, y)){
            System.out.println("("+x+", "+y+") is out of bounds");
            return null;
        }else if(pieceAt(x, y)==null){
            System.out.println("There is no piece at ("+x+", "+y+")");
            return null;
        }
        Piece removing = pieceAt(x, y);
        pieces[x][y] = null;
        return removing;
    }

    /** Returns true if the current player can end their turn */
    public boolean canEndTurn(){
        return hasMoved;
    }

    /** Ends the current player's turn (and starts the other player's) */
    public void endTurn(){
        hasMoved = false;
        isFireTurn = !isFireTurn;
        selectX = -1;
        selectY = -1;
        for(Piece[] ps : pieces){
            for(Piece p : ps){
                if(p!=null) p.doneCapturing();
            }
        }
    }

    /** Returns the winner of the game, if any */
    public String winner(){
        int fireCount = 0;
        int waterCount = 0;
        for(Piece[] ps : pieces){
            for(Piece p : ps){
                if(p!=null){
                    if(p.isFire()) fireCount++;
                    else waterCount++;
                }
            }
        }
        if(fireCount==0 && waterCount==0){
            return "No one";
        }else if(fireCount == 0){
            return "Water";
        }else if(waterCount == 0){
            return "Fire";
        }
        return null;
    }
}