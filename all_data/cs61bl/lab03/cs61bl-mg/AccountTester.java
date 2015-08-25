public class AccountTester {

	public static void main(String[] args) {
		boolean withdrawTest;
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		withdrawTest=mike.withdraw(200);
		System.out.println(withdrawTest);
		System.out.println(mike.balance());
		withdrawTest=mike.withdraw(1000);
		System.out.println(withdrawTest);
		System.out.println(mike.balance());
		withdrawTest=mike.withdraw(-5);
		System.out.println(withdrawTest);
		System.out.println(mike.balance());
		
		Account mergeMe = new Account(1000);
		mike.merge(mergeMe);
		System.out.println(mike.balance()+","+mergeMe.balance());
		
		Account testAccount = new Account(500,mike);
		testAccount.withdraw(600);
		System.out.println(mike.balance()+","+testAccount.balance());
		
	}

}