public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		Account mike2;
		mike = new Account(1000);
		mike2 = new Account(1000);
		mike.merge(mike2);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		mike.withdraw(-1);
		if (mike.withdraw(10000) == false) {
			System.out.println("false");
		}
		mike.withdraw(10);
		System.out.println(mike.balance());
		Account bob;
		Account bob2;
		bob = new Account(21000);
		bob2 = new Account(1000, bob);
		bob2.withdraw(2000);
		System.out.println("Bob's balance is " + bob2.balance());
		System.out.println("Bob's dad's balance is " + bob.balance());
		bob2.withdraw(2333333);
		Account bbob;
		bbob = new Account(21000);
		bbob.withdraw(23333333);	
	}
}