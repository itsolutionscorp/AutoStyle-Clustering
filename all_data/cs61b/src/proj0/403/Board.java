/*
Author: Xizhao Deng
Title: Checker game
Purpose: Porj0 for CS61B
Note: this is the final submission version.

Orientation of the checker board
y=row
^(0, 7)
|
|
|
|
|---------------> x=col
(0, 0)       (7, 0)
*/


public class Board
{
	private Piece[][] checkerBoard = new Piece[8][8];
	private boolean isItMoved; //Did any piece move in a turn
	private boolean anyCapture;	//Did any capture happen in a turn
	private boolean isFireTurn;	//Indicate whose turn it it
	private boolean bombHasCapture; //Has bomb conducted 1 capture and exploded
	private int selectedPieceX; //reset to -1 at the end of a turn
	private int selectedPieceY;	//reset to -1 at the end of a turn
	private int selectedSpaceX;	//reset to -1 at the end of a turn
	private int selectedSpaceY;	//reset to -1 at the end of a turn



	public Board(boolean shouldBeEmpty){
		if(shouldBeEmpty){
			for(int row=0; row<8; row++)
				for(int col=0; col<8; col++)
					checkerBoard[col][row] = null;
		}
		else{

			for(int row=0; row<8; row++)
				for(int col=0; col<8; col++)
					checkerBoard[col][row] = null;	

			for(int row=0; row<8; row++)
				for(int col=0; col<8; col++){
					if((row==0) && (col%2==0))
						checkerBoard[col][row] = new Piece(true, this, col, row, "pawn");
					if((row==1) && (col%2==1))
						checkerBoard[col][row] = new Piece(true, this, col, row, "shield");
					if((row==2) && (col%2==0))
						checkerBoard[col][row] = new Piece(true, this, col, row, "bomb");
					if((row==5) && (col%2==1))
						checkerBoard[col][row] = new Piece(false, this, col, row, "bomb");
					if((row==6) && (col%2==0))
						checkerBoard[col][row] = new Piece(false, this, col, row, "shield");
					if((row==7) && (col%2==1))
						checkerBoard[col][row] = new Piece(false, this, col, row, "pawn");
				}
		}

		isItMoved = false;
		anyCapture = false;
		isFireTurn = true;
		bombHasCapture = false;
		selectedPieceX = -1;
		selectedPieceY = -1;
		selectedSpaceX = -1;
		selectedSpaceY = -1;

	}

	private boolean boundCheck(int x, int y){
		if(((x>=0) && (x<=7)) && ((y>=0) && (y<=7)))
			return true;
		return false;
	}


	private boolean anyPieceSelected(){
		return ((selectedPieceX!=-1) && (selectedPieceY!=-1));
	}

	private boolean anySpaceSelected(){
		return ((selectedSpaceX!=-1) && (selectedSpaceY!=-1));
	}

	public Piece pieceAt(int x, int y){
		if(boundCheck(x, y))
			return checkerBoard[x][y];	//return the piece or null if no piece there
		else
			return null;	//return null if (x, y) is out of bounds
	}


