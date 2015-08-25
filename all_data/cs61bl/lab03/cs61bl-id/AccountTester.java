public class AccountTester {

	public static void main(String[] args) {
		//test account creation
		System.out.println("Testing account creation...");
		Account mike;
		mike = new Account(1000);
		System.out.println("Mike's Balance:" + mike.balance());
		System.out.println();
		//test deposit method
		System.out.println("Testing deposit method...");
		mike.deposit(100);
		System.out.println("Mike's Balance:" + mike.balance());
		System.out.println();
		//test withdraw method
		System.out.println("Testing deposit method...");
		mike.withdraw(200);
		System.out.println("Mike's Balance:" + mike.balance());
		System.out.println("Testing deposit method, balance exceeded...");
		mike.withdraw(1000);
		System.out.println("Mike's Balance:" + mike.balance());
		System.out.println();
		//test merge method
		System.out.println("New Account Created: Matt");
		Account matt;
		matt = new Account(300);
		System.out.println("Matt's balance:" + matt.balance());
		System.out.println("Testing merge method...");
		matt.merge(mike);
		System.out.println("Matt's balance:" + matt.balance());
		System.out.println("Mike's Balance:" + mike.balance());
		System.out.println();
		//test overdraft protection modifications
		System.out.println("New Account Created: Murphy; Parent: Matt");
		Account murphy;
		murphy = new Account(200, matt);
		System.out.println("Murphy's balance:" + murphy.balance());
		System.out.println("Matt's balance:" + matt.balance());
		System.out.println("Standard withdrawal");
		murphy.withdraw(100);
		System.out.println("Murphy's balance:" + murphy.balance());
		System.out.println("Matt's balance:" + matt.balance());
		System.out.println("Testing overdraft protection...");
		murphy.withdraw(200);
		System.out.println("Murphy's balance:" + murphy.balance());
		System.out.println("Matt's balance:" + matt.balance());
	}

}