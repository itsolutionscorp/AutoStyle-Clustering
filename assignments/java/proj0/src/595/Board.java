import java.awt.*;
import java.awt.image.*;


public class Board{

    private boolean[][] pieces = new boolean[8][8];
    private boolean shouldBeEmpty = true;
    private Piece pieces_set[][] = new Piece[8][8];
    private Piece selected_piece = null;
    private int selected_x, selected_y;
    private int turn = 0;
    private boolean moved = false;

    public static void main(String args[]){
        int x, y;
        int N = 8;
        String winner = null;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board playBoard = new Board(false);
        while (winner == null) {
            playBoard.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x_mouse = StdDrawPlus.mouseX();
                double y_mouse = StdDrawPlus.mouseY();
                if (playBoard.canSelect((int) x_mouse, (int) y_mouse)) {
                playBoard.select((int) x_mouse, (int) y_mouse);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (playBoard.canEndTurn()) {
                    playBoard.endTurn();
                    winner = playBoard.winner();
                }
            }
            StdDrawPlus.show(100);    
            }
            System.out.println(winner);
        }

    public Board(boolean shouldBeEmpty) {
        int N = 8;
        int x, y;
        this.shouldBeEmpty = shouldBeEmpty;
         if (this.shouldBeEmpty) {
        } else {
            for (y = 0; y < N; y++) {
                for (x = 0; x < N; x++) {
                    if ((y == 0) && (x % 2 == 0)) {
                    this.pieces[x][y] = true;
                    this.pieces_set[x][y] = new Piece(true, this, x, y, "pawn");
                }  else if ((y == 1) && (x % 2 != 0)) {
                    this.pieces[x][y] = true;
                    this.pieces_set[x][y] = new Piece(true, this, x, y, "shield");
                } else if ((y == 2) && (x % 2 == 0)) {
                    this.pieces[x][y] = true;
                    this.pieces_set[x][y] = new Piece(true, this, x, y, "bomb");
                } else if ((y == 7) && (x % 2 != 0)) {
                    this.pieces[x][y] = true;
                    this.pieces_set[x][y] = new Piece(false, this, x, y, "pawn");
                }  else if ((y == 6) && (x % 2 == 0)) {
                    this.pieces[x][y] = true;
                    this.pieces_set[x][y] = new Piece(false, this, x, y, "shield");
                } else if ((y == 5) && (x % 2 != 0)) {
                    this.pieces[x][y] = true;
                    this.pieces_set[x][y] = new Piece(false, this, x, y, "bomb");
                }
                else {
                    this.pieces[x][y] = false;
                }
            }
        }
    }
    }

    private void drawBoard(double N) {
        double y;
        double x;
        for (y = 0; y < N; y += 1.0) {
            for (x = 0; x < N; x += 1.0) {
                if ((x + y) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } 
                else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if ((this.selected_piece != null) && (x == (double) selected_x) && (y == (double) selected_y)){
                    StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
                }
                if (this.pieces[(int) x][(int) y]) {
                    StdDrawPlus.picture(x + .5, y + .5, this.getImage(this.pieces_set[(int) x][(int) y]), 1, 1);
                }
            }
        }
    }

private String getImage(Piece piece) {
        String elem;
        if (piece.isFire()) {
            elem = "fire";
        } else{
            elem = "water";
        }
            if (piece.isBomb()){
                if (piece.isKing()){
                    return "img/bomb-" + elem +"-crowned.png";
                } else {
                    return "img/bomb-" + elem +".png";
                }
            } else if (piece.isShield()) {
                if (piece.isKing()){
                    return "img/shield-" + elem +"-crowned.png";
                } else {                    return "img/shield-" + elem +".png";
                }
            }else { 
                if (piece.isKing()){
                    return "img/pawn-" + elem +"-crowned.png";
                } else {
                    return "img/pawn-" + elem +".png";
                }
            }
    }
    
    

    public Piece pieceAt(int x, int y) {
        if ((x >= 0) && (y >= 0) && (x < 8) && (y < 8)) {
            if (this.pieces[x][y]) {
                return this.pieces_set[x][y];
            }
        }
        return null;
    }