	private boolean validMoveHelper(int xi, int yi, int xf, int yf, boolean kingYN, boolean fireYN){
		
		if(kingYN && fireYN){
			if((((xf==(xi+1)) && (yf==(yi+1)))||
				((xf==(xi-1)) && (yf==(yi-1)))||
				((xf==(xi-1)) && (yf==(yi+1)))||
				((xf==(xi+1)) && (yf==(yi-1))))&&
				(!anyCapture))
				return true;

			else if(((pieceAt(xi+1, yi+1)!=null)&&(!checkerBoard[xi+1][yi+1].isFire()))&&
					((xf==(xi+2))&&(yf==(yi+2))))
				return true;

			else if(((pieceAt(xi-1, yi-1)!=null)&&(!checkerBoard[xi-1][yi-1].isFire()))&&
					((xf==(xi-2))&&(yf==(yi-2))))
				return true;

			else if(((pieceAt(xi+1, yi-1)!=null)&&(!checkerBoard[xi+1][yi-1].isFire()))&&
					((xf==(xi+2))&&(yf==(yi-2))))
				return true;

			else if(((pieceAt(xi-1, yi+1)!=null)&&(!checkerBoard[xi-1][yi+1].isFire()))&&
					((xf==(xi-2))&&(yf==(yi+2))))
				return true;

			else
				return false;
		}
		else if(kingYN && !fireYN){
			if((((xf==(xi+1)) && (yf==(yi+1)))||
				((xf==(xi-1)) && (yf==(yi-1)))||
				((xf==(xi-1)) && (yf==(yi+1)))||
				((xf==(xi+1)) && (yf==(yi-1))))&&
				(!anyCapture))
				return true;

			else if(((pieceAt(xi+1, yi+1)!=null)&&(checkerBoard[xi+1][yi+1].isFire()))&&
					((xf==(xi+2))&&(yf==(yi+2))))
				return true;

			else if(((pieceAt(xi-1, yi-1)!=null)&&(checkerBoard[xi-1][yi-1].isFire()))&&
					((xf==(xi-2))&&(yf==(yi-2))))
				return true;

			else if(((pieceAt(xi+1, yi-1)!=null)&&(checkerBoard[xi+1][yi-1].isFire()))&&
					((xf==(xi+2))&&(yf==(yi-2))))
				return true;

			else if(((pieceAt(xi-1, yi+1)!=null)&&(checkerBoard[xi-1][yi+1].isFire()))&&
					((xf==(xi-2))&&(yf==(yi+2))))
				return true;

			else
				return false;
		}
		else if(!kingYN && fireYN){
			if((((xf==(xi+1)) && (yf==(yi+1)))||((xf==(xi-1)) && (yf==(yi+1)))) && (!anyCapture))
				return true;

			else if(((pieceAt(xi+1, yi+1)!=null)&&(!checkerBoard[xi+1][yi+1].isFire()))&&
					((xf==(xi+2))&&(yf==(yi+2))))
				return true;

			else if(((pieceAt(xi-1, yi+1)!=null)&&(!checkerBoard[xi-1][yi+1].isFire()))&&
					((xf==(xi-2))&&(yf==(yi+2))))
				return true;

			else
				return false;	
		}
		else{
			if((((xf==(xi+1)) && (yf==(yi-1)))||((xf==(xi-1)) && (yf==(yi-1)))) && (!anyCapture))
				return true;

			else if(((pieceAt(xi+1, yi-1)!=null)&&(checkerBoard[xi+1][yi-1].isFire()))&&
					((xf==(xi+2))&&(yf==(yi-2))))
				return true;

			else if(((pieceAt(xi-1, yi-1)!=null)&&(checkerBoard[xi-1][yi-1].isFire()))&&
					((xf==(xi-2))&&(yf==(yi-2))))
				return true;

			else
				return false;		
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf){

		//if (xi, yi) is empty or (xf, yf) is not empty
		if((pieceAt(xi, yi)==null) || (pieceAt(xf, yf) != null))
			return false;

		//if (xi, yi is not empty and (xf, yf) is empty)
		if(checkerBoard[xi][yi].isKing()){
			if(checkerBoard[xi][yi].isFire())
				return validMoveHelper(xi, yi, xf, yf, true, true);
			else
				return validMoveHelper(xi, yi, xf, yf, true, false);
		}
		else{
			if(checkerBoard[xi][yi].isFire())
				return validMoveHelper(xi, yi, xf, yf, false, true);
			else
				return validMoveHelper(xi, yi, xf, yf, false, false);
		}
	}


	public boolean canSelect(int x, int y){
		if(boundCheck(x,y)){
			//there exists a piece at (x,y)
			if(pieceAt(x, y) != null){
				boolean condition1 = ((pieceAt(x, y).isFire() == isFireTurn) && (!anyPieceSelected()));
				boolean condition2 = ((pieceAt(x, y).isFire() == isFireTurn) && (anyPieceSelected() && (!isItMoved)));	

				return ((condition1 || condition2) && !bombHasCapture);
			}
			//(x, y) is empty
			else{
				boolean condition3 = ((anyPieceSelected() && (!isItMoved)) && validMove(selectedPieceX, selectedPieceY, x, y));
				boolean condition4 = ((anyPieceSelected() && anyCapture) && validMove(selectedPieceX, selectedPieceY, x, y));
				
				return ((condition3 || condition4) && !bombHasCapture);
			}
		}
		//out of bound, return false
		else{
			return false;
		}
	}

	public void select(int x, int y){

		if(checkerBoard[x][y] != null){
			selectedPieceX = x;
			selectedPieceY = y;
		}
		else{
			selectedSpaceX = x;
			selectedSpaceY = y;
		}
		
		if(anyPieceSelected() && anySpaceSelected()){
			//conduct a move
    		pieceAt(selectedPieceX, selectedPieceY).move(selectedSpaceX, selectedSpaceY);

    		update();
		}		
	}


    private void update(){
		//check if the piece is actually moved to the destination
    	//in case that the piece was a bomb and exploded after the move
		if(pieceAt(selectedSpaceX, selectedSpaceY)!=null){
    		selectedPieceX = selectedSpaceX;
    		selectedPieceY = selectedSpaceY;
    		isItMoved = true;
    		bombHasCapture = false;
			detectCapture();
	}
		//a bomb exploded, reset everything
		else{
    		isItMoved = true;
    		selectedPieceX = -1;
    		selectedPieceY = -1;
    		selectedSpaceX = -1;
    		selectedSpaceY = -1;
    		anyCapture = true;
    		bombHasCapture = true;
		}    
	}

	public void place(Piece p, int x, int y){
		if((boundCheck(x, y)) && (p!=null)){
			//if the slot is empty or there is a different piece on that slot
			checkerBoard[x][y] = p;
		}

	}

	public Piece remove(int x, int y){
		if(boundCheck(x, y)){
			if(pieceAt(x, y) != null){
				Piece removedPiece = checkerBoard[x][y];
				checkerBoard[x][y] = null;
				return removedPiece;
			}
			else{
				System.out.println("no piece at (x, y)");
				return null;
			}
		}
		else{
			System.out.println("The input coordinates are out of bounds");
			return null;
		}
	}

	public boolean canEndTurn(){
		return (isItMoved || anyCapture);
	}

	public void endTurn(){
		for(int row=0; row<8; row++){
			for(int col=0; col<8; col++){
				if(pieceAt(col, row) != null)
					pieceAt(col, row).doneCapturing();
			}
		}

		isItMoved = false;
		anyCapture = false;
		isFireTurn = !isFireTurn;
		bombHasCapture = false;
		selectedPieceX = -1;
		selectedPieceY = -1;
		selectedSpaceX = -1;
		selectedSpaceY = -1;
	
	}

	private void detectCapture(){
		if((selectedPieceX!=-1)&&(selectedPieceY!=-1)){
			if(checkerBoard[selectedPieceX][selectedPieceY].hasCaptured())
				anyCapture = true;
		}
	}


	private boolean anyFirePiece(){
		for(int row=0; row<8; row++){
			for(int col=0; col<8; col++){
				if((checkerBoard[col][row] != null) && (checkerBoard[col][row].isFire()))
					return true;
			}
		}
		return false;
	}

	private boolean anyWaterPiece(){
		for(int row=0; row<8; row++){
			for(int col=0; col<8; col++){
				if((checkerBoard[col][row] != null) && (!checkerBoard[col][row].isFire()))
					return true;
			}
		}
		return false;
	}

	public String winner(){
		if((!anyWaterPiece()) && (!anyFirePiece()))
			return "No one";
		else if((anyWaterPiece()) && (!anyFirePiece()))
			return "Water";
		else if((!anyWaterPiece()) && (anyFirePiece()))
			return "Fire";
		else
			return null;
	}

	private void placePicture(int col, int row){

        if(checkerBoard[col][row] != null){
            if(checkerBoard[col][row].isFire()){
            	if(checkerBoard[col][row].isKing()){
            		if(checkerBoard[col][row].isShield())
                		StdDrawPlus.picture(col + .5, row + .5, "img/shield-fire-crowned.png", 1, 1);
            		else if(checkerBoard[col][row].isBomb())
                		StdDrawPlus.picture(col + .5, row + .5, "img/bomb-fire-crowned.png", 1, 1);
            		else//pawn
                		StdDrawPlus.picture(col + .5, row + .5, "img/pawn-fire-crowned.png", 1, 1);
            	}
            	else{
            		if(checkerBoard[col][row].isShield())
                		StdDrawPlus.picture(col + .5, row + .5, "img/shield-fire.png", 1, 1);
            		else if(checkerBoard[col][row].isBomb())
                		StdDrawPlus.picture(col + .5, row + .5, "img/bomb-fire.png", 1, 1);
            		else//pawn
                		StdDrawPlus.picture(col + .5, row + .5, "img/pawn-fire.png", 1, 1);
           		}
            }
            else{ //isWater
            	if(checkerBoard[col][row].isKing()){
            		if(checkerBoard[col][row].isShield())
                		StdDrawPlus.picture(col + .5, row + .5, "img/shield-water-crowned.png", 1, 1);
            		else if(checkerBoard[col][row].isBomb())
                		StdDrawPlus.picture(col + .5, row + .5, "img/bomb-water-crowned.png", 1, 1);
            		else//pawn
                		StdDrawPlus.picture(col + .5, row + .5, "img/pawn-water-crowned.png", 1, 1);
            	}
            	else{
            		if(checkerBoard[col][row].isShield())
                		StdDrawPlus.picture(col + .5, row + .5, "img/shield-water.png", 1, 1);
            		else if(checkerBoard[col][row].isBomb())
                		StdDrawPlus.picture(col + .5, row + .5, "img/bomb-water.png", 1, 1);
            		else//pawn
                		StdDrawPlus.picture(col + .5, row + .5, "img/pawn-water.png", 1, 1);
				}           
            }
        }	
    }


    private void drawBoard(){
        for(int row=0; row<8; row++){
            for(int col=0; col<8; col++){

                if((col+row)%2==0)
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);

                StdDrawPlus.filledSquare(col + .5, row + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                placePicture(col, row);
            }
        }

        //highlight the selected square
        if(anyPieceSelected()){
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            StdDrawPlus.filledSquare(selectedPieceX + .5, selectedPieceY + .5, .5);
            placePicture(selectedPieceX, selectedPieceY);
        }
        if(anySpaceSelected()){
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            StdDrawPlus.filledSquare(selectedSpaceX + .5, selectedSpaceY + .5, .5);
            placePicture(selectedPieceX, selectedPieceY);
        }

		StdDrawPlus.show(100);
    }


//=======================Main========================
	
	public static void main(String[] args){

        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		Board gameBoard = new Board(false);
        int xCor;
        int yCor;


        gameBoard.drawBoard();


        while(gameBoard.winner() == null) {

        	//press n to start a new game
			if(StdDrawPlus.isNPressed()){
				gameBoard = new Board(false);
    		}

			//draw everything
        	gameBoard.drawBoard();

            while(!StdDrawPlus.isSpacePressed())
            {
	        	//press n to start a new game
    			if(StdDrawPlus.isNPressed()){
					gameBoard = new Board(false);
    				//draw everything
	            	gameBoard.drawBoard();
        		}


	            if (StdDrawPlus.mousePressed()) {
	                xCor = (int)StdDrawPlus.mouseX();
	                yCor = (int)StdDrawPlus.mouseY();

	                //gameBoard.detectCapture();	//if the selected piece has captured component piece, set the anyCapture flag
	                								//this is commented out with the use of detectCapture() in update()
	            	
	                if(gameBoard.canSelect(xCor, yCor)){
	            		gameBoard.select(xCor, yCor); //if successfully select a piece, save its postion
	            	}
	            	else{
	            		gameBoard.selectedSpaceX = -1;
						gameBoard.selectedSpaceY = -1;
	            	}

	    			//draw everything
		            gameBoard.drawBoard();
	            }
            }

            //draw everything
            gameBoard.drawBoard();
            if(gameBoard.canEndTurn())
            	gameBoard.endTurn();
        }
        System.out.println("The winner is " + gameBoard.winner());
    }
}

