public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		// Testing withdraw method
		System.out.println("Withdraw method tests");
		System.out.println(mike.withdraw(100));
		System.out.println(mike.withdraw(1100));
		System.out.println(mike.withdraw(-800));
		// Testing merge method
		System.out.println("Merge method tests");
		System.out.println(mike.balance());
		Account andy = new Account(600);
		System.out.println(andy.balance());
		mike.merge(andy);
		System.out.println(mike.balance());
		// Testing Overdraft
		System.out.println("Testing Overdraft");
		Account kathy1 = new Account(500);
		Account megan1 = new Account(100,kathy1);
		megan1.withdraw(50);
		System.out.println(megan1.balance());
		System.out.println(kathy1.balance());
		Account kathy2 = new Account(500);
		Account megan2 = new Account(100,kathy2);
		megan2.withdraw(200);
		System.out.println(megan2.balance());
		System.out.println(kathy2.balance());
		Account kathy3 = new Account(500);
		Account megan3 = new Account(100,kathy3);
		megan3.withdraw(700);
		System.out.println(megan3.balance());
		System.out.println(kathy3.balance());
	}

}