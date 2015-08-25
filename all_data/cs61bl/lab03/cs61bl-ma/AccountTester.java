public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		mike.withdraw(-10);
		mike.withdraw(2000);
		
		Account Matt;
		Matt = new Account (10000);
		mike.merge(Matt);
		System.out.println(mike.balance());
		System.out.println(Matt.balance());
		
		Account bill = new Account (100);
		Account kathy = new Account (500, bill);
		Account megan = new Account (50, kathy);
		megan.withdraw(700);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
		System.out.println(bill.balance());
		
	}

}