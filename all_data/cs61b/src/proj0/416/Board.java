import java.awt.Color;

public class Board {
    
    private static int boardSize = 8;
    private Piece[][] pieces = new Piece[boardSize][boardSize];
    private int player = 0;

    private boolean hasSelected = false;
    private boolean hasMoved = false;

    private Piece selectedPiece = null;
    private int selectedX = -1;
    private int selectedY = -1;

    public static void main(String[] args) {
        Board b = new Board(false);

        boolean running = true;
        while (running) {
            if (StdDrawPlus.mousePressed()) {
                int mousex = (int) StdDrawPlus.mouseX();
                int mousey = (int) StdDrawPlus.mouseY();
                if (b.canSelect(mousex, mousey)) {
                    b.select(mousex, mousey);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                    b.endTurn();
                }
            }
            StdDrawPlus.show(10);
            b.drawBoard(boardSize);
            b.drawPieces();
            if (!StdDrawPlus.mousePressed() && b.winner() != null) {
                running = false;
            }
        }
        System.out.println(b.winner());
    }

    public Board(boolean shouldBeEmpty) {
        drawBoard(boardSize);
        if (!shouldBeEmpty) {
            initPieces(boardSize);
        }
    }

    public boolean canSelect(int x, int y) {
        if (x < boardSize && y < boardSize) {
            if (((x + y) % 2) == 0) {
                Piece p = pieceAt(x, y);
                if (p != null) {
                    if (p.side() == player) {
                        if (selectedPiece == null) {
                            return true;
                        }
                        else if (!selectedPiece.hasCaptured() && !hasMoved) {
                            return true;
                        }
                    }
                }
                else if (hasSelected) {
                    if (validMove(x, y)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void select(int x, int y) {
        hasSelected = true;
        Piece p = pieceAt(x, y);
        if (p != null) {
            selectedPiece = p;
            selectedX = x;
            selectedY = y;
            selectedPiece.move(x, y);
        }
        else {
            if (selectedPiece != null) {
                selectedX = x;
                selectedY = y;
                selectedPiece.move(x, y);
                hasMoved = true;
            }
        }
    }

    public void place(Piece p, int x, int y) {
        if (p == null || x > pieces.length || y > pieces[0].length) {}
        else {
            pieces[x][y] = p;
        }
    }

    public Piece remove(int x, int y) {
        if ((x < boardSize && y < boardSize) && (x >= 0 && y >= 0)) {
            Piece p = pieces[x][y];
            if (p == null) {
                System.out.println("No piece at: " + x + ", " + y);
            }
            pieces[x][y] = null;
            return p;
        }
        System.out.println("remove: x or y out of bounds: " + x + ", " + y);
        return null;
        
    }

    private void drawPieces() {
        for (int i = 0; i < pieces.length; i+=1) {
            for (int j = 0; j < pieces[0].length; j+=1) {
                Piece p = pieceAt(i, j);
                if (p != null) {
                    p.move(i, j);
                }
            }
        }
    }

    private boolean validMove(int x, int y) {
        int dx = x - selectedX;
        int dy = y - selectedY;
        boolean king = false;
        boolean fire = false;
        if (selectedPiece.isKing()) {king = true;}
        if (selectedPiece.isFire()) {fire = true;}

        if (fire && !king) {
            if (dy < 0) {return false;}
        }
        else if (!fire && !king) {
            if (dy > 0) {return false;}
        }

        if (hasMoved && !selectedPiece.hasCaptured()) {
            return false;
        }

        if (Math.abs(dx) == 1 && Math.abs(dy) == 1) {
            if (hasMoved) {
                return false;
            }
            return true;
        }

        if (Math.abs(dx) == 2 && Math.abs(dy) == 2) {
            int betweenX = ((x - selectedX) / 2) + selectedX;
            int betweenY = ((y - selectedY) / 2) + selectedY;
            Piece p = pieceAt(betweenX, betweenY);
            if (p != null && p.isFire() != fire) {
                return true;
            }
        }

        return false;
    }

    private void initPieces(int N) {
        for (int i = 0; i < N; i+=1) {
            for (int j = 0; j < N; j+=1) {
                pieces[i][j] = null;
                if ((i + j) % 2 == 0) {
                    boolean fire = true;
                    String type = "pawn";
                    if (j == 1 || j == N-2) {type = "shield";}
                    if (j == 2 || j == N-3) {type = "bomb";}
                    if (j > N/2) {fire = false;}
                    if (j < 3 || j > N - 4) {
                        pieces[i][j] = new Piece(fire, this, i, j, type);
                    }
                }
            }
        }
    }

    private void drawBoard(int N) {

        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == selectedX && j == selectedY) {
                    colorSquare(i, j, StdDrawPlus.WHITE);
                }
                else if ((i + j) % 2 == 0) {
                    colorSquare(i, j, StdDrawPlus.GRAY);
                }
                else colorSquare(i, j, StdDrawPlus.RED);
            }
        }
    }

    public Piece pieceAt(int x, int y) {
        if ((x < boardSize && y < boardSize) && (x >= 0 && y >= 0)) {
            return pieces[x][y];
        }
        return null;
    }

    public boolean canEndTurn() {
        return hasMoved;
    }

    public void endTurn(){
        if (player == 0) {player = 1;}
        else {player = 0;}
        hasMoved = false;
        hasSelected = false;
        selectedPiece.doneCapturing();
        selectedPiece = null;
        selectedX = -1;
        selectedY = -1;
    }

    public String winner() {
        int fires = 0;
        int waters = 0;
        for (int i = 0; i < boardSize; i+=1) {
            for (int j = 0; j < boardSize; j+=1) {
                if (pieces[i][j] != null) {
                    if (pieces[i][j].isFire()) 
                         {fires+=1;}
                    else {waters+=1;}
                }
            }
        }
        if (waters == 0 && fires == 0) {return "No one";}
        if (waters == 0) {return "Fire";}
        if (fires == 0) {return "Water";}
        else {return null;}
    }

    private void colorSquare(int x, int y, Color c) {
        StdDrawPlus.setPenColor(c);
        StdDrawPlus.filledSquare(x + .5, y + .5, .5);
    }
}