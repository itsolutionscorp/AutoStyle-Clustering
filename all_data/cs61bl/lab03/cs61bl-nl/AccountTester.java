public class AccountTester {

	public static void main(String[] args) {
//		Account mike = new Account(1000);
//		System.out.println(mike.balance());
//		mike.deposit(100);
//		System.out.println(mike.balance());
//		mike.withdraw(200);
//		System.out.println(mike.balance());
//		Account bob = new Account(800);
//		mike.merge(bob);
//		System.out.println(mike.balance());
		Account kathy = new Account(500);
		Account megan = new Account(100,kathy);
		megan.withdraw(700);
		System.out.println("kathy " + kathy.balance());
		System.out.println("megan " + megan.balance());
		
	}

}