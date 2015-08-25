public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		
		/* Test cases for withdraw version 2 (recursive) */
		Account bob = new Account (100, mike);
		bob.withdraw(150);
		Account job = new Account (100, bob);
		System.out.println(bob.balance());
		System.out.println(mike.balance());
		bob.deposit(500);
		job.withdraw(2000);
		System.out.println(job.balance());
		System.out.println(bob.balance());
		System.out.println(mike.balance());
		
		/* Test cases for withdraw version 1 */
		if (mike.withdraw(1000)) {
			System.out.println("code is incorrect");
		} else {
			System.out.println("unsuccessful withdrawal");
		}
		if (mike.withdraw(800)) {
			System.out.println("successful withdrawal");
		} else {
			System.out.println("code is incorrect");
		}
				
		/* Test cases for merging accounts */
		System.out.println(job.balance());
		System.out.println(bob.balance());
		System.out.println(mike.balance());
		mike.merge(job);
		System.out.println(job.balance());
		System.out.println(bob.balance());
		System.out.println(mike.balance());
		
	}

}