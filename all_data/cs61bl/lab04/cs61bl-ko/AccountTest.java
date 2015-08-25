import static org.junit.Assert.*;

import org.junit.Test;


public class AccountTest {

	@Test
	public void testInit() {
		Account mike;
		mike = new Account(1000);
		assertTrue (mike.balance() == 1000);
	}
	
	@Test
	public void testInvalidArgs() {
		Account mike;
		mike = new Account(1000);
		assertTrue (mike.balance() == 1000);
		assertTrue (mike.withdraw(-500) == false);
		int balance = mike.balance();
		mike.deposit(-500);
		assertTrue (mike.balance() == balance);
	}
	
	@Test
	public void testOverdraft() {
		Account mike;
		mike = new Account(1000);
		int balance = mike.balance();
		mike.withdraw(2000);
		assertTrue (mike.balance() == balance);
	}
	
	@Test
	public void testDeposit() {
		Account mike;
		mike = new Account(10);
		mike.deposit(20);
		assertTrue (mike.balance() == 30);
	}
	
	@Test
	public void testWithdraw() {
		Account mike;
		mike = new Account(20);
		mike.withdraw(10);
		assertTrue (mike.balance() == 10);
	}

}
