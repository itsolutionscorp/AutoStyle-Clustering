import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.function.Consumer;

public class Board {

	/**
	 * @param args
	 */
	private int pieceTypes; // number of types of pieces
	private ArrayList<Piece> pieces;// all blocks
	private int[] myPrevMove;
	private Piece[][] occupied;// entire board
	private ArrayList<int[]> spaces;
	private Board myPrev;
	private int breadth;//x
	private int length; //y
	private boolean samePieces;
	private ArrayList<int[]> goal;
	
	/*
	 * getter funtion
	 */
	public int[] myPrevPath(){return myPrevMove;}
	
	public Board(){
		
	}
	/*
	 * constructor for the given configuration
	 */
	public Board(int[] size, ArrayList<int[]> blocks, ArrayList<int[]> goal) {
		this.goal = goal;
		breadth = size[0];
		length = size[1];
		pieces=new ArrayList<Piece>();
		occupied = new Piece[size[0]][size[1]];
		for (int[] piece : blocks) {
			Piece p = new Piece(piece);
			pieces.add(p);
			for (int i = piece[0]; i <= piece[2]; i++) {
				for (int j = piece[1]; j <= piece[3]; j++) {
					occupied[i][j] = p;
				}
			}
		}
		spaces = new ArrayList<int[]>();
		for (int i = 0; i < size[0]; i++) {
			for (int j = 0; j < size[1]; j++) {
				if (occupied[i][j] == null) {
					int[] space = { i, j };
					spaces.add(space);
				}
			}
		}
		int breadth = pieces.get(0).breadth();
		int length = pieces.get(0).length();
		samePieces=true;
		for(Piece p: pieces){
			if(p.breadth()!=breadth||p.length()!=length){
				samePieces= false;
				break;
			}
		}
	}
	
	/*
	 * getter method for pieces
	 */
	public ArrayList<Piece> pieces(){
		return pieces;
	}
	
	/*
	 * getter method for occupied
	 */
	public Piece[][] occupied(){
		return occupied;
	}
	
	/*
	 * constructs a board given a previous board and a move,
	 * assumes the move is valid
	 */
	public Board(Board b,int[] move) {
		this.goal = b.goal;
		this.samePieces = b.samePieces;
		this.length = b.length;
		this.breadth = b.breadth;
		this.pieces = new ArrayList<Piece>();
		Piece temp = null;
		this.occupied = new Piece[b.breadth][b.length];
		for(Piece p: b.pieces()){
			if(p.getX()==move[0]&&p.getY()==move[1]){
				temp = p.moveHelper(move);
			//	if(!inBound(temp.getX(),temp.getY())){
			//	}
			}else{
			    temp = new Piece(p);
			}
			for(int i = temp.getX(); i<temp.getX()+temp.breadth(); i++){
				for(int j= temp.getY(); j<temp.getY()+temp.length(); j++){
					this.occupied[i][j]=temp;
				}
			}
			pieces.add(temp);
		}
		
		this.spaces = new ArrayList<int[]>();
		for(int[] space: b.spaces){
			if(get(space[0], space[1])==null){
				int[] newSpace = new int[2];
				newSpace[0]=space[0];
				newSpace[1]=space[1];
				spaces.add(newSpace);
			}else {
				int[] newSpace = new int[2];
				newSpace = helper(space, move);
				spaces.add(newSpace);
			}
		}
		this.myPrevMove = move;
		this.myPrev = b;
	}
	
	/*
	 * return the place of the space before move
	 */
	public int[] helper(int[] space, int[] move){
		int direction = 0;
		int[] result= new int[2];
		if(move[0]!=move[2]){
			direction = move[0]-move[2];
			if(direction>0) result[0] = get(space[0], space[1]).getX()+get(space[0], space[1]).breadth();
			else result[0] = get(space[0], space[1]).getX()-1;
			result[1]=space[1];
		}else{
			direction = move[1]-move[3];
			if(direction>0) result[1] = get(space[0], space[1]).getY()+get(space[0], space[1]).length();
			else result[1] = get(space[0], space[1]).getY()-1;
			result[0]=space[0];
		}
		return result;
	}
	
	/*
	 * put a piece to the board
	 */
	public void addPiece(Piece p){
		for(int i=p.getX(); i<p.getX()+p.breadth; i++){
			for(int j=p.getY(); j<p.getY()+p.length; j++){
				occupied[i][j]=p;
			}
		}
	}
	
