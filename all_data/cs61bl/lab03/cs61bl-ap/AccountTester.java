public class AccountTester {

	public static void main(String[] args) {

		Account grandpa = new Account(1000);
		Account parent = new Account(5000, grandpa);
		Account mike = new Account(1000, parent);
		
		
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(600);
		System.out.println(mike.balance());
		mike.withdraw(2000);
		System.out.println(mike.balance());
		System.out.println(parent.balance());
		mike.withdraw(4000);
		System.out.println(mike.balance());
		System.out.println(parent.balance());
		System.out.println(grandpa.balance());
		mike.withdraw(10000);
		System.out.println(mike.balance());
		System.out.println(parent.balance());
		System.out.println(grandpa.balance());		
	
	}

}