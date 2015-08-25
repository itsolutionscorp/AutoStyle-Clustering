public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		System.out.println(mike.withdraw(200));
		System.out.println(mike.balance());
		System.out.println(mike.withdraw(666666));
		System.out.println(mike.withdraw(-666666));
		Account joe;
		joe = new Account(2000);
		joe.merge(mike);
		System.out.println(joe.balance());
		System.out.println(mike.balance());
		Account kathy;
		kathy = new Account(500);
		Account megan;
		megan = new Account(100, kathy);
		System.out.println("swag");
		System.out.println(megan.withdraw(50));
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
		System.out.println(megan.withdraw(200));
		System.out.println(kathy.balance());
		System.out.println(megan.withdraw(800));
		System.out.println(kathy.balance());
		
		
		
		
	}

}