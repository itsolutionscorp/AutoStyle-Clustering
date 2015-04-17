/** 
 *  @author Rongchang Lei
 *  Board.java
 */

public class Board {

	private boolean emptyBoard; //from the Board constructor 
	private int N = 8;	
	private Piece[][] pieces; 
	private Board currentBoard;
	private boolean isFireTurn = true; 
    private boolean[][] clickedPiece = new boolean[N][N]; 
    private boolean moveYet = false;
    private Piece storeAPiece;
	public static void main(String args[]) {
  		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
       
		//call constructor Board 
        Board currentBoard = new Board(false); 

        currentBoard.drawBoard(N);  //make sure empty board draw first and no need to loop 
        while(true) {
            if (currentBoard.emptyBoard == true) {
            	currentBoard.drawBoard(N);
            } else {
            	currentBoard.drawDefault(N);
            }   
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                currentBoard.clickedPiece[(int) x][(int) y] = true;
            }            
            StdDrawPlus.show(100);
        }
    }

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
    }

	private void drawDefault(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
             	if (pieces[i][j] != null) {
             		StdDrawPlus.picture(i + .5, j + .5, getName(pieces[i][j]), 1, 1);
             	}   
        	}    
        }
    }
    //Board Constructor: 
	public Board(boolean shouldBeEmpty) {
	//follow proj instruction: initilize empty board first, then default board(with pieces)
		pieces = new Piece[N][N]; 
		emptyBoard = shouldBeEmpty; 
		if (shouldBeEmpty) {
			for (int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					pieces[i][j] = null;   
				}
			}
		} else { 
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (((i + j) % 2) == 0) {
 						//Fire 
                        if 		(j == 0) {
                            pieces[i][j] = new Piece(true, currentBoard, i, j,"pawn");
                        }
                        else if (j == 1) {
                            pieces[i][j] = new Piece(true, currentBoard, i, j,"shield");
                        }
                        else if (j == 2) {
                            pieces[i][j] = new Piece(true, currentBoard, i, j,"bomb");
                        }
                        //Water 
                        else if (j == 5) {
                            pieces[i][j] = new Piece(false, currentBoard, i, j, "bomb");
                        }
                        else if (j == 6) {
                            pieces[i][j] = new Piece(false, currentBoard, i, j, "shield");
                        }
                        else if (j == 7) {
                            pieces[i][j] = new Piece(false, currentBoard, i, j, "pawn");
                        } else {
                            pieces[i][j] = null;
                        }

					}
				}
			}
		}
	}

	private static String getName(Piece p){
        //Fire Case
        if (p.isFire()){
            //fire bomb
            if( p.isBomb() ){
                if( p.isKing() )
                    return "img/bomb-fire-crowned.png";
                return "img/bomb-fire.png"; 
            }

            //fire shield
            else if(p.isShield()){
                if(p.isKing())
                    return "img/shield-fire-crowned";
                return "img/shield-fire.png";
            }

            // fire pawn
            else{
                if( p.isKing() )
                    return "img/pawn-fire-crowned.png";
                return "img/pawn-fire.png";
            }
        }

        //Water Case
        else {
            if( p.isBomb() ){
                if( p.isKing() )
                    return "img/bomb-water-crowned.png";
                return "img/bomb-water.png";    
            }

            //fire shield
            else if(p.isShield()){
                if(p.isKing())
                    return "img/shield-water-crowned";
                return "img/shield-water.png";
            }

            // fire pawn
            else{
                if( p.isKing() )
                    return "img/pawn-water-crowned.png";
                return "img/pawn-water.png";
            }
        }
    } 

    public void place(Piece p, int x, int y) {
        if (x > 7 || x < 0 || y >7 || y < 0) {
            return; 
        } 
        else if (p == null) {   
              } else {
        pieces[x][y] = p; }
        }    

    public Piece pieceAt(int x, int y) {
        if (x > 7 || x < 0 || y >7 || y < 0) {   
            return null; } 
        else {
            return pieces[x][y];} 
        }


    public Piece remove(int x, int y) {
            if (x > 7 || x < 0 || y > 7 || y < 0) {
                return null; }
            else if (pieces[x][y] == null) {
                return null; }  
            else {
                if (pieces[x][y].isBomb() == true && pieces[x][y].isFire()) {
                   Piece deleteObject = new Piece(true, currentBoard, x, y,"bomb");
                   pieces [x][y] = null; 
                   return deleteObject; }
                
                else if (pieces[x][y].isBomb() == true && pieces[x][y].isFire() == false) {
                   Piece deleteObject2 = new Piece(false,currentBoard,x,y,"bomb");
                   pieces[x][y] = null; 
                   return deleteObject2; }
                    
                else if (pieces[x][y].isShield() == true && pieces[x][y].isFire() ) {
                   Piece deleteObject3 = new Piece(true,currentBoard,x,y,"shield");
                   pieces[x][y] = null; 
                   return deleteObject3; }
                    
                else if (pieces[x][y].isShield() == true && pieces[x][y].isFire() == false ) {
                   Piece deleteObject4 = new Piece(true,currentBoard,x,y,"shield");
                   pieces[x][y] = null; 
                   return deleteObject4; }

                else if ( pieces[x][y].isFire() == false && pieces[x][y].isBomb() == false && pieces[x][y].isShield() == false) {
                    Piece deleteObject5 = new Piece(true,currentBoard,x,y,"pawn");
                    pieces[x][y] = null; 
                   return deleteObject5; }

                else if (pieces[x][y].isFire() == true &&  pieces[x][y].isBomb() == false && pieces[x][y].isShield() == false) {
                    Piece deleteObject6 = new Piece(true,currentBoard,x,y,"pawn");
                    pieces[x][y] = null; 
                   return deleteObject6;  }    
                } 
            return null;   
        }

