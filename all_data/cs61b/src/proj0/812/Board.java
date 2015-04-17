public class Board{
	private boolean moved;
	private boolean captured;
	private int selectedx;
	private int selectedy;
	private boolean selected;
	private int turn;
	private Piece[][] pieces;
	private int N;

	public static void main(String[] args){
		Board b = new Board(false);
		int N = 8;
		b.N = N;
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
		b.drawBoard(N);
		boolean end = false;
		while(!end){
			b.drawPieces(N);
			if(StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if(b.canSelect((int)x,(int)y)){
                 	b.select((int)x,(int)y);
                }
            }
            if(StdDrawPlus.isSpacePressed()){
            	if(b.canEndTurn()){
            		b.drawBoard(N);
            		b.endTurn();
            	}
            }
            if(b.winner()!=null){
	        	end = true;
	        }
            StdDrawPlus.show(50);
		}
	}
	public Board(){
		pieces = new Piece[8][8];
	}

	public Board(boolean shouldBeEmpty){
		if(shouldBeEmpty){
			pieces = new Piece[8][8];
		}else{
			pieces = new Piece[8][8];
			turn = 0;
			selected = false;
			captured = false;
			moved = false;
			int k = 0;
			for(int i=0; i<8; i+=2){
				pieces[i][k] = new Piece(true, this, i, k, "pawn");
			}
			k++;
			for(int i=1; i<8; i+=2){
				pieces[i][k] = new Piece(true, this, i, k, "shield");
			}
			k++;
			for(int i=0; i<8; i+=2){
				pieces[i][k] = new Piece(true, this, i, k, "bomb");

			}
			k=5;
			for(int i=1; i<8; i+=2){
				pieces[i][k] = new Piece(false, this, i, k, "bomb");
				
			}
			k++;
			for(int i=0; i<8; i+=2){
				pieces[i][k] = new Piece(false, this, i, k, "shield");
			}
			k++;
			for(int i=1; i<8; i+=2){
				pieces[i][k] = new Piece(false, this, i, k, "pawn");
			}
		}
		
	}

	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                //StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                //if (pieces[i][j]) {
                    //StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                //}
            }
        }
    }

    private void drawPieces(int N){
		//StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
    	for(int j=0; j<N;j++){
			for(int k=0; k<N; k++){
				if(pieces[j][k]!=null){
					if(pieces[j][k].isFire()){
						if(pieces[j][k].isBomb()){
							if(pieces[j][k].isKing()){
								StdDrawPlus.picture(j + .5, k + .5, "img/bomb-fire-crowned.png", 1, 1);
							}else{
								StdDrawPlus.picture(j + .5, k + .5, "img/bomb-fire.png", 1, 1);
							}
						}else if(pieces[j][k].isShield()){
							if(pieces[j][k].isKing()){
								StdDrawPlus.picture(j + .5, k + .5, "img/shield-fire-crowned.png", 1, 1);
							}else{
								StdDrawPlus.picture(j + .5, k + .5, "img/shield-fire.png", 1, 1);
							}
						}else{
							if(pieces[j][k].isKing()){
								StdDrawPlus.picture(j + .5, k + .5, "img/pawn-fire-crowned.png", 1, 1);
							}else{
								StdDrawPlus.picture(j + .5, k + .5, "img/pawn-fire.png", 1, 1);
							}
						}
					}else{
						if(pieces[j][k].isBomb()){
							if(pieces[j][k].isKing()){
								StdDrawPlus.picture(j + .5, k + .5, "img/bomb-water-crowned.png", 1, 1);
							}else{
								StdDrawPlus.picture(j + .5, k + .5, "img/bomb-water.png", 1, 1);
							}
						}else if(pieces[j][k].isShield()){
								if(pieces[j][k].isKing()){
									StdDrawPlus.picture(j + .5, k + .5, "img/shield-water-crowned.png", 1, 1);
								}else{
									StdDrawPlus.picture(j + .5, k + .5, "img/shield-water.png", 1, 1);
								}
						}else{
							if(pieces[j][k].isKing()){
								StdDrawPlus.picture(j + .5, k + .5, "img/pawn-water-crowned.png", 1, 1);
							}else{
								StdDrawPlus.picture(j + .5, k + .5, "img/pawn-water.png", 1, 1);
							}
						}
					}
				}
			}
		}
    }

	public Piece pieceAt(int x, int y){
		if(outofbounds(x,y)){
			return null;
		}
		return pieces[x][y];
	}

	public boolean canSelect(int x, int y){
		if(outofbounds(x,y)){
			return false;
		}
		if(pieces[x][y]!=null){
			if(turn!=pieces[x][y].side()){
				return false;
			}
			if((!moved)||(!selected)){
				return true;
			}
		}else{
			if(!(movable(selectedx,selectedy,x,y))){
				return false;
			}
			if((!moved)||((captured)&&(capturable(selectedx,selectedy,x,y)))){
				return true;
			}
		}
		return false;
	}

	public void select(int x, int y){
		if(selected){
			if(pieceAt(x,y)!=null){
				if(!moved){
					drawBoard(N);
					selectedx = x;
					selectedy = y;
					selected = true;
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					StdDrawPlus.filledSquare(x + .5, y + .5, .5);
				}
			}else{
				if(movable(selectedx,selectedy, x, y)){
					pieceAt(selectedx,selectedy).move(x,y);
					place(pieceAt(selectedx,selectedy), x, y);
					if(capturable(selectedx, selectedy, x, y)){
						capture(selectedx, selectedy, x, y);
						if(pieceAt(x,y).isBomb()){
							bombCapture(x, y);
						}
					}
					selectedx = x;
					selectedy = y;
					selected = true;
					moved = true;
					drawBoard(N);
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					StdDrawPlus.filledSquare(x + .5, y + .5, .5);
				}
			}
		}else{
			if(pieceAt(x,y)!=null){
				selectedx = x;
				selectedy = y;
				selected = true;
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				StdDrawPlus.filledSquare(x + .5, y + .5, .5);
			}
		}
	}

	private boolean movable(int x1, int y1, int x2, int y2){
		//checks if its crowned and see if its going right direction. It also checks if it can skip a
		//square by capturing.
		if(capturable(x1, y1, x2, y2)){
			if((Math.abs(x1-x2)>2)||(Math.abs(y1-y2)>2)){
			return false;
			}
		}else if((Math.abs(x1-x2)>1)||(Math.abs(y1-y2)>1)){
			return false;
		}
		if(((x1==x2)&&(y1!=y2))||((x1!=x2)&&(y1==y2))){
			return false;
		}
		if(pieceAt(x1,y1)!=null){
		if(!pieceAt(x1,y1).isKing()){
			if(pieceAt(x1,y1).isFire()){
				if(y2<y1){
					return false;
				}
			}else{
				if(y2>y1){
					return false;
				}
			}
		}
		}
		return true;
	}

	private boolean capturable(int x1, int y1, int x2, int y2){
		if((Math.abs(x1-x2)<2)||(Math.abs(y1-y2)<2)){
			return false;
		}
		int x = (x1+x2) >>> 1;
		int y = (y1+y2) >>> 1;
		if(pieces[x][y]==null){
			return false;
		}
		if(turn!=pieces[x][y].side()){
			return true;
		}
		return false;
	}

	private void capture(int x1, int y1, int x2, int y2){
		int x = (x1+x2)/2;
		int y = (y1+y2)/2;
		remove(x, y);
		captured = true;
	}

	private void bombCapture(int x2, int y2){
		for(int a = x2-1; a < x2+2; a++){
			for(int b = y2-1; b < y2+2; b++){
				if(!outofbounds(a,b)){
					if(pieceAt(a,b)!=null){
						if(!pieceAt(a,b).isShield()){
							remove(a,b);
						}
					}
				}
			}
		}
		captured = true;
	}

	public void place(Piece p, int x, int y){
		if((p!=null)&&(!outofbounds(x,y))){
			if(pieceAt(x,y)!=null){
				remove(x,y);
			}
			pieces[x][y]= p;
			if(selected){
			remove(selectedx,selectedy);
			}
		}
	}

	private boolean outofbounds(int x, int y){
		if((x>7)||(x<0)||(y>7)||(y<0)){
			return true;
		}
		return false;
	}

	public Piece remove(int x, int y){
		if(outofbounds(x,y)){
			System.out.println("Coordinates out of bounds.");
			return null;
		}
		if(pieces[x][y]==null){
			System.out.println("No piece at given Coordinates.");
			return null;
		}
		Piece temp = pieces[x][y];
		pieces[x][y] = null;
		return temp;
	}

	public boolean canEndTurn(){
		return ((captured)||(moved));
	}

	public void endTurn(){
		turn = (1-turn);
		selected = false;
		moved = false;
		captured = false;
		if(pieceAt(selectedx,selectedy)!=null){ 
			pieceAt(selectedx,selectedy).doneCapturing();
		}
	}

	public String winner(){
		int water = 0;
		int fire = 0;
		for(int j=0; j<8;j++){
			for(int k=0; k<8; k++){
				if(pieces[j][k]!=null){
					if(pieces[j][k].isFire()){
						fire++;
					}else{
						water++;
					}
				}
			}
		}
		if((water!=0)&&(fire!=0)){
			return null;
		}if(water>fire){
			return "Water";
		}if(water<fire){
			return "Fire";
		}else{
			return "No one";
		}
	}
}