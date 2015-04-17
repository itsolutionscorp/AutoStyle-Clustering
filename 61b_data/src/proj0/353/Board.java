public class Board{
	private boolean shouldBeEmpty, hasSelectPiece, hasMoved, isFirePlayer;
	private int selectedPiecePosX, selectedPiecePosY;
	private boolean tiles[][];
	private Piece[][] checkersPiece;
	private int xMax;
	private int yMax;
	private int countFire, countWater;

	public static void main(String[] args){
		Board playBoard = new Board(false);
		String winner;
		int N = 8;
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);
		System.out.println("Fire Player Turn");
		while(true){
			playBoard.drawBoard();
			playBoard.drawCheckersPiece();
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (playBoard.canSelect((int)x,(int)y)){
                	playBoard.tiles[playBoard.selectedPiecePosX][playBoard.selectedPiecePosY] = false;
                	playBoard.tiles[(int)x][(int)y] = true;
                	playBoard.select((int)x, (int)y);
                	if (playBoard.pieceAt(playBoard.selectedPiecePosX, playBoard.selectedPiecePosY) == null)
                		playBoard.tiles[playBoard.selectedPiecePosX][playBoard.selectedPiecePosY] = false;
                }  
            }
            if (StdDrawPlus.isSpacePressed()){
            	playBoard.tiles[playBoard.selectedPiecePosX][playBoard.selectedPiecePosY] = false;
            	playBoard.endTurn();
            	if (playBoard.isFirePlayer)
            		System.out.println("Fire Player Turn");
            	else
            		System.out.println("Water Player Turn");
            }
            winner = playBoard.winner();
        	if (winner != null){
            	System.out.println(winner);
            	return;
       		}
            StdDrawPlus.show(100);     
		}
	}

	private void drawBoard(){
		for (int i = 0; i < xMax; i += 1) {
			for (int j = 0; j < yMax; j += 1) {
				if (tiles[i][j])
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				else if ((i + j) % 2 == 0)
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				else
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
			}
		}
	}

	private void drawCheckersPiece(){
		for (int i = 0; i < xMax; i += 1){
			for (int j = 0; j < yMax; j += 1){
				if (pieceAt(i,j) != null){
					if (pieceAt(i,j).isFire()){
						if(pieceAt(i,j).isKing()){
							if(pieceAt(i,j).isShield())
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire-crowned.png",1 ,1);
							else if (pieceAt(i,j).isBomb())
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire-crowned.png",1 ,1);
							else
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire-crowned.png",1 ,1);
						}
						else{
							if(pieceAt(i,j).isShield())
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire.png",1 ,1);
							else if (pieceAt(i,j).isBomb())
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire.png",1 ,1);
							else
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire.png",1 ,1);
						}
					}
					else{
						if(pieceAt(i,j).isKing()){
							if(pieceAt(i,j).isShield())
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water-crowned.png",1 ,1);
							else if (pieceAt(i,j).isBomb())
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water-crowned.png",1 ,1);
							else
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water-crowned.png",1 ,1);
						}
						else{
							if(pieceAt(i,j).isShield())
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water.png",1 ,1);
							else if (pieceAt(i,j).isBomb())
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water.png",1 ,1);
							else
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water.png",1 ,1);
						}
					}
				}
			}
		}
	}
		

	private boolean isValidPos(int x, int y){
		if (x >= xMax || y >= yMax || x < 0 || y < 0)
			return false;
		return true;
	}

	public Board(boolean shouldBeEmpty){
		this.shouldBeEmpty = shouldBeEmpty;
		this.xMax = 8;
		this.yMax = 8;
		this.isFirePlayer = true;
		this.hasSelectPiece = false;
		this.checkersPiece = new Piece[xMax][yMax];
		this.tiles = new boolean[xMax][yMax]; //default value for boolean is false;
		this.hasMoved = false;
		this.selectedPiecePosX = 0;
		this.selectedPiecePosY = 0;
		if (!shouldBeEmpty)
			initializeCheckers();
	}

	private void initializeCheckers(){
		for (int i = 0; i < xMax; i += 1){
			for (int j = 0; j < yMax; j += 1){
				if ((i + j) % 2 == 0){
					if (j == 0){
						countFire += 1;
						checkersPiece[i][j] = new Piece(true, this, i, j, "pawn");
					}
					else if (j == 1){
						countFire += 1;
						checkersPiece[i][j] = new Piece(true, this, i, j, "shield");
					}	
					else if (j == 2){
						countFire += 1;
						checkersPiece[i][j] = new Piece(true, this, i, j, "bomb");
					}
					else if (j == 5){
						countWater += 1;
						checkersPiece[i][j] = new Piece(false, this, i, j, "bomb");
					}
					else if (j == 6){
						countWater += 1;
						checkersPiece[i][j] = new Piece(false, this, i, j, "shield");
					}
					else if (j == 7){
						countWater += 1;
						checkersPiece[i][j] = new Piece(false, this, i, j, "pawn");
					}
				}
			}
		}
	}

	private boolean isEmpty(){
		return !this.shouldBeEmpty;
	} 

	public Piece pieceAt(int x, int y){
		if (!isValidPos(x,y))
			return null;
		return checkersPiece[x][y];
	}

	public boolean canSelect(int x, int y){
		Piece comparator = this.pieceAt(x,y);
		//select an empty field after selecting a piece
		if (comparator == null){
			//During this turn, the player has selected a Piece which hasn’t moved yet and is 
			//selecting an empty spot which is a valid move for the previously selected Piece.
			if (hasSelectPiece){
				if (validMove(selectedPiecePosX, selectedPiecePosY, x,y))
					return true;
				return false;
			}
			return false;
		}
		if ((!hasSelectPiece || (hasSelectPiece && !hasMoved)) && comparator.isFire() == isFirePlayer)
			return true;
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf){
		Piece destination = pieceAt(xf,yf);
		Piece initial = pieceAt(xi,yi);
		int middlePosX = (int) ((xf + xi) / 2);
		int middlePosY = (int) ((yi + yf) / 2);
		Piece middlePiece = pieceAt(middlePosX, middlePosY);
		if (initial == null){
			return false;
		}
		if (destination != null){
			return false;
		}
		else if (initial.isFire()){
			if (initial.isKing()){
				if (!hasMoved && 
					( (xf == (xi + 1) && yf == (yi - 1)) || (xf == (xi - 1) && yf == (yi - 1) ) ) )
					return true;
				else if (!hasMoved && 
					( (xf == (xi + 1) && yf == (yi + 1)) || (xf == (xi - 1) && yf == (yi + 1) ) ) ){
					return true;
				}
				else if ( middlePiece != initial &&  
					(!hasMoved || (hasMoved && initial.hasCaptured()) ) &&
					middlePiece != null && middlePiece.isFire() == !initial.isFire() && 
					( (xf == (xi + 2) && yf == (yi - 2)) || (xf == (xi - 2) && yf == (yi - 2)) ) )
					return true;
				else if ( middlePiece != initial &&  
					(!hasMoved || (hasMoved && initial.hasCaptured()) ) &&
					middlePiece != null && middlePiece.isFire() == !initial.isFire() && 
					( (xf == (xi + 2) && yf == (yi + 2) ) || (xf == (xi - 2) && yf == (yi + 2) ) ) ){
					return true;
				}
				return false;
			}
			else if (!hasMoved && 
				( (xf == (xi + 1) && yf == (yi + 1)) || (xf == (xi - 1) && yf == (yi + 1) ) ) ){
				return true;
			}
			else if ( middlePiece != initial &&  
				(!hasMoved || (hasMoved && initial.hasCaptured()) ) &&
				middlePiece != null && middlePiece.isFire() == !initial.isFire() && 
				( (xf == (xi + 2) && yf == (yi + 2) ) || (xf == (xi - 2) && yf == (yi + 2) ) ) ){
				return true;
			}
			return false;
		}
		else{
			if (initial.isKing()){
				if (!hasMoved && 
					( (xf == (xi + 1) && yf == (yi - 1)) || (xf == (xi - 1) && yf == (yi - 1) ) ) )
					return true;
				else if (!hasMoved && 
					( (xf == (xi + 1) && yf == (yi + 1)) || (xf == (xi - 1) && yf == (yi + 1) ) ) ){
					return true;
				}
				else if ( middlePiece != initial &&  
					(!hasMoved || (hasMoved && initial.hasCaptured()) ) &&
					middlePiece != null && middlePiece.isFire() == !initial.isFire() && 
					( (xf == (xi + 2) && yf == (yi - 2)) || (xf == (xi - 2) && yf == (yi - 2)) ) )
					return true;
				else if ( middlePiece != initial &&  
					(!hasMoved || (hasMoved && initial.hasCaptured()) ) &&
					middlePiece != null && middlePiece.isFire() == !initial.isFire() && 
					( (xf == (xi + 2) && yf == (yi + 2) ) || (xf == (xi - 2) && yf == (yi + 2) ) ) ){
					return true;
				}
				return false;
			}
			else if (!hasMoved && 
				( (xf == (xi + 1) && yf == (yi - 1)) || (xf == (xi - 1) && yf == (yi - 1) ) ) ){
				return true;
			}
			else if ( middlePiece != initial &&  
				(!hasMoved || (hasMoved && initial.hasCaptured()) ) &&
				middlePiece != null && middlePiece.isFire() == !initial.isFire() && 
				( (xf == (xi + 2) && yf == (yi - 2) ) || (xf == (xi - 2) && yf == (yi - 2) ) ) ){
				return true;
			}
			return false;
		}
	}

	public void select(int x, int y){
		Piece comparator = pieceAt(x,y);
		if (comparator != null){
			hasSelectPiece = true;
			selectedPiecePosX = x;
			selectedPiecePosY = y;
		}
		else if (comparator == null){
			pieceAt(selectedPiecePosX,selectedPiecePosY).move(x,y);
			hasMoved = true;
			selectedPiecePosX = x;
			selectedPiecePosY = y;
		}
	}

	public void place(Piece p, int x, int y){
		if (!isValidPos(x,y) || p == null)
			return;
		if (pieceAt(x,y) != null)
			remove(x,y);
		if (p.isFire())
			countFire += 1;
		else
			countWater += 1;
		checkersPiece[x][y] = p;	
	}

	public Piece remove(int x, int y){
		if (!isValidPos(x,y)){
			System.out.println("Cannot remove checkers piece from out of bounds position");
			return null;
		}
		Piece removedPiece = pieceAt(x,y);
		if (removedPiece == null){
			System.out.println("There is no piece in this position");
			return null;
		}
		if (removedPiece.isFire())
			countFire -= 1;
		else
			countWater -= 1;
		checkersPiece[x][y] = null;
		return removedPiece;
	}

	public boolean canEndTurn(){
		if (hasMoved)
			return true;
		return false;
	}

	public void endTurn(){
		if (!canEndTurn()){
			System.out.println("Cannot end turn, must have at least moved a piece");
			return;
		}
		hasSelectPiece = false;
		hasMoved = false;
		isFirePlayer = !isFirePlayer;
		Piece selectedPiece = pieceAt(selectedPiecePosX, selectedPiecePosY);
		if (selectedPiece != null)
			selectedPiece.doneCapturing();
		selectedPiecePosX = 0;
		selectedPiecePosY = 0;

	}

	public String winner(){
		if (countFire == 0 && countWater == 0)
			return "No one";
		else if (countFire == 0)
			return "Water";
		else if (countWater == 0)
			return "Fire";
		return null;
	}
}