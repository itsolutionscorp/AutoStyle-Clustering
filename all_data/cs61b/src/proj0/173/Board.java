
public class Board {
	
	private static final int N = 8;
	private static final boolean debugEnabled = false;
	private Piece[][] pieces = new Piece[N][N]; 
	private int[] selected = {-1,-1};
	private boolean isFireTurn = true;
	private Piece lastMoved = null;
	private Piece selectedPiece = null;
	private int firePieceCount, waterPieceCount;
	
	/** Official API Implementation **/
	
	public Board(boolean shouldBeEmpty){
		if(!shouldBeEmpty)
			initFullBoard();
	}
		
	public Piece pieceAt(int x, int y){
		if(x>=N || y>=N || x < 0 || y < 0)
			return null;
		return pieces[x][y];
	}
	
	public boolean canSelect(int x, int y){
		Piece p = pieceAt(x,y);
		
		//clicked on empty space 
		if(p == null){
			//if previously selected something, then check if valid move
			if(hasSelected()){
				return validMove(selected[0],selected[1],x,y);
			}
			else
				return false;			
		}
		//clicked on piece
		else{
			//can't select opponent's pieces
			if(p.isFire() != isFireTurn)
				return false;
			//can only select piece if haven't moved a piece yet
			if(lastMoved == null)
				return true;
		}
		return false;
	}
	
	public void select(int x, int y){
		//if clicking on empty space
		if(pieceAt(x,y) == null){
			selectedPiece.move(x, y);
			lastMoved = selectedPiece;
		}
		setSelected(x,y);
	}
	
	public void place(Piece p, int x, int y){
		if(x < 8 && y < 8 && p != null){
			int[] loc = locationOfPiece(p);
			remove(loc[0],loc[1]);
			remove(x,y);
			pieces[x][y] = p;
			if(p.isFire())
				firePieceCount++;
			else
				waterPieceCount++;
		}
	}
	
	public Piece remove(int x, int y){
		if(x>=0 && x <N && y>=0 && y<N){
			Piece p = pieceAt(x,y);
			if(p != null){
				pieces[x][y] = null;
				if(p.isFire())
					firePieceCount--;
				else
					waterPieceCount--;
				return p;
			}
			else
				System.out.println("No piece found");
			return null;
		}
		System.out.println("Out of bounds");
		return null;
	}
	
	public boolean canEndTurn(){
		return lastMoved != null;
	}
	
	public void endTurn(){
		lastMoved.doneCapturing();
		lastMoved = null;
		unSetSelected();
		isFireTurn = !isFireTurn;
	}
	
	public String winner(){
		if(firePieceCount == 0 && waterPieceCount == 0)
			return "No one";
		if(firePieceCount == 0)
			return "Water";
		else if(waterPieceCount == 0)
			return "Fire";
		return null;
	}
	
	/** Helper Methods **/
	
	private static void debugOut(String s){
		if(debugEnabled)
			System.out.println(s);
	}
	
