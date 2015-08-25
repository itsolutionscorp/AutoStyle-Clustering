public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		mike.withdraw(-900);
		mike.withdraw(20000);
		mike.withdraw(1);
		
		Account john = new Account(4000);
		mike.merge(john);
		System.out.println(mike.balance());
		
		Account pete_mom = new Account (1000);
		Account pete = new Account (20, pete_mom);
		pete.withdraw(10);
		System.out.println(pete.balance());
		pete.withdraw(50);
		System.out.println(pete_mom.balance());
		pete.withdraw(2000);
	}

}