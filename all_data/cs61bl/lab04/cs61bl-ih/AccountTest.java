import junit.framework.TestCase;

public class AccountTest extends TestCase {
    public void testInit() {
        int initialBalance = 1000;
        Account acct = new Account(initialBalance);
        assertTrue(acct.balance() == initialBalance);
    }

    public void testInvalidArgs() {
        int initialBalance = 1000;
        Account acct = new Account(initialBalance);
        acct.withdraw(initialBalance + 1);
        assertTrue(acct.balance() == initialBalance);

        acct.withdraw(-1);
        assertTrue(acct.balance() == initialBalance);

        acct.deposit(-1);
        assertTrue(acct.balance() == initialBalance);
    }

    public void testOverdraft() {
        int initialBalance = 100;
        int parentInitialBalance = initialBalance * 10;
        Account parentAcct = new Account(parentInitialBalance);
        Account acct = new Account(initialBalance, parentAcct);

        acct.withdraw(initialBalance * 2);
        assertTrue(acct.balance() == 0);
        assertTrue(parentAcct.balance() == parentInitialBalance - initialBalance);
    }

    public void testDeposit() {
        int initialBalance = 1000;
        int deposit = 100;
        Account acct = new Account(initialBalance);
        acct.deposit(deposit);
        assertTrue(acct.balance() == initialBalance + deposit);
    }

    public void testWithdraw() {
        int initialBalance = 1000;
        int withdrawal = 100;
        Account acct = new Account(initialBalance);
        acct.withdraw(withdrawal);
        assertTrue(acct.balance() == initialBalance - withdrawal);
    }
}