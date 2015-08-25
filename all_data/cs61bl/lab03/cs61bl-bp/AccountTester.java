
public class AccountTester {
	
	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		Account kathy = new Account(500);
		Account megan = new Account(100,kathy);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		boolean success = mike.withdraw(200);
		if (success) {
			System.out.println("Withdrawal successful.");
		}
		else {
			System.out.println("Withdrawal failed.");
		}
		System.out.println(mike.balance());
		Account bob = new Account(1);
		mike.merge(bob);
		System.out.println(mike.balance());
		System.out.println(bob.balance());
		
		
		megan.withdraw(200);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
		
	}

}
