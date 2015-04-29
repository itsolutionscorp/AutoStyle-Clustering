public class Board {
	private Piece[][] bored= new Piece[8][8];
	private int fireturn = 0;
	private int curx = -1, cury = -1;
	private boolean moved = false;
	public static void main(String[] args) {
		Board b = new Board(false);
		
		while(true)
		{
		  StdDrawPlus.show(15);
		  if(StdDrawPlus.mousePressed())
		  {
			  double x = StdDrawPlus.mouseX();
              double y = StdDrawPlus.mouseY();
			  if(b.canSelect((int) x,(int) y)) b.select((int)x, (int) y);
		  }
		  if(StdDrawPlus.isSpacePressed() && b.canEndTurn()) b.endTurn();
		  String win = b.winner();
		  if(win != null){
			 System.out.println(win + " wins");
			 break;
		  }
		}
	}
	public Board(boolean shouldBeEmpty){
		DrawBoard(8);
		if(shouldBeEmpty){
			return;
		}
		placeline(true, 0, 0, "pawn");
		placeline(true, 1, 1, "shield");
		placeline(true, 2, 0, "bomb");
		placeline(false, 7, 1, "pawn");
		placeline(false, 6, 0, "shield");
		placeline(false, 5, 1, "bomb");		
	}
	private void placeline(boolean fire, int line, int start, String type){
		for(int i= start; i <= 7; i+= 2){
			Piece placing = new Piece(fire, this, i, line, type);
			bored[i][line] = placing;
			drawpiece(placing, i, line);
		}
	}
	private void DrawBoard(int s){
		StdDrawPlus.setXscale(0,s);
		StdDrawPlus.setYscale(0,s);
		for(double i = .5; i <s; i+=1){
			if((i-.5)%2==0){
			   for (double j = 1; j <s; j+=2){
					StdDrawPlus.setPenColor(StdDrawPlus.BOOK_RED);
					StdDrawPlus.filledSquare(j-.5,i, .5);
					StdDrawPlus.setPenColor(StdDrawPlus.DARK_GRAY);
					StdDrawPlus.filledSquare(j+.5, i, .5);
			     }
			}
			else{
			   for (double j = 1; j <s; j+=2){
				StdDrawPlus.setPenColor(StdDrawPlus.DARK_GRAY);
				StdDrawPlus.filledSquare(j-.5,i, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.BOOK_RED);
				StdDrawPlus.filledSquare(j+.5, i, .5);
			}}}}
	public Piece pieceAt(int x, int y){
		if(x>7|| y>7) return null;
		return bored[x][y];
	}
 	public Piece remove(int x, int y){
 		if((x < 0 || x > 7 ) || (y < 0 || y > 7)){
 			System.out.printf("There is no location (%d,%d)",x, y);
 			return null;
 		}
 		StdDrawPlus.setPenColor(StdDrawPlus.BOOK_RED);
 		StdDrawPlus.filledSquare(x+.5, y+.5, .5);
 		Piece delete = bored[x][y];
 		bored[x][y] = null;
 		if(delete == null) System.out.printf("There is no piece at (%d,%d)",x, y);
 		return delete;
 	}
	public void place(Piece p , int x, int y){
		if( x>7 || y > 7) return;
		if (p == null) return;
		if(pieceAt(x,y)!= null) remove(x,y);
		bored[x][y] = p;
		drawpiece(p, x, y);
	}
	private void drawpiece(Piece p, int x, int y){
		String[] details= new String[3];
		if(p.isFire()) details[1] = "fire";
		else details[1] = "water";
		if(p.isBomb()) details[0] = "bomb-";
		else if(p.isShield()) details[0] = "shield-";
		else details[0] = "pawn-";
		if(p.isKing()) details[2] = "-crowned";
		else details[2] = "";
		String location = "/img/" + details[0] + details[1]+ details[2] + ".png";
		StdDrawPlus.picture(x+ .5, y+.5 , location, 1, 1);
		
		
	}
	public boolean canSelect(int x, int y){
		if((x < 0 || x > 7 ) || (y < 0 || y > 7)) return false;
		if(pieceAt(x,y) == null){
			if (curx >= 0 && pieceAt(curx, cury)!= null){
			Piece p = pieceAt(curx,cury);
			if(!moved || p.hasCaptured()){		
				if (p.isKing()) return (validMove(1,x,y) || validMove(-1,x,y));
				int direction = -1;
				if(p.isFire()) direction = 1;
				return validMove(direction, x,y);
				}
			}
			return false;
		}
		boolean playerpiece = false;
		if(pieceAt(x,y).side() == fireturn) playerpiece = true;
		return playerpiece && !moved;
	}
	private boolean validMove(int direction, int xf, int yf){
		if((cury + 2*direction) == yf){
			int xdir = (xf-curx)/2;
			Piece capturing = pieceAt(curx+xdir,cury+direction);
			if (capturing != null && capturing.side() == fireturn) return false;
			return true;
		}
		if(!moved && (cury+direction == yf && ((curx+1)== xf || (curx -1) == xf))){
			return true;
		}
		return false;
	}
	public void select(int x, int y){
		if(pieceAt(x,y) != null){
			if(curx >= 0){
				StdDrawPlus.setPenColor(StdDrawPlus.BOOK_RED);
				StdDrawPlus.filledSquare(curx+.5, cury+.5, .5);
				if(pieceAt(curx,cury) != null) drawpiece(pieceAt(curx,cury), curx, cury);
			}
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			StdDrawPlus.filledSquare(x+.5,y+.5, .5);
			drawpiece(pieceAt(x,y), x, y);
			curx= x;
			cury = y;
			return;
		}
		pieceAt(curx, cury).move(x,y);
		moved = true;
		curx= x;
 		cury = y;
		
  }
	public boolean canEndTurn()	{
		return moved;
	}
	public void endTurn(){
		if(curx >= 0){
			StdDrawPlus.setPenColor(StdDrawPlus.BOOK_RED);
			StdDrawPlus.filledSquare(curx+.5, cury+.5, .5);
			if(pieceAt(curx,cury) != null) drawpiece(pieceAt(curx,cury), curx, cury);
		}
		if(pieceAt(curx,cury)!= null) pieceAt(curx, cury).doneCapturing();
		curx = -1;
		cury = -1;
		moved = false;
		if(fireturn == 0) fireturn = 1;
		else fireturn = 0;	
	}
	public String winner(){
		int fire = 0, water = 0;
		for(Piece[] pA: bored){
			for(Piece p: pA){
				if(p != null){
					if(p.side() == 0) fire++;
					else water++;
				}
			}
		}
		if(fire == 0 && water == 0) return "No one";
		if(fire == 0)return "Water";
		if(water == 0) return "Fire";
		return null;
	}
	
}