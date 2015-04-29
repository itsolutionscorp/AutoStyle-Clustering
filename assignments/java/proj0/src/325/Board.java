public class Board{
	private Piece[][] pieces;
	private int player = 1;
	private boolean hasSelected = false;	
	private boolean hasMoved = false;
	private int selectedx;
	private int selectedy;
	private boolean hasWinner =false;

	public Piece pieceAt(int x, int y){
		if (x>7 || x < 0 || y < 0 || y>7 || pieces[x][y]==null){
			return null;
		}
		return pieces[x][y];
	}


	public boolean canSelect(int x, int y){
		if ((!hasSelected || (hasSelected && !hasMoved)) && pieceAt(x, y)!=null && player == pieceAt(x, y).side()){
			return true;
		}
		else if (hasSelected) {
			if (!hasMoved && !PieceSelected().hasCaptured() && validMove(selectedx,selectedy,x,y))
				return true;
			if (pieceAt(selectedx, selectedy)!=null && pieceAt(selectedx, selectedy).hasCaptured() && validMove(selectedx,selectedy,x,y)){
				return true;
			}
		}
		return false;
	}

	private boolean tileSelected(){
		if (pieceAt(selectedx,selectedy)==null&&hasSelected)
			return true;
		return false;
	}

	private Piece PieceSelected(){
		if (pieceAt(selectedx,selectedy)!=null)
			return pieceAt(selectedx,selectedy);
		return null;
	}
	private boolean validMove(int xi, int yi, int xf, int yf){
		int distancex = xf - xi;
		int distancey = yf - yi;
		if (pieceAt(xf,yf)!=null)
			return false;
		if (!hasMoved && Math.abs(distancex) ==1 && Math.abs(distancey) == 1){
			if (pieceAt(xi,yi).isKing())
				return true;
			if (pieceAt(xi,yi).isFire())
				if (distancey>0)
					return true;
			if (!pieceAt(xi,yi).isFire())
				if (distancey<0)
					return true;
			return false;
		}
		//ifcancapturevalid
		else if (Math.abs(distancex) ==2 && Math.abs(distancey) ==2){
			//ifking
			if (pieceAt(xi,yi).isKing()){
				if (distancey<0){
					if (distancex<0){
						if (pieceAt(xf+1,yf+1)!=null && pieceAt(xf+1,yf+1).side()!=player)
							return true;
					}else if (distancex>0){
						if (pieceAt(xf-1,yf+1)!=null &&pieceAt(xf-1,yf+1).side()!=player)
							return true;
					}
				}if (distancey>0) {
					if (distancex<0){
						if (pieceAt(xf+1,yf-1)!=null && pieceAt(xf+1,yf-1).side()!=player)
							return true;
					} else if (distancex>0){
						if(pieceAt(xf-1,yf-1)!=null && pieceAt(xf-1,yf-1).side()!=player)
							return true;
					}
				}
			} else if (pieceAt(xi,yi).isFire()){
				if (distancey>0) {
					if (distancex<0){
						if (pieceAt(xf+1,yf-1)!=null &&pieceAt(xf+1,yf-1).side()!=player)
							return true;
					} else if (distancex>0){
						if(pieceAt(xf-1,yf-1)!=null &&pieceAt(xf-1,yf-1).side()!=player)
							return true;
					}
				}
			} else if (!pieceAt(xi,yi).isFire()){
				if (distancey<0) {
					if (distancex<0){
						if (pieceAt(xf+1,yf+1)!=null &&pieceAt(xf+1,yf+1).side()!=player)
							return true;
					} else if (distancex>0){
						if(pieceAt(xf-1,yf+1)!=null &&pieceAt(xf-1,yf+1).side()!=player)
							return true;
					}
				}
			}

		}
		return false;
	}

	public void select(int x, int y){
		if (pieceAt(x,y)==null){
			PieceSelected().move(x,y);
			hasMoved=true;
		}
		selectedx = x;
		selectedy = y;
		hasSelected = true;
	}

	public void place(Piece p, int x, int y){
		if (p==null || x > 7 || y > 7 || x<0 || y< 0)
			return;
		else 
			pieces[x][y]=p;
	}


	public Piece remove(int x, int y){
		if (x>7 || y>7){
			System.out.println("Selection out of bounds.");
			return null;
		}
		else if (pieceAt(x,y)==null){
			System.out.println("No piece at selection.");
			return null;
		}
		else {
			Piece p= pieces[x][y];
			pieces[x][y]=null;
			return p;
		}
	}

	public boolean canEndTurn(){
		if (!hasMoved&&selectedy==-1)
			return false;
		else if (hasMoved || pieceAt(selectedx,selectedy).hasCaptured())
			return true;
		return false;
	}

	public void endTurn(){
		if (player == 0)
			player += 1;
		else
			player = 0;
		if (PieceSelected()!=null)
			PieceSelected().doneCapturing();
		hasSelected = false;	
		hasMoved = false;
		selectedx=-1;
		selectedy=-1;
	}

	public String winner(){
		int fire = 0; int water = 0;
		int x= 0;
		while (x<8){
			int y= 0;
			while (y<8){
				Piece z = pieceAt(x,y);
				if (z!=null){
					if (z.isFire())
						fire+=1;
					else
						water+=1;
				}
			y+=1;
			}
		x+=1;
		}
		if (fire==0 && water==0){
			hasWinner=true;
			return "No one";
		}
		else if (fire==0){
			hasWinner=true;
			return "water";
		}
		else if (water==0){
			hasWinner=true;
			return "fire";
		}
		return null;
	}

	public Board(boolean shouldBeEmpty){
		int N = 8;
		pieces = new Piece[N][N];
		if (!shouldBeEmpty){
	        int j = 0;
			for (int i = 0; i < N; i+=2){
	        	pieces[i][j] = new Piece(true, this, i, j, "pawn");
	        }
	        j = 1;
	        for (int i = 1; i < N; i+=2){
	        	pieces[i][j] = new Piece(true, this, i, j, "shield");
	        }
	        j = 2;
	        for (int i = 0; i < N; i+=2){
	        	pieces[i][j] = new Piece(true, this, i, j, "bomb");
	        }	        
	        j = 7;
	        for (int i = 1; i < N; i+=2){
	        	pieces[i][j] = new Piece(false, this, i, j, "pawn");
	        }	        
	        j = 6;
	        for (int i = 0; i < N; i+=2){
	        	pieces[i][j] = new Piece(false, this, i, j, "shield");
	        }	        
	        j = 5;
	        for (int i = 1; i < N; i+=2){
	        	pieces[i][j] = new Piece(false, this, i, j, "bomb");
	        }
	    }
	}


	//Draws board
	private void drawBoard(int N) {
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
        if (hasSelected){
        	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        	StdDrawPlus.filledSquare(selectedx + .5, selectedy + .5, .5);
        }
        int x=0; 
        while (x<N) {
        	int y=0;
        	while (y<N){
        		if (pieces[x][y]!=null){
        			if (pieces[x][y].isKing()){
        				if (pieces[x][y].isFire()){
	        				if (pieces[x][y].isBomb())
					        	StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire-crowned.png", 1, 1);
					        else if (pieces[x][y].isShield())	
					        	StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire-crowned.png", 1, 1);
					        else
					        	StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire-crowned.png", 1, 1);
					    }
					    if (!pieces[x][y].isFire()){
	        				if (pieces[x][y].isBomb())
					        	StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water-crowned.png", 1, 1);
					        else if (pieces[x][y].isShield())	
					        	StdDrawPlus.picture(x + .5, y + .5, "img/shield-water-crowned.png", 1, 1);
					        else
					        	StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water-crowned.png", 1, 1);
					    }
        			}
        			else if (pieces[x][y].isFire()){
        				if (pieces[x][y].isBomb())
				        	StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
				        else if (pieces[x][y].isShield())	
				        	StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
				        else
				        	StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
        			}
        			else if (!pieces[x][y].isFire()){
        				if (pieces[x][y].isBomb())
				        	StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
				        else if (pieces[x][y].isShield())	
				        	StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
				        else
				        	StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);
        			}
			    }
			    y=y+1;
		    }
	    	x+=1;
        }
    }

	public static void main(String[] args){
		Board b = new Board(false);
		b.drawBoard(8);
		while (true){
			b.drawBoard(8);
			if (StdDrawPlus.mousePressed()){
				StdDrawPlus.show(20);
				int x = (int) StdDrawPlus.mouseX();
				int y = (int) StdDrawPlus.mouseY();
				if (b.canSelect(x,y)){
					b.select(x,y);
					}
				}
			StdDrawPlus.show(20);
			if (StdDrawPlus.isSpacePressed() && b.canEndTurn()){
				b.endTurn();
				if (b.hasWinner){
					System.out.println(b.winner() + " is the winner!");
					return;
				}
			}
		}
	}
}