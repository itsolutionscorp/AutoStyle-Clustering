
public class Block
{
	public short x1, y1, x2, y2;
	
	public Block(String s)
	{
		int firstSpace = s.indexOf(" ");
		int secondSpace = s.indexOf(" ", firstSpace + 1);
		int thirdSpace = s.indexOf(" ", secondSpace + 1);
		x1 = Short.valueOf(s.substring(0, firstSpace));
		y1 = Short.valueOf(s.substring(firstSpace + 1, secondSpace));
		x2 = Short.valueOf(s.substring(secondSpace + 1, thirdSpace));
		y2 = Short.valueOf(s.substring(thirdSpace + 1));
	}
	
	public Block(short x1, short y1, short x2, short y2)
	{
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public int hashCode()
	{
		return toString().hashCode();
	}
	
	public String toString()
	{
		return x1 + " " + y1 + " " + x2 + " " + y2;
	}
	
	public boolean equals(Object o)
	{
		Block b = (Block) o;
		return this.x1 == b.x1 && this.y1 == b.y1 && this.x2 == b.x2 && this.y2 == b.y2;
	}
	
	public static void checkBlock(String s)
	{
		int firstSpace = s.indexOf(" ");
		int secondSpace = s.indexOf(" ", firstSpace + 1);
		int thirdSpace = s.indexOf(" ", secondSpace + 1);
		Short.valueOf(s.substring(0, firstSpace));
		Short.valueOf(s.substring(firstSpace + 1, secondSpace));
		Short.valueOf(s.substring(secondSpace + 1, thirdSpace));
		Short.valueOf(s.substring(thirdSpace + 1));
	}
}
