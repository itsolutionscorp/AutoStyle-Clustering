public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		
		System.out.println(mike.withdraw(1000));
		System.out.println(mike.balance());

		System.out.println(mike.withdraw(-1));
		System.out.println(mike.balance());
		
		System.out.println(mike.withdraw(100));
		System.out.println(mike.balance());
		Account john = new Account(500);
		mike.merge(john);
		System.out.println(mike.balance());
		System.out.println(john.balance());
		
		Account steve = new Account(50, mike);
		System.out.println(steve.withdraw(100));
		System.out.println("steve: " + steve.balance());
		System.out.println(mike.balance());
		System.out.println(steve.withdraw(1500));
		System.out.println("steve: " + steve.balance());
		Account mary = new Account(0);
		Account poor = new Account(100, mary);
		Account bad = new Account(50, poor);
		System.out.println(bad.withdraw(200));
		System.out.println("bad:" + bad.balance());
		System.out.println("poor:" + poor.balance());
		System.out.println("mary:" + mary.balance());
		
		Account mary2 = new Account(0);
		Account poor2 = new Account(100, mary2);
		Account bad2 = new Account(50, poor2);
		System.out.println(bad2.withdraw(150));
		System.out.println("bad2:" + bad2.balance());
		System.out.println("poor2:" + poor2.balance());
		System.out.println("mary2:" + mary2.balance());
		
	}

}