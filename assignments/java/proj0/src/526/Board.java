public class Board{
	
	private Piece [][] arr;
	private boolean emptyBoard;
	private boolean playerPieceSelected;
	private boolean playerPieceMoved;
	private int csx; //currentSelectedX;
	private int csy; //currentSelectedY;
	private boolean alreadyCap;
	private String currColor = "fire";
	private Piece tempo;

	public static void main (String [] args){
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        //Piece [][] pieces = new Piece[N][N];

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        Board n = new Board(false);
        while(true) {
            n.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if(n.canSelect((int)x,(int)y)){
                	n.select((int)x,(int)y);
                }
            } 
            if (StdDrawPlus.isSpacePressed()) {
                if(n.canEndTurn())
                	n.endTurn();
             }       
            StdDrawPlus.show(100);
        }
	}

	public Board(boolean shouldBeEmpty){
		emptyBoard = shouldBeEmpty;
		if(emptyBoard)
			arr = new Piece[8][8];
		else{
			arr = new Piece[8][8];
			arr[0][0] = new Piece(true,this,0,0,"pawn");
			arr[2][0] = new Piece(true,this,2,0,"pawn");
			arr[4][0] = new Piece(true,this,4,0,"pawn");
			arr[6][0] = new Piece(true,this,6,0,"pawn");
			arr[1][1] = new Piece(true,this,1,1,"shield");
			arr[3][1] = new Piece(true,this,3,1,"shield");
			arr[5][1] = new Piece(true,this,5,1,"shield");
			arr[7][1] = new Piece(true,this,7,1,"shield");
			arr[0][2] = new Piece(true,this,0,2,"bomb");
			arr[2][2] = new Piece(true,this,2,2,"bomb");
			arr[4][2] = new Piece(true,this,4,2,"bomb");
			arr[6][2] = new Piece(true,this,6,2,"bomb");

			arr[1][5] = new Piece(false,this,1,5,"bomb");
			arr[3][5] = new Piece(false,this,3,5,"bomb");
			arr[5][5] = new Piece(false,this,5,5,"bomb");
			arr[7][5] = new Piece(false,this,7,5,"bomb");
			arr[0][6] = new Piece(false,this,0,6,"shield");
			arr[2][6] = new Piece(false,this,2,6,"shield");
			arr[4][6] = new Piece(false,this,4,6,"shield");
			arr[6][6] = new Piece(false,this,6,6,"shield");
			arr[1][7] = new Piece(false,this,1,7,"pawn");
			arr[3][7] = new Piece(false,this,3,7,"pawn");
			arr[5][7] = new Piece(false,this,5,7,"pawn");
			arr[7][7] = new Piece(false,this,7,7,"pawn");
			
		}
		playerPieceSelected = false;
		playerPieceMoved = false;
		alreadyCap = false; // has captured a piece
		csx = -1;
		csy = -1;
	}
	public Piece pieceAt(int x, int y){
		if(x<0 || x>7 || y <0 || y>7) return null;
		else return arr[x][y];
	}
	public boolean canSelect(int x,int y){
		if(x<0 || x>7 || y <0 || y>7) return false;
		//System.out.println(playerPieceSelected + " :: " + playerPieceMoved + " ::");
		if(arr[x][y] != null)
		{
			if(arr[x][y].isFire() && currColor.equals("water"))
				return false;
			if(!arr[x][y].isFire() && currColor.equals("fire"))
				return false;
			if(!playerPieceSelected || (playerPieceSelected && !playerPieceMoved)){
				//System.out.println("PPS" + playerPieceSelected + "PPM" + playerPieceMoved + "V" + validMove(csx,csy,x,y));
				return true;
			}
		}
		else{
			if(playerPieceSelected && !playerPieceMoved && validMove(csx,csy,x,y)){
				//System.out.println("PPS" + playerPieceSelected + "PPM" + playerPieceMoved + "V" + validMove(csx,csy,x,y));
				return true;
			}
			if(Math.abs(csx-x) == 2 && Math.abs(csy-y) == 2){
			if(playerPieceSelected && alreadyCap && validMove(csx,csy,x,y)){
				//System.out.println("PPS" + playerPieceSelected + "PPM" + playerPieceMoved + "V" + validMove(csx,csy,x,y));
				return true;
			}
			}
		}

		return false;
	}
	private boolean validMove(int xi, int yi, int xf, int yf){
		if(xi<0 || xi>7 || yi <0 || yi>7) return false;
		if(xf<0 || xf>7 || yf <0 || yf>7) return false;
		//other select factors
		if(arr[xi][yi] == null) return false;
		if(arr[xf][yf] != null) return false;

		if(arr[xi][yi].isFire() && currColor.equals("water"))
			return false;
		if(!arr[xi][yi].isFire() && currColor.equals("fire"))
			return false;
		int val = 1;
		int xx = (xf + xi) >>> 1;
		int yy = (yf + yi) >>> 1;
		if(currColor.equals("water")) val = -1;

		if(yf-yi == 1*val && Math.abs(xf-xi) == 1){
			return true;
		}
		else if(yf-yi == 2*val && Math.abs(xf-xi) == 2){
			
			if(arr[xx][yy] != null){
				if(!arr[xx][yy].isFire() && currColor.equals("fire"))
					return true;
				if(arr[xx][yy].isFire() && currColor.equals("water"))
					return true;
			}
		}
		else if(Math.abs(yf-yi) == 1 && Math.abs(xf-xi) == 1 && arr[xi][yi].isKing()){
			return true;
		}
		else if(Math.abs(yf-yi) == 2 && Math.abs(xf-xi) == 2 && arr[xi][yi].isKing()){
			if(arr[xx][yy] != null)
			{
				if(!arr[xx][yy].isFire() && currColor.equals("fire"))
					return true;
				if(arr[xx][yy].isFire() && currColor.equals("water"))
					return true;
			}
		}
		return false;
	}
	public void select(int x,int y){
 		//work
 		if(pieceAt(x,y) != null){
 			//System.out.println("ready");
 			csx = x;
 			csy = y;
 			playerPieceSelected = true;

 		}
 		else{

 			//System.out.println("moved");
 			playerPieceMoved = true;
 			tempo = arr[csx][csy];
 			tempo.move(x,y);
 			alreadyCap = tempo.hasCaptured();
 			if(!tempo.isBomb())
 				arr[x][y] = arr[csx][csy];
 			else if(alreadyCap)
 			{
 				arr[x][y] = null;
 			}
 			else
 				arr[x][y] = arr[csx][csy];
 			arr[csx][csy] = null;
 			csx = x;
 			csy = y;

 		}
	}
	public void place (Piece p, int x, int y){
		if(x<0 || x>7 || y <0 || y>7){
			return;
		}
		arr[x][y] = p;
	}
	public Piece remove(int x,int y){
		Piece z = pieceAt(x,y);
		if(x<0 || x>7 || y <0 || y>7){
			System.out.println("Error: Out of Bounds");
			return null;
		}
		if(z == null)
			System.out.println("Error: No Piece there");
		arr[x][y] = null;
		return z;
	}
	public boolean canEndTurn(){
		return playerPieceSelected && playerPieceMoved;
	}
	public void endTurn(){
		//System.out.println("ending turn");
		if(tempo != null)
			tempo.doneCapturing();
		playerPieceSelected = false;
		playerPieceMoved = false;
		playerPieceMoved = false;
		if(currColor.equals("fire"))
			currColor = "water";
		else
			currColor= "fire";
		csx = -1;
		csy = -1;
	}
	public String winner(){
		int redcount = 0;
		int bluecount = 0;
		for(int q=0;q<8;q++){
			for(int s=0;s<8;s++){
				Piece lol = pieceAt(q,s);
				if(lol != null && lol.isFire())
					redcount ++;
				else if(lol != null && !lol.isFire())
					bluecount++;

			}
		}
		if(redcount>0 && bluecount>0)
			return null;
		else if(redcount > 0)
			return "Fire";
		else if(bluecount >0)
			return "Water";
		else if(redcount == 0 && bluecount == 0)
			return "No one";
		return null;
	}


private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                if(arr[i][j] == null) continue;
                Piece a = arr[i][j];
                String sketch = "";

                if(a.isBomb()) sketch = "bomb-";
                else if(a.isShield()) sketch = "shield-";
                else sketch = "pawn-";

                if(a.isFire())
                	sketch = sketch + "fire";
                else
                	sketch = sketch + "water";

                if(a.isKing())
                	sketch = sketch + "-crowned";
                StdDrawPlus.picture(i + .5, j + .5, "img/" + sketch + ".png", 1, 1); 
                
            }
        }
    }



}
