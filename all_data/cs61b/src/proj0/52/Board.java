public class Board{

	private Piece[][] board;
	private Piece select;
	//private boolean hasSelected;
	private boolean moved;
	private boolean fireTurn;
	private int sX;
	private int sY;

	private void reassignSpots(int i, int j){
		Piece pieceAt = this.pieceAt(i, j);
		if(pieceAt != null){
			if (pieceAt.isFire() && !pieceAt.isBomb() && !pieceAt.isShield())
				StdDrawPlus.picture(i+ .5, j + .5, "img/pawn-fire.png", 1, 1);
			if (pieceAt.isFire() && pieceAt.isBomb())
				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
			if (pieceAt.isFire() && pieceAt.isShield())
				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
			if (!pieceAt.isFire() && !pieceAt.isBomb() && !pieceAt.isShield())
				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
			if (!pieceAt.isFire() && pieceAt.isBomb())
				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
			if (!pieceAt.isFire() && pieceAt.isShield())
				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
			if (pieceAt.isFire() && !pieceAt.isBomb() && !pieceAt.isShield() && pieceAt.isKing())
				StdDrawPlus.picture(i+ .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
			if (pieceAt.isFire() && pieceAt.isBomb() && pieceAt.isKing())
				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
			if (pieceAt.isFire() && pieceAt.isShield() && pieceAt.isKing())
				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
			if (!pieceAt.isFire() && !pieceAt.isBomb() && !pieceAt.isShield() && pieceAt.isKing())
				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
			if (!pieceAt.isFire() && pieceAt.isBomb() && pieceAt.isKing())
				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
			if (!pieceAt.isFire() && pieceAt.isShield() && pieceAt.isKing())
				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);

		}
		

	}

	private void draw(){
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		for(int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++){
				if ((i + j) % 2 != 0){
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				else StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				StdDrawPlus.filledSquare(i + .5, j + .5, 0.5);
				reassignSpots(i, j);
				

			}
			
		}
		if (select != null && !moved){
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				StdDrawPlus.filledSquare(sX + .5, sY + .5, 0.5);
				reassignSpots(sX, sY);
		}
		
	}

	public static void main(String []args){
		// Board b = new Board(true);
		Board b = new Board(false);
		b.draw();
	
		while(true){
			b.draw();
			if (StdDrawPlus.mousePressed()){
				int x = (int) StdDrawPlus.mouseX();
				int y = (int) StdDrawPlus.mouseY();
				if (b.canSelect(x, y)){
					b.select(x, y);
					
				}
			}
			if(StdDrawPlus.isSpacePressed() && b.canEndTurn()){
				b.endTurn();
				System.out.println(b.winner());
				
			}
			b.draw();
			
			StdDrawPlus.show(100);
			


		}
	}
	public Board(boolean shouldBeEmpty){
		board = new Piece[8][8];
		fireTurn = true;
		if(shouldBeEmpty){
			draw();
			return;
		}
		else{
			draw();
			
			for (int i = 0; i < 8; i++){
				if (i % 2 == 0)
					board[i][0] = new Piece(true, this, i, 0, "pawn");
			}
			for (int i = 0; i < 8; i++){
				if(i %2 != 0)
					board[i][1] = new Piece(true, this, i, 1, "shield");
			}

			for (int i = 0; i < 8; i++){
				if(i %2 == 0)
					board[i][2] = new Piece(true, this, i, 2, "bomb");
			}
			for (int i = 0; i < 8; i++){
				if(i % 2 != 0)
					board[i][5] = new Piece(false, this, i, 5, "bomb");
			}
			for (int i = 0; i < 8; i++){
				if(i % 2 == 0)
					board[i][6] = new Piece(false, this, i, 6, "shield");
			}
			for (int i = 0; i < 8; i++){
				if(i % 2 != 0)
					board[i][7] = new Piece(false, this, i, 7, "pawn");
			}
		}
	}

	public Piece pieceAt(int x, int y){
		if((x > 7 || y > 7) || (x < 0 || y < 0) || board[x][y] == null)
			return null;
		return board[x][y];

	}
// if(select == null){
			// 	boolean myTurn = (fireTurn && pieceAt(x, y).isFire()) || (!fireTurn && !pieceAt(x,y).isFire());
			// 	return myTurn;
			// }
			// else if(select != null && !moved){
			// 	boolean myTurn2 = (fireTurn && pieceAt(x, y).isFire()) || (!fireTurn && !pieceAt(x, y).isFire());
			// 	return myTurn2;
			// }
	public boolean canSelect(int x, int y){
		//if piece is there
		if(pieceAt(x, y) != null){
			//boolean myTurn = (fireTurn && pieceAt(x, y).isFire()) || (!fireTurn && !pieceAt(x, y).isFire());
			boolean myTurn = fireTurn == pieceAt(x, y).isFire();
			if(select == null && myTurn){
				return true;
			}
			else if(!moved && myTurn/*hasSelected*/){
				return true;
			}
			//return false;
		}
		//if piece isnt there
		else{
			if (select == null)
				return false;
			if (select != null && !moved && validMove(sX, sY, x, y))
				return true;
			if (select != null && select.hasCaptured() && canCapture(sX, sY, x, y))
				return true;
			
			
		}
		return false;
	}

	
	public void select(int x, int y){
		if (pieceAt(x, y) != null){
			select = pieceAt(x, y);
			sX = x;
			sY = y;
			//hasSelected = true;

		}
		else{
			select.move(x, y);
			moved = true;
			sX = x;
			sY = y;
		}
		// if(pieceAt(x, y) != null){
		// 	if(select == null){
		// 		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		// 		StdDrawPlus.filledSquare(x + .5, y + .5, 0.5);
		// 		reassignSpots(x, y);
		// 		select = pieceAt(x, y);
		// 		sX = x;
		// 		sY = y;
				
		// 	}
		// 	else{
		// 		draw();
		// 		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		// 		StdDrawPlus.filledSquare(x + .5, y + .5, 0.5);
		// 		reassignSpots(x, y);
		// 		select = pieceAt(x, y);
		// 		sX = x;
		// 		sY = y;
		// 	}
		// }
		// else{
		// 	if (select != null && validMove(sX, sY, x, y)){
		// 		select.move(x, y);
		// 		moved = true;
		// 		sX = x;
		// 		sY = y;
		// 		draw();
		// 	}
		// 	else if(moved && select.hasCaptured() && canCapture(sX, sY, x, y)){
		// 		select.move(x, y);
		// 		sX = x;
		// 		sY = y;
		// 		draw();
		// 	}
			
		// }
		

	}

	private boolean canCapture(int xi, int yi, int xf, int yf){
		if(pieceAt(xi, yi).isKing() && Math.abs(xf-xi) == 2 && Math.abs(yf - yi) == 2 && pieceAt((xi + xf)/2, (yi + yf)/2) != null)
				return true;
		
		else{
			if(pieceAt(xi, yi).isFire() && Math.abs(xf-xi) == 2 && yf - yi == 2 && pieceAt((xi + xf)/2, (yi + yf)/2) != null)
				return true;
			if(!pieceAt(xi, yi).isFire() && Math.abs(xf-xi) == 2 && yi - yf == 2 && pieceAt((xi + xf)/2, (yi + yf)/2) != null)
				return true;
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf){

		if (pieceAt(xf, yf) != null){
			return false;
		}
		else{
			if(pieceAt(xi, yi).isKing() && Math.abs(xf-xi) == 1 && Math.abs(yf - yi) == 1){
				return true;
			}
			else if(pieceAt(xi, yi).isFire() && Math.abs(xf - xi) == 1 && yf -yi == 1)
				return true;
			else if(!pieceAt(xi, yi).isFire() && Math.abs(xf - xi) == 1 && yi - yf == 1)
				return true;

			return canCapture(xi, yi, xf, yf);
		}
		

	}

	public Piece remove(int x, int y){
			Piece piece = pieceAt(x, y);
			if (piece == null){
				System.out.println("No piece here");
				return null;
			}
			board[x][y] = null;
			reassignSpots(x, y);
			return piece;
		}
	
	public void place(Piece p, int x, int y){
		if (p == null || x > 7 || y > 7)
			return;
		board[x][y] = p;
		//moved = true;
		// sX = x;
		// sY = y;
	}

	public boolean canEndTurn(){
		return moved;
	}

	public void endTurn(){
		select.doneCapturing();
		fireTurn = !fireTurn;
		select = null;
		moved = false;
	}

	public String winner(){
		int countFire = 0;
		int countWater = 0;
		for (int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++)
			{
				if (board[i][j] != null && board[i][j].isFire())
					countFire += 1;
				if (board[i][j] != null && !board[i][j].isFire())
					countWater += 1;
			}
		}
		if (countWater == 0 && countFire != 0)
			return "Fire";
		else if (countWater != 0 && countFire == 0)
			return "Water";
		else if(countWater != 0 && countFire != 0)
			return null;
		
		return "No one";
	}
}