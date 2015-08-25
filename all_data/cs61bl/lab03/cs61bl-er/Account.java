/**
 * This class represents a bank account whose current balance is a nonnegative
 * amount in US dollars.
 */
public class Account {

	/**
	 * Initialize an account with the given balance.
	 */
	Account parentAccount;
	
	public Account(int balance) {
		this.myBalance = balance;
		this.parentAccount = null;
	}
	public Account(int balance,  Account acc){
		this.myBalance = balance; 
		this.parentAccount = acc;
	}
	
	/**
	 * Add the given amount to the account.
	 */
	public void deposit(int amount) {
		if (amount < 0) {
			System.out.println("Cannot deposit negative amount.");
		} else {
			this.myBalance = this.myBalance + amount;
		}
	}

	/**
	 * Subtract the given amount from the account if possible. If the amount
	 * would leave a negative balance, print an error message and leave the
	 * balance unchanged.
	 */
	public void withdraw(int amount) {
		if (amount < 0) {
			System.out.println("Cannot withdraw negative amount.");
		}else if (this.myBalance < amount && this.parentAccount != null) {
				this.parentAccount.withdraw(amount- this.balance());
				this.withdraw(this.balance());
		}else if (this.balance() < amount && this.parentAccount == null){
			System.out.println(false);
		
		} else {
			this.myBalance = this.myBalance - amount;
			System.out.println(true);
		}
	}

	/**
	 * Return the number of dollars in the account.
	 */
	public int balance() {
		return this.myBalance;
	}
	
	public void merge (Account anotherAcct){
		int other_balance = anotherAcct.balance();
		anotherAcct.withdraw(other_balance);
		this.deposit(other_balance);
	}

	// instance variables
	private int myBalance;

}
