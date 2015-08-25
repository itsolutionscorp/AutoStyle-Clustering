public class AccountTester {

	public static void main(String[] args) {
		/*Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(2000);
		System.out.println(mike.balance());
		System.out.println(mike.withdraw(-200));
		System.out.println(mike.withdraw(20));
		System.out.println(mike.withdraw(2000));
		
		Account mary= new Account(500);
		System.out.println(mike.balance());
		System.out.println(mary.balance());
		mike.merge(mary);
		System.out.println(mike.balance());
		System.out.println(mary.balance());
		*/
		
		Account kathy = new Account(500);
		Account megan = new Account(100,kathy);
		megan.withdraw(50);
		System.out.println(kathy.balance());
		System.out.println(megan.balance());

		megan.withdraw(200);
		System.out.println(kathy.balance());
		System.out.println(megan.balance());

		megan.withdraw(700);
		System.out.println(kathy.balance());
		System.out.println(megan.balance());
	
	}

}