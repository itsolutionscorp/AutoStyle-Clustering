public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		
		mike.withdraw(-300);
		System.out.println(mike.balance());

		mike.withdraw(3000);
		System.out.println(mike.balance());
		
		Account rachel;
		rachel = new Account(500);
		System.out.println("Rachel's balance is " + rachel.balance() + " before merging.");
		System.out.println("Mike's is " + mike.balance() + ". \nMerging...");
		
		mike.merge(rachel);
		System.out.println("Rachel's balance is " + rachel.balance());
		System.out.println("Mike's balance is " + mike.balance());
		
		Account parent = new Account(600);
		Account child = new Account(500, parent);
		System.out.println("Parent balance: " + parent.balance() + ", Child balance: " + child.balance());
		child.withdraw(50);
		System.out.println("Withdrawing 50 from child.");
		System.out.println("Parent balance: " + parent.balance() + ", Child balance: " + child.balance());
		child.withdraw(600);
		System.out.println("Withdrawing 600 from child.");
		System.out.println("Parent balance: " + parent.balance() + ", Child balance: " + child.balance());
		System.out.println("Withdrawing 600 again from child.");
		child.withdraw(600);
		System.out.println("Parent balance: " + parent.balance() + ", Child balance: " + child.balance());
	}

}