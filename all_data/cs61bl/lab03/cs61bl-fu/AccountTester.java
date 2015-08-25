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
		System.out.println(mike.withdraw(1000));
		System.out.println(mike.balance());
		
		// Testing merge method
		Account sally = new Account(500);
		mike.merge(sally);
		System.out.println(mike.balance());
		System.out.println(sally.balance());
		
		// Testing parent account overdraft
		
		Account matthew = new Account(500, mike);
		matthew.withdraw(700);
		System.out.println(matthew.balance());
		System.out.println(mike.balance());
		
	}

}