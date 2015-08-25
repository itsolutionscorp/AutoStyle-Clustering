public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		Account gil = new Account(500);
		mike.merge(gil);
		System.out.println(mike.balance());
		
		System.out.println ("Testing overdraft protection");
		Account kathy;
		kathy = new Account(500);
		Account megan;
		megan = new Account (100, kathy);
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