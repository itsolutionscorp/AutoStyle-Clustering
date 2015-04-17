public class Board {

	private Piece[][] boardPieces; 
    private boolean[][] selected;
    //private int[][] captured;
    private boolean moved;



	private int N = 8;
	private boolean emptyBoard;


    private double shift = .5;
    private double squareRadius = .5;
 
    private double imageWidth = squareRadius * 1.8;
    private double imageHeight = imageWidth;


    private int playerTurn = 0; // 0 is fire, 1 is water


	public Board(boolean shouldBeEmpty){
 
      
		emptyBoard = shouldBeEmpty;

        boardPieces = new Piece[N][N];
        selected = new boolean[N][N];
        //captured = new int[N][N];
        moved = false;

        if(!emptyBoard)
            addAllPieces();
		
    }

    private void addAllPieces() {
        for(int y = 0; y < N; y++) {
                for(int x =0; x < N; x++) {
                    addPiece(x, y);
            }
        }
    }

    private void addPiece(int x, int y){
        Piece pieceToAdd = null;

        if(y==0 && x%2==0)
            pieceToAdd = new Piece(true, this, x, y, "pawn");

        else if(y==1 && x%2==1)
            pieceToAdd = new Piece(true, this, x, y, "shield");

        else if(y==2 && x%2==0)
            pieceToAdd = new Piece(true, this, x, y, "bomb");

        else if(y==5 && x%2==1)
            pieceToAdd = new Piece(false, this, x, y, "bomb");

        else if(y==6 && x%2==0)
            pieceToAdd = new Piece(false, this, x, y, "shield");

        else if(y==7 && x%2==1)
            pieceToAdd = new Piece(false, this, x, y, "pawn");

        place(pieceToAdd,x, y);
    }

    private void setUpInitialPieces(int x, int y){
        Piece pieceToAdd = null;

        if(y==0 && x%2==0)
            pieceToAdd = new Piece(true, this, x, y, "pawn");

        else if(y==1 && x%2==1)
            pieceToAdd = new Piece(true, this, x, y, "shield");

        else if(y==2 && x%2==0)
            pieceToAdd = new Piece(true, this, x, y, "bomb");

        else if(y==5 && x%2==1)
            pieceToAdd = new Piece(false, this, x, y, "bomb");

        else if(y==6 && x%2==0)
            pieceToAdd = new Piece(false, this, x, y, "shield");

        else if(y==7 && x%2==1)
            pieceToAdd = new Piece(false, this, x, y, "pawn");

        place(pieceToAdd,x, y);

        if(pieceToAdd!=null)
        {
            String type = pieceType(pieceToAdd);
            String side = pieceSide(pieceToAdd);
            StdDrawPlus.picture(shift+x, shift+y, "img/" + type + "-" + side + ".png" , imageWidth, imageHeight);
       }
    }

    private boolean noPieces () {
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++) {
                if (boardPieces[i][j]!=null)
                    return false;
            }
        }
        return true;
    }

    private void drawCurrentBoard() {
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++) {
                if (boardPieces[i][j]!=null)
                {
                    Piece pieceToDraw = pieceAt(i,j);
                    String type = pieceType(pieceToDraw);
                    String side = pieceSide(pieceToDraw);
                    if(!pieceToDraw.isKing())
                        StdDrawPlus.picture(shift+i, shift+j, "img/" + type + "-" + side + ".png" , imageWidth, imageHeight);
                    else
                    {
                        System.out.println("drawing kinged version");
                        StdDrawPlus.picture(shift+i, shift+j, "img/" + type + "-" + side + "-crowned.png" , imageWidth, imageHeight);
                    }
                }
                else
                {
                    colorCheckeredSquare(i, j);
                }
            }
        }
    }

    //make private
    // private void drawBoard(int dimension) {
    // 	for(int y = 0; y < dimension; y++) {
    // 		for(int x =0; x < dimension; x++) {
    //             colorCheckeredSquare(x,y);
    //             if (!emptyBoard)
    //                 setUpInitialPieces(x,y);
    // 		}	
    // 	}
    // } 

    private void drawBoard() {
        setScale();

        for(int y = 0; y < N; y++) {
            for(int x =0; x < N; x++) {
                colorCheckeredSquare(x,y);
                if (!emptyBoard)
                {
                    setUpInitialPieces(x,y);
                    moved = false;
                }
            }   
        }
    } 

    private String pieceType(Piece p){
        if(p.isShield())
            return "shield";
        else if (p.isBomb())
            return "bomb";
        else
            return "pawn";
    }

    private String pieceSide(Piece p){
        if (p.isFire())
            return "fire";
        else
            return "water";
    }

    private void clearSelected(){
        selected = new boolean[N][N];
    }

    // private void clearCaptured(){
    //     captured = new int[N][N];
    // }

    private boolean hasSelected() {
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(selected[i][j])
                    return true;
            }
        }
        return false;
    }

    public void place(Piece p, int x, int y){
        // String type = pieceType(p);
        // String side = pieceSide(p);
        if(pieceAt(x,y)!=null)
            remove(x,y);
        //should be drawn in main
        //StdDrawPlus.picture(shift+x, shift+y, "img/" + type + "-" + side + ".png" , imageWidth, imageHeight);
        boardPieces[x][y]= p;
        //moved = true;
    }

    private void colorCheckeredSquare(int x, int y){
        if ((x + y) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
        StdDrawPlus.filledSquare(shift+x, shift+y, squareRadius);
    }


    private boolean outOfBounds(int coord){
        return (coord < 0 || coord >=N);
    }


    //if remove didnt return null,
    //then colorCheckeredSquare(x,y) if not empty board
    public Piece remove(int x, int y) {
        Piece toReturn = null;

        if (outOfBounds(x) || outOfBounds(y) || pieceAt(x,y)==null)
        {
            return toReturn;
        }
        else
        {
            toReturn = pieceAt(x,y);

            boardPieces[x][y]= null;
            // if(!emptyBoard)
            // {
            //     colorCheckeredSquare(x,y);
            // }
        }
        return toReturn;

    }

    public Piece pieceAt(int x, int y) {
        if (outOfBounds(x) || outOfBounds(y) || boardPieces[x][y]==null)
            return null;
        return boardPieces[x][y];
    }

    private int getDimension(){
        return N;
    }

    //make private
    private boolean pieceSelected() {
        boolean selectedExists = false;
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j++){
                if(selected[i][j])
                    selectedExists = true;
            }
        }
        return selectedExists;
    }

    private int getPieceSelectedX(){
        int toReturn = -1;
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j++){
                if(selected[i][j])
                    toReturn = i;
            }
        }
        return toReturn; 
    }

    private int getPieceSelectedY(){
        int toReturn = -1;
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j++){
                if(selected[i][j])
                    toReturn = j;
            }
        }
        return toReturn; 
    }

    public boolean canSelect(int x, int y){

        //use validMove somewhere in here
        //if a piece has already been selected

        Piece pieceAtXY = pieceAt(x,y);

        //if a piece has been selected
        if (pieceAtXY != null) {
            if(pieceAtXY.side()==playerTurn) {
                return (!pieceSelected() || (pieceSelected() && !moved));
            } 
        }

        //if an empty square has been selected
        else {
                 //if(pieceSelected())
                 //    System.out.println("Piece is selected."); //debug
                 //if(!moved)
                 //    System.out.println("Piece hasn't yet moved.");
                 //if (validMove(getPieceSelectedX(), getPieceSelectedY(), x, y))
                 //   System.out.println("Valid move location.\n");

                //System.out.println("attempting to debug: " + pieceAt(getPieceSelectedX(), getPieceSelectedY()))
                // if(pieceSelected() && moved )
                //     System.out.println("selected: " + getPieceSelectedX() + " " + getPieceSelectedY());
                // if( pieceSelected() && moved && pieceAt(getPieceSelectedX(), getPieceSelectedY()).hasCaptured() && validMove(getPieceSelectedX(), getPieceSelectedY(), x, y))
                //     System.out.println("multicapture permitted");
            
            return ( ((pieceSelected() && !moved) && (validMove(getPieceSelectedX(), getPieceSelectedY(), x, y))));
                  // || ( pieceSelected() && moved && pieceAt(getPieceSelectedX(), getPieceSelectedY()).hasCaptured() && validMove(getPieceSelectedX(), getPieceSelectedY(), x, y)) );
            }
        return false;
    }

    private boolean isPawn(Piece p){
        return (!p.isBomb() && !p.isShield());
    }

    private boolean validMove(int xi, int yi, int xf, int yf){
        //returns true if the selected piece can move or capture to xf,yf

        //its refers to the selected piece

        //determine its type
        //determine where the selected type can move to
        //if xf,yf happens to be one of these, then return true
        //also consider kinged versions

        String theType;

        //an empty square cannot move, and a piece
        //cannot stack onto another piece

        Piece movingPiece = pieceAt(xi, yi);

        if(movingPiece==null || outOfBounds(xf) || outOfBounds(yf) || pieceAt(xf,yf)!=null)
            return false;
        //if(isPawn(pieceAt(xi, yi))) {
            if(Math.abs(xf-xi)==1){ 
                if(movingPiece.isKing() && Math.abs(yf-yi)==1)
                    return true;
                else if (playerTurn==0 && (yf-yi==1))
                    return true;
                else if (playerTurn==1 && (yi-yf==1)) 
                    return true;
                else
                    return false;
            }
            else if (Math.abs(xf-xi)==2){ //considers captures
                if(movingPiece.isKing() && Math.abs(yf-yi)==2)
                    return true;
                else if (playerTurn==0 && (yf-yi==2))
                    return true;
                else if (playerTurn==1 && (yi-yf==2))
                    return true;
                return false;
            }

        //}
        return false;

    }


    public void select(int x, int y) {
        //need to do the StdDrawPlus at main, or called from main
        //StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        //StdDrawPlus.filledSquare((int)x + shift, (int)y + shift, squareRadius);
        //StdDrawPlus.show(100); 
        // if (canSelect(x, y)) { //assumes canSelect returns true
            if(pieceAt(x,y)!=null)
            {
                clearSelected();
                selected[x][y] = true;
            }
            else
            {
                pieceAt(getPieceSelectedX(),getPieceSelectedY()).move(x, y);
                moved = true;
            }
        //}
    }

    private boolean hasCaptured(){
        // for (int i = 0; i<N; i++) {
        //     for (int j = 0; j < N; j++) {
        //         if (captured[i][j]!=0)
        //             return true;
        //     }
        // }
        // return false;

        for (int i = 0; i < N; i++ ) {
            for (int j = 0; j < N; j++) {
                if (boardPieces[i][j]!=null)
                    if(boardPieces[i][j].hasCaptured())
                        return true;
            }
        }
        return false;

    }

    //if returns true, then delete the drawing (since piece moved)
    public boolean canEndTurn() {
        //if(moved){
        //    System.out.println("can end the turn");
        //}
        return (moved || hasCaptured());
    }

    public void endTurn() {
        playerTurn = (playerTurn + 1)%2;
        //clear arrays
        moved = false;
        clearSelected();
        //clearCaptured();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(boardPieces[i][j]!=null)
                {
                    boardPieces[i][j].doneCapturing();
                }
            }
        }
    }




    public String winner() {
        int fires = 0;
        int waters = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++){
                if (boardPieces[i][j]!=null)
                    if(boardPieces[i][j].side()==0)
                        fires++;
                    else
                        waters++;
            }
        }

        if (fires==0 && waters==0)
            return "No one";
        else if (fires==0)
            return "Water";
        else if (waters==0)
            return "Fire";
        else
            return null;
    }

    private void setScale(){
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
    }


    public static void main(String[] args) {
        Board b = new Board(false);
        b.drawBoard();
        int player = 0;

        //it is currently fire's turn

        //while nobody has won...
        while (b.winner() == null) {
            

                //System.out.println(onBoardX + " " + onBoardY);
            
                //if player's piece is at x,y --> select it
                //while he keeps selecting his own pieces, select that one instead
             int onBoardX = -1;
             int onBoardY = -1;
    //         int fromBoardX;
    //         int fromBoardY;
    //         int toBoardX;
    //         int toBoardY;

             int [] clickHistory = new int[100];
             int counter = 0;
             do
             {

                 if (StdDrawPlus.mousePressed()) {
    //                 //to prevent StdDrawPlus collecting continous input 
                    StdDrawPlus.show(150);

                     double x = StdDrawPlus.mouseX();
                     double y = StdDrawPlus.mouseY();

                     onBoardX = (int)x;
                     onBoardY = (int)y;

                     //System.out.println("selected: " + onBoardX + " " + onBoardY);

                    if(b.canSelect(onBoardX,onBoardY)){ //changed from if..
                        clickHistory[counter] = onBoardX;
                        clickHistory[counter+1] = onBoardY;
                        counter+=2;
                        b.select(onBoardX, onBoardY);
                        //if(!emptyBoard)
                        //b.colorCheckeredSquare(onBoardX,onBoardY);
            
                        b.drawCurrentBoard();
                        StdDrawPlus.show(10);
                    }
  
                  if (b.canEndTurn())
                        {
                            b.colorCheckeredSquare(clickHistory[counter-4], clickHistory[counter-3]);
                            //b.drawCurrentBoard();
                            StdDrawPlus.show(50);
                            //System.out.println("colored the square! at " + clickHistory[counter-2] + " " + clickHistory[counter-1]);
                        }   

                }
               }while(!b.canEndTurn() && b.winner()==null);
               b.endTurn();
               b.drawCurrentBoard();
                // for(int x = 0; x < b.getDimension(); x++){ 
                //     for(int y = 0; y < b.getDimension(); y++) {
                //         if(b.boardPieces[x][y]!=null)
                //             System.out.println("[" + x + "]" + "[" + y + "]" + " = " + b.pieceAt(x,y).getType());
                //     }
                // }
               StdDrawPlus.show(50);
               //System.out.println("out here now!");
           }
           System.out.println(b.winner());
       }

   }
