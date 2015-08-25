public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		
		//test cases for withdraw 
		System.out.println(mike.withdraw(1000));
		System.out.println(mike.withdraw(-1000));
		System.out.println(mike.withdraw(300) + " " + mike.balance());
		
		//test cases for merge
		Account a = new Account(500);
		mike.merge(a);
		System.out.println("mike balance: " + mike.balance());
		System.out.println(a.balance());
		
		//test cases for overdraft protection
		Account c = new Account(500);
		Account b = new Account(100, c);
		Account d = new Account(300, b);
		d.withdraw(100);
		System.out.println("b balance: " + b.balance() + " c balance: " + c.balance()
				+ " d balance: " + d.balance());
		
		d.withdraw(200);
		System.out.println("b balance: " + b.balance() + " c balance: " + c.balance()
				+ " d balance: " + d.balance());
		d.withdraw(599);
		System.out.println("b balance: " + b.balance() + " c balance: " + c.balance()
				+ " d balance: " + d.balance());
		d.withdraw(2);
		System.out.println("b balance: " + b.balance() + " c balance: " + c.balance()
				+ " d balance: " + d.balance());
		
		
	}

}