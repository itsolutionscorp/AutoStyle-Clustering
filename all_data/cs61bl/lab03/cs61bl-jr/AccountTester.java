public class AccountTester {

	public static void main(String[] args) {
		Account alex = new Account (300);
		Account minho = new Account (20);
		Account mike = new Account(100,alex);

		System.out.println("mike balance = " + mike.balance());
		System.out.println("alex balance = " + alex.balance());
		System.out.println("minho balance = " + minho.balance());
		System.out.println();
		
		System.out.println("$100 will be deposited into mike's account");
		mike.deposit(100);
		System.out.println("mike balance = " + mike.balance());
		System.out.println();
		
		System.out.println("$50 will be withdrawn from mike's account");
		System.out.println(mike.withdraw(50));
		System.out.println("mike balance = " + mike.balance());
		System.out.println();
		
		System.out.println("mike's account will be merged with minho's account");
		mike.merge (minho);
		System.out.println("mike balance = " + mike.balance());
		System.out.println("minho balance = " + minho.balance()); 
		System.out.println();
		
		System.out.println("$50 more than mike's balance will be withdrawn");
		System.out.println(mike.withdraw(220));
		System.out.println("mike balance = " + mike.balance());
		System.out.println("alex balance = " + alex.balance()); 
		System.out.println();
		
		System.out.println("$300 will be withdrawn from alex's account");
		System.out.println(alex.withdraw(300));
		System.out.println("alex balance = " + alex.balance()); 
		System.out.println();
	}
}