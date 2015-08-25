public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		
		mike.withdraw(901);
		System.out.println(mike.balance());
		
		mike.withdraw(-1);
		System.out.println(mike.balance());
		
		Account friend = new Account(1000);
		System.out.println(friend.balance());
		System.out.println(mike.balance());
		
		mike.merge(friend);
		System.out.println(friend.balance());
		System.out.println(mike.balance());
		
		Account Kathy = new Account(500);
		Account Megan = new Account(100, Kathy);
		Megan.withdraw(50);
		System.out.println(Megan.balance());
		System.out.println(Kathy.balance());
		
		Kathy = new Account(500);
		Megan = new Account(100, Kathy);
		Megan.withdraw(200);
		System.out.println(Megan.balance());
		System.out.println(Kathy.balance());
		
		Kathy = new Account(500);
		Megan = new Account(100, Kathy);
		Megan.withdraw(700);
		System.out.println(Megan.balance());
		System.out.println(Kathy .balance());
		
	}

}