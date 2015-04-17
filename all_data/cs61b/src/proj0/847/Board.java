import com.sun.org.apache.bcel.internal.generic.Select;

import java.awt.*;

public class Board {
    private int turn = 0;
    private boolean hasMoved = false;
    private Piece selectedPiece;
    private Piece[] gamePieces;
    private Piece[][] board2d;
    private Color selectColor;
    private double highlightX;
    private double highlightY;
    private int selectedPieceX;
    private int selectedPieceY;
    private boolean isGameOver;
    private String[] bombAnimations = {"img/expl1.png", "img/expl2.png", "img/expl3.png",
            "img/expl4.png", "img/expl5.png"};

    public static void main(String[] args){
        Board b = new Board(false);
        StdDrawPlus.setCanvasSize();
        StdDrawPlus.setXscale(0,8);
        StdDrawPlus.setYscale(0,8);
//        Color fireColor = Color.RED;
//        Color baseColor = Color.DARK_GRAY;
        Color fireColor = new java.awt.Color(14960);
        Color baseColor = new java.awt.Color(16626965);
        StdDrawPlus.clear(baseColor);
        StdDrawPlus.setPenColor(baseColor);

        double PrevX = .5;
        double PrevY = .5;
        double SelectedX = .5;
        double SelectedY = .5;
        boolean fireAlive = false;
        boolean waterAlive = false;
        String gameover = null;
//PLAY THE GAME
        while(true) {
            StdDrawPlus.show(10);
//--------------CONTINUOUS BUILD OF BOARD------
            double x = .5;
            double y = .5;
            boolean fire = false;
            while (x < 8.5) {
                while (y < 8.5) {
                    if (fire) {
                        StdDrawPlus.setPenColor(fireColor);
                    }
                    else {
                        StdDrawPlus.setPenColor(baseColor);
                    }
                    StdDrawPlus.filledSquare(x, y, .5);
                    fire = !fire;
                    y += 1;
                }
                fire = !fire;
                x += 1;
                y = .5;
            }

//Highlight cursor square
            SelectedX = Math.floor(StdDrawPlus.mouseX()) + .5;
            SelectedY = Math.floor(StdDrawPlus.mouseY()) + .5;
            StdDrawPlus.setPenColor(Color.WHITE);
            StdDrawPlus.filledSquare(SelectedX, SelectedY, .5);
            Piece atPoint = b.board2d[(int)SelectedX][(int)SelectedY];


//MouseClicking
            if (StdDrawPlus.mousePressed()) {
                if (b.canSelect((int)SelectedX, (int)SelectedY)) {
                    if (b.pieceAt((int)SelectedX, (int)SelectedY) == null && b.selectedPiece != null &&
                            b.selectedPiece.isBomb() && Math.abs(b.selectedPieceX - (int)SelectedX) == 2) {
                        for (String fl: b.bombAnimations) {
                            StdDrawPlus.show(30);
                            b.DrawPieces(b);
                            StdDrawPlus.picture(SelectedX, SelectedY, fl, 4, 4);
                        }
                    }
                    b.select((int) (SelectedX), (int) (SelectedY));
                }
            }
            b.DrawPieces(b);

            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                    b.endTurn();
                }
            }
//CHECK IF GAME IS OVER
            gameover = b.winner();
            if (gameover != null && !b.isGameOver) {
                System.out.println(gameover + " wins!");
                b.isGameOver = true;
            }


