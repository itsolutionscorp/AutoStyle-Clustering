public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		mike.withdraw(-200);
		mike.withdraw(900);
		System.out.println(mike.balance());
		mike.withdraw(100);
		
		Account John = new Account(2500);
		mike.deposit(100);
		mike.merge(John);
		System.out.println(mike.balance());
		System.out.println(John.balance());
		
		Account jeff = new Account(400);
		Account caroline = new Account(200, jeff);
		Account elton = new Account(300, caroline);
		Account kevin = new Account(500, elton);
		kevin.withdraw(100);
		System.out.println(kevin.balance());
		System.out.println(elton.balance());
		System.out.println(caroline.balance());
		System.out.println(jeff.balance());
		kevin.withdraw(800);
		System.out.println(kevin.balance());
		System.out.println(elton.balance());
		System.out.println(caroline.balance());
		System.out.println(jeff.balance());
		kevin.deposit(400);
		System.out.println(kevin.balance());
		System.out.println(elton.balance());
		System.out.println(caroline.balance());
		System.out.println(jeff.balance());
		kevin.withdraw(1000);
		System.out.println(kevin.balance());
		System.out.println(elton.balance());
		System.out.println(caroline.balance());
		System.out.println(jeff.balance());
		
	}

}