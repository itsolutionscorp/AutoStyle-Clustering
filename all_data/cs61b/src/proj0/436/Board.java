public class Board{

	private boolean[][] pieces = new boolean[8][8];
	private int pieceSelX, pieceSelY;
	private Piece[][] table = new Piece[8][8];
	private boolean playerFireTurn = true;
	private boolean selected = false, moved = false, notbombed=true;

	private void putRowCheckers(int row, String type){
		boolean isFire;
		if (row<3) isFire = true;
		else	   isFire = false;
		for (int col = row % 2; col < 8; col += 2){
			Piece p = new Piece(isFire, this, col, row, type);
			place(p, col, row);
		}
	}
	public Board(boolean shouldBeEmpty){
		if(!shouldBeEmpty){
			putRowCheckers(0, "pawn");
			putRowCheckers(1, "shield");
			putRowCheckers(2, "bomb");
			putRowCheckers(5, "bomb");
			putRowCheckers(6, "shield");
			putRowCheckers(7, "pawn");
		}
	}

	public Piece pieceAt(int x, int y){
		if(x>=0 && x<=7 && y>=0 && y<=7) return table[x][y];
		else return null;
	}

	public void place(Piece p, int x, int y){
		if(x>=0 && x<=7 && y>=0 && y<=7) table[x][y]=p;
	}

	public Piece remove(int x, int y){
		if(x>7 || y>7 || x<0 || y<0){
			System.out.println("You are out of bound!!!");
			return null;
		}
		Piece piece=pieceAt(x,y);
		if (piece==null){
			System.out.println("You chose empty cell!!!");
			return null;
		}
		table[x][y]=null;
		return piece;
	}

	public void select(int x, int y){
		//System.out.println(canSelect(x, y));
			if (!selected){
				selected=true;
				pieces[x][y]=true;
				pieceSelX=x;
				pieceSelY=y;
			}else{
				if(pieceAt(x,y)!=null){
					pieces[pieceSelX][pieceSelY]=false;
					pieces[x][y]=true;
					pieceSelX=x;
					pieceSelY=y;
				}else{
					pieceAt(pieceSelX,pieceSelY).move(x,y);
					moved=true;
					pieces[pieceSelX][pieceSelY]=false;
					if (pieceAt(x,y)!=null) {pieces[x][y]=true;
					pieceSelX=x;
					pieceSelY=y;}else
					{
						notbombed=false;
					}
				}
			}
	}
	
	public String winner(){
		int numF=0, numW=0;
		for (int i=0; i<8;i++){
			for(int j=0; j<8; j++){
				if(pieceAt(i,j)!=null){
					if(pieceAt(i,j).isFire()) numF +=1;
					else numW +=1;
				}
			}
		}
		if (numW+numF==0) return "No one";
		else if (numF!=0 && numW!=0) return null;
		else if (numF!=0 && numW==0) return "Fire";
		else  return "Water";
	}

	public boolean canSelect(int x, int y){
		if(pieceAt(x, y)!=null && playerFireTurn == pieceAt(x, y).isFire() && (!selected || !moved)){
			return true;
		}
		if(pieceAt(x, y)==null && selected && !moved && validMove(pieceSelX, pieceSelY, x, y)){
			return true;
		}
		if(pieceAt(x, y)==null && selected && notbombed && pieceAt(pieceSelX,pieceSelY).hasCaptured() && validMove(pieceSelX, pieceSelY, x, y) && ableCapture(pieceSelX, pieceSelY, x, y)){
			return true;
		}
		return false;
	}

	private boolean ableCapture(int xi, int yi, int xf, int yf){
		if(!pieceAt(xi, yi).isKing()){
			if(pieceAt(xi,yi).isFire() && yf>yi && pieceAt(xf, yf)==null && 
			   pieceAt((xi+xf)/2, (yi+yf)/2)!= null && !(pieceAt((xf+xi)/2, (yi+yf)/2).isFire())){
				return true;
			}
			else if(!pieceAt(xi,yi).isFire() && yf<yi && pieceAt(xf, yf)==null && 
			   pieceAt((xi+xf)/2, (yi+yf)/2)!= null && pieceAt((xf+xi)/2, (yf+yi)/2).isFire()){
				return true;
			}
			return false;
		}else{
			if(xi>xf && yi>yf){
				for (int i=1; i<xi-xf; i++){
					if (pieceAt(xi-i, yi-i)!=null && pieceAt(xi-i, yi-i).isFire() != playerFireTurn) return true;
				}
				return false;
			}
			else if(xi<xf && yi<yf){
				for (int i=1; i<xf-xi; i++){
					if (pieceAt(xi+i, yi+i)!=null && pieceAt(xi+i, yi+i).isFire() != playerFireTurn) return true;
				}
				return false;
			}
			else if(xi>xf && yi<yf){
				for (int i=1; i<xi-xf; i++){
					if (pieceAt(xi-i, yi+i)!=null && pieceAt(xi-i, yi+i).isFire() != playerFireTurn) return true;
				}
				return false;
			}
			else{
				for (int i=1; i<xf-xi; i++){
					if (pieceAt(xi+i, yi-i)!=null && pieceAt(xi+i, yi-i).isFire() != playerFireTurn) return true;
				}
				return false;
			}
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf){
		if (xf>7 || yf>7 || xf<0 ||yf<0) return false;
		if(!pieceAt(xi, yi).isKing()){
			if(pieceAt(xi,yi).isFire()){
				if (yf==yi+1){
					return pieceAt(xf,yf)==null && (xf-xi==1 || xf-xi==-1);
				}
				if(yf==yi+2){
					return (xf-xi==2 || xf-xi==-2) && pieceAt(xf,yf)==null && 
					pieceAt((xi+xf)/2, (yf+yi)/2)!= null && !pieceAt((xi+xf)/2, (yf+yi)/2).isFire();
				}
				return false;
			}else{
				if (yf==yi-1){
					return pieceAt(xf,yf)==null && (xf-xi==1 || xf-xi==-1);
				}
				if(yf==yi-2){
					return (xf-xi==2 || xf-xi==-2) && pieceAt(xf,yf)==null && 
					pieceAt((xi+xf)/2, (yf+yi)/2)!= null && pieceAt((xi+xf)/2, (yf+yi)/2).isFire();
				}
				return false;
			}
		} else
		{
			if (yf==yi+1){
				return pieceAt(xf,yf)==null && (xf-xi==1 || xf-xi==-1);
			}
			if(yf==yi+2){
				return (xf-xi==2 || xf-xi==-2) && pieceAt(xf,yf)==null && 
				pieceAt((xi+xf)/2, (yf+yi)/2)!= null && pieceAt((xi+xf)/2, (yf+yi)/2).isFire()!=playerFireTurn;
			}
			if (yf==yi-1){
				return pieceAt(xf,yf)==null && (xf-xi==1 || xf-xi==-1);
			}
			if(yf==yi-2){
				return (xf-xi==2 || xf-xi==-2) && pieceAt(xf,yf)==null && 
				pieceAt((xi+xf)/2, (yf+yi)/2)!= null && pieceAt((xi+xf)/2, (yf+yi)/2).isFire()!=playerFireTurn;
			}
			return false;
		}
	}
	
	public boolean canEndTurn(){
		if (moved || (pieceAt(pieceSelX,pieceSelY)!=null && pieceAt(pieceSelX,pieceSelY).hasCaptured())) return true;
		else return false;
	}

	public void endTurn(){
		if(canEndTurn()){
			selected = false;
			moved = false;
			notbombed=true;
			playerFireTurn=!playerFireTurn;
			pieces[pieceSelX][pieceSelY]=false;
			if(pieceAt(pieceSelX,pieceSelY)!=null){
			pieceAt(pieceSelX,pieceSelY).doneCapturing();
				
			} 
		}
	}

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pieces[i][j]) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                
                // Drawing initial configuration
                Piece p = pieceAt(i, j);
     			if(p!=null){
	                if(p.isFire()){
	                	if(p.isBomb()){
	                		if (p.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
	                		else StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
	                	} else if(p.isShield()){
	                		if (p.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
	                		else StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
	                	} else{
	                		if (p.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
	                		else StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
	                	}
	                } else{
	                	if(p.isBomb()){
	                		if (p.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
	                		else StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
	                	} else if(p.isShield()){
	                		if (p.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
	                		else StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
	                	} else{
	                		if (p.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
	                		else StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
	                	}
	                }
	            }
            }
        }
    }

    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board board = new Board(false);
        while(true) {
            board.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (board.canSelect((int) x, (int) y)) board.select((int) x,(int) y);
            }
            if(StdDrawPlus.isSpacePressed()){
            	board.endTurn();
            	if(board.winner()!=null){
            	System.out.println(board.winner());
            	break;
            	}
            }
            StdDrawPlus.show(100);
        }
    }

}