import java.util.HashMap;
import java.util.HashSet;



public class Board implements Comparable<Board>{
	
	boolean[][] board;
	HashSet<Block> blocks;
	Board prevBoard; 
	String move;
	HashMap<Block,boolean[]> validMoves;
	
	int Bheight;
	int Bwidth;
	int distance;
	int hashnum;
	
	public Board(int height, int width){
		blocks = new HashSet<Block>();
		board = new boolean[height][width];
		validMoves = new HashMap<Block,boolean[]>();
		Bheight = height;
		Bwidth = width;
		distance = 0;
		hashnum = 0;
		
	}
	
	public static int calcDistance(Board b, Board end) {
		if (b.distance == 0) {
		int endDistance = 0;
		for (Block bl : end.blocks) {
			endDistance+=bl.myX1;
			endDistance+=bl.myY1;
			endDistance+=bl.myX2;
			endDistance+=bl.myY2;
		}
		
		int distance = 0;
		for (Block bl: b.blocks) {
			distance+= bl.myX1;
			distance+= bl.myY1;
			distance+= bl.myX2;
			distance+= bl.myY2;
	}
	
		int totalD = Math.abs(endDistance - distance);
		b.distance = totalD;
		}
		return b.distance;
	}

	
	public void placeBlock(Block b){
		for (int i = b.myY1; i <= b.myY2; i++){
			for (int j = b.myX1; j <= b.myX2; j++){
				if(board[i][j]){
					System.out.println("Invalid init and/or goal file.");
					System.exit(0);
				}
				board[i][j]=true;
			}
		}
		
		blocks.add(b);
	}
	
	public Board makeAMove(Block v, int d){ //arg will be representation of move and will return an updated board
		Board nextB = new Board(this.Bheight, this.Bwidth);
		
		nextB.prevBoard = this;
		String m = v.myY1 + " " + v.myX1 + " ";
		

		
		for(Block b: this.blocks){
			if (b==v){
				
				if (d==0){
					Block mu = new Block(b.myY1-1, b.myX1, b.myY2-1, b.myX2);
					nextB.placeBlock(mu);
					m+= (b.myY1-1) + " " + b.myX1;
				}else if (d==1){
					nextB.placeBlock(new Block(b.myY1, b.myX1+1, b.myY2, b.myX2+1));
					m+= b.myY1 + " " + (b.myX1+1);
				}else if (d==2){
					nextB.placeBlock(new Block(b.myY1+1, b.myX1, b.myY2+1, b.myX2));
					m+= (b.myY1+1) + " " + b.myX1;
				}else if (d==3){
					nextB.placeBlock(new Block(b.myY1, b.myX1-1, b.myY2, b.myX2-1));
					m+= b.myY1 + " " + (b.myX1-1);
				}
				
				nextB.move = m;
			}else{
				nextB.placeBlock(new Block(b.myY1, b.myX1, b.myY2, b.myX2));
			}
		}
		
		return nextB;
		
	}
	
	public void potentialMoves(){
			
		for (Block b: blocks){
			boolean[] directions = new boolean[4]; 
			boolean movable = false;
			if(canUp(b)){
				directions[0]=true;
				movable = true;
			}if(canRight(b)){
				directions[1]=true;
				movable = true;
			}if (canDown(b)){
				directions[2]=true;
				movable=true;
			}if (canLeft(b)){
				directions[3]=true;
				movable=true;
			}if (movable){
				validMoves.put(b, directions);
			}			
		}
		
	if (validMoves.isEmpty()){
	}
		
	}
	
	private boolean canUp(Block b){
		
		int row = b.myY1-1;
		if (row < 0){
			
			return false;
			
		}
		
		for (int i = b.myX1; i <= b.myX2; i++ ){
			if(board[row][i]){
				return false;
			}
		}
		return true;
	}
	
	private boolean canDown(Block b){//y2+1
		int row = b.myY2+1;
		if (row > Bheight-1){
			return false;
		}
		
		for (int i = b.myX1; i <= b.myX2; i++ ){
			if(board[row][i]){
				return false;
			}
		}
		
		return true;
		
	}
	
	private boolean canLeft(Block b){
		int column = b.myX1-1;
		if (column < 0){
			return false;
		}
		for (int i = b.myY1; i <= b.myY2; i++){
			if (board[i][column]){
				return false;
			}
		}
		return true;
	}
	
	private boolean canRight(Block b){
		int column = b.myX2+1;
		if (column > Bwidth-1){
			return false;
		}
		for (int i = b.myY1; i <= b.myY2; i++){
			if (board[i][column]){
				return false;
			}
		}
		return true;
	}

	@Override
	public int compareTo(Board b){
		if (distance < b.distance){
			return -1;
		}else if (distance == b.distance){
			return 0;
		}else{
			return 1;
		}
	}

	@Override
	public int hashCode() {
		int h = 7;
		if (hashnum == 0) {

			for (Block b : this.blocks) {
				h += b.hashCode();
			}
			hashnum = h;

		}
		return hashnum;
		
	}
	
	@Override 
	public boolean equals(Object o){
		if (o.hashCode() != hashCode() || o.getClass() != getClass()) {
			return false;
		}
		for (Block b : this.blocks){
			if (!((Board) o).blocks.contains(b)){
				return false;
			}
		}
		return true;
	}

	
}