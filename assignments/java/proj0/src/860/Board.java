public class Board {
	private boolean empty;
	private int player;
	private Piece selected;
	private int moved;
	private int sX; // x coordinate of selected piece
	private int sY; // y coordinate of selected piece
	private Piece[][] pieces;

	public Board(boolean shouldBeEmpty){
		empty = shouldBeEmpty;
		player = 1;
		moved = 0;
		selected = null;
		pieces = new Piece[8][8];
		if (this.empty == false){
			for (int x = 0; x<8; x++){
				if (x % 2 == 0){
					this.pieces[x][0] = new Piece(true,this,x,0,"pawn");
					this.pieces[x][6] = new Piece(false,this,x,6,"shield");
					this.pieces[x][2] = new Piece(true,this,x,2,"bomb");
				} else if (x % 2 == 1){
					this.pieces[x][1] = new Piece(true,this,x,1,"shield");
					this.pieces[x][7] = new Piece(false,this,x,7,"pawn");
					this.pieces[x][5] = new Piece(false,this,x,5,"bomb");
				}
			}
		}
	}

	private void drawBoard(){
		StdDrawPlus.setScale(0,8);
		for (int y = 0; y<8; y++){ //make colored board, alternating grey and red
			for (int x = 0; x<8; x++){
				if ((x+y) % 2 == 0){
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(x + .5, y + .5, .5);
			}
		}
		for (int x = 0; x<8; x++){
			for (int y = 0; y<8; y++){
				if (this.pieceAt(x,y) != null){
					Piece p = this.pieceAt(x,y);
					if(p.isKing() == false){
						if (p.isFire() == true){
							if(p.isBomb() == true){
								StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png",1,1);
							} else if (p.isShield() == true){
								StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png",1,1);
							} else {
								StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png",1,1);
							}
						} else {
							if(p.isBomb() == true){
								StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png",1,1);
							} else if (p.isShield() == true){
								StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png",1,1);
							} else {
								StdDrawPlus.picture(x + .5, y+ .5, "img/pawn-water.png",1,1);
							}
						}
					} else {
						if (p.isFire() == true){
							if(p.isBomb() == true){
								StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire-crowned.png",1,1);
							} else if (p.isShield() == true){
								StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire-crowned.png",1,1);
							} else {
								StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire-crowned.png",1,1);
							}
						} else {
							if(p.isBomb() == true){
								StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water-crowned.png",1,1);
							} else if (p.isShield() == true){
								StdDrawPlus.picture(x + .5, y + .5, "img/shield-water-crowned.png",1,1);
							} else {
								StdDrawPlus.picture(x + .5, y+ .5, "img/pawn-water-crowned.png",1,1);
							}
						}
					}
				}
			}
		}
		while (this.winner() == null){
			if (StdDrawPlus.mousePressed() == true){
				int x = (int)StdDrawPlus.mouseX();
				int y = (int)StdDrawPlus.mouseY();
				if (this.canSelect(x,y) == true){
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					StdDrawPlus.filledSquare(x + .5, y + .5, .5);
					this.select(x,y);
				}
			}
			if (StdDrawPlus.isSpacePressed() == true){
				if (this.canEndTurn() == true){
					this.endTurn();
					this.drawBoard();
				}
			} 
		}
	}
	// ******** IMPORTANT ********
	// REMEMBER TO ADD IMG/ TO STRINGS WHEN NOT WORKING IN ECLIPSE

	// functional methods
	public Piece pieceAt(int x, int y){
		if (x < 0 || x > 7 || y < 0 || y > 7){
			return null;
		}
		Piece p = this.pieces[x][y];
		if (p == null){
			return null;
		} else {
			return p;
		}	
	}

	public boolean canSelect(int x, int y){
		boolean result = false;
		if (x < 0 || x > 7 || y < 0 || y > 7){ // check that its not out of bounds
			result =  false;
		}
		int isPiece = 0;
		if (this.pieces[x][y] != null){
			isPiece = 1;
		}
		Piece possible = this.pieces[x][y];
		Piece q = this.selected;
		if (q != null && isPiece == 1){
			if (possible.side() == player && q.hasCaptured() == false){ //has captured will be reset to false after legal moves are done
				result =  true; //checks that the the player has not just moved
			}
		} else if (isPiece == 1 && q == null){
			if (possible.side() == player){
				result =  true;
			} else {
				result = false;
			}
		} else if (q != null && isPiece == 0){
			if (this.moved == 0){
				if(q.isKing() == false && q.isFire() == true){
					if((y - this.sY) == 1 && Math.abs(x - this.sX) == 1 && q.hasCaptured() == false){
						result =  true;
					} else if(this.capturing(x, y) == true){// find way to tell is capturing
						if ((this.selected.isBomb() == false && this.selected.hasCaptured() == true) || this.selected.hasCaptured() == false){
							result =  true;
						} else{
							result = false;
						}
					}
				} else if (q.isKing() == false && q.isFire() == false){
					if((this.sY-y) == 1 && Math.abs(x - this.sX) == 1 && q.hasCaptured() == false){
						result =  true;
					}else if(this.capturing(x, y) == true){ 
						if ((this.selected.isBomb() == false && this.selected.hasCaptured() == true) || this.selected.hasCaptured() == false){
							result =  true;
						} else{
							result = false;
						}
					}
				} else if (q.isKing() == true){
					if (Math.abs(y-this.sY) == 1 && Math.abs(x - this.sX) == 1 && q.hasCaptured() == false){
						result = true;
					} else if (this.capturing(x, y) == true){
						if ((this.selected.isBomb() == false && this.selected.hasCaptured() == false) || this.selected.hasCaptured() == true){
							result =  true;
						} else{
							result = false;
						} 
					}
				}
			}
		} else if (isPiece == 0 && q == null){
			result = false;
		} else {
			result = false;
		}
		return result;
	}

	private boolean capturing(int x, int y){ //returns if piece can capture
		Piece p = this.selected;
		if (x - this.sX == 0 || y - this.sY == 0){
			return false;
		}
		int dir = ((x - this.sX)/Math.abs(x - this.sX)); // direction of motion in the X direction
		if (p.isKing() == false && p.isFire() == true){
			Piece captive = pieceAt((x-(1*dir)), (y-1));
			if (captive != null){
				if (captive.isFire() == false && (x - (2*dir) == this.sX) && (y -2) == this.sY){
					return true;
				} else {
					return false;
				}
			}
		} else if (p.isKing() == false && p.isFire() == false){
			Piece captive = pieceAt((x-(1*dir)), (y+1));
			if (captive != null){
				if (captive.isFire() == true && (x - (2*dir) == this.sX) && (y + 2) == this.sY){
					return true;
				} else {
					return false;
				}
			}
		} else if (p.isKing() == true){
			int dirY = ((y - this.sY)/Math.abs(y - this.sY)); // direction of capture on Y axis: only relevant for kings
			Piece captive = pieceAt( x - 1*dir, y - 1*dirY);
			if (captive != null){
				if (captive.isFire() != p.isFire() && (x - (2*dir) == this.sX) && (y - (2*dirY) == this.sY)){
					return true;
				} else {
					return false;
				}
			}
		}

		return false;
	}

	public void place(Piece p, int x, int y){
		if (x>-1 && x<8 && y<8 && y>-1 && p != null){
			this.pieces[x][y] = p;
			Piece q = this.pieces[x][y];
			if (this.selected != null){
				if (sX != x || sY != y){
					this.pieces[sX][sY] = null;
				}
			}
		}
	}

	public void select(int x, int y){
		Piece p = this.pieceAt(x,y);
		if (p != null){
			this.selected = p;
			this.sX = x;
			this.sY = y;
			this.place(p, x, y);
		} else{
			if (this.capturing(x, y) == true){
				this.moved = 0;
			} else {
				this.moved = 1;
			}
			this.selected.move(x,y);
			this.place(p, x, y);
			this.sX = x;
			this.sY = y;
		}
	}

	public String winner(){
		int fire = 0;
		int water = 0;
		for (int x = 0; x<8; x++){
			for (int y = 0; y<8; y++){
				if (this.pieces[x][y] != null && this.pieces[x][y].side() == 1){
					fire = fire + 1;
				} else if (this.pieces[x][y] != null && this.pieces[x][y].side() == 0){
					water = water + 1;
				}
			}
		}
		if (fire == 0 && water == 0){
			return "No winner";
		} else if (fire == 0 && water > 0){
			return "Water";
		} else if (water == 0 && fire > 0){
			return "Fire";
		} else {
			return null;
		}
	}

	public Piece remove(int x, int y){
		if (x < 0 || x > 7 || y < 0 || y > 7){
			System.out.println("These coordinates are out of bounds");
			return null;
		} else if (this.pieceAt(x, y) == null){
			System.out.println("There is no piece to remove at this location");
			return null;
		}else {
			Piece P = this.pieceAt(x, y);
			this.pieces[x][y] = null;
			return P;
		}
	}

	public boolean canEndTurn(){ // determines whether piece has moved or captured
		if (this.selected == null){
			return false;
		}
		if (this.moved == 1 || this.selected.hasCaptured() == true){
			return true;
		} else {
			return false;
		}
	}

	public void endTurn(){
		if (this.canEndTurn() == true){
			this.selected.doneCapturing();
			this.selected = null;
			this.moved = 0;
			if (this.player == 1){
				this.player = 0;
			} else {
				this.player = 1;
			}
		}if (this.winner() != null){
			System.out.println(this.winner());
		}
	}

	public static void main(String args[]){
		Board b = new Board(false);
		b.drawBoard();
	}
}
