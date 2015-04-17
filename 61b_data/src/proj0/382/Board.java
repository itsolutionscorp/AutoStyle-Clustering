public class Board{

	private Piece[][] pieces;
	private int N;
	private boolean fireTurn;
	private boolean playerSelectedPiece;
	private boolean playerHasMovedPiece;
	private boolean playerDone;
	private int prevX, prevY;
	private Piece selectedPiece;

	public Board(boolean shouldBeEmpty){
		N = 8;
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);
		pieces = new Piece[N][N];
		fireTurn = true; //true = fire turn, false = water turn
		playerSelectedPiece = false;
		playerHasMovedPiece = false;
		playerDone = false;
		prevX = 0;
		prevY = 0;
		selectedPiece = null;

		if (shouldBeEmpty){

		}
		else{
			for(int i=0; i<N; i++){
				for(int j=0;j<N;j++){
					if(i%2==0 && j==0){
						pieces[i][j] = new Piece(true, this, i, j, "pawn");
					}
					if (i%2==1 && j==1){
						pieces[i][j] = new Piece(true, this, i, j, "shield");
					}
					if (i%2==0 && j==2){
						pieces[i][j] = new Piece(true, this, i, j, "bomb");
					}
					if(i%2==1 && j==5){
						pieces[i][j] = new Piece(false, this, i, j, "bomb");
					}
					if(i%2==0 && j==6){
						pieces[i][j] = new Piece(false, this, i, j, "shield");
					}
					if(i%2==1 && j==7){
						pieces[i][j] = new Piece(false, this, i, j, "pawn");
					}
				}
			}
		}
	}

	public Piece pieceAt(int x, int y){
		if(x >= N || y >= N || x <0 || y<0){
			return null;
		}
		else if(pieces[x][y] != null){
			return pieces[x][y];
		}
		else return null;
	}

	public boolean canSelect(int x, int y){
		if (playerDone == false){
			if(playerHasMovedPiece == false){
				if(fireTurn){
					if(pieceAt(x,y) != null && pieceAt(x,y).isFire()){
						return true;
					}
				}
				else if(fireTurn==false){
					if(pieceAt(x,y) != null && pieceAt(x,y).isFire() == false){
						return true;
					}
				}
			}
			
			//Checking for multi capture
			if(playerSelectedPiece == true && selectedPiece.hasCaptured()){
				int dx = x - prevX;
				int dy = y - prevY;
				if ((selectedPiece.isKing() && (dx==2 || dx==-2) && (dy == 2 || dy == -2)) || 
					(selectedPiece.isFire() && (dx==2 || dx==-2) && dy ==2) || 
					(selectedPiece.isFire() == false && (dx==2 || dx==-2) && dy == -2)){
					int midx = (prevX + x)/2;
					int midy = (prevY + y)/2;
					if(pieceAt(midx,midy) != null && pieceAt(midx,midy).side() != selectedPiece.side()){
						return true;
					}
				}
				return false;
			}

			if(playerSelectedPiece && playerHasMovedPiece == false){
				return validMove(prevX, prevY, x, y);
			}
		}

		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf){
		if(selectedPiece !=null){
			//if just moving diagonally by one
			if(selectedPiece.isKing()){
				if (Math.abs(xi - xf) ==1 && Math.abs(yi -yf)==1 && pieceAt(xf,yf) == null){
					return true;
				}
			}
			if (selectedPiece.isFire()) {
				if(Math.abs(xf-xi) == 1 && yi-yf == -1 && pieceAt(xf,yf) == null){
					return true;
				}
			}
			else if(selectedPiece.isFire() == false){
				if(Math.abs(xi-xf) == 1 && yi-yf == 1 && pieceAt(xf,yf) == null){
					return true;
				}
			}
			// if distance from initial pos to final pos is 2 by 2 and there is a piece between the two positions
			Piece captured = pieceAt((xi+xf)/2, (yi+yf)/2);
			if (Math.abs(xi-xf)==2 && Math.abs(yi-yf)==2 && captured != null
					 && captured.side() != selectedPiece.side()){
				return true;
			}
		}
		return false;
	}

	public void select(int x, int y){
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		StdDrawPlus.filledSquare(x+.5, y+.5, .5);

		//when you select a piece
		if(pieceAt(x,y) != null){
			playerSelectedPiece = true;
			selectedPiece = pieceAt(x,y);
			prevX = x;
			prevY = y;
		}
		//when you move to empty spot
		else if(playerSelectedPiece && pieceAt(x,y) == null){
			selectedPiece.move(x,y);
			playerHasMovedPiece = true;
			prevX = x;
			prevY = y;
		}
	}

	public void place(Piece p, int x, int y){
		if(p==null || x>=N || y>=N){
			return;
		}
		else{
			if(pieceAt(x,y) != null){
				remove(x,y);
			}
			pieces[x][y] = p;
		}

	}

	public Piece remove(int x, int y){
		if(x>=N || y>=N){
			System.out.println("Input out of bounds");
			return null;
		}
		else if(pieces[x][y] == null){
			System.out.println("Piece does not exist");
			return null;
		}
		else{
			Piece removed = pieces[x][y];
			pieces[x][y] = null;
			return removed;
		}
	}

	public boolean canEndTurn(){
		return playerHasMovedPiece;
	}

	public void endTurn(){
		if(canEndTurn()){
			fireTurn = ! fireTurn;
			playerSelectedPiece = false;
			playerHasMovedPiece = false;
			selectedPiece.doneCapturing();
			selectedPiece = null;
			playerDone = false;
		}
	}

	public String winner(){
		int fires = 0;
		int waters = 0;
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				Piece p = pieces[i][j];
				if (p != null) {
					if (p.isFire()) {
						fires += 1;
					}
					else{
						waters+=1;
					}
				}
			}
		}

		if(fires>0 && waters==0){
			return "Fire";
		}
		else if(fires==0 && waters>0){
			return "Water";
		}
		else if(fires==0 && waters==0){
			return "No One";
		}
		else{
			return null;
		}
	}

	private void drawBoard(int N){
		for(int i=0; i<N; i++){
			for(int j=0; j<N; j++){
				if ((i+j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				else StdDrawPlus.setPenColor(StdDrawPlus.RED);
				StdDrawPlus.filledSquare(i+.5, j+.5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				if (pieces[i][j] != null) {
					String img = "";
					if (pieces[i][j].isFire()){
						if (pieces[i][j].isBomb()){
							img = "img/bomb-fire.png";
						}
						else if (pieces[i][j].isShield()){
							img = "img/shield-fire.png";
						}
						else{
							img = "img/pawn-fire.png";
						}
					}
					else{
						if (pieces[i][j].isBomb()){
							img = "img/bomb-water.png";
						}
						else if (pieces[i][j].isShield()){
							img = "img/shield-water.png";
						}
						else{
							img = "img/pawn-water.png";
						}
					}

					if(pieces[i][j].isKing()){
						img = img.substring(0, img.indexOf(".")) + "-crowned.png";
					}

					StdDrawPlus.picture(i + .5, j + .5, img, 1, 1);
				}
			}
		}
	}

	public static void main(String[] args){
		Board b = new Board(false);
		b.drawBoard(b.N);
		//Check mouse presses.
		while(true) {
			b.drawBoard(b.N);
			if (StdDrawPlus.mousePressed()) {
				double xDouble = StdDrawPlus.mouseX();
				double yDouble = StdDrawPlus.mouseY();
				int x = (int) xDouble;
				int y = (int) yDouble;
				if(b.canSelect(x,y)){
					b.select(x,y);
				}
				System.out.println(x + ", " + y);
			}

			if(StdDrawPlus.isSpacePressed()){
				b.endTurn();
			}

			if(b.winner() != null){
				System.out.println(b.winner());
				break;
			}
			StdDrawPlus.show(100);
		}
	}
}