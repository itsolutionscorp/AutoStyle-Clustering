public class AccountTester {

	public static void main(String[] args) {
		Account jane = new Account (2000),
				kathy = new Account (4000, jane),
				mike = new Account (1500, kathy);
				

		System.out.println(mike.balance());
		//mike.deposit(100);
		System.out.println(mike.balance());		
		//mike.merge(jane);
		System.out.println(mike.withdraw(7600));
		System.out.println(mike.balance());
		System.out.println(kathy.balance());
		System.out.println(jane.balance());
		
	}

}