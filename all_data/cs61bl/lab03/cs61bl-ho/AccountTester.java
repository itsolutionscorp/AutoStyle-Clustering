public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		Account mary = new Account (2000, mike);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		if(mike.withdraw(200)){
			System.out.println("Successfully withdraw");
		} else {
			System.out.println("fail to withdraw");
		}
		System.out.println(mike.balance());
		System.out.println(mary.balance());
		mike.merge(mary);
		System.out.println(mike.balance());
		System.out.println(mary.balance());
		mary.deposit(2000);
		System.out.println(mary.balance());
		if(mary.withdraw(3000)){
			System.out.println("Successfully withdraw");
		} else {
			System.out.println("fail to withdraw");
		}
		System.out.println(mike.balance());
		System.out.println(mary.balance());
		if(mary.withdraw(2000)){
			System.out.println("Successfully withdraw");
		} else {
			System.out.println("fail to withdraw");
		}
		System.out.println(mike.balance());
		System.out.println(mary.balance());
	}

}