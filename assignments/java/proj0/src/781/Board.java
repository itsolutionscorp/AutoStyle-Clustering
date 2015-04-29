// 12/36 Test passed 2/14/15 16:23
// 13/36 Test passed 2/14/15 17:30

public class Board {

    private Piece[][] pieces;
    private boolean [][] kingPieces;
    private int N = 8;
    private int rescaleSize = 1;
    private boolean isFireTurn = true;
    private int oldxClick = -1;
    private int oldyClick = -1;
    private boolean isPieceSelected;
    private Piece removed;
    private Piece pieceSelected;
    private boolean moved;
    private boolean canCapture;
    private boolean northeast;
    private boolean northwest;
    private boolean southeast;
    private boolean southwest;
    private Piece deadPiece;


    public Board(boolean shouldBeEmpty) { 
        // System.out.println(shouldBeEmpty);
        // empty = shouldBeEmpty;
        System.out.println("Board(boolean shouldBeEmpty): " + shouldBeEmpty);
        StdDrawPlus.setScale(0, N);
        pieces = new Piece[N][N];
        drawBoard(N);
        if (!shouldBeEmpty) {
            drawPieces(N);
        }   
    }

    private void drawBoard(int N) { // drawing playing board
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }       

                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

            }
        }
    }

    private void drawPieces(int N) { // drawing playing pieces at default configuration
        for (int y = 0; y < N; y += 1) {
            for (int x = 0; x < N; x += 1) {
                if ((y == 0) && (x % 2 == 0)) {
                    StdDrawPlus.picture(x + 0.5, y + 0.5, "img/pawn-fire.png", rescaleSize, rescaleSize);
                    pieces[x][y] = new Piece(true, this, x, y, "Pawn");
                } else if ((y == 1) && (x % 2 == 1)) {
                    StdDrawPlus.picture(x + 0.5, y + 0.5, "img/shield-fire.png", rescaleSize, rescaleSize);
                    pieces[x][y] = new Piece(true, this, x, y, "Shield");
                } else if ((y == 2) && (x % 2 == 0)) {
                    StdDrawPlus.picture(x + 0.5, y + 0.5, "img/bomb-fire.png", rescaleSize, rescaleSize);
                    pieces[x][y] = new Piece(true, this, x, y, "Bomb");
                } else if ((y == 5) && (x % 2 == 1)) {
                    StdDrawPlus.picture(x + 0.5, y + 0.5, "img/bomb-water.png", rescaleSize, rescaleSize);
                    pieces[x][y] = new Piece(false, this, x, y, "Bomb");
                } else if ((y == 6) && (x % 2 == 0)) {
                    StdDrawPlus.picture(x + 0.5, y + 0.5, "img/shield-water.png", rescaleSize, rescaleSize);
                    pieces[x][y] = new Piece(false, this, x, y, "Shield");
                } else if ((y == 7) && (x % 2 == 1)) {
                    StdDrawPlus.picture(x + 0.5, y + 0.5, "img/pawn-water.png", rescaleSize, rescaleSize);
                    pieces[x][y] = new Piece(false, this, x, y, "Pawn");
                }
            }
        }
        // StdDrawPlus.show(100);
    }

    public Piece pieceAt(int x, int y) { // check to see what piece is at (x,y)
        System.out.println("pieceAt(int" + x + ", int " + y + ")");
        if ((x > 8) || (y > 8)) {
            // pieces[x][y] = null;
            return null;
        } else if ((x < 0) || (y < 0)) {
            // pieces[x][y] = null;
            return null;
        } else if (pieces[x][y] == null) {
            return null;
        } else {
            return pieces[x][y];
        }
    }

    public void place(Piece p, int x, int y) { // places piece at (x,y)
        System.out.println("place(Piece" + p + ", int " + x + ", int " + y + ")");
        if ((((x < 8) && (x >= 0)) && ((y < 8) && (y >= 0))) && p != null) {
            pieces[x][y] = p;
            // if ((oldxClick < 0) && (oldyClick < 0)) {
            //     removed = remove(x, y);
            // } else {
            //     removed = remove(oldxClick, oldyClick);
            // }
            // oldxClick = x;
            // oldyClick = y;
            moved = true;
            isPieceSelected = false;
            // redrawPiece(oldxClick, oldyClick, x, y);
            // if (isFireTurn && (y == 7)) {
            //     pieces[x][y] = new Piece(true, this, x, y, "King");
            // } else if ((!isFireTurn) && (y == 0)) {
            //     pieces[x][y] = new Piece(false, this, x, y, "King");
            // }

        }
    }


    private void redrawPiece(int oldx, int oldy, int newx, int newy) {
        Piece piecetype = pieceAt(oldx, oldy);
        if (piecetype.isFire()) {
            if (piecetype.isKing()) {
                if (piecetype.isBomb()) {
                    StdDrawPlus.picture(newx + 0.5, newy + 0.5, "img/bomb-fire-crowned.png", rescaleSize, rescaleSize);
                } else if (piecetype.isShield()) {
                    StdDrawPlus.picture(newx + 0.5, newy + 0.5, "img/king-fire-crowned.png", rescaleSize, rescaleSize);
                } else {
                    StdDrawPlus.picture(newx + 0.5, newy + 0.5, "img/pawn-fire-crowned.png", rescaleSize, rescaleSize);
                }
            } else {
                if (piecetype.isBomb()) {
                    StdDrawPlus.picture(newx + 0.5, newy + 0.5, "img/bomb-fire.png", rescaleSize, rescaleSize);
                } else if (piecetype.isShield()) {
                    StdDrawPlus.picture(newx + 0.5, newy + 0.5, "img/Shield-fire.png", rescaleSize, rescaleSize);
                } else {
                    StdDrawPlus.picture(newx + 0.5,newy + 0.5, "img/pawn-fire.png", rescaleSize, rescaleSize);
                }
            }
        } else {
            if (piecetype.isKing()) {
                if (piecetype.isBomb()) {
                    StdDrawPlus.picture(newx + 0.5, newy + 0.5, "img/bomb-water-crowned.png", rescaleSize, rescaleSize);
                } else if (piecetype.isShield()) {
                    StdDrawPlus.picture(newx + 0.5, newy + 0.5, "img/king-water-crowned.png", rescaleSize, rescaleSize);
                } else {
                    StdDrawPlus.picture(newx + 0.5, newy + 0.5, "img/pawn-water-crowned.png", rescaleSize, rescaleSize);
                }
            } else {
                if (piecetype.isBomb()) {
                    StdDrawPlus.picture(newx + 0.5, newy + 0.5, "img/bomb-water.png", rescaleSize, rescaleSize);
                } else if (piecetype.isShield()) {
                    StdDrawPlus.picture(newx + 0.5, newy + 0.5, "img/Shield-water.png", rescaleSize, rescaleSize);
                } else {
                    StdDrawPlus.picture(newx + 0.5, newy + 0.5, "img/pawn-water.png", rescaleSize, rescaleSize);
                }
            }
        }
    }

    public boolean canSelect(int x, int y) {
        if (isFireTurn) { // fire piece's turn
            System.out.println("fire piece's turn");
            System.out.println("canSelect(int " + x + ", int " + y + ")");
            if (isPieceSelected) {
                if ((oldxClick == x) && (oldyClick == y)) {
                    return true;
                } else if (pieceAt(x, y) != null) {
                    if (isFireTurn && pieceAt(x,y).isFire()) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    boolean isMoveValid = validMove(oldxClick, oldyClick, x, y);
                    if (isMoveValid) {
                        return true;
                    } else {
                        return false;
                    }
                }
            } else {
                if (pieceAt(x, y) != null) {
                    if (isFireTurn == pieceAt(x,y).isFire()) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } else { // water piece's turn
        System.out.println("water piece's turn");
            System.out.println("canSelect(int " + x + ", int " + y + ")");
            if (isPieceSelected) {
                if ((oldxClick == x) && (oldyClick == y)) {
                    return true;
                } else if (pieceAt(x, y) != null) {
                    if (isFireTurn == pieceAt(x,y).isFire()) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    boolean isMoveValid = validMove(oldxClick, oldyClick, x, y);
                    if (isMoveValid) {
                        return true;
                    } else {
                        return false;
                    }
                }
            } else {
                if (pieceAt(x, y) != null) {
                    if (isFireTurn == pieceAt(x,y).isFire()) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
    }

    public void select(int x, int y) {

        System.out.println("select(int " + x + ", int " + y + ")");
        if ((oldxClick == x) && (oldyClick == y)) {
            isPieceSelected = true;
        } else {
            if (isPieceSelected) {
                boolean isMoveValid = validMove(oldxClick, oldyClick, x, y);
                if (isMoveValid) {
                    pieceSelected = pieces[oldxClick][oldyClick];
                    pieceSelected.move(x,y);
                    unhighlight(oldxClick, oldyClick);
                    moved = true;
                    redrawPiece(oldxClick, oldyClick, x, y);
                    place(pieceSelected, x, y);
                    removed = remove(oldxClick, oldyClick);
                    if (northeast) {
                        deadPiece = remove(x-1, y-1);
                        unhighlight(x-1, y-1);
                    } else if (northwest) {
                        deadPiece = remove(x+1, y-1);
                        unhighlight(x+1, y-1);
                    } else if (southeast) {
                        deadPiece = remove(x-1, y+1);
                        unhighlight(x-1, y+1);
                    } else if (southwest) {
                        deadPiece = remove(x+1, y+1);
                        unhighlight(x+1, y+1);
                    }

                    if (canCapture) {
                        pieces[x][y].hasCaptured();
                    }
                    oldxClick = x;
                    oldyClick = y;
                } else {
                    unhighlight(oldxClick, oldyClick);
                    redrawPiece(oldxClick, oldyClick, oldxClick, oldyClick);
                    highlight(x, y);
                    redrawPiece(x, y, x, y);
                    pieceSelected = pieces[x][y];
                    oldxClick = x;
                    oldyClick = y;

                }
            } else {
                highlight(x, y);
                redrawPiece(x, y, x , y);
                isPieceSelected = true;
                pieceSelected = pieces[x][y];
                oldxClick = x;
                oldyClick = y;
            }
        }
    }

    private void highlight(int x, int  y) {

        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        StdDrawPlus.filledSquare(x + .5, y + .5, .5);

    }

    private void unhighlight(int x, int y) {
        if ((x + y) % 2 == 0) {
            StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        } else {
            StdDrawPlus.setPenColor(StdDrawPlus.RED);
        }       

        StdDrawPlus.filledSquare(x + .5, y + .5, .5);
    }

    public Piece remove(int x, int y) {
        System.out.println("remove(int" + x + ", int " + y + ")");
        Piece toBeRemoved = pieces[x][y];
        if (pieceAt(x, y) == null) {
            System.out.println("Nothing to be removed! "); 
            return null;
        } else {
            pieces[x][y] = null;
            moved = true;
            return toBeRemoved;
        }

    }

    private boolean validMove(int xi, int yi, int xf, int yf) { // checks to see if moving from initial to final is valid
        if (pieceAt(xi, yi) != null) {
            boolean isFirePiece = pieces[xi][yi].isFire(); // checks to see if piece is a fire piece
            boolean isKingPiece = pieces[xi][yi].isKing(); // checks to see if piece is king
            boolean isBombPiece = pieces[xi][yi].isBomb(); // checsk to see if piece is bomb
            boolean isShieldPiece = pieces[xi][yi].isShield(); // checks to see if piece is shield

            if ((Math.abs(xf - xi) == 1 && Math.abs(yf - yi) == 1) && (pieces[xf][yf] == null)) {
                // checks to see if player is moving only 1 square diagonal and if that square is empty
                if (isFirePiece) { // if player is moving fire piece
                    if (isKingPiece) { // if player is moving the fire king piece; king can moving in any directions
                        return true;
                    } else if (yf > yi) { // player is moving fire piece other than king; this piece can only move upward
                        return true;
                    } else {
                        return false;
                    }
                } else { // if player is moving water piece 
                    if (isKingPiece) { // if player is moving water king piece; king cna moving in any directions
                        return true;
                    } else if (yf < yi) { // player is moving water piece other than king; this piece can only move downward
                        return true;
                    } else {
                        return false;
                    }
                }

            } else if (((Math.abs(xf - xi) == 2) && (Math.abs(yf - yi) == 2)) && (pieces[xf][yf] == null)) {
                // checks to see player is moving 2 squares diagonal (capturing) and if final square is empty
                boolean isWaterPiece = false;
                boolean empty = true;
                
                // checking to see if there is a piece to capture
                if (yf > yi) {
                    if (xf > xi) { // if player is capturing in northeast direction
                        if (pieces[xf-1][yf-1] != null) {
                            northeast = true;
                            empty = false;
                            isWaterPiece = !(pieces[xf-1][yf-1].isFire());
                        }
                    } else { // if player is capturing in northwest direction
                        if (pieces[xf+1][yf-1] != null) {
                            northwest = true;
                            empty = false;
                            isWaterPiece = !(pieces[xf+1][yf-1].isFire());
                        }
                    }
                } else {
                    if (xf > xi) { // if player is capturing in southeast direction
                        if (pieces[xf-1][yf+1] != null) {
                            southeast = true;
                            empty = false;;
                            isWaterPiece = !(pieces[xf-1][yf+1].isFire());
                        }
                    } else { // if player is capturing in southwest direction
                        if (pieces[xf+1][yf+1] != null) {
                            empty = false;
                            southwest = true;
                            isWaterPiece = !(pieces[xf+1][yf+1].isFire());
                        }
                    }
                }

                if ((!empty) && isFirePiece && isWaterPiece) { // if piece is fire and water piece is in between
                    if (isKingPiece) { // if fire king piece
                        canCapture = true;
                        return true;
                    } else if (yf > yi) { // if fire piece is moving forward
                        canCapture = true;
                        return true;
                    }
                } else if ((!empty) && (!isFirePiece) && (!isWaterPiece)) { // if piece is water and fire piece is in between
                    if (isKingPiece) { // if water king piece
                        canCapture = true;
                        return true;
                    } if (yf < yi) { // if water piece moving downward
                        canCapture = true;
                        return true;
                    }
                }
            } 
        }
        return false;
    }

    public String winner() {
        int firePoint = 0;
        int waterPoint = 0;
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                if (pieceAt(i, j) != null) {
                    if (pieces[i][j].isFire()) {
                        firePoint += 1;
                    } else {
                        waterPoint += 1;
                    }
                }
            }
        }

        if (firePoint == waterPoint) {
            return "No one";
        } else if (firePoint > waterPoint) {
            return "Fire";
        } else {
            return "Water";
        }
    }

    public boolean canEndTurn() {
        if (moved) { // && (!isPieceSelected)) {
            return true;
        } else {
            return false;
        }
    }

    public void endTurn() {
        pieceSelected = null;
        removed = null;
        moved = false;
        isPieceSelected = false;
        oldxClick = -1;
        oldyClick = -1;
        isFireTurn = !isFireTurn;

    } 
    
    public static void main(String[] args) {
        Board b = new Board(false);
        while (true) {
            double x = 0;
            double y = 0;

            if (StdDrawPlus.mousePressed()) {
                x = StdDrawPlus.mouseX();
                y = StdDrawPlus.mouseY();
                boolean canSelectPlace = b.canSelect((int) x, (int) y);
                if (canSelectPlace) {
                    b.select((int) x, (int) y);
                }
            }

            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                    b.endTurn();
                }
            }
            // b.winner();
            StdDrawPlus.show(100);

        }
    }
}