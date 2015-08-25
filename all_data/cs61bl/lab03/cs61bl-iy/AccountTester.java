public class AccountTester {

	public static void main(String[] args) {
		/**
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance()); */
		
		/** // Test new withdraw method
		System.out.println(mike.withdraw(2000));
		System.out.println(mike.withdraw(-100));
		System.out.println(mike.withdraw(100)); */
		
		/** // Test new merge method
		Account sarah = new Account(1000);
		System.out.println("Mike's Initial Balance: " + mike.balance());
		System.out.println("Sarah's Initial Balance: " + sarah.balance());
		mike.merge(sarah);
		System.out.println("Mike's Final Balance: " + mike.balance());
		System.out.println("Sarah's Final Balance: " + sarah.balance()); */
		
		// Test new withdraw method (Overdraft Protection)
		
		// CASE 1
		Account kathy1 = new Account(500);
		Account megan1 = new Account(100,kathy1);
		System.out.println("Kathy1's Initial Balance: " + kathy1.balance());
		System.out.println("Megan1's Initial Balance: " + megan1.balance());
		System.out.println("Megan1 withdraws $50.");
		megan1.withdraw(50);
		System.out.println("Kathy1's Final Balance: " + kathy1.balance());
		System.out.println("Megan1's Final Balance: " + megan1.balance());
		
		System.out.println("");
		
		// CASE 2
		Account kathy2 = new Account(500);
		Account megan2 = new Account(100,kathy2);
		System.out.println("Kathy2's Initial Balance: " + kathy2.balance());
		System.out.println("Megan2's Initial Balance: " + megan2.balance());
		System.out.println("Megan2 withdraws $200.");
		megan2.withdraw(200);
		System.out.println("Kathy2's Final Balance: " + kathy2.balance());
		System.out.println("Megan2's Final Balance: " + megan2.balance());
		
		System.out.println("");
		
		// CASE 3
		Account kathy3 = new Account(500);
		Account megan3 = new Account(100,kathy3);
		System.out.println("Kathy3's Initial Balance: " + kathy3.balance());
		System.out.println("Megan3's Initial Balance: " + megan3.balance());
		System.out.println("Megan3 withdraws $700.");
		megan3.withdraw(700);
		System.out.println("Kathy3's Final Balance: " + kathy3.balance());
		System.out.println("Megan3's Final Balance: " + megan3.balance());
		
		System.out.println("");
		
		// CASE 4
		Account sarah4 = new Account(800);
		Account kathy4 = new Account(500,sarah4);
		Account megan4 = new Account(100,kathy4);
		System.out.println("Sarah4's Initial Balance: " + sarah4.balance());
		System.out.println("Kathy4's Initial Balance: " + kathy4.balance());
		System.out.println("Megan4's Initial Balance: " + megan4.balance());
		System.out.println("Megan4 withdraws $700.");
		megan4.withdraw(700);
		System.out.println("Sarah4's Final Balance: " + sarah4.balance());
		System.out.println("Kathy4's Final Balance: " + kathy4.balance());
		System.out.println("Megan4's Final Balance: " + megan4.balance());
	}

}