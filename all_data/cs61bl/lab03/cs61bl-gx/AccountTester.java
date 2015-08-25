public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		Account Bob;
		mike = new Account(1000);
		Bob = new Account(2000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		mike.withdraw(15000);
		mike.withdraw(-10);
		mike.merge(Bob);
		System.out.println(mike.balance());
		System.out.println(Bob.balance());
		Account harry = new Account(1500);
		Account steve = new Account(200, harry);
		steve.withdraw(1800);
		System.out.println(harry.balance());
		System.out.println(steve.balance());
		steve.withdraw(1500);
	}

}