    public boolean canSelect(int x, int y) {
        // System.out.println("if there are no selected piece, true");
        // System.out.println(this.selected_piece == null);
        // System.out.println("this moved");
        // System.out.println(this.moved);
        // System.out.println("Selected x and y");
        // System.out.println(selected_x);
        // System.out.println(selected_y);
        // System.out.println("This is a valid move");
        // System.out.println(validMove(selected_x, selected_y, x, y));
        if ((this.selected_piece == null) && (this.moved == true)) {
            return false;}
        if ((this.pieceAt(x, y) != null) && (this.pieceAt(x, y).side() == this.turn)){
            if (this.selected_piece == null) {
                return true;
            } else if (this.moved == false) {
            return true; }
        } else if (this.pieceAt(x, y) == null) {
            if ((this.selected_piece != null) &&  (this.moved == false) && validMove(selected_x, selected_y, x, y)) {
                return true;
        } else if (((this.selected_piece) != null) && (validMove(selected_x, selected_y, x, y))&& (this.selected_piece.hasCaptured())){
            return true;
        }
        }
        return false;
    }
    private boolean validMove(int xi, int yi, int xf, int yf){
        if ((xf < 0) || (yf < 0) || (xf >= 8) || (yf >= 8)) {
            return false;
        }
        Piece clicked_piece = this.pieceAt(xi, yi);
        int dx = xf - xi;
        int dy = yf - yi;
        int x_half = xi + (dx/2);
        int y_half = yi + (dy/2);
        boolean not_king_valid = (((clicked_piece.isFire()) && (dy > 0)) || ((clicked_piece.isFire() == false) && (dy < 0)));
        boolean king_valid = clicked_piece.isKing();
        if  ((not_king_valid)|| (king_valid)) {
            if (((Math.abs(dx) == 1) && (Math.abs(dy) == 1)) && (this.pieceAt(xf, yf) == null)){
                if (clicked_piece.hasCaptured()) {
                    return false;
                }
                return true;
                }
            else if ((Math.abs(dx) == 2) && (Math.abs(dy) == 2) && (this.pieceAt(xf, yf) == null) && (this.pieceAt(x_half, y_half) != null) && (this.pieceAt(x_half, y_half).side() != clicked_piece.side())){
                    if ((clicked_piece.isFire()) != (this.pieceAt(x_half, y_half).isFire())) {
                        return true;
                    }          
                }
            }
        return false;
    }
    public void select(int x, int y) {
            if (this.pieceAt(x, y) != null) {
                selected_x = x;
                selected_y = y;
                this.selected_piece = this.pieceAt(x, y);
            } else if ((this.pieceAt(x, y) == null) && (this.selected_piece != null)) {
                // System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                this.moved = true;
                this.selected_piece.move(x, y);
                this.selected_piece = this.pieceAt(x, y);    
                selected_x = x;
                selected_y = y;
            }
        }

    public boolean canEndTurn(){
        boolean a, b;
        a = this.moved;
        if (this.selected_piece == null) {
            b = false;
        } else {
            b = this.selected_piece.hasCaptured(); }
        return (a || b);
    }

    public void endTurn() {
        this.moved = false;

        this.selected_piece = null;
        if (this.turn == 0) {
            this.turn = 1;
        } else {
            this.turn = 0;
        } 
    }

    public String winner() {
        int y;
        int x;
        int red = 0;
        int other = 0;
        for (y = 0; y < 8; y++) {
                for (x = 0; x < 8; x++) {
                    if (this.pieceAt(x, y) != null) {
                        if (this.pieceAt(x, y).isFire()) {
                        red += 1;
                    } else {
                        other += 1;
                    }
                }
            }
        }
        if ((red != 0) && (other == 0)) {
            return "Fire";
        } else if ((other != 0) && (red == 0)) {
            return "Water";
        }else if ((other == 0) && (red == 0)) {
            return "No one";
        } else {
            return null;
        }
    }

//NEEEEEEEEEEEEEEEED TOOOO CONSIDER KING PIECES


    public void place(Piece p, int x, int y) {
        if ((x < 0) || (y < 0) || (x >= 8) || (y >= 8) || (p == null)) {
            return;
        }
        int i, j;
        for (j = 0; j < 8; j++) {
                for (i = 0; i < 8; i++) {
            if (this.pieceAt(i, j) == p) {
                this.remove(i, j);
                }
            }
        }
        if (this.pieceAt(x, y) != null) {
            this.remove(x, y);
        }

           this.pieces[x][y] = true;
           this.pieces_set[x][y] = p;
        }

    public Piece remove(int x, int y) {
        if ((x < 0) || (y < 0) || (x >= 8) || (y >= 8)) {
            System.out.println("The piece is out of bound.");
            return null;
        }
        else if (this.pieceAt(x, y) == null) {
            System.out.println("There is no piece.");
            return null;
        }
        Piece removed_piece =  this.pieceAt(x, y);
        this.pieces[x][y] = false;
        this.pieces_set[x][y] = null;
        return removed_piece;
    }

               
    
    }


