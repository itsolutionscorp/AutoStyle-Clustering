public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		
		//withdraw test
		System.out.println(mike.withdraw(1));
		System.out.println(mike.withdraw(5000));
		
		//merge test
		Account otherAcct = new Account(350);
		mike.merge(otherAcct);
		System.out.println(mike.balance());
		
		//Overdraw test
		Account parent = new Account(50000);
		Account bob = new Account(1000, parent);
		bob.withdraw(bob.balance() + 100);
		System.out.println(bob.balance());
		System.out.println(parent.balance());
		
		//Insufficient Funds
		bob.deposit(500);
		bob.withdraw(100000000);
		System.out.print(bob.balance());
		System.out.print(parent.balance());
		
	}

}