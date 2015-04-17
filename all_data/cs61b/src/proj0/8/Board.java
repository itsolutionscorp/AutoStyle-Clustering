import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Board {

    private static Piece[][] pieces;
    private Board board1;
    private boolean p1_turn = true;
    private boolean piece_selected = false;
    private boolean piece_moved = false;
    private boolean has_captured = false;
    private boolean can_move = false;
    private int curr_x;
    private int curr_y;
    private int size = 8;


    //create an method that takes SPiece of the piece and returns the type
    private static String ImageReturn(Piece P1) {
        if (P1 == null) {
            return "Nothing Here";
        }
        if (P1.isFire() && !P1.isShield() && !P1.isBomb() && P1.isKing()) {
            return "img/pawn-fire-crowned.png";
        } else if (P1.isFire() && P1.isShield() && P1.isKing()) {
            return "img/shield-fire-crowned.png";
        } else if (P1.isFire() && P1.isBomb() && P1.isKing()) {
            return "img/bomb-fire-crowned.png";
        } else if (!P1.isFire() && !P1.isShield() && !P1.isBomb() && P1.isKing()) {
            return "img/pawn-water-crowned.png";
        } else if (!P1.isFire() && P1.isShield() && P1.isKing()) {
            return "img/shield-water-crowned.png";
        } else if (!P1.isFire() && P1.isBomb() && P1.isKing()) {
            return "img/bomb-water-crowned.png";
        } else if (P1.isFire() && !P1.isShield() && !P1.isBomb()) {
            return "img/pawn-fire.png";
        } else if (P1.isFire() && P1.isShield()) {
            return "img/shield-fire.png";
        } else if (P1.isFire() && P1.isBomb()) {
            return "img/bomb-fire.png";
        } else if (!P1.isFire() && !P1.isShield() && !P1.isBomb()) {
            return "img/pawn-water.png";
        } else if (!P1.isFire() && P1.isShield()) {
            return "img/shield-water.png";
        } else if (!P1.isFire() && P1.isBomb()) {
            return "img/bomb-water.png";
        } else if (P1.isFire() && !P1.isShield() && !P1.isBomb()) {
            return "img/pawn-fire.png";
        }
        return "Nothing Here";
    }


    private static void drawHelper(int i, int j) {
        if (pieces[i][j] != null) {
            StdDrawPlus.picture(i + .5, j + .5, ImageReturn(pieces[i][j]), 1, 1);
        }
    }

    private static void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
                drawHelper(i, j);
            }
        }
    }

    public Board(boolean shouldBeEmpty) {
        pieces = new Piece[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                pieces[i][j] = null;
            }
        }
        if (shouldBeEmpty) {
            return;
        }

//        pieces[2][2] = new Piece(true, this, 2, 2, "shield");
//        pieces[3][3] = new Piece(false, this, 3, 3, "bomb");

//        pieces[1][1] = new Piece(false, this, 1, 1, "shield");
//        pieces[4][6] = new Piece(true, this, 4, 6, "bomb");
//        pieces[6][6] = new Piece(true, this, 6, 6, "pawn");
//
//        pieces[2][2] = new Piece(true, this, 2, 2, "pawn");
//        pieces[3][3] = new Piece(true, this, 3, 3, "pawn");