//should be private method 
    private boolean validMove(int xi, int yi, int xf, int yf) {
    //edge case  //if (pieces[xi][yi].isKing() == false)
    if (xi < 0 || xi > 7 || yi < 0 || yf > 7 || xf < 0 || xf > 7 || yf > 7 || yf < 0) {
        return false;
    }

    else if (pieces[xf][yf]!= null) {
        return false;
    }

    else if (pieces[xi][yi].isFire()) {
    			//1 step x 2 probability 

                if ((xi - xf == 1) && (yi - yf == -1)) {
                    if (pieces[xf][yf] == null) {
                        return true;
                    } 
                }                         
                else if ((xi - xf == -1) && (yi - yf == -1)) {
                    if (pieces[xf][yf] == null) {
                        return true;
                    }
                }
                //jump x2 probabilities


                else if ((xf - xi == 2) && (yf - yi == 2)) {
                    int row5 = (xf + xi) /2; 
                    int col5 = (yf + yi) /2;
                    if (((pieces[row5][col5].isFire()) == false) && (pieces[xf][yf] == null)) {
                        return true; 
                    
                }}
            

                else if ((xf - xi == -2) && (yf - yi == 2)) {
                    int row6 = (xf + xi) /2; 
                    int col6 = (yf + yi) /2;
                    if ((pieces[row6][col6].isFire() == false) && (pieces[xf][yf] == null)) {
                        return true; 
                    	} 
                	}
    		
//handle king case for fire pieces
                else if (pieces[xi][yi].isKing()) {

            	// 1 step x4 probaliity 
               		if ((xi - xf == 1) && (yi - yf == 1) && (pieces[xf][yf] == null)) {
                        return true;
                  	}                         
                	else if ((xi - xf == -1) && (yi - yf == 1) && (pieces[xf][yf] == null)) {
                        return true;
                    }
                	else if ((xi - xf == 1) && (yi - yf == -1) && (pieces[xf][yf] == null)) {
                        return true; 
                    }
					else if ((xi - xf == -1) && (yi - yf == -1) && (pieces[xf][yf] == null)) {
                        return true;
                    }
    			// 4 jump x4 probability
                	else if ((xf - xi == 2) && (yf - yi == 2)) {
                    	int row = (xf + xi) /2; 
                    	int col = (yf + yi) /2;
                    	if ((pieces[row][col].isFire() == false) && (pieces[xf][yf] == null)){
                        	return true; }
                    } 
                   	else if ((xf - xi == 2) && (yf - yi == -2)) {
                    	int row2 = (xf + xi) /2; 
                    	int col2 = (yf + yi) /2;
                    	if ((pieces[row2][col2].isFire() == false) && (pieces[xf][yf] == null)){
                        	return true; } 
               		}

                	else if ((xf - xi == -2) && (yf - yi == -2)) {
                    	int row3 = (xf + xi) /2; 
                    	int col3 = (yf + yi) /2;
                    	if ((pieces[row3][col3].isFire() == false) && (pieces[xf][yf] == null)){
                        	return true; }                
                    }
                	else if ((xf - xi == -2) && (yf - yi == 2)) {
                    	int row4 = (xf + xi) /2; 
                    	int col4 = (yf + yi) /2;
                    	if ((pieces[row4][col4].isFire() == false) && (pieces[xf][yf] == null)){
                        	return true; }
	                }                    
	            }
    		} else if (!(pieces[xi][yi].isFire())) {  //blue

           	 if ((xf - xi == -1) && (yf - yi == -1)) {
                    if (pieces[xf][yf] == null) {
                        return true;
               		}
                }                         
                else if ((xf - xi == 1) && (yf - yi == -1)) {
                    if (pieces[xf][yf] == null) {
                        return true;
                    } 
                }
                //jump x2 probabilities


                else if ((xf - xi == 2) && (yf - yi == -2)) {
                    int row5 = (xf + xi) / 2; 
                    int col5 = (yf + yi) / 2;
                    if ((pieces[row5][col5].isFire() == true) && (pieces[xf][yf] == null)){
                        return true; 
                  }
                }
            

                else if ((xf - xi == -2) && (yf - yi == -2)) {
                    int row6 = (xf + xi) /2; 
                    int col6 = (yf + yi) /2;
                    if ((pieces[row6][col6].isFire() == true) && (pieces[xf][yf] == null)){
                        return true; 
                   }
                } 
                else if (pieces[xi][yi].isKing()) {

            	// 1 step x4 probaliity (Blue Side)
               		if ((xi - xf == 1) && (yi - yf == 1) && (pieces[xf][yf] == null)) {
                        return true;
                  	}                         
                	else if ((xi - xf == -1) && (yi - yf == 1) && (pieces[xf][yf] == null)) {
                        return true;
                    }
                	else if ((xi - xf == 1) && (yi - yf == -1) && (pieces[xf][yf] == null)) {
                        return true; 
                    }
					else if ((xi - xf == -1) && (yi - yf == -1) && (pieces[xf][yf] == null)) {
                        return true;
                    }
    			// 4 jump x4 probability
                	else if ((xf - xi == 2) && (yf - yi == 2)) {
                    	int row = (xf + xi) /2; 
                    	int col = (yf + yi) /2;
                    	if ((pieces[row][col].isFire() == true) && (pieces[xf][yf] == null)){
                        	return true; }
                    } 
                   	else if ((xf - xi == 2) && (yf - yi == -2)) {
                    	int row2 = (xf + xi) /2; 
                    	int col2 = (yf + yi) /2;
                    	if ((pieces[row2][col2].isFire() == true) && (pieces[xf][yf] == null)){
                        	return true; } 
               		}

                	else if ((xf - xi == -2) && (yf - yi == -2)) {
                    	int row3 = (xf + xi) /2; 
                    	int col3 = (yf + yi) /2;
                    	if ((pieces[row3][col3].isFire() == true) && (pieces[xf][yf] == null)){
                        	return true; }                
                    }
                	else if ((xf - xi == -2) && (yf - yi == 2)) {
                    	int row4 = (xf + xi) /2; 
                    	int col4 = (yf + yi) /2;
                    	if ((pieces[row4][col4].isFire() == true) && (pieces[xf][yf] == null)){
                        	return true; }
	                }                    
	            }
            } 
        return false;
    } 

    public boolean canSelect(int x, int y) {
        if (pieces[x][y] != null) {  //select a piece
            if (isFireTurn) {
                
                boolean notSelected = true; 
                for(int i=0; i<8; i++){
                    for(int j=0; j<8; j++){
                
                    if (clickedPiece[i][j] == true) {
                        notSelected = false; }
                }
               } 
                if ((notSelected) && (pieces[x][y].isFire())) { //STILL CAN PICK OTHER FIRE 
                    return true; 
                } 

                else if ((!notSelected) && (!moveYet)) {
                    return true; 
                }
             } else { //BLUE Turn 
                boolean notSelected = true; 
                for(int i=0; i<8; i++){
                    for(int j=0; j<8; j++){
                
                if (clickedPiece[i][j] == true) {
                    notSelected = false; 
                }
               } 
           }

                if ((notSelected) && (pieces[x][y].isFire())) { //STILL CAN PICK OTHER FIRE 
                    return true; 
                } 

                else if (!notSelected && !moveYet) {
                    return true; 
                }                    
            } return false; 
        } 
        else if (pieces[x][y] == null) { //select empty square 
            boolean notSelected = true; 
            
        // store an a position which can get into empty position, and 
         //valid move can handle the rest of logic 
            int pieceStartX = 1; 
            int pieceStartY = 1; 
            for(int i=0; i<8; i++){
                for(int j=0; j<8; j++){
                    if(clickedPiece[i][j] == true){
                        notSelected = false;
                        pieceStartX = i;
                        pieceStartY = j;
                    }
            if (!notSelected){
                if ((moveYet == false) && validMove(pieceStartX,pieceStartY,x,y)) {
                    return true;
                }
                //select again the moved piece
            } else if (pieces[pieceStartX][pieceStartY] != null) {
                if ((pieces)[pieceStartX][pieceStartY].hasCaptured() && validMove(pieceStartX,pieceStartY,x,y) ) {
                    return true;
                }
            }
                        
                }
                
            }


        } return false; 
    }




    public void select(int x, int y) {
        if (pieces[x][y] != null) {
            clickedPiece[x][y] = true; 
            storeAPiece = pieces[x][y]; 
        }else if (pieces[x][y] == null){ 
            storeAPiece.move(x,y);
            moveYet = true;
            for(int i = 0; i <8; i++){
            for(int j = 0; j<8; j++){
                clickedPiece[i][j] = false;
            }
        }
        clickedPiece[x][y] = true; 
        }

    
    }


    public boolean canEndTurn() {
        // finish capture
        // normal move 
        if (moveYet){
            return true; 
        } 
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j ++) {
                if (pieces[i][j].hasCaptured()) {
                    return true; 
                }

            }
        } 
        return false; 

    }




    public void endTurn(){

    for(int i=0; i<8; i++){
        for(int j=0; j<8; j++){
                if((pieces[i][j]) != null) {
                    if(pieces[i][j].hasCaptured()){     
                        pieces[i][j].doneCapturing();
                            
                    }   
                }
            }
        }   

        isFireTurn = !isFireTurn; 
        moveYet= false;
        for(int i = 0; i <8; i++){
            for(int j = 0; j<8; j++){
                clickedPiece[i][j] = false;
            }
        }


    }




    public String winner() {
        boolean noPiece = true; 
        boolean firePieceLeft = false; 
        boolean waterPieceLeft = false; 
        
        for (int i = 0; i < N; i++){
            for(int  j= 0; j < N; j++) {
                if (pieces[i][j]  != null){
                    noPiece = false; 
                  if (pieces[i][j].isFire()){
                    firePieceLeft = true; 
                }else{
                    waterPieceLeft = true; 
                }
           }
        }
    }
        if (noPiece) {
            return "No one";

        }else if ((firePieceLeft) && (!waterPieceLeft)) { // If only fire pieces remain on the board, fire wins.
            return "Fire";
        }else if (!firePieceLeft && (waterPieceLeft)){  //If only water pieces remain, water wins
            return "Water"; } 
        else if ((firePieceLeft) && (waterPieceLeft)){
                return null; } 
        return null;  //avoid no pointer error

    }
     
}


