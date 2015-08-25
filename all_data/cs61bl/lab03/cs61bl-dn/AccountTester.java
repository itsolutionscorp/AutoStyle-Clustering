public class AccountTester {

	public static void main(String[] args) {
		test1();
		test2();
		test3();
	}
	
	public static void test1() {
		Account mike;
		mike = new Account(1000);
		
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		System.out.println(mike.withdraw(200));
		System.out.println(mike.balance());
		System.out.println("\n");
	}
	
	public static void test2() {
		Account bob, alice;
		
		bob = new Account(1000);
		alice = new Account(200);
		
		System.out.println(bob.balance());
		System.out.println(alice.balance());
		
		
		bob.merge(alice);
		
		System.out.println(bob.balance());
		System.out.println(alice.balance());
		System.out.println("\n");
	}
	
	public static void test3() {
		Account alice = new Account(200);
		Account bob = new Account(1000, alice);
		
		System.out.println(bob.balance());
		System.out.println(alice.balance());
		
		bob.withdraw(1100);
		
		System.out.println(bob.balance());
		System.out.println(alice.balance());
		System.out.println("\n");
	}

	
	
	
	
}