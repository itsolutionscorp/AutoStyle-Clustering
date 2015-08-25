public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		
		
		//tests for returning false and true for Withdraw class
		mike.withdraw(1000);
		System.out.println(mike.balance());
		mike.withdraw(-1000);
		
		if (!mike.withdraw(1000)) {
			System.out.println("Successfully returned false for overdrawn");
		}
		if (mike.withdraw(100)) {
			System.out.println("Successfully returned true");
		}
		
		if (!mike.withdraw(-100)) {
			System.out.println("Successfully returned false for negative");
		}
		
		//tests for Merge		
		Account other = new Account(1000);
		System.out.println(mike.balance());
		System.out.println(other.balance());
		mike.merge(other);
		System.out.println(mike.balance());
		System.out.println(other.balance());
		
		//tests for Overdraft
		Account kathy = new Account(500);
		Account megan = new Account(100, kathy);

		System.out.println("megan balance  " + megan.balance());
		System.out.println("kathy balance " + kathy.balance());
		megan.withdraw(400);
		System.out.println("megan's new balance " + megan.balance());
		System.out.println("kathy's new balance " + kathy.balance());
		
		megan.withdraw(25);
		System.out.println("megan's new balance " + megan.balance());
		System.out.println("kathy's new balance " + kathy.balance());
		
		megan.withdraw(5000);
		System.out.println("megan's new balance " + megan.balance());
		System.out.println("kathy's new balance " + kathy.balance());
		
		
		
		
		
	}

}