	private boolean validMove(int xi, int yi, int xf, int yf){
		//if x and y values are out of bounds, false
		if(!inRange(xi,yi) || !inRange(xf,yf)){
			debugOut("x and y are out of bounds");
			return false;
		}
			
		//if xf and yf is not empty, false
		if(pieceAt(xf,yf) != null){
			debugOut("destination is not empty");
			return false;
		}
			
		//if initial and final are the same, false
		if(xi == xf && yi == yf){
			debugOut("can't move to the same place");
			return false;
		}
		
		//if not moving anything, false
		Piece movingPiece = pieceAt(xi,yi);
		if(movingPiece == null){
			debugOut("cannot move nothing");
			return false;
		}
		
		int xDiff = xf-xi;
		int yDiff = yf-yi;
		int xDiffAbs = Math.abs(xDiff);
		int yDiffAbs = Math.abs(yDiff);
		
		//check forward range
		if(yDiffAbs > 2 || xDiffAbs > 2){
			debugOut("cannot move beyond 2 spaces");
			return false;
		}
					
		//check backward range
		if(movingPiece.isFire() && yDiff < 0 || !movingPiece.isFire() && yDiff > 0)
			if(!movingPiece.isKing()){
				debugOut("trying to go back as non king");
				return false;
			}
		
		//check capture
		if(xDiffAbs == yDiffAbs && xDiffAbs == 2){
			Piece potentialCapture = pieceAt(xi+xDiff/2,yi+yDiff/2);
			//valid move b/c valid capture
			return potentialCapture != null && potentialCapture.isFire() != movingPiece.isFire();
		}
		
		//already moved a piece and new dest is not a capture
		if(lastMoved != null)
			return false;
		
		//movement check. king water and fire are the same
		if(movingPiece.isKing()){
			//kings can move diagonally by 1 unit
			if(xDiffAbs == yDiffAbs && xDiffAbs == 1)
				return true;			
		}
		//for non king pieces
		else{
			//for fire pieces
			if(movingPiece.isFire()){
				return xDiffAbs == 1 && yDiff == 1;
			}
			//for water pieces
			else{
				return xDiffAbs == 1 && yDiff == -1;
			}
		}
				
		//false for all other scenarios
		debugOut("triggered the edge false");
		return false;
	}	
	
	private boolean inRange(int x, int y){
		return x >= 0 && x < N && y >= 0 && y < N;
	}
	
	private int[] locationOfPiece(Piece p){
		int[] result = {-1,-1};
		for(int i = 0;i<N;i++){
			for(int j = 0;j<N;j++){
				if(pieceAt(i,j) == p){
					remove(i,j);
					result[0] = i;
					result[1] = j;
					i=N;
					j=N;
				}
			}
		}
		return result;
	}
	
	//Select helper methods
	private void setSelected(int x, int y){
		unSetSelected();
		selectedPiece = pieceAt(x,y);
		selected[0] = x;
		selected[1] = y;
	}
	private void unSetSelected(){
		selectedPiece = null;
		selected[0] = -1;
		selected[1] = -1;
	}
	private boolean hasSelected(){
		return selected[0] != -1 && selected[1] != -1 && selectedPiece != null;
	}
	
	//Drawing related
	private static String getImagePath(Piece p){
		String fileName = "";
		if(p.isBomb())
			fileName += "bomb";
		else if(p.isShield())
			fileName += "shield";
		else
			fileName += "pawn";
		
		if(p.isFire())
			fileName += "-fire";
		else
			fileName += "-water";
		
		if(p.isKing())
			fileName += "-crowned";
		
		return "img/"+fileName+".png";
	}
	
	private void initFullBoard(){
		for(int i = 0;i<N;i++){
			if(i == 0)
				populateRow(i,0,true,"pawn");
			else if(i==1)
				populateRow(i,1,true,"shield");			
			else if(i==2)
				populateRow(i,0,true,"bomb");
			else if(i == 5)
				populateRow(i,1,false,"bomb");
			else if(i == 6)
				populateRow(i,0, false,"shield");
			else if(i == 7)
				populateRow(i,1,false,"pawn");
		}	
	}
	
	private void populateRow(int i, int start, boolean isFire, String type){
		for(int j = start; j<N;j+=2)
			place(new Piece(isFire,this,j,i,type),j,i);
	}
	
	private void drawBoard(){
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0)
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if(hasSelected()){
                	if(i == selected[0] && j == selected[1]){
                		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                		StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                	}
                }
                if (pieces[i][j] != null) 
                    StdDrawPlus.picture(i + .5, j + .5, getImagePath(pieces[i][j]), 1, 1);
            }
        }
	}
	
	/** Main **/
	
	public static void main(String[] args) {
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        Board b = new Board(false);
        while(b.winner() == null){
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if(b.canSelect(x,y))
                	b.select(x,y);
            }            
            if(StdDrawPlus.isSpacePressed()){
            	if(b.canEndTurn())
            		b.endTurn();
            }
            b.drawBoard();
            StdDrawPlus.show(20);
        }
	}
}
