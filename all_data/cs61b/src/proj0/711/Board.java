public class Board{
	private boolean shouldBeEmpty;
	private int size = 8;
	private boolean fireTurn;			//fire's turn if true
	private boolean selected;
	private boolean moved;    			//only change when a piece moves but not captures
	private boolean [][] isPiece;  
	private Piece selectedPiece;
	private Piece [][] pieceAtXY;

	public Board(boolean shouldBeEmpty){
		this.shouldBeEmpty = shouldBeEmpty;
		isPiece = new boolean[size][size];
		pieceAtXY = new Piece[size][size];
		fireTurn = true;
		selected = false;
		moved = false;
		selectedPiece = null;
		if(!this.shouldBeEmpty)
			makeBoard();
	}

	private int getX(Piece p){
		for(int i=0; i<size; i++)
			for(int j=0; j<size; j++){
				if(pieceAtXY[i][j]==p)
					return i;
			}
		return -1;
	}

	private int getY(Piece p){
		for(int i=0; i<size; i++)
			for(int j=0; j<size; j++){
				if(pieceAtXY[i][j]==p)
					return j;
			}
		return -1;
	}

	private void makeBoard(){
		for(int i=0; i<size; i++)
			for(int j=0; j<size; j++){
				isPiece[i][j] = false;
				if((i+j)%2 == 0){					
					if(j==7){
						pieceAtXY[i][j] = new Piece(false, this, i, j, "pawn");
						isPiece[i][j] = true;
					}
					else if(j==6){
						pieceAtXY[i][j] = new Piece(false, this, i, j, "shield");
						isPiece[i][j] = true;
					}
					else if(j==5){
						pieceAtXY[i][j] = new Piece(false, this, i, j, "bomb");
						isPiece[i][j] = true;
					}
					else if(j==2){
						pieceAtXY[i][j] = new Piece(true, this, i, j, "bomb");
						isPiece[i][j] = true;
					}
					else if(j==1){
						pieceAtXY[i][j] = new Piece(true, this, i, j, "shield");
						isPiece[i][j] = true;
					}
					else if(j==0){
						pieceAtXY[i][j] = new Piece(true, this, i, j, "pawn");
						isPiece[i][j] = true;
					}
				}
			}
	}

	public Piece pieceAt(int x, int y){
		if(x > size-1 || x < 0 || y > size-1 || y < 0 || !isPiece[x][y])
			return null;
		else
			return pieceAtXY[x][y];
	}

	public boolean canSelect(int x, int y){
		 int xpos = -1;
		 int ypos = -1;
		if(selected){
			xpos = getX(selectedPiece);
			ypos = getY(selectedPiece);
		}
		//out of bounds
		if(x > size-1 || x < 0 || y > size-1 || y < 0)
			return false;
		//piece selection
		else if(fireTurn && isPiece[x][y] && pieceAtXY[x][y].isFire() && (!selected || (selected && !moved && !selectedPiece.hasCaptured())))  //only select fire during fires turn
			return true;
		else if(!fireTurn && isPiece[x][y] && !pieceAtXY[x][y].isFire() && (!selected || (selected && !moved && !selectedPiece.hasCaptured())))
			return true;
		//normal moves
		else if(selected && !moved && !isPiece[x][y] && !selectedPiece.hasCaptured() && (selectedPiece.isFire() || selectedPiece.isKing()) && y==ypos+1 && (x==xpos-1 || x==xpos+1))
			return true;
		else if(selected && !moved && !isPiece[x][y] && !selectedPiece.hasCaptured() && (!selectedPiece.isFire() || selectedPiece.isKing()) && y==ypos-1 && (x==xpos-1 || x==xpos+1))
			return true;
		//caputres
		else if(selected && !moved && !isPiece[x][y] && selectedPiece.isFire() && x==xpos-2 && y==ypos+2 && isPiece[xpos-1][ypos+1] && !pieceAtXY[xpos-1][ypos+1].isFire())
			return true;
		else if(selected && !moved && !isPiece[x][y] && selectedPiece.isFire() && x==xpos+2 && y==ypos+2 && isPiece[xpos+1][ypos+1] && !pieceAtXY[xpos+1][ypos+1].isFire())
			return true;
		else if(selected && !moved && !isPiece[x][y] && !selectedPiece.isFire() && x==xpos-2 && y==ypos-2 && isPiece[xpos-1][ypos-1] && pieceAtXY[xpos-1][ypos-1].isFire())
			return true;
		else if(selected && !moved && !isPiece[x][y] && !selectedPiece.isFire() && x==xpos+2 && y==ypos-2 && isPiece[xpos+1][ypos-1] && pieceAtXY[xpos+1][ypos-1].isFire())
			return true;
		//king captures- special cases/ opposite direction
		else if(selected && !moved && !isPiece[x][y] && selectedPiece.isFire() && selectedPiece.isKing() && x==xpos-2 && y==ypos-2 && isPiece[xpos-1][ypos-1] && !pieceAtXY[xpos-1][ypos-1].isFire())
			return true;
		else if(selected && !moved && !isPiece[x][y] && selectedPiece.isFire() && selectedPiece.isKing() && x==xpos+2 && y==ypos-2 && isPiece[xpos+1][ypos-1] && !pieceAtXY[xpos+1][ypos-1].isFire())
			return true;
		else if(selected && !moved && !isPiece[x][y] && !selectedPiece.isFire() && selectedPiece.isKing() && x==xpos-2 && y==ypos+2 && isPiece[xpos-1][ypos+1] && pieceAtXY[xpos-1][ypos+1].isFire())
			return true;
		else if(selected && !moved && !isPiece[x][y] && !selectedPiece.isFire() && selectedPiece.isKing() && x==xpos+2 && y==ypos+2 && isPiece[xpos+1][ypos+1] && pieceAtXY[xpos+1][ypos+1].isFire())
			return true;
		else
			return false;
	}

	/*public boolean validMove(int xi, int yi, int xf, int yf){
		if(isPiece[xi][yi] && (pieceAtXY[xi][yi].isFire() || pieceAtXY[xi][yi].isKing()) && yf==yi+1 && (xf==xi-1 || xf==xi+1) && !isPiece[xf][yf])
			return true;
		else if(isPiece[xi][yi] && (!pieceAtXY[xi][yi].isFire() || pieceAtXY[xi][yi].isKing()) && yf==yi-1 && (xf==xi-1 || xf==xi+1) && !isPiece[xf][yf])
			return true;
		else if(isPiece[xi][yi] && (pieceAtXY[xi][yi].isFire() || pieceAtXY[xi][yi].isKing()) && yf==yi+2 && xf==xi-2 && !isPiece[xf][yf] && isPiece[xi-1][yi+1] && !pieceAtXY[xi-1][yi+1].isFire())
			return true;
		else if(isPiece[xi][yi] && (pieceAtXY[xi][yi].isFire() || pieceAtXY[xi][yi].isKing()) && yf==yi+2 && xf==xi+2 && !isPiece[xf][yf] && isPiece[xi+1][yi+1] && !pieceAtXY[xi+1][yi+1].isFire())
			return true;
		else if(isPiece[xi][yi] && (!pieceAtXY[xi][yi].isFire() || pieceAtXY[xi][yi].isKing()) && yf==yi-2 && xf==xi-2 && !isPiece[xf][yf] && isPiece[xi-1][yi-1] && pieceAtXY[xi-1][yi-1].isFire())
			return true;
		else if(isPiece[xi][yi] && (!pieceAtXY[xi][yi].isFire() || pieceAtXY[xi][yi].isKing()) && yf==yi-2 && xf==xi+2 && !isPiece[xf][yf] && isPiece[xi+1][yi-1] && pieceAtXY[xi+1][yi-1].isFire())
			return true;
		else 
			return false;
	}*/

	public void select(int x, int y){
		if(!isPiece[x][y]){
			selectedPiece.move(x, y);
			if(!selectedPiece.hasCaptured() || selectedPiece.isBomb()){
				moved = true;
			}
		}
		else{
			selected = true;
			selectedPiece = pieceAt(x,y);
		}
	}

	public void place(Piece p, int x, int y){
		if(x > size-1 || x < 0 || y > size-1 || y < 0 || p==null)
			return;
		else{
			isPiece[x][y] = true;
			pieceAtXY[x][y] = p;
		}
	}

	public Piece remove(int x, int y){
		if(x > size-1 || x < 0 || y > size-1 || y < 0){
			System.out.println("Out of Bounds");
			return null;
		}
		else if(!isPiece[x][y]){
			System.out.println("No piece found at this location" + x + " " + y );
			return null;
		}
		else{					
			Piece temp=	pieceAtXY[x][y];
			pieceAtXY[x][y]=null;
			isPiece[x][y] = false;
			return temp;
		}
	} 

	public boolean canEndTurn(){
		if(moved || (selected && selectedPiece.hasCaptured()))
			return true;
		return false;
	}

	public void endTurn(){
		selected = false;
		moved = false;
		selectedPiece.doneCapturing();
		selectedPiece = null;
		if(fireTurn)
			fireTurn = false;
		else
			fireTurn = true;
	}

	public String winner(){
		int firepieces = 0;
		int waterpieces = 0;

		for(int i=0; i<size; i++)
			for(int j=0; j<size; j++){
				if(isPiece[i][j]){
					if(pieceAt(i, j).isFire())
						firepieces+=1;
					else
						waterpieces+=1;
				}
			}

		if(firepieces!=0 && waterpieces!=0)
			return null;
		else if(firepieces < waterpieces) 
			return "Water";
		else if(firepieces > waterpieces)
			return "Fire";
		else
			return "No one";
		}

	private void drawBoard(){
		for(int i=0; i<size; i++)
			for(int j=0; j<size; j++){
				if((i+j)%2==0){
					if(isPiece[i][j] && selected && getX(selectedPiece)==i && getY(selectedPiece)==j)
						StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					else
						StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				}
				else{
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}

				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE); 

				if(isPiece[i][j]){
					if(pieceAtXY[i][j].isFire() && !pieceAtXY[i][j].isKing() && !pieceAtXY[i][j].isBomb() && !pieceAtXY[i][j].isShield())
						StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
					else if(pieceAtXY[i][j].isFire() && !pieceAtXY[i][j].isKing() && pieceAtXY[i][j].isShield())
						StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
					else if(pieceAtXY[i][j].isFire() && !pieceAtXY[i][j].isKing() && pieceAtXY[i][j].isBomb())
						StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
					else if(!pieceAtXY[i][j].isFire() && !pieceAtXY[i][j].isKing() && !pieceAtXY[i][j].isBomb() && !pieceAtXY[i][j].isShield())
						StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
					else if(!pieceAtXY[i][j].isFire() && !pieceAtXY[i][j].isKing() && pieceAtXY[i][j].isShield())
						StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
					else if(!pieceAtXY[i][j].isFire() && !pieceAtXY[i][j].isKing() && pieceAtXY[i][j].isBomb())
						StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
					else if(pieceAtXY[i][j].isFire() && pieceAtXY[i][j].isKing() && !pieceAtXY[i][j].isBomb() && !pieceAtXY[i][j].isShield())
						StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
					else if(pieceAtXY[i][j].isFire() && pieceAtXY[i][j].isKing() && pieceAtXY[i][j].isShield())
						StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
					else if(pieceAtXY[i][j].isFire() && pieceAtXY[i][j].isKing() && pieceAtXY[i][j].isBomb())
						StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
					else if(!pieceAtXY[i][j].isFire() && pieceAtXY[i][j].isKing() && !pieceAtXY[i][j].isBomb() && !pieceAtXY[i][j].isShield())
						StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
					else if(!pieceAtXY[i][j].isFire() && pieceAtXY[i][j].isKing() && pieceAtXY[i][j].isShield())
						StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
					else if(!pieceAtXY[i][j].isFire() && pieceAtXY[i][j].isKing() && pieceAtXY[i][j].isBomb())
						StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
				}
			}
	}


	public static void main(String[] args) {
		Board b = new Board(false);
		int n = 8;
        StdDrawPlus.setXscale(0, n);
        StdDrawPlus.setYscale(0, n);
		// while(b.winner()==null){
		while(true){
			b.drawBoard();
			if(StdDrawPlus.mousePressed()){
				int x = (int) StdDrawPlus.mouseX();
				int y = (int) StdDrawPlus.mouseY();

				if(b.canSelect(x, y)){    //pieceAt
					b.select(x, y);
					StdDrawPlus.filledSquare(x + .5, y + .5, .5);
				}
				// else if(b.canSelect( x, y) && b.pieceAt(x, y)==null && b.selected){  //pieceAt
				// 	b.selectedPiece.move(x, y);
				// 	if(!b.selectedPiece.hasCaptured() || b.selectedPiece.isBomb()){
				// 		b.moved = true;
				// 	}	
				// }
			}

			if(StdDrawPlus.isSpacePressed()){
				if(b.canEndTurn())
					b.endTurn();
			}
			StdDrawPlus.show(10);
		}
		//System.out.println(b.winner() + " wins!!");
	}
}