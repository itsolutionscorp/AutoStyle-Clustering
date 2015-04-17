public class Board {

    private Piece[][] pieces;
    private int currentPlayer; //fire = 0, water = 1
    private boolean playerHasSelected;
    private boolean playerHasMadeMove;
    private boolean playerInMultiCapture;
    private Piece activePiece;
    private int activePieceX;
    private int activePieceY;

    public Board(boolean shouldBeEmpty) {
        currentPlayer = 0;
        pieces = new Piece[8][8];
        if (!shouldBeEmpty) {
            fillPieces();
        }
    }

    public Piece pieceAt(int x, int y) {
        if (!validPoint(x, y)) {
            return null;
        }
        return pieces[x][y];
    }

    public boolean canSelect(int x, int y) {
        if (pieceAt(x, y) == null) {
            if (playerHasSelected && !playerHasMadeMove && validMove(activePieceX, activePieceY, x, y)) {
                return true;
            } else if (playerInMultiCapture && playerHasMadeMove && validMove(activePieceX, activePieceY, x, y)) {
                return true;
            } else {
                return false;
            }
        } else {
            Piece piece = pieceAt(x, y);
            if (currentPlayer == piece.side()) {
                if (!playerHasSelected || (playerHasSelected && !playerHasMadeMove)) {
                    return true;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    public void select(int x, int y) {
        Piece piece = pieceAt(x, y);
        if (piece != null) {
            playerHasSelected = true;
            activePiece = piece;
            activePieceX = x;
            activePieceY = y;
        } else if (piece == null && activePiece != null) {
            playerHasMadeMove = true;
            activePiece.move(x, y);
            if (activePiece.hasCaptured()) {
                playerInMultiCapture = true;
            }
            activePieceX = x;
            activePieceY = y;
        }
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        Piece piece = pieceAt(xi, yi);
        if (!validPoint(xf, yf) || piece == null) {
            return false;
        }
        if (piece.isKing()) {
            if ((xf == xi + 1 || xf == xi - 1) && (yf == yi + 1 || yf == yi - 1)) {
                Piece square = pieceAt(xf, yf);
                if (square == null && !piece.hasCaptured()) {
                    return true;
                } else {
                    return false;
                }
            } else if ((xf == xi - 2) && (yf == yi + 2)) {
                return validMoveForLocation(piece, xi - 1, yi + 1, xf, yf);
            } else if ((xf == xi + 2)  && (yf == yi + 2)) {
                return validMoveForLocation(piece, xi + 1, yi + 1, xf, yf);
            } else if ((xf == xi - 2) && (yf == yi - 2)) {
                return validMoveForLocation(piece, xi - 1, yi - 1, xf, yf);
            } else if ((xf == xi + 2) && (yf == yi - 2)) {
                return validMoveForLocation(piece, xi + 1, yi - 1, xf, yf);
            } else {
                return false;
            }
        } else { //not king
            if (piece.isFire()) {
                if ((xf == xi + 1 || xf == xi - 1) && (yf == yi + 1)) {
                    Piece square = pieceAt(xf, yf);
                    if (square == null && !piece.hasCaptured()) {
                        return true;
                    } else {
                        return false;
                    }
                } else if ((xf == xi - 2) && (yf == yi + 2)) {
                    return validMoveForLocation(piece, xi - 1, yi + 1, xf, yf);
                } else if ((xf == xi + 2)  && (yf == yi + 2)) {
                    return validMoveForLocation(piece, xi + 1, yi + 1, xf, yf);
                } else {
                    return false;
                }
            } else {
                if ((xf == xi + 1 || xf == xi - 1) && (yf == yi - 1)) {
                    Piece square = pieceAt(xf, yf);
                    if (square == null && !piece.hasCaptured()) {
                        return true;
                    } else {
                        return false;
                    }
                } else if ((xf == xi - 2) && (yf == yi - 2)) {
                    return validMoveForLocation(piece, xi - 1, yi - 1, xf, yf);
                } else if ((xf == xi + 2)  && (yf == yi - 2)) {
                    return validMoveForLocation(piece, xi + 1, yi - 1, xf, yf);
                } else {
                    return false;
                }
            }
        }
    }

    private boolean validMoveForLocation(Piece currentPiece, int otherPieceX, int otherPieceY, int xf, int yf) {
        Piece square = pieceAt(xf, yf);
        Piece piece2 = pieceAt(otherPieceX, otherPieceY);
        if (piece2 == null) {
            return false;
        } else if (square == null && currentPiece.side() != piece2.side()) {
            return true;
        } else if (square == null && currentPiece.side() == piece2.side()) {
            return false;
        }
        return false;
    }

    public void place(Piece p, int x, int y) {
        if (!validPoint(x, y)) {
            return;
        }
        pieces[x][y] = p;
    }

    public Piece remove(int x, int y) {
        if (!validPoint(x, y)) {
            System.out.println("Tried to remove from an invalid location.");
            return null;
        }
        Piece piece = pieces[x][y];
        pieces[x][y] = null;
        return piece;
    }

    public boolean canEndTurn() {
        return playerHasMadeMove;
    }

    public void endTurn() {
        playerHasSelected = false;
        playerHasMadeMove = false;
        playerInMultiCapture = false;
        currentPlayer = currentPlayer == 0 ? 1 : 0;
        activePiece.doneCapturing();
        activePiece = null;
        activePieceX = 0;
        activePieceY = 0;
    }

    public String winner() {
        int numFirePieces = 0;
        int numWaterPieces = 0;
        for (int i = 0; i < pieces.length; i += 1) {
            for (int j = 0; j < pieces[0].length; j += 1) {
                if (pieces[i][j] != null) {
                    if (pieces[i][j].isFire()) {
                        numFirePieces += 1;
                    } else {
                        numWaterPieces += 1;
                    }
                }
            }
        }
        if (numWaterPieces == 0 && numFirePieces == 0) {
            return "No one";
        } else if (numFirePieces > 0 && numWaterPieces == 0) {
            return "Fire";
        } else if (numWaterPieces > 0 && numFirePieces == 0) {
            return "Water";
        } else {
            return null;
        }
    }

    private boolean validPoint(int x, int y) {
        if (x < 0 || x >= pieces.length || y < 0 || y >= pieces.length) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Board board = new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        board.drawBoard();
        board.drawPieces();

        while(true) {
            board.drawBoard();
            board.drawPieces();
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int boardX = (int) x;
                int boardY = (int) y;
                Piece piece = board.pieceAt(boardX, boardY);
                if (board.canSelect(boardX, boardY)) {
                    board.select(boardX, boardY);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (board.canEndTurn()) {
                    board.endTurn();
                }
            }
            StdDrawPlus.show(100);
            if (board.winner() != null) {
                System.out.println(board.winner());
                return;
            }
        }
    }

    private void fillPieces() {
        int N = pieces.length;
        for (int i = 0; i < N; i += 2) {
            int j = 0;
            new Piece(true, this, i, j, "pawn");
        }
        for (int i = 1; i < N; i += 2) {
            int j = 1;
            new Piece(true, this, i, j, "shield");
        }
        for (int i = 0; i < N; i += 2) {
            int j = 2;
            new Piece(true, this, i, j, "bomb");
        }
        for (int i = 1; i < N; i += 2) {
            int j = N - 3;
            new Piece(false, this, i, j, "bomb");
        }
        for (int i = 0; i < N; i += 2) {
            int j = N - 2;
            new Piece(false, this, i, j, "shield");
        }
        for (int i = 1; i < N; i += 2) {
            int j = N - 1;
            new Piece(false, this, i, j, "pawn");
        }

    }

    private void highlight(int x, int y) {
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        StdDrawPlus.filledSquare(x + .5, y + .5, .5);
    }

    private void drawBoard() {
        int N = pieces.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
        if (activePiece != null) {
            highlight(activePieceX, activePieceY);
        }
    }

    private void drawPieces() {
        int N = pieces.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Piece piece = pieces[i][j];
                if (piece != null) {
                    if (piece.isFire()) {
                        if (piece.isBomb()) {
                            if (piece.isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                            }
                        } else if (piece.isShield()) {
                            if (piece.isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                            }
                        } else {
                            if (piece.isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                            }
                        }
                    } else {
                        if (piece.isBomb()) {
                            if (piece.isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                            }
                        } else if (piece.isShield()) {
                            if (piece.isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                            }
                        } else {
                            if (piece.isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                            }
                        }
                    }
                }
            }
        }
    }

}