	/*
	 * remove a piece from a board
	 */
	public void removePiece(Piece p){
		for(int i=p.getX(); i<p.getX()+p.breadth; i++){
			for(int j=p.getY(); j<p.getY()+p.length; j++){
				occupied[i][j]=null;
			}
		}
	}
	
	/*
	 * getter for space
	 */
	public ArrayList<int[]> spaces(){return spaces;}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Board) {
			Board b = (Board) o;
			boolean result = true;
			Piece otherPiece;
			for (Piece p: pieces){
				otherPiece=b.get(p.getX(), p.getY());
				if(otherPiece==null)return false;
				if(otherPiece.getX()!=p.getX()||otherPiece.getY()!=p.getY()){
					result = false; 
				    break;
				}
			}
			return result;
		}return false;
	}
	
	/*
	 * getter got previous board
	 */
	public Board myPrev(){return myPrev;}
	
	/*
	 * for experiment, see our design document
	 */
//	@Override 
//	public int hashCode(){
//		String code = new String();
//		for(int i=0; i<occupied.length; i++){
//			for(int j = 0; j<occupied[0].length; j++){
//				if(occupied[i][j]==null)code +="00";
//				else code+=occupied[i][j].length();
//			}
//		}
//		return code.hashCode();
//	}
	
	@Override
	public int hashCode(){
		if(samePieces&&spaces.size()<2){
			String s = new String();
			for(int[] space: spaceSortHelper()){
				s+=""+space[0]+""+space[1];
				return s.hashCode();
			}
		}
		String code = new String();
		for(Piece p: sortHelper()){
		    code += p.getX()+""+p.getY()+""+p.breadth()+""+p.length();
		}
		return code.hashCode();
	}
	
	/*
	 * sort spaces by topleft coord
	 */
	private ArrayList<int[]> spaceSortHelper(){
		ArrayList<int[]> result = new ArrayList<int[]>();
		HashMap<Integer, int[]> map = new HashMap<Integer, int[]>();
		for(int[] space: spaces){
			map.put(space[0]*breadth+space[1], space);
		}
		while(!map.keySet().isEmpty()){
			int next = Collections.min(map.keySet());
			result.add(map.get(next));
			map.remove(next);
		}
		return result;
	}
	
	/*
	 * sort pieces
	 */
	private ArrayList<Piece> sortHelper(){
		ArrayList<Piece> lst = new ArrayList<Piece>(); 
		HashMap<Integer, Piece> map = new HashMap<Integer, Piece>();
		for(Piece p: pieces){
			map.put(p.getX()*breadth+p.getY(), p);
		}
		while(!map.keySet().isEmpty()){
			int next = Collections.min(map.keySet());
			lst.add(map.get(next));
			map.remove(next);
		}
		return lst;
	}
	
	/*
	 * return the piece at(x, y), null if no piece there
	 */
	public Piece get(int x, int y) {
		if((!inBound(x, y))) return null;
		return occupied[x][y];
	}

	/*
	 * check whether a given coord is in bound
	 */
	private boolean inBound(int x, int y) {
		return (x >= 0) && (x < occupied.length) && (y >= 0)
				&& (y < occupied[0].length);
	}
	
	public ArrayList<Board> mynextBoards(){
		ArrayList<Board> result = new ArrayList<Board>();
		
		if(samePieces&&spaces.size()<2&&goal.size()==pieces.size()){
		    Iterator<int[]> iter = new MoveIterator();
		    Board myNext;
		    int[] move;
		    ArrayList<Board> result2 = new ArrayList<Board>();
		    ArrayList<Board> result3 = new ArrayList<Board>();
		    while(iter.hasNext()){
		    	move = iter.next();
		    	int indicator = distanceOnBoard(move[0],move[1],spaces.get(0)[0], spaces.get(0)[1])-distanceOnBoard(move[2],move[3],spaces.get(0)[0], spaces.get(0)[1]);
		    	if(indicator>0) result.add(new Board(this, move));
		    	else if(indicator==0) result2.add(new Board(this, move));
		    	else result3.add(new Board(this, move));
		    }
		    for(Board b: result2){result.add(b);}
		    for(Board b: result3){result.add(b);}
		}else{
			Iterator<int[]> iter = new MoveIterator();
			while(iter.hasNext()){ result.add(new Board(this, iter.next()));}
		}
		return result;
	}
	
	public int distanceOnBoard(int x1,int y1,int x2,int y2){
		return Math.abs(x1-x2)+Math.abs(y1-y2)-1;
	}
	
	public Iterator<int[]> iterator(){
		return new MoveIterator();
	}

	
	private class MoveIterator implements Iterator<int[]> {

		Stack<int[]> fringe;
		Iterator<int[]> space;
		HashSet<Integer> seenMoves;

		public MoveIterator() {
			fringe = new Stack<int[]>();
			space = spaces.iterator();
			seenMoves = new HashSet<Integer>();
			int[] s;
			while (fringe.isEmpty() && space.hasNext()) {
				s = space.next();
				for (int[] change : possibleMoves(s)) {
					if(!seenMoves.contains((change[0]*breadth+change[1])*breadth*length+change[2]*breadth+change[3])){
					fringe.push(change);
					seenMoves.add((change[0]*breadth+change[1])*breadth*length+change[2]*breadth+change[3]);
					}
				}
			}
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return !fringe.isEmpty();
		}

		@Override
		public int[] next() {
			// TODO Auto-generated method stub
			int[] result = fringe.pop();
			int[] s;
			while (fringe.isEmpty() && space.hasNext()) {
				s = space.next();
				for (int[] change : possibleMoves(s)) {
					if(!seenMoves.contains((change[0]*breadth+change[1])*breadth*length+change[2]*breadth+change[3])){
						fringe.push(change);
						seenMoves.add((change[0]*breadth+change[1])*breadth*length+change[2]*breadth+change[3]);
				}}
			}
			return result;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub

		}

		@Override
		public void forEachRemaining(Consumer<? super int[]> action) {
			// TODO Auto-generated method stub

		}

	}

	public List<int[]> possibleMoves(int[] space){
		List<int[]> lst = new LinkedList<int[]>();
		int[] p;
		//up
		if(findPiece(space[0], space[1]-1)!=null){
			p=findPiece(space[0], space[1]-1);
			if(p!=null&&isValid(p[0], p[1], p[0], p[1]+1)){
				 int[] temp = {p[0], p[1], p[0], p[1]+1};
					lst.add(temp);
		}}
		//down
		if(findPiece(space[0], space[1]+1)!=null){
				p=findPiece(space[0], space[1]+1);
				if(p!=null&&isValid(p[0], p[1], p[0], p[1]-1)){
					 int[] temp = {p[0], p[1], p[0], p[1]-1};
						lst.add(temp);
				}}        
		//left
		if(findPiece(space[0]-1, space[1])!=null){
				p=findPiece(space[0]-1, space[1]);
				if(p!=null&&isValid(p[0], p[1], p[0]+1, p[1])){
					int[] temp = {p[0], p[1], p[0]+1, p[1]};
					 lst.add(temp);
				}} 		
		//right
		if(findPiece(space[0]+1, space[1])!=null){
				p=findPiece(space[0]+1, space[1]);
				if(p!=null&&isValid(p[0], p[1], p[0]-1, p[1])){
					int[] temp = {p[0], p[1], p[0]-1, p[1]};
					 lst.add(temp);
				}			
		}
	    return lst;
	}

	// given a random piece coor, return topLeftcoord of the piece; return null
	// if coord is invalid
	public int[] findPiece(int x, int y) {
		if ((!(inBound(x, y)))||get(x, y)==null) return null;
		int[] result={get(x, y).getX(), get(x, y).getY()};
		return result;
	}

	public boolean isValid(int change0, int change1, int change2, int change3) {
		// assume change is legal, which means the move is of length 1;
		Piece toMove = get(change0, change1);
		boolean valid = true;
		if(!inBound(change2, change3)){return false;}
		int i;
		int j;
		if(change0==change2){
			if(change3-change1>0){
				if(!inBound(toMove.getX(), toMove.getY()+toMove.length()))return false;
				for(i=toMove.getX(); i<toMove.getX()+toMove.breadth(); i++){
					if(occupied[i][toMove.getY()+toMove.length()]!=null){
					return false;}
				}
			}else{
				if(!inBound(toMove.getX(), toMove.getY()-1))return false;
				for(i=toMove.getX(); i<toMove.getX()+toMove.breadth(); i++){
					if(occupied[i][toMove.getY()-1]!=null){
					return false;}
				}
			}
		}else {
			if(change2-change0>0){
				if(!inBound(toMove.getX()+toMove.breadth(), toMove.getY()))return false;
				for(i=toMove.getY(); i<toMove.getY()+toMove.length(); i++){
					if(occupied[toMove.getX()+toMove.breadth()][i]!=null){
					return false;}
				}
			}else{
				for(i=toMove.getY(); i<toMove.getY()+toMove.length(); i++){
					if(!inBound(toMove.getX()-1, toMove.getY()))return false;
					if(occupied[toMove.getX()-1][i]!=null){
					return false;}
				}
			}
		}
		return valid;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
