public class Board {
    private Piece pieces[][];
    private boolean fireTurn = true;
    private Piece selectedPiece = null;
    private int selectedPieceX;
    private int selectedPieceY;
    private boolean hasSelected = false;
    private Piece lastPieceMoved = null;
    private int waterPieces;
    private int firePieces;
    public Board(boolean shouldBeEmpty) {
        pieces = new Piece[8][8];
        waterPieces = 12;
        firePieces = 12;
        fireTurn = true;
        if (shouldBeEmpty == false) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if ((i + j) % 2 == 0) {
                        if (j == 2) {
                            pieces[i][j] = new Piece(true, this, i, j, "bomb");
                        }
                        if (j == 1) {
                            pieces[i][j] = new Piece(true, this, i, j, "shield");
                        }
                        if (j == 0) {
                            pieces[i][j] = new Piece(true, this, i, j, "pawn");
                        }
                        if (j == 5) {
                            pieces[i][j] = new Piece(false, this, i, j, "bomb");
                        }
                        if (j == 6) {
                            pieces[i][j] = new Piece(false, this, i, j, "shield");
                        }
                        if (j == 7) {
                            pieces[i][j] = new Piece(false, this, i, j, "pawn");
                        }
                    }
                }
            }
        }
    }

    private String imgPath(Piece p) {
        String toReturn = "img/";
        if (p.isFire()) {
            if (p.isBomb())
                toReturn = toReturn.concat("bomb-fire");
            if (p.isShield())
                toReturn = toReturn.concat("shield-fire");
            if (p.isShield() == false && p.isBomb() == false)
                toReturn = toReturn.concat("pawn-fire");
            if (p.isKing())
                toReturn = toReturn.concat("-crowned.png");
            if (p.isKing() == false)
                toReturn = toReturn.concat(".png");
        }

        if (p.isFire() == false) {
            if (p.isBomb())
                toReturn = toReturn.concat("bomb-water");
            if (p.isShield())
                toReturn = toReturn.concat("shield-water");
            if (p.isShield() == false && p.isBomb() == false)
                toReturn = toReturn.concat("pawn-water");
            if (p.isKing())
                toReturn = toReturn.concat("-crowned.png");
            if (p.isKing() == false)
                toReturn = toReturn.concat(".png");
        }
        return toReturn;
    }

    private void drawBoard() {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0)
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
             
                else                  
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);

                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (pieces[i][j] != null) {
                    if (selectedPiece == pieces[i][j]) {
                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                        StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                        StdDrawPlus.picture(i + .5, j + .5, imgPath(selectedPiece), 1, 1);
                    }

                    else {
                       StdDrawPlus.picture(i + .5, j + .5, imgPath(pieceAt(i, j)), 1, 1);
                    }
                }
            }
        }
    }

    public Piece pieceAt(int x, int y) {
        if (x >= 8 || y >= 8)
            return null;
        if (x < 0 || y < 0)
            return null;
        else
            return pieces[x][y];
    }

    public void place(Piece p, int x, int y) {
        if (x < 8 && y < 8 && x >= 0 && y >= 0) {
            pieces[x][y] = p;
        }
    }

    public Piece remove(int x, int y) {
        if (x >= 8 || y >= 8) {
            System.out.println("This spot does not exist on the board");
            return null;
        }

        if (pieceAt(x, y) != null) {
            Piece p = pieceAt(x, y);
            pieces[x][y] = null;
            return p;
        }

        else {
            return null;
        }

    }

    public void select(int x, int y) {
        if (pieces[x][y] != null) {
            selectedPiece = pieces[x][y]; 
            selectedPieceX = x;
            selectedPieceY = y;
            hasSelected = true;   
        } 
        
        if (pieces[x][y] == null && hasSelected == true) {
            selectedPiece.move(x, y);
            selectedPieceX = x;
            selectedPieceY = y;
            lastPieceMoved = pieceAt(x, y);
        }


        
    }

    private boolean validPiece(int x, int y) {
        if (fireTurn == true) {
            if (pieceAt(x, y).isFire()) {
                return true;
            }

            else
                return false;
        }

        else {
            if (pieceAt(x, y).isFire())
                return false;
            else 
                return true;
            
        }
        
    }

    public boolean canSelect(int x, int y) {
        if (lastPieceMoved != null && lastPieceMoved.hasCaptured() == false)
            return false;

        if (pieceAt(x, y) != null) {
            if (selectedPiece == null) {
                return validPiece(x, y);
            }

            if (lastPieceMoved == null) {
                return validPiece(x, y);
            }
        }

        if (pieceAt(x, y) == null) {
            if (selectedPiece != null && pieceAt(x, y) == null)
                return isValidMove(x, y);

            if (selectedPiece != null && pieceAt(x, y) == null && selectedPiece.hasCaptured())
                return isValidMove(x, y);

            else
                return false;
        }



        return false;
            
    }

    private boolean isValidMove(int x, int y) {
        int captureX = (x + selectedPieceX)/2;
        int captureY = (y + selectedPieceY)/2;

        if (selectedPiece.isKing() == false) { //Checks if selected piece is King
            if (selectedPiece.isFire()) { //checks if selected piece is a fire piece
                if ((y - selectedPieceY) == 1 && Math.abs(x - selectedPieceX) == 1 && pieceAt(x, y) == null) {
                    return true; //checks if valid location to move fire piece
                }
                if (pieces[captureX][captureY] != null && pieces[captureX][captureY].isFire() == false && (y - selectedPieceY) == 2 && Math.abs(selectedPieceX - x) == 2)
                    return true; //checks if valid spot to move after capturing a piece
                else
                    return false;

            }

            else {
                if ((selectedPieceY - y) == 1 && Math.abs(x - selectedPieceX) == 1 && pieceAt(x, y) == null)
                    return true;
                if (pieces[captureX][captureY] != null && pieces[captureX][captureY].isFire() == true && (selectedPieceY - y) == 2 && Math.abs(selectedPieceX - x) == 2)
                    return true;
                else
                    return false;
            }
        }
        else {
            if (selectedPiece.isFire()) {
                if (Math.abs(y - selectedPieceY) == 1 && Math.abs(selectedPieceX - x) == 1 && pieceAt(x, y) == null)
                    return true;
                if (pieces[captureX][captureY] != null && pieces[captureX][captureY].isFire() == false && Math.abs(y - selectedPieceY) == 2 && Math.abs(selectedPieceX - x) == 2)
                    return true;
                else
                    return false;
            }
            else {
                if (Math.abs(selectedPieceY - y) == 1 && Math.abs(selectedPieceX - x) == 1 && pieceAt(x, y) == null)
                    return true;
                if (pieces[captureX][captureY] != null && pieces[captureX][captureY].isFire() == true && Math.abs(selectedPieceY - y) == 2 && Math.abs(selectedPieceX - x) == 2)
                    return true;
                else
                    return false;
            }
        }
    }

    public boolean canEndTurn() {
        if (lastPieceMoved != null)
            return true;
        else
            return false;
    }

    public void endTurn() {
        lastPieceMoved.doneCapturing();
        lastPieceMoved = null;
        fireTurn = !fireTurn;
        selectedPiece = null;
        selectedPieceX = 0;
        selectedPieceY = 0;

    }


    private void countPieces() {
        firePieces = 0;
        waterPieces = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] != null && pieces[i][j].isFire())
                    firePieces += 1;
                if (pieces[i][j] != null && pieces[i][j].isFire() == false)
                    waterPieces += 1;
            }
        }
    }

    public String winner() {
        countPieces();
        if (firePieces == 0 && waterPieces == 0)
            return "No one";

        if (firePieces == 0)
            return "Water";

        if (waterPieces == 0)
            return "Fire";

        else
            return null;

    }


    public static void main(String[] args) {
        Board b = new Board(false);
        while(b.winner() == null) {
            b.drawBoard(); 
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int x1 = (int)x;
                int y1 = (int)y;
                if (b.canSelect(x1, y1)) { 
                    b.select(x1, y1);
                }
            
            }

            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn())
                    b.endTurn();
            }


            StdDrawPlus.show(25);


        }
        b.drawBoard(); 

        System.out.println(b.winner());
    }
}