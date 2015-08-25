
public class TestExceptions
{

	public static void main(String[] args)
	{
		Object o = null;
		Object o2 = new Object();
		Object[] strings = new String[3];

		try
		{
			o.equals(o2);

		} catch (NullPointerException e)
		{
			System.out.println("got null pointer");
		}
		try
		{
			strings[0] = new Integer(0);

		} catch (ArrayStoreException e)
		{
			System.out.println("got illegal array store");
		}
		try
		{
			o = new String();
			System.out.println((Integer)o);

		} catch (ClassCastException e)
		{
			System.out.println("got illegal class cast");
		}
	}
}
