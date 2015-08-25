public class AccountTester {

	public static void main(String[] args) {
		Account mike;
        System.out.println("Making Mike's account with balance of 1000");
        mike = new Account(1000);
		System.out.println("Mike's balance: " + mike.balance());
        System.out.println("Depositing 100 into Mike's account");
        mike.deposit(100);
		System.out.println("Mike's balance: " + mike.balance());
		if (!mike.withdraw(200))
            throw new AssertionError("Withdrawal of 200 failed!");
		System.out.println(mike.balance());
		if (mike.withdraw(1000))
            throw new AssertionError("Withdrawal of too much money succeeded!");
        if (mike.withdraw(-2))
            throw new AssertionError("Withdrawal of negative money succeeded!");

        System.out.println("Making Mike's secret offshore account");
        Account mikeSecret = new Account(1000000000);
        System.out.println("Mike's offshore account balance: " + mikeSecret.balance());

        System.out.println("Merging Mike's secret offshore account with Mike's regular account");
        mike.merge(mikeSecret);

        System.out.println("Mike's offshore account balance: " + mikeSecret.balance());
        System.out.println("Mike's balance: " + mike.balance());

        System.out.println("Making Mike's daughter Wendy's account");
        Account wendy = new Account(250, mike);
        System.out.println("Wendy's balance: " + wendy.balance());

        System.out.println("Withdrawing 5 from Wendy's account");
        if (!wendy.withdraw(5))
            throw new AssertionError("Couldn't withdraw 5 from Wendy's account (no overdraft)!");
        System.out.println("Wendy's balance: " + wendy.balance());
        System.out.println("Mike's balance: " + mike.balance());

        System.out.println("Withdrawing 500 from Wendy's account");
        if (!wendy.withdraw(500))
            throw new AssertionError("Couldn't withdraw 500 from Wendy's account (overdraft protection)!");
        System.out.println("Wendy's balance: " + wendy.balance());
        System.out.println("Mike's balance: " + mike.balance());

        System.out.println("Withdrawing 2000000000 from Wendy's account");
        if (wendy.withdraw(2000000000))
            throw new AssertionError("Withdrawal of 2000000000 from Wendy's account succeeded! (more money than Mike had)");

    }

}