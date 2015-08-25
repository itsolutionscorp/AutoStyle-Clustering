public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		//mike.withdraw(200);
		System.out.println(mike.withdraw(200));
		System.out.println(mike.balance());
		Account james = new Account(1000);
		james.merge(mike);
		System.out.println(james.balance());
		System.out.println(mike.balance());
		Account son = new Account(500,james);
		System.out.println(son.withdraw(1000));
		System.out.println(son.balance());
		System.out.println(james.balance());
	}

}