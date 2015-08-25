import junit.framework.TestCase;


public class ModNCounterTest extends TestCase {
	
	public void testInit() {
		Account eifu = new Account(1000);
		assertTrue (eifu.balance() == 1000);
		
	}
	
	public void testInvalidArgs() {
		Account eifu = new Account(1000);
		eifu.withdraw(-400);
		assertTrue (eifu.balance() == 1000);
		eifu.deposit(-666);
		assertTrue (eifu.balance() == 1000);
	}
	
	public void testOverdraft() {
		Account eifu = new Account(1000);
		eifu.withdraw(1000000);
		assertTrue (eifu.balance() == 1000);
	}
	
	public void testDeposit() {
		Account eifu = new Account(1000);
		eifu.deposit(100);
		assertTrue (eifu.balance() == 1100);
	}
	
	public void testWithdraw() {
		Account eifu = new Account(1000);
		eifu.withdraw(100);
		assertTrue (eifu.balance() == 900);
	}

}
