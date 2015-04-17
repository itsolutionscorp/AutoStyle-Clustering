public class Board{
	private int N = 8;
	private Piece[][] pieces = new Piece[N][N];
	private boolean didYouSelect;
	private boolean didYouMove;
	private int selectedX;
	private int selectedY;
	private boolean canYouEnd;
	private boolean fireTurn;

	
	private void drawBoard(int N){
		for (int i = 0; i < N; i +=1){
			for (int j = 0; j < N; j +=1){
				if ((i+j) % 2 == 0){
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				}
				else{
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				if (pieces[i][j] != null){
					if(pieces[i][j].isFire()){
						if(pieces[i][j].isBomb()){
							StdDrawPlus.picture(i+0.5, j+0.5, "img/bomb-fire.png", 1, 1);
							if(pieces[i][j].isKing()){
								StdDrawPlus.picture(i+0.5, j+0.5, "img/bomb-fire-crowned.png", 1, 1);
							}
						}
						else if(pieces[i][j].isShield()){
							StdDrawPlus.picture(i+0.5, j+0.5, "img/shield-fire.png", 1, 1);
							if(pieces[i][j].isKing()){
								StdDrawPlus.picture(i+0.5, j+0.5, "img/shield-fire-crowned.png", 1, 1);
							}
						}
						else{
							StdDrawPlus.picture(i+0.5, j+0.5, "img/pawn-fire.png", 1, 1);
							if(pieces[i][j].isKing()){
								StdDrawPlus.picture(i+0.5, j+0.5, "img/pawn-fire-crowned.png", 1, 1);
						}
					}
					}
					else{
						if(pieces[i][j].isBomb()){
							StdDrawPlus.picture(i+0.5, j+0.5, "img/bomb-water.png", 1, 1);
							if(pieces[i][j].isKing()){
								StdDrawPlus.picture(i+0.5, j+0.5, "img/bomb-water-crowned.png", 1, 1);
							}
						}
						else if(pieces[i][j].isShield()){
							StdDrawPlus.picture(i+0.5, j+0.5, "img/shield-water.png", 1, 1);
							if(pieces[i][j].isKing()){
								StdDrawPlus.picture(i+0.5, j+0.5, "img/shield-water-crowned.png", 1, 1);
							}
						}
						else{
							StdDrawPlus.picture(i+0.5, j+0.5, "img/pawn-water.png", 1, 1);
							if(pieces[i][j].isKing()){
								StdDrawPlus.picture(i+0.5, j+0.5, "img/pawn-water-crowned.png", 1, 1);
							}
						}
						
					}
					
				
			}
			}
		}
	
}	
	
	
	public static void main(String[] args){
		int N = 8;
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);
		Board b = new Board(false);
		while(true){
			b.drawBoard(N);
			if (StdDrawPlus.mousePressed()){
				double x = StdDrawPlus.mouseX();
				double y = StdDrawPlus.mouseY();
				if (b.canSelect((int) x, (int) y)){
					b.select((int)x, (int)y);
				}
			}
			if (StdDrawPlus.isSpacePressed()){
				if (b.canEndTurn()){
					b.endTurn();
				}
			}
			StdDrawPlus.show(20);
			
		}
	}
	
	public Board(boolean shouldBeEmpty){
		didYouSelect = false;
		didYouMove = false;
		canYouEnd = false;
		fireTurn = true;
		if (!shouldBeEmpty){
			for(int x = 0; x < N; x+=2){
				pieces[x][0] = new Piece(true, this, x, 0, "pawn");
				pieces[x][2] = new Piece(true, this, x, 2, "bomb");
				pieces[x][6] = new Piece(false, this, x, 6, "shield");
			}
			for(int x = 1; x < N; x+=2){
				pieces[x][1] = new Piece(true, this, x, 1, "shield");
				pieces[x][5] = new Piece(false, this, x, 5, "bomb");
				pieces[x][7] = new Piece(false, this, x, 7, "pawn");
			}
		}
	}
	
	public Piece pieceAt(int x, int y){
		if(x>=N || y>=N || x < 0 || y < 0){
			return null;
		}
		return pieces[x][y];
	}
	
	public void place(Piece p, int x, int y){
		if(x < N && y < N){
			pieces[x][y] = p;	
		}
	}
	
	public Piece remove(int x, int y){
		if (x >= N || y >= N || x < 0 || y < 0){
			System.out.println("Piece out of bounds");
			return null;
		}
		if (pieceAt(x, y) == null){
			System.out.println("No piece here");
			return null;
		}
		Piece removedPiece = pieces[x][y];
		pieces[x][y] = null;
		return removedPiece;
	}
	
	
	private boolean validMove(int xi, int yi, int xf, int yf){
		//Checks if destination is on board.//
		if (xf >= N || yf >= N || xf < 0 || yf < 0){
			return false;
		}
		//Checks if selected piece is on board.//
		else if (xi >= N || yi >= N || xi < 0 || yi < 0){
			return false;
		}
		//This checks for if I want to try to move 1 space//
		else if (((Math.abs(yf - yi)) == 1) && ((Math.abs(xf - xi)) == 1)){
			//This checks if there is a fire piece.//
			if ((this.pieceAt(xi, yi) != null) && (this.pieceAt(xi, yi).isFire())){
				//This checks if the spot is going up and there is no piece at the selected destination.//
				if(((yf-yi) == 1) && (this.pieceAt(xf, yf) == null)){
					return true;
				}
			}
			//This checks if there is a water piece.//
			if ((this.pieceAt(xi, yi)!= null) && (!this.pieceAt(xi, yi).isFire())){
				//This checks if the destination is empty and the spot is going down.//
				if(((yf - yi) == -1) && (this.pieceAt(xf, yf) == null)){
					return true;
				}
			}
			//This checks if the piece is a king, meaning it can go up and down//
			if ((this.pieceAt(xi, yi)!= null) && (this.pieceAt(xi, yi).isKing())){
				//This checks if the destination is empty.// 
				if((this.pieceAt(xf, yf)) == null){
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean validCaptureMove(int xi, int yi, int xf, int yf){
		int plusXi = xi + 1;
		int plusYi = yi + 1;
		int minusXi = xi - 1;
		int minusYi = yi - 1;
		//This checks for valid destination coordinates//
		if (xf >= N || yf >= N || xf < 0 || yf < 0){
			return false;
		}
		//This checks for valid starting point//
		else if (xi >= N || yi >= N || xi < 0 || yi < 0){
			return false;
		}
		//This checks if the resulting destination is two spots diagonally away.//
		else if (((Math.abs(yf - yi)) == 2) && ((Math.abs(xf - xi)) == 2)){
			//This checks if the piece is there and it's a fire piece.//
			if ((this.pieceAt(xi, yi)) != null && (this.pieceAt(xi, yi).isFire())){
				//This checks if it is going up and the destination is empty.//
				if (((yf - yi) == 2) && (this.pieceAt(xf, yf) == null)){
					//This checks if there is a piece to capture.//
					if (xf - xi == 2){
						if (this.pieceAt((plusXi), (plusYi)) != null){
							//This checks if the piece about to be captured is a water piece.//
							if (!this.pieceAt((plusXi), (plusYi)).isFire()){
								return true;
							}
						}
					}
					else{
						if (this.pieceAt(minusXi, plusYi) != null){
							if (!this.pieceAt(minusXi, plusYi).isFire()){
								return true;
							}
						}
					}
				}
				//This checks if the fire piece is a king and the destination is empty.//
				if (this.pieceAt(xi, yi).isKing() && this.pieceAt(xf, yf) == null){
					//This checks if there is a piece to capture going up.//
					if (xf - xi == 2){
						if (this.pieceAt((plusXi), (plusYi)) != null){
							//This checks if the piece about to be captured is a water piece.//
							if (!this.pieceAt((plusXi), (plusYi)).isFire()){
								return true;
							}
						}
						//This checks if there is a piece to capture going down.//
						if (this.pieceAt((plusXi), (minusYi)) != null){
							//This checks if the piece about to be captured is a water piece.//
							if (!this.pieceAt((plusXi), (minusYi)).isFire()){
								return true;
							}
						}
					}
					else{
						if (this.pieceAt(minusXi, plusYi) != null){
							if (!this.pieceAt(minusXi, plusYi).isFire()){
								return true;
							}
						}
						if (this.pieceAt(minusXi, minusYi)!= null){
							if (!this.pieceAt(minusXi, minusYi).isFire()){
								return true;
							}
						}
					}
				}
			}
			//This checks if the piece is there and is a water piece.//
			else if ((this.pieceAt(xi, yi)) != null && (!this.pieceAt(xi, yi).isFire())){
				//This checks if the destination is empty and is going down.//
				if (((yf - yi) == -2) && (this.pieceAt(xf, yf) == null)){
					if (xf - xi == 2){
						if (this.pieceAt(plusXi, minusYi) != null){
							if(this.pieceAt(plusXi, minusYi).isFire()){
								return true;
							}
						}
					}
					else{
						if (this.pieceAt(minusXi, minusYi) != null){
							if (this.pieceAt(minusXi, minusYi).isFire()){
								return true;
							}
						}
					}
				}
				//This checks if the water piece is king and the destination is empty.//
				if (this.pieceAt(xi, yi).isKing() && this.pieceAt(xf, yf) == null){
					//This checks to see if there is a piece to be captured going down.//
					if (xf - xi == 2){
						if (this.pieceAt(plusXi, minusYi) != null){
							//This checks if the piece about to be captured is fire.//
							if(this.pieceAt(plusXi, minusYi).isFire()){
								return true;
							}
						}
						//This checks to see if there is a piece to be captured going up.//
						if (this.pieceAt(plusXi, plusYi) != null){
							//This checks to see if the piece to be captured is fire.//
							if (this.pieceAt(plusXi, plusYi).isFire()){
								return true;
							}
						}
					}
					else{
						if (this.pieceAt(minusXi, minusYi) != null){
							if (this.pieceAt(minusXi, minusYi).isFire()){
								return true;
							}
						}
						if (this.pieceAt(minusXi, plusYi) != null){
							if (this.pieceAt(minusXi, plusYi).isFire()){
								return true;
							}
						}
					}
				}
			}	
		}
		return false;
	}
	
	
	public boolean canSelect(int x, int y){
		if (pieceAt(x, y) != null){
			if (pieceAt(x, y).isFire() && fireTurn){
				if (!didYouSelect){
					return true;
				}
				else if (!didYouMove){
					return true;
				}
			}
			if (!pieceAt(x, y).isFire() && !fireTurn){
				if (!didYouSelect){
					return true;
				}
				else if (!didYouMove){
					return true;
				}
			}
		}
		else if (pieceAt(x, y) == null){
			if (pieceAt(selectedX, selectedY) != null && pieceAt(selectedX, selectedY).isFire() && fireTurn){
				if (didYouSelect && !didYouMove && this.pieceAt(selectedX, selectedY) != null && (this.validMove(selectedX, selectedY, x, y) || this.validCaptureMove(selectedX, selectedY, x, y))){
					return true;
				}
				else if (didYouSelect && this.pieceAt(selectedX, selectedY)!= null && this.pieceAt(selectedX, selectedY).hasCaptured() && this.validCaptureMove(selectedX, selectedY, x, y)){
					return true;			
				}
			}
			if (pieceAt(selectedX, selectedY)!= null && !pieceAt(selectedX, selectedY).isFire() && !fireTurn){
				if (didYouSelect && !didYouMove && this.pieceAt(selectedX, selectedY) != null && (this.validMove(selectedX, selectedY, x, y) || this.validCaptureMove(selectedX, selectedY, x, y))){
					return true;
				}
				else if (didYouSelect && this.pieceAt(selectedX, selectedY)!= null && this.pieceAt(selectedX, selectedY).hasCaptured() && this.validCaptureMove(selectedX, selectedY, x, y)){
					return true;			
				}
			}
		}
		return false;
	}

	
	public void select(int x, int y){
		didYouSelect = true;
		if(this.pieceAt(x, y) != null){
			selectedX = x;
			selectedY = y;
		}
		else{
			this.pieceAt(selectedX, selectedY).move(x, y);
			canYouEnd = true;
			didYouMove = true;
			selectedX = x;
			selectedY = y;
		}
	}
	
	public boolean canEndTurn(){
		return canYouEnd;
	}
	
	public void endTurn(){
		canYouEnd = false;
		didYouMove = false;
		if (pieceAt(selectedX, selectedY) != null){
			pieceAt(selectedX, selectedY).doneCapturing();
		}
		if(fireTurn == false){
			fireTurn = true;
		}
		else{
			fireTurn = false;
		}
		winner();
	}
	
	public String winner(){
		int firecounter = 0;
		int watercounter = 0;
		for (int i = 0; i < N; i +=1){
			for (int j = 0; j < N; j +=1){
				if(pieces[i][j]!= null && pieces[i][j].isFire()){
					firecounter += 1;
				}
				if(pieces[i][j]!= null && !pieces[i][j].isFire()){
					watercounter +=1;
				}
			}			
		}
		if (firecounter == 0 && watercounter == 0){
			return "No one";
		}
		if (firecounter == 0){
			return "Water";
		}
		if (watercounter == 0){
			return "Fire";
		}
		return  null;
	}
	
	
}
		
	
