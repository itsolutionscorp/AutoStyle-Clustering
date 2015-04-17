public class Board {

	private Piece[][] pieces;
	private Piece selected;
	private int selectx;
	private int selecty;
	private boolean turnWater;
	private boolean moved;
	private boolean captured;
	private Piece cappedPiece;
	private int cappedPieceX;
	private int cappedPieceY;
	/**Draw N x N board
	*/

	public Board(boolean shouldBeEmpty) {
		this.pieces = new Piece[8][8];

		if (shouldBeEmpty == false) {
	        initializePieces();
		}
	}

	public Piece pieceAt(int x, int y) {
		if (x  > 7 || y  > 7){
			return null;
		}
		else if (x < 0 || y < 0) {
			return null;
		}
		else if (pieces[y][x] != null) {
			return pieces[y][x];
		}
		else {
			return null;
		}
	}
	//NEED TO TEST/ FIGURE OUT HOW TO TEST!!!!!!
	public void place(Piece p, int x, int y){
		if (x >= 0 && x < 8 && y >= 0 && y < 8 && p != null){
					pieces[y][x] = p;
		}
	}

	//NEED TO TEST, also why do we need to return an error message????, 
	public Piece remove(int x, int y){
		if (x >= 0 && x < 8 && y >= 0 && y < 8){
			if (pieces[y][x] != null){
				Piece temp = pieces[y][x];
				pieces[y][x] = null; 
				return temp;
			}
			else {
				System.out.println("no piece at this position");
				return null;
			}
		}
		else {
			System.out.println("out of bounds");
			return null;
		}
	}

	// //need to write this method
	public boolean canSelect(int x, int y)
	{
		Piece placer = pieceAt(x, y);
		if (placer == null)
		{
			if (validMove(selectx, selecty, x, y) == true)
				return true;
			else
				return false;
		}
		else 
		{
		 	if (turnWater == false)
		 	{
				if (placer.isFire() == true && moved == false) //need to account for when moved is false
					return true;
				else 
					return false;
			}	
			else 
			{
				if (placer.isFire() == false && moved == false)// need to account for when moved is false
					return true;
				else
					return false;
			}	
		}
	}

	public void select(int x, int y)
	{
		if (pieceAt(x, y) != null )
		{
			selected = pieceAt(x, y);
			selectx = x;
			selecty = y; 
		}
		else {
			if (moved == false && pieceAt(selectx, selecty).hasCaptured() == false && captured == false){
				selected.move(x,y);
				selectx = x;
				selecty = y; 
					// if (pieceAt(selectx, selecty).isKing() == true){
					// 	System.out.println("king bogus line");
					// 	moved = true;
					// }
				moved = true; 
			}

			else if (pieceAt(selectx, selecty)!=null && captured == true){ //took out moved == flase &&
				selected.move(x,y);
				selectx = x;
				selecty = y; 
				captured = false;
				moved = true;
			}
			else if (moved == false && pieceAt(selectx, selecty)!=null && captured == true){
				selected.move(x,y);
				selectx = x;
				selecty = y; 
				captured = false;
				moved = true;				
			}


			else if (moved == false && pieceAt(selectx, selecty)!=null && pieceAt(selectx, selecty).hasCaptured() == true && captured == false)
			{
				// selectx = x;
				// selecty = y; 
				selected.doneCapturing();
				moved = true;
			}
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf)
	{
		int xdist = xf - xi;
		int ydist = yf - yi;
		int boundy = yi + 1;
		int boundy1 = yi - 1;
		int boundx = xi + 1;
		int boundx1 = xi -1;

		if (pieceAt(xi, yi) == null || xf < 0 || xf > 7 || yf < 0 || yf > 7 || xi < 0 || xf > 7 || yi < 0 || yi > 7) //initial piece is null or out of bounds
			return false; //invalid move
		else //intial piece is not null
		{	
			if (pieceAt(xf,yf) != null) //final piece is not null
				return false; //invalid move


			else //final piece is null
			{ 
				if (pieceAt(xi, yi).isKing() == false) //not a King
				{
					if (pieceAt(xi, yi).isFire() == true) //not a King and a Fire piece
					{
						if (Math.abs(xdist) == 1 && ydist == 1)
						{
	 						return true; 
	 					}	
	 					if (boundx >= 0 && boundx < 8 && boundy >= 0 && boundy < 8 && pieceAt(xi+1, yi+1 ) != null && pieceAt(xi+1, yi+1 ).isFire() == false)//capture case 1 (up 1 right 1 is water piece)
	 					{
	 						if ((xdist == 2) && (ydist == 2)){ // up 2 right 2 capture case1
	 							captured = true;
	 							cappedPiece = pieceAt(xi+1, yi+1);
	 							// pieceAt(xi, yi).hasCaptured();
	 							cappedPieceX = xi + 1;
	 							cappedPieceY = yi + 1;
	 							return true;
	 							}
	 					}
	 					if (boundx1 >= 0 && boundx1 < 8 && boundy >= 0 && boundy < 8 && pieceAt(xi-1, yi +1 ) != null && pieceAt(xi-1, yi +1 ).isFire() == false )//capture case 2 (up 1 left 1 is water piece)
	 					{	
	 						if ((xdist == -2) && (ydist == 2)) //up 2 left 2 capture case 2
	 						{
	 							captured = true;
	 							cappedPiece = pieceAt(xi-1, yi +1 );
	 							// pieceAt(xi, yi).hasCaptured();
	 							cappedPieceX = xi - 1;
	 							cappedPieceY = yi + 1;	
	 							return true;
	 						}
	 					}
	 				}	
	 				else //not a King and a water piece
	 				{
	 					// System.out.println("we here");
	 					if (Math.abs(xdist) == 1 && ydist == -1) //move down and left or down and right
	 					{
	 						return true;
	 					}	
	 					if (boundx >= 0 && boundx < 8 && boundy1 >= 0 && boundy1 < 8 && pieceAt(xi+1, yi-1) != null && pieceAt(xi+1, yi-1 ).isFire() == true) //capture case 1
	 					{
	 						// System.out.println("we out here");
	 						if ((xdist == 2) && (ydist == -2)) //down 2 and right 2 capture case 1
	 							{
	 							captured = true;
	 							cappedPiece = pieceAt(xi+1, yi-1);
	 							// pieceAt(xi, yi).hasCaptured();
								cappedPieceX = xi + 1;
	 							cappedPieceY = yi - 1;	 							
	 							return true;
	 						}
						}
						if (boundx1 >= 0 && boundx1 < 8 && boundy1 >= 0 && boundy1 < 8 && pieceAt(xi-1, yi-1 ) != null && pieceAt(xi-1, yi-1 ).isFire() == true) //capture case2
						{	
							// System.out.println("we really here");
	 						if ((xdist == -2) && (ydist == -2))  //down 2 and right 2 capture case 2
	 						{
	 							cappedPiece = pieceAt(xi-1, yi-1);
	 							// pieceAt(xi, yi).hasCaptured();
	 							captured = true;
	 							cappedPieceX = xi - 1;
	 							cappedPieceY = yi - 1;
	 							return true;
	 						}
	 					}
					}
				}	
				else //is a King
				{
					// System.out.println("we be out here");
				    if ((Math.abs(xdist) == 1) && (Math.abs(ydist) == 1))//up 1, down 1, right 1, left 1, valid for all kings
				    	{ 
						return true;
						}	
					else if (pieceAt(xi,yi).isFire() == true)//king and fire
					{
						if (boundx >= 0 && boundx < 8 && boundy >= 0 && boundy < 8 && pieceAt(xi+1, yi+1) != null && pieceAt(xi+1, yi+1).isFire() == false)//up 1 right 1 is water peice
						{
							if (xdist== 2 && ydist == 2) //up 2 and right 2, capture move1
							{ 
								captured = true;
								cappedPiece = pieceAt(xi+1, yi+1);
								// pieceAt(xi, yi).hasCaptured();
								cappedPieceX = xi + 1;
	 							cappedPieceY = yi + 1;		
								return true;
							}
						}
						if (boundx >= 0 && boundx < 8 && boundy1  >= 0 && boundy1 < 8 && pieceAt(xi+1, yi-1) != null && pieceAt(xi+1, yi-1).isFire() == false) //down 1 and right 1 is water piece
						{
							if (xdist== 2 && ydist == -2) //down 2 and right 2, capture move2
							{
								captured = true;
								cappedPiece = pieceAt(xi+1, yi-1);
								// pieceAt(xi, yi).hasCaptured();
								cappedPieceX = xi + 1;
	 							cappedPieceY = yi - 1;				
								return true;
							}
						}
						if (boundx1 >= 0 && boundx1 < 8 && boundy  >= 0 && boundy < 8 && pieceAt(xi-1, yi+1) != null && pieceAt(xi-1, yi+1).isFire() == false) //up 1 and left 1 is water piece
						{
							if (xdist== -2 && ydist == 2) //up 2 and left 2, capture move3
							{
								captured = true;
								cappedPiece = pieceAt(xi-1, yi+1);
								// pieceAt(xi, yi).hasCaptured();
								cappedPieceX = xi - 1;
	 							cappedPieceY = yi + 1;				
								return true;
							}
						}
						if (boundx1 >= 0 && boundx1 < 8 && boundy1 >= 0 && boundy1 < 8 && pieceAt(xi-1, yi-1) != null && pieceAt(xi-1, yi-1).isFire() == false) //down 1 and left 1
						{
							if (xdist== -2 && ydist == -2) //down 2 and left 2, capture 4
							{
								captured = true;
								cappedPiece = pieceAt(xi-1, yi-1);
								// pieceAt(xi, yi).hasCaptured();
								cappedPieceX = xi - 1;
	 							cappedPieceY = yi - 1;						
								return true;
							}
						}
					}
					else //if (pieceAt(xi,yi).isFire() == false) //king and water
					{
						if (boundx >= 0 && boundx < 8 && boundy >= 0 && boundy < 8 && pieceAt(xi+1, yi+1) != null && pieceAt(xi+1, yi+1).isFire() == true) // up 1 right 1 is a fire
						{
							if (xdist== 2 && ydist == 2) //up 2 right 2, caputre case1
							{
								captured = true;
								cappedPiece = pieceAt(xi+1, yi+1);
								// pieceAt(xi, yi).hasCaptured();
								cappedPieceX = xi + 1;
	 							cappedPieceY = yi + 1;				
								return true;
							}
						}
						if (boundx >= 0 && boundx < 8 && boundy1 >= 0 && boundy1 < 8 && pieceAt(xi+1, yi-1) != null && pieceAt(xi+1, yi-1).isFire() == true) // down 1 left 1 is a fire
						{
							if (xdist== 2 && ydist == -2) //down 2 right 2, caputre case2
							{
								captured = true;
								cappedPiece = pieceAt(xi+1, yi-1);
								// pieceAt(xi, yi).hasCaptured();
								cappedPieceX = xi + 1;
	 							cappedPieceY = yi - 1;							
								return true;
							}
						}
						if (boundx1 >= 0 && boundx1 < 8 && boundy >= 0 && boundy < 8 && pieceAt(xi-1, yi+1) != null && pieceAt(xi-1, yi+1).isFire() == true) // up 1 left 1 is a fire
						{
							if (xdist== -2 && ydist == 2) //up 2 left 2, caputre case3
							{
								captured = true;
								cappedPiece = pieceAt(xi-1, yi+1);
								// pieceAt(xi, yi).hasCaptured();
								cappedPieceX = xi - 1;
	 							cappedPieceY = yi + 1;								
								return true;
							}
						}
						if ( boundx1 >= 0 && boundx1 < 8 && boundy1 >= 0 && boundy1 < 8 && pieceAt(xi-1, yi-1) != null && pieceAt(xi-1, yi-1).isFire() == true) // down 1 left 1 is a fire
						{
							if (xdist== -2 && ydist == -2) //down 2 left 2, caputre case1
							{
								captured = true;
								cappedPiece = pieceAt(xi-1, yi-1);
								// pieceAt(xi, yi).hasCaptured();
								cappedPieceX = xi - 1;
	 							cappedPieceY = yi - 1;								
								return true;
							}
						} 
					}				
				}
			}
			return false; //doesnt compile without this statement, also not getting the lines!!!!!!
		}	
	}			


	public boolean canEndTurn(){
		if (moved == true){
		return true;
		}
		else {
			return false;
		}
	}	

	public void endTurn(){
    	if (turnWater == false){
    		turnWater = true;
        }
        else{
            turnWater = false;
        }
        moved = false;		

	}

	public String winner(){
		int sumW = 0;
		int sumF = 0;
		for (int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if (pieces[j][i] == null){
					continue;
				}
				else if (pieces[j][i].isFire() == true){
					sumF += 1;
				}
				else{
					sumW += 1;
				}

			}
		}
		if (sumF + sumW == 0){
			return "No one";
		}
		else if (sumF == 0){
			return "Water";
		}
		else if (sumW == 0) {
			return "Fire";
		}
		else {
			return null;
		}
	
	}	

	private void drawBoard(int N) {
		for (int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if (i == selectx && j == selecty){
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				}
				else if((i +j ) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				}
				else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);	
				}
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);

			}
		}
		for (int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {	
				if(pieces[j][i] == null) {
					continue;
				}			
				if (pieces[j][i].isBomb() == true && pieces[j][i].isFire() == true && pieces[j][i].isKing() == false) {
					StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire.png", 1, 1);
				}
				else if (pieces[j][i].isBomb() == true && pieces[j][i].isFire() == false && pieces[j][i].isKing() == false) {
					StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water.png", 1, 1);
				}
				else if (pieces[j][i].isShield() == true && pieces[j][i].isFire() == true && pieces[j][i].isKing() == false) {
					StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire.png", 1, 1);
				}
				else if (pieces[j][i].isShield() == true && pieces[j][i].isFire() == false && pieces[j][i].isKing() == false) {
					StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water.png", 1, 1);
				}
				else if (pieces[j][i].isBomb() == true && pieces[j][i].isFire() == true && pieces[j][i].isKing() == true) {
					StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire-crowned.png", 1, 1);
				}

				else if (pieces[j][i].isBomb() == true && pieces[j][i].isFire() == false && pieces[j][i].isKing() == true) {
					StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water-crowned.png", 1, 1);
				}
				else if (pieces[j][i].isShield() == true && pieces[j][i].isFire() == true && pieces[j][i].isKing() == true) {
					StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire-crowned.png", 1, 1);
				}	
				else if (pieces[j][i].isShield() == true && pieces[j][i].isFire() == false && pieces[j][i].isKing() == true) {
					StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water-crowned.png", 1, 1);
				}			
				else if (pieces[j][i].isShield() == true && pieces[j][i].isFire() == true && pieces[j][i].isKing() == true) {
					StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire-crowned.png", 1, 1);
				}		
				else if (pieces[j][i].isFire() == true && pieces[j][i].isKing() == true) {
					StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire-crowned.png", 1, 1);
				}	
				else if (pieces[j][i].isFire() == false && pieces[j][i].isKing() == true) {
					StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water-crowned.png", 1, 1);
				}				
				else if (pieces[j][i].isFire() == true && pieces[j][i].isKing() == false) {
					StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire.png", 1, 1);
				}						
				else if (pieces[j][i].isFire() == false && pieces[j][i].isKing() == false) {
					StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water.png", 1, 1);
				}											

			}		
	}
}

	private void initializePieces() {
        	for (int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
						if ((i % 2) == 0 && j == 0) {
							pieces[j][i] = new Piece(true, this, i, j, "pawn");
						}
						else if ((i % 2) != 0 && j == 1) {
							pieces[j][i] = new Piece(true, this, i, j, "shield");
						}
						else if (i % 2 == 0 && j == 2) {
							pieces[j][i] = new Piece(true, this, i, j, "bomb");
						}
						else if ((i % 2) != 0 && j == 5) {
							pieces[j][i] = new Piece(false, this, i, j, "bomb");
						}	
						else if ((i % 2) == 0 && j == 6) {
							pieces[j][i] = new Piece(false, this, i, j, "shield");
						}	

						else if ((i % 2) != 0 && j == 7) {
							pieces[j][i] = new Piece(false, this, i, j, "pawn");
						}		
				}
			}		
	}
	public static void main(String[] args) {
        Board b1 = new Board(false);
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        while(true) {
            b1.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b1.canSelect((int) x, (int) y) == true){
                	b1.select((int) x, (int) y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
            	if (b1.canEndTurn() == true){
            		b1.endTurn();
            	}
            }                 
            StdDrawPlus.show(10);
        }
    }		
}


/** QUESTIONS
1. how to handle capture/move?????
*/
