public class AccountTester {

	public static void main(String[] args) {
		
		
		testBalance();
		testMerge();
		testParentAccount();
		//System.exit(0);
		
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
	}
	
	
	
	
	public static void testBalance() {
		Account test = new Account(-5);
		System.out.println(test.withdraw(-5) + " should be false");
		test = new Account(10);
		System.out.println(test.withdraw(15) + " should be false");
		test = new Account(10);
		System.out.println(test.withdraw(10) + " should be true");
	}
	
	public static void testMerge() {
		Account acc1 = new Account(10);
		Account acc2 = new Account(15);
		acc1.merge(acc2);
		System.out.println(acc1.balance() + " should be 25");
	}
	
	public static void testParentAccount() {
		
		Account parentParent = new Account(30);
		Account parent = new Account(20, parentParent);
		Account child = new Account(10, parent);
		
		//t1
		child.withdraw(5);
		System.out.println(child.balance() + " should be 5 (child)");
		//t2
		child.withdraw(6);
		System.out.println(child.balance() + " should be 0 (child), "
						 + parent.balance() + " should be 19 (parent)");
		//t3
		child.withdraw(30);
		System.out.println(child.balance() + " should be 0 (child), "
						 + parent.balance() + " should be 0 (parent)"
						 + parentParent.balance() + " should be 19 (parent of parent)");
		//t4
		boolean success = child.withdraw(50);
		System.out.println(success + " should be false (insufficient funds)");
	}

}
