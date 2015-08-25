
public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println("Mike's balance should be $1000");
		System.out.println("Mike's balance is: $" + mike.balance());
		mike.deposit(100);
		System.out.println("Depositing $100");
		System.out.println("Mike's balance should be $1100");
		System.out.println("Mike's balance is: $" + mike.balance());
		mike.withdraw(200);
		System.out.println("Withdrawing $200");
		System.out.println("Mike's balance should be $900");
		System.out.println("Mike's balance is: $" + mike.balance());
		Account bob = new Account(1);
		mike.merge(bob);
		System.out.println("Merging Mike's account with Bob's.");
		System.out.println("Bob's balance should be $0");
		System.out.println("Bob's balance is: $" + bob.balance());
		System.out.println("Mike's balance should be $901");
		System.out.println("Mike's balance is: $" + mike.balance());
		System.out.println("Attempting to withdraw an absurd amount from Mike's account...");
		System.out.println(mike.withdraw(134213421));
		System.out.println("Insufficient funds and false should have been printed");
		Account grandparent = new Account(100);
		Account parent = new Account(50, grandparent);
		Account child = new Account(5, parent);
		System.out.println("A child's account ($5) has been created, with a parent ($50) and a grandparent ($100).");
		System.out.println("Testing the overdraft feature...");
		child.withdraw(154);
		System.out.println("1 0 0 should be printed next.");
		System.out.println(grandparent.balance() + " " + parent.balance() + " " + child.balance());
		child.deposit(5);
		parent.deposit(50);
		grandparent.deposit(99);
		System.out.println("Account balances have been restored");
		child.withdraw(156);
		System.out.println("Insufficient funds should have been printed");
		System.out.println("100 50 5 should be printed next");
		System.out.println(grandparent.balance() + " " + parent.balance() + " " + child.balance());
	}

}