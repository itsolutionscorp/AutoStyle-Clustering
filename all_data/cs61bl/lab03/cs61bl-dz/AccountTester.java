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
				
		Account john;
		john = new Account(1000);
		
		mike.merge(john);
		System.out.println("Mike's Balance after merging with John: " + mike.balance());
		System.out.println("John's New Balance: " + john.balance());
		
		Account kathy;
		kathy = new Account(500);
		Account megan;
		megan = new Account(100, kathy);
		System.out.println("Kathy's Balance: " + kathy.balance());
		System.out.println("Megan's Balance: " + megan.balance());
		megan.withdraw(50);
		System.out.println("Withdraw 50");
		System.out.println("Kathy's Balance: " + kathy.balance());
		System.out.println("Megan's Balance: " + megan.balance());
		
		kathy.setMyBalance(500);
		megan.setMyBalance(100);
		megan.withdraw(200);
		System.out.println("Withdraw 200");
		System.out.println("Kathy's Balance: " + kathy.balance());
		System.out.println("Megan's Balance: " + megan.balance());
		
		kathy.setMyBalance(500);
		megan.setMyBalance(100);
		megan.withdraw(700);
		System.out.println("Withdraw 700");
		System.out.println("Kathy's Balance: " + kathy.balance());
		System.out.println("Megan's Balance: " + megan.balance());		
		
	}

}