public class Board {
    private static int N = 8;
    private Piece[][] position = new Piece[N][N]; //keeps track of all our positions
    private String[][] images = new String[N][N]; //tells us our images
    private boolean defaultBoard = true;
    private boolean shouldBeEmpty;
    private int clickX;
    private int clickY;
    private Piece selectedPiece;
    private int selectedPieceX;
    private int selectedPieceY;
    private Player currentPlayer;
    private Player otherPlayer;

	public static void main(String[] args) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);
        while(true) {
            while (b.winner()==null){
                b.drawBoard(N);
                if (StdDrawPlus.mousePressed()) { 
                    b.clickX = (int) StdDrawPlus.mouseX();
                    b.clickY = (int) StdDrawPlus.mouseY();
                    if (b.canSelect(b.clickX,b.clickY)){
                        b.select(b.clickX, b.clickY);
                    }   
                } 
                if (StdDrawPlus.isSpacePressed()) {
                    if (b.canEndTurn()) {
                        b.endTurn();
                    }
                }
                StdDrawPlus.show(20);
            }
        }
    }

    public Board(boolean shouldBeEmpty1){
        shouldBeEmpty = shouldBeEmpty1;
        Player player1 = new Player(0); //fire
        Player player2 = new Player(1); //water
        currentPlayer = player1;
        otherPlayer = player2;
    }
	private void drawBoard(int N) {
        if (shouldBeEmpty==false){
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    if (selectedPiece!=null && selectedPieceX==col && selectedPieceY==row) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    else if ((row + col) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                    else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                    StdDrawPlus.filledSquare(col + .5, row + .5, .5);
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    if (defaultBoard) {
                        drawDefaultBoard(col, row);
                    } else if (pieceAt(col, row)!=null){
                        drawPieces(col, row, pieceAt(col, row), images[col][row]); //we need to change the image's location
                    }
                }
            }
        }
    }
        
    private void drawDefaultBoard(int col, int row){
        if (col%2==0) {
            if (row==0){
                images[col][row] = "img/pawn-fire.png";
                drawPieces(col, row, new Piece(true, this, col, row, "pawn"), images[col][row]);
            } else if (row == 2) {
                images[col][row] = "img/bomb-fire.png";
                drawPieces(col, row, new Piece(true, this, col, row, "bomb"), images[col][row]);
            } else if (row==6){
                images[col][row] = "img/shield-water.png";
                drawPieces(col, row, new Piece(false, this, col, row, "shield"), images[col][row]);
            }
        } else if (col%2!= 0) {
            if (row==1){
                images[col][row] = "img/shield-fire.png";
                drawPieces(col, row, new Piece(true, this, col, row, "shield"), images[col][row]);
            } else if(row == 5){
                images[col][row] = "img/bomb-water.png";
                drawPieces(col, row, new Piece(false, this, col, row, "bomb"), images[col][row]);
            } else if (row==7){
                images[col][row] = "img/pawn-water.png";
                drawPieces(col, row, new Piece(false, this, col, row, "pawn"), images[col][row]);
                if (col==7) {
                    defaultBoard = false;
                }
            }
        }
    }

    private void drawPieces(int col, int row, Piece piece, String type){  
        StdDrawPlus.picture(col + .5, row + .5, type, 1, 1);
        place(piece, col, row);
    }
    private String createKingImage(Piece piece){
        if (piece.isBomb()) {
            if (piece.isFire()) return "img/bomb-fire-crowned.png";
            else return "img/bomb-water-crowned.png";
        } else if (piece.isShield()) {
            if (piece.isFire()) return "img/shield-fire-crowned.png";
            else return "img/shield-water-crowned.png";
        } else {
            if (piece.isFire()) return "img/pawn-fire-crowned.png";
            else return "img/pawn-water-crowned.png";
        }
    }

    private boolean validMove(int xi, int yi, int xf, int yf){ //take care of capture and side.
        boolean isKing = selectedPiece.isKing();
        boolean isFire = selectedPiece.isFire();
        if ((xf==xi+1 || xf==xi-1)) { //moving one diagonal 
            if((isKing&&(yf==yi+1||yf==yi-1)) || (isFire && yf==yi+1) || (isFire==false && yf==yi-1)){
                return true;
            }     
        } else if ((xf==xi+2 || xf==xi-2)) { //for capturing
            if (isKing && (yf==yi+2||yf==yi-2) && pieceAt((xf+xi)/2, (yf + yi)/2)!=null && //makes sure we are capturing
                            ((pieceAt((xf+xi)/2, (yf + yi)/2).isFire() == isFire==false))) { //makes sure we are capturing the opposite piece{
                return true; //this tests if the king is capturing
            } else if(isFire && yf==yi+2 && pieceAt((xf+xi)/2, yi + 1)!=null &&
                                            pieceAt((xf+xi)/2, yi + 1).isFire()==false){ //checks for a fire capture
                return true; 
            } else if(isFire==false && yf==yi-2 && pieceAt((xf+xi)/2, yi-1)!=null && //checks for a water capture
                                                    pieceAt((xf+xi)/2, yi-1).isFire()){
                return true;
            }
        }
        return false;
    }

	public Piece pieceAt(int x, int y){ //should work
        if (x<N && y<N && x>=0 && y>=0){
            return position[x][y];
        }
        return null;
    }
	public boolean canSelect(int x, int y){ 
        if (x<N && x >= 0 && y>=0  && y<N) {
            if (pieceAt(x,y)!=null && pieceAt(x,y).side()==currentPlayer.side && //selecting your first piece
                (currentPlayer.hasSelected==false || currentPlayer.hasMoved==false)) { 
                return true; 
            } else if ((selectedPiece!=null) && pieceAt(x,y)==null) { //we must have a selected piece
                if (currentPlayer.hasMoved==false|| //conditions to select an empty square
                (selectedPiece.hasCaptured() && Math.abs(selectedPieceX-x)==2 && Math.abs(selectedPieceY-y)==2)) {
                    if (validMove(selectedPieceX,selectedPieceY,x,y)) { 
                        return true;
                    }
                }
            }
        }
        return false;
    }

	public void select(int x, int y){
        if (x<N && y<N && x>=0 && y>=0){
            Piece selectedSquare = pieceAt(x,y);
            if (selectedSquare!=null) { //selecting a piece
                currentPlayer.hasSelected = true;
                selectedPiece = selectedSquare;
            } else if (selectedPiece!=null){ //selecting an empty square and
                //and makes sure you have already selected a piece
                selectedPiece.move(x,y);
                currentPlayer.hasMoved = true;
            } else return; //selecting an empty square without a selected piece
            selectedPieceX = x;
            selectedPieceY = y;
        }
    }
	public void place(Piece piece, int x, int y){  //works
        if (piece!=null && x<N && y<N){
            for(int row=0; row < N; row++){  //removes initial position
                for(int col = 0; col < N; col++){
                    if (position[col][row] == piece){
                        remove(col, row);
                        String prevImage = images[col][row];
                        images[col][row] = null;
                        if (piece.isKing()) {
                            images[x][y] = createKingImage(piece);
                        } else images[x][y] = prevImage;
                    }
                }
            }
            position[x][y] = null;
            position[x][y] = piece;
        }
        
    }

	public Piece remove(int x, int y){ //works
        if (x>N && y>N){
            System.out.println("Index out of bounds");
            return null;
        }
        Piece removed = position[x][y];
        if (removed == null){
            System.out.println("No piece at (x,y)");
        }
        position[x][y] = null;
        return removed;
    }
	public boolean canEndTurn(){
        if (currentPlayer.hasMoved || (selectedPiece!=null && selectedPiece.hasCaptured())) {
            return true;
        }
        return false;
    }
	public void endTurn(){ //switch players
        currentPlayer.hasMoved = false;
        currentPlayer.hasSelected = false;
        Player temp = currentPlayer;
        currentPlayer = otherPlayer;
        otherPlayer = temp;
        if (selectedPiece!=null) {
            selectedPiece.doneCapturing();
            selectedPiece=null;
        } 
    }
	public String winner(){
        return null;
    }

    private static class Player{
        private int side;
        private boolean hasMoved = false;
        private boolean hasSelected = false;
        private Player(int side1){
            side = side1;
        }
    }
}