import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class ExperimentalBoard
{
	/**
	 * Stored to optimize runtime of possibleMoves
	 */
	private HashSet<Coordinates>	openSpaces;
	private HashMap<String, Block>	blocks;
	private short					x, y;
	private int						hashCode;

	private HashSet<Coordinates>	occupiedSpaces;

	public static class Coordinates
	{
		short	x;
		short	y;
		Coordinates(short x, short y)
		{
			this.x = x;
			this.y = y;
		}
		public int hashCode()
		{
			return this.toString().hashCode();
		}
		public boolean equals(Object o)
		{
			return this.hashCode() == o.hashCode();
		}
		public String toString()
		{
			return "(" + x + ", " + y + ")";
		}
	}

	/**
	 * Construct Board with dimensions specified by s
	 * 
	 * @param s
	 *            configuration specifications
	 * @throws Exception
	 */
	public ExperimentalBoard(String s) throws Exception
	{
		short x = Short.valueOf(s.substring(0, s.indexOf(" ")));
		short y = Short.valueOf(s.substring(s.indexOf(" ") + 1));
		if (x <= 0 || y <= 0)
			throw new Exception();
		this.x = x;
		this.y = y;
		blocks = new HashMap<String, Block>();
		openSpaces = new HashSet<Coordinates>();
		occupiedSpaces = new HashSet<Coordinates>();
		hashCode = 1;
	}

	public ExperimentalBoard(HashMap<String, Block> blocks, HashSet<Coordinates> openSpaces, short x, short y, int hashCode)
	{
		this.blocks = blocks;
		this.openSpaces = openSpaces;
		this.x = x;
		this.y = y;
		this.hashCode = hashCode;
	}

	/**
	 * Used in constructor to add blocks to board
	 * 
	 * @param b
	 *            Block to add
	 * @throws Exception
	 */
	public void addBlock(String b) throws Exception
	{
		Block block = new Block(b);
		if (block.x1 < 0 || block.y1 < 0 || block.x2 >= x || block.y2 >= y)
			throw new Exception("Block dimensions are out of bounds");
		if (block.x1 > block.x2 || block.y1 > block.y2)
			throw new Exception("Invalid block dimensions");
		for (short i = block.x1; i <= block.x2; i++)
			for (short j = block.y1; j <= block.y2; j++)
			{
				Coordinates c = new Coordinates(i, j);
				if (occupiedSpaces.contains(c))
				{
					throw new Exception("Overlapping blocks");
				}
				occupiedSpaces.add(c);
			}
		blocks.put(firstTwoNumbers(b), block);
		hashCode += block.hashCode();
	}

	public void finishAddingBlocks()
	{
		for (short i = 0; i < x; i++)
			for (short j = 0; j < y; j++)
			{
				Coordinates c = new Coordinates(i, j);
				if (!occupiedSpaces.contains(c))
					openSpaces.add(c);
			}
		occupiedSpaces = null;
	}

	public LinkedList<String> possibleMoves()
	{
		LinkedList<String> possibleMoves = new LinkedList<String>();
		for (Block block : blocks.values())
		{
			if (block.x1 - 1 >= 0)
			{
				boolean canAdd = true;
				for (short i = block.y1; i <= block.y2; i++)
				{
					Coordinates c = new Coordinates((short) (block.x1 - 1), i);
					if (!openSpaces.contains(c))
					{
						canAdd = false;
						break;
					}
				}
				if (canAdd)
					possibleMoves.add(block.x1 + " " + block.y1 + " " + (block.x1 - 1) + " " + block.y1);
			}
			if (block.x2 + 1 < x)
			{
				boolean canAdd = true;
				for (short i = block.y1; i <= block.y2; i++)
				{
					Coordinates c = new Coordinates((short) (block.x2 + 1), i);
					if (!openSpaces.contains(c))
					{
						canAdd = false;
						break;
					}
				}
				if (canAdd)
					possibleMoves.add(block.x1 + " " + block.y1 + " " + (block.x1 + 1) + " " + block.y1);
			}
			if (block.y1 - 1 >= 0)
			{
				boolean canAdd = true;
				for (short i = block.x1; i <= block.x2; i++)
				{
					Coordinates c = new Coordinates(i, (short) (block.y1 - 1));
					if (!openSpaces.contains(c))
					{
						canAdd = false;
						break;
					}
				}
				if (canAdd)
					possibleMoves.add(block.x1 + " " + block.y1 + " " + block.x1 + " " + (block.y1 - 1));
			}
			if (block.y2 + 1 < y)
			{
				boolean canAdd = true;
				for (short i = block.x1; i <= block.x2; i++)
				{
					Coordinates c = new Coordinates(i, (short) (block.y2 + 1));
					if (!openSpaces.contains(c))
					{
						canAdd = false;
						break;
					}
				}
				if (canAdd)
					possibleMoves.add(block.x1 + " " + block.y1 + " " + block.x1 + " " + (block.y1 + 1));
			}
		}
		return possibleMoves;
	}

	public ExperimentalBoard move(String move)
	{
		if (move.equals(""))
			return this;
		int firstSpace = move.indexOf(" ");
		int secondSpace = move.indexOf(" ", firstSpace + 1);
		int thirdSpace = move.indexOf(" ", secondSpace + 1);
		short x1 = Short.valueOf(move.substring(0, firstSpace));
		short y1 = Short.valueOf(move.substring(firstSpace + 1, secondSpace));
		short x2 = Short.valueOf(move.substring(secondSpace + 1, thirdSpace));
		short y2 = Short.valueOf(move.substring(thirdSpace + 1));
		HashSet<Coordinates> newOpenSpaces = new HashSet<Coordinates>();
		for (Coordinates c : openSpaces)
			newOpenSpaces.add(new Coordinates(c.x, c.y));
		HashMap<String, Block> newBlocks = new HashMap<String, Block>();
		for (String s : blocks.keySet())
		{
			Block b = blocks.get(s);
			newBlocks.put(s, new Block(b.x1, b.y1, b.x2, b.y2));
		}
		Block toMove = newBlocks.remove(firstTwoNumbers(move));
		if (x2 - x1 == 1)
		{
			for (short i = y1; i <= toMove.y2; i++)
			{
				newOpenSpaces.add(new Coordinates(x1, i));
				newOpenSpaces.remove(new Coordinates((short) (toMove.x2 + 1), i));
			}
		}
		else if (x1 - x2 == 1)
		{
			for (short i = y1; i <= toMove.y2; i++)
			{
				newOpenSpaces.add(new Coordinates(toMove.x2, i));
				newOpenSpaces.remove(new Coordinates(x2, i));
			}
		}
		else if (y2 - y1 == 1)
		{
			for (short i = x1; i <= toMove.x2; i++)
			{
				newOpenSpaces.add(new Coordinates(i, y1));
				newOpenSpaces.remove(new Coordinates(i, (short) (toMove.y2 + 1)));
			}
		}
		else if (y1 - y2 == 1)
		{
			for (short i = x1; i <= toMove.x2; i++)
			{
				newOpenSpaces.add(new Coordinates(i, toMove.y2));
				newOpenSpaces.remove(new Coordinates(i, y2));
			}
		}
		int newHashCode = hashCode - toMove.hashCode();
		toMove.x2 += (x2 - x1);
		toMove.y2 += (y2 - y1);
		toMove.x1 = x2;
		toMove.y1 = y2;
		newBlocks.put(toMove.x1 + " " + toMove.y1, toMove);
		newHashCode += toMove.hashCode();
		return new ExperimentalBoard(newBlocks, newOpenSpaces, x, y, newHashCode);
	}

	/**
	 * Prints the board in the correct format
	 */
	public String toString()
	{
		String toReturn = x + " " + y + "\n";
		for (Block b : blocks.values())
			toReturn += b.toString() + "\n";
		return toReturn;
	}

	public boolean equals(Object o)
	{
		if (this.hashCode == o.hashCode())
		{
			ExperimentalBoard b = (ExperimentalBoard) o;
			for (String s : b.blocks.keySet())
			{
				if (!blocks.containsKey(s))
					return false;
				else if (!blocks.get(s).equals(b.blocks.get(s)))
					return false;
			}
			return true;
		}
		return false;
	}

	public static String firstTwoNumbers(String s)
	{
		int secondSpace = s.indexOf(" ", s.indexOf(" ") + 1);
		return s.substring(0, secondSpace);
	}
	
	public static String lastTwoNumbers(String s)
	{
		int secondSpace = s.indexOf(" ", s.indexOf(" ") + 1);
		return s.substring(secondSpace + 1);
	}

	public boolean checkGoal(HashMap<String, String> goals)
	{
		int count = goals.size();
		for (String b : blocks.keySet())
			if (goals.containsKey(b) && goals.get(b).equals(blocks.get(b).toString()))
				count--;
		return count == 0;
	}

	public int hashCode()
	{
		return hashCode;
	}
}
