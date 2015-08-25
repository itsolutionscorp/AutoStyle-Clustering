public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println("First balance of mike is " + mike.balance());
		mike.deposit(100);
		System.out.println("After depositing 100, mike has " + mike.balance());
		//Added tests
		System.out.println("Mike can withdraw 200: " + mike.withdraw(200));
		System.out.println("Mike's balance after withdrawing 200: " + mike.balance()); //prints 900
		Account bob = new Account(10, mike);
		System.out.println("Bob has " + bob.balance());
		System.out.println("Bob's parent has " + bob.parentAccount.balance()); //prints 900
		bob.withdraw(50);
		System.out.println("After withdrawing 50 from Bob, Mike has " +mike.balance()); //prints 860
		bob.deposit(100);
		System.out.println("After depositing 100 to bob, bob has " + bob.balance()); //prints 100
		mike.merge(bob);
		System.out.println("After merging Mike and Bob, Mike has " + mike.balance()); //prints 960
		System.out.println("After merging, Bob has " + bob.balance());	//prints 0
	}

}