public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		
		mike.withdraw(9999);
		mike.withdraw(-9999);
		
		Account jill = new Account(100);
		mike.merge(jill);
		System.out.println(mike.balance() + " should be 1000");
		System.out.println(jill.balance() + " should be 0");

		Account kathy = new Account(500);
		Account megan = new Account(100, kathy);
		megan.withdraw(200);
		System.out.println(megan.balance() + " should be 0");
		System.out.println(kathy.balance() + " should be 400");
		megan.withdraw(500);
		System.out.println(megan.balance() + " should be 0");
		System.out.println(kathy.balance() + " should be 400");
	}

}