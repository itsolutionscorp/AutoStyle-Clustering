
public class Board{

	public static void main(String[] args){
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);
        int x1, y1;
    
        while(true){	
        	b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                x1 = (int) StdDrawPlus.mouseX();
                y1 = (int) StdDrawPlus.mouseY();
                if(b.canSelect(x1, y1)){
	                b.select(x1, y1);
	            }    	
            }
            if(StdDrawPlus.isSpacePressed() && b.canEndTurn()){
            	b.endTurn();
            }

        	StdDrawPlus.show(25);
        }
	}
	
	private boolean[][] selected = new boolean[8][8];
    private Piece[][] pieces = new Piece[8][8];
    private Piece current = null;
    private boolean fireTurn;
    private boolean end = false;
    private int selectedX, selectedY;
	private int numWater = 0;
	private int numFire = 0;
	private boolean hasMoved, hasJumped;
	
	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	setColor(i, j);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (this.pieces[i][j] != null) {
                	Piece p =  this.pieces[i][j];
                	drawPiece(i, j, p);
                	if(this.selected[i][j]){
                		StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
                		drawPiece(i, j, this.pieces[i][j]);
                	}
 
                }
            }
        }

    }
	
	private void setColor(int x, int y){
        if ((x + y) % 2 == 0){
        	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        }
        else{
        	StdDrawPlus.setPenColor(StdDrawPlus.BOOK_RED);
        }
	}
	
	private void drawPiece(int x, int y, Piece p){
		String s;
		if(p.isFire()){
    		if(p.isBomb()){
                s = "img/bomb-fire.png";
        	}else if(p.isShield()){
        		s ="img/shield-fire.png";
        	}else{
        		s = "img/pawn-fire.png";
        	}
    	}else{
    		if(p.isBomb()){
    			s = "img/bomb-water.png";
        	}else if(p.isShield()){
                s = "img/shield-water.png";
        	}else{
        		s = "img/pawn-water.png";
        	}
    	}
		if(p.isKing()){
			s = s.substring(0, s.length()-4 ).concat("-crowned" + s.substring(s.length()-4, s.length()));
		}
        StdDrawPlus.picture(x + .5, y + .5, s, 1, 1);
	}
	
	public Board(boolean shouldBeEmpty){
		this.fireTurn = true;
		this.resetSelected();
		if(!shouldBeEmpty){
			for(int i = 0; i < 8; i++){
				for(int j = 0; j < 8; j++){
					if((i + j) %2 == 0 && j < 3){
						this.numFire += 1;
						if(j == 0){
							this.pieces[i][j] = new Piece(true, this, i, j, "pawn");
						}else if(j == 1){
							this.pieces[i][j] = new Piece(true, this, i, j, "shield");
						}else{
							this.pieces[i][j] = new Piece(true, this, i, j, "bomb");
						}
					}else if((i + j)%2 == 0 && j > 4){
						this.numWater += 1;
						if(j == 5){
							this.pieces[i][j] = new Piece(false, this, i, j, "bomb");
						}else if(j == 6){
							this.pieces[i][j] = new Piece(false, this, i, j, "shield");
						}else{
							this.pieces[i][j] = new Piece(false, this, i, j, "pawn");
						}
					}
				}
			}
		}
	}
	
	public Piece pieceAt(int x, int y){
		if (x >= 0 && x < 8 && y >= 0 && y < 8){
			return pieces[x][y];
		}
		return null;
	}
	
	public boolean canSelect(int x, int y){
		if(x < 0 || x > 7 || y < 0 || y > 7){
			return false;
		}


		if(this.hasJumped && x == selectedX && y == selectedY){
			return false;
		}

		if(this.hasMoved){
			return false;
		}

		if(this.becameKing){
			return false;
		}
		
		if(pieceAt(x, y) != null){
			if(this.fireTurn && pieces[x][y].isFire()){
				return true;
			}else if(!this.fireTurn && !pieces[x][y].isFire()){
				return true;
			}
		}else{
			if(validMove(selectedX, selectedY, x, y)){
				return true;
			}
		}
		return false;
	}
	
	private boolean validMove(int xi, int yi, int xf, int yf){
		if(xf > 7 || yf > 7 || yf < 0 || xf < 0){
			return false;
		}
		Piece p = pieceAt(xi, yi);
		if (p == null){
			return false;
		}
		if(pieceAt(xf, yf) == null){
			return moveCase(p, xi, yi, xf, yf) || jumpCase(p, xi, yi, xf, yf);
		}
		else{
			return false;
		}
	}
	
	private boolean jumpCase(Piece p, int xi, int yi, int xf, int yf){
		if(xf < 0 || xf > 7 || yf < 0 || yf > 7){
			return false;
		}

		
		if(pieceAt(xf, yf) != null){
			return false;
		}
		
		if((p.isFire()||(p.isKing() && !p.isFire())) && (xf == xi + 2 && yf == yi + 2)){
			if(pieceAt(xi + 1, yi + 1) != null && (!pieceAt(xi + 1, yi + 1).isFire() == p.isFire())){
				return true;
			}else{
				return false;
			}
		}else if((p.isFire()||(p.isKing() && !p.isFire())) && (xf == xi - 2 && yf == yi + 2)){
			if(pieceAt(xi - 1, yi + 1) != null && (!pieceAt(xi - 1, yi + 1).isFire() == p.isFire())){
				return true;
			}else{
				return false;
			}
		}else if((!p.isFire()||(p.isKing() && p.isFire())) && (xf == xi + 2 && yf == yi - 2)){ 
			if(pieceAt(xi + 1, yi - 1) != null && (!pieceAt(xi + 1, yi - 1).isFire() == p.isFire())){
				return true;
			}else{
				return false;
			}
		}else if((!p.isFire()||(p.isKing() && p.isFire())) && (xf == xi - 2 && yf == yi - 2)){
			if(pieceAt(xi - 1, yi - 1) != null && (!pieceAt(xi - 1, yi - 1).isFire() == p.isFire())){
				return true;
			}else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	
	private boolean moveCase(Piece p, int xi, int yi, int xf, int yf){
		if(this.hasJumped){
			return false;
		}
		if((p.isFire()||(p.isKing() && !p.isFire())) && ((xf == xi + 1 && yf == yi + 1) || (xf == xi - 1 && yf == yi + 1))){
			return true;
		}
		else if((!p.isFire()||(p.isKing() && p.isFire())) && ((xf == xi + 1 && yf == yi - 1)||(xf == xi - 1 && yf == yi -1))){
			return true;
		}else{
			return false;
		}		
	}
	
	private boolean canJumpAgain(Piece p, int x, int y){
		if(this.hasJumped){
			return jumpCase(p, x, y, x + 2, y + 2)||jumpCase(p, x, y, x - 2, y + 2)||jumpCase(p, x, y, x + 2, y - 2)||jumpCase(p, x, y, x - 2, y - 2);
		}
		return false;
	}
	
	private void resetSelected(){
		for(int i = 0; i < 8; i ++){
			for(int j = 0; j < 8; j++){
				selected[i][j] = false;
			}
		}
	}
 
	private boolean becameKing = false;

	public void select(int x, int y){
		resetSelected();
		selected[x][y] = true;
		if(pieceAt(x, y) != null){
			selectedX = x;
			selectedY = y;
			this.current = pieceAt(x, y);
		}else{
			if(this.current != null && !this.current.isKing()){
				if(this.current.isFire() &&  y == 7 || !this.current.isFire() && y == 0){
 					this.becameKing = true;
				}
			}

			if(this.current != null){
				this.current.move(x, y);
			}
			if(Math.abs(selectedX - x) == 1 && Math.abs(selectedY - y) == 1){
				this.hasMoved = true;
			}else if(Math.abs(selectedX - x) == 2 && Math.abs(selectedY - y) == 2){
				this.hasJumped = true;
			}
			selectedX = x;
			selectedY = y;
			this.end = true;

		}
	}
	 
	public void place(Piece p, int x, int y){
		if(p == null || x < 0 || x > 7 || y < 0 || y > 7){
			return;
		}
		if(p.isFire()){
			this.numFire += 1;
		}else{
			this.numWater += 1;
		}
		this.pieces[x][y] = p;
	}
	
	public Piece remove(int x, int y){
		if(x < 0 || x > 7 || y < 0 || y > 7){
			System.out.println("Out of bounds");
			return null;
		}
		if(pieceAt(x, y)!= null){
			Piece p = pieceAt(x, y);
			if(p.isFire()){
				this.numFire -= 1;
			}else{
				this.numWater -= 1;
			}
			pieces[x][y] = null;
			return p;
		}else{
			System.out.println("No piece at this location");
			return null;
		}
	}
	
	public boolean canEndTurn(){
		return this.end;
	}
	
	public void endTurn(){
		this.resetSelected();
		this.becameKing = false;
		this.fireTurn = !this.fireTurn;
		if(this.current != null){
			this.current.doneCapturing();
		}
		this.end = false;
		this.hasMoved = false;
		this.hasJumped = false;
	}

	public String winner(){
		if(this.numFire == 0 && this.numWater > 0){
			return "Water";
		}else if(this.numWater == 0 && this.numFire > 0){
			return "Fire";
		}else if(this.numFire == 0 && this.numWater == 0){
			return "No one";
		}else{
			return null;
		}
	}
}