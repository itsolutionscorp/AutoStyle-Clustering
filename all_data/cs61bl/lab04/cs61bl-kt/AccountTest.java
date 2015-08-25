import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit()
	{
		Account rand = new Account(100);
		assertTrue (rand.balance() == 100);
	}
	
	public void testInvalidArgs()
	{
		Account rand = new Account(100);
		assertTrue (rand.withdraw(-1) == false);
	}
	
	public void testOverdraft()
	{
		Account rand = new Account (100);
		rand.withdraw(101);
		assertTrue (rand.balance() == 100);
	}
	
	public void testDeposit()
	{
		Account rand = new Account(100);
		rand.deposit(20);
		assertTrue(rand.balance() == 120);
	}
	
	public void testWithdraw()
	{
		Account rand = new Account(100);
		rand.withdraw(20);
		assertTrue(rand.balance() == 80);
	}
}
