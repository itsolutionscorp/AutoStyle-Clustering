import java.awt.event.MouseEvent;


public class Board {
	
	private static int size = 8;
	private Piece[][] checker = new Piece[8][8];
	private boolean turn = true;
	private boolean selected = false;
	private boolean moved = false;
	private boolean captured = false;
	private int selectX = -1;
	private int selectY = -1;

	public Board(boolean shouldBeEmpty){
		if (!shouldBeEmpty)
			initChecker();
		}
	
	private void initChecker(){
		for(int j = 0; j < size; j++){
			for(int i = 0; i < size; i++){
				if(j == 0 && i % 2 == 0)
					checker[i][j] = new Piece(true, this, i, j, "pawn");
				else if(j == 1 && i % 2 == 1)
					checker[i][j] = new Piece(true, this, i, j, "shield");
				else if(j == 2 && i % 2 == 0)
					checker[i][j] = new Piece(true, this, i, j, "bomb");
				else if(j == 5 && i % 2 == 1)
					checker[i][j] = new Piece(false, this, i, j, "bomb");
				else if(j == 6 && i % 2 == 0)
					checker[i][j] = new Piece(false, this, i, j, "shield");
				else if(j == 7 && i % 2 == 1)
					checker[i][j] = new Piece(false, this, i, j, "pawn");
				else
					checker[i][j] = null;
			}
		}
	}
	
	private void setConf(){
		for (int j = 0; j < size; j++){
			for (int i = 0; i < size; i++)
			{
				if (checker[i][j] != null){
					if (checker[i][j].isBomb() && checker[i][j].isFire())
						if (checker[i][j].isKing())
							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
						else
							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
					else if (checker[i][j].isBomb() && !checker[i][j].isFire())
						if (checker[i][j].isKing())
							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
						else
							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
					else if (checker[i][j].isShield() && checker[i][j].isFire())
						if (checker[i][j].isKing())
							StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
						else
							StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
					else if (checker[i][j].isShield() && !checker[i][j].isFire())
						if (checker[i][j].isKing())
							StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
						else
							StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
					else{
						if (checker[i][j].isFire())
							if (checker[i][j].isKing())
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
							else
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
						else
							if (checker[i][j].isKing())
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
							else
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);				
					}
				}
			}
		}
	}
	
	private void drawEmpty(){
		for (int i = 0; i < size; i++){
			for (int j = 0; j < size; j++){
				if (i == selectX && j == selectY)
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				else if ((i + j) % 2 == 0) 
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                 
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
			}
		}
	}
	
	public Piece pieceAt(int x, int y){
		if (outOfBound(x, y) || checker[x][y] == null)
			return null;
		return checker[x][y];
	}

	public boolean canSelect(int x, int y){
		if (checker[x][y] != null && (!selected || !moved) && checker[x][y].isFire() == turn)
			return true;
		else if (checker[x][y] == null && selected){
			if (!moved && validMove(selectX, selectY, x, y))
				return true;
			else if (captured && validMove(selectX, selectY, x, y))
				return true;
			else
				return false;
		}
		return false;
	}
	
	private boolean validMove(int xi, int yi, int xf, int yf){
		if (outOfBound(xf, yf) || checker[xf][yf] != null)
			return false;
		if ((xf == xi + 1 || xf == xi - 1) && (yf == yi + 1 || (yf == yi - 1))){
			if (captured)
				return false;
			if (checker[xi][yi].isKing() || (checker[xi][yi].isFire() && yf == yi + 1) || (!checker[xi][yi].isFire() && yf == yi - 1))
				return true;
			return false;		
		}
		else if((xf == xi + 2 || xf == xi - 2) && (yf == yi + 2 || yf == yi - 2)){
			if (checker[Math.abs((xi + xf) / 2)][Math.abs((yi + yf) / 2)] == null)
				return false;
			else if (checker[xi][yi].isKing() || (checker[xi][yi].isFire() && yf == yi + 2) || (!checker[xi][yi].isFire() && yf == yi - 2)){
				if (checker[xi][yi].isFire() && !checker[(xi + xf) / 2][(yi + yf) / 2].isFire())
					return true;
				else if (!checker[xi][yi].isFire() && checker[(xi + xf) / 2][(yi + yf) / 2].isFire())
					return true;
				return false;
			}
			return false;			
		}
		else
			return false;		
	}
	
	public void select(int x, int y){
			if(checker[x][y] != null){
				selected = true;
			}
			else{	
				checker[selectX][selectY].move(x, y);
				moved = true;		
				}
        	selectX = x;
        	selectY = y;
        	if (checker[selectX][selectY] != null && checker[selectX][selectY].hasCaptured()){
        		captured = true;
        	}
		}			
	
	public void place(Piece p, int x, int y){
		if (outOfBound(x, y) || p == null)
			return;
		checker[x][y] = p;
	}
	
	public Piece remove(int x, int y){
		if (outOfBound(x, y)){
			System.out.println("index out of bounds");
			return null;
		}
		if (checker[x][y] == null){
			System.out.println("No piece at place");
			return null;
		}
		Piece temp = checker[x][y];
		checker[x][y] = null;
		return temp;
	}
	
	public boolean canEndTurn(){
		if (moved || captured)
			return true;
		return false;
	}
	
	public void endTurn(){
    	if(checker[selectX][selectY] != null)
    		checker[selectX][selectY].doneCapturing();
		turn = !turn;
		moved = false;
    	captured = false;
    	selected = false;
    	selectX = -1;
    	selectY = -1;
        	
	}
	
	public String winner(){
		int fire = 0;
		int water = 0;
		for(int j = 0; j < size; j++){
			for(int i = 0; i < size; i++){
				if (checker[i][j] != null){
					if (checker[i][j].isFire())
						fire++;
					else
						water++;
				}
			}
		}
		if (water == 0 && fire != 0)
			return "Fire";
		else if (water != 0 && fire == 0)
			return "Water";
		else if (water == 0 && fire == 0)
			return "No One";
		else
			return null;
	}
	
	private boolean outOfBound(int x, int y){
		if (x > 7 || x < 0 || y > 7 || y < 0)
			return true;
		return false;
	}
	
	public static void main (String args[]){
		StdDrawPlus.setXscale(0, size);
        StdDrawPlus.setYscale(0, size);
		Board a = new Board(false);
		while (a.winner() == null){
            while (!StdDrawPlus.isSpacePressed()){
    			a.drawEmpty();
                a.setConf();
				if (StdDrawPlus.mousePressed()) {
	                double x = StdDrawPlus.mouseX();
	                double y = StdDrawPlus.mouseY();
	                int px = (int) x;
	                int py = (int) y;
	                if (a.canSelect(px, py)){
	                	a.select(px, py);
	                }
				}
	            StdDrawPlus.show(100);
            } 
            if (a.canEndTurn()){
            	a.endTurn();
            }
		}
		System.out.println(a.winner());
	}
}
