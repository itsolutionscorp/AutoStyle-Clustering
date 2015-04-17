/**
 * @author Abhi Sharma
 */
public class Board {

    private Piece[][] pieces = new Piece[8][8];
    private Piece currentPiece;
    private boolean isFireTurn = true;
    private boolean hasSelected = false;
    private boolean hasFinishedMoving = false;
    private boolean justKinged = false;
    private int lastX, lastY;
    private int fireCount, waterCount = 0;

    public static void main(String[] args) {
        Board game = new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        game.drawBoard();
        StdDrawPlus.show(1000);
    }

    public Board(boolean shouldBeEmpty) {
        if (shouldBeEmpty) {

        } else {
            String type;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 3; j++) {
                    if (j == 2)
                        type = "bomb";
                    else if (j == 1)
                        type = "shield";
                    else
                        type = "pawn";
                    if ((i + j) % 2 == 0)
                        this.place(new Piece(true, this, i, j, type), i, j);
                }
            }
            for (int i = 0; i < 8; i++) {
                for (int j = 7; j > 4; j--) {
                    if (j == 5)
                        type = "bomb";
                    else if (j == 6)
                        type = "shield";
                    else
                        type = "pawn";
                    if ((i + j) % 2 == 0)
                        this.place(new Piece(false, this, i, j, type), i, j);
                }
            }
        }
    }

    private void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (this.pieces[i][j] != null) {
                    drawPiece(this.pieces[i][j], i, j);
                }
            }
        }
    }

    private void drawPiece(Piece p, int x, int y) {
        String img;
        if (p.isBomb())
            img = "bomb";
        else if (p.isShield())
            img = "shield";
        else
            img = "pawn";
        if (p.isFire())
            img += "-fire";
        else
            img += "-water";
        if (p.isKing())
            img += "-crowned.png";
        else
            img += ".png";
        StdDrawPlus.picture(x + .5, y + .5, img, 1, 1);
    }

    public Piece pieceAt(int x, int y) {
        if (x >= 0 && x < 8 && y >= 0 && y < 8 && this.pieces[x][y] != null)
            return this.pieces[x][y];
        return null;
    }

    public boolean canSelect(int x, int y) {
        boolean can = false;
        Piece selectedPiece = this.pieceAt(x, y);
        if (selectedPiece != null) {
            if (selectedPiece.isFire() == this.isFireTurn) {
                boolean secondSelect = this.hasSelected && !this.hasFinishedMoving && !selectedPiece.hasCaptured();
                if (secondSelect || !this.hasSelected) {
                    can = true;
                }
            }
        } else if (this.hasSelected && this.validMove(this.currentPiece.isFire() || this.currentPiece.isKing(), !this.currentPiece.isFire() || this.currentPiece.isKing(), this.lastX, this.lastY, x, y)) {
            can = !this.hasFinishedMoving || !this.currentPiece.hasCaptured() || this.validCapture(this.currentPiece.isFire() || this.currentPiece.isKing(), !this.currentPiece.isFire() || this.currentPiece.isKing(), this.lastX, this.lastY, x, y);
        }
        if (this.currentPiece != null && this.currentPiece.isBomb() && this.currentPiece.hasCaptured())
            can = false;
        if (this.justKinged && this.hasFinishedMoving)
            can = false;
        if (this.currentPiece != null && !this.currentPiece.hasCaptured() && this.hasFinishedMoving && can)
            can = false;
        return can;
    }

    private boolean validMove(boolean canUp, boolean canDown, int xi, int yi, int xf, int yf) {
        boolean valid = false;
        if (xi >= 0 && xi < 8 && yi >= 0 && yi < 8 && xf >= 0 && xf < 8 && yf >= 0 && yf < 8) {
            if (Math.abs(yf - yi) == Math.abs(xf - xi)) {
                boolean up = yf == yi + 1 || yf == yi + 2;
                boolean down = yf == yi - 1 || yf == yi - 2;
                if (up && canUp)
                    valid = true;
                if (down && canDown)
                    valid = true;
            }
        }
        return valid;
    }

    private boolean validCapture(boolean canUp, boolean canDown, int xi, int yi, int xf, int yf) {
        boolean valid = false;
        int deltaX = xf - xi;
        deltaX = deltaX / 2;
        int deltaY = yf - yi;
        deltaY = deltaY / 2;
        if (this.pieceAt(xi + deltaX, yi + deltaY) != null && this.pieceAt(xi + deltaX, yi + deltaY).isFire() == !this.isFireTurn)
            valid = true;
        return validMove(canUp, canDown, xi, yi, xf, yf) && valid;
    }

    public void select(int x, int y) {
        if (this.hasSelected) {
            if (this.pieceAt(x, y) == null) {
                if (this.currentPiece.isFire() && y == 7 && !this.currentPiece.isKing()) {
                    this.justKinged = true;
                } else if (!this.currentPiece.isFire() && y == 0 && !this.currentPiece.isKing()) {
                    this.justKinged = true;
                }
                this.currentPiece.move(x, y);
                int xf;
                boolean nextCaptureValid;
                if (x > this.lastX)
                    xf = x + 1;
                else
                    xf = x - 1;
                if (this.currentPiece.isKing())
                    nextCaptureValid = this.validCapture(this.currentPiece.isFire() || this.currentPiece.isKing(), !this.currentPiece.isFire() || this.currentPiece.isKing(), x, y, xf, y + 1) || this.validCapture(this.currentPiece.isFire() || this.currentPiece.isKing(), !this.currentPiece.isFire() || this.currentPiece.isKing(), x, y, xf, y - 1);
                else if (y > this.lastY)
                    nextCaptureValid = this.validCapture(this.currentPiece.isFire() || this.currentPiece.isKing(), !this.currentPiece.isFire() || this.currentPiece.isKing(), x, y, xf, y + 1);
                else
                    nextCaptureValid = this.validCapture(this.currentPiece.isFire() || this.currentPiece.isKing(), !this.currentPiece.isFire() || this.currentPiece.isKing(), x, y, xf, y - 1);
                this.hasFinishedMoving = !(this.currentPiece.hasCaptured() && nextCaptureValid);
            } else {
                this.currentPiece = pieceAt(x, y);
            }
        } else {
            this.currentPiece = pieceAt(x, y);
            this.hasSelected = true;
        }
        this.lastX = x;
        this.lastY = y;
    }

    public void place(Piece p, int x, int y) {
        if (p != null && x >= 0 && x < 8 && y >= 0 && y < 8) {
            this.pieces[x][y] = p;
            if (p.isFire())
                this.fireCount++;
            else
                this.waterCount++;
        }
    }

    public Piece remove(int x, int y) {
        if (x >= 0 && x < 8 && y >= 0 && y < 8){
            Piece result = this.pieces[x][y];
            if (result == null) {
                System.out.println("No piece at the input coordinates to remove");
                return null;
            }
            if (result.isFire())
                this.fireCount--;
            else
                this.waterCount--;
            this.pieces[x][y] = null;
            return result;
        } else {
            System.out.println("Input coordinates out of bounds");
            return null;
        }
    }

    public boolean canEndTurn() {
        return this.hasFinishedMoving;
    }

    public void endTurn() {
        this.isFireTurn = !this.isFireTurn;
        this.hasSelected = false;
        this.hasFinishedMoving = false;
        this.justKinged = false;
        this.currentPiece.doneCapturing();
        this.currentPiece = null;
    }

    public String winner() {
        if (this.waterCount == this.fireCount && this.fireCount == 0)
            return "No one";
        else if (this.waterCount == 0)
            return "Fire";
        else if (this.fireCount == 0)
            return "Water";
        else
            return null;
    }

}