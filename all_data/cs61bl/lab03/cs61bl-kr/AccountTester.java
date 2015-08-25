public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		mike.withdraw(200000000);
		System.out.println(mike.balance());
		mike.withdraw(-40);
		System.out.println(mike.balance());
		mike.withdraw(0);
		
		Account jose;
		jose = new Account(10);
		
		Account jim;
		jim = new Account(9000);
		jose.merge(jim);
		System.out.println(jose.balance());
		System.out.println(jim.balance());
		jim.merge(jose);
		System.out.println(jim.balance());
		System.out.println(jose.balance());
		
		Account daddy;
		Account son;
		son = new Account(100);
		daddy = new Account(100, son);
		daddy.withdraw(150);
		System.out.println(daddy.balance());
		System.out.println(son.balance());
		
		Account dave;
		dave = new Account(100);
		Account frank;
		frank = new Account(100);
		dave.merge(frank);
		System.out.println(dave.balance());
		System.out.println(frank.balance());
		
	}

}