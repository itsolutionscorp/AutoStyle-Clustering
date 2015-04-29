public class Board{
	
	private static Board board;
	private static int size = 8;
	private Piece[][] pieces =  new Piece[size][size];
	private static boolean[][] select = new boolean [size][size];; 
	private boolean isFireTurn = true;
	private int xSelect;
	private int ySelect;
	private boolean isMove;
	
	private static void drawEmptyBoard() {
		for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
    }
	private static void drawPiece(Piece p, int x, int y){
		String filepath;
        if (p != null){
        	if (p.isFire() == true){
        		if (p.isKing() == true){
        			if (p.isBomb() == true) {
        				filepath = "img/bomb-fire-crowned.png";
        			} else if(p.isShield()==true) {
        				filepath = "img/shield-fire-crowned.png";
        			} else filepath = "img/pawn-fire-crowned.png";
        		} else {
        			 if (p.isBomb() == true) {
        				filepath = "img/bomb-fire.png";
        			} else if(p.isShield()==true) {
        						 filepath = "img/shield-fire.png";
        			} else filepath = "img/pawn-fire.png";
        		}
        	} else {
        		if (p.isKing() == true){
        			if (p.isBomb() == true) {
        				filepath = "img/bomb-water-crowned.png";
        			} else if(p.isShield()==true) {
        				filepath = "img/shield-water-crowned.png";
        			} else filepath = "img/pawn-water-crowned.png";
        		} else {
        			if (p.isBomb() == true) {
        				 filepath = "img/bomb-water.png";
        			} else if(p.isShield()==true) {
        				filepath = "img/shield-water.png";
        			} else filepath = "img/pawn-water.png";
        		}
        	}
        	StdDrawPlus.picture(x + 0.5, y + 0.5, filepath, 1, 1);
        }
	}
	private static void drawIntialPieces(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
            	Piece p = board.pieceAt(i, j);
            	if (p!=null){
            	drawPiece(p, i, j);
            	}
            }
        }
	}

	public Board (boolean shouldBeEmpty){
		isFireTurn = true;
		if (shouldBeEmpty == false){
			for (int i = 0; i < size; i++){
				if(i % 2 == 0){
					pieces[i][0] = new Piece(true, this, i , 0, "pawn");
					pieces[i][2] = new Piece(true, this, i , 2, "bomb");
					pieces[i][6] = new Piece(false, this, i , 6, "shield");
					
				} else {
					pieces[i][1] = new Piece(true, this, i , 1, "shield");
					pieces[i][5] = new Piece(false, this, i , 5, "bomb");
					pieces[i][7] = new Piece(false, this, i , 7, "pawn");
				}
			}
		}
	}
	
	public Piece pieceAt(int x, int y){
		if(x >= size || y >= size || pieces[x][y] == null){
			return null;
		}
		return pieces[x][y];
	}

	public boolean canSelect(int x, int y){
		Piece p = pieceAt(x,y);
		if (p != null){
			if (isFireTurn != p.isFire()){
				return false;
			} else {
				if (select[x][y] == true) {
					if (isMove == true){
						return (p.hasCaptured() == true);
					} else return true;
				} else return true;
			} 
		} else {
			if (select[xSelect][ySelect] == true){
				return validMove(xSelect, ySelect, x, y);
			}
		}
		return false;
	}
	private boolean validMove(int xi, int yi, int xf, int yf){
		if (xf >= size || yf >= size || xf<0 || yf<0){
			return false;
		} else {
			Piece p = pieceAt(xf, yf);
			// check fire pieces valid moves without capture
		if (p == null && pieces[xi][yi].isFire() == true
				&& pieces[xi][yi].isKing()==false && yf == yi+1 && (xf == xi+1 || xf == xi-1)){
		return true;
		}
		if (p == null && pieces[xi][yi].isFire() == true 
				&& pieces[xi][yi].isKing()==true && (yf == yi+1 || yf == yi-1) 
				&& (xf == xi+1 || xf == xi-1)){
		return true;
		}
		// check water pieces valid moves without capture
		if (p == null && pieces[xi][yi].isFire() == false 
				&& pieces[xi][yi].isKing()==false && yf == yi-1 && (xf == xi+1 || xf == xi-1)){
		return true;
		}
		if (p == null && pieces[xi][yi].isFire() == false 
				&& pieces[xi][yi].isKing()==true && (yf == yi-1 || yf==yi+1)
				&& (xf == xi+1 || xf == xi-1)){
		return true;
		}
		// check fire pieces valid moves with capture
		if (p == null && pieces[xi][yi].isFire() == true && pieces[xi+1][yi+1] != null
				&& pieces[xi+1][yi+1].isFire() == false && yf == yi+2 && xf == xi+2){
		return true;
		}
		if (p == null && pieces[xi][yi].isFire() == true && pieces[xi-1][yi+1] != null
				&& pieces[xi-1][yi+1].isFire() == false && yf == yi+2 && xf == xi-2){
		return true;
		}
		if (p == null && pieces[xi][yi].isFire() == true && pieces[xi][yi].isKing()==true
				&& pieces[xi+1][yi-1] != null && pieces[xi+1][yi-1].isFire() == false
				&& yf == yi-2 && xf == xi+2){
		return true;
		}
		if (p == null && pieces[xi][yi].isFire() == true && pieces[xi][yi].isKing()==true
				&& pieces[xi-1][yi-1] != null && pieces[xi-1][yi-1].isFire() == false 
				&& yf == yi-2 && xf == xi-2){
		return true;
		}
		// check water pieces valid moves with capture
		if (p == null && pieces[xi][yi].isFire() == false && pieces[xi-1][yi-1] != null
				&& pieces[xi-1][yi-1].isFire() == true && yf == yi-2 && xf == xi-2){
		return true;
		}
		if (p == null && pieces[xi][yi].isFire() == false && pieces[xi+1][yi-1] != null
				&& pieces[xi+1][yi-1].isFire() == true && yf == yi-2 && xf == xi+2){
		return true;
		}
		if (p == null && pieces[xi][yi].isFire() == false && pieces[xi][yi].isKing()==true
				&& pieces[xi+1][yi+1] != null && pieces[xi+1][yi+1].isFire() == true
				&& yf == yi+2 && xf == xi+2){
		return true;
		}
		if (p == null && pieces[xi][yi].isFire() == false && pieces[xi][yi].isKing()==true
				&& pieces[xi-1][yi+1] != null && pieces[xi-1][yi+1].isFire() == true
				&& yf == yi+2 && xf == xi-2){
		return true;
		}
		}
		return false;
	}
