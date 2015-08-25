import java.util.*;

public class Block {
	private int topLeftY;
	private int topLeftX;
	private int bottomRightY;
	private int bottomRightX;
	private int horizontal;
	private int vertical;
	public ArrayList<int[]> placetomove;
	public boolean isMovable;

	public Block(int topLeft1, int topLeft2, int bottomRight1, int bottomRight2){
		topLeftY = topLeft1;
		topLeftX = topLeft2;
		bottomRightY= bottomRight1;
		bottomRightX = bottomRight2;
		horizontal = bottomRightX - topLeftX + 1;
		vertical = bottomRightY - topLeftY + 1;
		placetomove = new ArrayList<int[]>();
		isMovable = false;
	}


	public Block copy() {
		return new Block(topLeftY, topLeftX, bottomRightY, bottomRightX);
	}

	public int getHorizontal() {
		return horizontal;
	}

	public int getVertical() {
		return vertical;
	}

	public int hashCode(){
		return topLeftY * 1000 + topLeftX * 1;
	}

	public void setTopLeftX(int i) {
		topLeftX = i;
	}

	public void setTopLeftY(int i) {
		topLeftY = i;
	}

	public void setBottomRightX(int i) {
		bottomRightX = i;
	}

	public void setBottomRightY(int i) {
		bottomRightY = i;
	}
	public void addPlaceToMove(int[] anoption){
		placetomove.add(anoption);
	}

	public int getTopLeftX() {
		return topLeftX;
	}

	public int getTopLeftY() {
		return topLeftY;
	}

	public int getBottomRightX() {
		return bottomRightX;
	}

	public int getBottomRightY() {
		return bottomRightY;
	}
	public ArrayList<int[]> getPlacetoMove(){
		return placetomove;
	}

	@Override
	public String toString() {
		return "TopLeftY: " + topLeftY + ", TopLeftX: " + topLeftX + 
				", BottomRightY: " + bottomRightY + ", BottomRightX: " + bottomRightX + ", size: " + 
						horizontal + "x" + vertical;
	}

}
