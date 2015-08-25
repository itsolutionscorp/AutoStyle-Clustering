public class AccountTester {

	public static void main(String[] args) {
			
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		
		
		Account joe;
		joe = new Account(500);
		joe.withdraw(200);
		System.out.println(joe.balance());
		joe.withdraw(100);
		System.out.println(joe.balance());
		joe.withdraw(400);
		System.out.println(joe.balance());
		
		
		joe.merge(mike);
		System.out.println(joe.balance());
		System.out.println(mike.balance());
		
		mike.parentAcct(joe);
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(500);
		System.out.println(mike.balance());
		System.out.println(joe.balance());
		
	}

}