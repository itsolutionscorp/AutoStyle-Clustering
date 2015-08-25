import static org.junit.Assert.*;

import org.junit.Test;


public class AccountTest {

	@Test
	public void testInit(){
		Account ta = new Account(1000);
		assertEquals(1000, ta.balance());
	}
	
	@Test
	public void testInvalidArgs(){
		Account ta = new Account(1000);
		ta.withdraw(-100);
		assertEquals(1000, ta.balance());
		ta.deposit(-100);
		assertEquals(1000, ta.balance());
	}
	
	@Test
	public void testOverdraft(){
		Account ta = new Account(1000);
		ta.withdraw(1100);
		assertEquals(1000, ta.balance());
	}
	
	@Test
	public void testDeposit(){
		Account ta = new Account(1000);
		ta.deposit(100);
		assertEquals(1100, ta.balance());
	}

	@Test
	public void testWithdraw(){
		Account ta = new Account(1000);
		ta.withdraw(100);
		assertEquals(900, ta.balance());
	}
}
