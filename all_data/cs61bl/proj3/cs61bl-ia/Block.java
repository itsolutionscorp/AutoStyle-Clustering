import java.awt.Point;


public class Block {
	int myY1; //top left as represented in spec. y1<y2
	int myX1;
	int myY2; //bottom right 
	int myX2;
	
	Point upLeft;//not in use
	Point downRight;//not in use

	
	
	public Block(int y1, int x1, int y2, int x2){
		myY1 = y1;
		myX1 = x1;
		myY2 = y2;
		myX2 = x2;
		if (x1>x2 || y1>y2){
			System.out.println("Invalid init and/or goal file.");
			System.exit(0);
		}
		
		upLeft = new Point(x1, y1);//not in use
		downRight = new Point(x2, y2);//not in use anywhere
					
	}
	
	public int height(){
		return myY2 - myY1;
	}
	
	public int width(){
		return myX2 - myX1;
	}
	
	@Override
	public int hashCode() {
		int hashNum = 7;
		hashNum+= height() *3;
		hashNum+= width()*11;
		
		hashNum = hashNum + myY1 * hashNum;
		hashNum = hashNum + myY2 * hashNum;
		hashNum = hashNum + myX1 * hashNum;
		hashNum = hashNum + myX2 * hashNum;
		return hashNum;
	}
	@Override
	public boolean equals(Object o) {
		if (o.hashCode() != hashCode() || o.getClass() != getClass()) {
			return false;
		}
		if (((Block) o).myY1!=this.myY1 || ((Block) o).myX1!=this.myX1 || ((Block) o).myY2!=this.myY2 || ((Block) o).myX2!=this.myX2){
			return false;
		}
		return true;
	}
}
