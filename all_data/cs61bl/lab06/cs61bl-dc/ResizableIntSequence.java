public class ResizableIntSequence extends IntSequence
{
	public ResizableIntSequence(int capacity) 
	{
		super(capacity);
	}
	@Override
	public void add(int i)
	{
		if (myCount == myValues.length)
		{
			int[] newSequence = new int[myValues.length + 20];
			for (int j = 0; j < myCount; j++)
				newSequence[j] = myValues[j];
			myValues = newSequence;
		}
		super.add(i);
	}
	public void insert(int pos, int i)
	{
		if (myCount == myValues.length)
		{
			int[] newSequence = new int[myValues.length + 20];
			for (int j = 0; j < myCount; j++)
				newSequence[j] = myValues[j];
			myValues = newSequence;
		}
		super.insert(pos, i);
	}
	public void remove(int pos)
	{
		super.remove(pos);
		if (myValues.length > 20 && myValues.length / 4 > myCount)
		{
			int[] newSequence = new int[myValues.length / 2];
			for (int j = 0; j < myCount; j++)
				newSequence[j] = myValues[j];
			myValues = newSequence;
		}
	}
}
