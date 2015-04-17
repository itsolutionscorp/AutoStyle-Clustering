public class Board {
		private Piece[][] pieces;
		private boolean hasMoved;
		private boolean isFireTurn;
		private Piece chosen;
		private int xchos;
		private int ychos;
		private int fire_sum;
		private int water_sum;

		public static void main (String[] args){
			int N = 8;
			Board b = new Board(false);
			StdDrawPlus.setXscale(0, N);
        	StdDrawPlus.setYscale(0, N);
			//b.drawBoard(N);

			while(true) {
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int)x,(int)y)){
                	b.select((int)x,(int)y);
                }
            }   
            if (StdDrawPlus.isSpacePressed()){
            	if (b.canEndTurn()) {
            		b.endTurn();
            	}
            }         
            StdDrawPlus.show(10);
        }
		}

		private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                if (i==xchos && j == ychos && chosen != null) {
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (pieceAt(i,j) != null) {
                    StdDrawPlus.picture(i + .5, j + .5, checker(pieces[i][j]), 1, 1);
                }
            }
        }
    }
		private String checker(Piece x){
			if (x.isKing()){
				if (x.isFire()){
					if (x.isBomb()){
						return "img/bomb-fire-crowned.png";
					} else if (x.isShield()) {
						return "img/shield-fire-crowned.png";
					} else {
						return "img/pawn-fire-crowned.png";
					}
				} else {
					if (x.isBomb()){
						return "img/bomb-water-crowned.png";
					} else if (x.isShield()) {
						return "img/shield-water-crowned.png";
					} else {
						return "img/pawn-water-crowned.png";
					}
				}
			} else{
				if (x.isFire()){
					if (x.isBomb()){
						return "img/bomb-fire.png";
					} else if (x.isShield()) {
						return "img/shield-fire.png";
					} else {
						return "img/pawn-fire.png";
					}
				} else {
					if (x.isBomb()){
						return "img/bomb-water.png"; 
					} else if (x.isShield()) {
						return "img/shield-water.png";
					} else {
						return "img/pawn-water.png";
					}
				}	
			}
		}

		public Board(boolean shouldBeEmpty) {
			pieces= new Piece[8][8];
			if (!shouldBeEmpty){
				for (int i = 0; i < 8; i++) {
		            for (int j = 0; j < 8; j++) {
		                if (j == 7 && (i+j)%2 == 0){
		                	pieces[i][j]= new Piece(false, this, i, j, "pawn");
		                } else if (j == 6 && (i+j)%2 == 0) {
		                	pieces[i][j]= new Piece(false, this, i, j, "shield");
		                } else if (j == 5 && (i+j)%2 == 0) {
		                   	pieces[i][j]= new Piece(false, this, i, j, "bomb");
		                } else if (j == 2 && (i+j)%2 == 0) {
		                   	pieces[i][j]= new Piece(true, this, i, j, "bomb");
		                } else if (j == 1 && (i+j)%2 == 0) {
		                   	pieces[i][j]= new Piece(true, this, i, j, "shield");
		                } else if (j == 0 && (i+j)%2 == 0) {
		                   	pieces[i][j]= new Piece(true, this, i, j, "pawn");
		                }
		            }
		        }
	    	}
	    	hasMoved = false;
	    	isFireTurn= true;
	    }
	
		public Piece pieceAt(int x, int y){
			if (x >= 0 && y>= 0 && x <=7 && y <=7 ) {
				return pieces[x][y];
			} else {
				return null;
			}
		}
		public boolean canSelect(int x, int y) {
				if (pieceAt(x,y) != null && isFireTurn == pieceAt(x,y).isFire()) {
					if (chosen == null || (pieceAt(x,y) != null && hasMoved== false)) {
						return true;
					} 
				} else if (pieceAt(x,y) == null && chosen != null && validmove(xchos,x,ychos,y) &&  (hasMoved== false || (chosen.hasCaptured() == true && (x-xchos== 2 || x-xchos== -2)))) {
						return true;
					}
				
			return false;
		}
		private boolean validmove (int xi, int xf, int yi,int yf){
			if (chosen.isFire() && (xf-xi==1 || xf-xi==-1) && yf-yi==1){
				return true;
			} else if (!chosen.isFire() && (xf-xi==1 || xf-xi==-1) && yf-yi==-1) {
				return true;
			} else if (chosen.isFire() && (xf-xi==2 || xf-xi==-2) && yf-yi==2 && pieceAt((xf+xi)/2,(yf+yi)/2) != null && !pieceAt((xf+xi)/2,(yf+yi)/2).isFire() ){
				return true;
			} else if (!chosen.isFire() &&(xf-xi==2 || xf-xi==-2) && yf-yi==-2 && pieceAt((xf+xi)/2,(yf+yi)/2) != null && pieceAt((xf+xi)/2,(yf+yi)/2).isFire() ){
				return true;
			} else if (chosen.isKing() &&chosen.isFire() && (xf-xi==1 || xf-xi==-1) && (yf-yi==1 || yf-yi==-1)) {
				return true;
			} else if (chosen.isKing() && !chosen.isFire() && (xf-xi==1 || xf-xi==-1) && (yf-yi==1 || yf-yi==-1)){
				return true;
			} else if (chosen.isKing() && chosen.isFire() &&(xf-xi==2 || xf-xi==-2) && (yf-yi==2 || yf-yi==-2) && pieceAt((xf+xi)/2,(yf+yi)/2) != null && !pieceAt((xf+xi)/2,(yf+yi)/2).isFire() ){
				return true;
			} else if (chosen.isKing() && !chosen.isFire() &&(xf-xi==2 || xf-xi==-2) && (yf-yi==2 || yf-yi==-2) && pieceAt((xf+xi)/2,(yf+yi)/2) != null && pieceAt((xf+xi)/2,(yf+yi)/2).isFire() ){
				return true;
			}
			return false;
		}

		public void select(int x, int y) {
			if (pieceAt(x,y) != null) {
				chosen= pieceAt(x,y);
				xchos= x;
				ychos= y;
			} else {
				xchos= x ;
				ychos= y;
				chosen.move(x,y);
				hasMoved= true;
			}

		}
		public void place(Piece p, int x, int y){
			if (p == null || x >= 8 || y >= 8)  {
				return;
			} else{
				pieces[x][y] = p;
			}
		}
		public Piece remove(int x, int y) {
			Piece removed = pieces[x][y];
			pieces[x][y] = null;
			return removed;
		}
		public boolean canEndTurn(){
			return hasMoved;
		}
		public void endTurn(){
			xchos= -1;
			ychos= -1;
			if (chosen != null) {
				chosen.doneCapturing();
			}
			chosen= null;
			hasMoved = false;

			isFireTurn = !isFireTurn;
			
		}
		public String winner(){
			fire_sum= 0;
			water_sum= 0;
			for (int i = 0; i < 8; i++) {
            	for (int j = 0; j < 8; j++) {
            		if (pieces[i][j] != null && pieces[i][j].isFire()){
            			fire_sum= fire_sum + 1;
            		} else if (pieces[i][j] != null) {
            			water_sum= water_sum +1;
            		}
            	}
			}
			if (fire_sum == 0 && water_sum== 0 ) {
				return "No one";
			} else if (fire_sum==0 && water_sum>0) {
				return "Water";
			} else if (water_sum==0 && fire_sum>0) {
				return "Fire";
			} else {
				return null;
			}
		}
	}