public class Piece {

    private int topLeftX;
    private int topLeftY;
    private int bottomRightX;
    private int bottomRightY;

    public Piece(int x1, int y1, int x2, int y2) {
        topLeftX = x1;
        topLeftY = y1;
        bottomRightX = x2;
        bottomRightY = y2;
    }

    public Piece(Piece prevPiece) {
        topLeftX = prevPiece.topLeftX;
        topLeftY = prevPiece.topLeftY;
        bottomRightX = prevPiece.bottomRightX;
        bottomRightY = prevPiece.bottomRightY;
    }

    public int getTopLeftX() {
        return topLeftX;
    }

    public int getTopLeftY() {
        return topLeftY;
    }

    public int getBottomRightX() {
        return bottomRightX;
    }

    public int getBottomRightY() {
        return bottomRightY;
    }

    public void setTopLeftY(int Y) {
        topLeftY = Y;
    }

    public void setTopLeftX(int X) {
        topLeftX = X;
    }

    public void setBottomRightY(int Y) {
        bottomRightY = Y;
    }

    public void setBottomRightX(int X) {
        bottomRightX = X;
    }

    @Override
    public boolean equals(Object o) {

        Piece p = (Piece) o;

        if (this.bottomRightX != p.bottomRightX) {
            return false;

        } else if (this.bottomRightY != p.bottomRightY) {
            return false;

        } else if (this.topLeftX != p.topLeftX) {
            return false;

        } else if (this.topLeftY != p.topLeftY) {
            return false;

        }

        return true;

    }

    @Override
    public int hashCode() {
        return topLeftX + 20 * topLeftY + 30 * bottomRightX + 40 * bottomRightY;
    }

    @Override
    public String toString() {
        return topLeftX + " " + topLeftY + " " + bottomRightX + " " + bottomRightY;
    }

}