//
//        pieces[1][1] = new Piece(false, this, 1, 1, "shield");
//        pieces[6][6] = new Piece(true, this, 6, 6, "shield");
//        pieces[1][5] = new Piece(false, this, 1, 5, "bomb");
//        pieces[3][5] = new Piece(false, this, 3, 5, "bomb");
//        pieces[6][4] = new Piece(false, this, 5, 5, "bomb");
//        pieces[7][5] = new Piece(false, this, 7, 5, "bomb");
//
//        pieces[0][6] = new Piece(false, this, 0, 6, "shield");
//        pieces[4][6] = new Piece(false, this, 4, 6, "shield");
//        pieces[6][6] = new Piece(false, this, 6, 6, "shield");
//
//        pieces[1][7] = new Piece(false, this, 1, 7, "pawn");
//        pieces[3][7] = new Piece(true, this, 3, 7, "pawn");
//        pieces[5][7] = new Piece(false, this, 5, 7, "pawn");
//        pieces[7][7] = new Piece(false, this, 7, 7, "pawn");
//
//        pieces[0][0] = new Piece(true, this, 0, 0, "pawn");
//        pieces[2][0] = new Piece(true, this, 2, 0, "pawn");
//        pieces[4][0] = new Piece(false, this, 4, 0, "pawn");
//        pieces[6][0] = new Piece(true, this, 6, 0, "pawn");
//
//        pieces[1][1] = new Piece(true, this, 1, 1, "shield");
//        pieces[3][1] = new Piece(true, this, 3, 1, "shield");
//        pieces[5][1] = new Piece(true, this, 5, 1, "shield");
//        pieces[7][1] = new Piece(true, this, 7, 1, "shield");
//
//        pieces[0][2] = new Piece(true, this, 0, 2, "bomb");
//        pieces[2][2] = new Piece(true, this, 2, 2, "bomb");
//        pieces[4][2] = new Piece(true, this, 4, 2, "bomb");
//        pieces[6][2] = new Piece(true, this, 6, 2, "bomb");


        //correct setup
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

        pieces[1][5] = new Piece(false, this, 1, 5, "bomb");
        pieces[3][5] = new Piece(false, this, 3, 5, "bomb");
        pieces[5][5] = new Piece(false, this, 5, 5, "bomb");
        pieces[7][5] = new Piece(false, this, 7, 5, "bomb");

        pieces[0][6] = new Piece(false, this, 0, 6, "shield");
        pieces[2][6] = new Piece(false, this, 2, 6, "shield");
        pieces[4][6] = new Piece(false, this, 4, 6, "shield");
        pieces[6][6] = new Piece(false, this, 6, 6, "shield");

        pieces[1][7] = new Piece(false, this, 1, 7, "pawn");
        pieces[3][7] = new Piece(false, this, 3, 7, "pawn");
        pieces[5][7] = new Piece(false, this, 5, 7, "pawn");
        pieces[7][7] = new Piece(false, this, 7, 7, "pawn");

    }


    //created pieceAt method that returns null or the piece at x,y
    //need to create the pieces array
    public Piece pieceAt(int x, int y) {
        System.out.println("executing pieceAt function at " + x + ", " + y);
        if (x > 7 || x < 0 || y > 7 || y < 0) { // out of bounds -> null
            return null;
        } else if (pieces[x][y] == null) { //  empty space -> null
            return null;
        } else {
            return pieces[x][y];
        }
    }

    public void place(Piece p, int x, int y) {
        if (x > 7 || x < 0 || y > 7 || y < 0) {
            return;
        } else if (pieceAt(x, y) != null) {
            remove(x, y);
            pieces[x][y] = p;
            System.out.println("piece " + ImageReturn(p) + " placed at " + x + " " + y);
        } else {
            pieces[x][y] = p;
            System.out.println("piece " + ImageReturn(p) + " placed at " + x + " " + y);
        }
    }

    //created a remove method that removes whatever piece is at (x,y)
    public Piece remove(int x, int y) {
        if (pieceAt(x, y) == null) {
            System.out.println("No piece found");
            return null;
        } else if (x > 7 || x < 0 || y > 7 || y < 0) {
            System.out.println("Out of Bounds!");
            return null;
        } else {
            Piece removedpiece = pieces[x][y];
            pieces[x][y] = null;
            System.out.println("piece " + ImageReturn(removedpiece) + " removed from " + x + " " + y);
            return removedpiece;
        }
    }

    public boolean canSelect(int x, int y) {
        Piece temporary = pieces[x][y];
        Piece selected = pieces[curr_x][curr_y];
        if (p1_turn) {
            if (temporary != null && temporary.isFire()) { //if space has fire piece
                if (!piece_selected || (piece_selected && !piece_moved)) { // if no piece selected or piece selected and has not moved
                    return true;
                }
            } else if (temporary == null) { // if space is empty
                System.out.println("valid move: " + valid_move(x, y));
                if (piece_selected && !piece_moved && valid_move(x, y)) { //if piece is selected and piece hasnt moved and position at x, y is valid
                    return true;
                } else if (piece_selected && selected != null && selected.hasCaptured() && valid_move(x, y)) { //if piece is selected and selected piece is not empty and move is valid
                    return true;
                }
            }
        }
        if (!p1_turn) {
            if (temporary != null && !temporary.isFire()) { // if piece at space is water piece and exists
                if (!piece_selected || (piece_selected && !piece_moved)) {// if piece is not selected or piece selected and has not moved
                    return true;
                }
            } else if (temporary == null) { // space empty
                System.out.println("valid move: " + valid_move(x, y));
                if (piece_selected && !piece_moved && valid_move(x, y)) { // if piece is selected and piece has not moved and position x y is valid
                    return true;
                } else if (piece_selected && selected != null && selected.hasCaptured() && valid_move(x, y)) {// if piece is selected and selected piece is not empty and move is valid
                    return true;
                } //selected != null && selected.hasCaptured()
            }
        }
        return false;
    }


    //theres more to add to valid move such as if the piece is a water piece, if it is a king piece.
    private boolean valid_move(int x, int y) {
        Piece selected = pieces[curr_x][curr_y];
        if (piece_moved && selected != null && !selected.hasCaptured()) {
            return false;
        } else {
            if (pieces[curr_x][curr_y] != null) { //makes sure that the curr_x and curr_y are not null before running valid move
                if (piece_selected && pieces[curr_x][curr_y].isFire()) {
                    if (pieces[curr_x][curr_y].isKing()) {//for king to capture bc it can move in all dir
                        if (curr_x + 2 == x && curr_y + 2 == y && pieces[curr_x + 1][curr_y + 1] != null && !pieceAt(curr_x + 1, curr_y + 1).isFire()) {
                            return true;
                        } else if (curr_x - 2 == x && curr_y - 2 == y && pieces[curr_x - 1][curr_y - 1] != null && !pieceAt(curr_x - 1, curr_y - 1).isFire()) {
                            return true;
                        } else if (curr_x + 2 == x && curr_y - 2 == y && pieces[curr_x + 1][curr_y - 1] != null && !pieceAt(curr_x + 1, curr_y - 1).isFire()) {
                            return true;
                        } else if (curr_x - 2 == x && curr_y + 2 == y && pieces[curr_x - 1][curr_y + 1] != null && !pieceAt(curr_x + 1, curr_y + 1).isFire()) {
                            return true;
                        } else if (!piece_moved && Math.abs(curr_x - x) == 1 && Math.abs(curr_y - y) == 1) {
                            return true;
                        }
                    }
                    if (!piece_moved && ((curr_x + 1 == x) && (curr_y + 1 == y)) || !piece_moved && ((curr_x - 1 == x) && (curr_y + 1 == y))) {
                        return true;
                    } else if ((curr_x + 2 == x) && (curr_y + 2 == y) && pieces[curr_x + 1][curr_y + 1] != null) {
                        if (!pieceAt(curr_x + 1, curr_y + 1).isFire()) {
                            return true;
                        }
                    } else if ((curr_x - 2 == x) && (curr_y + 2 == y) && pieces[curr_x - 1][curr_y + 1] != null) {
                        if (!pieceAt(curr_x - 1, curr_y + 1).isFire()) {
                            return true;
                        }
                    }

                } else if (piece_selected && !pieces[curr_x][curr_y].isFire()) {
                    if (pieces[curr_x][curr_y].isKing()) {
                        if (curr_x + 2 == x && curr_y + 2 == y && pieces[curr_x + 1][curr_y + 1] != null && pieceAt(curr_x + 1, curr_y + 1).isFire()) {
                            return true;
                        } else if (curr_x - 2 == x && curr_y - 2 == y && pieces[curr_x - 1][curr_y - 1] != null && pieceAt(curr_x - 1, curr_y - 1).isFire()) {
                            return true;
                        } else if (curr_x + 2 == x && curr_y - 2 == y && pieces[curr_x + 1][curr_y - 1] != null && pieceAt(curr_x + 1, curr_y - 1).isFire()) {
                            return true;
                        } else if (curr_x - 2 == x && curr_y + 2 == y && pieces[curr_x - 1][curr_y + 1] != null && pieceAt(curr_x - 1, curr_y + 1).isFire()) {
                            return true;
                        } else if (!piece_moved && Math.abs(curr_x - x) == 1 && Math.abs(curr_y - y) == 1) {
                            return true;
                        }
                    }
                    if (!piece_moved && ((curr_x - 1 == x) && (curr_y - 1 == y)) || !piece_moved && ((curr_x + 1 == x) && (curr_y - 1 == y))) {
                        return true;
                    } else if ((curr_x - 2 == x) && (curr_y - 2 == y) && pieces[curr_x - 1][curr_y - 1] != null) {
                        if (pieceAt(curr_x - 1, curr_y - 1).isFire()) {
                            return true;
                        }
                    } else if ((curr_x + 2 == x) && (curr_y - 2 == y) && pieces[curr_x + 1][curr_y - 1] != null) {
                        if (pieceAt(curr_x + 1, curr_y - 1).isFire()) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }

    //We assume canSelect is true for the select method
    //if the temporary piece is there, change piece_selected to true and color the square white.
    //if temporary is null, change can_move to true color the square white and call the move function on the
    //curr_x and curr_y
    public void select(int x, int y) {
        System.out.println("executing select function at " + x + ", " + y);
        Piece temporary = pieces[x][y];
        Piece selected_piece = pieces[curr_x][curr_y];
//        if (canSelect(x, y)) { // canSelect && select should not call each other
        if (temporary != null) { // piece is there
            piece_selected = true; // set piece selected to true
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            StdDrawPlus.filledSquare(x + .5, y + .5, .5);
            drawHelper(x, y);
        } else if (temporary == null) {// if area selected is empty & piece selected then move piece
            can_move = true; //can move is set to true
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            StdDrawPlus.filledSquare(x + .5, y + .5, .5);
            pieces[curr_x][curr_y].move(x, y); //calls current piece and moves it to x, y
//                board_checker();
            piece_moved = true; // piece moved is set to true
//                if((Math.abs(x - curr_x) == 2) && (Math.abs(y - curr_y) == 2)) {
//                    has_captured = true;
        }
        curr_x = x; // current x set to move position x
        curr_y = y; // current y set to move position y
    }


    //not sure if i need to call done capturing from piece.java
    public boolean canEndTurn() {
        Piece selected = pieces[curr_x][curr_y];
        if (piece_moved || selected != null && selected.hasCaptured()) {
            return true;
        } else {
            return false;
        }
    }

    public void endTurn() {
        System.out.println("endTurn called");
        piece_selected = false;
        piece_moved = false;
        has_captured = false;
        winner();
        if (p1_turn) {
            p1_turn = false;
        } else if (!p1_turn) {
            p1_turn = true;
        }
    }

    //this is causing some problems for some reason
    public String winner() {
        int fire = 0;
        int water = 0;
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                Piece temporary = pieces[i][j];
                if (temporary != null) {
                    if (temporary.isFire()) {
                        fire++;
                    } else {
                        water++;
                    }
                }
            }
        }
        if (fire > 0 && water == 0) {
            return "Fire";
        } else if (water > 0 && fire == 0) {
            return "Water";
        } else if (water == 0 && fire == 0) {
            return "No One";
        } else {
            return null;
        }
    }

//
//    public String string_comparison(String i) {
//        if (i == "img/pawn-fire-crowned.png") {
//            return "PFC";
//        } else if (i == "img/shield-fire-crowned.png") {
//            return "SFC";
//        } else if (i == "img/bomb-fire-crowned.png") {
//            return "BFC";
//        } else if (i == "img/pawn-fire.png") {
//            return "PF";
//        } else if (i == "img/shield-fire.png") {
//            return "SF";
//        } else if (i == "img/bomb-fire.png") {
//            return "BF";
//        } else if (i == "img/pawn-water-crowned.png") {
//            return "PWC";
//        } else if (i == "img/shield-water-crowned.png") {
//            return "SWC";
//        } else if (i == "img/bomb-water-crowned.png") {
//            return "BWC";
//        } else if (i == "img/pawn-water.png") {
//            return "PW";
//        } else if (i == "img/shield-water.png") {
//            return "SW";
//        } else if (i == "img/bomb-water.png") {
//            return "BW";
//        }
//        return "nothing here";
//    }
//
//    public void board_checker() {
////        Pattern p = Pattern.compile("img/(\\S+).png");
//        for (int j = 7; j >= 0; j--) {
//            for (int i = 0; i <= 7; i++) {
//                if (pieces[i][j] == null) {
//                    System.out.print("\t");
//                } else {
////                    Matcher m = p.matcher(ImageReturn(pieces[i][j]));
//                    System.out.print(string_comparison(ImageReturn(pieces[i][j])) + "\t");
//                }
//            }
//            System.out.println();
//        }
//    }


    //draw board size is set to 8
    //curr_x and curr_y is set to 0
    //Board board1 is instantiated with the pieces
    public static void main(String[] args) throws Exception {
        int n = 8;
        Board board1 = new Board(false);
        StdDrawPlus.setXscale(0, n);
        StdDrawPlus.setYscale(0, n);
        drawBoard(n);
        /** Monitors for mouse presses. Wherever the mouse is pressed,
         a new piece appears. */
        //might want to have while loop be set to checking if there is a Winner?
        //otherwise it will loop infinitely
        while (true) {
            drawBoard(n);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
//                curr_x = (int) x;
//                curr_y = (int) y;
//                System.out.println(board1.canSelect((int) x, (int) y));
                if ((int) x > 7 || (int) x < 0 || (int) y > 7 || (int) y < 0) {
                    System.out.println("Out of Bounds");
                }

                if (board1.canSelect((int) x, (int) y)) {
                    board1.select((int) x, (int) y);
//                    board1.curr_x = (int) x;
//                    board1.curr_y = (int) y;
                }
            }
            StdDrawPlus.show(100);
            if (StdDrawPlus.isSpacePressed()) {
                System.out.println("space pressed");
                System.out.println("can end turn: " + board1.canEndTurn());
                if (board1.canEndTurn()) {
                    if (pieces[board1.curr_x][board1.curr_y] != null) { // prevents null pointer
                        pieces[board1.curr_x][board1.curr_y].doneCapturing(); // sets hascaptured to false
                    }
                    board1.endTurn();
//                    board1.piece_selected = false;
//                    board1.piece_moved = false;
//                    board1.has_captured = false;
                    System.out.println("player ones turn: " + board1.p1_turn);
                    if (board1.winner() != null) {
                        System.out.println("Game Finished");
                        return;
                    }
                }
            }
        }
    }
}


//System.out.println("Moving piece at:" + this.Sx + ", " + this.Sy + " to " + x + "," + y);