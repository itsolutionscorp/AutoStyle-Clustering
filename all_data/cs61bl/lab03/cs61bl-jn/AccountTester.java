public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		
		System.out.println(mike.balance());
		System.out.println(mike.withdraw(200));
		System.out.println(mike.balance());
		
		
		Account Jerry;
		Jerry = new Account(1000);
		mike.merge(Jerry);
		System.out.println(Jerry.balance());
		System.out.println(mike.balance());
		
		Account Alice=new Account(1000,mike);
		System.out.println("Alice balance = " + Alice.balance());
		Alice.withdraw(500);
		System.out.println(Alice.balance());
		System.out.println(mike.balance());
		Alice.withdraw(1300);
		System.out.println(Alice.balance());
		System.out.println("mike = "+mike.balance());
		Alice.withdraw(9000);
		System.out.println("Alice balance = " + Alice.balance());
		System.out.println(mike.balance());
		
	}

}