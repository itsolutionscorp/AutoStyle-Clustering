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
		System.out.println(mike.withdraw(500));
		Account oceanspray = new Account(9001);
		mike.merge(oceanspray);
		System.out.println(mike.balance());
		System.out.println(oceanspray.balance());
		
		System.out.println("");
		Account kathy = new Account(500);
		Account megan = new Account(100, kathy);
		System.out.println(megan.withdraw(50));
		System.out.println(megan.withdraw(200));
		System.out.println(megan.withdraw(700));
		System.out.println(kathy.balance());
		//System.out.println(megan.myBalance);
		//System.out.println(megan.parentAccount.myBalance);
	}

}