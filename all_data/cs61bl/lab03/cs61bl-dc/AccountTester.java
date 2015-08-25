public class AccountTester {

	public static void main(String[] args) {
		/*Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		mike.withdraw(1000);
		Account sue = new Account(1000);
		sue.withdraw(1000);
		System.out.println(sue.balance());
		sue.withdraw(-10);
		sue.merge(mike);
		System.out.println(sue.balance());
		System.out.println(mike.balance());
		*/
		
		
		Account kathy = new Account (500);
		Account megan = new Account(100, kathy);
		megan.withdraw(50);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
		
		megan.deposit(50);
		megan.withdraw(200);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
		megan.deposit(100);
		kathy.deposit(100);
		megan.withdraw(700);
		
		
		

		
		
	}

}