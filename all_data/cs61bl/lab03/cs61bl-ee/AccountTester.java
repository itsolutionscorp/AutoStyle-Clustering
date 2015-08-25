public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println("mike's account" + " " + mike.balance());
		mike.deposit(100);
		System.out.println("mike's account" + " " + mike.balance());
		mike.withdraw(200);
		System.out.println("mike's account" + " " + mike.balance());
		mike.withdraw(1000);
		System.out.println("mike's account" + " " + mike.balance());
		Account mikeParent=new Account (1000);
		mike = new Account(mike.balance(), mikeParent);
		mike.withdraw(1000);
		System.out.println("parent's account" + " " + mikeParent.balance());
		System.out.println("mike's account" + " " + mike.balance());
		mike.withdraw(1000);
		System.out.println("parent's account" + " " + mikeParent.balance());
		System.out.println("mike's account" + " " + mike.balance());
	}

}