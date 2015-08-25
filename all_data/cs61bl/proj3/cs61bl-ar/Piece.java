
public class Piece {

	/**
	 * @param args
	 */
	int length;
	int breadth;
	int xcoord;//breadth
	int ycoord;//length
	
	public Piece(int[] coords){
		length = coords[3]- coords[1]+1;
		breadth = coords[2] - coords[0] +1;
		xcoord = coords[0];
		ycoord = coords[1];
	}
	public Piece(Piece p){
		length=p.length();
		breadth=p.breadth();
        xcoord=p.getX();//breadth
		ycoord=p.getY();//length
	}
	
	public int getX(){
		return xcoord;
	}
	
	public String type(){
		String s = length+""+breadth;
		return s;
	}
	
	public int getY(){
		return ycoord;
	}
	public int breadth(){return breadth;}
	public int length(){return length;}
	
	public Piece moveHelper(int[] move){
		//assume move is valid
		return new Piece(new int[]{move[2], move[3], move[2]+breadth-1, move[3]+length-1});
	}
	
	

}
