public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		
		//withdraw boolean
		System.out.println(mike.withdraw(200));
		System.out.println(mike.withdraw(10000));
		System.out.println(mike.balance());
		
		//merge
		Account John;
		John = new Account(998);
		mike.merge(John);
		System.out.println(mike.balance());
		System.out.println(John.balance());
		
		//withdraw overdraft
		Account A;
		Account B;
		Account C;
		A = new Account(100);
		B = new Account(200);
		C = new Account(300);
		B.myPA = C;
		A.myPA = B;
		
		A.withdraw(150);
		System.out.println(A.balance());
		System.out.println(B.balance());
		System.out.println(C.balance());
	}

}