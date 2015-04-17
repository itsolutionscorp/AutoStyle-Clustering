public class Board {
	private static int boardSize=8;
	private Piece[][] pieces= new Piece[boardSize][boardSize];
	private static int numberPieces = 24;
	private boolean fireTurn= true;
	private int numberTurns=0;
	private boolean hasSelected=false;
	private Piece selectedPiece=null;
	private boolean hasMoved=false;
	// private boolean[][] clicks= new boolean[boardSize][boardSize];
	// private int firePieces=11;
	// private int waterPieces=11;

	public static void main(String args[]) {
		Board b= new Board(Boolean.parseBoolean(args[0]));
			StdDrawPlus.setXscale(0,boardSize);
			StdDrawPlus.setYscale(0,boardSize);
			b.drawBoard(boardSize);
			while (true) {
				b.redrawPieces();
				if (b.winner()!= null) {
                		b.winner();
                	}
				if (StdDrawPlus.mousePressed()) {
                	double x = StdDrawPlus.mouseX();
                	double y = StdDrawPlus.mouseY();
                	int xint= (int)x;
                	int yint=(int)y;
                	if (b.canSelect(xint, yint)) {
                		b.select(xint, yint);
                	}
                }
                else if (StdDrawPlus.isSpacePressed()) {
                	if (b.canEndTurn()) {
                		b.endTurn();
                	}
                	
                }
            	StdDrawPlus.show(100);
            }
	}
	public Board(boolean shouldBeEmpty){
		if (!shouldBeEmpty){
			makePieces();
		}
	}
	private static void drawBoard(int boardSize) {
		for (int i=0; i<boardSize; i+=1) {
			for (int j=0; j<boardSize; j+=1) {
				if ((i+j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
				}
				else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
					StdDrawPlus.filledSquare(i+0.5, j+0.5, 0.5);
				}
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			}
		}
	}
	private void makePieces() {
		int l=numberPieces; 
		int n=this.boardSize;
		
		for (int i = 0; i < (l); i+=1) {
			for (int j = 0; j < n; j+=1) {
				for (int k = 0; k < n; k+=1) {
					if (i < (l/6) && (j+k) % 2 == 0 && k==0) {
						this.pieces[j][k]= new Piece(true, this, j, k, "pawn" );
						// StdDrawPlus.picture(j + 0.5, k + 0.5, "img/pawn-fire.png", 1, 1);
					}
					if (i >= (l/6) && i < (l/3) && (j+k) % 2 == 0 && k==1) {
						this.pieces[j][k]= new Piece(true, this, j, k, "shield" );
						// StdDrawPlus.picture(j + 0.5, k + 0.5, "img/shield-fire.png", 1, 1);
					}
					if (i >= (l/3) && i < (l/2) && (j+k) % 2 == 0 && k==2){
						this.pieces[j][k]= new Piece(true, this, j, k, "bomb" );
						// StdDrawPlus.picture(j + 0.5, k + 0.5, "img/bomb-fire.png", 1, 1);
					}
					if (i >= (l/2) && i < (2*l/3) && (j+k) % 2 == 0 && k==5){
						this.pieces[j][k]= new Piece(false, this, j, k, "bomb" );
						// StdDrawPlus.picture(j + 0.5, k + 0.5, "img/bomb-water.png", 1, 1);
					}
					if (i >= (2*l/3) && i < (5*l/6) && (j+k) % 2 == 0 && k==6){
						this.pieces[j][k]= new Piece(false, this, j, k, "shield" );
						// StdDrawPlus.picture(j + 0.5, k + 0.5, "img/shield-water.png", 1, 1);
					}
					if (i >= (5*l/6) && (j+k) % 2 == 0 && k==7 ){
						this.pieces[j][k]= new Piece(false, this, j, k, "pawn" );
						// StdDrawPlus.picture(j + 0.5, k + 0.5, "img/pawn-water.png", 1, 1);
					}

				}
			}	
		}
	} 
	private void redrawPieces() {
		for (int i = 0; i < boardSize; i+=1) {
			for (int j = 0; j < boardSize; j+=1) {
				Piece p= pieceAt(i,j);
				if ((i+j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
				}
				else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
					StdDrawPlus.filledSquare(i+0.5, j+0.5, 0.5);
				}
				if (p==selectedPiece) {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
				}
				if (p!=null) {
					if (p.isFire()) {
						if (p.isKing()) {
							if (p.isShield()) {
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire-crowned.png", 1, 1);
							}
							else if (p.isBomb()){
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire-crowned.png", 1, 1);
							}
							else{
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire-crowned.png", 1, 1);
							}
						}
						else {
							if (p.isShield()) {
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire.png", 1, 1);
							}
							else if (p.isBomb()){
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire.png", 1, 1);
							}
							else{
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire.png", 1, 1);
							}
						}
					}
					else {
						if (p.isKing()){
							if (p.isShield()) {
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water-crowned.png", 1, 1);

							}
							else if (p.isBomb()){
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water-crowned.png", 1, 1);
							}
							else{
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water-crowned.png", 1, 1);
							}
						}
						else{
							if (p.isShield()) {
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water.png", 1, 1);
							}
							else if (p.isBomb()){
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water.png", 1, 1);
							}
							else{
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water.png", 1, 1);
							}
						}
					}
				}
				else {
					if ((i+j) % 2 == 0) {
						StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
						StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
					}
					else {
						StdDrawPlus.setPenColor(StdDrawPlus.RED);
						StdDrawPlus.filledSquare(i+0.5, j+0.5, 0.5);
					}

				}
			}
		}
	}

	public Piece pieceAt(int x, int y) {
		if (onBoard(x, y)) {
			return pieces[x][y];
		}
		else {return null;}
	}

	private int[] pieceLocation(Piece p) {
		for (int i=0; i<boardSize; i+=1) {
			for (int j=0; j<boardSize; j+=1) {
				if (pieces[i][j]==p) {
					int[] a= new int[] {i,j};
					return a;
				}
			}
		}
		return new int[]{-1,-1};
	}

	public void place(Piece p, int x, int y){
	 	if (onBoard(x,y) && p!=null) {
	 		this.pieces[x][y]=null;
	 		this.pieces[x][y]=p;
	 	}
	}
	public boolean canSelect(int x, int y) {
		Piece p=pieceAt(x,y);
		if (p!=null) {
			if ((p.isFire() && fireTurn) || ((!p.isFire()) && (!fireTurn))){
				if (hasSelected) {
					if (hasMoved) {
					return false;
					}
					else {return true;}
				}
				else {return true;}
			}
			else {return false;}
		}
		else {
			if (hasSelected) {
				int[] selectedPL= pieceLocation(selectedPiece);
				if (!hasMoved){
					if (validMove(selectedPL[0], selectedPL[1], x, y) ) {
						return true;
					}
					else {return false;}
				}
				else {
					if (selectedPiece.hasCaptured() && validMove(selectedPL[0], selectedPL[1], x, y) && Math.abs(x-selectedPL[0])==2 && Math.abs(y-selectedPL[1])==2) {
						return true;
					}
					else {return false;}
				}
			}
			else {return false;}
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (onBoard(xi, yi) && onBoard(xf, yf)){
			Piece p0 = pieceAt(xi, yi);
			Piece p1 = pieceAt(xf, yf);
			if (p1==null) {
				if ((xf+yf) % 2 == 0) {
					if (p0.isKing()) {
						if (Math.abs(yf-yi)==1 && Math.abs(xf-xi)==1) {
							return true;
						}
						else if (yf-yi==2 && Math.abs(xf-xi)==2) {
							if (xf<xi){
								Piece p2= pieceAt(xi-1, yi+1);
								if (p2!= null) {
									if (p0.isFire() ^ p2.isFire()) {
										return true;
									}
									else {return false;}
								}
								else {return false;}
							}
							else {
								Piece p2= pieceAt(xi+1, yi+1);
								if (p2 != null) {
									if (p0.isFire() ^ p2.isFire()) {
										return true;
									}
									else {return false;}
								}
								else {return false;}
							}
						}
						else if (yf-yi==-2 && Math.abs(xf-xi)==2) {
							if (xf<xi){
								Piece p2= pieceAt(xi-1, yi-1);
								if (p2!= null) {
									if (p0.isFire() ^ p2.isFire()) {
										return true;
									}
									else {return false;}
								}
								else {return false;}
							}
							else {
								Piece p2= pieceAt(xi+1, yi-1);
								if (p2 != null) {
									if (p0.isFire() ^ p2.isFire()) {
										return true;
									}
									else {return false;}
								}
								else {return false;}
							}
						}
						else {return false;}
					}
					else if (p0.isFire()) {
						if ((yf-yi)==1 && Math.abs(xf-xi)==1) {
							return true;
						}
						else if ((yf-yi)==2 && Math.abs(xf-xi)==2) {
							if (xf<xi){
								Piece p2= pieceAt(xi-1, yi+1);
								if (p2!= null) {
									if (!p2.isFire()) {
										return true;
									}
									else {return false;}
								}
								else {return false;}
							}
							else {
								Piece p2= pieceAt(xi+1, yi+1);
								if (p2 != null) {
									if (!p2.isFire()) {
										return true;
									}
									else {return false;}
								}
								else {return false;}
							}
						}
						else {return false;}
					}
					else {
						if ((yi-yf)==1 && Math.abs(xf-xi)==1) {
							return true;
						}
						else if ((yi-yf)==2 && Math.abs(xf-xi)==2) {
							if (xf<xi){
								Piece p2= pieceAt(xi-1, yi-1);
								if (p2!= null) {
									if (p2.isFire()) {
										return true;
									}
									else {return false;}
								}
								else {return false;}
							}
							else {
								Piece p2= pieceAt(xi+1, yi-1);
								if (p2 != null) {
									if (p2.isFire()) {
										return true;
									}
									else {return false;}
								}
								else {return false;}
							}
						}
						else {return false;}

					}
				}
				else {return false;}
			}
			else {return false;}
		}
		else {return false;}
	}

	public void select(int x, int y) {
		
		if (!hasSelected) {
			selectedPiece=pieceAt(x,y);
			hasSelected=true;
		}
		else if(pieceAt(x,y)!=null) {
			selectedPiece=pieceAt(x,y);
		}
		else {
			selectedPiece.move(x,y);
			hasMoved=true;
			
		}
	}

	public Piece remove(int x, int y) {
		if (onBoard(x,y)) {
			Piece p=pieceAt(x,y);
			pieces[x][y]=null;
			return p;
		}
		else {return null;}
	}
	public boolean canEndTurn() {
		if (hasMoved || (selectedPiece!=null && selectedPiece.hasCaptured())) {
			return true;
		}
		else {return false;}
	}
	public void endTurn() {
		hasSelected=false;
		hasMoved=false;
		selectedPiece.doneCapturing();
		selectedPiece=null;
		fireTurn=!fireTurn;
		numberTurns=numberTurns+1;
		// for (int i=0; i<boardSize ; i+=1 ) {
		// 	for (int j=0; j<boardSize ; j+=1 ) {
		// 		clicks[i][j]=false;
		// 	}
		// }
	}

	public String winner() {
		int firePieces=0;
		int waterPieces=0;
		for (int i=0; i<boardSize ; i+=1 ) {
			for (int j=0; j<boardSize ; j+=1) {
				if (pieceAt(i,j)!=null){
					if (pieceAt(i,j).isFire()){
						firePieces=firePieces+1;
					}
					else{
						waterPieces=waterPieces+1;
					}
				}
			}
		}
		if (firePieces==0 && waterPieces!=0) {
			return "Water";
		}
		else if (waterPieces==0 && firePieces!=0) {
			return "Fire";
		}
		else if (waterPieces==0 && firePieces==0) {
			return "No One";
		}
		else {return null;}
	}

	private boolean onBoard(int x, int y) {
		if (x<this.boardSize && y< this.boardSize && x>=0 &&y>=0) {
			return true;
		}
		else {return false;}

	}


	// private static void drawPieces(Piece[] pieces) {
	// 	for (int i=0, i < length.pieces; i+=1){
	// 		String type= pieces[i].type;
	// 		if (pieces[i],isFire()){
	// 			if (pieces[i].isKing()){

	// 			}
	// 			else {
	// 				switch (type) {
	// 					case "pawn":
							



	// 				}
	// 			}
	// 		}
	// 		else{

	// 		}

			 
	// 	}
	// }


}