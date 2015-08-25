import static org.junit.Assert.*;

import org.junit.Test;

public class AccountTest {

	@Test
	public void test() {
		Account richy = new Account(300);
		richy.deposit(20);
		richy.withdraw(50);
		assertTrue(richy.balance()==270);
	}

}
