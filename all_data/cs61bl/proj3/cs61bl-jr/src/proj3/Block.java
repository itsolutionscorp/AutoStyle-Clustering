//package proj3;
public class Block {

	private int TL_X;
	private int TL_Y;
	private int BR_X;
	private int BR_Y;
	private StringBuilder code;
	private int side1;
	private int side2;
	
	public Block (int tl_0, int tl_1, int br_0, int br_1) {
		TL_X = tl_0;
		TL_Y = tl_1;
		BR_X = br_0;
		BR_Y = br_1;
		side1 = Math.max (br_1 - tl_1 + 1, br_0 - tl_0 + 1);
		side2 = Math.min (br_1 - tl_1 + 1, br_0 - tl_0 + 1);
		code = new StringBuilder();
		code.append(side1);
		code.append(side2);
		code.append(TL_X);
		code.append(TL_Y);
		code.append(BR_X);
		code.append(BR_Y);
	}
	
	public int returnTL_X() {
		return TL_X;
	}
	
	public int returnTL_Y() {
		return TL_Y;
	}
	
	public int returnBR_X() {
		return BR_X;
	}
	
	public int returnBR_Y() {
		return BR_Y;
	}
	
	public void left() {
		TL_Y -= 1;
		BR_Y -= 1;
	}
	
	public void right() {
		TL_Y += 1;
		BR_Y += 1;
	}
	
	public void up() {
		TL_X -= 1;
		BR_X -= 1;
	}
	
	public void down() {
		TL_X += 1;
		BR_X += 1;
	}
	
	public Block returnCopy() {
		return new Block (TL_X, TL_Y, BR_X, BR_Y);
	}
	
	public String returnCode() {
		return code.toString();
	}
	
	public boolean equals (Object a) {
		Block a_block = (Block) a;
		return this.returnCode().equals(a_block.returnCode());
	}
	
	public int hashCode() {
		return Integer.parseInt(code.toString());
	}
}
