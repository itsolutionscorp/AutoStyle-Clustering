/* Author: Martin Thompson 
 *    DONE    */

public class Board{
    private int N = 8; // number of checkers in each direction
    private int currentPlayer = 0; // fire player goes first
    private Piece[][] pieces = new Piece[N][N];
    private int selectedX, selectedY;
    private int destX, destY;
    private boolean hasSelectedAPiece = false, hasSelectedADestination = false;
    private boolean hasMadeAMove = false, hasMadeACapture = false;


    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board board = new Board(false); // generates a non-empty board
        while (true){
            board.drawBoard();
            board.drawPieces();
            StdDrawPlus.show(100);
            board.checkMouse();
            board.checkSpace();
        }
    }

    /* The constructor for Board. If shouldBeEmpty is true, 
     * initializes an empty Board. Otherwise, initializes a Board 
     * with the default configuration. Note that an empty Board 
     * could possibly be useful for testing purposes. */
    public Board(boolean shouldBeEmpty){
        if (shouldBeEmpty){ // generate an empty pieces array
            generateEmptyBoardPosition();
        } else {
            generateNewGamePosition(); // generate a start position pieces array
        }
    }

    /* Gets the piece at position (x, y) on the board, or 
     * null if there is no piece. If (x, y) are out of bounds, 
     * returns null.*/
    public Piece pieceAt(int x, int y){
        if (x >= N || y >= N){
            return null;
        } else if (x < 0 || y < 0){
            return null;
        } else {
            return pieces[x][y];
        }
    }


    /* Returns true if there is a piece the piece at (x, y) and it can be selected.
     *
     * A piece may be selected if it is the corresponding player’s turn and one 
     * of the following is true:
     *
     *  The player has not selected a piece yet.
     *  The player has selected a piece, but did not move it.
     *
     *  An empty square may be selected if one of the following is true:
     * 
     *      During this turn, the player has selected a Piece which hasn’t 
     *          moved yet and is selecting an empty spot which is a valid move 
     *          for the previously selected Piece.
     *      During this turn, the player has selected a Piece, captured, and 
     *          has selected another valid capture destination. (You may select as 
     *          many captures in a row as long as they are all valid and from 
     *          the same piece.) */
    public boolean canSelect(int x, int y){
        if (pieceAt(x,y) != null && pieceAt(x,y).side() == currentPlayer){
            if (!hasSelectedAPiece || !hasMadeAMove) {
                return true;
            } else return false;


        } else if (pieceAt(x,y) == null){
            if (hasSelectedAPiece && !hasMadeAMove &&
                validMove(selectedX, selectedY, x, y)) {
                return true;
            } else if (hasSelectedAPiece && 
                       pieceAt(selectedX, selectedY) != null &&
                       pieceAt(selectedX, selectedY).hasCaptured() &&
                       validMove(selectedX, selectedY, x, y) &&
                       isCaptureMove(selectedX, selectedY, x, y)) {
                return true;
            } else return false;


        } else {
            return false;
        }
    }

    /* Returns true if the piece at (xi, yi) can either move to (xf, yf) or 
     * capture to (xf, yf) in a valid fashion compatible with the rules. */
    private boolean validMove(int xi, int yi, int xf, int yf){
        if (isMoveDirectionValid(xi, yi, xf, yf)){
            if (isCaptureMove(xi, yi, xf, yf) || isBasicMove(xi, yi, xf, yf)){
                return true;
            } else return false;
        } else {
            return false;
        }
    }

    /* Selects the piece at (x, y) if possible. Optionally, it is recommended 
     * to color the background of the selected square white on the GUI via the 
     * pen color function. For any piece to perform a capture, that piece must 
     * have been selected first. */
    public void select(int x, int y){
        if (pieceAt(x, y) == null){
            destX = x;
            destY = y;
            hasSelectedADestination = true;
            executeMove(); // this is where the pieces actually move
        } else {
            selectedX = x;
            selectedY = y;
            hasSelectedAPiece = true;
            hasSelectedADestination = false;
        }
    }


    /* Places p at (x, y). If (x, y) is out of bounds or if p is null, does 
     * nothing. If p already exists in the current Board, first removes it 
     * from its initial position. If another piece already exists at (x, y), 
     * p will replace that piece. (This method is potentially useful for 
     * creating specific test circumstances.) */
    public void place(Piece p, int x, int y){
        if (x >= N || y >= N || p == null){
            return;
        } else {       
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (p == pieces[i][j]){
                        remove(i, j);
                    }
                }
            }
            pieces[x][y] = p;
        }
    }


    /* Executes a remove. Returns the piece that was removed. If the input 
     * (x, y) is out of bounds, returns null and prints an appropriate message. 
     * If there is no piece at (x, y), returns null and prints an appropriate 
     * message. */
    public Piece remove(int x, int y){
        if (x >= N || y >= N || x < 0 || y < 0){
            System.out.println("Attempted to remove a null Piece from the board");
            return null;
        } else {
            Piece p = pieces[x][y];
            pieces[x][y] = null;
            return p;
        }
    }

    /* Returns whether or not the the current player can end their turn. To 
     * be able to end a turn, a piece must have moved or performed a capture. */
    public boolean canEndTurn(){
        if (hasMadeAMove || hasMadeACapture){
            return true;
        } else {
            return false;
        }
    }

    /* Called at the end of each turn. Handles switching of players and anything 
     * else that should happen at the end of a turn. */
    public void endTurn(){
        currentPlayer = (currentPlayer + 1) % 2; // toggle current player
        hasMadeAMove = false;
        hasSelectedAPiece = false;
        hasSelectedADestination = false;
        hasMadeACapture = false;
        if (pieceAt(destX, destY) == null){
            // do nothing
        } else {
            pieceAt(destX, destY).doneCapturing();
        }
    }


    private void checkMouse(){
        if (StdDrawPlus.mousePressed()) {
            double x = StdDrawPlus.mouseX();
            double y = StdDrawPlus.mouseY();
            int castX = (int) x;
            int castY = (int) y;
            if (canSelect(castX, castY)){
                select(castX, castY);
            }           
        }
    }


    private void checkSpace(){
        if (StdDrawPlus.isSpacePressed()){
            if (canEndTurn()){
                endTurn();
            }
        }
    }

    /* Returns the winner of the game. "Fire", "Water", "No one" (tie / no pieces 
     * on the board), or null if the game is not yet over. Assume there is no 
     * stalemate situation in which the current player has pieces but cannot 
     * legally move any of them (In this event, just leave it at null). Determine 
     * the winner solely by the number of pieces belonging to each team. */
    public String winner(){
        if (checkIsGameOver()){
           int[] pieceCount = countPieces();
            if (pieceCount[1] > pieceCount[0]){
                return "Water";
            } else if (pieceCount[0] > pieceCount[1]){
                return "Fire";
            } else {
                return "No one";
            }
        } else {
            return null;
        }
    }


    private void executeMove(){
        hasMadeAMove = true;
        hasMadeACapture = false;
        if (isCaptureMove(selectedX, selectedY, destX, destY)){
            hasMadeACapture = true;
        }
        pieceAt(selectedX, selectedY).move(destX, destY);
        selectedX = destX;
        selectedY = destY;
        hasSelectedADestination = false;
        if (pieceAt(destX, destY) != null && 
            pieceAt(destX, destY).isBomb() && 
            pieceAt(destX, destY).hasCaptured()) {
            for (int i = destX -1; i <= destX + 1; i++){
                for (int j = destY - 1; j <= destY + 1; j++){
                    if (pieceAt(i, j) != null && !pieceAt(i, j).isShield()){
                        remove(i, j);
                    }
                }
            }
        }
    }


    private boolean checkIsGameOver(){
        int[] pieceCount = countPieces();
        if (pieceCount[1] == 0 || pieceCount[0] == 0){
            return true;
        } else return false;
    }

    private int[] countPieces(){
        int fireCount = 0;
        int waterCount = 0;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (pieceAt(i, j) == null){
                    continue;
                } else if (pieceAt(i, j).isFire()){
                    fireCount++;
                } else if (!pieceAt(i, j).isFire()){
                    waterCount++;
                }
            }
        }
        return new int[]{fireCount, waterCount};
    }

    private boolean isMoveDirectionValid(int xi, int yi, int xf, int yf){
        if (pieceAt(xi, yi).isKing()){
            return true;
        } else if (pieceAt(xi, yi).isFire()){
            return (yf - yi) > 0;
        } else {
            return (yi - yf) > 0;
        }
    }

    private boolean isCaptureMove(int xi, int yi, int xf, int yf){
        if (xi - xf == 2 || xf - xi == 2){
            if (yi - yf == 2 || yf - yi == 2){
                return doesJumpOverEnemyPiece(xi, yi, xf, yf);
            } else return false;
        } else return false;
    }

    private boolean doesJumpOverEnemyPiece(int xi, int yi, int xf, int yf){
        int minX, minY, middleX, middleY;
        Piece middlePiece;
        minX = Math.min(xi, xf);
        minY = Math.min(yi, yf);
        middleX = minX + 1;
        middleY = minY + 1;
        middlePiece = pieceAt(middleX, middleY);
        if (middlePiece == null){
            return false;
        } else {
            return middlePiece.side() != currentPlayer;
        }
    }

    private boolean isBasicMove(int xi, int yi, int xf, int yf){
        if (xi - xf == 1 || xf - xi == 1){
            if (yi - yf == 1 || yf - yi == 1){
                return true;
            } else return false;
        } else return false;
    }

    private void drawHighLightedSpace(int x, int y){
        StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
        drawPieces();
    }

    private void drawBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.BLUE);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
        if      (hasSelectedAPiece) drawHighLightedSpace(selectedX, selectedY);
        if (hasSelectedADestination) drawHighLightedSpace(destX, destY);
    }
    private void drawPieces(){
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (pieces[i][j] != null){
                    drawSinglePiece(pieces[i][j], i, j);
                }
            }
        }
    }
    private void drawSinglePiece(Piece p, int x, int y){
        String img = "img/";
        if (p.isBomb()){
            img = img + "bomb-";
        } else if (p.isShield()){
            img = img + "shield-";
        } else {
            img = img + "pawn-";
        }

        if (p.isFire()){
            img = img + "fire";
        } else {
            img = img + "water";
        }

        if (p.isKing()){
            img = img + "-crowned";
        }

        img = img + ".png";
        StdDrawPlus.picture(x + 0.5, y + 0.5, img, 1, 1);
    }
    /* should fill up the pieces array with the right pieces in the right spots */
    private void generateNewGamePosition(){
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
    private void generateEmptyBoardPosition(){
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                pieces[i][j] = null;
            }
        }
    }
}