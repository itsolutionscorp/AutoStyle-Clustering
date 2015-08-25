public class AccountTester {

	public static void main(String[] args) {
		/*Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		
		//Tests for modified return type for withdraw behavior
		System.out.println("When we try withdrawing 1000, the withdraw mehtod returns " + mike.withdraw(1000)); 
		System.out.println(mike.balance());
		System.out.println("When we try withdrawing 500, the withdraw mehtod returns " + mike.withdraw(500));
		System.out.println(mike.balance());
		System.out.println("When we try withdrawing -500, the withdraw mehtod returns " + mike.withdraw(-500));
		System.out.println(mike.balance());
		
		//Tests for mergeAccounts method
		System.out.println("Mike's Balance is " + mike.balance());
		Account bob = new Account (1000);
		System.out.println("Merged Bob into Mike");
		System.out.println("Bob's Balance is " + bob.balance());
		mike.merge(bob);
		System.out.println("Mike's Balance is " + mike.balance());
		System.out.println("Bob's Balance is " + bob.balance());  */
		
		//Tests for parent account overdraw
		Account kathy, megan;
		megan = new Account(100);
		kathy = new Account(500, megan);
		System.out.println("Withdraw 50 ");
		kathy.withdraw(50);
		System.out.println("Kathy's balance is " + kathy.balance());
		System.out.println("Megan's balance is " + megan.balance());
		System.out.println("Withdraw -50 ");
		kathy.withdraw(-50);
		System.out.println("Kathy's balance is " + kathy.balance());
		System.out.println("Megan's balance is " + megan.balance());
		System.out.println("Withdraw 700 ");
		kathy.withdraw(700);
		System.out.println("Kathy's balance is " + kathy.balance());
		System.out.println("Megan's balance is " + megan.balance());
		System.out.println("Withdraw 550 ");
		kathy.withdraw(550);
		System.out.println("Kathy's balance is " + kathy.balance());
		System.out.println("Megan's balance is " + megan.balance());
		
		
	}

}