//	private void highlight(){
//		
//	}
	
	public void select(int x, int y){
		select[x][y] = true;
		Piece p = pieceAt(x,y);
		if (p != null && this.canSelect(x,y)){
			select[xSelect][ySelect] = false;
			xSelect = x;
			ySelect = y;
			select[xSelect][ySelect] = true;
		} else if (this.canSelect(x,y)){
			this.pieceAt(xSelect,ySelect).move(x,y);
		}
	}
	public void place(Piece p, int x, int y){
		if(x < size && y <size && x >=0 && y>=0){ 
			if (pieceAt(x,y) != null){
				remove(x,y); 
			}
			pieces[x][y] = p;
//			xMove = x;
//			yMove = y;
			//isMove = true;
			//p.move(x, y);
		} else System.out.println("cannot place at location"+x+y);
	}
	public Piece remove(int x, int y){
		if(x < size && y <size && x >=0 && y>=0){
		Piece p = pieceAt(x,y);
			if (p!= null){
//				xSelect = x;
//				ySelect = y;
				isMove = true;
				pieces[x][y] = null;
				return p;
			} else {
				System.out.println("there is no piece at: "+x +"," +y);
				return p;
			}
		} else {
			System.out.println("the location is out of bound");
			return null;
		}
	}
	public boolean canEndTurn(){
		return isMove || pieces[xSelect][ySelect].hasCaptured();
	}
	public void endTurn(){
		if (canEndTurn()){
			isFireTurn = !isFireTurn;
			isMove = false;
		}
	}
	public String winner(){
		int countFire =0 ;   
		int countWater = 0;
		for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
            	Piece p = pieceAt(i,j);
            	if (p!= null){
            		if(p.isFire()==true){
            			countFire++;
            		} else countWater++;
            	}
            }
		}
		if (countFire == 0 && countWater == 0){
			return "No one";
		} else if (countFire == 0 || countWater > countFire){
			return "Water";
		} else if (countWater == 0 || countWater < countFire){
			return  "Fire";
		}
		return null;
	}
	public static void main(String[] args) {
        StdDrawPlus.setXscale(0, size);
        StdDrawPlus.setYscale(0, size);
        board = new Board(false);
        drawEmptyBoard();
        drawIntialPieces();
        while(true) {
           if (StdDrawPlus.mousePressed()) {
                double xClick = StdDrawPlus.mouseX();
                double yClick = StdDrawPlus.mouseY();
                int x = (int)xClick;
                int y = (int)yClick;
                board.select(x,y);
                if (StdDrawPlus.isSpacePressed()){
             	   if(board.canEndTurn()){
             		   board.endTurn();  
             	   }
                }
            }
           
            StdDrawPlus.show(100);
        }  
    }
}