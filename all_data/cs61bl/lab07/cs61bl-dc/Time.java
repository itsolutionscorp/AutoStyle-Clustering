public class Time
{
	private int	myHours;
	private int	myMinutes;

	public Time(String s)
	{
		try
		{
			int colonPos = s.indexOf(":");
			if (s.substring(0, 1).equals("0"))
				throw new IllegalArgumentException("Too many zeros before hours");
			myHours = Integer.parseInt(s.substring(0, colonPos));
			if (myHours > 23 || myHours < 0)
				throw new IllegalArgumentException("Hours out of range");
			myMinutes = Integer.parseInt(s.substring(colonPos + 1));
			if (myMinutes > 59 || myMinutes < 0)
				throw new IllegalArgumentException("Minutes out of range");
			if (myMinutes < 10 && s.substring(colonPos + 1, colonPos + 3).equals("00") || myMinutes > 10 && s.substring(colonPos + 1, colonPos + 2).equals("0"))
				throw new IllegalArgumentException("Too many zeros before minutes");
		} catch (Exception e)
		{
			if (e instanceof NullPointerException)
				throw new IllegalArgumentException("null is not a valid argument");
			if (e instanceof StringIndexOutOfBoundsException)
				throw new IllegalArgumentException("No colon in argument");
			if (e instanceof NumberFormatException)
				throw new IllegalArgumentException(e.getMessage().substring(e.getMessage().indexOf(":") + 2, e.getMessage().length()) + " is not valud input");
			throw e;
		}
	}

	public Time(int hours, int minutes)
	{
		myHours = hours;
		myMinutes = minutes;
		if (myMinutes > 59 || myMinutes < 0)
			throw new IllegalArgumentException("Minutes out of range");
		if (myHours > 23 || myHours < 0)
			throw new IllegalArgumentException("Hours out of range");
	}

	public boolean equals(Object obj)
	{
		Time t = (Time) obj;
		return myHours == t.myHours && myMinutes == t.myMinutes;
	}

	public String toString()
	{
		if (myMinutes < 10)
		{
			return myHours + ":0" + myMinutes;
		} else
		{
			return myHours + ":" + myMinutes;
		}
	}
}
