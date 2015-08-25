public class AccountTester {

	public static void main(String[] args) {
		/*Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		System.out.println(mike.withdraw(200));
		mike.withdraw(10000);
		System.out.println(mike.withdraw(10000));
		Account A;
		A = new Account(2000);
		Account B;
		B = new Account(1000);
		B.merge(A);
		System.out.println(B.balance());
		System.out.println(A.balance());*/
		/*Account C = new Account(2000);
		Account D = new Account(1000,C);
		System.out.println(D.withdraw(1500));
		System.out.println(C.balance());
		System.out.println(D.balance());*/
		Account E = new Account(2000);
		Account F = new Account(1000,E);
		System.out.println(F.withdraw(15000));
		System.out.println(E.balance());
		System.out.println(F.balance());
	}

}