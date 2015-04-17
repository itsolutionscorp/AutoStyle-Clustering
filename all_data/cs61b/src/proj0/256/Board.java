public class Board {
    private Piece[][] board;
    private Piece selectedPiece;
    private boolean madeMove;
    private int selectedX;
    private int selectedY;
    private boolean currentPlayerFire;

    public Board(boolean shouldBeEmpty){
        board = new Piece[8][8];
        selectedPiece = null;
        madeMove = false;
        currentPlayerFire = true;

        if (!shouldBeEmpty){
            for (int i = 0; i < 4; i++){
                //fire and water pawns
                board[i*2][0] = new Piece(true, this, i*2, 0, "pawn");
                board[1 + i*2][7] = new Piece(false, this,  1 + i*2, 7, "pawn");

                //fire and water shields
                board[1 + i*2][1] = new Piece(true, this,  1 + i*2,1, "shield");
                board[i*2][6] = new Piece(false, this,  i*2,6, "shield");

                //fire and water bombs
                board[i*2][2] = new Piece(true, this,  i*2, 3, "bomb");
                board[1 + i*2][5] = new Piece(false, this,  1 + i*2,5, "bomb");
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
                if (selectedPiece != null && i == selectedX && j == selectedY)
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);

                if (board[i][j] != null) {
                    Piece p = board[i][j];
                    String img = "img/";
                    if (p.isBomb()){
                        img += "bomb";
                    }
                    else if (p.isShield()){
                        img += "shield";
                    }
                    else {
                        img += "pawn";
                    }

                    if (p.isFire()){
                        img += "-fire";
                    }
                    else {
                        img += "-water";
                    }

                    if (p.isKing()){
                        img += "-crowned";
                    }

                    img += ".png";

                    StdDrawPlus.picture(i + .5, j + .5, img, 1, 1);
                }
            }
        }
    }

    public void place(Piece p, int x, int y){
        if (x > 7 | x < 0 | y > 7 | y < 0){
            return;
        }
        board[x][y] = p;
    }

    public Piece pieceAt(int x, int y){
        if (x > 7 | x < 0 | y > 7 | y < 0){
            return null;
        }
        return board[x][y];
    }

    public Piece remove(int x, int y){
        if (x > 7 | x < 0 | y > 7 | y < 0){
            return null;
        }
        Piece p = board[x][y];
        board[x][y] = null;
        return p;

    }

    private boolean validMove(int xi, int yi, int xf, int yf){
        if (xf > 7 | xf < 0 | yf > 7 | yf < 0){
            return false;
        }
        //handle king

        //can't move to a place with a piece in it
        if (board[xf][yf] != null){
            return false;
        }

        //can't jump too far
        if (Math.abs(yi - yf) > 2 || Math.abs(xi - xf) > 2){
            return false;
        }

        //not safe for water
        //non king can't move backwards
        if (!pieceAt(xi,yi).isKing()){
            if (pieceAt(xi,yi).isFire() && yf < yi)
                return false;
            if (!pieceAt(xi,yi).isFire() && yf > yi)
                return false;
        }

        //non capture is safe
        if (Math.abs(xi - xf) == 1 && Math.abs(yi - yf) == 1)
            return true;

        //can't capture non-diagonal
        if (Math.abs(xi - xf) != Math.abs(yi - yf))
            return false;

        //can't capture own piece
        if (pieceAt((xi + xf)/2, (yi + yf)/2).isFire() == pieceAt(xi,yi).isFire())
            return false;

        return true;
    }

    public boolean canSelect(int x, int y){
        if (pieceAt(x,y) != null){
            if (currentPlayerFire != pieceAt(x,y).isFire()) 
                return false;
            return selectedPiece == null || !madeMove;
        }
        if (selectedPiece != null && !madeMove && validMove(selectedX, selectedY, x, y)){
            return true;
        }
        if (selectedPiece != null && madeMove && selectedPiece.hasCaptured() && validMove(selectedX, selectedY, x, y))
            return true;
        return false;
    }

    public void select(int x, int y){
        Piece p = pieceAt(x,y);
        if (!canSelect(x,y))
            return;

        if (p != null){
            selectedPiece = p;
            selectedX = x;
            selectedY = y;
            return;
        }

        if (selectedPiece != null){
            selectedPiece.move(x,y);
            board[selectedX][selectedY] = null;
            selectedX = x;
            selectedY = y;
            madeMove = true;
        }

    }

    public boolean canEndTurn(){
        return madeMove;
    }

    public void endTurn(){
        selectedPiece = null;
        madeMove = false;
        currentPlayerFire = !currentPlayerFire;
    }

    public String winner(){
        int numFire = 0;
        int numWater = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++){
                Piece p = pieceAt(i,j);
                if (p == null)
                    continue;
                if (p.isFire()){
                    numFire++;
                }
                else {
                    numWater++;
                }
            }
        }
        if (numFire > 0 && numWater == 0)
            return "Fire";
        if (numWater > 0 && numFire == 0)
            return "Water";
        if (numFire == 0 && numWater == 0)
            return "No one";
        return null;
    }

    public static void main(String[] args){
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board board = new Board(false);

        /** Monitors for mouse presses. Wherever the mouse is pressed,
          a new piece appears. */
        while(true) {
            board.drawBoard();
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                board.select(x,y);
            }            
            if (StdDrawPlus.isSpacePressed()){
                if (board.canEndTurn()){
                    board.endTurn();
                }
            }
            StdDrawPlus.show(100);
        }
    }
}
