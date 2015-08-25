import junit.framework.TestCase;


public class AccountTest extends TestCase 
{
	public void testInit()
	{
		Account account = new Account(500);
		assertTrue(account.balance() == 500);
	}
	public void testInvalidArgs()
	{
		Account account = new Account(500);
		account.deposit(-1);
		assertTrue(account.balance() == 500);
		assertFalse(account.withdraw(-1));
		assertTrue(account.balance() == 500);
	}
	public void testOverdraft()
	{
		Account account = new Account(500);
		assertFalse(account.withdraw(1000));
		assertTrue(account.balance() == 500);
	}
	public void testDeposit()
	{
		Account account = new Account (500);
		account.deposit(1000);
		assertTrue(account.balance() == 1500);
	}
	public void testWithdraw()
	{
		Account account = new Account(500);
		assertTrue(account.withdraw(200));
		assertTrue(account.balance() == 300);
	}
}
