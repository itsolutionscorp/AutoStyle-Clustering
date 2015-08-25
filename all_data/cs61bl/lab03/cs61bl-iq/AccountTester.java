public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		
		Account son;
		son = new Account(500, mike);
		son.withdraw(300);
		System.out.println(mike.balance());
		System.out.println(son.balance());
		son.withdraw(400);
		System.out.println(mike.balance());
		System.out.println(son.balance());
		son.withdraw(1000);
		System.out.println(mike.balance());
		System.out.println(son.balance());
		
		mike.withdraw(-5);
		System.out.println(mike.balance());
		mike.withdraw(500);
		System.out.println(mike.balance());
		mike.withdraw(1000);
		System.out.println(mike.balance());
		
		Account hester;
		hester = new Account(2000);
		mike.merge(hester);
		System.out.println(mike.balance());
		
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
	}

}