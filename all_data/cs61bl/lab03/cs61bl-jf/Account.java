/**
 * This class represents a bank account whose current balance is a nonnegative
 * amount in US dollars.
 */
public class Account {

	/**
	 * Initialize an account with the given balance.
	 */
	public Account(int balance) {
		this.myBalance = balance;
		this.parentAccount = null;
	}
	
	public Account(int moneyInA, Account parentAccount) {
		this.myBalance = moneyInA;
		this.parentAccount = parentAccount;
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
	public boolean withdraw(int amount) {
		if (amount < 0) {
			System.out.println("Cannot withdraw negative amount.");
			return false;
		} else if (this.myBalance < amount) {
			
			if (this.parentAccount != null && this.parentAccount.withdraw(amount-this.myBalance) == true) {
				return true;	
			} else {
				System.out.println("Insufficient funds");
				return false;
			}	
		} else {
			this.myBalance = this.myBalance - amount;
			return true;
		}
	}
	
	public void merge(Account anotherAcct) {
		this.myBalance = this.myBalance + anotherAcct.balance();
		anotherAcct.myBalance = 0;
	}

	/**
	 * Return the number of dollars in the account.
	 */
	public int balance() {
		return this.myBalance;
	}

	// instance variables
	private int myBalance;
	public Account parentAccount;

}
