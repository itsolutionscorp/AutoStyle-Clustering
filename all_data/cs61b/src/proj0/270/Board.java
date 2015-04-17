public class Board{
	
    private Piece[][] board;
    private boolean fireTurn;
    private Piece pieceSelected;
    private int currentX;
    private int currentY;
    private boolean hasMovedPiece;

	public static void main(String[] args) {
      	int numSquares = 8;
        StdDrawPlus.setXscale(0, numSquares);
        StdDrawPlus.setYscale(0, numSquares);
        Board b = new Board(false);
        while(true){
            b.drawBoard(numSquares);
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if(b.canSelect(x, y))
                    b.select(x, y);
            }
            if(StdDrawPlus.isSpacePressed() && b.canEndTurn()){     
                b.endTurn();
                String potentialWinner = b.winner();
                if(potentialWinner != null)
                    System.out.println(potentialWinner + " wins!");
            }
                

            StdDrawPlus.show(100);
        }
    }

    public Board(boolean shouldBeEmpty){
        board = new Piece[8][8];
        fireTurn = true;
        pieceSelected = null;
        hasMovedPiece = false;
        currentX = -1;
        currentY = -1;
        drawBoard(8);

        if(!shouldBeEmpty){
            //Initializing Fire team.
            for(int i = 0; i < 7; i += 2)
                board[i][0] = new Piece(true, this, i, 0, "normal");     
            for(int i = 1; i < 8; i += 2)
                board[i][1] = new Piece(true, this, i, 1, "shield");
            for(int i = 0; i < 7; i += 2)
                board[i][2] = new Piece(true, this, i, 2, "bomb");

            //Initializing Water team.
            for(int i = 1; i < 8; i += 2)
                board[i][5] = new Piece(false, this, i, 5, "bomb");
            for(int i = 0; i < 7; i += 2)
                board[i][6] = new Piece(false, this, i, 6, "shield");
            for(int i = 1; i < 8; i += 2)
                board[i][7] = new Piece(false, this, i, 7, "normal");
        }
        

    }

    private void setSelect(Piece p, int x, int y){
        pieceSelected = p;
        currentX = x;
        currentY = y;
    }


	private void drawBoard(int numSquares) {
        //Drawing the board.
        for (int i = 0; i < numSquares; i++) {
            for (int j = 0; j < numSquares; j++) {
                if ((i + j) % 2 == 0) 
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                if(currentX == i && currentY == j)
                    StdDrawPlus.setPenColor(StdDraw.WHITE);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
        for(int i = 0; i < numSquares; i++){
            for(int j = 0; j < numSquares; j++){
                Piece p = pieceAt(i, j);
                if(p != null){
                    String link = "img/";
                    //Checking piece type.
                    if(p.isShield())
                        link = link + "shield-";
                    else if(p.isBomb())
                        link = link + "bomb-";
                    else
                        link = link + "pawn-";
                    //Checking piece side.
                    if(p.isFire())
                        link = link + "fire";
                    else
                        link = link + "water";
                    //Checking piece is crowned.
                    if(p.isKing())
                        link = link + "-crowned";
                    link = link + ".png";
                    StdDrawPlus.picture(i + .5, j + .5, link, 1, 1);
                }
            }
        }
    }

	public boolean canSelect(int x, int y){
        Piece piece = pieceAt(x, y);
        //Selecting a piece case.
        if(piece != null && ((piece.isFire() && fireTurn) || (!piece.isFire() && !fireTurn))){
            if(pieceSelected == null || (pieceSelected != null && !hasMovedPiece))
                return true;
        }
        //Selecting an empty square case.
        //***Pieces will be able to continue moving unless hasCaptured is changed at some point.
        else{
            if(pieceSelected != null && !hasMovedPiece && validMove(pieceSelected, currentX, currentY, x, y))
                return true;
            if(pieceSelected != null && pieceSelected.hasCaptured() && isCaptureDestination(currentX, currentY, x, y) && validMove(pieceSelected, currentX, currentY, x, y))
                return true;
        }
        return false;
	}

    private boolean isCaptureDestination(int xi, int yi, int xf, int yf){
        int dx = Math.abs(xf - xi);
        int dy = Math.abs(yf - yi);
        if(dx == 2 && dy == 2)
            return true;
        else
            return false;
    }

    private boolean validMove(Piece piece, int xi, int yi, int xf, int yf){
        //Create a list of possible moves, depending on the side and if the piece is crowned.
        int dx = xf - xi;
        int dy = yf - yi;

        //Checking captures moves.
        //A piece can only capture another piece if the piece to be captured is on the opposite side.
        if(dx == -2 && dy == -2)
            return (!piece.isFire() || piece.isKing()) && (pieceAt(xi-1, yi-1).isFire() != piece.isFire());
        if(dx == 2 && dy == -2)
            return (!piece.isFire() || piece.isKing()) && (pieceAt(xi+1, yi-1).isFire() != piece.isFire());
        if(dx == -2 && dy == 2)
            return (piece.isFire() || piece.isKing()) && (pieceAt(xi-1, yi+1).isFire() != piece.isFire());
        if(dx == 2 && dy == 2)
            return (piece.isFire() || piece.isKing()) && (pieceAt(xi+1, yi+1).isFire() != piece.isFire());

        //Checking regular moves.
        //A piece can only move regularly if it has not moved already.
        if(!hasMovedPiece){
            if(dx == -1 && dy == -1)
            return (!piece.isFire() || piece.isKing()) && (pieceAt(xi-1, yi-1) == null);
            if(dx == 1 && dy == -1)
                return (!piece.isFire() || piece.isKing()) && (pieceAt(xi+1, yi-1) == null);
            if(dx == -1 && dy == 1)
                return (piece.isFire() || piece.isKing()) && (pieceAt(xi-1, yi+1) == null);
            if(dx == 1 && dy == 1)
                return (piece.isFire() || piece.isKing()) && (pieceAt(xi+1, yi+1) == null);
        }
        
        return false;

    }

    public void select(int x, int y){
        if(canSelect(x, y)){
            Piece p = pieceAt(x, y);
            if(p != null){
                pieceSelected = p;
                currentX = x;
                currentY = y;
            }   
            else{
                pieceSelected.move(x, y);
                hasMovedPiece = true;
                currentX = x;
                currentY = y;
            }
        }   
    }

    public Piece pieceAt(int x, int y){
        if(x > -1 && x < 8 && y < 8 && y > -1){
            return board[x][y];
        }
        else
            return null;
    }

    public void place(Piece p, int x, int y){
        if(x > -1 && x < 8 && y < 8 && y > -1 && p != null){
            if(pieceAt(x, y) != null)
                remove(x, y);
            board[x][y] = p;
        }   
    }

    public Piece remove(int x, int y){
        //Ensuring the coordinates are in bounds.
        if(x > -1 && x < 8 && y < 8 && y > -1){
            Piece piece = pieceAt(x, y);
            board[x][y] = null;
            if(piece != null){
                System.out.println("Piece at " + x + " " + y + " removed.");
                return piece;
            }   
            else{
                System.out.println("No piece at " + x + " " + y);
                return null;
            }        
        }
        else{
            System.out.println("Out of bounds.");
            return null;
        }
    }

    public boolean canEndTurn(){
        return hasMovedPiece;
    }

    public void endTurn(){
        pieceSelected.doneCapturing();
        pieceSelected = null;
        hasMovedPiece = false;
        currentX = -1;
        currentY = -1;
        fireTurn = !fireTurn;
    }

    public String winner(){
        boolean existsFirePiece = false;
        boolean existsWaterPiece = false;
        for(int x = 0; x < 8; x++){
            for(int y = 0; y < 8; y++){
                if(pieceAt(x, y) != null && pieceAt(x, y).isFire())
                    existsFirePiece = true;
                if(pieceAt(x, y) != null && !pieceAt(x, y).isFire())
                    existsWaterPiece = true;
            }
        }
        if(existsFirePiece && existsWaterPiece)
            return null;
        else if(existsFirePiece && !existsWaterPiece)
            return "Fire";
        else if(!existsFirePiece && existsWaterPiece)
            return "Water";
        else
            return "No one";
    }

}