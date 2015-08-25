public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		System.out.println(mike.withdraw(200));
		System.out.println(mike.withdraw(10000));
		System.out.println(mike.balance());
		
		Account tom = new Account(12);
		tom.merge(mike);
		System.out.println(mike.balance());
		System.out.println(tom.balance());
		
		Account kathy = new Account(500);
		Account megan = new Account(100, kathy);
		megan.withdraw(50);
		megan.withdraw(200);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
		megan.withdraw(700);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
	}

}