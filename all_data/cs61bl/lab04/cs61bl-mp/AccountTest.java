import junit.framework.TestCase;


public class AccountTest extends TestCase {
	public void testInit() {
        Account c = new Account(500);
        assertTrue (c.balance() == 500);
}
	public void testInvalidArgs() {
		Account c = new Account(500);
        assertTrue (c.balance() == 500);
        c.withdraw(-100);
        assertTrue (c.balance() == 500);
        c.deposit(-100);
        assertTrue (c.balance() == 500);
}
	public void testOverdraft() {
		Account c = new Account(500);
        assertTrue (c.balance() == 500);
        c.withdraw(1000);
        assertTrue (c.balance() == 500);
        Account mom = new Account(100);
		Account son = new Account(500, mom);
        assertTrue (son.balance() == 500);
        son.withdraw(100);
        assertTrue (son.balance() == 400);
        son.withdraw(800);
        assertTrue (son.balance() == 400);
        assertTrue (mom.balance() == 100);
        
}
	public void testDeposit() {
		Account c = new Account(500);
        assertTrue (c.balance() == 500);
        c.deposit(100);
        assertTrue (c.balance() == 600);
	}
	public void testWithdraw() {
		Account mom = new Account(1000);
		Account son = new Account(500, mom);
        assertTrue (son.balance() == 500);
        son.withdraw(100);
        assertTrue (son.balance() == 400);
        son.withdraw(800);
        assertTrue (son.balance() == 0);
        assertTrue (mom.balance() == 600);
	}
}
