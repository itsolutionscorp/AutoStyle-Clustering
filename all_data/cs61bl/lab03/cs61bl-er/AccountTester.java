public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		mike.withdraw(1000);
		System.out.println(mike.balance());
		
		Account yimeng;
		yimeng = new Account(10000);
		yimeng.merge(mike);
		System.out.println(yimeng.balance());
		Account kathy;
		Account megan;
		kathy = new Account(500);
		megan = new Account(100,kathy);
		megan.withdraw(100);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
		megan.withdraw(100);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
		megan.withdraw(900);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
		
		
	}

}