            if (gameover != null && StdDrawPlus.isNPressed()) {
                b = new Board(false);
            }


        }

    }

    public Board(boolean shouldBeEmpty) {
        selectedPieceX = 0;
        selectedPieceY = 0;
        selectColor = null;
        highlightX = 0;
        highlightY = 0;
        gamePieces = new Piece[24];
        board2d = new Piece[8][8];
        isGameOver = false;
        selectedPiece = null;
        if (!shouldBeEmpty) {
            int x = 0;
            int y = 0;
            int i = 0;
            boolean isFire = true;
            String[] types = {"pawn", "shield", "bomb"};


            for (String s: types) {
                while (x < 8) {
                    Piece placed = new Piece(isFire, this, x, y, s);
                    gamePieces[i] = placed;
                    place(placed, x, y);
                    x += 2;
                    i += 1;
                }
                y += 1;
                if (y % 2 != 0) {
                    x = 1;
                }
                else {
                    x = 0;
                }
            }
            isFire = false;
            y = 7;
            for (String s: types) {
                while (x < 8) {
                    Piece placed = new Piece(isFire, this, x, y, s);
                    gamePieces[i] = placed;
                    place(placed, x, y);
                    x += 2;
                    i += 1;
                }
                y -= 1;
                if (y % 2 != 0) {
                    x = 1;
                }
                else {
                    x = 0;
                }
            }
        }
    }

    public Piece pieceAt(int x, int y) {
        if (0 <= x && x <= 7 && 0 <= y && y <= 7) {
            if (this.board2d[x][y] != null) {
                return this.board2d[x][y];
            }
        }
        return null;
    }

    private void DrawPieces(Board b) {
        if (b.selectColor != null) {
            StdDrawPlus.setPenColor(b.selectColor);
            StdDrawPlus.filledSquare(b.highlightX, b.highlightY, .5);
        }
        int x = 0;
        int y = 0;
        Piece layPiece;
        while (x <= 7) {
            while (y <= 7) {
                if (b.board2d[x][y] != null) {
                    layPiece = b.board2d[x][y];
                    if (layPiece.isShield()) {
                        if (layPiece.side() == 0) {
                            if (layPiece.isKing()) {
                                StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
                            }
                        } else {
                            if (layPiece.isKing()) {
                                StdDrawPlus.picture(x + .5, y + .5, "img/shield-water-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
                            }
                        }
                    }
                    else if (layPiece.isBomb()) {
                        if (layPiece.side() == 0) {
                            if (layPiece.isKing()) {
                                StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
                            }
                        } else {
                            if (layPiece.isKing()) {
                                StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
                            }
                        }
                    }
                    else {
                        if (layPiece.side() == 0) {
                            if (layPiece.isKing()) {
                                StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
                            }
                        } else {
                            if (layPiece.isKing()) {
                                StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);
                            }
                        }
                    }
                }

                y += 1;
            }
            x += 1;
            y = 0;
        }
    }

    public void select(int x, int y) {
        if (selectedPiece != pieceAt(x, y) && pieceAt(x, y) == null){
            selectedPiece.move(x, y);
            selectedPieceX = x;
            selectedPieceY = y;
            hasMoved = true;
        }
        else {
            selectedPiece = board2d[x][y];
            selectedPieceX = x;
            selectedPieceY = y;
        }
//        selectColor = Color.ORANGE;
        selectColor = Color.RED;
        highlightX = (double)x + .5;
        highlightY = (double)y + .5;

    }

    public boolean canSelect(int x, int y) {
        if ((x > 7) || (x < 0) || (y > 7) || (y < 0)) {
            return false;
        }
        Piece atPoint = pieceAt(x, y);
        if (atPoint == null && (selectedPiece != null)) {
            if (hasMoved && (!selectedPiece.hasCaptured())) {
                return false;
            }

            int diffx = Math.abs(selectedPieceX - x);
            int diffy = y - selectedPieceY;
            if ((diffx == 2) && (Math.abs(diffy) == 2)) {
                Piece opponentCheck = board2d[(x + selectedPieceX)/2][(y + selectedPieceY)/2];
                if ((opponentCheck != null) && (!(opponentCheck.side() == turn))) {
                    if ((turn == 0) && (!selectedPiece.isKing())) {
                        return diffy == 2;
                    }
                    else if ((turn == 1) && (!selectedPiece.isKing())) {
                        return diffy == -2;
                    }
                    else {
                        return true;
                    }
                }
            }
            else if ((!selectedPiece.hasCaptured()) && (!hasMoved)) {
                if ((turn == 0) && (!selectedPiece.isKing())) {
                    return (Math.abs(selectedPieceX - x) == 1) && (diffy == 1);
                }
                else if ((turn == 1) && (!selectedPiece.isKing())) {
                    return (Math.abs(selectedPieceX - x) == 1) && (diffy == -1);
                }
                else {
                    return (diffx == 1) && (Math.abs(diffy) == 1);
                }
            }

        }
//        Debugging assist by Michael Cho, Below are cases of selecting new pawn
        if ((atPoint != null) && (atPoint.side() == turn) && (selectedPiece == null)) {
            return true;
        }
        else if ((selectedPiece != null) && selectedPiece.hasCaptured() && (hasMoved == false)){
            return true;
        }
        else if ((selectedPiece != null) && hasMoved == false
                && pieceAt(x, y) != null && turn == pieceAt(x, y).side()) {
            return true;
        }
        return false;
    }

    public void place(Piece p, int x, int y) {
        Piece replaced = board2d[x][y];
        if (replaced != null) {
            remove(x, y);
        }
        board2d[x][y] = p;
    }

    public Piece remove(int x, int y) {
        Piece removed = board2d[x][y];
        if (removed != null) {
            board2d[x][y] = null;
        }
        else {
            if ((x > 7) || (x < 0) || (y > 7) || (y < 0)) {
                System.out.println("Can't remove out of bounds");
                return null;
            }
            System.out.println("Can't remove from a null space");
        }
        return removed;
    }

    public boolean canEndTurn() {
        if ((selectedPiece != null) && ((selectedPiece.hasCaptured() || hasMoved))) {
            return true;
        }
        return false;
    }

    public void endTurn() {
        hasMoved = false;
        if (selectedPiece!=null) {
            selectedPiece.doneCapturing();
        }
        if (turn == 0) {
            turn = 1;
        }
        else {
            turn = 0;
        }
        selectedPiece = null;

    }

    public String winner() {
        boolean fireAlive = false;
        boolean waterAlive = false;
        for (Piece[] column: board2d) {
            for (Piece isAlive: column){
                if (isAlive!=null) {
                    if (isAlive.isFire()) {
                        fireAlive = true;
                    }
                    else {
                        waterAlive = true;
                    }
                }
            }
        }
        if (!fireAlive && !waterAlive) {
            return "No one";
        }
        else if (!fireAlive) {
            return "Water";
        }
        else if (!waterAlive) {
            return "Fire";
        }
        return null;

    }


}