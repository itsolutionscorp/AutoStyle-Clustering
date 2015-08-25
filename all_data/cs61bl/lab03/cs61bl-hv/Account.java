/**
 * This class represents a bank account whose current balance is a nonnegative
 * amount in US dollars.
 */
public class Account {

	
	private Account parentAccount;
	
	public Account(int balance, Account parentAccount) {
		myBalance = balance;
		this.parentAccount = parentAccount;
	}
	
	/**
	 * Initialize an account with the given balance.
	 */
	public Account(int balance) {
		this.myBalance = balance;
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
			
			int needed = amount - myBalance;
			
			if(parentAccount == null) {
				System.out.println("Insufficient funds");
				return false;
			} else {
				myBalance = 0;
				parentAccount.withdraw(needed);
				return true;
			}
		} else {
			this.myBalance = this.myBalance - amount;
			return true;
		}
	}
	
	public void merge(Account acc2) {
		myBalance += acc2.myBalance;
		acc2.myBalance = 0;
	}

	/**
	 * Return the number of dollars in the account.
	 */
	public int balance() {
		return this.myBalance;
	}

	// instance variables
	private int myBalance;

	

	
}
