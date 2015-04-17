/**
** @Author Richard Meng
*/


public class Board {
	
	private Piece[][] pieces;
	private boolean turn = true;
	private boolean select = false;
	private boolean move = false;
	private boolean capture = false;
	private boolean draw = false;
	private int n = 8;
	private int tempx;
	private int tempy;
	private int c;
	private int v;
	private int player1;
	private int player2;

	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty == true) {
			pieces = new Piece[n][n];
					}
		else {
			pieces = new Piece[n][n];
			for (double i = 0.5; i < 7; i = i + 2) {
				int conv = (int)i;
				pieces[conv][0] = new Piece(true, this, conv, 0, "pawn");
				pieces[conv][2] = new Piece(true, this, conv, 2, "bomb");
				pieces[conv][6] = new Piece(false, this, conv, 6, "shield");

			}
			for (double i = 1.5; i < 8; i = i + 2) {
				int conv = (int)i;
				pieces[conv][1] = new Piece(true, this, conv, 1, "shield");
				pieces[conv][7] = new Piece(false, this, conv, 7, "pawn");
				pieces[conv][5] = new Piece(false, this, conv, 5, "bomb");

			}

	}
}
	public Piece pieceAt(int x, int y) {
		if ((pieces.length <= x) || (pieces[0].length <= y) || (x<0) || (y<0)) {
			return null;
		}
		

		if (pieces[x][y] == null) {
			return null;
		}
		return pieces[x][y];
	}
	public boolean canSelect(int x, int y) {
		

		if (pieceAt(x,y) != null) {

			if (turn == true) {
				if (pieceAt(x,y).isFire() == true) {
					if ((select == false) || (move == false)) {
						return true;
				}	return false;
				} else {
					return false;
				}

			} else {
				if (pieceAt(x,y).isFire() == false) {
					if ((select == false) || (move == false)) {
						return true;
				} return false;

			} else {
				return false;
			}
					}
		}
		else {

			if (turn == true) {
				if ((select == true) && (move == false) && (pieceAt(tempx,tempy)!=null)&&(validMove(tempx, tempy, x, y) == true)) {

					return true;
				} else if  ((Math.abs(tempx - x) == 2) && (Math.abs(tempy - y) == 2)) {
				if (validMove(tempx, tempy, x, y) == true) {
					return true;
				}
				return false;
			}
			} else if (turn == false) {
				if ((select == true) && (move == false) && (pieceAt(tempx,tempy)!=null)&&(validMove(tempx, tempy, x, y) == true)) {
					return true;
				} 
				else if  ((Math.abs(tempx - x) == 2) && (Math.abs(tempy - y) == 2)) {
				if (validMove(tempx, tempy, x, y) == true) {
					return true;
				}
				return false;
			}
			}
			

			return false;
	}
}
	private boolean validMove(int xi, int yi, int xf, int yf) {
		if ((Math.abs(xi - xf) == 1) && (Math.abs(yf - yi) == 1)) {
			if ((pieceAt(xi,yi).isFire() == true) && (pieceAt(xi,yi).isKing() == false) && (yf > yi)) {
			move = true;
			return true;
		}
			else if ((pieceAt(xi,yi).isFire() == false) && (pieceAt(xi,yi).isKing() == false) && (yf < yi)) {
				move = true;
				return true;
			}
			else if (pieceAt(xi,yi).isKing() == true) {
				move = true;
				return true;
			}
			else {
				return false;
			}

		}
		else if ((Math.abs(xi - xf) == 2) && (Math.abs(yf - yi) == 2)) {
			if ((pieceAt(xi,yi)!=null)&&(pieceAt(xi,yi).isFire() == true) && (pieceAt(xi,yi).isKing() == false) && (yf > yi)) {
				if ((pieceAt((xi+xf)/2,(yi+yf)/2) != null) && (pieceAt((xi+xf)/2,(yi+yf)/2).isFire() == false)) {
					capture = true;
					move = true;
					return true;
				} else {
					return false;
				}
			}
			else if ((pieceAt(xi,yi)!=null)&&(pieceAt(xi,yi).isFire() == false) && (pieceAt(xi,yi).isKing() == false) && (yf < yi)) {
				if ((pieceAt((xi+xf)/2,(yi+yf)/2) != null) && (pieceAt((xi+xf)/2,(yi+yf)/2).isFire()==true)) {
					capture = true;
					move = true;
					return true;
				} else {
					return false;
				}
			}
			else if ((pieceAt(xi,yi)!=null)&&pieceAt(xi,yi).isKing() == true) {
				if ((pieceAt((xi+xf)/2,(yi+yf)/2) != null) && (pieceAt((xi+xf)/2,(yi+yf)/2).isFire()!=pieceAt(xi,yi).isFire())) {
					capture = true;
					move = true;
					return true;
				} else {
					return false;
				}
				}
			}
		return false;
		}
				
	

	public void select(int x, int y) {
		if (pieceAt(x,y) != null){

			// graphics(2,x,y);
			select = true;
            tempx = x;
            tempy = y;
		}
		else {
			if  ((Math.abs(tempx - x) == 2) && (Math.abs(tempy - y) == 2) && (capture == true)) {
				if (pieceAt(tempx,tempy).hasCaptured() == false) {
				pieceAt(tempx,tempy).doneCapturing();}
			}/////new
			else if (validMove(tempx,tempy,x,y)==true) {
				if (capture == true) {
					if (pieceAt(tempx,tempy).hasCaptured() == false) {
					pieceAt(tempx,tempy).doneCapturing();}
				}
			}///new
			draw = true;
			if ((select == true) && (capture == false)) {

				pieces[x][y] = pieceAt(tempx,tempy);
				pieceAt(tempx,tempy).move(x,y);
				remove(tempx,tempy);
				c = tempx;
				v = tempy;
				
				tempx = x;
				tempy = y;
				// graphics(4,x,y,c,v);
			}
			else if ((select == true) && (capture == true)) {
				// int c;
				// int v;
				int xm = (tempx+x)/2;
				int ym = (tempy+y)/2;
				pieces[x][y] = pieceAt(tempx,tempy);
				pieceAt(tempx,tempy).move(x,y);
				remove(tempx,tempy);
				// graphics(4,x,y);
				c = tempx;
				v = tempy;
            	remove(xm,ym);
            	tempx = x;
            	tempy = y;
            	// graphics(4,x,y,c,v);
			}
			
		// }
	}
}

	public void place(Piece p, int x, int y) {
		pieces[x][y] = p;

	}
	public Piece remove(int x, int y){
		Piece copy = pieceAt(x,y);

		pieces[x][y] = null;
		// graphics(5,x,y);
        return copy;
	}

	public boolean canEndTurn() {

		if (move == true) {
			return true;
		}
		

		else if (capture == true) {
			return true;

		}
		else if ((move != true)&&(capture!=true)) {//////new
			for (int i = 0; i < n; i++) {
            	for (int j = 0; j < n; j++) {

					if ((pieceAt(i,j) != null) && (pieceAt(i,j).hasCaptured() == true)) {

						return true;}}
					}

						return false;
					
		}

		return false;
	}

	public void endTurn() {
			turn = !turn;
			select = false;
			move = false;
			capture = false;
			draw = false;
			for (int i = 0; i < n; i++) {
            	for (int j = 0; j < n; j++) {
            		if ((pieceAt(i,j) != null) && (pieceAt(i,j).hasCaptured() == true)) {
            			pieceAt(i,j).doneCapturing();
            		}
            	}}
		
	}

	private String filename(Piece p) {
		if (p.isFire()==true) {
			if (p.isBomb()) {
				if (p.isKing()){
					return "img/bomb-fire-crowned.png";
				}
				else {
					return "img/bomb-fire.png";
				}
			}
			else if (p.isShield()==true) {
				if (p.isKing()) {
					return "img/shield-fire-crowned.png";
				}
				else {
					return "img/shield-fire.png";
				}
				
			} else {
				if (p.isKing()) {
					return "img/pawn-fire-crowned.png";
				}
				else {
					return "img/pawn-fire.png";
				}
			}
		} else {
			if (p.isBomb()) {
				if (p.isKing()){
					return "img/bomb-water-crowned.png";
				}
				else {
					return "img/bomb-water.png";
				}
			}
			else if (p.isShield()==true) {
				if (p.isKing()) {
					return "img/shield-water-crowned.png";
				}
				else {
					return "img/shield-water.png";
				}
				
			} else {
				if (p.isKing()) {
					return "img/pawn-water-crowned.png";
				}
				else {
					return "img/pawn-water.png";
				}
			}
		}
	}

	private void graphics(int u, int x, int y, int k, int o) {
		if (u == 1) {
			StdDrawPlus.setXscale(0, n);
        	StdDrawPlus.setYscale(0, n);
        	for (int i = 0; i < n; i++) {
            	for (int j = 0; j < n; j++) {
                	if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                		else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                		StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                		
			}
		}
			for (double i = 0.5; i < 7; i = i + 2) {
				StdDrawPlus.picture(i, 0.5, "img/pawn-fire.png", 1, 1);/// same
				StdDrawPlus.picture(i, 2.5, "img/bomb-fire.png", 1, 1);
				StdDrawPlus.picture(i, 6.5, "img/shield-water.png", 1, 1);///till here
			}
			for (double i = 1.5; i < 8; i = i + 2) {
				StdDrawPlus.picture(i, 1.5, "img/shield-fire.png", 1, 1);
				StdDrawPlus.picture(i, 7.5, "img/pawn-water.png", 1, 1);
				StdDrawPlus.picture(i, 5.5, "img/bomb-water.png", 1, 1);///till here
			}
			
		}
		if (u == 2) {
			if ((tempx + tempy) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
            else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
            StdDrawPlus.filledSquare(tempx+0.5,tempy+0.5,0.5);
            if (pieces[tempx][tempy] != null) {
            StdDrawPlus.picture(tempx+0.5,tempy+0.5,filename(pieceAt(tempx,tempy)), 1, 1);}
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            StdDrawPlus.filledSquare(x+0.5,y+0.5,0.5);
			StdDrawPlus.picture(x+0.5, y+0.5, filename(pieceAt(x, y)), 1, 1);
		}
		if (u == 4) {
			if (pieceAt(x,y) != null) {
				StdDrawPlus.picture(x+0.5,y+0.5,filename(pieceAt(x,y)), 1, 1);}
				move = true;

				if ((k + o) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
            	else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
            	
            	StdDrawPlus.filledSquare(k+0.5,o+0.5,0.5);
		}
		if (u == 5) {
			for (int i = 0; i < n; i++) {
            	for (int j = 0; j < n; j++) { 
           			if ((pieceAt(i,j)==null)&&((i + j) % 2 == 0)) {
            		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
            		StdDrawPlus.filledSquare(i+0.5,j+0.5,0.5);}
        			else if ((pieceAt(i,j)==null)&&((i+j) % 2 != 0)) {
        				StdDrawPlus.setPenColor(StdDrawPlus.RED);
        			           	StdDrawPlus.filledSquare(i+0.5,j+0.5,0.5);
        			           }           
        }
    }
		}
	}
	public String winner() {
		player1 = 0;
		player2 = 0;
		    for (int i = 0; i < n; i++) {
            	for (int j = 0; j < n; j++) { 
            		if ((pieceAt(i,j)!= null)&&(pieceAt(i,j).isFire() == true)) {
            			player1 = player1+1;
            		} if ((pieceAt(i,j)!= null)&&(pieceAt(i,j).isFire() == false)) {
            			player2 = player2+1;
            		}}}
        if ((player1 == 0)&&(player2 == 0)) {
        	return "No one";
        }
		else if (player1 == 0) {
			return "Water";
		}
		else if (player2 == 0) {
			return "Fire";
			
		}
		 
		
		return null;
	}
	public static void main(String args[]) {
		Board b = new Board(false);
		b.graphics(1,0,0,0,0);
		while(true){

		if (StdDrawPlus.mousePressed()) {
			StdDrawPlus.show(10);
			double x = StdDrawPlus.mouseX();
			double y = StdDrawPlus.mouseY();

			if (b.canSelect((int)x,(int)y) == true) {
				if (b.pieceAt((int)x,(int)y)!=null) {
					b.graphics(2,(int)x,(int)y,0,0);
				}
				b.select((int)x,(int)y);
				if (b.draw == true) {
					b.draw = false;
					b.graphics(4,(int)x,(int)y,b.c,b.v);

				}
				b.graphics(5,0,0,0,0);

			}
		}
		else if (StdDrawPlus.isSpacePressed()) {

			if ((b.move == true) || (b.capture == true)){
				if (b.canEndTurn()==true) {
				b.endTurn();}
			} else {}
		}
	}
}
}