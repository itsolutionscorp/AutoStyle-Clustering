import static org.junit.Assert.*;

import org.junit.Test;


public class AccountTest {

	@Test
	public void testInit() {
		Account steve = new Account(1000);
		assertTrue (steve.balance() == 1000);
	}
	
	@Test
	public void testInvalidArgs() {
		Account steve = new Account(1000);
		steve.deposit(-1);
		assertTrue (steve.balance() == 1000);
		
		steve.withdraw(-1);
		assertTrue (steve.balance() == 1000);
		
	}
	@Test
	public void testOverdraft() {
		Account steve = new Account(1000);
		steve.withdraw(2000);
		assertTrue (steve.balance() == 1000);
	}
	
	@Test
	public void testDeposit() {
		Account steve = new Account(1000);
		steve.deposit(2000);
		assertTrue (steve.balance() == 3000);
	}
	
	@Test
	public void testWithdraw() {
		Account steve = new Account(1000);
		steve.withdraw(500);
		assertTrue (steve.balance() == 500);
	}
}
