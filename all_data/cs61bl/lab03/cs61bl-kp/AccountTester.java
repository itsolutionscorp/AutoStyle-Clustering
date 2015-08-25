public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		System.out.println(mike.withdraw(1000));
		System.out.println(mike.withdraw(200));
		System.out.println(mike.balance());
		Account joe = new Account(500);
		mike.merge(joe);
		System.out.println(joe.balance());
		System.out.println(mike.balance());
		Account kathy, megan;
		kathy = new Account(500);
		megan = new Account(100, kathy);
		megan.withdraw(50);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
		megan.withdraw(200);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
		megan.withdraw(700);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
	}

}